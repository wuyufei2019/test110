<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!doctype html>
<head>
<meta charset="utf-8">
<title>政府端</title>
<link href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css"  type="text/css" media="all"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-select2/htools.select.skin.css">
<link href="${ctx}/static/model/vd2/css/style.css"  rel="stylesheet" type="text/css" media="all"/>
<link href="${ctx}/static/model/vd2/css/gov.css"  rel="stylesheet" type="text/css" media="all"/>

<script src="${ctx}/static/model/vd2/js/echarts.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/echarts/theme/vintage.js"></script>
<script type="text/javascript" src="${ctx}/static/echarts/chart/echarts-liquidfill.min.js"></script>
<script src="${ctx}/static/model/vd2/js/particles.min.js"></script>
<script src="${ctx}/static/jquery/jquery-3.3.1.min.js"></script>
<script src="${ctx}/static/jquery-select2/jquery.htools.select.js"></script>


<script src="${ctxStatic}/model/visualData/js/Plugin/bmap.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=LelFSt6BfykGf8m3PB5zr8LaXAG2cMg6"></script>
<!-- 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=u2shbb8SxUlE3GVjw9ylIekRBjcHE7tc"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=eoc1mrq7dOrepxytBMMrSYPMc9rzhSxW"></script>
 -->
<script src="${ctxStatic}/model/visualData/js/index2.js"></script>

<script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/qyfbmapindex.js?v=1.3"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/custom/stylelist.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<style>

</style>
</head>
<body style="background: #000000 url(${ctx}/static/model/vd2/img/a_bg.png);background-size: cover;background-position:center;">
<div class="wpbox">
<!-- 视频监控窗 -->
<div class="videoWindow">
	<div class="vw_h">
		<span class="f2">视频监控</span>
		<span class="vw_cls fn2" style="padding: 7px 12px;">X</span>
	</div>
	<div class="vw_b">
		<div class="vw_b_l">
			<div class="vw_vdo"></div>
		</div>
		<div class="vw_b_r">
			<div class="vw_vdo2 f3">
				<div class="">设备列表</div>
				<div class="sblist">
					
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 详情滑出窗 -->
<div class="slider_ slider_l">
	<!-- 安全指数悬浮球详情 -->
    <div id="aqzs_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>风险点详情
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="aqzs_b">
			<div class="aqzs_b_l">
				<div class="min_tit f3">
					基本信息
				</div>
				<div class="fxd_t_info">
					<div class="t_info_h">
						<span>风险点名称：线切割</span><span>等级：<span style="color:yellow">&nbsp;黄</span></span>
					</div>
					<div class="t_info_b">
						<div class="pic_">
							<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
						</div>
						<div class="t_2">
							<div class="">场所/环节/部位
							</div>
							<div class="">易导致（风险）
							</div>
							<div class="">检查状态
							</div>
							<div class="">最近检查人
							</div>
						</div>
						<div class="t_3">
							<div class="">线切割
							</div>
							<div class="">触电,火灾
							</div>
							<div class="">待检查
							</div>
							<div class="">王庭伟&nbsp;&nbsp;&nbsp;&nbsp;2019-07-01
							</div>
						</div>
					</div>
				</div>
				
				<div class="tab_ f3">
					<div class="capChart">
						<div class="capChart_t capChart_t_l">安全巡查</div>
						<div class="capChart_t capChart_t_b">动态监测</div>
						<div class="capChart_t capChart_t_r">安全档案</div>
						<div class="capChart_t capChart_t_t">隐患排查</div>
						<div class="capChart_q capChart_t_l"></div>
						<div class="capChart_q capChart_t_b"></div>
						<div class="capChart_q capChart_t_r"></div>
						<div class="capChart_q capChart_t_t"></div>
						<div class="capChart_ f3">
							<div class="fn1">88</div>
							<div>安全指数</div>
						</div>
					</div>
				</div>
				
				<div class="tab_ctt">
					<div id="zschart2" style="height: 100%;"></div>
				</div>
			</div>
			<div class="aqzs_b_r">
				<div id="qhxq_ctt">
					<div class="head">
						<div class="chos">
							<span class="f3">安全巡查</span>
						</div>
						<div class="">
							<span class="f3">隐患排查</span>
						</div>
						<div class="">
							<span class="f3">动态监测</span>
						</div>
						<div class="">
							<span class="f3">安全档案</span>
						</div>
					</div>
					<div class="chosText">共有5个隐患，其中2个已超期</div>
					<div class="left2_table">
			            <ul style="margin-right: 4px;">
			                <li class="list_ f3">
			                	<div class="lihd" style="margin-top: 5px;">
			                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span class="rsn f2">定期指标</span>
			                		<span class="lilab">已过期</span>
			              		</div>
			              		<div class="libd">
			                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
			                 		<div class="info">
				                		<span>点位名称：<span>随手拍</span></span>
				                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
				                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
				                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
			              			</div>
			               		</div>
			               </li>
			                 <li class="list_ f3 bg">
			                	<div class="lihd" style="margin-top: 5px;">
			                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span class="rsn f2">定期指标</span>
			                		<span class="lilab">已过期</span>
			              		</div>
			              		<div class="libd">
			                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
			                 		<div class="info">
				                		<span>点位名称：<span>随手拍</span></span>
				                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
				                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
				                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
			              			</div>
			               		</div>
			               </li>
			               <li class="list_ f3">
			                	<div class="lihd" style="margin-top: 5px;">
			                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span class="rsn f2">定期指标</span>
			                		<span class="lilab">已过期</span>
			              		</div>
			              		<div class="libd">
			                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
			                 		<div class="info">
				                		<span>点位名称：<span>随手拍</span></span>
				                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
				                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
				                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
			              			</div>
			               		</div>
			               </li>
			               <li class="list_ f3 bg">
			                	<div class="lihd" style="margin-top: 5px;">
			                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span class="rsn f2">定期指标</span>
			                		<span class="lilab">已过期</span>
			              		</div>
			              		<div class="libd">
			                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
			                 		<div class="info">
				                		<span>点位名称：<span>随手拍</span></span>
				                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
				                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
				                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
			              			</div>
			               		</div>
			               </li>
			            </ul>
			         </div>
				</div>
			</div>
		</div>
     </div>
