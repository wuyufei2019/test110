<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>平台资源库</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sekb/ptzyk/searchIndex.js?v=2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="sekb_ptzyk_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input id="zdgl_flfg_m1_1" name="zdgl_flfg_m1_1" class="easyui-combobox" value="${m1_1 }" style="height: 30px;" data-options="prompt: '大类别',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'7',text:'其他'}]" />
		<input id="zdgl_flfg_m1" name="zdgl_flfg_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '小类别'" />
		<input type="text" id="zdgl_flfg_m2" name="zdgl_flfg_m2" style="height: 30px;" value="${m2 }" class="easyui-textbox" data-options="prompt: '名称'"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span> 
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="back()" ><i class="fa fa-backward"></i> 返回</span>         	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="sekb_ptzyk_dg"></table> 

<script type="text/javascript">
var wfwurl = '${wfwurl}';
var m2 = '${m2}';
var m1_1 = '${m1_1}';
</script>
</body>
</html>