<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>变更申请管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">变更名称：</label></td>
					<td class="width-30" colspan="3">
						${bgsq.m1 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30">${bgsq.sqr }</td>
					<td class="width-20 active"><label class="pull-right">部门：</label></td>
					<td class="width-30">
					${bgsq.depart }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">变更日期：</label></td>
					<td class="width-30" colspan="3"><fmt:formatDate pattern="yyyy-MM-dd" value="${bgsq.m2 }" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">变更说明的技术依据：</label></td>
					<td class="width-30" colspan="3" height="80px" >${bgsq.m3 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">风险分析的管控对策：</label></td>
					<td class="width-30" colspan="3" height="80px" >${bgsq.m4 }</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">技术文件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty bgsq.m13}">
					 <c:forTokens items="${bgsq.m13}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty bgsq.m14}">
					 <c:forTokens items="${bgsq.m14}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30">							
						<c:if test="${bgsq.m7=='1'}">同意</c:if>
						<c:if test="${bgsq.m7=='0'}">不同意</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${bgsq.m9 }" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" colspan="3">${bgsq.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-30">							
						<c:if test="${bgsq.m10=='1'}">同意</c:if>
						<c:if test="${bgsq.m10=='0'}">不同意</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${bgsq.m12 }" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" colspan="3">${bgsq.m11 }</td>
				</tr>
				
			</table>

		  	<tbody>
       </form>
</body>
</html>