<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>事件信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="${ctx}/static/model/js/workflow/process-list.js?v=1.1"></script>
</head>
<body>
<!-- 工具栏 -->
<div id="dg_tb" style="padding:5px;height:auto">
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <button class="btn btn-white btn-sm" data-toggle="tooltip"
                        data-placement="left" onclick="exportXml()" title="添加">
                    <i class="fa fa-plus"></i> 导入
                </button>

            </div>
        </div>
    </div>

</div>


<table id="dg"></table>

<div id="deployFieldset" style="display: none;width: 350px;height: 150px;padding: 10px">
    <div><b>支持文件格式：</b>zip、bar、bpmn、bpmn20.xml</div>
    <form action="${ctx }/workflow/deploy" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="Submit"/>
    </form>
</div>

</body>
</html>