<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备保养计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbytask/index.js?v=1.1"></script>
</head>
<body >
<!------------------------ 设备保养计划 ----------------------->
<div>
	<div id="sbssgl_sbbytask_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
		<div class="form-group">
			<c:if test="${type eq '2'}">
				<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
			</c:if>
			<input name="m1" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '计划标题'" />
			<input name="m2" class="easyui-numberbox"  style="height: 30px;" data-options="min:0,editable:true,prompt: '年份'" />
			<input name="m3" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '计划类型',panelHeight:'auto',data: [
											{value:'1',text:'月度'},
											{value:'2',text:'季度'},
											{value:'3',text:'半年度'},
											{value:'4',text:'年度'}]" />
			<input name="zt" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '是否完成',panelHeight:'auto',data: [
											{value:'0',text:'未完成'},
											{value:'1',text:'已完成'}]" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
	    </div>
		</form>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<shiro:hasPermission name="sbssgl:sbbytask:add">
				       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="sbssgl:sbbytask:update">
					    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
					</shiro:hasPermission>
					<shiro:hasPermission name="sbssgl:sbbytask:delete">
						<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
					</shiro:hasPermission>
					<shiro:hasPermission name="sbssgl:sbbytask:view">
		        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
		        	</shiro:hasPermission>
			        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
				
					</div>
				<div class="pull-right">
				</div>
			</div>
		</div> 	   
	</div>
	<table id="sbssgl_sbbytask_dg"></table> 
</div>

<!------------------------ 设备保养记录 ----------------------->
<div>
	<center style="font-size: 18px;color: #1e90ff;margin-bottom: -3px;margin-top: 2px;"><strong>保养记录</strong></center>
	<div id="sbssgl_sbby_tb" style="padding:5px;height:auto;"></div>
	<table id="sbssgl_sbby_dg"></table> 
</div>

<script>
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
	$(function(){
		var winHeight = document.documentElement.clientHeight;
		var taskHeight = winHeight * 0.28+"px";//设备保养计划的高度
		var recordHeight;//设备保养记录的高度
		if (winHeight > 680) {
			recordHeight = winHeight * 0.48+"px";
		} else {
			recordHeight = winHeight * 0.42+"px";
		}
		//设置设备保养计划的页面显示高度
		$("#sbssgl_sbbytask_tb").parent().css("height", "50%");
		$("#sbssgl_sbbytask_tb").next().css("height", taskHeight);
		$("#sbssgl_sbbytask_tb").next().next(".datagrid-pager").css("border-width", "1px 0 1px 0");
		
		//设置设备保养记录的页面显示高度
		$("#sbssgl_sbby_tb").parent().css("height", "50%");
		$("#sbssgl_sbby_tb").next().css("height", recordHeight);
	})
</script>
</body>
</html>