<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>类型添加</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/yhpc/rcjc/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                 <tr>
                     <td class="width-30 active"><label class="pull-right">类型名称：</label></td>
      				 <td class="width-70" ><input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m1 }" data-options="required:true, validType:['length[0,25]']" /></td>
                 </tr> 
                 
                 <input type="hidden" name="typelx" value="${type}" />
				 <c:if test="${not empty entity.ID}">
				 	<input type="hidden" name="ID" value="${entity.ID}" />
                    <input type="hidden" name="type" value="${entity.type}" />
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