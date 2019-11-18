<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<%
    String logotitle="化工企业安全生产信息化管理平台"; String logoimg="/static/model/main/images/logo.png";
//    if(session.getAttribute("logotitle")!=null&&!session.getAttribute("logotitle").equals(""))
//        logotitle=(String)session.getAttribute("logotitle");
//    if(session.getAttribute("logoimg")!=null&&!session.getAttribute("logoimg").equals(""))
//        logoimg=(String)session.getAttribute("logoimg");
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>化工企业安全生产信息化管理</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/style.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/otherstyle.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/skin_/main.css"/>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/global.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/ace/assets/css/font-awesome.css" />
    <script src="${ctxStatic}/layer-v2.0/layer/layer.js"></script>
</head>
<style>
    .logo{
        display:flex;font-size:19px;align-items: center;height:100%
    }
    .comLogo{
        width: auto;
        height: 58px;
        margin: 0 7px 0 15px;
    }
</style>
<body>
<div id="container">
    <div style="background: #1394ea">
        <div id="navheader">
            <div class="topleft">
                <div class="logo">
                    <img id="comLogo" class="comLogo" style="margin: 0 5px 0 7px;" src="<%=logoimg %>"   />
                    <div class="comInfo" style="font-size:16px;text-align:center;color:#ffffffe8;font-weight:550;margin-top: -2px;">
                        <span class="comName" style="font-size:18px;margin-bottom: 1px;"><%=logotitle %></span>
                    </div>
                </div>
            </div>
            <div class="topright">
                <ul>
                    <li><span><img src="${ctxStatic}/model/images/home/help.png" title="帮助" class="helpimg"/></span><a
                            href="#">帮助</a></li>
                    <li><a href="#">关于</a></li>
                    <li><a href="${ctx}/a/logout" target="_parent">退出</a></li>
                </ul>
                <div class="user">
                    <a href="javascript:void(0);" onclick="openDialogInfo('个人信息设置','${ctx}/system/user/infor')"
                       title="个人档案"><span><shiro:principal property="name"/></span></a>
                    <a href="javascript:void(0);" onclick="openDialogInfo('消息中心','${ctx }/system/message/index')"
                       title="消息中心"><i>消息</i>
                        <b id="msgtotal"></b></a>
                </div>
            </div>

            <div class="hd-bottom">
                <div class="nav-wrap">
                    <ul id="horizontalnav" class="nav ue-clear">

                    </ul>
                </div>
                <div class="nav-btn">
                    <a href="javascript:;" class="nav-prev-btn"></a>
                    <a href="javascript:;" class="nav-next-btn"></a>
                </div>
            </div>
        </div>
    </div>

    <div id="bd">
        <iframe width="100%" scrolling="no" height="100%" id="mainIframe" src="${ctx}/a/nav" frameborder="0"></iframe>
    </div>

    <div id="ft" class="ue-clear">
        <div class="ft1 ue-clear">
            <i class="ft-icon1"></i> <span>江苏中安联科信息技术有限公司&nbsp;&nbsp;copyright  2018 </span>

        </div>
        <div class="ft2 ue-clear">

            <em>V3.0 2018</em>
            <i class="ft-icon2"></i>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var totalWidth = 0,G_data=[];

    //打开对话框(查看)
    function openDialogInfo(title, url, width, height) {
        layer.open({
            type: 2,
            shift: 1,
            area: ['80%', '60%'],
            //offset: ['100px', '200px']
            title: title,
            maxmin: true,
            content: url,
            btn: ['关闭'],
            cancel: function (index) {
            }
        });
    }

    $(function () {
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

        //请求菜单数据
        $.ajax({
            type: "POST",
            url: "${ctx}/system/permission/i/menujson",
            dataType: 'json',
            success: function (data) {
                G_data= data;
                showMenu(data);
            }
        });

        $.post("${ctx}/system/message/msgjson",function(data){
            var msgtotal = 0;
            for(var item of data){
                if(item.msgtype=='dxj'||item.msgtype=='dsh'||item.msgtype=='dsp'||item.msgtype=='pxtz'||item.msgtype=='djc'||item.msgtype=='xwj'){
                    msgtotal+=item.total;
                }
            }
            if(msgtotal>99){
                msgtotal = '...';
            }
            $("#msgtotal").text(msgtotal);
        });
    });

    //加载横向菜单（一级菜单）
    function showMenu(data) {
        var html = "";
        var firstid=data[0].id;
        $.each(data, function (i, item) {
            html += '<li><a onclick="onloadChildMenu('+item.id+')" id=a'+item.id+' href="javascript:;" ' + ((i == 0) ? 'class="selected"' : '') + '><img src="${ctxStatic}/model/main/images/' + item.icon + '" title="'+item.name+'" /><h2>' + item.name + '</h2></a></li>';
        });
        $('#horizontalnav').append(html);
        totalWidth = $('.nav').find('li').outerWidth() * data.length;
        $('.nav').width(totalWidth);
        //防止首次加载找不到iframe的nav class
        $('#mainIframe').load(function(){
            onloadChildMenu(firstid);
        });
    }

    function  onloadChildMenu(id){
        $('.nav').find('li a').removeClass("selected");
        $('#a'+id).addClass("selected");
        for(var item of G_data){
            var html='';
            if(item.id ==id){
                //二级菜单
                if(item.children && item.children.length>0) {
                    $.each(item.children, function (i, item) {
                        if(item.children && item.children.length>0){
                            html += '<li id="cap" class="nav-li" '+(item.href ? 'href="${ctx}/' + item.href + '" data-id="' + item.id + '" ' : '')+'>';
                            html += '<i class="nav-icon"></i><a href="javascript:;" class="ue-clear"><span class="nav-ivon"></span><span class="nav-text">' + item.name + '</span></a>';
                        }else{
                            html += '<li id="cap" class="nav-li" '+(item.href ? 'href="${ctx}/' + item.href + '" data-id="' + item.id + '" ' : '')+'>';
                            html += '<i class="nav-icon-hide fa '+item.icon+'"></i><a href="javascript:;" class="ue-clear"><span class="nav-ivon"></span><span class="nav-text">' + item.name + '</span></a>';
                        }
                        //三级菜单
                        if(item.children && item.children.length>0){
                            html+='<ul class="subnav">';
                            $.each(item.children, function (i, item) {
                                if(item.children && item.children.length>0){
                                    html+='<li class="subnav-li" '+(item.href ? 'href="${ctx}/' + item.href + '" data-id="' + item.id + '" ' : '')+'><a href="javascript:;" class="ue-clear"><i class="nav-icon"></i><span class="subnav-text">'+item.name+'</span></a>';
                                    html+='<ul class="three-nav">';
                                    //四级菜单
                                    $.each(item.children, function (i, item) {
                                        html+='<li '+(item.href ? 'href="${ctx}/' + item.href + '" data-id="' + item.id + '" ' : '')+' class="threenav-li"><i class="subNav-icon fa '+item.icon+'"></i><a href="javascript:;"><span>'+item.name+'</span></a></li>';
                                    });
                                    html+='</ul>';
                                    html+='</li>';
                                }else{
                                    html+='<li class="subnav-li" '+(item.href ? 'href="${ctx}/' + item.href + '" data-id="' + item.id + '" ' : '')+'><a href="javascript:;" class="ue-clear"><i class="subNav-icon fa '+item.icon+'"></i><span class="subnav-text">'+item.name+'</span></a></li>';
                                }
                            });
                            html+='</ul>';
                        }
                        html += '</li>';
                    });
                };
                $('#mainIframe').contents().find(".sidebar h2 span").html(item.name);
                $('#mainIframe').contents().find(".nav").html(html);
                $('#mainIframe').contents().find(".tree-list").addClass("outwindow");
                break;
            }
        }
    }

    $("#bd").height($(window).height() - $("#hd").outerHeight() - 112);

    $(window).resize(function (e) {
        $("#bd").height($(window).height() - $("#hd").outerHeight() - 112);
    });

    (function () {
        var current = 1;

        function currentLeft() {
            return -(current - 1) * 87 * 4;
        }

        $('.nav-btn a').click(function (e) {
            var tempWidth = totalWidth - (Math.abs($('.nav').css('left').split('p')[0]) + $('.nav-wrap').width());
            if ($(this).hasClass('nav-prev-btn')) {
                if (parseInt($('.nav').css('left').split('p')[0]) < 0) {
                    current--;

                    Math.abs($('.nav').css('left').split('p')[0]) > 93 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': 0}, 200);
                }
            } else {
                if (tempWidth > 0) {
                    current++;
                    tempWidth > 87 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': $('.nav').css('left').split('p')[0] - tempWidth}, 200);
                }
            }
        });


        $.each($('.skin-opt li'), function (index, element) {
            if ((index + 1) % 3 == 0) {
                $(this).addClass('third');
            }
            $(this).css('background', $(this).attr('attr-color'));
        });

        $('.setting-skin').click(function (e) {
            $('.skin-opt').show();
        });

        $('.skin-opt').click(function (e) {
            if ($(e.target).is('li')) {
                alert($(e.target).attr('attr-color'));
            }
        });

        $('.hd-top .user-info .more-info').click(function (e) {
            $(this).toggleClass('active');
            $('.user-opt').toggle();
        });

        $('.logo-icon').click(function (e) {
            $(this).toggleClass('active');
            $('.system-switch').toggle();
        });
    })();


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