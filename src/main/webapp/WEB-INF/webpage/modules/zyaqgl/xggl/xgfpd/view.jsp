<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">相关方单位名称：</label></td>
					<td class="width-30" colspan="3">
						${xgfpd.pddw }
					</td>
				</tr>
               <c:forEach items="${pdcont }" var="item" step="2" varStatus="status">
                     <tr>
                        <td class="width-20 active"><label class="pull-right">${item.m1 }：</label></td>
                        <td class="width-30">${item.point }</td>
                        <c:if test="${status.index+1<pdcont.size()}">
                           <td class="width-20 active"><label class="pull-right">${pdcont[status.index+1].m1 }：</label></td>
                           <td class="width-30"> ${pdcont[status.index+1].point}</td>
                        </c:if>
                     </tr>
                     </c:forEach>
				<tr>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>总评：</label></td>
					<td class="width-30">${xgfpd.m11 }</td>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>评定等级：</label></td>
					<td class="width-30">${xgfpd.m12 }</td>
				</tr>

			</table>

		  	<tbody>
       </form>
 
</body>
</html>