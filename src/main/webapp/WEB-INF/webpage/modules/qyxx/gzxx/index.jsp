<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>工种（岗位）信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/gzxx/index.js?v=1.1"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="bis_gzxx_tb" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="bis:gzxx:add">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
							<i class="fa fa-plus"></i> 添加
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="bis:gzxx:update">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
							<i class="fa fa-file-text-o"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="bis:gzxx:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
							<i class="fa fa-trash-o"></i> 删除
						</button>
					</shiro:hasPermission>
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
						<i class="glyphicon glyphicon-repeat"></i> 刷新
					</button>

				</div>
			</div>
		</div>

	</div>
	<table id="bis_gzxx_dg"></table>
</body>
</html>