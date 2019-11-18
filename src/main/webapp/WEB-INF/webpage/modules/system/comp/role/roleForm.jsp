<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>企业内部角色管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var index = parent.layer.getFrameIndex(window.name);
        var url = "${ctx}/system/compuser/comprole/${action}";
        var validateForm;

        $(document).ready(function () {
            var tabsHeight = $(".easyui-tabs").height();
            parent.layer.style(index, {
                height: tabsHeight
            });
            $('#role').tabs({
                onSelect: function(title){
                    if(title=='2、权限分配'){
                        parent.layer.style(index, {
                            height: '500px',
                            top:'10px'
                        });
                    }
                }
            });
            validateForm = $("#inputForm").validate({
                debug: true
            });

        });
        function addRole() {
            $.ajax({
                type:'post',
                url:url,
                data:$("#inputForm").serialize(),
                success:function(data){
                    if(data.indexOf('success')!=-1){
                        $("#tfooter").remove();
                        parent.dg.datagrid('reload');
                        var roleid =data.split(":")[1];
                        var content ='<iframe id=periframe frameborder=0 width=100% height=432px marginheight=0 marginwidth=0 scrolling=no src="'+
                            '${ctx}/system/compuser/'+roleid+'/setrole"></iframe><div style="text-align:center"><a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"' +
                            'onclick="savePer('+roleid+')" title="保存"><i class="fa fa-plus"></i>保存</a></div>';
                        //加载tab页
                        $('#role').tabs('add',{
                            title:'2、权限分配',
                            content:content
                        });
                    }
                    else{
                        layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
                    }
                }
            });
        }

        function savePer(roleId) {
            var permissions = $("#periframe")[0].contentWindow.getPermissionList();
                $.ajax({
                    async:false,
                    type:'POST',
                    data:JSON.stringify(permissions),
                    contentType:'application/json;charset=utf-8',
                    url:'${ctx}/system/compuser/'+roleId+'/updatePermission',
                    success: function(data){
                        if(data=='success')
                            top.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
                        else
                            top.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
                        parent.layer.close(index);
                    }
                });
        }

    </script>

</head>
<body>

<div id="role" class="easyui-tabs" style="width:100%;height:auto">
    <div title="1、角色信息" style="padding:10px">
        <form:form id="inputForm" modelAttribute="role" class="form-horizontal">
            <form:hidden path="id"/>
            <form:hidden path="delFlag"/>
            <form:hidden path="qyid"/>
            <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
                <tbody>
                <tfoot id="tfooter">
                <tr>
                    <td align="center" colspan="4">
                        <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"

                           onclick="addRole()" title="确定"><i class="fa fa-plus"></i>确定</a>
                </tr>
                </tfoot>
                <tr>
                    <td class="width-15 active"><label class="pull-right">角色名:</label></td>
                    <td class="width-35"><form:input path="name" htmlEscape="false" maxlength="50"
                                                     class="form-control required"/></td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">角色编码:</label></td>
                    <td class="width-35"><form:input path="roleCode" htmlEscape="false" maxlength="50"
                                                     class="form-control"/></td>

                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">排序:</label></td>
                    <td class="width-35"><form:input path="sort" htmlEscape="false" maxlength="11"
                                                     class="form-control digits"/></td>

                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">描述:</label></td>
                    <td class="width-35"><form:textarea path="description" htmlEscape="false" rows="3" maxlength="200"
                                                        class="form-control "/></td>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>
    <%--<div title="2、权限分配" style="padding:10px;">
        <iframe frameborder=0 width=100% height=700px marginheight=0 marginwidth=0 scrolling=no
                src="${ctx}/system/compuser/1/setrole"></iframe>
    </div>--%>
</div>
</body>
</html>