<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全规章制度与操作规程</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/ztzr/fzr/gzzd/index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ztzr_gzzd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">
		<div class="form-group">
			<input id="ztzr_gzzd_year" name="ztzr_gzzd_year" class="easyui-textbox" style="height: 30px;" data-options="prompt: '年度 ', editable:false "/>
			<input type="text" id="ajqyname" name="ajqyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</div>
	</form>
	<div class="pull-left">
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
		<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<shiro:hasPermission name="target:aqzz:view">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
				</shiro:hasPermission>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		</div>
	</div>
</div>
<table id="ztzr_gzzd_dg"></table>
<script type="text/javascript">
	var ajqyid='${ajqyid}';
	var usertype='${usertype}';
	var parid;

	//年份下拉框初始化
	$("#ztzr_gzzd_year").combobox({
		valueField:'year',
		textField:'year',
		panelHeight:'auto'
	});

	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear()+2;

	for(var i=0;i<6;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
	$("#ztzr_gzzd_year").combobox("loadData", data);//下拉框加载数据

	//创建查询对象并查询
	function search(){
		var obj=$("#searchFrom").serializeObject();
		dg.datagrid('load',obj);
	}

	function reset(){
		$("#searchFrom").form("clear");
		var obj=$("#searchFrom").serializeObject();
		dg.datagrid('load',obj);
	}

</script>

</body>
</html>