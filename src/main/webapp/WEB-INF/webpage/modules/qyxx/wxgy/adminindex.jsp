<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
  <head>
    <head>
  <title>危险工艺</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript" src="${ctx}/static/model/js/qyxx/wxgy/adminindex.js?v=1.3"></script>
  </head>
  
  <body>
    <div id="tb" style="padding:5px;height:auto">
    	<form id="searchForm" class="form-inline" style="margin-bottom:8px;">
    	<div class="form-group">
    	<input type="text" id="bis_gwgy_qy_name" name="bis_gwgy_qy_name" style="height:30px" class="easyui-combobox" data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
    		 <input type="text" id="bis_gwgy_cx_m1" name="bis_gwgy_cx_m1"  style="height:30px" class="easyui-combobox" data-options="editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/gwgy',prompt: '高危工艺' "/>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    	</div>
    	</form>
    	<div class="row">
    		<div class="col-sm-12">
    			<div class="pull-left">
    				<shiro:hasPermission name="bis:gwgy:add">
		       			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			       	</shiro:hasPermission>
			       	<shiro:hasPermission name="bis:gwgy:delete">
			            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="bis:gwgy:update">
			            <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
			        </shiro:hasPermission>
			        <shiro:hasPermission name="bis:gwgy:view">
		        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
		        	</shiro:hasPermission>
		        	<shiro:hasPermission name="bis:gwgy:export">
					    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
					</shiro:hasPermission>
		        	<shiro:hasPermission name="bis:gwgy:statistics">
				        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="statistics()" title="统计"><i class="fa fa-bar-chart"></i> 统计</button>	
					</shiro:hasPermission>
                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
    			</div>
    		</div>
    	</div>
    </div>
    <table id="dg"></table>
  </body>
</html>
