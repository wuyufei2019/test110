<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故案例信息</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
    <script type="text/javascript" src="${ctx}/static/model/js/sekb/sgal/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/sekb/sgal/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-30"><input style="width: 700px;height: 30px;" name="M1"  class="easyui-combobox" value="${res.m1 }" data-options="editable:false ,
									valueField: 'text',textField: 'text',required:true, 
									data: [
								        {value:'火灾',text:'火灾'},
								        {value:'容器爆炸',text:'容器爆炸'},
								        {value:'锅炉爆炸',text:'锅炉爆炸'},
								        {value:'其他爆炸',text:'其他爆炸'},
								        {value:'中毒和窒息',text:'中毒和窒息'},
								        {value:'灼烫',text:'灼烫'},
								        {value:'触电',text:'触电'},
								        {value:'物体打击',text:'物体打击'},
								        {value:'车辆伤害',text:'车辆伤害'},
								        {value:'机械伤害',text:'机械伤害'},
								        {value:'起重伤害',text:'起重伤害'}, 
								        {value:'淹溺',text:'淹溺'},
								        {value:'高处坠落',text:'高处坠落'},
								        {value:'坍塌',text:'坍塌'},
								        {value:'其他伤害',text:'其他伤害'},]"/></td>

				<tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">标题：</label></td>
					<td class="width-30"><input style="width: 700px;height: 30px;" name="M2"  class="easyui-textbox" value="${res.m2 }" data-options="required:true, validType:'length[0,250]'"/></td>

				<tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">正文：</label></td>
					<td class="width-85" colspan="3">
					<textarea id="textarea_kindM3" name="M3" style="width:100%;height:300px;visibility:hidden;">${res.m3 }</textarea>
					</td>					
				</tr>	
				
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${res.m4 }"   class="easyui-textbox" style="width: 700px;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>

				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list2 = $('#m5fileList'); //文件上传
	
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
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_ws_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	if('${action}' == 'update'){
		var fjUrl = '${res.m5}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_ws_m5').append( $input );
			}
		}
	}
	
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").serializeObject()
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
	
	//富文本渲染
	onloadEditor();

});


</script>
</body>
</html>