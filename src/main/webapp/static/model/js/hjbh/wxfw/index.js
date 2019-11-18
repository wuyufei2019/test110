var dg;
var d;
$(function(){
	dg=$('#hjbh_wxfw_dg').datagrid({    
	method: "post",
    url:ctx+'/hjbh/wxfw/list', 
    fit : true,
	fitColumns : true,
	idField : 'id',
    columns:[[    
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'year',title:'年度',sortable:false,width:40},    
        {field:'informant',title:'填报人',sortable:false,width:50},    
        {field:'phone',title:'填报人电话',sortable:false,width:60},    
        {field:'recordtime',title:'填报日期',sortable:false,width:80,formatter :function(value,row){
        	return new Date(value).format("yyyy-MM-dd");
        }},    
        {field:'principal',title:'单位负责人',sortable:false,width:50},
        {field:'operation',title:'操作',sortable:false,width:40,formatter :function(value,row){
        	return "<a class='btn btn-warning btn-xs' onclick='viewdetail("+row.id+")'>修改附表信息</a>"
        }}   
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view();
    },
    onLoadSuccess: function(){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#hjbh_wxfw_tb'
	});
	
});
//查看
function viewdetail(id) {
	RecodId=id;
	layer.open({
		type: 1,  
		area: ['90%', '90%'],
		title:'修改',
		content: $("#select_detail"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	    d =$('#detail').datagrid({
	    	    	url :ctx+"/hjbh/wxfw/detaillist/" + id,
	    	    	fitColumns : true,
					border : true,
					striped : true,
					rownumbers : true,
					nowrap : false,
					idField : 'id',
					scrollbarSize : 0,
					singleSelect : true,
					striped : true,
					columns : [ [ 
		         	{field : 'name',title : '废物名称',align : 'center',width : 40}, 
					{field : 'kind',title : '废物类别',align : 'center',width : 40}, 
					{field : 'resource',title : '产生源',align : 'center',width : 60}, 
					{field : 'unit',title : '计量单位',align : 'center',width : 30}, 
					{field : 'direction',title : '废物流向',align : 'center',width : 70}, 
					{field : 'm1',title : '外单位处置的企业',align : 'center',width : 70}, 
					{field : 'm2',title : '内部利用处理量',align : 'center',width : 40}, 
					{field : 'm3',title : '委托利用处理量',align : 'center',width : 40}, 
					{field : 'm4',title : '累计贮存量',align : 'center',width : 40}, 
					{field : 'm5',title : '年度产生量',align : 'center',width : 40},
					{field : 'operation',title : '操作',align : 'center',width : 60,
						formatter : function(value, row) {
							return "<a class='btn btn-warning btn-xs' onclick='upddetail(" + row.id + ")'>修改</a>"
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
	openDialog("添加危险废物信息",ctx+"/hjbh/wxfw/create/","90%", "60%","");
}
function adddetail() {
	openDialog("添加危险废物信息",ctx+"/hjbh/wxfw/detailcreate/","800px", "400px","");
}
function upddetail(id) {
	openDialog("修改危险废物信息",ctx+"/hjbh/wxfw/updatedetail/"+id ,"800px", "400px","");
}
function deleterow(id) {
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/hjbh/wxfw/deletedetail/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				d.datagrid('reload');
				d.datagrid('clearChecked');
				d.datagrid('clearSelections');
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
	
	openDialog("修改危险废物信息",ctx+"/hjbh/wxfw/update/"+row.id,"800px", "300px","");
	
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
			url:ctx+"/hjbh/wxfw/delete/"+ids,
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
	
	openDialogView("查看危险废物信息",ctx+"/hjbh/wxfw/view/"+row.id,"90%", "90%","");
	
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
		url:ctx+"/hjbh/wxfw/exportword/"+ids,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}