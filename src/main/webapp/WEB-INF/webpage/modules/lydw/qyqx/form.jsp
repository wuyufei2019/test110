<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>区域权限管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/lydw/qyqx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
            <tr>
                <td class="width-20 active"><label class="pull-right">区域名称：</label></td>
                <td class="width-30">
                    <input type="text" name="name" class="easyui-textbox" value="${qyqx.name }"
                           data-options="required:'true'" style="width: 100%;height: 30px;"/>
                </td>
                <td class="width-20 active"><label class="pull-right">楼层：</label></td>
                <td class="width-30"><input name="floor" class="easyui-combobox" value="${qyqx.floor }"
                                            style="width: 100%;height: 30px;"
                                            <c:if test="${action == 'update'}">readonly</c:if>
                                            data-options="editable:false,panelHeight:'120px',data: [
                                                            {value:'1',text:'1层'},
                                                            {value:'2',text:'2层'},
                                                            {value:'3',text:'3层'},
                                                            {value:'4',text:'4层'},
                                                            {value:'5',text:'5层'}]"/></td>
            </tr>
            <tr >
                <td class="width-20 active"><label class="pull-right">允许进出部门：</label></td>
                <td class="width-30" colspan="3">
                    <input type="text" id="deptnames" name="deptnames" class="easyui-combobox" value="${qyqx.deptnames }"
                           data-options="required:'true',multiple:true,multiline:true,panelHeight:'100',valueField: 'text',textField: 'text',url:'${ctx}/system/department/deptjson'" style="width: 100%;height: 50px;" />
                </td>
            </tr>
        </tbody>
    </table>
    <c:if test="${not empty qyqx.ID}">
        <input type="hidden" name="ID" value="${qyqx.ID}"/>
        <input type="hidden" name="mappoint" value="${qyqx.mappoint}"/>
    </c:if>
</form>
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
                return false; //返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({icon: 1, title: '提示', offset: 'rb', content: '操作成功！', shade: 0, time: 2000});
                else
                    parent.layer.open({icon: 2, title: '提示', offset: 'rb', content: '操作失败！', shade: 0, time: 2000});
                parent.dg.datagrid('reload');
                parent.dg.datagrid('clearChecked');
                parent.dg.datagrid('clearSelections');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });
</script>
</body>
</html>