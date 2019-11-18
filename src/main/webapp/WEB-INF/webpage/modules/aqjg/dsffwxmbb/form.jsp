<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方服务项目报备管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/fwxmbb/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${bb.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${bb.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">单位名称：</label></td>
					<td class="width-35" colspan="3"><input value="${bb.ID2 }" name="ID2" style="width: 100%;height: 30px;"
							class="easyui-combobox"
							data-options="editable:false,valueField:'id2',textField:'text',url:'${ctx }/dsffw/fwxmbb/dwnamelist',required:'true'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">项目类型：</label></td>
					<td class="width-35" colspan="3"><input name="M1" class="easyui-textbox" style="width: 100%;height: 30px;"
							value="${bb.m1 }" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">项目名称：</label></td>
					<td class="width-35" colspan="3"><input name="M2" class="easyui-textbox" value="${bb.m2 }"
							style="width: 100%;height: 30px;"
							data-options="required:'true',validType:'length[0,20]'" /></td>
				</tr>
				<tr>
                    <td class="width-15 active"><label class="pull-right">服务项目内容：</label></td>
					<td class="width-35" colspan="3"><input name="M3" class="easyui-textbox" value="${bb.m3 }"
							style="width: 100%;height: 60px;"
							data-options="validType:'length[0,100]',multiline:true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35"><input id="SignedStartDate" name="M6" class="easyui-datebox" value="${bb.m6 }"
							style="width: 100%;height: 30px;" /></td>
					<td class="width-15 active"><label class="pull-right">结束时间：</label></td>
					<td class="width-35"><input id="SignedEndDate" name="M7" class="easyui-datebox" value="${bb.m7 }"
							style="width: 100%;height: 30px;"
							data-options="validType:'equaldDate[\'#SignedStartDate\']'" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">项目负责人：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${bb.m4 }"
							style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>
					<td class="width-15 active"><label class="pull-right">项目合同资金(万元)：</label></td>
					<td class="width-35"><input name="M5" class="easyui-numberbox" value="${bb.m5 }"
							style="width: 100%;height: 30px;"  /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传文件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m8">
					    <div id="m8fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
			<c:if test="${not empty bb.ID}">
				<input type="hidden" name="S3" value="${bb.s3 }" /> 
				<input type="hidden" name="S1" value="<fmt:formatDate value="${bb.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID" value="${bb.ID}" />
				<input type="hidden" name="ID1" value="${bb.ID1}" />
			</c:if>
			</tbody>
			</table>

		  	
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list2 = $('#m8fileList'); //文件上传
	
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M8" value="'+newurl+'"/>');
			
			$('#uploader_ws_m8').append( $input );
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
		var fjUrl = '${bb.m8}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M8" value="'+arry[i]+'"/>');
				$('#uploader_ws_m8').append( $input );
			}
		}
	}
	
	
	</script>
</body>
</html>