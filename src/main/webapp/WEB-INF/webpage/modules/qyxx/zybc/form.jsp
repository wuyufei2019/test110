<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>作业班次信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/zybc/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${zybclist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${zybclist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">班次：</label></td>
					<td class="width-35"><input name="M1" class="easyui-textbox" value="${zybclist.m1 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,25]' "/></td>
					<td class="width-15 active"><label class="pull-right">人数：</label></td>
					<td class="width-35"><input name="M4" class="easyui-numberbox" value="${zybclist.m4 }" style="width: 100%;height: 30px;" data-options="required:'true' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">接班时间：</label></td>
					<td class="width-35"><input name="M2" class="easyui-timespinner" value="${zybclist.m2 }" style="width: 100%;height: 30px;" data-options="editable:true,min:'00:00'" /></td>
					<td class="width-15 active"><label class="pull-right">交班时间：</label></td>
					<td class="width-35"><input name="M3" class="easyui-timespinner" style="width: 100%;height: 30px;" data-options="editable:true,min:'00:00'" value="${zybclist.m3 }" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="M5" type="text" value="${zybclist.m5}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,250]' "></td>					
				</tr>
				
				<c:if test="${not empty zybclist.ID}">
					<input type="hidden" name="ID" value="${zybclist.ID}" />
					<input type="hidden" name="ID1" value="${zybclist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${zybclist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${zybclist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>