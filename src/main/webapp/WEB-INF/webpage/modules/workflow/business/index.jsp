<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>业务信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="${ctx}/static/model/js/workflow/business/index.js?v=1.0"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="dg_tb" style="padding:5px;height:auto">
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <button class="btn btn-white btn-sm" data-toggle="tooltip"
                        data-placement="left" onclick="upd()" title="分配">
                    <i class="fa fa-plus"></i> 分配
                </button>
                <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>


            </div>
        </div>
    </div>

</div>


<table id="dg"></table>

</body>
</html>