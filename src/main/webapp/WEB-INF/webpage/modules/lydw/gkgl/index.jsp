<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>工卡信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/lydw/gkgl/index.js?v=1.7"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="lydw_gkgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="fileid" class="easyui-textbox" style="height: 30px;" data-options="prompt: '标签号'" />
    	
    	<input type="text" name="fstatus" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '状态',data: [
								        {value:'1',text:'bit8-按键信息'},
								        {value:'2',text:'bit0-标签运动/禁止'},
								        {value:'3',text:'bit1-低电量提醒'},] "/>
		    <input type="text" name="online" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '是否在线',data: [
								        {value:'0',text:'离线'},
								        {value:'1',text:'在线'},]"/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>							        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="lydw:gkgl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="lydw:gkgl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="lydw:gkgl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="lydw:gkgl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="lydw:gkgl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="lydw:gkgl:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/lydw/gkgl/exinjump','${ctx}/lydw/gkgl/exin','${ctx}/static/templates/车间信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="lydw_gkgl_dg"></table> 

</body>
</html>