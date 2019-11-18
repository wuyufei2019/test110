<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全文件管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/aqwjfb/index_qy.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_aqwj_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="issue_aqwjfb_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '文件名称'" />
		<input type="text" name="check_company_starttime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '开始日期' "/>
		<input type="text" name="check_company_endtime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '结束日期' "/>
        <input type="text" id="issue_islook" name="issue_islook" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '查阅情况',  data:[{value:'0',text:'未查看'},{value:'1',text:'已查看'}]"/>
        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> 
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>
<table id="issue_aqwj_dg"></table> 
<script type="text/javascript">
   var f='${sys}';
</script> 
</body>
</html>