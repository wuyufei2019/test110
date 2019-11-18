var dg;
var d;
$(function() {
	dg = $('#target_aqdt_dg').datagrid(
			{
				method : "get",
				url : ctx + '/target/aqdt/list?qyid='+qyid,
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
							title : 'id',
							checkbox : true,
							width : 50,
							align : 'center'
						},
						{
							field : 'qyname',
							title : '企业名称',
							hidden : true,
							width : 200 
						},
						{
							field : 'm1',
							title : '主题',
							sortable : false,
							width : 100 
						},
						{
							field : 's1',
							title : '发布时间',
							sortable : false,
							width : 60,
							align : 'center',
							formatter : function(value) {
								if (value != null && value != '') {
									var dt = new Date(value); 
						        	return dt.format("yyyy-MM-dd hh:mm:ss"); 
								} else {
									return '/';
								}
							}
						},
						{
							field : 'm5',
							title : '文件类型',
							sortable : false,
							width : 40,
							align : 'center'
						},
						{
							field : 'username',
							title : '发布人',
							sortable : false,
							width : 50,
							align : 'center'
						},

						
						{
							field : 'viewcount',
							title : '浏览次数',
							sortable : false,
							width : 40,
							align : 'center'
						}

				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function() {
					if(usertype=='isbloc'){
			    		$(this).datagrid("showColumn","qyname");
			    		$(this).datagrid("autoMergeCells", [ 'qyname' ]);
					}
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#target_aqdt_tb'
			});


});

// 弹窗增加
function add(u) {
	openDialog("添加安全动态信息", ctx + "/target/aqdt/create/", "800px", "400px", "");
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
			url : ctx + "/target/aqdt/delete/" + ids,
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
	openDialog("修改动态信息", ctx + "/target/aqdt/update/" + row.id, "800px","400px", "");
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

	window.open(ctx + "/target/aqdt/view/" + row.id, "信息查看");

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
