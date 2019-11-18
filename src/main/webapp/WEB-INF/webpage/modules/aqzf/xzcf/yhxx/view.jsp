<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>员工管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">银行名称：</label></td>
					<td class="width-35">${bie.bankname }</td>
					<td class="width-15 active"><label class="pull-right">银行账号：</label></td>
					<td class="width-35">${bie.account }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35 " colspan="3" >${bie.address }</td>
				</tr>
				
					<input type="hidden" name="ID" value="${bie.ID}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${bie.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</tbody>
			</table>
       </form>
</body>
</html>