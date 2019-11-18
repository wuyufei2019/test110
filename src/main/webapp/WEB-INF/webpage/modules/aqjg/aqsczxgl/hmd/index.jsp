<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>黑名单信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqsczxgl/hmd/index.js?v=3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqjg_hmd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="aqjg_hmd_cx_m1" name="hmd_M1" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		<input id="aqjg_hmd_cx_m2" class="easyui-combobox" name="hmd_M2" type="text"  style="height: 30px;"
					               data-options="data: [
									{value:'0',text:'一年内发生生产安全重大责任事故，或累计发生责任事故死亡10人（含）以上'},
									{value:'1',text:'重大安全生产隐患不及时整改或整改不到位'},
									{value:'2',text:'发生暴力抗法的行为，或未按时完成行政执法指令'},
									{value:'3',text:'发生事故隐瞒不报、谎报或迟报，故意破坏事故现场、毁灭有关证据'},
									{value:'4',text:'无证、证照不全、超层越界开采、超载超限超时运输等非法违法行为'},
									{value:'5',text:'经监管执法部门认定严重威胁安全生产的其他行为'},
									{value:'6',text:'一年内发生较大生产安全责任事故，或累计发生责任事故死亡超过3人（含）以上'},
									{value:'7',text:'一年内发生死亡2人（含）以上的生产安全责任事故，或累计发生责任事故死亡超过2人（含）以上'},
									{value:'8',text:'一年内发生死亡责任事故'}],prompt: '黑名单行为'"  />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>    	    	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="zxgl:hmd:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:hmd:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:hmd:check">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 审核</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="zxgl:hmd:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			</span>
			<shiro:hasPermission name="zxgl:hmd:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="zxgl:hmd:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<span id="divper2">
			</span>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="aqjg_hmd_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>