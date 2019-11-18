<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课程管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
			  		<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-35"> 
					<c:choose>
						<c:when test="${aqpxlist.m5 eq '1'}">
							日常计划培训
						</c:when>
						<c:when test="${aqpxlist.m5 eq '2'}">
							入职三级教育培训
						</c:when>
						<c:when test="${aqpxlist.m5 eq '3'}">
							外来方培训
						</c:when>
						<c:otherwise>${aqpxlist.m5}</c:otherwise>
					</c:choose> </td>  
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35"> ${aqpxlist.m1 } </td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">课程学时(h)：</label></td>
					<td class="width-30">${aqpxlist.m2 }</td>
					<td class="width-20 active"><label class="pull-right">课程学分：</label></td>
					<td class="width-30">${aqpxlist.m3 }</td>
				</tr>

				<tr id="dep">	
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">课件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty url}">
					 <c:forTokens items="${url}" delims="," var="url" varStatus="urls">
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
	var m5='${aqpxlist.m5}';
	if(m5==2){
		 var td="<td class=\"width-20 active\"><label class=\"pull-right\">培训部门：</label></td>"
			 +"<td class=\"width-30\">"+'${dep}'+"</td>";	   	
		 $("#dep").append(td); 
	}
</script>
</body>
</html>