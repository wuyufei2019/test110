<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消防设施登记</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/xfssgl/xfssdj/index.js?v=1.1"></script>
</head>
<body>
<div class="easyui-layout" style="height:100%; ">
	<div data-options="region:'west',split:true,border:false,title:'设施类别列表'"  style="width: 300px">
    	<div id="tg_tb2" style="padding:5px;height:auto">
		    <div>
		    	<shiro:hasPermission name="xfssgl:xfssdj:add">
		    	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addlb()" title="添加"><i class="fa fa-plus"></i> 添加</button>
		    	</shiro:hasPermission>
		        <shiro:hasPermission name="xfssgl:xfssdj:update">
  				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="updlb()" title="修改"><i class="fa fa-file-text-o"></i>修改</button>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="xfssgl:xfssdj:delete">
		        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="dellb()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
		        </shiro:hasPermission>
		    </div>
		</div>
		<table id="categoryDg"></table>
    </div>  
    <div data-options="region:'center',split:true,border:false,title:'设施详细信息'">
    	<div id="tg_tb" style="padding:5px;height:auto">
		    <div>
		    	<shiro:hasPermission name="xfssgl:xfssdj:add">
		    	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
		    	</shiro:hasPermission>
		        <shiro:hasPermission name="xfssgl:xfssdj:update">
  				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button>
		        </shiro:hasPermission>
		        <shiro:hasPermission name="xfssgl:xfssdj:delete">
		        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
				<shiro:hasPermission name="xfssgl:xfssdj:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>		
	        	<shiro:hasPermission name="xfssgl:xfssdj:export">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-file-excel-o"></i> 导出</button> 
	        	</shiro:hasPermission>       
				</shiro:hasPermission>
		    </div>
		</div>
    	<table id="dg"></table>
    </div>   
</div>
	 
    
<div id="dlg"></div> 
<div id="icon_dlg"></div>  

</body>
</html>