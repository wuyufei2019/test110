var dg;
var d;
$(function(){
	dg=$('#aqpx_stkxx_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/stkxx/list', 
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
              {field:'kc',title:'课程名称',width:60},   
              {field:'m1',title:'题目类型',width:50,formatter:function(value,row,index){
             		if(value=='1'){return '单选'}  
             		if(value=='2'){return '多选'}  
             		if(value=='3'){return '填空'}  
             		if(value=='4'){return '判断'}  
              }},
              {field:'m2',title:'题干',width:120 },
              {field:'m8',title:'答案',width:40,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onLoadSuccess(data){
        $(this).datagrid("autoMergeCells",['kc']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_stkxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加试题库信息",ctx+"/aqpx/stkxx/create/","800px", "400px","");
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
			url:ctx+"/aqpx/stkxx/delete/"+ids,
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
	
	openDialog("修改试题库信息",ctx+"/aqpx/stkxx/update/"+row.id,"800px", "400px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看试题库信息",ctx+"/aqpx/stkxx/view/"+row.id,"800px", "400px","");
	
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
	url=ctx+"/aqpx/stkxx/export";
	window.location.href=url;
}