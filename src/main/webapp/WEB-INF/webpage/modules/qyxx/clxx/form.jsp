<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/clxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${clxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${clxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr>
					<td class="width-20 active"><label class="pull-right">车牌号码：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M1" value="${clxx.m1 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'"/></td>

					<td class="width-20 active"><label class="pull-right">车型：</label></td>
					<td class="width-30"><input name="M2" type="text" class="easyui-textbox" value="${clxx.m2 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">驾驶员姓名：</label></td>
					<td class="width-30"><input name="M3" type="text" class="easyui-textbox" value="${clxx.m3 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>

					<td class="width-20 active"><label class="pull-right">驾驶员手机号码：</label></td>
					<td class="width-30"><input name="M4" type="text" class="easyui-textbox" value="${clxx.m4 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">押送员姓名：</label></td>
					<td class="width-30"><input name="M5" type="text" class="easyui-textbox" value="${clxx.m5 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>

					<td class="width-20 active"><label class="pull-right">押送员手机号码：</label></td>
					<td class="width-30"><input name="M6" type="text" class="easyui-textbox" value="${clxx.m6 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">允许行驶的线路：</label></td>
					<td class="width-30" colspan="3"><input name="M7" type="text" class="easyui-textbox" value="${clxx.m7 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,100]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">允许停泊的位置：</label></td>
					<td class="width-30"><input name="M8" type="text" class="easyui-textbox" value="${clxx.m8 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]'" /></td>

					<td class="width-20 active"><label class="pull-right">允许停泊的时长（H）：</label></td>
					<td class="width-30"><input name="M9" type="text" class="easyui-numberbox" value="${clxx.m9 }" style="width: 100%;height: 30px;"  data-options="min:0,precision:1" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注 ：</label></td>
					<td class="width-30" colspan="3"><input name="M10" type="text" value="${clxx.m10}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,250]' "></td>
				</tr>
				
				<c:if test="${not empty clxx.ID}">
					<input type="hidden" name="ID" value="${clxx.ID}"/>
					<input type="hidden" name="ID1" value="${clxx.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${clxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${clxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>