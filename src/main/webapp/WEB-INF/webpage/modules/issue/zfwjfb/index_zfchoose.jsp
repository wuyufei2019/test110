<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>政府人员选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/zfwjfb/index_zfchoose.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_zfchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		
		<input type="text"  name="wghgl_zfwjfb_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchqy()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>
	<div class="pull-left" style="margin-top:-45px;margin-left:180px">
   </div>
</div>
<c:if test="${flag eq 'add' }">

<table id="issue_zfchoose_dg"></table> 
</c:if>
<c:if test="${flag ne 'add' }">
<input id="wjid" type="hidden" value="${flag} "/>
<table id="issue_zfchoose_dg2"></table> 
</c:if>
</body>
</html>