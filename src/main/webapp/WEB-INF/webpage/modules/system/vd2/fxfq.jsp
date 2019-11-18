<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <title>风险分区大数据</title>
    <link href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-select2/htools.select.skin.css">
    <link href="${ctx}/static/model/vd2/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/static/model/vd2/css/com.css" rel="stylesheet" type="text/css" media="all"/>

    <script src="${ctx}/static/model/vd2/js/echarts.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/static/echarts/theme/vintage.js"></script>
    <script type="text/javascript" src="${ctx}/static/echarts/chart/echarts-liquidfill.min.js"></script>
    <script src="${ctx}/static/model/vd2/js/particles.min.js"></script>
    <script src="${ctx}/static/jquery/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/jquery-select2/jquery.htools.select.js"></script>
    <script src="${ctx}/static/layer-v2.0/layer/layer.js"></script>

    <style>
        body {
            background: #1e2840 url(${ctx}/static/model/vd2/img/titl__.jpg);
            background-size: 100%;
            background-position: top center;
            background-repeat: no-repeat;
        }

        .tith2 {
            width: calc(100% - 50px);
            padding-top: 0px;
            margin-left: 15px;
            text-align: left;
            padding-left: 20px;
            background: url(${ctx}/static/model/vd2/img/tit24.png);
            background-repeat: no-repeat;
            background-position: left center;
            color: rgb(115, 149, 198);
            font-weight: 600;
        }

        .tith2 > span:hover {
            color: #36a4fb;
        }

        .ggk {
            text-align: left;
            font-size: 12px;
            padding: 0 15px;
            height: 78%;
            width: 89%;
            overflow-y: scroll;
        }

        .b-tit {
            font-size: 14px;
            color: #ffba49;
            font-weight: 600;
            margin-top: 8px !important;
        }

        .aleftboxttop, .aleftboxtbott, .aleftboxtmidd, .aleftboxtbott {
            background: url(${ctx}/static/model/vd2/img/sbBg__.png);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            background-position: top center;
            width: 100%;
            padding-top: 2%;
            margin-bottom: 0.5% !important;
        }

        .amiddboxttop {
            overflow: hidden;
            background: url(${ctx}/static/model/vd2/img/mid_bg__.png);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            background-position: top center;
            width: 100%;
        }

        .ggk_text {
            /*color: #0092dc;*/
        }

        .ggk_text span {
            color: #00e2ff;
            font-size: 14px;
            font-weight: 600;
        }

        .amiddboxttop_map {
            height: 100%;
        }

        .amiddboxttop .tab_ctt {
            height: 86%;
        }

        #cqmnt_ctt, #fxzyfbt_ctt {
            height: 93%;
        }

        #fxzyfbt_ctt span.camera_l {
            background: url(${ctx}/static/model/images/fxgk/wxzy.png);
            background-repeat: no-repeat;
            background-size: cover;
            background-position: 0 0;
            width: 28px;
            height: 28px;
            display: inline-block;
            position: absolute;
        }

        .topbnt_left {
            width: 28%;
        }

        .topbnt_left ul {
            padding-top: 3%;
            padding-left: 9.5%;
            width: 100%;
        }

        .topbnt_left li a {
            text-decoration: none;
            color: #7fa0d0;
        }

        .topbnt_right {
            padding-top: 0.6%;
            padding-right: 2.5%;
            width: 21%;
        }

        .topbnt_right li a {
            text-decoration: none;
            color: #5e8ace;
        }

        .amiddboxttop_map {
            background: #10548cfc url(${ctx}/static/model/vd2/img/nodata.png);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            background-position: center;
            position: relative;
            width: 94%;
            margin: 1.6% 3% 0;
        }

        .amiddboxttop_map2 {
            background: #10548cfc url(${ctx}/static/model/vd2/img/nodata.png) no-repeat;
            background-position: center;
        }


        .show_ {
            display: unset;
        }

        .videoWindow {
            z-index: 999;
            position: fixed;
            background: rgba(13, 36, 93, 0.98);
            box-shadow: 0 0 16px 0 #0009318f;
            border-radius: 10px;
            display: none;
        }

        @media screen and (max-width: 10000px) {
            .videoWindow {
                width: 760px;
                height: 560px;
                margin-left: calc(50% - 380px);
                margin-top: 7%;
                color: #e6e6e6;
            }

            .f2 {
                font-size: 16px;
            }

            .fn2 {
                font-size: 20px;
                font-weight: 600;
            }

            #fxfbt_ctt .btmsth > div > img, #fxzyfbt_ctt .btmsth > div > img {
                width: 22px;
                height: 22px;
                margin-right: 12px;
            }
        }

        @media screen and (max-width: 1366px) {
            .videoWindow {
                width: 640px;
                height: 460px;
                margin-left: calc(50% - 320px);
                margin-top: 7%;
                color: #e6e6e6;
            }

            #fxfbt_ctt .btmsth > div > img, #fxzyfbt_ctt .btmsth > div > img {
                width: 18px;
                height: 18px;
                margin-right: 10px;
            }
        }

        #fxfbt_ctt .btmsth, #fxzyfbt_ctt .btmsth {
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin: 1.2% 10% 0;
        }

        #fxfbt_ctt .btmsth > div {
            display: flex;
        }

        .videoWindow .vw_h {
            display: flex;
            justify-content: space-between;
            padding: 4px 5px 4px 12px;
            align-items: center;
        }

        .videoWindow .vw_h .vw_cls {
            cursor: pointer;
        }

        .videoWindow .vw_b {
            height: calc(100% - 39px);
            background: rgba(9, 97, 150, 0.6392156862745098);
            display: flex;
        }

        .videoWindow .vw_b_l {
            height: 100%;
            width: 100%;
        }

        .videoWindow .vw_b_r {
            height: 100%;
            width: 25%;
            display: none;
        }

        .videoWindow .vw_vdo {
            width: 97%;
            margin: 1.5%;
            height: 96.5%;
            background: black;
        }

        .videoWindow .vw_vdo2 {
            width: 91%;
            margin: 4.5%;
            height: 96.5%;
        }

        .videoWindow .vw_vdo2 .sblist {
            height: calc(100% - 25px);
            margin: 5px 0 0;
        }

        .menu2ul {
            display: none;
            padding: 5px 0 5px 0px;
        }

        .menuul .menu2 {
            padding-left: 30px;
        }

        .menuul li {
            padding-top: 6px;
            padding-bottom: 6px;
            padding-left: 16px;
        }

        .menuul li {
            cursor: pointer;
        }

        .menuul li.chos_ {
            background: #3190c2;
        }

        .menuul .menu1ul .menu1 {
            display: flex;
            align-items: center;
        }

        .menuul .menu1ul .menu1 > div {
            width: 12px;
            height: 12px;
            margin-left: -18px;
            margin-right: 6px;
            background: url(${ctx}/static/model/vd2/img/hideson.png) no-repeat;
        }

        .menuul .menu1ul .menu1.show_ > div {
            width: 12px;
            height: 12px;
            margin-top: -3px;
            background: url(${ctx}/static/model/vd2/img/showson.png) no-repeat;
        }

        #previous, #previous2 {
            left: 0px;
            border-radius: 0px 7px 7px 7px;
            background: url(${ctx}/static/model/vd2/img/tol.png) no-repeat;
            background-position: center;
        }

        #next, #next2 {
            right: 0px;
            border-radius: 7px 0px 0px 7px;
            background: url(${ctx}/static/model/vd2/img/tor.png) no-repeat;
            background-position: center;
        }


        #ldsk_ctt .ctt {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            padding: 0px 5px 5px;
            background: white;
        }

        #cate_ .ctt .ctt_item {
            margin: 8px 6px 2px;
            background: white;
            padding: 7px 15px;
            box-shadow: 0 0 6px 0 #e0e0e0;
        }

        #cate_ .tit2 {
            padding: 5px;
            color: #666;
            font-size: 12px;
            width: 100%;
            background: white;
            border-bottom: 0.1px solid #e8e8e8;
        }

        #cate_ .tit2 span {
            font-size: 12px;
            padding: 0 5px 4px;
        }

        #cate_ span.t_chos {
            color: #2f7ec3;
            border-bottom: 2px solid #338ddc;
            font-size: 13px;
        }

        .ctt_item_img {
            text-align: center;
            padding: 1px;
            font-size: 24px;
        }

        .dragAble {
            position: relative;
            cursor: move;
        }
        .img-con {
            position: relative;
            width: 94%;
            height: 100%;
            overflow: hidden;
        }
    </style>
