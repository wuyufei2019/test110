<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检内容管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxdxx/xjnrall.js?v=5"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_xjnrall_tb" style="padding:5px;height:40px">
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
				<shiro:hasPermission name="yhpc:jcbk:add">
			       		<button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="add2()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
			</div>
		</div>
	</div>
</div>
<table id="fxgk_xjnrall_dg" style="height: 86%"></table> 
<script type="text/javascript">
var id1 = '${id1}'; 
var qyid= '${qyid}';
var url = ctx+'/fxgk/fxxx/xjnralllist?id1='+id1+'&qyid='+qyid;
var dg;
$(function(){
	dg=$('#fxgk_xjnrall_dg').datagrid({    
	method: "get",
    url:url,
    fit : false,
	fitColumns : true,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:false,
	striped:true,
    columns:[[    
  			{field :'id',title : 'id',checkbox : true,width : 50,align : 'center'},  
  	        {field:'dangerlevel',title:'隐患级别',width:50,
  	        	formatter : function(value, row, index){
  	        		if(value=="1") return value='一般';
  	        		if(value=="2") return value='重大';
  	        	}
  	        },
  	        {field:'checktitle',title:'检查单元',width:80 },  
  	        {field:'content',title:'检查项目',width:150 },  
    ]],
    onLoadSuccess: function(){
    	 var rows=parent.dg.datagrid('getData').rows;
    	for(var i=0;i<rows.length;i++){
    		dg.datagrid('selectRecord',rows[i].id);
    	}
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:true,
	selectOnCheck:true,
    toolbar:'fxgk_xjnrall_tb'
	});
});

</script>
</body>
</html>