<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
	<title>化工企业安全生产管理一体化云平台</title>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery.js" ></script>
	<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
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
			bottom: 20px;
			font-size: 16px;
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
				bottom: 20px;
				font-size: 14px;
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
				bottom: 20px;
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
	</style>
</head>

<body>
<div class="">
	<div class="title">
		化工企业安全生产管理一体化云平台
	</div>

	<div class="sim-button button6" onclick="logout()"><span>退&nbsp;出</span></div>

	<div class="card-box-area">
		<div class="card-box">
			<div class="card button25" onclick="bigdataJump(1)">
				<img src="${ctxStatic}/model/images/hgqy/ic1.png" />
				<div class="tit">
					重大危险源
				</div>
				<div class="tit">
					监控预警系统
				</div>
			</div>

			<div class="card button25" onclick="systemJump(2,4310)">
				<img src="${ctxStatic}/model/images/hgqy/ic2.png" />
				<div class="tit">
					企业安全风险
				</div>
				<div class="tit">
					分区管理系统
				</div>
			</div>

			<div class="card button25" onclick="systemJump(3,4311)">
				<img src="${ctxStatic}/model/images/hgqy/ic3.png" />
				<div class="tit">
					人员在岗
				</div>
				<div class="tit">
					在位管理系统
				</div>
			</div>

			<div class="card button25" onclick="systemJump(4,4312)">
				<img src="${ctxStatic}/model/images/hgqy/ic4.png" />
				<div class="tit">
					企业安全生产
				</div>
				<div class="tit">
					全流程管理系统
				</div>
			</div>
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

	function bigdataJump(type){
		window.location.href="${ctx}/hgqyIndex/bigdata/"+type;
	}

	function systemJump(type,pid){
		window.location.href="${ctx}/hgqyIndex/sys/"+type+"/"+pid;
	}
</script>
</html>
