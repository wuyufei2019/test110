<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>安全风险研判库信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
    <tbody>
    <tr>
        <td class="width-15 active"><label class="pull-right">大类：</label></td>
        <td class="width-85" colspan="3"><input readonly class="easyui-combobox" value="${res.m1 }" style="width: 100%;height: 30px;"
                                    data-options="editable:false,panelHeight:'auto',data:
                       [{value:'1',text:'生产装置安全运行状态'},
                       {value:'2',text:'危险化学品、罐区、仓库等重大危险源的安全运行状态'},
                       {value:'3',text:'高危生产活动及作业的安全风险可控状态'}]"/></td>

    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">小类：</label></td>
        <td class="width-85" colspan="3">${res.m2 }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">备注：</label></td>
        <td class="width-85" colspan="3">${res.m3 }</td>
    </tr>


    </tbody>
</table>
</body>
</html>