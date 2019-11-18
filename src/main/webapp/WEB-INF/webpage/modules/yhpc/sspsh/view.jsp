<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
					<td class="width-20 active"><label class="pull-right">隐患等级：</label></td>
					<td class="width-30">
					<c:if test="${sspsh.dangerlevel eq '1'}">一级</c:if>
					<c:if test="${sspsh.dangerlevel eq '2'}">二级</c:if>
					<c:if test="${sspsh.dangerlevel eq '3'}">三级</c:if>
					<c:if test="${sspsh.dangerlevel eq '4'}">四级</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30">${sspsh.shr }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">隐患发现时间：</label></td>
					<td class="width-30" >
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sspsh.createtime }" />
					</td>
					<td class="width-20 active"><label class="pull-right">隐患发现人：</label></td>
					<td class="width-30">${sspsh.yhfxr }</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">计划整改时间：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sspsh.sechandletime }" />
					</td>
					<td class="width-20 active"><label class="pull-right">计划整改人：</label></td>
					<td class="width-30">${sspsh.jhzgr }
					</td>	
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">隐患备注：</label></td>
					<td class="width-30" colspan="3">${sspsh.dangerdesc }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty sspsh.dangerphoto}">
					 <c:forTokens items="${sspsh.dangerphoto}" delims="||" var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  style="max-width: 200px;"/><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
		  	</tbody>
			</table>
       </form>

</body>
</html>