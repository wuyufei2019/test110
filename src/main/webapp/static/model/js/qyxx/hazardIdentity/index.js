
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/hazardIdentity/list/'+demo,
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,	
	checkOnSelect:false,
    columns:[[
        {field:'ID',title:'ID',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'类别',sortable:false,width:100},
        {field:'m7',title:'危险性分类及说明',sortable:false,width:100,align:'center'},
        {field:'m2',title:'辨识物质',sortable:false,width:100,align:'center'},    
        {field:'m3',title:'最大储量（t）',sortable:false,width:100,align:'center'},
        {field:'m4',title:'临界储量（t）',sortable:false,width:100,align:'center'},
//        {field:'m5',title:'计算',sortable:false,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb'
	});
});

//增加
function add() {
	openDialog("添加重大危险源信息",ctx+'/bis/hazardIdentity/create/'+returnId,"900px","400px","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}
	
	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/hazardIdentity/delete/"+ids,
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

//修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改重大危险源信息",ctx+'/bis/hazardIdentity/update/'+row.ID,"900px", "400px","");
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null||row=='') {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看重大危险源信息",ctx+'/bis/hazardIdentity/view/'+row.ID,"900px", "400px","");
}

//查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	obj.bis_hazardIdentity_wuzhi_name=$("#bis_hazardIdentity_wuzhi_name").combobox('getText');
	obj.dj=$("#dj").combobox('getText');
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
			   		{colval:'m1', coltext:'类别'},
			   		{colval:'m2', coltext:'辨识物质'},
			   		{colval:'m3', coltext:'最大储量'},
			   		{colval:'m4', coltext:'临界储量'},
			   		{colval:'m6', coltext:'计算值 '},
		   	];
	
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: true, 
        shift: 1,
	    content: ctx+'/bis/hazardIdentity/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}