<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>受限空间作业分析</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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
</head>
<body>
<form id="inputForm" action="${ctx}/zyaqgl/dhzy/act/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <input type="hidden" name="id" value="${dhzyid }"/>
        </tbody>
    </table>
    <p style="margin-top: 10px;color: red;font-size: 15px;">
        <strong>分析数据</strong>
        <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加分析数据"><i
                class="fa fa-plus"></i> 添加分析数据</a>
    </p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;"/>

    <table id="rwtable" style="width: 100%;" border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7">
        <tr>
            <td style="text-align: center;width: 18%;">分析点名称</td>
            <td style="text-align: center;width: 30%;">分析数据</td>
            <td style="text-align: center;width: 10%;">分析时间</td>
            <td style="text-align: center;width: 10%;">分析人</td>
        </tr>
    </table>
    <input type="hidden" name="taskId" value="${taskId}">
</form>
<script type="text/javascript">
    var rwid = 1;

    function addRw() {
        var $list = $("#rwtable tr").eq(-1);
        var $li = $(
            '<tr style="height:30px" >' +
            '<td style="" align="center">' +
            '<input type="text" id="name_' + rwid + '" name="name" class="easyui-textbox" value="" style="height: 30px;width: 99%;" />' +
            '</td>' +
            '<td style="" align="center">' +
            '<input type="text" id="data_' + rwid + '" name="data" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />' +
            '</td>' +
            '<td style="" align="center">' +
            '<input type="text" id="time_' + rwid + '" name="time" class="easyui-textbox" value=""  style="height: 30px;" />' +
            '</td>' +
            '<td style="" align="center">' +
            '<input type="text" id="person_' + rwid + '" name="person" class="easyui-textbox" value="" required="true" style="height: 30px;" />' +
            '</td>' +
            '</tr>'
        );
        $list.after($li);

        $('#time_' + rwid).datetimebox({
            editable: false
        });

        $('#person_' + rwid).combobox({
            editable: true,
            url: '${ctx}/system/compuser/userjson',
            valueField: 'id',
            textField: 'text',
            panelHeight: '150px'
        });

        $('#name_' + rwid).textbox({
            required: 'true',
            validType: 'length[0,100]'
        });

        rwid = rwid + 1;
    }

    //删除指定行的数据
    function removeTr(obj) {
        obj.remove();
    }
</script>
</body>
</html>