<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全会议管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqhy/${action}" method="post" class="form-horizontal">
      <table readonly="true" class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active" align="center" colspan="4">会议信息</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">会议主题:</label></td>
               <td class="width-35" colspan="3"><input id="theme" name="theme" class="easyui-textbox" value="${target.theme }"
                     style="width:100%;height: 30px;" data-options="required:true,validType:'length[0,25]'" /></td>

            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">会议开始时间:</label></td>
               <td class="width-35"><input id="time" name="time" class="easyui-datetimebox" value="${target.time }"
                     style="width:100%;height: 30px;"></td>
               <td class="width-15 active"><label class="pull-right">状态:</label></td>
               <td class="width-35"><input id="state" name="state" class="easyui-combobox" value="${target.state }"
                     style="width:100%;height: 30px;"
                     data-options="panelHeight: 'auto',editable: false, valueField:'value',textField:'text',
                   data:[{value:1,text:'待开'},{value:2,text:'推迟'},{value:3,text:'结束'},]" />
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">召集部门:</label></td>
               <td class="width-35"><input id="ID2" name="ID2" type="text" value="${target.ID2 }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="required:true,panelHeight:100,valueField:'id',textField:'text',
                                    url: '${ctx}/system/department/deptjson'" /></td>

               <td class="width-15 active"><label class="pull-right">主持人:</label></td>
               <td class="width-35"><input id="hostess" name="hostess" class="easyui-textbox" value="${target.hostess }"
                     style="width:100%;height: 30px;"></td>

            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">参加部门:</label></td>
               <td class="width-35"><input id="attenddeps" name="attenddeps" type="text" value="${target.attenddeps }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="panelHeight:100, valueField:'id',textField:'text',multiple:true,
                                    url: '${ctx}/system/department/deptjson'"></td>
               <td class="width-15 active"><label class="pull-right">会议类型:</label></td>
               <td class="width-35"><input name="type" class="easyui-combobox" value="${target.type }" style="width:100%;height: 30px;"
                     data-options="editable: false,panelHeight:'auto', valueField:'value',textField:'text',
                   data:[{value:1,text:'公司级'},{value:2,text:'部门级'},{value:3,text:'临时会议'},{value:4,text:'其他会议'}]" />
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">地点:</label></td>
               <td class="width-35" colspan="3"><input name="address" type="text" value="${target.address }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="required:true" /></td>
            </tr>

         </tbody>
      </table>
      <c:if test="${!(action eq 'update' and (target.state eq '1' or target.state eq '2') and flg ne 'addcont') }">
         <table id="table" class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
            <tbody>
               <tr>
                  <td class="width-15 active" align="center" colspan="4">会议记录</td>
               </tr>
               <tr>
                  <td class="width-15 active"><label class="pull-right">会议记录:</label></td>
                  <td class="width-85"><input id="content" name="content" type="text" value="${target.content }" class="easyui-textbox"
                        style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,2500]'" /></td>
               </tr>
               <tr>
                  <td class="width-15 active"><label class="pull-right">会议督办事项:</label></td>
                  <td class="width-85"><input id="note" name="note" type="text" value="${target.note }" class="easyui-textbox"
                        style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,1000]'" /></td>
               </tr>
               <tr>
                  <td class="width-15 active"><label class="pull-right">会议文件:</label></td>
                  <td class="width-85">
                     <div id="uploader_ws_file">
                        <div id="fileList" class="uploader-list"></div>
                        <div id="filePicker">选择文件</div>
                     </div>
                  </td>
               </tr>
               <tr>
                  <td class="width-15 active"><label class="pull-right">记录人:</label></td>
                  <td class="width-35"><input id="recordper" name="recordper" type="text" value="${target.recordper }" class="easyui-textbox"
                        style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" /></td>

               </tr>
               <input type="hidden" name="flg" value="${flg}" />
            </tbody>
         </table>
      </c:if>
      <c:if test="${target.state eq '3' and not empty target.feedback}">
         <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
            <tr>
               <td class="width-15 active" align="center" colspan="4">会议督办事项反馈</td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">督办事项反馈:</label></td>
               <td class="width-85"><input id="feedback" name="feedback" type="text" class="easyui-textbox" style="width: 100%;height: 50px;"
                     value="${target.feedback }" data-options="required: true,multiline:true,validType:'length[0,1000]'" /></td>
            </tr>
         </table>
      </c:if>
      <c:if test="${not empty target.ID}">
         <input type="hidden" name="ID" value="${target.ID}" />
         <input type="hidden" name="ID1" value="${target.ID1}" />
         <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
         <input type="hidden" name="S3" value="${target.s3}" />
      </c:if>
      <c:if test="${not empty target.delayreason }">
         <input type="hidden" name="delayreason" value="${target.delayreason}" />
      </c:if>
   </form>

   <script type="text/javascript">
				$(function() {
					if ("${action}" == "create") {
						$("#time").datetimebox("setValue", new Date().format("yyyy-MM-dd hh:00:00"));
						$("#ID2").combobox("setValue", '${id2}');
					}
					if ('${target.state}' == '2') {
						$("#state").combobox("setValue", 3);
					}
					if (!'${target.hostess}')
						$("#hostess").textbox("setValue", '${username}');
					$("#recordper").textbox("setValue", '${username}');
				});
				$('#time').datetimebox({
					onChange : function(date) {
						var now = new Date().format("yyyy-MM-dd hh:mm:ss");
						if (date > now) {
							$("#state").combobox("setValue", 1);
						} else {
							$("#state").combobox("setValue", 3);
						}
					}
				});
				$('#state').combobox({
					onChange : function(data) {
						if (data == 1) {
							$("#table").hide();
						} else if (data == 3) {
							$("#table").show();
						}
					}
				});
				//文件上传
				var $ = jQuery;
				$list2 = $('#fileList');//附件上传
				//附件
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

				// 负责预览图的销毁
				function removeFile(fileid) {
					$("#" + fileid).remove();
					$("#input_" + fileid).remove();
				};

				// 文件上传成功 
				fileuploader.on('uploadSuccess', function(file, cre) {
					if (cre.error == 0) {
						var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + cre.url + "')\">" + cre.fileName + "</a>" + "<span class=\"ss\" onClick=\"removeFile('"
								+ file.id + "')\" style=\"cursor: pointer\">删除</span>" + "</div>");
						$list2.append($li);
						var newurl = cre.url + "||" + cre.fileName;
						var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
						$('#uploader_ws_file').append($input);
					} else {
						layer.msg(cre.message, {
							time : 3000
						});
					}
					$.jBox.closeTip();
				});

				var fjUrl = '${target.url}';
				if (fjUrl != null && fjUrl != '') {
					arry = fjUrl.split(",");
					for (var i = 0; i < arry.length; i++) {
						var arry2 = arry[i].split("||");
						var id = "ws_fj_" + i;
						var $li = $("<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('" + arry2[0] + "')\">" + arry2[1] + "</a>" + "<span class=\"ss\" onClick=\"removeFile('" + id
								+ "')\" style=\"cursor: pointer\">删除</span>" + "</div>");
						$list2.append($li);

						var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
						$('#uploader_ws_file').append($input);
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