<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
	<!-- 引入蜂鸟地图JS -->
	<link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
	<link rel="stylesheet" href="${ctxStatic}/model/css/rydw/style.css">
	<!-- 引入自定义 css js -->
	<link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
	<script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>
	<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
	<script src="${ctxStatic}/fengmap/js/createBubble.js"></script>
	<script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>

</head>
<body>

     <form id="inputForm" action="${ctx}/bis/spsbxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${spsbxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${spsbxx.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr>
					<td class="width-20 active"><label class="pull-right">重大危险源名称：</label></td>
					<td class="width-30"><input name="hazardcode" type="text" class="easyui-combobox" value="${spsbxx.hazardcode }" style="width: 100%;height: 30px;"  data-options="required:'true',editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/bis/hazard/hazardCodeJson'"/></td>

					<td class="width-20 active"><label class="pull-right">生产单元区域标识：</label></td>
					<td class="width-30"><input name="prodcellid" type="text" class="easyui-textbox" value="${spsbxx.prodcellid }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,32]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头编码：</label></td>
					<td class="width-30"><input name="M1" type="text" class="easyui-textbox" value="${spsbxx.m1 }" style="width: 100%;height: 30px;"  data-options="required:'true',validType:'length[0,50]'" /></td>

					<td class="width-20 active"><label class="pull-right">摄像头类型：</label></td>
					<td class="width-30"><input name="M2" type="text" class="easyui-textbox" value="${spsbxx.m2 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-30"><input class="easyui-textbox" name="M3" value="${spsbxx.m3 }" style="width: 100%;height: 30px;"  data-options="required:'true',validType:'length[0,25]',prompt:'例如：shebei01'"/></td>

					<td class="width-20 active"><label class="pull-right">设备编码：</label></td>
					<td class="width-30"><input name="M4" type="text" class="easyui-textbox" value="${spsbxx.m4 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">RTSP视频流：</label></td>
					<td class="width-30" colspan="3"><input name="rtsp" type="text" class="easyui-textbox" value="${spsbxx.rtsp }" style="width: 100%;height: 30px;"  data-options="required:'true',validType:'length[0,100]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">接口请求地址：</label></td>
					<td class="width-30" colspan="3"><input name="apiaddress" type="text" class="easyui-textbox" value="${spsbxx.apiaddress }" style="width: 100%;height: 30px;" data-options="required:'true',validType:'length[0,50]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">视频播放地址：</label></td>
					<td class="width-30" colspan="3"><input name="playaddress" type="text" class="easyui-textbox" value="${spsbxx.playaddress }" style="width: 100%;height: 30px;" data-options="required:'true',validType:'length[0,50]' " /></td>
				</tr>

				<c:if test="${action=='update' }">
					<tr>
						<td class="width-15 active"><label class="pull-right">url：</label></td>
						<td class="width-30" colspan="3"><input name="url" type="text" class="easyui-textbox" value="${spsbxx.url }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,100]'"/></td>
					</tr>
				</c:if>

				<tr>
					<td class="width-20 active"><label class="pull-right">是否为重大危险源区域：</label></td>
					<td class="width-30"><input name="M21" type="text" class="easyui-combobox" value="${spsbxx.m21 }" style="width: 100%;height: 30px;"  data-options="required:'true',editable:false,panelHeight:'auto',data:[{value:'0',text:'否'},{value:'1',text:'是'}]" /></td>

					<td class="width-20 active"><label class="pull-right">设备IP：</label></td>
					<td class="width-30"><input name="M5" type="text" class="easyui-textbox" value="${spsbxx.m5 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头名称 ：</label></td>
					<td class="width-30" colspan="3"><input name="M20" type="text" value="${spsbxx.m20}" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]',prompt:'例如：中控室' "></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">外观类型：</label></td>
					<td class="width-30"><input name="M6" type="text" class="easyui-textbox" value="${spsbxx.m6 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,100]' " /></td>

					<td class="width-20 active"><label class="pull-right">布控区域：</label></td>
					<td class="width-30"><input name="M9" type="text" class="easyui-textbox" value="${spsbxx.m9 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经度：</label></td>
					<td class="width-30"><input name="M7" type="text" class="easyui-numberbox" value="${spsbxx.m7 }" style="width: 100%;height: 30px;"  data-options="min:0,precision:6" /></td>

					<td class="width-20 active"><label class="pull-right">纬度：</label></td>
					<td class="width-30"><input name="M8" type="text" class="easyui-numberbox" value="${spsbxx.m8 }" style="width: 100%;height: 30px;"  data-options="min:0,precision:6" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">地图位置：</label></td>
					<td colspan="3">
					<span>
						<label>X</label>
						<input id="M22" name="M22" value="${spsbxx.m22}" class="easyui-textbox" readonly="readonly" data-options="" style="width:120px;height:30px;"/>
						<label>Y</label>
						<input id="M23" name="M23" value="${spsbxx.m23}" class="easyui-textbox" readonly="readonly" data-options="" style="width:120px;height:30px;"/>
						<label>Z</label>
						<input id="M24" name="M24" value="${spsbxx.m24}" class="easyui-textbox" readonly="readonly" data-options="" style="width:120px;height:30px;"/>
						<a class="btn btn-primary" onclick="showMapXY()" style="width:80px;">地图选点</a>
					</span></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">通道号：</label></td>
					<td class="width-30"><input name="M10" type="text" class="easyui-textbox" value="${spsbxx.m10 }" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]' " /></td>

					<td class="width-20 active"><label class="pull-right">端口号：</label></td>
					<td class="width-30"><input name="M11" type="text" class="easyui-textbox" value="${spsbxx.m11 }" style="width: 100%;height: 30px;"  data-options=" validType:'length[0,50]' " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">摄像头顺序号：</label></td>
					<td class="width-30"><input name="M12" type="text" class="easyui-textbox" value="${spsbxx.m12 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>

					<td class="width-20 active"><label class="pull-right">登录用户：</label></td>
					<td class="width-30"><input name="M13" type="text" class="easyui-textbox" value="${spsbxx.m13 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">登录密码：</label></td>
					<td class="width-30"><input name="M14" type="text" class="easyui-textbox" value="${spsbxx.m14 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>

					<td class="width-20 active"><label class="pull-right">客户端接入服务器ID：</label></td>
					<td class="width-30"><input name="M15" type="text" class="easyui-textbox" value="${spsbxx.m15 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">企业负责人：</label></td>
					<td class="width-30"><input name="M16" type="text" class="easyui-textbox" value="${spsbxx.m16 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>

					<td class="width-20 active"><label class="pull-right">负责人联系电话：</label></td>
					<td class="width-30"><input name="M17" type="text" class="easyui-textbox" value="${spsbxx.m17 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">生产厂家：</label></td>
					<td class="width-30"><input name="M18" type="text" class="easyui-textbox" value="${spsbxx.m18 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]'" /></td>

					<td class="width-20 active"><label class="pull-right">主要技术参数：</label></td>
					<td class="width-30"><input name="M19" type="text" class="easyui-textbox" value="${spsbxx.m19 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,25]'" /></td>
				</tr>

				<c:if test="${not empty spsbxx.ID}">
					<input type="hidden" name="ID" value="${spsbxx.ID}"/>
					<input type="hidden" name="ID1" value="${spsbxx.ID1}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${spsbxx.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${spsbxx.s3}" />
					<input type="hidden" name="videoid" value="${spsbxx.videoid}" />
				</c:if>
				</tbody>
			</table>
       </form>

	 <div id="enterprise_dlg" style="width:100%;height:100%; text-align:center;display: none" >
		 <div id="fengMap" style="width: 100%;height: 100%"></div>
	 </div>

<script type="text/javascript">

	var usertype=${usertype};
	//设置选点参数
	var locx = '${spsbxx.m22}';
	var locy = '${spsbxx.m23}';
	var locz = '${spsbxx.m24}';
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
		appName : '苏州兴业',
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

	var groupControl;

	//2/3D切换配置
	var toolControl2 = new fengmap.toolControl(map,{
		imgURL : '${ctxStatic}/fengmap/image/',
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
			area: ['900px', '700px'],
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
					addMarkers(locx,locy,locz);
				});
			},
			yes: function(index, layero){
				$("#M22").textbox("setValue", locx);//x坐标
				$("#M23").textbox("setValue", locy);//y坐标
				$("#M24").textbox("setValue", locz);//z坐标
				layer.close(index);
			},
			cancel: function(index){
			}
		});
	}

</script>
</body>
</html>