<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/tjfx/index.js?v=1.5"></script>
</head>
<body >
<div id="yhpc_tjfx_tb" style="padding:5px;height:auto">
	<div style="width: 100%;text-align: center;margin-top: -5px;"><span style="color: red;font-size: 28px;">企业巡检点统计分析表</span></div> 
	

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
			    <input type="text" id="yhpc_tjfx_qyname" name="yhpc_tjfx_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
			    <input name="yhpc_tjfx_starttime" id="yhpc_tjfx_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检时间起'" />
    			<input name="yhpc_tjfx_finishtime" id="yhpc_tjfx_finishtime"  class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '巡检时间止'" />	 
    			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>     
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="fileexport()" ><i class="fa fa-external-link"></i> 导出</span>             
			</form>
		</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
</div>

<table id="yhpc_tjfx_dg"></table> 
<script type="text/javascript">
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}  

function myformatter2(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-01';  
}  

$(function(){  
   //设置时间  
　 var curr_time = new Date();     
 　 $("#yhpc_tjfx_starttime").datebox("setValue",myformatter2(curr_time));  
 　 $("#yhpc_tjfx_finishtime").datebox("setValue",myformatter(curr_time));  
});  
</script>
</body>
</html>