</div>
<!-- 详情滑出窗 -->
<div class="slider_ slider_r disabled">
	<div id="aqry_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>安全人员
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="head clrq">
			<div class="">
				<span class="f2">企事业单位管理员<span class="clrw">&nbsp;&nbsp;&nbsp;&nbsp;18</span></span>
			</div>
			<div class="">
				<span class="f2">企业安全管理员<span class="clrw">&nbsp;&nbsp;&nbsp;&nbsp;18</span></span>
			</div>
		</div>
		<div class="left2_table">
	         <ul style="margin-right: 4px;">
	         	<li class="list_ f3">
	           		<div class="libd">
		              	<div class="info">
		              		<span>王庭伟&nbsp;&nbsp;<span>181****8870</span></span>
		              		<span>职&nbsp;&nbsp;&nbsp;&nbsp;位：<span>安全管理员</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	           		<div class="libd">
		              	<div class="info">
		              		<span>王庭伟&nbsp;&nbsp;<span>181****8870</span></span>
		              		<span>&nbsp;<span>&nbsp;</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	           		<div class="libd">
		              	<div class="info">
		              		<span>王庭伟&nbsp;&nbsp;<span>181****8870</span></span>
							<span>职&nbsp;&nbsp;&nbsp;&nbsp;位：<span>安全管理员</span></span>
		           		</div>
	            	</div>
	            </li>
	         </ul>
	     </div>
     </div>
     
	<div id="fxd_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>风险点
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="head">
			<div class="clrr">
				<p class="fn1">15</p>
				<p class="f2">红</p>
			</div>
			<div class="clro">
				<p class="fn1">15</p>
				<p class="f2">橙</p>
			</div>
			<div class="clry">
				<p class="fn1">15</p>
				<p class="f2">黄</p>
			</div>
			<div class="clrb">
				<p class="fn1">15</p>
				<p class="f2">蓝</p>
			</div>
		</div>
		<div class="left2_table">
	         <ul style="margin-right: 4px;">
	         	<li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab">红</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab" style="background:orange">橙</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab clrd" style="background:yellow">黄</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab" style="background: #2196f3">蓝</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	             <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab">红</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab" style="background:orange">橙</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab clrd" style="background:yellow">黄</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab" style="background: #2196f3">蓝</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>空压机及空气储罐</span></span>
		              		<span>上次巡查人员：<span>王庭伟</span></span>
		              		<span>上次巡查时间：<span>2019-06-27</span></span>
		           		</div>
	            	</div>
	            </li>
	         </ul>
	     </div>
     </div>
     
     <!-- 特种设备 -->
     <div id="tzsb_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>特种设备
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="head">
			<div class="chos">
				<span class="f2">全部<span>15</span></span>
			</div>
			<div class="">
				<span class="f2">已过期<span class="clrr">15</span></span>
			</div>
			<div class="">
				<span class="f2">未过期<span class="">0</span></span>
			</div>
		</div>
		<div class="left2_table">
	         <ul style="margin-right: 4px;">
	         	<li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab">已过期</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>设备名称：<span>行车</span></span>
		              		<span>出厂编号：<span>JACS001</span></span>
		              		<span>负&nbsp;&nbsp;责&nbsp;人：<span>张甜甜</span></span>
		              		<span>有效期至：<span>2020-04-09</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="lilab" style="background:green">未过期</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>设备名称：<span>行车</span></span>
		              		<span>出厂编号：<span>JACS001</span></span>
		              		<span>负&nbsp;&nbsp;责&nbsp;人：<span>张甜甜</span></span>
		              		<span>有效期至：<span>2020-04-09</span></span>
		           		</div>
	            	</div>
	            </li>
	         </ul>
	     </div>
     </div>
     
     <!-- 右下角 共用 -->
     <div id="pub_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>历史警报
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="head">
			<div class="" style="width: 100%;">
				<select id="select1">
		            <option value="1">全部</option>
		            <option value="2">储罐监测警报</option>
		            <option value="3">有毒气体警报</option>
		            <option value="4">高危工艺警报</option>
		            <option value="5">视频监控警报</option>
		        </select>
			</div>
			<!--
			<div class="">
				<select id="select2">
		            <option value="1">全部</option>
		            <option value="2">可燃/有毒气体监测</option>
		            <option value="3">电气火灾监测</option>
		            <option value="4">储罐监测</option>
		            <option value="5">独立烟感报警监测</option>
		            <option value="6">废水监测</option>
		            <option value="7">废气监测</option>
		        </select>
			</div>
			 -->
		</div>
		<div class="left2_table">
	         <ul style="margin-right: 4px;">
	         <!-- 
	         	<li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="f2">电气火灾监测</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>传感器名称：<span>江苏</span></span>
		              		<span>传&nbsp;感&nbsp;器&nbsp;ID：<span>JACS001</span></span>
		              		<span>区&nbsp;域&nbsp;位&nbsp;置：<span>江苏-昆山开发区</span></span>
		              		<span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：<span><i class="fa fa-circle clrr"></i>失联</span></span>
		              		<span>失&nbsp;联&nbsp;时&nbsp;间：<span>2019-06-27 14:03</span></span>
		           		</div>
	            	</div>
	            </li>
	            <li class="list_ f3">
	             	<div class="lihd" style="margin-top: 5px;">
	             		<span class="f2">储罐监测</span>
	           		</div>
	           		<div class="libd">
	              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
		              	<div class="info">
		              		<span>传感器名称：<span>江苏</span></span>
		              		<span>传&nbsp;感&nbsp;器&nbsp;ID：<span>JACS001</span></span>
		              		<span>区&nbsp;域&nbsp;位&nbsp;置：<span>江苏-昆山开发区</span></span>
		              		<span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：<span><i class="fa fa-circle clrr"></i>失联</span></span>
		              		<span>失&nbsp;联&nbsp;时&nbsp;间：<span>2019-06-27 14:03</span></span>
		           		</div>
	            	</div>
	            </li>
	             -->
	                             <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_13.png"/><span class="rsn">储罐报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_12.png"/><span class="rsn">高危工艺报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_9.png"/><span class="rsn">有毒气体报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_14.png"/><span class="rsn">视频监控报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
                               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_13.png"/><span class="rsn">储罐报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_12.png"/><span class="rsn">高危工艺报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_9.png"/><span class="rsn">有毒气体报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_14.png"/><span class="rsn">视频监控报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
	         </ul>
	     </div>
     </div>
     
     <!-- 厂区地图风险点 点击后滑出的slider 风险点详情 -->
     <div id="fxdxq_slider" class="f3">
		<div class="slider_tit f1">
			<i class="fa fa-block clrq"></i>风险点详情
			<span class="slider_cls fn1">X</span>
		</div>
		<div class="min_tit f3">
			基本信息
		</div>
		<div class="fxd_t_info">
			<div class="t_info_h">
				<span>风险点名称：线切割</span><span>等级：<span style="color:yellow">&nbsp;黄</span></span>
			</div>
			<div class="t_info_b">
				<div class="pic_">
					<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
				</div>
				<div class="t_2">
					<div class="">场所/环节/部位
					</div>
					<div class="">易导致（风险）
					</div>
					<div class="">检查状态
					</div>
					<div class="">最近检查人
					</div>
				</div>
				<div class="t_3">
					<div class="">线切割
					</div>
					<div class="">触电,火灾
					</div>
					<div class="">待检查
					</div>
					<div class="">王庭伟&nbsp;&nbsp;&nbsp;&nbsp;2019-07-01
					</div>
				</div>
			</div>
		</div>
		
		<div class="tab_">
			<div id="qhxq_" class="chos">
				<div class="min_tit f3">
					隐患详情
				</div>
			</div>
			<div id="xjxq_" class="">
				<div class="min_tit f3">
					隐患详情
				</div>
			</div>
		</div>
		
		<div class="tab_ctt">
			<div id="qhxq_ctt">
				<div class="head">
					<div class="chos">
						<span class="f3">全部<span>15</span></span>
					</div>
					<div class="">
						<span class="f3">已过期<span class="clrr">4</span></span>
					</div>
					<div class="">
						<span class="f3">未过期<span class="">0</span></span>
					</div>
					<div class="">
						<span class="f3">待复查<span class="">0</span></span>
					</div>
				</div>
				<div class="left2_table">
		            <ul style="margin-right: 4px;">
		                <li class="list_ f3">
		                	<div class="lihd" style="margin-top: 5px;">
		                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/><span class="rsn f2">定期巡查</span>
		                		<span class="lilab">已过期</span>
		              		</div>
		              		<div class="libd">
		                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
		                 		<div class="info">
			                		<span>点位名称：<span>随手拍</span></span>
			                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
			                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
			                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
		              			</div>
		               		</div>
		               </li>
		                 <li class="list_ f3 bg">
		                	<div class="lihd" style="margin-top: 5px;">
		                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/><span class="rsn f2">定期巡查</span>
		                		<span class="lilab">已过期</span>
		              		</div>
		              		<div class="libd">
		                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
		                 		<div class="info">
			                		<span>点位名称：<span>随手拍</span></span>
			                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
			                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
			                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
		              			</div>
		               		</div>
		               </li>
		               <li class="list_ f3">
		                	<div class="lihd" style="margin-top: 5px;">
		                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/><span class="rsn f2">定期巡查</span>
		                		<span class="lilab">已过期</span>
		              		</div>
		              		<div class="libd">
		                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
		                 		<div class="info">
			                		<span>点位名称：<span>随手拍</span></span>
			                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
			                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
			                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
		              			</div>
		               		</div>
		               </li>
		               <li class="list_ f3 bg">
		                	<div class="lihd" style="margin-top: 5px;">
		                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/><span class="rsn f2">定期巡查</span>
		                		<span class="lilab">已过期</span>
		              		</div>
		              		<div class="libd">
		                 		<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
		                 		<div class="info">
			                		<span>点位名称：<span>随手拍</span></span>
			                		<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
			                		<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-13</span></span></span>
			                		<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span>
		              			</div>
		               		</div>
		               </li>
		            </ul>
		         </div>
			</div>
			
			<div id="xjxq_ctt">
				<div class="head">
					<div class="chos">
						<span class="f3">全部<span>15</span></span>
					</div>
					<div class="">
						<span class="f3">正常<span class="clrr">15</span></span>
					</div>
					<div class="">
						<span class="f3">异常<span class="">0</span></span>
					</div>
				</div>
				<div class="left2_table">
			         <ul style="margin-right: 4px;">
			         	<li class="list_ f3">
			             	<div class="lihd" style="margin-top: 5px;">
			             		<span class="lilab">已过期</span>
			           		</div>
			           		<div class="libd">
			              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/ic1_.png) no-repeat;background-size:100%;background-position:center;"></div>
				              	<div class="info">
				              		<span>设备名称：<span>行车</span></span>
				              		<span>出厂编号：<span>JACS001</span></span>
				              		<span>负&nbsp;&nbsp;责&nbsp;人：<span>张甜甜</span></span>
				              		<span>有效期至：<span>2020-04-09</span></span>
				           		</div>
			            	</div>
			            </li>
			            <li class="list_ f3">
			             	<div class="lihd" style="margin-top: 5px;">
			             		<span class="lilab" style="background:green">未过期</span>
			           		</div>
			           		<div class="libd">
			              		<div class="pic" style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
				              	<div class="info">
				              		<span>设备名称：<span>行车</span></span>
				              		<span>出厂编号：<span>JACS001</span></span>
				              		<span>负&nbsp;&nbsp;责&nbsp;人：<span>张甜甜</span></span>
				              		<span>有效期至：<span>2020-04-09</span></span>
				           		</div>
			            	</div>
			            </li>
			         </ul>
			     </div>
			</div>
		</div>
     </div>
