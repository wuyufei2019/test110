<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>化工企业安全生产管理一体化云平台</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery.js" ></script>
	<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
	<link href="${ctxStatic}/model/css/index/style10.css" rel="stylesheet" type="text/css" >
	<style>
		body {
			height: 100%;
			background: url(${ctxStatic}/model/images/hgqy/bg.jpg);
			background-size: cover;
			background-position: center;
			margin: 0;
			background-repeat: no-repeat;
		}

		.title {
			width: 1041px;
			height: 102px;
			display: flex;
			align-items: center;
			justify-content: center;
			margin: 0 auto;
			background: url(${ctxStatic}/model/images/hgqy/title.png);
			background-size: cover;
			font-size: 32px;
			font-weight: 600;
			color: white;
			text-align: center;
		}

		.card-box {
			width: 1200px;
			display: flex;
			justify-content: center;
			margin: 0 auto;
			margin-top: 100px;
		}

		.card {
			width: 230px;
			height: 314px;
			display: flex;
			flex-direction: column;
			align-items: center;
			background: url(${ctxStatic}/model/images/hgqy/card.png) no-repeat;
			background-size: 100%;
			background-position: center;
			font-size: 22px;
			font-weight: 600;
			color: rgb(255, 215, 145);
			margin: 30px 23px;
			cursor: pointer;
		}

		.card img {
			width: 140px;
			margin: 60px 0px 30px;
		}

		.copyright {
			width: 100%;
			position: fixed;
			bottom: 10px;
			font-size: 14px;
			color: white;
			text-align: center;
		}

		.button25 {
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
			-o-transition: all 0.5s;
			transition: all 0.5s;
			position: relative;
			overflow: hidden;
		}

		.button25 a {
			color: rgba(51, 51, 51, 1);
			text-decoration: none;
			display: block;
		}

		.button25::before,
		.button25::after {
			content: '';
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			z-index: 1;
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
			-o-transition: all 0.5s;
			transition: all 0.5s;
			opacity: 1;
			-webkit-transform: translate(-105%, 0);
			transform: translate(-105%, 0);
			border-right-width: 1px;
			border-right-style: solid;
			border-right-color: rgba(255, 255, 255, 1);
			background-color: rgba(255, 255, 255, 0.25);
		}

		.button25::after {
			-webkit-transition-delay: 0.2s;
			/* Safari */
			transition-delay: 0.2s;
		}

		.button25:hover::before,
		.button25:hover::after {
			opacity: 0;
			-webkit-transform: translate(0, 0);
			transform: translate(0, 0);
		}

		@media (max-width: 2000px) {
			.title {
				width: 1041px;
				height: 102px;
				font-size: 32px;
			}
			.card-box {
				width: 1200px;
				display: flex;
				justify-content: center;
				margin: 0 auto;
				margin-top: 80px;
			}
			.card {
				width: 230px;
				height: 314px;
				font-size: 22px;
				margin: 30px 23px;
			}
			.card img {
				width: 140px;
				margin: 60px 0px 30px;
			}
		}

		@media (max-width: 1440px) {
			.title {
				width: 800px;
				height: 78px;
				font-size: 26px;
			}
			.card-box {
				width: 1000px;
				margin-top: 60px;
			}
			.card {
				width: 180px;
				height: 245px;
				font-size: 20px;
				margin: 30px 15px;
			}
			.card img {
				width: 110px;
				margin: 46px 0px 20px;
			}
			.copyright {
				bottom: 10px;
				font-size: 13px;
			}
		}

		@media (max-width: 1200px) {
			.title {
				width: 660px;
				height: 64px;
				font-size: 22px;
			}
			.card-box {
				width: 800px;
				margin-top: 70px;
			}
			.card {
				width: 160px;
				height: 218px;
				font-size: 18px;
				margin: 30px 12px;
			}
			.card img {
				width: 96px;
				margin: 40px 0px 18px;
			}
			.copyright {
				bottom: 10px;
				font-size: 14px;
			}
		}

		.button6 {
			color: rgba(255, 255, 255, 1);
			-webkit-transition: all 0.5s;
			-moz-transition: all 0.5s;
			-o-transition: all 0.5s;
			transition: all 0.5s;
			border: 1px solid rgba(255, 255, 255, 0.5);
			position: absolute;
			right: 40px;
			top: 30px;
			width: 90px;
			height: 36px;
			line-height: 36px;
			text-align: center;
			cursor: pointer;
		}

		.button6 a {
			color: rgba(51, 51, 51, 1);
			text-decoration: none;
			display: block;
		}

		.button6 span {
			z-index: 2;
			display: block;
			position: absolute;
			width: 100%;
			height: 100%;
		}

		.button6::before {
			content: '';
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			z-index: 1;
			opacity: 0;
			background-color: rgba(255, 255, 255, 0.5);
			-webkit-transition: all 0.4s;
			-moz-transition: all 0.4s;
			-o-transition: all 0.4s;
			transition: all 0.4s;
			-webkit-transform: scale(0.5, 1);
			transform: scale(0.5, 1);
		}

		.button6:hover::before {
			opacity: 1;
			-webkit-transform: scale(1, 1);
			transform: scale(1, 1);
		}

		.chooseBody{
            width: calc(100% - 400px);
            height: 70%;
            margin: 40px auto 0;
			position: relative;
			background: url(${ctx}/static/model/vd2/img/multi/biji.png) no-repeat;
			background-size: 100%;
		}

		.p_door{
			display: flex;
			align-items: center;
		}

		.p_door-1, .p_door-3{
			margin-left: -40px;
		}
		.p_door-3{
			margin-top: 60px;
		}
		.p_door-2, .p_door-4{
			position: absolute;
			right: -40px;
		}

        .p_item-value{
            background: url(${ctx}/static/model/vd2/img/multi/p_value.png) no-repeat;
            background-size: 100% 100%;
        }

		.p_item-value-2{
			background: url(${ctx}/static/model/vd2/img/multi/p_value2.png) no-repeat;
			background-size: 100% 100%;
		}

		.p_item-value>div{
			display: flex;
			align-items: center;
		}
		.p_item-value>div>img{
			margin-left: 20px;
            margin-right: 8px;
		}
        .p_circle{
            border-radius:50% ;
            background-size: 100% 100%;
            cursor: pointer;
            background: url(${ctx}/static/model/vd2/img/multi/circle__.png) no-repeat;
            background-size: 100% 100%;
        }

		.p_circle:hover{
			border-radius:50% ;
			background-size: 100% 100%;
            cursor: pointer;
			background: url(${ctx}/static/model/vd2/img/multi/circle_.png) no-repeat;
			background-size: 100% 100%;
		}
		.p_circle .ca-icon{
			background-size: contain!important;
			background-position-x: center!important;
			margin-top: 20px;
		}

		.p_circle-1{
			margin-left: -40px;
		}
		.p_circle-1 .ca-icon{
			background: url(${ctx}/static/model/vd2/img/multi/c_ic1.png) no-repeat;
			background-size: 100% 100%;
		}

		.p_circle-2{
            z-index: 99;
		}
		.p_circle-2 .ca-icon{
			background: url(${ctx}/static/model/vd2/img/multi/c_ic2.png) no-repeat;
		}

		.p_circle-3{
            margin-left: -40px;
		}
		.p_circle-3 .ca-icon{
			background: url(${ctx}/static/model/vd2/img/multi/c_ic3.png) no-repeat;
			background-size: 100% 100%;
		}

		.p_circle-4{
            z-index: 99;
		}
		.p_circle-4 .ca-icon{
			background: url(${ctx}/static/model/vd2/img/multi/c_ic4.png) no-repeat;
			background-size: 100% 100%;
		}

		@media (max-width: 5000px) {

			.p_item-value{
				width: 230px;
                padding: 6px 0;
                height: 144px;
                display: flex;
                flex-direction: column;
                justify-content: center;
			}

			.p_circle{
				width: 230px;
				height: 230px;
			}
            .p_circle>div {
                margin-top: 58%;
                color: white;
                font-weight: 600;
                text-align: center;
                font-size: 16px;
            }

            .p_door-2{
                top: 0px;
            }
            .p_door-4{
                top: 280px;
            }

			.font-btns>div{
				width: 80px;
				height: 106px;
				margin: 0 25px;
                cursor: pointer;
			}
			.font-btns>div>div{
				margin-top: 86px;
				font-size: 14px;
				color: rgb(253, 204, 0);
				text-align: center;
			}
			.p_item-value>div>span{
				font-size: 14px;
				color: rgb(0, 179, 234);
			}
			.p_item-value>div>span>span {
				font-size: 22px;
				color: rgb(255, 201, 0);
				font-weight: 600;
                margin-left: 12px;
			}
            .font-btns{
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
                position: fixed;
                bottom: 70px;
            }
            .p_item-value>div{
                display: flex;
                align-items: center;
                padding: 3px 0;
            }
            .p_item-value-2>div {
                display: flex;
                align-items: center;
                padding: 2px 0;
                padding-left: 20px;
            }
		}

		@media (max-width: 1600px) {
			.p_door{
				width: 400px;
			}
			.p_door-3 {
				margin-top: 30px;
			}
			.p_item-value{
				width: 220px;
				padding: 3px 0;
				background-size: 100% 100%;
				height: 116px;
				display: flex;
				flex-direction: column;
				justify-content: center;
			}
			.p_circle{
				width: 190px;
				height: 190px;
			}
			.p_circle>div{
				margin-top: 56%;
				color: white;
				font-weight: 600;
				text-align: center;
				font-size: 13px;
			}

			.p_door-2{
				top: 0px;
			}
			.p_door-4{
				top: 220px;
			}
			.font-btns>div{
				width: 58px;
				height: 70px;
				margin: 0 15px;
				cursor: pointer;
			}
			.font-btns>div>div{
				margin-top: 58px;
				font-size: 13px;
				color: rgb(253, 204, 0);
				text-align: center;
			}
			.p_item-value>div>span{
				font-size: 14px;
				color: rgb(0, 179, 234);
			}
			.p_item-value>div>span>span {
				font-size: 19px;
				color: rgb(255, 201, 0);
				font-weight: 600;
				margin-left: 14px;
			}
			.font-btns{
				width: 100%;
				display: flex;
				align-items: center;
				justify-content: center;
				position: fixed;
				bottom: 80px;
			}

			.ca-icon {
				font-family: 'WebSymbolsRegular', cursive;
				font-size: 40px;
				color: #f6f6f6;
				line-height: 50px;
				position: absolute;
				width: 100%;
				height: 50px;
				left: 0px;
				top: 20px;
				text-align: center;
				-webkit-transition: all 200ms linear;
				-moz-transition: all 200ms linear;
				-o-transition: all 200ms linear;
				-ms-transition: all 200ms linear;
				transition: all 200ms linear;
			}
			.p_circle .ca-main {
				font-size: 15px;
			}
			.ca-main {
				font-size: 15px;
				position: absolute;
				top: 90px;
				text-align: center;
				color: #fff;
				width: 100%;
			}
			.p_circle:hover .ca-main {
				color: #fff;
				font-size: 16px;
				-webkit-animation: moveFromBottom 500ms ease;
				-moz-animation: moveFromBottom 500ms ease;
				-ms-animation: moveFromBottom 500ms ease;
			}
		}

		@media (max-width: 1366px) {
			.p_door{
				width: 340px;
			}
			.p_item-value{
				width: 190px;
                padding: 3px 0;
                background-size: 100% 100%;
                height: 116px;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }
			.p_circle{
				width: 160px;
				height: 160px;
			}
			.p_circle>div{
				margin-top: 56%;
				color: white;
				font-weight: 600;
				text-align: center;
				font-size: 13px;
			}

			.p_door-2{
				top: 0px;
			}
			.p_door-4{
				top: 220px;
			}
			.font-btns>div{
                width: 50px;
                height: 67px;
                margin: 0 15px;
                cursor: pointer;
			}
			.font-btns>div>div{
                margin-top: 58px;
                font-size: 12px;
                color: rgb(253, 204, 0);
                text-align: center;
			}
			.p_item-value>div>span{
				font-size: 13px;
				color: rgb(0, 179, 234);
			}
			.p_item-value>div>span>span {
				font-size: 18px;
				color: rgb(255, 201, 0);
				font-weight: 600;
                margin-left: 14px;
			}
            .font-btns{
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
                position: fixed;
                bottom: 80px;
            }
		}




		.font-btns>div:nth-child(1){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn1.png) no-repeat;
			background-size: 100% 100%;
		}
		.font-btns>div:nth-child(2){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn2.png) no-repeat;
			background-size: 100% 100%;
		}
		.font-btns>div:nth-child(3){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn3.png) no-repeat;
			background-size: 100% 100%;
		}
		.font-btns>div:nth-child(4){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn4.png) no-repeat;
			background-size: 100% 100%;
		}
		.font-btns>div:nth-child(5){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn5.png) no-repeat;
			background-size: 100% 100%;
		}
		.font-btns>div:nth-child(6){
			background: url(${ctx}/static/model/vd2/img/multi/f_btn6.png) no-repeat;
			background-size: 100% 100%;
		}

        .p_item-value-2>div {
            display: flex;
            align-items: center;
            padding-left: 20px;
        }
        .title>span{
            background: linear-gradient(to right, #fdffba, #01fff4);
            -webkit-background-clip: text;
            color: transparent;
        }
        .dot_{
            width: 12px;
            height: 12px;
            background: url(${ctx}/static/model/vd2/img/multi/dot.png) no-repeat;
            background-size: 100% 100%;
            margin-left: 20px;
            margin-right: 12px;
        }
	</style>
</head>

<body>
<div class="" style="height: 100%;">
	<div class="title">
		<span>化工企业安全生产管理一体化云平台</span>
	</div>

	<div class="sim-button button6" onclick="logout()"><span>退&nbsp;出</span></div>

	<!--body-->
	<div class="chooseBody">
		<div class="p_door p_door-1">
			<div>
				<div class="p_item-value">
					<div><div class="dot_"></div><span>储罐接入<span id="cg">0</span>个</span></div>
					<div><div class="dot_"></div><span>气体接入<span id="qt">0</span>个</span></div>
					<div><div class="dot_"></div><span>视频接入<span id="sp">0</span>个</span></div>
					<div><div class="dot_"></div><span>告警数量<span id="gjsl">0</span>个</span></div>
				</div>
			</div>
			<div class="p_circle p_circle-1" onclick="systemJump(1,4309)">
				<a href="#">
					<span class="ca-icon"></span>
					<div class="ca-content">
						<h2 class="ca-main">重大危险源<br/>监控预警系统</h2>
						<h3 class="ca-sub">24/7 for you needs</h3>
					</div>
				</a>
				<%--<div>重大危险源<br/>监控预警系统</div>--%>
			</div>
		</div>
		<div class="p_door p_door-2">
			<div class="p_circle p_circle-2" onclick="systemJump(3,4311)">
				<a href="#">
					<span class="ca-icon"></span>
					<div class="ca-content">
						<h2 class="ca-main">人员在岗<br/>在位管理系统</h2>
						<h3 class="ca-sub">24/7 for you needs</h3>
					</div>
				</a>
				<%--<div>人员在岗<br/>在位管理系统</div>--%>
			</div>
			<div>
				<div class="p_item-value p_item-value-2" style="margin-left: -40px;">
					<div><div class="dot_"></div><span>全厂员工<span id="yg">0</span>个</span></div>
					<div><div class="dot_"></div><span>访客<span id="fk">0</span>个</span></div>
					<div><div class="dot_"></div><span>告警<span id="gj">0</span>个</span></div>
				</div>
			</div>
		</div>
		<div class="p_door p_door-3">
			<div>
				<div class="p_item-value">
					<div><div class="dot_"></div><span>红色等级<span id="red">0</span>个</span></div>
					<div><div class="dot_"></div><span>橙色等级<span id="ora">0</span>个</span></div>
					<div><div class="dot_"></div><span>黄色等级<span id="yel">0</span>个</span></div>
					<div><div class="dot_"></div><span>蓝色等级<span id="blu">0</span>个</span></div>
				</div>
			</div>
			<div class="p_circle p_circle-3" onclick="bigdataJumpNew(2)">
				<a href="#">
					<span class="ca-icon"></span>
					<div class="ca-content">
						<h2 class="ca-main">企业安全风险<br/>分区管理系统</h2>
						<h3 class="ca-sub">24/7 for you needs</h3>
					</div>
				</a>
				<%--<div>企业安全风险<br/>分区管理系统</div>--%>
			</div>
		</div>
		<div class="p_door p_door-4">
			<div class="p_circle p_circle-4" onclick="systemJump(4,4312)">
				<a href="#">
					<span class="ca-icon"></span>
					<div class="ca-content">
						<h2 class="ca-main">企业安全生产<br/>全流程管理系统</h2>
						<h3 class="ca-sub">24/7 for you needs</h3>
					</div>
				</a>
				<%--<div>企业安全生产<br/>全流程管理系统</div>--%>
			</div>
			<div>
				<div class="p_item-value p_item-value-2" style="margin-left: -40px;">
					<div><div class="dot_"></div><span>到期提醒<span id="dqtx">0</span>个</span></div>
					<div><div class="dot_"></div><span>审核审批<span id="shsp">0</span>个</span></div>
					<div><div class="dot_"></div><span>隐患整改<span id="yhzg">0</span>个</span></div>
				</div>
			</div>
		</div>
	</div>

	<div class="font-btns">
		<%--<div onclick="bigdataJumpNew(-1)">
			<div>三维沙盘</div>
		</div>
		<div onclick="bigdataJumpNew(0)">
			<div>GIS地图</div>
		</div>--%>
		<div onclick="bigdataJumpNew(1)">
			<div>重大危险源大数据</div>
		</div>
		<div onclick="bigdataJumpNew(2)">
			<div>风险分区大数据</div>
		</div>
		<%--<div onclick="bigdataJumpNew(3)">
			<div>人员定位大数据</div>
		</div>--%>
		<div onclick="bigdataJumpNew(4)">
			<div>双重机制大数据</div>
		</div>
	</div>


	<div class="copyright">
		&copy;2019&nbsp; 技术支持：中安联科
	</div>
</div>

</body>

<script>
	$(function() {
		initElement();

		getData();

		$(".font-btns>div").mouseenter(function () {
            $(this).animate({marginTop:"-20px"},150,'swing',function() {
				//$(".slider_l").removeClass("show_");
			},'300');
        })
		$(".font-btns>div").mouseleave(function () {
			$(this).animate({marginTop:"0px"},150,'swing',function() {
				//$(".slider_l").removeClass("show_");
			});
		})
	})
	$(window).resize(function() {
		initElement();
	})

	function initElement() {
		$("body").height($(window).height())
	}

	function logout(){
		layer.confirm('你确定要退出系统？', {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			window.location.href="${ctx}/a/logout";
		});
	}

	/*function bigdataJump(type){
		if(type==1){
			window.location.href="${ctx}/hgqyIndex/zdwxyhome";
		}else
			window.location.href="${ctx}/hgqyIndex/bigdata/"+type;
	}*/

	function systemJump(type,pid){
		if (type==2) {
			window.location.href="${ctx}/hgqyIndex/bigdata/4";
		} else {
			window.location.href="${ctx}/hgqyIndex/sys/"+type+"/"+pid;
		}
	}


	function bigdataJumpNew(type){
		if(type==-1)
			window.open('http://139.129.23.185:8090/demo/index.html','_blank');
		if(type==0)
			window.open('http://139.129.23.185:8099/HHTJXF/index.html','_blank');
		if(type==1)
			window.location.href="${ctx}/hgqyIndex/bigdata/"+type;
		if(type==2)
			window.location.href="${ctx}/hgqyIndex/bigdata/4";
		if(type==3)
			window.location.href="${ctx}/hgqyIndex/bigdata/2";
		if(type==4)
			window.location.href="${ctx}/hgqyIndex/bigdata/3";
	}

	// 数据填充
	function getData() {
		$.ajax({
			type: 'POST',
			url:  '${ctx}/hgqyIndex/getmenudata',
			dataType: 'json',
			success: function (data) {
				$('#cg').text(data.cgCount);// 储罐数量
				$('#qt').text(data.qtCount);// 气体点位数量
				$('#gwgy').text(data.gwgyCount);// 高危工艺数量
				$('#sp').text(data.spCount);// 视频数量
                $('#yg').text(data.ygCount);// 员工数量

                $('#red').text(data.redCount);// 红色等级数量
                $('#ora').text(data.oraCount);// 橙色等级数量
                $('#yel').text(data.yelCount);// 黄色等级数量
                $('#blu').text(data.bluCount);// 蓝色等级数量
			}
		})

		$.ajax({
			url : '${ctx}/syshome/qysjzs',
			type : "POST",
			success : function(data) {
				for(var key in data){
					$('#dqtx').text(data['aqglzs'] + data['tzsb'] + data['aqss'] + data['zybwh'] + data['zybtj']);// 到期提醒
					$('#shsp').text(data['dsh'] + data['dpz']);// 审核审批
					$('#yhzg').text(data['dzg']);// 隐患整改
				}
			}
		});
	}
</script>
</html>
