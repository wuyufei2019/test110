<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>${code_description }信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src=" ${r'${ctx}'}/static/model/js/${code_viewpath!'' }/process-list.js?v=1.0"></script>
</head>
<body >
<!-- 工具栏 -->
   <div id="dg_tb" style="padding:5px;height:auto">
      <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline">
         <div class="form-group">
         <#list columns as map>
         <#if map.col_serach=='true'>
            <input type="text" name="view_${map.col_ename!'' }" class="easyui-${map.col_showtype!'textbox' }" style="height: 30px;" data-options="<#if map.col_showtype??&&map.col_showtype=='combobox'>valueField: 'value',textField: 'text',panelHeight:'auto',data:[{text:'',value:''}],</#if>prompt: '${map.col_cname!'' }'" />
         </#if>
         </#list>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
               class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
         </div>
      </form>

      <div class="row">
         <div class="col-sm-12">
            <div class="pull-left">
            <#if code_permisson=="true">  
               <shiro:hasPermission name="${permisson.permCode }add">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                     <i class="fa fa-plus"></i> 添加
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="${permisson.permCode }update">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                     <i class="fa fa-file-text-o"></i> 修改
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="${permisson.permCode }delete">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                     <i class="fa fa-trash-o"></i> 删除
                  </button>
               </shiro:hasPermission>
               <shiro:hasPermission name="${permisson.permCode }view">
                  <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                     <i class="fa fa-search-plus"></i> 查看
                  </button>
               </shiro:hasPermission>
               <#else>
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加">
                  <i class="fa fa-plus"></i> 添加
               </button>
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改">
                  <i class="fa fa-file-text-o"></i> 修改
               </button>
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除">
                  <i class="fa fa-trash-o"></i> 删除
               </button>
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                  <i class="fa fa-search-plus"></i> 查看
               </button>
               </#if>  
               <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新">
                  <i class="glyphicon glyphicon-repeat"></i> 刷新
               </button>

            </div>
         </div>
      </div>
      
   </div>

   <table id="table_dg"></table> 

<script type="text/javascript">

</script>
</body>
</html>