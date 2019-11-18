<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加复检信息</title>
	<meta name="decorator" content="default"/>
	<%-- <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script> --%>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqjd/jcjl/${action}"  method="post" class="form-horizontal" >
		
		<div class="easyui-accordion"  style="width:100%;height：100%">
	
		<table   class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr >
					<td class="width-15 active" ><label class="pull-right">专项检查名称：</label></td>
					<td class="width-35"  ><input readonly="true" style="width:100%;height: 30px;"
						name="id1" class="easyui-combobox" value="${cre.ID1 }"
						data-options="required:'true',valueField: 'id1',textField: 'text',url:'${ctx}/aqjd/jcjl/checkplanlist'" />
					    <input type='hidden' name="S1" value="<fmt:formatDate value="${cre.s1 }"/>" /> 
						<input type='hidden' name="ID" value="${cre.ID}" />
						<input type='hidden' name="ID1" value="${cre.ID1}" />
						<input type='hidden' name="ID2" value="${cre.ID2}" />
						<input type='hidden' name="ID3" value="${cre.ID3}" />
						<input type='hidden' name="M7" value="${cre.m7}" />
						<input type='hidden' name="M8" value="${cre.m8}" />
						<input type='hidden' name="checkflag" value="1" />
						</td>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><input  readonly="true" name="M2" class="easyui-datebox" value="${cre.m2 }" style="width: 100%;height: 30px;"  /></td>	
				</tr>
		</table>
		
			<div title="初检记录" iconCls="icon-add" style="padding:10px;"  data-options="collapsed:true"  >
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				<tr>
					<td class="width-15 active"><label class="pull-right">检查人员姓名：</label></td>
					<td class="width-35"><input name="M3" readonly="true" class="easyui-textbox" value="${cre.m3 }" style="width: 100%;height: 30px;"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患描述：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" readonly="true" type="text" value="${cre.m4 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">整改负责人姓名：</label></td>
					<td class="width-35"><input name="M5" readonly="true" class="easyui-textbox" value="${cre.m5 }" style="width: 100%;height: 30px;" /></td>
					<td class="width-15 active"><label class="pull-right">整改期限至：</label></td>
					<td class="width-35"><input name="M6" readonly="true" class="easyui-datebox" value="${cre.m6 }" style="width: 100%;height: 30px;" /></td>
				</tr>

				<tr>
					<td class="width-15 active" style="width:6.5%"><label class="pull-right">隐患照片：</label></td>
					<td class="width-35" colspan="3">
						 <c:if test="${not empty cre.m7}">
					 <c:forTokens items="${cre.m7}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/><a target="_blank" href="${urlna[0]}">${urlna[1]}</a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right">检查表附件：</label></td>
					<td class="width-35" colspan="3">
					 <c:if test="${not empty cre.m8}">
					 <c:forTokens items="${cre.m8}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="margin: 5px;"> 
			            	<a style="color:#337ab7;text-decoration:none;" target="_blank" href="${urlna[0]}">${urlna[1]}</a>
			            </div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				</table>
			</div>

			<div title="复检记录" iconCls="icon-add" style="padding:10px;" selected="true" data-options="collapsed:false" >
		
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				
				<tr>
					<td class="width-15 active"><label class="pull-right">复查人员姓名：</label></td>
					<td class="width-35"><input name="M9" class="easyui-textbox" value="${cre.m9 }" style="width: 100%;height: 30px;"/></td>
					<td class="width-15 active"><label class="pull-right">复查时间：</label></td>
					<td class="width-35"><input name="M10" class="easyui-datebox" value="${cre.m10 }" style="width: 100%;height: 30px;"  /></td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">复查意见：</label></td>
					<td class="width-85" colspan="3">
					<input name="M11" type="text" value="${cre.m11 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">复查照片附件：</label></td>
					<td class="width-85" colspan="3">
					<div id=uploader_ws_m12>
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				 <tr>
					<td class="width-15 active" ><label class="pull-right">复查检查表附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m13">
					    <div id="m13fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>
				
			</table>
			</div>			
			</div>
       </form>

<script type="text/javascript">
	//var usertype=${usertype};
	
	var $ = jQuery,
    $list = $('#m12fileList'); //图片上传
    $list2 = $('#m13fileList');//附件上传
    //图片上传
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
	
    //附件上传
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
			
			$('#uploader_ws_m12').append( $input );
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="m13" value="'+newurl+'"/>');
			
			$('#uploader_ws_m13').append( $input );
		}else{
			layer.msg(cre.message,{time: 3000});
		}
	});
 
	
	
	if('${action}' == 'createfjSub'){
		var zpUrl = '${cre.m12}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_ws_m12').append( $input );
			}
		}
		var fjUrl = '${cre.m13}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M13" value="'+arry[i]+'"/>');
				$('#uploader_ws_m13').append( $input );
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