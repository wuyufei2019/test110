<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${ctx}/static/model/js/basic/car/index2.js?v=1.3"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
			<input name="basic_car_m1" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车型'" />
		    <input name="basic_car_m3" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车牌号'" />	
         	<input name="basic_car_m9" class="easyui-textbox"  style="height: 30px;" data-options="prompt: '请输入车辆负责人姓名'"/>	
         	<input name="type" type="hidden" value="${type}"/>	        
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> 
			<span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="basic:car:add">
                  <button id="btn1" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="basic:car:update">
                  <button id="btn2" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="basic:car:delete">
                  <button id="btn3" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="basic:car:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
	           <shiro:hasPermission name="basic:car:export">
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
var usertype ='${usertype}';
var type = '${type}';//类型是否为黑名单
if(type == 'hmd'){
	$("#btn1").hide();
	$("#btn2").hide();
	$("#btn3").hide();
}
</script>
</body>
</html>