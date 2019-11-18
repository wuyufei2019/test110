var dg;
var d;
$(function(){
	dg=$('#issue_zfchoose_dg').datagrid({    
	method: "get",
    url:ctx+'/issue/zfwjfb/zflist/add', 
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
	{field:'wgd',title:'所属网格',sortable:false,width:100,align:'center'},
	{field:'NAME',title:'政府人员',sortable:false,width:100,align:'center'}   
	]],
	checkOnSelect:true,
	selectOnCheck:false,
    toolbar:'#issue_zfchoose_tb'
	});
	
	d=$('#issue_zfchoose_dg2').datagrid({    
		method: "get",
	    url:ctx+'/issue/zfwjfb/zflist/'+$("#wjid").val(), 
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
	{field:'NAME',title:'政府人员',sortable:false,width:100,align:'center'},    
	]],
	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		$.each(rowdata.rows,function(i,row){
				d.datagrid('selectRow',i);
		});
	},
		checkOnSelect:true,
		selectOnCheck:false,
	    toolbar:'#issue_zfchoose_tb'
		});
});


function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
	d.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	d.datagrid('load',obj); 
}
function getidname(f){
	var row;
	if(f=='add')
	 row=$('#issue_zfchoose_dg').datagrid('getChecked');
	else
	 row=$('#issue_zfchoose_dg2').datagrid('getChecked');
		
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].ID +"||"+row[i].NAME+ ",";
		}
	}
	return idname;
}

function getzfids(){
	var row=$('#issue_zfchoose_dg2').datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].ID + ",";
		}
	}
	return ids;
}

