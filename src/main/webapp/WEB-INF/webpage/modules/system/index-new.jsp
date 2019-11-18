<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<%
    String logotitle=" ";String logoimg="/model/images/login/logo_qy.png";
    if(session.getAttribute("logotitle")!=null&&!session.getAttribute("logotitle").equals(""))
        logotitle=(String)session.getAttribute("logotitle")+" —— ";
    if(session.getAttribute("logoimg")!=null&&!session.getAttribute("logoimg").equals(""))
        logoimg=(String)session.getAttribute("logoimg");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/style.css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/main.css?v=1.0" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/index/nav.css" />
<script type="text/javascript" src="${ctxStatic}/jquery/jquery.js" ></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/global.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/nav.js"></script>
<script type="text/javascript" src="${ctxStatic}/model/js/index/Menu.js"></script>
<script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
        
<title><%=logotitle %></title>
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
</style>

<body>
<div id="container">
	<div id="hd">
    	<div class="hd-top">
            <h1 class="logo">
                <img id="comLogo" class="comLogo" src="${ctxStatic}/<%=logoimg %>"  onmouseover="layer.tips('点击logo可以修改哦！', '#comLogo',{time: 2000} );" onclick="updQyLogo()"/><!-- onclick="updQyLogo()" -->
                <span class="comName"><%=logotitle %>智能工厂HSE综合管理云平台</span>
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
                  <%--  <li><a href="${ctx}/static/templates/企业智慧安全、环保、消防一体化云平台使用手册(2018.5).pdf" target="_blank">帮助</a></li>--%>
                    <li><a href="javascript:void(0);" onclick="location.href='${ctx}/'">切换</a></li> 
                    <li><a class="close-btn exit" onclick="logout()"></a></li>
                </ul>
            </div>
            <div class="user-info">
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
                <li class="subnav-li" href="${ctx}/a/home" data-id="1"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">首页</span></a></li>
            </ul>
            <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
         <div class="main">
        	<div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">
                   
                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div> 
    </div>
    
    <div id="ft" class="ue-clear">
    	<div class="ft1 ue-clear">
        	<i class="ft-icon1"></i>
            <span>智慧安全管理系统</span>
            <em>ZHONGAN LIANKE</em>
        </div>
        <div class="ft2 ue-clear">
        	<span>中安联科</span>
            <em id="project_version" onclick="openDialog('更新日志','${ctx}system/xmversion/showhis')" style="padding-left: 10px;cursor: pointer;"> </em>
            <i class="ft-icon2"></i>
        </div>
    </div>
</div>
<div class="opt-panel skin-opt" style="top:37px;right:200px;display: none;">
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
	// 获取企业logo图片地址
	$.ajax({
        type:"POST",
        url:"${ctx}/bis/qyjbxx/getlogo",
        success:function(data){
        	if (data) {
	        	var logoUrl = data.split("||");
	            $("#comLogo").attr("src", '${ctx}' + logoUrl[0]);
	         }
        }
    });
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
        url:"${ctx}/system/permission/i/menujson",
        dataType: 'json', 
        success:function(data){
            Gdata = data;
        	showMenu();
    		//menuaction();
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
            console.log('点了')
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
					//var f= true;
      				for(var subitem of childs){//二级菜单循环
      						if(subitem.children&&subitem.children.length>0){//存在三级菜单
	      						html+='<li class="nav-li"><a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">'+subitem.name+'</span></a>';
      							//三级菜单
      							html+='<ul class="subnav">';
         						for(ssubitem of subitem.children){
         						    html+='<li class="subnav-li" data-id="'+ssubitem.id+'" href="${ctx }/'+ssubitem.href+'"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">'+ssubitem.name+'</span></a></li>';
         						}
         						html+='</ul>';
      						}else{//不存在三级菜单
      							html+='<li class="nav-li" data-id="'+subitem.id+'" href="${ctx }/'+subitem.href+'"><a href="javascript:;" class="ue-clear"><span class="nav-text" style="margin-left:33px;">'+subitem.name+'</span></a>';
      						}
      						//f=false;
      						html+='</li>';
      					}
      			    }
			   	$(".nav2").append(html);
			    break;
			}else if(id=='0'){
				html+='<li class="nav-li" data-id="0" href="http://139.129.23.185:8090/demo/index.html"><a href="javascript:;" class="ue-clear"><span class="nav-text" style="margin-left:33px;">三维图案例1</span></a></li>';
				html+='<li class="nav-li" data-id="-1" href="http://139.129.23.185:8099/HHTJXF/index.html#"><a href="javascript:;" class="ue-clear"><span class="nav-text" style="margin-left:33px;">三维图案例2</span></a></li>';
				
				$(".nav2").append(html);
				break;
			}
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
	    area: ["80%", "60%"],
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
	$(".main").attr("style","overflow:auto;height:"+(document.documentElement.clientHeight - 149)+"px;");
	resizeTimeout = setTimeout(function() {
		$('iframe[name="myiframe"]').height($(window).height()-181);
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

function updQyLogo(){
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ["400px", "280px"],
	    title: "修改企业logo",
    	maxmin: true, 
	    content: "${ctx}/bis/qyjbxx/updlogo",
	    btn: ['确定','取消'],
		yes: function(index, layero){
			 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
		} 	    
	}); 
}
</script>

</html>
