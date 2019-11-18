<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方机构信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/dsf/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-35" colspan="3"><input value="${manage.m1 }" name="M1" style="width: 100%;height: 30px;"
								class="easyui-textbox"
								data-options="required:'true',validType:'length[0,20]'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">单位类型：</label></td>
					<td class="width-35" colspan="3"><input name="M2" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${manage.m2 }"
								data-options="editable:false ,panelHeight:'auto' ,data: [
									{value:'设计',text:'设计'},
									{value:'安装',text:'安装'},
									{value:'评估',text:'评估'},
									{value:'检测',text:'检测'},
									{value:'工程',text:'工程'},
									{value:'维修',text:'维修'},
									{value:'登记',text:'登记'}]" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">法人代表：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${manage.m3 }"
								style="width: 100%;height: 30px;"
								data-options="required:'true',validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${manage.m4 }"
								style="width: 100%;height: 30px;" data-options="validType:'mobile'" /></td>
				</tr>

				
				<tr>
					<td class="width-15 active"><label class="pull-right">注册地址：</label></td>
					<td class="width-35"><input name="M5" class="easyui-textbox" value="${manage.m5 }"
								style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">注册资金(万元)：</label></td>
					<td class="width-35"><input name="M6" class="easyui-textbox" value="${manage.m6 }"
								style="width: 100%;height: 30px;" data-options="validType:['cczu_checkPriceSub','number']" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">项目负责人：</label></td>
					<td class="width-35"><input name="M7" class="easyui-textbox" value="${manage.m7 }"
								style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-35"><input name="M8" class="easyui-textbox" value="${manage.m8 }"
								style="width: 100%;height: 30px;" data-options="validType:'mobile'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M9" type="text" value="${manage.m9 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传文件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m10">
					    <div id="m10fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
			<c:if test="${not empty manage.ID}">
				<input type="hidden" name="S3" value="${manage.s3 }" /> 
				<input type="hidden" name="S1" value="<fmt:formatDate value="${manage.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID" value="${manage.ID}" />
			</c:if>
			</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list2 = $('#m10fileList'); //文件上传
	
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M10" value="'+newurl+'"/>');
			
			$('#uploader_ws_m10').append( $input );
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
		var fjUrl = '${manage.m10}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M10" value="'+arry[i]+'"/>');
				$('#uploader_ws_m10').append( $input );
			}
		}
	}
	
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){    
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
	</script>
</body>
</html>