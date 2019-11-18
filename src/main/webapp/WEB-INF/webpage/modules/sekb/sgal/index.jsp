<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故案例信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sekb/sgal/index.js?v=3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="sekb_sgal_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
				
	<div class="form-group">
		<input type="text" id="sekb_sgal_bt_name" name="sekb_sgal_bt_name" style="height: 30px;" class="easyui-textbox" data-options="prompt: '标题'"/> 
		<input style="height: 30px;" id="sglb" name="sglb"  class="easyui-combobox" data-options="editable:false ,
									valueField: 'text',textField: 'text',prompt:'类别',
									data: [
								        {value:'火灾',text:'火灾'},
								        {value:'容器爆炸',text:'容器爆炸'},
								        {value:'锅炉爆炸',text:'锅炉爆炸'},
								        {value:'其他爆炸',text:'其他爆炸'},
								        {value:'中毒和窒息',text:'中毒和窒息'},
								        {value:'灼烫',text:'灼烫'},
								        {value:'触电',text:'触电'},
								        {value:'物体打击',text:'物体打击'},
								        {value:'车辆伤害',text:'车辆伤害'},
								        {value:'机械伤害',text:'机械伤害'},
								        {value:'起重伤害',text:'起重伤害'}, 
								        {value:'淹溺',text:'淹溺'},
								        {value:'高处坠落',text:'高处坠落'},
								        {value:'坍塌',text:'坍塌'},
								        {value:'其他伤害',text:'其他伤害'},]"/>   
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sekb:sgal:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:sgal:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:sgal:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sekb:sgal:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="sekb:sgal:excel">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="sekb_sgal_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>