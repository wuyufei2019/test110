<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>课程信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqpx/kcxx/index.js?v=1.5"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/kcxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-35"><input id="M5" name="M5" style="width: 100%;height: 30px;" class="easyui-combobox"
								value="${aqpxlist.m5 }"
								data-options="panelHeight:'auto',required:'true',editable:false,data: [
										{value:'1',text:'日常计划培训'},
								        {value:'2',text:'入职三级教育培训'},
								        {value:'3',text:'外来方培训'} ]" /></td>
					<td class="width-15 active"><label class="pull-right">课程名称：</label></td>
					<td class="width-35"><input name="M1" style="width: 100%;height: 30px;" class="easyui-textbox"
								value="${aqpxlist.m1 }"
								data-options="required:'true'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">课程学时(h)：</label></td>
					<td class="width-35"><input name="M2" class="easyui-textbox"
								value="${aqpxlist.m2 }" style="width: 100%;height: 30px;" data-options="validType:'mone', required:true " /></td>
					<td class="width-15 active"><label class="pull-right">课程学分：</label></td>
					<td class="width-35"><input name="M3" class="easyui-textbox" value="${aqpxlist.m3 }" style="width: 100%;height: 30px;"
								data-options=" validType:'mone', required:true " /></td>
				</tr>
				
				<tr id="dep">
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">课件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m4">
					    <div id="m4fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					    <a  href="javascript:void(0)"  class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="openkjlist()" title="选择检查点"><i class="fa fa-plus"></i> 课件库中选择</a>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty aqpxlist.ID}">
					<input type="hidden" name="ID" value="${aqpxlist.ID}" />
					<input type="hidden" name="ID1" value="${aqpxlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${aqpxlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${aqpxlist.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){
	    	/* if($("input[name='M4']").val()==null){
	    		layer.msg("请上传课件",{time:5000});
	    		return false;
	    	} */
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

});	

	$list2 = $('#m4fileList'); //文件上传
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#kj_"+fileid).remove();
    	$("#input_"+fileid).remove();
    	$("#lx_"+fileid).remove();
    };
    var allMaxSize = 50;
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/uploadkj?dir=',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    fileSizeLimit: allMaxSize*1024*1024,//限制大小10M，所有被选文件，超出选择不上
	    duplicate :true  
	});
	
	//  验证大小
    fileuploader.on("error",function (type){ 
         if(type == "Q_EXCEED_SIZE_LIMIT"){
              layer.msg("所选附件总大小不可超过"+allMaxSize+"M", {time : 3000});
         }
     });
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var url=res.url.split(".");
			var fjurl=url[0]+"."+res.fileExt;			
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+fjurl+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M4" value="'+newurl+'"/>');
			var $input2 = $('<input id="lx_'+file.id+'" type="hidden" name="lx" value="'+res.fileExt+'"/>');
			$('#uploader_ws_m4').append( $input );
			$('#uploader_ws_m4').append( $input2 );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var fjUrl = '${url}';
		var lx = '${lx}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			lxarry =lx.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var wjarry =arry2[0].split(".");
				var wjurl=wjarry[0]+"."+lxarry[i];
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+wjurl+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M4" value="'+arry[i]+'"/>');
				var $input2 = $('<input id="lx_'+id+'" type="hidden" name="lx" value="'+lxarry[i]+'"/>');
				
				$('#uploader_ws_m4').append( $input );
				$('#uploader_ws_m4').append( $input2 );
			}
		}
		//如果是三级教育，显示部门
		if('${aqpxlist.m5}'=='2'){
			 var td="<td class=\"width-20 active\"><label class=\"pull-right\">培训部门：</label></td>"
				 +"<td class=\"width-30\"><input id=\"ID2\" name=\"ID2\" type=\"text\" class=\"easyui-combobox\" "
				 +"style=\"width: 100%;height: 30px;\" /></td>";
		 	 $("#dep").append(td);
			 //下拉关联信息
			 $("#ID2").combobox({
				 value:'${aqpxlist.ID2}',
				 required:true,
                 multiple:true,
				 panelHeight:'140',
				 editable:true ,
				 valueField: 'id',
				 textField: 'text',
				 url:ctx+"/system/department/deptjson",
			 });
		}
	}
	
	$("#M5").combobox({
		 onSelect: function(rec){
			 if(rec.value=='2'){
				 var td="<td class=\"width-20 active\"><label class=\"pull-right\">培训部门：</label></td>"
						 +"<td class=\"width-30\"><input id=\"ID2\" name=\"ID2\" type=\"text\" class=\"easyui-combobox\" "
						 +"style=\"width: 100%;height: 30px;\" /></td>";
				 $("#dep").append(td);
				 
				 //下拉关联信息
				 $("#ID2").combobox({
					 required:true,
                     multiple:true,
					 panelHeight:'140',
					 editable:true ,
					 valueField: 'id',
					 textField: 'text',
					 url:ctx+"/system/department/deptjson",
				 });
			 }else{
				 $("#dep").html("");
			 }
		 }
	})
</script>
</body>
</html>