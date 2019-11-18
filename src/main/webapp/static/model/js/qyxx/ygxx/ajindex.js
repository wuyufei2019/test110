var dg;
var d;
$(function(){
	dg=$('#bis_ygxx_dg').datagrid({    
	method: "get",
    url:ctx+'/bis/ygxx/ajlist', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'}, 
        {field:'m1',title:'姓名',sortable:false,width:40},    
        {field:'m3',title:'性别',sortable:true,width:40,align:'center'},
        {field:'m8',title:'身份证号',sortable:false,width:100,align:'center'},
        {field:'m4',title:'岗位',sortable:true,width:60,align:'center'},
        {field:'m5',title:'学历',sortable:true,width:40,align:'center'},
        {field:'m9',title:'联系方式',sortable:false,width:70,align:'center'},
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_ygxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加员工信息",ctx+"/bis/ygxx/create/","800px", "400px","");
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
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/ygxx/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改员工信息",ctx+"/bis/ygxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看员工信息",ctx+"/bis/ygxx/view/"+row.id,"800px", "400px","");
	
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
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'qyname', coltext:'企业名称'},
			   		{colval:'M1', coltext:'姓名'},
			   		{colval:'M3', coltext:'性别'},
			   		{colval:'M4', coltext:'岗位'},
			   		{colval:'M5', coltext:'学历'},
			   		{colval:'M6', coltext:'专业'},
			   		{colval:'M7', coltext:'职称'},
			   		{colval:'bm', coltext:'部门'},
			   		{colval:'M8', coltext:'身份证号'},
			   		{colval:'M9', coltext:'联系方式'},
			   		{colval:'M10', coltext:'出生日期'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/ygxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}