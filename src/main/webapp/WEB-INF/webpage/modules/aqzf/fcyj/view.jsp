<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>复查意见管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
      <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-30" colspan="3">${fcyj.m0 }</td>
				</tr>
				
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-30" colspan="3">${fcyj.qyname }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-30" colspan="3">
						${fcyj.m2 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">提出意见：</label></td>
					<td class="width-30" colspan="3">
						${fcyj.m6 }
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">整改完毕日期：</label></td>
					<td class="width-30" colspan="3">
						<fmt:formatDate value="${fcyj.m1}" pattern="yyyy-MM-dd "  />
					</td>
				</tr>
									
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty fcyj.m7}">
					 <c:forTokens items="${fcyj.m7}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="100px;" height="100px;"/><br/>${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${fcyj.m3_1 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${fcyj.m4_1 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${fcyj.m3_2 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${fcyj.m4_2}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30" colspan="3" >${fcyj.m5 }</td>
				</tr>
								
			</table>

		  	<tbody>
       </form>
 
</body>
</html>