<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>作业单位确认页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
		if(uploadImgFlag==false){
			layer.open({title: '提示',offset: 'auto',content: '未上传签字照片，请上传！',shade: 0 ,time: 2000 });
			return;
		}
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
</head>
<body>
     <form id="inputForm" action="${ctx}/ztzyaqgl/dhzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>

			    <tr>
				  <td class="width-20 active"><label class="pull-right">完工时间：</label></td>
				  <td class="width-80" colspan="3">
				  		<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${wgsj }"/>
				  </td>
			    </tr>
			    			  
			    <tr>
				  <td class="width-20 active"><label class="pull-right">签字上传：</label></td>
				  <td class="width-80" colspan="3">
					  <div id="uploader_ws_signpic">
				      <div id="signpicfileList" class="uploader-list" ></div>
				      <div id="imagePicker">选择图片</div>
				      </div> 
				  </td>
			    </tr>

				<input type="hidden" name="dhid" value="${dhid }" />
				</tbody>
			</table>	
      </form>
<script type="text/javascript">
	var action = $("[name=action]").val();
	var uploadImgFlag="";
	var $ = jQuery,
	$list = $('#signpicfileList'); //图片上传
	
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
  
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,dhzy) {
		$.jBox.closeTip();
		if(dhzy.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img onclick=openImgView('"+dhzy.url+"')>" +
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
			
			
			var newurl=dhzy.url+"||"+dhzy.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="signpic" value="'+newurl+'"/>');
			
			$('#uploader_ws_signpic').append( $input );
			uploadImgFlag=true;//存在上传图片
			
		}else{
			layer.msg(dhzy.message,{time: 3000});
		}
	});

	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='signpic']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
	
	  // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    	isuploadImg();
    };	
</script>
</body>
</html>