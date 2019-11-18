<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sggl/sgxx/index.js?v=1.5"></script>
	<script type="text/javascript">
		var type='${type}';
		var editIndex = undefined;
		var usertype = '${usertype}';
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="sggl_sgxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
	    <c:if test="${ type eq '2' || usertype != '1'}">
	        <input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
	    </c:if>
		<input type="text" id="sggl_sgxx_sgbh" name="sggl_sgxx_sgbh" class="easyui-textbox" style="height: 30px;" data-options="prompt: '事故编号 '"/>
		<input type="text" id="sggl_sgxx_cs_name" name="sggl_sgxx_cs_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '事故名称 '"/>
		<input type="text" name="sggl_sgxx_sglx" class="easyui-combobox" style="height: 30px;" 
							data-options="prompt: '事故类型',panelHeight:'auto',editable:false,data: [
									{value:'死亡Fatal',text:'死亡Fatal'},
									{value:'损工事故',text:'损工事故'},
									{value:'医疗处理事故',text:'医疗处理事故'},
									{value:'急救事故',text:'急救事故'},
									{value:'幸免事故',text:'幸免事故'}]" />   	        
		<input type="text" id="sggl_sgxx_sglevel" name="sggl_sgxx_sglevel" class="easyui-combobox" style="height: 30px;"
							data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故等级',data: [
							        {value:'一般',text:'一般'},
							        {value:'较大',text:'较大'},
							        {value:'重大',text:'重大'},
							        {value:'特大',text:'特大'} ]" />
		<input name="sggl_sgxx_m5" class="easyui-datebox"  style="height: 30px;" data-options="editable:false ,prompt: '发生时间'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="sggl:sgxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sggl:sgxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sggl:sgxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sggl:sgxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	
        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		</span>
		</div>
	</div>
	</div> 
	   
</div>
<table id="sggl_sgxx_dg"></table> 
</body>
</html>