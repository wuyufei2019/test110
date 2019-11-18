<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>应急装备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
   
</head>
<body>

     <form id="inputForm" action="${ctx}/erm/yjwz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">物资类别：</label></td>
					<td class="width-35"><input class="easyui-combobox" name="M1" value="${res.m1 }" style="width: 100%;height: 30px;" data-options="
								required:'true',editable:false ,data: [
								        {value:'防护用品',text:'防护用品'},
								        {value:'生命救助',text:'生命救助'},
								        {value:'生命支持',text:'生命支持'},
								        {value:'临时食宿',text:'临时食宿'},
								        {value:'通讯广播',text:'通讯广播'},
								        {value:'污染清理',text:'污染清理'},
								        {value:'动力燃料',text:'动力燃料'},
								        {value:'器材工具',text:'器材工具'},
								        {value:'其他',text:'其他'} ]
						    "/></td>
					<td class="width-15 active"><label class="pull-right">物资名称：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M2" class="easyui-textbox " value="${res.m2 }" data-options="validType:'length[0,25]' ,required:'true' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">型号：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M3" class="easyui-textbox " value="${res.m3 }" data-options="validType:'length[0,10]'"/></td>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M4" class="easyui-numberbox" value="${res.m4 }" data-options="min:0,max:999999"/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">功能用途：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M5" class="easyui-textbox " value="${res.m5 }" data-options="validType:'length[0,200]'"/></td>
					<td class="width-15 active"><label class="pull-right">自储数量：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M6" class="easyui-numberbox" value="${res.m6 }" data-options="min:0,max:999999"/></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">代储数量：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M7" class="easyui-numberbox" value="${res.m7 }" data-options="min:0,max:999999"/></td>
					<td class="width-15 active"><label class="pull-right">储存单位：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M8" class="easyui-textbox " value="${res.m8 }" data-options="validType:'length[0,20]'"/></td>
				</tr>

				<tr >
					<td class="width-15 active"><label class="pull-right">储存地址：</label></td>
					<td class="width-35" colspan="3">
						<input style="width: 100%;height: 30px;" name="M9" class="easyui-textbox" value="${res.m9 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				
			<c:if test="${usertype != '1'}">
				<tr>
					<td id="map_test" class="width-15 active" ><label class="pull-right">位置 ：</label></td><td colspan="3">
						<label style="margin-left: 10px;height:30px;line-height:30px;">经度：</label>
						<input id="erm_map_c_x" name="M14" class="easyui-textbox" value="${res.m14 }" style="width:35%;height: 30px;"  readonly="readonly" data-options=""  />
						<label style="margin-left: 10px;height:30px;line-height:30px;">纬度：</label>
						<input id="erm_map_c_y" name="M15" class="easyui-textbox" value="${res.m15 }" style="width:35%;height: 30px;"  readonly="readonly" data-options=""  />
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></td></td>
				</tr>
			</c:if>	
			
			<c:if test="${usertype == '1'}">
				<input type="hidden" name="M14" value="${locx}" />
				<input type="hidden" name="M15" value="${locy}" />
			</c:if>
			
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M10" class="easyui-textbox " value="${res.m10 }" data-options="validType:'length[0,20]'"/></td>
					<td class="width-15 active"><label class="pull-right">联系人电话：</label></td>
					<td class="width-35"><input style="width: 100%;height: 30px;" name="M11" class="easyui-textbox" value="${res.m11 }" data-options="validType:'mobile'"/></td>
				</tr>
					
				</tr>
				
				<tr> 
					<td class="width-15 active"><label class="pull-right">应对事故类型：</label></td><td colspan="3"><input type="checkbox" id="ydlx1" name="M12" value="1" /><a style="cursor:default;" onclick="ydlxChange1()">物体打击</a>
																		                <input type="checkbox" id="ydlx2" name="M12" value="2" /><a style="cursor:default;" onclick="ydlxChange2()">车辆伤害</a>
																		                <input type="checkbox" id="ydlx3" name="M12" value="3" /><a style="cursor:default;" onclick="ydlxChange3()">机械伤害</a>
																		                <input type="checkbox" id="ydlx4" name="M12" value="4" /><a style="cursor:default;" onclick="ydlxChange4()">起重伤害</a>
																		                <input type="checkbox" id="ydlx5" name="M12" value="5" /><a style="cursor:default;" onclick="ydlxChange5()">触电</a><br/>
																		                <input type="checkbox" id="ydlx6" name="M12" value="6" /><a style="cursor:default;" onclick="ydlxChange6()">淹溺</a>
																		                <input type="checkbox" id="ydlx7" name="M12" value="7" /><a style="cursor:default;" onclick="ydlxChange7()">灼烫</a>
																		                <input type="checkbox" id="ydlx8" name="M12" value="8" /><a style="cursor:default;" onclick="ydlxChange8()">火灾</a>
																		                <input type="checkbox" id="ydlx9" name="M12" value="9" /><a style="cursor:default;" onclick="ydlxChange9()">高处坠落</a>
																		                <input type="checkbox" id="ydlx10" name="M12" value="10" /><a style="cursor:default;" onclick="ydlxChange10()">坍塌</a><br/>
																		                <input type="checkbox" id="ydlx11" name="M12" value="11" /><a style="cursor:default;" onclick="ydlxChange11()">冒顶片帮</a>
																		                <input type="checkbox" id="ydlx12" name="M12" value="12" /><a style="cursor:default;" onclick="ydlxChange12()">透水</a>
																		                <input type="checkbox" id="ydlx13" name="M12" value="13" /><a style="cursor:default;" onclick="ydlxChange13()">放炮</a>
																		                <input type="checkbox" id="ydlx14" name="M12" value="14" /><a style="cursor:default;" onclick="ydlxChange14()">火药爆炸</a>
																		                <input type="checkbox" id="ydlx15" name="M12" value="15" /><a style="cursor:default;" onclick="ydlxChange15()">瓦斯爆炸</a><br/>
																		                <input type="checkbox" id="ydlx16" name="M12" value="16" /><a style="cursor:default;" onclick="ydlxChange16()">锅炉爆炸</a>
																		                <input type="checkbox" id="ydlx17" name="M12" value="17" /><a style="cursor:default;" onclick="ydlxChange17()">容器爆炸</a>
																		                <input type="checkbox" id="ydlx18" name="M12" value="18" /><a style="cursor:default;" onclick="ydlxChange18()">其它爆炸</a>
																		                <input type="checkbox" id="ydlx19" name="M12" value="19" /><a style="cursor:default;" onclick="ydlxChange19()">中毒和窒息</a>
																		                <input type="checkbox" id="ydlx20" name="M12" value="20" /><a style="cursor:default;" onclick="ydlxChange20()">其它伤害</a></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">现场图片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_wz_m16">
					    <div id="m16fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">文档附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_wz_m17">
					    <div id="m17fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M13" type="text" value="${res.m13 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
					
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="ID1" value="${res.ID1}" />
					<input type="hidden" name="userid" value="${res.userid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
				<input type="hidden" id="locx" name="locx" value="${locx }"/>
				<input type="hidden" id="locy" name="locy" value="${locy }"/>
			</table>

		  	<tbody>
       </form>
 
 	<div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注应急物资位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_erm_dlg_map" style="width:100%;height: 285px;"></div>
	</div>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;
