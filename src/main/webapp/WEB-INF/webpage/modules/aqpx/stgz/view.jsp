<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>出卷规则信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >  
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-85" colspan="3" >
					${kcmc}
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">单选题（道）：</label></td>
					<td class="width-30">${stgz.m1 }</td>
					<td class="width-20 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-30">${stgz.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">多选题（道）：</label></td>
					<td class="width-30">${stgz.m2 }</td>
					<td class="width-20 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-30">${stgz.m6 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">填空题（道）：</label></td>
					<td class="width-30">${stgz.m3 }</td>
					<td class="width-20 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-30">${stgz.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">判断题（道）：</label></td>
					<td class="width-30">${stgz.m4 }</td>
					<td class="width-20 active"><label class="pull-right">每题分值：</label></td>
					<td class="width-30">${stgz.m8 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">合格分数线：</label></td>
					<td class="width-30">${stgz.m9 }</td>
				</tr>				
			</tbody>		
		</table>
	 </form>
</body>
</html>