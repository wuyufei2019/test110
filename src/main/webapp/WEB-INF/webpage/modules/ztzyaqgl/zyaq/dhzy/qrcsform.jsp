<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/dhzy/${action}"  method="post" class="form-horizontal" >
		<table id="csdata" class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			 <tbody>
			  <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
			  <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			  <tr id="aqcs">
			  
			  </tr>
			  
			  <tr>
				  <td class="width-20 active"><label class="pull-right">负责人签字：</label></td>
				  <td class="width-80" colspan="3">
					  <div id="uploader_ws_m23_2">
				      <div id="m23_2fileList" class="uploader-list" ></div>
				      <div id="imagePicker">选择图片</div>
				      </div> 
				  </td>
			   </tr>

			   <input type="hidden" id="num" />
			   <input type="hidden" name="dhid" value="${id1 }" />
			<tbody>
		</table>
     </form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	
var num=0;
var $ = jQuery;
var imgname="";
var uploadImgFlag=false;

//单选框验证
$.extend($.fn.validatebox.defaults.rules, {
	requireRadio: {
		validator: function(value, param){
			var input = $(param[0]);
			input.off('.requireRadio').on('click.requireRadio',function(){
				$(this).focus();
			});
			return $(param[0] + ':checked').val() != undefined;
		},
		message: '请选择{1}！'
		//message: 'Please choose option for {1}.'
	}
});

function doSubmit(){
	isuploadImg();
	if(!uploadImgFlag){
		layer.open({title: '提示',offset: 'auto',content: '未上传照片，请上传！',shade: 0 ,time: 2000 });
		return;		
	}
		
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

var filebq=$(".webUpload_current").attr('id');

function webUpload(obj) {
    $(".webUpload_current").removeClass("webUpload_current");//先全部移除
    $(obj).parent().addClass("webUpload_current");//添加当前标识
    filebq=$(".webUpload_current").attr('id');
}
$(function(){ 
	var aqcslist = '${aqcslist}';
	qrlist=JSON.parse(aqcslist);  
    $.each(qrlist, function(idx, obj) {
    	num++;
        var $list= $("#csdata #aqcs").eq(-1);
		var $li = $(
			'<tr style="height:40px" >'+
			'<td class="width-80" colspan="3"><label class="pull-left">'+
			obj.m1+
			'</label></td>'+
			'<td class="width-20 ">'+
			'<input type="radio" class="easyui-validatebox" name="cz_'+obj.id+'" style="margin-bottom: 6px;" value="已执行" data-options="validType:\'requireRadio[\\\'input[name=cz_'+obj.id+']\\\', \\\'执行情况\\\']\'" >已执行' +
			'<input type="radio" class="easyui-validatebox" name="cz_'+obj.id+'" style="margin-bottom: 6px;margin-left: 20px;" value="未执行"> 未执行' +
			'<input type="hidden" name="csid" value="'+obj.id+'" >'+
			'</td>'+
			'</tr>'+
			'<tr>'+
			'<td class="width-15 active"><label class="pull-right">现场照片：</label></td>'+
			'<td class="width-35" colspan="3">'+
				'<div id="uploader_ws_xczp'+obj.id+'">'+
			    	'<div id="xczp'+obj.id+'fileList" class="uploader-list" ></div>'+
			    	'<div class="uploadImg" id="filePicker" onclick="webUpload(this)">选择图片</div>'+
				'</div>'+
			'</td>'+
		'</tr>'		
            );
		$list.after( $li );
	    //判断批量生成的上传空间是否全都上传图片
		if(imgname=="")
			imgname=imgname+"xczp"+obj.id;
		else
			imgname=imgname+','+"xczp"+obj.id;
		$.parser.parse($li);
    });	
    $("#num").val(num);


	
    var uploader3 = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
        server:'${ctx}/kindeditor/upload?dir=image',
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick:  {
        	id:'.uploadImg',
        	multiple : false,
        },
        duplicate :true,
        // 只允许选择图片文件。
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

    uploader3.on( 'uploadProgress', function( file, percentage ) {
    	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
    });
    
    //图片上传成功，显示预览图
    uploader3.on( 'uploadSuccess', function( file ,dhzy) {
    	$.jBox.closeTip();
    	var filearray = filebq.split("_");
    	if(dhzy.error==0){
    		var $li = $(
    	            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
    	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
    	                "<img onclick=openImgView('"+dhzy.url+"')>" +
    	                "<div class=\"info\">" + file.name + "</div>" +
    	            "</div>"
    	            ),

    	    $img = $li.find('img');
    		$list3 = $('#'+filearray[2]+'fileList'); //文件上传
    	    $list3.append( $li );

    	    // 创建缩略图
    	    uploader.makeThumb( file, function( error, src ) {
    	        if ( error ) {
    	            $img.replaceWith('<span>不能预览</span>');
    	            return;
    	        }

    	        $img.attr( 'src', src );
    	    }, 100, 100 );
    		
    		var imgurl=filebq.split("_");
    		var newurl=dhzy.url+"||"+dhzy.fileName;
    		var $input = $('<input id="input_'+file.id+'" type="hidden" name="'+imgurl[2]+'" value="'+newurl+'"/>');
    		
    		$('#'+filebq).append( $input );
    	}else{
    		layer.msg(dhzy.message,{time: 3000});
    	}
    });

    
});

var $ = jQuery,
$list = $('#m23_2fileList'); //负责人签字

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
uploader.on( 'uploadSuccess', function( file ,dhzy) {
	$.jBox.closeTip();
	if(dhzy.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img onclick=openImgView('"+dhzy.url+"')>" +
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
		
		
		var newurl=dhzy.url+"||"+dhzy.fileName;
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="signpic" value="'+newurl+'"/>');
		
		$('#uploader_ws_m23_2').append( $input );
		
	}else{
		layer.msg(dhzy.message,{time: 3000});
	}
});

//判断是否上传图片
function isuploadImg(){
	var img=$("input[name='signpic']").val();
	if(img==null||img==""){
		uploadImgFlag=false;
	}else{
		var imgurl2=imgname.split(",");
		for(var i=0;i<imgurl2.length;i++){
			var data=$("input[name='"+imgurl2[i]+"']").val();
			console.log(data);
			if(data==null||data==''||data=='undefined'){
				uploadImgFlag=false;
				return
			}
			else{
				uploadImgFlag=true;
			}
		}
	}
}

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
	uploadImgFlag=false;
};

</script>
</body>
</html>