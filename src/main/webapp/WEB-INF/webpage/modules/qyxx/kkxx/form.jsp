<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>卡口信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
	<!-- 引入蜂鸟地图JS -->
	<link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
	<!-- 引入自定义 css js -->
	<%--<link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
	<script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>--%>
	<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
	<script src="${ctxStatic}/fengmap/js/createBubble.js"></script>
	<script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/kkxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-80">
							<input value="${kkxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-80">
						<input value="${kkxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">卡口名称：</label></td>
					<td class="width-80"><input name="M1" type="text" class="easyui-textbox" value="${kkxx.m1 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">卡口位置：</label></td>
					<td class="width-80">
						<span><input id='M2' name="M2" type="text" class="easyui-textbox" value='${kkxx.m2 }' style="width: 600px;height: 30px;" readonly="readonly" data-options=" validType:'length[0,100]' " />
						<a class="btn btn-primary" onclick="showMapXY()" style="height:30px;width:20%;">地图选点</a></span>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">卡口设备 ：</label></td>
					<td class="width-50"><input name="M3" type="text" value="${kkxx.m3}"   class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'"></td>

				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">卡口图片 ：</label></td>
					<td class="width-80">
						<div id="uploader_ws_m15">
							<div id="m15fileList" class="uploader-list"></div>
							<div id="imagePicker">选择图片</div>
						</div>
					</td>
				</tr>
				
				<c:if test="${not empty kkxx.ID}">
					<input type="hidden" name="ID" value="${kkxx.ID}"/>
					<input type="hidden" name="ID1" value="${kkxx.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${kkxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${kkxx.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>

	 <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none" >
		 <div id="fengMap" style="width: 100%;height: 100%"></div>
	 </div>

