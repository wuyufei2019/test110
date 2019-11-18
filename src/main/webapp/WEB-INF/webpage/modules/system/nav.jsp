<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/style.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/model/css/home/skin_/nav.css"/>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/global.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/Menu.js"></script>
    <script type="text/javascript" src="${ctxStatic}/model/js/home/nav.js"></script>
    <title>底部内容页</title>
</head>
<style>
	.sidebar::-webkit-scrollbar{display:none}
</style>
<body>
<div id="container">
    <div id="bd" style="background: #ffffff;">
        <div class="sidebar" style="overflow-y: scroll;">
            <div class="sidebar-bg"></div>
            <h2 style="pointer-events: none;"><a href="javascript:;"><i class="h2-icon" ></i><span>导航菜单</span></a></h2>
            <ul class="nav" style="background: rgba(62, 164, 236, 0.1); padding-bottom: 0px;">
                <li style="display: none" class="nav-li" href="${ctx}/a/home" data-id="1"><a
                        href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">首页
                </span></a></li>
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
<script type="text/javascript">
    var menu = new Menu({
    });

    $('.sidebar h2').click(function (e) {
        $('.tree-list').toggleClass('outwindow');
        $('.nav').toggleClass('outwindow');
    });

    $(document).click(function (e) {
        if (!$(e.target).is('.tab-more')) {
            $('.tab-more').removeClass('active');
            $('.more-bab-list').hide();
        }
    });
</script>
</html>
