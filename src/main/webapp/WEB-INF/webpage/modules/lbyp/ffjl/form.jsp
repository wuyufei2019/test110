<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>发放记录</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/lbyp/ffjl/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
			  <c:if test="${action eq 'create' }">
				<tr>
					<td class="width-15 active"><label class="pull-right">员工姓名：</label></td>
					<td class="width-35" colspan="3"><input name="ID1" id="ID1" readonly="true" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${entity.ID1 }" /></td>
				</tr>
				</c:if>
					<tr>
					<td class="width-15 active"><label class="pull-right">领取日期：</label></td>
					<td class="width-35"><input name="time" id="time" class="easyui-datetimebox" value="${entity.time}"
						style="width: 100%;height: 30px;" data-options="editable:false,required:'true'" /></td>
					<td class="width-15 active"><label class="pull-right">领取人：</label></td>
					<td class="width-35"><input id="receiveperson" name="receiveperson" class="easyui-textbox" value="${entity.receiveperson }"
						style="width: 100%;height: 30px;"
						data-options="prompt: '不填默认员工姓名'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">用品名称：</label></td>
					<td class="width-35"><input name="goodsname" id="goodsname" readonly="true" class="easyui-textbox" value="${entity.goodsname }"
						style="width: 100%;height: 30px;" data-options="readonly: true" /></td>
					<td class="width-15 active"><label class="pull-right">用品数量：</label></td>
					<td class="width-35"><input name="amount" id="amount" readonly="true" class="easyui-textbox" value="${entity.amount }"
						style="width: 100%;height: 30px;" data-options="" /></td>
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
		if('${action}'=='create'){
			$("#ID1").textbox("setValue",parent.ename);
			$("#goodsname").textbox("setValue",parent.goodsname);
			$("#receiveperson").textbox("setValue",parent.ename);
			$("#amount").textbox("setValue",parent.amount);
			$("#time").datetimebox("setValue",new Date().format("yyyy-MM-dd hh:mm:ss"));
		}else if('${action}'=='update'){
		    $("")
		}
	})
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			$("#ID1").textbox("setValue",parent.eid);
			$("#inputForm").submit();
		}
		function exportbd() {
			$("#ID1").textbox("setValue",parent.eid);
			var obj=$("#inputForm").serializeObject();
			$.post("${ctx}/lbyp/ffjl/exportbd",obj,function(data){window.open('${ctx}/download/' + data);});
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