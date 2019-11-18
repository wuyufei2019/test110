<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>车间管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.3"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/cjxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-80" colspan="3">
							<input value="${workshop.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-80" colspan="3">
						<input value="${workshop.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr >
					<td class="width-20 active"><label class="pull-right">车间名称：</label></td>
					<td class="width-80" colspan="3">
						<input type="text" name="M1" class="easyui-textbox" value="${workshop.m1 }"  data-options="required:'true',validType:'length[0,10]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">车间编号：</label></td>
					<td class="width-30"><input name="M2" class="easyui-textbox" value="${workshop.m2 }" style="width: 100%;height: 30px;" data-options="validType:['cczu_englishCheckSub','length[0,10]'] "/></td>
					<td class="width-20 active"><label class="pull-right">占地面积（㎡）：</label></td>
					<td class="width-30"><input name="M3" class="easyui-textbox" value="${workshop.m3 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'mone'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">层数：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M6" value="${workshop.m6 }" style="width: 100%;height: 30px;" data-options="required:'true',
								editable:false ,panelHeight:130 , data: [
								        {value:'1',text:'1层'},
								        {value:'2',text:'2层'},
								        {value:'3',text:'3层'},
								        {value:'4',text:'4层'},
								        {value:'5',text:'5层'},
								        {value:'6',text:'6层'},
								        {value:'7',text:'7层'},
								        {value:'8',text:'8层'},
								        {value:'9',text:'9层'},
								        {value:'10',text:'10层'},
								        {value:'11',text:'11层'},
								        {value:'12',text:'12层'},
								        {value:'13',text:'13层'},
								        {value:'14',text:'14层'},
								        {value:'15',text:'15层'},
								        {value:'16',text:'16层'},
								        {value:'17',text:'17层'},
								        {value:'18',text:'18层'},
								        {value:'19',text:'19层'},
								        {value:'20',text:'20层'} ]
						    "/></td>
					<td class="width-20 active"><label class="pull-right">建筑结构：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M5" value="${workshop.m5 }" style="width: 100%;height: 30px;" data-options="
								 editable:false ,panelHeight:'auto' ,data: [
								        {value:'0',text:'钢混结构'},
								        {value:'1',text:'砖混结构'},
								        {value:'2',text:'钢结构'},
								        {value:'3',text:'框架结构'},
								        {value:'5',text:'框排架结构'},
								        {value:'4',text:'其他结构'} ]
						    "/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">火灾危险等级：</label></td>
					<td class="width-30" ><input class="easyui-combobox" name="M4" value="${workshop.m4 }" style="width: 100%;height: 30px;" data-options="
								editable:false ,panelHeight:110 ,data: [
								        {value:'1',text:'甲类'},
								        {value:'2',text:'乙类'},
								        {value:'3',text:'丙类'},
								        {value:'4',text:'丁类'},
								        {value:'5',text:'戊类'} ]
						    "/></td>
					<td class="width-20 active"><label class="pull-right">耐火等级：</label></td>
					<td class="width-30" >
							<input class="easyui-combobox" name="M8" value="${workshop.m8 }" style="width: 100%;height: 30px;"
								data-options="editable:false ,panelHeight:'auto' ,data: [
							        {value:'1',text:'一级'},
							        {value:'2',text:'二级'},
							        {value:'3',text:'三级'},
							        {value:'4',text:'四级'}]
	    					" />
						</td>
					
				</tr>
				
				
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">
					<input name="M7" type="text" value="${workshop.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[1,250]'">
					</td>
					
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m9">
					    <div id="m9fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws_m10">
					    <div id="m10fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty workshop.ID}">
					<input type="hidden" name="ID" value="${workshop.ID}" />
					<input type="hidden" name="ID1" value="${workshop.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${workshop.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${workshop.s3}" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	uploadImgFlag=false;//是否上传图片
	
	var $ = jQuery,
    $list = $('#m9fileList'); //图片上传
	$list2 = $('#m10fileList'); //文件上传
	
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
	
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=draw',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M9" value="'+newurl+'"/>');
			
			$('#uploader_ws_m9').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(res.message,{time: 3000});
		}
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
	
	
	
	if('${action}' == 'update'){
		var zpUrl = '${workshop.m9}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M9" value="'+arry[i]+'"/>');
				$('#uploader_ws_m9').append( $input );
			}
		}
		
		var fjUrl = '${workshop.m10}';
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
		
		isuploadImg();
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M9']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
	</script>
</body>
</html>