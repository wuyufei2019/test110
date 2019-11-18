<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课件管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-85"> ${kjlist.m1 } </td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">课件：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty kjlist.m2}">
					 <c:forTokens items="${kjlist.m2}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<c:set var="urlna2" value="${kjlist.m5}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna2}">${urlna[1]}</a>
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