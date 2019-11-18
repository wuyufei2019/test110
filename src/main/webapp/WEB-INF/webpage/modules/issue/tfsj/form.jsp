<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>突发事件信息</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/model/js/aqwj/tfsj/index.js"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/issue/tfsj/${action}"
		method="post" class="form-horizontal">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">标题：</label></td>
					<td class="width-85" colspan="3"><input style="width: 100%;height: 30px;"
						name="M1" class="easyui-textbox" value="${tfsj.m1 }"
						data-options="validType:'length[0,100]',required:'true'" /></td>
				<tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-35" ><input id="M3" name="M3" class="easyui-textbox" value="${tfsj.m3 }" style="height: 30px;width: 100%;" data-options="validType:'length[0,100]'" /></td>
					<td class="width-15 active"><label class="pull-right">发生时间：</label></td>
					<td class="width-35" ><input id="M4" name="M4" class="easyui-datetimebox" value="${tfsj.m4 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85" colspan="3"><textarea
							id="textarea_kindM3" name="M2"
							style="width: 100%;height:600px;visibility:hidden;">${tfsj.m2 }</textarea>
					</td>
				</tr>
				
				<!-- <tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m3">
							<div id="m3fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr> -->
				<c:if test="${not empty tfsj.ID}">
					<input type="hidden" name="ID" value="${tfsj.ID}" />
					<input type="hidden" name="ID1" value="${tfsj.ID1}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${tfsj.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="M5" value="${tfsj.m5}" />
				</c:if>
				<tbody>
		</table>

	</form>


	<script type="text/javascript">
		/* var $ = jQuery, $list2 = $('#m3fileList'); //文件上传
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
		fileuploader.on(
			'uploadSuccess',
			function(file, sfr) {
				$.jBox.closeTip();
				if (sfr.error == 0) {
					var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
							+ sfr.url
							+ "')\">"
							+ sfr.fileName
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeFile('"
							+ file.id
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");

					$list2.append($li);

					var newurl = sfr.url + "||" + sfr.fileName;

					var $input = $('<input id="input_'+file.id+'" type="hidden" name="M3" value="'+newurl+'"/>');

					$('#uploader_ws_m3').append($input);
				} else {
					layer.msg(sfr.message, {
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
			var fjUrl = '${sfr.m3}';
			if (fjUrl != null && fjUrl != ''&&fjUrl!='null') {
				arry = fjUrl.split(",");
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
					$list2.append($li);

					var $input = $('<input id="input_'+id+'" type="hidden" name="M3" value="'+arry[i]+'"/>');
					$('#uploader_ws_m3').append($input);
				}
			}
		} */

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

			//富文本渲染
			onloadEditor();

		});
	</script>
	</body>
</html>