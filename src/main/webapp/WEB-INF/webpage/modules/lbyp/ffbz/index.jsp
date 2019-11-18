<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品发放标准信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/lbyp/ffbz/index.js?v=1.0"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="lbyp_ffbz_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
			<div class="form-group">
				<input type="text" name="view_wpname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '用品名称'" />
				<input type="text" name="view_jobname" class="easyui-combobox" style="height: 30px;"
					data-options="prompt: '岗位/工种名称',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/gwgz'" />
				<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i>
					查询</span> <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i>
					全部</span>
			</div>
		</form>

		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="lbyp:ffbz:add">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
							<i class="fa fa-plus"></i> 添加
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="lbyp:ffbz:update">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
							<i class="fa fa-file-text-o"></i> 修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="lbyp:ffbz:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
							<i class="fa fa-trash-o"></i> 删除
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="lbyp:ffbz:view">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
							<i class="fa fa-search-plus"></i> 查看
						</button>
					</shiro:hasPermission>
					<%-- 	<shiro:hasPermission name="lbyp:ffbz:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="lbyp:ffbz:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/lbyp/ffbz/exinjump','${ctx}/lbyp/ffbz/exin','${ctx}/static/templates/仓库信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
					<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
						<i class="glyphicon glyphicon-repeat"></i> 刷新
					</button>

				</div>
			</div>
		</div>

	</div>


	<table id="lbyp_ffbz_dg"></table>

	<script type="text/javascript">
	var qyid = '${qyid}';
	</script>
</body>
</html>