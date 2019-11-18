<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测点维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/bis/cgjcdwhsj/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">传感器编号：</label></td>
            <td class="width-80"><input name="number" class="easyui-textbox" value="${cglist.number }" style="width: 100%;height: 30px;"
                                        data-options="required:true,validType:'length[0,50]'"/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">从站id：</label></td>
            <td class="width-80"><input class="easyui-numberbox" id="salveId" name="salveId" value="${cglist.salveId }" style="width: 100%;height: 30px;"
                                        data-options=""/></td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">偏移量：</label></td>
            <td class="width-80"><input name="offset" class="easyui-numberbox" value="${cglist.offset }" style="width: 100%;height: 30px;"
                                        data-options=""/></td>
        </tr>

        <c:if test="${not empty cglist.ID}">
            <input type="hidden" name="ID" value="${cglist.ID}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;

    $(function () {
        if ('${action}' == 'create') {
            $('#salveId').textbox('setValue', 1);
        }
    })

    function doSubmit() {
        $("#inputForm").submit();
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {opacity: 0});
                    return true;
                }
                return false;	// 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                else
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });

</script>
</body>
</html>