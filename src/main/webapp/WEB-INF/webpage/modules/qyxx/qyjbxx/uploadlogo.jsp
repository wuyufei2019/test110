<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>上传附件</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/bis/qyjbxx/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-25 active">
						<label class="pull-right">logo图片：</label>
					</td>
					<td class="width-75">
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择图片</div>
						</div>
					</td>
				</tr>
				<input type="hidden" name="id" value="${id}" />
				<input type="hidden" name="status" value="${status}" />
			</tbody>
		</table>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
var imgflag = false;
var imgUrl;

var $ = jQuery,
$list = $('#fileList'); //文件上传

var uploader = WebUploader.create({
    auto: true,
    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
    server: '${ctx}/kindeditor/upload?dir=image',
    pick: {
    	id:'#insertfilebt',
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
				"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	/* "<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+ */
	                "<img>" +
	                /* "<div class=\"info\">" + file.name + "</div>" + */
	            "</div>"
	            );
		$img = $li.find('img');
	    $list.html( $li );	
	    
	    // 创建缩略图
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img.attr( 'src', src );
	    }, 100, 100 );
	    
		var newurl=res.url+"||"+res.fileName;		
		
		$("[name='imgfile']").val('');
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="imgfile" value="'+newurl+'"/>');
		$('#fileList').append( $input );
		imgflag = true;
		imgUrl = res.url;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

function doSubmit(){
	if(!imgflag){
		layer.msg("请上传图片！",{time: 3000});
		return;
	}
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
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
	    	//parent.dg.datagrid('reload');
	    	$("#comLogo", parent.document).attr("src", imgUrl);// 修改logo对应img标签的src属性值
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>