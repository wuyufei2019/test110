<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js?v=1.2"></script>
<title></title>

</head>
<body>
		<form id="inputForm" action="${ctx}/bis/tzsbxx/${action}" method="post">
			<table class="table table-bordered dataTable" style="margin:auto;">
			<tbody>
			<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3"><div>
							<input value="${qylist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></div>
						</td>
					</tr>
			 </c:if>
			 <c:if test="${usertype != '1' and action eq 'update'}">
			    	<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${qylist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height:30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			  </c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35" colspan="3"><div>
							<input name="M1" style="width: 100%;height: 30px;" class="easyui-textbox easyui-validatebox" value="${qylist.m1 }" data-options="required:'true',validType:'length[0,30]'" />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-35"><div>
							<input name="M2" class="easyui-textbox easyui-validatebox" value="${qylist.m2 }" style="width: 100%;height: 30px;" data-options="required:'true',validType:['length[0,15]','cczu_englishCheckSub']" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-35"><div>
							<input name="M3" class="easyui-combobox" value="${qylist.m3 }" style="width: 100%;height: 30px;"
								data-options="required:'true',
								editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/tzsblx' " />
						</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规格型号：</label></td>
					<td class="width-35"><div>
							<input name="M4" class="easyui-textbox" value="${qylist.m4 }" style="width: 100%;height: 30px;" data-options="validType:['cczu_englishCheckSub','length[0,15]']" />
						</div></td>
					<td class="width-15 active"><label class="pull-right">出厂年月：</label></td>
					<td><input name="M15" class="easyui-datebox" value="${qylist.m15}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">生产厂名：</label></td>
					<td class="width-35" ><div>
							<input name="M16" class="easyui-textbox" value="${qylist.m16 }" style="width: 100%;height: 30px;" data-options="multiline:true,validType:'Length[0,50]'" />
						</div>
					</td>
					<td class="width-15 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-35" ><div>
							<input name="M17" class="easyui-textbox" value="${qylist.m17 }" style="width: 100%;height: 30px;" data-options="multiline:true,validType:'Length[0,25]'" />
						</div>
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">注册代码：</label></td>
					<td class="width-35" ><div>
							<input name="M18" class="easyui-textbox" value="${qylist.m18 }" style="width: 100%;height: 30px;" data-options="multiline:true,validType:'Length[0,25]'" />
						</div>
					</td>					
					<td class="width-15 active"><label class="pull-right">始用年月：</label></td>
					<td><input name="M14" class="easyui-datebox" value="${qylist.m14}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">上次审验：</label></td>
					<td><input name="M9" class="easyui-datebox" value="${qylist.m9}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">下次审验：</label></td>
					<td><input name="M10" class="easyui-datebox" value="${qylist.m10}" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>	
					<td class="width-15 active"><label class="pull-right">使用部门：</label></td>
					<td class="width-35" ><input name="M19" value="${qylist.m19}" type="text" class="easyui-combobox"  style="width: 100%;height: 30px;"
						data-options="panelHeight:'150px', required:'true',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson' "/></td>
					<td class="width-15 active"><label class="pull-right">使用地点：</label></td>
					<td class="width-35" ><div>
							<input name="M20" class="easyui-textbox" value="${qylist.m20 }" style="width: 100%;height: 30px;" data-options="multiline:true,validType:'Length[0,50]'" />
						</div>
					</td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m12">
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><div>
						<input type="text" name="M8" value="${qylist.m8 }" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]'"/>
					</div></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">相关信息：</label></td>
					<td class="width-35" colspan="3"><div>
						<input type="text" name="M21" value="${qylist.m21 }" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]'"/>
					</div></td>
				</tr>
				
				<c:if test="${not empty qylist.ID}">
					<input type="hidden" name="ID" value="${qylist.ID}" />
					<input type="hidden" name="ID1" value="${qylist.ID1}" />
					<input type="hidden" name="S1"
						value='<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
					<input type="hidden" name="S3" value="${qylist.s3}" />
				</c:if>
				</tbody>
			</table>
		</form>
<script type="text/javascript">
	var usertype=${usertype};
	
	var $ = jQuery,
    $list2 = $('#m12fileList'); //文件上传
    
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
	
    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    	isuploadImg();
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
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
			
			$('#uploader_ws_m12').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var fjUrl = '${qylist.m12}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_ws_m12').append( $input );
			}
		}
	}
</script>
</body>
</html>