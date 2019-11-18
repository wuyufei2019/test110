<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备大修项</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/sbdx/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
			<c:if test="${type == '2'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
								onSelect: function(row){
									$('#m5').combobox('setValue', '');
									$('#m5').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}" />
					</td>
				</tr> 
			</c:if>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-80" colspan="3">
					<c:if test="${action eq 'create' }">
						<input id="m5" name="m5" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m5 }" 
							data-options="editable:false,panelHeight:'100px',valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson',
								onSelect: function(row){
									$('#deptid').val('');
									if ('${sbtype}' == '0') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson5/'+row.id);
									} else if ('${sbtype}' == '1') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson5/'+row.id);
									}
								}
							" />
					</c:if>
					<c:if test="${action eq 'update' }">
						<input id="m5" name="m5" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m5 }" 
							data-options="editable:false,panelHeight:'100px',valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson3/'+${sbdx.qyid},
								onSelect: function(row){
									$('#deptid').val('');
									if ('${sbtype}' == '0') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson5/'+row.id);
									} else if ('${sbtype}' == '1') {
										$('#m2').combobox('setValue', '');
										$('#m2').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson5/'+row.id);
									}
								}
							" />
					</c:if>	
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">工作令号：</label></td>
					<td class="width-30">
						<input id="m1" name="m1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m1 }" data-options="validType:'length[0,50]'" />
				   	</td>
		    <c:if test="${sbtype eq '0'}">
				   	<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30">
					<c:if test="${action eq 'create' }">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m2 }" 
							data-options="panelHeight:'150px',valueField:'id',textField:'id',url:'${ctx}/sbssgl/sbgl/sbjson5/',
								onSelect: function(row){
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson2/'+row.id,
										success: function(data){
											$('#m3').textbox('setValue', data.m3);
											$('#m4').textbox('setValue', new Date(data.m16).format('yyyy-MM'));
										}
									});
								}
							" />
					</c:if>
					<c:if test="${action eq 'update' }">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m2 }" 
							data-options="panelHeight:'150px',valueField:'id',textField:'id',url:'${ctx}/sbssgl/sbgl/sbjson5/'+${sbdx.m5 },
								onSelect: function(row){
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson2/'+row.id,
										success: function(data){
											$('#m3').textbox('setValue', data.m3);
											$('#m4').textbox('setValue', new Date(data.m16).format('yyyy-MM'));
										}
									});
								}
							" />
					</c:if>	
				   	</td>
				</tr>
			</c:if>
			<c:if test="${sbtype eq '1'}">
				   	<td class="width-20 active"><label class="pull-right">设备编号：</label></td>
					<td class="width-30">
					<c:if test="${action eq 'create' }">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m2 }" 
							data-options="panelHeight:'150px',valueField:'id',textField:'id',url:'${ctx}/sbssgl/sbgl/tzsbjson5/',
								onSelect: function(row){
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson2/'+row.id,
										success: function(data){
											$('#m3').textbox('setValue', data.m3);
											$('#m4').textbox('setValue', new Date(data.m16).format('yyyy-MM'));
										}
									});
								}
							" />
					</c:if>
					<c:if test="${action eq 'update' }">
						<input id="m2" name="m2" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m2 }" 
							data-options="panelHeight:'150px',valueField:'id',textField:'text',url:'${ctx}/sbssgl/sbgl/tzsbjson5/'+${sbdx.m5 },
								onSelect: function(row){
									$.ajax({
										type: 'POST',
										url: '${ctx}/sbssgl/sbgl/sbinfojson2/'+row.id,
										success: function(data){
											$('#m3').textbox('setValue', data.m3);
											$('#m4').textbox('setValue', new Date(data.m16).format('yyyy-MM'));
										}
									});
								}
							" />
					</c:if>	
				   	</td>
				</tr>
			</c:if>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">型号名称：</label></td>
					<td class="width-30">
						<input id="m3" name="m3" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m3 }" data-options="validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">启用年月：</label></td>
					<td class="width-30">
						<input id="m4" name="m4" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbdx.m4 }" data-options="editable:false" />
				   	</td>
				</tr>
				<tr>
				   	<td class="width-20 active"><label class="pull-right">计划修理费（万元）：</label></td>
					<td class="width-30">
						<input id="m6" name="m6" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m6 }" data-options="validType:'length[0,20]'" />
				   	</td>
				   		<td class="width-20 active"><label class="pull-right">计划修理时间：</label></td>
					<td class="width-30">
						<input id="m18" name="m18" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m18 }" data-options="required:'true',editable:false" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">修理类别：</label></td>
					<td class="width-30">
						<input id="m8" name="m8" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m8 }" 
							data-options="editable:false, panelHeight: 'auto', data: [{'value': '0', 'text': '大修'},{'value': '1', 'text': '项修'}]" />
				   	</td>
				</tr>
				<td class="width-20 active"><label class="pull-right">设备现状描述：</label></td>
					<td class="width-80" colspan="3">
						<input id="m16" name="m16" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbdx.m16 }" data-options="multiline: true,validType:'length[0,100]'" />
				   	</td>
				<tr>
					<td class="width-20 active"><label class="pull-right">检维修方案：</label></td>
					<td class="width-80" colspan="3">
						<input id="m15" name="m15" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbdx.m15 }" data-options="multiline: true,validType:'length[0,250]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">技术验收质量要求：</label></td>
					<td class="width-80" colspan="3">
						<input id="m20" name="m20" class="easyui-textbox" style="width: 100%;height: 50px;" value="${sbdx.m20 }" data-options="multiline: true,validType:'length[0,100]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">检维修负责人：</label></td>
					<td class="width-30">
						<input id="m21" name="m21" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m21 }" data-options="validType:'length[0,25]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">是否完成：</label></td>
					<td class="width-30">
						<input id="m19" name="m19" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sbdx.m19 }" 
							data-options="required:'true', editable:false, panelHeight: 'auto', data: [{'value': '0', 'text': '未完成'},{'value': '1', 'text': '已完成'}]" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">定人：</label></td>
					<td class="width-30">
						<input id="m9" name="m9" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m9 }" data-options="required:'true',validType:'length[0,25]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">定期：</label></td>
					<td class="width-30">
						<input id="m10" name="m10" class="easyui-datebox" style="width: 100%;height: 30px;" value="${sbdx.m10 }" data-options="required:'true',editable:false" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">定点：</label></td>
					<td class="width-30">
						<input id="m11" name="m11" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m11 }" data-options="required:'true',validType:'length[0,50]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">定质：</label></td>
					<td class="width-30">
						<input id="m12" name="m12" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m12 }" data-options="required:'true',validType:'length[0,50]'" />
				   	</td>
				   	<td class="width-20 active"><label class="pull-right">定量：</label></td>
					<td class="width-30">
						<input id="m13" name="m13" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sbdx.m13 }" data-options="required:'true',validType:'length[0,100]'" />
				   	</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传验收报告：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws">
						    <div id="fileList" class="uploader-list" ></div>
						    <div id="insertfilebt">选择文件</div>
						</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">上传维修合同：</label></td>
					<td class="width-80" colspan="3">
						<div id="uploader_ws2">
						    <div id="fileList2" class="uploader-list" ></div>
						    <div id="insertfilebt2">选择文件</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>	
			<input type="hidden" name="type" value="${type}" /><!-- 用户类型 -->
			<input type="hidden" name="sbtype" value="${sbtype}" /><!-- 设备类型 -->
			<input type="hidden" id="deptid" name="deptid" value="" />
		<c:if test="${not empty sbdx.ID}">
			<input type="hidden" name="ID" value="${sbdx.ID}" />
			<input type="hidden" name="qyid" value="${sbdx.qyid}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${sbdx.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${sbdx.s3}" />
			<input type="hidden" name="m7" value="${sbdx.m7}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

