<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标分配管理</title>
<meta name="decorator" content="default" />
<style type="text/css">
.textred {
	color: red;
}
</style>
</head>
<body>

   <form id="inputForm" action="${ctx}/target/zbfp/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <shiro:hasAnyRoles name="admin,superadmin">
               <tr>
                  <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-80" colspan="4"><input value="${target.ID2 }" id="_qyid" name="ID2" style="width: 100%;height: 30px;"
                        class="easyui-combobox"
                        data-options="required:'true',valueField: 'id',
                            <c:if test="${action eq 'update' }">readonly:'true',</c:if>
                           textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </td>
               </tr>
            </shiro:hasAnyRoles>
            <tr>
               <td class="width-20 active"><label class="pull-right" style="width: 174px;text-align: right;">责任部门：</label></td>
               <c:choose>
				    <c:when test="${deptid != null && deptid != '' }">
				        <td class="width-30" colspan="2"><input id="ID3"
						name="ID3" type="text" value="${deptid }"
						class="easyui-combotree" style="width: 100%;height: 30px;"
						data-options="required:true,editable : false ,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/idjson'">
				    </c:when>
				    <c:otherwise>
               <td class="width-30" colspan="2"><input id="ID3"
						name="ID3" type="text" value="${target.ID3 }"
						class="easyui-combotree" style="width: 100%;height: 30px;"
						<c:if test="${action eq 'update' }"> readonly='true'</c:if>
						data-options="required:true,editable : false ,panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'">
				    </c:otherwise>
				</c:choose>
               <td class="width-20 active"><label class="pull-right" style="width: 170px;text-align: right;">级别：</label></td>
               <td class="width-30"><input name="M3" type="text" value="${target.m3 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="editable:false,required:true,panelHeight:'auto',validType:'length[0,10]',data:[{value:'1',text:'公司'},{value:'2',text:'部门'},{value:'3',text:'班组'}] "></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">年份：</label></td>
               <td class="width-30" colspan="2"><input id="M1" name="M1" value="${target.m1 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="editable: false, valueField:'year',textField:'year',panelHeight:'auto' "></td>
            </tr>
            <tr id="tr2">
               <td class="width-20 active"><label class="pull-right">目标指标：</label></td>
               <td class="width-80" colspan="4"><input id="t_id1" name="ID1" value="${target.ID1 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                    <c:if test="${action eq 'create' }"> multiple='true'</c:if> <c:if test="${action eq 'update' }"> readonly='true'</c:if> 
                    data-options="panelHeight:'120',required:true,editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/target/zbsz/idjson'"></td>
            </tr>
             <c:if test="${action eq 'update' }">
            <tr>
               	<td class="width-20 active"><label class="pull-right">目标指标值：</label></td>
               	<td class="width-30" colspan="2"><input id="targetval" name="targetval" value="${target.targetval }" 
             	   class="easyui-textbox" style="width: 100%;height: 30px;"></td>
			   	<td class="width-20 active"><label class="pull-right">预算（万元）：</label></td>
			   	<td class="width-30"><input name="M11" class="easyui-textbox" value="${target.m11 }" style="width: 100%;height: 30px;" data-options="validType:'mone'" /></td>
            </tr>
            <tr>
			   	<td class="width-20 active"><label class="pull-right">责任人：</label></td>
				<td class="width-30" colspan="2"><input id="M12" name="M12" type="text" value="${target.m12 }" class="easyui-combobox" style="width: 100%;height: 30px;" data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text'"></td>
			    <td class="width-20 active"><label class="pull-right">预计完成时间：</label></td>
				<td class="width-30"><input name="M13" value="${target.m13 }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
			</tr>
             </c:if> 
            <tr id="tr1">
               <td class="width-20 active"><label class="pull-right">制定人：</label></td>
               <td class="width-30" colspan="2"><input id="M7" name="M7" type="text" value="${target.m7 }" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,50]' "></td>
               <td class="width-20 active"><label class="pull-right">审核人：</label></td>
               <td class="width-30"><input id="M8" name="M8" value="${target.m8 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">批准人：</label></td>
               <td class="width-30" colspan="2"><input name="M9" type="text" value="${target.m9 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="panelHeight:'120',editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"></td>
               <td class="width-20 active"><label class="pull-right">批准日期：</label></td>
               <td class="width-30"><input id="M5" name="M5" value="${target.m5 }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
            </tr>


            <tr>
               <td class="width-20 active"><label class="pull-right">备注：</label></td>
               <td class="width-80" colspan="4"><input name="M6" type="text" value="${target.m6 }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,250]' "></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">安全目标责任书：</label></td>
               <td class="width-80" colspan="4">
                  <div id="uploader_ws_url">
                     <div id="urlfileList" class="uploader-list"></div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>

            <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
				var arr = 0;
   				$(function() {
					//年度下拉框数据
					var data = [];
					var thisYear;
					var startYear=new Date().getUTCFullYear()+1;
					for(var i=0;i<3;i++){
						thisYear=startYear-i;
						data.push({"year":thisYear});
					}
					if('${action}'=='create'){
					$("#M7").textbox("setValue" , '${username}');
					$("#M1").combobox("setValue" ,thisYear+1);
					}
					$("#M1").combobox("loadData", data);
					$("#M5").datebox("setValue", new Date().format("yyyy-MM-dd"));
					/* $("#M13").datebox("setValue", new Date().format("yyyy-MM-dd")); */
				
				 	$("#ID3").combobox({
						onSelect : function(rec) {
							if ($("#t_id1").combobox("getValue") != null && $("#t_id1").combobox("getValue") != '') {
								$("#t_id1").combobox("clear");
								$("#q").remove();
								arr = 0;
								$("[name=all]").remove();
							}
							$.ajax({
								type : 'get',
								url : '${ctx}/target/zbsz/idjson',
								data : { 'deptid' : rec.id,'year' : $("#M1").combobox("getValue" )},
								success : function(data) {
									var d = JSON.parse(data);
									if(d.length==0){
										layer.msg("指标已分配完毕或指标未设置");
									} 
									$('#t_id1').combobox('loadData', d);
								}
							});
						},
						
						loader : function(param, success, error) {
							var deptid = $('#ID3').combobox('getValue');
								$.ajax({
									type : 'get',
									url : '${ctx}/target/zbsz/idjson',
									data : { 'deptid' : deptid,'year' : $("#M1").combobox("getValue" )},
									success : function(data) {
										var d = JSON.parse(data);
										if(d.length==0){
											layer.msg("指标已分配完毕或指标未设置");
										} 
										$('#t_id1').combobox('loadData', d);
									}
								});	
								if ('${action}' == 'update') {
									var t1 = $('#ID3').combobox('getValue');
									var url = '${ctx}/system/compuser/userjson?bmid='+ t1;
										$.ajax({
											url : url,
											dataType : 'json',
											type : 'POST',
											contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
											success : function(data) {
												$("#M12").combobox('loadData',data);
											}
										});
								}
						}
									/* loader : function(param, success, error) {
										var deptid = $('#ID3').combobox('getValue');
										if (deptid) {
											$.ajax({
												type : 'get',
												url : '${ctx}/system/user/ujson',
												data : { 'deptid' : deptid },
												success : function(data) {
													var d = JSON.parse(data);
													if(d.length==0){
														layer.msg("指标已分配完毕");
													} 
													$('#M8').combobox('loadData', d);
												}
											});
										}
									} */
									});
				});
				$("#t_id1").combobox({
									onSelect : function(rec) {
										if (!$("#ID3").combobox("getValue") || !$("#M1").combobox("getValue")) {
											layer.msg("请先选择部门、年份!");
											$(this).combobox("clear");
											return;
										}else{
											arr++;
										}
										var html2 = "<tr id='q'>"
												+ "<td class='width-20 active'><label class='pull-right textred'>指标名称：</label></td>"
												+ "<td class='width-15 active'><label class='pull-right textred'>指标值：</label></td>"
												+ "<td class='width-15 active'><label class='pull-right textred' data-options='required:true'>预算（万元）：</label></td>"
												+ "<td class='width-20 active'><label class='pull-right textred' data-options='required:true'>责任人：</label></td>"
												+ "<td class='width-30 active'><label class='pull-right textred' data-options='required:true'>预计完成时间：</label></td>"
												+ "</tr>";
										if (arr == 1) {
											$("#tr2").after(html2);
										}
										var bmid = $("#ID3").combobox("getValue");
										var html = "<tr id="+rec.id+" name='all'>"
												+ "<td class='width-20'><a class='fa fa-minus-square btn-danger btn-outline' onclick='remove("
												+ rec.id
												+ ")'></a> <input class='easyui-textbox' style='width: 145px; height: 30px;' value='"+rec.text+"' data-options='readonly : true' /></td>"
												+ "<td class='width-15'><input name='targetval' class='easyui-textbox' value='"+rec.val+"' style='width: 120px;height: 30px;'data-options='required: true' /></td>"
												+ "<td class='width-15'><input name='M11' class='easyui-textbox' value='${target.m11 }' style='width: 120px;height: 30px;'data-options=\"validType:'mone',required:true\" /></td>"
												+ "<td class='width-20'><input id='M12' name='M12' type='text' value='${target.m12 }' class='easyui-combobox' style='width: 100%;height: 30px;' data-options=\"panelHeight:'120',editable:true ,required:true,valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson?bmid="+bmid+"'\"></td>"
												+ "<td class='width-20'><input name='M13' value='${target.m13 }' class='easyui-datebox' style='width: 100%;height: 30px;'data-options='required:true'/></td>"
												+ "</tr>";
										/* "<tr id="+rec.id+">"+
										"<td class='width-15 active'><label class='pull-right textred'><a class='fa fa-minus-square btn-danger btn-outline' onclick='remove("+rec.id+")'></a> 指标名称：</label></td>"+
										"<td class='width-35'><input class='easyui-textbox' style='width: 100%;height: 30px;' value='"+rec.text+"' data-options='readonly : true' /></td>"+
										"<td class='width-15 active'><label class='pull-right textred'>指标值：</label></td>"+
										"<td class='width-35'><input name='targetval' class='easyui-textbox' value='"+rec.val+"' style='width: 100%;height: 30px;'data-options='required: true' /></td>"+
										"</tr>"; */
										$("#tr1").before(html);
										$.parser.parse("#" + rec.id);
									},
									onUnselect : function(rec) {
										arr--;
										$("#" + rec.id).remove();
										if (arr == 0) {
											$("#q").remove();
										}
									}
								});
				function remove(id) {
					$("#" + id).remove();
					if (arr == 0) {
						$("#q").remove();
					}
					$('#t_id1').combobox('unselect', id);
				}
				$("#_qyid").combobox({
					onSelect : function(rec) {
						$.ajax({
							type : 'get',
							url : '${ctx}/target/zbsz/idjson',
							data : {
								'qyid' : rec.id
							},
							success : function(data) {
								var d = JSON.parse(data);
								$('#id1').combobox('loadData', d);
							}
						});
					},
					loader : function(param, success, error) {
						var qyid = $('#_qyid').combobox('getValue');
						if (qyid) {
							$.ajax({
								type : 'get',
								url : '${ctx}/target/zbsz/idjson',
								data : {
									'qyid' : qyid
								},
								success : function(data) {
									var d = JSON.parse(data);
									$('#id1').combobox('loadData', d);
								}
							});
						}
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
				fileuploader.on('uploadProgress', function(file, percentage) {
					$.jBox.tip("正在上传，请稍等...", 'loading', {
						opacity : 0
					});
				});
				// 文件上传成功 
				fileuploader
						.on(
								'uploadSuccess',
								function(file, target) {
									$.jBox.closeTip();
									if (target.error == 0) {
										var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
												+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
												+ target.url
												+ "')\">"
												+ target.fileName
												+ "</a>"
												+ "<span class=\"ss\" onClick=\"removeFile('"
												+ file.id
												+ "')\" style=\"cursor: pointer\">删除</span>"
												+ "</div>");

										$list2.append($li);

										var newurl = target.url + "||"
												+ target.fileName;

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