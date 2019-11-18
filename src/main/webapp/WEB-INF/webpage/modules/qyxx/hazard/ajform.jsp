<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
</head>
<body>
	<div style="width:850px;height:auto;padding:5px 5px; margin: 0 auto;"
		closed="true">
		<form id="inputForm" class="form-horizontal" action="${ctx}/bis/hazard/${action}" method="post">
			<table class="table table-bordered dataTable" style="margin:auto;">
				<tr>
					<td class="width-15 active"><label class="pull-right">企业档案R值：</label></td>
					<td class="width-35"><div>
							<input name="M2" id="bis_hazard_M2" class="easyui-textbox"
								value="${qylist.m2 }" style="width:100%;height:30px;"
								data-options="validType:['cczu_numberDecimalsCheck']" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">重大危险源级别：</label></td>
					<td class="width-35"><div>
							<input class="easyui-combobox" id="bis_hazard_M1" name="M1"
								value="${qylist.m1 }" style="width:100%;height:30px;"
								data-options="editable:false ,data: [
								        {value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'} ]" />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">增加暴露人数：</label></td>
					<td><input id="M9_1" name="M9_1" value="${qylist.m9_1 }"
						class="easyui-numberbox" style="width:100%;height:30px;"
						data-options="min:0,validType:'length[1,10]'" /></td>
										<td class="width-15 active"><label class="pull-right">厂区人数：</label></td>
					<td class="width-35"><div>
							<input name="M3" class="easyui-numberbox" value="${qylist.m3 }"
								style="width:100%;height:30px;"
								data-options="validType:'length[0,10]',min:0" />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">系统计算R值：</label></td>
					<td class="width-35"><div>
							<input class="easyui-textbox" value="${qylist.m9 }"
								style="width:100%;height:30px;" data-options="disabled:true" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">是否有监控预警系统：</label></td>
					<td class="width-35"><div>
							<input class="easyui-combobox" name="M4" value="${qylist.m4 }"
								style="width:100%;height:30px;"
								data-options="panelHeight:50, editable:false ,data: [
								        {value:'0',text:'否'},
								        {value:'1',text:'是'} ]
						    " />
						</div></td>
				</tr>
				<tr>


					<td class="width-15 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-35" colspan="3"><div>
							<input class="easyui-combobox" name="M5" id="hcc"
								value="${qylist.m5 }" style="width:100%;height:30px;"
								data-options="
								required:true,
								editable:false ,
								valueField: 'text',
								textField: 'text',
								multiple:'true',
								url:'${ctx}/tcode/dict/wxhxpzywx',
								onSelect: 
								function(rec){
								$('#haz').val($('#hcc').combobox('getValues').join(','));
								},
								onLoadSuccess: 
								function(){
								var hazx=$('#haz').val().split(',');
								$('#hcc').combobox('setValues', hazx);
								}
								 " />
						</div></td>

				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35"><div>
							<input name="M6" class="easyui-textbox" value="${qylist.m6 }"
								style="width:100%;height:30px;"
								data-options="validType:['length[0,50]','cczu_CHSENG']" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">联系电话：</label></td>
					<td class="width-35"><div>
							<input name="M7" class="easyui-textbox" value="${qylist.m7 }"
								style="width:100%;height:30px;"
								data-options="validType:'cczu_mobileTelephone' " />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">易导致事故类型： </label></td>
					<td class="width-35" align="left" colspan="3">
					<input type="checkbox" id="ydlx1" name="M10" class="i-checks" value="1" title="" lay-skin="primary" />物体打击 
					<input type="checkbox" id="ydlx2" name="M10" class="i-checks" value="2" title="" lay-skin="primary" />车辆伤害 
					<input type="checkbox" id="ydlx3" name="M10" class="i-checks" value="3" title="" lay-skin="primary" />机械伤害
					<input type="checkbox" id="ydlx4" name="M10" class="i-checks" value="4" title="" lay-skin="primary" />起重伤害 
					<input type="checkbox" id="ydlx5" name="M10" class="i-checks" value="5" title="" lay-skin="primary" />触电 
					<input type="checkbox" id="ydlx6" name="M10" class="i-checks" value="6" title="" lay-skin="primary" />淹溺 
					<input type="checkbox" id="ydlx7" name="M10" class="i-checks" value="7" title="" lay-skin="primary" />灼烫
					<input type="checkbox" id="ydlx8" name="M10" class="i-checks" value="8" title="" lay-skin="primary" />火灾 
					<input type="checkbox" id="ydlx9" name="M10" class="i-checks" value="9" title="" lay-skin="primary" />高处坠落 </br>
					<input type="checkbox" id="ydlx12" name="M10" class="i-checks" value="12" title="" lay-skin="primary" />透水
					<input type="checkbox" id="ydlx10" name="M10" class="i-checks" value="10" title="" lay-skin="primary" />坍塌
					<input type="checkbox" id="ydlx11" name="M10" class="i-checks" value="11" title="" lay-skin="primary" />冒顶片帮 
					<input type="checkbox" id="ydlx13" name="M10" class="i-checks" value="13" title="" lay-skin="primary" />放炮 
					<input type="checkbox" id="ydlx14" name="M10" class="i-checks" value="14" title="" lay-skin="primary" />火药爆炸
					<input type="checkbox" id="ydlx15" name="M10" class="i-checks" value="15" title="" lay-skin="primary" />瓦斯爆炸 
					<input type="checkbox" id="ydlx16" name="M10" class="i-checks" value="16" title="" lay-skin="primary" />锅炉爆炸 
					<input type="checkbox" id="ydlx17" name="M10" class="i-checks" value="17" title="" lay-skin="primary" />容器爆炸 
					<input type="checkbox" id="ydlx18" name="M10" class="i-checks" value="18" title="" lay-skin="primary" />其它爆炸</br>
					<input type="checkbox" id="ydlx19" name="M10" class="i-checks" value="19" title="" lay-skin="primary" />中毒和窒息 
					<input type="checkbox" id="ydlx20" name="M10" class="i-checks" value="20" title="" lay-skin="primary" />其它伤害</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m11">
					    <div id="m11fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全监控措施：</label></td>
					<td class="width-35" colspan="3"><input type="text" name="M8"
						value="${qylist.m8 }" class="easyui-textbox"
						style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]'" />
					</td>

				</tr>
				<c:if test="${not empty qylist.ID}">
					<input type="hidden" name="ID" value="${qylist.ID}" />
					<input type="hidden" name="ID1" value="${qylist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${qylist.s3}" />
				</c:if>
				<input type="hidden" id="haz" name="haz" value="${qylist.m5}" />
			</table>
		</form>
	</div>
	<div style="display:none">
		<shiro:hasAnyRoles name="企业">
			<input type="hidden" id="bis_qy_qx" value="qy" />
		</shiro:hasAnyRoles>
	</div>
	<script type="text/javascript">
	var usertype=${usertype};
	uploadImgFlag=false;//是否上传图片
	
	var $ = jQuery,
    $list = $('#m11fileList'); //图片上传
	
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
    	isuploadImg();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		        $img = $li.find('img');

		    $list.append( $li );
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');
			
			$('#uploader_ws_m11').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
 
	if('${action}' == 'ajupdate'){
		var zpUrl = '${qylist.m11}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
				$('#uploader_ws_m11').append( $input );
			}
		}
		isuploadImg();
	}
	
	
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
		var qylist = "${qylist.m10}";
		var qylistArr = qylist.split(",");
		for (var i = 0; i < qylistArr.length; i++) {
			$("input[name='M10']:checkbox[value='" + qylistArr[i] + "']").attr('checked', 'true');
		}
		$(function() {
			$('#bis_hazard_M2').textbox({  
				  onChange: function(value){  
					  var r = value;
						if (r < 10) {
							$("#bis_hazard_M1").combobox("setValue", "4");
						} else if (10 <= r && r < 50) {
							$("#bis_hazard_M1").combobox("setValue", "3");
						} else if (50 <= r && r < 100) {
							$("#bis_hazard_M1").combobox("setValue", "2");
						} else {
							$("#bis_hazard_M1").combobox("setValue", "1");
						}
				  }  
			}); 
		});
		function doSubmit(){
			if(uploadImgFlag==false){
				layer.open({title: '提示',offset: 'auto',content: '未上传现场照片，请上传！',shade: 0 ,time: 2000 });
				return;
			}
			$("#inputForm").submit(); 
		}
		$(function(){
			var flag=true;
			$('#inputForm').form({    
			    onSubmit:function(){    
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
			$("#bis_hazard_M1").combobox('readonly',true);
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