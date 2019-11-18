<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>服务项目管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >  
					<td class="width-15 active"><label class="pull-right">项目类型：</label></td>
					<td colspan="3">${fwxm.m1 }</td> 
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">项目名称：</label></td>
					<td class="width-30" colspan="3">${fwxm.m2 }</td>
					
				</tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">服务项目内容：</label></td>
					<td class="width-30" colspan="3" style="height:80px">${fwxm.m3 }</td>
				</tr>
				<tr>	
					<td class="width-20 active"><label class="pull-right">项目负责人：</label></td>
					<td class="width-30">
					${fwxm.m4 }
					</td>
					<td class="width-20 active"><label class="pull-right">项目合同资金(万元)：</label></td>
					<td class="width-30">${fwxm.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${fwxm.m6 }"/></td>
					<td class="width-20 active"><label class="pull-right">结束时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${fwxm.m7 }"/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">上传文件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty fwxm.m8}">
					 <c:forTokens items="${fwxm.m8}" delims="," var="url" varStatus="urls">
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