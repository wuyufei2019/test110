<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>燃气信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/ballblast/index.js?v=1.5"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_ballblast_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
    	<input  id="m1" name="M1" type="text" class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto', prompt: '设备型号',data: [
							            {value:'1',text:'立式'},
								        {value:'2',text:'卧式'},
								        {value:'3',text:'手动'} ] " />
    	<input  id="m5" name="M5" type="text"  class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'120px' ,prompt: '产品材质',data: [
								        {value:'不锈钢',text:'不锈钢'},
								        {value:'黄铜',text:'黄铜'},
								        {value:'铸铁',text:'铸铁'},
								        {value:'铁',text:'铁'},
								        {value:'铸钢',text:'铸钢'},
								        {value:'碳纤维',text:'碳纤维'},
								        {value:'轴承钢',text:'轴承钢'},
								        {value:'钢架构',text:'钢架构'},
								        {value:'铝合金',text:'铝合金'},
								        {value:'钢',text:'钢'},
								        {value:'模具钢',text:'模具钢'},
								        {value:'钢材',text:'钢材'},
								        {value:'钢铁',text:'钢铁'},
								        {value:'不锈钢等硬质合金',text:'不锈钢等硬质合金'},
								        {value:'铝',text:'铝'} ] " />
    	<input  id="m6" name="M6" type="text"  class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'120px' ,prompt: '砂丸材质',data: [
								        {value:'钢丸',text:'钢丸'},
								        {value:'玻璃',text:'玻璃'},
								        {value:'钢',text:'钢'},
								        {value:'钢珠',text:'钢珠'},
								        {value:'棕刚玉',text:'棕刚玉'},
								        {value:'不锈钢',text:'不锈钢'},
								        {value:'棕钢砂',text:'棕钢砂'},
								        {value:'氧化锆',text:'氧化锆'},
								        {value:'金钢玉',text:'金钢玉'},
								        {value:'铁',text:'铁'} ] " />
    	<input  id="m7" name="M7" type="text"  class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto' ,prompt: '清理制度',data: [
								        {value:'0',text:'有'},
								        {value:'1',text:'无'} ] " />
    	<input  id="m8" name="M8" type="text"  class="easyui-combobox" style="height: 30px;"
								data-options="editable:false ,panelHeight:'auto' ,prompt: '清理记录',data: [
								        {value:'0',text:'有'},
								        {value:'1',text:'无'} ] " />
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	    <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:ballblast:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ballblast:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ballblast:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:ballblast:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:ballblast:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:ballblast:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/ballblast/exinjump','${ctx}/bis/ballblast/exin','${ctx}/static/templates/抛丸信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_ballblast_dg"></table>

<script type="text/javascript">

</script>
</body>
</html>