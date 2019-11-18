<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/yhpc/ssp/${action}" class="form-horizontal" method="post">
     		<input type="hidden" name="id" value="${ssp.id }">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						${ssp.qyname }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患处理状态：</label></td>
					<td class="width-35">
						${ssp.yhzt }<%-- <input data-options="" type="text" class="easyui-textbox" value="${ssp.yhzt }"  style="height: 30px;width: 100%" disabled="disabled"/> --%>
					</td>
					<td class="width-15 active"><label class="pull-right">隐患等级：</label></td>
					<td class="width-35">
						<input data-options=" panelHeight:'auto',data: [
											{value:'1',text:'一级'},
									        {value:'2',text:'二级'},
									        {value:'3',text:'三级'},
									        {value:'4',text:'四级'}
								        ]" type="text" id="dangerlevel" name="dangerlevel" class="easyui-combobox" value="${ssp.dangerlevel }"  style="height: 30px;width: 100%" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患发现人：</label></td>
					<td class="width-35">
						${ssp.jcr }<%-- <input data-options="" type="text" class="easyui-textbox" value="${ssp.jcr }"  style="height: 30px;width: 100%" disabled="disabled"/> --%>
					</td>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35">
						${ssp.shr }<%-- <input data-options="" type="text" class="easyui-textbox" value="${ssp.shr }"  style="height: 30px;width: 100%" disabled="disabled"/> --%>
					</td>
					
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患发现时间：</label></td>
					<td class="width-35" >
						<input id="createtime" name="createtime" class="easyui-datetimebox" value="${ssp.createtime }"
							style="width: 100%;height: 30px;" data-options="editable:true " />
					</td>
					<td class="width-15 active"><label class="pull-right">计划整改时间：</label></td>
					<td class="width-35">
						<input id="sechandletime" name="sechandletime" class="easyui-datetimebox" value="${ssp.sechandletime }"
							style="width: 100%;height: 30px;" data-options="editable:true " />
					</td>	
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">计划整改人：</label></td>
					<td class="width-35">
						${ssp.jhzgr }<%-- <input data-options="" type="text" class="easyui-textbox" value="${ssp.jhzgr }"  style="height: 30px;width: 100%" disabled="disabled"/> --%>
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患备注：</label></td>
					<td class="width-35" colspan="3">
						<input data-options="" type="text" name="dangerdesc" class="easyui-textbox" value="${ssp.yh }"  style="height: 30px;width: 100%"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-85" colspan="3">
						<input type="hidden" id="dangerphoto" name="dangerphoto" value="${ssp.yhzp}" />
						<div id="uploader_sspimg">
						    <div id="sspimg_fileList" class="uploader-list" >
							    <c:if test="${not empty ssp.yhzp}">
									 <c:forTokens items="${ssp.yhzp}" delims="||" var="url" varStatus="urls">
									 	<c:set var="urlna" value="${fn:split(url, '||')}" />
									 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
									 		<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${urlna[0]}')">隐患照片</a>
									 	</div>
									 </c:forTokens>
								 </c:if>
						    </div>
						    <div id="imagePickersspimg">选择图片</div>
						</div>
					</td>
				</tr>
		  	</tbody>
			</table>
       </form>
      
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
function doSubmit(){
 	$("#inputForm").submit(); 
}
$(function(){
	//表单提交
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
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
    		}else {
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
    		}
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
})

$(function(){
	//上传照片
	$list1 = $('#sspimg_fileList'); 
	var uploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=image',
	    pick: {
	    	id:'#imagePickersspimg',
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

	uploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
					"<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            "</div>"
		            );

		    $list1.html( $li );	
			var newurl=res.url;			
			$('#dangerphoto').val( newurl+"||" );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
});

</script>
</body>
</html>