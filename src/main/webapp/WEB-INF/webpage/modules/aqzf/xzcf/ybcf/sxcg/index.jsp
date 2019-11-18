<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事先催告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/sxcg/index.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="xzcf_sxcg_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="xzcf_sxcg_number" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
		<input type="text" name="xzcf_sxcg_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '受处单位名称'" />
         <input type="text" id="type"name="xzcf_sxcg_type" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt:'强制执行申请条件', 
         data:[{value:'1',text:'满足'},{value:'0',text:'不满足'}]"/>
		<!-- <input type="text" class="easyui-datebox" name="xzcf_sxcg_startdate"  style="height: 30px;" data-options="prompt: '开始时间'"  >
		<input type="text" class="easyui-datebox" name="xzcf_sxcg_enddate"  style="height: 30px;" data-options="prompt: '结束时间'"  > -->
		<%-- <input  name="aqjg_jcjl_year" style="height: 30px;"class="easyui-combobox"data-options="prompt:'年份',valueField: 'id',textField: 'text',url:'${ctx}/aqjd/jcjh/year'" /> --%>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="xzcf:sxcg:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="xzcf:sxcg:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="xzcf:sxcg:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="xzcf:sxcg:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-file-word-o"></i> 导出事先催告书</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>
<table id="xzcf_sxcg_dg"></table> 
<script>
</script>
</body>
</html>