<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>安全风险研判库</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" action="${ctx}/aqfxyp/aqfxypk/${action}"  method="post" class="form-horizontal" >
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">大类：</label></td>
            <td class="width-85" colspan="3">
                <input name="M1" class="easyui-combobox" value="${res.m1 }" style="width: 100%;height: 30px;"
                       data-options="required:true,editable:false,panelHeight:'auto',data:
                       [{value:'1',text:'生产装置安全运行状态'},
                       {value:'2',text:'危险化学品、罐区、仓库等重大危险源的安全运行状态'},
                       {value:'3',text:'高危生产活动及作业的安全风险可控状态'}]"/>
            </td>

        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">小类：</label></td>
            <td class="width-85" colspan="3">
                <input id="M2" name="M2" class="easyui-textbox" value="${res.m2 }" style="width: 100%;height: 30px;"
                       data-options="multiline:'true',alidType:['length[0,500]'] "/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">备注：</label></td>
            <td class="width-85" colspan="3">
                <input id="M3" name="M3" class="easyui-textbox" value="${res.m3 }" style="width: 100%;height: 50px;"
                       data-options="multiline:'true',alidType:['length[0,500]'] "/>
            </td>
        </tr>

        <c:if test="${not empty res.ID}">
            <input type="hidden" name="ID" value="${res.ID}"/>
            <input type="hidden" name="ID1" value="${res.ID1}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />"/>
            <input type="hidden" name="S3" value="${res.s3}"/>
        </c:if>
        </tr></tbody>
    </table>
</form>
<script type="text/javascript">

    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    var validateForm;
    function doSubmit(){
        $("#inputForm").submit();
    }

    $(function(){

        var flag=true;
        $('#inputForm').form({
            onSubmit : function() {
                var isValid = $(this).form('validate');
                if(isValid&&flag){
                    flag=false;
                    $.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
                    return true;
                }
                return false; // 返回false终止表单提交
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