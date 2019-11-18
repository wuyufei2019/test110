<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>法律法规库</title>
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
     <form id="inputForm" action="${ctx}/zdgl/flfg/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
                <input type="hidden" id="type" name="type" value="${type}">
			    <tr>
					<td class="width-15 active"><label class="pull-right">大类别：</label></td>
					<td class="width-35"><input id="M1_1" name="M1_1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${flfg.m1_1 }" data-options="required:'true',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'8',text:'国际公约'},
								        {value:'7',text:'其他'}]" /></td>
                    <c:if test="${type==1 || (flfg.ID1!=null && flfg.ID1!='')}">
					<td class="width-15 active"><label class="pull-right">小类别：</label></td>
					<td class="width-35">
					<input id="M1" name="M1" class="easyui-combotree" style="width: 100%;height: 30px;" value="${flfg.m1 }" data-options="required:false,panelHeight:'150px',editable:false,url: '${ctx}/zdgl/flfg/treelist'" /></td>
                    </c:if>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">法律法规名称：</label></td>
					<td class="width-35" colspan="3">
						<input id="M2" name="M2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${flfg.m2 }" data-options="required:'true',validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">颁布单位：</label></td>
					<td class="width-35"><input id="M3" name="M3" style="width: 100%;height: 30px;" class="easyui-textbox" value="${flfg.m3 }" data-options="validType:'length[0,100]'"/></td>
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35"><input id="M4" name="M4" style="width: 100%;height: 30px;" class="easyui-textbox" value="${flfg.m4 }" data-options="validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><input id="M5" name="M5" class="easyui-datebox" style="width: 100%;height: 30px;" value="${flfg.m5 }" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-35" ><input id="M6" name="M6" class="easyui-datebox" style="width: 100%;height: 30px;" value="${flfg.m6 }" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">摘要：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M7" class="easyui-textbox" value="${flfg.m7 }"  data-options="multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">附件（单文件）：</label></td>
					<td colspan="3">
					<input type="hidden" id="M8" name="M8" value="${flfg.m8}" />
					<div id="uploader_flfg_m8">
						    <div id="m8fileList" class="uploader-list" >
							    <c:if test="${not empty flfg.m8}">
								<c:set var="url" value="${fn:split(flfg.m8, '||')}" />
									<div style="margin-bottom: 10px;">
									<a style="color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;" onclick="window.open('${url[0]}')">${url[1]}</a>
									</div>
								</c:if>
						    </div>
						    <div id="insertfilebtM8">选择文件</div>
					</div>
					</td>
				</tr>
				<c:if test="${not empty flfg.ID}">
					<input type="hidden" name="ID" value="${flfg.ID}" />
					<input type="hidden" name="ID1" value="${flfg.ID1}" />
					<input type="hidden" name="M10" value="${flfg.m10}" />
					<input type="hidden" name="M11" value="${flfg.m11}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${flfg.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="S3" value="${flfg.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
     var $ = jQuery,
     $list = $('#m8fileList'); //文件上传

     var fileuploader = WebUploader.create({
	    auto: true,
	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
	    server: '${ctx}/kindeditor/upload?dir=file',		
	    pick: {
	    	id:'#insertfilebtM8',
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
			$('#M8').val( newurl );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
</script>
</body>
</html>