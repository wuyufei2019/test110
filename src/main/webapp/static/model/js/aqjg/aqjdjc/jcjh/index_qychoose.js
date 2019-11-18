var dg;
var d;
var qyids;
$(function(){
	 qyids=window.parent.getqyids();
	dg=$('#jcjh_qychoose_dg').datagrid({    
	method: "get",
    url:ctx+'/aqjd/jcjh/qylist/add', 
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
{field:'m1',title:'企业名称',sortable:false,width:100,align:'center'}   
]],
	checkOnSelect:true,
	selectOnCheck:false,
	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
		$.each(rowdata.rows,function(i,row){
			if((','+qyids).indexOf(','+row.ID+',')!=-1){
				$("#jcjh_qychoose_dg").datagrid('selectRow',i);
			}
		});
	},
    toolbar:'#jcjh_qychoose_tb'
	});
	
	d=$('#jcjh_qychoose_dg2').datagrid({    
		method: "get",
	    url:ctx+'/aqjd/jcjh/qylist/'+$("#jhid").val(), 
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
	    toolbar:'#jcjh_qychoose_tb'
		});
	
});


function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function getidname(){
	var row=$('#jcjh_qychoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].ID +"||"+row[i].m1+ ",";
		}
	}
	return idname;
}
function getqyids(){
	var row=$('#jcjh_qychoose_dg2').datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].ID + ",";
		}
	}
	return ids;
}

