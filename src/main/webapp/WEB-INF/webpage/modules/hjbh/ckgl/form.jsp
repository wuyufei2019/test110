<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>出库管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" action="${ctx}/hjbh/ckgl/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物名称：</label></td>
               <td class="width-30"><input name="id1" id="id1" style="width: 100%;height: 30px;" class="easyui-combobox" value="${entity.id1 }"
                     data-options="panelHeight:'100',required: true,valueField: 'id',textField: 'name',url:'${ctx}/hjbh/wxfwgl/wxfwjson'" /></td>
               <td class="width-20 active"><label class="pull-right">出库时间：</label></td>
               <td class="width-30"><input name="outtime" id="outtime" class="easyui-datetimebox" value="${entity.outtime }"
                     style="width: 100%;height: 30px;" /></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">废物数量(公斤/立方米)：</label></td>
               <td class="width-30"><input name="amount" type="text" value="${entity.amount }" class="easyui-textbox"
                     style="width: 100%;height: 30px;"></td>
               <td class="width-20 active"><label class="pull-right">废物流向：</label></td>
               <td class="width-30"><input name="direction" type="text" value="${entity.direction }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="validType:'lenght[0,50]'"></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物运送部门经办人：</label></td>
               <td class="width-30"><input name="ysoperator" type="text" value="${entity.ysoperator }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options=""></td>
               <td class="width-20 active"><label class="pull-right">废物贮存部门经办人：</label></td>
               <td class="width-30"><input name="ccoperator" type="text" value="${entity.ccoperator }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options=""></td>
            </tr>


            <c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
               <input type="hidden" name="id1" value="${entity.id1}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${entity.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
    $(function() {
    	if('${action}'=='create')
   		$("#outtime").datetimebox("setValue",new Date().format("yyyy-MM-dd HH:mm:ss"));
    });
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