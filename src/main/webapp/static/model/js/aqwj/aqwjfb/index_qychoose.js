var dg;
var d;
$(function(){
	dg=$('#issue_qychoose_dg').datagrid({    
	method: "get",
    url:ctx+'/issue/aqwjfb/qylist/add', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
{field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
{field:'m1',title:'企业名称',sortable:false,width:100,align:'center'},    


]],
	checkOnSelect:true,
	selectOnCheck:false,
    toolbar:'#issue_qychoose_tb'
	});
	
	d=$('#issue_qychoose_dg2').datagrid({    
		method: "get",
	    url:ctx+'/issue/aqwjfb/qylist/'+$("#wjid").val(), 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'ID',
		striped:true,
		pagination:false,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		scrollbarSize:0,
		singleSelect:true,
		striped:true,
	    columns:[[    
	{field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	{field:'m1',title:'企业名称',sortable:false,width:100,align:'center'},    
	]],
	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
	},
		checkOnSelect:true,
		selectOnCheck:false,
	    toolbar:'#issue_qychoose_tb'
		});
});


function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
	d.datagrid('load', obj);
}

function reset(){
	$("#xj").iCheck('uncheck');  
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	d.datagrid('load',obj); 
}
function getidname(f){
	var row;
	if(f=='add')
	 row=dg.datagrid('getChecked');
	else
	 row=d.datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].ID +"||"+row[i].m1+ ",";
		}
	}
	return idname;
}

function getqyids(){
	var row=d.datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].ID + ",";
		}
	}
	return ids;
}

