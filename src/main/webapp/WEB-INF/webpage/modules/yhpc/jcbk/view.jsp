<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查内容表库管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患级别：</label></td>
				    <td class="width-35">${jcbk.dangerlevel }</td>
				    <c:if test="${jcbk.usetype eq '2'}">
				    <td class="width-15 active"><label class="pull-right">检查单元 ：</label></td>
					<td class="width-35">${jcbk.checktitle }</td>
					</c:if>
				</tr>
				
				<tr>
				    <td class="width-15 active"><label class="pull-right">检查项目：</label></td>
					<td class="width-35" colspan="3" >
			        ${jcbk.content}
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患内容：</label></td>
				    <td class="width-35">${jcbk.checkyes}</td>
				    <td class="width-15 active"><label class="pull-right">正常内容 ：</label></td>
				<td class="width-35">${jcbk.checkno}</td>
				</tr>
				</tbody>
			</table>
       </form>
 
</body>
</html>