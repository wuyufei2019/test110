<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>app版本管理</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="版本发布"><i class="fa fa-plus"></i> 版本发布</button>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
<!--         		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
 -->	            <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
		
		
			</div>
<!-- 		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
		</div> -->
	</div>
	</div> 
	   
</div>


<table id="app_version_dg"></table> 
<script type="text/javascript">
var dg;
$(function() {
	dg = $('#app_version_dg').datagrid(
			{
				method : "post",
				url : ctx + '/system/version/list',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 5,
				singleSelect : true,
				striped : true,
				columns : [ [
						{
							field : 'id',
							title : 'id',
							checkbox : true,
							width : 50,
							align : 'center'
						},
						{
							field : 'name',
							title : '版本名称',
							sortable : false,
							width : 80 
						},
						{
							field : 'versionNo',
							title : '版本号',
							sortable : false,
							width : 50 
						},
						{
							field : 'type',
							title : '类型',
							sortable : false,
							width : 50 ,
							formatter:function(value){
								return value==1?"安监":"企业";
							}
						},
						{
							field : 'fileurl',
							title : '新版本地址',
							sortable : false,
							width : 200,
							align : 'center',
							formatter:function(value){
								if(value)
									return "<a href="+value+">"+value+"</a>";
								else
									return '';
							}
						},
						{
							field : 'message',
							title : '更新内容',
							sortable : false,
							width : 100,
							align : 'center'
						},

				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
      			checkOnSelect : false,
      			selectOnCheck : false,
			});

});

// 弹窗增加
function add(u) {
	openDialog("添加新版本", ctx + "/system/version/create/", "800px", "400px", "");
}


// 删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/system/version/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});

}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialog("修改版本信息", ctx + "/system/version/update/" + row.id, "900px","550px", "");

}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}

	openDialogView("查看版本信息", ctx + "/system/version/view/" + row.id, "800px","400px", "");

}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

</script>

</body>
</html>