</head>
<body style="">
<div class="wpbox">
    <!-- 视频监控窗 -->
    <div class="videoWindow">
        <div class="vw_h">
            <span class="f2" style="color: #81cfff;">视频监控</span>
            <span class="vw_cls fn2" style="padding: 7px 12px;">X</span>
        </div>
        <div class="vw_b">
            <div class="vw_b_l">
                <div class="vw_vdo" style="text-align: center;">
                    <img src="${ctxStatic}/model/vd2/img/noinfo.png" style="height:300px;margin-top: 40px"/>
                </div>
            </div>
            <div class="vw_b_r">
                <div class="vw_vdo2 f3">
                    <div class="">设备列表</div>
                    <div class="sblist">
                        <ul class="menuul">
                            <li class="menu1">设备1</li>
                            <ul class="menu1ul">
                                <li class="menu1">
                                    <div></div>
                                    设备2
                                </li>
                                <ul class="menu2ul">
                                    <li class="menu2">设备2-1</li>
                                    <li class="menu2">设备2-2</li>
                                </ul>
                            </ul>
                            <li class="menu1">设备3</li>
                            <ul class="menu1ul">
                                <li class="menu1">
                                    <div></div>
                                    设备4
                                </li>
                                <ul class="menu2ul">
                                    <li class="menu2">设备4-1</li>
                                    <li class="menu2">设备4-2</li>
                                </ul>
                            </ul>
                        </ul>
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
                                <div class="pic"
                                     style="background:#00075b url(${ctx}/static/model/vd2/img/m3.png) no-repeat;background-size:100%;background-position:center;"></div>
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

                    <%--<div class="tab_ f3">
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
                    </div>--%>

                    <%--<div class="tab_ctt">
                        <div id="zschart2" style="height: 100%;"></div>
                    </div>--%>
                </div>
                <%--<div class="aqzs_b_r">
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
                                        <img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span
                                            class="rsn f2">定期指标</span>
                                        <span class="lilab">已过期</span>
                                    </div>
                                    <div class="libd">
                                        <div class="pic"
                                             style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
                                        <div class="info">
                                            <span>点位名称：<span>随手拍</span></span>
                                            <span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
                                            <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-08</span></span></span>
                                            <span>计划整改：<span>达莎博<span class="clrr">2019-10-08</span></span></span>
                                        </div>
                                    </div>
                                </li>
                                <li class="list_ f3 bg">
                                    <div class="lihd" style="margin-top: 5px;">
                                        <img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span
                                            class="rsn f2">定期指标</span>
                                        <span class="lilab">已过期</span>
                                    </div>
                                    <div class="libd">
                                        <div class="pic"
                                             style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
                                        <div class="info">
                                            <span>点位名称：<span>随手拍</span></span>
                                            <span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
                                            <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-08</span></span></span>
                                            <span>计划整改：<span>达莎博<span class="clrr">2019-10-08</span></span></span>
                                        </div>
                                    </div>
                                </li>
                                <li class="list_ f3">
                                    <div class="lihd" style="margin-top: 5px;">
                                        <img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span
                                            class="rsn f2">定期指标</span>
                                        <span class="lilab">已过期</span>
                                    </div>
                                    <div class="libd">
                                        <div class="pic"
                                             style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
                                        <div class="info">
                                            <span>点位名称：<span>随手拍</span></span>
                                            <span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
                                            <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-08</span></span></span>
                                            <span>计划整改：<span>达莎博<span class="clrr">2019-10-08</span></span></span>
                                        </div>
                                    </div>
                                </li>
                                <li class="list_ f3 bg">
                                    <div class="lihd" style="margin-top: 5px;">
                                        <img class="_ic" src="${ctx}/static/model/vd2/img/ic4_.png"/><span
                                            class="rsn f2">定期指标</span>
                                        <span class="lilab">已过期</span>
                                    </div>
                                    <div class="libd">
                                        <div class="pic"
                                             style="background:#00075b url(${ctx}/static/model/vd2/img/m2.png) no-repeat;background-size:100%;background-position:center;"></div>
                                        <div class="info">
                                            <span>点位名称：<span>随手拍</span></span>
                                            <span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>
                                            <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>达莎博<span>2019-10-08</span></span></span>
                                            <span>计划整改：<span>达莎博<span class="clrr">2019-10-08</span></span></span>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>--%>
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
                    <span class="f2">企事业单位管理员<span class="clrw">&nbsp;&nbsp;&nbsp;&nbsp;7</span></span>
                </div>
                <div class="">
                    <span class="f2">企业安全管理员<span class="clrw">&nbsp;&nbsp;&nbsp;&nbsp;3</span></span>
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
                            <div class="pic"
                                 style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
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
                            <div class="pic"
                                 style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
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
        <%--<div id="pub_slider" class="f3">
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
                            <span class="f2">有毒气体</span>
                        </div>
                        <div class="libd">
                            <div class="pic"
                                 style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
                            <div class="info">
                                <span>传感器名称：<span>江苏</span></span>
                                <span>传&nbsp;感&nbsp;器&nbsp;ID：<span>JACS001</span></span>
                                <span>区&nbsp;域&nbsp;位&nbsp;置：<span>江苏-昆山开发区</span></span>
                                <span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：<span><i
                                        class="fa fa-circle clrr"></i>失联</span></span>
                                <span>失&nbsp;联&nbsp;时&nbsp;间：<span>2019-10-08 14:03</span></span>
                            </div>
                        </div>
                    </li>
                    <li class="list_ f3">
                        <div class="lihd" style="margin-top: 5px;">
                            <span class="f2">储罐监测</span>
                        </div>
                        <div class="libd">
                            <div class="pic"
                                 style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
                            <div class="info">
                                <span>传感器名称：<span>江苏</span></span>
                                <span>传&nbsp;感&nbsp;器&nbsp;ID：<span>JACS001</span></span>
                                <span>区&nbsp;域&nbsp;位&nbsp;置：<span>江苏-昆山开发区</span></span>
                                <span>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：<span><i
                                        class="fa fa-circle clrr"></i>失联</span></span>
                                <span>失&nbsp;联&nbsp;时&nbsp;间：<span>2019-10-08 12:53</span></span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>--%>

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
                    <span id="fxdname"></span><span id="fxdcolor"></span>
                </div>
                <div class="t_info_b">
                    <div class="pic_" id="fxdpic">

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
                        <div class="" id="bw">
                        </div>
                        <div class="" id="ydzfx">
                        </div>
                        <div class="" id="jczt">
                        </div>
                        <div class="" id="zjjcr">
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
                <%--<div id="xjxq_" class="">
                    <div class="min_tit f3">
                        隐患详情
                    </div>
                </div>--%>
            </div>

            <div class="tab_ctt">
                <div id="qhxq_ctt">
                    <div class="head">
                        <div class="chos" onclick="getYhInfo('all')">
                            <span class="f3">全部<span id="all">0</span></span>
                        </div>
                        <div class="" onclick="getYhInfo('0')">
                            <span class="f3">待整改<span class="clrr" id="dzg">0</span></span>
                        </div>
                        <div class="" onclick="getYhInfo('1')">
                            <span class="f3">待复查<span class="" id="dfc">0</span></span>
                        </div>
                        <div class="" onclick="getYhInfo('3')">
                            <span class="f3">整改完成<span class="" id="zgwc">0</span></span>
                        </div>
                    </div>
                    <div class="left2_table">
                        <ul style="margin-right: 4px;" id="yhxq">

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
                                    <div class="pic"
                                         style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/ic1_.png) no-repeat;background-size:100%;background-position:center;"></div>
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
                                    <div class="pic"
                                         style="display:none;background:#00075b url(${ctx}/static/model/vd2/img/comInfo1.png) no-repeat;background-size:100%;background-position:center;"></div>
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
                <li class="weather"><a href="#">${now}</a></li>
            </ul>
        </div>
        <h1 class="tith1 fl" style="color: #89b1f9;">安全风险分区大数据</h1>
        <div class=" fr topbnt_right">
            <ul>
                <li><a onclick="req()" style="cursor: pointer">导航菜单</a></li>
            </ul>

        </div>
    </div>
    <!-- bnt end -->
    <div class="left1" style="width: 28%">
        <div class="aleftboxttop" style="height: 26.7%;margin-bottom: 1.5%;">
            <h2 class="tith2 ptm1">风险研判和安全承诺公告</h2>
            <div id="nogg" style="text-align: center;display: none;">
                <img src="${ctx}/static/model/images/hgqy/nogg.png" height="180px">
            </div>
            <div class="ggk" style="color: #b3b3b3">
                <div class="b-tit" style="margin-top: 5px">企业状态</div>
                <div class="ggk_text" id="qyzt"></div>
                <div class="b-tit" style="margin-top: 5px">企业承诺</div>
                <div class="ggk_text" id="qycn"></div>
                <div style="display: flex;">主要负责人：&nbsp;&nbsp;<span id="zyfzr"></span></div>
            </div>
        </div>

        <div class="aleftboxtbott" style="height: 21.8%;margin-bottom: 1.5%;">
            <h2 class="tith2">预警提醒</h2>
            <div class="lefttoday_number lb_h f3" style=" color: #dedede;">
                <div id="aqry_" class="v_btn_yj">
                    <img src="${ctx}/static/model/vd2/img/comInfo1.png">
                    <div>
                        <span class="fn2 clr_lg">${fxyjCount}</span>
                        <span>风险预警</span>
                    </div>
                </div>
                <div id="fxd_" class="v_btn_yj">
                    <img src="${ctx}/static/model/vd2/img/ic_2.png">
                    <div>
                        <span class="fn2 clr_lg">0</span>
                        <span>复评预警</span>
                    </div>
                </div>
                <div id="tzsb_" class="v_btn_yj">
                    <img src="${ctx}/static/model/vd2/img/ic_3.png">
                    <div>
                        <span class="fn2 clr_lg">${bgyjCount}</span>
                        <span>变更预警</span>
                    </div>
                </div>
                <div id="dqyh_" class="v_btn_yj">
                    <img src="${ctx}/static/model/vd2/img/ic_4.png">
                    <div>
                        <span class="fn2 clr_lg">${dqyhCount}</span>
                        <span>当前隐患</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="aleftboxtmidd" style="margin-bottom: 1.5%;">
            <h2 class="tith2">风险点统计</h2>
            <div id="aleftboxtmidd" class="aleftboxtmiddcont"></div>
        </div>

        <div class="aleftboxtbott" style="height: 16.9%;">
            <h2 class="tith2">重大危险源<span style="float: right;width: auto;cursor: pointer" onclick="bigdataJumpNew(1)">更多>></span>
            </h2>
            <div class="lefttoday_number lb_h f3">
                <div id="slider-wrap">
                    <ul id="slider__" style="color: #dedede;">
                        <li data-color="#1abc9c">
                            <div>
                                <div id="pub_" class="v_btn">
                                    <img src="${ctx}/static/model/vd2/img/ic_9.png">
                                    <div>
                                        <span class="fn1 clr_lg" id="ydqt">${qtCount}</span>
                                        <span>气体数量</span>
                                    </div>
                                </div>
                                <div id="pub_" class="v_btn">
                                    <img src="${ctx}/static/model/vd2/img/ic_14.png">
                                    <div>
                                        <span class="fn1 clr_lg" id="spjk">${spCount}</span>
                                        <span>视频监控</span>
                                    </div>
                                </div>
                            </div>
                            <i class="fa fa-image"></i>
                        </li>

                        <li data-color="#3498db">
                            <div>
                                <div id="pub_" class="v_btn">
                                    <img src="${ctx}/static/model/vd2/img/ic_15.png">
                                    <div>
                                        <span class="fn1 clr_lg" id="gwgy">${gwgyCount}</span>
                                        <span>高危工艺</span>
                                    </div>
                                </div>

                                <div id="pub_" class="v_btn">
                                    <img src="${ctx}/static/model/vd2/img/ic_13.png">
                                    <div>
                                        <span class="fn1 clr_lg" id="cgxx">${cgCount}</span>
                                        <span>储罐信息</span>
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
    </div>
    <!-- left1 end -->
    <div class="mrbox_topmidd" style="width: 70.3%;">
        <div class="amiddboxttop" style="height: 98.4%;padding-top: 1%;">
            <h2 class="tith2 pt2">风险点一览</h2>
            <div class="tab_">
                <div id="cqmnt_" class="chos">
                    <div class="min_tit f3">
                        风险四色图
                    </div>
                </div>
                <div id="fxzyfbt_" class="">
                    <div class="min_tit f3">
                        危险作业分布图
                    </div>
                </div>
                <div id="fxfbt_">
                    <div class="min_tit f3">
                        风险分布图
                    </div>
                </div>
                <div id="fxdlb_" class="">
                    <div class="min_tit f3">
                        风险清单
                    </div>
                </div>
                <div id="ldsk_" class="">
                    <div class="min_tit f3">
                        两单三卡
                    </div>
                </div>
            </div>

            <%--<div class="tab_ctt">--%>
                <%--<div id="cqmnt_ctt" style="height: 93%">--%>
                    <%--<div class="amiddboxttop_map" style="">--%>
                        <%--<span class="camera_l vw_ic" style=" top:34%;left:32%"></span>--%>
                        <%--<span class="camera_l vw_ic" style=" top:10%;left:10%"></span>--%>
                        <%--<span id="fxdxq_" class="fxd_ic_1" style=" top:5%;left:40%"></span>--%>
                        <%--<span class="camera_l vw_ic" style=" top:10%;left:50%"></span>--%>
                        <%--<span id="fxdxq_" class="fxd_ic_2" style=" top:30%;left:75%"></span>--%>
                        <%--<span id="fxdxq_" class="fxd_ic_3" style=" top:5%;left:92%"></span>--%>
                        <%--<span id="fxdxq_" class="fxd_ic_4" style=" top:40%;left:83%"></span>--%>
                    <%--</div>--%>
            <div class="tab_ctt" style="height: 88%">
                <div id="cqmnt_ctt" style="height: 93%">
                    <div></div>
                    <div class="amiddboxttop_map img-con" style="background: unset;text-align: center;">
                        <img id="fxdsst" src="${ctx}/static/model/vd2/img/nodata.png" class="dragAble" />
                    </div>
                    <%--<div class="btmsth f3">
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_1.png"/>重大风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_2.png"/>较大风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_3.png"/>一般风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_4.png"/>低风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/camera_l.png"/>视频监控</div>
                    </div>--%>
                </div>
                <div id="fxzyfbt_ctt" style="display:none">
                    <div class="amiddboxttop_map amiddboxttop_map2" style="background-size: unset" id="fxzyfbtt">
                        <%--<span class="fxd_ic_1 camera_l" style=" top:34%;left:32%" onclick="sss()"></span>
                        <span class="fxd_ic_2 camera_l" style=" top:10%;left:10%"></span>
                        <span id="1fxdxq_" class="fxd_ic_3  camera_l" style=" top:5%;left:40%"></span>
                        <span class="fxd_ic_4 camera_l" style=" top:10%;left:50%"></span>
                        <span id="1fxdxq_" class="fxd_ic_5 camera_l" style=" top:30%;left:75%"></span>
                        <span id="1fxdxq_" class="fxd_ic_6 camera_l" style=" top:5%;left:92%"></span>
                        <span id="1fxdxq_" class="fxd_ic_7 camera_l" style=" top:40%;left:83%"></span>--%>
                    </div>
                    <div class="btmsth f3" style="display: flex;align-items: center;">
                        <div class="" style="display: flex;align-items: center;margin-right: 20px;">
                            <img class="_ic" style="margin-right: 8px;" src="${ctx}/static/model/images/fxgk/wxzy.png"/>危险作业
                        </div>
                        <div class="" style="display: flex;align-items: center;">
                            <img class="_ic" style="margin-right: 8px;" src="${ctx}/static/model/images/fxgk/red1.png"/>动火作业
                        </div>
                    </div>
                </div>
                <div id="fxfbt_ctt" style="height: 93%;display:none">
                    <div class="amiddboxttop_map" style="background-size: unset" id="dituContent2">
                    </div>
                    <div class="btmsth f3">
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_1.png"/>重大风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_2.png"/>较大风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_3.png"/>一般风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/fxd_4.png"/>低风险</div>
                        <div class=""><img class="_ic" src="${ctx}/static/model/vd2/img/camera_l.png"/>视频监控</div>
                    </div>
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
                        <c:forEach items="${fxqd}" var="s">
                            <div id="fxdxq_" class="list_li" onclick="getFxdInfo(${s.id},'${s.m9}')">
                                <div class="w25">${s.m1}</div>
                                <div class="w20">${s.m2}</div>
                                <div class="w20">${s.m4}</div>
                                <div class="w20">${s.m8}</div>
                                <div class="w15">
                                    <c:if test="${s.m9 eq '1'}">
                                        <div class="bgr">红</div>
                                    </c:if>
                                    <c:if test="${s.m9 eq '2'}">
                                        <div class="bgo">橙</div>
                                    </c:if>
                                    <c:if test="${s.m9 eq '3'}">
                                        <div class="bgy clrd">黄</div>
                                    </c:if>
                                    <c:if test="${s.m9 eq '4'}">
                                        <div class="bgb">蓝</div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div id="ldsk_ctt" style="width: 94%;height: 98%;margin: 1.6% 3% 0;display:none">
                    <iframe style="border-width: 0px;width: 100% ;height: 100%" src="${ctx}/hgqyIndex/ldsk"></iframe>
                </div>
            </div>
        </div>

    </div>
