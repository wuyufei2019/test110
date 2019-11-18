<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>变更申请管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/bgsq/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${bgsq.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${bgsq.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">变更名称：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 30px;" id="M1" name="M1" required="true" class="easyui-textbox" value="${bgsq.m1 }" data-options="validType:['length[0,50]'] " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" readonly="true" class="easyui-textbox" value="${bgsq.sqr }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">部门：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" id="depart" class="easyui-textbox" value="${bgsq.depart }" data-options="validType:['length[0,50]'] " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">变更日期：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M2" name="M2" required="true" class="easyui-datebox" editable="false" value="${bgsq.m2 }"  /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">变更说明的技术依据：</label></td>
					<td class="width-80" colspan="3"><input id="M3" name="M3" class="easyui-textbox" style="width: 100%;height: 80px;" value="${bgsq.m3 }" data-options="multiline:true, editable:true, validType:['length[0,1000]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">风险分析的管控对策：</label></td>
					<td class="width-80" colspan="3"><input id="M4" name="M4" class="easyui-textbox" style="width: 100%;height: 80px;" value="${bgsq.m4 }" data-options="multiline:true, editable:true, validType:['length[0,1000]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">上传技术文件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m13">
					    <div id="m13fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m14">
					    <div id="m14fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>

				<c:if test="${type eq 'sh'}">
				<input type="hidden" name="role" value="2" />
				<tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30">							
							<c:choose>
								<c:when test="${bgsq.m7=='0'}">
									<input type="radio" value="1" class="i-checks" name="M7" />同意
									<input type="radio" value="0" class="i-checks"  name="M7" checked="checked" />不同意
								</c:when>
								<c:otherwise>
									<input type="radio" value="1" class="i-checks" name="M7" checked="checked" />同意
									<input type="radio" value="0" class="i-checks"  name="M7" />不同意
								</c:otherwise>
							</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-datebox" editable="false" value="${bgsq.m9 }"  />
					</td>
				</tr>
				
				<c:if test="${action eq 'update'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" colspan="3"><input readonly="true" style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${bgsq.m8 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				</c:if>
				</c:if>
				
				<c:if test="${type eq 'sp'}">
				<input type="hidden" name="role" value="3" />
				<input type="hidden" name="M7" value="${bgsq.m7}" />
				<input type="hidden" name="M8" value="${bgsq.m8}" />
				<input type="hidden" name="M9" value="${bgsq.m9}" />
				<tr>
					<td class="width-20 active"><label class="pull-right">批准意见：</label></td>
					<td class="width-30">							
					       <c:choose>
								<c:when test="${bgsq.m10=='0'}">
									<input type="radio" value="1" class="i-checks" name="M10" />同意
									<input type="radio" value="0" class="i-checks"  name="M10" checked="checked" />不同意
								</c:when>
								<c:otherwise>
									<input type="radio" value="1" class="i-checks" name="M10" checked="checked" />同意
									<input type="radio" value="0" class="i-checks"  name="M10" />不同意
								</c:otherwise>
							</c:choose>
					</td>
					<td class="width-20 active"><label class="pull-right">批准日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" id="M12" name="M12" class="easyui-datebox" value="${bgsq.m12 }" editable="false" data-options="validType:['length[0,50]'] " />
					</td>
				</tr>
				
				<c:if test="${action eq 'update'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" colspan="3"><input readonly="true" style="width: 100%;height: 30px;" id="M11" name="M11" class="easyui-textbox" value="${bgsq.m11 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				</c:if>
				</c:if>
				
				<c:if test="${not empty bgsq.id}">
					<input type="hidden" name="ID" value="${bgsq.id}" />
					<input type="hidden" name="ID1" value="${bgsq.id1}" />
					<input type="hidden" name="ID2" value="${bgsq.id2}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${bgsq.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${bgsq.s3}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	$list = $('#m14fileList'); //图片上传
	$list2 = $('#m13fileList'); //文件上传
		
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
	    }
	});
	
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
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,bgsq) {
		if(bgsq.error==0){
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
			
			
			var newurl=bgsq.url+"||"+bgsq.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M14" value="'+newurl+'"/>');
			
			$('#uploader_ws_m14').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(bgsq.message,{time: 3000});
		}
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,bgsq) {
		if(bgsq.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+bgsq.url+"')\">"+bgsq.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=bgsq.url+"||"+bgsq.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M13" value="'+newurl+'"/>');
			
			$('#uploader_ws_m13').append( $input );
		}else{
			layer.msg(bgsq.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
    if('${action}' == 'create'){
    	$("#sqr").val('${sqr}');
    	$("#depart").val('${depart}');
    }
    
    	if('${action}' == 'update'){
    	$("#M8").val('${czr}');
    	$("#M11").val('${czr}');
		var zpUrl = '${bgsq.m14}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M14" value="'+arry[i]+'"/>');
				$('#uploader_ws_m14').append( $input );
			}
		}
		
		var fjUrl = '${bgsq.m13}';
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

function doSubmit(){
	$("#inputForm").serializeObject();
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