</div>
<div class="bnt" style="background: url(${ctx}/static/model/vd2/img/header_bg.png);background-size:100% 100%;background-position:center;">
  <div class="topbnt_left fl">
   <ul>
      <li class="weather"><a href="#">2019年7月4日&nbsp;&nbsp;10:01&nbsp;&nbsp;雷阵雨</a></li>
      <!--<li><a href="${ctx}/a/vd_gov">政府端</a></li>
       <li class="active"><a href="${ctx}/a/vd_com">企业端</a></li>
      <li><a href="activity.html">活动情况</a></li> -->
   </ul>
  </div>
  <h1 class="tith1 fl">昆山市浩旭车料有限公司</h1>
  <div class=" fr topbnt_right">
    <ul>
       <li><a onclick="closewindow()">返回</a></li>
       <!-- <li><a href="traffic.html">交通</a></li>
       <li><a href="index.html">舆情</a></li> -->
    </ul>
   
  </div>
</div>
<!-- bnt end -->
<div class="left1"><!--
    <div class="aleftboxttop">
    	<h2 class="tith2 ptm1">企业信息总览</h2>
	   <div class="lefttoday_tit" style=" height:13%">
	   		<h3 class="f1" style="margin-top: 2%;">昆山市浩旭车料有限公司</h3>
	   </div>
	   <div class="userInfo f3">
	   		<p>安全管理员：<span>王庭伟</span></p>
	   		<p>联&nbsp;系&nbsp;方&nbsp;式：<span>181****8870</span></p>
	   		<div id="xfq" class="xfq"></div>
	   </div>
	   <div class="lefttoday_number f3">
	   		<div id="aqry_" class="v_btn">
	   			<img src="${ctx}/static/model/vd2/img/comInfo1.png">
	   			<div>
		   			<span class="fn2 clr_lg">54</span>
		   			<span>安全人员</span>
		   		</div>
	   		</div>
	   		<div id="fxd_" class="v_btn">
	   			<img src="${ctx}/static/model/vd2/img/ic_2.png">
	   			<div>
		   			<span class="fn2 clr_lg">44</span>
		   			<span>风险点数</span>
		   		</div>
	   		</div>
	   		<div id="tzsb_" class="v_btn">
	   			<img src="${ctx}/static/model/vd2/img/ic_3.png">
	   			<div>
		   			<span class="fn2 clr_lg">24</span>
		   			<span>特种设备</span>
		   		</div>
	   		</div>
	   		<div id="dqyh_" class="v_btn-">
	   			<img src="${ctx}/static/model/vd2/img/ic_4.png">
	   			<div>
		   			<span class="fn2 clr_lg">0</span>
		   			<span>当前隐患</span>
		   		</div>
	   		</div>
	    </div>
    </div>
    -->
    <!--<div class="bwMb2"></div> -->
    <div class="aleftboxtmidd" style="height: 34.8%;">
      <h2 class="tith2">接入企业</h2>
      <div id="aleftboxtmidd" class="aleftboxtmiddcont"></div>
  </div>
  <!--<div class="bwMb2"></div> -->
  <div class="arightboxtop"><h2 class="tith2 ptm">当前隐患</h2>
         <div class="head">
			<div class="chos">
				<span class="f3">一级</span>
			</div>
			<div class="">
				<span class="f3">二级</span>
			</div>
			<div class="">
				<span class="f3">三级</span>
			</div>
			<div class="">
				<span class="f3">四级</span>
			</div>
		</div>
         <div class="left2_table" style="height: 80%;">
            <ul style="margin-right: 4px;">
                <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li><li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               
               <li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li><li class="list_ f3">
                	<div class="lihd" style="margin-top: 5px;display:none">
                		<span class="lilab">一级</span>
              		</div>
              		<div class="libd" style=" margin-bottom: 5px;">
                 		<div class="pic" style="width: 40px;height: 40px;background: url(${ctx}/static/model/vd2/img/comii.png) no-repeat;background-size:100%;background-position:center;"></div>
                 		<div class="info" style="height: 50px;">
	                		<span>企业名称：<span>xxxxxxxxxx</span></span>
	                		<span>监控系统：<span>有</span></span>
              			</div>
               		</div>
               </li>
               
            </ul>
         </div>
      </div>
	</div>
	
	<!--  left1 end -->
     <div class="mrbox_topmidd">
         <div class="amiddboxttop" style="height: 100%;">
             <h2 class="tith2 pt2">风险点一览</h2>
	             <!--<div class="tab_">
					<div id="cqmnt_" class="chos">
						<div class="min_tit f3">
							厂区模拟图（开发中）
						</div>
					</div>
					<div id="fxdlb_" class="">
						<div class="min_tit f3">
							风险点列表
						</div>
					</div>
				</div>-->
				
				<div class="tab_ctt" style="height: 94%;margin: 0 24px;">
					<div id="cqmnt_ctt" class="map__">
						<div id="mapChart" style="width:100%;height:100%;display: inline-block;margin:0%;"></div>
					</div>
					<div id="fxdlb_ctt" style="display:none">
						<div class="list_h f2-5" style="">
							<div class="w25">风险点名称</div>
				            <div class="w20">风险类别</div>
				            <div class="w20">行业类别</div>
				            <div class="w20">事故类型</div>
				            <div class="w15">风险分级</div>
			            </div>
			            <div class="list_b f3" style="">
			            	<div id="fxdxq_" class="list_li">
			            		<div class="w25 line" title="燃气调压站燃气调压站燃气调压站燃气调压站">燃气调压站燃气调压站燃气调压站燃气调压站</div>
					            <div class="w20 line" title="">设备设施</div>
					            <div class="w20 line" title="">公共部分</div>
					            <div class="w20 line" title="火灾；其它爆炸火灾；其它爆炸">火灾；其它爆炸火灾；其它爆炸</div>
					            <div class="w15"><div class="bgr">红</div></div>
			            	</div>
			            	<div id="fxdxq_" class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgo">橙</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgy clrd">黄</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgb">蓝</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25 line" title="燃气调压站燃气调压站燃气调压站燃气调压站">燃气调压站燃气调压站燃气调压站燃气调压站</div>
					            <div class="w20 line" title="">设备设施</div>
					            <div class="w20 line" title="">公共部分</div>
					            <div class="w20 line" title="火灾；其它爆炸火灾；其它爆炸">火灾；其它爆炸火灾；其它爆炸</div>
					            <div class="w15"><div class="bgr">红</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgo">橙</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgy clrd">黄</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgb">蓝</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25 line" title="燃气调压站燃气调压站燃气调压站燃气调压站">燃气调压站燃气调压站燃气调压站燃气调压站</div>
					            <div class="w20 line" title="">设备设施</div>
					            <div class="w20 line" title="">公共部分</div>
					            <div class="w20 line" title="火灾；其它爆炸火灾；其它爆炸">火灾；其它爆炸火灾；其它爆炸</div>
					            <div class="w15"><div class="bgr">红</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgo">橙</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgy clrd">黄</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgb">蓝</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25 line" title="燃气调压站燃气调压站燃气调压站燃气调压站">燃气调压站燃气调压站燃气调压站燃气调压站</div>
					            <div class="w20 line" title="">设备设施</div>
					            <div class="w20 line" title="">公共部分</div>
					            <div class="w20 line" title="火灾；其它爆炸火灾；其它爆炸">火灾；其它爆炸火灾；其它爆炸</div>
					            <div class="w15"><div class="bgr">红</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgo">橙</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgy clrd">黄</div></div>
			            	</div>
			            	<div class="list_li">
			            		<div class="w25">燃气调压站</div>
					            <div class="w20">设备设施</div>
					            <div class="w20">公共部分</div>
					            <div class="w20">火灾；其它爆炸</div>
					            <div class="w15"><div class="bgb">蓝</div></div>
			            	</div>
			            </div>
					</div>
				</div>
           </div>

		<!--
           <div class="amidd_bott">
             <div class="amiddboxtbott1 fl" >
               <h2 class="tith2 ptm__">单位巡查</h2>
               <div id="arightboxbott" class="amiddboxtbott1content" ></div>
             </div>
        </div>-->
    </div>

     <div class="mrbox_top_right">
       <!--<div class="bwMb2"></div> -->
       <div class="arightboxbott"><h2 class="tith2 ">动态监测</h2>
         <div class="lefttoday_tit height"><p class="fl">状态：已更新</p><p class="fr">时间段：2019-10-13 至 2019-10-14</p></div>
         <!--<div id="aleftboxtbott" class="arightboxbottcont" style=""></div> -->
         	    <div class="lefttoday_number lb_h f3">
	   		<div id="aqry_">
	   			<img src="${ctx}/static/model/vd2/img/ic_13.png">
	   			<div>
		   			<span class="fn2 clr_lg">74</span>
		   			<span>储罐信息</span>
		   		</div>
	   		</div>
	   		<div id="fxd_">
	   			<img src="${ctx}/static/model/vd2/img/ic_14.png">
	   			<div>
		   			<span class="fn2 clr_lg">44</span>
		   			<span>视频监控</span>
		   		</div>
	   		</div>
	   		<div id="tzsb_">
	   			<img src="${ctx}/static/model/vd2/img/ic_9.png">
	   			<div>
		   			<span class="fn2 clr_lg">24</span>
		   			<span>有毒气体</span>
		   		</div>
	   		</div>
	   		<div id="dqyh_">
	   			<img src="${ctx}/static/model/vd2/img/ic_12.png">
	   			<div>
		   			<span class="fn2 clr_lg">0</span>
		   			<span>高危工艺</span>
		   		</div>
		   		
	   		</div>
	    </div>
	    
         <div class="lefttoday_number lb_h f3" style="display:none">
         	<div id="slider-wrap">
			  <ul id="slider__">
			  <!-- 
				 <li data-color="#1abc9c">
					<div>
						<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_15.png">
				   			<div>
					   			<span class="fn1 clr_lg">54</span>
					   			<span>独立烟感</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_10.png">
				   			<div>
					   			<span class="fn1 clr_lg">84</span>
					   			<span>电气火灾</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_12.png">
				   			<div>
					   			<span class="fn1 clr_lg">44</span>
					   			<span>废水信息</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_16.png">
				   			<div>
					   			<span class="fn1 clr_lg">84</span>
					   			<span>消防主机</span>
					   		</div>
				   		</div>
					</div>       
					<i class="fa fa-image"></i>
				 </li>
				  -->
				 <li data-color="#3498db">
					<div>
						<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_13.png">
				   			<div>
					   			<span class="fn1 clr_lg">24</span>
					   			<span>储罐信息</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_14.png">
				   			<div>
					   			<span class="fn1 clr_lg">44</span>
					   			<span>视频监控</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_9.png">
				   			<div>
					   			<span class="fn1 clr_lg">54</span>
					   			<span>有毒气体</span>
					   		</div>
				   		</div>
				   		<div id="pub_" class="v_btn">
				   			<img src="${ctx}/static/model/vd2/img/ic_12.png">
				   			<div>
					   			<span class="fn1 clr_lg">24</span>
					   			<span>高危工艺</span>
					   		</div>
				   		</div>
					</div>
					<i class="fa fa-gears"></i>
				 </li>
			  </ul>
			   <!--controls-->
			  <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
			  <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
			  <div id="counter"></div>
			  <div id="pagination-wrap">
				<ul>
				</ul>
			  </div>    
			</div>
       </div>
     </div>
     
     <div class="arightboxtop"><h2 class="tith2 ptm">实时警报</h2>
         <div class="lefttoday_tit" style="padding-top: 0;"><p class="fl" style="display:none">状态：已更新</p><p id="pub_" class="fr v_btn" style="margin-top: -6px;padding: 6px 4px 6px 100px;color: #e8e8e8;background: -webkit-linear-gradient(left, #000e2f00 , #2e8291);cursor: pointer;">历史警报 ></p></div>
         <div class="search_inp">
         	<form>
         		<input placeholder="请输入企业名称"><button type="button" style="background: #3190c2;">搜索</button>
         	</form>
         </div>
         <div class="left2_table" style="height: 76%;">
            <ul style="margin-right: 4px;">
                <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_13.png"/><span class="rsn">储罐报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_12.png"/><span class="rsn">高危工艺报警</span>
                		<span class="lilab handle">已处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_9.png"/><span class="rsn">有毒气体报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
               <li id="warnning_" class="list_ f3 v_btn2">
                	<div class="lihd" style="margin-top: 5px;">
                		<img class="_ic" src="${ctx}/static/model/vd2/img/ic_14.png"/><span class="rsn">视频监控报警</span>
                		<span class="lilab">未处理</span>
              		</div>
              		<div class="libd">
                 		<!--<div class="pic" style="background:#00075b url(${ctx}/static/model/vd2/img/m1.png) no-repeat;background-size:100%;background-position:center;"></div> -->
                 		<div class="info" style="height: 80px;">
	                		<span>企业名称：<span>xxxxxxx</span></span>
	                		<!--<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span> -->
	                		<span>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：<span>2019-10-13</span></span>
	                		<!--<span>计划整改：<span>达莎博<span class="clrr">2019-10-13</span></span></span> -->
	                		<span>单位地址：<span>常州市武进区创研港2号楼</span></span>
              			</div>
               		</div>
               </li>
            </ul>
         </div>
       </div>
  </div>
