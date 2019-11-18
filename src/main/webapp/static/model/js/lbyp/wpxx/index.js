var dg;
var d;
$(function(){
	dg=$('#lbyp_wpxx_dg').datagrid({    
	method: "post",
    url:ctx+'/lbyp/wpxx/list', 
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
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'ckname',title:'所在仓库名称',sortable:false,width:80},    
        {field:'name',title:'用品名称',sortable:false,width:80},    
        {field:'number',title:'用品编号',sortable:false,width:50},    
        {field:'unit',title:' 计量单位',sortable:false,width:30},    
        {field:'specifications',title:'规格/型号',sortable:false,width:30},    
        {field:'storagerate',title:'库存量',sortable:false,width:30}   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['ckname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_wpxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加用品信息",ctx+"/lbyp/wpxx/create/","800px", "300px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/lbyp/wpxx/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改用品信息",ctx+"/lbyp/wpxx/update/"+row.id,"800px", "300px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看用品信息",ctx+"/lbyp/wpxx/view/"+row.id,"800px", "300px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

