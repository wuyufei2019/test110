<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>仓库区管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr >
				<td class="width-20 active"><label class="pull-right">仓库区编号：</label></td>
				<td class="width-30" colspan="3">
					${ckqxx.m1 }
				</td>
			</tr>

			<tr >
				<td class="width-20 active"><label class="pull-right">仓库区名称：</label></td>
				<td class="width-30" colspan="3">
					${ckqxx.m2 }
				</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">仓库区面积(㎡)：</label></td>
				<td class="width-30">${ckqxx.m3 }</td>

				<td class="width-20 active"><label class="pull-right">库房个数：</label></td>
				<td class="width-30">${ckqxx.m4 }</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">仓库区位置：</label></td>
				<td colspan="3" style="height:30px;line-height:30px;">
					<label>经度：</label>${ckqxx.m5 }
					<label>纬度：</label>${ckqxx.m6 }
				</td>
			</tr>

			<tr>
				<td class="width-20 active"><label class="pull-right">备注：</label></td>
				<td class="width-30" colspan="3" style="height: 80px;">${ckqxx.m7 }</td>
			</tr>
		</tbody>
	</table>

	<c:if test="${cklist ne null}">
		<c:forEach items="${cklist}" var="cklist" varStatus="count">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
				<tr>
					<td colspan="4" style="text-align: center;font-size: 20px;color: red;">仓库${count.count}</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">所属仓库区：</label></td>
					<td class="width-35">
							${cklist.m13 }
					</td>
					<td class="width-15 active"><label class="pull-right">仓库名称：</label></td>
					<td class="width-35">
							${cklist.m1 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">仓库编号：</label></td>
					<td class="width-35">${cklist.m2 }</td>
					<td class="width-15 active"><label class="pull-right">建筑面积(㎡)：</label></td>
					<td class="width-35">${cklist.m3 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">层数：</label></td>
					<td class="width-35">${cklist.m6 }</td>
					<td class="width-15 active"><label class="pull-right">建筑结构：</label></td>
					<td class="width-35">${cklist.m5 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">火灾危险等级：</label></td>
					<td class="width-35" colspan="3">
							${cklist.m4 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">占地面积(㎡)：</label></td>
					<td class="width-35">${cklist.m8 }</td>
					<td class="width-15 active"><label class="pull-right">耐火等级：</label></td>
					<td class="width-35">${cklist.m9 }</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">储存物料：</label></td>
					<td class="width-35" colspan="3">
							${cklist.m10}
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3" >
							${cklist.m7 }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
						<c:if test="${not empty cklist.m11}">
							<c:forTokens items="${cklist.m11}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
									<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
								</div>
							</c:forTokens>
						</c:if>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
						<c:if test="${not empty cklist.m12}">
							<c:forTokens items="${cklist.m12}" delims="," var="url" varStatus="urls">
								<c:set var="urlna" value="${fn:split(url, '||')}" />
								<div style="margin: 5px;">
									<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
								</div>
							</c:forTokens>
						</c:if>
					</td>
				</tr>
				</tbody>
			</table>
		</c:forEach>
	</c:if>
</body>
</html>