<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<link href="${ctx}/static/model/css/home/styles.css?v=1.0"
	rel="stylesheet" />
<link href="${ctxStatic}/jqueryToast/css/toast.style.css" rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
<script type="text/javascript"
	src="${ctx}/static/common/initmap.js?v=1.2"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
<script type="text/javascript" src="${ctxStatic}/jqueryToast/js/toast.script.js"></script>
<style type="text/css">
body {
	background-color: #FAFAFA
}

.tit {
	padding: 0 10px 0 6px;
	border-left: 3px solid #34abb8;
	line-height: 15px;
	font-size: 15px;
	color: #0096a7;
	font-weight: 600;
}

.lab {
	height: 345px;
	margin-left: 0px;
	margin-right: 0px;
	align-items: unset;
	flex-direction: column;
	background: rgb(255, 255, 255);
	box-shadow: 2px 2px 8px #ececec;
}

.margin_ {
	margin-top: 10px;
	margin-bottom: 10px;
	padding-left: 10px;
	padding-right: 10px;
}

#block_item .lab {
	height: 716px
}

.labwds_ {
	padding: 10px;
	background: rgba(33, 150, 243, 0.16862745098039217);
}

.labic_ {
	height: calc(100% - 30px);
}

.ul__ {
	height: 100%;
	padding: 6px 0px;
	overflow-y: scroll!important;
	overflow-x: hidden;
}

.li__ {
	display: flex;
	align-items: center;
	padding: 10px 10px
}

.tag__ {
	display: flex;
	align-items: center;
}

.tag__ img {
	margin-top: 2px;
	margin-right: 4px;
}

.li__ .news__ {
	margin-right: 10px;
}

.ul__ .li__:nth-child(odd) {
	background: #f7f7f7;
}

.ul__ .li__:nth-child(even) {
	background: #fff;
}

.line_ {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}

.str_wrap {
	overflow: hidden; //
	zoom: 1;
	width: 100%;
	font-size: 13px;
	line-height: 16px;
	position: relative;
	-moz-user-select: none;
	-khtml-user-select: none;
	user-select: none;
	background: #fff;
	white-space: nowrap;
}

.str_move {
	white-space: nowrap;
	position: absolute;
	top: 0;
	left: 0;
	cursor: move;
	width: 100%;
}

#fxdlb_ctt {
	margin: 8px;
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

.w35 {
	width: 35%
}

.w30 {
	width: 30%
}

#fxdlb_ctt .list_b {
	flex-direction: column;
	overflow-y: scroll;
	/*height: 252px*/
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
/*
.line {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1;
	-webkit-box-orient: vertical;
}*/

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
.bggr {
	background: #45a549;
}
.bgy {
	background: yellow
}

.bgb {
	background: #2196f3
}

.lab {
	color: #333;
}

.spjk_{
	width: 95.2%;
    margin: 2% 2.4%;
    height: 84%;
    position: relative;
    background: #000;
}

.spjk_btns{
	display: flex;
    font-size: 12px;
    position: absolute;
    right: 10px;
    margin-top: 5px;
    color: #ababab;
}

.spjk_btn{
    margin: 0 4px;
    padding: 4px 9px;
    background: #06587d;
    border-radius: 3px;
    cursor: pointer;
}
.btn_chos{
	background: #4897d4;
	color: #fff;
}

.lab {
	border:1px solid #c9dbdc;
}
body {
	background-color: #ffffff;
}
.panel-header, .panel-body {
	border-color: #bfd6d3;
}
.datagrid-header .datagrid-cell {
	height: 16px;
}

