<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>试题库信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>				
				<tr>
					<td class="width-20 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-30">${kcmc }</td>
					<td class="width-20 active"><label class="pull-right">题目类型：</label></td>
					<td class="width-30">
						<c:if test="${aqpxlist.m1 == 1}">
							单选题
						</c:if>
						<c:if test="${aqpxlist.m1 == 2}">
							多选题
						</c:if>
						<c:if test="${aqpxlist.m1 == 3}">
							填空题
						</c:if>
						<c:if test="${aqpxlist.m1 == 4}">
							判断题
						</c:if>																		
					</td>
				</tr>
				
			  	<tr >  
					<td class="width-15 active"><label class="pull-right">题干：</label></td>
					<td class="width-85" colspan="3" >
					${aqpxlist.m2 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">选项A：</label></td>
					<td class="width-30">${aqpxlist.m3 }</td>
					<td class="width-20 active"><label class="pull-right">选项B：</label></td>
					<td class="width-30">${aqpxlist.m4 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">选项C：</label></td>
					<td class="width-30">${aqpxlist.m5 }</td>
					<td class="width-20 active"><label class="pull-right">选项D：</label></td>
					<td class="width-30">${aqpxlist.m6 }</td>
				</tr>
				
			  	<tr >  
					<td class="width-15 active"><label class="pull-right">选项E：</label></td>
					<td class="width-85" colspan="3" >
					${aqpxlist.m7 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">答案：</label></td>
					<td class="width-30" colspan="3">${aqpxlist.m8 }</td>
				</tr>
			</tbody>		
		</table>
	 </form>
</body>
</html>