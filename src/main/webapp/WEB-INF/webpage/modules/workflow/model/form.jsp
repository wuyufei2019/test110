<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>模型</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引


        function doSubmit() {
            $("#inputForm").serializeObject();
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
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                }
            });

        });

    </script>

</head>
<body>

<form id="inputForm" action="${ctx}/workflow/model/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">

        <tr>
            <td class="width-15 active"><label class="pull-right">名称：</label></td>
            <td class="width-35"><input name="name" type="text" value="${entity.name }"
                                        class="easyui-textbox"
                                        style="width: 100%;height: 30px;"
                                        data-options="multiline:true,validType:'length[0,500]' "></td>
            <td class="width-15 active"><label class="pull-right">KEY：</label></td>
            <td class="width-35"><input name="key" type="text" value="${entity.key }"
                                        class="easyui-textbox"
                                        style="width: 100%;height: 30px;"
                                        data-options="multiline:true,validType:'length[0,500]' "></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">描述：</label></td>
            <td class="width-35" colspan="3"><input name="description" type="text" value="${entity.description }"
                                        class="easyui-textbox"
                                        style="width: 100%;height: 30px;"
                                        data-options="multiline:true,validType:'length[0,500]' "></td>
        </tr>
    </table>
</form>
</body>
</html>