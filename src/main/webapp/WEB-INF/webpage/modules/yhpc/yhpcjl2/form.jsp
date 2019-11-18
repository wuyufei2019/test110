<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>隐患排查记录</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/yhpc/yhpcjl/${action}"  method="post"  class="form-horizontal" >
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
        <tbody>
        <tr >
            <td class="width-15 active"><label class="pull-right">巡检点名称：</label></td>
            <td class="width-35" colspan="3">
                ${yhpcjl.jcd}
            </td>
        </tr>
        <tr >
            <td class="width-15 active"><label class="pull-right">检查内容：</label></td>
            <td class="width-35" colspan="3">
                ${yhpcjl.jcnr }
            </td>
        </tr>
        <tr >
            <td class="width-15 active"><label class="pull-right">隐患处理状态：</label></td>
            <td class="width-35">
                ${yhpcjl.yhzt }
            </td>
            <td class="width-15 active"><label class="pull-right">隐患发现人：</label></td>
            <td class="width-35">
                ${yhpcjl.jcr }
            </td>
        </tr>
        <tr >
            <td class="width-15 active"><label class="pull-right">隐患发现时间：</label></td>
            <td class="width-35" >
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${yhpcjl.createtime }" />
            </td>
            <td class="width-15 active"><label class="pull-right">计划整改时间：</label></td>
            <td class="width-35">
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${yhpcjl.sechandletime }" />
            </td>
        </tr>
        <tr >
            <td class="width-15 active"><label class="pull-right">指定整改人：</label></td>
            <td class="width-30"><input name="handlepersons" id="handlepersons" type="text" style="width: 100%;height: 30px;" class="easyui-combobox" value="${yhpcjl.handlepersons }",
                                        data-options=" multiple: true,editable:true ,panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
            </td>
        </tr>
        <tr >
            <td class="width-15 active"><label class="pull-right">隐患备注：</label></td>
            <td class="width-35" colspan="3">
                ${yhpcjl.yh }
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">隐患照片：</label></td>
            <td class="width-85" colspan="3">
                <c:if test="${not empty yhpcjl.yhzp}">
                    <c:forTokens items="${yhpcjl.yhzp}" delims="||" var="url" varStatus="urls">
                        <c:set var="urlna" value="${fn:split(url, '||')}" />
                        <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                            <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="150px;"/><br/></a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
        </tr>
        <c:if test="${not empty yhpcjl.id}">
            <input type="hidden" name="ID" value="${yhpcjl.id}" />
            <input type="hidden" name="qyid" value="${yhpcjl.qyid}" />
        </c:if>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;

    function doSubmit(){
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }

    $(function(){
        var flag=true;
        $('#inputForm').form({
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if(isValid&&flag){
                    flag=false;
                    $.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
                    return true;
                }
                return false;	// 返回false终止表单提交
            },
            success:function(data){
                $.jBox.closeTip();
                if(data=='success')
                    parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
                else
                    parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
            }
        });

    });
</script>
</body>
</html>