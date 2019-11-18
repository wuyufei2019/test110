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
				<tr>
					<td class="width-20 active"><label class="pull-right">备案编号：</label></td>
					<td class="width-30">${obj.m1 }</td>				
					<td class="width-20 active"><label class="pull-right">备案日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${obj.m2 }"/></td>
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">备案经手人：</label></td>
					<td colspan="3" style="height: 50px">${obj.m7}</td> 
				</tr>
				
				<tr >  
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td colspan="3"style="height: 50px">${obj.m8}</td> 
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">应急流程图：</label></td>
					<td class="width-30" colspan="3">
					<c:if test="${not empty obj.m9}">
					 <c:forTokens items="${obj.m9}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty obj.m6}">
					 <c:forTokens items="${obj.m6}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</div> 
					</td>
				</tr>
				
			</table>

		  	</tbody>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>