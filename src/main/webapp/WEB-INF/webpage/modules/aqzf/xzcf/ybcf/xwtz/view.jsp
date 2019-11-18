<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问通知查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-35" colspan="3">${xwtz.m0}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">被询问单位：</label></td>
				    <td class="width-35" colspan="3">${qyname}</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">调查主题：</label></td>
				    <td class="width-35" colspan="3">${xwtz.m1 }</td>
				</tr>
				<tr>
				<td class="width-15 active"><label class="pull-right">询问时间：</label></td>
				<td class="width-35" colspan="3"><fmt:formatDate value="${xwtz.m2 }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问地点：</label></td>
				    <td class="width-35" colspan="3">${xwtz.m3 }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">证件材料：</label></td>
				    <td class="width-35" colspan="3">
				    ${xwtz.m8 }
				    </td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">额外材料：</label></td>
					<td class="width-35" colspan="3">
			        ${xwtz.m4}
					</td>
				</tr>
				<%-- 
				<tr>
					<td class="width-15 active"><label class="pull-right">监管部门地址：</label></td>
				    <td class="width-35" colspan="3">${xwtz.m5 }</td>
				</tr> --%>
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35">${xwtz.m6 }</td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35">${xwtz.m7 }</td>	    
				</tr> --%>
			  </tbody>
			</table>
		  	
	 </form>
</body>
</html>