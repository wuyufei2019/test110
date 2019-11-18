<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>演练整改记录信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
    <tbody>
    <tr>
        <td class="width-15 active"><label class="pull-right">演练记录：</label></td>
        <td class="width-35">${res.m3 }</td>
        <td class="width-15 active"><label class="pull-right">整改人员：</label></td>
        <td class="width-35">${res.m1 }</td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">整改内容：</label></td>
        <td class="width-30" colspan="3" style="width: 100%;height: 60px;">
            ${res.m4 }
        </td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">整改照片：</label></td>
        <td class="width-85" colspan="3">
            <c:if test="${not empty res.m2}">
                <c:forTokens items="${res.m2}" delims="," var="url" varStatus="urls">
                    <c:set var="urlna" value="${fn:split(url, '||')}" />
                    <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                        <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" height="150px;"/><br/>${urlna[1]}</a>
                    </div>
                </c:forTokens>
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">附件：</label></td>
        <td class="width-30" colspan="3">
            <c:if test="${not empty res.m5}">
                <c:forTokens items="${res.m5}" delims="," var="url" varStatus="urls">
                    <c:set var="urlna" value="${fn:split(url, '||')}" />
                    <div style="margin: 5px;">
                        <a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
                    </div>
                </c:forTokens>
            </c:if>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>