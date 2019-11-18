<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>压力传感器实时监测数据</title>
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
				<input type="text" id="ssjc_wl_qy_name" name="ssjc_wl_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/wdcgq/qyjson',prompt: '企业名称',
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
				<button class="btn btn-success btn-sm"  onclick="view()"><i class="fa fa-search-plus"></i> 查看</button> 
	            <button class="btn btn-warning btn-sm" onclick="fileexport()"><i class="fa fa-external-link"></i> 导出</button>
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
			<div title="图形显示" style="height:100%;">
				<div style="height: 99.5%">
					<iframe src="${ctx}/zxjkyj/ylcgq/view/${qyid}" frameborder="0" width="100%" height="100%"></iframe>
				</div>
			</div>
			<div title="列表显示" style="height:100%;" data-options="">
				<div id="ssjc_wl_tb" style="padding:5px;height:auto">
					<div class="row">
						<div class="col-sm-12">
							<div class="pull-left">
					           	<button class="btn btn-warning btn-sm" onclick="fileexport()"><i class="fa fa-external-link"></i> 导出</button>
					           	<button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
							</div>
						</div>
					</div> 
				</div>
				<table id="ssjc_wl_dg"></table> 
				<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
					<table id="qyDatagrid">	</table>
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
    url:ctx+'/zxjkyj/ylcgq/list',
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
        {field:'qyname',title:'企业名称',sortable:false,width:150,align:'center'},    
        {field:'wh',title:'储罐位号',sortable:false,width:80,align:'center',
        	formatter : function(value, row, index) {
     		if (value==null||value=='')
     			return '/';
     		else
     			return value;
     	}},    
        {field:'wl',title:'存储物料名称',sortable:false,width:100,align:'center'},
        {field:'lx',title:'储罐类型',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='立式圆筒形储罐';
            	if(value=='2') return value='卧式圆筒形储罐';
				if(value=='3') return value='球形储罐';
				if(value=='4') return value='其他储罐'; 
				if(value==null) return value='仓库';
        	}      
        },
        {field:'rj',title:'容积(m³)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},    
        {field:'gh',title:'罐高(m)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},
        {field:'gj',title:'罐径(m)',sortable:false,width:60,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value;
     	}},
     	{field:'ylcgqbh',title:'压力传感器编码',sortable:false,width:80,align:'center'},
        {field:'bjyl',title:'报警压力(kPa)',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
         		if (value==null || value == 0.00)
         			return '/';
         		else
         			return value.toFixed(2);
         	},
			styler: function(value){
				if (value != null && value != 0.00) {
					return 'background-color:red;color:white';
				}
			}
     	},
     	 
        {field:'ylbjsj',title:'压力报警时间',sortable:false,width:80,align:'center',
			formatter: function(value){
        		if (value) {
					var datetime = new Date(value);
					return datetime.format("yyyy-MM-dd hh:mm:ss");
				} else {
        			return '/';
				}

			},
			styler: function(value){
        		if (value) {
        			return 'background-color:red;color:white';
				}
			}
		},
        {field:'ssyl',title:'实时压力(kPa)',sortable:false,width:80,align:'center',
     		formatter : function(value, row, index) {
         		if (value==null)
         			return '/';
         		else
         			return value.toFixed(2);
         	}
     	},
     	{field:'cjsj',title:'更新时间',sortable:false,width:100,align:'center',
         	formatter : function(value) {
         		var datetime = new Date(value);
                return datetime.format("yyyy-MM-dd hh:mm:ss");
     	}}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	if(usertype!='1' )
    		showqycg(rowindex.qyid);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    rowStyler:function(index,row){
		if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
			return 'background-color:rgb(232, 190, 101);';
		}
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
	$('#ssjc_wl_qy_name').combobox('setValue',$('#ssjc_wl_qy_name').combobox('getText') ) ;
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//datagrid刷新
function refresh(){
	dg.datagrid('reload'); 
}


//显示企业储罐信息页面
function showqycg(n){
	openDialogView("实时储量",ctx+'/zxjkyj/wdcgq/view/'+n,"100%", "100%","");
}


//显示企业储罐信息页面
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	showqycg(row.qyid);
}


//导出选择企业页面
function fileexport(){
	if(usertype=='1'){
		window.open('${ctx}/zxjkyj/wdcgq/export?ids=');
	}else{
		
	layer.open({
	    type: 1,  
	    area: ['400px', '400px'],
	    title: '选择企业',
        shift: 5,
        shade :0,
	    content: $('#selectQyPanel'),
	    success: function(layero, index){
	    	loadSelectDatagrid();
	    },
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var row = $('#qyDatagrid').datagrid('getChecked');

	    	var ids="";
	    	for(var i=0;i<row.length;i++){
	    		if(ids==""){
	    			ids=row[i].id;
	    		}else{
	    			ids=ids+","+row[i].id;
	    		}
	    	}
	    	window.open('${ctx}/zxjkyj/wdcgq/export?ids='+ids);
		  },
		cancel: function(index){ 
	     }
	    
	});
	
	}
}

//加载企业名称
function loadSelectDatagrid(){
	$('#qyDatagrid').datagrid({
	    url:'${ctx}/zxjkyj/wdcgq/qyjson',
	    fit : true,
	    scrollbarSize:0,
		fitColumns : true,
		rownumbers:true,
	    columns:[[
			{field:'id',title:'id',checkbox:true},
			{field:'m1',title:'企业名称',width:100}
	    ]]
	});
}


</script>
</body>
</html>