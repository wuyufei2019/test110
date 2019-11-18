var dg;
$(function() {
	dg = $('#target_aqzrs_dg').datagrid({
		method : "post",
		url : ctx + '/target/aqzrs/qylist?qyid='+qyid,
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
			field : 'year',
			title : '年份',
			sortable : false,
			width : 50,
			align : 'center'
		}, {
			field : 'title',
			title : '标题',
			sortable : false,
			width : 150,
			align : 'center'
		},{
			field : 'url',
			title : '责任书附件',
			sortable : false,
			width : 100,
			align : 'center',
			formatter : function(value) {
				if (value) {
					var urls = value.split(",");
					var html = "";
					for ( var index in urls) {
						html += "<a class='fa fa-file-word-o btn-danger btn-outline' target='_blank' style='margin:1px auto' href='" + urls[index].split("||")[0] + "'> 附件" + (parseInt(index) + 1) + "</a><br>";
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
		 onLoadSuccess: function(){
		        $(this).datagrid("autoMergeCells",['year']);
		    },
		checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#target_aqzrs_tb'
	});
});