</div>
     

<script type="text/javascript">
function  closewindow() {
	window.close();
}
/*
var xfqChart = echarts.init(document.getElementById('xfq'));
xfqoption = {
	    series: [{
	    	type: 'liquidFill',
	        radius: '123%',
	        data: [0.88],
	        label: {
			    normal:{
			    	position: ['0%', '0%'],
		            formatter: function() {
		                return '88';
		            },
		            textStyle:{
		                fontSize:10
		            }
		        }
	        }
	    }]
	};
xfqChart.setOption(xfqoption);
*/

var fxdchart = echarts.init(document.getElementById('aleftboxtmidd'));
  fxdoption = {
   tooltip: {
formatter: "{a} <br/>{b} : {c}个"
},
  color:['red','orange', 'yellow', '#5578cf'],
    backgroundColor: 'rgba(1,202,217,.0)',
    grid: {
              left:20,
              right:20,
              top:0,
              bottom:20
            },
            series : [
     {
         name: '实时风险点',
         type: 'pie',
         radius : '60%',
         center: ['50%', '50%'],
         data:[
           {value:335, name:'红色风险点'},
           {value:310, name:'橙色风险点'},
           {value:234, name:'黄色风险点'},
           {value:135, name:'蓝色风险点'}
         ],
         itemStyle: {
             emphasis: {
                 shadowBlur: 10,
                 shadowOffsetX: 0,
                 shadowColor: 'rgba(0, 0, 0, 0.5)'
             }
         }
     }
 ]
};
fxdchart.setOption(fxdoption);
// echart点击事件
fxdchart.on("click",function(param) {
	if(param.dataIndex == 0)
		showOrhide_slider(null,"echart_fxd_r");
	if(param.dataIndex == 1)
		showOrhide_slider(null,"echart_fxd_o");
	if(param.dataIndex == 2)
		showOrhide_slider(null,"echart_fxd_y");
	if(param.dataIndex == 3)
		showOrhide_slider(null,"echart_fxd_b");
	var oEvent = param.event || event; 
    stopBubble(oEvent); 
});
   
