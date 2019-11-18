var dg;
var d;
var permissionDg;
var parentPermId=0;
$(function(){   
	dg=$('#dg').treegrid({  
	method: "get",
    url : ctx+'/bis/aqwlgl/json',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField : 'text',
	animate : true, 
	rownumbers : true,
	singleSelect : true,
	striped:true,
    columns:[[    
        {field:'id',title:'id',hidden:true,width:100},    
        {field:'text',title:'名称',width:100},
        {field:'m2',title:'负责人',width:100},
        {field:'m3',title:'负责人联系方式',width:100},
        {field:'m5',title:'备注',width:100}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    toolbar:'#tb',
    dataPlain: true
	});
	
});


function add(u) {
	var row = dg.treegrid('getSelected');
	if(row){
		parentPermId=row.id;
	}
	
	openDialog("添加信息",ctx+"/bis/aqwlgl/create/","800px", "400px","");
}

//删除
function del(){
	var row = dg.treegrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	 
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/aqwlgl/delete/"+row.id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.treegrid('reload');
				dg.treegrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
 
}

//弹窗修改
function upd(){
	var row = dg.treegrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	parentPermId=row.pid;
	openDialog("修改信息",ctx+"/bis/aqwlgl/update/"+row.id,"800px", "400px","");
	
}

//架构图
function viewTree(){
	layer.open({
	    type: 2,  
	    area: ['896px', '440px'],
	    title: '查看网络架构图',
        maxmin: true, 
	    content: ctx+'/bis/aqwlgl/viewtree/'+qyid,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 	
	
}

//查看详情
function view(){
	var row = dg.treegrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看信息",ctx+"/bis/aqwlgl/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'车间名称'},
			   		{colval:'m2', coltext:'车间编号'},
			   		{colval:'m3', coltext:'建筑面积'},
			   		{colval:'m4', coltext:'火灾危险等级'},
			   		{colval:'m5', coltext:'建筑结构'},
			   		{colval:'m6', coltext:'层数'},
			   		{colval:'m7', coltext:'备注'},
			   		{colval:'m8', coltext:'耐火等级'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/cjxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}