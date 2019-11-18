<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!doctype html>
<head>
	<meta charset="utf-8">
	<title>企业端</title>
	<link href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css"  type="text/css" media="all"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-select2/htools.select.skin.css">
	<link href="${ctx}/static/model/vd2/css/style.css"  rel="stylesheet" type="text/css" media="all"/>
	<script src="${ctx}/static/model/vd2/js/echarts.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/static/echarts/theme/vintage.js"></script>
	<script type="text/javascript" src="${ctx}/static/echarts/chart/echarts-liquidfill.min.js"></script>
	<script src="${ctx}/static/model/vd2/js/particles.min.js"></script>
	<script src="${ctx}/static/jquery/jquery-3.3.1.min.js"></script>
	<script src="${ctx}/static/jquery-select2/jquery.htools.select.js"></script>
	<style>
		body{
			-moz-user-select: none;
			-webkit-user-select: none;
			-ms-user-select: none;
			-khtml-user-select: none;
			user-select: none;
		}
		.slider_{
			height: 100%;
			position: fixed;
			z-index: 990;
			background: #002d75;
			box-shadow: 0 0 10px 0 #000931;
		}

		#lunbobox {
			width: 100%;
			height: 100%;
			position:relative;
		}
		.lunbo {
			width:100%;
			height:100%;
		}
		.lunbo>div{
			width:100%;
			height:100%;
			display: flex;
			flex-wrap: wrap;
			flex-shrink: 0;
		}
		#lunbobox ul {
			position:absolute;
			bottom:10px;
			right:calc(50% - 26px);
			z-index:5;
		}
		#lunbobox ul li {
			cursor:pointer;
			width:10px;
			height:4px;
			border:1px solid #017097;
			float:left;
			list-style:none;
			background:#017097;
			text-align:center;
			margin:0px 5px 0px 0px;
		}
		#toleft {
			display:none;
			width:30px;
			height:100px;
			font-size:40px;
			line-height:100px;
			text-align:center;
			color:#f4f4f4;
			position:absolute;
			top:0px;
			left:0px;
			cursor:pointer;
			z-index:500;
			opacity:0.4;
		}
		#toright {
			display:none;
			width:30px;
			height:100px;
			font-size:40px;
			line-height:100px;
			text-align:center;
			color:#f4f4f4;
			position:absolute;
			top:0px;
			right:0px;
			cursor:pointer;
			z-index:500;
			opacity:0.4;
		}

		@media screen and (max-width: 10000px){
			.slider_l{
				left: -946px;
				width: 946px;
				display:none
			}
			.slider_r{
				right:-473px;
				width: 473px;
				display:none
			}
			.f1 {
				font-size: 18px;
			}
			.f2 {
				font-size: 16px;
			}
			.f2-5 {
				font-size: 15px;
			}
			.f3 {
				font-size: 14px;
			}
			.fn1{
				font-size: 22px;
				font-weight:600;
			}
			.fn2{
				font-size: 20px;
				font-weight:600;
			}
			.lefttoday_number>div>img,.lefttoday_number #slider__ li>div>div>img{
				width:50px;
				height:50px;
				margin-right:12px;
			}
			.aleftboxttop .lefttoday_number>div>img{
				width:40px;
				height:40px;
				margin-right:10px;
			}
			.xfq{
				width: 80px;
				height: 80px;
				background: white;
				position: absolute;
				left: 17%;
				margin-top: -60px;
				border-radius: 50%;
				box-shadow: rgb(215, 237, 255) 0px 0px 2.5em;
				overflow: hidden;
			}
			.pt2{padding-top: 1.4%; }
			.list_ .libd .pic{
				width: 94px;
				height: 124px;
				margin-right: 16px;
				flex-shrink: 0;
			}
			.list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 124px;
			}
			.list_ .lihd .lilab {
				position: absolute;
				right: 10px;
				width: 14px;
				background: red;
				padding: 2px 7px 6px;
				border-radius: 0 0 13px 13px;
				line-height: 17px;
				margin-top: -15px;
				font-weight: 600;
			}
			.list_ .lihd .rsn{
				margin-left: 44px;
			}
			.list_ .lihd ._ic{
				width: 28px;
				position: absolute;
				margin: -4px 4px 4px;
			}
			.ptm{
				padding-top: 3%;
			}
			.ptm1{
				padding-top: 2.6%;
			}
			.ptm__{
				padding-top: 0.9%;
			}
			#fxd_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 90px;
			}
			#fxd_slider .list_ .lihd .lilab {
				background: red;
				margin-top: -17px;
			}
			#tzsb_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 100px;
			}
			#pub_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 140px;
			}
			.tith1{ width: 45.3%; text-align: center; padding-top: 16px; font-weight: bold; letter-spacing:8px; font-size: 32px;}
			.t_info_b .pic_ .pic{width: 110px;height:140px;margin: 10px;}
			#fxdxq_slider #qhxq_ctt .head>div,#aqzs_slider #qhxq_ctt .head>div{width:94px;}
			.slider_ .tab_>div,.amiddboxttop .tab_>div{padding: 10px 0px 10px 10px;}
			.amiddboxttop .tab_{margin: 1% 1% 0;}
			#cqmnt_ctt img.ic_{width: 26px;height: 26px;}
			#cqmnt_ctt .btmsth>div>img{width: 22px;height: 22px;margin-right: 12px;}
			#fxdlb_ctt .list_b>div.list_li>div{padding: 6px;line-height: 24px;border-left: 1px solid #638192;}

			.aqzs_b_l .tab_{
				margin: 14% 0 10%;
			}
			.aqzs_b_l .tab_ .capChart{
				padding: 45px;
				width: 70px;
				height: 70px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_l{
				margin: 25px 0 0 -120px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_b{
				margin: 128px 0 0 8px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_r{
				margin: 25px 0 0 133px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_t{
				margin: -81px 0 0 8px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_l{
				width: 20px;
				height: 20px;
				margin: 25px 0 0 -56px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_b{
				width: 20px;
				height: 20px;
				margin: 104px 0 0 25px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_r{
				width: 20px;
				height: 20px;
				margin: 25px 0 0 104px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_t{
				width: 20px;
				height: 20px;
				margin: -56px 0 0 25px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_ .fn1{padding-top: 14px;line-height: 20px;}

			.videoWindow{
				width:760px;
				height:560px;
				margin-left: calc(50% - 380px);
				margin-top: 10%;
			}


		}
		@media screen and (max-width: 1366px){
			.slider_l{
				left: -840px;
				width: 840px;
				display:none
			}
			.slider_r{
				right:-420px;
				width: 420px;
				display:none
			}
			.f1 {
				font-size: 16px;
			}
			.f2 {
				font-size: 14px;
			}
			.f2-5 {
				font-size: 13px;
			}
			.f3 {
				font-size: 12px;
			}
			.fn1{
				font-size: 16px;
				font-weight:600;
			}
			.fn2{
				font-size: 14px;
				font-weight:600;
			}
			.lefttoday_number>div>img,.lefttoday_number #slider__ li>div>div>img{
				width:32px;
				height:32px;
				margin-right:8px;
			}
			.aleftboxttop .lefttoday_number>div>img{
				width:28px;
				height:28px;
				margin-right:8px;
			}
			.xfq{
				width: 64px;
				height: 64px;
				background: white;
				position: absolute;
				left: 17%;
				margin-top: -54px;
				border-radius: 50%;
				box-shadow: rgb(215, 237, 255) 0px 0px 2.5em;
				overflow: hidden;
			}
			.pt2{padding-top: 1.0%; }

			.list_ .libd .pic{
				width: 74px;
				height: 96px;
				margin-right: 10px;
				flex-shrink: 0;
			}
			.list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 80px;
			}
			.list_ .lihd .lilab {
				position: absolute;
				right: 10px;
				width: 14px;
				background: red;
				padding: 2px 5px 5px;
				border-radius: 0 0 11px 11px;
				line-height: 15px;
				margin-top: -13px;
			}
			.list_ .lihd .rsn{
				margin-left: 36px;
			}
			.list_ .lihd ._ic{
				width: 22px;
				position: absolute;
				margin: -4px 4px 4px;
			}
			.ptm{
				padding-top: 2.1%;
			}
			.ptm1{
				padding-top: 1.9%;
			}
			.ptm__{
				padding-top: 1%;
			}
			#fxd_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 60px;
			}
			#fxd_slider .list_ .lihd .lilab {
				background: red;
				margin-top: -14px;
			}
			#tzsb_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 80px;
			}
			#tzsb_slider .list_ .libd .info{
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				height: 110px;
			}
			.tith1{ width: 42.3%; text-align: center; padding-top: 16px; font-weight: bold; letter-spacing:8px; font-size:20px;}
			.t_info_b .pic_ .pic{width: 90px;height:110px;margin: 8px;}
			#fxdxq_slider #qhxq_ctt .head>div,#aqzs_slider #qhxq_ctt .head>div{width:86px;}
			.slider_ .tab_>div,.amiddboxttop .tab_>div{padding: 7px 0 7px 8px;}
			.amiddboxttop .tab_{margin: 0.7% 1% 0;}
			#cqmnt_ctt img.ic_{width: 22px;height: 22px;}
			#cqmnt_ctt .btmsth>div>img{width:18px;height: 18px;margin-right: 10px;}
			#fxdlb_ctt .list_b>div.list_li>div{padding: 4px 6px;line-height: 22px;border-left: 1px solid #638192;}

			.aqzs_b_l .tab_{
				margin: 13% 0 10%;
			}
			.aqzs_b_l .tab_ .capChart{
				padding: 35px;
				width: 60px;
				height: 60px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_l{
				margin: 20px 0 0 -105px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_b{
				margin: 110px 0 0 5px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_r{
				margin: 21px 0 0 115px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_t.capChart_t_t{
				margin: -70px 0 0 5px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_l{
				width:18px;
				height:18px;
				margin: 21px 0 0 -44px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_b{
				width:18px;
				height:18px;
				margin: 86px 0 0 21px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_r{
				width:18px;
				height:18px;
				margin: 21px 0 0 86px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_q.capChart_t_t{
				width: 18px;
				height: 18px;
				margin: -44px 0 0 21px;
			}
			.aqzs_b_l .tab_ .capChart .capChart_ .fn1{padding-top: 11px;line-height: 18px;}

			.videoWindow{
				width:640px;
				height:460px;
				margin-left: calc(50% - 320px);
				margin-top: 7%;
			}

		}
		.show_{
			display: unset;
		}


		/*轮播*/
		#slider-wrap{
			width:100%;
			height:100%;
			position:relative;
			overflow:hidden;
		}

		#slider-wrap ul#slider__{
			width:100%;
			height:100%;
			display: flex;
			position:absolute;
			top:0;
			left:0;
		}

		#slider-wrap ul#slider__ li{
			float:left;
			position:relative;
			width:100%;
			height:100%;
		}

		#slider-wrap ul#slider__ li > div{
			display: flex;
			flex-wrap: wrap;
			height: 100%;
		}

		#slider-wrap ul#slider__ li i{
			text-align:center;
			line-height:400px;
			display:block;
			width:100%;
			font-size:90px;
		}


		/*btns*/
		.btns{
			position:absolute;
			width:50px;
			height:60px;
			top:50%;
			margin-top:-25px;
			line-height:57px;
			text-align:center;
			cursor:pointer;
			background:rgba(0,0,0,0.1);
			z-index:100;


			-webkit-user-select: none;
			-moz-user-select: none;
			-khtml-user-select: none;
			-ms-user-select: none;

			-webkit-transition: all 0.1s ease;
			-moz-transition: all 0.1s ease;
			-o-transition: all 0.1s ease;
			-ms-transition: all 0.1s ease;
			transition: all 0.1s ease;
		}

		.btns:hover{
			background:rgba(0,0,0,0.3);
		}

		#next{right:-50px; border-radius:7px 0px 0px 7px;background: #ffffff45;}
		#previous{left:-50px; border-radius:0px 7px 7px 7px;background: #ffffff45;}
		#counter{
			top: 30px;
			right:35px;
			width:auto;
			position:absolute;
		}

		#slider-wrap.active #next{right:0px;}
		#slider-wrap.active #previous{left:0px;}


		/*bar*/
		#pagination-wrap{
			min-width:60px;
			margin-top:72%;
			margin-left: auto;
			margin-right: auto;
			height:15px;
			position:relative;
			text-align:center;
		}
		#pagination-wrap:hover{cursor: default;}
		#pagination-wrap ul {
			width:100%;
		}

		#pagination-wrap ul li{
			margin: 0 4px;
			display: inline-block;
			width:5px;
			height:5px;
			border-radius:50%;
			background:#fff;
			opacity:0.5;
			position:relative;
			top:0;
		}

		#pagination-wrap ul li.active{
			width: 7px;
			height: 7px;
			top: 1px;
			opacity: 0.8;
			box-shadow:rgba(0,0,0,0.1) 1px 1px 0px;
		}

		/*ANIMATION*/
		#slider-wrap ul, #pagination-wrap ul li{
			-webkit-transition: all 0.3s cubic-bezier(1,.01,.32,1);
			-moz-transition: all 0.3s cubic-bezier(1,.01,.32,1);
			-o-transition: all 0.3s cubic-bezier(1,.01,.32,1);
			-ms-transition: all 0.3s cubic-bezier(1,.01,.32,1);
			transition: all 0.3s cubic-bezier(1,.01,.32,1);
		}

	</style>
