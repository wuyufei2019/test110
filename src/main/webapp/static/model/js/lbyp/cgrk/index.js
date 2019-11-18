var dg;
var d;
$(function(){
	dg=$('#lbyp_cgrk_dg').datagrid({    
	method: "post",
    url:ctx+'/lbyp/cgrk/list', 
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
        {field:'name',title:'用品名称',sortable:false,width:60},    
        {field:'number',title:'用品编号',sortable:false,width:40},    
        {field:'amount',title:'采购数量',sortable:false,width:40},    
        {field:'price',title:'单价（元）',sortable:false,width:40},    
        {field:'buytime',title:'采购时间',sortable:false,width:50,formatter : function (value){
        	return new Date(value).format("yyyy-MM-dd");
        }},    
        {field:'note',title:'备注',sortable:false,width:80}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        //$(this).datagrid("autoMergeCells",['ckname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_cgrk_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加采购入库信息",ctx+"/lbyp/cgrk/create/","800px", "300px","");
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
			url:ctx+"/lbyp/cgrk/delete/"+ids,
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
	
	openDialog("修改采购入库信息",ctx+"/lbyp/cgrk/update/"+row.id,"800px", "300px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看采购入库信息",ctx+"/lbyp/cgrk/view/"+row.id,"800px", "300px","");
	
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

