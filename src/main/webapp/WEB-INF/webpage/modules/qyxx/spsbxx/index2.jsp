<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频设备信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/spsbxx/index.js?v=1.0"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_spsbxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
	<div class="form-group">
		<input type="text" id="bis_spsbxx_cx_m1" name="bis_spsbxx_cx_m1" style="height:30px" class="easyui-textbox" data-options="width:200,prompt: '摄像头编码'"/>
		<input type="text" id="bis_spsbxx_cx_m3" name="bis_spsbxx_cx_m3" style="height:30px" class="easyui-textbox" data-options="width:200,prompt: '设备名称'"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

</div>


<table id="bis_spsbxx_dg"></table>

<script>
	var zdwxy = '${zdwxy}';

</script>
</body>
</html>