/*     
var xjchart = echarts.init(document.getElementById('arightboxbott'));
xjcoption = {
   color:['#7de494','#7fd7b1', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
     backgroundColor: 'rgba(1,202,217,.0)',

     grid: {
         left: '5%',
         right: '8%',
         bottom: '7%',
         top:'8%',
         containLabel: true
     },
     toolbox: {
         feature: {
             saveAsImage: {}
         }
     },
     xAxis: {
         type: 'category',
         boundaryGap: false,
         axisLine:{
           lineStyle:{
             color:'rgba(255,255,255,.2)'
           }
         },
         splitLine:{
           lineStyle:{
             color:'rgba(255,255,255,.1)'
           }
         },
         axisLabel:{
             color:"rgba(255,255,255,.7)"
         },
         data: ['6-08','6-09','6-10','6-11','6-12','6-13','6-14']
     },
     yAxis: {
         type: 'value',
         axisLine:{
           lineStyle:{
             color:'rgba(255,255,255,.2)'
           }
         },
         splitLine:{
           lineStyle:{
             color:'rgba(255,255,255,.1)'
           }
         },
         axisLabel:{
             color:"rgba(255,255,255,.7)"
         },
     },
     series: [
         {
             name:'2014年',
             type:'line',
             stack: '总量',
               areaStyle: {normal: {}},
             data:[120, 132, 101, 134, 90, 230, 210]
         }

      ]
   };
xjchart.setOption(xjcoption);
*/

