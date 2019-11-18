<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急处置卡</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/yjczk/index.js?v=1.0"></script>
	<script type="text/javascript">
	    var bistype = '${bistype}';
	</script>
</head>
<body >
<div id="fxgk_yjczk_tb" style="padding:5px;height:auto">
	<form id="searchForm" style="margin-bottom: 8px;" class="form-inline">
		<div class="form-group">
			<input type="text" name="fxgk_yjczk_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '岗位名称 '" />
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
				class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
		</div>
	</form>

	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<shiro:hasPermission name="fxgk:yjczk:add">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
						<i class="fa fa-plus"></i> 添加
					</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="fxgk:yjczk:update">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
						<i class="fa fa-file-text-o"></i> 修改
					</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="fxgk:yjczk:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
						<i class="fa fa-trash-o"></i> 删除
					</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="fxgk:yjczk:view">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
						<i class="fa fa-search-plus"></i> 查看
					</button>
				</shiro:hasPermission>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="reset()" title="刷新">
					<i class="glyphicon glyphicon-repeat"></i> 刷新
				</button>
			</div>
			<div class="pull-right"></div>
		</div>
	</div>
</div>
<table id="fxgk_yjczk_dg"></table>

</body>
</html>