<script type="text/javascript">
	var usertype=${usertype};
	var uploadImgFlag = false;//是否上传图片
	var $list = $('#m15fileList');

	//设置选点参数
	var locx;
	var locy;
	var locz;
	var layers;
	var im;
	var group;

	//地图初始化
	var fmapId = 'realidmap1';

	map = new fengmap.FMMap({
		//渲染dom
		container : document.getElementById('fengMap'),
		//地图数据位置
		mapServerURL : '${ctxStatic}/fengmap/data/' + fmapId,

		//主题数据位置
		mapThemeURL : '${ctxStatic}/fengmap/data/theme',
		//设置主题
		defaultThemeName : 'realidmap1',

		// 默认比例尺级别设置为20级
		defaultMapScaleLevel : 18,
		//两楼层间的高度
		defaultGroupSpace: 20,
		//不支持单击模型高亮，true为单击时模型高亮
		modelSelectedEffect : false,

		//是否对不可见图层启用透明设置 默认为true
		focusAlphaMode : false,
		//对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
		focusAlpha : 0.1,

		//开发者申请应用下web服务的key
		key : 'b501e571307da75ab6c89e960e5f6148',
		//开发者申请应用名称
		appName : '苏州兴业'
	});

	//控件配置
	//楼层控制控件配置参数
	var ctlOpt = new fengmap.controlOptions({
		position: fengmap.controlPositon.RIGHT_TOP,//默认在右上角
		showBtnCount: 3,  //默认显示楼层的个数
		allLayer:true,   //初始是否是多层显示，默认单层显示
		needAllLayerBtn: true, //是否显示多层/单层切换按钮
		//位置x,y的偏移量
		offset: {
			x: 5,
			y: 400
		},
		imgURL : '${ctxStatic}/fengmap/image/'
	});

	//2/3D切换配置
	var toolControl2 = new fengmap.toolControl(map,{
		init2D:false,   //初始化2D模式
		groupsButtonNeeded:false,   //设置为false表示只显示2D,3D切换按钮
		//点击按钮的回调方法,返回type表示按钮类型,value表示对应的功能值
		clickCallBack:function(type,value){
			// console.log(type,value);
		}
	});

	function addMarkers(locx,locy,locz) {
		//获取当前聚焦楼层
		group = map.getFMGroup(locz);
		layers = group.getOrCreateLayer('imageMarker');
		im = new fengmap.FMImageMarker({
			//标注x坐标点
			x: locx,
			//标注y坐标点
			y: locy,
			//设置图片路径
			url: '${ctxStatic}/fengmap/image/blueImageMarker.png',
			//设置图片显示尺寸
			size: 32,
			//标注高度，大于model的高度
			height: 4
		});
		layers.addMarker(im);
	}

	//选点
	map.on('mapClickNode', function (event) {
		if(layers){
			layers.removeAll();
		}
		var target = event.target;
		if(!target){
			console.log('标点不是地图模型！');
			return;
		}
		locx = event.eventInfo.coord.x;
		locy = event.eventInfo.coord.y;
		locz = event.target.groupID;
		addMarkers(locx,locy,locz);
	});

	//弹出地图界面
	function showMapXY( ){
		layer.open({
			type: 1,
			area: ['800px', '700px'],
			title: '标注坐标',
			maxmin: true,
			shift: 1,
			shade :0,
			content: $('#enterprise_dlg'),
			btn: ['确定', '关闭'],
			success: function(layero, index){
				map.openMapById(fmapId);
				map.on('loadComplete', function() {
					console.log('地图加载完成!');
					groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
					pointInit();
					addMarkers(locx,locy,locz);
				});
			},
			yes: function(index, layero){
				debugger;
				$("#M2").textbox("setValue", "{\"x\":" + locx + ",\"y\":" + locy + ",\"z\":" + locz + "}");
				layer.close(index);
			},
			cancel: function(index){
			}
		});
	}

	function pointInit() {
		var pos = JSON.parse('${kkxx.m2}');
		locx = pos.x;
		locy = pos.y;
		locz = pos.z;
	}

	var uploader = WebUploader.create({

		auto: true,

		swf: '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

		server: '${ctx}/kindeditor/upload?dir=image',

		pick: {
			id: '#imagePicker',
			multiple: false,
		},
		duplicate: true,
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/jpg,image/jpeg,image/png'
		},
		compress: {
			width: 1200,
			height: 1200,
			quality: 90,
			allowMagnify: false,
			crop: false,
			preserveHeaders: false,
			noCompressIfLarger: false,
			compressSize: 1024 * 50
		}
	});

	uploader.on('uploadProgress', function (file, percentage) {
		$.jBox.tip("正在上传，请稍等...", 'loading', {opacity: 0});
	});

	// 负责预览图的销毁
	function removeFile(fileid) {
		$("#" + fileid).remove();
		$("#input_" + fileid).remove();
		isuploadImg();
	};

	// 图片上传成功，显示预览图
	uploader.on('uploadSuccess', function (file, res) {
		$.jBox.closeTip();
		if (res.error == 0) {
			var $li = $(
					"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
					"<span class=\"cancel\" onClick=\"removeFile('" + file.id + "')\" style=\"cursor: pointer\">删除</span>" +
					"<img>" +
					"<div class=\"info\">" + file.name + "</div>" +
					"</div>"
					),

					$img = $li.find('img');

			$list.append($li);
			uploadImgFlag = true;
			// 创建缩略图
			uploader.makeThumb(file, function (error, src) {
				if (error) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}

				$img.attr('src', src);
			}, 100, 100);


			var newurl = res.url + "||" + res.fileName;
			var $input = $('<input id="input_' + file.id + '" type="hidden" name="M4" value="' + newurl + '"/>');

			$('#uploader_ws_m15').append($input);
		} else {
			layer.msg(res.message, {time: 3000});
		}
	});

	if ('${action}' == 'update') {
		var zpUrl = '${kkxx.m4}';
		if (zpUrl != null && zpUrl != '') {
			var arry = zpUrl.split(",");
			for (var i = 0; i < arry.length; i++) {
				var arry2 = arry[i].split("||");
				var id = "ws_zp_" + i;
				var $li = $(
						"<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
						"<span class=\"cancel\" onClick=\"removeFile('" + id + "')\" style=\"cursor: pointer\">删除</span>" +
						"<img src=\"" + arry2[0] + "\" style=\"width:100px; height: 100px\">" +
						"<div class=\"info\">" + arry2[1] + "</div>" +
						"</div>"
				);

				$list.append($li);

				var $input = $('<input id="input_' + id + '" type="hidden" name="M4" value="' + arry[i] + '"/>');
				$('#uploader_ws_m15').append($input);
			}
		}
		isuploadImg();
	}

	//判断是否上传图片
	function isuploadImg() {
		var img = $("input[name='M4']").val();
		if (img == null || img == "") {
			uploadImgFlag = false;
		} else {
			uploadImgFlag = true;
		}
	}
</script>
</body>
</html>