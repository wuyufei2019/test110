<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>查看审核申请</title>
<meta name="decorator" content="default" />
</head>
<body>
		<table id="rwtable" class="table table-bordered " style="margin-bottom: 5px">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
					<td class="width-35" colspan="3">${entity.reason }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请人：</label></td>
					<td class="width-35"><input name="ID2" id="ID2"
						class="easyui-combobox" value="${entity.ID2}"
						style="width: 100%;height: 30px;"
						data-options="readonly : true, valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-15 active"><label class="pull-right">部门（班组）：</label></td>
					<td class="width-35"><input name="ID4" id="ID4"
						class="easyui-combobox" value="${entity.ID4}"
						style="width: 100%;height: 30px;"
						data-options="readonly : true, valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson'" /></td>

				</tr>
			</tbody>
		</table>
		<div class="easyui-accordion" id="accordion" border="true" 
			style="border-width:  1px 1px 0 1px;">
			<div title="用品清单" border="false" >
				<table id="wpqd_dg" ></table>
			</div>
		</div>
		<c:if test="${ not empty entity.result }">
		<table class="table table-bordered" style="margin-top: 5px">
			<tr>
				<td class="width-15 active"><label class="pull-right">审核人：</label></td>
				<td class="width-35"><input name="ID3" id="ID3"
					class="easyui-combobox" value="${entity.ID3 }"
					style="width: 100%;height: 30px;" readonly="true"
					data-options="panelHeight:'100',required: true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				<td class="width-15 active"><label class="pull-right">审核结论：</label></td>
				<td class="width-35" style="text-align: center"><input type='radio' name='result' <c:if test="${entity.result eq '1' or entity.result eq ''}">checked='checked'</c:if>  value='1' class='i-checks'  />通过
						       <input type='radio' name='result' value='0' <c:if test="${entity.result eq '0' }">checked='checked'</c:if> class='i-checks'/>不通过</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
				<td class="width-35" colspan="3">${entity.opinion }</td>
			</tr>
			</c:if>
		</table>
	<script type="text/javascript">
	$(function(){
		
		var dg = $('#wpqd_dg').datagrid({
			fitColumns : true,
			border : false,
			idField : 'id',
			striped : true,
			rownumbers : true,
			nowrap : false,
			scrollbarSize : 0,
			singleSelect : true,
			striped : true,
			columns : [ [ {
				field : 'id',
				title : 'id',
				hidden : true
			}, {
				field : 'goodsname',
				title : '用品名称',
				width : 150,
				align : 'center'
			}, {
				field : 'amount',
				title : '数量',
				width : 50,
				align : 'center'
			} ] ],
		});
		dg.datagrid("loadData", eval('${wplist}'));
	});
	</script>


</body>
</html>