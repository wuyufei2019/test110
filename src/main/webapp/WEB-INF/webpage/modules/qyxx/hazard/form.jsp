<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<title></title>
	<script>
		var demo = '';
		var action = "${action}";
		$(function () {
			if (action =='create'){
				demo='x';
			}
			if (action == 'update'){
				demo = "${wxyId}";
			}
			if (action == 'view'){
				demo = "${wxyId}";
				$("#saveButton").hide();
				$("#addButton").hide();
				$("#updateButton").hide();
				$("#deleteButton").hide();
			}

		})
	</script>
<script type="text/javascript" src="${ctx}/static/model/js/qyxx/hazardIdentity/index.js?v=1.1"></script>
</head>
<body>
	<div style="width:850px;height:auto;padding:5px 5px; margin: 0 auto;" >
		<form id="bis_hazard_form_mainform" novalidate>
			<table class="table table-bordered dataTable" style="margin:auto;">
				<tr>
					<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30" >
						<input class="easyui-textbox" name="M12"
							   value="${bh.m12}" style="width:220px;height:30px;"
							   data-options="required:true,validType:['length[0,100]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">网关编码：</label></td>
					<td class="width-30" >
						<input class="easyui-textbox" name="gatewaycode"
							   value="${bh.gatewaycode}" style="width:220px;height:30px;"
							   data-options="validType:['length[0,11]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">重大危险源简称：</label></td>
					<td class="width-30" >
						<input class="easyui-textbox" name="hazardshortname" id=""
							   value="${bh.hazardshortname }" style="width:220px;height:30px;"
							   data-options="required:true,validType:['length[0,50]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">重大危险源编码：</label></td>
					<td class="width-30" >
						<input class="easyui-textbox" name="hazardcode" id=""
							   value="${bh.hazardcode }" style="width:220px;height:30px;"
							   data-options="validType:['length[0,14]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">企业档案R值：</label></td>
					<td class="width-30"> <input name="M2" id="bis_hazard_M2" class="easyui-textbox"
								value="${bh.m2 }" style="width:220px;height:30px;"
								data-options="validType:['number','length[0,10]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">重大危险源级别：</label></td>
					<td class="width-30"><div>
							<input class="easyui-combobox" id="bis_hazard_M1" name="M1"
								value="${bh.m1 }" style="width:220px;height:30px;"
								data-options="editable:false ,panelHeight:'auto',data: [
								        {value:'1',text:'一级'},
								        {value:'2',text:'二级'},
								        {value:'3',text:'三级'},
								        {value:'4',text:'四级'} ]" />
						</div></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-80" colspan="3" >
						<input class="easyui-textbox" name="address" id=""
							   value="${bh.address }" style="width:100%;height:30px;"
							   data-options="required:true,validType:['length[0,200]']" />
					</td>
				</tr>
				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">经纬度：</label></td>
					<td colspan="3">
						<span><label>经度</label>
						<input id="longitude" name="longitude" value="${bh.longitude}" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:100px;height:30px;"/>
						<label>纬度</label> 
						<input id="latitude" name="latitude" value="${bh.latitude }" class="easyui-textbox" readonly="readonly" data-options="required:'true'" style="width:100px;height:30px;"/>
						<a class="btn btn-primary" onclick="showMapXY( )" style="width:60px;">定位</a></span></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">投用日期：</label></td>
					<td class="width-30" >
						<input id="" name="establishdate" class="easyui-datebox" value="${bh.establishdate }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">最大罐组：</label></td>
					<td class="width-30" >
						<input id="" name="maxjar" class="easyui-textbox" value="${bh.maxjar }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">周边防护目标最近距离（米）：</label></td>
					<td class="width-30" >
						<input id="" name="protectiontargetdistance" class="easyui-textbox" value="${bh.protectiontargetdistance }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,5]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">外边界500米范围人数估算：</label></td>
					<td class="width-30" >
						<input id="" name="people500m" class="easyui-textbox" value="${bh.people500m }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,8]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">压力容器总数：</label></td>
					<td class="width-30" >
						<input id="" name="presvesselnum" class="easyui-textbox" value="${bh.presvesselnum }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,8]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">生产经营活动类型：</label></td>
					<td class="width-30" >
						<input id="" name="activetype" class="easyui-textbox" value="${bh.activetype }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']" />
					</td>
				</tr>			
				
				<tr>
					<td class="width-20 active"><label class="pull-right">三类压力容器总数：</label></td>
					<td class="width-30" >
						<input id="" name="presVessel3thnum" class="easyui-textbox" value="${bh.presVessel3thnum }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,8]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">生产存储场所产权：</label></td>
					<td class="width-30" >
						<input id="" name="storagefacilityproperty" class="easyui-textbox" value="${bh.storagefacilityproperty }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">主要产品：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="material" value="${bh.material }" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:['length[0,500]']" />
					</td>
				
				</tr>
				
				<tr>
 					<td class="width-20 active"><label class="pull-right">最近一次检测时间：</label></td>
					<td class="width-30" >
						<input id="" name="lightdevlastchkdate" class="easyui-datebox" value="${bh.lightdevlastchkdate }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					</td> 
					
					<td class="width-20 active"><label class="pull-right">到期时间：</label></td>
					<td class="width-30" >
						<input id="" name="lightdevvalidate" class="easyui-datebox" value="${bh.lightdevvalidate }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					</td> 
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">危险源备案期限(起)：</label></td>
					<td class="width-30" >
						<input id="" name="startarchivedate" class="easyui-datebox" value="${bh.startarchivedate }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					</td>
					
					<td class="width-20 active"><label class="pull-right">危险源备案期限(止)：</label></td>
					<td class="width-30" >
						<input id="" name="endarchivedate" class="easyui-datebox" value="${bh.endarchivedate }" style="width: 100%;height: 30px;" data-options="editable:false,showSeconds:false" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">安全负责人姓名：</label></td>
					<td class="width-30" >
						<input id="" name="M6" class="easyui-textbox" value="${bh.m6 }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]']" />
					</td>
					<td class="width-20 active"><label class="pull-right">安全负责人手机：</label></td>
					<td class="width-30" >
						<input id="" name="M7" class="easyui-textbox" value="${bh.m7 }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]'],validType:'mobile'" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">是否在化工园区内：</label></td>
					<td class="width-30" >
						<input id="inindustrialpark" name="inindustrialpark" class="easyui-combobox" value="${bh.inindustrialpark }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',data:[{value:'1',text:'是'},{value:'0',text:'否'}]" />
					</td>
					<td class="width-20 active"><label class="pull-right">园区标识：</label></td>
					<td class="width-30" >
						<input id="parkid" name="parkid" class="easyui-textbox" value="${bh.parkid }" style="width: 100%;height: 30px;" data-options="validType:['length[0,32]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">所属化工园区名称：</label></td>
					<td class="width-30" colspan="3">
						<input id="industrialparkname" name="industrialparkname" class="easyui-textbox" value="${bh.industrialparkname }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto',validType:['length[0,100]']" />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">增加暴露人数：</label></td>
					<td><input id="M9_1" name="M9_1" value="${bh.m9_1 }"
						class="easyui-numberbox" style="width:220px;height:30px;"
						data-options="min:0,validType:'length[1,10]'" /></td>
					<td class="width-20 active"><label class="pull-right">厂区人数：</label></td>
					<td class="width-30"> <input name="M3" class="easyui-textbox" value="${bh.m3 }"
								style="width:220px;height:30px;"
								data-options="validType:['length[0,10]','integ']" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">系统计算R值：</label></td>
					<td class="width-30"> <input class="easyui-textbox" value="${bh.m9 }"
								style="width:220px;height:30px;" data-options="disabled:true" />
					</td>
					<td class="width-20 active"><label class="pull-right">是否有监控预警系统：</label></td>
					<td class="width-30"><div>
							<input class="easyui-combobox" name="M4" value="${bh.m4 }"
								style="width:220px;height:30px;"
								data-options="panelHeight:50,editable:false ,data: [
								        {value:'0',text:'否'},
								        {value:'1',text:'是'} ]
						    " />
						</div></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">主要危险性：</label></td>
					<td class="width-30" colspan="3"> 
							<input class="easyui-combobox" name="M5" id="hcc"
								value="${bh.m5 }" style="width:645px;height:30px;"
								data-options="
								required:true,
								editable:false ,
								valueField: 'text',
								textField: 'text',
								multiple:'true',
								url:'${ctx}/tcode/dict/wxhxpzywx',
								onSelect: 
								function(rec){
								$('#haz').val($('#hcc').combobox('getValues').join(','));
								},
								onLoadSuccess: 
								function(){
								var hazx=$('#haz').val().split(',');
								$('#hcc').combobox('setValues', hazx);
								}
								 " />
				 </td>

				</tr>


				<tr>
					<td class="width-20 active"><label class="pull-right">易导致事故类型： </label></td>
					<td class="width-30" align="left" colspan="3">
					<input type="checkbox" id="ydlx1" name="M10" class="i-checks" value="1" title="" lay-skin="primary" />物体打击 
					<input type="checkbox" id="ydlx2" name="M10" class="i-checks" value="2" title="" lay-skin="primary" />车辆伤害 
					<input type="checkbox" id="ydlx3" name="M10" class="i-checks" value="3" title="" lay-skin="primary" />机械伤害
					<input type="checkbox" id="ydlx4" name="M10" class="i-checks" value="4" title="" lay-skin="primary" />起重伤害 
					<input type="checkbox" id="ydlx5" name="M10" class="i-checks" value="5" title="" lay-skin="primary" />触电 
					<input type="checkbox" id="ydlx6" name="M10" class="i-checks" value="6" title="" lay-skin="primary" />淹溺 
					<input type="checkbox" id="ydlx7" name="M10" class="i-checks" value="7" title="" lay-skin="primary" />灼烫
					<input type="checkbox" id="ydlx8" name="M10" class="i-checks" value="8" title="" lay-skin="primary" />火灾 
					<input type="checkbox" id="ydlx9" name="M10" class="i-checks" value="9" title="" lay-skin="primary" />高处坠落 </br>
					<input type="checkbox" id="ydlx12" name="M10" class="i-checks" value="12" title="" lay-skin="primary" />透水
					<input type="checkbox" id="ydlx10" name="M10" class="i-checks" value="10" title="" lay-skin="primary" />坍塌
					<input type="checkbox" id="ydlx11" name="M10" class="i-checks" value="11" title="" lay-skin="primary" />冒顶片帮 
					<input type="checkbox" id="ydlx13" name="M10" class="i-checks" value="13" title="" lay-skin="primary" />放炮 
					<input type="checkbox" id="ydlx14" name="M10" class="i-checks" value="14" title="" lay-skin="primary" />火药爆炸
					<input type="checkbox" id="ydlx15" name="M10" class="i-checks" value="15" title="" lay-skin="primary" />瓦斯爆炸 
					<input type="checkbox" id="ydlx16" name="M10" class="i-checks" value="16" title="" lay-skin="primary" />锅炉爆炸 
					<input type="checkbox" id="ydlx17" name="M10" class="i-checks" value="17" title="" lay-skin="primary" />容器爆炸 
					<input type="checkbox" id="ydlx18" name="M10" class="i-checks" value="18" title="" lay-skin="primary" />其它爆炸</br>
					<input type="checkbox" id="ydlx19" name="M10" class="i-checks" value="19" title="" lay-skin="primary" />中毒和窒息 
					<input type="checkbox" id="ydlx20" name="M10" class="i-checks" value="20" title="" lay-skin="primary" />其它伤害</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m11">
					    <div id="m11fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">安全监控措施：</label></td>
					<td class="width-30" colspan="3"><input type="text" name="M8"
						value="${bh.m8 }" class="easyui-textbox"
						style="width: 100%;height: 80px;" data-options="multiline:true" />
					</td>

				</tr>
				<c:if test="${not empty bh.ID}">
					<input type="hidden" name="ID" value="${bh.ID}" />
					<input type="hidden" name="ID1" value="${bh.ID1}" />
					<input type="hidden" name="watercode" value="${bh.watercode}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${bh.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${bh.s3}" />
				</c:if>
				<input type="hidden" id="haz" name="haz" value="${bh.m5}" />
				<input type="hidden" name="ID" id="ID" value="" />
			</table>
		</form>
		<div id="saveButton" style="text-align:center;margin: 20px;">
			<a id="bis_hazard_form_savebt" class="btn btn-primary"
				onclick="bis_hazard_form_save()" style="width:120px">保存</a>
		</div>
	</div>

	<div id="tb" style="padding:5px;height:auto">
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">
					<button id="addButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="add()" title="添加">
						<i class="fa fa-plus"></i> 添加
					</button>
					<button id="updateButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="upd()" title="修改">
						<i class="fa fa-file-text-o"></i> 修改
					</button>
					<button id="deleteButton" class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="del()" title="删除">
						<i class="fa fa-trash-o"></i> 删除
					</button>
					<button class="btn btn-white btn-sm" data-toggle="tooltip"
						data-placement="left" onclick="view()" title="查看">
						<i class="fa fa-search-plus"></i> 查看
					</button>
					<%--<button class="btn btn-white btn-sm" data-toggle="tooltip"--%>
						<%--data-placement="left" onclick="fileexport()" title="导出">--%>
						<%--<i class="fa fa-external-link"></i> 导出--%>
					<%--</button>--%>
					<button class="btn btn-white btn-sm " data-toggle="tooltip"
							data-placement="left" onclick="refresh()" title="刷新">
							<i class="glyphicon glyphicon-repeat"></i> 刷新
						</button>
				</div>
			</div>
		</div>
	</div>
	<div style="height:300px">
	<table id="dg"></table>
	</div>
	<div style="display:none">
		<shiro:hasAnyRoles name="企业">
			<input type="hidden" id="bis_qy_qx" value="qy" />
		</shiro:hasAnyRoles>
	</div>
	
	    <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none;" >
		<div><span style="color: red;margin: 0 10px;">点击地图标注位置!</span>
		<input class="easyui-searchbox" style="width:300px" data-options="prompt:'请输入搜索条件',searcher:function search(value,name){ addmap(value); }" />
		</div>
		<div id="bis_enterprise_dlg_map" style="width:100%;height: 285px;"></div>
		</div>
	
	
	<script type="text/javascript">
	var dg;
	var $ = jQuery;
    $list = $('#m11fileList'); //图片上传
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		        $img = $li.find('img');

		    $list.append( $li );
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M11" value="'+newurl+'"/>');
			
			$('#uploader_ws_m11').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});


	if('${action}' == 'update'){
		var zpUrl = '${qylist.m11}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M11" value="'+arry[i]+'"/>');
				$('#uploader_ws_m11').append( $input );
			}
		}
	}
	
	
	

		var url = "";
		var qylist = "${bh.m10}";
		var qylistArr = qylist.split(",");
		var returnId ;
		for (var i = 0; i < qylistArr.length; i++) {
			$("input[name='M10']:checkbox[value='" + qylistArr[i] + "']").attr('checked', 'true');
		}
		$(function() {
			if (action == 'create') {
				$("#bis_yc").hide();
                $("#inindustrialpark").combobox('setValue','${inindustrialpark}');
                $('#parkid').textbox('setValue','${parkid}');
                $('#industrialparkname').textbox('setValue','${industrialparkname}');
			} else if (action == 'update') {
				$("#bis_yc").show();
				returnId = demo ;
			}else if (action == 'view'){
				returnId = demo ;
			}
	
			$("input[name='M10']:checkbox").css("width", "18px");
			$("input[name='M10']:checkbox").css("height", "18px");
					
			$("#bis_hazard_M1").combobox('readonly',true);
		});
	
		function bis_hazard_form_save() {
	
			// 添加修改区分
			if (action == 'create') {
				url = "${ctx}/bis/hazard/create/";
				$("#bis_hazard_form_id_table_td_div1_01").css('display', 'none');
			} else if (action == 'update') {
				url = "${ctx}/bis/hazard/update/";
			}

			var isValid = $("#bis_hazard_form_mainform").form('validate');
			if (!isValid) {
				return isValid; //返回false终止表单提交
			} else {
				$.ajax({
					type : "POST",
					url : url,
					data : $("#bis_hazard_form_mainform").serialize(),
					success : function(data) {
						returnId = data;
						demo = data;
						$("#ID").val(returnId);
						if (action == 'create'){
							var lj = '${ctx}/bis/hazard/update/';
							window.location = lj+ demo;
						}
						parent.dg.datagrid('reload');
						layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
					},
					error : function(e) {
						alert("出错：" + e);
					}

				});
			}
		}

		$(function() {
			if (returnId == ''||returnId==null){
				$("#tb").hide();
			} else {
				$("#tb").show();
			}
			$('#bis_hazard_M2').textbox({  
				  onChange: function(value){  
					  var r = value;
						if (r < 10) {
							$("#bis_hazard_M1").combobox("setValue", "4");
						} else if (10 <= r && r < 50) {
							$("#bis_hazard_M1").combobox("setValue", "3");
						} else if (50 <= r && r < 100) {
							$("#bis_hazard_M1").combobox("setValue", "2");
						} else {
							$("#bis_hazard_M1").combobox("setValue", "1");
						}
				  }  
			}); 
			 
		});
	function doSubmit() {
		$("#inputForm").submit();
		parent.layer.close(index);//关闭对话框。
        parent.window.location.reload();
	}
	
	
	var locx ='${bh.longitude}';
	var locy ='${bh.latitude}';
	
	//弹出地图界面
	function showMapXY( ){
		layer.open({
		    type: 1,  
		    area: ['500px', '400px'],
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
		    	$("#longitude").textbox("setValue", locx);//经度
				$("#latitude").textbox("setValue", locy);//纬度
				layer.close(index);
			  },
			  cancel: function(index){ 
		       }
		});
	}
	
	function addmap(searchcon){	
		initMap("bis_enterprise_dlg_map",locx,locy);
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
	
	
	
	
	</script>
</body>
</html>