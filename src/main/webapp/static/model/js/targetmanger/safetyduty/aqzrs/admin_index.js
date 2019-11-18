var dg;
$(function() {
	dg = $('#target_aqzrs_dg').datagrid({
		method : "post",
		url : ctx + '/target/aqzrs/adminlist',
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
			field : 'qyname',
			title : '企业名称',
			sortable : false,
			width : 100,
			align : 'center'
		}, {
			field : 'year',
			title : '年份',
			sortable : false,
			width : 50,
			align : 'center'
		}, {
			field : 'url',
			title : '责任书下发',
			sortable : false,
			width : 100,
			align : 'center',
			formatter : function(value) {
				if (value) {
					var urls = value.split(",");
					var html = "";
					for ( var index in urls) {
						html += "<a class='fa fa-picture-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='" + urls[index].split("||")[0] + "'> 附件" + (parseInt(index) + 1) + "</a><br>";
					}
					return html;
				} else
					return;
			}
		}, {
			field : 'note',
			title : '备注',
			sortable : false,
			width : 150,
			align : 'center'
		}, ] ],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			view();
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		},
		checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#target_aqzrs_tb'
	});
});
