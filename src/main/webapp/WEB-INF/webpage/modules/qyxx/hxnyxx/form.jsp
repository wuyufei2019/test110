<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>化学能源信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/hxnyxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${hxnyxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input value="${hxnyxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">能源名称：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M1" value="${hxnyxx.m1 }" style="width: 100%;height: 30px;"  data-options="required:'true', panelHeight:'auto',
								editable:false ,data: [
								        {value:'液氨',text:'液氨'},
								        {value:'天然气',text:'天然气'},
								        {value:'液化气',text:'液化气'},
								        {value:'导热油',text:'导热油'},
								        {value:'煤',text:'煤'},
								        {value:'乙炔',text:'乙炔'},
								        {value:'生物质燃料',text:'生物质燃料'},
								        {value:'其他',text:'其他'}]"/></td>
					<td class="width-20 active"><label class="pull-right">年用量（t/m3）：</label></td>
					<td class="width-30"><input name="M2" type="text" class="easyui-textbox" value="${hxnyxx.m2 }" style="width: 100%;height: 30px;"  data-options="min:0, validType:'mone' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">最大储存量（t/m3）：</label></td>
					<td class="width-30"><input name="M3" type="text" class="easyui-textbox" value="${hxnyxx.m3 }" style="width: 100%;height: 30px;"  data-options="min:0, validType:'mone' " /></td>
					<td class="width-20 active"><label class="pull-right">涉及工艺：</label></td>
					<td class="width-30"><input name="M4" type="text" class="easyui-textbox" value="${hxnyxx.m4 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否进行安全评价：</label></td>
					<td class="width-30" ><input name="M5" class="easyui-combobox"
								value="${hxnyxx.m5 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data:[
										{value:'0',text:'否'},
								        {value:'1',text:'是'}]" /></td>
					<td class="width-20 active"><label class="pull-right">是否设置监控设施：</label></td>
					<td class="width-30" >
							<input name="M6" class="easyui-combobox"
								value="${hxnyxx.m6 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'}] " />
					</td>
					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">设置监控设施内容：</label></td>
					<td class="width-30" colspan="3"><input name="M8" type="text" class="easyui-textbox" value="${hxnyxx.m8 }" style="width: 100%;height: 30px;" data-options=" validType:'length[0,125]' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注 ：</label></td>
					<td class="width-30" colspan="3"><input name="M7" type="text" value="${hxnyxx.m7}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
				</tr>
				
				<c:if test="${not empty hxnyxx.ID}">
					<input type="hidden" name="ID" value="${hxnyxx.ID}"/>
					<input type="hidden" name="ID1" value="${hxnyxx.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${hxnyxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${hxnyxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>