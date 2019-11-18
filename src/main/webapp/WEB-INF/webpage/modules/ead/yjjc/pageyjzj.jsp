<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title> </title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
	<script type="text/javascript" src="${ctxStatic}/model/js/ead/yjjc/pageyjzj.js?v=1.3"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 

</head>
<body>
<div id="yjjc_consequence_yjzj_map" style="height:100%;width:100%;border:#ccc solid 1px;position:absolute;"></div>
<script type="text/javascript">
var consequenceid='${consequenceid}';
initMap("yjjc_consequence_yjzb_map");
onloadyjzj();
</script>
</body>
</html>