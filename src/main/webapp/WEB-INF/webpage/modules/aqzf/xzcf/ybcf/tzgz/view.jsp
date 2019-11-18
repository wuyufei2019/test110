<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">编号：</label></td>
					<td class="width-35"  colspan="3"><input name="number" class="easyui-textbox" editable="false" value="${yte.number }" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr>
					<td class="width-15 active" ><label class="pull-right">单位（个人）:</label></td>
					<td class="width-35"  >${yte.name }</td>
					<td class="width-15 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-35"><fmt:formatDate value="${yte.punishdate}"/></td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">违法行为：</label></td>
					<td class="width-35" colspan="3">${yte.illegalact }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证据：</label></td>
					<td class="width-85" colspan="3">${yte.evidence }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-35"  colspan="3">${yte.unlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚依据：</label></td>
					<td class="width-35" colspan="3">${yte.enlaw }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">处罚结果：</label></td>
					<td class="width-35" colspan="3">${yte.punishresult }</td>
				</tr>
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">联系人姓名：</label></td>
					<td class="width-35">${yte.contactname }</td>
					<td class="width-15 active"><label class="pull-right">联系人电话：</label></td>
					<td class="width-35">${yte.phone }</td>
				</tr> --%>
		</table>
	</div>
</body>
</html>