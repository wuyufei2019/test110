<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>卡口作业申报信息</title>
    <meta name="decorator" content="default"/>
    <%--<script src="${ctxStatic}/common/webUpload.js"></script>--%>
    <style>

        .active label {
            text-align: center;
        }

        .width-14 {
            width: 14%;
        }

        .width-86 {
            width: 86%;
            padding-bottom: 5px;
        }

        .width-14 label {
            color: #d0ebff;
        }

        .fxdxxtip {
            width: 400px;
            color: #ffffff;
            background: rgba(103, 103, 103, 0.9);
            position: absolute;
            top: 10px;
            border-radius: 20px;
            padding: 10px 10px 5px 0px;
            z-index: 1200000;
            display: none;
            text-align: left;
            font-size: 5px;
        }

    </style>
</head>
<body>
<form id="inputForm" action="${ctx}/yszy/kkzysb/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tr>
            <td class="width-10 active" rowspan="7"><label class="pull-right">承运方：</label></td>
            <td class="width-15 active"><label class="pull-right">单位名称：</label></td>
            <td class="width-15" colspan="5">${entity.acceptCompany }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">经营许可证：</label></td>
            <td class="width-15" colspan="2">${entity.businessPermitNum }</td>
            <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
            <td class="width-15" colspan="2">${entity.acceptContact }</td>
        </tr>
        <tr>
            <td class="width-15 active" rowspan="2"><label class="pull-right">车辆信息：</label></td>
            <td class="width-15 active"><label class="pull-right">车牌号码：</label></td>
            <td class="width-15"><input name="plateNum" id="plateNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-combobox"
                                        value="${entity.plateNum }"
                                        data-options="textField:'m3',valueField:'m3',url:'${ctx}/beian/roadtransport/listjson',required:true,validType:['plateCode']"/>
            </td>
            <td class="width-15 active" rowspan="2"><label class="pull-right">挂车信息：</label></td>
            <td class="width-15 active"><label class="pull-right">车牌号码：</label></td>
            <td class="width-15"><input name="plateNumG" id="plateNumG"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-combobox"
                                        value="${entity.plateNumG }"
                                        data-options="textField:'m3',valueField:'m3',url:'${ctx}/beian/roadtransport/listjson',validType:['plateCode']"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">道路运输证号：</label></td>
            <td class="width-15"><input name="wayTransportNum" id="wayTransportNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.wayTransportNum }"
                                        data-options="required:true"/></td>
            <td class="width-15 active"><label class="pull-right">道路运输证号：</label></td>
            <td class="width-15"><input name="wayTransportNumG" id="wayTransportNumG"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.wayTransportNumG }"
                                        data-options=""/></td>
        </tr>

        <tr>
            <td class="width-15 active" rowspan="3"><label class="pull-right">驾驶员：</label></td>
            <td class="width-15 active"><label class="pull-right">姓名：</label></td>
            <td class="width-15"><input name="driverName" id="driverName"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverName }"
                                        data-options="required:true"/></td>
            <td class="width-15 active" rowspan="3"><label class="pull-right">押运员：</label></td>
            <td class="width-15 active"><label class="pull-right">姓名：</label></td>
            <td class="width-15"><input name="supercargoName" id="supercargoName"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoName }"
                                        data-options="required:true"/></td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">从业资格证号：</label></td>
            <td class="width-15"><input name="driverDutyRequireNum" id="driverDutyRequireNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverDutyRequireNum }"
                                        data-options="required:true"/></td>
            <td class="width-15 active"><label class="pull-right">从业资格证号：</label></td>
            <td class="width-15"><input name="supercargoDutyRequireNum" id="supercargoDutyRequireNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoDutyRequireNum }"
                                        data-options="required:true"/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-15"><input name="driverContact" id="driverContact"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverContact }"
                                        data-options="required:true,validType:'mobile'"/></td>
            <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-15"><input name="supercargoContact" id="supercargoContact"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoContact }"
                                        data-options="required:true,validType:'mobile'"/></td>
        </tr>
    </table>
    
    <c:if test="${not empty entity.ID}">
        <input type="hidden" name="ID" value="${entity.ID}"/>
        <input type="hidden" name="type" value="${entity.type}"/>
        <input type="hidden" name="userid" value="${entity.userid}"/>
        <input type="hidden" name="entrustCompany" value="${entity.entrustCompany}"/>
        <input type="hidden" name="entrustContact" value="${entity.entrustContact}"/>
        <input type="hidden" name="consigneeCompany" value="${entity.consigneeCompany}"/>
        <input type="hidden" name="consigneeContact" value="${entity.consigneeContact}"/>
        <input type="hidden" name="loadingAddress" value="${entity.loadingAddress}"/>
        <input type="hidden" name="loadingTime" value="<fmt:formatDate value="${entity.loadingTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
        <input type="hidden" name="predictArriveTime" value="<fmt:formatDate value="${entity.predictArriveTime}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
        <input type="hidden" name="transportDestination" value="${entity.transportDestination}"/>
        <input type="hidden" name="acceptCompany" value="${entity.acceptCompany}"/>
        <input type="hidden" name="businessPermitNum" value="${entity.businessPermitNum}"/>
        <input type="hidden" name="acceptContact" value="${entity.acceptContact}"/>
        <input type="hidden" name="number" value="${entity.number}"/>
        <input type="hidden" name="state" value="${entity.state}"/>
        <input type="hidden" name="attachment" value="${entity.attachment}"/>
        <input type="hidden" name="S1"
               value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
    </c:if>
</form>
<script type="text/javascript">
    var count = 0;
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

    function doSubmit() {
        $("#inputForm").submit();
    }

    $(function () {
        $("#plateNumG").combobox({
            onSelect: function (res) {
                $("#wayTransportNumG").textbox("setValue", res.m4);
            }
        });
        /* $("#driverName").combobox({
            valueField: 'driverName',
            textField: 'driverName',
            url: '${ctx}/beian/drivingcertificate/listjson',
            onSelect: function (res) {
                $("#driverDutyRequireNum").textbox("setValue", res.driverDutyRequireNum);
                $("#driverContact").textbox("setValue", res.phone);
            }
        });
        $("#supercargoName").combobox({
            valueField: 'driverName',
            textField: 'driverName',
            url: '${ctx}/beian/drivingcertificate/listjson',
            onSelect: function (res) {
                $("#supercargoDutyRequireNum").textbox("setValue", res.driverDutyRequireNum);
                $("#supercargoContact").textbox("setValue", res.phone);
            }
        });
 */

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
            }
        });

    });

</script>

</body>
</html>