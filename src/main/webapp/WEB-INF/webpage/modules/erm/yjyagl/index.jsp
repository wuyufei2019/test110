<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急预案信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/erm/yjyagl/index.js?v=1.7"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="erm_yjyagl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
        <c:if test="${usertype ne '1' }">
          <input id="cx_type_con" name="cx_type_con" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '类型 ',editable:false ,data: [{value:'1',text:'安监'},
																																		        {value:'2',text:'企业'}],
																																		        onLoadSuccess: function () { 
																																                 	$('#cx_type_con').combobox('setValue', '1');
																																				},
																																				onSelect:function(rec){
																																					if(rec.value=='1'){
																																						$('#divper').show();
																																						$('#divper2').show();
																																						search();
																																					}
																																					if(rec.value=='2'){
																																						$('#divper').hide();
																																						$('#divper2').hide();
																																						search();
																																					}
																																					
																																				}"/>
          </c:if>
		<input type="text" id="number" name="number" class="easyui-textbox" style="height: 30px;" data-options="prompt: '编号 '"/>  
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span> 	        
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		<span id="divper">
			<shiro:hasPermission name="erm:yjyagl:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="erm:yjyagl:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="erm:yjyagl:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			</span>
			<shiro:hasPermission name="erm:yjyagl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="erm:yjyagl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>


<table id="erm_yjyagl_dg"></table> 
<script type="text/javascript">
	var usertype = '${usertype}';
</script>
</body>
</html>