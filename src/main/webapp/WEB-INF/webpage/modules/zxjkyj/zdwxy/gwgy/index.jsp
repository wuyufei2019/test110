<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>重大危险源高危工艺实时监测数据</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<!-- 安监和集团公司界面 -->
<c:if test="${usertype != '1'}">
	<div id="ssjc_wl_tb" style="padding:5px;height:auto">
		<div class="row">
		<div class="col-sm-12">
		
			<div class="pull-left">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<input type="text" id="ssjc_wl_qy_name" name="ssjc_wl_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/zxjkyj/zdwxygwgy/qyjson',prompt: '企业名称',
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
	
	<table id="ssjc_wl_dg"></table> 
	
	
	<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
		<table id="qyDatagrid">	</table>
	</div>
</c:if>

<!-- 企业界面（非集团公司） -->
<c:if test="${usertype == '1'}">
	<div class="easyui-tabs" fit="true">
			<div title="列表显示" style="height:100%;" data-options="">
				<div id="ssjc_wl_tb" style="padding:5px;height:auto">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
						<div class="form-group">
							<input name="gwgyname" id="gwgyname" class="easyui-combobox" style="height: 30px;"
								   data-options="prompt: '工艺名称',editable:false,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/gwgy'" />
							<input name="dwh" id="dwh" class="easyui-textbox" style="height: 30px;"
								   data-options="prompt: '点位号'" />
							<input name="type" id="type" class="easyui-combobox" style="height: 30px;"
								   data-options="prompt: '监测类型',editable:false,panelHeight:'auto',data:[
									   {value:'WD',text:'温度'},
									   {value:'YL',text:'压力'},
									   {value:'YW',text:'液位'}]" />
							<input name="starttime" id="starttime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '采集时间'" />~
							<input name="endtime" id="endtime" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '采集时间'" />
							<span  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
							<span  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
							<span class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</span>
						</div>
					</form>
				</div>
				<table id="ssjc_wl_dg"></table>

				<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
					<table id="qyDatagrid">	</table>
				</div>
			</div>
			
			<div title="图形显示" style="height:100%;">
				<div style="height: 99.5%">
					<iframe src="${ctx}/zxjkyj/zdwxygwgy/view/${qyid}" frameborder="0" width="100%" height="100%"></iframe>
				</div>
			</div>
	</div>
</c:if>

<script type="text/javascript" >
var usertype=${usertype};
var dg;
var d;

$(function(){   
	dg=$('#ssjc_wl_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/zxjkyj/zdwxygwgy/list',
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
     	{field:'gwgyname',title:'工艺名称',sortable:false,width:60,align:'center'},
		{field:'bit_no',title:'点位号',sortable:false,width:120,align:'center'},
		{field:'kzfs',title:'控制方式',sortable:false,width:80,align:'center'},
        {field:'target_type',title:'监测类型',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value == 'WD')
         			return '温度';
         		else if (value == 'YL')
         			return '压力';
         		else  if (value == 'YW')
         			return '液位';
         	}
     	},
     	{field:'alarm_value',title:'是否报警',sortable:false,width:80,align:'center',
			formatter : function(value, row, index) {
				if (row.alarm_time == null || row.alarm_time == ''){
					return '正常';
				}else{
					return value.toFixed(2) + ' ' +row.unit;
				}
			},
			styler: function(value, row){
				if (row.alarm_time != null && row.alarm_time != '') {
					return 'background-color:red;color:white';
				}
			}
		},
        {field:'value',title:'监测数据',sortable:false,width:80,align:'center',
     		formatter : function(value, row, index) {
				if (value == null) {
					return '/';
				} else {
					return value.toFixed(2) + ' ' +row.unit;
				}
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
		{field:'zt',title:'状态',sortable:false,width:100,align:'center',
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
				a+= "<a style='margin:2px' class='btn btn-info btn-xs' onclick=viewxq(" + row.gwgyid + ",'"+row.target_type+"'," + row.id + ")>详情</a>";
				a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick=viewbjxx(" + row.id + ",'"+row.target_type+"')>报警信息</a>";
				a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt(" + row.id + ",'"+row.target_type+"')>历史波动图</a>";
				return a;
     		}
     	}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	if(usertype!='1' )
    		showqycg(rowindex.qyid);
    	else
    		viewxq(rowindex.dwh);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname','wh','wl','lx']);
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
    toolbar:'#ssjc_wl_tb'
	});
  
});

timerID = setInterval("refresh()",60000);

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
//查询
function search(){
	/*$('#ssjc_wl_qy_name').combobox('setValue',$('#ssjc_wl_qy_name').combobox('getText') ) ;*/
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//datagrid刷新
function refresh(){
	dg.datagrid('reload'); 
}


//显示企业高危工艺信息页面
function showqycg(n){
	openDialogView("实时储量",ctx+'/zxjkyj/zdwxygwgy/view/'+n,"100%", "100%","");
}

//查看详情
function viewxq(gwgyid,jctype,pointid){
	openDialogView("查看高危工艺实时监测信息",ctx+"/zxjkyj/zdwxygwgy/viewxq/"+gwgyid+"/"+jctype+"?pointid="+pointid,"1000px", "80%","");
}

//查看报警信息
function viewbjxx(id,jctype){
	openDialogView("查看高危工艺报警信息",ctx+"/zxjkyj/zdwxygwgy/viewbjxx/"+id+"/"+jctype,"800px", "400px","");
}

//查看波动图信息
function viewbdt(id,jctype){
	openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxygwgy/viewbdtindex/"+id+"/"+jctype,"100%", "100%","");
}
</script>
</body>
</html>