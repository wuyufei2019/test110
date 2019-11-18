<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqcns/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35"><input id="year"name="year" class="easyui-combobox" value="${target.year }"
                     style="width:100%;height: 30px;"
                     data-options="required:true,panelHeight:100, valueField: 'year',textField: 'year' " /></td>
               <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-35" ><input name="post" class="easyui-combobox" value="${target.post }"
                     style="width:100%;height: 30px;"
                     data-options="required:true,panelHeight:100, valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson' " /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">姓名：</label></td>
               <td class="width-35"><input id="pername" name="pername" type="text" value="${target.pername }" class="easyui-textbox"
                     style="width: 100%;height: 30px;"></td>
               <td class="width-15 active"><label class="pull-right">电话：</label></td>
               <td class="width-35"><input id="phone" name="phone" type="text" value="${target.phone }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="validType:'mobile'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">承诺书上传：</label></td>
               <td class="width-35"colspan="3">
                  <div id="uploader_ws_url">
                     <div id="urlfileList" class="uploader-list"></div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>

            <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="ID1" value="${target.ID1}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
   $(function() {
		//年度下拉框数据
		var data = [];
		var thisYear;
		var startYear = new Date().getUTCFullYear() + 1;
		for (var i = 0; i < 5; i++) {
			thisYear = startYear - i;
			data.push({
				"year" : thisYear
			});
		}
		$("#year").combobox("loadData", data);
		if("${action}"=="create"){
			$("#pername").textbox("setValue","${username}");
			$("#phone").textbox("setValue","${phone}");
		}
	});
	//文件上传
	var $ = jQuery, $list2 = $('#urlfileList');
	var action = '${action}';
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
	fileuploader.on('uploadSuccess', function(file, target) {
		if (target.error == 0) {
			var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + target.url + "')\">" + target.fileName + "</a>"
					+ "<span class=\"ss\" onClick=\"removeFile('" + file.id + "')\" style=\"cursor: pointer\">删除</span>" + "</div>");

			$list2.append($li);

			var newurl = target.url + "||" + target.fileName;

			var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');

			$('#uploader_ws_url').append($input);
		} else {
			layer.msg(target.message, {
				time : 3000
			});
		}
		$.jBox.closeTip();
	});

	// 负责预览图的销毁
	function removeFile(fileid) {
		$("#" + fileid).remove();
		$("#input_" + fileid).remove();
	};

	if (action == 'update') {
		var fjUrl = '${target.url}';
		if (fjUrl != null && fjUrl != '' && fjUrl != 'null') {
			var arry = fjUrl.split(",");
			for (var i = 0; i < arry.length; i++) {
				var arry2 = arry[i].split("||");
				var id = "ws_fj_" + i;
				var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + arry2[0] + "')\">" + arry2[1] + "</a>" + "<span class=\"ss\" onClick=\"removeFile('" + id
						+ "')\" style=\"cursor: pointer\">删除</span>" + "</div>");
				$list2.append($li);

				var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
				$('#uploader_ws_url').append($input);
			}
		}
	}
				//提交处理
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