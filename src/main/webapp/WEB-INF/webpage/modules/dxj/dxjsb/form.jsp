<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/dxj/sb/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
				    <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-85" colspan="3">
						<input value="${sb.m1}"  id="M1" name="M1" style="width:100%;height: 30px;"
								class="easyui-combotree" data-options="method: 'get',required:true,editable:'false',url:'${ctx}/dxj/sb/sbxx'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">绑定二维码：</label></td>
					<td class="width-35">
						<input name="bindcontent" class="easyui-textbox" value="${sb.bindcontent }" style="width: 100%;height: 30px;" 
								data-options="validType:'length[0,100]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">绑定rfid：</label></td>
					<td class="width-35">
						<input name="rfid" class="easyui-textbox" value="${sb.rfid }" style="width: 100%;height: 30px;" 
								data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active" ><label class="pull-right">rfid卡批次代码：</label></td>
					<td class="width-35">
						<input name="area" class="easyui-textbox" value="${sb.area }" style="width: 100%;height: 30px;" 
								data-options="validType:'length[0,50]'" /></td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m2">
					    <div id="m2fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
                <input type="hidden" id="M1_id" name="M1_id" value="${sb.m1_id}"
				<c:if test="${not empty sb.ID}">
					<input type="hidden" name="ID" value="${sb.ID}" />
					<input type="hidden" name="ID1" value="${sb.ID1}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
	var $ = jQuery,
    $list = $('#m2fileList'); //图片上传
    
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
		            ),$img = $li.find('img');
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M2" value="'+newurl+'"/>');
			$('#uploader_ws_m2').append( $input );
			uploadImgFlag=true;
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var zpUrl = '${sb.m2}';
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
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M2" value="'+arry[i]+'"/>');
				$('#uploader_ws_m2').append( $input );
			}
		}
	}
    //判断树形结构是否选中
    function checkbm(id){
        var data = $("#"+id).combotree('tree').tree('getSelected');
        var text = $("#"+id).combotree('getText');
        if (!data) {//判断是否选中
            return false;
        }else if (data.text != text) {//防止选中后乱输信息
            return false;
        }

        return true;
    }

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
	function doSubmit(){
        if (!checkbm("M1")) {
            layer.msg("请点击选中设备",{time: 3000});
            return;
        }
	    var text = $("#M1").combotree('getText');
	    var value = $("#M1").combotree('getValue')
        $("#M1_id").val(value);
        $("#M1").combotree('setValue',text);
		$("#inputForm").submit(); 
	}
	
	$(function(){
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
			},
		    success:function(data){ 
		        $.jBox.closeTip();
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else if(data=='ewmerror')
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '二维码重复,操作失败！',shade: 0 ,time: 2000 });
		    	else if(data=='rfiderror')
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: 'rfid重复,操作失败！',shade: 0 ,time: 2000 });
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