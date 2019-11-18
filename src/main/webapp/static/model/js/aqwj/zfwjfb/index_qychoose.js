var dg;
var d;
$(function(){	
	d=$('#issue_qychoose_dg2').datagrid({    
		method: "get",
	    url:ctx+'/issue/zfwjfb/qylist/'+$("#wjid").val(), 
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
		$.each(rowdata.rows,function(i,row){
				d.datagrid('selectRow',i);
		});
	},
		checkOnSelect:true,
		selectOnCheck:false,
	    toolbar:'#issue_qychoose_tb'
		});
});


function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	d.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	d.datagrid('load',obj); 
}
function getidname(f){
	var row;
	 row=$('#issue_qychoose_dg2').datagrid('getChecked');
		
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].ID +"||"+row[i].m1+ ",";
		}
	}
	return idname;
}

function getqyids(){
	var row=$('#issue_qychoose_dg2').datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].ID + ",";
		}
	}
	return ids;
}

