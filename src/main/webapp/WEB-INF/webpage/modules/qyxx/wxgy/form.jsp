<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.2"></script>
</head>
<body>
	<form id="inputForm" action="${ctx}/bis/gwgy/${action}" method="post">
		<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3"><div>
								<input value="${qylist.ID1 }" id="_qyid" name="ID1"
									style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
							</div></td>
					</tr>
				</c:if>
				<c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3"><input
							value="${qylist.ID1 }" id="_qyid" name="ID1"
							style="width: 100%;height:30px;" class="easyui-combobox"
							data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">工艺名称：</label></td>
					<td class="width-30" colspan="3">
						<div>
						<input name="M1" id="M1" class="easyui-combobox" value="${qylist.m1 }" style="width:100%;height: 30px;"
							   data-options="required:'true', editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/gwgy',
							    onSelect: function(rec){
							   	    $('#M1').combobox('setValue',rec.text);
									$('#processcode').val(rec.id);
							    }"/>
						</div>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">描述：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="describe"
															value="${qylist.describe }" class="easyui-textbox"
															style="width: 100%;height:30px;" data-options="multiline:true,validType:'length[0,250]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">控制方式：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="ctrlmode"
															value="${qylist.ctrlmode }" class="easyui-textbox"
															style="width: 100%;height:30px;" data-options="multiline:true,validType:'length[0,500]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">控制参数：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="ctrlpara"
															value="${qylist.ctrlpara }" class="easyui-textbox"
															style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,500]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">控制措施：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="ctrlmeasure"
															value="${qylist.ctrlmeasure }" class="easyui-textbox"
															style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,1000]'" /></td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">是否满足国家规定的控制要求：</label></td>
					<td class="width-30">
						<c:choose>
							<c:when test="${qylist.isnationdemand=='0'}">
								<input type="radio" value="1" class="i-checks"  name="isnationdemand"  />是
								<input type="radio" value="0" class="i-checks"  name="isnationdemand" checked="checked"/>否
							</c:when>
							<c:otherwise>
								<input type="radio" value="1" class="i-checks" name="isnationdemand" checked="checked"/>是
								<input type="radio" value="0" class="i-checks"  name="isnationdemand"/>否
							</c:otherwise>
						</c:choose>
					</td>

					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30"\><input name="equipcode" id="equipcode" class="easyui-combobox" value="${qylist.equipcode }" style="width: 100%;height: 30px;"
															data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'name',url:'${ctx}/jtjcsj/sbxx/equipCodeJson'"/></td>
				</tr>

				<input type="hidden" id="processcode" name="processcode" value="${qylist.processcode}" />
				<c:if test="${not empty qylist.ID}">
					<input type="hidden" name="ID" value="${qylist.ID}" />
					<input type="hidden" name="ID1" value="${qylist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${qylist.s3}" />
					<input type="hidden" name="companycode" value="${qylist.companycode}" />
					<input type="hidden" name="uuid" value="${qylist.uuid}" />
					<input type="hidden" id="districtcode" name="districtcode" value="${qylist.districtcode}" />
					<input type="hidden" id="parkid" name="parkid" value="${qylist.parkid}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var usertype=${usertype};
	</script>
</body>
</html>
