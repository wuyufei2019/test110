<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全生产技术标准信息</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
    <script type="text/javascript" src="${ctx}/static/model/js/sekb/aqscjsbz/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/sekb/aqscjsbz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">标题：</label></td>
					<td class="width-30"><input style="width: 700px;height: 30px;" name="M1"  class="easyui-textbox" value="${res.m1 }" data-options="required:true, validType:'length[0,250]'"/></td>

				<tr>	
				
				<tr>
					<td class="width-20 active"><label class="pull-right">附件：</label></td>
					<td class="width-30" >
							<input type="hidden" id="M4" name="M4" value="${res.m4}" />
							<input type="hidden" id="M5" name="M5" value="${res.m5}" />
							<input type="hidden" id="M6" name="M6" value="${res.m6}" />
							<div id="uploader_qy_m4">
								    <div id="m4fileList" class="uploader-list" >
									    <c:if test="${not empty res.m4}">
										<c:set var="url" value="${fn:split(res.m4, '||')}" />
											<div id="word" style="margin-bottom: 10px;">
											<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
											<span class="ss" onClick="removeFile('word')" style="cursor: pointer">删除</span>
											</div>
										</c:if>
								    </div>
								    
							</div><div id="insertfilebtM4">选择文件</div>
							
					</td>

				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M3" type="text" value="${res.m3 }"   class="easyui-textbox" style="width: 700px;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>

				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
				</tbody>
			</table>

		  	
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list2 = $('#m4fileList'); //文件上传
	
	var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=flexpaper',		
	    pick: {
	    	id:'#insertfilebtM4',
	    	multiple : false,
	    },
	    accept: {
	        title: 'word',
	        extensions: 'doc,docx,pdf',
	        mimeTypes: '.doc,.docx,.pdf' 
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
		    $list2.html( $li );
			var newurl=res.url+"||"+res.fileName;
			$('#M4').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$('#M4').val("");
    	$('#M5').val("");
    	$('#M6').val("");
    };
	
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	var file=$("#M4").val();
	if(file==null||file==""){
			layer.msg('请上传附件',{time:1000});
			return;
	}
	$("#inputForm").serializeObject()
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
</body>
</html>