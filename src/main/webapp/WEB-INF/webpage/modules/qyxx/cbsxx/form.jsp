<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>承包商信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/cbsxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${cbslist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${cbslist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">承包商名称：</label></td>
					<td class="width-35" colspan="3"><input name="M1" type="text" class="easyui-textbox" value="${cbslist.m1 }" style="width: 100%;height: 30px;"  data-options="required:true, validType:'length[0,50]' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">承包项目：</label></td>
					<td class="width-35" colspan="3"><input name="M2" type="text" class="easyui-textbox" value="${cbslist.m2 }" style="width: 100%;height: 30px;"  data-options="required:true, validType:'length[0,50]' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-35" colspan="3"><input name="M3" type="text" class="easyui-textbox" value="${cbslist.m3 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业人数 ：</label></td>
					<td class="width-35" ><input name="M4" type="text" class="easyui-numberbox" value="${cbslist.m4 }" style="width: 100%;height: 30px;" data-options="validType:length[0,10]" /></td>
					<td class="width-15 active"><label class="pull-right">承包商负责人 ：</label></td>
					<td class="width-35" >
							<input name="M8" type="text" class="easyui-textbox" value="${cbslist.m8 }" style="width: 100%;height: 30px;"  data-options="required:true" />
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-35" colspan="3"><input name="M9" type="text" class="easyui-textbox" value="${cbslist.m9 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">资质情况：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker1">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">承包协议：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m6">
					    <div id="m6fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">安全工管协议：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
					    <div id="filePicker3">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty cbslist.ID}">
					<input type="hidden" name="ID" value="${cbslist.ID}" />
					<input type="hidden" name="ID1" value="${cbslist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${cbslist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${cbslist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	$list1 = $('#m5fileList'); //文件上传
	$list2 = $('#m6fileList'); //文件上传
	$list3 = $('#m7fileList'); //文件上传
	
	var fileuploader1 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker1',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader1.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	var fileuploader2 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader2.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	var fileuploader3 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker3',
	    	multiple : false,
	    },
	    duplicate :true  
	});	
	
	fileuploader3.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
	// 文件上传成功 
	fileuploader1.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list1.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_ws_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

	// 文件上传成功 
	fileuploader2.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M6" value="'+newurl+'"/>');
			
			$('#uploader_ws_m6').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	// 文件上传成功 
	fileuploader3.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list3.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M7" value="'+newurl+'"/>');
			
			$('#uploader_ws_m7').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){	
		var fjUrl = '${cbslist.m5}';
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
			    $list1.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_ws_m5').append( $input );
			}
		}
		
		var fjUrl2 = '${cbslist.m6}';
		if(fjUrl2!=null&&fjUrl2!=''){
			arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj2_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M6" value="'+arry[i]+'"/>');
				$('#uploader_ws_m6').append( $input );
			}
		}
		
		var fjUrl3 = '${cbslist.m7}';
		if(fjUrl3!=null&&fjUrl3!=''){
			arry =fjUrl3.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj3_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list3.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M7" value="'+arry[i]+'"/>');
				$('#uploader_ws_m7').append( $input );
			}
		}
	}
</script>
</body>
</html>