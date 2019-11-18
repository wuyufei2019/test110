<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业生产单元区域信息</title>
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

<form id="inputForm" action="${ctx}/jtjcsj/qyscdyqyxx/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <c:if test="${usertype != '1' and action eq 'create'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qyscdyqyxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${usertype != '1' and action eq 'update'}">
            <tr>
                <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                <td class="width-30" colspan="3">
                    <input value="${qyscdyqyxx.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
                           class="easyui-combobox"
                           data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'"/>
                </td>
            </tr>
        </c:if>

		<tr>
			<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
            <td class="width-30">
                <input name="hazardcode" class="easyui-combobox" value="${qyscdyqyxx.hazardcode }" style="width: 100%;height: 30px;"
                 		data-options="required:'true',editable:false,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/bis/hazard/hazardCodeJson'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
            <td class="width-30" colspan="3">
                <input name="prodcellid" class="easyui-textbox" value="${qyscdyqyxx.prodcellid }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,24]'"/>
            </td>
		</tr>
		
		<tr>
            <td class="width-20 active"><label class="pull-right">生产区域类型：</label></td>
            <td class="width-30">
                <input name="prodcelltype" class="easyui-combobox" value="${qyscdyqyxx.prodcelltype }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'auto',required:'true',data:[{value:'HEV01',text:'罐区'},{value:'HEV02',text:'生产装置区'},{value:'HEV03',text:'库区'}]"/>
            </td>
			<td class="width-20 active"><label class="pull-right">生产单元区域名称：</label></td>
            <td class="width-30">
                <input name="prodcellname" class="easyui-textbox" value="${qyscdyqyxx.prodcellname }" style="width: 100%;height: 30px;"
                       data-options="required:'true',validType:'length[0,100]'"/>
            </td>
        </tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">安全负责人姓名：</label></td>
            <td class="width-30">
                <input name="safedutyername" class="easyui-textbox" value="${qyscdyqyxx.safedutyername }" style="width: 100%;height: 30px;"
                       data-options="validType:'length[0,25]'"/>
            </td>
            <td class="width-20 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-30">
                <input name="linkmode" class="easyui-textbox" value="${qyscdyqyxx.linkmode }" style="width: 100%;height: 30px;"
                       data-options="validType:['number','length[0,50]']"/>
            </td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">面积：</label></td>
            <td class="width-30">
                <input name="prodarea" class="easyui-textbox" value="${qyscdyqyxx.prodarea }" style="width: 100%;height: 30px;"
                       data-options="validType:['number','length[0,21]']"/>
            </td>
            <td class="width-20 active"><label class="pull-right">防护堤面积  ：</label></td>
            <td class="width-30">
                <input name="iprotectarea" class="easyui-textbox" value="${qyscdyqyxx.iprotectarea }" style="width: 100%;height: 30px;"
                       data-options="validType:['number','length[0,21]']"/>
            </td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">有无防护堤：</label></td>
            <td class="width-30">
                <input name="isprotect" class="easyui-combobox" value="${qyscdyqyxx.isprotect }" style="width: 100%;height: 30px;"
                       data-options="panelHeight:'auto',data:[{value:'0',text:'否'},{value:'1',text:'是'}]"/>
            </td>
            <td class="width-20 active"><label class="pull-right">贮罐个数：</label></td>
            <td class="width-30">
                <input name="tanknum" class="easyui-textbox" value="${qyscdyqyxx.tanknum }" style="width: 100%;height: 30px;"
                       data-options="validType:['number','length[0,8]']"/>
            </td>
		</tr>
		
		
		<tr>
			<td class="width-20 active"><label class="pull-right">罐间最小间距：</label></td>
            <td class="width-30">
                <input name="mintankspace" class="easyui-textbox" value="${qyscdyqyxx.mintankspace }" style="width: 100%;height: 30px;"
                       data-options="validType:['number','length[0,21]']"/>
            </td>
		</tr>
		
		<tr>
			<td class="width-20 active"><label class="pull-right">生产工艺或存储状况简介  ：</label></td>
 			<td class="width-30" colspan="3"><input name="craftintroduction" class="easyui-textbox" value="${qyscdyqyxx.craftintroduction }"
            	style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'"/></td>
		</tr>
		
		<tr>
            <td class="width-20 active"><label class="pull-right">安全措施：</label></td>
            <td class="width-30" colspan="3"><input name="safemeasures" class="easyui-textbox" value="${qyscdyqyxx.safemeasures }"
            	style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,100]'"/></td>
        </tr>
		
        <c:if test="${not empty qyscdyqyxx.id}">
            <input type="hidden" name="ID" value="${qyscdyqyxx.id}"/>
            <input type="hidden" name="qyid" value="${qyscdyqyxx.qyid}"/>
            <input type="hidden" name="createby" value="${qyscdyqyxx.createby}"/>
            <input type="hidden" name="companycode" value="${qyscdyqyxx.companycode}"/>
            <input type="hidden" name="parkid" value="${qyscdyqyxx.parkid}"/>
            <input type="hidden" name="createdate"
                   value="<fmt:formatDate value="${qyscdyqyxx.createdate}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="status" value="${qyscdyqyxx.status}"/>
        </c:if>
        </tbody>
    </table>
</form>

<script type="text/javascript">
    
</script>
</body>
</html>