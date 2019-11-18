<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>复查意见管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/fcyj/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<td class="width-30" colspan="3"><input type="text" id="ID2" name="ID2" value="${fcyj.ID2}" class="easyui-combobox"  style="height: 30px;width: 100%" data-options="required:true, readonly:'true', panelHeight:'auto', editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' "/></td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">整改完毕日期：</label></td>
					<td class="width-30" colspan="3">
						<input id="M1" name="M1" class="easyui-datebox" value="${fcyj.m1 }" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在问题：</label></td>
					<td class="width-80" colspan="3">
						<input name="M2" type="text" value="${fcyj.m2 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,500]'">
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">提出意见：</label></td>
					<td class="width-80" colspan="3">
						<input name="M6" type="text" value="${fcyj.m6 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,500]'">
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">整改照片：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M3_1" name="M3_1" class="easyui-combobox" value="${fcyj.m3_1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M4_1" name="M4_1" class="easyui-textbox" style="height: 30px; width: 100%;" value="${fcyj.m4_1 }" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M3_2" name="M3_2" class="easyui-combobox" value="${fcyj.m3_2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'170px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M4_2" name="M4_2" class="easyui-textbox" style="height: 30px; width: 100%;" value="${fcyj.m4_2}" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30" colspan="3" ><input type="text" id="M5" name="M5" class="easyui-textbox" value="${fcyj.m5 }"  data-options=" " style="height: 30px;width: 100%" /></td>
				</tr>
				
				<input type="hidden" name="ID1" value="${fcyj.ID1}" />				
				<c:if test="${not empty fcyj.ID}">
					<input type="hidden" name="ID" value="${fcyj.ID}" />
					<input type="hidden" name="M0" value="${fcyj.m0}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${fcyj.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${fcyj.s3}" />
				</c:if>
				<tbody>
			</table>

       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

$("#M1").datebox({  
    onSelect:function(date){  
        var nowDate = new Date();  
        if(date>nowDate){  
            $("#M1").datebox("setValue","");  
            layer.msg("不能超过当天日期", {
				time : 2000
			});
        }  
    }  
}); 

function doSubmit(){
	$('#M3_1').combobox('setValue',$('#M3_1').combobox('getText'));
	$('#M3_2').combobox('setValue',$('#M3_2').combobox('getText'));
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
		  
	//下拉关联信息
	$("#M3_1").combobox({
		onSelect: function(){
			var id=$("#M3_1").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M4_1").textbox('setValue',data);
			}
		});
		}
	});

	$("#M3_2").combobox({
		onSelect: function(){
			var id=$("#M3_2").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M4_2").textbox('setValue',data);
			}
		});
		}
	});
});

	uploadImgFlag=false;//是否上传图片
	var $ = jQuery,
    $list = $('#m7fileList'); //图片上传
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
			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M7" value="'+newurl+'"/>');
			
			$('#uploader_ws_m7').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var zpUrl = '${fcyj.m7}';
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
		isuploadImg();
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M7']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
</script>
</body>
</html>