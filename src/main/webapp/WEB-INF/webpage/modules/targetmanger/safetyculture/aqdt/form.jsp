<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全法律信息</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript" src="${ctx}/static/model/js/aqwj/aqwjfb/index.js"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/target/aqdt/${action}" method="post" class="form-horizontal">
		<c:if test="${not empty entity.ID}">
			<input type='hidden' name="ID" value="${entity.ID}" />
			<input type="hidden" name="ID1" value="${entity.ID1}" />
			<input type="hidden" name="ID2" value="${entity.ID2}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
			<input type="hidden" name="S3" value="${entity.s3}" />
		</c:if>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全动态主题：</label></td>
					<td class="width-85"><input style="width: 100%;height: 30px;" name="M1" class="easyui-textbox"
						value="${entity.m1 }" data-options="validType:'length[0,250]',required:'true'" /></td>
				<tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">动态分类：</label></td>
					<td class="width-85"><input style="width: 100%;height: 30px;" name="M5" class="easyui-combobox"
						value="${entity.m5 }"
						data-options="required:'true',editable:false ,panelHeight:150 , data: [
								        {value:'公司新闻',text:'公司新闻'},
								        {value:'公告通知',text:'公告通知'},
								        {value:'其他新闻',text:'其他新闻'}]" />
					</td>
				<tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85"><textarea id="textarea_kindM2" name="M2"
							style="width:100%;height:350px;visibility:hidden;">${entity.m2 }</textarea></td>
				</tr>


				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85"><input name="M4" type="text" value="${entity.m4 }" class="easyui-textbox"
						style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'"></td>

				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-85">
						<div id="uploader_ws_m3">
							<div id="m3fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
			<tbody>
		</table>
	</form>


	<script type="text/javascript">
		//var usertype=${usertype};
		var $ = jQuery, $list2 = $('#m3fileList'); //文件上传

		var fileuploader = WebUploader.create({

			auto : true,

			swf : '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

			server : '${ctx}/kindeditor/upload?dir=file',

			pick : {
				id : '#filePicker',
				multiple : false,
			},
			duplicate : true
		});

		fileuploader.on('uploadProgress', function(file, percentage) {
			$.jBox.tip("正在上传，请稍等...", 'loading', {
				opacity : 0
			});
		});

		// 文件上传成功 
		fileuploader.on('uploadSuccess',function(file, entity) {
			$.jBox.closeTip();
			if (entity.error == 0) {
				var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
						+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:15px;\" onclick=\"window.open('"
						+ entity.url
						+ "')\">"
						+ entity.fileName
						+ "</a>"
						+ "<span class=\"ss\" onClick=\"removeFile('"
						+ file.id
						+ "')\" style=\"cursor: pointer\">删除</span>"
						+ "</div>");
	
				$list2.append($li);
	
				var newurl = entity.url + "||" + entity.fileName;
	
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M3" value="'+newurl+'"/>');
	
				$('#uploader_ws_m3').append($input);
			} else {
				layer.msg(entity.message);
			}});

		// 负责预览图的销毁
		function removeFile(fileid) {
			$("#" + fileid).remove();
			$("#input_" + fileid).remove();
		};

		if ('${action}' == 'updateSub') {
			var fjUrl = '${entity.m3}';
			arry =fjUrl.split(",");
			if (fjUrl&&fjUrl!='null') {
				var arry = fjUrl.split(",");
				for (var i = 0; i < arry.length; i++) {
					var arry2 = arry[i].split("||");
					var id = "ws_fj_" + i;
					var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:15px;\" onclick=\"window.open('"
							+ arry2[0]
							+ "')\">"
							+ arry2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeFile('"
							+ id
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list2.append($li);

					var $input = $('<input id="input_'+id+'" type="hidden" name="M3" value="'+arry[i]+'"/>');
					$('#uploader_ws_m3').append($input);
				}
			}
		}
		
		// 初始化
		function onloadEditor() {

			var KConfigForFile = {
				uploadJson : ctx + '/aqpxkindeditor/uploadFile.shtml',
				fileManagerJson : ctx + '/kindeditor/manager.shtml',
				allowFileManager : false
			};

			var KEditorConfig = {
				uploadJson : ctx + '/kindeditor/uploadFile.shtml',
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
			window.editor = KindEditor.create('#textarea_kindM2', $.extend({},
					KEditorConfig, {
						width : '100%',
						items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft',
								'justifycenter', 'justifyright', '|', 'fontsize',
								'forecolor', 'hilitecolor', 'bold', 'italic', '|',
								'quickformat', '|', 'image', '|', 'link', 'fontname',
								'fullscreen' ]
					}));

		}

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
							time : 1500
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 1500
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