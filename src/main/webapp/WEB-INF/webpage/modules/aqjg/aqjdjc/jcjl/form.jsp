<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加初检记录</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqjd/jcjl/${action}"  method="post" class="form-horizontal" >
		<tbody>
	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active" ><label class="pull-right">专项检查名称：</label></td>
					<td class="width-35"  ><input style="width:100%;height: 30px;"
						name="ID1" editable="false" class="easyui-combobox" value="${cre.ID1 }"
						data-options="panelHeight:'auto', required:'true',valueField: 'id1',textField: 'text',url:'${ctx}/aqjd/jcjl/addcheckplanlist/${action}'" />
					    <input type='hidden' name="S1" value="<fmt:formatDate value="${cre.s1 }"/>" /> 
						<input type='hidden' name="ID" value="${cre.ID}" />
						</td>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><input name="M2" class="easyui-datebox" value="${cre.m2 }" style="width: 100%;height: 30px;"  /></td>	
				</tr>
		
		        <tr>
					<td class="width-15 active"><label class="pull-right">检查人员姓名：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${cre.m3 }" style="width: 100%;height: 30px;"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患描述：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${cre.m4 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">整改负责人姓名：</label></td>
					<td class="width-35"><input name="M5" class="easyui-textbox" value="${cre.m5 }" style="width: 100%;height: 30px;" /></td>
					<td class="width-15 active"><label class="pull-right">整改期限至：</label></td>
					<td class="width-35"><input name="M6" class="easyui-datebox" value="${cre.m6 }" style="width: 100%;height: 30px;" /></td>
				</tr>

		      <tr>
					<td class="width-15 active" style="width:6.5%"><label class="pull-right">隐患照片附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div> 
					</td>
				</tr>
				 <tr>
					<td class="width-15 active" style="width:6.5%"><label class="pull-right">检查表附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m8">
					    <div id="m8fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>
			</table>
</div>
</form>
 
<script type="text/javascript">
	//var usertype=${usertype};
	
	var $ = jQuery,
    $list = $('#m7fileList'); //图片上传
    $list2 = $('#m8fileList');//附件上传
	//图片
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
	
    //附件
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,cre) {
		$.jBox.closeTip();
		if(cre.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img>" +
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
			
			
			var newurl=cre.url+"||"+cre.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M7" value="'+newurl+'"/>');
			
			$('#uploader_ws_m7').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,cre) {
		$.jBox.closeTip();
		if(cre.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+cre.url+"')\">"+cre.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=cre.url+"||"+cre.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="m8" value="'+newurl+'"/>');
			
			$('#uploader_ws_m8').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
	});
 
	
	
	if('${action}' == 'updateSub'){
		var zpUrl = '${cre.m7}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M7" value="'+arry[i]+'"/>');
				$('#uploader_ws_m7').append( $input );
			}
		}
		var fjUrl = '${cre.m8}';
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
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m8" value="'+arry[i]+'"/>');
				$('#uploader_ws_m8').append( $input );
			}
		}
		
	}
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
		}
		$(function() {
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					if (data == 'success')
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '操作成功！',
							shade : 0,
							time : 2000
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				}
			});
		});
	
	
	</script>
</body>
</html>