</div>

</div>
</div>


<script type="text/javascript">
    var fxdchart;
    var pointid;

    function closewindow() {
        window.close();
    }

    $(function () {
        // 显示饼状图
        getPingData();

        // 获取最新的承诺公告
        getCngg();

    })

    // 加载饼状图数据
    function getPingData() {
        $.ajax({
            type: 'GET',
            url: '${ctx}/fxgk/fxxx/getFxdCount',
            success: function (data) {
                loadPing(data);

                // 填充点击饼状图滑出框的头部数据
                $('#fxd_red').html(data['red']);
                $('#fxd_ora').html(data['orange']);
                $('#fxd_yel').html(data['yellow']);
                $('#fxd_blu').html(data['blue']);

            }
        })
    }

    // 加载饼状图
    function loadPing(data) {
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
    }

    // 获取最新的承诺公告
    function getCngg() {
        $.ajax({
            type: 'POST',
            url: '${ctx}/aqfxyp/fxypbg/getlatest',
            dataType: 'json',
            success: function(data) {
                if (data != null && data != '') {
                    $('#qyzt').html(data.m8);
                    $('#qycn').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + data.m9);
                    $('#zyfzr').html(data.m10);
                } else {
                    $('.ggk').hide();
                    $('#nogg').show();
                }
            }
        })
    }

    //风险点分布图、四色图
    var fxdlist = '${fxdlist}';
    var pmtpath = '${bis.m33_3}';
    var sstpath = '${bis.m33_4}';
    var pmturl;
    var ssturl;

    $(function(){
        loadFxsst();
    })

    // 加载风险四色图
    function loadFxsst(){
        if(sstpath == '' ||sstpath == null ||sstpath == undefined){
            layer.msg("请在企业安全生产全流程系统-基础信息-企业信息中上传企业风险四色图！",{time: 5000});
        } else {
            $('#fxdsst').prop('src', '');
            var urls = sstpath.split(',');
            var url = urls[0].split('||');
            ssturl = url[0];
            //初始化四色图
            $('#fxdsst').prop('src', ssturl);
        }

    }

    // 加载危险作业分布图
    function loadWxzyfbt(){
        if(pmtpath == '' ||pmtpath == null ||pmtpath == undefined){
            flag = '1';
            layer.msg("请在企业安全生产全流程系统-基础信息-企业信息中上传企业平面图！",{time: 5000});
        } else {
            var url = pmtpath.split('||');
            pmturl = url[0];
            //初始化平面图
            initImg('fxzyfbtt', url[0]);
        }

    }

    // 加载风险分布图
    function loadFxfbt(){
        if(pmtpath == '' ||pmtpath == null ||pmtpath == undefined){
            flag = '1';
            layer.msg("请在企业安全生产全流程系统-基础信息-企业信息中上传企业平面图！",{time: 5000});
        } else {
            var url = pmtpath.split('||');
            pmturl = url[0];
            //初始化平面图
            initImg('dituContent2', url[0]);
            initMapQy();
        }

    }

    // 显示风险点mark点
    function initMapQy(){
        // 清除平面图图标
        $('[name="fxdxq_"]').remove();

        var wh=$("#dituContent2").width();
        var wh2=$("#dituContent2").height();
        var fxds=JSON.parse(fxdlist);
        $.each(fxds, function(idx, obj) {
            var x=obj.m19;
            var y=obj.m20;
            if(x!="" && y!=""){
                var top=y*wh2+"px";
                var left=x*wh+"px";
                var ys = obj.m9;
                if(ys == '1'){
                    $("#dituContent2").append('<span name="fxdxq_" class="fxd_ic_1 v_btn_" style="top:'+top+';left:'+left+'" onclick=getFxdInfo('+obj.id+',"'+ys+'") title="'+obj.m1+'"></span>');
                }else if(ys == '2'){
                    $("#dituContent2").append('<span name="fxdxq_" class="fxd_ic_2 v_btn_" style="top:'+top+';left:'+left+'" onclick=getFxdInfo('+obj.id+',"'+ys+'") title="'+obj.m1+'"></span>');
                }else if(ys == '3'){
                    $("#dituContent2").append('<span name="fxdxq_" class="fxd_ic_3 v_btn_" style="top:'+top+';left:'+left+'" title="'+obj.m1+'"></span>');
                }else if(ys == '4'){
                    $("#dituContent2").append('<span name="fxdxq_" class="fxd_ic_4 v_btn_" style="top:'+top+';left:'+left+'" onclick=getFxdInfo(\'+obj.id+\',"\'+ys+\'") title="'+obj.m1+'"></span>');
                }
            }
        });
    }

    // 初始化企业平面图
    function initImg(idname, url){
        $("#"+idname).css("background-image","url("+url+")");
        $("#"+idname).css('backgound-size','100% 100%');
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

    // 点击风险清单记录滑动框基本信息数据填充
    function getFxdInfo(id, fxdcolor) {
        pointid = id;
        $.ajax({
            type: 'POST',
            url: '${ctx}/fxgk/fxxx/getFxdInfo/'+id,
            dataType: 'json',
            success: function(data) {
                if (data != null && data != '') {
                    var colorHtml = '';
                    if (fxdcolor == '1') {
                        colorHtml = '等级：<span style="color:red">&nbsp;红</span>';
                    } else if (fxdcolor == '2') {
                        colorHtml = '等级：<span style="color:orange">&nbsp;橙</span>';
                    } else if (fxdcolor == '3') {
                        colorHtml = '等级：<span style="color:yellow">&nbsp;黄</span>';
                    } else if (fxdcolor == '4') {
                        colorHtml = '等级：<span style="color:blue">&nbsp;蓝</span>';
                    }
                    $('#fxdcolor').html(colorHtml);

                    $('#fxdname').html('风险点名称：'+data.m1);
                    $('#bw').html(data.m6);
                    $('#ydzfx').html(data.m8);

                    var imgUrl;
                    if (data.m16) {
                        var fxdphotos = data.m16.split(',');
                        imgUrl = fxdphotos[0].split('||')[0];
                    } else {
                        imgUrl = '${ctx}/static/model/vd2/img/m2.png';
                    }
                    var html = '<div class="pic" style="background:#00075b url('+imgUrl+') no-repeat;background-size:100%;background-position:center;"></div>';
                    $('#fxdpic').html(html);
                }
            }

        })


        $.ajax({
            type: 'POST',
            url: '${ctx}/yhpc/xjdzt/getInfo/'+id,
            data: {'fxdid': id},
            dataType: 'json',
            success: function(data) {
                if (data.length > 0) {
                    var checkresult = data[0].checkresult;
                    $('#zjjcr').html(data[0].uname+'&nbsp;&nbsp;&nbsp;&nbsp;'+data[0].xcsj);

                    if (checkresult != '0' && checkresult != '1') {
                        $('#jczt').html('待检查');
                    } else {
                        $('#jczt').html('已检查');
                    }
                }
            }
        })
    }

    // 获取隐患详情各个状态对应的数量
    function getStateCount() {
        $.ajax({
            type: 'POST',
            url: '${ctx}/yhpc/yhpcjl/getstatecount/'+pointid,
            dataType: 'json',
            success: function(data) {
                var allCount = 0;
                $.each(data, function (index, el) {
                    if (el.state == '0') {
                        allCount++;
                        $('#dzg').html(el.statecount);
                    } else if (el.state == '1') {
                        allCount++;
                        $('#dfc').html(el.statecount);
                    } else if (el.state == '3') {
                        allCount++;
                        $('#zgwc').html(el.statecount);
                    }
                })
                $('#all').html(allCount);
            }
        })
    }

    // 点击风险清单记录滑动框隐患详情数据填充
    function getYhInfo(type) {
        getStateCount();

        $.ajax({
            type: 'POST',
            url: '${ctx}/yhpc/yhpcjl/getyhinfo',
            data: {'pointid': pointid, 'type': type},
            dataType: 'json',
            success: function(data) {
                $('#yhxq').html('');
                if (data.length > 0) {
                    var html = '';
                    $.each(data, function (index, el) {
                        var ztHtml = '';
                        if (el.yhzt == '0') {
                            ztHtml = '<span class="lilab">待整改</span>';
                        } else if (el.yhzt == '1') {
                            ztHtml = '<span class="lilab" style="background-color: blue;">待复查</span>\';                                                                          ;">待复查</span>';
                        } else if (el.yhzt == '3') {
                            ztHtml = '<span class="lilab" style="background-color: green;">整改完成</span>';
                        }

                        var imgUrl;
                        if (el.dangerphoto) {
                            var dangerphotos = el.dangerphoto.split(',');
                            imgUrl = dangerphotos[0].split('||')[0];
                        } else {
                            imgUrl = '${ctx}/static/model/vd2/img/m3.png';
                        }

                        html += '<li class="list_ f3">\n' +
                            '        <div class="lihd" style="margin-top: 5px;">\n' +
                            '            <img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/>' +
                            '            <span class="rsn f2">定期巡查</span>\n' +
                            ztHtml +
                            '        </div>\n' +
                            '        <div class="libd">\n' +
                            '            <div class="pic" style="background:#00075b url('+imgUrl+') no-repeat;background-size:100%;background-position:center;"></div>\n' +
                            '                <div class="info">\n' +
                            '                    <span>点位名称：<span>'+el.dwmc+'</span></span>\n' +
                            '                    <span>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：<span>企业自查</span></span>\n' +
                            '                    <span>上&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报：<span>'+el.sbrname+'<span>'+el.sbsj+'</span></span></span>\n' +
                            '                    <span>计划整改：<span>'+el.jhzgr+'<span class="clrr">'+el.jhzgsj+'</span></span></span>\n' +
                            '                </div>\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '    </li>';
                    })
                    $('#yhxq').html(html);
                }
            }
        })
    }

    var select1 = $("#select1");
    //初始化插件
    select1.goSelectInput({
        height: 30,
        width: 100
    });
    var select2 = $("#select2");
    //初始化插件
    select2.goSelectInput({
        height: 30,
        width: 100
    });
    //**获取两者的值
    $("#get").click(function () {
        alert("全部的select的值为" + select1.val() + ",选中文本为'" + select1.find("option:selected").text() + "'");
    });

    //current position
    var pos = 0;
    //number of slides
    var totalSlides = $('#slider-wrap ul li').length;

    window.onload = function () {
        // 设置单页面相应模块的高度-设置风险点列表高度
        setIndexModulesHeight();

        // $(".v_btn,.list_li,[class^=fxd_ic_]").click(function (ev) {
        $(".list_li,[class^=fxd_ic_]").click(function (ev) {
            // 滑动框弹出时触发点击隐患详情的'全部'按钮事件
            $('#all').trigger('click');

            hideSlider_l();
            showOrhide_slider($(this), "v_btn");
            // 阻止事件传递
            var oEvent = ev || event;
            //oEvent.cancelBubble = true;//高版本浏览器
            stopBubble(oEvent);
            //在低版本的chrome和firefox浏览器中需要兼容性处理
            //高版本chrome和firefox浏览器直接使用上面这行代码即可
        })

        $(".userInfo .xfq").click(function (ev) {
            hideSlider_r();
            // 操作左侧slider
            if ($(".slider_l").hasClass("show_")) {
                $(".slider_l").animate({left: "-946px"}, 'easing', function () {
                    $(".slider_l").removeClass("show_");
                });
            } else {
                $(".slider_l").addClass("show_");
                $(".slider_l").animate({left: "0px"}, 'easing');

                setSliderTableHeight();
                initSlider_l_data();
                if (zschart2)
                    zschart2.resize();
            }

            // 阻止事件传递
            var oEvent = ev || event;
            //oEvent.cancelBubble = true;//高版本浏览器
            stopBubble(oEvent);
            //在低版本的chrome和firefox浏览器中需要兼容性处理
            //高版本chrome和firefox浏览器直接使用上面这行代码即可
        })

        $(".slider_").click(function (ev) {
            var oEvent = ev || event;
            stopBubble(oEvent);
        })

        // 点击空白处 滑出slider
        $(".slider_cls,.wpbox").click(function (ev) {
            hideSlider_l();
            hideSlider_r();
            var oEvent = ev || event;
            stopBubble(oEvent);
        })

        /*
        * 特种设备 slider
        */
        $(".slider_ .head>div").click(function () {
            $(this).addClass("chos").siblings().removeClass("chos");
        })

        /*
        * 风险点详情 slider
        */
        $(".slider_ .tab_>div").click(function () {
            $(this).addClass("chos").siblings().removeClass("chos");
            // 根据点击按钮， 获取相应内容
            if ($(this).attr("id") != null && $(this).attr("id") != "undefined") {
                var ctt_id = $(this).attr("id") + "ctt";
                $("#" + ctt_id).show().siblings().hide();
            }
        })

        /*
        * 风险点一览
        */
        /*$(".amiddboxttop .tab_>div").click(function(ev){
        $('.tab_ctt .amiddboxttop_map').empty();
        $(this).addClass("chos").siblings().removeClass("chos");
        // 根据点击按钮， 获取相应内容
        if($(this).attr("id")!=null && $(this).attr("id")!="undefined"){
        var ctt_id = $(this).attr("id") + "ctt";
        $("#"+ctt_id).show().siblings().hide();
        console.log(ctt_id);
        // 选中之后插入thingjs-iframe
        if(ctt_id=='cqmnt_ctt'){
        var thingjs_iframe = '<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/d4aefa133c7a6764338d202d"></iframe>';
        $("#"+ctt_id).children('.amiddboxttop_map').append(thingjs_iframe);
        }
        if(ctt_id=='fxzyfbt_ctt'){
        var thingjs_iframe = '<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/a2187ea4e244ccfb61c116e9"></iframe>';
        $("#"+ctt_id).children('.amiddboxttop_map').append(thingjs_iframe);
        }
        if(ctt_id=='fxfbt_ctt'){
        var thingjs_iframe = '<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/294e21f70715634e5600f07f"></iframe>';
        $("#"+ctt_id).children('.amiddboxttop_map').append(thingjs_iframe);
        }


        if(ctt_id == "fxdlb_ctt")
        $("#fxdlb_ctt .list_b").height($("#fxdlb_ctt").height()-$("#fxdlb_ctt .list_h").height());
        }
        })*/
        $(".amiddboxttop .tab_>div").click(function (ev) {
            $(this).addClass("chos").siblings().removeClass("chos");
            // 根据点击按钮， 获取相应内容
            if ($(this).attr("id") != null && $(this).attr("id") != "undefined") {
                var ctt_id = $(this).attr("id") + "ctt";
                $("#" + ctt_id).show().siblings().hide();
                if (ctt_id == "cqmnt_ctt")
                    loadFxsst();
                if (ctt_id == "fxdlb_ctt")
                    $("#fxdlb_ctt .list_b").height($("#fxdlb_ctt").height() - $("#fxdlb_ctt .list_h").height());
                if (ctt_id == "fxzyfbt_ctt")
                    loadWxzyfbt();
                if (ctt_id == "fxfbt_ctt")
                    loadFxfbt();
            }
        })

        /*
        * 风险点一览
        */
        $("#cqmnt_ctt .vw_ic, .videoWindow .vw_cls").click(function (ev) {
            // 根据点击按钮， 获取相应内容
            if ($(".videoWindow").hasClass("show_")) {
                $(".videoWindow").hide();
                $(".videoWindow").removeClass("show_");
            } else {
                $(".videoWindow").show();
                $(".videoWindow").addClass("show_");
            }
        })


        /*****************
         BUILD THE SLIDER
         *****************/
        //set width to be 'x' times the number of slides
        $('#slider-wrap ul#slider__').width($('#slider-wrap').width() * totalSlides);

        //next slide
        $('#next').click(function () {
            lb_slideRight();
        });

        //previous slide
        $('#previous').click(function () {
            lb_slideRight();
        });

        /*************************
         //*> OPTIONAL SETTINGS
         ************************/
            //automatic slider
        var autoSlider = setInterval(lb_slideRight, 8000);

        //for each slide
        $.each($('#slider-wrap ul li'), function () {
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
            function () {
                $(this).addClass('active');
                clearInterval(autoSlider);
            },
            function () {
                $(this).removeClass('active');
                autoSlider = setInterval(lb_slideRight, 8000);
            }
        );


        // 四色图图片拖拽
        var oImg = document.getElementsByTagName("img")[0];
        function fnWheel(obj, fncc) {
            obj.onmousewheel = fn;
            if (obj.addEventListener) {
                obj.addEventListener('DOMMouseScroll', fn, false);
            }
            function fn(ev) {
                var oEvent = ev || window.event;
                var down = true;
                if (oEvent.detail) {
                    down = oEvent.detail > 0
                } else {
                    down = oEvent.wheelDelta < 0
                }
                if (fncc) {
                    fncc.call(this, down, oEvent);
                }
                if (oEvent.preventDefault) {
                    oEvent.preventDefault();
                }
                return false;
            }
        };
        fnWheel(oImg, function(down, oEvent) {
            var oldWidth = this.offsetWidth;
            var oldHeight = this.offsetHeight;
            var oldLeft = this.offsetLeft;
            var oldTop = this.offsetTop;
            var scaleX = (oEvent.clientX - oldLeft) / oldWidth; //比例
            var scaleY = (oEvent.clientY - oldTop) / oldHeight;
            if (down) {
                this.style.width = this.offsetWidth * 0.9 + "px";
                this.style.height = this.offsetHeight * 0.9 + "px";
            } else {
                this.style.width = this.offsetWidth * 1.1 + "px";
                this.style.height = this.offsetHeight * 1.1 + "px";
            }
            var newWidth = this.offsetWidth;
            var newHeight = this.offsetHeight;
            this.style.left = oldLeft - scaleX * (newWidth - oldWidth) + "px";
            this.style.top = oldTop - scaleY * (newHeight - oldHeight) + "px";
        });
    };

    /***********
     SLIDE LEFT
     ************/
    function lb_slideRight() {
        pos--;
        if (pos == -1) {
            pos = totalSlides - 1;
        }
        $('#slider-wrap ul#slider__').css('left', -($('#slider-wrap').width() * pos));

        //*> optional
        lb_countSlides();
        pagination();
    }

    /************
     SLIDE RIGHT
     *************/
    function lb_slideRight() {
        pos++;
        if (pos == totalSlides) {
            pos = 0;
        }
        $('#slider-wrap ul#slider__').css('left', -($('#slider-wrap').width() * pos));

        //*> optional
        lb_countSlides();
        pagination();
    }

    /************************
     //*> OPTIONAL SETTINGS
     ************************/
    function lb_countSlides() {
        //$('#counter').html(pos+1 + ' / ' + totalSlides);
    }

    function pagination() {
        $('#pagination-wrap ul li').removeClass('active');
        $('#pagination-wrap ul li:eq(' + pos + ')').addClass('active');
    }


    /*
    * 显隐 初始化 slider
    */
    function showOrhide_slider($this, eventType) {
        if ($(".slider_r").hasClass("show_")) {
            $(".slider_r").animate({right: "-473px"}, 'easing', function () {
                $(".slider_r").removeClass("show_");
            });
        } else {
            $(".slider_r").addClass("show_");
            $(".slider_r").animate({right: "0px"}, 'easing');

            // 根据点击按钮， 获取相应内容
            if (eventType == "v_btn") {
                // 根据点击按钮， 获取相应内容
                if ($this.attr("id") != null && $this.attr("id") != "undefined") {
                    var slider_id = $this.attr("id") + "slider";
                    $("#" + slider_id).show().siblings().hide();

                    //设置滑出窗内的left2_table list高度
                    setSliderTableHeight();
                }
            } else if (eventType.substring(0, 10) == "echart_fxd") {
                $("#fxd_slider").show().siblings().hide();
                var aa = "clr" + eventType.substring(11, 12);
                $(".slider_ .head>div." + aa).trigger("click");
            }
        }
    }

    //隐藏左侧slider_l
    function hideSlider_l() {
        if ($(".slider_l").hasClass("show_")) {
            $(".slider_l").animate({left: "-946px"}, 'easing', function () {
                $(".slider_l").removeClass("show_");
            });
        }
    }

    //隐藏右侧slider_r
    function hideSlider_r() {
        if ($(".slider_r").hasClass("show_")) {
            $(".slider_r").animate({right: "-473px"}, 'easing', function () {
                $(".slider_r").removeClass("show_");
            });
        }
    }

    //阻止冒泡事件的兼容性处理
    function stopBubble(e) {
        if (e && e.stopPropagation) { //非IE
            e.stopPropagation();
        } else { //IE
            window.event.cancelBubble = true;
        }
    }

    //设置slider中滑出窗内的 left2_table list高度
    function setIndexModulesHeight() {
        $("#fxdlb_ctt .list_b").height($("#fxdlb_ctt").height() - $("#fxdlb_ctt .list_h").height());
        //设置轮播标点位置
        $("#slider-wrap ul#slider__").width($("#slider-wrap").width() * 3);
        $("#slider-wrap ul#slider__ li").width($("#slider-wrap").width());
        var lbbdMT = ($("#slider__").height() - 26) + "px";
        $("#pagination-wrap").css("margin-top", lbbdMT);
    }

    // 设置slider中滑出窗内的 left2_table list高度
    function setSliderTableHeight() {
        $("#fxd_slider .left2_table").height($(".slider_").outerHeight(true) - $("#fxd_slider .slider_tit").outerHeight(true) - $("#fxd_slider .head").outerHeight(true) - 15);
        $("#tzsb_slider .left2_table").height($(".slider_").outerHeight(true) - $("#tzsb_slider .slider_tit").outerHeight(true) - $("#tzsb_slider .head").outerHeight(true) - 15);
        $("#fxdxq_slider .left2_table").height($(".slider_").outerHeight(true) - $("#fxdxq_slider .slider_tit").outerHeight(true) - $("#fxdxq_slider>.min_tit").outerHeight(true) - $("#fxdxq_slider>.fxd_t_info").outerHeight(true) - $("#fxdxq_slider>.tab_").outerHeight(true) - $("#fxdxq_slider .tab_ctt .head").outerHeight(true) - 15);
        $("#pub_slider .left2_table").height($(".slider_").outerHeight(true) - $("#pub_slider .slider_tit").outerHeight(true) - $("#pub_slider .head").outerHeight(true) - 15);
        $("#aqzs_slider .left2_table").height($(".slider_").outerHeight(true) - $("#aqzs_slider .slider_tit").outerHeight(true) - $("#aqzs_slider .aqzs_b_r .head").outerHeight(true) - $("#aqzs_slider .chosText").outerHeight(true) - 15);
        $("#aqzs_slider .aqzs_b_l .tab_ctt").height($(".slider_").outerHeight(true) - $("#aqzs_slider .slider_tit").outerHeight(true) - $("#aqzs_slider .aqzs_b_l .min_tit").outerHeight(true) - $("#aqzs_slider .aqzs_b_l .fxd_t_info").outerHeight(true) - $("#aqzs_slider .aqzs_b_l .tab_").outerHeight(true) - 15);
        $("#aqry_slider .left2_table").height($(".slider_").outerHeight(true) - $("#aqry_slider .slider_tit").outerHeight(true) - $("#aqry_slider .head").outerHeight(true) - 15);
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
                    show: false,
                    lineStyle: {
                        color: '#fff'
                    }
                }
            },
            yAxis: {
                type: 'value',
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
                    normal: {
                        color: function (params) {
                            var colorList = ['#ff6864', '#f19ec2', '#f8e19a', '#00b7ee'];
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

        // xfqChart.resize();
        // xjchart.resize();
        fxdchart.resize();
        if (zschart2)
            zschart2.resize();
    }

    function bigdataJumpNew(type) {
        if (type == -1)
            window.open('http://139.129.23.185:8090/demo/index.html', '_blank');
        if (type == 0)
            window.open('http://139.129.23.185:8099/HHTJXF/index.html', '_blank');
        if (type == 1)
            window.location.href = "${ctx}/hgqyIndex/bigdata/" + type;
        if (type == 2)
            window.location.href = "${ctx}/hgqyIndex/bigdata/4";
        if (type == 3)
            window.location.href = "${ctx}/hgqyIndex/bigdata/2";
        if (type == 4)
            window.location.href = "${ctx}/hgqyIndex/bigdata/3";
    }

    function req() {
        window.location.href = "${ctx}/a";
    }



    // 四色图图片拖拽
    var ie = document.all;
    var nn6 = document.getElementByIdx && !document.all;
    var isdrag = false;
    var y, x;
    var oDragObj;
    function moveMouse(e) {
        if (isdrag) {
            oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y) + "px";
            oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x) + "px";
            return false;
        }
    }
    function initDrag(e) {
        var oDragHandle = nn6 ? e.target : event.srcElement;
        var topElement = "HTML";
        while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") {
            oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement;
        }
        if (oDragHandle.className == "dragAble") {
            isdrag = true;
            oDragObj = oDragHandle;
            nTY = parseInt(oDragObj.style.top + 0);
            y = nn6 ? e.clientY : event.clientY;
            nTX = parseInt(oDragObj.style.left + 0);
            x = nn6 ? e.clientX : event.clientX;
            document.onmousemove = moveMouse;
            return false;
        }
    }
    document.onmousedown = initDrag;
    document.onmouseup = new Function("isdrag=false");
</script>
</body>
</html>
