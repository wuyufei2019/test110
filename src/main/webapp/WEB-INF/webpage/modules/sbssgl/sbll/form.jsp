<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备履历</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbll/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbll.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$($('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson/'+row.id))
								}" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" >
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbll.m2 }" 
							data-options="required:'true',valueField:'text',textField:'text',url:'${ctx}/sbssgl/sbgl/sbjson/',
								onSelect: function(row){
									$.ajax({
										type: 'post',
										url: '${ctx}/sbssgl/sbgl/sbinfojson/'+row.id,
										success: funciton(data){
											console.log(data);
										}
									})
								}" />
					</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m1 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" >
						<input id="m20" name="m20" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m20 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >
						<input id="m21" name="m21" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m21 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" >
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m3 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m4 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-20 active"><label class="pull-right">设备制造商：</label></td>
					<td class="width-30" >
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m5 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">购置日期：</label></td>
					<td class="width-30" >
						<input id="m6" name="m6" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbll.m6 }" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">价格：</label></td>
					<td class="width-30" >
						<input id="m7" name="m7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m7 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">系列号：</label></td>
					<td class="width-30" >
						<input id="m9" name="m9" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m9 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">放置地点：</label></td>
					<td class="width-80" colspan="3">
						<input id="m8" name="m8" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m8 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">电气功率(30KVA)：</label></td>
					<td class="width-30" >
						<input id="m10" name="m10" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m10 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">用气量(m3/min)：</label></td>
					<td class="width-30" >
						<input id="m11" name="m11" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m11 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">用水量：</label></td>
					<td class="width-30" >
						<input id="m12" name="m12" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m12 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">外形尺寸：</label></td>
					<td class="width-30" >
						<input id="m13" name="m13" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m13 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">加工范围：</label></td>
					<td class="width-30" >
						<input id="m14" name="m14" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m14 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">重量：</label></td>
					<td class="width-30" >
						<input id="m15" name="m15" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m15 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">启用日期：</label></td>
					<td class="width-30" >
						<input id="m16" name="m16" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbll.m16 }" data-options="required:'true',editable:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">固定资产编号：</label></td>
					<td class="width-30" >
						<input id="m17" name="m17" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbll.m17 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
                     <td class="width-15 active"><label class="pull-right">上传图片：</label></td>
      				 <td class="width-35" colspan="3">
						<div id="uploader_sb_m22"></div>
							<div id="m22fileList" class="uploader-list" ></div>
						    <div id="imagePickerm22">选择图片</div>
						</div>
      				 </td>
                </tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">
						<input style="width:100%;height: 60px;" id="m18"name="m18"  class="easyui-textbox" value="${sbll.m18 }" data-options="multiline:true,validType:'length[0,1000]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">保管人：</label></td>
					<td class="width-30" >
						<input id="bgrid" name="bgrid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbll.bgrid }" data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
					</td>
				</tr> 
			</tbody>
		</table>	
		
		<input type="hidden" name="sbjfid" value="${sbll.sbjfid}" />
		<c:if test="${not empty sbll.ID}">
			<input type="hidden" name="ID" value="${sbll.ID}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbll.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbll.s3}" />
			<input type="hidden" name="m19" value="${sbll.m19}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

function doSubmit(){
	var value = $("#bgrid").combobox('getValue');
	if(value != ''){
		if(!checkcombobox('bgrid')){
			layer.open({title: '提示',offset: 'auto',content: '所选保管人不存在！',shade: 0 ,time: 2000 });
			return;
		}
	}
	$("#inputForm").submit(); 
}

//验证下拉框的值是否有效
function checkcombobox(id){
	var options = $("#"+id).combobox('options');  
 	var data = $("#"+id).combobox('getData');		/* 下拉框所有选项 */  
 	var value = $("#"+id).combobox('getValue');	/* 用户输入的值 */  
 	var b = false;		/* 标识是否在下拉列表中找到了用户输入的字符 */  
 	for (var i = 0; i < data.length; i++) {  
    	if (data[i][options.valueField] == value) {  
        	b=true;  
       	 	break;  
    	}  
	}
 	return b;
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

//上传图片
var $list = $('#m22fileList'); 
var uploader = WebUploader.create({
    auto: true,
    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
    server: '${ctx}/kindeditor/upload?dir=image',
    pick: {
    	id:'#imagePickerm22',
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
            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );

	    $list.html( $li );	
		var newurl=res.url+"||"+res.fileName;			
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m22" value="'+newurl+'"/>');
		$('#uploader_sb_m22').append( $input );
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
   	//图片
   	var imgUrl = '${sbll.m22}';
	if(imgUrl!=null&&imgUrl!=''){
		arry =imgUrl.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ws_img_"+i;
			var $li = $(
		            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list.append( $li );
		    var $input = $('<input id="input_'+id+'" type="hidden" name="m22" value="'+arry[i]+'"/>');
			$('#uploader_sb_m22').append( $input );
		}
	}
}
</script>
</body>
</html>