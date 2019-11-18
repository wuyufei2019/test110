
//弹窗增加
function add(u) {
	openDialog("上传文件信息",ctx+"/sbssgl/zyzx/create","600px", "230px","");
}

//删除
function del(id){
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/sbssgl/zyzx/delete/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				location.reload();
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
	
	openDialog("修改附件信息",ctx+"/sbssgl/zyzx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看附件信息",ctx+"/sbssgl/zyzx/view/"+row.ID,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	$("#searchFrom").submit();
}

function reset(){
	$("#searchFrom").form("clear");
	search(); 
}

function downloadfj(fjurl){
	var furl = fjurl.split("||");
	window.open(furl[0]);
}

