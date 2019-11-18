<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>储罐报警信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">企业：</label></td>
					<td class="width-85" colspan="3">
						${bj.qyname }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">报警时间：</label></td>
					<td class="width-85" colspan="3">
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"  value="${bj.colltime}"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报警情况：</label></td>
					<td class="width-85" colspan="3" >
					${bj.situation}
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">报警原因：</label></td>
					<td class="width-85" colspan="3" >
					${bj.reason}
					</td>					
				</tr>

				</tbody>
			</table>
	 </form>
<script type="text/javascript">
	
</script>
</body>
</html>