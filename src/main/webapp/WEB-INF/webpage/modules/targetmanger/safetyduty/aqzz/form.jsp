<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责管理</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqzz/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-35" ><input id="jobid" name="jobid" class="easyui-combobox" value="${target.jobid }"
                     style="width:100%;height: 30px;"
                     data-options="required:true,panelHeight:150, valueField: 'id',textField: 'text',url:'${ctx}/bis/gzxx/textjson' " /></td>
          	   <td class="width-15 active"><label class="pull-right">编制人：</label></td>
               <td class="width-35"><input id="bzperson" name="bzperson" type="text" value="${target.bzperson }" class="easyui-combobox"
                     style="width: 100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"></td></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">岗位安全职责：</label></td>
               <td class="width-35" colspan="3"><textarea id="textarea_kindM3" name="duty" style="width:100%;height:300px;visibility:hidden;">${target.duty }</textarea>
               </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">审核人：</label></td>
               <td class="width-35"><input name="shperson" type="text" value="${target.shperson }" class="easyui-combobox"
                     style="width: 100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"></td></td>
               <td class="width-15 active"><label class="pull-right">批准人：</label></td>
               <td class="width-35"><input name="pzperson" value="${target.pzperson }" class="easyui-combobox" style="width: 100%;height: 30px;"
                      data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"></td></td>
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
            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3"><input name="note" type="text" value="${target.note }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,250]' "></td>
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
			  $(function(){
				  if('${action}'=='create')
			  	  $("#jobid").combobox("setValue",parent.parid);
			  })
				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
					$("#inputForm").serializeObject();
					$("#inputForm").submit();
				}
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
					window.editor = KindEditor.create('#textarea_kindM3', $.extend({},
							KEditorConfig, {
								width : '100%',
								items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft',
										'justifycenter', 'justifyright', '|', 'fontsize',
										'forecolor', 'hilitecolor', 'bold', 'italic', '|',
										'quickformat', '|', 'image', '|', 'link', 'fontname',
										'fullscreen' ]
							}));

				}

				$(function() {
					if('${action}'=='create'){
	   					$("#bzperson").textbox("setValue",'${username}');
	   				} 
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
				
				//文件上传
				var $ = jQuery, $list = $('#urlfileList');
				var action = '${action}';
				var fileuploader = WebUploader.create({
					auto : true,
					swf : '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
					server : '${ctx}/kindeditor/upload?dir=file',
					pick : {
						id : '#filePicker',
						multiple : false
					},
					duplicate : true
				});
				// 文件上传成功 
				fileuploader.on('uploadSuccess', function(file, target) {
					if (target.error == 0) {
						var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + target.url + "')\">" + target.fileName + "</a>"
								+ "<span class=\"ss\" onClick=\"removeFile('" + file.id + "')\" style=\"cursor: pointer\">删除</span>" + "</div>");

						$list.append($li);

						var newurl = target.url + "||" + target.fileName;
						
						var $input = $('<input id="input_'+file.id+'" type="hidden" name="fileurl" value="'+newurl+'"/>');
						console.log($input);
						$('#uploader_ws_url').append($input);
					} else {
						layer.msg(target.message, {
							time : 3000
						});
					}
				});
				
				// 销毁文件
				function removeFile(fileid) {
					$("#" + fileid).remove();
					$("#input_" + fileid).remove();
				};
				
				if (action == 'update') {
					var fjUrl = '${target.fileurl}';
					if (fjUrl != null && fjUrl != '' && fjUrl != 'null') {
						var arry = fjUrl.split(",");
						for (var i = 0; i < arry.length; i++) {
							var arry2 = arry[i].split("||");
							var id = "ws_fj_" + i;
							var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + arry2[0] + "')\">" + arry2[1] + "</a>" + "<span class=\"ss\" onClick=\"removeFile('" + id
									+ "')\" style=\"cursor: pointer\">删除</span>" + "</div>");
							$list.append($li);

							var $input = $('<input id="input_'+id+'" type="hidden" name="fileurl" value="'+arry[i]+'"/>');
							$('#uploader_ws_url').append($input);
						}
					}
				}
			</script>
</body>

</html>