<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>合理化建议管理</title>
<meta name="decorator" content="default" />
<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
</head>
<body>

   <form id="inputForm" action="${ctx}/target/hlhjy/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">主题：</label></td>
               <td class="width-35" colspan="3"><input style="width: 100%;height: 30px;" name="theme" class="easyui-textbox" value="${target.theme }"
                     data-options="validType:'length[0,25]',required:'true'" /></td>
            <tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">内容：</label></td>
               <td class="width-35" colspan="3"><textarea id="textarea_kind" name="content" style="width:100%;height:300px;visibility:hidden;">${target.content }</textarea>
               </td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">类别：</label></td>
               <td class="width-35"><input style="width: 100%;height: 30px;" name="type" class="easyui-combobox" value="${target.type }"
                     data-options="required:'true', panelHeight:'auto', valueField: 'value',textField: 'text', 
                     data: [{'value':'隐患','text':'隐患'},{'value':'措施','text':'措施'},{'value':'经验','text':'经验'},{'value':'其他','text':'其他'}]" /></td>
               <td class="width-15 active"><label class="pull-right">建言人：</label></td>
               <td class="width-35"><input id="name" name="name" value="${target.name }"class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="readonly:true"></td>
            <tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">附件：</label></td>
               <td class="width-35" colspan="3">
                  <div id="uploader_ws_url">
                     <div id="fileList" class="uploader-list"></div>
                     <div id="filePicker">选择文件</div>
                  </div>
               </td>
            </tr>
              <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="ID1" value="${target.ID1}" />
               <input type="hidden" name="ID2" value="${target.ID2}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         <tbody>
      </table>

   </form>
   <script type="text/javascript">
	//文件上传
	var $ = jQuery;
    $list2 = $('#fileList');//附件上传
	//图片
    //附件
	var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=file',
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
					$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 图片上传成功，显示预览图
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,cre) {
		$.jBox.closeTip();
		if(cre.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+cre.url+"')\">"+cre.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list2.append( $li );
			var newurl=cre.url+"||"+cre.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
			$('#uploader_ws_url').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
	});
 
	if('${action}' == 'update'){
		var fjUrl = '${target.url}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
				$('#uploader_ws_url').append( $input );
			}
		}
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
		window.editor = KindEditor.create('#textarea_kind', $.extend({},
				KEditorConfig, {
					width : '100%',
					items : [ 'source', '|', 'undo', 'redo', '|', 'justifyleft',
							'justifycenter', 'justifyright', '|', 'fontsize',
							'forecolor', 'hilitecolor', 'bold', 'italic', '|',
							'quickformat', '|', 'image', '|', 'link', 'fontname',
							'fullscreen' ]
				}));

	}
	
				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
					$("#inputForm").serializeObject();
					$("#inputForm").submit();
				}

				$(function() {
					onloadEditor();
					$("#name").textbox("setValue",'${username}');
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