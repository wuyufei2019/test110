<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全网络管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/aqwl/index.js?v=1.0"></script>
	<script type="text/javascript" src="${ctx}/static/echarts/echarts.js"></script>
	<script type="text/javascript">
	var qyid='${qyid}';
	</script>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:aqwlgl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:aqwlgl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:aqwlgl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:aqwlgl:view">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:aqwlgl:view">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="viewTree()" title="架构图"><i class="fa fa-folder-open-o"></i> 架构图</button>
			</shiro:hasPermission>
        	 
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
 
</div>
<table id="dg"></table>
</body>
</html>