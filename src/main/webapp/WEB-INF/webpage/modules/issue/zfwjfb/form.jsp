<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>政府文件互发</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/model/js/aqwj/zfwjfb/index.js?v=1.1"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/issue/zfwjfb/${action}" method="post" class="form-horizontal">
		<c:if test="${not empty sfr.ID}">
		<input type='hidden'  name="ID" value="${sfr.ID}" />
		<input type="hidden" name="ID1" value="${sfr.ID1}" />
		<input type="hidden" name="S1" value="<fmt:formatDate value="${sfr.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
		<input type="hidden" name="S3" value="${sfr.s3}" />
		</c:if>
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">标题：</label></td>
					<td class="width-30"><input style="width: 700px;height: 30px;"
						name="M1" class="easyui-textbox" value="${sfr.m1 }"
						data-options="validType:'length[0,250]',required:'true'" /> </td>
				<tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">文件类型：</label></td>
					<td class="width-30"><input style="width: 200px;height: 30px;"
						name="M5" class="easyui-combobox" value="${sfr.m5 }"
						data-options="required:'true',editable:false ,panelHeight:150 , data: [
								        {value:'总局文件',text:'总局文件'},
								        {value:'省局文件',text:'省局文件'},
								        {value:'市局文件',text:'市局文件'},
								        {value:'区县局发文',text:'区县局发文'},
								        {value:'本级发文',text:'本级发文'},
								        {value:'其他文件',text:'其他文件'} ]" />  </td>
				<tr>
				<tr style="display: none;">
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85" colspan="3"><textarea
							id="textarea_kindM3" name="M2"
							style="width:100%;height:300px;visibility:hidden;">${sfr.m2 }</textarea>
					</td>
				</tr>


				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3"><input name="M4" type="text"
						value="${sfr.m4 }" class="easyui-textbox"
						style="width: 700px;height: 80px;"
						data-options="multiline:true , validType:'length[0,250]'">
					</td>

				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m3">
							<div id="m3fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">所选政府人员：</label></td>
					<td class="width-35" colspan="3">
						<!-- 负责传值 -->
						<div id="qyIDs">
							<input id="zfids" type="hidden" name="zfids" />
							<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openzflist('${action}','${sfr.ID}')" title="选择工作人员"><i class="fa fa-plus"></i> 选择工作人员</a><span id="tip" style="color:red">（如果不选择，则默认发送给所有工作人员）</span>
							<div id="qyList"></div>
						</div>
					</td>
				</tr>
			<tbody>
		</table>
	</form>
	

	<script type="text/javascript">
		//var usertype=${usertype};
		var $ = jQuery, $list2 = $('#m3fileList'); //文件上传
		var action = $("[name=action]").val();

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
		fileuploader
				.on(
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
			$("#tip").hide();
			var fjUrl = '${sfr.m3}';
			var $list = $("#qyList");
			//企业id||企业name
			var ids = '${idname}';
			var qyids="";
			if (ids != null && ids != '' && ids != 'null') {
				var arry3 = ids.split(",");
				for (var i = 0; i < arry3.length; i++) {
					var arry4 = arry3[i].split("||");
					var $li = $("<div id=\"" +arry4[0]+ "\" style=\"margin-bottom: 10px;\">"
							+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\">"
							+ arry4[1]
							+ "</a>"
							+ "<span class=\"ss\" onClick=\"removeQy('"
							+ arry4[0]
							+ "')\" style=\"cursor: pointer\">删除</span>"
							+ "</div>");
					qyids += arry4[0] + ",";
					$list.append($li);
				}
			}
			$("#zfids").val(qyids);

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
					$list2.append($li);

					var $input = $('<input id="input_'+id+'" type="hidden" name="M3" value="'+arry[i]+'"/>');
					$('#uploader_ws_m3').append($input);
				}
			}
		}

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
		var divtxt=$("#qyList").text();
		if(divtxt==null||divtxt==''){
			top.layer.confirm('您还没有选择任何政府人员，这将会将信息发送给所有政府人员!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				$("#inputForm").serializeObject();
				$("#inputForm").submit();
				top.layer.close(index);
			});
		}else{
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
		}

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