<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>粉尘信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/fcxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${fcxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${fcxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">粉尘种类：</label></td>
					<td class="width-35" colspan="3"><input name="M1" class="easyui-combobox"
								value="${fcxx.m1 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'115',
								editable:true ,data: [
										{value:'铝粉',text:'铝粉'},
								        {value:'镁粉',text:'镁粉'},
								        {value:'铁粉',text:'铁粉'},
								        {value:'锌粉',text:'锌粉'},
								        {value:'铜粉',text:'铜粉'},
								        {value:'木粉',text:'木粉'},
								        {value:'树脂粉尘 ',text:'树脂粉尘 '},
								        {value:'面粉',text:'面粉'},
								        {value:'小麦粉尘',text:'小麦粉尘'},
								        {value:'乳糖粉尘',text:'乳糖粉尘'},
								        {value:'纸浆粉尘',text:'纸浆粉尘'},
								        {value:'聚酯纤维',text:'聚酯纤维'},
								        {value:'棉花',text:'棉花'},
								        {value:'橡胶粉尘',text:'橡胶粉尘'},
								        {value:'乳化剂 ',text:'乳化剂 '},
								        {value:'静电粉末',text:'静电粉末'},
								        {value:'不锈钢',text:'不锈钢'}
													  ] " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">涉粉作业人数：</label></td>
					<td class="width-35"><input name="M2" class="easyui-numberbox"
								value="${fcxx.m2 }" style="width: 100%;height: 30px;" data-options="min:0" /></td>
					<td class="width-15 active"><label class="pull-right">涉粉单班<br>作业人数：</label></td>
					<td class="width-35"><input name="M3" type="text" class="easyui-numberbox" value="${fcxx.m3 }" style="width: 100%;height: 30px;" data-options="min:0" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">是否有除尘器：</label></td>
					<td class="width-35" >						<c:choose>
						<c:when test="${fcxx.m6=='1'}">
							<input type="radio" value="1" class="i-checks" name="M6" checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M6" />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M6" />是
							<input type="radio" value="0" class="i-checks"  name="M6" checked="checked"/>否
						</c:otherwise>
						</c:choose></td>
					<td class="width-15 active"><label class="pull-right">除尘器种类：</label></td>
					<td class="width-35" >
							<input class="easyui-combobox" id="fcxx_m7" name="M7" value="${fcxx.m7 }"
								style="width: 100%;height: 30px;"
								data-options=" editable:false ,panelHeight:50,data: [
								        {value:'湿式',text:'湿式'},
								        {value:'干式',text:'干式'},
								        {value:'袋式',text:'袋式'},
								        {value:'旋风分离式',text:'旋风分离式'}] " />
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">是否建立粉尘<br>清扫制度：</label></td>
					<td class="width-35" >						<c:choose>
						<c:when test="${fcxx.m8=='1'}">
							<input type="radio" value="1" class="i-checks" name="M8" checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M8" />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M8" />是
							<input type="radio" value="0" class="i-checks"  name="M8"  checked="checked"/>否
						</c:otherwise>
						</c:choose></td>
					<td class="width-15 active"><label class="pull-right">是否安装监控：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m9=='1'}">
							<input type="radio" value="1" class="i-checks" name="M9"  checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M9"  />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M9"  />是
							<input type="radio" value="0" class="i-checks"  name="M9"  checked="checked"/>否
						</c:otherwise>
						</c:choose>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">是否涉爆：</label></td>
					<td class="width-35" >						<c:choose>
						<c:when test="${fcxx.m13=='1'}">
							<input type="radio" value="1" class="i-checks" name="M13" checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M13" />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M13" />是
							<input type="radio" value="0" class="i-checks"  name="M13"  checked="checked"/>否
						</c:otherwise>
						</c:choose></td>
					<td class="width-15 active"><label class="pull-right">是否职业病<br>危害因素：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m9=='1'}">
							<input type="radio" value="1" class="i-checks" name="M14"  checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M14"  />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M14"  />是
							<input type="radio" value="0" class="i-checks"  name="M14"  checked="checked"/>否
						</c:otherwise>
						</c:choose>
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">是否验收通过：</label></td>
					<td class="width-35" >
						<c:choose>
						<c:when test="${fcxx.m11=='1'}">
							<input type="radio" value="1" class="i-checks" name="M11" checked="checked"/>是
							<input type="radio" value="0" class="i-checks"  name="M11" />否
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M11" />是
							<input type="radio" value="0" class="i-checks"  name="M11" checked="checked"/>否
						</c:otherwise>
						</c:choose>
					</td>
					<td class="width-15 active"><label class="pull-right">除尘器数量：</label></td>
					<td class="width-35" >
							<input name="M12" type="text" class="easyui-numberbox" value="${fcxx.m12 }" style="width: 100%;height: 30px;"  data-options="min:0" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="M10" type="text" value="${fcxx.m10 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
				</tr>
				
				<c:if test="${not empty fcxx.ID}">
					<input type="hidden" name="ID" value="${fcxx.ID}" />
					<input type="hidden" name="ID1" value="${fcxx.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${fcxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${fcxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>