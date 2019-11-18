<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<!-- 
	Author ： Captain
	Date : 19/04/10
-->
<head>
	<title>人员在岗在位管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">

	<link rel="stylesheet" href="${ctx}/static/font-awesome-4.7.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx}/static/layui-2.2.5/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx}/static/sys/css/index.css" media="all" />
	<link rel="stylesheet" href="${ctx}/static/sys/css/cap.css" media="all" />

	<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
	
	<script src="${ctxStatic}/jquery/jquery-2.1.1.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/static/layui-2.2.5/layui.js"></script>
	<script type="text/javascript" src="${ctx}/static/sys/js/index.js"></script>
	<script type="text/javascript" src="${ctx}/static/sys/js/cache.js"></script>
	<style>
		.logo {
			width: 242px;
		}
		.p_tit{
			color: rgb(32, 34, 42);
			background: #b0e2f9;
			margin-bottom: 8px;
			/*margin-left: -15px;*/

			padding-left: 15px;
			font-size: 17px;
			line-height: 40px !important;
		}
		.p_tits{
			width: 282px;
			background: #38a6c3;
			position: absolute;
			left: -320px;
			text-align: left;
			padding: 0px 15px 5px;
			}
			.p_active{
				display: block;
		}

		.cap__{
			background: rgba(255, 255, 255, 0.48);
			margin: 8px 0;
			border-radius: 4px;
			box-shadow: 0 0 3px 0 #00484c1c inset;
		}
