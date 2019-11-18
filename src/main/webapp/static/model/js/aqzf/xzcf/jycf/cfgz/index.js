var dg;
var d;
$(function() {
	dg = $('#jycf_cfgz_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/jycf/cfgz/list',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'ID',
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
									field : 'ID',
									title : 'ID',
									checkbox : true,
									width : 50,
									align : 'center'
								},
								{
									field : 'casename',
									title : '案件名称',
									sortable : false,
									width : 100,
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
									width : 100,
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
									field : 'illegalact',
									title : '违法行为',
									sortable : false,
									width : 100,
									align : 'center',
									
								},
								{
									field : 'explainflag',
									title : '陈述申辩',
									sortable : false,
									width : 50,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null && value != '') {
											if(value=='0'){
												return	"<a  class='btn btn-info btn-xs' onclick='addCheck("
												+ row.id + ")'>申辩</a>";
											}
											if(value='2'){
												return "<a  onclick='updateReCheck("
												+ row.id + ")'>修改申辩记录</a>";
											}
										} else {
											return "<a  disabled='true' class='btn btn-info btn-xs'>已逾期</a>";
										}
									},
									styler : function(value, row, index) {
										if (value == '2')
											return 'background-color:rgb(216,155,176);';
									}
								},
								{
									field : 'punishflag',
									title : '处罚决定',
									sortable : false,
									width : 50,
									align : 'center',
									formatter : function(value, row, index) {
										if (value != null && value != '') {
											if(value=='0'){
													return "<a  class='btn btn-danger btn-xs' onclick='addCheck("
															+ row.id + ")'>添加处罚决定</a>";
												
											}
											else{
												return "<a  onclick='updateReCheck("
											+ row.id + ")'>修改处罚决定</a>";
											}
									}
								},
								styler : function(value, row, index) {
									if (value == '1')
										return 'background-color:rgb(216,155,176);';
								}
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#jycf_cfgz_tb'
					});

});

// 添加告知记录
function add() {
	openDialog("添加告知记录", ctx + "/xzcf/jycf/cfgz/create", "750px",
			"400px", "");
}

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
			url : ctx + "/xzcf/jycf/cfgz/delete/" + ids,
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
	openDialog("修改告知记录", ctx + "/xzcf/jycf/cfgz/update/" + row.id, "700px","400px", "");
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
	openDialogView("告知信息查看",ctx + "/xzcf/jycf/cfgz/view/" + row.id, "700px","400px", "");

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
		url : ctx + "/xzcf/jycf/cfgz/exportgzs/" + row.id,
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
			url : ctx + "/xzcf/jycf/cfgz/exportcssb/" + row.id,
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
		url : ctx + "/xzcf/jycf/cfgz/exportcfjd/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
