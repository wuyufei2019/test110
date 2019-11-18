<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>重大风险分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/zdfx/index.js?v=1.1"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="fxpg_zdfx_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         	<c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if>    
            <input type="text" name="view_name" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '名称'" />
            <input type="text" name="view_deptname" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '部门'" />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
                <!--   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button> -->
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="excelexport()" title="导出">
                  <i class="fa fa-file-excel-o"></i> 导出
               </button>
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>

   </div>

   <table id="fxpg_zdfx_dg"></table>
<script type="text/javascript">
	var type = '${type}';
</script>
</body>
</html>