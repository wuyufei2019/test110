<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用使用管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/aqsctr/fysy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${fysy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${fysy.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">日期：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M1" required="true" editable="false" name="M1" class="easyui-datebox" value="${fysy.m1 }"  />
					</td>
					<td class="width-20 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" required="true" id="ID2" name="ID2" class="easyui-combobox" value="${fysy.ID2 }" 
						data-options="validType:'length[0,250]',panelHeight:'120px',editable:true,multiple:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'" />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">支出项目类别：</label></td>
					<td class="width-30" colspan="3">
					<input class="easyui-combotree" id="M2" name="M2" value="${fysy.m2 }" style="width: 100%;height: 30px;"
								data-options="method:'get', panelHeight:'120px',
								editable:false ,url:'${ctx}/aqsctr/fyys/zclxjson' " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">具体用途：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${fysy.m3 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">金额（万元）：</label></td>
					<td class="width-30"><input required="true" style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${fysy.m4 }" data-options="validType:['number','length[0,10]'] " /></td>
					<td class="width-20 active"><label class="pull-right">经办人：</label></td>
					<td class="width-30" >
						<input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-combobox" value="${fysy.m5 }" data-options="panelHeight:'120px', valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
					</td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30" >
						<input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-combobox" value="${fysy.m6 }" data-options="panelHeight:'120px', valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
					</td>
					<td class="width-20 active"><label class="pull-right">批准人：</label></td>
					<td class="width-30" >
						<input style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-combobox" value="${fysy.m7 }" data-options="panelHeight:'120px', valueField: 'text',textField: 'text',url:'${ctx}/system/compuser/userjson'"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3"><input id="M8" name="M8" class="easyui-textbox" style="width: 100%;height: 80px;" value="${fysy.m8 }" data-options="multiline:true, editable:true,validType:['length[0,1000]']  " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">票据附件（图片）：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m9">
					    <div id="m9fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<c:if test="${not empty fysy.ID}">
					<input type="hidden" name="ID" value="${fysy.ID}" />
					<input type="hidden" name="ID1" value="${fysy.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${fysy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${fysy.s3}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	$list = $('#m9fileList'); //图片上传
	
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
	    }
	});
	
	uploader.on( 'uploadProgress', function( file, percentage ) {
			$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
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
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
    	if('${action}' == 'update'){
		var zpUrl = '${fysy.m9}';
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
	}

function doSubmit(){
	$("#inputForm").serializeObject();
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
</script>
</body>
</html>