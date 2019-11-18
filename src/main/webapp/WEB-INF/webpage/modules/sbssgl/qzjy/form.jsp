<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbgl/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-25 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$('#m23').combobox('setValue', '');
									$('#m23').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							" />
					</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">部门名称：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">
						<input id="m23" name="m23" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.m23 }" 
							data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/system/department/deptjson'" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m1 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m2 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m20" name="m20" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.m20 }" 
							data-options="panelHeight:'auto',
								data: [
									{'value': '0', 'text': 'A类'},
									{'value': '1', 'text': 'B类'},
									{'value': '2', 'text': 'C类'},
								]" />
					</td>
			<c:if test="${sbtype ne '1' and sbtype ne '0' and action eq 'create'}">
					<td class="width-25 active"><label class="pull-right">是否是特种设备：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m21" name="m21" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.m21 }" 
							data-options="required:'true',editable:false, panelHeight:'auto',
								data: [
									{'value': '0', 'text': '否'},
									{'value': '1', 'text': '是'},
								],
								onSelect: function(row){
									if (row.value == '1') {
										addTzsbInfo();
									} else {
										removeTzsbInfo();
									}
								}" />
					</td>
			</c:if>
				</tr> 
			<c:if test="${sbtype eq '1'}">
				<tr name="tzsb">
					<td class="width-25 active"><label class="pull-right">注册登记日期：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m28" name="m28" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m28 }" data-options="required:'true',editable: false" />
					</td>
					<td class="width-25 active"><label class="pull-right">状态：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m29" name="m29" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m29 }" 
							data-options="required:'true',panelHeight: 'auto',
								data: [
									{value: '0', text: '不完好'},
									{value: '1', text: '完好'}
								]" />
					</td>
				</tr>
				<tr name="tzsb">
					<td class="width-25 active"><label class="pull-right">安装单位：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">
						<input id="m30" name="m30" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m30 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr> 
				<tr name="tzsb">
					<td class="width-25 active"><label class="pull-right">本次检验日期：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m31" name="m31" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m31 }" data-options="required:'true',editable: false" />
					</td>
					<td class="width-25 active"><label class="pull-right">下次检验日期：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m32" name="m32" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m32 }" data-options="required:'true',editable: false" />
					</td>
				</tr> 
			</c:if>
				<tr id="prependTr">
					<td class="width-25 active"><label class="pull-right">规格：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m3 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">型号：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m27" name="m27" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m20 }" data-options="validType:'length[0,25]'" />
					</td>
				</tr> 
				<tr>
					<td class="width-25 active"><label class="pull-right">设备制造商：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m5 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m4 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">购置日期：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m6" name="m6" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m6 }" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">价格：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m7" name="m7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m7 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">系列号：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m9" name="m9" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m9 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">放置地点：</label></td>
					<td class="width-75" colspan="3" style="height: 30px;">
						<input id="m8" name="m8" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m8 }" data-options="validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">电气功率(30KVA)：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m10" name="m10" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m10 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">外形尺寸：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m13" name="m13" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m13 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">加工范围：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m14" name="m14" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m14 }" data-options="validType:'length[0,10]'" />
					</td>
					<td class="width-25 active"><label class="pull-right">重量：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m15" name="m15" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m15 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">启用日期：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m16" name="m16" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m16 }" data-options="required:'true',editable:false" />
					</td>
					<td class="width-25 active"><label class="pull-right">固定资产编号：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="m17" name="m17" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m17 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr> 
                <tr>
                     <td class="width-25 active" rowspan="2"><label class="pull-right">上传设备照片：</label></td>
      				 <td class="width-25">
						<div id="uploader_sb_m22"></div>
						<div id="imagePickerm22">选择正面照片</div>
      				 </td>
      				 <td class="width-25">
						<div id="uploader_sb_m24"></div>
					    <div id="imagePickerm24">选择侧面照片</div>
      				 </td>
      				 <td class="width-25">
						<div id="uploader_sb_m25"></div>
					    <div id="imagePickerm25">选择铭牌照片</div>
      				 </td>
                </tr>
                <tr>
                	<td class="width-25" id="front">
						<div id="m22fileList" class="uploader-list" ></div>
      				 </td>
      				 <td class="width-25" id="side">
						<div id="m24fileList" class="uploader-list" ></div>
      				 </td>
      				 <td class="width-25" id="nameplate">
						<div id="m25fileList" class="uploader-list" ></div>
      				 </td>
                </tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">备注：</label></td>
					<td class="width-75" colspan="3">
						<input style="width:100%;height: 60px;" id="m18"name="m18"  class="easyui-textbox" value="${sbgl.m18 }" data-options="multiline:true,validType:'length[0,1000]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">保管人：</label></td>
					<td class="width-25" style="height: 30px;">
						<input id="bgrid" name="bgrid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.bgrid }" data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
					</td>
				</tr> 
			</tbody>
		</table>	
		<input type="hidden" name="sbtype" value="${sbtype}" />
		<input type="hidden" name="sbjfid" value="${sbgl.sbjfid}" />
		<c:if test="${not empty sbgl.ID}">
			<input type="hidden" name="ID" value="${sbgl.ID}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbgl.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbgl.s3}" />
			<input type="hidden" name="m19" value="${sbgl.m19}" /><!-- 状态 -->
			<input type="hidden" name="m21" value="${sbgl.m21}" /><!-- 设备类别（普通设备还是特种设备） -->
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

