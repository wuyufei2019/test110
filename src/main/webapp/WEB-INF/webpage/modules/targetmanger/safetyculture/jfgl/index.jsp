<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>积分管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="${ctx}/static/model/js/targetmanger/safetyculture/jfgl/qy_index.js?v=1.3"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="target_jfgl_tb" style="padding:5px;height:auto">
    <form id="searchFrom" style="margin-bottom: 8px;" class="form-inline pull-left">
        <div class="form-group">
            <c:if test="${ usertype eq 'isbloc' }">
                <input type="text" name="target_jfgl_qyname" style="height:30px" class="easyui-textbox"
                       data-options="width:200,prompt: '企业名称'"/>
            </c:if>
            <input type="text" name="page_year" style="height:30px" class="easyui-textbox"
                   data-options="width:100,prompt: '年份'"/>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i
                    class="fa fa-search"></i> 查询</span>
            <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i
                    class="fa fa-refresh"></i> 全部</span>
        </div>
    </form>
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <%--  <shiro:hasPermission name="target:jfgl:view">
                   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看">
                      <i class="fa fa-search-plus"></i> 查看
                   </button>
                </shiro:hasPermission> --%>
                <%--   <shiro:hasPermission name="target:jfgl:export">
                   <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出">
                      <i class="fa fa-external-link"></i> 导出
                   </button>
                </shiro:hasPermission> --%>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()"
                        title="刷新">
                    <i class="glyphicon glyphicon-repeat"></i> 刷新
                </button>

            </div>

        </div>
    </div>

</div>


<table id="target_jfgl_dg"></table>
<div id="select_dg" style="display:none;height:100%">
    <table id="target_dg"></table>
</div>
<script type="text/javascript">
    var qyid = '${qyid}';
    var usertype = '${usertype}';
</script>
</body>
</html>