var dg;
var d;
$(function() {	
	var type = $("#type").val();
	dg = $('#xfssgl_xfssjc_dg').datagrid({
	method : "get",
	url : ctx + '/xfssgl/xfssjc/list',
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
        {field:'employeeid',title:'employeeid',hidden:true,width:50,align:'left'}, 
		{field:'id',title:'id',checkbox:true,width:50,align:'left'},    
		{field:'qyname',title:'企业名称',sortable:false,width:50,align:'left'},
		{field:'xfssname',title:'设施名称',sortable:false,width:50,align:'left'},    
		{field:'checktime',title:'检查时间',sortable:false,width:50,align:'left'},
        {field:'checkcontent',title:'检查内容',sortable:false,width:50,align:'left'},
        {field:'checkresult',title:'检查结论',sortable:false,width:50,align:'left'},
        {field:'employeename',title:'检查人员',sortable:false,width:50,align:'left'}]],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			view();
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
			$(this).datagrid("autoMergeCells",['qyname']);
			 if (type == '1') {
				 $(this).datagrid("hideColumn",'qyname');
			 } else if (type == '2') {
				 $(this).datagrid("showColumn",'qyname');
			 }
		},
		checkOnSelect : false,
	selectOnCheck : false,
	toolbar : '#xfssgl_xfssjc_tb'
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
			url : ctx + "/xfssgl/xfssjc/delete/" + ids,
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

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	
	openDialogView("查看消防设施信息",ctx + "/xfssgl/xfssjc/view/" + row.id + "/" + row.employeeid,"800px", "500px","");

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

//导出
/*function fileexport(n){
	var content;
	content=ctx+'/yhpc/yhpcd/colindex';
	window.expara=$("#searchFrom").serializeObject();
	
	window.exdata=[
			   		{colval:'name', coltext:'巡查点名称'},
			   		{colval:'bindcontent', coltext:'绑定二维码'},
			   		{colval:'rfid', coltext:'绑定rfid'},
			   		{colval:'area', coltext:'rfid卡批次代码'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: content,
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  }, 
	});
		
}*/
