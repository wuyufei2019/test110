<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品发放记录信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/lbyp/ffjl/index.js?v=1.0"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="lbyp_ffjl_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

         <div class="form-group">
            <input id="view_year" name="view_year" class="easyui-combobox" style="height: 30px;"
               data-options="valueField: 'year',textField: 'year',prompt: '年份',panelHeight:'auto'" />
            <input type="text" name="ename" class="easyui-textbox" style="height: 30px;" data-options="prompt: '员工名称'" />
            <input type="text" name="job_name" class="easyui-combobox" style="height: 30px;"
               data-options="prompt: '岗位（工种）名称',panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

   </div>


   <table id="lbyp_ffjl_dg"></table>
   <div id="select_dg" style="display:none;height:100%">
      <div style="margin-bottom: 5px"></div>
      <table id="dgdata"></table>
   </div>
   <script type="text/javascript">
   var qyid = '${qyid}';
				$(function() {
					var data = [];
					var thisYear;
					var startYear = new Date().getUTCFullYear() + 1;

					for (var i = 0; i < 5; i++) {
						thisYear = startYear - i;
						data.push({
							"year" : thisYear
						});
					}
					$("#view_year").combobox("loadData", data);//下拉框加载数据

				});
			</script>
</body>
</html>