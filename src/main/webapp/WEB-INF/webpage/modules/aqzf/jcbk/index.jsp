<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查表库记录表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcbk/index.js?v=2"></script>
</head>
<body >
<div id="aqzf_jcbk_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="aqzf_jcbk_M1" name="aqzf_jcbk_M1" style="height: 30px;"
					class="easyui-combobox" data-options="editable:true,valueField:'id',textField:'text',url:'${ctx }/aqzf/jcdy/jcdylist',prompt: '检查单元'"/> 	  
	<input type="text" id="aqzf_jcbk_M2" name="aqzf_jcbk_M2" style="height: 30px;"
	                class="easyui-textbox" data-options="prompt: '检查内容'">				  	        
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="aqzf:jcbk:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcbk:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcbk:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcbk:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:jcbk:manage">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="manage()" title="检查单元管理"><i class="fa fa-bars"></i> 检查单元管理</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:jcbk:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqzf:jcbk:exin">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileimport()" title="导入"><i class="fa fa-external-link-square"></i> 导入</button> 
        	</shiro:hasPermission>
        	</span>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>

<table id="aqzf_jcbk_dg"></table> 
</body>
</html>