<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/aqpxjl/ygindex.js?v=1.4"></script>
<script type="text/javascript">
var ctx='${ctx}';
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
<div class="easyui-tabs" fit="true">
		<div title="考试记录" style="height:100%;">
			<div id="aqpx_pxjl_tb" style="padding:5px;height:auto">
        	<div>
        	<form id="aqpx_pxjl_searchFrom" >
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" data-options="width:150,prompt: '课程名称'"/>
		        <input type="hidden" id="kclx" name="kclx" value="1" />
		        <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>  
				<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-delete" plain="true" onclick="clearA()">清空</a>
			</form>
   
        	</div>    
 			</div>

			<table id="aqpx_pxjl_dg"></table> 
		</div>
		
		<div title="学习记录" style="height:100%;">
			<div id="aqpx_pxjl_tb2" style="padding:5px;height:auto">
        	<div>
        	<form id="aqpx_pxjl_searchFrom2" >
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" data-options="width:150,prompt: '课程名称'"/>
		        <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx2()">查询</a>
		        
			</form>
   
        	</div>    
 			</div>

			<table id="aqpx_pxjl_dg2"></table> 
		</div>

</div>


<div id="aqpx_pxjl_dlg"></div>  

</body>
</html>