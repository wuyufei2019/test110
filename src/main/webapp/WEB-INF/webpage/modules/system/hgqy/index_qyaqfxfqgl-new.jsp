<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<%
    String logotitle=" ";
    if(session.getAttribute("logotitle")!=null&&!session.getAttribute("logotitle").equals(""))
        logotitle=(String)session.getAttribute("logotitle")+" —— ";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/style.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/main.css?v=1.0" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/nav.css" />
<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/font-awesome.css" />
<script type="text/javascript" src="${ctxStatic}/jquery/jquery.js" ></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/global.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/nav.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/Menu.js"></script>
<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
<style type="text/css">
/* 扇形导航 */
    .tips {
		width: 702px;
		margin: 0 auto;
		line-height: 24px;
		padding-top: 10px;
	}
	
	.bredcolor {
		color: #fff;
	}
	
	.menuHolder {
		position: fixed;
		z-index: 100;
		top: 0;
		right: 0;
		transform: rotateY(180deg);
	}
	
	.menuHolder ul {
		padding: 0;
		margin: 0;
		list-style: none;
		position: absolute;
		left: 0;
		top: 0;
		width: 0;
		height: 0;
	}
	
	.menuHolder ul li {
		border-radius: 0 0 300px 0;
		width: 0;
		height: 0;
	}
	
	.menuHolder ul li a {
		color: #000;
		text-decoration: none;
		font: bold 13px/30px arial, sans-serif;
		text-align: center;
		box-shadow: -5px 5px 5px rgba(0, 0, 0, 0.4);
		-webkit-transform-origin: 0 0;
		-moz-transform-origin: 0 0;
		-ms-transform-origin: 0 0;
		-o-transform-origin: 0 0;
		transform-origin: 0 0;
	}
	
	.menuHolder ul.p1 li {
		position: absolute;
		left: 0;
		top: 0;
	}
	
	.menuHolder ul.p2 {
		z-index: -1;
	}
	
	.menuHolder li.s1>a {
		position: absolute;
		display: block;
		width: 60px;
		height: 60px;
		background: #efefef;
		border-radius: 0 0 60px 0;
	}
	
	.menuHolder li.s2>a {
		position: absolute;
		display: block;
		width: 90px;
		padding-left: 90px;
		height: 180px;
		background: #efefef;
		border-radius: 0 0 180px 0;
	}
	
	.menuHolder ul ul {
		-webkit-transform-origin: 0 0;
		-moz-transform-origin: 0 0;
		-ms-transform-origin: 0 0;
		-o-transform-origin: 0 0;
		transform-origin: 0 0;
		-webkit-transform: rotate(90deg);
		-moz-transform: rotateZ(90deg);
		-ms-transform: rotate(90deg);
		-o-transform: rotate(90deg);
		transform: rotate(90deg);
		-webkit-transition: 0.4s;
		-moz-transition: 0.4s;
		-ms-transition: 0.4s;
		-o-transition: 0.4s;
		transition: 0.4s;
	}
	
	.menuHolder li.s2:nth-of-type(4)>a {
		background: #c3c3c3;
		-webkit-transform: rotate(45deg);
		-moz-transform: rotateZ(45deg);
		-ms-transform: rotate(45deg);
		-o-transform: rotate(45deg);
		transform: rotate(67.5deg);
	}
	
	.menuHolder li.s2:nth-of-type(3)>a {
		background: #d4d4d4;
		-webkit-transform: rotate(30deg);
		-moz-transform: rotateZ(30deg);
		-ms-transform: rotate(30deg);
		-o-transform: rotate(30deg);
		transform: rotate(45deg);
	}
	
	.menuHolder li.s2:nth-of-type(2)>a {
		background: #e2e2e2;
		-webkit-transform: rotate(15deg);
		-moz-transform: rotateZ(15deg);
		-ms-transform: rotate(15deg);
		-o-transform: rotate(15deg);
		transform: rotate(22.5deg);
	}
	
	.menuHolder li.s1:hover ul.p2 {
		-webkit-transform: rotate(0deg);
		-moz-transform: rotateZ(0deg);
		-ms-transform: rotate(0deg);
		-o-transform: rotate(0deg);
		transform: rotate(0deg);
	}
	
	.menuHolder ul li:hover>a {
		background: #555;
		color: #fff;
	}
	
	.menuHolder li.s2:hover>a {
		background: #457092;
		color: #fff;
	}
	
	.menuWindow {
		width: 60px;
		height: 68px;
		border-radius: 0 0 60px 0;
		overflow: hidden;
		position: absolute;
		left: 0;
		top: 0;
		z-index: 100;
	}
	
	.menuHolder:hover .menuWindow {
		width: 190px;
		height: 190px;
	}
	
	.menuHolder span {
		display: block;
		-webkit-transform: rotate(5deg);
		-moz-transform: rotateZ(5deg);
		-ms-transform: rotate(5deg);
		-o-transform: rotate(5deg);
		transform: rotate(5deg);
		transform: rotateY(180deg);
		transform: rotateY(180deg) rotateZ(-10deg);
		line-height: 56px;
  			padding-left: 10px;
	}
	
	.menuHolder~img.close {
		width: 0;
		height: 0;
		position: fixed;
		z-index: -1;
		left: 0;
		top: 0;
	}
	
	.menuHolder:hover~img.close {
		width: 100%;
		height: 100%;
	}
	
	
	
	/* 隐藏sidebar */
	#container>div:first-child{
	height: 64px!important;
	}
	.hd-bottom{
		display: none;
	}
	.main .title{
		height: 0px;
		overflow:hidden;
	}
	#bd:nth-child(2){
	padding-left: 0px!important;
	 margin-top: 0px;
	}
	.sidebar{
		display:none!important
	}
	#ft{
	height: 35px;
    line-height:35px;
    background: url(${ctx}/static/model/images/index/skin_/footerbg.png);
    background-size: 100% 100%;
} 
</style>            
<title><%=logotitle %>企业安全风险分区管理系统</title>
</head>

