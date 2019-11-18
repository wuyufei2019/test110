<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>图片上传</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引	
</script>
</head>
<body>

     <form id="inputForm" action="" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">责任部门：</label></td>
					<td class="width-30"><input id="M13" name="M13" type="text" class="easyui-combobox"
						style="width: 100%;height: 30px;"
						data-options="editable:false ,valueField: 'text',textField: 'text', panelHeight:'140', url:'${ctx}/system/department/deptjson'" /></td>
					<td class="width-20 active"><label class="pull-right">发生区域：</label></td>
					<td class="width-30"><input id="M14" name="M14" type="text" class="easyui-textbox" 
						style="width: 100%;height: 30px;" data-options="validType:'length[0,50]'" /></td>
				</tr>			  
			  
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患类别：</label></td>
					<td class="width-30"><input style="width:100%;height: 30px;" id="M2" name="M2" class="easyui-combobox" value="${res.m1 }" data-options="panelHeight:'120', editable:true ,
									valueField: 'text',textField: 'text',required:true, 
									data: [
								        {value:'火灾',text:'火灾'},
								        {value:'容器爆炸',text:'容器爆炸'},
								        {value:'锅炉爆炸',text:'锅炉爆炸'},
								        {value:'其他爆炸',text:'其他爆炸'},
								        {value:'中毒和窒息',text:'中毒和窒息'},
								        {value:'灼烫',text:'灼烫'},
								        {value:'触电',text:'触电'},
								        {value:'物体打击',text:'物体打击'},
								        {value:'车辆伤害',text:'车辆伤害'},
								        {value:'机械伤害',text:'机械伤害'},
								        {value:'起重伤害',text:'起重伤害'}, 
								        {value:'淹溺',text:'淹溺'},
								        {value:'高处坠落',text:'高处坠落'},
								        {value:'坍塌',text:'坍塌'},
								        {value:'其他伤害',text:'其他伤害'},]"/></td>					
					<td class="width-20 active"><label class="pull-right">计划完成时间：</label></td>
					<td class="width-30" ><input id="M3" name="M3" class="easyui-datebox" style="width:100%;height: 30px;" data-options="editable:false, required:'true' "  " /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患等级：</label></td>
					<td class="width-30"><input style="width:100%;height: 30px;" id="yhdj" name="yhdj" class="easyui-combobox" data-options="panelHeight:'auto', editable:false ,
									valueField: 'text',textField: 'text',required:true, 
									data: [
								        {value:'一般',text:'一般'},
								        {value:'重大',text:'重大'}]"/>
					</td>
					<td class="width-20 active"><label class="pull-right">整改人：</label></td>
					<td class="width-30"><input name="M9" id="M9" style="width: 100%;height: 30px;" class="easyui-combobox"
						data-options="panelHeight:'100',required:'true',valueField: 'id',textField: 'text',url:'${ctx}/system/compuser/userjson'" />
					</td>
				</tr>
				
			  	<tr>
					<td class="width-20 active"><label class="pull-right">问题描述：</label></td>
					<td class="width-80" colspan="3">
						<input type="text" id="M1" name="M1" style="width:100%;height: 80px;" class="easyui-textbox"  data-options="multiline:true" />
					</td>
				</tr>		

			  	<tr>
					<td class="width-20 active"><label class="pull-right">解决措施：</label></td>
					<td class="width-80" colspan="3">
						<input type="text" id="M15" name="M15" style="width:100%;height: 80px;" class="easyui-textbox"  data-options="multiline:true" />
					</td>
				</tr>
										
				<tr>
					<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-80" colspan="3">
					<div id="uploader_ws_m15">
					    <div id="m15fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">	
	var pic=parent.pic;
	var content=parent.content;
	var finishtime=parent.finishtime;
	var yhlb=parent.yhlb;
	var yhdj=parent.yhdj;
	var zgr=parent.zgr;
	var zrbm=parent.zrbm;
	var fsqy=parent.fsqy;
	var jjcs=parent.jjcs;

	$(function(){
		if(content!=null&&content!=""){
			$('#M1').textbox('setValue',content);
			$('#M2').combobox('setValue',yhlb);
			$('#M3').datebox('setValue',finishtime);
			$('#yhdj').textbox('setValue',yhdj);
			$('#M9').textbox('setValue',zgr);
			$('#M13').combobox('setValue',zrbm);
			$('#M14').textbox('setValue',fsqy);
			$('#M15').textbox('setValue',jjcs);
				var arry =pic.split(",");
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
				    var $input = $('<input id="url" type="hidden" name="url" value="'+pic+'"/>');
					$('#uploader_ws_m15').append( $input );
				}
			}
	})

	var $ = jQuery,
    $list = $('#m15fileList'); //图片上传
	
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
	
    // 负责预览图的销毁
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

		    $list.html( $li );
	
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
	
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="url" type="hidden" name="url" value="'+newurl+'"/>'+
				'<input id="filename" type="hidden" name="filename" value="'+res.fileName+'"/>');
			
			$('#uploader_ws_m15').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#url").remove();
};	
	
//获取图片地址
function geturl(){
	var url=$('#url').val();
	if(url==null){
		url="";
	}
	return url;
}

//获取问题描述
function getczwt(){
	var czwt=$('#M1').textbox('getValue');
	return czwt;
}

//获取结束时间
function gettime(){
	var time=$('#M3').textbox('getValue');
	return time;
}

//获取隐患类别
function getlx(){
	var lx=$('#M2').datebox('getValue');
	return lx;
}

//获取整改人
function getzgr(){
	var zgr=$('#M9').datebox('getValue');
	return zgr;
}

//获取隐患等级
function getyhdj(){
	var yhdj=$('#yhdj').datebox('getValue');
	return yhdj;
}

//获取责任部门
function getzrbm(){
	var zrbm=$('#M13').combobox('getValue');
	return zrbm;
}

//获取发生区域
function getfsqy(){
	var fsqy=$('#M14').textbox('getValue');
	return fsqy;
}

//获取解决措施
function getjjcs(){
	var jjcs=$('#M15').textbox('getValue');
	return jjcs;
}
</script>
</body>
</html>