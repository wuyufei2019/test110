<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>app</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>

   <form id="inputForm" action="${ctx}/system/version/${action}" method="post" class="form-horizontal">
   <input type="hidden" name="id" value="${list.id }">
   <input type="hidden" name="versionNo" value="${list.versionNo }">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">版本名称：</label></td>
               <td class="width-30"><input style="width: 100%;height: 30px;" name="name" class="easyui-textbox" value="${list.name }"
                     data-options="validType:'length[0,25]',required:'true'" /></td>
               <td class="width-20 active"><label class="pull-right">app类型：</label></td>
               <td class="width-30"><input style="width: 100%;height: 30px;" name="type" class="easyui-combobox" value="${list.type }"
                     data-options="editable:false ,panelHeight:150 , data: [
                                {value:1,text:'安监'},
                                {value:2,text:'企业'} ]" /></td>
            <tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">版本更新内容：</label></td>
               <td class="width-30" colspan="3"><input name="message" type="text" value="${list.message }" class="easyui-textbox"
                     style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,500]'"></td>

            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">apk文件：</label></td>
               <td class="width-30" colspan="3">
               <input type="hidden" id="fileurl" name="fileurl" value="${list.fileurl}" />
                  <div id="uploader_ws_fileurl">
                     <div id="fileurlfileList" class="uploader-list">
                     	<c:if test="${not empty list.fileurl}">
							<c:set var="url" value="${fn:split(list.fileurl, '||')}" />
								<div  style="margin-bottom: 10px;">
								<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
								</div>
						</c:if>
                     </div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>
         <tbody>
      </table>
   </form>


   <script type="text/javascript">
				//var usertype=${usertype};
				var $ = jQuery, $list2 = $('#fileurlfileList'); //文件上传
				var action = '${action}';

				var fileuploader = WebUploader.create({

					auto : true,

					swf : '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

					server : '${ctx}/appupload/uploadfile?dir=appapk',

					pick : {
						id : '#filePicker',
						multiple : false,
					},
					duplicate : true
				});
				
				fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

				// 文件上传成功 
				fileuploader.on('uploadSuccess', function(file, list) {
				$.jBox.closeTip();
					if (list.error == 0) {
						var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + list.url + "')\">" + list.fileName + "</a>" + "</div>");

						$list2.html($li);

						var newurl = list.url;
						$('#fileurl').val( newurl );
					} else {
						layer.msg(list.message, {
							time : 3000
						});
					}
				});


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

				});
			</script>
</body>
</html>