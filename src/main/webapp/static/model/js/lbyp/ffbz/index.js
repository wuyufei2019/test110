var dg;
var d;
$(function(){
	dg=$('#lbyp_ffbz_dg').datagrid({    
	method: "post",
    url:ctx+'/lbyp/ffbz/list?qyid='+qyid,
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
        {field:'jobtype',title:'岗位/工种名称',sortable:false,width:50},
        {field:'goodsname',title:'用品名称',sortable:false,width:80},
        {field:'amount',title:'发放数量',sortable:false,width:30},
        {field:'cyclemonth',title:'发放周期',sortable:false,width:30,formatter :function(value){
        	return value+"月";
        }}   
    ]],
    onDblClickRow: function (){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['jobtype']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#lbyp_ffbz_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加发放标准信息",ctx+"/lbyp/ffbz/create/","800px", "450px","");
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
			url:ctx+"/lbyp/ffbz/delete/"+ids,
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
	
	openDialog("修改发放标准信息",ctx+"/lbyp/ffbz/update/"+row.id,"800px", "300px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看发放标准信息",ctx+"/lbyp/ffbz/view/"+row.id,"800px", "300px","");
	
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

