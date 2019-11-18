<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>点巡检项目</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dxj/dxjxm/index.js?v=1.1"></script>
	<script type="text/javascript">
		var type = '${type}';
	</script>
</head>
<body>
<div id="dxj_dxjxm_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type != '1' }">
			<input type="text" id="dxj_dxjxm_qyname" name="dxj_dxjxm_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
			<input type="text" id="dxj_dxjxm_sbname" name="dxj_dxjxm_sbname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备名称' "/>
		</c:if>	
		<c:if test="${type != '2' }">
			<input type="text" id="dxj_dxjxm_sbname" name="dxj_dxjxm_sbname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/dxj/sb/sblist',prompt: '设备名称' "/>
		</c:if>
		<input type="text" id="dxj_dxjxm_sbxmname" name="dxj_dxjxm_sbxmname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备项目名称' "/>  	        
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>   
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="dxj:dxjxm:add">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dxj:dxjxm:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dxj:dxjxm:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dxj:dxjxm:sbgl">
		       	<button class="btn btn-white btn-sm"  data-toggle="tooltip" data-placement="left" onclick="sbgl()" title="设备管理"><i class="fa fa-bars"></i> 设备管理</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dxj:dxjxm:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="dxj:dxjxm:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	</span>
        	<span id="divper2">
			</span>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>

<table id="dxj_dxjxm_dg"></table> 
</body>
</html>