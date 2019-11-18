<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>处理措施管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-30" colspan="3">${clcs.m0 }</td>
				</tr>
				
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-30" colspan="3">
						${clcs.qyname }
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30">${clcs.m9 }</td>
					<td class="width-20 active"><label class="pull-right">检查日期：</label></td>
					<td class="width-30">
						<fmt:formatDate value="${clcs.m1}" pattern="yyyy-MM-dd "  />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">事故隐患：</label></td>
					<td class="width-30" colspan="3">
					${sgyh }
					</td>
				</tr>
								
				<tr >
					<td class="width-20 active"><label class="pull-right">依据规定：</label></td>
					<td class="width-30" colspan="3">
						${clcs.m3}
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">处理决定：</label></td>
					<td class="width-30" colspan="3">
					${clcs.m4 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申诉政府：</label></td>
					<td class="width-30">${clcs.m5 }</td>
					<td class="width-20 active"><label class="pull-right">诉讼法院：</label></td>
					<td class="width-30">${clcs.m6}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${clcs.m7_1 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${clcs.m8_1 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${clcs.m7_2 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${clcs.m8_2}</td>
				</tr>

				<tbody>
			</table>
       </form>
</body>
</html>