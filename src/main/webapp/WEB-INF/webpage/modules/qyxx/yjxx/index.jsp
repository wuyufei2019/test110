<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>冶金信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/yjxx/index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_yjxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	<input type="text" id="bis_yjxx_cx_m1" name="bis_yjxx_cx_m1" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '类别名称',editable:false,data: [
										{value:'黑色金属铸造',text:'黑色金属铸造'},
								        {value:'有色金属铸造',text:'有色金属铸造'},
								        {value:'有色金属合金铸造',text:'有色金属合金铸造'},
								        {value:'铝合金压铸',text:'铝合金压铸'},
								        {value:'镁合金压铸',text:'镁合金压铸'}] ,
										"/>
	<input type="text" id="bis_yjxx_cx_m7" name="bis_yjxx_cx_m7" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '熔炼场所建筑物结构',editable:false,data: [
										{value:'混合结构',text:'混合结构'},
								        {value:'框架结构',text:'框架结构'},
								        {value:'框架剪力墙结构',text:'框架剪力墙结构'},
								        {value:'剪力墙结构',text:'剪力墙结构'},
								        {value:'框筒结构',text:'框筒结构'},
								        {value:'筒中筒结构',text:'筒中筒结构'},
								        {value:'钢网架、悬索结构',text:'钢网架、悬索结构'} ] ,
										"/>
	<input type="text" id="bis_yjxx_cx_m8" name="bis_yjxx_cx_m8" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '金属液体转运方式',editable:false,data: [
										{value:'轨道',text:'轨道'},
								        {value:'行车',text:'行车'},
								        {value:'人工抬运',text:'人工抬运'},
								        {value:'其他',text:'其他'},] ,
										"/>
<input type="text" id="bis_yjxx_cx_m3" name="bis_yjxx_cx_m3" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '载体是否为移动式',editable:false,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'}] ,
										"/>
<input type="text" id="bis_yjxx_cx_m4" name="bis_yjxx_cx_m4" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '加热方式',editable:false,data: [
										{value:'电炉',text:'电炉'},
								        {value:'天然气',text:'天然气'},
								        {value:'柴油',text:'柴油'},
								        {value:'生物燃料',text:'生物燃料'},
								        {value:'重油',text:'重油'},
								        {value:'其他',text:'其他'},] ,
										"/>																	        										
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:yjxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:yjxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:yjxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:yjxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:yjxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:yjxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/yjxx/exinjump','${ctx}/bis/yjxx/exin','${ctx}/static/templates/冶金信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_yjxx_dg"></table> 

</body>
</html>