<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>文件接收回执</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>
	<form id="inputForm" action="${ctx}/issue/wjcdjs/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                <tr>
                <td class="width-15 active"><label class="pull-right">回执内容：</label></td>
                         <td class="width-35"><input style="height: 50px;width:100%"
                              name="M4" class="easyui-textbox" value="${ife.m4 }"
                              data-options="validType:'length[0,250]',required:'true',multiline:true" /> </td>
                </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_url">
							<div id="urlfileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
                    <!--隐藏域  -->
                    <input type="hidden" id="id1" name="ID1" value="${ife.ID1 }"/>
			<tbody>
		</table>
	</form>
	

	<script type="text/javascript">
		//var usertype=${usertype};
		if('${action}'=='createSub'){
			$("#id1").val('${fid}');
		}
		var $ = jQuery, $list = $('#urlfileList'); //文件上传
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
		
		fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

		// 文件上传成功 
		fileuploader.on('uploadSuccess',
						function(file, ife) {
						$.jBox.closeTip();
							if (ife.error == 0) {
								var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
										+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
										+ ife.url
										+ "')\">"
										+ ife.fileName
										+ "</a>"
										+ "<span class=\"ss\" onClick=\"removeFile('"
										+ file.id
										+ "')\" style=\"cursor: pointer\">删除</span>"
										+ "</div>");

								$list.append($li);

								var newurl = ife.url + "||" + ife.fileName;

								var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');

								$('#uploader_ws_url').append($input);
							} else {
								layer.msg(ife.message, {
									time : 3000
								});
							}
						});

		// 负责预览图的销毁
		function removeFile(fileid) {
			$("#" + fileid).remove();
			$("#input_" + fileid).remove();
		};

		if ('${action}' == 'updateSub') {
			var fjUrl = '${ife.url}';
			if (fjUrl != null && fjUrl != '' && fjUrl != 'null') {
				var arry = fjUrl.split(",");
				for (var i = 0; i < arry.length; i++) {
					var arry2 = arry[i].split("||");
					var id = "ws_fj_" + i;
					var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
							+ arry2[0]
							+ "')\">"
							+ arry2[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeFile('"
							+ id
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					$list.append($li);

					var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
					$('#uploader_ws_url').append($input);
				}
			}
		}

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
			$("#inputForm").serializeObject();
			$("#inputForm").submit();
		}		
		
		$(function() {
			var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
			    	var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false;	// 返回false终止表单提交
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