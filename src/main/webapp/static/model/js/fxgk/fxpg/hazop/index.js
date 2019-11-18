var dg;
var d;
$(function(){
	dg=$('#fxpg_hazop_dg').datagrid({    
	method: "post",
    url:ctx+'/fxpg/hazop/list', 
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
        {field:'question',title:'分析题目',sortable:false,width:70},    
        {field:'drawingnumber',title:'图纸编号',sortable:false,width:50},    
        {field:'revision',title:'修订号',sortable:false,width:50},    
        {field:'analysistime',title:'分析时间',sortable:false,width:50,
        	formatter :function(value,row){
        	return new Date(value).format("yyyy-MM-dd");
        }},    
        {field:'member',title:'小组成员',sortable:false,width:80},    
        {field:'meetingtime',title:'会议日期',sortable:false,width:30,
        	formatter :function(value,row){
        	return new Date(value).format("yyyy-MM-dd");
        }},
        {field:'analysispart',title:'分析部分',sortable:false,width:70},   
        {field:'operation',title:'操作',sortable:false,width:50,formatter :function(value,row){
        	return "<a class='btn btn-warning btn-xs' onclick='viewaction("+row.id+")'>修改过程</a>"+
        	"<a class='btn btn-success btn-xs' onclick='exin("+row.id+")'>导入过程</a>"
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
    toolbar:'#fxpg_hazop_tb'
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
	    	    	url :ctx+"/fxpg/hazop/actionlist/" + id,
	    	    	fitColumns : true,
					border : true,
					striped : true,
					rownumbers : true,
					nowrap : false,
					idField : 'id',
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [  {
						field : 'guidanceword',
						title : '引导词',
						sortable : false,
						width : 60
					}, {
						field : 'factor',
						title : '要素',
						sortable : false,
						width : 60
					}, {
						field : 'deviation',
						title : '偏差',
						sortable : false,
						width : 60
					}, {
						field : 'possiblereason',
						title : '可能原因',
						sortable : false,
						width : 100
					}, {
						field : 'result',
						title : '后果',
						sortable : false,
						width : 50
					}, {
						field : 'safetymeasure',
						title : '安全措施',
						sortable : false,
						width : 100
					}, {
						field : 'note',
						title : '注释',
						sortable : false,
						width : 50
					}, {
						field : 'suggestion',
						title : '建议安全措施',
						sortable : false,
						width : 100
					}, {
						field : 'executor',
						title : '执行人',
						sortable : false,
						width : 50
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
	openDialog("添加风险评估信息",ctx+"/fxpg/hazop/create/","90%", "90%","");
}
function addAction() {
	openDialog("添加风险活动信息",ctx+"/fxpg/hazop/actioncreate/","800px", "400px","");
}
function updaction(id) {
	openDialog("修改风险活动信息",ctx+"/fxpg/hazop/updateaction/"+id ,"800px", "400px","");
}
function deleterow(id) {
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/fxpg/hazop/deleteact/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				d.datagrid('reload');
				d.datagrid('clearChecked');
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
	
	openDialog("修改风险评估信息",ctx+"/fxpg/hazop/update/"+row.id,"800px", "300px","");
	
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
			url:ctx+"/fxpg/hazop/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
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
	
	openDialogView("查看风险评估信息",ctx+"/fxpg/hazop/view/"+row.id,"90%", "90%","");
	
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
    openImportDialog(ctx+'/fxpg/hazop/exinjump',ctx+'/fxpg/hazop/exin/'+id,ctx+'/static/templates/hazop风险过程模板.xls')
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
		url:ctx+"/fxpg/hazop/exportword/"+ids,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}