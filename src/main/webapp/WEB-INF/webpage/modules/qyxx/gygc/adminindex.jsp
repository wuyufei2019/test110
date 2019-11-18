<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%><html>
<head>
<title></title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/qyxx/gygc/adminindex.js?v=1.6"></script>
</head>
<body>

<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom"  class="form-inline" style="margin-bottom: 8px;">
       	        <input id="bis_gygc_cx_id1" name="bis_gygc_cx_id1" class="easyui-combobox" style="height: 30px;"  data-options="width:150 ,valueField: 'text' , textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt:'企业名称' " />
       	        <input type="text" id="bis_gygc_cx_m1" name="bis_gygc_cx_m1" style="height: 30px;" class="easyui-combobox" data-options="editable:true,panelHeight:'150px',prompt: '类别',data: [
       	        		{value:'导热油炉',text:'导热油炉'},
				        {value:'污水处理池',text:'污水处理池'},
				        {value:'焚烧炉',text:'焚烧炉'},
				        {value:'配电房',text:'配电房'},
		        		{value:'空压机房',text:'空压机房'},
		        		{value:'锅炉房',text:'锅炉房'},
		        		{value:'制冷机房',text:'制冷机房'},
		        		{value:'消防布房',text:'消防布房'},
		        		{value:'供气站',text:'供气站'},
				        {value:'其他',text:'其他'}] "/>
       	        <input type="text" id="bis_gygc_cx_m3" name="bis_gygc_cx_m3" style="height: 30px;" class="easyui-combobox" data-options="editable:false,prompt: '建筑结构' ,panelHeight:'auto' ,valueField: 'text',textField: 'text',data: [
								        {value:'0',text:'钢混结构'},
								        {value:'1',text:'砖混'},
								        {value:'2',text:'钢结构'},
								        {value:'3',text:'框架'},
								        {value:'4',text:'其他'} ]
								"/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
		        <div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
			
				        <shiro:hasPermission name="bis:gygc:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				       	</shiro:hasPermission>
				        <shiro:hasPermission name="bis:gygc:update">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				        </shiro:hasPermission>
				       	<shiro:hasPermission name="bis:gygc:delete">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
				        </shiro:hasPermission>
				        <shiro:hasPermission name="bis:gygc:view">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="bis:gygc:export">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
						</shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
				</div>
			</div>
        </div>
  </div>
<table id="dg"></table> 
</body>
</html>