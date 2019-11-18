<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>合理化建议管理</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>

   <form id="inputForm" action="${ctx}/target/hlhjysh/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15" colspan="4" style="text-align:center;font-size:16px;"><label><b>建议内容</b></label></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">主题：</label></td>
               <td class="width-35" colspan="3"><input style="width: 100%;height: 30px;"class="easyui-textbox" value="${t.theme }"
                     data-options="readonly:true" /></td>
            <tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">内容：</label></td>
               <td class="width-35" colspan="3"><textarea id="textarea_kind" style="width:100%;height:300px;visibility:hidden;">${t.content }</textarea>
               </td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">类别：</label></td>
               <td class="width-35"><input style="width: 100%;height: 30px;" class="easyui-combobox" value="${t.type }"
                     data-options="readonly :'true'" /></td>
               <td class="width-15 active"><label class="pull-right">建言人：</label></td>
               <td class="width-35"><input value="${t.name }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="readonly:true"></td>
            <tr>
            <tr>
               <td class="width-15" colspan="4" style="text-align:center;font-size:16px;"><label><b>审核意见</b></label></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">审核意见：</label></td>
               <td class="width-35" colspan="3"><input name="opinion" value="${target.opinion }" class="easyui-textbox"
                     style="width: 100%;height: 50px;" data-options="required:true,multiline:true,validType:'length[0,250]'"></td>
            <tr>
            
            <tr>
               <td class="width-15 active"><label class="pull-right">积分：</label></td>
               <td class="width-35"><input id="integral" name="integral" value="${target.integral }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="validType:'number'"></td>
               <td class="width-15 active"><label class="pull-right">审核人：</label></td>
               <td class="width-35"><input id="name" name="name" value="${target.name }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'"></td>
            <tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">审核结果：</label></td>
               <td class="width-35"><input  name="result" value="${target.result }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"  data-options="required:'true', panelHeight:'auto', valueField: 'value',textField: 'text', 
                     data: [{'value':'1','text':'审核通过'},{'value':'0','text':'审核不通过'}]" /></td>
               <td class="width-15 active"><label class="pull-right">审核时间：</label></td>
               <td class="width-35"><input id="time" name="time" value="${target.time }" class="easyui-datebox"
                     style="width: 100%;height: 30px;" ></td>
            <tr>
            
            
               <input type="hidden" name="ID1" value="${t.ID}" />
              <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="ID2" value="${target.ID2}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         <tbody>
      </table>

   </form>
   <script type="text/javascript">
	// 初始化
	function onloadEditor() {

		var KConfigForFile = {
			uploadJson : ctx + '/kindeditor/upload.shtml',
			fileManagerJson : ctx + '/kindeditor/manager.shtml',
			allowFileManager : false
		};

		var KEditorConfig = {
			uploadJson : ctx + '/kindeditor/upload.shtml',
			fileManagerJson : ctx + '/kindeditor/manager.shtml',
			allowFileManager : false,
			filterMode : true,
			afterBlur : function() {
				this.sync();
			},
			afterChange : function() {

			},
			pasteType : 1,
			afterCreate : function() {
				var self = this;
				KindEditor.ctrl(document, 13, function() {
					self.sync();
					KindEditor('form[name=form1]')[0].submit();
				});
				KindEditor.ctrl(self.edit.doc, 13, function() {
					self.sync();
					KindEditor('form[name=form1]')[0].submit();
				});
			}
		};

		var upEditor = KindEditor.editor(KConfigForFile);

		// 渲染富文本
		window.editor = KindEditor.create('#textarea_kind', $.extend({},
				KEditorConfig, {
					width : '100%',
					items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft',
							'justifycenter', 'justifyright', '|', 'fontsize',
							'forecolor', 'hilitecolor', 'bold', 'italic', '|',
							'quickformat', '|', 'image', '|', 'link', 'fontname',
							'fullscreen' ]
				}));

	}
	
				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
					$("#inputForm").serializeObject();
					$("#inputForm").submit();
				}

				$(function() {
					onloadEditor();
					$("#name").textbox("setValue",'${username}');
					$("#time").datebox("setValue",new Date().format("yyyy-MM-dd"));
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