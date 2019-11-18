<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
		isuploadImg();
		if(!uploadImgFlag){
			layer.open({title: '提示',offset: 'auto',content: '未上传现场照片，请上传！',shade: 0 ,time: 2000 });
			return;		
		}
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
     <form id="inputForm" action="${ctx}/ztzyaqgl/sxzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<input type="hidden" name="id" value="${sxzyid }" />
				</tbody>
			</table>
			<p style="margin-top: 10px;color: red;font-size: 15px;">
				<strong>分析数据</strong>
				<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加分析数据"><i class="fa fa-plus"></i> 添加分析数据</a>
			</p>
			<hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;" />
			
			<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
				<tr >
					<td style="text-align: center;width: 14%;">类型</td>
					<td style="text-align: center;width: 5%;">介质</td>
					<td style="text-align: center;width: 5%;">数值</td>
					<td style="text-align: center;width: 5%;">单位</td>
					<td style="text-align: center;width: 16%;">部位</td>
					<td style="text-align: center;width: 18%;">分析人</td>
					<td style="text-align: center;width: 25%;">现场照片</td>
					<td style="text-align: center;width: 12%;">操作</td>
				</tr>
			</table>	
						
       </form>
<script type="text/javascript">
var filebq=$(".webUpload_current").attr('id');
var username='${username}';

function webUpload(obj) {
    $(".webUpload_current").removeClass("webUpload_current");//先全部移除
    $(obj).parent().addClass("webUpload_current");//添加当前标识
    filebq=$(".webUpload_current").attr('id');
}

var rwid=1;
var x=0;
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:30px" >'+
				'<td style="" align="center">'+
				'<input type="text" id="type_'+rwid+'" name="type" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="media_'+rwid+'" name="media" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="number_'+rwid+'" name="number" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="unit_'+rwid+'" name="unit" class="easyui-textbox" value="" style="height: 30px;width: 99%;" />'+
				'</td>'+				
				'<td style="" align="center">'+
				'<input type="text" id="part_'+rwid+'" name="part" class="easyui-textbox" value=""  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="person_'+rwid+'" name="person" class="easyui-textbox" value="'+username+'"  style="height: 30px;width: 99%;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<div id="uploader_ws_xczp'+rwid+'">'+
		    	'<div id="xczp'+rwid+'fileList" class="uploader-list" ></div>'+
		    	'<div class="uploadImg" id="filePicker'+rwid+'" onclick="webUpload(this)">选择图片</div>'+
				'</div>'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:78px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+				
				'</tr>'
	            );
	$list.after( $li );

	$('#type_'+rwid).combobox({ 
		panelHeight:'auto',
		editable:true,
		valueField: 'label',
		textField: 'value',
		data: [{
			label: '有毒有害物质',
			value: '有毒有害物质'
		},{
			label: '可燃气',
			value: '可燃气'
		},{
			label: '氧含量',
			value: '氧含量'
		}]
	});
	
	$('#unit_'+rwid).combobox({ 
		panelHeight:'auto',
		editable:true,
		valueField: 'label',
		textField: 'value',
		data: [{
			label: '%',
			value: '%'
		},{
			label: 'ppm',
			value: 'ppm'
		},{
			label: 'mg/m³',
			value: 'mg/m³'
		}]
	});

	$('#name_'+rwid).textbox({  
		required:'true',
		validType:'length[0,100]'
	});
	
	$('#media_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	
	$('#number_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	
	$('#unit_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	
	$('#person_'+rwid).textbox({  
		required:'true',
		validType:'length[0,50]'
	});
	initimg(rwid);
	rwid=rwid+1;
	x++;
}

//删除指定行的数据
function removeTr(obj) {
	x--;
	obj.remove();
}

function initimg(rwid){
	var ipt = $('#filePicker' + rwid);
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
        server:'${ctx}/kindeditor/upload?dir=image',
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick:  {
        	id:ipt,
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

    uploader.on( 'uploadProgress', function( file, percentage ) {
    	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
    });
    
    //图片上传成功，显示预览图
    uploader.on( 'uploadSuccess', function( file ,sxzy) {
    	$.jBox.closeTip();
    	var filearray = filebq.split("_");
    	if(sxzy.error==0){
    		var $li = $(
    	            "<div id=\"" + file.id + "\" >" +
    	            	"<span id=\"" + file.id + "\" class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
    	                "<a target='_blank' href='"+sxzy.url+"'><div class=\"info\">" + file.name + "</div></a>" +
    	            "</div>"
    	            ),

    	    $img = $li.find('img');
    		$list3 = $('#'+filearray[2]+'fileList'); //文件上传
    	    $list3.html( $li );

    	    // 创建缩略图
    	    uploader.makeThumb( file, function( error, src ) {
    	        if ( error ) {
    	            $img.replaceWith('<span>不能预览</span>');
    	            return;
    	        }

    	        $img.attr( 'src', src );
    	    }, 100, 100 );
    		
    		var imgurl=filebq.split("_");
    		var newurl=sxzy.url+"||"+sxzy.fileName;
    		var $input = $('<input id="input_'+file.id+'" type="hidden" name="xczp" value="'+newurl+'"/>');
    		
    		$('#'+filebq).append( $input );
    	}else{
    		layer.msg(sxzy.message,{time: 3000});
    	}
    });
}

//负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

//判断是否上传图片
function isuploadImg(){
	var arr=$('[name="xczp"]');
	if(arr.length<x)
		uploadImgFlag=false;
	else
		uploadImgFlag=true;
}
</script>
</body>
</html>