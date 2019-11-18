<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/yhpcjl/index.js?v=2.4"></script>
</head>
<body >
<c:if test="${type != '1'}">
	<div id="yhpc_yhpcjl_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
		<div class="form-group">
		<input type="text" id="yhpc_yhpcjl_qyname" name="yhpc_yhpcjl_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	        
	    <input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
									 editable:false ,panelHeight:'auto' ,data: [
									        {value:'1',text:'一般'},
									        {value:'2',text:'重大'}
															]
							    "/>
		<input name="yhpc_yhpcjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
	    <input name="yhpc_yhpcjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />
	    <input type="text" id="zgzt"name="zgzt" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患处理情况',data: [
							         {value:'0',text:'未整改'},
							         {value:'1',text:'整改待复查'},
							         {value:'2',text:'复查未通过'},
							         {value:'3',text:'整改完成'}] "/>
		<c:if test="${usertype != '1' }">
			<input type="text"  name="wghgl_xjjl_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>		
		</c:if>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>			    
	    </div>
		</form>
	
		<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
			<span id="divper">
				<shiro:hasPermission name="yhpc:yhpcjl:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
                </shiro:hasPermission><%--
                <shiro:hasPermission name="yhpc:yhpcjl:update">
                    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
                </shiro:hasPermission>--%>
				<shiro:hasPermission name="yhpc:yhpcjl:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
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
	<table id="yhpc_yhpcjl_dg"></table> 
</c:if>
<c:if test="${type == '1'}">
	<!-- <div class="easyui-tabs" fit="true">   -->
		<div title="自查隐患" style="height:100%;" data-options="">
			<div id="yhpc_yhpcjl_tb" style="padding:5px;height:auto">
				<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
			    <input class="easyui-combobox" name="yhjb" style="height: 30px;" data-options="prompt: '隐患级别' ,
											 editable:false ,panelHeight:'auto' ,data: [
											        {value:'1',text:'一般'},
											        {value:'2',text:'重大'}
																	]
									    "/>
				<input name="yhpc_yhpcjl_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
			    <input name="yhpc_yhpcjl_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />
			    <input type="text" id="zgzt"name="zgzt" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患处理情况',data: [
									         {value:'0',text:'未整改'},
									         {value:'1',text:'整改待复查'},
									         {value:'2',text:'复查未通过'},
									         {value:'3',text:'整改完成'}] "/>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>			    
			    </div>
				</form>
			
				<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
					<span id="divper">
						<shiro:hasPermission name="yhpc:yhpcjl:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission><%--
                        <shiro:hasPermission name="yhpc:yhpcjl:update">
                            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
                        </shiro:hasPermission>--%>
                        <shiro:hasPermission name="yhpc:yhpcjl:delete">
                            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
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
			<table id="yhpc_yhpcjl_dg"></table> 
		</div>
		
		<!-- <div title="网格隐患" style="height:100%;">
			<div id="wghgl_xjjlzg_tb" style="padding:5px;height:auto">
				<form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">				
					<div class="form-group">
						<input name="wghgl_xjjlzg_starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查开始时间'" />
					    <input name="wghgl_xjjlzg_finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '检查结束时间'" />	
					    <input type="text" id="wghgl_xjjlzg_dangerstatus" name="wghgl_xjjlzg_dangerstatus" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto',editable:false,prompt: '隐患状态',data: [
										         {value:'0',text:'未整改'},
										         {value:'1',text:'整改待复查'},
										         {value:'2',text:'复查未通过'},
										         {value:'3',text:'整改完成'}] "/>   	 
					    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
						<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>       
				    </div>
				</form>
			
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
						<span id="divper">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="viewxq()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
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
			<table id="wghgl_xjjlzg_dg"></table> 
		</div>
	</div> -->
</c:if>
<script type="text/javascript">
var f='${sys}';
var cljd='${cljd}';
var usertype = '${usertype}';
var type = '${type}';
var qyid = '${qyid}';
</script>   
</body>
</html>