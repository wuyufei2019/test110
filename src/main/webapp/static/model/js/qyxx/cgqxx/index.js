var dg;
var d;
$(function(){
	dg=$('#bis_cgqxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/cgqxx/list', 
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
        {field:'m1',title:'储罐区编号',sortable:false,width:100},
        {field:'m2',title:'储罐区名称',sortable:false,width:100,align:'center'},
        {field:'m3',title:'储罐区面积(㎡)',sortable:true,width:100,align:'center'},
        {field:'m4',title:'储罐个数',sortable:true,width:100,align:'center'},
		{field:'m5',title:'罐间最小距离(m)',sortable:true,width:100,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_cgqxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加储罐区信息",ctx+"/bis/cgqxx/create/","800px", "550px","");
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
			url:ctx+"/bis/cgqxx/delete/"+ids,
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
	
	openDialog("修改储罐区信息",ctx+"/bis/cgqxx/update/"+row.id,"800px", "500px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看储罐区信息",ctx+"/bis/cgqxx/view/"+row.ID,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'储罐区编号'},
			   		{colval:'m2', coltext:'储罐区名称'},
			   		{colval:'m3', coltext:'储罐区面积(㎡)'},
			   		{colval:'m4', coltext:'储罐个数'},
			   		{colval:'m6', coltext:'经度'},
			   		{colval:'m7', coltext:'纬度'},
					{colval:'m5', coltext:'罐间最小距离'},
					{colval:'m8', coltext:'备注'}
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/cgqxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}