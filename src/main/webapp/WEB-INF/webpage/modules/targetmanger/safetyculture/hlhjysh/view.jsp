<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>合理化建议管理</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>

   <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
      <tbody>
         <tr>
            <td class="width-15" colspan="4" style="text-align:center;font-size:16px;"><label>
                  <b>建议内容</b>
               </label></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">主题：</label></td>
            <td class="width-35" colspan="3">${t.theme }</td>
         <tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">内容：</label></td>
            <td class="width-35" colspan="3"><textarea id="textarea_kind" style="width:100%;height:300px;visibility:hidden;">${t.content }</textarea>
            </td>
         </tr>

         <tr>
            <td class="width-15 active"><label class="pull-right">类别：</label></td>
            <td class="width-35">${t.type }</td>
            <td class="width-15 active"><label class="pull-right">建言人：</label></td>
            <td class="width-35">${t.name }</td>
         <tr>
         <tr>
            <td class="width-15" colspan="4" style="text-align:center;font-size:16px;"><label>
                  <b>审核意见</b>
               </label></td>
         </tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">审核意见：</label></td>
            <td class="width-35" colspan="3">${target.opinion }</td>
         <tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">积分：</label></td>
            <td class="width-35">${target.integral }</td>
            <td class="width-15 active"><label class="pull-right">审核人：</label></td>
            <td class="width-35">${target.name }</td>
         <tr>
         <tr>
            <td class="width-15 active"><label class="pull-right">审核结果：</label></td>
            <td class="width-35"><c:choose>
                  <c:when test="${target.result  eq '1'}">审核通过</c:when>
                  <c:when test="${target.result  eq '0'}">审核不通过</c:when>
               </c:choose></td>
            <td class="width-15 active"><label class="pull-right">审核时间：</label></td>
            <td class="width-35"><fmt:formatDate value="${target.time }" pattern="yyyy-MM-dd" /></td>
         <tr>

      <tbody>
   </table>

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
					window.editor = KindEditor.create('#textarea_kind', $.extend({}, KEditorConfig, {
						width : '100%',
						items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'fontsize', 'forecolor', 'hilitecolor', 'bold', 'italic', '|', 'quickformat', '|', 'image', '|', 'link', 'fontname', 'fullscreen' ]
					}));

				}

				$(function() {
					onloadEditor();
				});
			</script>
</body>

</html>