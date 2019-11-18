<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>责令整改管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
      <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-30" colspan="3">${zlzg.m0 }</td>
				</tr>
				
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-30" colspan="3">${zlzg.qyname }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-85" colspan="3" >
						${czwt }
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">整改完毕日期：</label></td>
					<td class="width-30" colspan="3">
						<fmt:formatDate value="${zlzg.m3}" pattern="yyyy-MM-dd "  />
					</td>
				</tr>
									
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申诉政府：</label></td>
					<td class="width-30">${zlzg.m4 }</td>
					<td class="width-20 active"><label class="pull-right">诉讼法院：</label></td>
					<td class="width-30">${zlzg.m5}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${zlzg.m6_1 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${zlzg.m7_1 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30">${zlzg.m6_2 }</td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30">${zlzg.m7_2}</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30" >${zlzg.m8 }</td>
					<td class="width-20 active"><label class="pull-right">检查日期：</label></td>
					<td class="width-30" >
						<fmt:formatDate value="${zlzg.m1}" pattern="yyyy-MM-dd "  />
					</td>
				</tr>
								
			</table>

		  	<tbody>
       </form>
 
</body>
</html>