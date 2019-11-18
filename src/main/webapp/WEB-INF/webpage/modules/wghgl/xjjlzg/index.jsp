<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格巡检隐患记录信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/xjjlzg/index.js?v=1.3"></script>
</head>
<body >
<div id="wghgl_xjjlzg_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	    <input type="text" id="wghgl_xjjlzg_qyname" name="wghgl_xjjlzg_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input name="wghgl_xjjlzg_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '发现隐患开始时间'" />
	    <input name="wghgl_xjjlzg_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '发现隐患结束时间'" />	
	    <input type="text" name="wghgl_xjjlzg_dangerstatus" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患状态',data: [
						         {value:'0',text:'未整改'},
						         {value:'1',text:'整改待复查'},
						         {value:'2',text:'复查未通过'},
						         {value:'3',text:'整改完成'}] "/> 
	    <input type="text"  name="wghgl_xjjlzg_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/> 
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span> 	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="wghgl:xjjlzg:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="wghgl:xjjlzg:view">
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

<table id="wghgl_xjjlzg_dg"></table> 
</body>
</html>