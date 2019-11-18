<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>特殊作业报备管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/dw/zysp/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">作业方式：</label></td>
					<td class="width-35" colspan="3"><input class="easyui-combobox" name="M1" value="${zysp.m1 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto', editable:false , multiple:true , required:true ,data: [
								        {value:'1',text:'动火作业'},
								        {value:'2',text:'受限空间作业'},
								        {value:'3',text:'管道拆卸作业'},
								        {value:'4',text:'盲板抽堵作业'},
								        {value:'5',text:'高处安全作业'},
								        {value:'6',text:'吊装安全作业'},
								        {value:'7',text:'临时用电安全作业'},
								        {value:'8',text:'动土安全作业'},
								        {value:'9',text:'断路安全作业'},
								        {value:'10',text:'其他作业'}]"/></td>				
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">作业类型：</label></td>
					<td class="width-35" ><input class="easyui-combobox" name="M20" value="${zysp.m20 }" style="width: 100%;height: 30px;" data-options="
								panelHeight:'auto', editable:false , required:true ,data: [
								        {value:'1',text:'特种作业'},
								        {value:'2',text:'一般作业'}]"/></td>
					<td class="width-15 active"><label class="pull-right">作业级别：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M2" class="easyui-combobox " value="${zysp.m2 }" data-options="panelHeight:'auto',editable:false , required:true ,data: [
								        {value:'1',text:'特级'},
								        {value:'2',text:'一级'},
								        {value:'3',text:'二级'},
								        {value:'4',text:'其他'}]"/></td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">作业队伍：</label></td>
					<td class="width-35">
						<input class="easyui-combobox" name="M17" value="${zysp.m17 }" style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto',
								editable:false ,required:'true',data: [
								       {value:'1',text:'外包施工队'},
								       {value:'2',text:'本场人员'}]" />
					</td>
					<td class="width-15 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-35">
						<input style="width: 100%;height: 30px;" name="M3" class="easyui-textbox" value="${zysp.m3 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M7" class="easyui-datetimebox" value="${zysp.m7 }" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-15 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M8" class="easyui-datetimebox" value="${zysp.m8 }" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业负责人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M4" class="easyui-textbox " value="${zysp.m4 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">作业人员：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M5" class="easyui-textbox" value="${zysp.m5 }" data-options="min:0,max:999999"/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">第三方服务公司：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M18" class="easyui-textbox " value="${zysp.m18 }" data-options="validType:'length[0,50]'"/></td>
					<td class="width-15 active"><label class="pull-right">第三方负责人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M19" class="easyui-textbox" value="${zysp.m19 }" data-options="validType:'length[0,50]'"/></td>
				</tr>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-35" colspan="3">
						<input name="M6" type="text" value="${zysp.m6 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>

				<tr> 
					<td class="width-15 active"><label class="pull-right">危险因素：</label></td>
					<td colspan="3">
									<table>
										<tr>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard1" name="M9" value="1" /><a style="cursor:default;" onclick="hazardChange1()">易燃易爆物质</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard2" name="M9" value="2" /><a style="cursor:default;" onclick="hazardChange2()">坠落</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard3" name="M9" value="3" /><a style="cursor:default;" onclick="hazardChange3()">腐蚀性物质</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard4" name="M9" value="4" /><a style="cursor:default;" onclick="hazardChange4()">蒸汽</a></td>
										</tr>
										<tr>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard5" name="M9" value="5" /><a style="cursor:default;" onclick="hazardChange5()">高压气体/液体</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard6" name="M9" value="6" /><a style="cursor:default;" onclick="hazardChange6()">有毒有害物质</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard7" name="M9" value="7" /><a style="cursor:default;" onclick="hazardChange7()">高温/低温</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard8" name="M9" value="8" /><a style="cursor:default;" onclick="hazardChange8()">触电</a></td>
										</tr>
										<tr>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard9" name="M9" value="9" /><a style="cursor:default;" onclick="hazardChange9()">窒息</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard10" name="M9" value="10" /><a style="cursor:default;" onclick="hazardChange10()">噪音</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard11" name="M9" value="11" /><a style="cursor:default;" onclick="hazardChange11()">产生火花/静电</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard12" name="M9" value="12" /><a style="cursor:default;" onclick="hazardChange12()">旋转设备</a></td>
										</tr>
										<tr>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard13" name="M9" value="13" /><a style="cursor:default;" onclick="hazardChange13()">机械伤害</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard14" name="M9" value="14" /><a style="cursor:default;" onclick="hazardChange14()">粉尘</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard15" name="M9" value="15" /><a style="cursor:default;" onclick="hazardChange15()">不利天气</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard16" name="M9" value="16" /><a style="cursor:default;" onclick="hazardChange16()">淹没/埋没</a></td>
										</tr>
										<tr>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard17" name="M9" value="17" /><a style="cursor:default;" onclick="hazardChange17()">辐射</a></td>
											<td style="border-width:0px;height:15px;"><input type="checkbox" id="hazard18" name="M9" value="18" /><a style="cursor:default;" onclick="hazardChange18()">交叉作业</a></td>
											<td style="border-width:0px;height:15px;" colspan="2"><input type="checkbox" id="hazard19" name="M9" value="19" /><a style="cursor:default;" onclick="hazardChange19()">其他：</a>
											<input style="width: 100%;height: 30px;" name="M10" class="easyui-textbox" value="${zysp.m10 }" data-options="validType:'length[0,75]'"/></td>
										</tr>
									</table>
								</td>
							</tr>	
									
				<tr> 
					<td class="width-15 active"><label class="pull-right">易导致事故类型：</label></td><td colspan="3"><input type="checkbox" id="sglx1" name="M11" value="1" /><a style="cursor:default;" onclick="sglxChange1()">物体打击</a>
																		                <input type="checkbox" id="sglx2" name="M11" value="2" /><a style="cursor:default;" onclick="sglxChange2()">车辆伤害</a>
																		                <input type="checkbox" id="sglx3" name="M11" value="3" /><a style="cursor:default;" onclick="sglxChange3()">机械伤害</a>
																		                <input type="checkbox" id="sglx4" name="M11" value="4" /><a style="cursor:default;" onclick="sglxChange4()">起重伤害</a>
																		                <input type="checkbox" id="sglx5" name="M11" value="5" /><a style="cursor:default;" onclick="sglxChange5()">触电</a><br/>
																		                <input type="checkbox" id="sglx6" name="M11" value="6" /><a style="cursor:default;" onclick="sglxChange6()">淹溺</a>
																		                <input type="checkbox" id="sglx7" name="M11" value="7" /><a style="cursor:default;" onclick="sglxChange7()">灼烫</a>
																		                <input type="checkbox" id="sglx8" name="M11" value="8" /><a style="cursor:default;" onclick="sglxChange8()">火灾</a>
																		                <input type="checkbox" id="sglx9" name="M11" value="9" /><a style="cursor:default;" onclick="sglxChange9()">高处坠落</a>
																		                <input type="checkbox" id="sglx10" name="M11" value="10" /><a style="cursor:default;" onclick="sglxChange10()">坍塌</a><br/>
																		                <input type="checkbox" id="sglx11" name="M11" value="11" /><a style="cursor:default;" onclick="sglxChange11()">冒顶片帮</a>
																		                <input type="checkbox" id="sglx12" name="M11" value="12" /><a style="cursor:default;" onclick="sglxChange12()">透水</a>
																		                <input type="checkbox" id="sglx13" name="M11" value="13" /><a style="cursor:default;" onclick="sglxChange13()">放炮</a>
																		                <input type="checkbox" id="sglx14" name="M11" value="14" /><a style="cursor:default;" onclick="sglxChange14()">火药爆炸</a>
																		                <input type="checkbox" id="sglx15" name="M11" value="15" /><a style="cursor:default;" onclick="sglxChange15()">瓦斯爆炸</a><br/>
																		                <input type="checkbox" id="sglx16" name="M11" value="16" /><a style="cursor:default;" onclick="sglxChange16()">锅炉爆炸</a>
																		                <input type="checkbox" id="sglx17" name="M11" value="17" /><a style="cursor:default;" onclick="sglxChange17()">容器爆炸</a>
																		                <input type="checkbox" id="sglx18" name="M11" value="18" /><a style="cursor:default;" onclick="sglxChange18()">其它爆炸</a>
																		                <input type="checkbox" id="sglx19" name="M11" value="19" /><a style="cursor:default;" onclick="sglxChange19()">中毒和窒息</a>
																		                <input type="checkbox" id="sglx20" name="M11" value="20" /><a style="cursor:default;" onclick="sglxChange20()">其它伤害</a></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">作业票：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_dw_m12">
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">作业方案：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_dw_m13">
					    <div id="m13fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">应急方案：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_dw_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="filePicker3">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">人员证件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_dw_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="filePicker4">选择文件</div>
					</div> 
					</td>
				</tr>
				

				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_dw_m14">
					    <div id="m14fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>			
			<c:if test="${not empty zysp.ID}">
					<input type="hidden" name="ID" value="${zysp.ID}" />
					<input type="hidden" name="ID1" value="${zysp.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${zysp.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${zysp.s3}" />
				</c:if>
				<tbody>
			</table>

       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
