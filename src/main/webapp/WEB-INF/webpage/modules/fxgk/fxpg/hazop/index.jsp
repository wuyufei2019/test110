<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>hazop风险分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxpg/hazop/index.js?v=1.2"></script>
</head>
<body>
   <!-- 工具栏 -->
   <div id="fxpg_hazop_tb" style="padding:5px;height:auto">
      <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         	<c:if test="${type != '1' }">
				<input type="text" id="view_qyname" name="view_qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>	  	 
			</c:if>
            <input type="text" name="view_wtname" class="easyui-textbox" style="height: 30px;"
               data-options="prompt: '题目名称',panelHeight:'100' " />
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
               <shiro:hasPermission name="fxpg:hazop:add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="fxpg:hazop:update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="fxpg:hazop:delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="fxpg:hazop:view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="fxpg:hazop:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexportword()" title="导出Word"><i class="fa fa-file-word-o"></i> 导出Word</button> 
        		</shiro:hasPermission> 
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>

   </div>

   <table id="fxpg_hazop_dg"></table>
   <div id="select_action" style="display:none;height:100%">
      <shiro:hasPermission name="fxpg:hazop:add">
         <button style="margin:5px" class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addAction()" title="添加">
            <i class="fa fa-plus"></i> 添加
         </button>
      </shiro:hasPermission>
      <table id="action"></table>
   </div>
   <script type="text/javascript">
   		var type = '${type}';
		var riskid;//全局变量，选中记录的id
   </script>
</body>
</html>