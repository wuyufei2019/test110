var dg;
var d;
var categoryDg;
var menuId=0;
var parentId;
$(function(){   
	categoryDg=$('#categoryDg').treegrid({  
	method: "get",
	url:ctx+'/xfssgl/xfssdj/json', 
    fit : true,
	fitColumns : true,
	border : true,
	idField : 'id',
	treeField : 'text',
	animate : true, 
	rownumbers : true,
	singleSelect : true,
	scrollbarSize:0,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true},    
        {field:'text',title:'名称',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    dataPlain: true,
    onClickRow:function(rowData){
    	menuId=rowData.id;
    	parentId=rowData.id;
    	dg.datagrid('reload',{pid:menuId});
    }
	});
	
 	dg=$('#dg').datagrid({   
	method: "get",
	url:ctx+'/xfssgl/xfssdj/list',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField:'name',
	parentField : 'pid',
	iconCls: 'icon',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	pagination : true,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true,width:100},    
		{field:'name',title:'设施名称',sortable:false,width:50,align:'left'},
        {field:'ggxh',title:'规格型号',sortable:false,width:50,align:'left'},
        {field:'cycle',title:'换验周期 ',sortable:false,width:50,align:'left'},
        {field:'state',title:'状态',sortable:false,width:50,align:'left'},
        {field:'sccs',title:'生产厂商',sortable:false,width:80,align:'left'},
        {field:'caozuo',title:'操作',sortable:false,width:30,align:'center',
        	formatter : function(value, row, index) {
        		return "<a class='btn btn-success btn-xs' onclick='openerm("+row.id+")'>生成二维码</a>"
        	}
        }
    ]],
    toolbar:'#tg_tb',
    dataPlain: true,
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    }
	}); 
	
});

//弹窗增加
function add() {
	var row = categoryDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请先选择所属设施类型！",{time: 1000});
		return;
	}
	openDialog("添加设施",ctx+"/xfssgl/xfssdj/create?pid="+parentId,"800px", "400px","");
}

//添加设施类型
function addlb() {
	var row = categoryDg.treegrid('getSelected');
	if(row){
		parentPermId=row.id;
	}
	openDialog("添加设施类型",ctx+"/xfssgl/xfssdj/createLb/","800px", "400px","");
}

//删除类别
function dellb(){
	var row = categoryDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/xfssgl/xfssdj/delete/"+row.id,
			success: function(data){
				if(data=='success')
					layer.alert('删除成功！', {offset: 'rb',shade: 0,time: 2000}); 
		    	else
		    		layer.alert('删除失败！', {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				categoryDg.treegrid('reload');
				categoryDg.datagrid('clearChecked');
				categoryDg.datagrid('clearSelections');
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
}

//删除设施
function del(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/xfssgl/xfssdj/delete/"+row.id,
			success: function(data){
				if(data=='success')
					layer.alert('删除成功！', {offset: 'rb',shade: 0,time: 2000}); 
		    	else
		    		layer.alert('删除失败！', {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
			}
		});
	});
}

//修改类别
function updlb(){
	
	var row = categoryDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一条记录！",{time: 1000});
		return;
	}else{
		parentPermId=row.pid;
	}
	openDialog("修改设施类别",ctx+"/xfssgl/xfssdj/updateLb/"+row.id,"800px", "400px","");

}

//修改设施
function upd(){
	
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一条记录！",{time: 1000});
		return;
	}
	openDialog("修改设施信息",ctx+"/xfssgl/xfssdj/update/"+row.id,"800px", "400px","");

}

//查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	
	openDialogView("查看消防设施信息",ctx + "/xfssgl/xfssdj/view/" + row.id,"100%", "100%","");

}

//生成二维码图片
function openerm(id){
	$.ajax({
		type : 'get',
		url : ctx + "/xfssgl/xfssdj/erm?id=" + id,
		success : function(data) {
			window.open(ctx+data);
		}
	});
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'cname', coltext:'消防设施类别'},
			   		{colval:'name', coltext:'设施名称'},
			   		{colval:'ggxh', coltext:'规格型号'},
			   		{colval:'cycle', coltext:'换验周期'},
			   		{colval:'state', coltext:'状态'},
			   		{colval:'sccs', coltext:'生产厂商'},
			   		{colval:'m20', coltext:'备注'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/xfssgl/xfssdj/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}