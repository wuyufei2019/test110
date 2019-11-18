<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>随手拍审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
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
     <form id="inputForm" action="${ctx}/yhpc/sspsh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">隐患发现时间：</label></td>
					<td class="width-30" ><input id="createtime" name="createtime" class="easyui-datetimebox" style="width: 240px;height: 30px;" value="${sspsh.createtime }" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患备注：</label></td>
					<td class="width-80" colspan="3">
						<input type="text" name="dangerdesc" class="easyui-textbox" value="${sspsh.dangerdesc }"  data-options="multiline:true" style="width: 620px;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws_m9">
						    <div id="m9fileList" class="uploader-list" ></div>
						    <div id="imagePicker">选择图片</div>
						</div>
					</td>
				</tr>
				
				<c:if test="${not empty sspsh.ID}">
					<input type="hidden" name="ID" value="${sspsh.ID}" />
					<input type="hidden" name="qyid" value="${sspsh.qyid}" />
					<input type="hidden" name="dangerstatus" value="${sspsh.dangerstatus}" />
					<input type="hidden" name="dangerorigin" value="${sspsh.dangerorigin}" />
					<input type="hidden" name="approvestatue" value="${sspsh.approvestatue}" />
					<input type="hidden" name="userid" value="${sspsh.userid}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
	uploadImgFlag=false;//是否上传图片
	var $ = jQuery,
    $list = $('#m9fileList'); //图片上传
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
	
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
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    	isuploadImg();
    };
    
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img>" +
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
			
			
			var newurl=localhostPaht+res.url+"||";
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="dangerphoto" value="'+newurl+'"/>');
			
			$('#uploader_ws_m9').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

	if('${action}' == 'update'){
		var zpUrl = '${sspsh.dangerphoto}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split("||");
			for(var i=0;i<arry.length-1;i++){
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry[i]+"\" style=\"width:100px; height: 100px\">" +
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="dangerphoto" value="'+arry[i]+'||'+'"/>');
				$('#uploader_ws_m9').append( $input );
			}
		}
		isuploadImg();
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M9']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
</script>
</body>
</html>