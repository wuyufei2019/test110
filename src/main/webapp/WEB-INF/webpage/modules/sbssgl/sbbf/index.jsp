<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备报废</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbf/index.js?v=1.1"></script>
</head>
<body >
<div id="sbssgl_sbbf_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="m1" id="m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备编号'" />
		<input name="m2" id="m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '设备名称',panelHeight:'150px'" />
		<input name="m6" id="m6" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '购入时间',editable: false" />
		<input name="gdsynx" id="gdsynx" class="easyui-textbox" style="width: 120px;height: 30px;" data-options="prompt: '规定使用年限'" />
		<input name="sjsynx" id="sjsynx" class="easyui-textbox" style="width: 120px;height: 30px;" data-options="prompt: '实际使用年限'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<shiro:hasPermission name="sbssgl:sbbf:add">
			       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbbf:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbbf:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbbf:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="sbssgl:sbbf:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出设备报废申请单"><i class="fa fa-file-word-o"></i> 导出设备报废申请单</button>
        	</shiro:hasPermission>
		        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			
				</div>
			<div class="pull-right">
			</div>
		</div>
	</div> 	   
</div>
<table id="sbssgl_sbbf_dg"></table> 

<script>
	var role = '0';//通过或驳回
	var uploadrole = '0';//上传附件 
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
</script>
<shiro:hasPermission name="sbssgl:sbbf:operation">
	<script>role = '1';</script>
</shiro:hasPermission>
<shiro:hasPermission name="sbssgl:sbbf:upload">
	<script>uploadrole = '1';</script>
</shiro:hasPermission>
</body>
</html>