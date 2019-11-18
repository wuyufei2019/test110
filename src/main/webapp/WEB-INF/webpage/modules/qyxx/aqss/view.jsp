<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">大类：</label></td>
					<td class="width-35"> ${aqss.m1 } </td>
					<td class="width-15 active"><label class="pull-right">小类：</label></td>
					<td class="width-35">${aqss.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全设施名称</label></td>
					<td class="width-35" colspan="3">${aqss.m9 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">涉及工艺设施：</label></td>
					<td class="width-35" colspan="3">${aqss.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${aqss.m5 }"  /></td>
					<td class="width-15 active"><label class="pull-right">到期时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${aqss.m6 }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35" >${aqss.m4 }</td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35" > 
					<c:if test="${aqss.m7 ==1}">失效</c:if> 
					<c:if test="${aqss.m7 ==2}">停用</c:if> 
					<c:if test="${aqss.m7 ==0}">正常</c:if> 
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >${aqss.m8 }</td>
					
				</tr>
				<!-- 
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty m9}">
					 <c:forTokens items="${m9}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/><a target="_blank" href="${urlna[0]}">${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty m10}">
					 <c:forTokens items="${m10}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</div> 
					</td>
				</tr>
				 -->
				</tbody>
			</table>
	</form>
</body>
</html>