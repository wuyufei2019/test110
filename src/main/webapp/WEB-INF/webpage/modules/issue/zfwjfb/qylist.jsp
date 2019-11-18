<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title></title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">

<table id="userlist_dg"></table> 

<script type="text/javascript" >
var dg;


$(function(){
	dg=$('#userlist_dg').datagrid({    
	nowrap:false,
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:false,
	rownumbers:true,
	singleSelect:true,
    columns:[[     
        {field:'name',title:'用户名',sortable:false,width:100,align:'center'}
    ]],
    enableHeaderClickMenu: true,
    enableRowContextMenu: false
	});
	
	var data = $.parseJSON('${list}');    
	dg.datagrid('loadData',data);
  
});


</script>
</body>
</html>