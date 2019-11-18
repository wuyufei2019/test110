<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>车辆信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/basic/car/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>

        <tr>
            <td class="width-15 active"><label class="pull-right">牵引车车牌号码：</label></td>
            <td class="width-35"><input name="m3" id="m3" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m3 }"
                                        data-options="required: true, validType:['plateCode','length[0,25]','FUN[ValidateName,\'车牌号已存在\']']"/>
            </td>
            
            <td class="width-15 active"><label class="pull-right">挂车车牌号码：</label></td>
            <td class="width-35"><input name="m13" id="m13" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m13}"
                                        data-options="validType:['plateCode','length[0,25]','FUN[ValidateName,\'车牌号已存在\']']"/>
            </td>
        </tr>   
        <tr>
            <td class="width-15 active"><label class="pull-right">车辆负责人：</label></td>
            <td class="width-35"><input name="m9" id="m9" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m9 }" data-options="validType:['length[0,25]']"/></td>
            <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
            <td class="width-35"><input style="width:100%;height: 30px;" id="m10" name="m10" value="${entity.m10 }"
                                        class="easyui-textbox" data-options="validType:'mobile'"/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">车型：</label></td>
            <td class="width-35"><input name="m1" id="m1" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m1 }" data-options="validType:['length[0,25]']"/></td>
            <td class="width-15 active"><label class="pull-right">车辆吨位：</label></td>
            <td class="width-35"><input name="m4" id="m4" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m4 }" data-options="validType:['length[0,25]']"/></td>
        </tr>

        <tr>
        	 
            <td class="width-15 active"><label class="pull-right">车辆品牌：</label></td>
            <td class="width-35"><input name="m2" id="m2" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m2 }" editable="true"
                                        data-options="validType:['length[0,25]']"/></td>
        	
            <td class="width-15 active"><label class="pull-right">保险公司：</label></td>
            <td class="width-35" colspan="3"><input name="m12" id="m12" style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"
                                                    value="${entity.m12 }" data-options="validType:['length[0,50]']"/>
            </td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">最大核载人数：</label></td>
            <td class="width-35"><input name="m5" id="m5" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m5 }" data-options="validType:['length[0,25]']"/></td>
            <td class="width-15 active"><label class="pull-right">车高(米)：</label></td>
            <td class="width-35"><input name="m6" id="m6" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m6 }" data-options=" validType:['length[0,25]']"/></td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">车长(米)：</label></td>
            <td class="width-35"><input name="m7" id="m7" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m7 }" data-options=" validType:['length[0,25]']"/></td>

            <td class="width-15 active"><label class="pull-right">车宽(米)：</label></td>
            <td class="width-35"><input name="m8" id="m8" style="width: 100%;height: 30px;" class="easyui-textbox"
                                        value="${entity.m8 }" data-options=" validType:['length[0,25]']"/></td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">备注：</label></td>
            <td class="width-35" colspan="3"><input name="m11" id="m11" style="width: 100%;height: 80px;"
                                                    class="easyui-textbox"
                                                    value="${entity.m11 }"
                                                    data-options="multiline: true, validType:['length[0,100]']"/></td>
        </tr>

        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="score" value="${entity.score}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
        </c:if>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function doSubmit() {
        $("#inputForm").submit();
    }

    function ValidateName(val) {

        var result = true;
        if('${action}' =='create'){
            $.ajax({
                method: 'POST',
                url: "${ctx}/basic/car/valid/unique/" + val,
                async: false,
                success: function (data) {
                    result = data;
                }
            });
        }
        return result;
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {
                        opacity: 0
                    });
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({
                        icon: 1,
                        title: '提示',
                        offset: 'rb',
                        content: '操作成功！',
                        shade: 0,
                        time: 2000
                    });
                else
                    parent.layer.open({
                        icon: 2,
                        title: '提示',
                        offset: 'rb',
                        content: '操作失败！',
                        shade: 0,
                        time: 2000
                    });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
                if ('${action}' == 'create') {
                    $('#m4fileList').html('');
                    $('#m12fileList').html('');
                    $("#inputForm").form("clear");
                } else {
                    parent.layer.close(index);//关闭对话框。
                }
            }
        });

    });

</script>

</body>
</html>