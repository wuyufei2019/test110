<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
  <head>
    <title>危险工艺</title>
	<meta name="decorator" content="default"/>
  </head>

  <body>
  <form class="form-horizontal">
   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
   				<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">工艺名称：</label></td>
					<td class="width-30">
						${qylist.m1 }
					</td>
					<td class="width-20 active"><label class="pull-right">工艺编码：</label></td>
					<td class="width-30">${qylist.processcode }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">描述：</label></td>
					<td class="width-30" colspan="3">${qylist.describe }</td>
				</tr>


				<tr>
					<td class="width-20 active"><label class="pull-right">控制方式：</label></td>
					<td class="width-30" colspan="3">${qylist.ctrlmode }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">控制参数：</label></td>
					<td class="width-30" colspan="3">${qylist.ctrlpara }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">控制措施：</label></td>
					<td class="width-30" colspan="3">${qylist.ctrlmeasure }</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">是否满足国家规定的控制要求：</label></td>
					<td class="width-30">
						<c:choose>
							<c:when test="${qylist.isnationdemand=='0'}">
								否
							</c:when>
							<c:otherwise>
								是
							</c:otherwise>
						</c:choose>
					</td>

					<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30">${qylist.equipcode }</td>
				</tr>
				</tbody>
			</table>
			</form>
  </body>
</html>