</style>
</head>

	<body class="main_body">
		<div class="layui-layout layui-layout-admin">
			<!-- 顶部 -->
			<div class="layui-header header">
				<div class="layui-main mag0" style="display: flex;background: rgba(21, 165, 146, 0.54);border-bottom: 1px solid #1e8a80;">
					<!--<a href="${ctx}/a/vd_com" class="logo" style="letter-spacing: 2px;font-size: 20px;font-weight: 600;" title="回到首页">人员在岗在位管理系统</a>-->
					<a id="logo_a" href="#" class="logo" style="letter-spacing: 2px;font-size: 20px;font-weight: 600;" title="点击进入平台导航">
						<div>
							<div id="p_nav_" class="p_active" onclick="showSysNav()">人员在岗在位管理系统</div>
							<div class="p_tits" onmouseleave="hideSysNav()">
								<div class="p_tit button6" onclick="systemJump(1,4309)">重大危险源监测预警系统</div>
								<div class="p_tit button6" onclick="systemJump(4,4312)">安全生产全流程管理系统</div>
								<div class="p_tit button6" onclick="bigdataJumpNew(2)">安全风险分区管理系统</div>
							</div>
						</div>
					</a>
                    <a href="${ctx}/a/vd_gov" class="" style="letter-spacing: 2px;font-size: 20px;font-weight: 600;display: none" title="">平</a>
					<!-- 显示/隐藏菜单 -->
					<a href="javascript:;" class="hideMenu fa fa-bars"></a>
					<!-- 顶级菜单 -->
					<ul class="layui-nav mobileTopLevelMenus" mobile>
						<li class="layui-nav-item" data-menu="xtsy">
							<a href="javascript:;"><i class="fa fa-bars"></i><cite>人员在岗在位管理系统</cite></a>
							<dl class="layui-nav-child">
							</dl>
						</li>
					</ul>
					<ul class="layui-nav topLevelMenus" pc style="display: flex;flex-grow: 1;overflow: hidden;padding: 0 10px 0 0;margin-left: 10px;border-radius: 0px;">
					</ul>
					<!-- 顶部右侧菜单 -->
					<ul class="layui-nav top_menu" style="flex-shrink: 0;display: flex;">
						<i class="layui-icon tagToL">&#xe623;</i>
						<i class="layui-icon tagToR">&#xe623;</i>
						<!--<li class="layui-nav-item lockcms" pc>
							<a href="javascript:;"><i class="fa fa-lock" style="margin-right: 5px;font-size: 15px !important;"></i><cite>锁屏</cite></a>
						</li>-->

						<li class="layui-nav-item" id="userInfo">
							<a href="${ctx}/a" class="cap__" style="line-height: 34px !important;max-height: 34px !important;color: #086c71;">
								<cite class="adminName" style="margin-right: 10px;">
									<span>导航菜单</span>
								</cite>
							</a>
							<dl class="layui-nav-child">
								<%--<dd>--%>
									<%--<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dxj')"><cite>人员在岗在位管理系统</cite><span class="num" id="dxj" style="display: none">(0)</span></a>--%>
								<%--</dd>--%>
								<dd>
									<a class="text" onclick="systemJump(1,4309)"><cite>重大危险源监测预警系统</cite><span class="num" id="dsh" style="display: none">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="systemJump(4,4312)"><cite>安全生产全流程管理系统</cite><span class="num" id="dsp" style="display: none">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="bigdataJumpNew(2)"><cite>安全风险分区管理系统</cite><span class="num" id="dsp" style="display: none">(0)</span></a>
								</dd>
							</dl>
						</li>

						<li class="layui-nav-item" pc>
							<a href="javascript:;" onclick="openScreen()"><i class="" style="margin-right: 5px;font-size: 15px !important;"></i><cite id="screen">全屏</cite></a>
						</li>

						<li class="layui-nav-item" id="userInfo">
							<a href="javascript:;">
								<img src="${ctx}/static/sys/images/avatar.png" class="layui-nav-img userAvatar" width="35" height="35">
								<c:if test="">
									<span class="layui-badge-dot" style="margin: -14px 6px 0 -18px;"></span>
								</c:if>

								<cite class="adminName" style="margin-right: 10px;">
									<span><shiro:principal property="name"/></span>
								</cite>
							</a>
							<dl class="layui-nav-child">
								<!--<dd>
									<a href="javascript:;" data-url="page/system/userInfo.html"><i class="fa fa-newspaper-o"></i><cite>个人资料</cite></a>
								</dd>
								<dd>
									<a href="javascript:;" class="showNotice"><i class="fa fa-bullhorn"></i><cite>系统公告</cite><span class="layui-badge-dot"></span></a>
								</dd>-->
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dxj')"><i class="fa fa-bullhorn"></i><cite>待巡检</cite><span class="num" id="dxj">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dsh')"><i class="fa fa-bullhorn"></i><cite>待审核</cite><span class="num" id="dsh">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dsp')"><i class="fa fa-bullhorn"></i><cite>待审批</cite><span class="num" id="dsp">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=djc')"><i class="fa fa-bullhorn"></i><cite>待检查</cite><span class="num" id="djc">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dzg')"><i class="fa fa-bullhorn"></i><cite>待整改</cite><span class="num" id="dzg">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=xwj')"><i class="fa fa-bullhorn"></i><cite>新文件</cite><span class="num" id="xwj">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=pxtz')"><i class="fa fa-bullhorn"></i><cite>培训通知</cite><span class="num"id="pxtz">(0)</span></a>
								</dd>
								<dd>
									<a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dcwj')"><i class="fa fa-bullhorn"></i><cite>调查问卷</cite><span class="num"id="dcwj">(0)</span></a>
								</dd>
								<!--<dd>
									<a class="signOut" onclick="logout()"><i class="fa fa-power-off"></i><cite>退出</cite></a>
								</dd>-->
							</dl>
						</li>

						<li class="layui-nav-item" pc>
							<a href="javascript:;" onclick="logout()" style="padding: 0 5px 0 15px;"><i class="fa fa-power-off" style="margin-right: 5px;font-size: 15px !important;"></i></a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 左侧导航 -->
			<div class="layui-side layui-bg-black">
				<div class="user-photo">
					<a class="img" title="企业logo"><img src="${ctx}/static/sys/images/avatar.png" class="userAvatar"></a>
					<%--<p>你好！<span><shiro:principal property="name"/></span>, 欢迎登录</p>--%>
					<p><span style="font-size: 15px; font-weight: 400;"><shiro:principal property="name"/></span></p>
				</div>
				<!-- 搜索 -->
				<div class="layui-form component" style="display: none;">
					<select name="search" id="search" lay-search lay-filter="searchPage">
						<option value="">搜索页面或功能</option>
						<option value="1">layer</option>
						<option value="2">form</option>
					</select>
					<i class="layui-icon">&#xe615;</i>
				</div>
				<div class="navBar layui-side-scroll" id="navBar">
					<ul class="layui-nav layui-nav-tree">
					</ul>
				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="layui-body layui-form">
				<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
					<ul class="layui-tab-title top_tab" id="top_tabs">
						<li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite cap-url="lydw/rydw/index">首页</cite></li>
					</ul>
					<ul class="layui-nav closeBox">
						<i class="tagToL fa fa-caret-left"></i>
						<i class="tagToR fa fa-caret-right"></i>
						<i class="refresh refreshThis fa fa-repeat" title="刷新"></i>
						<i class="closePageOther fa fa-ban" title="关闭其它"></i>
						<i class="closePageAll fa fa-times-circle-o" title="关闭所有"></i>
						
						<li class="layui-nav-item" style="width: 100%;display: none;">
							<a href="javascript:;">更多</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a>
								</dd>
								<dd>
									<a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a>
								</dd>
								<dd>
									<a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a>
								</dd>
							</dl>
						</li>
					</ul>
					<div class="layui-tab-content clildFrame">
						<div class="layui-tab-item layui-show">
							<iframe src="${ctx}/lydw/rydw/index"></iframe>
						</div>
					</div>
				</div>
			</div>
			<!-- 底部 -->
			<div class="layui-footer footer">
				<p><span>&copy;2019 &nbsp;技术支持：中安联科</span>
					<a style="display: none;" onclick="" class="layui-btn layui-btn-danger layui-btn-sm"></a>
					<span class="copyright">v1.1.0</span>
				</p>
			</div>
		</div>

		<!-- 移动导航 -->
		<div class="site-tree-mobile"></div>
		<div class="site-mobile-shade"></div>
	</body>
	
	<script type="text/javascript">
		var pid = ${pid};
		var index_path = "lydw/rydw/index";

	layui.use(['layer'],function(){
	    var layer = parent.layer === undefined ? layui.layer : top.layer;
	        
		// 添加
		$(".copyright-").click(function(){
	        var index = layui.layer.open({
	            title : "系统版本",
	            type : 2,
	            area: ['60%', '80%'],
	            maxmin: true,
	            content : ctx+"/a/sysVersion",
	            success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	            }
	        })
	        //layer.alert("请使用 人员在岗在位管理系统 - 企业版 进行该操作！")
	    })	
	})

		function openDialog(title,url){
			layer.open({
				type: 2,
				shift: 1,
				area: ["80%", "80%"],
				title: title,
				maxmin: true,
				content: url
			});
		}

		function logout(){
			layer.confirm('你确定要退出系统？', {icon: 3, title:'提示'}, function(index){
				layer.close(index);
				window.location.href="${ctx}/a/logout";
			});
		}

		// 全屏效果
		function openScreen(){
//打开全屏
			var screenState = $('#screen').text();
			if(screenState=='全屏'){
				$('#screen').text('退出全屏');
			}else{
				$('#screen').text('全屏')
			}
			var docElm = document.documentElement;
			//W3C
			if(docElm.requestFullscreen) {
				docElm.requestFullscreen();

			}

			//FireFox
			else if(docElm.mozRequestFullScreen) {
				docElm.mozRequestFullScreen();

			}

			//Chrome等
			else if(docElm.webkitRequestFullScreen) {
				docElm.webkitRequestFullScreen();

			}

			//IE11
			else if(elem.msRequestFullscreen) {
				elem.msRequestFullscreen();

			}

// 关闭全屏
			if (document.exitFullscreen) {
				document.exitFullscreen();
			}
			//FireFox
			else if (document.mozCancelFullScreen) {
				document.mozCancelFullScreen();
			}
			//Chrome等
			else if (document.webkitCancelFullScreen) {
				document.webkitCancelFullScreen();
			}
			//IE11
			else if (document.msExitFullscreen) {
				document.msExitFullscreen();
			}
		}

		function showSysNav() {
			if(!$(".p_active").hasClass("p_tit_show")){
				$(".p_tits").animate({left:"0px"},200,function () {
				});
				$(".p_active").addClass("p_tit_show");
			}else{
				$(".p_tits").animate({left:"-320px"},200,function () {
				});
				$(".p_active").removeClass("p_tit_show");
			}
		}
		function hideSysNav() {
			$(".p_tits").animate({left:"-320px"},200,function () {
			});
			$(".p_active").removeClass("p_tit_show");
		}
		//返回主菜单
		function openHgqyIndex(){
			window.location.href="${ctx}/hgqyIndex/index";
		}

		function bigdataJump(type){
			window.location.href="${ctx}/hgqyIndex/bigdata/"+type;
		}

		function systemJump(type,pid){
			if (type == 2) {
				window.location.href="${ctx}/hgqyIndex/bigdata/4";
			} else {
				window.location.href="${ctx}/hgqyIndex/sys/"+type+"/"+pid;
			}
		}

		function bigdataJumpNew(type){
			if(type==2)
				window.location.href="${ctx}/hgqyIndex/bigdata/4";
		}
	</script>

