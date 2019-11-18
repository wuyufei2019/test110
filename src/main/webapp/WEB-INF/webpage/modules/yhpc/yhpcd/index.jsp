<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/yhpcd/index.js?v=1.5"></script>
	<script type="text/javascript">
	var usertype = '${usertype}';
	</script>
</head>
<body >
<div id="yhpc_yhpcd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${usertype != '1' }">
			<input type="text" id="yhpc_yhpcd_cx_qyname" name="yhpc_yhpcd_cx_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>	
		<input type="text" id="yhpc_yhpcd_yhdname" name="yhpc_yhpcd_yhdname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '隐患自查点名称' "/>  	        
		<!-- <input name="yhpc_yhpcd_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '创建时间起'" />
	    <input name="yhpc_yhpcd_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '创建时间止'" />  -->
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>   
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="yhpc:yhpcd:add">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:yhpcd:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:yhpcd:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="yhpc:yhpcd:bind">
		       		<button class="btn btn-white btn-sm"  data-toggle="tooltip" data-placement="left" onclick="checkContentManage()" title="绑定巡检内容"><i class="fa fa-plus"></i> 绑定巡检内容</button>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="yhpc:yhpcd:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="yhpc:yhpcd:disable">
				<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disable()" title="停产"> 停产</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:yhpcd:enable">
				<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enable()" title="恢复"> 恢复</button>
			</shiro:hasPermission>
        	<shiro:hasPermission name="yhpc:yhpcd:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport(1)" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
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

<table id="yhpc_yhpcd_dg"></table> 
</body>
</html>