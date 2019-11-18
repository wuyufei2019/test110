<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全设施管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/aqss/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${aqss.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
								data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${aqss.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr >
					<td class="width-15 active"><label class="pull-right">大类 ：</label></td>
					<td class="width-35"> <input type="text" name="M1" class="easyui-combobox" value="${aqss.m1 }"  data-options="required:'true', panelHeight:'auto', editable:false ,
									data: [
								        {value:'预防事故设施',text:'预防事故设施'},
								        {value:'控制事故设施',text:'控制事故设施'},
								        {value:'减少与消除事故影响设施',text:'减少与消除事故影响设施'} ],
								      	onSelect: function(rec){
							      		  var datam1 = [
							      		  {value:'检测、报警设施',text:'检测、报警设施'},
										        {value:'设备安全防护设施',text:'设备安全防护设施'},
										        {value:'防爆设施',text:'防爆设施'},
										        {value:'作业场所防护设施',text:'作业场所防护设施'},
										        {value:'安全警示标志',text:'安全警示标志'},
	       	        	  					  {value:'防雷防静电设施',text:'防雷防静电设施'},
	       	        	  					  {value:'粉尘监测设施',text:'粉尘监测设施'},
	       	        	  					  {value:'其他安全设施',text:'其他安全设施'}];  
								        
	       	        	  				 if(rec.value=='控制事故设施'){
	       	        	  					  datam1 = [
	       	        	  					  {value:'泄压和止逆设施',text:'泄压和止逆设施  '},
	       	        	  					  {value:'紧急处理设施',text:'紧急处理设施'}]; 
	       	        	  					 }else  if(rec.value=='减少与消除事故影响设施'){
	       	        	  					  datam1 = [
	       	        	  					  {value:'防止火灾蔓延设施',text:'防止火灾蔓延设施'},
	       	        	  					  {value:'灭火设施',text:'灭火设施'},
	       	        	  					  {value:'紧急个体处理设施',text:'紧急个体处理设施'},
	       	        	  					  {value:'应急救援设施',text:'应急救援设施'},
	       	        	  					  {value:'劳动防护用品和装备',text:'劳动防护用品和装备'} 
	       	        	  					  ]; 
	       	        	  					 }
	       	        	  					 $('#bis_aqss_m2').combobox('setValue',''); 
							       	         $('#bis_aqss_m2').combobox('loadData',datam1); 
								      	}" style="width: 100%;height: 30px;" /></td>
					<td class="width-15 active"><label class="pull-right">小类：</label></td>
					<td class="width-35"><input id="bis_aqss_m2" name="M2" class="easyui-combobox" value="${aqss.m2 }" style="width: 100%;height: 30px;" data-options="valueField:'value', textField:'text', panelHeight:'auto',
					onSelect: function(rec){
								      		 
								      			var  datam9 = [
								      			{value:'压力表',text:'压力表'},
								      			{value:'温度计',text:'温度计'},
								      			{value:'液位仪',text:'液位仪'},
								      			{value:'流量计',text:'流量计'},
								      			{value:'组份器',text:'组份器'},
								      			{value:'可燃气体报警仪',text:'可燃气体报警仪'},
								      			{value:'有毒有害气体报警仪',text:'有毒有害气体报警仪'},
								      			{value:'氧气检测仪',text:'氧气检测仪'}];
								        
	       	        	  				 if(rec.value=='设备安全防护设施'){
	       	        	  					  datam9 = [
	       	        	  					  	{value:'防护罩',text:'防护罩'},
	       	        	  					  	{value:'防护屏',text:'防护屏'},
	       	        	  					  	{value:'负荷限制器',text:'负荷限制器'},
	       	        	  					  	{value:'行程限制器',text:'行程限制器'},
	       	        	  					  	{value:'制动器',text:'制动器'},
	       	        	  					  	{value:'限速器',text:'限速器'},
	       	        	  					  	{value:'防雷设备',text:'防雷设备'},
	       	        	  					  	{value:'防潮设备',text:'防潮设备'},
	       	        	  					  	{value:'防晒设备',text:'防晒设备'},
	       	        	  					  	{value:'防冻设备',text:'防冻设备'},
	       	        	  					  	{value:'防腐设备',text:'防腐设备'},
	       	        	  					  	{value:'防渗漏设备',text:'防渗漏设备'},
	       	        	  					  	{value:'传动设备',text:'传动设备'},
	       	        	  					  	{value:'安全锁闭设备',text:'安全锁闭设备'},
	       	        	  					  	{value:'电器过载保护设备',text:'电器过载保护设备'},
	       	        	  					  	{value:'静电接地设备',text:'静电接地设备'}]; 
									            
	       	        	  					 }else  if(rec.value=='防爆设施'){
	       	        	  					  datam9 = [
	       	        	  					  	{value:'电气防爆设备',text:'电气防爆设备'},
	       	        	  					  	{value:'仪表防爆设备',text:'仪表防爆设备'},
	       	        	  					  	{value:'抑制助燃物品混入（如氮封）监测器',text:'抑制助燃物品混入（如氮封）监测器'},
	       	        	  					  	{value:'易燃易爆气体监测器',text:'易燃易爆气体监测器'},
	       	        	  					  	{value:'粉尘形成监测器',text:'粉尘形成监测器'},
	       	        	  					  	{value:'阻隔防爆器材',text:'阻隔防爆器材'},
	       	        	  					  	{value:'防爆工器具',text:'防爆工器具'}]; 
	       	        	  					 }else  if(rec.value=='作业场所防护设施'){
	       	        	  					  datam9 = [
	       	        	  					 	 {value:'防辐射设施',text:'防辐射设施'},
	       	        	  					 	 {value:'防静电设施',text:'防静电设施'},
	       	        	  					 	 {value:'防噪音设施',text:'防噪音设施'},
	       	        	  					 	 {value:'通风设施',text:'通风设施'},
	       	        	  					 	 {value:'除尘设施',text:'除尘设施'},
	       	        	  					 	 {value:'排毒设施',text:'排毒设施'},
	       	        	  					 	 {value:'防护栏（网）',text:'防护栏（网）'},
	       	        	  					 	 {value:'防滑设施',text:'防滑设施'},
	       	        	  					 	 {value:'防灼烫设施',text:'防灼烫设施'}]; 
	       	        	  					 } else  if(rec.value=='安全警示标志'){
	       	        	  					  datam9 = [
	       	        	  					   {value:'各种指示标识',text:'各种指示标识'},
	       	        	  					   {value:'警示作业安全',text:'警示作业安全'},
	       	        	  					   {value:'逃生避难标识',text:'逃生避难标识'},
	       	        	  					   {value:'风向警示标志',text:'风向警示标志'}]; 
	       	        	  					 } else  if(rec.value=='泄压和止逆设施'){
	       	        	  					  datam9 = [
	       	        	  					    {value:'泄压阀门',text:'泄压阀门'},
	       	        	  					    {value:'爆破片',text:'爆破片'},
	       	        	  					    {value:'放空管',text:'放空管'},
	       	        	  					    {value:'止逆的阀门',text:'止逆的阀门'},
	       	        	  					    {value:'真空系统的密封设施',text:'真空系统的密封设施'} ]; 
	       	        	  					 } else  if(rec.value=='紧急处理设施'){
	       	        	  					  datam9 = [
	       	        	  					   {value:'紧急备用电源',text:'紧急备用电源'},
	       	        	  					   {value:'紧急切断设施',text:'紧急切断设施'},
	       	        	  					   {value:'分流设施',text:'分流设施'},
	       	        	  					   {value:'排放（火炬）设施',text:'排放（火炬）设施'},
	       	        	  					   {value:'吸收设施',text:'吸收设施'},
	       	        	  					   {value:'中和设施',text:'中和设施'},
	       	        	  					   {value:'冷却设施',text:'冷却设施'},
	       	        	  					   {value:'通入或者加入惰性气体设施',text:'通入或者加入惰性气体设施'},
	       	        	  					   {value:'反应抑制剂设施',text:'反应抑制剂设施'},
	       	        	  					   {value:'紧急停车设施',text:'紧急停车设施'},
	       	        	  					   {value:'仪表联锁设施',text:'仪表联锁设施'}]; 
	       	        	  					 } else  if(rec.value=='防止火灾蔓延设施'){
	       	        	  					  datam9 = [
	       	        	  					 	 {value:'阻火器',text:'阻火器'},
	       	        	  					 	 {value:'安全水封',text:'安全水封'},
	       	        	  					 	 {value:'回火防止器',text:'回火防止器'},
	       	        	  					 	 {value:'防油（火）堤',text:'防油（火）堤'},
	       	        	  					 	 {value:'防爆墙',text:'防爆墙'},
	       	        	  					 	 {value:'防爆门等',text:'防爆门等'},
	       	        	  					 	 {value:'防火墙',text:'防火墙'},
	       	        	  					 	 {value:'防火门',text:'防火门'},
	       	        	  					 	 {value:'蒸汽幕',text:'蒸汽幕'},
	       	        	  					 	 {value:'水幕',text:'水幕'},
	       	        	  					 	 {value:'防火材料涂层',text:'防火材料涂层'}]; 
	       	        	  					 } else  if(rec.value=='灭火设施'){
	       	        	  					  datam9 = [
	       	        	  					 	{value:'水喷淋灭火设施',text:'水喷淋灭火设施'},
	       	        	  					 	{value:'惰性气体灭火设施',text:'惰性气体灭火设施'},
	       	        	  					 	{value:'蒸气灭火设施',text:'蒸气灭火设施'},
	       	        	  					 	{value:'泡沫释放灭火设施',text:'泡沫释放灭火设施'},
	       	        	  					 	{value:'消火栓',text:'消火栓'},
	       	        	  					 	{value:'高压水枪（炮）',text:'高压水枪（炮）'},
	       	        	  					 	{value:'消防车',text:'消防车'},
	       	        	  					 	{value:'消防水管网',text:'消防水管网'},
	       	        	  					 	{value:'消防站',text:'消防站'}]; 
	       	        	  					 } else  if(rec.value=='紧急个体处理设施'){
	       	        	  					  datam9 = [
	       	        	  					 	 {value:'洗眼器',text:'洗眼器'},
	       	        	  					 	 {value:'喷淋器',text:'喷淋器'},
	       	        	  					 	 {value:'逃生器',text:'逃生器'},
	       	        	  					 	 {value:'逃生索',text:'逃生索'},
	       	        	  					 	 {value:'应急照明灯',text:'应急照明灯'}]; 
	       	        	  					 } else  if(rec.value=='应急救援设施'){
	       	        	  					  datam9 = [
	       	        	  					  	 {value:'工程抢险装备',text:'工程抢险装备'},
	       	        	  					  	 {value:'现场受伤人员医疗抢救装备',text:'现场受伤人员医疗抢救装备'}]; 
	       	        	  					 } else  if(rec.value=='劳动防护用品和装备'){
	       	        	  					  datam9 = [
	       	        	  					  	{value:'头部防护用品',text:'头部防护用品'},
	       	        	  					  	{value:'面部防护用品',text:'面部防护用品'},
	       	        	  					  	{value:'视觉防护用品',text:'视觉防护用品'},
	       	        	  					  	{value:'呼吸防护用品',text:'呼吸防护用品'},
	       	        	  					  	{value:'听觉器官防护用品',text:'听觉器官防护用品'},
	       	        	  					  	{value:'四肢防护用品',text:'四肢防护用品'},
	       	        	  					  	{value:'躯干防火设施',text:'躯干防火设施'},
	       	        	  					  	{value:'防毒设施',text:'防毒设施'},
	       	        	  					  	{value:'防灼烫设施',text:'防灼烫设施'},
	       	        	  					  	{value:'防腐蚀设施',text:'防腐蚀设施'},
	       	        	  					  	{value:'防噪声设施',text:'防噪声设施'},
	       	        	  					  	{value:'防光射设施',text:'防光射设施'},
	       	        	  					  	{value:'防高处坠落设施',text:'防高处坠落设施'},
	       	        	  					  	{value:'防砸击设施',text:'防砸击设施'},
	       	        	  					  	{value:'防刺伤设施',text:'防刺伤设施'}]; 
	       	        	  					 } else  if(rec.value=='逃生避难设施'){
	       	        	  					  datam9 = [
		       	        	  					 {value:'逃生和避难的安全通道（梯）',text:'逃生和避难的安全通道（梯）'},
		       	        	  					 {value:'安全避难所（带空气呼吸系统）',text:'安全避难所（带空气呼吸系统）'},
		       	        	  					 {value:'避难信号',text:'避难信号'}]; 
	       	        	  					 } 
							       	          $('#bis_aqss_m9').combobox('loadData',datam9); 
								      	}
					 "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">安全设施名称</label></td>
					<td class="width-35" colspan="3"><input id="bis_aqss_m9" name="M9" class="easyui-combobox" value="${aqss.m9 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]',panelHeight:'150px' " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">涉及工艺设施：</label></td>
					<td class="width-35" colspan="3"><input name="M3" class="easyui-textbox" value="${aqss.m3 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检测时间：</label></td>
					<td class="width-35"><input class="easyui-datebox" name="M5" value="<fmt:formatDate value="${aqss.m5 }"/>" style="width:100%;height:30px;" data-options="editable:false"/></td>
					<td class="width-15 active"><label class="pull-right">到期时间：</label></td>
					<td class="width-35" ><input class="easyui-datebox" name="M6" value="<fmt:formatDate value="${aqss.m6 }"/>" style="width:100%;height:30px;" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">数量：</label></td>
					<td class="width-35"><input class="easyui-textbox" name="M4" value="${aqss.m4 }" style="width: 100%;height: 30px;" data-options="validType:'integ' "/></td>
					<td class="width-15 active"><label class="pull-right">状态：</label></td>
					<td class="width-35" >
						<input class="easyui-combobox" name="M7" value="${aqss.m7 }" style="width: 100%;height: 30px;"data-options="editable:false ,panelHeight:'auto' ,
							data: [ {value:'1',text:'失效'},
								    {value:'2',text:'停用'},
								    {value:'0',text:'正常'}] " />
					</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M8" type="text" value="${aqss.m8 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,250]'">
					</td>
					
				</tr>
			<!-- 
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m9">
					    <div id="m9fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">图纸附件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m10">
					    <div id="m10fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				 -->
				<c:if test="${not empty aqss.ID}">
					<input type="hidden" name="ID" value="${aqss.ID}" />
					<input type="hidden" name="ID1" value="${aqss.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${aqss.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${aqss.s3}" />
				</c:if>
				</tbody>
			</table>

		  	
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	
	var $ = jQuery,
    $list = $('#m9fileList'); //图片上传
	$list2 = $('#m10fileList'); //文件上传
	
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

	    server: '${ctx}/kindeditor/upload?dir=draw',
	    		
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M9" value="'+newurl+'"/>');
			
			$('#uploader_ws_m9').append( $input );
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M10" value="'+newurl+'"/>');
			
			$('#uploader_ws_m10').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		var zpUrl = '${m9}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M9" value="'+arry[i]+'"/>');
				$('#uploader_ws_m9').append( $input );
			}
		}
		
		var fjUrl = '${m10}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M10" value="'+arry[i]+'"/>');
				$('#uploader_ws_m10').append( $input );
			}
		}
	}
	
	
	</script>
</body>
</html>