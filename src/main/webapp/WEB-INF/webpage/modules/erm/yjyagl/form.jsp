<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全备案管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yjyagl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			
				<tr>
					<td class="width-15 active"><label class="pull-right">备案编号：</label></td>
					<td class="width-35" ><input name="M1" class="easyui-textbox" value="${aqbalist.m1 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,100]'" /></td>
					<td class="width-15 active"><label class="pull-right">备案日期：</label></td>
					<td class="width-35"><input id="aqjg_aqba_form_mainform_M2" name="M2" style="width: 100%;height: 30px;" class="easyui-datebox" value="<fmt:formatDate value="${aqbalist.m2 }"/>" style="width:198px;" data-options="required:true, editable:false "/></td>
				</tr>				
				<tr>
					<td class="width-15 active"><label class="pull-right">备案经手人：</label></td>
					<td class="width-35" colspan="3"><input name="M7" class="easyui-textbox" value="${aqbalist.m7 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
				</tr>

				<shiro:hasAnyRoles name="admin,ajcountyadmin">
				<tr>  
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35" colspan="3"><input name="M5" style="width: 100%;height: 80px;"  class="easyui-textbox" data-options="multiline:true ,validType:'length[0,250]'">${aqbalist.m5}</textarea></td> 
				</tr>
				</shiro:hasAnyRoles>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
					<input name="M8" type="text" value="${aqbalist.m8 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,500]'">
					</td>
					
				</tr>
				
				<tr>
					<td class="width-22 active"><label class="pull-right">应急流程图：</label></td>
					<td colspan="3">
						<div id="uploader_ws_m9">
						    <div id="m9fileList" class="uploader-list" ></div>
						    <div id="imagePickerm9">选择图片</div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m6">
					    <div id="m6fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<shiro:hasAnyRoles name="admin,ajcountyadmin">
					<input type="hidden" id="M4" name="M4" value="1" />
				</shiro:hasAnyRoles>
			
				<shiro:hasAnyRoles name="company,companyadmin">
					<input type="hidden" id="M4" name="M4" value="0" />
				</shiro:hasAnyRoles>
				
				<c:if test="${not empty aqbalist.ID}">
					<input type="hidden" name="ID" value="${aqbalist.ID}" />
					<input type="hidden" name="ID1" value="${aqbalist.ID1}" />
					<input type="hidden" id="M3" name="M3" value="${aqbalist.m3}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${aqbalist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	
	var $ = jQuery,
	$list2 = $('#m6fileList'); //文件上传
	
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
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M6" value="'+newurl+'"/>');
			
			$('#uploader_ws_m6').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	$list3 = $('#m9fileList'); 
	var uploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=image',
	    pick: {
	    	id:'#imagePickerm9',
	    	multiple : false,
	    },
	    duplicate :true,
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
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

	uploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
					"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list3.append( $li );	
			var newurl=res.url+"||"+res.fileName;	
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M9" value="'+newurl+'"/>');
			$('#uploader_ws_m9').append( $input );	
			uploadImgFlag=true;
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	if('${action}' == 'update'){
		var fjUrl = '${aqbalist.m6}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M6" value="'+arry[i]+'"/>');
				$('#uploader_ws_m6').append( $input );
			}
		}
		
		var imgUrl = '${aqbalist.m9}';
		if(imgUrl!=null&&imgUrl!=''){
			arry =imgUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_img_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list3.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M9" value="'+arry[i]+'"/>');
				$('#uploader_ws_m9').append( $input );
			}
		}
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
	}
	$(function(){
		var flag=true;
		$('#inputForm').form({
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
		    	if(isValid&&flag){
		    		flag=false;
		    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
		    		return true;
		    	}
				return false; //返回false终止表单提交
		    },
		    success:function(data){
		    	$.jBox.closeTip();
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	});
	
	
	</script>
</body>
</html>