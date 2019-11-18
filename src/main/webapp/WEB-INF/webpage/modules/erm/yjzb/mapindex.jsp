 <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
}
 
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

#r-result {
	width: 100%;
	clear: both;
	zoom:1;  
}

#dituContent {
	height: 100%;
	width: 100%;
	 
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/erm/yjzb/mapindex.js"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
</head>
<body>
   <div id="r-result" style="margin:5px;" >
		&nbsp;&nbsp;
		<input id="keyword" type="text" class="easyui-textbox" style="width:150px;" value="" data-options="prompt: '装备名称' " />
		<input type="button" class="easyui-linkbutton" value="搜索" onclick="search('keyword')" style="width: 50px;height: 22px;background-color:transparent;border:0;color:gray " />
		<input type="button"class="easyui-linkbutton" onclick="reset()" value="全部数据" style="width: 100px;height: 22px;background-color:transparent;border:0;color:gray " />
	</div>
	<div id="dituContent"></div>
   <script type="text/javascript">
      var ctx = '${ctx}';
  	  var cxtype = '${cxtype}';
      initMap("dituContent");
   </script>
</body>
</html>