$list1 = $('#m12fileList'); //文件上传
$list2 = $('#m13fileList'); //文件上传
$list3 = $('#m14fileList'); //图片上传
$list4 = $('#m15fileList'); //文件上传
$list5 = $('#m16fileList'); //文件上传
	//作业票
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
	
	//作业方案
	var fileuploader2 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader2.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	//应急方案
	var fileuploader3 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker3',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader3.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	//人员证件
	var fileuploader4 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker4',
	    	multiple : false,
	    },
	    duplicate :true  
	});

	fileuploader4.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	//现场照片
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
	
		    
		    $list1.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
			
			$('#uploader_dw_m12').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	

	// 文件上传成功 
	fileuploader2.on( 'uploadSuccess', function( file ,res) {
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M13" value="'+newurl+'"/>');
			
			$('#uploader_dw_m13').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	

	// 文件上传成功 
	fileuploader3.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list4.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M15" value="'+newurl+'"/>');
			
			$('#uploader_dw_m15').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	
	
		// 文件上传成功 
	fileuploader4.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list5.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M16" value="'+newurl+'"/>');
			
			$('#uploader_dw_m16').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	
	
	
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

		    $list3.append( $li );
	
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
	
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M14" value="'+newurl+'"/>');
			
			$('#uploader_dw_m14').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
function doSubmit(){
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

	var wxys = "${wxys}";
	var wxysArr = wxys.split(",");
	for(var i=0;i<wxysArr.length;i++){
		$("input[name='M9']:checkbox[value='"+wxysArr[i]+"']").attr('checked','true');
	}
	
	var M9 = "${zysp.m9}";
	var hiArr = M9.split(",");
	for(var i=0;i<hiArr.length;i++){
		$("#hazard"+hiArr[i]).attr('checked','true');
	}
	$(function(){ 
		$("input[name='M9']:checkbox").css("width","18px");
		$("input[name='M9']:checkbox").css("height","18px");
		$("input[name='M11']:checkbox").css("width","18px");
		$("input[name='M11']:checkbox").css("height","18px");
	});

	function hazardChange1(){
		$('#hazard1').prop('checked',!$("#hazard1").prop("checked"));
	}
	function hazardChange2(){
		$('#hazard2').prop('checked',!$("#hazard2").prop("checked"));
	}
	function hazardChange3(){
		$('#hazard3').prop('checked',!$("#hazard3").prop("checked"));
	}
	function hazardChange4(){
		$('#hazard4').prop('checked',!$("#hazard4").prop("checked"));
	}
	function hazardChange5(){
		$('#hazard5').prop('checked',!$("#hazard5").prop("checked"));
	}
	function hazardChange6(){
		$('#hazard6').prop('checked',!$("#hazard6").prop("checked"));
	}
	function hazardChange7(){
		$('#hazard7').prop('checked',!$("#hazard7").prop("checked"));
	}
	function hazardChange8(){
		$('#hazard8').prop('checked',!$("#hazard8").prop("checked"));
	}
	function hazardChange9(){
		$('#hazard9').prop('checked',!$("#hazard9").prop("checked"));
	}
	function hazardChange10(){
		$('#hazard10').prop('checked',!$("#hazard10").prop("checked"));
	}
	function hazardChange11(){
		$('#hazard11').prop('checked',!$("#hazard11").prop("checked"));
	}
	function hazardChange12(){
		$('#hazard12').prop('checked',!$("#hazard12").prop("checked"));
	}
	function hazardChange13(){
		$('#hazard13').prop('checked',!$("#hazard13").prop("checked"));
	}
	function hazardChange14(){
		$('#hazard14').prop('checked',!$("#hazard14").prop("checked"));
	}
	function hazardChange15(){
		$('#hazard15').prop('checked',!$("#hazard15").prop("checked"));
	}
	function hazardChange16(){
		$('#hazard16').prop('checked',!$("#hazard16").prop("checked"));
	}
	function hazardChange17(){
		$('#hazard17').prop('checked',!$("#hazard17").prop("checked"));
	}
	function hazardChange18(){
		$('#hazard18').prop('checked',!$("#hazard18").prop("checked"));
	}
	function hazardChange19(){
		$('#hazard19').prop('checked',!$("#hazard19").prop("checked"));
	}


	var sglx = "${sglx}";
	var sglxArr = sglx.split(",");
	for(var i=0;i<sglxArr.length;i++){
		$("input[name='M11']:checkbox[value='"+sglxArr[i]+"']").attr('checked','true');
	}	
	function sglxChange1(){
		$('#sglx1').prop('checked',!$("#sglx1").prop("checked"));
	}
	function sglxChange2(){
		$('#sglx2').prop('checked',!$("#sglx2").prop("checked"));
	}
	function sglxChange3(){
		$('#sglx3').prop('checked',!$("#sglx3").prop("checked"));
	}
	function sglxChange4(){
		$('#sglx4').prop('checked',!$("#sglx4").prop("checked"));
	}
	function sglxChange5(){
		$('#sglx5').prop('checked',!$("#sglx5").prop("checked"));
	}
	function sglxChange6(){
		$('#sglx6').prop('checked',!$("#sglx6").prop("checked"));
	}
	function sglxChange7(){
		$('#sglx7').prop('checked',!$("#sglx7").prop("checked"));
	}
	function sglxChange8(){
		$('#sglx8').prop('checked',!$("#sglx8").prop("checked"));
	}
	function sglxChange9(){
		$('#sglx9').prop('checked',!$("#sglx9").prop("checked"));
	}
	function sglxChange10(){
		$('#sglx10').prop('checked',!$("#sglx10").prop("checked"));
	}
	function sglxChange11(){
		$('#sglx11').prop('checked',!$("#sglx11").prop("checked"));
	}
	function sglxChange12(){
		$('#sglx12').prop('checked',!$("#sglx12").prop("checked"));
	}
	function sglxChange13(){
		$('#sglx13').prop('checked',!$("#sglx13").prop("checked"));
	}
	function sglxChange14(){
		$('#sglx14').prop('checked',!$("#sglx14").prop("checked"));
	}
	function sglxChange15(){
		$('#sglx15').prop('checked',!$("#sglx15").prop("checked"));
	}
	function sglxChange16(){
		$('#sglx16').prop('checked',!$("#sglx16").prop("checked"));
	}
	function sglxChange17(){
		$('#sglx17').prop('checked',!$("#sglx17").prop("checked"));
	}
	function sglxChange18(){
		$('#sglx18').prop('checked',!$("#sglx18").prop("checked"));
	}
	function sglxChange19(){
		$('#sglx19').prop('checked',!$("#sglx19").prop("checked"));
	}
	function sglxChange20(){
		$('#sglx20').prop('checked',!$("#sglx20").prop("checked"));
	}

	if('${action}' == 'update'){		

		var fjUrl = '${zysp.m12}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list1.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_dw_m12').append( $input );
			}
		}

		var fjUrl = '${zysp.m13}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M13" value="'+arry[i]+'"/>');
				$('#uploader_dw_m13').append( $input );
			}
		}
		
		var fjUrl = '${zysp.m15}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list4.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M15" value="'+arry[i]+'"/>');
				$('#uploader_dw_m15').append( $input );
			}
		}
		
		var fjUrl = '${zysp.m16}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list5.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M16" value="'+arry[i]+'"/>');
				$('#uploader_dw_m16').append( $input );
			}
		}
		
		var zpUrl = '${zysp.m14}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ck_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );

			    $list3.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M14" value="'+arry[i]+'"/>');
				$('#uploader_dw_m14').append( $input );
			}
		}		
	}
</script>
</body>
</html>