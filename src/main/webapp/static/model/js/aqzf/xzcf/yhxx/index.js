var dg;
var d;
$(function() {
	dg = $('#xzcf_yhxx_dg')
			.datagrid(
					{
						method : "get",
						url : ctx + '/xzcf/yhxx/list',
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
									field : 'bankname',
									title : '银行名称',
									sortable : false,
									width : 50,
									align : 'center'
									
								},
								{
									field : 'account',
									title : '账号',
									sortable : false,
									width : 50,
									align : 'center'
								},
								{
									field : 'address',
									title : '地址',
									sortable : false,
									width : 50,
									align : 'center'
								},
								
								 ] ],
						onDblClickRow : function(rowdata, rowindex,rowDomElement) {
								view();
						},
						onLoadSuccess : function() {
						},
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#xzcf_yhxx_tb'
					});

});

// 添加告知记录
function add() {
	openDialog("添加银行信息", ctx + "/xzcf/yhxx/create", "600px",
			"300px", "");
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
			url : ctx + "/xzcf/yhxx/delete/" + ids,
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
	openDialog("修改银行信息", ctx + "/xzcf/yhxx/update/" + row.id, "600px","300px", "");
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
	openDialogView("处罚信息查看",ctx + "/xzcf/yhxx/view/" + row.id, "600px","300px", "");

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

