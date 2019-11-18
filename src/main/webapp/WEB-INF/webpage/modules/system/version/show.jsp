<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default"/>
</head>
<body>
<div style="padding: 30px 100px 24px; max-width: 900px; min-width: 780px;">
    <div><h1 style="font-size: 28px; line-height: 40px;   padding-right: 8px; color: #333;">更新日志</h1></div>

    <c:forEach items="${list}" var="infor">
        <div style="padding: 0 0 5px 5px;"><h2 >${infor.name}</h2>  <span style=""> 更新时间：${infor.updatetime} </span> </div>
        <div>${infor.message}</div>
    </c:forEach>

</div>
</body>
</html>
