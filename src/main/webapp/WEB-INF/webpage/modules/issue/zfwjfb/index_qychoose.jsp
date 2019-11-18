<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/zfwjfb/index_qychoose.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_qychoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="qynm"  class="easyui-textbox" style="height: 30px" data-options="prompt: '企业名称'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchqy()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>
	<div class="pull-left" style="margin-top:-45px;margin-left:180px">
   </div>
</div>
<input id="wjid" type="hidden" value="${wjid} "/>
<table id="issue_qychoose_dg2"></table> 
</body>
</html>