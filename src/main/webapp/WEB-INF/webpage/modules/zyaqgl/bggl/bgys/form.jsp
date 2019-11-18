<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>变更验收管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/bgys/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${bgsq.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${bgsq.ID1 }" id="ID1" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">变更项目：</label></td>
					<td class="width-30" colspan="3">
						<input value="${bgsq.m1 }" id="M1" name="M1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/zyaqgl/bgys/bgxmjson'" />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${bgsq.m2 }" data-options="validType:['length[0,25]'] " /></td>
					<td class="width-20 active"><label class="pull-right">验收日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" editable="false" id="M3" name="M3" class="easyui-datebox" value="${bgsq.m3 }" " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收人员：</label></td>
					<td class="width-30" colspan="3"><input id="M4" name="M4" type="text" value="${bgsq.m4 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="editable:true,multiple:true,panelHeight:100,valueField:'text',textField:'text',url: '${ctx}/zyaqgl/bgys/userjson'"></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">验收意见：</label></td>
					<td class="width-80" colspan="3"><input id="M5" name="M5" class="easyui-textbox" style="width: 100%;height: 80px;" value="${bgsq.m5 }" data-options="multiline:true, editable:true, validType:['length[0,1000]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">验收结论：</label></td>
					<td class="width-30" colspan="3">							<c:choose>
								<c:when test="${bgsq.m6=='0'}">
									<input type="radio" value="1" class="i-checks" name="M6" />合格
									<input type="radio" value="0" class="i-checks"  name="M6" checked="checked" />不合格
								</c:when>
								<c:otherwise>
									<input type="radio" value="1" class="i-checks" name="M6" checked="checked" />合格
									<input type="radio" value="0" class="i-checks"  name="M6" />不合格
								</c:otherwise>
							</c:choose></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">抄送部门：</label></td>
					<td class="width-30" colspan="3"><input id="M7" name="M7" type="text" value="${bgsq.m7 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="editable:false,multiple:true,panelHeight:100,valueField:'text',textField:'text',url: '${ctx}/system/department/deptjson'"></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">记录人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${bgsq.m8 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">记录日期：</label></td>
					<td class="width-30">
					<input style="width: 100%;height: 30px;" editable="false" id="M9" name="M9" class="easyui-datebox" value="${bgsq.m9 }" " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">上传附件：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m10">
					    <div id="m10fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>

				<c:if test="${not empty bgsq.id}">
					<input type="hidden" name="ID" value="${bgsq.id}" />
					<input type="hidden" name="ID1" value="${bgsq.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${bgsq.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${bgsq.s3}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

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
	fileuploader.on( 'uploadSuccess', function( file ,bgsq) {
		$.jBox.closeTip();
		if(bgsq.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+bgsq.url+"')\">"+bgsq.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=bgsq.url+"||"+bgsq.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M10" value="'+newurl+'"/>');
			
			$('#uploader_ws_m10').append( $input );
		}else{
			layer.msg(bgsq.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    
    	if('${action}' == 'update'){
    	$("#M12").val('${czr}');//审核人
    	$("#M15").val('${czr}');//批准人
		var zpUrl = '${bgsq.m14}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M14" value="'+arry[i]+'"/>');
				$('#uploader_ws_m14').append( $input );
			}
		}
		
		var fjUrl = '${bgsq.m10}';
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