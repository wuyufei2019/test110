<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查点选择</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/bcrw/index_jcdchoose.js?v=1.5"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="yhpc_jcdchoose_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group" style="margin-top:10px">
	<c:if test="${flag eq '0'}">
		<input type="text" id="qyname" name="qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	</c:if>
		<input type="text" name="jcdname"  class="easyui-textbox" style="height: 30px" data-options="prompt: '检查点名称'" />
		<input type="text" name="jcdtype" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '检查点类型',data: [
						         {value:'1',text:'风险点'},
						         {value:'2',text:'隐患点'}] "/>
		<input type="text" name="fxfj" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险分级',panelHeight:'auto',editable:false,data: [
										{value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'},
								        {value:'5',text:'自定义点'}
								        ]"/> 
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchjcd()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
</div>
<table id="yhpc_jcdchoose_dg"></table> 
<script type="text/javascript">
//集团公司
var flag = '${flag}';
</script>
</body>
</html>