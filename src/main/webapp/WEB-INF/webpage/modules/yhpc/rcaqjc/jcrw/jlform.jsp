<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/jcrw/index.js?v=1.1"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/rcjl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 30px;" class="easyui-textbox" value="${jcrw.qyname}" data-options="editable:false "/>
					</td>
				</tr>
			  
				<tr>
					<td class="width-20 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-30" ><input id="M1" name="M1" class="easyui-datetimebox" value="${jcjl.m1 }" style="height: 30px;width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
					<td class="width-20 active"><label class="pull-right">责任部门：</label></td>
					<td class="width-30" ><input name="M2" id="M2" value="${jcjl.m2}" type="text" class="easyui-combobox"  style="width: 100%;height: 30px;"
						data-options="required:true, panelHeight:'150px', editable:true, valueField: 'id', textField: 'text', url:'${ctx}/system/department/deptjson' "/></td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">检查人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" class="easyui-textbox" value="${jcrw.jcr}" data-options="editable:false "/></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m3">
						    <div id="m3fileList" class="uploader-list" ></div>
						    <div id="imagePicker">选择图片</div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<table id="tt" ></table>
					</td>
				</tr>

				<div class="col-xs-9" id="content">
				</div>

				<tr>
					<td class="width-20 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-30" colspan="3">
						<input type="text" name="M5" class="easyui-textbox" value="${jcjl.m5 }"  data-options="multiline:true ,validType:'length[0,500]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<c:if test="${action eq 'create'}">	
					<input type="hidden" name="ID1" value="${jcrw.id}" />
					<input type="hidden" name="qyid" value="${jcrw.id1}" />
				</c:if>
				<c:if test="${action eq 'update'}">	
					<input type="hidden" name="ID1" value="${jcjl.ID1}" />
					<input type="hidden" name="ID2" value="${jcjl.ID2}" />				
				</c:if>
					<input type="hidden" name="m8" value="${jcjl.m8}" />
                <input type="hidden" id="M6" name="M6" value="" />
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
//得到当前日期
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
} 
	
function doSubmit(){
    layer.confirm('确认是否完成？', {
        btn: ['确认','下次再填'] //按钮
    }, function(){
        $('#M6').val("1");
        layer.close(layer.index);
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    }, function(){
        $('#M6').val("2");
        layer.close(layer.index);
        $("#inputForm").serializeObject();
        $("#inputForm").submit();
    });

}

$(function(){
    //第一次添加时,设置默认时间  ;
	var curr_time = new Date();
	if('${action}'=='create'){    
		$("#M1").datebox("setValue",myformatter(curr_time)); 	
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
	    	parent.dg2.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

    var jcwtallids = "";
	var faid;
	faid= '${jcrw.m4}';
	if(faid == null||faid == undefined){
		faid = 0;
	}
	var target=$('#tt').datagrid({
		method : "get",
		fitColumns : true,
		nowrap:false,
		singleSelect:true,
		url:ctx+'/yhpc/jcrw/nrlist/'+faid,
		columns:[[
			{field:'m3',title:'检查内容',width:535},
			{field:'m4',title:'检查结果',width:143,
				formatter: function(value, rowData, rowIndex){
                	return '无隐患<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + 'onclick="removeInfo(' + 
                	rowData.id+' )" />' +
                	'有隐患<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="addNr(' + rowData.id + ')" />';
            	}
			}
		]], 
        detailFormatter:function(rowIndex, rowData){
            jcwtallids +="jcwt_" + rowData.id+",";
			return '<div id="ddv-' + rowIndex + '" ><table style="width: 98%;">' +
					'<input type="hidden" name="jcnrid_' + rowData.id + '" value="' + rowData.id + '" />' +
					'<input type="hidden" id="jcnr_' + rowData.id + '" value="' + rowData.jcnr + '" />' +
					'<tr>'+
					'<td>' +
					'检查问题：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<input type="text" id="jcwt_' + rowData.id + '" name="jcwt_' + rowData.id + '" style="width: 667px;height: 30px;" />' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'图片：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td>' +
					'<div id="uploader_ws_'+rowData.id+'" style="height: 42px;">'+
					'<input type="hidden" name="url_'+rowData.id+'" id="url_'+rowData.id+'" value="'+rowData.pic+'" />'+
					'<div id="fileList_'+rowData.id+'" class="uploader-list" ></div>'+
					'<a style="margin:2px" class="btn btn-success btn-xs" onclick="openPicForm(' + 
						rowData.id+','+rowIndex+' )">上传图片</a>'+
					'</div>'+
					'</td>'+									
					'</tr>'+
					'</table></div>';    
		},
	});
});

    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#pic_"+fileid).remove();
    	$("#input_"+fileid).remove();
    	$("#url_"+fileid).remove();
    };
 
    
	var $ = jQuery,
    $list = $('#m3fileList'); //图片上传
	
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M3" value="'+newurl+'"/>');
			
			$('#uploader_ws_m3').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
</script>
</body>
</html>