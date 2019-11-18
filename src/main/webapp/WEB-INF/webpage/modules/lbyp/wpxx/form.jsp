<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>用品管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/lbyp/wpxx/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35"><input name="name" id="name" style="width: 100%;height: 30px;" class="easyui-combobox"
						value="${entity.name }"
                     data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url: '${ctx}/tcode/dict/lbyp' " /></td>
					<td class="width-15 active"><label class="pull-right">用品编号：</label></td>
					<td class="width-35"><input name="number" class="easyui-textbox" value="${entity.number }"
						style="width: 100%;height: 30px;" data-options="validType:['cczu_englishCheckSub','length[0,25]'] " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">所在仓库：</label></td>
					<td class="width-35"><input name="ID2" class="easyui-combobox" value="${entity.ID2 }"
						style="width: 100%;height: 30px;"
						data-options="editable:false,required:'true',panelHeight:'100',valueField: 'id',textField: 'text',url:'${ctx}/lbyp/ckxx/idjson'" /></td>
					<td class="width-15 active"><label class="pull-right">库存量：</label></td>
					<td class="width-35"><input name="storagerate" class="easyui-textbox" value="${entity.storagerate }"
						style="width: 100%;height: 30px;"
						data-options="required : true,validType:'number'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">计量单位：</label></td>
					<td class="width-35"><input name="unit" id="unit" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${entity.unit }" /></td>
					<td class="width-15 active"><label class="pull-right">型号/规格：</label></td>
					<td class="width-35"><input name="specifications" class="easyui-combobox" value="${entity.specifications }"
						style="width: 100%;height: 30px;"
						data-options="panelHeight:'auto',valueField: 'value',textField: 'text',data:[{'value':'大','text':'大'},{'value':'中','text':'中'},{'value':'小','text':'小'}]" /></td>
					</td>
				</tr>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="ID1" value="${entity.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
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