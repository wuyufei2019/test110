<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
   <title>应急处置卡</title>
   <meta name="decorator" content="default" />
   <%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
   <script type="text/javascript" src="${ctx}/static/model/js/fxgk/yjczk/index.js?v=1.0"></script>
</head>
<body>
   <form id="inputForm" action="${ctx}/fxgk/yjczk/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-30"colspan="3"><input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-textbox" value="${entity.m1 }" data-options="required: true,validType:'lenght[0,50]'" /></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">生产安全事故处置程序及职责：</label></td>
               <td class="width-30" colspan="3" style="height: 100px;"><textarea id="textarea_kindM2" name="M2" style="width:100%;height:300px;visibility:hidden;">${entity.m2 }</textarea></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">注意事项：</label></td>
               <td class="width-30" colspan="3" style="height: 100px;"><textarea id="textarea_kindM3" name="M3" style="width:100%;height:300px;visibility:hidden;">${entity.m3 }</textarea></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">本企业救援队联系方式：</label></td>
               <td class="width-30"><input name="M4" type="text" value="${entity.m4 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>

               <td class="width-20 active"><label class="pull-right">应急负责人联系方式：</label></td>
               <td class="width-30"><input name="M5" type="text" value="${entity.m5 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">控制室联系方式：</label></td>
               <td class="width-30"><input name="M6" type="text" value="${entity.m6 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>

               <td class="width-20 active"><label class="pull-right">工厂总经理联系方式：</label></td>
               <td class="width-30"><input name="M7" type="text" value="${entity.m7 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">班长联系方式：</label></td>
               <td class="width-30"><input name="M8" type="text" value="${entity.m8 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>

               <td class="width-20 active"><label class="pull-right">当地应急响应中心联系方式：</label></td>
               <td class="width-30"><input name="M9" type="text" value="${entity.m9 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">当地安监部门联系方式：</label></td>
               <td class="width-30"><input name="M10" type="text" value="${entity.m10 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>

               <td class="width-20 active"><label class="pull-right">当地环保部门联系方式：</label></td>
               <td class="width-30"><input name="M11" type="text" value="${entity.m11 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">社会救援队联系方式：</label></td>
               <td class="width-30"><input name="M12" type="text" value="${entity.m12 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>

               <td class="width-20 active"><label class="pull-right">友邻单位名称：</label></td>
               <td class="width-30"><input name="M13" type="text" value="${entity.m13 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">友邻单位联系方式：</label></td>
               <td class="width-30"><input name="M14" type="text" value="${entity.m14 }" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'lenght[0,25]'"></td>
            </tr>

            <c:if test="${not empty entity.ID}">
               <input type="hidden" name="ID" value="${entity.ID}" />
               <input type="hidden" name="qyid" value="${entity.qyid}" />
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

       //富文本渲染
       onloadEditor();

	});
</script>

</body>
</html>