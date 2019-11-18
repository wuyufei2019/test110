<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.0"></script>
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/yhpc/aqkf/${action}" method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="margin:auto;">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">扣分时间：</label></td>
					<td class="width-35"><input name="M1" type="text" class="easyui-datebox" value="<fmt:formatDate value="${aqkf.m1 }"/>" style="width:100%;height:30px;" data-options="required:'true',editable:false"/></td>
					<td class="width-15 active"><label class="pull-right">扣分分值：</label></td>
					<td class="width-35"><input type="text" name="M3" class="easyui-textbox" value="${aqkf.m3 }"  style="width: 100%;height: 30px; " data-options="required:true" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">扣分原因：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 80px;" id="M2" name="M2" class="easyui-textbox" value="${aqkf.m2 }" data-options="multiline:true"/>
					</td>
				</tr>
				
				<c:if test="${action eq 'createhis'}">
					<input type="hidden" name="ID1" value="${mainid}" />
				</c:if>
				
				<c:if test="${not empty aqkf.ID}">
					<input type="hidden" name="ID" value="${aqkf.ID}" />
					<input type="hidden" name="QYID" value="${aqkf.QYID}" />
					<input type="hidden" name="ID1" value="${aqkf.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${aqkf.s1}" pattern="yyyy-MM-dd HH:mm:ss" />" />
					<input type="hidden" name="S3" value="${aqkf.s3}" />
				</c:if>
				</tbody>
			</table>
		</form>
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>