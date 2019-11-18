<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全操作规程</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
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
</head>
<body>
     <form id="inputForm" action="${ctx}/zdgl/czgc/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">规程编号：</label></td>
					<td class="width-35" colspan="3">
						<input id="M2" name="M2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${czgc.m2 }" data-options="required:'true',validType:'length[0,100]'" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">规程名称：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${czgc.m1 }" data-options="required:'true',validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">版本号：</label></td>
					<td class="width-35"><input id="M3" name="M3" style="width: 100%;height: 30px;" class="easyui-textbox" value="${czgc.m3 }" data-options="validType:'length[0,100]'"/></td>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><input id="M4" name="M4" class="easyui-datebox" style="width: 100%;height: 30px;" value="${czgc.m4 }" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">规程正文：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M5" class="easyui-textbox" value="${czgc.m5 }"  data-options="multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
					<input type="hidden" id="M6" name="M6" value="${czgc.m6}" />
					<div id="uploader_czgc_m6">
						    <div id="m6fileList" class="uploader-list" >
							    <c:if test="${not empty czgc.m6}">
								<c:set var="url" value="${fn:split(czgc.m6, '||')}" />
									<div style="margin-bottom: 10px;">
									<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
									</div>
								</c:if>
						    </div>
						    <div id="insertfilebtM6">选择文件</div>
					</div>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">编辑部门：</label></td>
					<td class="width-35">
						<input id="M7" name="M7" style="width: 100%;height: 30px;" class="easyui-combobox" value="${czgc.m7 }" 
							data-options="validType:'length[0,250]',panelHeight:'150px',editable:false,multiple:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">适用部门：</label></td>
					<td class="width-35">
						<input id="M8" name="M8" style="width: 100%;height: 30px;" class="easyui-combobox" value="${czgc.m8 }" 
							data-options="validType:'length[0,250]',panelHeight:'150px',editable:false,multiple:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'"/>
					</td>
				</tr>
				
				<c:if test="${action != 'create'}">
					<c:if test="${type eq 'sh'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${czgc.m10=='0'}">
										<input type="radio" value="1" class="i-checks" name="M10" />同意
										<input type="radio" value="0" class="i-checks"  name="M10" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M10" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M10" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="2" />
					</c:if>
					
					<c:if test="${type eq 'pz'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<input class="easyui-textbox" style="width: 100%;height: 30px;" value="同意" data-options="readonly:'true'"/>
							</td>
							<td class="width-15 active"><label class="pull-right">领导批准意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${czgc.m13=='0'}">
										<input type="radio" value="1" class="i-checks" name="M13" />同意
										<input type="radio" value="0" class="i-checks"  name="M13" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M13" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M13" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="3" />
						<input type="hidden" name="M9" value="${czgc.m9}" />
						<input type="hidden" name="M10" value="${czgc.m10}" />
						<input type="hidden" name="M11"
							value="<fmt:formatDate value="${czgc.m11}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</c:if>
				</c:if>
				
				<c:if test="${not empty czgc.ID}">
					<input type="hidden" name="ID" value="${czgc.ID}" />
					<input type="hidden" name="ID1" value="${czgc.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${czgc.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="S3" value="${czgc.s3}" />
					<input type="hidden" name="userid" value="${czgc.userid}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
     var $ = jQuery,
     $list = $('#m6fileList'); //文件上传
     
     var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=file',		
	    pick: {
	    	id:'#insertfilebtM6',
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
		            "</div>"
		            );		    
		    $list.html( $li );
			var newurl=res.url+"||"+res.fileName;
			$('#M6').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
</script>
</body>
</html>