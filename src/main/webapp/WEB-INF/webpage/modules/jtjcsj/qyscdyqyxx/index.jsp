<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业生产单元区域信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/jtjcsj/qyscdyqyxx/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="jtjcsj_qyscdyqyxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="jtjcsj_qyscdyqyxx_prodcelltype" name="jtjcsj_qyscdyqyxx_prodcelltype" class="easyui-combobox" style="height: 30px;" data-options="prompt: '生产区域类型',panelHeight:'auto',data:[{value:'HEV01',text:'罐区'},{value:'HEV02',text:'生产装置区'},{value:'HEV03',text:'库区'}]" />
		<input type="text" id="jtjcsj_qyscdyqyxx_prodcellname" name="jtjcsj_qyscdyqyxx_prodcellname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '生产单元区域名称'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="jtjcsj:qyscdyqyxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:qyscdyqyxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:qyscdyqyxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:qyscdyqyxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
<%--         	<shiro:hasPermission name="jtjcsj:qyscdyqyxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jtjcsj:qyscdyqyxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/jtjcsj/qyscdyqyxx/exinjump','${ctx}/jtjcsj/qyscdyqyxx/exin','${ctx}/static/templates/监测指标基础信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission> --%>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="jtjcsj_qyscdyqyxx_dg"></table> 

<script type="text/javascript">
var usertype = '${usertype}';
</script>
</body>
</html>