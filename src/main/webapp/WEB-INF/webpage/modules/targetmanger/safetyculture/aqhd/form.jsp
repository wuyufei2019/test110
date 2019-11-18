<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>安全职责管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/aqhd/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">活动名称：</label></td>
               <td class="width-35"><input id="name"name="name" class="easyui-textbox" value="${target.name }"
                     style="width:100%;height: 30px;"data-options="required:true,validType:'length[0,25]'" /></td>
               <td class="width-15 active"><label class="pull-right">时间：</label></td>
               <td class="width-35" ><input id="time" name="time" class="easyui-datebox" value="${target.time }"
                     style="width:100%;height: 30px;/"> </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">地点：</label></td>
               <td class="width-35" colspan="3"><input name="address" type="text" value="${target.address }"
                class="easyui-textbox" style="width: 100%;height: 30px;"/></td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">召集部门：</label></td>
               <td class="width-35"><input id="ID2" name="ID2" type="text" value="${target.ID2 }"
                class="easyui-combobox"style="width: 100%;height: 30px;"
                     data-options="required:true,panelHeight:100,valueField:'id',textField:'text',
                                    url: '${ctx}/system/department/deptjson'"/></td>
               <td class="width-15 active"><label class="pull-right">召集人：</label></td>
               <td class="width-35" ><input id="gatherper" name="gatherper" class="easyui-textbox" value="${target.gatherper }"
                     style="width:100%;height: 30px;"/> </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">参加部门：</label></td>
               <td class="width-35"><input id="attenddeps" name="attenddeps" type="text" value="${target.attenddeps }"
                class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="panelHeight:100, valueField:'id',textField:'text',multiple:true,
                                    url: '${ctx}/system/department/deptjson'"/></td>
               <td class="width-15 active"><label class="pull-right">活动级别：</label></td>
               <td class="width-35" ><input name="actiontlev" class="easyui-combobox" value="${target.actiontlev }"
                   style="width:100%;height: 30px;"data-options="panelHeight:'auto', valueField:'value',textField:'text',
                   data:[{value:1,text:'公司'},{value:2,text:'部门'},{value:3,text:'班组'},]"/> </td>
            </tr>
             <tr>
               <td class="width-15 active"><label class="pull-right">记录人：</label></td>
               <td class="width-35"><input id="recordper" name="recordper" type="text" value="${target.recordper }"
                class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,10]'"/></td>
               <td class="width-15 active"><label class="pull-right">状态：</label></td>
               <td class="width-35" ><input name="state" class="easyui-combobox" value="${target.state }"
                   style="width:100%;height: 30px;"data-options="panelHeight: 'auto', valueField:'value',textField:'text',
                   data:[{value:1,text:'待开'},{value:2,text:'推迟'},{value:3,text:'结束'},]"/> </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">活动照片：</label></td>
               <td class="width-35"colspan="3">
                  <div id="uploader_ws_image">
                     <div id="imageList" class="uploader-list"></div>
                     <div id="imagePicker">选择图片</div>
                  </div>
               </td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">其他材料上传：</label></td>
               <td class="width-35"colspan="3">
                  <div id="uploader_ws_file">
                     <div id="fileList" class="uploader-list"></div>
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
		if("${action}"=="create"){
			$("#time").datebox("setValue", new Date().format("yyyy-MM-dd"));
			$("#ID2").combobox("setValue", '${id2}');
			//$("#gatherper").textbox("setValue", '${username}');
			$("#recordper").textbox("setValue", '${username}');
		}
	});
	//文件上传
	var $ = jQuery;
    $list = $('#imageList'); //图片上传
    $list2 = $('#fileList');//附件上传
	//图片
	var uploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=image',
	    pick: {
	    	id:'#imagePicker',
	    	multiple : false,
	    },
	    duplicate :true,	    
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
	    }
	});
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
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,cre) {
		if(cre.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img>" +
		                "<div class=\"info\">" + file.name + "</div>" +
		            "</div>"
		            ),
	
		    $img = $li.find('img');

		    $list.append( $li );
	
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			
			var newurl=cre.url+"||"+cre.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
			$('#uploader_ws_image').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
		$.jBox.closeTip();
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,cre) {
		if(cre.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+cre.url+"')\">"+cre.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list2.append( $li );
			var newurl=cre.url+"||"+cre.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="otherurl" value="'+newurl+'"/>');
			$('#uploader_ws_file').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
		$.jBox.closeTip();
	});
 
	
	
	if('${action}' == 'update'){
		var zpUrl = '${target.url}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="url" value="'+arry[i]+'"/>');
				$('#uploader_ws_image').append( $input );
			}
		}
		var fjUrl = '${target.otherurl}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="otherurl" value="'+arry[i]+'"/>');
				$('#uploader_ws_file').append( $input );
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