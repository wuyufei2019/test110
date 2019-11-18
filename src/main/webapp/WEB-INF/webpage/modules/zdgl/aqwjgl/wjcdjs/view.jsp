<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件传阅情况</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div class="easyui-tabs" fit="true">   
		<div title="已阅人员" style="height:100%;">
			<table id="zdgl_cyqk_dg1"></table> 
		</div>
		<div title="未阅人员" style="height:100%;">
			<table id="zdgl_cyqk_dg2"></table> 
		</div>
	</div>
<script type="text/javascript">
var wjid='${wjid}';
var dg1;
$(function(){
	dg1=$('#zdgl_cyqk_dg1').datagrid({    
	method: "post",
    url:ctx+'/zdgl/cdjs/cyqklist?wjid='+wjid+'&flag=1',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
  	        {field:'bm',title:'部门',width:50,align:'center'},  
  	        {field:'name',title:'姓名',width:80,align:'center'}
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
	
	dg2=$('#zdgl_cyqk_dg2').datagrid({    
	method: "post",
    url:ctx+'/zdgl/cdjs/cyqklist?wjid='+wjid+'&flag=2',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
  	        {field:'bm',title:'部门',width:50,align:'center'},  
  	        {field:'name',title:'姓名',width:80,align:'center'}
    ]],
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
});
</script>
</body>
</html>