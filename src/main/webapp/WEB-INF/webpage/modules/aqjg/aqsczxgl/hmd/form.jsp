<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>黑名单管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/zxgl/hmd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${hmd.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
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
						<input value="${hmd.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">黑名单行为：</label></td>
					<td class="width-35" colspan="3"><input id="M1" name="M1" class="easyui-combobox" style="width: 100%;height: 30px;"
								value="${hmd.m1 }" data-options="required:'true',data: [
									{value:'0',text:'一年内发生生产安全重大责任事故，或累计发生责任事故死亡10人（含）以上'},
									{value:'1',text:'重大安全生产隐患不及时整改或整改不到位'},
									{value:'2',text:'发生暴力抗法的行为，或未按时完成行政执法指令'},
									{value:'3',text:'发生事故隐瞒不报、谎报或迟报，故意破坏事故现场、毁灭有关证据'},
									{value:'4',text:'无证、证照不全、超层越界开采、超载超限超时运输等非法违法行为'},
									{value:'5',text:'经监管执法部门认定严重威胁安全生产的其他行为'},
									{value:'6',text:'一年内发生较大生产安全责任事故，或累计发生责任事故死亡超过3人（含）以上'},
									{value:'7',text:'一年内发生死亡2人（含）以上的生产安全责任事故，或累计发生责任事故死亡超过2人（含）以上'},
									{value:'8',text:'一年内发生死亡责任事故'}],
								 	onSelect:function(rec){
								 		var value=$('#M1').combobox('getValue');
						           		$('#M3').combobox('clear');
						           		if(Number(value)>3)
						           		{
						           			value='3';
						           		}
            							$('#M3').combobox('setValue',value);
        							}"  /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">黑名单行为等级：</label></td>
					<td class="width-35"><input id="M3" name="M3" class="easyui-combobox" value="${hmd.m3 }"
								style="width: 100%;height: 30px;"
								data-options="readonly:true,validType:'length[0,20]',data: [
									{value:'0',text:'国家'},
									{value:'1',text:'省级'},
									{value:'2',text:'市（地）级'},
									{value:'3',text:'县（区）级'}]"  /></td>
					<td class="width-15 active"><label class="pull-right">黑名单时间起：</label></td>
					<td class="width-35"><input name="M5" class="easyui-datebox" value="${hmd.m5 }"
								style="width: 100%;height: 30px;" data-options="required:'true',validType:'length[0,20]'" /></td>
				</tr>

				<tr >  
					<td class="width-15 active"><label class="pull-right">黑名单行为描述：</label></td>
					<td class="width-85" colspan="3"><input name="M2" type="text" value="${hmd.m2 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"></td> 
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M7" type="text" value="${hmd.m7 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true">
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
				<c:if test="${action eq 'update'}">
				<tr>
				    <td class="width-15 active"><label class="pull-right">状态：</label></td>
				    <td class="width-35" colspan="3"><input id="M8" name="M8" class="easyui-combobox" value="${hmd.m8 }"
								style="width: 100%;height: 30px;"
								data-options="panelHeight:'auto' ,editable:false,data: [
									{value:'0',text:'有效'},
									{value:'1',text:'无效'}]"  /></td>
				</tr>
				</c:if>
				<c:if test="${not empty hmd.ID}">
				<input type="hidden" name="S3" value="${hmd.s3 }" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${hmd.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				<input type="hidden" name="ID" value="${hmd.ID}" />
				<input type="hidden" name="ID1" value="${hmd.ID1}" />
				</c:if>
				<tbody>
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
		var fjUrl = '${hmd.m4}';
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