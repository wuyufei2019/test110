<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检班次任务查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">班次名称：</label></td>
					<td class="width-35">${bcrw.name}</td>
					<td class="width-15 active"><label class="pull-right">班次类型：</label></td>
					<td class="width-35">
						<c:if test="${bcrw.type eq '1'}">每月一次</c:if>
						<c:if test="${bcrw.type eq '2'}">每周一次</c:if>
						<c:if test="${bcrw.type eq '3'}">每天一次</c:if>
						<c:if test="${bcrw.type eq '4'}">临时班次</c:if>
					</td>
				</tr>
				<c:if test="${bcrw.type eq '1'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">每月检查日期：</label></td>
					    <td class="width-35"  colspan="3">${bcrw.month}</td>
					</tr>
				</c:if>
				<c:if test="${bcrw.type eq '2'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">每周检查：</label></td>
					    <td class="width-35"  colspan="3">${bcrw.weeknum}</td>
					</tr>
				</c:if>
				<c:if test="${bcrw.type eq '3'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">每天开始时间：</label></td>
						<td class="width-35">${bcrw.startmin}</td>
						<td class="width-15 active"><label class="pull-right">每天结束时间：</label></td>
						<td class="width-35">${bcrw.endmin}</td>
					</tr>
				</c:if>
				<c:if test="${bcrw.type eq '4'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">临时起始时间：</label></td>
						<td class="width-35">${bcrw.startdate}</td>
						<td class="width-15 active"><label class="pull-right">临时结束时间：</label></td>
						<td class="width-35">${bcrw.enddate}</td>
					</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">巡检点：</label></td>
				    <td class="width-35" colspan="3">${jcdnames}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">巡检人员：</label></td>
				    <td class="width-35" colspan="3">${xjrynames}</td>
				</tr>
			  </tbody>
			</table>
		  	
	 </form>
</body>
</html>