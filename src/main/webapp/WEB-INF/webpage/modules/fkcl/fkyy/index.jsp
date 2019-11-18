<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>访客预约信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fkcl/fkyy/index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="fkcl_fkyy_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
			<input type="text" name="fkcl_fkyy_m1" style="height:30px" class="easyui-datebox" data-options="editable:false,prompt: '预约日期'"/>	
			<input type="text" name="fkcl_fkyy_m2" style="height: 30px;" class="easyui-textbox"  data-options="prompt: '被预约人'" />
			<input type="text" name="fkcl_fkyy_status" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto',prompt:'预约状态',editable:false,data: [
										{value:'1',text:'预约确认中'},
								        {value:'2',text:'拒绝预约'},
								        {value:'3',text:'预约通过待进厂'},
								        {value:'4',text:'进厂待出厂'},
								        {value:'5',text:'已出厂'}]"/>	
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>															        										
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="fkcl:fkyy:add">
		       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="预约申请"><i class="fa fa-plus"></i> 预约申请</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fkcl:fkyy:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="fkcl:fkyy:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="fkcl:fkyy:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fkcl:fkyy:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
	</div>
	</div>  
</div>
<table id="fkcl_fkyy_dg"></table> 
<script>var yyqr = '1';</script>
<shiro:hasPermission name="fkcl:fkyy:yyqr">
	<script>yyqr = '2';</script>
</shiro:hasPermission>
<script>var jccqr = '1';</script>
<shiro:hasPermission name="fkcl:fkyy:jccqr">
	<script>jccqr = '2';</script>
</shiro:hasPermission>
</body>
</html>