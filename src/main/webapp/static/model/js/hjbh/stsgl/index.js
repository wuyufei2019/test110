var dg;
var d;
//添加三同时管理信息
function add() {
	openDialog("添加三同时管理信息",  ctx + "/hjbh/stsgl/create","880px", "420px","");
}
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看三同时管理信息",ctx+"/hjbh/stsgl/view/"+row.id,"880px", "420px","");
}

//跳转到修改危险废物特性页面
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改三同时管理信息",ctx+"/hjbh/stsgl/update/"+row.id,"880px", "420px","");
	
}

//删除危险废物特性记录
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
			url:ctx+"/hjbh/stsgl/delete/"+ids,
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

//创建查询对象并查询
function search(){
	var obj=$("#searchForm").serializeObject();
	dg.datagrid('load',obj);
}
//刷新页面
function reset(){
	$("#searchForm").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj);
}
