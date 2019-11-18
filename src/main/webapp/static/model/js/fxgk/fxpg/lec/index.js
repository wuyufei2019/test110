var dg;
var d;
$(function(){
	dg=$('#fxpg_lec_dg').datagrid({    
	method: "post",
    url:ctx+'/fxpg/lec/list', 
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
        {field:'deptname',title:'部门',sortable:false,width:50},    
        {field:'jobname',title:'岗位/工种名称',sortable:false,width:50},    
        {field:'work',title:'工作任务',sortable:false,width:80},    
        {field:'analysisper',title:'分析人',sortable:false,width:30},    
        {field:'analysistime',title:'分析时间',sortable:false,width:30,
        	formatter :function(value,row){
        	return new Date(value).format("yyyy-MM-dd");
        }},    
        {field:'reviewer',title:'审核人',sortable:false,width:30},    
        {field:'auditor',title:'审定人',sortable:false,width:30},   
        {field:'operation',title:'操作',sortable:false,width:30,formatter :function(value,row){
        	return "<a class='btn btn-warning btn-xs' onclick='viewaction("+row.id+")'>修改风险活动</a>"+
        	"<a class='btn btn-success btn-xs' onclick='exin("+row.id+")'>导入风险活动</a>"
        }}   
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
    toolbar:'#fxpg_lec_tb'
	});
	
});
//查看风险活动
function viewaction(id) {
	riskid=id;
	layer.open({
		type: 1,  
		area: ['90%', '90%'],
		title:'修改风险活动',
		content: $("#select_action"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	    d =$('#action').datagrid({
	    	    	url :ctx+"/fxpg/lec/actionlist/" + id,
	    	    	fitColumns : true,
					border : true,
					striped : true,
					rownumbers : true,
					nowrap : false,
					idField : 'id',
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [ {
						field : 'workaction',
						title : '工作步骤',
						sortable : false,
						width : 100
					}, {
						field : 'potentialhazard',
						title : '潜在危害',
						sortable : false,
						width : 100
					}, {
						field : 'mainresult',
						title : '主要后果',
						sortable : false,
						width : 100
					}, {
						field : 'safetymeasure',
						title : '现有安全措施',
						sortable : false,
						width : 100
					}, {
						field : 'possibility',
						title : '可能性(等级)',
						sortable : false,
						width : 50
					}, {
						field : 'exposefrequency',
						title : '暴露频率(等级)',
						sortable : false,
						width : 50
					},{
						field : 'severity',
						title : '严重度(等级)',
						sortable : false,
						width : 50
					}, {
						field : 'riskvalue',
						title : '风险值',
						sortable : false,
						width : 50
					}, {
						field : 'risklevel',
						title : '风险等级',
						sortable : false,
						width : 50
					}, {
						field : 'improvemeasure',
						title : '改进措施',
						sortable : false,
						width : 100
					}, {
						field : 'operation',
						title : '操作',
						sortable : false,
						width : 50,
						formatter : function(value, row) {
							return "<a class='btn btn-warning btn-xs' onclick='updaction(" + row.id+ ")'>修改</a>" 
							+ "<a class='btn btn-danger btn-xs' onclick='deleterow(" + row.id + ")'>删除</a>";
						}
					}

					] ],
					onLoadSuccess : function() {
					},
					checkOnSelect : false,
					selectOnCheck : false
	    	    });
	      },
		cancel: function(index){ 
		}
	}); 
}

//弹窗增加
function add(u) {
	openDialog("添加风险评估信息",ctx+"/fxpg/lec/create/","90%", "90%","");
}
function addAction() {
	openDialog("添加风险活动信息",ctx+"/fxpg/lec/actioncreate/","700px", "400px","");
}
function updaction(id) {
	openDialog("修改风险活动信息",ctx+"/fxpg/lec/updateaction/"+id ,"700px", "400px","");
}
function deleterow(id) {
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/fxpg/lec/deleteact/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				d.datagrid('reload');
				d.datagrid('clearChecked');
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
	
	openDialog("修改风险评估信息",ctx+"/fxpg/lec/update/"+row.id,"800px", "400px","");
	
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
			url:ctx+"/fxpg/lec/delete/"+ids,
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
	
	openDialogView("查看风险评估信息",ctx+"/fxpg/lec/view/"+row.id,"90%", "90%","");
	
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

function exin(id){
    openImportDialog(ctx+'/fxpg/lec/exinjump',ctx+'/fxpg/lec/exin/'+id,ctx+'/static/templates/lec风险活动导入模板.xls')
}


//导出word
function fileexportword(){
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
	
	$.ajax({
		url:ctx+"/fxpg/lec/exportword/"+ids,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}