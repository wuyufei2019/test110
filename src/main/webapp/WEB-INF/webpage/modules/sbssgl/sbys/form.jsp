<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备验收单</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbys/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">设备类型：</label></td>
					<td class="width-30" >
						<input id="m1" name="m1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbys.m1 }" data-options="required:'true',editable:false,panelHeight:'auto',data: [
										{value:'生产设备/厂务设施',text:'生产设备/厂务设施'},
								        {value:'产品量测/检验仪器',text:'产品量测/检验仪器'},
								        {value:'其它',text:'其它'}]" />
					</td>
					<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30" >
						<input id="m2" name="m2" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m2 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">验收日期：</label></td>
					<td class="width-30" >
						<input id="m3" name="m3" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbys.m3 }" data-options="required:'true',editable:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30" >
						<input id="m4" name="m4" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m4 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">规格/型号：</label></td>
					<td class="width-30" >
						<input id="m5" name="m5" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m5 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">出厂编号：</label></td>
					<td class="width-30" >
						<input id="m6" name="m6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m6 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">设备制造商/代理商：</label></td>
					<td class="width-30" >
						<input id="m7" name="m7" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m7 }" data-options="validType:'length[0,50]'" />
					</td>
					<td class="width-20 active"><label class="pull-right">出厂日期：</label></td>
					<td class="width-30" >
						<input id="m8" name="m8" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbys.m8 }" data-options="required:'true',editable:false" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">放置地点：</label></td>
					<td class="width-80" colspan="3">
						<input id="m9" name="m9" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m9 }" data-options="validType:'length[0,50]'" />
					</td>
				</tr>
			</tbody>
		</table>	
		
		<p style="margin-top: 10px;text-align: center;font-size: 17px;color: dodgerblue;">
			<strong>硬件验收</strong>
		</p>
		<table style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 25%;">项目</td>
				<td style="text-align: center;width: 10%;">定购数量</td>
				<td style="text-align: center;width: 10%;">实交数量</td>
				<td style="text-align: center;width: 20%;">外观</td>
				<td style="text-align: center;width: 35%;">说明</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">主件</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m10" name="m10" class="easyui-numberbox" value="${sbys.m10 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m11" name="m11" class="easyui-numberbox" value="${sbys.m11 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 20%;padding: 4px;">
					<input type="text" id="m12" name="m12" class="easyui-textbox" value="${sbys.m12 }"  style="height: 30px;width: 188px;" data-options="validType:'length[0,50]'"/>
				</td>
				<td style="text-align: center;width: 40%;padding: 4px;">
					<input type="text" id="m13" name="m13" class="easyui-textbox" value="${sbys.m13 }"  style="height: 30px;width: 334px;" data-options="validType:'length[0,100]'"/>
				</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">附件</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m14" name="m14" class="easyui-numberbox" value="${sbys.m14 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m15" name="m15" class="easyui-numberbox" value="${sbys.m15 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 20%;padding: 4px;">
					<input type="text" id="m16" name="m16" class="easyui-textbox" value="${sbys.m16 }"  style="height: 30px;width: 188px;" data-options="validType:'length[0,50]'"/>
				</td>
				<td style="text-align: center;width: 40%;padding: 4px;">
					<input type="text" id="m17" name="m17" class="easyui-textbox" value="${sbys.m17 }"  style="height: 30px;width: 334px;" data-options="validType:'length[0,100]'"/>
				</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">技术手册、设备资料</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m18" name="m18" class="easyui-numberbox" value="${sbys.m18 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m19" name="m19" class="easyui-numberbox" value="${sbys.m19 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 20%;padding: 4px;">
					<input type="text" id="m20" name="m20" class="easyui-textbox" value="${sbys.m20 }"  style="height: 30px;width: 188px;" data-options="validType:'length[0,50]'"/>
				</td>
				<td style="text-align: center;width: 40%;padding: 4px;">
					<input type="text" id="m21" name="m21" class="easyui-textbox" value="${sbys.m21 }"  style="height: 30px;width: 334px;" data-options="validType:'length[0,100]'"/>
				</td>
			</tr>
			<tr >
				<td style="text-align: center;width: 20%;padding: 4px;">随机工具</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m22" name="m22" class="easyui-numberbox" value="${sbys.m22 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 10%;padding: 4px;">
					<input type="text" id="m23" name="m23" class="easyui-numberbox" value="${sbys.m23 }"  style="height: 30px;width: 90px;" data-options="min:0"/>
				</td>
				<td style="text-align: center;width: 20%;padding: 4px;">
					<input type="text" id="m24" name="m24" class="easyui-textbox" value="${sbys.m24 }"  style="height: 30px;width: 188px;" data-options="validType:'length[0,50]'"/>
				</td>
				<td style="text-align: center;width: 40%;padding: 4px;">
					<input type="text" id="m25" name="m25" class="easyui-textbox" value="${sbys.m25 }"  style="height: 30px;width: 334px;" data-options="validType:'length[0,100]'"/>
				</td>
			</tr>
		</table>	
		<p style="margin-top: 10px;text-align: center;">
			<a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="addRw()" title="添加功能验收"><i class="fa fa-plus"></i> 添加功能验收</a>
		</p>
		
		<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
			<tr >
				<td style="text-align: center;width: 45%;">测试项目</td>
				<td style="text-align: center;width: 45%;">测试结果</td>
				<td style="text-align: center;width: 10%;">操作</td>
			</tr>
		</table>	
		
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			    <%-- <tr>
					<td class="width-20 active"><label class="pull-right">保修开始日期：</label></td>
					<td class="width-30" >
						<input id="m26" name="m26" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbys.m26 }" data-options="editable:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">保修结束日期：</label></td>
					<td class="width-30" >
						<input id="m27" name="m27" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbys.m27 }" data-options="editable:false" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">保修证书：</label></td>
					<td class="width-80" colspan="3">
						<input id="m28" name="m28" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbys.m28 }" data-options="validType:'length[0,100]'" />
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">保修收费条件：</label></td>
					<td class="width-80" colspan="3">
						<input id="m29" name="m29" class="easyui-textbox" style="width: 100%;height: 60px;" value="${sbys.m29 }" data-options="multiline:true,validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检验条件：</label></td>
					<td class="width-80" colspan="3">
						<input type="radio" name="m30" style="margin-bottom: 6px;" value="1"> 不需校验
						<input type="radio" name="m30" style="margin-bottom: 6px;margin-left: 20px;" value="2"> 需校验
                    </td>	
				</tr>
				<tr id="z1">
					<td class="width-20 active"><label class="pull-right">是否新购买设备：</label></td>
					<td class="width-80" colspan="3">
						<input type="radio" name="m31" style="margin-bottom: 6px;" value="1"> 不选择
						<input type="radio" name="m31" style="margin-bottom: 6px;margin-left: 20px;" value="2"> 选择
                    </td>	
				</tr>
				<tr id="z2">
					<td class="width-20 active"><label class="pull-right">合格实验室校验报告：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws">
							<input id="m32" type="hidden" name="m32" value=""/>
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="filePicker">选择文件</div>
						</div>
                    </td>	
				</tr> --%>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">验收最终结果和相关说明：</label></td>
					<td class="width-80" colspan="3">
						<input style="width:100%;height: 80px;" id="m33"name="m33"  class="easyui-textbox" value="${sbys.m33 }" data-options="multiline:true,validType:'length[0,500]'" />
				   	</td>
				</tr>
				
			</tbody>
		</table>	
		
		<c:if test="${action == 'create'}">
			<input type="hidden" name="qgsbid" value="${qgsbid}" />
		</c:if>
		<c:if test="${action == 'update'}">
			<input type="hidden" name="qgsbid" value="${sbys.qgsbid}" />
		</c:if>
		<c:if test="${not empty sbys.ID}">
			<input type="hidden" name="ID" value="${sbys.ID}" />
			<input type="hidden" name="qyid" value="${sbys.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbys.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbys.s3}" />
			<input type="hidden" name="m34" value="${sbys.m34}" />
			<input type="hidden" name="m35" value="${sbys.m35}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