</head>
<body style="">
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
											<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
											<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
											<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
											<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
											<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
											<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
											<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
											<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
				<div class="clrr" onclick="getXcinfo('1')">
					<p class="fn1" id="fxd_red">0</p>
					<p class="f2">红</p>
				</div>
				<div class="clro" onclick="getXcinfo('2')">
					<p class="fn1" id="fxd_ora">0</p>
					<p class="f2">橙</p>
				</div>
				<div class="clry" onclick="getXcinfo('3')">
					<p class="fn1" id="fxd_yel">0</p>
					<p class="f2">黄</p>
				</div>
				<div class="clrb" onclick="getXcinfo('4')">
					<p class="fn1" id="fxd_blu">0</p>
					<p class="f2">蓝</p>
				</div>
			</div>
			<div class="left2_table">
				<ul style="margin-right: 4px;" id="fxd_ping_slide">
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
				<i class="fa fa-block clrq"></i>设备统计
				<span class="slider_cls fn1">X</span>
			</div>
			<div class="head">
				<div class="">
					<select id="select1">
						<option value="1">全部</option>
						<option value="2">正常</option>
						<option value="3">警报</option>
						<option value="4">故障</option>
						<option value="5">失联</option>
					</select>
				</div>
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
			</div>
			<div class="left2_table">
				<ul style="margin-right: 4px;">
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
										<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
										<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
										<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
										<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
										<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
										<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
										<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-06-22</span></span></span>
										<span>计划整改：<span>达莎博<span class="clrr">2019-06-22</span></span></span>
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
	<div class="bnt">
		<div class="topbnt_left fl">
			<ul>
				<!--<li><a href="${ctx}/a/vd_gov">政府端</a></li>
       <li class="active"><a href="${ctx}/a/vd_com">企业端</a></li>
      <li><a href="activity.html">活动情况</a></li> -->
			</ul>
		</div>
		<h1 class="tith1 fl">${bisenterprise.m1}</h1>
		<div class=" fr topbnt_right">
			<ul>
				<li><a onclick="req()" style="cursor: pointer">导航菜单</a></li>
			</ul>

		</div>
	</div>
	<!-- bnt end -->
	<div class="left1">
		<div class="aleftboxttop">
			<h2 class="tith2 ptm1">企业信息总览</h2>
			<div class="lefttoday_tit" style=" height:13%">
				<!--<p class="fl">地区：甘孜</p><p class="fr">2018-06-14</p> -->
				<h3 class="f1" style="margin-top: 2%;">${bisenterprise.m1}</h3>
			</div>
			<div class="userInfo f3">
				<p>安全管理员：<span>${bisenterprise.m23}</span></p>
				<p>联&nbsp;系&nbsp;方&nbsp;式：<span>${bisenterprise.m25}</span></p>
			</div>
			<div class="lefttoday_number f3">
				<div id="aqry_" class="v_btn">
					<img src="${ctx}/static/model/vd2/img/comInfo1.png">
					<div>
						<span class="fn2 clr_lg" id="ygzs">0</span>
						<span >员工总数</span>
					</div>
				</div>
				<div id="fxd_" class="v_btn">
					<img src="${ctx}/static/model/vd2/img/ic_2.png">
					<div>
						<span class="fn2 clr_lg" id="fxds">0</span>
						<span >风险点数</span>
					</div>
				</div>
				<div id="tzsb_" class="v_btn">
					<img src="${ctx}/static/model/vd2/img/ic_3.png">
					<div>
						<span class="fn2 clr_lg" id="tzsb">0</span>
						<span >特种设备</span>
					</div>
				</div>
				<div id="dqyh_" class="v_btn-">
					<img src="${ctx}/static/model/vd2/img/ic_4.png">
					<div>
						<span class="fn2 clr_lg" id="dqyh">0</span>
						<span >今日隐患</span>
					</div>
				</div>
			</div>
			<!-- lefttoday_number end -->
		</div>
		<!--<div class="bwMb2"></div> -->
		<div class="aleftboxtmidd">
			<h2 class="tith2">风险点统计</h2>
			<div id="aleftboxtmidd" class="aleftboxtmiddcont"></div>
		</div>
		<!--<div class="bwMb2"></div> -->
		<div class="aleftboxtbott">
			<h2 class="tith2">隐患整改情况</h2>
			<div class="lefttoday_tit height"><p class="fl">状态：已调节</p><p class="fr">时间段：2018-06-10 至 2018-06-14</p></div>
			<!-- <div id="amiddboxtbott1" class="aleftboxtbott_cont" ></div> -->
			<div class="lefttoday_number lb_h f3">
				<div id="aqry_">
					<img src="${ctx}/static/model/vd2/img/ic_5.png">
					<div>
						<span class="fn2 clr_lg" id="yhzs">74</span>
						<span>隐患总数</span>
					</div>
				</div>
				<div id="fxd_">
					<img src="${ctx}/static/model/vd2/img/ic_6.png">
					<div>
						<span class="fn2 clr_lg" id="yzg">44</span>
						<span>已整改</span>
					</div>
				</div>
				<div id="tzsb_">
					<img src="${ctx}/static/model/vd2/img/ic_7.png">
					<div>
						<span class="fn2 clr_lg" id="wzg">24</span>
						<span>未整改</span>
					</div>
				</div>
				<div id="dqyh_">
					<img src="${ctx}/static/model/vd2/img/ic_8.png">
					<div>
						<span class="fn2 clr_lg" id="zgl">0</span>
						<span>整改率</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  left1 end -->
	<div class="mrbox_topmidd">
		<div class="amiddboxttop">
			<h2 class="tith2 pt2">风险点一览</h2>
			<div class="tab_">
				<div id="fxdlb_" class="">
					<div class="min_tit f3">
						风险点列表
					</div>
				</div>
			</div>

			<div class="tab_ctt">
				<div id="fxdlb_ctt" >
					<div class="list_h f2-5" style="">
						<div class="w25">风险点名称</div>
						<div class="w20">风险类别</div>
						<div class="w20">行业类别</div>
						<div class="w20">事故类型</div>
						<div class="w15">风险分级</div>
					</div>
					<div class="list_b f3" style="">
						<c:forEach var="item" items="${fxdlist}" varStatus="status">
							<div id="fxdxq_" class="list_li">
								<div class="w25 line" title="">${item.m1}</div>
								<div class="w20 line" title="">${item.m2}</div>
								<div class="w20 line" title="">${item.m4}</div>
								<div class="w20 line" title="">${item.m8}</div>
								<div class="w15">
									<c:if test="${item.m9 eq 1}">
										<div class="bgr">红</div>
									</c:if>
									<c:if test="${item.m9 eq 2}">
										<div class="bgo">橙</div>
									</c:if>
									<c:if test="${item.m9 eq 3}">
										<div class="bgy">黄</div>
									</c:if>
									<c:if test="${item.m9 eq 4}">
										<div class="bgb">蓝</div>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!--<div class="bwMb2"></div> -->

		<div class="amidd_bott">
			<div class="amiddboxtbott1 fl" >
				<h2 class="tith2 ptm__">实时隐患排查数(本周)</h2>
				<div id="arightboxbott" class="amiddboxtbott1content" ></div>
			</div>
		</div>
	</div>

	<div class="mrbox_top_right">
		<div class="arightboxtop"><h2 class="tith2 ptm">当前隐患</h2>
			<div class="lefttoday_tit"><p class="fl">状态：已调节</p></div>
			<div class="left2_table">
				<ul style="margin-right: 4px;">
					<c:forEach items='${jryhlist}' var="c" varStatus="status">
						<li class="list_ f3">
							<div class="lihd" style="margin-top: 5px;">
								<img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/><span class="rsn">${c.dangerdesc}</span>
							</div>
							<div class="libd">
								<div class="pic" style="background:#00075b url(${c.dangerphoto}) no-repeat;background-size:100%;background-position:center;"></div>
								<div class="info">
									<span>点位名称：<span>${c.yhdmc}</span></span>
									<span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>${c.ly}</span></span>
									<span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>${c.fxr}<span><fmt:formatDate value="${c.pzsj}"    pattern="yyyy-MM-dd HH:mm"/></span></span></span>
									<span>计划整改：<span>${c.jhzgr}<span class="clrr"><fmt:formatDate value="${c.jhzgsj}"    pattern="yyyy-MM-dd HH:mm"/></span></span></span>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<!--<div class="bwMb2"></div> -->
		<div class="arightboxbott"><h2 class="tith2 ">动态监测</h2>
			<div class="lefttoday_tit height"><p class="fl">状态：已调节</p></div>
			<!--<div id="aleftboxtbott" class="arightboxbottcont" style=""></div> -->
			<div class="lefttoday_number lb_h f3">
				<div id="slider-wrap">
					<ul id="slider__">
						<li data-color="#3498db">
							<div>
								<div id="pub_" class="v_btn">
									<img src="${ctx}/static/model/vd2/img/ic_15.png">
									<div>
										<span class="fn1 clr_lg" id="qtnd">54</span>
										<span>气体浓度</span>
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
									<img src="${ctx}/static/model/vd2/img/ic_13.png">
									<div>
										<span class="fn1 clr_lg" id="cgxx">24</span>
										<span>储罐信息</span>
									</div>
								</div>
								<div id="pub_" class="v_btn">
									<img src="${ctx}/static/model/vd2/img/ic_10.png">
									<div>
										<span class="fn1 clr_lg" id="sdq">84</span>
										<span>水电气</span>
									</div>
								</div>
							</div>
							<i class="fa fa-gears"></i>
						</li>

						<li data-color="#1abc9c">
							<div>
								<%--						<div id="pub_" class="v_btn">
                                                               <img src="${ctx}/static/model/vd2/img/ic_9.png">
                                                               <div>
                                                                   <span class="fn1 clr_lg">54</span>
                                                                   <span>有毒气体</span>
                                                               </div>
                                                           </div>
                                                           <div id="pub_" class="v_btn">
                                                               <img src="${ctx}/static/model/vd2/img/ic_12.png">
                                                               <div>
                                                                   <span class="fn1 clr_lg">44</span>
                                                                   <span>废水信息</span>
                                                               </div>
                                                           </div>--%>
								<div id="pub_" class="v_btn">
									<img src="${ctx}/static/model/vd2/img/ic_11.png">
									<div>
										<span class="fn1 clr_lg" id="edm">24</span>
										<span>二道门</span>
									</div>
								</div>
								<div id="pub_" class="v_btn">
									<img src="${ctx}/static/model/vd2/img/ic_16.png">
									<div>
										<span class="fn1 clr_lg" id="gwgy">84</span>
										<span>高危工艺</span>
									</div>
								</div>
							</div>
							<i class="fa fa-image"></i>
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
	</div>
