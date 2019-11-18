<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全备案管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

<form id="inputForm" action="${ctx}/aqjg/aqba/${action}"  method="post" class="form-horizontal" >
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
		<c:if test="${usertype != '1' and action eq 'create'}">
			<tr>
				<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
				<td class="width-35" colspan="3">
					<input value="${aqbalist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
						   class="easyui-combobox"
						   data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
				</td>
			</tr>
			<shiro:hasAnyRoles name="company,companyadmin">
			</shiro:hasAnyRoles>
		</c:if>
		<c:if test="${usertype != '1' and action eq 'update'}">
			<tr >
				<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
				<td class="width-35" colspan="3">
					<input value="${aqbalist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
						   class="easyui-combobox"
						   data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
			</tr>
		</c:if>
		<tr>
			<td class="width-15 active"><label class="pull-right">备案编号：</label></td>
			<td class="width-35"><input name="M1" class="easyui-textbox" value="${aqbalist.m1 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,100]'" /></td>
			<td class="width-15 active"><label class="pull-right">类别：</label></td>
			<td class="width-35"><input id="M3" name="M3" class="easyui-combobox" value="${aqbalist.m3 }" style="width: 100%;height: 30px;"
										data-options="required:true ,valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/balb'
								"/></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">备案日期：</label></td>
			<td class="width-35"><input id="aqjg_aqba_form_mainform_M2" name="M2" value="${aqbalist.m2 }" style="width: 100%;height: 30px;" class="easyui-datebox" data-options="editable:false,required:'true' "/></td>
			<td class="width-15 active"><label class="pull-right">编制日期：</label></td>
			<td class="width-35"><input id="ruletime" name="ruletime" value="${aqbalist.ruletime }" style="width: 100%;height: 30px;" class="easyui-datebox" data-options="editable:false,required:'true' " /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">中介公司名称：</label></td>
			<td class="width-35"><input id="agency" name="agency" class="easyui-textbox" value="${aqbalist.agency }" style="width: 100%;height: 30px;"
										data-options=""/></td>
			<td class="width-15 active"><label class="pull-right">到期日期：</label></td>
			<td class="width-35"><input id="expiration" name="expiration" style="width: 100%;height: 30px;" class="easyui-datebox" value="<fmt:formatDate value="${aqbalist.expiration }"/>"  data-options="editable:false,required:'true' " /></td>
		</tr>
		<tr>
			<td class="width-15 active"><label class="pull-right">经手人：</label></td>
			<td class="width-35" colspan="3"><input name="M7" class="easyui-textbox" value="${aqbalist.m7 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">备注：</label></td>
			<td class="width-85" colspan="3">
				<input name="M8" type="text" value="${aqbalist.m8 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,500]'">
			</td>

		</tr>

		<tr>
			<td class="width-15 active"><label class="pull-right">附件：</label></td>
			<td class="width-35" colspan="3">
				<div id="uploader_ws_m6">
					<div id="m6fileList" class="uploader-list" ></div>
					<div id="filePicker">选择文件</div>
				</div>
			</td>
		</tr>

		<shiro:hasAnyRoles name="admin,ajcountyadmin">
			<input type="hidden" id="M4" name="M4" value="1" />
		</shiro:hasAnyRoles>

		<shiro:hasAnyRoles name="company,companyadmin">
			<input type="hidden" id="M4" name="M4" value="0" />
		</shiro:hasAnyRoles>

		<c:if test="${not empty aqbalist.ID}">
			<input type="hidden" name="ID" value="${aqbalist.ID}" />
			<input type="hidden" id="ID1" name="ID1" value="${aqbalist.ID1 }" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${aqbalist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
		</c:if>
		</tbody>
	</table>

</form>

<script type="text/javascript">
	var usertype=${usertype};

	var $ = jQuery,
			$list2 = $('#m6fileList'); //文件上传

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

			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M6" value="'+newurl+'"/>');

			$('#uploader_ws_m6').append( $input );
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
		var fjUrl = '${aqbalist.m6}';
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

				var $input = $('<input id="input_'+id+'" type="hidden" name="M6" value="'+arry[i]+'"/>');
				$('#uploader_ws_m6').append( $input );
			}
		}
	}


</script>
</body>
</html>