///轮播		加载地图
$(function() { 		
    var ctx = '${ctx}';
    initMap("mapChart");
    getQyfbJson();
    var mapStyle ={
        features: ["road","building","water","land"],//隐藏地图上的"poi",
        style : 'midnight',
    };
    map.setMapStyle(mapStyle);


    $.ajax({
        url :  '${ctx}/syshome/kshsj',
        type : "POST",
        success : function(data) {
            var tjdata=eval(data);
            for (var key in tjdata) {
                for ( var i in tjdata[key] ){
                    $("#"+i).text(tjdata[key][i]);
                }
            }
            loadEcharts(tjdata[0]);
            loadEcharts2(tjdata[0]);
        }
    });

    $.ajax({
        url :  '${ctx}/syshome/yhpcs',
        type : "POST",
        success : function(data) {
            loadEcharts3(data);
        }
    });

    $.ajax({
        url :  '${ctx}/syshome/yhzgs',
        type : "POST",
        success : function(data) {
            loadEcharts4(data);
        }
    });
})

var select1 = $("#select1");
//初始化插件
select1.goSelectInput({
    height: 30,
    width:100
});
var select2 = $("#select2");
//初始化插件
select2.goSelectInput({
  height: 30,
  width:100
});
//**获取两者的值
$("#get").click(function(){
    alert("全部的select的值为" + select1.val() + ",选中文本为'" + select1.find("option:selected").text() + "'");
});


