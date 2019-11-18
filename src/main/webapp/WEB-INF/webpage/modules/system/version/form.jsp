<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>app</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
   <script type="text/javascript">
       //初始化
       function onloadEditor(){

           var KConfigForFile = {
               uploadJson : ctx + '/kindeditor/upload.shtml',
               fileManagerJson : ctx + '/kindeditor/manager.shtml',
               allowFileManager : false
           };

           var KEditorConfig= {
               uploadJson : ctx + '/kindeditor/uploadFile.shtml',
               fileManagerJson : ctx + '/kindeditor/manager.shtml',
               allowFileManager : false,
               filterMode: true,
               afterBlur: function(){this.sync();},
               afterChange : function() {

               },
               pasteType: 1,
               afterCreate: function () {
                   var self = this;
                   KindEditor.ctrl(document, 13, function () {
                       self.sync();
                       KindEditor('form[name=form1]')[0].submit();
                   });
                   KindEditor.ctrl(self.edit.doc, 13, function () {
                       self.sync();
                       KindEditor('form[name=form1]')[0].submit();
                   });
               }
           };

           var upEditor = KindEditor.editor(KConfigForFile);

           //渲染富文本
           window.editor = KindEditor.create(
               '#textarea_kindmessage',
               $.extend({},KEditorConfig,{
                   width: '500',
                   items:[ 'source', '|', 'undo', 'redo', '|', 'justifyleft','justifycenter','justifyright','|',
                       'fontsize','forecolor','hilitecolor','bold','italic','|','quickformat','|','image','|','link','fontname','fullscreen'
                   ]
               })
           );

       }
   </script>
</head>
<body>

   <form id="inputForm" action="${ctx}/system/xmversion/${action}" method="post" class="form-horizontal">
   <input type="hidden" name="id" value="${infor.id }">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-30 active"><label class="pull-right">版本名称：</label></td>
               <td class="width-70"><input style="width: 100%;height: 30px;" name="name" class="easyui-textbox" value="${infor.name }"
                     data-options="validType:'length[0,25]',required:'true'" /></td>
            <tr>
            <tr>
               <td class="width-30 active"><label class="pull-right">更新日期：</label></td>
               <td class="width-70"><input style="width: 100%;height: 30px;" name="updatetime" class="easyui-datebox" value="${infor.updatetime }"
                                           data-options="editable:false" /></td>
            <tr>
            <tr>
               <td class="width-30 active"><label class="pull-right">版本更新内容：</label></td>
               <td class="width-70">
                  <textarea id="textarea_kindmessage" name="message" style="width:100%;height:300px;visibility:hidden;">${infor.message }</textarea>

            </tr>

         <tbody>
      </table>
   </form>


   <script type="text/javascript">
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;

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




                    //富文本渲染
                    onloadEditor();
				});
			</script>
</body>
</html>