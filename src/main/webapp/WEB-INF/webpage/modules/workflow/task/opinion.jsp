<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>历史意见</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<div class="easyui-accordion" style="width:100%;">
    <div class="easyui-accordion" style="width:100%;height:100%">
        <iframe frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no
                src="${ctx}${url}"></iframe>
    </div>

</div>
<div class="easyui-accordion" style="width:100%;">
    <form id="inputForm" action="${ctx}/suggestion/${action}" method="post" class="form-horizontal">
        <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
            <tbody>
            <c:if test="${type ne 'view'}">
                <tr>
                    <td class="width-15 active"><label class="pull-right">意见填写：</label></td>
                    <td class="width-85"><input name="workflow_opinion" id="opinion" type="text"
                                                class="easyui-textbox" value="同意"
                                                style="width: 100%;height: 30px;"
                                                data-options="multiline:true,validType:'length[0,500]' ">
                    </td>
                    </td>
                </tr>
                <%--<c:if test="${handler ne '0'}">
                    <tr>
                        <td class="width-15 active"><label class="pull-right">选择处理人：</label></td>
                        <td class="width-85"><input name="workflow_assigner" id="handler" type="text"
                                                    class="easyui-combobox"
                                                    style="width: 100%;height: 30px;"
                                                    data-options="
                                                    required:true,valueField:'text',textField:'text',url:'${ctx}/system/user/userjson' ,method:'get'">
                        </td>
                    </tr>
                </c:if>--%>
            </c:if>
            <c:if test="${fn:length(commentsList)!=0}">
                <tr>
                    <td class="width-35" colspan="3">历史意见
                </tr>
            </c:if>
            <c:forEach var="item" items="${commentsList}" varStatus="status">
                <tr>
                    <td class="width-15 active"><label class="pull-right">姓名：</label></td>
                    <td class="width-85">${item.userId}</td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">处理时间：</label></td>
                    <td
                            class="width-85"><fmt:formatDate value="${item.time}"
                                                             pattern="yyyy-MM-dd hh:mm:ss"/></td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">意见：</label></td>
                    <td class="width-85">${item.message}</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </form>
    <div id="decide" style="float: right;text-align: center"></div>
</div>
<script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var btns = eval('${btns}');
    var taskId = '${taskId}';
    var processInstanceId = '${processInstanceId}';

    function doSubmit() {
        complete();
    }
    function doPass() {
        complete({workflow_pass: true});
    }
    function doUnPass() {
        complete({workflow_pass: false});
    }

    if ('${type}' != 'view') {
        var html = "";
        $.each(btns, function (index, value) {
            html
                += '<a class="btn btn-info btn-sm padding-12 action" data-toggle="tooltip" data-placement="left">' + value + '</a>';
        });
        $("#decide").html(html);
    }

    function complete(map) {
        layer.load();
        $.post(ctx + '/task/complete/' + taskId + "?processInstanceId=" + processInstanceId,
            $.extend($("#inputForm").serializeObject(), map), function (resp) {
                layer.closeAll();
                if (resp == 'success') {
                    layer.msg('任务完成');
                    layer.closeAll();
                    parent.layer.close(index);//关闭对话框。
                } else {
                    layer.msg('操作失败!');
                }
                parent.dg.datagrid("reload");

            });
    }
</script>
</body>
</html>