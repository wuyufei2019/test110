<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>陈述申辩笔录查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
					<td class="width-30" colspan="3" >${cssbbl.m11 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">申辩开始时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${cssbbl.m1 }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="width-20 active"><label class="pull-right">申辩结束时间：</label></td>
					<td class="width-30"><fmt:formatDate value="${cssbbl.m2 }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">地点：</label></td>
					<td class="width-30" colspan="3" >${cssbbl.m3 }</td>	
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">陈述申辩人：</label></td>
					<td class="width-30">${cssbbl.m4 }</td>	
				   <td class="width-20 active"><label class="pull-right">性别：</label></td>
				   <td class="width-30">${cssbbl.m5 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" colspan="3">${cssbbl.m6 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-30" colspan="3">${cssbbl.m7 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30">${cssbbl.m8 }</td>	
				   <td class="width-20 active"><label class="pull-right">邮编：</label></td>
				   <td class="width-30">${cssbbl.m10 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">联系地址：</label></td>
					<td class="width-30" colspan="3">${cssbbl.m9 }</td>	
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30">${cssbbl.m12 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${cssbbl.m13 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30">${cssbbl.m14 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${cssbbl.m15 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30">${cssbbl.m17 }</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">陈述申辩记录：</label></td>
					<td class="width-30" colspan="3">${cssbbl.m16 }</td>					
				</tr>
				
			  </tbody>
			</table>
	 </form>
</body>
</html>