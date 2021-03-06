<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>道路运输证管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/beian/roadtransport/index2.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
		    <input name="beian_rt_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入业户名称'" />	
		    <input name="beian_rt_m3" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车牌名称'"
            />
		    <input name="beian_rt_m5" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车辆类型'" />
		    <input name="beian_rt_m8" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入经营范围'" />
         	<input name="beian_rt_m10" class="easyui-datebox"  style="height: 30px;" data-options="prompt: '请选择有效期'" />
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> 
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="beian:roadtransport:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:roadtransport:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:roadtransport:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="beian:roadtransport:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>

                <shiro:hasPermission name="beian:roadtransport:exin">
                    <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/beian/roadtransport/exinjump','${ctx}/beian/roadtransport/exin','${ctx}/static/templates/道路运输证信息.xls')" title="导入">
                        <i class="fa fa-folder-open-o"></i> 导入</button>
                </shiro:hasPermission>

                <shiro:hasPermission name="beian:roadtransport:export">
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