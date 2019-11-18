<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标分配管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqzrs/${action}" method="post" class="form-horizontal">
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
               <td class="width-15 active"><label class="pull-right">标题：</label></td>
               <td class="width-35" colspan="3"><input name="title" type="text" value="${target.title }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="required:true,validType:'length[0,100]' "></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">年份：</label></td>
               <td class="width-35"><input name="year" id="year" value="${target.year }" class="easyui-combobox"
                     style="width: 100%;height: 30px;" data-options="required:true, valueField:'year',textField:'year',panelHeight:'auto'" /></td>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35"><input id="departments" name="departments" type="text" value="${target.departments }"
                     style="width: 100%;height: 30px;" class="easyui-combobox"
                     data-options="required:true,panelheight:200,valueField:'id',textField:'text',multiple :true,
                                    url: '${ctx}/system/department/deptjson'"></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3"><input name="note" type="text" value="${target.note }" class="easyui-textbox"
                     style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]' "></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">责任书下发：</label></td>
               <td class="width-35" colspan="3">
                  <div id="uploader_ws_url">
                     <div id="urlfileList" class="uploader-list"></div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>

            <c:if test="${action eq 'update'}">
               <input type="hidden" name="insertid"/>
               <input type="hidden" name="deleteid"/>
            </c:if>
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
					var sub=$("#inputForm").serializeObject();
					if('${action}'=='update'){
						var d1='${target.departments}'.split(",");
						var d2=$("#departments").combobox("getValues");
						$("input[name='insertid']").val(d2.minus(d1));
						$("input[name='deleteid']").val(d1.minus(d2));
					}
					$("#inputForm").submit();
				}
				$(function() {
					var flag = true;
					$('#inputForm').form({
						onSubmit : function() {
							var url=$("input[name='url']").val()
							if(!url){
								layer.msg("请上传文件！");
								return false;
							}
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