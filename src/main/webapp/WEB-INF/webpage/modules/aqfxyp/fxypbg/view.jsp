<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>风险研判报告信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
    <tbody>
    <tr>
        <td class="width-15 active"><label class="pull-right">等级：</label></td>
        <td class="width-35" >${res.m1 }</td>
        <td class="width-15 active"><label class="pull-right">负责人：</label></td>
        <td class="width-35" >${res.m10 }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">日期：</label></td>
        <td class="width-35" >${res.m11 }</td>
    </tr>
    <c:if test="${ res.m1 !='厂级' }">
        <tr>
            <td class="width-15 active"><label class="pull-right">车间：</label></td>
            <td class="width-35" >${res.m2 }</td>
            <td class="width-15 active"><label class="pull-right">班组：</label></td>
            <td class="width-35" >${res.m3 }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">研判风险 ：</label></td>
            <td class="width-85" colspan="3">${res.m4 }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">高危生产活动及</br>作业的安全风险</br>可控状态：</label></td>
            <td class="width-85" colspan="3">${res.m5 }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">生产装置</br>运行状态：</label></td>
            <td class="width-85" colspan="3">${res.m6 }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">涉及罐区、仓库等</br>重大危险源</br>处于安全状态：</label></td>
            <td class="width-85" colspan="3">${res.m7 }</td>
        </tr>
    </c:if>
    <tr>
        <td class="width-15 active"><label class="pull-right">安全状态：</label></td>
        <td class="width-85" colspan="3">${res.m8 }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">承诺：</label></td>
        <td class="width-85" colspan="3">${res.m9 }</td>
    </tr>


    </tbody>
</table>
</body>
</html>