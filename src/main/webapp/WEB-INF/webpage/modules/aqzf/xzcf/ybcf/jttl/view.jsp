<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>集体讨论记录查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">记录编号：</label></td>
					<td class="width-30" colspan="3">${jttl.m1}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
				    <td class="width-30" colspan="3">${jttl.m2}</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">讨论开始时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${jttl.m3 }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="width-20 active"><label class="pull-right">讨论结束时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${jttl.m4 }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">地点：</label></td>
				    <td class="width-30" colspan="3">${jttl.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">主持人：</label></td>
				    <td class="width-30">${jttl.m6 }</td>
				    <td class="width-20 active"><label class="pull-right">汇报人：</label></td>
				    <td class="width-30">${jttl.m7 }</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30" colspan="3">${jttl.m8}</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">出席人员姓名及职务：</label></td>
					<td class="width-30" colspan="3">${jttl.m9}</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">讨论内容：</label></td>
					<td class="width-30" colspan="3">${jttl.m10}</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">讨论记录：</label></td>
					<td class="width-30" colspan="3">${jttl.m11}</td>
				</tr>
				<tr>
				    <td class="width-20 active"><label class="pull-right">结论性意见：</label></td>
					<td class="width-30" colspan="3">${jttl.m12}</td>
				</tr>
				
			  </tbody>
			</table>
	 </form>
</body>
</html>