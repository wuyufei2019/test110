<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格巡检记录信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/xjjl/index.js?v=1.8"></script>
</head>
<body >
<div id="wghgl_xjjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	    <input type="text" id="wghgl_xjjl_qyname" name="wghgl_xjjl_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input name="wghgl_xjjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检开始时间'" />
	    <input name="wghgl_xjjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检结束时间'" />	
	    <input type="text" name="wghgl_xjjl_checkresult" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查结果',data: [
						         {value:'0',text:'无隐患'},
						         {value:'1',text:'有隐患'}] "/>  	
	    <input type="text"  name="wghgl_xjjl_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>  	
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="wghgl:xjjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="wghgl:xjjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
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

<table id="wghgl_xjjl_dg"></table> 
</body>
</html>