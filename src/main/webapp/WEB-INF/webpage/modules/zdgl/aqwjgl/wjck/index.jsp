<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全查看</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/zdgl/aqwjgl/wjck/index.js?v=1"></script>
</head>
<body >
<div id="zdgl_wjck_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="zdgl_wjck_wjname" name="zdgl_wjck_wjname" style="height: 30px;" class="easyui-textbox" data-options="prompt: '文件名称'"/>
		<input id="zdgl_wjck_ydqk" name="zdgl_wjck_ydqk" class="easyui-combobox" style="height: 30px;" data-options="prompt: '阅读情况',panelHeight:'auto',editable:false,data: [
										{value:'0',text:'未查看'},
								        {value:'1',text:'已查看'}]" />
		<input id="zdgl_wjck_xzqk" name="zdgl_wjck_xzqk" class="easyui-combobox" style="height: 30px;" data-options="prompt: '下载情况',panelHeight:'auto',editable:false,data: [
										{value:'0',text:'未下载'},
								        {value:'1',text:'已下载'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="zdgl_wjck_dg"></table> 
</body>
</html>