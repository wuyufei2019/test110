<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件接收与传递</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/aqscdt/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_scdt_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="issue_aqscdt_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '文件名称'" />
		<input type="text" name="check_company_fbstarttime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布开始日期' "/>
		<input type="text" name="check_company_fbendtime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布结束日期' "/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="issue:aqscdt:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="issue:aqscdt:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="issue:aqscdt:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
        	<shiro:hasPermission name="issue:aqscdt:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="issue:aqscdt:view">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-trash-o"></i> 查看</button> 
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> 
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="issue_scdt_dg"></table> 

</body>
</html>