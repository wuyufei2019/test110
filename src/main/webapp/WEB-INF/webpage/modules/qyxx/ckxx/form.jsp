<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>
     <form id="inputForm" action="${ctx}/bis/ckxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${cklist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson', 
									onSelect : function(rec){	
											 	$.ajax({
											 	url:'${ctx}/bis/wlxx/wlforck',
					 							data:{'qyid':rec.id},
					 							dataType:'json',
					 							type : 'POST',
					 							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 				 			success: function (data){
					 				 			$('#M10').combobox('clear');
						           				$('#M10').combobox('loadData', data); 
						           				}
										    });
										 }
									" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${cklist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',
									 onLoadSuccess: function(){
									 $.ajax({
											 	url:'${ctx}/bis/wlxx/wlforck',
					 							data:{'qyid':${cklist.ID1 }},
					 							dataType:'json',
					 							type : 'POST',
					 							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 				 			success: function (data){
					 				 			$('#M10').combobox('clear');
						           				$('#M10').combobox('loadData', data); 
						           				}
										    });
									 
									if($('#m10Hidden').val() != ''){
									var wlnm=$('#m10Hidden').val().split('|');
									$('#M10').combobox('setValues', wlnm);
											}
									},
									onSelect : function(rec){								
											 	$.ajax({
											 	url:'${ctx}/bis/wlxx/wlforck',
					 							data:{'qyid':rec.id},
					 							dataType:'json',
					 							type : 'POST',
					 							contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					 				 			success: function (data){
						           				}
										    });
										 }
									" /></td>
				</tr>
				</c:if>
				
				<tr >
					<td class="width-15 active"><label class="pull-right">所属仓库区：</label></td>
					<td class="width-35">
						<input name="M13" id="M13" class="easyui-combobox" value="${cklist.m13 }" style="width: 100%;height: 30px;"
							   data-options="editable:false,panelHeight:'150px',valueField: 'text',textField: 'text'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">仓库名称：</label></td>
					<td class="width-35">
					<input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-textbox" value="${cklist.m1 }" data-options="required:'true',validType:'length[0,10]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">仓库编号：</label></td>
					<td class="width-35"><input name="M2" class="easyui-textbox" value="${cklist.m2 }" style="width: 100%;height: 30px;" data-options="validType:['cczu_englishCheckSub','length[0,10]'] "/></td>
					<td class="width-15 active"><label class="pull-right">建筑面积(㎡)：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${cklist.m3 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'number'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">层数：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="M6" value="${cklist.m6 }" style="width: 100%;height: 30px;" data-options="
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
					<td class="width-15 active"><label class="pull-right">建筑结构：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="M5" value="${cklist.m5 }" style="width: 100%;height: 30px;" data-options="
								 editable:false ,panelHeight:'auto' ,data: [
								        {value:'0',text:'钢混结构'},
								        {value:'1',text:'砖混结构'},
								        {value:'2',text:'钢结构'},
								        {value:'3',text:'框架结构'},
								        {value:'5',text:'框排架结构'},
								        {value:'4',text:'其他结构'} ]
						    "/></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">火灾危险等级：</label></td>
					<td class="width-35" colspan="3">
					<input class="easyui-combobox" name="M4" value="${cklist.m4 }" style="width: 100%;height: 30px;" data-options="
								editable:false ,panelHeight:'auto' ,data: [
								        {value:'1',text:'甲类'},
								        {value:'2',text:'乙类'},
								        {value:'3',text:'丙类'},
								        {value:'4',text:'丁类'},
								        {value:'5',text:'戊类'} ]
						    "/>
					</td>
				</tr>
								<tr>
					<td class="width-15 active"><label class="pull-right">占地面积(㎡)：</label></td>
					<td class="width-35"><input name="M8" class="easyui-textbox" value="${cklist.m8 }" style="width: 100%;height: 30px;" data-options=" validType:'number' " /></td>
					<td class="width-15 active"><label class="pull-right">耐火等级：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="M9" value="${cklist.m9 }" style="width: 100%;height: 30px;"
								data-options="editable:false ,data: [
							          {value:'1',text:'一级'},
							        {value:'2',text:'二级'},
							        {value:'3',text:'三级'},
							        {value:'4',text:'四级'}]
	    					" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">储存物料：</label></td>
					<td class="width-35" colspan="3">
					<input type="hidden" id="m10Hidden" value="${cklist.m10}" />
					
					<c:if test="${usertype != '1'}">
					<input name="M10" id="M10" style="width: 100%;height: 30px;" class="easyui-combobox"
								 data-options="editable:false ,panelHeight:'150px', multiple:true,valueField: 'text',textField: 'text',separator:'|',
								 onLoadSuccess: function(){
									if($('#m10Hidden').val() != ''){
									var wlnm=$('#m10Hidden').val().split('|');
									$('#M10').combobox('setValues', wlnm);
											}
									},

					" />
					</c:if>
					<c:if test="${usertype == '1'}">
					<input name="M10" id="M10" style="width: 100%;height: 30px;" class="easyui-combobox"
								 data-options="editable:false ,panelHeight:'150px', multiple:true,valueField: 'text',textField: 'text',url:'${ctx}/bis/wlxx/wlforck',separator:'|',
								 onLoadSuccess: function(){
									if($('#m10Hidden').val() != ''){
									var wlnm=$('#m10Hidden').val().split('|');
									$('#M10').combobox('setValues', wlnm);
											}
									}
									" />					
					</c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${cklist.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[1,250]'">
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ck_m11">
					    <div id="m11fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ck_m12">
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty cklist.ID}">
					<input type="hidden" name="ID" value="${cklist.ID}" />
					<input type="hidden" name="ID1" value="${cklist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${cklist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${cklist.s3}" />
				</c:if>
				</tbody>
			</table>

		  	
       </form>


<script type="text/javascript">
	var usertype=${usertype};
	uploadImgFlag=false;//是否上传图片

	$(function () {
		// 所属仓库区
		$('#M13').combobox({
			url:'${ctx}/bis/ckqxx/json/${qyid}'
		})
	})

	var $ = jQuery,
    $list = $('#m11fileList'); //图片上传
	$list2 = $('#m12fileList'); //文件上传
	
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');
			
			$('#uploader_ck_m11').append( $input );
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
			
			$('#uploader_ck_m12').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var zpUrl = '${cklist.m11}';
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

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
				$('#uploader_ck_m11').append( $input );
			}
		}
		
		var fjUrl = '${cklist.m12}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_ck_m12').append( $input );
			}
		}
		isuploadImg();
	}
	
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
		if(uploadImgFlag==false){
			layer.open({title: '提示',offset: 'auto',content: '未上传现场照片，请上传！',shade: 0 ,time: 2000 });
			return;
		}
		$('#M10').combobox('setValue', $('#M10').combobox('getText'));
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

	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M11']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
</script>


</body>
</html>