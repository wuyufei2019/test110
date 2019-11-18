<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>员工管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/ygxx/index.js?v=1.6"></script>
</head>
<body>

	<form id="inputForm" action="${ctx}/bis/ygxx/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3"><input value="${yglist.ID3 }" id="_qyid" name="ID3"
							style="width: 100%;height: 30px;" class="easyui-combobox"
							data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>
				<c:if test="${usertype != '1' and action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3"><input value="${yglist.ID3 }" id="_qyid" name="ID3"
							style="width: 100%;height: 30px;" class="easyui-combobox"
							data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">工号：</label></td>
					<td class="width-35" ><input name="M2" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${yglist.m2 }" data-options="required:'true',validType:'length[0,100]'" /></td>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35"><input id="M1" name="M1" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${yglist.m1 }" data-options="required:'true',validType:'length[0,100]'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35"><input id="M3" name="M3" class="easyui-combobox" value="${yglist.m3 }"
						style="width: 100%;height: 30px;"
						data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'男',text:'男'},
								        {value:'女',text:'女'} ] " /></td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"><input id="M8" name="M8" type="text" class="easyui-textbox"
						style="width: 100%;height: 30px;" value="${yglist.m8 }" data-options="validType:'idCode'" /></td>
				</tr>
				
				<tr>
                    <td class="width-15 active"><label class="pull-right">人员类别：</label></td>
                    <td class="width-35"><input name="M16" type="text" class="easyui-combobox" style="width: 100%;height: 30px;"
                          value="${yglist.m16 }"
                          data-options=" editable:false ,panelHeight:'auto', data: [
										  {value:'固定制',text:'固定制'},
								          {value:'固定制实习',text:'固定制实习'},
								          {value:'计工制',text:'计工制'},
								          {value:'计工制实习',text:'计工制实习'},
								          {value:'驻外人员',text:'驻外人员'}]" />
                    </td>
					<td class="width-15 active"><label class="pull-right">出生日期：</label></td>
					<td class="width-35"><input id="M10" name="M10" class="easyui-datebox" value="${yglist.m10 }"
						style="width: 100%;height: 30px;" data-options="editable:true " /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">到职日期：</label></td>
					<td class="width-35"><input id="M14" name="M14" class="easyui-datebox" value="${yglist.m14 }"
						style="width: 100%;height: 30px;" data-options="editable:false " /></td>
					<td class="width-15 active"><label class="pull-right">婚姻状况：</label></td>
					<td class="width-35"><input id="M11" name="M11" type="text" class="easyui-combobox"
						style="width: 100%;height: 30px;" value="${yglist.m11 }" data-options="editable: false,panelHeight: 'auto',data: [
										{value:'已婚',text:'已婚'},
								        {value:'未婚',text:'未婚'} ]" /></td>				
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">部门：</label></td>
					<td class="width-35"><input id="ID4" name="ID4" type="text" class="easyui-combobox" value="${yglist.ID4 }"
						style="width: 100%;height: 30px;"
						data-options="editable:false ,valueField: 'id',textField: 'text', panelHeight:'140', url:'${ctx}/system/department/deptjson'" /></td>
					<td class="width-15 active"><label class="pull-right">职位/岗位：</label></td>
					<td class="width-35"><input name="M4" type="text" class="easyui-combobox" style="width: 100%;height: 30px;"
						value="${yglist.m4 }" data-options="required:'true',editable:false ,validType:'length[0,25]',panelHeight: '100',valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson'<%-- '${ctx}/tcode/dict/gwgz' --%>" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35"><input name="M7" type="text" class="easyui-textbox" value="${yglist.m7 }"
						style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
					<td class="width-15 active"><label class="pull-right">学历：</label></td>
					<td class="width-35"><input id="" name="M5" type="text" class="easyui-combobox"
						style="width: 100%;height: 30px;" value="${yglist.m5 }"
						data-options=" editable:false ,panelHeight:'auto', data: [
										{value:'研究生',text:'研究生'},
								        {value:'本科',text:'本科'},
								        {value:'大专',text:'大专'},
								        {value:'高中',text:'高中'},
								        {value:'初中',text:'初中'}]" /></td>				
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">贯籍：</label></td>
					<td class="width-35"><input id="M12" name="M12" class="easyui-textbox" value="${yglist.m12 }"
						style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
					<td class="width-15 active"><label class="pull-right">民族：</label></td>
					<td class="width-35"><input id="M13" name="M13" class="easyui-textbox" value="${yglist.m13 }"
						style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">专业：</label></td>
					<td class="width-35"><input name="M6" type="text" class="easyui-textbox" value="${yglist.m6 }"
						style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
					<td class="width-15 active"><label class="pull-right">联系方式：</label></td>
					<td class="width-35"><input id="M9" name="M9" style="width: 100%;height: 30px;" class="easyui-textbox"
						value="${yglist.m9 }" data-options="validType:'mobileAndTel'" /></td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">责任区域：</label></td>
					<td class="width-85" colspan="3"><input name="M18" type="text" class="easyui-textbox" value="${yglist.m18 }"
						style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m19">
					    <div id="m19fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择照片</div>
					</div>
					</td>
				</tr>

				<input type="hidden" name="M17" value="${yglist.m17 }"  />
				<input type="hidden" id="ID1" name="ID1" value="${yglist.ID1}" />
				<c:if test="${not empty yglist.ID}">
					<input type="hidden" name="ID" value="${yglist.ID}" />
					<input type="hidden" name="ID2" value="${yglist.ID2}" />
					<input type="hidden" name="ID3" value="${yglist.ID3}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${yglist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${yglist.s3}" />
					<input type="hidden" name="M15" value="<fmt:formatDate value="${yglist.m15}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="status" value="${yglist.status}" />
				</c:if>
			</tbody>
		</table>
	</form>

