<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备项目选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dxj/dxjbc/index_jcdchoose.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="dxj_jcdchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="sbxmname"  class="easyui-textbox" style="height: 30px" data-options="prompt: '设备项目名称'" />
		<input type="text" name="sbname" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'100px',editable:true,prompt: '设备名称',valueField: 'text',textField: 'text',url:'${ctx}/dxj/sb/sblist'"/> 
    </div>
	</form>
	<div class="pull-left" style="margin-top:-37px;margin-left:350px">
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchjcd()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
   </div>
</div>
<table id="dxj_jcdchoose_dg"></table> 
</body>
</html>