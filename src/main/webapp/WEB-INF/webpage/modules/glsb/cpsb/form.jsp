<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车牌识别</title>
	<meta name="decorator" content="default" />
	
	</head>
<body>
	<form id="inputForm" action="${ctx}/glsb/cpsb/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
            <tr>
                <td class="width-20 active"><label class="pull-right">进出编号：</label></td>
                <td class="width-30"><input name="code" class="easyui-textbox" value="${entity.code }"
                                            data-options="validType:'length[0,100]'" style="width: 100%;height: 30px;"/>
                </td>
                <td class="width-20 active"><label class="pull-right">车牌号：</label></td>
                <td class="width-30"><input name="car_code" class="easyui-textbox" value="${entity.car_code }"
                                            data-options="validType:'length[0,100]'" style="width: 100%;height: 30px;"/>
                </td>
            </tr>
            <tr>
                <td class="width-20 active"><label class="pull-right">进出时间：</label></td>
                <td class="width-30"><input style="width: 100%;height: 30px;" name="in_out_time" class="easyui-datetimebox" value="${entity.in_out_time }" data-options="editable:false,showSeconds:false" /></td>
            </tr>
				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
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