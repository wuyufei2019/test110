<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>第三方检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/dsfjcjl/index.js?v=1.4"></script>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/dsffw/jcjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">第三方单位名称：</label></td>
					<td class="width-35" colspan="3"><input value="${dsfjcjl.ID2 }" name="ID2" style="width: 100%;height: 30px;"
							class="easyui-combobox"
							data-options="panelHeight:200, editable:true,valueField:'id2',textField:'text',url:'${ctx }/dsffw/fwxmbb/dwnamelist',required:'true'" /></td>
				
				</tr>
				
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">检查企业：</label></td>
						<td class="width-35" colspan="3">
							<input value="${dsfjcjl.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    
			    <c:if test="${usertype != '1' and action eq 'updatecc'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">检查企业：</label></td>
					<td class="width-35" colspan="3">
						<input value="${dsfjcjl.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>
			    
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><input id="SignedStartDate" name="M2" class="easyui-datebox" value="${dsfjcjl.m2 }" style="width: 100%;height: 30px;" data-options="required:'true', editable:true " /></td>
					<td class="width-15 active"><label class="pull-right">整改期限：</label></td>
					<td class="width-35"><input id="SignedEndDate" name="M3" class="easyui-datebox" value="${dsfjcjl.m3 }" style="width: 100%;height: 30px;" data-options="required:'true', editable:true, validType:'equaldDate[\'#SignedStartDate\']' " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${dsfjcjl.m4 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,25]' " /></td>
					<td class="width-15 active"><label class="pull-right">整改负责人：</label></td>
					<td class="width-35"><input name="M5" class="easyui-textbox" value="${dsfjcjl.m5 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,25]' " /></td>
				</tr>

				<tr>
				    <td align="center" colspan="4">
						<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addNr()" title="存在问题"><i class="fa fa-plus"></i> 检查情况</a>
					</td>	
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">检查问题：</label></td>
					<td class="width-35" colspan="3">
						<table id="czwttable" style="width: 90%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
							<tr >
								<td style="text-align: center;width: 30%;  ">问题</td>
								<td style="text-align: center;width: 45%;">图片(点击查看原图)</td>
								<td style="text-align: center;">操作</td>
							</tr>
						</table>	
					</td>
				</tr>				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传检查记录表：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
					    <div id="filePicker1">选择文件</div>
						</div> 
					</td>
				</tr>	

				<input type="hidden" name="wtnum" />
				<c:if test="${not empty dsfjcjl.ID}">
					<input type="hidden" name="ID" value="${dsfjcjl.ID}" />
					<input type="hidden" name="ID1" value="${dsfjcjl.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dsfjcjl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dsfjcjl.s3}" />
				</c:if>
				</tbody>
			</table>
				<input type="hidden" name="M13" value="0" />
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};

	var $ = jQuery,
	$list2 = $('#m7fileList'); //检查文件上传
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker1',
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M7" value="'+newurl+'"/>');
			
			$('#uploader_ws_m7').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	
	
	if('${action}' == 'updatecc'){
		var wtListInit='${czwt}';	
		var fjUrl = '${dsfjcjl.m7}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M7" value="'+arry[i]+'"/>');
				$('#uploader_ws_m7').append( $input );
			}
		}
	}
	
	$(function(){
		if('${action}' == 'updatecc'){
			initData();
		}
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
</body>
</html>