<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定计划管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">相关方名称：</label></td>
					<td class="width-30" colspan="3">
						${xgfpdjh.pddw }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">评定日期：</label></td>
					<td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd" value="${xgfpdjh.m1 }" /></td>
					<td class="width-20 active"><label class="pull-right">评定主持人：</label></td>
					<td class="width-30">
					${xgfpdjh.m3 }
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">合作项目清单：</label></td>
					<td class="width-30" colspan="3" height="80px" >${xgfpdjh.m2 }</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">评定人员：</label></td>
					<td class="width-30" colspan="3" height="80px" ><div id="pdryList">
						<div id="pdryIDs">
							<div id="pdryList"></div>
						</div>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">总评：</label></td>
					<td class="width-30">${xgfpdjh.m4 }</td>
					<td class="width-20 active"><label class="pull-right">评定等级：</label></td>
					<td class="width-30">
					${xgfpdjh.m5 }
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30">							
						<c:if test="${xgfpdjh.m6=='1'}">同意</c:if>
						<c:if test="${xgfpdjh.m6=='0'}">不同意</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${xgfpdjh.m8 }" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" colspan="3">${xgfpdjh.m7 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-30">							
						<c:if test="${xgfpdjh.m9=='1'}">同意</c:if>
						<c:if test="${xgfpdjh.m9=='0'}">不同意</c:if>
					</td>
					<td class="width-20 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-30">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${xgfpdjh.m11 }" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" colspan="3">${xgfpdjh.m10 }</td>
				</tr>
				
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
if ('${action}' == 'view') {
			var $list = $("#pdryList");
			var ids = '${idname}';
			var pdryids="";
			if (ids != null && ids != '' && ids != 'null') {
				var arry3 = ids.split(",");
				for (var i = 0; i < arry3.length-1; i++) {
					var arry4 = arry3[i].split("||");
					var $li = $("<span id=\"" +arry4[0]+ "\" style=\"margin-bottom: 10px;\">"
							+ arry4[1]
							+ "</span>,&nbsp");
					pdryids += arry4[0] + ",";
					$list.append($li);
				}
			}
}
</script>
</body>
</html>