<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>临时申请</title>
    <meta name="decorator" content="default"/>
    <style type="text/css">
        .textred {
            color: red;
        }
    </style>
</head>
<body>
<form id="inputForm" action="${ctx}/lbyp/lssq/${action}" method="post"
      class="form-horizontal">
    <table id="rwtable" class="table table-bordered " style="border-collapse: collapse;">
        <tfoot>
        <tr>
            <td align="center" colspan="4">
                <a class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left"
                   onclick="addgoods()" title="添加申请物品"><i class="fa fa-plus"></i>添加申请物品</a></td>
        </tr>
        </tfoot>
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">申请原因：</label></td>
            <td class="width-35" colspan="3"><input name="reason"
                                                    id="reason" style="width: 100%;height: 50px;"
                                                    class="easyui-textbox" value="${entity.reason }"
                                                    data-options="multiline: true, validType:'length[0,100]'"/></td>
        </tr>
        <div></div>
        <tr>
            <td class="width-15 active"><label class="pull-right">申请人：</label></td>
            <td class="width-35"><input name="ID2" id="ID2"
                                        class="easyui-combobox" value="${entity.ID2}"
                                        style="width: 100%;height: 30px;"
                                        data-options="readonly : true, valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
            </td>
            <td class="width-15 active"><label class="pull-right">审核人：</label></td>
            <td class="width-35"><input name="ID3" id="ID3"
                                        class="easyui-combobox" value="${entity.ID3}"
                                        style="width: 100%;height: 30px;" required="true"
                                        data-options="panelHeight:'130',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
            </td>
        </tr>
        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="ID1" value="${entity.ID1}"/>
            <input type="hidden" name="ID4" value="${entity.ID4}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${entity.s3}"/>
        </c:if>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    var count=0;
    $(function () {
        if ('${action}' == 'create') {
            $("#ID2").combobox("setValue", '${ID2}');
        }
        var wpqd = [];
        if ('${action}' == 'update') {
            var wplist = eval('${wplist}');
            var goods;
            for (var index in wplist) {
                goods = wplist[index];
                var html = "<tr id=" + goods.id + ">" +
                    "<td class='width-15 active'><label class='pull-right textred'><a class='fa fa-minus-square btn-danger btn-outline' onclick='remove(" + goods.id + ")'></a> 用品名称：</label></td>" +
                    "<td class='width-35'><input class='easyui-combobox' style='width: 100%;height: 30px;' name='goodsname' value='" + goods.goodsname +
                    "' data-options=\"required:'true',panelHeight:'100',valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/lbyp' \" /></td>" +
                    "<td class='width-15 active'><label class='pull-right textred'>用品数量：</label></td>" +
                    "<td class='width-35'><input name='amount' class='easyui-numberbox'value='" + goods.amount + "' style='width: 100%;height: 30px;'data-options='required: true' /></td>" +
                    "</tr>";
                var $list = $("#rwtable tr").eq(-1);
                $list.after(html);
                $.parser.parse("#" + goods.id);
            }
        }
    })

    function remove(id) {
        $("#"+id).remove();
    }
    function addgoods() {
        var $list = $("#rwtable tr").eq(-1);
        var html = "<tr id='"+count+"'>" +
            "<td class='width-15 active'><label class='pull-right textred'><a class='fa fa-minus-square btn-danger btn-outline'></a> 用品名称：</label></td>" +
            "<td class='width-35'><input class='easyui-combobox' name='goodsname' style='width: 100%;height: 30px;' data-options=\"required:'true',panelHeight:'100',valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/lbyp' \" /></td>" +
            "<td class='width-15 active'><label class='pull-right textred'>用品数量：</label></td>" +
            "<td class='width-35'><input name='amount' class='easyui-numberbox' style='width: 100%;height: 30px;'data-options='required: true' /></td>" +
            "</tr>";
        $list.after(html);
        $.parser.parse("#"+count);
        count++;
        $(".btn-danger").click(function(){
            $(this).parent().parent().parent().remove();
        });
    }

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
                parent.sqdg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });
</script>

</body>
</html>