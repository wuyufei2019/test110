<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高位工艺实时监测数据</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<!-- 安监和集团公司界面 -->
<c:if test="${usertype != '1'}">
	<div id="ssjc_gwgy_tb" style="padding:5px;height:auto">
		<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				<input type="text" id="ssjc_gwgy_qy_name" name="ssjc_gwgy_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/gwgyjc/qyjson',prompt: '企业名称',
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
	            <button class="btn btn-warning btn-sm" onclick="fileexport()"><i class="fa fa-external-link"></i> 导出</button>
	            <button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				<label id="shuzi" style="font-size: 15px;color:#FF00FF"></label>
			</div>
		</div>
		</div> 
	</div>
	
	<table id="ssjc_gwgy_dg"></table> 
	
	<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
		<table id="qyDatagrid">	</table>
	</div>
</c:if>

<!-- 企业界面（非集团公司） -->
<c:if test="${usertype == '1'}">
	<div class="easyui-tabs" fit="true">
	<div title="图形显示" style="height:100%;">
		<div style="height: 99.5%">
			<iframe src="${ctx}/zxjkyj/gwgyjc/view/${qyid}" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</div>
	<div title="列表显示" style="height:100%;" data-options="">
		<div id="ssjc_gwgy_tb" style="padding:5px;height:auto">
			<div class="row">
			<div class="col-sm-12">
			       
				<div class="pull-left">
			           <button class="btn btn-warning btn-sm" onclick="fileexport()"><i class="fa fa-external-link"></i> 导出</button>
			           <button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
				</div>
			</div> 
		</div>
	
		<table id="ssjc_gwgy_dg"></table> 
	
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
	dg=$('#ssjc_gwgy_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/zxjkyj/gwgyjc/list',
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
        {field:'name',title:'高危工艺名称',sortable:false,width:100,align:'center'},    
        {field:'wl',title:'反应物料',sortable:false,width:100,align:'center'},
        {field:'rj',title:'容积(m³)',sortable:false,width:100,align:'center' ,
        	formatter : function(value, row, index) {
     		if (value==null||value=='')
     			return 'N/A';
     		else
     			return value;
 		}},
 		{field:'level',title:'液位',sortable:false,width:100,align:'center',
 			formatter : function(value, row, index) {
 	     		if (value==null||value=='')
 	     			return 'N/A';
 	     		else
 	     			return value;
 	 		}},
 		{field:'innertemp',title:'釜内温度(℃)',sortable:false,width:100,align:'center',
 			formatter : function(value, row, index) {
 	     		if (value==null||value=='')
 	     			return 'N/A';
 	     		else
 	     			return value;
 	 		}},
 		{field:'ourtertemp',title:'夹套温度(℃)',sortable:false,width:100,align:'center',
 			formatter : function(value, row, index) {
 	     		if (value==null||value=='')
 	     			return 'N/A';
 	     		else
 	     			return value;
 	 		}},
        {field:'pressure',title:'压力(MPa)',sortable:false,width:100,align:'center' ,
        	formatter : function(value, row, index) {
         		if (value==null||value=='')
         			return 'N/A';
         		else
         			return value;
     		}},    
        {field:'flux',title:'流量(m3/h)',sortable:false,width:100,align:'center' ,
        	formatter : function(value, row, index) {
         		if (value==null||value=='')
         			return 'N/A';
         		else
         			return value;
     		}},
        {field:'weight',title:'称重(kg)',sortable:false,width:100,align:'center' ,
        	formatter : function(value, row, index) {
         		if (value==null||value=='')
         			return 'N/A';
         		else
         			return value;
     		}},
     	{field:'current',title:'搅拌电流(A)',sortable:false,width:80,align:'center' ,
     		formatter : function(value, row, index) {
         		if (value==null||value=='')
         			return 'N/A';
         		else
         			return value;
     		}},
     	{field:'ph',title:'PH值',sortable:false,width:80,align:'center' ,
     		formatter : function(value, row, index) {
         		if (value==null||value=='')
         			return 'N/A';
         		else
         			return value;
     		}},
     	{field:'colltime',title:'更新时间',sortable:false,width:100,align:'center',
             	formatter : function(value, row, index) {
             		var datetime = new Date(value);  
                    return datetime.format("yyyy-MM-dd hh:mm:ss");
         	}}
        
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	if(usertype!='1' )
    		show(rowindex.qyid);
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
     
    rowStyler:function(index,row){
		if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
			return 'background-color:rgb(232, 190, 101);';
		}
	},
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#ssjc_gwgy_tb'
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
	$('#ssjc_gwgy_qy_name').combobox('setValue',$('#ssjc_gwgy_qy_name').combobox('getText') ) ;
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//datagrid刷新
function refresh(){
dg.datagrid('reload'); 
}

function show(n){
	openDialogView("高危工艺实时数据",ctx+'/zxjkyj/gwgyjc/view/'+n,"100%", "100%","");
}

//导出选择企业页面
function fileexport(){
	if(usertype=='1'){
		window.open('${ctx}/zxjkyj/gwgyjc/export?ids=');
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
	    	window.open('${ctx}/zxjkyj/gwgyjc/export?ids='+ids);
		  },
		cancel: function(index){ 
	     }
	    
	});
	
	}
}

//加载企业名称
function loadSelectDatagrid(){
	$('#qyDatagrid').datagrid({
	    url:'${ctx}/zxjkyj/gwgyjc/qyjson',
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