$list = $('#fileList'); //文件上传

function startWebUploader(){
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
	
	//文件上传成功 
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
			
			$("#m32").val(newurl);
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
}

var rwid=1;

//添加行
function addRw() {
	var $list= $("#rwtable tr").eq(-1);
	var $li = $(
				'<tr style="height:30px" >'+
				'<td style="" align="center">'+
				'<input type="text" id="csxm_'+rwid+'" name="csxm" class="easyui-textbox" value=""  style="height: 30px;width: 438px;" />'+
				'</td>'+
				'<td style="" align="center">'+
				'<input type="text" id="csjg_'+rwid+'" name="csjg" class="easyui-textbox" value=""  style="height: 30px;width: 438px;" />'+
				'</td>'+
				'<td align="center">'+
				'<button class="btn btn-info btn-sm" style="width:94px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
				'</td>'+
				'</tr>'
	            );
	$list.after( $li );
	startEasyui();
	rwid=rwid+1;
}

//修改页面初始化
if('${action}'=='update'){
	var rwlist = '${gnyslist}';
	nrList=JSON.parse(rwlist);  
	$.each(nrList, function(idx, obj) {
		var $list= $("#rwtable tr").eq(-1);
		var $li = $(
					'<tr style="height:30px" >'+
					'<td style="" align="center">'+
					'<input type="text" id="csxm_'+rwid+'" name="csxm" class="easyui-textbox" value="'+obj.m1+'"  style="height: 30px;width: 438px;" />'+
					'</td>'+
					'<td style="" align="center">'+
					'<input type="text" id="csjg_'+rwid+'" name="csjg" class="easyui-textbox" value="'+obj.m2+'"  style="height: 30px;width: 438px;" />'+
					'</td>'+
					'<td align="center">'+
					'<button class="btn btn-info btn-sm" style="width:94px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
					'</td>'+
					'</tr>'
		            );
		$list.after( $li );
		startEasyui();
		rwid=rwid+1;
	});
}

