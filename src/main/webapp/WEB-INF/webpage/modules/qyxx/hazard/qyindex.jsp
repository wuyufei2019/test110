<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/hazard/index.js?v=1.7"></script>
</head>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;">
			<input type="text" id="bis_hazard_wuzhi_name"
				name="bis_hazard_wuzhi_name" class="easyui-combobox"
				style="height:30px;"
				data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/hazardIdentity/wznmlist',prompt: '辨识物质' " />
			<input type="text" id="dj" name="dj" class="easyui-combobox"
				style="height:30px;"
				data-options="editable:false ,valueField: 'value',textField: 'text',prompt: '重大危险源级别',data: [
								        {value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'}]" />
			<span class="btn btn-primary btn-rounded btn-outline btn-sm "
				onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
				class="btn btn-primary btn-rounded btn-outline btn-sm "
				onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
		</form>
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="add()" title="添加">
						<i class="fa fa-plus"></i> 添加
					</button>
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="upd()" title="修改">
						<i class="fa fa-file-text-o"></i> 修改
					</button>
					<shiro:hasPermission name="bis:hazard:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="del()" title="删除">
						<i class="fa fa-trash-o"></i> 删除
					</button>
					</shiro:hasPermission>
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="view()" title="查看">
						<i class="fa fa-search-plus"></i> 查看
					</button>
					<%--<button class="btn btn-white btn-sm" data-toggle="tooltip"--%>
						<%--data-placement="left" onclick="fileexport()" title="导出">--%>
						<%--<i class="fa fa-external-link"></i> 导出--%>
					<%--</button>--%>
					<button class="btn btn-white btn-sm " data-toggle="tooltip"
							data-placement="left" onclick="refresh()" title="刷新">
							<i class="glyphicon glyphicon-repeat"></i> 刷新
						</button>
				</div>
			</div>
		</div>
	</div>
	<table id="dg"></table>
	<div style="display:none">
		<shiro:hasAnyRoles name="企业">
			<input type="hidden" id="bis_qy_qx" value="qy" />
		</shiro:hasAnyRoles>
	</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>