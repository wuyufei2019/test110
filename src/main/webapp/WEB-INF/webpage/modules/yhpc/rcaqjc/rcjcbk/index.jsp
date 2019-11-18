<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常检查表库</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcjcbk/index.js?v=1.3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="yhpc_rcjcbk_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
   	<input id="yhpc_rcjcbk_cx_m1" name="yhpc_rcjcbk_cx_m1" class="easyui-combobox" style="height: 30px;"
							data-options="panelHeight:'180px', prompt:'类别',editable:true ,valueField: 'text',textField: 'text',data: [
								        {value:'公司级1',text:'公司级'},
								        {value:'车间级',text:'车间级'},
								        {value:'班组级',text:'班组级'},
								        {value:'季节性',text:'季节性'},
								        {value:'节假日',text:'节假日'},
								        {value:'专项检查',text:'专项检查'},
								        {value:'其他',text:'其他'}] " />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>    
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="yhpc:rcjcbk:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:rcjcbk:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:rcjcbk:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:rcjcbk:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:rcjcbk:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/yhpc/rcjcbk/exinjump','${ctx}/yhpc/rcjcbk/exin','${ctx}/static/templates/隐患排查日常检查表库导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="yhpc_rcjcbk_dg"></table> 

</body>
</html>