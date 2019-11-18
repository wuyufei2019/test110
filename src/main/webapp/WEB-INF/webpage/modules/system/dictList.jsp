<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/common/contabs.js"></script> 
 
</head>
<body class="gray-bg">

<!-- 工具栏 -->
<div id="tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
	<div class="form-group">
    	<span>标签：</span><input type="text" name="filter_LIKES_label" class="form-control"  />
    	<span>值：</span><input type="text" name="filter_LIKES_value" class="form-control" />
    	<span>类型：</span><input type="text" name="filter_LIKES_type" class="form-control"/>
       </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:dict:add">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:dict:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:dict:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
			</shiro:hasPermission>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
		</div>
	</div>
	</div> 
	   
</div>

<table id="dg"></table> 


<script type="text/javascript">
var dg;

$(function(){   
	dg=$('#dg').datagrid({    
	method: "POST",
    url:'${ctx}/system/dict/json', 
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	idField : 'id',
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 300 ],
	scrollbarSize:5,
	singleSelect:true,
    columns:[[
		{field:'id',title:'id',hidden:true},  
		{field:'label',title:'标签',sortable:true,width:100},
		{field:'value',title:'值',sortable:true,width:100},
        {field:'type',title:'类型',sortable:true,width:100},    
        {field:'sort',title:'排序',sortable:true},
        {field:'description',title:'描述',sortable:true,width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb'
});
});

//弹窗增加
function add() {
	
	openDialog("添加字典","${ctx}"+"/system/dict/create","800px", "500px","");

}

//删除
function del(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:"${ctx}/system/dict/delete/"+row.id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
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
		
	openDialog("修改字典","${ctx}/system/dict/update/"+row.id,"800px", "500px","");

}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('reload',obj); 
}

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}


</script>
</body>
</html>