<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看事先催告记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  colspan="3">${yse.number }</td>
				</tr>
				
				<tr>
					<td class="width-20 active" ><label class="pull-right">单位（个人）:</label></td>
					<td class="width-30" colspan="3" >
					${yse.qyname }
					</td>
					
				</tr>
		        <tr>
					<td class="width-20 active"><label class="pull-right">尚未履行的行政决定：</label></td>
					<td class="width-30" colspan="3">${yse.xzjd }</td>
				</tr>
                 <tr>
                <td class="width-20 active"><label class="pull-right">罚款缴纳日期：</label></td>
                         <td class="width-30"><fmt:formatDate value="${yse.finedate }"/></td>
                 </tr>   
				<tr>
					<td class="width-20 active"><label class="pull-right">罚款：</label></td>
					<td class="width-30">${yse.fine }</td>
					<td class="width-20 active"><label class="pull-right">加处罚款：</label></td>
					<td class="width-30">${yse.extrafine }</td>
				</tr>
                <tr>
					<td class="width-20 active"><label class="pull-right">合计：</label></td>
					<td class="width-30">${yse.allfine }</td>
				</tr>
                <tr>
                     <td class="width-20 active"><label class="pull-right">立即履行的行政决定：</label></td>
                     <td class="width-30" colspan="3">${yse.extraxzjd }</td>
                 </tr>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${yse.s1}"/>"/>
				 <input type="hidden" name="ID" value="${yse.ID}" />
				 <input type="hidden" name="id1" value="${yse.id1}" />
		</table>
	</div>
	</tbody>
</body>
</html>