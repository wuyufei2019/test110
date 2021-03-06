<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车辆行驶证管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/beian/drivinglicence/index2.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
			<input name="beian_dl_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入证号'" />
		    <input name="beian_dl_m2" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入姓名'" />	
         	<input name="beian_dl_m8" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入准驾车型'" />
         	<input name="beian_dl_m9" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '请选择有效期'" />
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> 
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="beian:drivinglicence:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:drivinglicence:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:drivinglicence:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:drivinglicence:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               
               <shiro:hasPermission name="beian:drivinglicence:exin">
				   <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/beian/drivinglicence/exinjump','${ctx}/beian/drivinglicence/exin','${ctx}/static/templates/驾驶证审核管理信息导入模板.xls')" title="导入">
				   <i class="fa fa-folder-open-o"></i> 导入</button>
			   </shiro:hasPermission>

	           <shiro:hasPermission name="beian:drivinglicence:export">
	        	   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link">
	        	   		</i> 导出
	        	   </button> 
	           </shiro:hasPermission>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>
      
   </div>

   <table id="table_dg"></table> 

<script type="text/javascript">
var usertype = '${usertype}';
var userid = '${userid}';
var userRoleCode = '${userRoleCode}';
</script>
</body>
</html>