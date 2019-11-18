<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业生产装置危化品配置信息</title>
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

<form id="inputForm" action="${ctx}/jtjcsj/qysczzpzxx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qysczzpzxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qysczzpzxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">所属生产装置：</label></td>
            <td class="width-30" colspan="3">
                <input name="proddevid" class="easyui-combobox" value="${qysczzpzxx.proddevid }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'120px',required:'true',editable:false,valueField: 'id',textField: 'text',url:'${ctx}/jtjcsj/qysczzxx/sczzJson'"/>
            </td>
		</tr>
		
       	<tr>
        	<td class="width-20 active"><label class="pull-right">CAS号：</label></td>
            <td class="width-30">
                <input name="cascode" class="easyui-combobox" value="${qysczzpzxx.cascode }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'150px',editable:false,valueField: 'text',textField: 'text',url:'${ctx}/jtjcsj/hxpckpz/casJson',
                       		onChange: function(rec){
									$.ajax({
							           url:'${ctx}/jtjcsj/hxpckpz/wlname',
							           data:{'text':rec},
							           type : 'post',
							           dataType: 'json',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#chemcname').textbox('setValue', data.zwm);
							                $('#chemename').textbox('setValue', data.ywm);
							           }
							     	});
							    }"/>
            </td>
            <td class="width-20 active"><label class="pull-right">最大在线量：</label></td>
            <td class="width-30">
                <input name="maxolqty" class="easyui-textbox" value="${qysczzpzxx.maxolqty }" style="width: 100%;height: 30px;"
                 		data-options="validType:'length[0,50]'"/>
            </td>
        </tr>
        
 		<tr>
            <td class="width-20 active"><label class="pull-right">危化品中文名称：</label></td>
            <td class="width-30" colspan="3">
                <input id="chemcname" name="chemcname" class="easyui-textbox" value="${qysczzpzxx.chemcname }" style="width: 100%;height: 30px;"
                 		data-options="validType:'length[0,500]'"/>
            </td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">危化品英文名称：</label></td>
            <td class="width-30" colspan="3">
                <input id="chemename" name="chemename" class="easyui-textbox" value="${qysczzpzxx.chemename }" style="width: 100%;height: 30px;"
                 		data-options="validType:'length[0,500]'"/>
            </td>
		</tr>
		
        <c:if test="${not empty qysczzpzxx.id}">
            <input type="hidden" name="ID" value="${qysczzpzxx.id}"/>
            <input type="hidden" name="qyid" value="${qysczzpzxx.qyid}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${qysczzpzxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${qysczzpzxx.s3}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    

    
</script>
</body>
</html>