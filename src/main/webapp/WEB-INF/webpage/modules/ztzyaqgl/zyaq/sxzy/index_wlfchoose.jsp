<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzyaqgl/zyaq/sxzy/index_wlfchoose.js?v=1.0"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="zyaqgl_wlfchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
		<input type="text" id="aqzf_xgdw_m2" name="aqzf_xgdw_m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '单位名称'"/>
		<input name="aqzf_xgdw_m3" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'120',editable:false ,prompt: '行业类型', valueField:'text',textField:'text',url:'${ctx }/tcode/dict/hylx'" />
      	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchwlf()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>
	<div class="pull-right" style="margin-top:-45px">
   </div>
</div>

<table id="zyaqgl_wlfchoose_dg"></table> 
</body>
</html>