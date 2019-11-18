<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测点维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>

        <tr>
            <td class="width-25 active"><label class="pull-right">传感器编号：</label></td>
            <td class="width-75">${cglist.number }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">从站id：</label></td>
            <td class="width-75">${cglist.salveId }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">偏移量：</label></td>
            <td class="width-75">${cglist.offset }</td>
        </tr>

        </tbody>
    </table>
</form>
</body>
</html>