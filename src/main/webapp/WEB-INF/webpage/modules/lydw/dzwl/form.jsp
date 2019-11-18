<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>工卡管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/lydw/dzwl/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>

        <tr>
            <td class="width-20 active"><label class="pull-right">围栏名称：</label></td>
            <td class="width-80" colspan="3">
                <input type="text" name="name" class="easyui-textbox" value="${dzwl.name }"
                       data-options="required:'true'" style="width: 100%;height: 30px;"/>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">所在建筑：</label></td>
            <td class="width-30">
                <input type="text" name="buildingname" class="easyui-textbox" value="${dzwl.buildingname }" readonly
                       data-options="" style="width: 100%;height: 30px;"/>
            </td>
            <td class="width-20 active"><label class="pull-right">楼层：</label></td>
            <td class="width-30"><input name="floor" class="easyui-combobox" value="${dzwl.floor }" readonly
                                        style="width: 100%;height: 30px;"
                                        data-options="editable:false,panelHeight:'120px',data: [
                                                        {value:'1',text:'1层'},
                                                        {value:'2',text:'2层'},
                                                        {value:'3',text:'3层'},
                                                        {value:'4',text:'4层'},
                                                        {value:'5',text:'5层'}]"/>
            </td>

        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">允许进入人员：</label></td>
            <td class="width-30"><input name="allowids" class="easyui-combobox" value="${dzwl.allowids }"
                                        style="width: 100%;height: 30px;"
                                        data-options="editable:false,multiple:true,panelHeight:'auto',valueField: 'id',textField: 'text',url:'${ctx}/lydw/gkgl/jsonlist2'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">禁止进入人员：</label></td>
            <td class="width-30"><input name="banids" class="easyui-combobox" value="${dzwl.banids }"
                                        style="width: 100%;height: 30px;"
                                        data-options="editable:false,multiple:true,panelHeight:'auto',valueField: 'id',textField: 'text',url:'${ctx}/lydw/gkgl/jsonlist2'"/>
            </td>
        </tr>
        </tbody>
    </table>
    <c:if test="${not empty dzwl.ID}">
        <input type="hidden" name="ID" value="${dzwl.ID}"/>
        <input type="hidden" name="ID1" value="${dzwl.ID1}"/>
        <input type="hidden" name="floorname" value="${dzwl.floorname}"/>
        <input type="hidden" name="building" value="${dzwl.building}"/>
        <input type="hidden" name="mappoint" value="${dzwl.mappoint}"/>
        <input type="hidden" name="type" value="${dzwl.type}"/>
    </c:if>
    <c:if test="${empty dzwl.ID}">
        <input type="hidden" name="type" value="${type}"/>
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