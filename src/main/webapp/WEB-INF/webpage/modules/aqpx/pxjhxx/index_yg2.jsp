<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/pxjhxx/ygindex2.js?v=1.0"></script>
<script type="text/javascript">
var ctx='${ctx}';
var jhid='${jhid}';
var type='${type}';

</script>
<style type="text/css">
.aqpx_pxjl_sj{
font-size: 12px;
margin: 8px 0px;
}
.aqpx_pxjl_sj input{
width: 13px;
margin: 0px;
padding: 0px;
}
.aqpx_pxjl_sjtk{
font-size: 12px;
margin: 5px 0px;
}
</style>
</head>
<body>
	<div title="考试记录" style="height:100%;">
		<div id="aqpx_pxjl_tb" style="padding:5px;height:auto">
       	<div>
       	<form id="aqpx_pxjl_searchFrom" >
			<input type="hidden" name="jhid"/>
		</form>
  
       	</div>    
			</div>

		<table id="aqpx_pxjl_dg"></table> 
	</div>
		
	<div id="aqpx_pxjl_dlg"></div>  
</body>
</html>