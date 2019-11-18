<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>规章制度</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/sbssgl/gzzd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">规章制度名称：</label></td>
					<td class="width-75" colspan="3"><input name="m1" style="width: 100%;height: 30px;" class="easyui-textbox" value="${gzzd.m1 }" data-options="required:'true'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">颁布单位：</label></td>
					<td class="width-35"><input id="m4" name="m4" style="width: 100%;height: 30px;" class="easyui-textbox" value="${gzzd.m4 }" data-options="validType:'length[0,100]'"/></td>
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35"><input id="m5" name="m5" style="width: 100%;height: 30px;" class="easyui-textbox" value="${gzzd.m5 }" data-options="validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><input id="m6" name="m6" class="easyui-datebox" style="width: 100%;height: 30px;" value="${gzzd.m6 }" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-35" ><input id="m7" name="m7" class="easyui-datebox" style="width: 100%;height: 30px;" value="${gzzd.m7 }" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">摘要：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="m8" class="easyui-textbox" value="${gzzd.m8 }"  data-options="multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">文件：</label></td>
					<td class="width-75" colspan="3">
						<div id="uploader_ws_m3">
					    <div id="m3fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
			  <tbody>
		    </table>
			<c:if test="${not empty gzzd.ID}">
			<input type="hidden" name="ID" value="${gzzd.ID}" />
			<input type="hidden" name="qyid" value="${gzzd.qyid}" />
			<input type="hidden" name="scrid" value="${gzzd.scrid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${gzzd.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="m2" value="<fmt:formatDate value="${gzzd.m2}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${gzzd.s3}" />
		</c:if>				
		  	
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	
var fileflag = false;

var $ = jQuery,
$list = $('#m3fileList'); //文件上传

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
	    $list.html( $li );
		var newurl=res.url+"||"+res.fileName;
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m3" value="'+newurl+'"/>');
		$('#uploader_ws_m3').append( $input );
		fileflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

//文件删除
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

function doSubmit(){
	if (!filePicker) {
		layer.open('请上传文件', {time: 3000});
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
	
	var fjUrl = '${gzzd.m3}';
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
			var $input = $('<input id="input_'+id+'" type="hidden" name="m3" value="'+arry[i]+'"/>');
			$('#uploader_ws_m3').append( $input );
			fileflag = true;
		}
	}
});	
	
	
</script>
</body>
</html>