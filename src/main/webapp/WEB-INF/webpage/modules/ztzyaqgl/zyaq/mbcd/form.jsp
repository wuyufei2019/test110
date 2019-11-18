<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>盲板抽堵作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/mbcd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${mbcd.bzr }" editable="false" /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" ><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${mbcd.m1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${mbcd.m2 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${mbcd.m3 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">设备管道名称：</label></td>
					<td class="width-80" colspan="3"><input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${mbcd.m4 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">介质：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-textbox" value="${mbcd.m5 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">温度：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-textbox" value="${mbcd.m6 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">压力：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-textbox" value="${mbcd.m7 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">盲板材质：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${mbcd.m8 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">盲板规格：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox" value="${mbcd.m9 }"  data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">盲板编号：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M10" name="M10" class="easyui-textbox" value="${mbcd.m10 }"  data-options="validType:['length[0,50]'] " /></td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">实施时间（堵）：</label></td>
					<td class="width-30"><input id="M11" name="M11" class="easyui-datetimebox" value="${mbcd.m11 }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">实施时间（抽）：</label></td>
					<td class="width-30"><input id="M12" name="M12" class="easyui-datetimebox" value="${mbcd.m12 }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业人（堵）：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M13" name="M13" class="easyui-textbox" value="${mbcd.m13 }"  data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">作业人（抽）：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M14" name="M14" class="easyui-textbox" value="${mbcd.m14 }"  data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监护人（堵）：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M15" name="M15" class="easyui-textbox" value="${mbcd.m15 }"  data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">监护人（抽）：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M16" name="M16" class="easyui-textbox" value="${mbcd.m16 }"  data-options="validType:['length[0,50]'] " /></td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">生产单位作业指挥：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M17" name="M17" class="easyui-textbox" value="${mbcd.m17 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位负责人：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M18" name="M18" class="easyui-textbox" value="${mbcd.m18 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M19" name="M19" class="easyui-textbox" value="${mbcd.m19 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M20" name="M20" class="easyui-textbox" value="${mbcd.m20 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
								
				<tr> 
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="进入受限空间" />进入受限空间</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="高处作业" />高处作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="吊装作业" />吊装作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="动火作业 " />动火作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="动土作业" />动土作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="断路作业" />断路作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="临时用电 " />临时用电 </div> 
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M21" value="其他" />其他</div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">其他特殊作业：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M21_1" name="M21_1" class="easyui-textbox" value="${mbcd.m21_1 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

			    <tr>
				  <td class="width-20 active"><label class="pull-right">盲板位置图：</label></td>
				  <td class="width-80" colspan="3">
					  <div id="uploader_ws_m22">
				      <div id="m22fileList" class="uploader-list" ></div>
				      <div id="imagePicker">选择图片</div>
				      </div> 
				  </td>
			    </tr>
			    							
				<c:if test="${not empty mbcd.id}">
					<input type="hidden" name="ID" value="${mbcd.id}" />
					<input type="hidden" name="ID1" value="${mbcd.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${mbcd.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${mbcd.s3}" />
					<input type="hidden" name="zt" value="${mbcd.zt}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

var $ = jQuery;
$list = $('#m22fileList'); //图片上传

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

// 图片上传成功，显示预览图
uploader.on( 'uploadSuccess', function( file ,mbcd) {
	$.jBox.closeTip();
	if(mbcd.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img onclick=openImgView('"+mbcd.url+"')>" +
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
		
		
		var newurl=mbcd.url+"||"+mbcd.fileName;
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M22" value="'+newurl+'"/>');
		
		$('#uploader_ws_m22').append( $input );
		uploadImgFlag=true;//存在上传图片
		
	}else{
		layer.msg(mbcd.message,{time: 3000});
	}
});

//判断是否上传图片
function isuploadImg(){
	var img=$("input[name='signpic']").val();
	if(img==null||img==""){
		uploadImgFlag=false;
	}else{
		uploadImgFlag=true;
	}
}

  // 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
	isuploadImg();
};

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

var action = "${action}";
if(action=="update"){
	//特殊作业
	var tszy = "${mbcd.M21}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M21']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='M21']:checkbox").css("width","18px");
	$("input[name='M21']:checkbox").css("height","18px");
});

</script>
</body>
</html>