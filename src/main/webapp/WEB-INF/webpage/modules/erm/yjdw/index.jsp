<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急队伍信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/erm/yjdw/index.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="erm_yjdw_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="erm_yjdw_cs_name" name="erm_yjdw_cs_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '队伍名称 '"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>   	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="erm:yjdw:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="erm:yjdw:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="erm:yjdw:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
		</span>
			<shiro:hasPermission name="erm:yjdw:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	
        	<shiro:hasPermission name="erm:yjdw:map">
        	  <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openMap()" title="地图查看"><i class="fa fa-search-plus"></i> 地图查看</button>
        	  </shiro:hasPermission>
        	<shiro:hasPermission name="erm:yjdw:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	     
        	<span id="divper2">
        	<shiro:hasPermission name="erm:yjdw:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/erm/yjdw/exinjump','${ctx}/erm/yjdw/exin','${ctx}/static/templates/应急队伍导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			</span>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="erm_yjdw_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>