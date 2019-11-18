<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>演练计划管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	  <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">年度：</label></td>
					<td class="width-35" colspan="3">
						${res.m1}
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">层级：</label></td>
					<td class="width-35">${res.m2 }</td>
					<td class="width-15 active"><label class="pull-right">部门：</label></td>
					<td class="width-35">${res.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">演练主题：</label></td>
					<td class="width-35" colspan="3">
						${res.m4 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">参演部门：</label></td>
					<td class="width-35">${res.m5 }</td>
					<td class="width-15 active"><label class="pull-right">执行人：</label></td>
					<td class="width-35">${res.m6 }</td>
				</tr>

				 <tr>
					<td class="width-15 active" ><label class="pull-right">附件资料：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty res.m8}">
					 <c:forTokens items="${res.m8}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">完成情况：</label></td>
					<td class="width-35" colspan="3">${res.m7 }</td>
				</tr>
				
			</table>

		  	<tbody>
       </form>
</body>
</html>