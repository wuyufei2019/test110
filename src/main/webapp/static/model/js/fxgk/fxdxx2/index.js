// 弹窗增加
function add(u) {
	openDialog("添加风险点信息", ctx + "/fxgk/fxxx2/create/", "100%", "100%", "");
}

//弹窗复制
function addfz() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialog("复制风险点信息", ctx + "/fxgk/fxxx2/addfz/" + row.id, "100%","100%", "");
}

// 删除预览企业
function removeQy(fileid) {
	var ids = $("#qyids");
	var qy = ids.val();

	if (qy.split(",").length == 2) {
		ids.val("");
	} else {
		ids.val(qy.replace(fileid + ",", ""));
	}
	$("#" + fileid).remove();

};


// 删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/fxgk/fxxx2/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});

}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialog("修改风险点信息", ctx + "/fxgk/fxxx2/update/" + row.id, "100%","100%", "");
}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看风险点信息", ctx + "/fxgk/fxxx2/view/" + row.id, "100%","100%", "");
}

//查看风险点巡检记录
function viewXjjl(fxdid) {
	openDialogView("查看历史巡检记录", ctx + "/fxgk/fxxx2/viewXjjl/" + fxdid, "900px","400px", "");
}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//跳转统计分析
function tjfx() {
	window.location.href = ctx+'/fxgk/tjfx/index';
}

//跳转风险点分布图
function fxdfbt() {
	window.location.href = ctx+'/fxgk/fxyt/map';
}

//导出Excel
function exportexc(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	            {colval:'qyname', coltext:'企业名称'},
	            {colval:'m1', coltext:'风险点名称'},  
	            {colval:'areaname', coltext:'所在位置'},
	           	{colval:'m7', coltext:'主要危险因素'},
	           	{colval:'m8', coltext:'可能导致事故类型'},
	           	{colval:'fxfj', coltext:'风险等级'},
	           	{colval:'rq', coltext:'辨识时间'},
	           	{colval:'m10', coltext:'主要方法措施'},
	           	{colval:'m12', coltext:'主要依据'},
	           	{colval:'depart', coltext:'责任部门'},
	           	{colval:'m13', coltext:'负责人'},
	           	{colval:'m14', coltext:'负责人电话'},
	           	{colval:'aprobability', coltext:'可能性(L)'},
	           	{colval:'exposefrequency', coltext:'暴露频率(E)'},
	           	{colval:'aseverity', coltext:'严重性(C)'},
	           	{colval:'fxz', coltext:'风险值'},
	           	{colval:'fjgk1', coltext:'公司'},
	           	{colval:'fjgk2', coltext:'部门'},
	           	{colval:'fjgk3', coltext:'班组'},
	           	{colval:'fjgk4', coltext:'岗位'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
      maxmin: false, 
      shift: 1,
	    content: ctx+'/fxgk/fxxx2/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}
// 导出标示word
function exportbs() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/fxgk/fxxx2/exportbs/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//导出彩色风险告知word
function exportfxgzA3() {
	exportfxgz("A3");
}

// 导出彩色风险告知word
function exportfxgzA2() {
	exportfxgz("A2");
}

function exportfxgz(type){
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	$.post(ctx + "/fxgk/fxxx2/exportfxgz/" + row.id,{"type":type},function(data){
		window.open(ctx+"/download/"+encodeURIComponent(data));
	});
}
// 导出彩色风险告知word
function exportpdf() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	
	$.ajax({
		url : ctx + "/fxgk/fxxx2/exportpdf/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}

//绑定巡检内容
function checkContentManage(){
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	openDialogView("巡检内容管理",ctx+"/fxgk/fxxx2/bdxjnr/"+row.id+"?qyid="+row.id1,"100%", "100%","");
}