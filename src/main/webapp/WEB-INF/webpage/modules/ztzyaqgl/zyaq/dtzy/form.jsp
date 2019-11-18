<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动土作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/dtzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${dtzy.bzr }" editable="false" /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${dtzy.m1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${dtzy.m2 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${dtzy.m3 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监护人：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${dtzy.m4 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><input id="M5" name="M5" class="easyui-datetimebox" value="${dtzy.m5 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><input id="M6" name="M6" class="easyui-datetimebox" value="${dtzy.m6 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-textbox" value="${dtzy.m7 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${dtzy.m8 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox" value="${dtzy.m9 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr> 
					<td class="width-20 active"><label class="pull-right">审批部门：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="process" value="2" />能控中心</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="3" />分厂</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="4" />设备处</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="5" />保卫处 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="process" value="6" />安全科 </div>
																		                </td>
				</tr>
				
				<tr> 
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="进入受限空间" />进入受限空间</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="高处作业" />高处作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="吊装作业" />吊装作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="盲板抽堵" />盲板抽堵 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="动火作业" />动火作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="断路作业" />断路作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="临时用电" />临时用电 </div> 
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="其他" />其他</div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">其他特殊作业：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M10_1" name="M10_1" class="easyui-textbox" value="${dtzy.m10_1 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr> 
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="物体打击" />物体打击</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="机械伤害" />机械伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="车辆伤害" />车辆伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="起重伤害" />起重伤害</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高处坠落" />高处坠落</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="中毒和窒息" />中毒和窒息</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="触电" />触电</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="淹溺" />淹溺</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="灼烫" />灼烫</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="火灾" />火灾</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="坍塌" />坍塌</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="透水" />透水</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="放炮" />放炮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="冒顶片帮" />冒顶片帮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="火药爆炸" />火药爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="瓦斯爆炸" />瓦斯爆炸</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="锅炉爆炸" />锅炉爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="容器爆炸" />容器爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="其他" />其他</div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">其他危害辨识：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M11_1" name="M11_1" class="easyui-textbox" value="${dtzy.m11_1 }" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:['length[0,250]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 80px;" id="M12" name="M12" class="easyui-textbox" value="${dtzy.m12 }" data-options="multiline:true, validType:['length[0,500]'] " /></td>
				</tr>
				
			    <tr>
				  <td class="width-20 active"><label class="pull-right">简图：</label></td>
				  <td class="width-80" colspan="3">
					  <div id="uploader_ws_m12_1">
				      <div id="m12_1fileList" class="uploader-list" ></div>
				      <div id="imagePicker">选择图片</div>
				      </div> 
				  </td>
			    </tr>
			    				
				<c:if test="${not empty dtzy.id}">
					<input type="hidden" name="ID" value="${dtzy.id}" />
					<input type="hidden" name="ID1" value="${dtzy.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dtzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dtzy.s3}" />
					<input type="hidden" name="zt" value="${dtzy.zt}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

var $ = jQuery;
$list = $('#m12_1fileList'); //图片上传

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
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12_1" value="'+newurl+'"/>');
		
		$('#uploader_ws_m12_1').append( $input );
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
	//审批部门
	var tszy = "${dtzy.process}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M10']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
	
	//特殊作业
	var tszy = "${dtzy.M10}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M10']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
	
	//危害辨识
	var whbs = "${dtzy.M11}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M11']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='process']:checkbox").css("width","18px");
	$("input[name='process']:checkbox").css("height","18px");
	$("input[name='M10']:checkbox").css("width","18px");
	$("input[name='M10']:checkbox").css("height","18px");
	$("input[name='M11']:checkbox").css("width","18px");
	$("input[name='M11']:checkbox").css("height","18px");
});

$("#M5").datetimebox({  
    onChange:function(date){ 
   		var ksdate = $("#M5").datetimebox("getValue");   
        var jsdate = $("#M6").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M5").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M6").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M5").datetimebox("getValue");  
        var jsdate = $("#M6").datetimebox("getValue");  
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M6").datetimebox("setValue","");  
	            layer.msg("不能小于作业开始时间！", {
					time : 2000
				});
	        }
        }  
    }  
});
</script>
</body>
</html>