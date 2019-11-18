<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故调查管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">事故名称：</label></td>
					<td class="width-35" colspan="3">
						${res.m1 }
					</td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">发生时间：</label></td>
					<td class="width-35" colspan="3">
						<fmt:formatDate value="${res.m2 }"/>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">经济损失（万元）：</label></td>
					<td class="width-35">
						${res.m3 }
					</td>
					<td class="width-15 active"><label class="pull-right">伤亡人数：</label></td>
					<td class="width-35">
						${res.m4 }
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">调查报告：</label></td>
					<td class="width-35" colspan="3">
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
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
</script>
</body>
</html>