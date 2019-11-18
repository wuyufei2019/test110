<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>职业病体检</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/occupillexam/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${zybtj.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${zybtj.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">体检日期：</label></td>
					<td class="width-30"><input name="M1" type="text" class="easyui-datebox" value="<fmt:formatDate value="${zybtj.m1}" pattern="yyyy-MM-dd HH:mm:ss"  />" style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
					<td class="width-20 active"><label class="pull-right">体检人数：</label></td>
					<td class="width-30"><input name="M3" class="easyui-textbox" value="${zybtj.m3 }" style="width: 100%;height: 30px;" data-options="  validType:'number'" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">体检类型：</label></td>
					<td class="width-85" colspan="3">
					<input class="easyui-combobox" name="M6" value="${zybtj.m6 }" style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto',
								editable:false ,required:'true',data: [
								       {value:'岗前',text:'岗前'},
								       {value:'岗中',text:'岗中'},
								       {value:'离岗',text:'离岗'},] , " />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检医院：</label></td>
					<td class="width-85" colspan="3">
					<input name="M2" class="easyui-combobox" value="${zybtj.m2 }" style="width: 100%;height: 30px;" data-options=" validType:['length[0,50]','cczu_CHS']" />
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">体检结论：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${zybtj.m4 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'">
					</td>					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${zybtj.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options=" multiline:true, validType:'length[0,250]'">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_zybtj_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty zybtj.ID}">
					<input type="hidden" name="ID" value="${zybtj.ID}" />
					<input type="hidden" name="ID1" value="${zybtj.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${zybtj.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${zybtj.s3}" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	
	var $ = jQuery,
    $list = $('#m5fileList'); //图片上传
	
		
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
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
	
		    
		    $list.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_zybtj_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var fjUrl = '${zybtj.m5}';
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
			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_zybtj_m5').append( $input );
			}
		}
	}
	
	
</script>
</body>
</html>