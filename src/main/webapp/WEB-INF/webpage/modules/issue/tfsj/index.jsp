<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>突发事件</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/tfsj/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_tfsj_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="issue_tfsj_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '事件标题'" />
		<input type="text" name="issue_tfsj_fbstarttime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布开始日期' "/>
		<input type="text" name="issue_tfsj_fbendtime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布结束日期' "/>
		<input type="text" name="issue_tfsj_m5" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '处理状态', 
																												data:[{value:'1',text:'未处理'},{value:'2',text:'处理中'},{value:'3',text:'处理完成'}]"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="issue:tfsj:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="issue:tfsj:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="issue:tfsj:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
        	<shiro:hasPermission name="issue:tfsj:view">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-trash-o"></i> 查看</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="issue:tfsj:jspl">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="jspl()" title="处理完成"><i class="fa fa-check-square-o"></i> 处理完成</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> 
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>

<table id="issue_tfsj_dg"></table> 
</body>
</html>