<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>危险废物特性列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx }/static/model/js/hjbh/wxgl/index.js?v=1.1"></script>
</head>
<style>
	.datagrid-body {
		overflow: hidden;
	}
</style>
   <div id="dxj_dxjbc_tb" style="padding:5px;height:auto;">
      <form id="searchForm" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if> 
         	<input type="text" name="name" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '废物名称',panelHeight:'100',valueField: 'text',textField: 'text'" />
               <input type="text" name="kind" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '废物类别',panelHeight:'100',valueField: 'text',textField: 'text'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
             	<shiro:hasPermission name="hjbh:wxgl:add">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:wxgl:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:wxgl:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="hjbh:wxgl:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
				       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

            </div>
         </div>
      </div>

   </div>

   
   
<table id="hjbh_wxgl_dg"></table> 

<div id="select_otherdw" style="display:none;height:100%">
      <shiro:hasPermission name="fxpg:jha:add">
         <button style="margin:5px" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addOtherDw()" title="添加">
            <i class="fa fa-plus"></i> 添加
         </button>
      </shiro:hasPermission>
      <table id="otherdw"></table>
      <!-- <div class="easyui-accordion" id="accordion" border="false"> -->
   </div>
<script type="text/javascript">
var dg;
var riskid;//全局变量，选中记录的id
var type = '${type}';
$(function(){
	if (type == '2') {//集团登录
		dg=$('#hjbh_wxgl_dg').datagrid({    
			method: "post",
		    url:ctx+'/hjbh/wxfwgl/list', 
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'ID',
			striped:true,
			pagination:true,
			rownumbers:true,
			nowrap:false,
			pageNumber:1,
			pageSize : 50,
			pageList : [ 50, 100, 150, 200, 250 ],
			scrollbarSize:0,
			singleSelect:true,
			striped:true,
		    columns:[[
					{field :'qyname',title : '企业名称',sortable : false,width : 80},
		  			{field : 'ID',title : '废物编号',checkbox : true,width : 50},
		  			{field : 'name',title : '废物名称',sortable : false,width : 50},
		  			{field : 'kind',title : '废物类别',sortable : false,width : 50},
					{field : 'danger_type',title : '主要危险性',sortable : false,width : 50,
						formatter : function(value, row, index) {
							var dangerType = "";
							if(value){
								var splitValue = value.split(",");
								for (var i = 0; i<splitValue.length; i++) {
									if(splitValue[i] == '腐蚀性') {
										dangerType += ('腐蚀性'+" ");  
				                  	}else if(splitValue[i] == '急性毒性'){
				                  		dangerType += ('急性毒性'+" ");  
				                  	}else if(splitValue[i] == '浸出毒性'){
				                  		dangerType += ('浸出毒性'+" ");  
				                  	}else if(splitValue[i] == '易燃性'){
				                  		dangerType += ('易燃性'+" ");  
				                  	}else if(splitValue[i] == '反应性'){
				                  		dangerType += ('反应性'+" ");  
				                  	}else if(splitValue[i] == '含毒性物质'){
				                  		dangerType += ('含毒性物质'+" ");  
				                  	}else if(splitValue[i] == '传染性物质'){
				                  		dangerType += ('传染性物质');  
				                  	}else{
				                  		dangerType += (splitValue[i]+" ");
				                  	}
								}
							}
		                  	return  dangerType;
		              	}	
					},
					{field : 'content',title : '主要化学组成',sortable : false,width : 50},
					{field : 'express_type',title : '废物表现形态',sortable : false,width : 50,
						formatter : function(value, row, index) {
		                  	if(value == '固态') {
		                  		return '固态';  
		                  	}else if(value == '半固态'){
		                  		return '半固态';  
		                  	}else if(value == '液态'){
		                  		return '液态';  
		                  	}else if(value == '气态'){
		                  		return '气态';  
		                  	}else{
		                  		return value;
		                  	}
		              	}	
					},
					{field : 'store_type',title : '贮存方式',sortable : false,width : 50,
						formatter : function(value, row, index) {
		                  	if(value == '圆桶') {
		                  		return '圆桶';  
		                  	}else if(value == '槽罐'){
		                  		return '槽罐';  
		                  	}else if(value == '编织袋'){
		                  		return '编织袋';  
		                  	}else{
		                  		return value;
		                  	}
		              	}	
					},
					{field : 'other_handler',title : '是否提供委托外单位利用处理',sortable : false,width : 60,
						formatter : function(value, row, index) {
		                  	if(value == '1') {
		                  		 return '是';
		                  	}else if (value == '0'){
		                  		return '否';
		                  	}
		              	}	
					},
			        {field:'operation',title:'操作',sortable:false,width:30,formatter :function(value,row){
			        	if (row.other_handler == '0') {
			        		return "";
			        	} else if (row.other_handler == '1') {
			        		return "<a class='btn btn-warning btn-xs' onclick='viewotherdw("+row.id+")'>修改外单位信息</a>"
			        	}
			        }}   
		    ]],
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		        view();
		    },
		    onLoadSuccess: function(){
		    	 $(this).datagrid("autoMergeCells",['qyname']);
		    },
		     onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
		    },
			checkOnSelect:false,
			selectOnCheck:false,
			toolbar:'#dxj_dxjbc_tb'
			});
	} else {//企业登录
		dg=$('#hjbh_wxgl_dg').datagrid({    
			method: "post",
		    url:ctx+'/hjbh/wxfwgl/list', 
		    fit : true,
			fitColumns : true,
			border : false,
			idField : 'ID',
			striped:true,
			pagination:true,
			rownumbers:true,
			nowrap:false,
			pageNumber:1,
			pageSize : 50,
			pageList : [ 50, 100, 150, 200, 250 ],
			scrollbarSize:0,
			singleSelect:true,
			striped:true,
		    columns:[[
		  			{field : 'ID',title : '废物编号',checkbox : true,width : 50},
		  			{field : 'name',title : '废物名称',sortable : false,width : 50},
		  			{field : 'kind',title : '废物类别',sortable : false,width : 50},
					{field : 'danger_type',title : '主要危险性',sortable : false,width : 50,
						formatter : function(value, row, index) {
							var dangerType = "";
							if(value){
								var splitValue = value.split(",");
								for (var i = 0; i<splitValue.length; i++) {
									if(splitValue[i] == '腐蚀性') {
										dangerType += ('腐蚀性'+" ");  
				                  	}else if(splitValue[i] == '急性毒性'){
				                  		dangerType += ('急性毒性'+" ");  
				                  	}else if(splitValue[i] == '浸出毒性'){
				                  		dangerType += ('浸出毒性'+" ");  
				                  	}else if(splitValue[i] == '易燃性'){
				                  		dangerType += ('易燃性'+" ");  
				                  	}else if(splitValue[i] == '反应性'){
				                  		dangerType += ('反应性'+" ");  
				                  	}else if(splitValue[i] == '含毒性物质'){
				                  		dangerType += ('含毒性物质'+" ");  
				                  	}else if(splitValue[i] == '传染性物质'){
				                  		dangerType += ('传染性物质');  
				                  	}else{
				                  		dangerType += (splitValue[i]+" ");
				                  	}
								}
							}
		                  	return  dangerType;
		              	}	
					},
					{field : 'content',title : '主要化学组成',sortable : false,width : 50},
					{field : 'express_type',title : '废物表现形态',sortable : false,width : 50,
						formatter : function(value, row, index) {
		                  	if(value == '固态') {
		                  		return '固态';  
		                  	}else if(value == '半固态'){
		                  		return '半固态';  
		                  	}else if(value == '液态'){
		                  		return '液态';  
		                  	}else if(value == '气态'){
		                  		return '气态';  
		                  	}else{
		                  		return value;
		                  	}
		              	}	
					},
					{field : 'store_type',title : '贮存方式',sortable : false,width : 50,
						formatter : function(value, row, index) {
		                  	if(value == '圆桶') {
		                  		return '圆桶';  
		                  	}else if(value == '槽罐'){
		                  		return '槽罐';  
		                  	}else if(value == '编织袋'){
		                  		return '编织袋';  
		                  	}else{
		                  		return value;
		                  	}
		              	}	
					},
					{field : 'other_handler',title : '是否提供委托外单位利用处理',sortable : false,width : 60,
						formatter : function(value, row, index) {
		                  	if(value == '1') {
		                  		 return '是';
		                  	}else if (value == '0'){
		                  		return '否';
		                  	}
		              	}	
					},
			        {field:'operation',title:'操作',sortable:false,width:30,formatter :function(value,row){
			        	//'是否提供委托外单位利用处理'的值为 0 时，不显示'修改外单位信息'按钮， 否则显示
			        	if (row.other_handler == '0') {
			        		return "";
			        	} else if (row.other_handler == '1') {
			        		return "<a class='btn btn-warning btn-xs' onclick='viewotherdw("+row.id+")'>修改外单位信息</a>"
			        	}
			        }}   
		    ]],
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		        view();
		    },
		    onLoadSuccess: function(){
		    },
		     onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
		    },
			checkOnSelect:false,
			selectOnCheck:false,
			toolbar:'#dxj_dxjbc_tb'
			});
	}
		
    
});
	
</script>

</body>
</html>