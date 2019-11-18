<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>审核申请</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/lbyp/lssq/${action}" method="post"
		class="form-horizontal">
		<table id="rwtable" class="table table-bordered "
			style="margin-bottom: 5px">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">申请原因：</label></td>
					<td class="width-35" colspan="3"><input name="reason"
						id="reason" style="width: 100%;height: 50px;"
						class="easyui-textbox" value="${entity.reason }"
						data-options="readonly : true ,multiline: true, validType:'length[0,100]'" /></td>
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
					<input type="hidden" name="ID" value="${entity.ID}" />
			</tbody>
		</table>
		<div class="easyui-accordion" id="accordion" border="true"
			style="border-width:  1px 1px 0 1px">
			<div title="用品清单" border="false">
				<table id="wpqd_dg"></table>
			</div>
		</div>
		<table class="table table-bordered" style="margin-top: 5px">
			<tr>
				<td class="width-15 active"><label class="pull-right">审核人：</label></td>
				<td class="width-35"><input name="ID3" id="ID3"
					class="easyui-combobox" value="${entity.ID3 }"
					style="width: 100%;height: 30px;" readonly="true"
					data-options="panelHeight:'100',required: true,valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
				<td class="width-15 active"><label class="pull-right">审核结论：</label></td>
				<td class="width-35" style="text-align: center"><input type='radio' name='result' <c:if test="${entity.result eq '1' or empty entity.result }">checked='checked'</c:if>  value='1' class='i-checks'  />通过
						       <input type='radio' name='result' value='0' <c:if test="${entity.result eq '0' }">checked='checked'</c:if> class='i-checks'/>不通过</td>
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
				<td class="width-35" colspan="3"><input name="opinion" type="text"
					value="${entity.opinion }" class="easyui-textbox"
					style="width: 100%;height: 30px;"
					data-options="multiline:true,validType:'length[0,100]' "></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		var dg = $('#wpqd_dg').datagrid({
			fitColumns : true,
			border : false,
			idField : 'id2',
			striped : true,
			rownumbers : true,
			nowrap : false,
			scrollbarSize : 0,
			singleSelect : true,
			striped : true,
			columns : [ [ {
				field : 'id2',
				title : 'id2',
				hidden : true
			}, {
				field : 'goodsname',
				title : '用品名称',
				width : 100,
				align : 'center'
			}, {
				field : 'amount',
				title : '数量',
				width : 50,
				align : 'center'
			} ] ]
		});
		$(function() {
			dg.datagrid("loadData", eval('${wplist}'));
			if ('${action}' == 'review') {
				$("#ID3").combobox("setValue", '${userid}');
			}
		})

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			$("#inputForm").submit();
		}
		$(function() {
			var flag = true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (isValid && flag) {
						flag = false;
						$.jBox.tip("正在提交，请稍等...", 'loading', {
							opacity : 0
						});
						return true;
					}
					return false; // 返回false终止表单提交
				},
				success : function(data) {
					$.jBox.closeTip();
					if (data == 'success')
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '操作成功！',
							shade : 0,
							time : 2000
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				}
			});

		});
	</script>


</body>
</html>