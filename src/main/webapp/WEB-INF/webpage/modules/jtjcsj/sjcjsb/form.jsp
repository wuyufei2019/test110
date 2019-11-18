<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>在线监测指标</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_main.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/GlobalProvinces_extend.js"></script>
    <script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script>
    <script type="text/javascript">
        var usertype =${usertype};

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
</head>
<body>

<form id="inputForm" action="${ctx}/jtjcsj/sjcjsb/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${sjcjsb.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${sjcjsb.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td class="width-20 active"><label class="pull-right">网关编码：</label></td>
            <td class="width-30">
                <input name="gatewaycode" class="easyui-textbox" value="${sjcjsb.gatewaycode }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,11]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">网关名称：</label></td>
            <td class="width-30">
                <input name="gatewayname" class="easyui-textbox" value="${sjcjsb.gatewayname }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,100]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">供应商：</label></td>
            <td class="width-30">
                <input name="supplier" class="easyui-textbox" value="${sjcjsb.supplier }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,100]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">网关类别：</label></td>
            <td class="width-30">
                <input name="gatewaytype" class="easyui-combobox" value="${sjcjsb.gatewaytype }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'auto',validType:'length[0,100]',data:[{value:'实体网关',text:'实体网关'},{value:'虚拟网关',text:'虚拟网关'}]"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">网关型号：</label></td>
            <td class="width-30">
                <input name="gatewaymodel" class="easyui-textbox" value="${sjcjsb.gatewaymodel }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,100]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">IP地址：</label></td>
            <td class="width-30">
                <input name="ipaddr" class="easyui-textbox" value="${sjcjsb.ipaddr }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,50]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">访问端口：</label></td>
            <td class="width-30">
                <input name="portno" class="easyui-textbox" value="${sjcjsb.portno }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,50]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">子网掩码：</label></td>
            <td class="width-30">
                <input name="netmask" class="easyui-textbox" value="${sjcjsb.netmask }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,52]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">网关地址：</label></td>
            <td class="width-30">
                <input name="gateway" class="easyui-textbox" value="${sjcjsb.gateway }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,52]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">安装位置：</label></td>
            <td class="width-30">
                <input name="installloc" class="easyui-textbox" value="${sjcjsb.installloc }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,100]'"/>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">安装日期：</label></td>
            <td class="width-30">
                <input name="installdate" class="easyui-datebox" value="${sjcjsb.installdate }" style="width: 100%;height: 30px;"
                       data-options="required:'true',editable:false "/>
            </td>
            <td class="width-20 active"><label class="pull-right">采集频率(秒)：</label></td>
            <td class="width-30">
                <input name="frequncey" class="easyui-textbox" value="${sjcjsb.frequncey }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,6]'"/>
            </td>
        </tr>

        <c:if test="${not empty sjcjsb.id}">
            <input type="hidden" name="ID" value="${sjcjsb.id}"/>
            <input type="hidden" name="qyid" value="${sjcjsb.qyid}"/>
            <input type="hidden" name="createby" value="${sjcjsb.createby}"/>
            <input type="hidden" name="createdate"
                   value="<fmt:formatDate value="${sjcjsb.createdate}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="status" value="${sjcjsb.status}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    
</script>
</body>
</html>