<style>
    .logo{
        display:flex;position:fixed;font-size:19px;align-items: center;margin-top: -9px;
    }
    .comLogo{
        width: auto;
        height: 40px;
        margin: 0 7px 0 20px;
    }
    .subNav-icon2{
    	float: left;
	    margin: 11px 0 0 10px;
	    width: 12px;
	    height: 15px;
	    background: url(../../../images/home/list.gif) no-repeat;
    }
    .hd-top .logo {
        width: 700px;
    }

    .hd-top .setting .setting-main{
        width: 100px;
    }
</style>

<body>
<div id="container">
	<div id="hd">
    	<div class="hd-top">
            <h1 class="logo">
                <img id="comLogo" class="comLogo" src="${ctxStatic}/model/images/login/logo_qy.png"/>
                <span class="comName"><%=logotitle %>企业安全风险分区管理系统</span>
            </h1>
            <div class="setting ue-clear">
            	<div class="setting-skin">
                	<div class="switch-bar">
                    	<i class="skin-icon"></i>
                    	<i class="info-num"><span id="msgtotal">0</span></i>
                        <span class="text" style="display: block;">消息中心</span>
                        <i class="arrow-icon"></i>
                    </div>
                </div>
                <ul class="setting-main ue-clear">
					<li style="width: 60px"><a class="" id="screen" onclick="openScreen()">全屏</a></li>
                    <li><a class="close-btn exit" onclick="logout()"></a></li>
                </ul>
            </div>
            <div class="user-info">
                <!-- <a class="" id="screen" onclick="openHgqyIndex()"
                   style="padding: 0px 12px;display: flex;align-items: center;background: #ffffff;color: #ed5565;cursor: pointer;
                   font-weight: 600;height: 32px!important;border-radius: 16px;float: left;margin-top: 10px;margin-right: 15px;">返回主菜单</a> -->
                <a href="javascript:;" onclick="openDialog('个人信息设置','${ctx}/system/user/infor')" title="个人档案" class="user-avatar"><span></span></a>
                <span class="user-name"><shiro:principal property="name"/>，欢迎您</span>
            
            </div>
            
        </div>
        <div class="hd-bottom">
            <div style="height:10px"></div>
        	<!--<i class="home"><a href="javascript:;"></a></i>-->
        	<div class="nav-wrap">
                <ul class="nav ue-clear" id ="menulist">
                </ul>
            </div>
            <a href="javascript:;" class="nav-add-btn"></a>
            <div class="nav-btn">

            	<a href="javascript:;" class="nav-prev-btn"></a>
                <a href="javascript:;" class="nav-next-btn"></a>
            </div>
        </div>
    </div>
	<div id="bd">
    	<div class="sidebar">
        	<div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>

            <ul class="nav2">
                <li class="subnav-li" href="${ctx}/hgqyIndex/fxfqglhome" data-id="1"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">首页</span></a></li>
            </ul>
            <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
         <div class="main" style="height: 100%;">
        	<div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">
                   
                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content" style="height: 100%!important;">
            </div>
        </div> 
    </div>
    
    <div id="ft" class="ue-clear">
    	<div class="ft1 ue-clear">
        	<i class="ft-icon1"></i>
            <span>企业安全风险分区管理系统</span>
            <em>ZHONGAN LIANKE</em>
        </div>
        <div class="ft2 ue-clear">
        	<span>中安联科</span>
            <em id="project_version" onclick="openDialog('更新日志','${ctx}/system/xmversion/showhis')" style="padding-left: 10px;cursor: pointer;"> </em>
            <i class="ft-icon2"></i>
        </div>
    </div>
