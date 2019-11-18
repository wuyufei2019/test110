var dg;
var d;
$(function() {	
	dg = $('#yhpc_yhpcd_dg').datagrid({
	method : "get",
	url : ctx + '/yhpc/yhpcd/list',
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
		{field:'qyname',title:'企业名称',sortable:false,width:80},    
		{field:'name',title:'巡查点名称',sortable:false,width:80,align:'center'},
        {field:'bindcontent',title:'绑定二维码',sortable:false,width:50,align:'center'},
        {field:'rfid',title:'绑定rfid',sortable:false,width:50,align:'center'},
        {field:'area',title:'rfid卡批次代码',sortable:false,width:50,align:'center'},
        {field:'ewm',title:'二维码',sortable:false,width:30,align:'center',
        	formatter : function(value, row, index) {
        		return "<a class='btn btn-success btn-xs' onclick='openerm("+row.id+")'>生成二维码</a>";
        	}
        },
        {field:'zt',title:'状态',sortable:false,width:30,align:'center',
        	formatter : function(value, row, index) {
        		if(row.stoppointid != null){
        			return "<a class='btn btn-danger btn-xs'>停产</a>";
        		}else{
        			return "<a class='btn btn-info btn-xs'>正常</a>";
        		}
        	}
        }		
        ]],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			view();
		},
		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
			if(usertype == '1'){
				$(this).datagrid("hideColumn", [ 'qyname' ]);
			}else{
				$(this).datagrid("showColumn", [ 'qyname' ]);
				$(this).datagrid("autoMergeCells", [ 'qyname' ]);
			}
		},
		checkOnSelect : false,
	selectOnCheck : false,
	toolbar : '#yhpc_yhpcd_tb'
});

});

// 弹窗增加
function add(u) {
	openDialog("添加隐患排查点", ctx + "/yhpc/yhpcd/create/", "100%", "100%", "");
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
			url : ctx + "/yhpc/yhpcd/delete/" + ids,
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
	openDialog("修改隐患排查点", ctx + "/yhpc/yhpcd/update/" + row.id, "100%",
			"100%", "");
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
	
	openDialogView("查看隐患排查点",ctx + "/yhpc/yhpcd/view/" + row.id,"100%", "100%","");

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
function fileexport(n){
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
	openDialogView("巡检内容管理",ctx+"/yhpc/yhpcd/bdxjnr/"+row.id+"?qyid="+row.id1,"900px", "400px","");
}

//生成二维码图片
function openerm(id){
	$.ajax({
		type : 'get',
		url : ctx + "/yhpc/yhpcd/erm?id=" + id,
		success : function(data) {
			window.open(ctx+data);
		}
	});
}

//停产
function disable(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.stoppointid != null){
        layer.msg("该自定义巡检点已停产！");
        return;
    }
    openDialog("添加停产日期", ctx + "/yhpc/stoppoint/disableindex/"+row.id+"/2", "400px","350px", "");
}

//恢复
function enable(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.stoppointid == null){
        layer.msg("该自定义巡检点状态正常！");
        return;
    }
    top.layer.confirm('确定要恢复该自定义巡检点？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url: ctx + "/yhpc/stoppoint/enable/"+row.id+"/2",
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
                dg.datagrid('clearSelections');
            }
        });
    });
}