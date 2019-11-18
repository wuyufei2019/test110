<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全标示信息</title>
	<meta name="decorator" content="default"/>
	<%-- <%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%> --%>
    <script type="text/javascript" src="${ctx}/static/model/js/sekb/aqbskgl/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/sekb/aqbskgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30"><input style="width: 400px;height: 30px;" name="M1"  class="easyui-combobox" value="${res.m1 }" data-options="editable:false ,
									valueField: 'text',textField: 'text',required:true, 
									data: [
								        {value:'禁止标志',text:'禁止标志'},
								        {value:'警告标志',text:'警告标志'},
								        {value:'指令标志',text:'指令标志'},
								        {value:'提示标志',text:'提示标志'},
								        {value:'其他',text:'其他'},]"/></td>

				<tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">名称：</label></td>
					<td class="width-30"><input style="width: 400px;height: 30px;" name="M2"  class="easyui-textbox" value="${res.m2 }" data-options="required:true, validType:'length[0,250]'"/></td>

				<tr>

				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85" colspan="3">
					<textarea id="textarea_kindM3" name="M3" style="width:100%;height:300px;visibility:hidden;">${res.m3 }</textarea>
					</td>					
				</tr> --%>	
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图片：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m3">
						    <div id="m3fileList" class="uploader-list" ></div>
						    <div id="imagePicker">选择图片</div>
						    <div id="uploader_ws_m3_1"></div>
						</div> 
					</td>
				</tr>

				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${res.m4 }"   class="easyui-textbox" style="width: 700px;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr> --%>

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
	$list2 = $('#m3fileList'); //图片上传
	var action = $("[name=action]").val();
	var fileuploader = WebUploader.create({

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
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 图片上传成功，显示预览图
	fileuploader.on( 'uploadSuccess', function( file ,sgfx) {
			$.jBox.closeTip();
			if(sgfx.error==0){
				var $li = $(
			            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img onclick=openImgView('"+sgfx.url+"')>" +
			                "<div class=\"info\">" + file.name + "</div>" +
			            "</div>"
			            ),
		
			    $img = $li.find('img');

			    $list2.html( $li );
		
			    // 创建缩略图
			    fileuploader.makeThumb( file, function( error, src ) {
			        if ( error ) {
			            $img.replaceWith('<span>不能预览</span>');
			            return;
			        }
		
			        $img.attr( 'src', src );
			    }, 100, 100 );
				
				
				var newurl=sgfx.url+"||"+sgfx.fileName;
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M3" value="'+newurl+'"/>');
				
				$('#uploader_ws_m3_1').html( $input );
				uploadImgFlag=true;//存在上传图片
				
			}else{
				layer.msg(sgfx.message,{time: 3000});
			}
		});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	if('${action}' == 'update'){
		var fjUrl = '${res.m3}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
				            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
				                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\" onclick=openImgView('"+arry2[0]+"')>" +
				                "<div class=\"info\">" + arry2[1] + "</div>" +
				            "</div>"
			            );
			    $list2.html( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M3" value="'+arry[i]+'"/>');
				$('#uploader_ws_m3_1').html( $input );
			}
		}
	}
	
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
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
	
	//富文本渲染
	/* onloadEditor(); */

});


</script>
</body>
</html>