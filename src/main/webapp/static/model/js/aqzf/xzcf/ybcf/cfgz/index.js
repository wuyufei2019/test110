var dg;
var d;
$(function() {
	dg = $('#ybcf_cfgz_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/cfgz/list',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						pagination : true,
						rownumbers : true,
						nowrap : false,
						pageNumber : 1,
						pageSize : 50,
						pageList : [ 50, 100, 150, 200, 250 ],
						scrollbarSize : 5,
						singleSelect : true,
						striped : true,
						columns : [ [
								{
									field : 'id',
									title : 'ID',
									checkbox : true,
									width : 50,
									align : 'center'
								},{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 70,
									align : 'center'
								},
								
								{
									field : 'name',
									title : '单位（个人）名',
									sortable : false,
									width : 100,
									align : 'center'
								},
								{
									field : 'punishdate',
									title : '处罚时间',
									sortable : false,
									width : 50,
									align : 'center',
									formatter : function(value) {
										if (value != null && value != '') {
											var date = new Date(value);
											var y = date.getFullYear();
											var m = date.getMonth() + 1;
											var d = date.getDate();
											var hh = date.getHours();
											var mm = date.getMinutes();
											var ss = date.getSeconds();
											return y + '/'
													+ (m < 10 ? ('0' + m) : m)
													+ '/'
													+ (d < 10 ? ('0' + d) : d);
										} else {
											return '';
										}
									}
								},
								{
									field : 'unlaw',
									title : '违反条款',
									sortable : false,
									width : 150,
									align : 'center',
									
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#ybcf_cfgz_tb'
					});

});

//// 添加告知记录
//function add() {
//	openDialog("添加告知记录", ctx + "/xzcf/ybcf/cfgz/create", "750px",
//			"400px", "");
//}

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
			url : ctx + "/xzcf/ybcf/cfgz/delete/" + ids,
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
	openDialog("修改告知记录", ctx + "/xzcf/ybcf/cfgz/update/" + row.id, "800px","400px", "");
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
	openDialogView("告知信息查看",ctx + "/xzcf/ybcf/cfgz/view/" + row.id, "800px","400px", "");

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

// 导出告知书word
function fileexportgzs() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/cfgz/exportgzs/gz/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
// 导出申辩记录word
function fileexportsbjl() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if(row.explainflag=='1'){
		$.ajax({
			url : ctx + "/xzcf/ybcf/cfgz/exportcssb/" + row.id,
			type : "POST",
			success : function(data) {
				window.open(ctx + data);
			}
		});
		}else{
			top.layer.confirm('您还没有添加申辩记录！', {
				icon : 2,
				title : '提示' 
			}, function(index) {
				top.layer.close(index);
			});
			return;
		}
	}


//导出处罚决定书word
function fileexportcfjd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	if (row.punishflag ==null||row.punishflag == '0') {
		top.layer.confirm('您还没有添加申辩记录！', {
			icon : 2,
			title : '提示' 
		}, function(index) {
			top.layer.close(index);
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/cfgz/exportcfjd/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
