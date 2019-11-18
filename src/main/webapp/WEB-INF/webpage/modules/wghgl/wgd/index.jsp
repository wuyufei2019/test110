<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格点信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/wgd/index.js?v=2"></script>
</head>
<body >
<div id="wghgl_wgd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="wghgl_wgd_cx_qyname" name="wghgl_wgd_cx_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	        
    <input type="text"  name="wghgl_wgd_cx_wgxzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="wghgl:wgd:add">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="wghgl:wgd:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="wghgl:wgd:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="wghgl:wgd:bind">
		       		<button class="btn btn-white btn-sm"  data-toggle="tooltip" data-placement="left" onclick="checkContentManage()" title="绑定巡检内容"><i class="fa fa-plus"></i> 绑定巡检内容</button>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="wghgl:wgd:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="wghgl:wgd:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(1)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
            <shiro:hasPermission name="wghgl:wgd:export">
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportgzk()" title="导出巡检告知卡"><i class="fa fa-file-word-o"></i> 导出网格员巡检告知卡</button> 
            </shiro:hasPermission>
        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="plsc()" title="批量生成"><i class="fa fa-plus"></i> 批量生成</button> 
        	</span>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>

<table id="wghgl_wgd_dg"></table> 
</body>
</html>