//初始化控件
function startEasyui(){
	$('#csxm_'+rwid).textbox({
		required:'true',
		validType:'length[0,250]'
	}); 
	
	$('#csjg_'+rwid).textbox({  
		required:'true',
		validType:'length[0,250]'
	});
}
	
//删除指定行的数据
function removeTr(obj) {
	obj.remove();
}

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	if('${action}'=='create'){
		$('#z1').hide();
		$('#z2').hide();
	}
	
	if('${action}'=='update'){
		/* if('${sbys.m30}'==1){
			$("input[name='m30']").get(0).checked = true;
			$('#z1').hide();
			$('#z2').hide();
		}else{
			$("input[name='m30']").get(1).checked = true;
			$('#z1').show();
			if('${sbys.m31}'==1){
				$("input[name='m31']").get(0).checked = true;
				$('#z2').hide();
			}else{
				$("input[name='m31']").get(1).checked = true;
				$('#z2').show();
				startWebUploader();
			}
		} */
		
		var fjUrl = '${sbys.m32}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            "</div>"
			            );
			    $list.html( $li );
			    
			    $("#m32").val(arry[i]);
			}
		}
	}
	
	$("input[name='m30']").change(function() {
		var flag = $(this).val();
		if (flag == "1") {
			$('#z1').hide();
			$('#z2').hide();
		}else if (flag == "2") {
			$('#z1').show();
			$('#z2').hide();
		}
	});
	
	$("input[name='m31']").change(function() {
		var flag = $(this).val();
		if (flag == "1") {
			$('#z2').hide();
		}else if (flag == "2") {
			$('#z2').show();
			startWebUploader();
		}
	});
	
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