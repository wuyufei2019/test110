<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>预防保养年度计划</title>
    <meta name="decorator" content="default" />
</head>
<body>
<form class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr >
            <td class="width-15 active"><label class="pull-right">年度：</label></td>
            <td class="width-35" colspan="3">
                ${sbgl.m8 }
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">项目名称：</label></td>
            <td class="width-35" >${sbgl.m1 }</td>
            <td class="width-15 active"><label class="pull-right">检修内容：</label></td>
            <td class="width-35" >${sbgl.m2 }</td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">检修方案：</label></td>
            <td class="width-35" >${sbgl.m3 }</td>
            <td class="width-15 active"><label class="pull-right">检修人员：</label></td>
            <td class="width-35" >${sbgl.m4 }</td>
        </tr>

        <tr >
            <td class="width-15 active"><label class="pull-right">安全措施：</label></td>
            <td class="width-35" colspan="3">
                ${sbgl.m5 }
            </td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">检修质量：</label></td>
            <td class="width-35" >${sbgl.m6 }</td>
            <td class="width-15 active"><label class="pull-right">检修进度：</label></td>
            <td class="width-35" >${sbgl.m7 }</td>
        </tr>

        </tbody>
    </table>
</form>
</body>
</html>