<script type="text/javascript">
var usertype = ${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

function doSubmit(){
	$("#inputForm").serializeObject();
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
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    		parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
	    	}else if(data=='ewmerror'){
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '二维码重复，操作失败！',shade: 0 ,time: 2000 });
	    		flag=true;
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    		parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
			}
	    }    
	});
});

var $ = jQuery,
$list = $('#m19fileList'); //图片上传

var uploader = WebUploader.create({

    auto: true,

    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

    server: '${ctx}/kindeditor/upload?dir=image',

    pick: {
    	id:'#imagePicker',
    	multiple : false,
    },
    duplicate :true,	    
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/jpg,image/jpeg,image/png' 
    },
    compress :{
        width: 1200,
        height: 1200,
        quality: 90,
        allowMagnify: false,
        crop: false,
        preserveHeaders: false,
        noCompressIfLarger: false,
        compressSize: 1024*50
    }
});

uploader.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

//负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

// 图片上传成功，显示预览图
uploader.on( 'uploadSuccess', function( file ,res) {
$.jBox.closeTip();
	if(res.error==0){
		var $li = $(
	            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
	            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
	                "<img>" +
	                "<div class=\"info\">" + file.name + "</div>" +
	            "</div>"
	    ),
		$img = $li.find('img');

	    $list.append( $li );

	    // 创建缩略图
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }

	        $img.attr( 'src', src );
	    }, 100, 100 );
		
		var newurl=res.url+"||"+res.fileName;
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M19" value="'+newurl+'"/>');
		
		$('#uploader_ws_m19').append( $input );
		uploadImgFlag=true;
		
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

if('${action}' == 'update'){
	var zpUrl = '${yglist.m19}';
	if(zpUrl!=null&&zpUrl!=''){
		var arry =zpUrl.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ws_zp_"+i;
			var $li = $(
		            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
		                "<div class=\"info\">" + arry2[1] + "</div>" +
		            "</div>"
		            );

		    $list.append( $li );
		    
		    var $input = $('<input id="input_'+id+'" type="hidden" name="M19" value="'+arry[i]+'"/>');
			$('#uploader_ws_m19').append( $input );
		}
	}
}
</script>
</body>
</html>