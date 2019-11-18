<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>职业病体检</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${zybtj.m1}" /></td>
					<td class="width-20 active"><label class="pull-right">体检人数：</label></td>
					<td class="width-30">${zybtj.m3 }</td>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-85" colspan="3">${zybtj.m6}</td>					
				</tr>	
				<tr>
					<td class="width-15 active"><label class="pull-right">体检医院：</label></td>
					<td class="width-85" colspan="3">
					${zybtj.m2 }
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${zybtj.m4 }
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >
					${zybtj.m7 }
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty zybtj.m5}">
					 <c:forTokens items="${zybtj.m5}" delims="," var="url" varStatus="urls">
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
</body>
</html>