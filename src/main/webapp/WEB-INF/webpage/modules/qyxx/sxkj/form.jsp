<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/sxkj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${sxkj.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${sxkj.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-30" colspan="3"><input name="M5" type="text" class="easyui-textbox" value="${sxkj.m5 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">主要危险有害物质：</label></td>
					<td class="width-30" colspan="3"><input name="M6" type="text" class="easyui-textbox" value="${sxkj.m6 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">数量：</label></td>
					<td class="width-30" ><input name="M2" type="text" class="easyui-numberbox" value="${sxkj.m2 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
					<td class="width-20 active"><label class="pull-right">易导致事故类型：</label></td>
					<td class="width-30"><input name="M7" class="easyui-combobox" value="${sxkj.m7 }" style="width: 100%;height: 30px;"
								data-options="required:'true',multiple:true,
								editable:false ,valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/sglx' " /></td>
				</tr>
				<tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">位置：</label></td>
					<td class="width-30" colspan="3"><input name="M3" type="text" class="easyui-textbox" value="${sxkj.m3 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">安全设施(预防/应急)：</label></td>
					<td class="width-30" colspan="3"><input name="M8" type="text" class="easyui-textbox" value="${sxkj.m8 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,1000]' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3"><input name="M9" type="text" value="${sxkj.m9}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
				</tr>
				
				<c:if test="${not empty sxkj.ID}">
					<input type="hidden" name="ID" value="${sxkj.ID}" />
					<input type="hidden" name="ID1" value="${sxkj.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${sxkj.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${sxkj.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>