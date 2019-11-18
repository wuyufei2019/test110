var dg;
var d;
$(function(){
	dg=$('#fxpg_wxd_dg').datagrid({    
	method: "post",
    url:ctx+'/fxpg/wxd/list', 
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
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field :'qyname',title : '企业名称',sortable : false,width : 80},
        {field:'unit',title:'单元名称',sortable:false,width:50},    
        {field:'material',title:'涉及物料',sortable:false,width:80},    
        {field:'matter',title:'物质分值',sortable:false,width:30},    
        {field:'capacity',title:'容量分值',sortable:false,width:30},    
        {field:'temperature',title:'温度分值',sortable:false,width:30},    
        {field:'pressure',title:'压力分值',sortable:false,width:30},    
        {field:'operation',title:'操作分值',sortable:false,width:30},    
        {field:'riskvalue',title:'风险值',sortable:false,width:30},    
        {field:'risklevel',title:'风险等级',sortable:false,width:30},    
        {field:'improvemeasure',title:'改进措施',sortable:false,width:30}   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#fxpg_wxd_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加风险评估信息",ctx+"/fxpg/wxd/create/","90%", "90%","");
}


//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialog("修改风险评估信息",ctx+"/fxpg/wxd/update/"+row.id,"800px", "400px","");
	
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
			url:ctx+"/fxpg/wxd/delete/"+ids,
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



//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看风险评估信息",ctx+"/fxpg/wxd/view/"+row.id,"90%", "90%","");
	
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

