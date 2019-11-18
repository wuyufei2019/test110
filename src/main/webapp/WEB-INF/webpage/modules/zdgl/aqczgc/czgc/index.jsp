<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全操作规程</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zdgl/aqczgc/czgc/index.js?v=1.1"></script>
</head>
<body >
<div id="zdgl_czgc_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="zdgl_czgc_m1" name="zdgl_czgc_m1" style="height: 30px;" class="easyui-textbox" data-options="prompt: '安全操作规程名称'"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="zdgl:czgc:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:czgc:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:czgc:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:czgc:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zdgl:czgc:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/zdgl/czgc/exinjump','${ctx}/zdgl/czgc/exin','${ctx}/static/templates/安全操作规程导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
        	<shiro:hasPermission name="zdgl:czgc:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="zdgl_czgc_dg"></table> 
<script>var role = '1';var xd;</script>
<shiro:hasPermission name="zdgl:czgc:sh">
	<shiro:lacksPermission  name="zdgl:czgc:sp">
		<script>role = '2';</script>
	</shiro:lacksPermission >
	<shiro:hasPermission name="zdgl:czgc:sp">
		<script>role = '4';</script>
	</shiro:hasPermission>
</shiro:hasPermission>
<shiro:lacksPermission  name="zdgl:czgc:sh">
	<shiro:hasPermission name="zdgl:czgc:sp">
		<script>role = '3';</script>
	</shiro:hasPermission>
</shiro:lacksPermission >
<shiro:hasPermission name="zdgl:czgc:update">
    <script>xd = true;</script>
</shiro:hasPermission>
<script type="text/javascript">
var qyid = '${qyid}';
</script>
</body>
</html>