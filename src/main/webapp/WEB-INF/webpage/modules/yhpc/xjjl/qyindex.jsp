<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/xjjl/qyindex.js?v=2.0"></script>
	<style>
		.datagrid-cell{
			line-height:12px
		}
	</style>
</head>
<body >
<script type="text/javascript">
var iszdwxy='${iszdwxy}';
</script>
<div id="yhpc_xjjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
	<div class="form-group">
		<input name="yhpc_xjjl_jcdname" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '检查点名称'" />
		<input type="text" name="yhpc_xjjl_checkplan" class="easyui-combobox" style="height: 30px;" data-options="editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/yhpc/bcrw/bclist',prompt: '所属班次' "/>
		<input name="yhpc_xjjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检开始时间'" />
	    <input name="yhpc_xjjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检结束时间'" />
	    <input type="text" name="yhpc_xjjl_checkresult" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查结果',data: [
						         {value:'0',text:'无隐患'},
						         {value:'1',text:'有隐患'}] "/>
		<input name="iszdwxy" type="hidden" value="${iszdwxy }">
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
        	<shiro:hasPermission name="yhpc:xjjl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
        	</shiro:hasPermission>
			<shiro:hasPermission name="yhpc:xjjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

			</div>
		<div class="pull-right">
		</div>
	</div>
	</div>
</div>

<table id="yhpc_xjjl_dg"></table>
</body>
</html>