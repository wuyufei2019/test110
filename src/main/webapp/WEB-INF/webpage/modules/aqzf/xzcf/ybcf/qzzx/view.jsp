<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看强制执行申请记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<c:if test="${action eq 'updateSub'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  colspan="3">${yqe.number }</td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-20 active" ><label class="pull-right">受处单位（个人）:</label></td>
					<td class="width-30" >${yqe.dsname }</td>
                         <td class="width-20 active"><label class="pull-right">法院名称：</label></td>
                         <td class="width-30">${yqe.court }</td>
					
				</tr>
                <tr>
                     <td class="width-20 active"><label class="pull-right">相关材料：</label></td>
                     <td class="width-30" colspan="3" >${yqe.clname}</td>
                 </tr>
		</table>
	</div>
	</tbody>
</body>
</html>