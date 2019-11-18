<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事件管理</title>
	<meta name="decorator" content="default"/>
	<%--  <script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>  --%>
</head>
<body>
     <form id="inputForm" action="${ctx}/aqjg/sjgl/${action}"  method="post" class="form-horizontal" >
     <input type="hidden" name="ID" value="${aie.ID} ">
	 <input type="hidden" name="S1" value="<fmt:formatDate value="${aie.s1}" pattern="yyyy-MM-dd HH:mm:ss" />" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
					<tr>
						<td class="width-20 active"><label class="pull-right">发生单位：</label></td>
						<td class="width-30" colspan="3">
							<input value="${aie.m2 }" id="_qyid" name="M2" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
				<shiro:hasAnyRoles name="company,companyadmin">
				</shiro:hasAnyRoles>
				<tr>
					<td class="width-20 active"><label class="pull-right">发生日期：</label></td>
					<td class="width-30"><input id="aqjg_sjgl_form_mainform_M1" name="M1" data-options="required:'true'" style="width: 100%;height: 30px;" class="easyui-datebox" value="<fmt:formatDate value="${aie.m1 }"/>" style="width:198px;" /></td>
					<td class="width-20 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-30" ><input name="M3" class="easyui-textbox" value="${aie.m3 }" style="width: 100%;height: 30px;"  /></td>
					<%-- <td class="width-30"><input id="M3" name="M3" class="easyui-combobox" value="${aie.m3 }" style="width: 100%;height: 30px;"
								data-options="required:true ,valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/balb' ,
								onSelect : function(rec){
									 $('#balb').val(rec.text);
								}
								
								"/></td> --%>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故类型：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M4" value="${aie.m4 }" style="width: 100%;height: 30px;" data-options="
								editable:false ,panelHeight:130 , required:'true',data: [
								        {value:'wtdj',text:'物体打击'},
								        {value:'zt',text:'灼烫'},
								        {value:'wsbz',text:'瓦斯爆炸'},
								        {value:'clsh',text:'车辆伤害'},
								        {value:'hz',text:'火灾'},
								        {value:'hybz',text:'火药爆炸'},
								        {value:'jxsh',text:'机械伤害'},
								        {value:'gczl',text:'高处坠落'},
								        {value:'glbz',text:'锅炉爆炸'},
								        {value:'qzsh',text:'起重伤害'},
								        {value:'tt',text:'坍塌'},
								        {value:'rqbz',text:'容器爆炸'},
								        {value:'cd',text:'触电'},
								        {value:'mdpb',text:'冒顶片帮'},
								        {value:'qtbz',text:'其他爆炸'},
								        {value:'yn',text:'淹溺'},
								        {value:'ts',text:'透水'},
								        {value:'zdhzx',text:'中毒和窒息'},
								        {value:'fp',text:'放炮'},
								        {value:'qtsh',text:'其他伤害'} ]
						    "/></td>
					<td class="width-20 active"><label class="pull-right">事故等级：</label></td>
					<td class="width-30"><input class="easyui-combobox" name="M5" value="${aie.m5 }" style="width: 100%;height: 30px;" data-options="
								editable:false ,panelHeight:130 ,required:'true', data: [
								     {value:'0',text:'特别重大事故'},
								        {value:'1',text:'重大事故'},
								        {value:'2',text:'较大事故'},
								        {value:'3',text:'一般事故'} ]"/></td>
				</tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">死亡人数：</label></td>
					<td class="width-30" ><input name="M6" class="easyui-textbox" value="${aie.m6 }"  data-options="validType:'number'" style="width: 100%;height: 30px;"  /></td>
					<td class="width-20 active"><label class="pull-right">重伤人数：</label></td>
					<td class="width-30" ><input name="M7" class="easyui-textbox " value="${aie.m7 }"  data-options="validType:'number'"style="width: 100%;height: 30px;"  /></td>
				</tr>
				<tr><td class="width-20 active"><label class="pull-right">轻伤人数：</label></td>
					<td class="width-80" ><input name="M8" class="easyui-textbox" value="${aie.m8 }"  data-options="validType:'number'" style="width: 100%;height: 30px;"  /></td></tr>
				<tr>
				<td class="width-20 active"><label class="pull-right">直接经济损失(万元)：</label></td>
					<td class="width-30" ><input name="M9" class="easyui-textbox" value="${aie.m9 }" data-options="validType:'number'" style="width: 100%;height: 30px;"  /></td>
					<td class="width-20 active"><label class="pull-right">间接经济损失(万元)：</label></td>
					<td class="width-30" ><input name="M10" class="easyui-textbox" value="${aie.m10 }" data-options="validType:'number'" style="width: 100%;height: 30px;"  /></td>
				</tr>
                <tr>
					<td class="width-20 active"><label class="pull-right">事故描述：</label></td>
					<td class="width-80" colspan="3">
					<input name="M11" type="text" value="${aie.m11 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,250]'">
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故处理：</label></td>
					<td class="width-80" colspan="3">
					<input name="M12" type="text" value="${aie.m12 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,250]'">
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">事故预防对策：</label></td>
					<td class="width-80" colspan="3">
					<input name="M13" type="text" value="${aie.m13 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,250]'">
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" colspan="3">
					<input name="M14" type="text" value="${aie.m14 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true ,validType:'length[0,250]'">
					</td>
				</tr>
				  <tr>
					<td class="width-20 active"><label class="pull-right">附件（事故调查）：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="filePicker1">选择文件</div>
					</div> 
					</td>
				</tr>
					<tr>
					<td class="width-20 active"><label class="pull-right">附件（事故处理）：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>  
				<%-- 
				<shiro:hasAnyRoles name="admin,ajcountyadmin">
					<input type="hidden" id="M4" name="M4" value="1" />
				</shiro:hasAnyRoles> --%>
			
				<%-- <shiro:hasAnyRoles name="company,companyadmin">
					<input type="hidden" id="M4" name="M4" value="0" />
				</shiro:hasAnyRoles> --%>
				
			<%-- 	<input type="hidden" id="balb" name="balb" />
				<c:if test="${not empty aie.ID}">
					<input type="hidden" name="ID" value="${aie.ID}" />
					<input type="hidden" id="M1" name="M1" value="${aie.m1}" />
					<input type="hidden" id="ID1" name="ID1" value="${aie.ID1 }" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${aie.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
				</c:if> --%>
				<tbody>
			</table>
		  	
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var $ = jQuery,
	$list1 = $('#m15fileList'); //文件上传
	$list2 = $('#m16fileList');
	
	var fileuploader1 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker1',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader1.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
		var fileuploader2 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader2.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 文件上传成功 1
	fileuploader1.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"F1" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile1('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list1.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input1_'+file.id+'" type="hidden" name="M15" value="'+newurl+'"/>');
			
			$('#uploader_ws_m15').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	// 文件上传成功 2
	fileuploader2.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"F2" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile2('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input2_'+file.id+'" type="hidden" name="M16" value="'+newurl+'"/>');
			
			$('#uploader_ws_m16').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	// 负责预览图的销毁
    function removeFile1(fileid) {
    	$("#F1"+fileid).remove();
    	$("#input1_"+fileid).remove();
    };
	 function removeFile2(fileid) {
    	$("#F2"+fileid).remove();
    	$("#input2_"+fileid).remove();
    };
       
	if('${action}' == 'update'){
		var fjUrl = '${aie.m15}';
		var fjUrl2 = '${aie.m16}';
		if(fjUrl!=null&&fjUrl!=''&&fjUrl!='null'){
			 var arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"F1" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile1('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list1.append( $li );
			    
			    var $input = $('<input id="input1_'+id+'" type="hidden" name="M15" value="'+arry[i]+'"/>');
				$('#uploader_ws_m15').append( $input );
			}
		}
			if(fjUrl2!=null&&fjUrl2!=''&&fjUrl2!='null'){
			var arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"F2" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile2('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input2_'+id+'" type="hidden" name="M16" value="'+arry[i]+'"/>');
				$('#uploader_ws_m16').append( $input );
			}
		}
	}
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;

		function doSubmit() {
		$("#inputForm").serializeObject();
		$("#inputForm").submit();
		}
		$(function() {
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
					return isValid; // 返回false终止表单提交
				},
				success : function(data) {
					if (data == 'success')
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '操作成功！',
							shade : 0,
							time : 2000
						});
					else
						parent.layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				}
			});
		});
	</script>
</body>
</html>