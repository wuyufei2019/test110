<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>历史意见</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<div class="easyui-accordion" style="width:100%;height:50%;">
    <iframe frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no
            src="${ctx}/../${url}"></iframe>

</div>
<div class="easyui-accordion" style="width:100%;">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-35" colspan="4">历史意见
        </tr>
        <tr>
            <th class="width-25"><label >环节：</label></th>
            <th class="width-25"><label >姓名：</label></th>
            <th class="width-25"><label >处理时间：</label></th>
            <th class="width-25"><label >意见：</label></th>
        </tr>
        <c:forEach var="item" items="${approvalOpinions}" varStatus="status">
            <tr>
                <td class="width-25">${item.activityName}</td>
                <td class="width-25">${item.userName}</td>
                <td class="width-25"><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                <td class="width-25">${item.message}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>