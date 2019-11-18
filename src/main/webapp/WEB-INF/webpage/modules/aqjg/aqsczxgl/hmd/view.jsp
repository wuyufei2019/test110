<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>黑名单管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3" >
						${hmd.qyname }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">黑名单行为：</label></td>
					<td class="width-35" colspan="3" >
						${hmd.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">黑名单行为等级：</label></td>
					<td class="width-30">${hmd.m3 }</td>
					<td class="width-20 active"><label class="pull-right">黑名单时间起：</label></td>
					<td class="width-30"><fmt:formatDate value="${hmd.m5 }"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">黑名单行为描述：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${hmd.m2 }
					</td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${hmd.m7 }
					</td>				
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">佐证材料：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty hmd.m4}">
					 <c:forTokens items="${hmd.m4}" delims="," var="url" varStatus="urls">
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