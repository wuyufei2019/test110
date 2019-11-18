<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/aqwjfb/index_qychoose.js?v=1.3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_qychoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" name="qynm"  class="easyui-textbox" style="height: 30px" data-options="prompt: '企业名称'" />
      	<input  name="qygm" class="easyui-combobox" style="height:30px;"
								data-options="prompt: '规模情况',
								panelHeight:50,editable:false ,data: [
										{value:'规上',text:'规上'},
										{value:'规下',text:'规下'}] " />
		<input type="text"  name="xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
	    <input type="checkbox" class="i-checks" id="xj" name="xj" value="1"><font style="size:14px" >不包含下级</font>
      	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchqy()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>
	<div class="pull-right" style="margin-top:-45px">
   </div>
</div>
<c:if test="${flag eq 'add' }">

<table id="issue_qychoose_dg"></table> 
</c:if>
<c:if test="${flag ne 'add' }">
<input id="wjid" type="hidden" value="${flag} "/>
<table id="issue_qychoose_dg2"></table> 
</c:if>
</body>
</html>