<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
</head>
<body>
<table id="bis_hazardIdentity_dg" ></table> 

<script type="text/javascript">
var qyid="${qyid }";
$(function(){
zdwxybs();
});

function zdwxybs(){   
	$('#bis_hazardIdentity_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/hazardIdentity/ajlist/'+qyid, 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	border : false,
	idField : 'ID',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:0,
	singleSelect:true,
    columns:[[    
        {field:'ID',title:'ID',hidden:true},    
        {field:'m1',title:'类别',width:100},
        {field:'m2',title:'辨识物质',width:150,align:'center'},    
        {field:'m3',title:'最大储量（t）',width:70,align:'center'},
        {field:'m4',title:'临界储量（t）',width:70,align:'center'},
       // {field:'m5',title:'计算',width:50,align:'center'}
    ]]
	});
}




</script>
</body>
</html>