//current position
var pos = 0;
//number of slides
var totalSlides = $('#slider-wrap ul li').length;

window.onload = function(){
	// 设置单页面相应模块的高度-设置风险点列表高度
	setIndexModulesHeight();
	
	$(".v_btn,.list_li,[class^=fxd_ic_]").click(function(ev){
		hideSlider_l();
		showOrhide_slider($(this),"v_btn");
	    // 阻止事件传递
	    var oEvent = ev || event; 
        //oEvent.cancelBubble = true;//高版本浏览器 
        stopBubble(oEvent); 
        //在低版本的chrome和firefox浏览器中需要兼容性处理 
        //高版本chrome和firefox浏览器直接使用上面这行代码即可 
	})
	
	$(".userInfo .xfq").click(function(ev){
		hideSlider_r();
		// 操作左侧slider
		if($(".slider_l").hasClass("show_")){
			$(".slider_l").animate({left:"-946px"},'easing',function() {
				$(".slider_l").removeClass("show_");
			});
		}else{
			$(".slider_l").addClass("show_");
			$(".slider_l").animate({left:"0px"},'easing');
			
			setSliderTableHeight();
			initSlider_l_data();
			if(zschart2)
		   		zschart2.resize();
		}
		
		// 阻止事件传递
	    var oEvent = ev || event; 
        //oEvent.cancelBubble = true;//高版本浏览器 
        stopBubble(oEvent); 
        //在低版本的chrome和firefox浏览器中需要兼容性处理 
        //高版本chrome和firefox浏览器直接使用上面这行代码即可 
	})
	
	$(".slider_").click(function(ev){
		var oEvent = ev || event; 
        stopBubble(oEvent);
	})
	
	// 点击空白处 滑出slider
	$(".slider_cls,.wpbox").click(function(ev){
		hideSlider_l();
		hideSlider_r();
	    var oEvent = ev || event; 
        stopBubble(oEvent); 
	})
	
/*
 * 特种设备 slider
 */
$(".slider_ .head>div").click(function(){
	$(this).addClass("chos").siblings().removeClass("chos");
})

/*
 * 特种设备 slider
 */
$(".arightboxtop .head>div").click(function(){
	$(this).addClass("chos").siblings().removeClass("chos");
})

/*
 * 风险点详情 slider
 */
$(".slider_ .tab_>div").click(function(){
	$(this).addClass("chos").siblings().removeClass("chos");
	// 根据点击按钮， 获取相应内容
	if($(this).attr("id")!=null && $(this).attr("id")!="undefined"){
		var ctt_id = $(this).attr("id") + "ctt";
		$("#"+ctt_id).show().siblings().hide();
	}
})

/*
 * 风险点一览
 */
$(".amiddboxttop .tab_>div").click(function(ev){
	$(this).addClass("chos").siblings().removeClass("chos");
	// 根据点击按钮， 获取相应内容
	if($(this).attr("id")!=null && $(this).attr("id")!="undefined"){
		var ctt_id = $(this).attr("id") + "ctt";
		$("#"+ctt_id).show().siblings().hide();
		if(ctt_id == "fxdlb_ctt")
			$("#fxdlb_ctt .list_b").height($("#fxdlb_ctt").height()-$("#fxdlb_ctt .list_h").height());
	}
})

/*
 * 风险点一览
 */
$("#cqmnt_ctt .vw_ic, .videoWindow .vw_cls").click(function(ev){
	// 根据点击按钮， 获取相应内容
	if($(".videoWindow").hasClass("show_")){
		$(".videoWindow").hide();
		$(".videoWindow").removeClass("show_");
	}else{
		$(".videoWindow").show();
		$(".videoWindow").addClass("show_");
	}
})


/*****************
BUILD THE SLIDER
*****************/
//set width to be 'x' times the number of slides
$('#slider-wrap ul#slider__').width($('#slider-wrap').width()*totalSlides);

//next slide 	
$('#next').click(function(){
	lb_slideRight();
});

//previous slide
$('#previous').click(function(){
	lb_slideRight();
});

/*************************
//*> OPTIONAL SETTINGS
************************/
//automatic slider
var autoSlider = setInterval(lb_slideRight, 8000);

//for each slide 
$.each($('#slider-wrap ul li'), function() { 
  //set its color
  //var c = $(this).attr("data-color");
  //$(this).css("background",c);
  
  //create a pagination
  var li = document.createElement('li');
  $('#pagination-wrap ul').append(li);	   
});

//counter
lb_countSlides();

//pagination
pagination();

$('#slider-wrap').hover(
	function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
	function(){ $(this).removeClass('active'); autoSlider = setInterval(lb_slideRight, 8000); }
);

};
/***********
 SLIDE LEFT
************/
function lb_slideRight(){
	pos--;
	if(pos==-1){ pos = totalSlides-1; }
	$('#slider-wrap ul#slider__').css('left', -($('#slider-wrap').width()*pos)); 	
	
	//*> optional
	lb_countSlides();
	pagination();
}
/************
SLIDE RIGHT
*************/
function lb_slideRight(){
	pos++;
	if(pos==totalSlides){ pos = 0; }
	$('#slider-wrap ul#slider__').css('left', -($('#slider-wrap').width()*pos)); 
	
	//*> optional 
	lb_countSlides();
	pagination();
}
/************************
//*> OPTIONAL SETTINGS
************************/
function lb_countSlides(){
	//$('#counter').html(pos+1 + ' / ' + totalSlides);
}
function pagination(){
	$('#pagination-wrap ul li').removeClass('active');
	$('#pagination-wrap ul li:eq('+pos+')').addClass('active');
}


/*
 * 显隐 初始化 slider
 */
