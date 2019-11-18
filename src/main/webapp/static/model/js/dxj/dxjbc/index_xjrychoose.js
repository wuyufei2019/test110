var dg;
var d;
var xjryids;
$(function(){
	xjryids=window.parent.getxjryids();
	dg=$('#dxj_xjrychoose_dg').datagrid({    
		method: "get",
	    url:ctx+'/system/user/json', 
	    fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		nowrap:false,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		scrollbarSize:0,
		checkOnSelect:true,
		selectOnCheck:false,
	    columns:[[    
	        {field:'ID',title:'id',checkbox:true,align:'center'},
	        {field:'NAME',title:'昵称',width:100},
	        {field:'GENDER',title:'性别',width:20,
	        	formatter : function(value, row, index) {
	       			return value==1?'男':'女';
	        	}
	        },
	        {field:'PHONE',title:'电话',width:60}
	    ]],
		onLoadSuccess:function(rowdata, rowindex, rowDomElement){
			$.each(rowdata.rows,function(i,row){
				if((','+xjryids).indexOf(','+row.id+',')!=-1){
					$("#dxj_xjrychoose_dg").datagrid('selectRow',i);
				}
			});
		},
	    toolbar:'#dxj_xjrychoose_tb'
		});
});


function searchxjry() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function getidname(){
	var row=$('#dxj_xjrychoose_dg').datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].ID +"||"+row[i].NAME+"||"+row[i].GENDER+"||"+row[i].PHONE+ ",";
		}
	}
	return idname;
}


