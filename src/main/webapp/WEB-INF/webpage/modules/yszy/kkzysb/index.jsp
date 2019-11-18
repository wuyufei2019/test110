<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>卡口作业申报信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/yszy/kkzysb/index2.js?v=1.4"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         	<input name="entrust_company" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入托运方单位'" />
		    <input name="consignee_company" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入收货方单位'" />
		    <input name="accept_company" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入承运方单位'" />	
		    <input name="plate_num" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车牌号码'" />
		    <input name="driver_name" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入驾驶员姓名'" />	
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> 
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row" id="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="yszy:kkzysb:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="yszy:kkzysb:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd2()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="yszy:kkzysb:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="yszy:kkzysb:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
	       <%--    <shiro:hasPermission name="yszy:kkzysb:export">
	        	   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link">
	        	   		</i> 导出
	        	   </button> 
	           </shiro:hasPermission>--%>
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
var cphm = '${cphm}';
var carview = '${carview}';
if(carview =='carview'){
	$("#searchFrom").hide();
	$("#row").hide();
}
var userRoleCode = '${userRoleCode}';
</script>
</body>
</html>