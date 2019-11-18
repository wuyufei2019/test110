<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>

<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/common/initmap.js?v=1.1"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/model/js/qyxx/qyjbxx/mapindex.js?v=2.0"></script>
<script type="text/javascript"
	src="${ctxStatic}/model/js/qyxx/qyjbxx/datagrid.js?v=1.2"></script>
<script type="text/javascript"
	src="${ctxStatic}/echarts/theme/vintage.js"></script>
<link rel="stylesheet"
	href="http://api.map.baidu.com/library/SearchInfoWindow/1.4/src/SearchInfoWindow_min.css" />
<link href="${ctxStatic}/model/css/home/styles.css" rel="stylesheet" />
<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
}

.btn-group label {
	margin-right: 5px !important;
}

#dituContent {
	height: 100%;
	width: 100%;
}

.BMap_cpyCtrl {
	display: none;
}

.anchorBL {
	display: none;
}

#dituContent label {
	max-width: none;
}

.boxx2 .list-inline {
	margin-top: 10px;
	float: left
}

.boxx2 .list-inline li {
	width: 47%;
	height: 40px;
	line-height: 40px;
	margin-left: 9px;
	margin-bottom: 10px;
	padding-left: 0px;
	overflow: hidden;
	white-space: nowrap;
	margin-bottom: 10px;
}

.boxx2 .list-inline li a {
	text-decoration: none;
	height: 45px;
	line-height: 45px;
	padding-left: 5px;
	display: inline-block;
}

.boxx2 .list-inline li i {
	display: inline-block;
	width: 44px;
	height: 44px;
	font-size: 24px;
	padding-left: 12px;
	line-height: 45px;
	color: #fff;
	margin: auto 0;
}

.boxx2 .list-inline li .iblue {
	background-color: #69B6EA;
	float: left;
}

.boxx2 .list-inline li .igreen {
	background-color: #91D33E;
	float: left;
}

.boxx2 .list-inline .blue {
	background-color: #D2E8F6;
}

.boxx2 .list-inline .green {
	background-color: #DFEECD;
}

.boxx2 span {
	font: 14px/16px "微软雅黑";
}

.container-fluid {
	height: 100%;
	padding-right: 5px;
}

#titletext,#titletext2 {
	padding: 4px 8px 5px;
	margin: 4px;
}

#titletext:hover,#titletext2:hover {
	cursor: pointer;
}

.chos {
	background: #f4f4f4;
	border-radius: 5px 5px 0px 0;
	border-left: 1px solid;
	border-color: #d6d6d6;
	border-top: 1px solid;
	border-right: 1px solid;
	border-color: #d6d6d6;
}

.bs-example {
	padding: 5px;
}

#cate_ {
	width: 100%;
	height: calc(100% - 28px);
	background: #f4f4f4;
	padding: 10px 0px 5px;
}

#cate_ .tit {
	padding: 5px;
	width: 100%;
	color: white;
	background: -webkit-linear-gradient(left, #3b8bd2, #cde3f5);
	/* Safari 5.1 - 6.0 */
	background: -o-linear-gradient(right, #3b8bd2, #cde3f5);
	/* Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(right, #3b8bd2, #cde3f5);
	/* Firefox 3.6 - 15 */
	background: linear-gradient(to #3b8bd2, #3b8bd2, #cde3f5); /* 标准的语法 */
}

#cate_ .tit.tit_3 {
	padding: 5px;
	width: 100%;
	color: white;
	background: -webkit-linear-gradient(left, #ffe500, #f7fcff);
	/* Safari 5.1 - 6.0 */
	background: -o-linear-gradient(right, #ffe500, #f7fcff);
	/* Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(right, #ffe500, #f7fcff);
	/* Firefox 3.6 - 15 */
	background: linear-gradient(to #3b8bd2, #ffe500, #f7fcff); /* 标准的语法 */
}

#cate_ .tit.tit_2 {
	padding: 5px;
	width: 100%;
	color: white;
	background: -webkit-linear-gradient(left, #ff8100, #ffffff);
	/* Safari 5.1 - 6.0 */
	background: -o-linear-gradient(right, #ff8100, #ffffff);
	/* Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(right, #ff8100, #ffffff);
	/* Firefox 3.6 - 15 */
	background: linear-gradient(to #3b8bd2, #ff8100, #ffffff); /* 标准的语法 */
}

#cate_ .tit.tit_1 {
	padding: 5px;
	width: 100%;
	color: white;
	background: -webkit-linear-gradient(left, #ffb3b3, #f6fbff);
	/* Safari 5.1 - 6.0 */
	background: -o-linear-gradient(right, #ffb3b3, #f6fbff);
	/* Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(right, #ffb3b3, #f6fbff);
	/* Firefox 3.6 - 15 */
	background: linear-gradient(to #3b8bd2, #ffb3b3, #f6fbff); /* 标准的语法 */
}

#cate_ .ctt {
	width: 100%;
	display: flex;
	flex-wrap: wrap;
	padding: 0px 5px 5px;
	background: white;
}

#cate_ .ctt .ctt_item {
	margin: 8px 6px 2px;
	background: white;
	padding: 7px 15px;
	box-shadow: 0 0 6px 0 #e0e0e0;
}

#cate_ .tit2 {
	padding: 5px;
	color: #666;
	font-size: 12px;
	width: 100%;
	background: white;
	border-bottom: 0.1px solid #e8e8e8;
}

#cate_ .tit2 span {
	font-size: 12px;
	padding: 0 5px 4px;
}

#cate_ span.t_chos {
	color: #2f7ec3;
	border-bottom: 2px solid #338ddc;
	font-size: 13px;
}

.ctt_item_img {
	text-align: center;
	padding: 1px;
	font-size: 24px;
}

.lab_btn {
	margin: 10px;
	position: absolute;
	z-index: 999;
	width: 240px;
}

.lab_btn2 {
	margin: 10px;
	position: absolute;
	z-index: 999;
	width: 100%;
}

.lab_btn3 {
	margin: 10px;
	position: absolute;
	top: calc(50% - 100px);
	z-index: 999;
	width: 110px;
}

.lab_btn3 .ctt_item_img {
	text-align: center;
	padding: 1px;
	font-size: 18px;
	margin-right: 5px;
}

.btngroup .ctt_item {
	padding: 0 5px 5px;
	margin: 0 5px 5px 0;
}

#cate_ .ctt.hid {
	display: none;
}

.btngroup .ctt_item:hover,.tit2 span:hover {
	cursor: pointer;
}

.btngroup .ctt_item.b_chos {
	background: rgba(45, 45, 45, 0.74);
}

.btngroup .ctt_item {
	background: rgba(232, 232, 232, 0.82);
	box-shadow: 0 0 8px 0 #424242;
	margin-right: 10px;
}

/* 风险点列表 */
#fxdlb_ctt {
	margin: 0px 0;
	width: 100%;
}

#fxdlb_ctt .list_h,#fxdlb_ctt .list_b,#fxdlb_ctt .list_b>div.list_li {
	display: flex;
}

