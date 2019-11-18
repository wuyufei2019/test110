<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>接触名单</title>
	<meta name="decorator" content="default"/>
<body >
	<!--搜索框-->
	<div class="">
		<input id="ss" class="easyui-searchbox" style="width:300px;height: 30px;" data-options="prompt: '请输入员工姓名',searcher:doSearch" />
	</div>
	<div id="bis_occupharm_jcmd_tb">
		<table id="bis_occupharm_dg_1"></table> 
	</div>
	
<script type="text/javascript">
var bmid = '${bmid}';
var gwname = '${gwname}';
var ygid = '${ygid}';
var winHeight = $(window).height();
$(function(){
	parent.dg=$('#bis_occupharm_jcmd_tb').datagrid({    
	method: "post",
    url: ctx+'/bis/occupharm/jcmd/json?bmid='+bmid+'&gwname='+gwname, 
    toolbar:'#bis_occupharm_dg_1',
    /* fit : true, */
    height: winHeight - 32,
	fitColumns : true,
	border : false,
	idField : 'id',
	textField: 'name',
	striped:true,
	singleSelect:false,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 10,
	scrollbarSize:5,
	striped:true,
    columns:[[    
    	{field:'id',title:'id',hidden: true},
		{field:'name',title:'姓名',width:190},
		{field:'sex',title:'性别',width:190},
		{field:'deptname',title:'部门名称',width:190},
    ]],
    onLoadSuccess: function(data) {
    	if (ygid != null && ygid != '') {
	    	var arr_ygid = ygid.split(",");
	    	for (var i = 0; i < arr_ygid.length; i++) {
	    		parent.dg.datagrid('selectRecord', arr_ygid[i]);
	    	}
    	}
    },
	checkOnSelect:false,
	selectOnCheck:false
	});
});

function doSearch(value){
	parent.dg.datagrid('reload', {keyword: value});
}
</script>
</body>
</html>