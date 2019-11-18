<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方评定管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/xgfpd/setting/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">评定内容：</label></td>
					<td class="width-80">
						<input name="m1"  value="${entity.m1 }"  style="width: 100%;height: 30px;" class="easyui-textbox" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">内容介绍：</label></td>
					<td class="width-80">
						<input name="m2"  value="${entity.m2 }"  style="width: 100%;height: 30px;" class="easyui-textbox" />
					</td>
				</tr>
			</table>
      <c:if test="${not empty entity.ID}">
         <input type="hidden" name="ID" value="${entity.ID}" />
         <input type="hidden" name="ID1" value="${entity.ID1}" />
         <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
         <input type="hidden" name="S3" value="${entity.s3}" />
      </c:if>
      <tbody>
       </form>
 
<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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