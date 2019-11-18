<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/jycf/cfgz/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="jycf_cfgz_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="jycf_cfgz_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '单位名称'" />
		<input type="text" class="easyui-datebox" name="jycf_cfgz_startdate"  style="height: 30px;" data-options="prompt: '开始时间'"  >
		<input type="text" class="easyui-datebox" name="jycf_cfgz_enddate"  style="height: 30px;" data-options="prompt: '结束时间'"  >
		<%-- <input  name="aqjg_jcjl_year" style="height: 30px;"class="easyui-combobox"data-options="prompt:'年份',valueField: 'id',textField: 'text',url:'${ctx}/aqjd/jcjh/year'" /> --%>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="jycf:cfgz:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加惩罚告知记录</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jycf:cfgz:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jycf:cfgz:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportgzs()" title="导出"><i class="fa fa-external-link"></i> 导出惩罚告知书</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>
<table id="jycf_cfgz_dg"></table> 
<script>
		
</script>
</body>
</html>