<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/cjxx/index.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_cjxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="bis_cjxx_cx_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '车间名称'" />
    	
    	<input type="text" id="hzwxx" name="hzwxx" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '火灾危险等级',data: [
								        {value:'1',text:'甲类'},
								        {value:'2',text:'乙类'},
								        {value:'3',text:'丙类'},
								        {value:'4',text:'丁类'},
								        {value:'5',text:'戊类'} ] "/>
		    <input type="text" id="jzjg" name="jzjg" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '建筑结构',data: [
								        {value:'0',text:'钢混结构'},
								        {value:'1',text:'砖混'},
								        {value:'2',text:'钢结构'},
								        {value:'3',text:'框架'},
								        {value:'4',text:'其他'} ]"/>
       	        <input type="text" id="cs" name="cs" class="easyui-combobox"  style="height: 30px;" data-options="editable:false ,valueField: 'value',textField: 'text',prompt: '层数',data: [
								        {value:'1',text:'1层'},
								        {value:'2',text:'2层'},
								        {value:'3',text:'3层'},
								        {value:'4',text:'4层'},
								        {value:'5',text:'5层'},
								        {value:'6',text:'6层'},
								        {value:'7',text:'7层'},
								        {value:'8',text:'8层'},
								        {value:'9',text:'9层'},
								        {value:'10',text:'10层'},
								        {value:'11',text:'11层'},
								        {value:'12',text:'12层'},
								        {value:'13',text:'13层'},
								        {value:'14',text:'14层'},
								        {value:'15',text:'15层'},
								        {value:'16',text:'16层'},
								        {value:'17',text:'17层'},
								        {value:'18',text:'18层'},
								        {value:'19',text:'19层'},
								        {value:'20',text:'20层'} ]"/>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>							        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:cjxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cjxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cjxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:cjxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:cjxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:cjxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/cjxx/exinjump','${ctx}/bis/cjxx/exin','${ctx}/static/templates/车间信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_cjxx_dg"></table> 

</body>
</html>