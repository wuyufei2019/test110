<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" action="${ctx}/bis/gyfkt/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <c:if test="${usertype != '1' and action eq 'create'}">
               <tr>
                  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-35">
                        <input value="${cse.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
                           data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                     </td>
               </tr>
            </c:if>
            <c:if test="${usertype != '1' and action eq 'update'}">
               <tr>
                  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-35"><input value="${cse.ID1 }" id="_qyid" name="ID1" style="width: 100%;height:30px;" class="easyui-combobox"
                        data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
               </tr>
            </c:if>


            <tr>
               <td class="width-15 active"><label class="pull-right">工艺名称：</label></td>
               <td class="width-35">
               <input name="name" class="easyui-textbox" value="${cse.name }" style="width:660px;height:30px;" data-options="required:true" /></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">简介：</label></td>
               <td class="width-35"><input type="text" name="introduction" value="${cse.introduction}" class="easyui-textbox" style="width: 660px;height: 80px;"
                     data-options="multiline:true" /></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right"><font color="red">*</font>工艺方块图：</label></td>
               <td class="width-35">
                  <div id="uploader_ws_url">
                     <div id="urlfileList" class="uploader-list"></div>
                     <div id="imagePicker">选择图片</div>
                  </div>
               </td>
            </tr>
            <c:if test="${not empty cse.ID}">
               <input type="hidden" name="ID" value="${cse.ID}" />
               <input type="hidden" name="S3" value="${cse.s3}" />
               <input type="hidden" name="ID1"value="${cse.ID1}" />
               <input type="hidden" name="ID2"value="${cse.ID2}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${cse.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
				var usertype = ${usertype};
				uploadImgFlag = false;//是否上传图片

				var $ = jQuery, $list = $('#urlfileList'); //图片上传
				var uploader = WebUploader.create({

					auto : true,

					swf : '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

					server : '${ctx}/kindeditor/upload?dir=image',

					pick : {
						id : '#imagePicker',
						multiple : false,
					},
					duplicate : true,
					accept : {
						title : 'Images',
						extensions : 'gif,jpg,jpeg,bmp,png',
						mimeTypes : 'image/jpg,image/jpeg,image/png'
					},
				    compress :{
				        width: 1200,
				        height: 1200,
				        quality: 90,
				        allowMagnify: false,
				        crop: false,
				        preserveHeaders: false,
				        noCompressIfLarger: false,
				        compressSize: 1024*50
				    }
				});
				
				uploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

				// 负责预览图的销毁
				function removeFile(fileid) {
					$("#" + fileid).remove();
					$("#input_" + fileid).remove();
					isuploadImg();
				};

				// 图片上传成功，显示预览图
				uploader.on('uploadSuccess', function(file, res) {
				$.jBox.closeTip();
					if (res.error == 0) {
						var $li = $("<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" + "<span class=\"cancel\" onClick=\"removeFile('" + file.id + "')\" style=\"cursor: pointer\">删除</span>" + "<img>" + "<div class=\"info\">" + file.name + "</div>" + "</div>"),

						$img = $li.find('img');

						$list.append($li);
						uploadImgFlag = true;
						// 创建缩略图
						uploader.makeThumb(file, function(error, src) {
							if (error) {
								$img.replaceWith('<span>不能预览</span>');
								return;
							}

							$img.attr('src', src);
						}, 100, 100);

						var newurl = res.url + "||" + res.fileName;
						var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');

						$('#uploader_ws_url').append($input);
					} else {
						layer.msg(res.message, {
							time : 3000
						});
					}
				});

				if ('${action}' == 'update') {
					var zpUrl = '${cse.url}';
					if (zpUrl != null && zpUrl != '') {
						var arry = zpUrl.split(",");
						for (var i = 0; i < arry.length; i++) {
							var arry2 = arry[i].split("||");
							var id = "ws_zp_" + i;
							var $li = $("<div id=\"" + id + "\" class=\"file-item thumbnail\">" + "<span class=\"cancel\" onClick=\"removeFile('" + id + "')\" style=\"cursor: pointer\">删除</span>" + "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" + "<div class=\"info\">" + arry2[1]
									+ "</div>" + "</div>");

							$list.append($li);

							var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
							$('#uploader_ws_url').append($input);
						}
					}
					isuploadImg();
				}

				//判断是否上传图片
				function isuploadImg() {
					var img = $("input[name='url']").val();
					if (img == null || img == "") {
						uploadImgFlag = false;
					} else {
						uploadImgFlag = true;
					}
				}

				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;

				function doSubmit() {
					if (usertype != '1') {
						var options = $("#_qyid").combobox('options');
						var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */
						var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */
						var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
						for (var i = 0; i < data.length; i++) {
							if (data[i][options.valueField] == value) {
								b = true;
								break;
							}
						}
						if (b == false) {
							layer.open({
								icon : 1,
								title : '提示',
								offset : 'auto',
								content : '所选企业不存在！',
								shade : 0,
								time : 2000
							});
							return;
						}
					}
					if (uploadImgFlag == false) {
						layer.open({
							title : '提示',
							offset : 'auto',
							content : '未上传工艺流程图，请上传！',
							shade : 0,
							time : 2000
						});
						return;
					}
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