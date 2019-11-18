<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查计划信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-10 active"><label class="pull-right">年度：</label></td>
					<td class="width-30">${jcjh.m1 }</td>
					<td class="width-10 active"><label class="pull-right">月份：</label></td>
					<td class="width-30">${jcjh.m2 }</td>
				</tr>
				<tr>
					<td class="width-10 active"><label class="pull-right">属地：</label></td>
					<td class="width-30">${jcjh.sd }</td>
					<td class="width-10 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-30">${jcjh.hylx }</td>
				</tr>
				<tr>
					<td class="width-10 active"><label class="pull-right">检查处室：</label></td>
					<td class="width-30" colspan="3">${jcjh.m5 }</td>
			     </tr>
			     <tr>
					<td class="width-10 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">${jcjh.qyname }</td>
				</tr>
				</tbody>
			</table>
		  	
	 </form>
</body>
</html>