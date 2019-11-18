<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检测报告管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/occupharmreportreport/index.js?v=1.5"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_occupharmreport_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="bis_zybjcbg_cx_m10" name="bis_zybjcbg_cx_m10" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '职业病危害因素分类',editable:true,data: [
								        {value:'粉尘',text:'粉尘'},
								        {value:'化学因素',text:'化学因素'},
								        {value:'物理因素',text:'物理因素'},
								        {value:'放射性因素',text:'放射性因素'},
								        {value:'生物性因素',text:'生物性因素'},
								        {value:'其他因素',text:'其他因素'} ]"/>
    	<input type="text" id="bis_zybjcbg_cx_m11" name="bis_zybjcbg_cx_m11" class="easyui-combobox" style="height: 30px;" data-options="prompt: '职业病危害因素名称',
		         valueField: 'id',textField: 'text',url:'${ctx}/bis/occupharm/izy'"/>
		<input type="text" id="bis_zybjcbg_cx_m1" name="bis_zybjcbg_cx_m1" style="height: 30px;" class="easyui-textbox" data-options="prompt: '检测机构'"/>
    	<input type="text" id="bis_zybjcbg_cx_m2" name="bis_zybjcbg_cx_m2" style="height: 30px;" class="easyui-datebox" data-options="prompt: '检测日期'"/>
    	<input type="text" id="bis_zybjcbg_cx_time" name="bis_zybjcbg_cx_time" style="height: 30px;" class="easyui-combobox" data-options="prompt: '到期时间查询',editable:false,data: [
								       {value:'7',text:'一个星期'},
								       {value:'14',text:'两个星期'},
								       {value:'21',text:'三个星期'},
							           {value:'30',text:'一个月'},
							           {value:'60',text:'两个月'},
							            {value:'0',text:'已过期'}] ,
							           onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#bis_zybjcbg_cx_time1').combobox('clear');
								 		}
        							} "/>
        <input type="text" class="easyui-datebox" id="bis_zybjcbg_cx_time1" name="bis_zybjcbg_cx_time1"  style="height: 30px;"
		          data-options="prompt: '具体到期时间查询' ,
		          onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#bis_zybjcbg_cx_time').combobox('clear');
								 		}
        							} " />   
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>    
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:occupharmreport:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharmreport:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharmreport:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:occupharmreport:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:occupharmreport:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	 <shiro:hasPermission name="bis:occupharmreport:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/occupharmreport/exinjump','${ctx}/bis/occupharmreport/exin','${ctx}/static/templates/检测报告导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</div>
	</div>
	</div> 
	   
</div>


<table id="bis_occupharmreport_dg"></table> 
<script type="text/javascript">
   var f='${sys}';
</script> 
</body>
</html>