function showOrhide_slider($this,eventType){
	if($(".slider_r").hasClass("show_")){
		$(".slider_r").animate({right:"-473px"},'easing',function() {
			$(".slider_r").removeClass("show_");
		});
	}else{
		$(".slider_r").addClass("show_");
		$(".slider_r").animate({right:"0px"},'easing');
		
		// 根据点击按钮， 获取相应内容
		if(eventType == "v_btn"){
			// 根据点击按钮， 获取相应内容
			if($this.attr("id")!=null && $this.attr("id")!="undefined"){
				var slider_id = $this.attr("id") + "slider";
				$("#"+slider_id).show().siblings().hide();
				
				//设置滑出窗内的left2_table list高度
				setSliderTableHeight();
			}
		}else if(eventType.substring(0,10) == "echart_fxd"){
			$("#fxd_slider").show().siblings().hide();var aa = "clr" + eventType.substring(11,12);
			$(".slider_ .head>div."+aa).trigger("click");
		}
	}
}

//隐藏左侧slider_l
function hideSlider_l() { 
	if($(".slider_l").hasClass("show_")){
		$(".slider_l").animate({left:"-946px"},'easing',function() {
			$(".slider_l").removeClass("show_");
		});
	}
}
//隐藏右侧slider_r
function hideSlider_r() { 
	if($(".slider_r").hasClass("show_")){
		$(".slider_r").animate({right:"-473px"},'easing',function() {
			$(".slider_r").removeClass("show_");
		});
	}
}
//阻止冒泡事件的兼容性处理 
function stopBubble(e) { 
   if(e && e.stopPropagation) { //非IE 
    	e.stopPropagation(); 
  	} else { //IE 
    	window.event.cancelBubble = true; 
  	} 
}

//设置slider中滑出窗内的 left2_table list高度
function setIndexModulesHeight() {
	$("#fxdlb_ctt .list_b").height($("#fxdlb_ctt").height()-$("#fxdlb_ctt .list_h").height());
	//设置轮播标点位置
	$("#slider-wrap ul#slider__").width($("#slider-wrap").width()*3);
	$("#slider-wrap ul#slider__ li").width($("#slider-wrap").width());
    var lbbdMT = ($("#slider__").height()-26)+"px";
	$("#pagination-wrap").css("margin-top",lbbdMT);
} 

// 设置slider中滑出窗内的 left2_table list高度
function setSliderTableHeight() { 
	$("#fxd_slider .left2_table").height($(".slider_").outerHeight(true)-$("#fxd_slider .slider_tit").outerHeight(true)-$("#fxd_slider .head").outerHeight(true)-15);
	$("#tzsb_slider .left2_table").height($(".slider_").outerHeight(true)-$("#tzsb_slider .slider_tit").outerHeight(true)-$("#tzsb_slider .head").outerHeight(true)-15);
    $("#fxdxq_slider .left2_table").height($(".slider_").outerHeight(true)-$("#fxdxq_slider .slider_tit").outerHeight(true)-$("#fxdxq_slider>.min_tit").outerHeight(true)-$("#fxdxq_slider>.fxd_t_info").outerHeight(true)-$("#fxdxq_slider>.tab_").outerHeight(true)-$("#fxdxq_slider .tab_ctt .head").outerHeight(true)-15);
    $("#pub_slider .left2_table").height($(".slider_").outerHeight(true)-$("#pub_slider .slider_tit").outerHeight(true)-$("#pub_slider .head").outerHeight(true)-15);
    $("#aqzs_slider .left2_table").height($(".slider_").outerHeight(true)-$("#aqzs_slider .slider_tit").outerHeight(true)-$("#aqzs_slider .aqzs_b_r .head").outerHeight(true)-$("#aqzs_slider .chosText").outerHeight(true)-15);
    $("#aqzs_slider .aqzs_b_l .tab_ctt").height($(".slider_").outerHeight(true)-$("#aqzs_slider .slider_tit").outerHeight(true)-$("#aqzs_slider .aqzs_b_l .min_tit").outerHeight(true)-$("#aqzs_slider .aqzs_b_l .fxd_t_info").outerHeight(true)-$("#aqzs_slider .aqzs_b_l .tab_").outerHeight(true)-15);
    $("#aqry_slider .left2_table").height($(".slider_").outerHeight(true)-$("#aqry_slider .slider_tit").outerHeight(true)-$("#aqry_slider .head").outerHeight(true)-15);
} 

var zschart2;
// 加载左侧slider数据
function initSlider_l_data() { 
	
	// 渲染echart
	zschart2 = echarts.init(document.getElementById("zschart2"), "vintage");
	zschart2.setOption({
		backgroundColor: '#ffffff00',
	    legend: {},
	    tooltip: {},
	    dataset: {
	        source: [
	            ['隐患排查', 31],
	            ['安全巡检', 90],
	            ['动态监测', 34],
	            ['安全档案', 45]
	        ]
	    },
	    grid: {
	        left: '3%',
	        right: '5%',
	        top: '3%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	    	type: 'category',
        	axisLine: {
	            lineStyle: {
	                color: '#fff'
	            }
	        },
	        splitLine: {
	            show:false,
	            lineStyle: {
	                color: '#fff'
	            }
	        }
	    },
	    yAxis: {
	    	type : 'value',
        	axisLine: {
	            lineStyle: {
	                color: '#fff'
	            }
	        },
	        splitLine: {
	            show: true,
	            lineStyle: {
	                color: '#ffffff70'
	            }
	        }
		},
	    series: [{
		        type: 'bar',
		        itemStyle: {
		           normal:{  
		           color: function (params){
		               var colorList = ['#ff6864','#f19ec2','#f8e19a','#00b7ee'];
		               return colorList[params.dataIndex];
		           }
		       },
		    }
	    }]
	})	
}

window.onresize = function () {
	//设置滑出窗内list高度
   	setSliderTableHeight()
  	//设置单页面相应模块的高度
   	setIndexModulesHeight()
	// 调用方法重置轮播left
   	lb_slideRight();
	
   	//xfqChart.resize();
   	//xjchart.resize();
   	fxdchart.resize();
   	if(zschart2)
   		zschart2.resize();
}

</script>
</body>
</html>
