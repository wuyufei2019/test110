<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>提交审批</title>
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
                    if(data=='success')
                        parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
                    else
                        parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
                    parent.dg.datagrid('reload');
                    parent.layer.close(index);//关闭对话框。
                }
            });

        });

    </script>

</head>
<body>

<form id="inputForm" action="${ctx}/WorkFlowController/create" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">

        <tr>
            <td class="width-15 active"><label class="pull-right">业务名称：</label></td>
            <td class="width-35"><input type="text" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${businessname }" readonly></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">流程：</label></td>
            <td class="width-35" ><input name="processInstanceKey" type="text" value="${processid }"
                                         class="easyui-combobox"
                                         style="width: 100%;height: 30px;"
                                         data-options="panelHeight:'auto', editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/workflow/dict/plist/${businesstype }' "></td>
        </tr>

    </table>
    <input name="businessId" value="${id }" type="hidden"/>
    <input name="type" value="${businesstype }" type="hidden"/>
</form>
</body>
</html>