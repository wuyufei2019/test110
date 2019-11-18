<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>失信行为管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/zxgl/sxjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${sx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
				<shiro:hasAnyRoles name="company,companyadmin">
				</shiro:hasAnyRoles>
			    </c:if>
			    <c:if test="${action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${sx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">失信行为：</label></td>
					<td class="width-35" colspan="3"><input name="M1" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${sx.m1 }"
								data-options="panelHeight:'auto', data: [
									{value:'生产经营单位一年内发生生产安全死亡责任事故',text:'生产经营单位一年内发生生产安全死亡责任事故'},
									{value:'非法违法组织生产经营建设',text:'非法违法组织生产经营建设'},
									{value:'执法检查发现存在重大安全生产隐患、重大职业病危害隐患',text:'执法检查发现存在重大安全生产隐患、重大职业病危害隐患'},
									{value:'未按规定开展企业安全生产标准化建设的或在规定期限内未达到安全生产标准化要求',text:'未按规定开展企业安全生产标准化建设的或在规定期限内未达到安全生产标准化要求'},
									{value:'未建立隐患排查治理制度，不如实记录和上报隐患排查治理情况，期限内未完成治理整改',text:'未建立隐患排查治理制度，不如实记录和上报隐患排查治理情况，期限内未完成治理整改'},
									{value:'拒不执行安全监管监察指令的，以及逾期不履行停产停业、停止使用、停止施工和罚款等处罚',text:'拒不执行安全监管监察指令的，以及逾期不履行停产停业、停止使用、停止施工和罚款等处罚'},
									{value:'未依法依规报告事故、组织开展抢险救援',text:'未依法依规报告事故、组织开展抢险救援'},
									{value:'其他安全生产非法违法或造成恶劣社会影响的行为',text:'其他安全生产非法违法或造成恶劣社会影响的行为'}]" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">失信行为等级：</label></td>
					<td class="width-35"><input name="M3" class="easyui-combobox" value="${sx.m3 }"
								style="width: 100%;height: 30px;"
								data-options="validType:'length[0,20]',panelHeight:'auto', data: [
									{value:'一级',text:'一级'},
									{value:'二级',text:'二级'},
									{value:'三级',text:'三级'},
									{value:'四级',text:'四级'},
									{value:'五级',text:'五级'},
									{value:'六级',text:'六级'}]" /></td>
					<td class="width-15 active"><label class="pull-right">失信时间起：</label></td>
					<td class="width-35"><input name="M5" class="easyui-datebox" value="${sx.m5 }"
								style="width: 100%;height: 30px;" data-options="validType:'length[0,20]'" /></td>
				</tr>

				<tr >  
					<td class="width-15 active"><label class="pull-right">失信行为描述：</label></td>
					<td class="width-85" colspan="3"><input name="M2" type="text" value="${sx.m2 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"></td> 
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${sx.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
					</td>		
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">佐证材料：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m4">
					    <div id="m4fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty sx.ID}">
				<input type="hidden" name="S3" value="${sx.s3 }" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${sx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID" value="${sx.ID}" />
				<input type="hidden" name="ID1" value="${sx.ID1}" />
				</c:if>
				</tbody>
			</table>

       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list2 = $('#m4fileList'); //文件上传
	
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M4" value="'+newurl+'"/>');
			
			$('#uploader_ws_m4').append( $input );
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
		var fjUrl = '${sx.m4}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M4" value="'+arry[i]+'"/>');
				$('#uploader_ws_m4').append( $input );
			}
		}
	}
	
	
	</script>
</body>
</html>