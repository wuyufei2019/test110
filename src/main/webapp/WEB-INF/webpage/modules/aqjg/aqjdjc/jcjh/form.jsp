<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全法律信息</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/model/js/aqjg/aqjdjc/jcjh/index.js?v=1.1"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/aqjd/jcjh/${action}"
		method="post" class="form-horizontal">
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active"><label  class="pull-right">专项检查名称：</label></td>
					<td class="width-30"><input style="height: 30px;width:650px;"
						name="M1" class="easyui-textbox" value="${cpe.m1 }"
						data-options="validType:'length[0,250]',required:'true'" />  <input type='hidden' name="S1" value="<fmt:formatDate value="${cpe.s1 }"/>" /> 
						<input type='hidden' name="ID" value="${cpe.ID}" />
						</td>
				<tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">计划时间：</label></td>
					<td class="width-30" ><input id="M2"name="M2" style="height: 30px;" type="text" class="easyui-datebox"  required="required"
						value="${cpe.m2 }" >
					</td>

				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">工作清单：</label></td>
					<td class="width-30" colspan="3"><textarea
							id="textarea_kindM3" name="M3"
							style="height:300px;width:100%;visibility:hidden;">${cpe.m3 }</textarea>
					</td>
				</tr>


				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3"><input name="M4" type="text"
						value="${cpe.m4 }" class="easyui-textbox"
						style="height: 80px; width:650px;"
						data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">检查表附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m5">
							<div id="m5fileList" class="uploader-list"></div>
							<div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">所选企业：</label></td>
					<td class="width-30" colspan="3">
						<!-- 负责传值 -->
						<div id="qyIDs">
							<input id="qyids" type="hidden" name="qyids" />
							<a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openqylist()" title="选择企业"><i class="fa fa-plus"></i> 选择企业</a><span id="tip" style="color:red">（如果不选择，则默认发送给所有企业）</span>
							<div id="qyList"></div>
						</div>
					</td>
				</tr>
			<tbody>
		</table>
	</form>


	<script type="text/javascript">
		//var usertype=${usertype};
		var $ = jQuery, $list2 = $('#m5fileList'); //文件上传
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
						function(file, cpe) {
							$.jBox.closeTip();
							if (cpe.error == 0) {
								var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
										+ "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
										+ cpe.url
										+ "')\">"
										+ cpe.fileName
										+ "</a>"
										+ "<span class=\"ss\" onClick=\"removeFile('"
										+ file.id
										+ "')\" style=\"cursor: pointer\">删除</span>"
										+ "</div>");

								$list2.append($li);

								var newurl = cpe.url + "||" + cpe.fileName;

								var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');

								$('#uploader_ws_m5').append($input);
							} else {
								layer.msg(cpe.message, {
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
			var fjUrl = '${cpe.m5}';
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
			$("#qyids").val(qyids);

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

					var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
					$('#uploader_ws_m5').append($input);
				}
			}
		}

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
		var divtxt=$("#qyList").text();
		 var divtxtfj=$("#m5fileList").text();
		if(divtxtfj==null||divtxtfj==''){
			top.layer.confirm('您还没有上传附件，请上传!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				top.layer.close(index);
			});
		}
		else  if(divtxt==null||divtxt==''){
			top.layer.confirm('您还没有选择任何企业，这将会将信息发送给所有企业!', {
				icon : 3,
				title : '提示'
			}, function(index) {
				$("#inputForm").serializeObject();
				$("#inputForm").submit();
				top.layer.close(index);
			});
		}
		else {
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

			//日期控件只显示年月
           var M2 = $('#M2');
            M2.datebox({
                onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                    span.trigger('click'); //触发click事件弹出月份层
                    if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
                    if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                        tds = p.find('div.calendar-menu-month-inner td');
                        tds.click(function (e) {
                            e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                            var year = /\d{4}/.exec(span.html())[0]//得到年份
                            , month = parseInt($(this).attr('abbr'), 10); //
                            M2.datebox('hidePanel')//隐藏日期对象
                            .datebox('setValue', year + '-' + month); //设置日期的值
                        });
                    }, 0);
                    yearIpt.unbind();//解绑年份输入框中任何事件
                },
                parser: function (s) {
                    if (!s) return new Date();
                    var arr = s.split('-');
                    return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
                },
                formatter: function (d) { return d.getFullYear() + '-' + (d.getMonth() + 1); }
            });
            var p = M2.datebox('panel'), //日期选择对象
                tds = false, //日期选择对象中月份
                aToday = p.find('a.datebox-current'),
                yearIpt = p.find('input.calendar-menu-year'),//年份输入框
                //显示月份层的触发控件
                span = aToday.length ? p.find('div.calendar-title span') :
                p.find('span.calendar-text'); 
            if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
               
                aToday.unbind('click').click(function () {
                    var now=new Date();
                    M2.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
                });
            }
		});
	</script>
	</body>
</html>