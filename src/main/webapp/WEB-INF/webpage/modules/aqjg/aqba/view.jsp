<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全备案管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" class="form-horizontal" >
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
		<tr >
			<td class="width-15 active"><label class="pull-right">备案编号：</label></td>
			<td class="width-35" >
				${aqbalist.m1 }
			</td>
			<td class="width-20 active"><label class="pull-right">类别：</label></td>
			<td class="width-30">${aqbalist.m3 }</td>
		</tr>
		<tr>
			<td class="width-20 active"><label class="pull-right">备案日期：</label></td>
			<td class="width-30"><fmt:formatDate value="${aqbalist.m2 }"/></td>
			<td class="width-20 active"><label class="pull-right">编制日期：</label></td>
			<td class="width-30"><fmt:formatDate value="${aqbalist.ruletime }"/></td>
		</tr>
		<tr>
			<td class="width-20 active"><label class="pull-right">中介公司名称：</label></td>
			<td class="width-30">${aqbalist.agency }</td>
			<td class="width-20 active"><label class="pull-right">到期日期：</label></td>
			<td class="width-30"><fmt:formatDate value="${aqbalist.expiration }"/></td>
		</tr>
		<tr>
			<%-- 	<td class="width-20 active"><label class="pull-right">是否审核：</label></td>
                <td class="width-30">
                <c:choose>
                    <c:when test="${aqbalist.m4 eq '1'}">
                        是
                    </c:when>
                    <c:when test="${aqbalist.m4 eq '0'}">
                        否
                    </c:when>
                </c:choose>
                </td> --%>
			<td class="width-20 active"><label class="pull-right">经手人：</label></td>
			<td class="width-30">${aqbalist.m7 }</td>
		</tr>

		<tr >
			<td class="width-15 active"><label class="pull-right">备注：</label></td>
			<td colspan="3">${aqbalist.m8}</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">附件：</label></td>
			<td class="width-30" colspan="3">
				<c:if test="${not empty aqbalist.m6}">
					<c:forTokens items="${aqbalist.m6}" delims="," var="url" varStatus="urls">
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

</form>
<script type="text/javascript">

</script>
</body>
</html>