var dg;
var d;
$(function() {
	dg = $('#ybcf_jasp_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/ybcf/jasp/list',
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
								},
								{
									field : 'number',
									title : '编号',
									sortable : false,
									width : 70,
									align : 'center'
								},{
									field : 'casename',
									title : '案件名称',
									sortable : false,
									width : 80,
									align : 'center'
								},
								{
									field : 'punishname',
									title : '受处单位（个人）名',
									sortable : false,
									width : 80,
									align : 'center'
								},
								{
									field : 'exeucondition',
									title : '执行情况',
									sortable : false,
									width : 150,
									align : 'center'
								}
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#ybcf_jasp_tb'
					});

});


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
			url : ctx + "/xzcf/ybcf/jasp/delete/" + ids,
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
	openDialog("修改结案审批记录", ctx + "/xzcf/ybcf/jasp/update/" + row.id, "800px","400px", "");
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
	openDialogView("结案审批记录信息查看",ctx + "/xzcf/ybcf/jasp/view/" + row.id, "800px","400px", "");

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

// 导出安监呈批表word
function fileexportjasp() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/xzcf/ybcf/jasp/exportjasp/ja/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}
