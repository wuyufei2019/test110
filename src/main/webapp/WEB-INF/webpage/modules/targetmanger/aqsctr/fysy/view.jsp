<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用使用管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">日期：</label></td>
					<td class="width-30">
						<fmt:formatDate value="${fysy.m1 }"/>
					</td>
					<td class="width-20 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-30">${fysy.depart }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">支出项目类别：</label></td>
					<td class="width-30" colspan="3">${fysy.lx }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">具体用途：</label></td>
					<td class="width-30" colspan="3">${fysy.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">金额（万元）：</label></td>
					<td class="width-30">${fysy.m4}</td>
					<td class="width-20 active"><label class="pull-right">经办人：</label></td>
					<td class="width-30" >
						${fysy.m5 }
					</td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" >
						${fysy.m6 }
					</td>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" >
						${fysy.m7 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3" style="height:80px" >${fysy.m8 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">票据附件（图片）：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty fysy.m9}">
					 <c:forTokens items="${fysy.m9}" delims="," var="url" varStatus="urls">
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