<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>外来方培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
			  		<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-35" colspan="3"> ${pxjl.m1_4} </td>  
				</tr>
				
			  	<tr>
			  		<td class="width-15 active"><label class="pull-right">外来方单位名称：</label></td>
					<td class="width-35" colspan="3"> ${pxjl.m1} </td>  
				</tr>

				<tr>
			  		<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35"> ${pxjl.m1_1}</td>  
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"> ${pxjl.m1_2}</td>
				</tr>
			  
			  	<tr>
			  		<td class="width-15 active"><label class="pull-right">工作岗位：</label></td>
					<td class="width-35"> ${pxjl.m1_3} </td>  
					<td class="width-15 active"><label class="pull-right">培训时间：</label></td>
					<td class="width-35"> <fmt:formatDate value="${pxjl.m2 }" pattern="yyyy-MM-dd"  /> </td>
				</tr>
				
				<tr>
			  		<td class="width-15 active"><label class="pull-right">培训人员：</label></td>
					<td class="width-35"> ${pxjl.m4}</td>  
					<td class="width-15 active"><label class="pull-right">教育人：</label></td>
					<td class="width-35"> ${pxjl.m5 }</td>
				</tr>
				
				<tr>
			  		<td class="width-15 active"><label class="pull-right">考试成绩：</label></td>
					<td class="width-35"> ${pxjl.m7} </td>  
					<td class="width-15 active"><label class="pull-right">培训结果：</label></td>
					<td class="width-35"> ${pxjl.m8} </td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">培训内容：</label></td>
					<td class="width-80" colspan="3">${pxjl.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">${pxjl.m9 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" colspan="3">
					 <c:if test="${not empty pxjl.m10}">
					 <c:forTokens items="${pxjl.m10}" delims="," var="url" varStatus="urls">
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
</body>
</html>