<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>上传附件</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbsq/upload" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active">
						<label class="pull-right">附件：</label>
					</td>
					<td class="width-80">
						<input type="hidden" id="m11" name="m11" value="" />
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择文件</div>
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

var $ = jQuery,
$list = $('#fileList'); //文件上传

var fileuploader = WebUploader.create({
   auto: true,
   swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
   server: '${ctx}/kindeditor/upload?dir=file',		
   pick: {
	   	id:'#insertfilebt',
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
	            "</div>"
	            );		    
	    $list.html( $li );
		var newurl=res.url+"||"+res.fileName;
		$('#m11').val( newurl );
		imgflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

function doSubmit(){
	if(!imgflag){
		layer.msg("请上传附件！",{time: 3000});
		return;
	}
	$("#inputForm").serializeObject();
	top.layer.confirm('是否验证附件准确无误？', {icon: 3, title:'提示'}, function(index){
		$("#inputForm").submit(); 
		top.layer.close(index);		
	});
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
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>