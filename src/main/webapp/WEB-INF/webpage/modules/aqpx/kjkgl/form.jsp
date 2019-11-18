<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课件管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/kjkgl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active" ><label class="pull-right">课件名称：</label></td>
					<td class="width-85"><input name="M1" style="width: 100%;height: 30px;" class="easyui-textbox"
								value="${kjlist.m1 }"
								data-options="required:'true'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">课件：</label></td>
					<td class="width-85">
					<div id="uploader_ws_m2">
					    <div id="m2fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty kjlist.ID}">
					<input type="hidden" name="ID" value="${kjlist.ID}" />
					<input type="hidden" name="ID1" value="${kjlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${kjlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${kjlist.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){
	    	if($("input[name='M2']").val()==null){
	    		layer.msg("请上传课件",{time:5000});
	    		return false;
	    	}
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});	

	$list2 = $('#m2fileList'); //文件上传
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    	$("#lx_"+fileid).remove();
    };
    var allMaxSize = 50;
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/uploadkj?dir=1',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    fileSizeLimit: allMaxSize*1024*1024,//限制大小10M，所有被选文件，超出选择不上
	    duplicate :true  
	});
	//  验证大小
    fileuploader.on("error",function (type){ 
         if(type == "Q_EXCEED_SIZE_LIMIT"){
              layer.msg("所选附件总大小不可超过"+allMaxSize+"M", {time : 3000});
         }
     });
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var url=res.url.split(".");
			var fjurl=url[0]+"."+res.fileExt;
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+fjurl+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    
		    $list2.html( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M2" value="'+newurl+'"/>');
			var $input2 = $('<input id="lx_'+file.id+'" type="hidden" name="M3" value="'+res.fileExt+'"/>');
			
			$('#uploader_ws_m2').append( $input );
			$('#uploader_ws_m2').append( $input2 );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var fjUrl = '${kjlist.m2}';
		var lx = '${kjlist.m3}';
		if(fjUrl!=null&&fjUrl!=''){
			var arr =fjUrl.split(".");
			var arr2=fjUrl.split("||");
			var wjurl=arr[0]+"."+lx;
			var $li = $(
		            "<div id=\"1\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+wjurl+"')\">"+arr2[1]+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+1+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list2.html( $li );
		}
	}
	
</script>
</body>
</html>