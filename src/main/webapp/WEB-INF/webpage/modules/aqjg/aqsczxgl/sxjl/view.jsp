<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>失信管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3" >
						${qyname }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">失信行为：</label></td>
					<td class="width-35" colspan="3" >
						${sx.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">失信行为等级：</label></td>
					<td class="width-30">${sx.m3 }</td>
					<td class="width-20 active"><label class="pull-right">失信时间起：</label></td>
					<td class="width-30"><fmt:formatDate value="${sx.m5 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">失信行为描述：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${sx.m2 }
					</td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${sx.m7 }
					</td>				
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">佐证材料：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty sx.m4}">
					 <c:forTokens items="${sx.m4}" delims="," var="url" varStatus="urls">
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