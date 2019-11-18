<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>采购入库管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/lbyp/cgrk/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35"><input name="ID1" id="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
						value="${entity.ID1 }" data-options="required:'true',editable: false,panelHeight:'100',valueField: 'id',textField: 'text',url:'${ctx}/lbyp/wpxx/idjson'" /></td>
					<td class="width-15 active"><label class="pull-right">采购时间：</label></td>
					<td class="width-35"><input name="buytime" id="buytime" style="width: 100%;height: 30px;" class="easyui-datebox"
						value="${entity.buytime }" data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">采购单价：</label></td>
					<td class="width-35"><input name="price" class="easyui-textbox" value="${entity.price }"
						style="width: 100%;height: 30px;" data-options="validType:'money'"/></td>
					<td class="width-15 active"><label class="pull-right">采购数量：</label></td>
					<td class="width-35"><input name="amount" class="easyui-textbox" value="${entity.amount }"
						style="width: 100%;height: 30px;" data-options="required:'true',validType:'number'"/></td>
				</tr>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="note" type="text" value="${entity.note }" class="easyui-textbox"
						style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,100]'"></td>
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
		$(function(){
			$("#buytime").datebox("setValue",new Date().format("yyyy-MM-dd"));
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