#fxdlb_ctt .list_h>div {
	text-align: center;
	padding: 6px;
}

#fxdlb_ctt .list_b>div.list_li>div {
	text-align: center;
	padding: 8px 6px;
}

#fxdlb_ctt .list_h {
	background: #2d75ad;
}

#fxdlb_ctt .list_b>div.list_li:hover,#cqmnt_ctt .camera_l:hover,.userInfo .xfq:hover
	{
	cursor: pointer;
}

.w25 {
	width: 25%
}

.w20 {
	width: 20%
}

.w15 {
	width: 15%
}

#fxdlb_ctt .list_b {
	flex-direction: column;
	overflow-y: scroll;
	height: 160px
}

#fxdlb_ctt .list_b>div.list_li:nth-child(odd) {
	width: 100%;
	border-bottom: .1px solid #dedede;
	background: #f7f7f7;
	color: #444;
	flex-shrink: 0;
}

#fxdlb_ctt .list_b>div.list_li:nth-child(even) {
	width: 100%;
	border-bottom: .1px solid #dedede;
	background: #ffffff;
	color: #444;
	flex-shrink: 0;
}

.line {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}

#fxdlb_ctt .list_b>div.list_li>div>div {
	margin: 0px 13% 0 22%;
	color: #fff;
}

#fxdlb_ctt .list_b>div.list_li>div>div.clrd {
	color: #333;
}

.bgr {
	background: red
}

.bgo {
	background: orange
}

.bgy {
	background: yellow
}

.bggr {
	background: #45a549;
}

.bgb {
	background: #2196f3
}

