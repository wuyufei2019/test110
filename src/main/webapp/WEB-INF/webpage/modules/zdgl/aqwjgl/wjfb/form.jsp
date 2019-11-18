<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件发布</title>
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
     <form id="inputForm" action="${ctx}/zdgl/wjfb/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35" colspan="3">
						<input id="M2" name="M2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${wjfb.m2 }" data-options=" " />
					</td>
				</tr>
			  	<tr >
					<td class="width-15 active"><label class="pull-right">文件名称：</label></td>
					<td class="width-35" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${wjfb.m1 }" data-options="required:'true',validType:'length[0,100]'" />
					</td>
				</tr>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">类别：</label></td>
					<td class="width-35"><input id="M3" name="M3" class="easyui-combobox" style="width: 100%;height: 30px;" value="${wjfb.m3 }" data-options="panelHeight:'150px',editable:false,data: [
										{value:'1',text:'国家总局'},
								        {value:'2',text:'省局'},
								        {value:'3',text:'市局'},
								        {value:'4',text:'区县级'},
								        {value:'5',text:'行业'},
								        {value:'6',text:'主管部门'},
								        {value:'7',text:'公司'},
								        {value:'8',text:'部门'},
								        {value:'9',text:'其他'}]" /></td>
					<td class="width-15 active"><label class="pull-right">性质：</label></td>
					<td class="width-35"><input id="M4" name="M4" class="easyui-combobox" style="width: 100%;height: 30px;" value="${wjfb.m4 }" data-options="panelHeight:'150px',editable:false,data: [
										{value:'1',text:'转发'},
								        {value:'2',text:'内部'},
								        {value:'3',text:'规章'}]" /></td>
				</tr>  
				<tr>
					<td class="width-15 active"><label class="pull-right">发送部门：</label></td>
						<td class="width-35" colspan="3">
							<input id="M13" name="M13" style="width: 100%;height: 30px;" class="easyui-combobox" value="${wjfb.m13 }" 
								data-options="required:'true',validType:'length[0,250]',panelHeight:'150px',editable:false,multiple:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">文件内容：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M5" class="easyui-textbox" value="${wjfb.m5 }"  data-options="multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
					<input type="hidden" id="M6" name="M6" value="${wjfb.m6}" />
					<div id="uploader_wjfb_m6">
						    <div id="m6fileList" class="uploader-list" >
							    <c:if test="${not empty wjfb.m6}">
								<c:set var="url" value="${fn:split(wjfb.m6, '||')}" />
									<div style="margin-bottom: 10px;">
									<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
									</div>
								</c:if>
						    </div>
						    <div id="insertfilebtM6">选择文件</div>
					</div>
					</td>
				</tr>
				
				<c:if test="${action != 'create'}">
					<c:if test="${type eq 'sh'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${wjfb.m8=='0'}">
										<input type="radio" value="1" class="i-checks" name="M8" />同意
										<input type="radio" value="0" class="i-checks"  name="M8" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M8" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M8" />不同意
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
									<c:when test="${wjfb.m11=='0'}">
										<input type="radio" value="1" class="i-checks" name="M11" />同意
										<input type="radio" value="0" class="i-checks"  name="M11" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M11" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M11" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="3" />
						<input type="hidden" name="M7" value="${wjfb.m7}" />
						<input type="hidden" name="M8" value="${wjfb.m8}" />
						<input type="hidden" name="M9"
							value="<fmt:formatDate value="${wjfb.m9}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</c:if>
				</c:if>
				
				<c:if test="${not empty wjfb.ID}">
					<input type="hidden" name="ID" value="${wjfb.ID}" />
					<input type="hidden" name="ID1" value="${wjfb.ID1}" />
					<input type="hidden" name="userid" value="${wjfb.userid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${wjfb.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="S3" value="${wjfb.s3}" />
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