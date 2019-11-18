<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/cgxx/ajindex.js?v=1.8"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_cgxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="bis_cgxx_cx_qyname" name="bis_cgxx_cx_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input type="text" id="bis_cgxx_cx_m1" name="bis_cgxx_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '储罐名称'" />
		
    	<input  id="M6" name="M6" type="text" class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto',valueField: 'value',textField: 'text', prompt: '火灾危险性分类', data: [
							        {value:'1',text:'甲类'},
							        {value:'2',text:'乙类'},
							        {value:'3',text:'丙类'},
							        {value:'4',text:'丁类'},
							        {value:'5',text:'戊类'} ]
	    					" />
    	<input id="M7" name="M7" style="height: 30px;" class="easyui-combobox" type="text"
				data-options="editable:true ,prompt: '物料',validType:'length[0,100]',valueField: 'text',textField: 'text',url:'${ctx}/bis/wlxx/wldict'" />
		<input  id="M2" name="M2" type="text" class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto',valueField: 'value',textField: 'text', prompt: '储罐类型', data: [
									{value:'1',text:'立式圆筒形储罐'},
									{value:'2',text:'卧式圆筒形储罐'},
									{value:'3',text:'球形储罐'},
									{value:'4',text:'其他储罐'} ]
	    					" />    
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>       	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:cgxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cgxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cgxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cgxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:cgxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:cgxx:statistics">
		        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="statistics()" title="统计"><i class="fa fa-bar-chart"></i> 统计</button>
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_cgxx_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>