var $ = jQuery;

$list = $('#m16fileList'); //图片上传
$list2 = $('#m17fileList'); //文件上传

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

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
	isuploadImg();
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
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M16" value="'+newurl+'"/>');
		
		$('#uploader_wz_m16').append( $input );
		uploadImgFlag=true;
		
	}else{
		layer.msg(res.message,{time: 3000});
	}
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
		
		var $input = $('<input id="input_'+file.id+'" type="hidden" name="M17" value="'+newurl+'"/>');
		
		$('#uploader_wz_m17').append( $input );
	}else{
		layer.msg(res.message,{time: 3000});
	}
});

if('${action}' == 'update'){
	var zpUrl = '${res.m16}';
	if(zpUrl!=null&&zpUrl!=''){
		var arry =zpUrl.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ck_zp_"+i;
			var $li = $(
		            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
		                "<div class=\"info\">" + arry2[1] + "</div>" +
		            "</div>"
		            );

		    $list.append( $li );
		    
		    var $input = $('<input id="input_'+id+'" type="hidden" name="M16" value="'+arry[i]+'"/>');
			$('#uploader_wz_m16').append( $input );
		}
	}
	
	var fjUrl = '${res.m17}';
	if(fjUrl!=null&&fjUrl!=''){
		arry =fjUrl.split(",");
		for(var i=0;i<arry.length;i++){
			var arry2 =arry[i].split("||");
			var id="ck_fj_"+i;
			var $li = $(
		            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
		    $list2.append( $li );
		    
		    var $input = $('<input id="input_'+id+'" type="hidden" name="M17" value="'+arry[i]+'"/>');
			$('#uploader_wz_m17').append( $input );
		}
	}
	isuploadImg();
}

	var action = "${action}";
	var url="";
	var bmx_x="";
	var bmy_y="";
	var locx ='${res.m14}';
	var locy ='${res.m15}'; 
	var ydlx = "${ydlx}";
	var ydlxArr = ydlx.split(",");
	for(var i=0;i<ydlxArr.length;i++){
		$("input[name='M12']:checkbox[value='"+ydlxArr[i]+"']").attr('checked','true');
	}
	if (action == 'create') {
		url="/erm/yjzb/create";
		for(var i=1;i<=20;i++){
		$("input[name='M12']:checkbox[value='"+i+"']").attr('checked','true');
	}
	} else if (action == 'update') {
		url="/erm/yjzb/update";
	} else if (action == 'view') {
		for(var i=1;i<=15;i++){
			$("*[name='M" + i +"']").attr('readonly', 'readonly');
			$("*[name='M" + i +"']").css('background', '#eee');
		}
		$("*[name='M1']").attr('disabled', 'true');
		$("*[name='M12']").attr('disabled', 'true');
		$("#erm_yjzb_form_savebt").css('display','none');
		$("#map_test_divaxy").hide();
	}
	
	$(function(){ 
		$(".erm_yjzb_form_id_table td").mouseover(function() {
			$(this).addClass("over");
		});
		$(".erm_yjzb_form_id_table td").mouseout(function() {
			$(this).removeClass("over");
		});
		
		//init_map();
		
		$("#erm_yjzb_dlg_map").show();
		
		$("input[name='M12']:checkbox").css("width","18px");
		$("input[name='M12']:checkbox").css("height","18px");
	});
	//弹出地图界面
	function showMapXY(x_x,y_y){
		bmx_x=x_x;
		bmy_y=y_y;
		$("#yjzb_dlg").dialog("open").dialog("center").dialog("setTitle","经纬度标注");
		init_map('');
		
	}
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '300px'],
		    title: '标注坐标',
	        maxmin: true, 
	        shift: 1,
	        shade :0,
		    content: $('#enterprise_dlg'),
		    btn: ['确定', '关闭'],
		    success: function(layero, index){
		    	addmap("");
		    },
		    yes: function(index, layero){
		    	$("#erm_map_c_x").textbox("setValue", locx);//经度
				$("#erm_map_c_y").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
		  	cancel: function(index){ 
	        }
		});
	}
	
	function addmap(searchcon){	
		initMap("bis_erm_dlg_map",locx,locy,'','',1);
		map.setDefaultCursor("crosshair");//设置地图默认的鼠标指针样式
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		local.search(searchcon);

		var marker = new BMap.Marker(point); //创建marker对象
		map.addOverlay(marker); //在地图中添加marker
		
		map.addEventListener("click", function(e){	
			locx=e.point.lng;
			locy=e.point.lat;
			var now_point =  new BMap.Point(e.point.lng, e.point.lat );
			marker.setPosition(now_point);//设置覆盖物位置
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		});
		
	}
	
	function ydlxChange1(){
		$('#ydlx1').prop('checked',!$("#ydlx1").prop("checked"));
	}
	function ydlxChange2(){
		$('#ydlx2').prop('checked',!$("#ydlx2").prop("checked"));
	}
	function ydlxChange3(){
		$('#ydlx3').prop('checked',!$("#ydlx3").prop("checked"));
	}
	function ydlxChange4(){
		$('#ydlx4').prop('checked',!$("#ydlx4").prop("checked"));
	}
	function ydlxChange5(){
		$('#ydlx5').prop('checked',!$("#ydlx5").prop("checked"));
	}
	function ydlxChange6(){
		$('#ydlx6').prop('checked',!$("#ydlx6").prop("checked"));
	}
	function ydlxChange7(){
		$('#ydlx7').prop('checked',!$("#ydlx7").prop("checked"));
	}
	function ydlxChange8(){
		$('#ydlx8').prop('checked',!$("#ydlx8").prop("checked"));
	}
	function ydlxChange9(){
		$('#ydlx9').prop('checked',!$("#ydlx9").prop("checked"));
	}
	function ydlxChange10(){
		$('#ydlx10').prop('checked',!$("#ydlx10").prop("checked"));
	}
	function ydlxChange11(){
		$('#ydlx11').prop('checked',!$("#ydlx11").prop("checked"));
	}
	function ydlxChange12(){
		$('#ydlx12').prop('checked',!$("#ydlx12").prop("checked"));
	}
	function ydlxChange13(){
		$('#ydlx13').prop('checked',!$("#ydlx13").prop("checked"));
	}
	function ydlxChange14(){
		$('#ydlx14').prop('checked',!$("#ydlx14").prop("checked"));
	}
	function ydlxChange15(){
		$('#ydlx15').prop('checked',!$("#ydlx15").prop("checked"));
	}
	function ydlxChange16(){
		$('#ydlx16').prop('checked',!$("#ydlx16").prop("checked"));
	}
	function ydlxChange17(){
		$('#ydlx17').prop('checked',!$("#ydlx17").prop("checked"));
	}
	function ydlxChange18(){
		$('#ydlx18').prop('checked',!$("#ydlx18").prop("checked"));
	}
	function ydlxChange19(){
		$('#ydlx19').prop('checked',!$("#ydlx19").prop("checked"));
	}
	function ydlxChange20(){
		$('#ydlx20').prop('checked',!$("#ydlx20").prop("checked"));
	}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M16']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
	
</script>
</body>
</html>