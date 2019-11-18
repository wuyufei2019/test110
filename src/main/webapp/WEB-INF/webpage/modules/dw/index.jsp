<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>特殊作业审批信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/dw/index.js?v=1.3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="dw_zysp_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${usertype eq '0' }">
		<input type="text" id="dw_zysp_cx_qyname" name="dw_zysp_cx_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	</c:if>
		<input id="dw_zysp_cx_m1" name="dw_zysp_cx_m1" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '特殊作业类型',editable:false,data: [
								        {value:'1',text:'动火作业'},
								        {value:'2',text:'受限空间作业'},
								        {value:'3',text:'管道拆卸作业'},
								        {value:'4',text:'盲板抽堵作业'},
								        {value:'5',text:'高处安全作业'},
								        {value:'6',text:'吊装安全作业'},
								        {value:'7',text:'临时用电安全作业'},
								        {value:'8',text:'动土安全作业'},
								        {value:'9',text:'断路安全作业'},
							        	]  "/>
		<input id="dw_zysp_cx_m2" name="dw_zysp_cx_m2" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '特殊作业级别',editable:false,data: [
								        {value:'1',text:'特级'},
								        {value:'2',text:'一级'},
								        {value:'3',text:'二级'},
								        {value:'4',text:'其他'},
							        	]  "/>
		<input id="dw_zysp_cx_m17" name="dw_zysp_cx_m17" class="easyui-combobox"  style="height: 30px;" value="" data-options="panelHeight:'auto' ,prompt: '作业队伍',editable:false,data: [
								        {value:'1',text:'外包施工队'},
								        {value:'2',text:'本厂人员'},
							        	]  "/> 	
		<input type="text" name="starttime" class="easyui-datetimebox" style="height: 30px;" data-options="prompt: '开始时间'"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>	      	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="dw:zysp:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="dw:zysp:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dw:zysp:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="dw:zysp:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="dw:zysp:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>

	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="dw_zysp_dg"></table> 
<script type="text/javascript">
   var qyid= '${qyid}';
   var usertype = '${usertype}';
   var type = '${type}';
   var f='${sys}';
   var zydw='${zydw}';
</script>
</body>
</html>