<%--<script type="text/javascript">--%>
	<%--var ph=document.getElementById("p_nav_");--%>
	<%--var otime=null;--%>
	<%--var num=-30;--%>
	<%--function tomove(){--%>
		<%--otime=setInterval(function (){--%>
			<%--num+=1;--%>
			<%--if(num==240)--%>
			<%--{--%>
				<%--num=-30;--%>
				<%--clearInterval(otime);--%>
			<%--}--%>
			<%--ph.style.backgroundPosition=num+"px 0px";--%>

		<%--},20)--%>
	<%--}--%>
	<%--tomove();--%>
	<%--setInterval(function(){--%>
		<%--tomove();--%>
	<%--},3000);--%>
<%--</script>--%>
<script type="text/javascript" src="${ctx}/static/model/vd2/js/ripplet.js"></script>
<script>
	// window.event polyfill for Gecko
	if(!Object.prototype.hasOwnProperty.call(window, 'event')) {
		['mousedown', 'mouseenter', 'onmouseleave'].forEach(function(eventType) {
			window.addEventListener(eventType, function(event) {
				window.event = event
			}, true);
		});
	}
	// heading ripplet
	window.addEventListener('load', function() {
		setTimeout(function() {
			var heading = document.querySelector('#logo_a');
			var headingRect = heading.getBoundingClientRect();
			headingRipplet();

			function headingRipplet() {
				var spreadingDuration = Math.floor(4000 * Math.random() + 200);
				ripplet({
					currentTarget: heading,
					clientX: headingRect.left + Math.random() * headingRect.width,
					clientY: headingRect.top + Math.random() * headingRect.height,
				}, {
					color: 'rgba(' +
							Math.floor(Math.random() * 256) + ',' +
							Math.floor(Math.random() * 256) + ',' +
							Math.floor(Math.random() * 256) + ',' +
							(Math.random() * .3 + .1) +
							')',
					spreadingDuration: spreadingDuration + 'ms',
					clearingDelay: Math.floor(spreadingDuration * .8) + 'ms',
					clearingDuration: Math.floor(spreadingDuration * .6) + 'ms',
				});
				setTimeout(headingRipplet, Math.floor(4000 * Math.random() * Math.random()));
			}
		});
	});
</script>
</html>