$(function() {
	initDateShow("m4");
	initDateShow("m18");
	
	/* if ('${type}' == '2' && '${action}' == 'update') {
		$('#m5').combobox('reload', '${ctx}/system/department/deptjson3/'+'${sbdx.qyid}');
	} */
	
	$("#m2").combobox({
		formatter: function(row){
			return '<span class="item-text">'+row.id+" ("+row.text+")"+'</span>';
		}
	});
});

//初始化年份-月份控件
function initDateShow(idName){
	var yjyf = $("#"+idName);
	yjyf.datebox({
	    onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	        span.trigger('click'); //触发click事件弹出月份层
	        if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
	        if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            tds = p.find('div.calendar-menu-month-inner td');
	            tds.click(function (e) {
	                e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
	                var year = /\d{4}/.exec(span.html())[0]//得到年份
	                , month = parseInt($(this).attr('abbr'), 10); //
	                yjyf.datebox('hidePanel')//隐藏日期对象
	                .datebox('setValue', year + '-' + month); //设置日期的值
	            });
	        }, 0);
	        yearIpt.unbind();//解绑年份输入框中任何事件
	    },
	    parser: function (s) {
	        if (!s) return new Date();
	        var arr = s.split('-');
	        return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	    },
	    formatter: function (d) { 
	    	var dMonth = (d.getMonth() + 1);
	    	if (dMonth < 10) {
	    		dMonth = ("0"+dMonth);//1月到9月自动补0
	    	}
	    	return d.getFullYear() + '-' + dMonth; 
    	}
	});
	var p = yjyf.datebox('panel'), //日期选择对象
	    tds = false, //日期选择对象中月份
	    aToday = p.find('a.datebox-current'),
	    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
	    //显示月份层的触发控件
	    span = aToday.length ? p.find('div.calendar-title span') :
	    p.find('span.calendar-text'); 
	if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
	   
	    aToday.unbind('click').click(function () {
	        var now=new Date();
	        yjyf.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
	    });
	}
	/* var now=new Date();
	yjyf.datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1)); */
}

