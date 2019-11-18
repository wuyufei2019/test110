<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>权限管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

	function doSubmit() {
		$("#inputForm").submit();
	}

	$(function() {
		$("input", $("#permCode").next("span")).blur(function() {
			var permCode = $("#permCode").textbox("getValue");
			if((permCode.charAt(permCode.length-1))!=":"){
				$("#permCode").textbox("setValue",permCode+":");
			}
			
		});
		$("input", $("#description").next("span")).blur(function() {
			var description = $("#description").textbox("getValue");
			if((description.charAt(description.length-1))!="-"){
				$("#description").textbox("setValue",description+"-");
			}
		});
		$('#inputForm').form({
			onSubmit : function() {
				var isValid = $("#inputForm").validate().form();
				return isValid; // 返回false终止表单提交
			},
			success : function(data) {
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
</head>
<body>

   <form id="inputForm" action="${ctx}/system/permission/${action}" method="post" class="form-horizontal">
      <input type="hidden" id="pid" name="pid" value="${pid }" />
      <input type="hidden" name="type" value="${type }" />
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">权限编码：</label></td>
               <td class="width-80"><input id="permCode" name="permCode" class="easyui-textbox" style="width:100%" data-options="required:true ,prompt: '省略增删改查后缀'" /></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">描述：</label></td>
               <td class="width-80"><input id="description" name="description" class="easyui-textbox" style="width:100%" data-options="required:true ,prompt: '省略增删改查后缀'" /></td>
            </tr>
         </tbody>
      </table>
   </form>

</body>
</html>