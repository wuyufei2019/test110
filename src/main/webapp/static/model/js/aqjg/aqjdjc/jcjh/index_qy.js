var dg;
var d;
$(function() {
	dg = $('#aqjg_jcjh_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqjd/jcjh/list',
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
							field : 'm2',
							title : '计划时间',
							sortable : false,
							width : 100,
							align : 'center',
							formatter : function(value) {
								var arry=value.split("-");
								return arry[0]+"年"+arry[1]+"月"
							}
						},
						{
							field : 'm1',
							title : '专项检查名称',
							sortable : false,
							width : 100,
							align : 'center'
						},
						
						{
							field : 'm4',
							title : '备注',
							sortable : false,
							width : 100,
							align : 'center'
						}
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function() {
					$(this).datagrid("autoMergeCells", [ 'm2' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqjg_jcjh_tb'
			});


});


// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}

	window.open(ctx + "/aqjd/jcjh/view/" + row.ID, "信息查看");

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
