var dg;
var d;
$(function(){
	dg=$('#zyaqgl_wlfchoose_dg').datagrid({    
	method: "get",
    url:ctx+'/ztzyaqgl/dtzy/wlflist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:false,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},   
              {field:'m1',title:'类别',width:60,align:'center'},  
              {field:'m2',title:'单位名称',width:60,align:'center'},    
              {field:'m3',title:'行业类型',width:70,align:'center'},
              {field:'m6',title:'联系人',width:100,align:'center'},
              {field:'m7',title:'联系电话',width:60,align:'center'} 
]],
	onDblClickRow: function (rowdata, rowindex, rowDomElement){
	    view();
	},
	checkOnSelect:true,
	selectOnCheck:false,
    toolbar:'#zyaqgl_wlfchoose_tb'
	});
	
});

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看相关方单位信息",ctx+"/ztzyaqgl/xgdw/view/"+row.id,"90%", "90%","");
	
}

function searchwlf() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#xj").iCheck('uncheck');  
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function getidname(f){
	var row;
	row=dg.datagrid('getChecked');
	var idname="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			idname = idname + row[i].id +"||"+row[i].m2+ ",";
		}
	}
	return idname;
}

function getwlfids(){
	var row=d.datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].ID + ",";
		}
	}
	return ids;
}