</div>
<div class="opt-panel skin-opt" style="top:37px;right:168px;display: none;">
   <ul class="ue-clear">
   		<li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dxj')">待巡检<span class="num" id="dxj">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dsh')">待审核<span class="num" id="dsh">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dsp')">待审批<span class="num" id="dsp">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=djc')">待检查<span class="num" id="djc">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dzg')">待整改<span class="num" id="dzg">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=xwj')">新文件<span class="num" id="xwj">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=pxtz')">培训通知<span class="num"id="pxtz">(0)</span></a></li>
        <li><a class="text" onclick="openDialog('消息查看','${ctx}/system/message/index?msgtype=dcwj')">调查问卷<span class="num"id="dcwj">(0)</span></a></li>
    </ul>
</div>
<div class="more-bab-list">
   <ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>

<div class="menuHolder">
	<div class="menuWindow">
		<ul class="p1">
			<li class="s1">
				<a href="#url">
					<img src="${ctxStatic}/model/images/menu_.png" style="margin: 8px 10px 0 0px;" />
				</a>
				<ul class="p2">
					<li class="s2">
						<a onclick="openHgqyIndex()"><span>主菜单</span></a>
					</li>
					<li class="s2">
						<a onclick="bigdataJump(1)"><span>重大危险源</span></a>
					</li>
					<li class="s2">
						<a onclick="systemJump(3,4311)"><span>人员在岗在位</span></a>
					</li>
					<li class="s2 b6">
						<a onclick="systemJump(4,4312)"><span>全流程管理</span></a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/model/js/index/core.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/jquery.dialog.js"></script>
<script type="text/javascript">
//遍历菜单生成html
var html="";
var firstlogin = true;
var Gdata=[];
var msgbool= false;
$(function(){
    //版本号获取
    $.ajax({
        type:"POST",
        url:"${ctx}/system/xmversion/maxversion",
        success:function(data){
            $("#project_version").text(data);
        }
    });

	//请求菜单数据
	$("#bd").height($(window).height()-$("#hd").outerHeight()-35);
	//指定菜单，缺少导致无法展开左侧菜单
	var menu = new Menu({
		defaultSelect: $('.nav2').find('li[data-id="1"]')	
	});
	
	$(window).resize(function(e) {
	    $("#bd").height($(window).height()-$("#hd").outerHeight()-35);
	});

	msgtx();
	
	$.ajax({
        type:"POST",
        url:"${ctx}/system/permission/hgqy/menujson?pid=${pid}",
        dataType: 'json', 
        success:function(data){
            Gdata = data[0].children;
        	showMenu();
        }
    });

	$.each($('.skin-opt li'),function(index, element){
		if((index + 1) % 3 == 0){
			$(this).addClass('third');	
		}
		$(this).css('background',$(this).attr('attr-color'));
	});
	
	$('.setting-skin').click(function(e) {
        $('.skin-opt').toggle();
        msgtx();
    });
	
	//离开隐藏控件
    $(".skin-opt").hover(function(){
    },function(){
	 	$('.skin-opt').toggle();
    });
	
	$('.skin-opt').click(function(e) {
        if($(e.target).is('li')){
		//	alert($(e.target).attr('attr-color'));
		}
    });
	
	$('.user-info').click(function(e) {
       $(this).toggleClass('active'); 
	   $('.user-opt').toggle();
    });
	
	hideElement($('.user-opt'), function(current, target){
	});
	
});

//消息提醒
function msgtx(){
	//if(!msgbool){
    $.post("${ctx}/system/message/msgjson",function(data){
 	   var msgtotal = 0;
        for(var item of data){
        		$("#"+item.msgtype).text("("+item.total+")");
        		if(item.msgtype=='dxj'||item.msgtype=='dsh'||item.msgtype=='dsp'||item.msgtype=='pxtz'||item.msgtype=='djc'||item.msgtype=='xwj'||item.msgtype=='dcwj' ||item.msgtype=='dzg'){
        			msgtotal+=item.total;
        		}
        }
        if(msgtotal>99){
     	   msgtotal = '...';
        }
        $("#msgtotal").text(msgtotal);
    });
    //msgbool=true;
 //}
}

