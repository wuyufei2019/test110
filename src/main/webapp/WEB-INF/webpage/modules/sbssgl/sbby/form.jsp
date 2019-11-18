<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>修改附件信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbby/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-20 active">
						<label class="pull-right">附件：</label>
					</td>
					<td class="width-80">
						<div id="uploader_ws">
							<input id="m3" type="hidden" name="m3" value=""/>
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="filePicker">选择文件</div>
						</div>
					</td>
				</tr>
			</tbody>
			<c:if test="${not empty sbby.ID}">
				<input type="hidden" name="ID" value="${sbby.ID}" />
				<input type="hidden" name="taskid" value="${sbby.taskid}" />
				<input type="hidden" name="sbid" value="${sbby.sbid}" />
				<input type="hidden" name="userid" value="${sbby.userid}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${sbby.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="S3" value="${sbby.s3}" />
				<input type="hidden" name="m1" value="${sbby.m1}" />
				<input type="hidden" name="m2" value="${sbby.m2}" />
			</c:if>		
		</table>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

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
	            "</div>"
	            );
		
	    $list.html( $li );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("#m3").val(newurl);
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

if('${action}'=='update'){
	var fjUrl = '${sbby.m3}';
	if(fjUrl!=null&&fjUrl!=''){
		arry =fjUrl.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ws_fj_"+i;
			var $li = $(
		            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
		            "</div>"
		            );
		    $list.html( $li );
		    
			$("#m3").val(arry[i]);
		}
	}
}

function doSubmit(){
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