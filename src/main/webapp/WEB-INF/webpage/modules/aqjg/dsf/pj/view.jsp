<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方评价信息查看</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">第三方机构名称：</label></td>
					<td class="width-30" >
						<input value="${pjlist.m1}" type="text" id="M1" name="M1" style="width: 500px;height: 30px;"
								class="easyui-combobox"
								data-options="panelHeight:'auto' ,required:true,disabled:true,editable:true,valueField:'id',textField:'text',url:'${ctx }/dsffw/pj/dnamelist2'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">评价：</label></td>
					<td class="width-30"><c:choose>
						<c:when test="${pjlist.m2 eq '1'}">
							优秀
						</c:when>
						<c:when test="${pjlist.m2 eq '2'}">
							良好
						</c:when>
						<c:when test="${pjlist.m2 eq '3'}">
							合格
						</c:when>
						<c:when test="${pjlist.m2 eq '4'}">
							不合格
						</c:when>
					</c:choose></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">年度：</label></td>
					<td class="width-30">${pjlist.m4 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">评价人：</label></td>
					<td class="width-30">${pjlist.m5 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30">${pjlist.m6 }</td>
				</tr>
			</table>

		  	</tbody>
	 </form>
</body>
</html>