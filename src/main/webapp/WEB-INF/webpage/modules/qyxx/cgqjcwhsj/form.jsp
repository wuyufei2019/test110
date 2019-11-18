<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐区、库区监测维护数据</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" action="${ctx}/bis/cgqjcwhsj/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-25 active"><label class="pull-right">所属储罐区：</label></td>
            <td class="width-25" colspan="3"><input name="ID1" id="ID1" class="easyui-combobox" value="${cglist.ID1 }" style="width: 100%;height: 30px;"
                                        data-options="required:'true',editable:true,panelHeight:'150px',valueField: 'id',textField: 'text',url:'${ctx}/bis/cgqxx/json/${qyid}'"/></td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">气体传感器设备编号：</label></td>
            <td class="width-25" colspan="3"><input name="QTCGQBH" class="easyui-textbox" value="${cglist.QTCGQBH }" style="width: 100%;height: 30px;"
                                        data-options="validType:'length[0,50]'"/></td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">气体点位号：</label></td>
            <td class="width-25"><input name="QTDWH" class="easyui-textbox" value="${cglist.QTDWH }" style="width: 100%;height: 30px;"
                                        data-options="validType:'length[0,25]'"/></td>

            <td class="width-25 active"><label class="pull-right">气体传感器位置：</label></td>
            <td class="width-25"><input class="easyui-textbox" name="QTCGQWZ" value="${cglist.QTCGQWZ }" style="width: 100%;height: 30px;"
                                        data-options="validType:'length[0,25]'"/></td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">气体名称：</label></td>
            <td class="width-25"><input name="QTMC" class="easyui-textbox" value="${cglist.QTMC }" style="width: 100%;height: 30px;"
                                        data-options="validType:'length[0,25]'"/></td>

            <td class="width-25 active"><label class="pull-right">气体类别：</label></td>
            <td class="width-25"><input class="easyui-combobox" name="QTTYPE" value="${cglist.QTTYPE }" style="width: 100%;height: 30px;"
                                        data-options="required:true,editable:false,panelHeight:'auto',data:[{value:'0',text:'有毒气体'},{value:'1',text:'可燃气体'}]"/></td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">气体类别第一级<br>报警阈值(%LEL)：</label></td>
            <td class="width-25"><input class="easyui-numberbox" name="QTBJYZ1" value="${cglist.QTBJYZ1 }"
                                        style="width: 100%;height: 30px;" data-options="precision:5"/></td>

            <td class="width-25 active"><label class="pull-right">气体浓度第二级<br>报警阈值(%LEL)：</label></td>
            <td class="width-25"><input class="easyui-numberbox" name="QTBJYZ2" value="${cglist.QTBJYZ2 }"
                                        style="width: 100%;height: 30px;" data-options="precision:5"/></td>
        </tr>

        <c:if test="${not empty cglist.ID}">
            <input type="hidden" name="ID" value="${cglist.ID}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${cglist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${cglist.s3}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;

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