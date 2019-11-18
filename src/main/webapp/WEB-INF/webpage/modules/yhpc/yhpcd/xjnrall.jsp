<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/yhpcd/xjnrall.js?v=2.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_xjnrall_tb" style="padding:5px;height:auto">
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<form id="searchFrom" class="form-inline" action="">				
					<input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
											 editable:false ,panelHeight:'auto' ,data: [
											        {value:'1',text:'一般'},
											        {value:'2',text:'重大'}]"/>	
					<input name="checktitle" class="easyui-combobox"  style="height: 30px; " 
									data-options="editable:true ,prompt: '检查单元',valueField: 'text',textField: 'text',url:'${ctx}/yhpc/jcbk/gettype' " />
				</form>
			</div>
			<div class="pull-left" style="margin-left: 5px">
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
				<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="add()"><i class="fa fa-plus"></i> 绑定</button>  
			</div>
		</div>
	</div>
</div>
<table id="fxgk_xjnrall_dg" style="height: 308px;"></table> 
<script type="text/javascript">
var id1 = '${id1}'; 
var qyid= '${qyid}';
</script>
</body>
</html>