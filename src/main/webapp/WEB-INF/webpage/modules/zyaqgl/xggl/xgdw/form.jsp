<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关单位管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/xgdw/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${xgdw.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${xgdw.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-30" colspan="3">
						<input style="required:'true'; width: 100%;height: 30px;" id="M2" name="M2" required="true" class="easyui-textbox" value="${xgdw.m2 }" data-options="validType:['length[0,50]'] " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-combobox" value="${xgdw.m1 }" data-options="panelHeight:'auto' ,editable:false ,valueField:'text',textField:'text', data:[{value:'承包商',text:'承包商'},{value:'供应商',text:'供应商'},{value:'其他',text:'其他'}] " /></td>
					<td class="width-20 active"><label class="pull-right">行业类型：</label></td>
					<td class="width-30">
					<input type="text" id="M3" name="M3" class="easyui-combobox" style="height: 30px; width: 100%;" value="${xgdw.m3}" data-options="panelHeight:'150px' ,editable:false ,valueField:'text',textField:'text',url:'${ctx }/tcode/dict/hylx'" />
					</td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>法人代表：</label></td>
					<td class="width-28"><input name="M4" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgdw.m4 }" data-options="validType:'length[1,100]' " /></td>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系电话：</label></td>
					<td class="width-28"><input name="M5" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgdw.m5 }" data-options="validType:'mobileAndTel' " /></td>
				</tr>

				<tr>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系人：</label></td>
					<td class="width-28"><input name="M6" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgdw.m6 }" data-options="validType:'length[1,100]' " /></td>
					<td class="width-22 active"><label class="pull-right"><font color="red"></font>联系电话：</label></td>
					<td class="width-28"><input name="M7" style="width:220px;height:30px;" class="easyui-textbox"
						value="${xgdw.m7 }" data-options="validType:'mobileAndTel' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经营范围：</label></td>
					<td class="width-80" colspan="3"><input id="M10" name="M10" class="easyui-textbox" style="width: 100%;height: 80px;" value="${xgdw.m10 }" data-options="multiline:true, editable:true, validType:['length[0,1000]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">营业执照：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m11">
					    <div id="m11fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">商务合同：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>		
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全合同：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">人员资质：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m17">
					    <div id="m17fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全协议：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m18">
					    <div id="m18fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">施工方案：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m19">
					    <div id="m19fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">技术措施：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m20">
					    <div id="m20fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">相关培训资料：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m21">
					    <div id="m21fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">法人和安全人员证书：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m22">
					    <div id="m22fileList" class="uploader-list" ></div>
					    <div class="uploadBtn_pic" onclick="addWebUpload_current(this)">选择文件</div>
					</div> 
					</td>
				</tr>																														
				
				<c:if test="${not empty xgdw.id}">
					<input type="hidden" name="ID" value="${xgdw.id}" />
					<input type="hidden" name="ID1" value="${xgdw.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${xgdw.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${xgdw.s3}" />
					<input type="hidden" name="M12" value="${xgdw.m12}" />
                    <input type="hidden" name="type" value="${xgdw.type}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	$list = $('#m11fileList'); //图片上传
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
	
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,xgdw) {
		$.jBox.closeTip();
		if(xgdw.error==0){
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
			
			
			var newurl=xgdw.url+"||"+xgdw.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');
			
			$('#uploader_ws_m11').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(xgdw.message,{time: 3000});
		}
	});
	

	var filebq=$(".webUpload_current").attr('id');
	
	function addWebUpload_current(obj) {
	    $(".webUpload_current").removeClass("webUpload_current");//先全部移除
	    $(obj).parent().addClass("webUpload_current");//添加当前标识
	    filebq=$(".webUpload_current").attr('id');
	}
	//上传文件
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'.uploadBtn_pic',
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
		var filearray = filebq.split("_");
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    
			$list2 = $('#'+filearray[2]+'fileList'); //文件上传
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="'+filearray[2]+'" value="'+newurl+'"/>');
			
			$('#'+filebq).append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

    //修改的时候初始化已上传的文件内容
    function initfile(url,name){
		var fjUrl = url;
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+name+"_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
				$list2 = $('#'+name+'fileList');
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name='+name+' value="'+arry[i]+'"/>');
				$('#uploader_ws_'+name).append( $input );
			}
		}    	
    }
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
    if('${action}' == 'update'){
		var zpUrl = '${xgdw.m11}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
				$('#uploader_ws_m11').append( $input );
			}
		}
		initfile('${xgdw.m15}','m15');
		initfile('${xgdw.m16}','m16');
		initfile('${xgdw.m17}','m17');
		initfile('${xgdw.m18}','m18');
		initfile('${xgdw.m19}','m19');
		initfile('${xgdw.m20}','m20');
		initfile('${xgdw.m21}','m21');
		initfile('${xgdw.m22}','m22');
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