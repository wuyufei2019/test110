<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>立案审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/lasp/index.js?v=1.7"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="ybcf_lasp_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="ybcf_lasp_number" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号'" />
		<input type="text" name="ybcf_lasp_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '案由'" />
		立案时间：
		<input type="text" class="easyui-datebox" name="ybcf_lasp_startdate"  style="height: 30px;" data-options="prompt: '开始时间'"  >
		<input type="text" class="easyui-datebox" name="ybcf_lasp_enddate"  style="height: 30px;" data-options="prompt: '结束时间'"  >
		<%-- <input  name="aqjg_jcjl_year" style="height: 30px;"class="easyui-combobox"data-options="prompt:'年份',valueField: 'id',textField: 'text',url:'${ctx}/aqjd/jcjh/year'" /> --%>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="ybcf:lasp:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:lasp:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addtemp(${count})" title="添加"><i class="fa fa-plus"></i> 待补全<c:if test="${count ne 0}"><span style="color:red">(${count})</span></c:if></button>
			</shiro:hasPermission> 
			<shiro:hasPermission name="ybcf:lasp:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i>修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:lasp:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="ybcf:lasp:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	  <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
        <br>
        	<shiro:hasPermission name="ybcf:lasp:export">
        		<button class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportlasp()" title=""><i class="fa fa-file-word-o"></i> 立案审批表</button> 
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportxw()" title=""><i class="fa fa-file-word-o"></i> 询问通知书</button> 
        		<button class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportdc()" title=""><i class="fa fa-file-word-o"></i> 调查报告</button> 
        		<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporttl()" title=""><i class="fa fa-file-word-o"></i> 集体讨论</button> 
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportsb()" title=""><i class="fa fa-file-word-o"></i> 陈述申辩</button>
        		<button class="btn btn-warning btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportgzs()" title=""><i class="fa fa-file-word-o"></i> 处罚告知书</button> 
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporttz()" title=""><i class="fa fa-file-word-o"></i> 听证告知书</button> 
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportajcp()" title=""><i class="fa fa-file-word-o"></i> 案件呈批表</button> 
        		<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportcfjd()" title=""><i class="fa fa-file-word-o"></i> 处罚决定书</button> 
    <%--     	<shiro:hasPermission name="ybcf:lasp:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" style="background-color: #e84e40"data-placement="left" onclick="fileexportsxcg()" title=""><i class="fa fa-file-word-o"></i> 事先催告书</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="ybcf:lasp:export">
        		<button class="btn btn-default btn-sm" data-toggle="tooltip"style="background-color:#e51c23" data-placement="left" onclick="fileexportqzzx()" title=""><i class="fa fa-file-word-o"></i> 强制执行书</button> 
        	</shiro:hasPermission> --%>
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportjasp()" title=""><i class="fa fa-file-word-o"></i> 结案审批表</button> 
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexporsdhz()" title=""><i class="fa fa-file-word-o"></i> 送达回执书</button> 
        	</shiro:hasPermission>
		
			</div>
	</div>
	</div> 
</div>
<table id="ybcf_lasp_dg"></table> 
<script>
		
</script>
</body>
</html>