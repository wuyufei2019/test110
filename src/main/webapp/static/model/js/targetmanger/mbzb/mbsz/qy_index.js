var dg;
$(function() {
		dg = $('#target_zbsz_dg').datagrid({
			method : "post",
			url : ctx + '/target/zbsz/qylist?qyid='+qyid,
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
			scrollbarSize : 5,
			singleSelect : true,
			striped : true,
			columns : [ [ {
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 50,
				align : 'center'
			}, {
				field : 'm1',
				title : '指标名称',
				sortable : false,
				width : 100,
				align : 'center'
			}, {
				field : 'm2',
				title : '指标值',
				sortable : false,
				width : 100,
				align : 'center'
			}, {
				field : 'm3',
				title : '备注',
				sortable : false,
				width : 100,
				align : 'center'
			}, ] ],
			onDblClickRow : function(rowdata, rowindex, rowDomElement) {
				view();
			},
			checkOnSelect : false,
			selectOnCheck : false,
			toolbar : '#target_zbsz_tb'
		});
});
