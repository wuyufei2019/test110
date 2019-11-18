//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//清空
function reset2(){
	$("#searchFrom2").form("clear");
	search2();
}

//查询
function search2(){
	var obj=$("#searchFrom2").serializeObject();
	dg2.datagrid('load',obj); 
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
			url:ctx+"/dxj/bcrw/delete/"+ids,
			success: function(data){
				layer.alert(data, {icon:6,title: '提示',offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
				dg2.datagrid('reload');
				dg2.datagrid('clearChecked');
				dg2.datagrid('clearSelections');
			}
		});
	});
 
}

//添加
function add() {
	openDialog("添加点巡检班次任务",ctx+"/dxj/bcrw/create","1000px", "100%","");
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改点巡检班次任务",ctx+"/dxj/bcrw/update/"+row.id,"1000px", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看点巡检班次任务",ctx+"/dxj/bcrw/view/"+row.id,"800px", "400px","");
}

//查看
function view2(){
	var row = dg2.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看点巡检班次任务",ctx+"/dxj/bcrw/view/"+row.id,"800px", "400px","");
}

//向子页面发送xjryids
function  getxjryids(){
	return $("#xjryids").val();
}

//弹出巡检人员选择框
function openxjrylist() {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '选择检查人员',
        maxmin: false, 
	    content: ctx + "/dxj/bcrw/xjrychoose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#xjryList");
	    	$list.html("");
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname();
	        var arr1=idname.split(",");
	        var ids="";
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					// 巡检人员名称拼接
					var $li = $("<div id=\""
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeXjry('"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#xjryids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

// 删除预览巡检人员
function removeXjry(fileid) {
	var ids = $("#xjryids");
	var xjry = ids.val();

	if (xjry.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(xjry.replace(fileid + ",", ""));
	}
	$("#" + fileid).remove();
}

//向子页面发送jcdids
function  getjcdids(){
	return $("#jcdids").val();
}

//弹出检查点选择框
function openjcdlist() {
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '选择设备项目',
        maxmin: false, 
	    content: ctx + "/dxj/bcrw/jcdchoose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	var  $list= $("#jcdList");
	    	$list.html("");
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getidname2();
	        var arr1=idname.split(",");
	        var ids="";
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split("||");
					var $li = $("<div id=\"sbxm_"
							+ arr2[0]
							+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arr2[4]+" —— "+arr2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeJcd('sbxm_"
							+ arr2[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);
					ids = ids + arr2[0] + ",";
				}
			$("#jcdids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}

// 删除预览检查点
function removeJcd(fileid) {
	var ids = $("#jcdids");
	var jcd = ids.val();

	if (jcd.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(jcd.replace(fileid.split("_")[1] + ",", ""));
	}
	$("#" + fileid).remove();

}
