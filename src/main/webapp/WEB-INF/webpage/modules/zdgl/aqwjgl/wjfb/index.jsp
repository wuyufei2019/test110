<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全文件发布</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zdgl/aqwjgl/wjfb/index.js?v=1"></script>
</head>
<body >
<div id="zdgl_wjfb_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="zdgl_wjfb_m1" name="zdgl_wjfb_m1" style="height: 30px;" class="easyui-textbox" data-options="prompt: '文件名称'"/>
		<input id="zdgl_wjfb_m3" name="zdgl_wjfb_m3" class="easyui-combobox" style="height: 30px;" data-options="prompt: '类别',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'国家总局'},
								        {value:'2',text:'省局'},
								        {value:'3',text:'市局'},
								        {value:'4',text:'区县级'},
								        {value:'5',text:'行业'},
								        {value:'6',text:'主管部门'},
								        {value:'7',text:'公司'},
								        {value:'8',text:'部门'},
								        {value:'9',text:'其他'}]" />
		<input id="zdgl_wjfb_m4" name="zdgl_wjfb_m4" class="easyui-combobox" style="height: 30px;" data-options="prompt: '性质',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'转发'},
								        {value:'2',text:'内部'},
								        {value:'3',text:'其他'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="zdgl:wjfb:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="文件发布"><i class="fa fa-plus"></i> 文件发布</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:wjfb:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:wjfb:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zdgl:wjfb:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="zdgl_wjfb_dg"></table> 
<script>var role = '1';</script>
<shiro:hasPermission name="zdgl:wjfb:sh">
	<shiro:lacksPermission  name="zdgl:wjfb:sp">
		<script>role = '2';</script>
	</shiro:lacksPermission >
	<shiro:hasPermission name="zdgl:wjfb:sp">
		<script>role = '4';</script>
	</shiro:hasPermission>
</shiro:hasPermission>
<shiro:lacksPermission  name="zdgl:wjfb:sh">
	<shiro:hasPermission name="zdgl:wjfb:sp">
		<script>role = '3';</script>
	</shiro:hasPermission>
</shiro:lacksPermission >
</body>
</html>