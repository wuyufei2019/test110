<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>气体浓度实时监测数据</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<!-- 安监和集团公司界面 -->
<c:if test="${usertype != '1'}">
	<div id="ssjc_qtnd_tb" style="padding:5px;height:auto">
		<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<input type="text" id="ssjc_qtnd_qy_name" name="ssjc_qtnd_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/zxjkyj/zdwxyqt/qyjson',prompt: '企业名称',
					onLoadSuccess:function(data){
						var text='接入实时数据企业数：'+data.length;
						$('#shuzi').append(text);
					}
				 "/>
				</form>
	        </div>
			<div class="pull-left">
				<button  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</button>
				<button  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
				<button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				<label id="shuzi" style="font-size: 15px;color:#FF00FF"></label>
			</div>
		</div>
		</div> 
	</div>
	
	<table id="ssjc_qtnd_dg"></table> 
	
	<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
		<table id="qyDatagrid">	</table>
	</div>
</c:if>

<!-- 企业界面（非集团公司） -->
<c:if test="${usertype == '1'}">
<div class="easyui-tabs" fit="true">
	<div title="列表显示" style="height:100%;" data-options="">
		<div id="ssjc_qtnd_tb" style="padding:5px;height:auto">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<div class="form-group">
					<input name="cgqname" id="cgqname" class="easyui-combobox" style="height: 30px;"
						   data-options="prompt: '储罐区名称',editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/cgqxx/json'" />
					<input name="dwh" id="dwh" class="easyui-textbox" style="height: 30px;"
						   data-options="prompt: '点位号'" />
					<input name="type" id="type" class="easyui-combobox" style="height: 30px;"
						   data-options="prompt: '气体类型',editable:false,panelHeight:'auto',data:[
							   {value:'YDQT',text:'有毒气体'},
							   {value:'KRQT',text:'可燃气体'}]" />
					<input name="starttime" id="starttime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '采集时间'" />~
					<input name="endtime" id="endtime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '采集时间'" />
					<span  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
					<span class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</span>
				</div>
			</form>
		</div>
		<table id="ssjc_qtnd_dg"></table>

		<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
			<table id="qyDatagrid">	</table>
		</div>
	</div>
	<div title="图形显示" style="height:100%;">
		<div style="height: 99.5%">
			<iframe src="${ctx}/zxjkyj/zdwxyqt/view/${qyid}" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</div>
</div>
</c:if>
<script type="text/javascript" >
var usertype=${usertype};
var dg;
var d;

$(function(){   
	dg=$('#ssjc_qtnd_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/zxjkyj/zdwxyqt/list',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[
		{field:'qyname',title:'企业名称',sortable:false,width:120,align:'center'},
		{field:'cgqmc',title:'储罐区名称',sortable:false,width:100,align:'center'},
		{field:'bit_no',title:'点位号',sortable:false,width:80,align:'center'},
		{field:'target_type',title:'气体类型',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
         		if (value == 'YDQT') {
         			return '有毒气体';
         		} else if(value == 'KRQT') {
         			return '可燃气体';
         		}
         	}
        },
        {field:'alarm_value',title:'是否报警',sortable:false,width:80,align:'center',
			formatter : function(value, row, index) {
				if (row.alarm_time == null || row.alarm_time == '')
					return '正常';
				else{
					return value.toFixed(2) + ' ' +row.unit;
				}
			}
		},
     	{field:'value',title:'监测数据',sortable:false,width:100,align:'center',
        	formatter : function(value, row, index) {
        		if (value!=null){
					return value.toFixed(2) + ' ' +row.unit;
        		}
        		else
         			return '/';
         	}
        },
        {field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
			formatter : function(value, row, index) {
				if(value!=null&&value!='') {
					var datetime=new Date(value);
					return datetime.format('yyyy-MM-dd hh:mm:ss');
				}
			}
        },
		{field:'zt',title:'状态',sortable:false,width:80,align:'center',
			formatter : function(value) {
				var title = '';
				var imgUrl = '';
				if (value == '0') {
					title = '在线';
					imgUrl = ctx + '/static/model/images/hgqy/zx.png';
				} else {
					title = '离线';
					imgUrl = ctx + '/static/model/images/hgqy/lx.png';
				}
				return '<img src="'+imgUrl+'" title="'+title+'"/>';
			}
		},
     	{field:'cz',title:'操作',sortable:false,width:150,align:'center',
     		formatter : function(value, row, index) {
     			var a = "";
				a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick=viewxq(" + row.id + ",'"+row.target_type+"')>详情</a>";
				a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick=viewbjxx(" + row.id + ",'"+row.target_type+"')>报警信息</a>";
				a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt(" + row.id + ",'"+row.target_type+"')>历史波动图</a>";
				return a;
     		}
     	}
        
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	/*if(usertype!='1' )
    		showqycg(rowindex.qyid);
    	else
    		viewxq(rowindex.dwh);*/
		viewxq(rowindex.areaid, rowindex.target_type, rowindex.id);
    },
    onLoadSuccess: function(){
		$(this).datagrid("autoMergeCells",['qyname', 'sbname']);
		if (usertype == '1') {
			$(this).datagrid("hideColumn",['qyname']);
		}
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    rowStyler:function(index,row){
		/*if (row.zt == '1'){
			return 'background-color:rgb(232, 190, 101);';
		}*/
	},
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#ssjc_qtnd_tb'
	});
  
});

timerID = setInterval("refresh()",60000);

//显示企业有毒气体信息页面
function showqycg(n){
	openDialogView("气体实时浓度",ctx+'/zxjkyj/zdwxyqt/view/'+n,"100%", "100%","");
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
//查询
function search(){
	//$('#ssjc_qtnd_qy_name').combobox('setValue',$('#ssjc_qtnd_qy_name').combobox('getText') ) ;
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//datagrid刷新
function refresh(){
dg.datagrid('reload'); 
}

//查看详情
function viewxq(pointid,jctype){
	openDialogView("查看气体实时监测信息",ctx+"/zxjkyj/zdwxyqt/viewxq/"+pointid+"/"+jctype,"900px", "450px","");
	
}

//查看报警信息
function viewbjxx(id,jctype){
	openDialogView("查看气体报警信息",ctx+"/zxjkyj/zdwxyqt/viewbjxx/"+id+"/"+jctype,"800px", "400px","");
	
}

//查看波动图信息
function viewbdt(id,jctype){
	openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxyqt/viewbdtindex/"+id+"/"+jctype,"100%", "100%","");
	
}
</script>
</body>
</html>