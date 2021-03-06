<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>危险废物</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/hjbh/wxfw/index.js?v=1.0"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="hjbh_wxfw_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
            <input type="text" name="view_year" id="year" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '年度',panelHeight:'100',valueField: 'year',textField: 'year'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="hjbh:wxfw:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:wxfw:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:wxfw:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="hjbh:wxfw:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
             <%--   <shiro:hasPermission name="hjbh:wxfw:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button> 
        		</shiro:hasPermission>   --%>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>

   </div>

   <table id="hjbh_wxfw_dg"></table>
   <div id="select_detail" style="display:none;height:100%">
      <shiro:hasPermission name="hjbh:wxfw:add">
         <button style="margin:5px" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="adddetail()" title="添加">
            <i class="fa fa-plus"></i> 添加
         </button>
      </shiro:hasPermission>
      <table id="detail"></table>
   </div>
   <script type="text/javascript">
 //年度下拉框数据
	var yeardata = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear()+1;
	for(var i=0;i<5;i++){
		thisYear=startYear-i;
		yeardata.push({"year":thisYear});
	}
	$(function(){
      	$("#year").combobox("loadData", yeardata);
	});
	var RecodId;//全局变量，选中记录的id
	var type = '${type}';
	</script>
</body>
</html>