<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>网格员班次任务查看</title>
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
						<c:if test="${bcrw.type eq '3'}">月检</c:if>
						<c:if test="${bcrw.type eq '4'}">年检</c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">任务设置：</label></td>
					<td class="width-35" colspan="3">
						<table style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 50%;">起始时间</td>
								<td style="text-align: center;width: 50%;">结束时间</td>
							</tr>
							<c:if test="${bcrw.type != '3'}">
								<c:forEach var="item" items="${rwsjlist}" varStatus="status">
		        						<tr >
										<td style="text-align: center;width: 50%;">${item.starttime}</td>
										<td style="text-align: center;width: 50%;">${item.endtime}</td>
									</tr>
								</c:forEach> 
							</c:if>
							<c:if test="${bcrw.type eq '3'}">
								<c:forEach var="item" items="${rwsjlist}" varStatus="status">
		        						<tr >
										<td style="text-align: center;width: 50%;">${item.starttime}日</td>
										<td style="text-align: center;width: 50%;">${item.endtime}日</td>
									</tr>
								</c:forEach> 
							</c:if>
						</table>	
					</td>
				</tr>
			  </tbody>
			</table>
		  	
	 </form>
</body>
</html>