</div>


<script type="text/javascript">
	var ctx = '${ctx}';
	var qyid = '${qyid}';
	$(function() {
		//企业信息总览
		$.ajax({
			url :  '${ctx}/yhpc/dsj/xxzl?qyid='+qyid,
			type : "POST",
			success : function(data) {
				data=JSON.parse(data);
				$("#ygzs").html(data.ygCount);
				$("#fxds").html(data.fxCount);
				$("#tzsb").html(data.tzsbCount);
				$("#dqyh").html(data.yhCount);
				$("#cgxx").html(data.cgCount);
				$("#sdq").html(data.sdqCount);
				$("#edm").html(data.edmCount);
				$("#gwgy").html(data.gwgyCount);
				$("#qtnd").html(data.qtCount);
			}
		});

		//风险点统计
		$.ajax({
			url :  '${ctx}/yhpc/dsj/fxdtj?qyid='+qyid,
			type : "POST",
			success : function(data) {
				data=JSON.parse(data);
				loadEharts(data);
			}
		});

		//隐患整改情况
		$.ajax({
			url :  '${ctx}/yhpc/dsj/yhzgqk?qyid='+qyid,
			type : "POST",
			success : function(data) {
				data=JSON.parse(data);
				console.log(data);
				$("#yhzs").html(data.zs);
				$("#yzg").html(data.yzg);
				$("#wzg").html(data.wzg);
				$("#zgl").html(data.zgl);
			}
		});

		//实时隐患排查数(本周)
		$.ajax({
			url :  '${ctx}/yhpc/dsj/yhpc?qyid='+qyid,
			type : "POST",
			success : function(data) {
				data=JSON.parse(data);
				console.log(data.wed);
				loadEharts2(data);
			}
		});
	})


	function loadEharts(data) {
		fxdchart = echarts.init(document.getElementById('aleftboxtmidd'));
		fxdoption = {
			tooltip: {
				formatter: "{a} <br/>{b} : {c}个"
			},
			color: ['red', 'orange', 'yellow', '#5578cf'],
			backgroundColor: 'rgba(1,202,217,.0)',
			grid: {
				left: 20,
				right: 20,
				top: 0,
				bottom: 20
			},
			series: [
				{
					name: '实时风险点',
					type: 'pie',
					radius: '60%',
					center: ['50%', '50%'],
					data: [
						{value: data['red'], name: '红色风险点'},
						{value: data['orange'], name: '橙色风险点'},
						{value: data['yellow'], name: '黄色风险点'},
						{value: data['blue'], name: '蓝色风险点'}
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
		fxdchart.on("click", function (param) {
			if (param.dataIndex == 0)
				showOrhide_slider(null, "echart_fxd_r");
			if (param.dataIndex == 1)
				showOrhide_slider(null, "echart_fxd_o");
			if (param.dataIndex == 2)
				showOrhide_slider(null, "echart_fxd_y");
			if (param.dataIndex == 3)
				showOrhide_slider(null, "echart_fxd_b");
			var oEvent = param.event || event;
			stopBubble(oEvent);
		});

		// 填充点击饼状图滑出框的头部数据
		$('#fxd_red').html(data['red']);
		$('#fxd_ora').html(data['orange']);
		$('#fxd_yel').html(data['yellow']);
		$('#fxd_blu').html(data['blue']);
	}

	function loadEharts2(data) {
		console.log(data);
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
				data: ['周一','周二','周三','周四','周五','周六','周日'],
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
					data:[data.mon,data.tue, data.wed,data.thu,data.fri,data.sat,data.sun]
				}

			]
		};
		xjchart.setOption(xjcoption);
	}


	///轮播
	$(function() {

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

/*		$(".v_btn,.list_li,[class^=fxd_ic_]").click(function(ev){
			hideSlider_l();
			showOrhide_slider($(this),"v_btn");
			// 阻止事件传递
			var oEvent = ev || event;
			//oEvent.cancelBubble = true;//高版本浏览器
			stopBubble(oEvent);
			//在低版本的chrome和firefox浏览器中需要兼容性处理
			//高版本chrome和firefox浏览器直接使用上面这行代码即可
		})*/

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

		xjchart.resize();
		fxdchart.resize();
		if(zschart2)
			zschart2.resize();
	}

	// 根据风险点颜色获取巡查信息
	function getXcinfo(fxdcolor) {
		$('#fxd_ping_slide').html('');
		$.ajax({
			type: 'POST',
			url: '${ctx}/yhpc/xjjl/getxcinfo/'+fxdcolor,
			dataType: 'json',
			success: function(data) {
				var html = '';
				var colorHtml = '';
				if (fxdcolor == '1') {
					colorHtml = '<span class="lilab">红</span>';
				} else if (fxdcolor == '2') {
					colorHtml = '<span class="lilab" style="background:orange">橙</span>';
				} else if (fxdcolor == '3') {
					colorHtml = '<span class="lilab clrd" style="background:yellow">黄</span>';
				} else if (fxdcolor == '4') {
					colorHtml = '<span class="lilab" style="background: #2196f3">蓝</span>';
				}

				$.each(data, function(index, el){
					html += '<li class="list_ f3">\n' +
							'        <div class="lihd" style="margin-top: 5px;">\n' +
							colorHtml +
							'        </div>\n' +
							'        <div class="libd">\n' +
							'            <div class="pic"\n' +
							'                 style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>\n' +
							'                <div class="info">\n' +
							'                    <span>点&nbsp;&nbsp;位&nbsp;&nbsp;名&nbsp;&nbsp;称&nbsp;：<span>'+ el.dwmc +'</span></span>\n' +
							'                    <span>上次巡查人员：<span>'+el.xcry+'</span></span>\n' +
							'                    <span>上次巡查时间：<span>'+el.xcsj+'</span></span>\n' +
							'                </div>\n' +
							'                </div>\n' +
							'  </li>';
				})
				$('#fxd_ping_slide').html(html);
			}
		})
	}

	function req() {
		window.location.href = "${ctx}/a";
	}

</script>
</body>
</html>
