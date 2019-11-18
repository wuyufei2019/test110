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

 
#dituContent {
	height: 100%;
	width: 100%;
	 
}
 .BMap_cpyCtrl{ display:none; }  
 .anchorBL{ display:none;}   
 #dituContent label{max-width:none;}
</style>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/qyfbmapindex.js?v=1.2"></script>
<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
</head>
<body>
   <div id="r-result" style="margin:10px auto;" >
		&nbsp;&nbsp;
		<input id="keyword" type="text" class="easyui-textbox" style="width:150px;" value="" data-options="prompt: '企业名称' " />
          <button type="button" class="btn btn-info btn-xs" onclick="search('keyword')" style="width:50px" >搜索</button>
          <button type="button" class="btn btn-info btn-xs" onclick="reset()" style="width:70px">全部数据</button>
	</div>
	<div id="dituContent"></div>
<script type="text/javascript">
   var ctx = '${ctx}';
   var mappoint='${mappoint}';
   var index = top.layer.load();
   initMap("dituContent");
   window.createPolygon(mappoint);
    var data=[];//搜索用，减少后台交互次数
    var d=getJson(4);
	data=data.concat(d);
    d=getJson(3);
    data=data.concat(d);
    d=getJson(2);
    data=data.concat(d);
    d=getJson(1);
    data=data.concat(d);
	top.layer.close(index);
	window.searchClass = new SearchClass();
	searchClass.setData(data);
</script>
</body>
</html>