<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检班次任务设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/bcrw/index.js?v=1.3"></script>
</head>
<body >
<script type="text/javascript">
var userrole = '${userrole}';
var dg;
var dg2;

$(function(){
		dg=$('#yhpc_bcrw_dg').datagrid({    
		method: "post",
	    url:ctx+'/yhpc/bcrw/list', 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		idField : 'ID',
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
	  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
	  			{field : 'name',title : '班次名称',sortable : false,width : 100},
				{field : 'type',title : '班次类型',sortable : false,width : 100,align : 'center',
					formatter : function(value, row, index) {
	                  	if(value == '1') {
	                  		return '日检';  
	                  	}else if(value == '2'){
	                  		return '周检';  
	                  	}else if(value == '3'){
	                  		return '月检';  
	                  	}else if(value == '4'){
	                  		return '年检';  
	                  	}
	              	}	 
				}
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
	    toolbar:'#yhpc_bcrw_tb'
		});
	 
});

//我的任务
$(function(){
	dg2=$('#yhpc_bcrw_dg2').datagrid({    
	method: "post",
    url:ctx+'/yhpc/bcrw/myrwlist', 
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'ID',
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
  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'name',title : '班次名称',sortable : false,width : 100},
			{field : 'type',title : '班次类型',sortable : false,width : 100,align : 'center',
				formatter : function(value, row, index) {
                  	if(value == '1') {
                  		return '日检';  
                  	}else if(value == '2'){
                  		return '周检';  
                  	}else if(value == '3'){
                  		return '月检';  
                  	}else if(value == '4'){
                  		return '年检';  
                  	}
              	}	 
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view2();
    },
    onLoadSuccess: function(){
    },
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_bcrw_tb2'
	});
	
});
</script>
<div class="easyui-tabs" fit="true">
		<div title="班次设置" style="height:100%;" data-options="">
			<div id="yhpc_bcrw_tb" style="padding:5px;height:auto">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
				<input name="yhpc_bcrw_type" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '班次类型',data: [
													{value:'1',text:'日检'},
											        {value:'2',text:'周检'},
											        {value:'3',text:'月检'},
											        {value:'4',text:'年检'}
											        ]
													" />	
		        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  	        
			    </div>
				</form>
			
				<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
					<span id="divper">
						<shiro:hasPermission name="yhpc:bcrw:add">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:bcrw:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:bcrw:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="yhpc:bcrw:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        	</span>
			        	<span id="divper2">
						</span>
				       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					
						</div>
					<div class="pull-right">
					</div>
				</div>
				</div> 
			</div>
			<table id="yhpc_bcrw_dg"></table> 
	</div>
	
	<div title="我的任务" style="height:100%;">
			<div id="yhpc_bcrw_tb2" style="padding:5px;height:auto">
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
					       	<form id="searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline" >
					      	   <div class="form-group">
									<input name="yhpc_bcrw_type2" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '班次类型',data: [
													{value:'1',text:'日检'},
											        {value:'2',text:'周检'},
											        {value:'3',text:'月检'},
											        {value:'4',text:'年检'}]" />	  	
							        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
									<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>        
			    				</div> 
							</form>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="pull-left">
								    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
								    <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> 
								</div>
								<div class="pull-right">
			        			</div> 
			        		</div>
						</div> 
		        	</div>
	        	</div>  
        	</div> 
			<table id="yhpc_bcrw_dg2"></table> 
	</div>
</div>
 
</body>
</html>