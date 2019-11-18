var dg;
var d;
$(function() {	
	dg = $('#wghgl_wgd_dg').datagrid({
	method : "get",
	url : ctx + '/wghgl/wgd/list',
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
		{field:'ID',title:'id',checkbox:true,width:50,align:'center'},  
		{field:'wgname',title:'所属网格',sortable:false,width:80},  
		{field:'qyname',title:'企业名称',sortable:false,width:80,align:'center'},    
		{field:'name',title:'巡查点名称',sortable:false,width:80,align:'center'},
        {field:'bindcontent',title:'二维码',sortable:false,width:50,align:'center'},
        {field:'rfid',title:'rfid',sortable:false,width:50,align:'center'},
        {field:'area',title:'rfid卡批次代码',sortable:false,width:50,align:'center'}				             
        ]],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			view();
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
			$(this).datagrid("autoMergeCells", [ 'wgname' ]);
		},
		checkOnSelect : false,
	selectOnCheck : false,
	toolbar : '#wghgl_wgd_tb'
});

});

// 弹窗增加
function add(u) {
	openDialog("添加网格点", ctx + "/wghgl/wgd/create/", "900px", "450px", "");
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
			url : ctx + "/wghgl/wgd/delete/" + ids,
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
	openDialog("修改网格点", ctx + "/wghgl/wgd/update/" + row.id, "900px",
			"450px", "");
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
	
	openDialogView("查看网格点",ctx + "/wghgl/wgd/view/" + row.id,"900px", "450px","");

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
//导出网格员巡检告知word
function exportgzk() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}

	$.ajax({
		url : ctx + "/wghgl/wgd/exportgzk/" + row.id,
		type : "POST",
		success : function(data) {
			window.open(ctx + data);
		}
	});
}


//导出
function fileexport(n){
	var content;
	content=ctx+'/wghgl/wgd/colindex';
	window.expara=$("#searchFrom").serializeObject();
	
	window.exdata=[
	                {colval:'wgname', coltext:'网格名称'},
			   		{colval:'name', coltext:'巡查点名称'},
			   		{colval:'createtime', coltext:'创建时间'},
			   		{colval:'bindcontent', coltext:'二维码'},
			   		{colval:'rfid', coltext:'rfid'},
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
		
}

//批量生成
function plsc() {
	var index = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
	$.ajax({
		type : 'post',
		url : ctx + "/wghgl/wgd/plsc",
		success : function(data) {
			layer.close(index);//关闭加载层
			layer.alert(data, {
				offset : 'rb',
				shade : 0,
				time : 2000
			});
			dg.datagrid('reload');
		}
	});
}


//绑定巡检内容
function checkContentManage(){
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg('请选择一行记录', {
			time : 1000
		});
		return;
	}
	openDialogView("巡检内容管理",ctx+"/wghgl/wgd/bdxjnr/"+row.id+"?qyid="+row.id1,"900px", "600px","");
}