<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/rcpxtj/ygindex.js?v=1.3"></script>
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
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课程名称'"/>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="cx()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear1()" ><i class="fa fa-refresh"></i> 全部</span> 
				<input type="hidden" name="aqpx_pxtj_cx_id"/>
			</form>
        	</div>    
 			</div>

			<table id="aqpx_pxjl_dg"></table> 
		</div>
		
		<div title="学习记录" style="height:100%;">
			<div id="aqpx_pxjl_tb2" style="padding:5px;height:auto">
        	<div>
        	<form id="aqpx_pxjl_searchFrom2" >
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课程名称'"/>
		        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="cx2()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear2()" ><i class="fa fa-refresh"></i> 全部</span>
		      	<input type="hidden" name="aqpx_pxtj_cx_id"/>  
			</form>
   
        	</div>    
 			</div>

			<table id="aqpx_pxjl_dg2"></table> 
		</div>

</div>


<div id="aqpx_pxjl_dlg"></div>  

</body>
</html>