var $ = jQuery,
$list = $('#fileList'); //文件上传
$list2 = $('#fileList2'); //文件上传

var fileuploader = WebUploader.create({
   auto: true,
   swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
   server: '${ctx}/kindeditor/upload?dir=file',		
   pick: {
	   	id:'#insertfilebt',
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
		
		$list.html( $li );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("[name='m14']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m14" value="'+newurl+'"/>');
		
		$('#uploader_ws').append( $input );
		
		imgflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

var fileuploader2 = WebUploader.create({
   auto: true,
   swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
   server: '${ctx}/kindeditor/upload?dir=file',		
   pick: {
	   	id:'#insertfilebt2',
	   	multiple : false,
   },
   duplicate :true  
});

fileuploader2.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

// 文件上传成功 
fileuploader2.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li2 = $(
	            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
	            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
	            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	            "</div>"
	            );		    
		
		$list2.html( $li2 );
 
		var newurl=res.url+"||"+res.fileName;
		
		$("[name='m22']").remove();
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="m22" value="'+newurl+'"/>');
		
		$('#uploader_ws2').append( $input );
		
		imgflag = true;
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};
	
function doSubmit(){
	/* if ($('#deptid').val() != '' && $('#deptid').val() != null) {//当用户选择设备编号后，将部门编号放入id为deptid隐藏域中，如果用户并未修改部门编号，保存时，将值取出来赋给m5
		$("#m5").combobox('setValue', $('#deptid').val());
	} */
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
	
	if('${action}'=='update'){
		var fjUrl = '${sbdx.m14}';
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
				$list.html( $li );
			    $("[name='m14']").remove();
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m14" value="'+arry[i]+'"/>');
				$('#uploader_ws').append( $input );
			}
		}
		
		var fjUrl2 = '${sbdx.m22}';
		if(fjUrl2!=null&&fjUrl2!=''){
			arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
				$list2.html( $li );
			    $("[name='m22']").remove();
			    var $input = $('<input id="input_'+id+'" type="hidden" name="m22" value="'+arry[i]+'"/>');
				$('#uploader_ws2').append( $input );
			}
		}
	}
});
</script>
</body>
</html>
