<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/pxjhxx/ygindex.js?v=1.0"></script>
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
      	        <%-- <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="width:150,prompt: '课程名称'"/>
      	        <input type="text" id="aqpx_pxjl_cx_m3" name="aqpx_pxjl_cx_m3" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' , editable:false,data: [
								        {value:'合格',text:'合格'},
								        {value:'不合格',text:'不合格'} ] ,prompt: '考试结果'"/>
				<input type="hidden" name="type" value="${type }">
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="cx()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear1()" ><i class="fa fa-refresh"></i> 全部</span> --%>
			<input type="hidden" name="jhid"/>
		</form>
  
       	</div>    
			</div>

		<table id="aqpx_pxjl_dg"></table> 
	</div>
		
	<div id="aqpx_pxjl_dlg"></div>  
</body>
</html>