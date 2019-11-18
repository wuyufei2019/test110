<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>冶金信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">类别名称：</label></td>
					<td class="width-35">${yjlist.m1 }</td>
					<td class="width-15 active"><label class="pull-right">熔炼场所建筑物结构：</label></td>
					<td class="width-35">${yjlist.m7 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">金属液体转运方式：</label></td>
					<td class="width-35">${yjlist.m8 }</td>
					<td class="width-15 active"><label class="pull-right">主要产品：</label></td>
					<td class="width-35">${yjlist.m2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">载体是否为移动式：</label></td>
					<td class="width-35" >
					<c:if test="${yjlist.m3==0}">
						否
					</c:if>
					<c:if test="${yjlist.m3==1}">
						是
					</c:if>	
					</td>
					<td class="width-15 active"><label class="pull-right">加热方式：</label></td>
					<td class="width-35" >
							${yjlist.m4 }
					</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">年产量(t)：</label></td>
					<td class="width-35" >${yjlist.m5 }</td>
					<td class="width-15 active"><label class="pull-right">现场人数：</label></td>
					<td class="width-35" >${yjlist.m6 }</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" style="height:80px">
					${yjlist.m9}
					</td>					
				</tr>
				</tbody>
			</table>

	</form>
</body>
</html>