//横向一级导航栏拼接显示
function showMenu(){
    //记录菜单个数
    var liNum = 0;
    $.each(Gdata, function(i, item) {
        liNum += 1
        var li="";
        html+='<li><a href="javascript:void(0);" id="'+item.id+'"><img src="${ctxStatic}/model/main/images/'+item.icon+'"/>'+item.name+'</a></li>';
    });
    $('#menulist').append(html);
    var totalWidth = 0, current = 1;

    //根据 横向一级导航栏 计算宽度
    totalWidth = liNum * 130;

    function currentLeft(){
        return -(current - 1) * 200;
    }

    //横向一级导航栏 最右方按钮点击 图标移动距离
    $('.nav-btn a').click(function(e) {
        var tempWidth = totalWidth - ( Math.abs($('.nav').css('left').split('p')[0]) + $('.nav-wrap').width() );
        if($(this).hasClass('nav-prev-btn')){
            if( parseInt($('.nav').css('left').split('p')[0]) < 0){
                current--;
                Math.abs($('.nav').css('left').split('p')[0]) > 200 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': 0}, 200);
            }
        }else{
            if(tempWidth  > 0)	{
                current++;
                tempWidth > 200 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': $('.nav').css('left').split('p')[0]-tempWidth-50}, 200);
            }
        }
    });

  //横向一级导航栏激活标签
	$(".nav li a").click(function(){
		if(firstlogin){
	    	//首次进来默认隐藏，加载首页地址
        	$(".sidebar-show").click();
			firstlogin = false;
	    }
	    $(".nav li a").removeClass("active");
	    $(this).addClass("active");
		$(".nav2").empty();
	    var id  = $(this).attr("id");
	    var html='';//父节点html
	    for(var item of Gdata){
			if(item.id==id){
				var childs = item.children;
			    if(childs&&childs.length>0){
					var f= true;
      				for(var subitem of childs){//二级菜单循环
      					if(subitem.children&&subitem.children.length>0){//存在三级菜单
      						var current = "current";
      						if(f){
      							f=false;
      						}else{
      							current = "";
      						}
      						html+='<li class="nav-li '+current+'"><a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">'+subitem.name+'</span></a>';
     						//三级菜单
     						html+='<ul class="subnav">';
        					for(ssubitem of subitem.children){
        						html+='<li class="subnav-li" data-id="'+ssubitem.id+'" href="${ctx }/'+ssubitem.href+'"><a href="javascript:;" class="ue-clear"><i class="subnav-icon fa '+ssubitem.icon+'"></i><span class="subnav-text">'+ssubitem.name+'</span></a></li>';
        					}
        					html+='</ul>';
      					}else{//不存在三级菜单
      						html+='<li class="nav-li" data-id="'+subitem.id+'" href="${ctx }/'+subitem.href+'"><a href="javascript:;" class="ue-clear"><i class="subNav-icon2 fa '+subitem.icon+'"></i><span class="nav-text" style="">'+subitem.name+'</span></a>';
      					}
      					html+='</li>';
      				}
      			}
			   	$(".nav2").append(html);
			    break;
			}/* else if(id=='0'){
				html+='<li class="nav-li" data-id="0" href="http://139.129.23.185:8090/demo/index.html"><a href="javascript:;" class="ue-clear"><span class="nav-text" style="margin-left:33px;">三维图案例1</span></a></li>';
				html+='<li class="nav-li" data-id="-1" href="http://139.129.23.185:8099/HHTJXF/index.html#"><a href="javascript:;" class="ue-clear"><span class="nav-text" style="margin-left:33px;">三维图案例2</span></a></li>';
				
				$(".nav2").append(html);
				break;
			} */
	    }
	    /* if($(".nav li a").eq(0).attr("class")=='active'){
	    	
	    } */
	});
	//首次进入默认激活第一个标签
	$(".nav li a").eq(0).click();
}
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

//屏幕改变事件
var resizeTimeout;
$(window).resize(function(){
	//$(".main").attr("style","overflow:auto;height:"+(document.documentElement.clientHeight - 149)+"px;");// 还原需解除注释
	$(".main").attr("style","overflow:auto;height:"+(document.documentElement.clientHeight - 100)+"px;");
	resizeTimeout = setTimeout(function() {
		
		// 还原需解除注释
		//$('iframe[name="myiframe"]').height($(window).height()-181);
		$('iframe[name="myiframe"]').height($(window).height()-105);
    }, 1);
});

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

//返回主菜单
function openHgqyIndex(){
	window.location.href="${ctx}/hgqyIndex/index";
}

function bigdataJump(type){
	window.location.href="${ctx}/hgqyIndex/bigdata/"+type;
}

function systemJump(type,pid){
	window.location.href="${ctx}/hgqyIndex/sys/"+type+"/"+pid;
}

$(window).ready(function(){
	$(".content>iframe").css("height","100%")
})

$(window).resize(function(){
	$(".content").css("height","100%")
})
</script>
</html>