.lab {
	color: #333;
}
</style>
</head>
<body>
	<div class="container-fluid bggrey" style="overflow: scroll;">
		<div id="mapcontent" class="col-lg-6 col-md-6 col-sm-12 col-xs-12 bs-example mgt10 "
			style="margin-right:5px;margin-left: -5px;box-shadow: 1px 1px 5px #ddd;height: calc(100% - 10px);">
			<div class="lab_btn">
			<!-- 
				<span style="margin:5px auto;"> <input id="keyword"
					type="text" class="easyui-combobox"
					style="height: 25px;width:120px"
					data-options="valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' " />
					<button type="button" class="btn btn-info btn-xs"
						style="margin: 0px 0px 0px 0px;" onclick="searchAll('keyword')">搜索
					</button>
					<button type="button" class="btn btn-info btn-xs"
						style="margin: 0px 4px 0px 0px;" onclick="resetAll()">全部数据</button>
				</span> -->
			</div>
			<div class="lab_btn2 col-lg-8 col-md-12">
				<div class="btngroup"
					style="width:400px;margin-left: calc(50% - 200px);display:flex;flex-wrap:wrap;">
					<div id="fxxx" class="ctt_item b_chos"
						style="color: #e02323;border:1px solid red;">
						<div class="ctt_item_img">
							<i class="fa fa-area-chart"></i>
						</div>
						<div class="ctt_item_t">风险点分布</div>
					</div>
					<div id="zdzd" class="ctt_item"
						style="color: #fff900;border:1px solid #fff900;">
						<div class="ctt_item_img">
							<i class="fa fa-area-chart"></i>
						</div>
						<div class="ctt_item_t">风险四色</div>
					</div>
					<div id="fxyt" class="ctt_item"
						style="color: #ce4ee4;border:1px solid #ce4ee4;">
						<div class="ctt_item_img">
							<i class="fa fa-area-chart"></i>
						</div>
						<div class="ctt_item_t">危险作业</div>
					</div>
					<div id="qyfb" class="ctt_item"
						style="color: #2196F3;border:1px solid #2196F3; ">
						<div class="ctt_item_img">
							<i class="fa fa-area-chart"></i>
						</div>
						<div class="ctt_item_t">人员位置</div>
					</div>
					<!-- <button type="button" class="btn btn-primary btn-danger btn-xs" id="qyfb">企业分布</button>
                 <button type="button" class="btn btn-primary btn-xs" id="fxxx">风险点信息</button>
                 <button type="button" class="btn btn-primary btn-xs" id="cgxx">储罐信息</button>
                 <button type="button" class="btn btn-primary btn-xs" id="spjk">视频监控</button>
                 <button type="button" class="btn btn-primary btn-xs" id="fxyt">固有风险云图</button>
                 <button type="button" class="btn btn-primary btn-xs" id="zdzd">两重点一重大分布图</button>
                 <button type="button" class="btn btn-primary btn-xs" id="wgfx">网格风险分布图</button>
                 <button type="button" class="btn btn-primary btn-xs" id="yjdw">应急队伍</button>
                 <button type="button" class="btn btn-primary btn-xs" id="yjzb">应急装备</button>
                 <button type="button" class="btn btn-primary btn-xs" id="yjwz">应急物资</button>
                 <button type="button" class="btn btn-primary btn-xs" id="bncs">避难场所</button>
                 <button type="button" class="btn btn-primary btn-xs" id="ryfb">人员分布</button>
                 <button type="button" class="btn btn-primary btn-xs" id="wxzy">每日危险作业分布图</button>
                 <button type="button" class="btn btn-primary btn-xs" id="cbs">每日承包商分布图</button>
                 <button type="button" class="btn btn-primary btn-xs" id="whpcl">危化品车辆</button> -->
				</div>
			</div>
			<div class="lab_btn3">
				<!--
             <div class="btngroup">
                 <div id="spjk" class="ctt_item" style="display:flex;align-items:center;color: #e02323;border:1px solid red;">
  					<div class="ctt_item_img"><i class="fa fa-area-chart"></i></div>
  					<div class="ctt_item_t">视频监控</div>
  				</div>
  				<div id="cgxx" class="ctt_item" style="display:flex;align-items:center;color: #ce4ee4;border:1px solid #ce4ee4;">
  					<div class="ctt_item_img"><i class="fa fa-area-chart"></i></div>
  					<div class="ctt_item_t">储罐信息</div>
  				</div>
  				<div id="qtnd" class="ctt_item" style="display:flex;align-items:center;color: #2196F3;border:1px solid #2196F3;">
  					<div class="ctt_item_img"><i class="fa fa-area-chart"></i></div>
  					<div class="ctt_item_t">气体浓度</div>
  				</div>
             </div>-->
			</div>
			<%-- <div id="dituContent"></div>--%>
			<div class="bs-example" id="dituContent"></div>
		</div>
		<div class="boxx2 col-lg-6 col-md-6 col-sm-12 col-xs-12 bs-example mgt10"
			style="height: calc(100% - 10px);float: left;box-shadow:1px 1px 5px #ddd;overflow-y:scroll;padding: 5px;">
			<div id="" style="padding:5px;height:auto; background: white;">
				<a><i id="rightcont" class="fa fa-angle-double-right fa-2x"
					style="float: left;margin-top: -3px"></i></a>&nbsp; <span
					id="titletext" class="chos">数据统计</span>
				<!--<span id="titletext2">企业列表</span>  -->
			</div>

			<!-- 分类统计卡 -->
			<div id="cate_" class="">
				<div id="cate_1_1">
					<div id="cate_1_ecs" class="col-lg-5 col-md-5 col-sm-5">
						<div class="tit tit_1">企业风险</div>
						<div class="ctt">
							<div id="container" style="min-height: 150px;width:100%"></div>
						</div>
					</div>


					<div id="cate_1_r" class="col-lg-7 col-md-7 col-sm-7">
						<div class="tit b_ tit_1">风险点统计</div>
						<div class="ctt" style="min-height: 154px;">

							<div id="chart2" style="min-height: 150px;width:100%"></div>
						</div>
					</div>
				</div>


				<div id="cate_1_2" class="col-lg-12 col-md-12"
					style="margin-top:10px">
					<div class="tit tit_2">行业风险</div>

					<div class="tit2">
						<span class="t_chos">红色风险点</span>
						<span>橙色风险点</span>
						<span>黄色风险点</span>
						<span>蓝色风险点</span>
					</div>
					<div class="ctt" style="padding-top:5px;">
						<div class="ctt c_1">
							<div id="fxdlb_ctt" style="border: 1px solid #ffe0de;">
								<div class="list_h f2-5" style="background: #ffe0de;">
									<div class="w25">风险点名称</div>
									<div class="w20">风险类别</div>
									<div class="w20">行业</div>
									<div class="w15">现场照片</div>
									<div class="w20">状态</div>
								</div>
								<div class="list_b f3" style="" id="red_fxd"></div>
							</div>
						</div>
						<div class="ctt c_2 hid">
							<div id="fxdlb_ctt" style="border: 1px solid #ffd3a6;">
								<div class="list_h f2-5" style="background: #ffd3a6;">
									<div class="w25">风险点名称</div>
									<div class="w20">风险类别</div>
									<div class="w20">行业</div>
									<div class="w15">现场照片</div>
									<div class="w20">状态</div>
								</div>
								<div class="list_b f3" style="" id="orange_fxd"></div>
							</div>
						</div>
						<div class="ctt c_3 hid">
							<div id="fxdlb_ctt" style="border: 1px solid #ffefbe;">
								<div class="list_h f2-5" style="background: #ffefbe;">
									<div class="w25">风险点名称</div>
									<div class="w20">风险类别</div>
									<div class="w20">行业</div>
									<div class="w15">现场照片</div>
									<div class="w20">状态</div>
								</div>
								<div class="list_b f3" style="" id="yellow_fxd"></div>
							</div>
						</div>
						<div class="ctt c_4 hid">
							<div id="fxdlb_ctt" style="border: 1px solid #daeeff;">
								<div class="list_h f2-5" style="background: #daeeff;">
									<div class="w25">风险点名称</div>
									<div class="w20">风险类别</div>
									<div class="w20">行业</div>
									<div class="w15">现场照片</div>
									<div class="w20">状态</div>
								</div>
								<div class="list_b f3" style="" id="blue_fxd"></div>
							</div>
						</div>
					</div>
				</div>


				<div id="cate_1_4" class="col-lg-12 col-md-12"
					style="margin-top:10px">
					<div class="tit">两单三卡管理</div>
					<div class="ctt">
						<div class="ctt_item" style="color: #ff5a5a;background: #ffb3b3;cursor: pointer;" onclick="showbsqd()">
							<div class="ctt_item_img">
								<i class="fa fa-bar-chart-o"></i>
							</div>
							<div class="ctt_item_t" style="">风险点辨识清单</div>
						</div>
						<div class="ctt_item" style="color: #125a9a;background: #c6dff3;cursor: pointer;" onclick="showfjgk()">
							<div class="ctt_item_img">
								<i class="fa fa-pie-chart"></i>
							</div>
							<div class="ctt_item_t">风险分级管控清单</div>
						</div>
						<div class="ctt_item" style="color: #ff7800;background: #ffd89e;cursor: pointer;" onclick="showgzk()">
							<div class="ctt_item_img">
								<i class="fa fa-line-chart"></i>
							</div>
							<div class="ctt_item_t">风险告知卡</div>
						</div>
						<div class="ctt_item" style="color: #439446;background: #cbeaa7;cursor: pointer;" onclick="showczk()">
							<div class="ctt_item_img">
								<i class="fa fa-area-chart"></i>
							</div>
							<div class="ctt_item_t">应急处置卡</div>
						</div>
						<div class="ctt_item"
							style="color: #ca4ce0;background: #e4b1ec;padding: 5px 28px;cursor: pointer;" onclick="showcnk()">
							<div class="ctt_item_img">
								<i class="fa fa-dedent"></i>
							</div>
							<div class="ctt_item_t">安全承诺卡</div>
						</div>
					</div>
				</div>
			</div>

			<div id="cate_2"
				style="height:calc(100% - 28px);width:100%;display:none">
				<table id="mapdatagrid" style="height:100%;width:100%;"></table>
			</div>
		</div>
	</div>

	<div id="select_type" style="display:none;height:350px">
		<table id="areadata"></table>
	</div>
	<div id="zdzdtab" class="easyui-tabs" data-options="fit:true"
		style="width:850px;height:auto;text-align: center;display:none;">
	</div>
	<script type="text/javascript">
		/*****************************  企业风险  **********************************/
		var a;
		$(function(){
			$.ajax({
				type: 'POST',
				async: false,
				url: ctx + '/fxgk/fxxx/getFxdjz',
				success: function (data) {
					loadEcharts(data);
				}
			})
		})

		function loadEcharts(data) {
			a = echarts.init(document.getElementById("container"), "vintage");
			a.setOption({
				backgroundColor: '#ffffff00',
				tooltip: {
					//formatter: "{a} <br/>{b} : {c}个"
				},
				toolbox: {
					show: !0,
					feature: {}
				},
				series: [{
					name: "",
					type: "gauge",
					radius: "90%",
					center: ["50%", "50%"],
					min: 0,
					max: 1000,
					precision: 0,
					splitNumber: 4,
					axisLine: {
						show: !0,
						lineStyle: {
							color: [[.001, "#439cea"], [.03, "yellow"],
								[.87, "orange"], [1, "red"]],
							width: 13
						}
					},
					axisTick: {
						show: !0,
						splitNumber: 4,
						length: 8,
						lineStyle: {
							color: "#eee",
							width: 1,
							type: "solid"
						}
					},
					axisLabel: {
						show: !0,
						formatter: function (t) {
							switch (t + "") {
								case "20":
									return "";
								case "40":
									return "";
								case "80":
									return "";
								case "100":
									return "";
								default:
									return ""
							}
						},
						textStyle: {
							color: "#333"
						}
					},
					splitLine: {
						show: !0,
						length: 20,
						lineStyle: {
							color: "#eee",
							width: 2,
							type: "solid"
						}
					},
					pointer: {
						length: "70%",
						width: 6,
						color: "auto"
					},
					title: {
						show: !0,
						offsetCenter: ["0%", 40],
						textStyle: {
							color: "auto",
							fontSize: 15
						}
					},
					detail: {
						show: !0,
						backgroundColor: "rgba(0,0,0,0)",
						borderWidth: 0,
						borderColor: "#ccc",
						width: 100,
						height: 40,
						offsetCenter: ["0%", 40],
						formatter: "{value}",
						textStyle: {
							color: "auto",
							fontSize: 1
						}
					},
					data: [{
						value: data.data,
						name: data.color
					}]
				}]
			})
		}

		/*****************************  地图展示  **********************************/
		var ctx = '${ctx}';
		var mappoint = '${mappoint}';
		var MAP_TYPE = "qyfb";//类型全局变量
		var BIS_R = []; //红
		var BIS_O = [];//橙
		var BIS_Y = [];//黄
		var BIS_B = [];//蓝
		initMap("dituContent",'${bis.m16}','${bis.m17}',20);
		addZoomListener();

		if ('${disablescroll}' == 'yes')//首页和一张图的禁用滚轮设置
			map.disableScrollWheelZoom();
		var Global_Search_Data = [];//后台返回map点数据，搜索用

		$(function() {
			$("#container").height($("#cate_1_r").height() - 32);
			a.resize();

			// 切换列表卡
			$("#titletext2").click(function() {
				$(this).addClass("chos").siblings("span").removeClass("chos");
				$("#cate_2").css("display", "block");
				$("#cate_").css("display", "none");

				getQyfbJson();
				loadMapData_qyfb();
			})
			$("#titletext").click(function() {
				$(this).addClass("chos").siblings("span").removeClass("chos");
				$("#cate_2").css("display", "none");
				$("#cate_").css("display", "block");
			})

			var primarywidth = $("#mapcontent").width();
			$(".btngroup .ctt_item").click(function() {
				$(".btngroup .ctt_item").removeClass("b_chos");
				$(this).addClass("b_chos");
			});
			$("#rightcont").click(function() {
				$(".boxx2").fadeToggle();
				$("#mapcontent").width($(".bggrey").width() - 30);
				$("#leftcont").toggle();
			});
			$("#leftcont").click(function() {
				$("#mapcontent").width(primarywidth);
				$(".boxx2").toggle();
				$("#leftcont").toggle();
			});

			$(".tit2 span").click(
					function() {
						$(this).addClass("t_chos").siblings().removeClass(
								"t_chos");
						var ind = $(this).index() + 1;

						$(".ctt.c_" + ind).removeClass("hid").siblings()
								.addClass("hid");
					})

			function getData(url) {
				var d1;
				$.ajax({
					type : "POST",
					//url : ctx + "/bis/qyjbxx/maplist/",
					url : ctx + url,
					async : false,
					dataType : 'json',
					success : function(data) {
						d1 = data;
					}
				});
				return d1;
			}

			$("#fxxx").click(function() {
				MAP_TYPE = 'fxxx';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						$("#titletext2").text("风险点信息");
						getQyfbJson();
						loadMapData_qyfb();
					}
				});
				$.jBox.closeTip();
			});
			$("#cgxx").click(function() {
				MAP_TYPE = 'cgxx';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = getData("/bis/cgxx/cgmapjson");
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							$("#titletext2").text("储罐信息");
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_cgxx();
						}
					}
				});
				$.jBox.closeTip();
			});
			$("#spjk").click(function() {
				MAP_TYPE = 'spjk';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = getData("/zxjkyj/spjk/qymaplist");
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							//markerArr = eval(Global_Search_Data);
							$("#titletext2").text("视频监控");
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_spjk();
						}
					}
				});
				$.jBox.closeTip();
			});

			$("#qyfb").click(function() {
				MAP_TYPE = 'qyfb';
				addZoomListener();
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						$("#titletext2").text("企业分布");
						getQyfbJson();
						loadMapData_qyfb();
					}
				});
				$.jBox.closeTip();
			});

			$("#fxyt").click(function() {
				MAP_TYPE = 'fxyt';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						map.clearOverlays();
						createPolygon();
						loadHeatMap(getData("/fxgk/fxfb/showfxyt"), 900);
					}
				});
				$.jBox.closeTip();
			});
			$("#zdzd")
					.click(
							function() {
								MAP_TYPE = 'zdzd';
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														Global_Search_Data = getData("/fxgk/fxfb/showzdzd");
														setSearchData(Global_Search_Data);
														if (Global_Search_Data) {
															$("#titletext2")
																	.html(
																			"两重点一重大信息<!--<i class='fa fa-circle' style='color:#ffd075;'>重点监管</i><i class='fa fa-circle' style='color:#e57e20;'>高危工艺</i><i class='fa fa-circle' style='color:#ed2d2d;'>重大危险源</i>-->");
															reloadMap(Global_Search_Data);
															loadMapData_zdzd();
														}
													}
												});
								$.jBox.closeTip();
							});
			$("#wgfx")
					.click(
							function() {
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														MAP_TYPE = "wgfx";
														map.clearOverlays();
														var mapData = getData("/fxgk/fxfb/barrocolor");
														var data = mapData.list;
														var colorlist = [];
														var len = data.length;
														var max = mapData.max;
														var min = mapData.min;
														var dif = max - min;
														var dif2 = dif / 2;
														var everyd = 255 / dif2;
														var startRGB = colorRgb('#00ff00');//绿色,先变前两位00
														startR = startRGB[0];
														startG = startRGB[1];
														startB = startRGB[2];
														var endRGB = colorRgb('#ff0000');
														endR = endRGB[0];
														endG = endRGB[1];
														endB = endRGB[2];
														colorlist
																.push('#00ff00');
														for (var i = 1; i < len - 1; i++) {
															var count = data[i].COUNT;
															if ((count - min) <= dif2) {
																startR = parseInt(everyd
																		* (count - min));
																colorlist
																		.push("rgb("
																				+ startR
																				+ ","
																				+ startG
																				+ ","
																				+ startB
																				+ ")");
															} else {
																endG = parseInt(everyd
																		* (max - count));
																colorlist
																		.push("rgb("
																				+ endR
																				+ ","
																				+ endG
																				+ ","
																				+ endB
																				+ ")");
															}
														}
														colorlist
																.push('#ff0000');
														createPolygons(data,
																colorlist);
														loadMapData_wgfx(data,
																colorlist);
														$("#titletext2").text(
																"网格风险分布");
													}
												});
								$.jBox.closeTip();
							});
			$("#yjdw")
					.click(
							function() {
								MAP_TYPE = 'yjdw';
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														Global_Search_Data = getData("/erm/yjdw/maplist/").data;
														setSearchData(Global_Search_Data);
														if (Global_Search_Data != undefined) {
															reloadMap(Global_Search_Data);// 创建和初始化地图
															loadMapData_yj(Global_Search_Data);
															$("#titletext2")
																	.text(
																			"应急队伍");
														}
													}
												});
								$.jBox.closeTip();
							});
			$("#yjzb")
					.click(
							function() {
								MAP_TYPE = 'yjzb';
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														Global_Search_Data = getData("/erm/yjzb/maplist/1").data;
														setSearchData(Global_Search_Data);
														if (Global_Search_Data != undefined) {
															reloadMap(Global_Search_Data);// 创建和初始化地图
															loadMapData_yj(Global_Search_Data);
															$("#titletext2")
																	.text(
																			"应急装备");
														}
													}
												});
								$.jBox.closeTip();
							});
			$("#yjwz")
					.click(
							function() {
								MAP_TYPE = 'yjwz';
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														Global_Search_Data = getData("/erm/yjwz/maplist/1").data;
														setSearchData(Global_Search_Data);
														if (Global_Search_Data != undefined) {
															reloadMap(Global_Search_Data);// 创建和初始化地图
															loadMapData_yj(Global_Search_Data);
															$("#titletext2")
																	.text(
																			"应急物资");
														}
													}
												});
								$.jBox.closeTip();
							});
			$("#bncs").click(function() {
				MAP_TYPE = 'bncs';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = getData("/erm/bncs/maplist").data;
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_yj(Global_Search_Data);
							$("#titletext2").text("避难场所");
						}
					}
				});
				$.jBox.closeTip();
			});
			$("#ryfb").click(function() {
				MAP_TYPE = 'ryfb';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = [ {
							title : "许宏斌",
							pointx : "120.940578",
							pointy : "31.901546",
							address : "南通瑞翔新材料有限公司",
							contact : "13951319311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "徐洪芬",
							pointx : "120.962555",
							pointy : "31.855715",
							address : "南通天和树脂有限公司",
							contact : "13951312311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "徐章兴",
							pointx : "120.969061",
							pointy : "31.847652",
							address : "欧区爱铸造材料（中国）有限公司",
							contact : "13951322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "侯杰",
							pointx : "120.956532",
							pointy : "31.833934",
							address : "中化南通石化储运有限公司",
							contact : "15231322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "朱晓峰",
							pointx : "120.960352",
							pointy : "31.860037",
							address : "江苏宝灵化工股份有限公司",
							contact : "13941322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "赵锋",
							pointx : "120.95324",
							pointy : "31.839281",
							address : "南通嘉民港储有限公司",
							contact : "16951322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "高建荣",
							pointx : "120.970786",
							pointy : "31.865247",
							address : "朗盛高新材料(南通)有限公司",
							contact : "14551322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "夏海平",
							pointx : "120.959156",
							pointy : "31.863819",
							address : "台橡宇部(南通)化学工业有限公司",
							contact : "17651322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "熊建增",
							pointx : "120.952649",
							pointy : "31.85606",
							address : "南通千红石化港储有限公司",
							contact : "12451322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "范裕兴",
							pointx : "120.964698",
							pointy : "31.834141",
							address : "江苏王子制纸有限公司",
							contact : "19051322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "孙振",
							pointx : "120.933294",
							pointy : "31.91191",
							address : "凡特鲁斯特种化学品（南通）有限公司",
							contact : "14551322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "顾宏伟",
							pointx : "120.962095",
							pointy : "31.865825",
							address : "台橡（南通）实业有限公司",
							contact : "15451322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "陶军国",
							pointx : "120.971643",
							pointy : "31.86747",
							address : "迈图高新材料（南通）有限公司",
							contact : "13955522311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "丁钧",
							pointx : "120.971152",
							pointy : "31.845773",
							address : "日立化成工业（南通）化工有限公司",
							contact : "17751322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "吴增飞",
							pointx : "120.974766",
							pointy : "31.839617",
							address : "万洲石化（江苏）有限公司",
							contact : "13951323331",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "刘灿明",
							pointx : "121.031085",
							pointy : "31.823144",
							address : "上海振华重工集团（南通）传动机械有限公司",
							contact : "16551311311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "徐惠明",
							pointx : "120.947771",
							pointy : "31.864263",
							address : "惠生(南通)重工有限公司",
							contact : "17833322311",
							icon : "/static/model/images/map/person.png"
						}, {
							title : "沈骐",
							pointx : "120.936052",
							pointy : "31.90183",
							address : "南通中集安瑞科食品装备有限公司",
							contact : "1251322311",
							icon : "/static/model/images/map/person.png"
						} ];
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_ryfb(Global_Search_Data);
							$("#titletext2").text("人员分布");
						}
					}
				});
				$.jBox.closeTip();
			});
			$("#wxzy")
					.click(
							function() {
								MAP_TYPE = 'wxzy';
								$("#keyword").combobox("setValue", "");
								$.jBox
										.tip(
												"请稍等...",
												'loading',
												{
													opacity : 0,
													loaded : function() {
														Global_Search_Data = getData("/cbsgl/cngg/wxzymapjson");
														setSearchData(Global_Search_Data);
														if (Global_Search_Data != undefined) {
															reloadMap(Global_Search_Data);// 创建和初始化地图
															loadMapData_wxzy();
															/*$("#titletext2").html("危险作业<i class='fa fa-circle' style='color:#ed2d2d;'>动火11家</i>" +
															    "<i class='fa fa-circle' style='color:#e67f21;'>登高4家</i>" +
															    "<i class='fa fa-circle' style='color:#ffd075;'>有限空间3家</i>" +
															    "<i class='fa fa-circle' style='color:#07A0FD;'>外来方施工2家</i>" +
															    "<i class='fa fa-circle' style='color:#1ab394;'>动土0家</i>");*/
														}
													}
												});
								$.jBox.closeTip();
							});
			$("#cbs").click(function() {
				MAP_TYPE = 'cbs';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = getData("/cbsgl/cngg/cbsmapjson");
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_cbs();
						}
					}
				});
				$.jBox.closeTip();
			});
			$("#whpcl").click(function() {
				MAP_TYPE = 'whpcl';
				$("#keyword").combobox("setValue", "");
				$.jBox.tip("请稍等...", 'loading', {
					opacity : 0,
					loaded : function() {
						Global_Search_Data = [ {
							pointx : "120.940578",
							pointy : "31.901546",
							title : "南通瑞翔新材料有限公司",
							address : "南通瑞翔新材料有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.962555",
							pointy : "31.855715",
							address : "南通天和树脂有限公司",
							title : "南通天和树脂有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.969061",
							pointy : "31.847652",
							title : "欧区爱铸造材料（中国）有限公司",
							address : "欧区爱铸造材料（中国）有限公司",
							count : "4",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.956532",
							pointy : "31.833934",
							title : "中化南通石化储运有限公司",
							address : "中化南通石化储运有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.960352",
							pointy : "31.860037",
							title : "江苏宝灵化工股份有限公司",
							address : "江苏宝灵化工股份有限公司",
							count : "5",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.95324",
							pointy : "31.839281",
							title : "南通嘉民港储有限公司",
							address : "南通嘉民港储有限公司",
							count : "1",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.970786",
							pointy : "31.865247",
							title : "朗盛高新材料(南通)有限公司",
							address : "朗盛高新材料(南通)有限公司",
							count : "2",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.959156",
							pointy : "31.863819",
							title : "台橡宇部(南通)化学工业有限公司",
							address : "台橡宇部(南通)化学工业有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.952649",
							pointy : "31.85606",
							title : "南通千红石化港储有限公司",
							address : "南通千红石化港储有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.964698",
							pointy : "31.834141",
							title : "江苏王子制纸有限公司",
							address : "江苏王子制纸有限公司",
							count : "4",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.933294",
							pointy : "31.91191",
							title : "凡特鲁斯特种化学品（南通）有限公司",
							address : "凡特鲁斯特种化学品（南通）有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.962095",
							pointy : "31.865825",
							title : "台橡（南通）实业有限公司",
							address : "台橡（南通）实业有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.971643",
							pointy : "31.86747",
							title : "迈图高新材料（南通）有限公司",
							address : "迈图高新材料（南通）有限公司",
							count : "1",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.971152",
							pointy : "31.845773",
							title : "日立化成工业（南通）化工有限公司",
							address : "日立化成工业（南通）化工有限公司",
							count : "2",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.974766",
							pointy : "31.839617",
							title : "万洲石化（江苏）有限公司",
							address : "万洲石化（江苏）有限公司",
							count : "1",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "121.031085",
							pointy : "31.823144",
							title : "上海振华重工集团（南通）传动机械有限公司",
							address : "上海振华重工集团（南通）传动机械有限公司",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.947771",
							pointy : "31.864263",
							title : "惠生(南通)重工有限公司",
							address : "惠生(南通)重工有限公司",
							count : "1",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.936052",
							pointy : "31.90183",
							title : "南通中集安瑞科食品装备有限公司",
							address : "南通中集安瑞科食品装备有限公司",
							count : "2",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.986012",
							pointy : "31.99163",
							title : "南通金钛特种钢有限公司",
							address : "南通金钛特种钢有限公司",
							count : "1",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						}, {

							pointx : "120.976002",
							pointy : "31.91173",
							title : "南通祥电力机械有限",
							address : "南通祥电力机械有限",
							count : "3",
							icon : "/static/model/images/ead/yjjc/i_yjzb.png"
						} ];
						setSearchData(Global_Search_Data);
						if (Global_Search_Data != undefined) {
							reloadMap(Global_Search_Data);// 创建和初始化地图
							loadMapData_whpcl(Global_Search_Data);
							$("#titletext2").html("危化品车辆");
						}
					}
				});
				$.jBox.closeTip();
			});

		});

		var nameLabels = [];

		function addZoomListener() {
			map.addEventListener("zoomend", zoomfun);
			map.addEventListener("moveend", zoomfun);
		}

		function zoomfun() {
			if (MAP_TYPE == 'qyfb') {
				if (map.getZoom() > 16) {
					var bounds = map.getBounds();
					$.each(BIS_R, function(index, value) {
						var point = new BMap.Point(value.pointx, value.pointy);
						if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
							var opts = {
								position : point,
								offset : new BMap.Size(5, -10)
							//设置文本偏移量
							}
							var label = new BMap.Label(value.title, opts); // 创建文本标注对象
							label.setStyle({
								"border-color" : "red"
							});
							map.addOverlay(label);
							nameLabels.push(label);
						}
					});
					$.each(BIS_O, function(index, value) {
						var point = new BMap.Point(value.pointx, value.pointy);
						if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
							var opts = {
								position : point,
								offset : new BMap.Size(5, -10)
							//设置文本偏移量
							}
							var label = new BMap.Label(value.title, opts); // 创建文本标注对象
							label.setStyle({
								"border-color" : "orange"
							});
							map.addOverlay(label);
							nameLabels.push(label);
						}
					});
					$.each(BIS_Y, function(index, value) {
						var point = new BMap.Point(value.pointx, value.pointy);
						if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
							var opts = {
								position : point,
								offset : new BMap.Size(5, -10)
							//设置文本偏移量
							}
							var label = new BMap.Label(value.title, opts); // 创建文本标注对象
							label.setStyle({
								"border-color" : "yellow"
							});
							map.addOverlay(label);
							nameLabels.push(label);
						}
					});
					$.each(BIS_B, function(index, value) {
						var point = new BMap.Point(value.pointx, value.pointy);
						if (BMapLib.GeoUtils.isPointInRect(point, bounds)) {
							var opts = {
								position : point,
								offset : new BMap.Size(5, -10)
							//设置文本偏移量
							}
							var label = new BMap.Label(value.title, opts); // 创建文本标注对象
							label.setStyle({
								"border-color" : "blue"
							});
							map.addOverlay(label);
							nameLabels.push(label);
						}
					});
				} else {
					$.each(nameLabels, function(index, value) {
						map.removeOverlay(value);
					});
					nameLabels = [];
				}
			}
		}

		function searchAll() {
			search();
			if (MAP_TYPE == 'qyfb' || MAP_TYPE == 'fxxx') {
				search_qyfb();
			} else if (MAP_TYPE == 'cgxx') {
				search_cgxx();
			} else if (MAP_TYPE == 'spjk') {
				search_spjk();
			} else if (MAP_TYPE == 'zdzd') {
				search_zdzd();
			} else if (MAP_TYPE == 'wxzy') {
				search_wxzy();
			} else if (MAP_TYPE == 'cbs') {
				search_cbs();
			}
		}

		function resetAll() {
			reset();
			if (MAP_TYPE == 'zdzd') {
				reset_zdzd();
			} else if (MAP_TYPE == 'wgfx') {
				$("#wgfx").click();
			} else
				reset_qyfb();
		}



		/*****************************  风险点统计  **********************************/
		var chart2;
		$(function(){
			$.ajax({
				type: 'POST',
				url: ctx + '/fxgk/fxxx/getFxdCount',
				success: function (data) {
					loadEcharts2(data);
				}
			})
		})

		function loadEcharts2(data) {
			chart2 = echarts.init(document.getElementById("chart2"), "vintage");
			chart2.setOption({
				legend: {},
				tooltip: {},
				color:['red','orange', 'yellow', '#5578cf'],
				backgroundColor: 'rgba(1,202,217,.0)',
				dataset: {
					source: [
						['红', data.red],
						['橙', data.orange],
						['黄', data.yellow],
						['蓝', data.blue]
					]
				},
				grid: {
					left: '3%',
					right: '5%',
					top: '10%',
					bottom: '3%',
					containLabel: true
				},
				xAxis: {type: 'category'},
				yAxis: {},
				series: [{
						type: 'bar',
						itemStyle: {
						   normal:{
						   color: function (params){
							   var colorList = ['#ff4844','#ffa042','#f9f900','#2894ff'];
							   return colorList[params.dataIndex];
						   }
					   },
					}
				}]
			})
		}

		window.onresize = function() {
			$("#container").height($("#cate_1_r").height() - 32);
			a.resize();
			chart2.resize();
		}
		window.onload = function() {
			$("#fxxx").trigger("click");
		}

		/*****************************  行业风险  **********************************/
		$(function(){
			// 红色风险点
			$.ajax({
				type: 'POST',
				async: false,
				url: ctx + '/fxgk/fxxx/getFxdInfo/1',
				success: function (data) {
					var redFxdList = JSON.parse(data);
					$.each(redFxdList, function (index, el) {
						var picHtml = getPicHtml(el);
						var ztHtml = getZtHtml(el);
						var html = getAppendHtml(el, picHtml, ztHtml);
						$('#red_fxd').append(html);
					})
				}
			})

			// 橙色风险点
			$.ajax({
				type: 'POST',
				async: false,
				url: ctx + '/fxgk/fxxx/getFxdInfo/2',
				success: function (data) {
					var orangeFxdList = JSON.parse(data);
					$.each(orangeFxdList, function (index, el) {
						var picHtml = getPicHtml(el);
						var ztHtml = getZtHtml(el);
						var html = getAppendHtml(el, picHtml, ztHtml);
						$('#orange_fxd').append(html);
					})
				}
			})

			// 黄色风险点
			$.ajax({
				type: 'POST',
				async: false,
				url: ctx + '/fxgk/fxxx/getFxdInfo/3',
				success: function (data) {
					var yellowFxdList = JSON.parse(data);
					$.each(yellowFxdList, function (index, el) {
						var picHtml = getPicHtml(el);
						var ztHtml = getZtHtml(el);
						var html = getAppendHtml(el, picHtml, ztHtml);
						$('#yellow_fxd').append(html);
					})
				}
			})

			// 蓝色风险点
			$.ajax({
				type: 'POST',
				async: false,
				url: ctx + '/fxgk/fxxx/getFxdInfo/4',
				success: function (data) {
					var blueFxdList = JSON.parse(data);
					$.each(blueFxdList, function (index, el) {
						var picHtml = getPicHtml(el);
						var ztHtml = getZtHtml(el);
						var html = getAppendHtml(el, picHtml, ztHtml);
						$('#blue_fxd').append(html);
					})
				}
			})

		})

		// 现场照片展示样式
		function getPicHtml(obj) {
			var picHtml = '';
			var picUrl = obj.m16;
			if(picUrl != null && picUrl != '') {
				var urls = picUrl.split(",");
				for(var i in urls){
					picHtml = "<a class='fa fa-picture-o btn-danger btn-outline' onclick=openImgView('"+urls[i].split("||")[0]+"')>照片"+(parseInt(i)+1)+"</a>";
				}
			}else {
				picHtml = '/';
			}
			return picHtml;
		}

		// 状态展示样式
		function getZtHtml(obj) {
			var ztHtml = '';
			if (obj.stoppointid) {
				ztHtml = '<div class="bgr">停产</div>';
			} else {
				ztHtml = '<div class="bggr">正常</div>';
			}
			return ztHtml;
		}

		// 追加的内容
		function getAppendHtml(obj, picHtml, ztHtml) {
			var html = '<div id="fxdxq_" class="list_li">' +
					'    <div class="w25 line" title="">' + obj.m1 + '</div>' +
					'    <div class="w20 line" title="">' + obj.m2 + '</div>' +
					'    <div class="w20 line" title="">' + obj.m3 + '</div>' +
					'    <div class="w15 line" title="">' + picHtml + '</div>' +
					'    <div class="w20 line" title="">' + ztHtml + '</div>' +
					'</div>';
			return html;
		}

		/***************************  两单三卡管理  *********************************/
		// 弹出风险点辨识清单信息
		function showbsqd() {
			openDialogView("查看风险点辨识清单信息",ctx+"/fxgk/fxdbsqd/index","800px", "500px","");
		}

		// 弹出风险分级管控清单信息
		function showfjgk() {
			openDialogView("查看风险分级管控清单信息",ctx+"/fxgk/fxdfjgkqd/index","800px", "500px","");
		}

		// 弹出风险告知卡信息
		function showgzk() {
			openDialogView("查看风险告知卡信息",ctx+"/fxgk/fxgzk/index","800px", "500px","");
		}

		// 弹出应急处置卡信息
		function showczk() {
			openDialogView("查看应急处置卡信息",ctx+"/fxgk/yjczk/index","800px", "500px","");
		}

		// 弹出安全承诺卡信息
		function showcnk() {
			openDialogView("查看安全承诺卡信息",ctx+"/fxgk/aqcnk/index","800px", "500px","");
		}
	</script>
</body>
</html>