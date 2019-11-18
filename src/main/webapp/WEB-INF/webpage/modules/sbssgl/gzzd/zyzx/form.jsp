<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>上传文件</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/sbssgl/zyzx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">标题：</label></td>
					<td class="width-75" colspan="3"><input name="m1" style="width: 100%;height: 30px;" class="easyui-textbox" value="${zyzx.m1 }" data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">文件：</label></td>
					<td class="width-75" colspan="3">
						<div id="uploader_ws_m3">
					    <div id="fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	
var fileflag = false;//文件是否上传

$list = $('#fileList'); //文件上传
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

//文件上传成功 
fileuploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );
		
	    $list.html( $li );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("[name='m3']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m3" value="'+newurl+'"/>');
		
		$('#uploader_ws_m3').append( $input );
		
		fileflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

function doSubmit(){
	if (!fileflag) {
		layer.msg('请上传文件', {time: 3000});
		return;
	}
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.location.reload();
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
	
	if('${action}'=='update'){
		var fjUrl = '${zyzx.m3}';
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
			    $list.html( $li );
			    $("[name='m3']").remove();
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m3" value="'+arry[i]+'"/>');
				$('#uploader_ws_m3').append( $input );
			}
		}
		fileflag = true;
	}

});	
</script>
</body>
</html>