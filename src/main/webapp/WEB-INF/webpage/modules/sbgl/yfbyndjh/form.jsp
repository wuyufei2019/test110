<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>预防保养年度计划管理</title>
    <meta name="decorator" content="default" />
</head>
<body>

<form id="inputForm" action="${ctx}/sbgl/yfbyndjh/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr >
            <td class="width-15 active"><label class="pull-right">年度：</label></td>
            <td class="width-35" colspan="3">
                <input style="width: 100%;height: 30px;" id="m8" name="m8"  class="easyui-combobox" value="${sbgl.m8 }" data-options="required :true,validType:'length[0,50]'"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">项目名称：</label></td>
            <td class="width-35">
                <input  name="m1" style="width: 100%;height: 30px;" required ="true" class="easyui-textbox" value="${sbgl.m1 }" />
            </td>
            <td class="width-15 active"><label class="pull-right">检修内容：</label></td>
            <td class="width-35">
                <input  name="m2" style="width: 100%;height: 30px;" required ="true" class="easyui-textbox" value="${sbgl.m2 }" />
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">检修方案：</label></td>
            <td class="width-35">
                <input  name="m3" style="width: 100%;height: 30px;"  class="easyui-textbox" value="${sbgl.m3 }" />
            </td>
            <td class="width-15 active"><label class="pull-right">检修人员：</label></td>
            <td class="width-35">
                <input  name="m4" style="width: 100%;height: 30px;" required ="true" class="easyui-combobox" value="${sbgl.m4 }"
                        data-options="editable : false ,panelHeight:100,valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson'"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">安全措施：</label></td>
            <td class="width-35" colspan="3"><input  name="m5" type="text" value="${sbgl.m5 }"
                                                    class="easyui-textbox" style="width: 100%;height: 80px;"
                                                    data-options="multiline : true,validType:'length[0,200]'"/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">检修质量：</label></td>
            <td class="width-35">
                <input  name="m6" style="width: 100%;height: 30px;"  class="easyui-textbox" value="${sbgl.m6 }" />
            </td>
            <td class="width-15 active"><label class="pull-right">检修进度：</label></td>
            <td class="width-35">
                <input  name="m7" style="width: 100%;height: 30px;"  class="easyui-textbox" value="${sbgl.m7 }" />
            </td>
        </tr>

        <c:if test="${not empty sbgl.ID}">
            <input type="hidden" name="ID" value="${sbgl.ID}" />
            <input type="hidden" name="qyid" value="${sbgl.qyid}" />
            <input type="hidden" name="S1" value="<fmt:formatDate value="${sbgl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
            <input type="hidden" name="S3" value="${sbgl.s3}" />
        </c:if>
        </tbody>
    </table>
</form>
<script type="text/javascript">

    //提交处理
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;
    function doSubmit() {
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }

    //年份下拉框初始化
    $("#m8").combobox({
        valueField:'year',
        textField:'year',
        panelHeight:'auto'
    });
    var data = [];
    var thisYear;
    var startYear=new Date().getUTCFullYear()+2;

    for(var i=0;i<6;i++){
        thisYear=startYear-i;
        data.push({"year":thisYear});
    }

    $("#m8").combobox("loadData", data);//下拉框加载数据

    $(function() {
        var flag = true;
        $('#inputForm').form({
            onSubmit : function() {
                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {
                        opacity : 0
                    });
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success : function(data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({
                        icon : 1,
                        title : '提示',
                        offset : 'rb',
                        content : '操作成功！',
                        shade : 0,
                        time : 2000
                    });
                else
                    parent.layer.open({
                        icon : 2,
                        title : '提示',
                        offset : 'rb',
                        content : '操作失败！',
                        shade : 0,
                        time : 2000
                    });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });
    });
</script>
</body>

</html>