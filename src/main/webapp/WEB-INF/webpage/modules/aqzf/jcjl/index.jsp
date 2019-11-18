<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/jcjl/index.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqzf_jcjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="aqzf_jcjl_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
    	<input name="aqzf_jcjl_M6" class="easyui-datetimebox"  style="height: 30px;" data-options="editable:false,prompt: '检查起始时间'" />
	    <input name="aqzf_jcjl_M7" class="easyui-datetimebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />	  	  	      
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqzf:jcjl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addJcjl()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 	 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="aqzf:jcjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission> 
        	<shiro:hasPermission name="aqzf:jcjl:exportword">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出现场检查记录"><i class="fa fa-file-word-o"></i> 导出现场检查记录</button> 
        	</shiro:hasPermission> 
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>
<table id="aqzf_jcjl_dg"></table> 
<script type="text/javascript">
var laspadd = '0';
</script>
<shiro:hasPermission name="ybcf:lasp:add">
<script type="text/javascript">
laspadd = '1';
</script>
</shiro:hasPermission>
</body>
</html>