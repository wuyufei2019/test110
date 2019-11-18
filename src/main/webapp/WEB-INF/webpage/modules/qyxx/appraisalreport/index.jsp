<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>评价报告信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/appraisalreport/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqjg_aqba_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<c:if test="${usertype eq '0' }">
		<input type="text" id="aqjg_aqba_cx_qyname" name="aqjg_aqba_cx_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	</c:if>
		<input id="aqjg_aqba_cx_name" name="aqjg_aqba_cx_m3" class="easyui-combobox" value="" style="height: 30px;" data-options="panelHeight:'auto' ,prompt: '备案类别',data:[
									{value:'安全评价报告',text:'安全评价报告'},
									{value:'应急预案',text:'应急预案'},
									{value:'职业病危害报告',text:'职业病危害报告'}] " />
    	<input id="aqjg_aqba_cx_m4" name="aqjg_aqba_cx_m4" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '是否审核',editable:false,data: [
							        {value:'1',text:'已审核'},
							        {value:'0',text:'未审核'}]  "/>
    	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>   	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			</span>
			<shiro:hasPermission name="aqjg:aqba:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="aqjg:aqba:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqjg_aqba_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>