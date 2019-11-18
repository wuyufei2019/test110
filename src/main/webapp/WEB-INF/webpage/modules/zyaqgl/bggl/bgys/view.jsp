<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>变更验收管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">变更项目：</label></td>
					<td class="width-30" colspan="3">
						${bgsq.bgxm }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收单位：</label></td>
					<td class="width-30">${bgsq.m2 }</td>
					<td class="width-20 active"><label class="pull-right">验收日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${bgsq.m3 }" />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收人员：</label></td>
					<td class="width-30" colspan="3">${bgsq.m4 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">验收意见：</label></td>
					<td class="width-30" colspan="3" height="80px" >${bgsq.m5 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收结论：</label></td>
					<td class="width-30" colspan="3">
						<c:if test="${bgsq.m6=='1'}">同意</c:if>
						<c:if test="${bgsq.m6=='0'}">不同意</c:if>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">抄送部门：</label></td>
					<td class="width-30" colspan="3">${bgsq.m7 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30">${bgsq.m8 }</td>
					<td class="width-20 active"><label class="pull-right">记录日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${bgsq.m9 }" />
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty bgsq.m10}">
					 <c:forTokens items="${bgsq.m10}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>	
					 </c:if>
					</td>
				</tr>
			</table>

		  	<tbody>
       </form>
</body>
</html>