.labwds_ {
	padding: 6px 10px;
	background: rgba(33, 166, 169, 0.15);
}
</style>
</head>
<body>
	<div class="">
		<div class="col-lg-12 col-md-12 col-xs-12 mgb10" style="padding: 0px;">
			<div class="col-lg-12 col-md-12 col-xs-12 mgt10 mtb8" id="block_items"
				style="padding:0px;margin: 0;">
				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">储罐实时监测</span>
						</div>
						<div class="labic_">
							<div id="fxdlb_ctt" class="__aa" style="">
								<table id="ssjc_cg_dg"></table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit" id="qt">气体实时监测</span>
						</div>
						<div class="labic_">
							<div id="fxdlb_ctt" class="__aa" style="">
								<table id="ssjc_qt_dg"></table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">视频实时监测</span>
						</div>
						<div class="labic_" style="position: relative;">
							<div class="spjk_btns" style="z-index: 100;">
								<div class="spjk_btn btn_chos" id="sp1" style="display: none;">1</div>
								<div class="spjk_btn" id="sp2" style="display: none;">2</div>
								<div class="spjk_btn" id="sp3" style="display: none;">3</div>
								<div class="spjk_btn" id="spmore" style="display: none;">更多</div>
							</div>
							<div id="playlive" class="" style="position: relative;text-align: center;height: 100%;">

							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">二道门进出信息</span>
						</div>
						<div id="edminfo" class="labic_">

						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="">
							<span class="tit">高危工艺实时监测</span>
						</div>
						<div class="labic_">
							<div id="fxdlb_ctt" class="__aa" style="">
								<table id="ssjc_gwgy_dg"></table> 
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-xs-12 margin_">
					<div class="lab">
						<div class="labwds_" style="display: flex;justify-content: space-between;">
							<span class="tit" style="line-height: 20px;">报警信息</span>
							<span class="tit" style="border-left: 0px solid #34abb8;font-weight: 500;padding: 0 4px 0 6px;" onclick="getMoreBjxx()">更多>></span>
						</div>
						<div class="labic_">
							<!-- <div id="fxdlb_ctt" class="__aa" style="border: 1px solid #daeeff;">
								<table id="ssjc_bj_dg"></table>
							</div> -->
							<c:choose>
								<c:when test="${bjxxList != null && fn:length(bjxxList) != 0}">
									<div class="ul__ dowebok" style="overflow:hidden;">
										<c:forEach items="${bjxxList }" var="s">
											<div class="li__" style="display: flex; justify-content: space-between;">
												<div style="display: flex;align-item:center;">
													<div class="tag__">
														<img src="${ctxStatic}/model/images/home/skin_/new.png" />
													</div>
													<div class="line_ news__">
														<c:if test="${s.jctype eq '1'}">
															储罐${s.wh } ${s.alarmtype }：${s.bjxx } ${s.unit}
														</c:if>
														<c:if test="${s.jctype eq '2'}">
															${s.cgqname } ${s.alarmtype }：${s.bjxx } ${s.unit}
														</c:if>
														<c:if test="${s.jctype eq '3'}">
															高危工艺${s.name } ${s.alarmtype }：${s.bjxx } ${s.unit}
														</c:if>
													</div>
												</div>

												<div class="date__">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${s.bjsj }" />
												</div>
											</div>
										</c:forEach>
									</div>
								</c:when>
								<c:otherwise>
									<div style="text-align: center;">
										<img src="${ctxStatic}/model/images/hgqy/nobjxx.png" style="height:300px;" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>

	<%-- 报警信息toast弹出框 --%>
	<div id="msg__s" style="position:fixed;bottom:0;right:0">

	</div>

	<script type="text/javascript">
	var qyid = '${qyid}';
	var dg;
	var dg2;
	var dg3;
		$(function() {
			$('.dowebok').liMarquee({
				direction : 'up',
				scrollamount : 10
			});

			//getData1();// 获取储罐、气体、高危工艺实时数据
			getSpData();// 获取视频信息
			getedminfo();// 获取二道门信息
			getBjxx();
		});

		$(window).ready(function() {
			$(".__aa").height($(".__aa").parent().height()-18);
			
			$(".__aa .list_b").height($(".__aa").height()-$(".__aa .list_h").height());
			
			$(".spjk_btn").click(function(){
				$(this).addClass("btn_chos").siblings(".spjk_btn").removeClass("btn_chos");
			})
		})

		$(window).resize(function() {
		})

		function initBlocks() {

		}

		// 查看详情提示框
		function tipsInfo(idName){
            layer.tips("<span style='color:#fff;'>点击查看详情</span>",'#'+idName,{tips:[3,'#f8ac59'],time:1000,area: 'auto',maxWidth:500,tipsMore:true});
        }

	// 获取储罐、气体、高危工艺实数数据
	$(function(){
			dg=$('#ssjc_cg_dg').datagrid({// 储罐
			nowrap:false,
			method: "post",
		    url:ctx+'/zxjkyj/zdwxycg/list',
		    queryParams: {qyid: qyid},
		    fit : true,
			fitColumns : true,
			selectOnCheck:false,
			nowrap: false,
			idField : 'id',
			striped:true,
			scrollbarSize:0,
			pagination:true,
			rownumbers:true,
			pageNumber:1,
			pageSize : 50,
			pageList : [20, 50, 100, 150, 200, 250 ],
			singleSelect:true,
		    columns:[[
				{field:'cgname',title:'储罐名称',sortable:false,width:80,align:'center'},
				{field:'wh',title:'储罐位号',sortable:false,width:60,align:'center',
					formatter : function(value, row, index) {
						if (value==null||value=='')
							return '/';
						else
							return '<a id="wh_'+row.RowNumber+'" onclick=viewxq('+row.tankid+',"0") onmouseover=tipsInfo("wh_'+row.RowNumber+'")>'+value+'</a>';
					}
				},
				{field:'target_type',title:'监测类型',sortable:false,width:60,align:'center',
					formatter : function(value, row, index) {
						if (value == 'WD')
							return '温度';
						else if (value == 'YL')
							return '压力';
						else  if (value == 'YW')
							return '液位';
					}
				},
				{field:'alarm_value',title:'是否报警',sortable:false,width:80,align:'center',
					formatter : function(value, row, index) {
						if (row.alarm_time == null || row.alarm_time == ''){
							return '正常';
						}else{
							var imgSrc = '${ctxStatic}/model/images/xfss/zmdj.gif';
							return '<img src="'+imgSrc+'">';
						}
					}
				},
				{field:'value',title:'监测数据',sortable:false,width:80,align:'center',
					formatter : function(value, row, index) {
						if (value == null) {
							return '/';
						} else {
							return value.toFixed(2) + ' ' +row.unit;
						}
					}
				},
				{field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
					formatter : function(value) {
						var datetime = new Date(value);
						return datetime.format("yyyy-MM-dd hh:mm:ss");
					}
				},
				{field:'zt',title:'状态',sortable:false,width:50,align:'center',
					formatter : function(value) {
                        var title = '';
                        var imgUrl = '';
                        if (value == '0') {
                            title = '在线';
                            imgUrl = ctx + '/static/model/images/hgqy/zx.png';
                        } else {
                            title = '离线';
                            imgUrl = ctx + '/static/model/images/hgqy/lx.png';
                        }
                        return '<img src="'+imgUrl+'" title="'+title+'"/>';
					}
				},
				{field:'cz',title:'操作',sortable:false,width:150,align:'center',
					formatter : function(value, row, index) {
						var a = "";
						a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick=viewbjxx(" + row.id + ",'"+row.target_type+"')>报警信息</a>";
						a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt(" + row.id + ",'"+row.target_type+"')>趋势图</a>";
						return a;
					}
				}
		    ]],
		    onDblClickRow: function (rowdata, rowindex, rowDomElement){
		    },
		    onLoadSuccess: function(){
		    	$(this).datagrid("autoMergeCells",['qyname','cgname','wh']);
		    },
		    onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
		    },
		    rowStyler:function(index,row){
				/*if (row.zt == '1'){
					return 'background-color:rgb(232, 190, 101);';
				}*/
			},
		    enableHeaderClickMenu: true,
		    enableRowContextMenu: false,
		    toolbar:''
			});
		  
			dg2=$('#ssjc_qt_dg').datagrid({// 气体
				nowrap:false,
				method: "post",
			    url:ctx+'/zxjkyj/zdwxyqt/list',
			    queryParams: {qyid: qyid},
			    fit : true,
				fitColumns : true,
				selectOnCheck:false,
				nowrap: false,
				idField : 'id',
				striped:true,
				scrollbarSize:0,
				pagination:true,
				rownumbers:true,
				pageNumber:1,
				pageSize : 50,
				pageList : [20, 50, 100, 150, 200, 250 ],
				singleSelect:true,
			    columns:[[
					{field:'bit_no',title:'点位号',sortable:false,width:80,align:'center'},
					{field:'target_type',title:'气体类型',sortable:false,width:80,align:'center',
						formatter : function(value, row, index) {
							if (value == 'YDQT') {
								return '有毒气体';
							}else if(value == 'KRQT') {
								return '可燃气体';
							}
						}
					},
					{field:'alarm_value',title:'是否报警',sortable:false,width:80,align:'center',
						formatter : function(value, row, index) {
							if (row.alarm_time == null || row.alarm_time == ''){
								return '正常';
							}else{
								var imgSrc = '${ctxStatic}/model/images/xfss/zmdj.gif';
								return '<img src="'+imgSrc+'">';
							}
						}
					},
					{field:'value',title:'实时浓度',sortable:false,width:80,align:'center',
						formatter : function(value, row, index) {
							if (value == null) {
								return '/';
							} else {
								return value.toFixed(2) + ' ' +row.unit;
							}
						}
					},
					{field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
						formatter : function(value, row, index) {
							var datetime = new Date(value);
							return datetime.format('yyyy-MM-dd hh:mm:ss');
						}
					},
					{field:'zt',title:'状态',sortable:false,width:50,align:'center',
						formatter : function(value) {
                            var title = '';
							var imgUrl = '';
							if (value == '0') {
							    title = '在线';
								imgUrl = ctx + '/static/model/images/hgqy/zx.png';
							} else {
                                title = '离线';
								imgUrl = ctx + '/static/model/images/hgqy/lx.png';
							}
							return '<img src="'+imgUrl+'" title="'+title+'"/>';
						}
					},
					{field:'cz',title:'操作',sortable:false,width:150,align:'center',
						formatter : function(value, row, index) {
							var a = "";
							a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick=viewbjxx1(" + row.id + ",'"+row.target_type+"')>报警信息</a>";
							a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt1(" + row.id + ",'"+row.target_type+"')>趋势图</a>";
							return a;
						}
					}
			    ]],
			    onDblClickRow: function (rowdata, rowindex, rowDomElement){
			    },
			    onLoadSuccess: function(){
			        $(this).datagrid("autoMergeCells",['qyname']);
			    },
			    onLoadError:function(){
			    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			    },
			    rowStyler:function(index,row){
					/*if (row.zt == '1'){
						return 'background-color:rgb(232, 190, 101);';
					}*/
				},
			    enableHeaderClickMenu: true,
			    enableRowContextMenu: false,
			    toolbar:''
				});

			dg3=$('#ssjc_gwgy_dg').datagrid({// 高危工艺
				nowrap:false,
				method: "post",
				url:ctx+'/zxjkyj/zdwxygwgy/list',
				queryParams: {qyid: qyid},
				fit : true,
				fitColumns : true,
				selectOnCheck:false,
				nowrap: false,
				idField : 'id',
				striped:true,
				scrollbarSize:0,
				pagination:true,
				rownumbers:true,
				pageNumber:1,
				pageSize : 50,
				pageList : [20, 50, 100, 150, 200, 250 ],
				singleSelect:true,
				columns:[[
					{field:'gwgyname',title:'工艺名称',sortable:false,width:60,align:'center'},
					{field:'bit_no',title:'点位号',sortable:false,width:60,align:'center'},
					/*{field:'kzfs',title:'控制方式',sortable:false,width:80,align:'center'},*/
					{field:'target_type',title:'监测类型',sortable:false,width:60,align:'center',
						formatter : function(value, row, index) {
							if (value == 'WD')
								return '温度';
							else if (value == 'YL')
								return '压力';
							else  if (value == 'YW')
								return '液位';
						}
					},
					{field:'alarm_value',title:'是否报警',sortable:false,width:80,align:'center',
						formatter : function(value, row, index) {
							if (row.alarm_time == null || row.alarm_time == ''){
								return '正常';
							}else{
								var imgSrc = '${ctxStatic}/model/images/xfss/zmdj.gif';
								return '<img src="'+imgSrc+'">';
							}
						}
					},
					{field:'value',title:'监测数据',sortable:false,width:80,align:'center',
						formatter : function(value, row, index) {
							if (value == null) {
								return '/';
							} else {
								return value.toFixed(2) + ' ' +row.unit;
							}
						}
					},
					{field:'cjsj',title:'采集时间',sortable:false,width:100,align:'center',
						formatter : function(value) {
							var datetime = new Date(value);
							return datetime.format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{field:'zt',title:'状态',sortable:false,width:50,align:'center',
						formatter : function(value) {
                            var title = '';
                            var imgUrl = '';
                            if (value == '0') {
                                title = '在线';
                                imgUrl = ctx + '/static/model/images/hgqy/zx.png';
                            } else {
                                title = '离线';
                                imgUrl = ctx + '/static/model/images/hgqy/lx.png';
                            }
                            return '<img src="'+imgUrl+'" title="'+title+'"/>';
						}
					},
					{field:'cz',title:'操作',sortable:false,width:150,align:'center',
						formatter : function(value, row, index) {
							var a = "";
							a+= "<a style='margin:2px' class='btn btn-warning btn-xs' onclick=viewbjxx2(" + row.id + ",'"+row.target_type+"')>报警信息</a>";
							a+= "<a style='margin:2px' class='btn btn-primary btn-xs' onclick=viewbdt2(" + row.id + ",'"+row.target_type+"')>趋势图</a>";
							return a;
						}
					}
				]],
				onDblClickRow: function (rowdata, rowindex, rowDomElement){
				},
				onLoadSuccess: function(){
					$(this).datagrid("autoMergeCells",['qyname','cgname','wh','wl','lx']);
				},
				onLoadError:function(){
					layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
				},
				rowStyler:function(index,row){
					/*if (row.zt == '1'){
						return 'background-color:rgb(232, 190, 101);';
					}*/
				},
				enableHeaderClickMenu: true,
				enableRowContextMenu: false,
				toolbar:''
			});
	})

		// 视频实时监测
		var spUrls = [];
		function getSpData() {
			$.ajax({
				type: 'POST',
				url: ctx+'/bis/spsbxx/zdwxySpUrls',
				data: {'qyid': qyid},
				dataType: 'json',
				success: function(data) {
                    var parseData = JSON.parse(data);
					if (parseData != "" && parseData != null && parseData != undefined) {
						if (parseData.length == 1) {// 如果只有一个重大危险源视频，则只显示'1'按钮
							$('#sp1').show();
						} else if (parseData.length == 2) {// 如果有两个重大危险源视频，则显示'1,2'按钮
							$('#sp1').show();
							$('#sp2').show();
						} else if (parseData.length == 3) {// 如果有三个重大危险源视频，则显示'1,2,3'按钮
							$('#sp1').show();
							$('#sp2').show();
							$('#sp3').show();
						} else if (parseData.length > 3) {// 如果超过三个重大危险源视频，则显示全部按钮
							$('#sp1').show();
							$('#sp2').show();
							$('#sp3').show();
							$('#spmore').show();
						}

						// 循环将url放入spUrls中，方便按钮点击事件获取对应的url
						$.each(parseData, function (index, el) {
							spUrls.push(el.url);
						})
						// 显示视频图像
						showVideo(spUrls[0]);
					} else {
                        $('.spjk_btns').hide();
                        $('#playlive').append('<img src="${ctxStatic}/model/images/hgqy/nozdwxysp.png" style="height:300px;" />');
					}
				}
			})
		}

		// 显示视频播放器
		function showVideo(url) {
			var videoObject = {
				container: '#playlive',//“#”代表容器的ID，“.”或“”代表容器的class
				variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
				autoplay: false, //是否自动播放
				video:url //视频地址
			};
			var player=new ckplayer(videoObject);
		}

		// '1,2,3,更多'按钮点击触发事件
		$('#sp1').click(function(){
			showVideo(spUrls[0]);
		});

		$('#sp2').click(function(){
			showVideo(spUrls[1]);
		});

		$('#sp3').click(function(){
			showVideo(spUrls[2]);
		});

		$('#spmore').click(function(){
			openDialogView("查看更多重大危险源视频",ctx+"/zdwxy/spjk/index","90%", "90%","");
		});

		/* 储罐实时监测 */
		//查看详情
		function viewxq(tankid,jctype){
			//openDialogView("查看储罐实时监测信息",ctx+"/zxjkyj/zdwxycg/viewxq/"+tankid+"/"+jctype,"1000px", "80%","");
			openDialogView("查看储罐信息",ctx+"/bis/cgxx/view/"+tankid,"1000px", "80%","");
		}

		//查看报警信息
		function viewbjxx(id,jctype){
			openDialogView("查看储罐报警信息",ctx+"/zxjkyj/zdwxycg/viewbjxx/"+id+"/"+jctype,"800px", "400px","");
		}

		//查看波动图信息
		function viewbdt(id,jctype){
			openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxycg/viewbdtindex/"+id+"/"+jctype,"100%", "100%","");
		}

		/* 气体实时监测 */
		//查看详情
		function viewxq1(cgqbh){
			openDialogView("查看气体实时监测信息",ctx+"/zxjkyj/zdwxyqt/viewxq/"+cgqbh,"900px", "450px","");
		}

		//查看报警信息
		function viewbjxx1(id,jctype){
			openDialogView("查看气体报警信息",ctx+"/zxjkyj/zdwxyqt/viewbjxx/"+id+"/"+jctype,"800px", "400px","");
		}

		//查看波动图信息
		function viewbdt1(id,jctype){
			openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxyqt/viewbdtindex/"+id+"/"+jctype,"100%", "100%","");
		}

		/* 高危工艺实时监测 */
		//查看详情
		function viewxq2(gwgyid,jctype){
			openDialogView("查看高危工艺实时监测信息",ctx+"/zxjkyj/zdwxygwgy/viewxq/"+gwgyid+"/"+jctype,"900px", "450px","");
		}

		//查看报警信息
		function viewbjxx2(id,jctype){
			openDialogView("查看高危工艺报警信息",ctx+"/zxjkyj/zdwxygwgy/viewbjxx/"+id+"/"+jctype,"800px", "400px","");
		}

		//查看波动图信息
		function viewbdt2(id,jctype){
			openDialogView("查看历史波动图",ctx+"/zxjkyj/zdwxygwgy/viewbdtindex/"+id+"/"+jctype,"100%", "100%","");
		}

		// 获取更多报警信息
		function getMoreBjxx() {
			openDialogView("查看更多报警信息",ctx+"/bis/cgjcwhsj/viewmorebjxx","90%", "80%","");
		}

	// ajax获取最新的报警信息
	function getBjxx() {
		// 清空 消息列表
		$("#msg__s").html("");

		$.ajax({
			type: 'POST',
			url: ctx + '/bis/cgjcwhsj/getNewBjxx',
			success: function (data) {
				var parseData = JSON.parse(data);
				if (parseData != null && parseData != '' && parseData != undefined) {
					var content  = '';
					$.each(parseData, function (index, el) {
						if (el.jctype == 1) {
							content += "<span style='cursor: pointer;'>储罐 <span style='color: purple;'><strong>" + el.wh + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
						} else if (el.jctype == 2) {
							content += "<span style='cursor: pointer;'>储罐区 <span style='color: purple;'><strong>" + el.cgqname + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
						} else if (el.jctype == 3) {
							content += "<span style='cursor: pointer;'>高危工艺 <span style='color: purple;'><strong>" + el.name + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
						}
					})
					toastInfo(content);
				}
			}
		})
	}

		// 每隔一分钟请求一次报警数据
		timerID = setInterval("getBjxx()", 60000);

		// toast提示框
		function toastInfo(msg) {
			$.Toast($("#msg__s"),"报警信息", msg, "error", {
				stack: true,
				has_icon:true,
				has_close_btn:true,
				fullscreen:false,
				timeout:0,
				sticky:false,
				has_progress:true,
				rtl:false,
			});
		}

        // 点击toast弹出框查看对应的储罐报警信息
        function showBjxx(sigid, type) {
            openDialogView("查看储罐报警信息",ctx+"/zxjkyj/bjxx/cgbjindex?sigid="+sigid+"&&type="+type,"900px", "450px","");
        }

		// 点击toast弹出框查看对应的气体报警信息
        function showBjxx1(sigid, type) {
            openDialogView("查看气体报警信息",ctx+"/zxjkyj/bjxx/ndbjindex?sigid="+sigid+"&&type="+type,"900px", "450px","");
        }
		
      //获取二道门信息
        function getedminfo(){
        	$.ajax({
        		type: 'POST',
        		url: ctx + '/zxjkyj/edm/getEdmInfo',
        		success: function (data) {
        			$('#edminfo').html("");
        			var html="";
        			var parseData = JSON.parse(data);
        			if (parseData != null && parseData != '' && parseData != undefined) {
        				html = '<div class="ul__ dowebok" style="overflow:hidden">';
                        $.each(parseData, function (index, el) {
                        	html += '<div class="li__" style="display: flex; justify-content: space-between;">';
                        	html += '<div style="display: flex;align-item:center;">';
                        	html += '<div class="tag__"><img src="${ctxStatic}/model/images/home/skin_/new.png" /></div>';
                        	html += '<div class="line_ news__">';
                        	html += el.ygname+' 工号：'+el.ygcode+' 在'+el.edmname+' '+el.type;
                        	html += '</div>';
                        	html += '</div>';
                        	html += '<div class="date__">';
                        	var gxtime = '';
                        	if(el.updatetime != null){
        				        var datetime = new Date(el.updatetime);  
        				        gxtime = datetime.format('yyyy-MM-dd hh:mm:ss');    
        			        }
                        	html += gxtime;
                        	html += '</div>';
                        	html += '</div>';
                        });
                        html += '</div>';
        			}else{
        				html = '<div style="text-align: center;"><img src="${ctxStatic}/model/images/hgqy/noedm.png" style="height:300px;" /></div>';
        			}
        			$('#edminfo').append(html.replace(/undefined/g,''));
        		}
        	})
        }


	//一分钟更新二道门信息
	window.setInterval(getedminfo,60000);

    //一分钟更新储罐、气体、高危工艺信息
    timerID = setInterval("reloadDg()", 60000);

    function reloadDg() {
        dg.datagrid('reload');
        dg2.datagrid('reload');
        dg3.datagrid('reload');
    }

	</script>
</body>
</html>