<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>失信记录信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqsczxgl/sxjl/index.js?v=1.2"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqjg_sxjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="aqjg_sxjl_cx_m1" name="sxjl_M1" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input id="aqjg_sxjl_cx_m2" class="easyui-combobox" name="sxjl_M2" type="text"
					style="height: 30px;" data-options="data: [
									{value:'生产经营单位一年内发生生产安全死亡责任事故',text:'生产经营单位一年内发生生产安全死亡责任事故'},
									{value:'非法违法组织生产经营建设',text:'非法违法组织生产经营建设'},
									{value:'执法检查发现存在重大安全生产隐患、重大职业病危害隐患',text:'执法检查发现存在重大安全生产隐患、重大职业病危害隐患'},
									{value:'未按规定开展企业安全生产标准化建设的或在规定期限内未达到安全生产标准化要求',text:'未按规定开展企业安全生产标准化建设的或在规定期限内未达到安全生产标准化要求'},
									{value:'未建立隐患排查治理制度，不如实记录和上报隐患排查治理情况，期限内未完成治理整改',text:'未建立隐患排查治理制度，不如实记录和上报隐患排查治理情况，期限内未完成治理整改'},
									{value:'拒不执行安全监管监察指令的，以及逾期不履行停产停业、停止使用、停止施工和罚款等处罚',text:'拒不执行安全监管监察指令的，以及逾期不履行停产停业、停止使用、停止施工和罚款等处罚'},
									{value:'未依法依规报告事故、组织开展抢险救援',text:'未依法依规报告事故、组织开展抢险救援'},
									{value:'其他安全生产非法违法或造成恶劣社会影响的行为',text:'其他安全生产非法违法或造成恶劣社会影响的行为'}],prompt: '失信行为'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zxgl:sxjl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:sxjl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:sxjl:check">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 审核</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:sxjl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			</span>
			<shiro:hasPermission name="zxgl:sxjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zxgl:sxjl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqjg_sxjl_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>