function addTzsbInfo(){
   var tr =$('<tr name="tzsbTr">'
                   + '    <td class="width-25 active"><label class="pull-right">注册登记日期：</label></td>'
    				+ '    <td class="width-25"><input id="m28" name="m28" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m28 }" data-options="required:\'true\',editable: false" /></td>'
    				+ '    <td class="width-25 active"><label class="pull-right">状态：</label></td>'
    				+ '    <td class="width-25"><input id="m29" name="m29" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbgl.m29 }"'
    				+ '	   	   data-options="required:\'true\',editable: false,panelHeight: \'auto\',data: [{value: \'0\', text: \'不完好\'},{value: \'1\', text: \'完好\'}]" /></td>'
                   + '</tr>'
                   + '<tr name="tzsbTr">'
                   + '    <td class="width-25 active"><label class="pull-right">安装单位：</label></td>'
    				+ '    <td class="width-75" colspan="3" style="height: 30px;"><input id="m30" name="m30" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbgl.m30 }" data-options="validType:\'length[0,50]\'" /></td>'
                   + '</tr>'
                   + '<tr name="tzsbTr">'
                   + '    <td class="width-25 active"><label class="pull-right">本次检验日期：</label></td>'
    				+ '    <td class="width-25" ><input id="m31" name="m31" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m31 }" data-options="required:\'true\',editable: false" /></td>'
    				+ '    <td class="width-25 active"><label class="pull-right">下次检验日期：</label></td>'
    				+ '    <td class="width-25"><input id="m32" name="m32" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbgl.m32 }" data-options="required:\'true\',editable: false" /></td>'
    				+ '</tr>');
	 $("#prependTr").before(tr);
	 $.parser.parse(tr);	 
}

//删除添加的tr
function removeTzsbInfo(){
	$("[name='tzsbTr']").remove();
}

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

//上传设备正面图片
var $list = $('#m22fileList'); 
var $list1 = $('#m24fileList'); 
var $list2 = $('#m25fileList');
 
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
				"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img>" +
	                "<div class=\"info\">" + file.name + "</div>" +
	            "</div>"
	            );
		$img = $li.find('img');
	    $list.html( $li );	
	    
	    // 创建缩略图
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img.attr( 'src', src );
	    }, 100, 100 );
	    
		var newurl=res.url+"||"+res.fileName;			
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m22" value="'+newurl+'"/>');
		$('#uploader_sb_m22').append( $input );
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

var uploader1 = WebUploader.create({
    auto: true,
    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
    server: '${ctx}/kindeditor/upload?dir=image',
    pick: {
    	id:'#imagePickerm24',
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
uploader1.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

uploader1.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li1 = $(
				"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img>" +
	                "<div class=\"info\">" + file.name + "</div>" +
	            "</div>"
	            );
		$img1 = $li1.find('img');
	    $list1.html( $li1 );	
	    
	    // 创建缩略图
	    uploader1.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img1.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img1.attr( 'src', src );
	    }, 100, 100 );	
		var newurl1=res.url+"||"+res.fileName;
		var $input1 = $('<input id="input_'+file.id+'" type="hidden" name="m24" value="'+newurl1+'"/>');
		
		$('#uploader_sb_m24').append( $input1 );
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

var uploader2 = WebUploader.create({
    auto: true,
    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
    server: '${ctx}/kindeditor/upload?dir=image',
    pick: {
    	id:'#imagePickerm25',
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
uploader2.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

uploader2.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li2 = $(
				"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img>" +
	                "<div class=\"info\">" + file.name + "</div>" +
	            "</div>"
	            );
		$img2 = $li2.find('img');
	    $list2.html( $li2 );
	    
	    // 创建缩略图
	    uploader2.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img2.attr( 'src', src );
	    }, 100, 100 );
	    	
		var newurl2=res.url+"||"+res.fileName;			
		
		var $input2 = $('<input id="input_'+file.id+'" type="hidden" name="m25" value="'+newurl2+'"/>');
		$('#uploader_sb_m25').append( $input2 );
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
   	//正面图片
   	var imgUrl = '${sbgl.m22}';
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
	
	//侧面图片
   	var imgUrl1 = '${sbgl.m24}';
	if(imgUrl1!=null&&imgUrl1!=''){
		arry =imgUrl1.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ws_img_"+i;
			var $li1 = $(
		            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list1.append( $li1 );
		    var $input1 = $('<input id="input_'+id+'" type="hidden" name="m24" value="'+arry[i]+'"/>');
			$('#uploader_sb_m24').append( $input1 );
		}
	}
	
	//铭牌图片
   	var imgUrl2 = '${sbgl.m25}';
	if(imgUrl2!=null&&imgUrl2!=''){
		arry =imgUrl2.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ws_img_"+i;
			var $li2 = $(
		            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list2.append( $li2 );
		    var $input2 = $('<input id="input_'+id+'" type="hidden" name="m25" value="'+arry[i]+'"/>');
			$('#uploader_sb_m25').append( $input2 );
		}
	}
}
</script>
</body>
</html>