<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全标示库管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sekb/aqbskgl/index.js?v=3"></script>
	<style>
		.datagrid-cell{
			line-height:50px
		}
	</style>
</head>
<body >
<!-- 工具栏 -->
<div id="sekb_aqbskgl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input style="height: 30px;" id="aqbskgllb" name="aqbskgllb"  class="easyui-combobox" data-options="editable:false ,
									valueField: 'text',textField: 'text',prompt:'类别',
									data: [
								        {value:'禁止标志',text:'禁止标志'},
								        {value:'警告标志',text:'警告标志'},
								        {value:'指令标志',text:'指令标志'},
								        {value:'提示标志',text:'提示标志'},
								        {value:'其他',text:'其他'},]"/>   
		<input type="text" id="sekb_aqbskgl_bt_name" name="sekb_aqbskgl_bt_name" style="height: 30px;" class="easyui-textbox" data-options="prompt: '名称'"/> 
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sekb:aqbskgl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:aqbskgl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:aqbskgl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="sekb_aqbskgl_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>