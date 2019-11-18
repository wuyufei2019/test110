<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全责任书上传管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqzrssc/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <shiro:hasAnyRoles name="admin,superadmin">
               <tr>
                  <td class="width-15 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-35" colspan="3"><input value="${target.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                        class="easyui-combobox"
                        data-options="required:'true',valueField: 'id',
                            <c:if test="${action eq 'update' }">readonly:'true',</c:if>
                           textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </td>
               </tr>
            </shiro:hasAnyRoles>
            <tr>
               <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-35"><input id="gwname" name="gwname" type="text" value="${target.gwname }" class="easyui-combobox"
                     style="width: 100%;height: 30px;"
                     data-options="panelHeight:150, valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson' " /></td>
                     <td class="width-15 active"><label class="pull-right">签订日期：</label></td>
               <td class="width-35"><input id="signtime" name="signtime" value="${target.signtime }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">电话：</label></td>
               <td class="width-35"><input id="phone" name="phone" type="text" value="${target.phone }" class="easyui-textbox"
                     style="width: 100%;height: 30px;"
                     data-options="validType:'mobile'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3"><input name="note" type="text" value="${target.note }" class="easyui-textbox"
                     style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]' "></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">回执上传：</label></td>
               <td class="width-35" colspan="3">
                  <div id="uploader_ws_url">
                     <div id="urlfileList" class="uploader-list"></div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>

               <input type="hidden"  id="ID1" name="ID1" value="${target.ID1}" />
               <input type="hidden"  id="ID3" name="ID3" value="${target.ID3}" />
            <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
				$(function() {
					if("${action}"=="create"){
						$("#ID1").val('${id1}');
						$("#ID3").val('${id3}');
						$("#gwname").combobox("setValue","${jobname}");
						$("#pername").textbox("setValue","${name}");
						$("#phone").textbox("setValue","${phone}");
						$("#signtime").datebox("setValue", new Date().format("yyyy-MM-dd"));
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
						multiple : false
					},
					duplicate : true
				});
				
				//上传中动画
				fileuploader.on( 'uploadProgress', function( file, percentage ) {
					$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
				});
				
				// 文件上传成功 
				fileuploader.on('uploadSuccess', function(file, target) {
					$.jBox.closeTip();//关闭正在上传的动画
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
				Array.intersect = function(arr1, arr2) {
				    if(Object.prototype.toString.call(arr1) === "[object Array]" && Object.prototype.toString.call(arr2) === "[object Array]") {
				        return arr1.filter(function(v){  
				          return arr2.indexOf(v)!==-1  
				        })  
				    }
				};
				Array.prototype.minus = function(arr) {
				    if(Object.prototype.toString.call(arr) === "[object Array]") {
				        var interArr = Array.intersect(this, arr);// 交集数组
				        return this.filter(function(v){
				            return interArr.indexOf(v) === -1
				        })
				    }
				};
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