<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>图片上传</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引	
</script>
</head>
<body>

     <form id="inputForm" action="" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-80" ><input id="M1" name="M1" type="text"  class="easyui-textbox" value="${czwt.m1 }"  data-options="required:true, multiline:true"  style="width: 100%;height: 80px" /></td>
				</tr>				
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" >
					<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">	
	var $ = jQuery,
    $list = $('#m15fileList'); //图片上传
	
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
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,res) {
		if(res.error==0){
			var $li = $(
				"<a target='_blank' href='"+res.url+"'>"+
				"<div class=\"info\">" + file.name + "</div>" +
				"</a>"
		            );
	
		    $list.append( $li );			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="url" type="hidden" name="url" value="'+newurl+'"/>'+
				'<input id="filename" type="hidden" name="filename" value="'+res.fileName+'"/>');
			
			$('#uploader_ws_m15').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

function geturl(){
	var url=$('#url').val();
	if(url==null){
		url="";
	}
	return url;
}

function getfilename(){
	var filename=$('#filename').val();
	return filename;
}	

function getczwt(){
	var czwt=$('#M1').textbox('getValue');
	return czwt;
}	
	</script>
</body>
</html>