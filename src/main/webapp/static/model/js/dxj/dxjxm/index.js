var dg;
var d;
$(function() {	
	dg = $('#dxj_dxjxm_dg').datagrid({
		method : "get",
		url : ctx + '/dxj/sbxjd/list',
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
			{field:'id',title:'id',checkbox:true,width:50,align:'center'},    
			{field:'qyname',title:'企业名称',sortable:false,width:70},    
			{field:'sbm',title:'设备名称',sortable:false,width:60,align:'center'},
			{field:'name',title:'项目名称',sortable:false,width:60,align:'center'},
			{field:'checkmethod',title:'检查方法',sortable:false,width:50,align:'center'},
			{field:'standard',title:'标准',sortable:false,width:30,align:'center'},
			{field:'scope',title:'范围',sortable:false,width:50,align:'center'}
	        ]],
			onDblClickRow : function(rowdata, rowindex, rowDomElement) {
				view();
			},
			onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
				if(type == '1'){
					$(this).datagrid("hideColumn", [ 'qyname' ]);
				}else{
					$(this).datagrid("showColumn", [ 'qyname' ]);
					$(this).datagrid("autoMergeCells", [ 'qyname' ]);
				}
				$(this).datagrid("autoMergeCells", [ 'sbm' ]);
			},
			checkOnSelect : false,
		selectOnCheck : false,
		toolbar : '#dxj_dxjxm_tb'
	});
});

//创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 
//重置
function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//设备管理
function sbgl() {
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ['100%', '100%'],
	    title: "设备管理",
        maxmin: false, 
	    content: ctx+"/dxj/sb/index",
	    btn: ['关闭'],
		end: function(layero, index){
			  window.location.reload();
		 },
		  cancel: function(index){ 
	     }
	}); 
}

// 弹窗增加
function add() {
	openDialog("添加设备项目信息", ctx + "/dxj/sbxjd/create/", "800px", "400px", "");
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
			url : ctx + "/dxj/sbxjd/delete/" + ids,
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
	openDialog("修改设备项目信息", ctx + "/dxj/sbxjd/update/" + row.id, "800px",
			"400px", "");
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
	openDialogView("查看设备项目信息",ctx + "/dxj/sbxjd/view/" + row.id,"800px", "400px","");
}

//导出
function fileexport(){
	var content;
	content=ctx+'/dxj/sbxjd/colindex';
	window.expara=$("#searchFrom").serializeObject();
	
	window.exdata=[
	               	{colval:'qyname', coltext:'企业名称'},
	               	{colval:'sbm', coltext:'设备名称'},
	               	{colval:'name', coltext:'设备项目名称'},
	               	{colval:'standard', coltext:'标准'},
			   		{colval:'scope', coltext:'范围'},
			   		{colval:'checkmethod', coltext:'点检方法'}
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