<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>岗位信息管理</title>
<meta name="decorator" content="default" />
</head>
<body>

	<form id="inputForm" action="${ctx}/bis/gzxx/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">车间（装置）名称：</label></td>
					<td class="width-80"><input name="M3" class="easyui-textbox" value="${entity.m3 }"
												style="width: 100%;height: 30px;"
												data-options="" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">岗位名称：</label></td>
					<td class="width-80"><input name="M1" class="easyui-combobox" value="${entity.m1 }"
						style="width: 100%;height: 30px;" 
                  data-options="panelHeight:'100',required:'true', valueField: 'id',textField: 'text',url: '${ctx}/tcode/dict/gwgz' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">装置区与岗位允许最大人数：</label></td>
					<td class="width-80"><input name="M4" class="easyui-textbox" value="${entity.m4}"
						style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">人员在岗时间段开始时间：</label></td>
					<td class="width-80"><input name="M5" class="easyui-timespinner" value="${entity.m5}"
												style="width: 100%;height: 30px;" data-options="editable:true,min:'00:00'" /></td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">人员在岗时间段结束时间：</label></td>
					<td class="width-80"><input name="M6" class="easyui-timespinner" value="${entity.m6}"
												style="width: 100%;height: 30px;" data-options="editable:true,min:'00:00'" /></td>

				</tr>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="ID1" value="${entity.ID1}" />
				</c:if>
			</tbody>
		</table>
	</form>

	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
			$("#inputForm").serializeObject();
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
							time : 1500
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败,禁止重复添加！',
							shade : 0,
							time : 1500
						});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				}
			});
		});
	</script>
</body>
</html>