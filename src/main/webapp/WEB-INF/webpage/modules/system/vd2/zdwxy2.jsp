<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>

<!doctype html>
<head>
    <meta charset="utf-8">
    <title>重大危险源大数据</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/ckplayer/ckplayer.js"></script>
    <link href="${ctxStatic}/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-select2/htools.select.skin.css">
    <link href="${ctx}/static/model/vd2/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/static/model/vd2/css/gov.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/static/jqueryToast/css/toast.style.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/jqueryToast/js/toast.script.js"></script>
    <style>
        .amiddboxttop, .arightboxtop {
            overflow: hidden;
            background: url(${ctx}/static/model/vd2/img/lbBg.png);
            background-size: 100% 100%;
            background-repeat: no-repeat;
            background-position: top center;
            width: 100%;
        }

        .amiddboxttop .tab_ctt, .aleftboxtmidd .tab_ctt {
            height: 80%;
        }

        .spjk_ {
            width: 95.2%;
            margin: 2% 2.4%;
            height: 84%;
            position: relative;
            /*background: #000;*/
        }

        .spjk_btns {
            display: flex;
            font-size: 12px;
            position: absolute;
            right: 10px;
            margin-top: 15px;
            color: #ababab;
        }

        .spjk_btn {
            margin: 0 4px;
            padding: 4px 9px;
            background: #013d59;
            border-radius: 3px;
            cursor: pointer;
        }

        .btn_chos {
            background: #2d75ad;
            color: #fff;
        }

        .show_info {
            padding: 2px 8px;
            cursor: pointer;
        }

        .amiddboxtbott1content {
            height: 85%;
        }

        .video__:hover {
            cursor: pointer;
        }

        .show_ {
            display: block !important;
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
            .pt2, .ptm, .ptm__ {
                padding-top: 2.4% !important;
            }

            .___sd {
                margin: 1% 0;
                font-size: 16px !important;
            }

            #slider-wrap ul#slider__ li > div, .item_block {
                display: flex;
                flex-wrap: wrap;
                height: 100%;
            }

            .qt_item {
                width: 50%;
                height: 50%;
            }

            .qt_item > div {
                display: flex;
                align-items: center;
                height: 100%;
                width: 100%;
                justify-content: center;
            }

            .qt_item_text {
                margin-left: 10px;
                font-size: 12px;
            }

            .qt_item_text > div {
                margin: 8px 0;
            }

            .qt_item_text span {
                padding: 1px 5px;
                border-radius: 4px;
                background: rgb(114, 196, 234);
                color: black;
                font-size: 12px;
            }

            .qt_item_img {
                width: 50px;
                height: 50px;
                background: url(${ctx}/static/model/vd2/img/normal.gif) no-repeat;
                background-size: 100% 100%;
                margin-left: 6px;
            }

            .gy_item_img {
                width: 120px;
                height: 190px;
                background: url(${ctx}/static/model/vd2/img/gy.gif) no-repeat;
                background-size: 100% 100%;
                margin-left: 6px;
            }

            .item-ctt .asd {
                width: 50px;
            }

            .item-ctt .cg_1 {
                margin-top: 3%;
            }

            .videoWindow {
                width: 760px;
                height: 560px;
                margin-left: calc(50% - 380px);
                margin-top: 7%;
                color: #e6e6e6;
            }

            .__img {
                height: 300px;
            }


            .list_ .libd .info {
                display: flex;
                flex-direction: column;
                justify-content: space-around;
                height: 56px;
            }

            .list_ctt .list_b > div.list_li > div {
                padding: 6px;
                line-height: 24px;
                border-left: 1px solid #638192;
            }
        }


        @media screen and (max-width: 1440px) {
            .pt2, .ptm, .ptm__ {
                padding-top: 1.8% !important;
            }

            .___sd {
                margin: 0.4% 0;
            }

            #slider-wrap ul#slider__ li > div, .item_block {
                display: flex;
                flex-wrap: wrap;
                height: 100%;
            }

            .qt_item {
                width: 49%;
                height: 50%;
            }

            .qt_item > div {
                display: flex;
                align-items: center;
                height: 100%;
                width: 100%;
                justify-content: center;
            }

            .qt_item_text {
                margin-left: 10px;
                font-size: 12px;
            }

            .qt_item_text > div {
                margin: 8px 0;
            }

            .qt_item_text span {
                padding: 1px 5px;
                border-radius: 4px;
                background: rgb(114, 196, 234);
                color: black;
                font-size: 12px;
            }

            .qt_item_img {
                width: 80px;
                height: 80px;
                background: url(${ctx}/static/model/vd2/img/qt1.gif) no-repeat;
                background-size: 100% 100%;
                margin-left: 6px;
            }

            .gy_item_img {
                width: 80px;
                height: 200px;
                background: url(${ctx}/static/model/vd2/img/gy.gif) no-repeat;
                background-size: 100% 100%;
                margin-left: 6px;
            }

            .___sd {
                margin: 0% 0;
                font-size: 16px !important;
            }

            .item-ctt .asd {
                width: 46px;
            }

            .item-ctt .cg_1 {
                margin-top: 0%;
            }

            .videoWindow {
                width: 640px;
                height: 460px;
                margin-left: calc(50% - 320px);
                margin-top: 7%;
                color: #e6e6e6;
            }

            .__img {
                height: 200px;
            }
            .slider_ .tab_>div, .amiddboxttop .tab_>div {
                padding: 3px 0 3px 8px;
            }

            .f3 {
                font-size: 10px;
            }
            .f2-5 {
                font-size: 11px;
            }

            #fxdlb_ctt .list_h>div, #fxdlb_ctt .list_b>div.list_li>div,.list_ctt .list_h > div, .list_ctt .list_b > div.list_li > div {
                text-align: center;
                padding:3px 6px!important;
            }

            .qt_item_img {
                width: 60px;
                height: 60px;
                margin-left: 6px;
            }
            .qt_item_text > div {
                margin: 4px 0;
            }

            .qt_item_text {
                margin-left: 4px;
                font-size: 10px;
            }
            .qt_item_text span {
                padding: 1px 2px;
                border-radius: 4px;
                font-size: 10px;
            }
        }

        @media screen and (max-width: 1366px) {
            .list_ .libd .info {
                display: flex;
                flex-direction: column;
                justify-content: space-around;
                height: 50px;
            }

            .slider_ .tab_>div, .amiddboxttop .tab_>div {
                padding: 3px 0 3px 8px;
            }

            .qt_item_img {
                width: 60px;
                height: 60px;
                margin-left: 6px;
            }
            .qt_item_text > div {
                margin: 4px 0;
            }

            .qt_item_text {
                margin-left: 4px;
                font-size: 10px;
            }
            .qt_item_text span {
                padding: 1px 2px;
                border-radius: 4px;
                font-size: 10px;
            }
            .f3 {
                font-size: 10px;
            }
            .f2-5 {
                font-size: 11px;
            }

            #fxdlb_ctt .list_h>div, #fxdlb_ctt .list_b>div.list_li>div,.list_ctt .list_h > div, .list_ctt .list_b > div.list_li > div {
                text-align: center;
                padding:3px 6px!important;
            }

            .cg_v_item.___sd{
                margin-bottom: 0px!important;
            }
            .item-ctt .asd{
                width: 34px;
            }
            .___sd{
                font-size: 13px !important;
            }
            .cg_v_item_ {
                height: 16%!important;
                margin-bottom: 3%!important;
            }
            .cg_v_item.cg_v_item_ > div > div:nth-child(2) {
                font-size: 9px!important;
                bottom: -5px!important;
            }
            .cg_v_item.cg_v_item_ > div > div:nth-child(1) {
                margin-top: -4px!important;
            }
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


        /* 重写easyui */
        .panel-body {
            background-color: #00075a;
            color: #fff;
            font-size: 12px;
        }

        .datagrid-htable, .datagrid-btable, .datagrid-ftable, .datagrid-cell-rownumber {
            color: #f4f4f4;
        }

        .datagrid-header, .datagrid-td-rownumber {
            background-color: #1d639a;
            background: #1d639a;
        }

        .datagrid-header, .datagrid-toolbar, .datagrid-pager, .datagrid-footer-inner {
            border-color: #7799c7;
        }

        .datagrid-header td, .datagrid-body td, .datagrid-footer td {
            border-color: #638192;
            border-style: solid;
        }

        .datagrid-row-alt {
            background: #3d5f7b;
        }

        .datagrid-header td.datagrid-header-over {
            background: #0a3e82 !important;
            color: #fff;
            cursor: pointer;
        }

        .datagrid-row-over {
            background: #00064c;
            color: #fff;
            cursor: pointer;
        }

        .datagrid-row-alt.datagrid-row-over {
            background: #32536f;
            color: #fff;
            cursor: pointer;
        }

        .datagrid-row-selected {
            background: #ffe48d !important;
            color: #000000 !important;
        }

        .datagrid-toolbar, .datagrid-pager {
            background: #00075a;
            color: #bdbdbd;
        }

        .pagination-page-list, .pagination .pagination-num {
            border-color: #638192;
            background-color: #3d5f7b;
        }

        .datagrid-pager.pagination td:nth-child(1), .datagrid-pager.pagination td:nth-child(2) {
            display: none;
        }

        .line_ {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 1;
            -webkit-box-orient: vertical;
            font-size: 10px;
        }

        .date__ {
            flex-shrink: 0;
        }

        .item-ctt {
            height: 100%;
        }

        .cg_v_item {
            width: 100%;
            display: flex;
            justify-content: space-around
        }

        .cg_v_item.cg_v_item_ > div {
            width: 20%;
            background: url(${ctx}/static/model/vd2/img/333.png) no-repeat;
            background-size: 100% 100%;
            position: relative;
            text-align: center;
            display: flex;
            justify-content: center;
        }

        .cg_v_item.cg_v_item_ > div > div:nth-child(2) {
            position: absolute;
            bottom: 0;
            font-size: 12px;
        }

        .cg_v_item.cg_v_item_ > div > div:nth-child(1) {
            margin-top: 0%;
            font-size: 16px;
            font-weight: 600;
        }

        .cg_v_item_ {
            height: 15%;
            margin-bottom: 3%;
        }

        .cg_1 {
            background: url(${ctx}/static/model/vd2/img/hx.png) no-repeat;
            background-size: 100%;
            background-position: bottom;
            width: 100%;
            display: flex;
            justify-content: space-around;
            align-items: center;
            padding-bottom: 5px;
            margin-bottom: 5px;
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

        .row__ {
            display: flex !important;
            flex-direction: row !important;
            width: 100% !important;
            justify-content: space-between;
        }

        .row__ > div {
            width: 30% !important;
        }

        .row__ > div > div {
            padding: 3px 0;
            text-align: center;
            background: #134d7b;
            color: #FF9800;
        }

        .row__ > div > div.value_ {
            background: #206ba7;
            color: #00fff6;
        }

        .lihd .lilab {
            display: none;
        }

        .list_ .libd {
            display: flex;
            align-items: center;
            margin-top: 2%;
        }


        .list_ctt {
            width: 94%;
            height: 98%;
            margin: 1.6% 3% 0;
            /* background: aliceblue; */
        }

        .list_ctt .list_h {
            background: #2d75ad;
        }

        .list_ctt .list_h, .list_ctt .list_b, .list_ctt .list_b > div.list_li {
            display: flex;
        }

        .list_ctt .list_h > div, .list_ctt .list_b > div.list_li > div {
            text-align: center;
            padding: 6px;
        }

        .list_ctt .list_b {
            flex-direction: column;
            overflow-y: scroll;
        }

        .list_ctt .list_b > div.list_li:nth-child(odd) {
            width: 100%;
            border-bottom: .1px solid #4d7d9c;
            background: #00075a;
            color: #fff;
            flex-shrink: 0;
        }

        .list_ctt .list_b > div.list_li:nth-child(even) {
            width: 100%;
            border-bottom: .1px solid #4880a0;
            background: #3d5f7b;
            color: #fff;
            flex-shrink: 0;
        }

        .datagrid-header-row td {
            background-color: #3190c2;
        }

        .datagrid-header-row td .datagrid-cell span {
            color: #ffffff;
        }

        #edminfo .li__{
              padding: 4px 0;
          }
        #edminfo .li__:nth-child(2n - 1){
            background: rgba(81, 132, 158, 0.5);
        }
        #edminfo .li__ div:nth-child(1){
            margin-right: 10px;
        }

        .playlive{
            width: 80%!important;
            height: 80%!important;
            margin: 7%!important;
        }

        /*.qt_item_img div {*/
            /*padding: 1px 5px;*/
            /*border-radius: 4px;*/
            /*background: rgb(114, 196, 234);*/
            /*color: black;*/
            /*font-size: 12px;*/
        /*}*/

        .qt_item_img div {
            position: absolute;
        }

        .qt_item_text{
            margin-top: -10px;
            text-align: left;
        }

        .qt_item_img_tt{
            margin-top: 2px;
        }
    </style>
</head>
<body style="background: #000000 url(${ctx}/static/model/vd2/img/a_bg.png);background-size: cover;background-position:center;">

<!-- 详情滑出窗 -->
<div class="slider_ slider_r disabled">
    <!-- 厂区地图风险点 点击后滑出的slider 风险点详情 -->
    <div id="fxdxq_slider" class="f3">
        <div class="slider_tit f1" style="width: 40%;">
            <i class="fa fa-block clrq"></i>实时监控详情
            <span class="slider_cls fn1">X</span>
        </div>
        <div class="tab_ctt">
            <div id="qhxq_ctt">
                <div class="head">
                    <div class="chos" onclick="getJcdXq(null)">
                        <span class="f3">全部<span>${cgSsCount}</span></span>
                    </div>
                    <div class="" onclick="getJcdXq('bj')">
                        <span class="f3">报警<span class="clrr">${cjBjCount}</span></span>
                    </div>
                    <div class="" onclick="getJcdXq('zc')">
                        <span class="f3">正常<span class="">${cjZcCount}</span></span>
                    </div>
                    <div class="" onclick="getJcdXq('lx')">
                        <span class="f3">离线<span class="">${cjLxCount}</span></span>
                    </div>
                </div>
                <div class="left2_table">
                    <ul id="jcdxq" style="margin-right: 4px;">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="wpbox">

    <div class="bnt"
         style="background: url(${ctx}/static/model/vd2/img/header_bg.png);background-size:100% 100%;background-position:center;">
        <div class="topbnt_left fl">
            <ul>
                <li class="weather"></li>
            </ul>
        </div>
        <h1 class="tith1 fl">重大危险源监控预警系统</h1>
        <div class=" fr topbnt_right">
            <ul>
                <li style="width: 120px;cursor: pointer;"><a onclick="req()">导航菜单</a></li>
            </ul>

        </div>
    </div>

    <div class="left1" style="width: 26%;">
        <div class="amiddboxttop" style="height: 33.3%;">
            <h2 class="tith2 pt2" style="padding-top: 2.5%;">储罐实时监控</h2>
                <div style="margin-left: 2.5%;margin-top: 1%;height: 87%;width: 95%;">
                <div class="item-ctt">
                    <div class="cg_1" style="margin-top: 1%;width: 100%;display: flex;justify-content: space-around;align-items: center">
                        <div style="font-size: 15px;color: #8cbef9;"><img class="asd" src="${ctx}/static/model/vd2/img/222.png">&nbsp;&nbsp;&nbsp;<span>储罐总数</span>
                        </div>
                        <div class="show_info"
                             style="color: #8cbef9;font-size: 22px;margin-left: -15%;">${cgCount}</div>
                    </div>
                    <div class="cg_v_item ___sd" style="font-size: 15px;margin-bottom: 10px;">
                        <div><span>告警点位&nbsp;&nbsp;&nbsp;&nbsp;<span
                                style="color: #b30e0e;font-size: 18px;font-weight: 600;">${gjCount}</span></span></div>
                        <div><span>离线点位&nbsp;&nbsp;&nbsp;&nbsp;<span
                                style="color: rgb(197, 192, 128);font-size: 18px;font-weight: 600;">${lxCount}</span></span>
                        </div>
                    </div>
                    <div class="cg_v_item cg_v_item_">
                        <div>
                            <div style="color: #b30e0e;">${wdMap.gjcount}</div>
                            <div>温度</div>
                        </div>
                        <div>
                            <div style="color: rgb(197, 192, 128);">${wdMap.lxcount}</div>
                            <div>温度</div>
                        </div>
                    </div>
                    <div class="cg_v_item cg_v_item_">
                        <div>
                            <div style="color: #b30e0e;">${ylMap.gjcount}</div>
                            <div>压力</div>
                        </div>
                        <div>
                            <div style="color: rgb(197, 192, 128);">${ylMap.lxcount}</div>
                            <div>压力</div>
                        </div>
                    </div>
                    <div class="cg_v_item cg_v_item_">
                        <div>
                            <div style="color: #b30e0e;">${ywMap.gjcount}</div>
                            <div>液位</div>
                        </div>
                        <div>
                            <div style="color: rgb(197, 192, 128);">${ywMap.lxcount}</div>
                            <div>液位</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="amiddboxttop" style="height: 33.3%;">
            <h2 class="tith2 pt2" style="padding-top: 2.5%;">气体实时监测</h2>
            <div class="tab_">
                <div id="cqmnt_" class="chos">
                    <div class="min_tit f3">
                        图形化展示
                    </div>
                </div>
                <div id="fxdlb_">
                    <div class="min_tit f3">
                        数据列表
                    </div>
                </div>
            </div>
            <div class="tab_ctt">
                <div id="cqmnt_ctt" style="margin-left: 2.5%; margin-top: 1%;height: 92%;width: 95%;">
                    <div class="item-ctt">
                        <div id="slider-wrap" class="active">
                            <ul id="slider__">
                                <c:choose>
                                <c:when test="${qttxInfoList != null && fn:length(qttxInfoList)>0}">
                                <c:forEach items="${qttxInfoList}" var="ss" varStatus="status">
                                <c:if test="${ status.index %4 == 0}">
                                <li data-color="#1abc9c">
                                    <div class="item_block" style="width: 100%!important;">
                                        </c:if>
                                        <div class="qt_item v_btn_">
                                            <div class="qtinfor">
                                                <div class="">
                                                    <div class="qt_item_img">
                                                    </div>
                                                    <div class="qt_item_img_tt">${ss.bit_no}</div>
                                                </div>
                                                <div class="qt_item_text">
                                                    <%--<div><span style="background: #FFC107;">${ss.target_name}</span>
                                                    </div>--%>
                                                    <div><span>${ss.value}<c:if test="${ss.target_type eq 'YDQT'}">mg/m³或ppm</c:if><c:if test="${ss.target_type eq 'KRQT'}">%LEL</c:if></span></div>
                                                    <div><span><c:if test="${ss.target_type eq 'YDQT'}">有毒气体</c:if><c:if test="${ss.target_type eq 'KRQT'}">可燃气体</c:if></span></div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${status.index %4 == 3 && fn:length(qttxInfoList)>4}">
                                        <i class="fa fa-image"></i>
                                    </div>
                                </li>
                                </c:if>
                                </c:forEach>
                                <i class="fa fa-image"></i>
                            </div>
                            </li>
                            </c:when>
                            <c:otherwise><div style="text-align: center;"><img class="nonData" src="${ctx}/static/model/images/hgqy/noxgsb.png" style="height:300px;"></div></c:otherwise>
                            </c:choose>
                            </ul>
                            <!--controls-->
                            <div class="btns" id="next"><i class="fa fa-arrow-right" style="display: none"></i></div>
                            <div class="btns" id="previous"><i class="fa fa-arrow-left" style="display: none"></i></div>
                            <div id="counter"></div>
                            <div id="pagination-wrap">
                                <ul>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <%--气体实时监测数据列表--%>
                <div id="fxdlb_ctt" class="list_ctt" style="display: none;">
                    <div class="list_h f2-5" style="">
                        <div class="w20">所属罐区</div>
                        <div class="w20">是否报警</div>
                        <div class="w20">实时浓度</div>
                        <div class="w20">采集时间</div>
                        <div class="w20">气体类型</div>
                    </div>
                    <div class="list_b f3" style="">
                        <c:if test="${qtsjlbList != null and fn:length(qtsjlbList) > 0}">
                            <c:forEach items="${qtsjlbList}" var="s">
                                <div id="fxdxq_" class="list_li">
                                    <div class="w20">${s.cgqmc}</div>
                                    <div class="w20">
                                        <c:choose>
                                            <c:when test="${s.alarm_time == null || s.alarm_time == ''}">正常</c:when>
                                            <c:otherwise>报警</c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="w20">
                                        <c:if test="${s.value != null}">
                                            <c:if test="${s.target_type eq 'YDQT'}">mg/m³或ppm</c:if>
                                            <c:if test="${s.target_type eq 'KRQT'}">%LEL</c:if>
                                        </c:if>
                                    </div>
                                    <div class="w20"><fmt:formatDate value="${s.cjsj}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                    <div class="w20">
                                        <c:if test="${s.target_type eq 'YDQT'}"><div class="bgr">有毒气体</div></c:if>
                                        <c:if test="${s.target_type eq 'KRQT'}"><div class="bgo">可燃气体</div></c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="arightboxtop" style="height: 33.3%;position: relative;">
            <h2 class="tith2 ptm__" style="padding-top:1.5%;">视频实时监测</h2>
            <div class="spjk_btns" style="z-index: 100;">
                <div class="spjk_btn btn_chos" id="sp1" style="display: none;">1</div>
                <div class="spjk_btn" id="sp2" style="display: none;">2</div>
                <div class="spjk_btn" id="sp3" style="display: none;">3</div>
                <div class="spjk_btn" id="spmore" style="display: none;">更多</div>
            </div>
            <div class="item-ctt" style="height: 84%;">
                <div class="item_block2" id="splb"
                     style="display: flex;flex-wrap: wrap;height: 100%;margin: 5px 0 0px 10px;width: 96%!important;">
                    <%--<div class="qt_item v_btn_">
                        <div class="spjk_ playlive" style="width: 98%;height: 98%;margin: 1%" id="playlive0"></div>
                    </div>--%>
                    <%--<div class="qt_item v_btn_">
                        <div class="spjk_ playlive" style="width: 98%;height: 98%;margin: 1%" id="playlive1"></div>
                    </div>
                    <div class="qt_item v_btn_">
                        <div class="spjk_ playlive" style="width: 98%;height: 98%;margin: 1%" id="playlive2"></div>
                    </div>
                    <div class="qt_item v_btn_">
                        <div class="spjk_ playlive" style="width: 98%;height: 98%;margin: 1%" id="playlive3"></div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>

    <div class="mrbox_topmidd" style="width: 47.2%;margin-left:0.4%">
        <div class="amiddboxttop" style="height: 100%;">
            <%-- 使用ThingJS --%>
            <%--<iframe id="thingJsMapiframe" name="thingJsMapiframe" style="width:100%;height:100%;border-width: 0;" src="https://www.thingjs.com/s/294e21f70715634e5600f07f"></iframe>--%>
            <iframe src="${ctx}/hgqyIndex/map" style="width:100%;height:100%;border-width: 0;"></iframe>
            <%-- 使用百度地图 --%>
           <%-- <div id="bis_enterprise_dlg_map" style="width:100%;height: 100%;"></div>--%>
        </div>
    </div>

    <div class="mrbox_top_right" style="width: 26%;">
        <div class="amiddboxttop" style="height: 33.3%;">
            <h2 class="tith2 pt2" style="padding-top: 2.5%;">高危工艺实时监测</h2>
            <div class="tab_">
                <div id="gwgytx_" class="chos">
                    <div class="min_tit f3">
                        图形化展示
                    </div>
                </div>
                <div id="gwgylb_" class="">
                    <div class="min_tit f3">
                        数据列表
                    </div>
                </div>
            </div>
            <div class="tab_ctt">
                <div id="gwgytx_ctt" style="margin-left: 2.5%;    margin-top: 1%;height: 87%;width: 95%;">
                    <%--<table id="ssjc_gwgy_dg"></table> --%>
                    <div class="item-ctt">
                        <div id="slider-wrap2" class="active">
                            <ul id="slider__2">
                                <c:choose>
                                    <c:when test="${gwgylbList != null && fn:length(gwgylbList)>0}">
                                        <c:forEach items="${gwgylbList}" var="ss" varStatus="status">
                                            <c:if test="${ status.index %2 == 0}">
                                                <li data-color="#1abc9c">
                                                    <div class="item_block">
                                            </c:if>
                                                        <div class="qt_item v_btn_" style="height: 100%">
                                                            <div class="qtinfor">
                                                                <div class="gy_item_img"></div>
                                                                <div class="qt_item_text">
                                                                    <div><span style="background: #FFC107;">${ss.gwgyname}</span></div>
                                                                    <div><span>${ss.wd}℃</span></div>
                                                                    <div><span>${ss.yl}kPa</span></div>
                                                                    <div><span>${ss.yw}m</span></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                            <c:if test="${status.index %2 == 1 && fn:length(gwgylbList)>2}">
                                                        <i class="fa fa-image"></i>
                                                    </div>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <i class="fa fa-image"></i>
                                        </div>
                                        </li>
                                    </c:when>
                                    <c:otherwise><div style="text-align: center;"><img class="nonData" src="${ctx}/static/model/images/hgqy/noxgsb.png" style="height:200px;"></div></c:otherwise>
                                </c:choose>
                            </ul>
                            <!--controls-->
                            <div class="btns" id="next2"><i class="fa fa-arrow-right" style="display: none"></i></div>
                            <div class="btns" id="previous2"><i class="fa fa-arrow-left" style="display: none"></i>
                            </div>
                            <div id="counter2"></div>
                            <div id="pagination-wrap2">
                                <ul>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="gwgylb_ctt" class="list_ctt" style="display:none">
                    <div class="list_h f2-5" style="">
                        <div class="w20">工艺名称</div>
                        <div class="w20">温度</div>
                        <div class="w20">压力</div>
                        <div class="w15">液位</div>
                        <div class="w25">采集时间</div>
                    </div>
                    <div class="list_b f3" style="">
                        <c:if test="${gwgylbList != null and fn:length(gwgylbList) > 0}">
                            <c:forEach items="${gwgylbList}" var="s">
                                <div id="fxdxq_" class="list_li">
                                    <div class="w20">${s.gwgyname}</div>
                                    <div class="w20">
                                        <c:if test="${s.wd != null}">
                                            ${s.wd}℃
                                        </c:if>
                                    </div>
                                    <div class="w20">
                                        <c:if test="${s.yl != null}">
                                            ${s.yl}kPa
                                        </c:if>
                                    </div>
                                    <div class="w15">
                                        <c:if test="${s.yw != null}">
                                            ${s.yw}m
                                        </c:if>
                                    </div>
                                    <div class="w25"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="amidd_bott" style="height: 33.3%;">
            <div class="amiddboxtbott1 fl">
                <h2 class="tith2 ptm">二道门进出信息</h2>
                <div id="edminfo" class="labic_" style="margin: 15px;margin: 15px;overflow-y: scroll; height: 82%;text-align: center;margin-top: 5px">
                </div>
            </div>
        </div>

        <div class="arightboxtop" style="height: 33.3%;">
            <h2 class="tith2 ptm">报警信息</h2>
            <div class="labic_" style="margin: 15px;height: 82%;overflow-y: scroll;margin-top: 5px">
                <div class="ul__ dowebok" style="overflow:hidden;text-align: center;">
                    <div class="li__" style="padding: 10px;background:#122f5da3;text-align: left; margin-bottom: 10px;">
                        <c:choose>
                            <c:when test="${bjxxList != null && fn:length(bjxxList) != 0}">
                                <c:forEach items="${bjxxList }" var="s">
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 7px; color: #ff4848">
                                        <span>
                                            <c:if test="${s.jctype eq '1'}">
                                                <img style="margin-right: 8px;"
                                                     src="${ctxStatic}/model/vd2/img/jb1.png"/>储罐报警&nbsp;&nbsp;
                                            </c:if>
                                            <c:if test="${s.jctype eq '2'}">
                                                  <img style="margin-right: 8px;"
                                                       src="${ctxStatic}/model/vd2/img/jb3.png"/>气体浓度报警&nbsp;&nbsp;
                                            </c:if>
                                            <c:if test="${s.jctype eq '3'}">
                                                  <img style="margin-right: 8px;"
                                                       src="${ctxStatic}/model/vd2/img/jb3.png"/>高危工艺报警&nbsp;&nbsp;
                                            </c:if>
                                        </span>
                                        <span style="color: #6781b1;"><fmt:formatDate value="${s.alarmtime}"
                                                                                      pattern="yy/MM/dd HH:mm"/></span>
                                    </div>
                                    <div style="color: #ccd2da;">
                                        <c:if test="${s.jctype eq '1'}">
                                            储罐名称：${s.name}; 位号：${s.wh}; ${s.alarmtype}
                                        </c:if>
                                        <c:if test="${s.jctype eq '2'}">
                                            储罐区名称：${s.name}; 编号：${s.wh}; ${s.alarmtype}
                                        </c:if>
                                        <c:if test="${s.jctype eq '3'}">
                                            工艺名称：${s.name}; ${s.alarmtype}
                                        </c:if>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-top: 7px;">
                                        <div>
                                            <span style="font-size: 12px;color: #6781b1;"><img
                                                    style="margin-right: 6px;"
                                                    src="${ctxStatic}/model/vd2/img/dw_.png"/>${s.cgqname}</span>
                                        </div>
                                        <div style="flex-shrink: 0;">
                                            <img class="video__" style="width: 24px"
                                                 src="${ctxStatic}/model/vd2/img/camera_l.png"/>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: center;">
                                    <img src="${ctxStatic}/model/images/hgqy/nobjxx.png" style="height:190px;" />
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%-- toast弹出框 --%>
    <div id="msg__s" style="position:fixed;bottom:0;right:0">

    </div>


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
    <script type="text/javascript">
        var qyid = '${qyid}';

        function entersys() {
            window.location.href = "${ctx}/hgqyIndex/sys/1/4309";
        }

        function req() {
            window.location.href = "${ctx}/a";
        }


        //current position
        var pos = 0;
        //number of slides
        var totalSlides = $('#slider-wrap ul li').length;

        //current position
        var pos2 = 0;
        //number of slides
        var totalSlides2 = $('#slider-wrap2 ul li').length;

        window.onload = function () {
            //设置滑出窗内list高度
            setSliderTableHeight();

            $(".show_info").click(function (ev) {
                hideSlider_l();
                showOrhide_slider($(this), "v_btn");
                // 阻止事件传递
                var oEvent = ev || event;
                //oEvent.cancelBubble = true;//高版本浏览器
                stopBubble(oEvent);
                //在低版本的chrome和firefox浏览器中需要兼容性处理
                //高版本chrome和firefox浏览器直接使用上面这行代码即可
                setSliderTableHeight();
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

            $(".spjk_btn").click(function () {
                $(this).addClass("btn_chos").siblings(".spjk_btn").removeClass("btn_chos");
            })

            $(".list_ctt .list_b").height($(".list_ctt").height() - $(".list_ctt .list_h").height() - 20);

            $(function(){
                getedminfo();
                getBjxx();
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
            var autoSlider = setInterval(lb_slideRight, 800000);

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
                    $(this).addClass('active2');
                    clearInterval(autoSlider);
                },
                function () {
                    $(this).removeClass('active2');
                    autoSlider = setInterval(lb_slideRight, 800000);
                }
            );


            /*****************
             BUILD THE SLIDER
             *****************/
//set width to be 'x' times the number of slides
            $('#slider-wrap2 ul#slider__2').width($('#slider-wrap2').width() * totalSlides2);

//next slide
            $('#next2').click(function () {
                lb_slideRight2();
            });

//previous slide
            $('#previous2').click(function () {
                lb_slideRight2();
            });

            /*************************
             //*> OPTIONAL SETTINGS
             ************************/
//automatic slider
            var autoSlider2 = setInterval(lb_slideRight2, 800000);

//for each slide
            $.each($('#slider-wrap2 ul li'), function () {
                //set its color
                //var c = $(this).attr("data-color");
                //$(this).css("background",c);

                //create a pagination
                var li2 = document.createElement('li');
                $('#pagination-wrap2 ul').append(li2);
            });

//counter
            lb_countSlides2();

//pagination
            pagination2();

            $('#slider-wrap2').hover(
                function () {
                    $(this).addClass('active2');
                    clearInterval(autoSlider2);
                },
                function () {
                    $(this).removeClass('active2');
                    autoSlider2 = setInterval(lb_slideRight2, 800000);
                }
            );


            $(".video__,.videoWindow .vw_cls").click(function () {
                if ($(".videoWindow").hasClass("show_")) {
                    $(".videoWindow").removeClass("show_");
                } else {
                    $(".videoWindow").addClass("show_");
                }

            })


            /*
         * 风险点一览
         */
            $(".amiddboxttop .tab_>div").click(function (ev) {
                $(this).addClass("chos").siblings().removeClass("chos");
                // 根据点击按钮， 获取相应内容
                if ($(this).attr("id") != null && $(this).attr("id") != "undefined") {
                    var ctt_id = $(this).attr("id") + "ctt";
                    $("#" + ctt_id).show().siblings().hide();
                    if (ctt_id == "fxdlb_ctt") {
                        $(".list_ctt .list_b").height($(".list_ctt").height() - $(".list_ctt .list_h").height() - 20);
                    }
                }
            })



            // 设置未接入图标居中
            var slider_wrap = $("#slider-wrap").width();
            if(slider_wrap>=300){
                var aaa = (slider_wrap - 200)/2;
                $(".nonData").css("margin-left",aaa+"px");
            }
        }

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
            $('#pagination-wrap ul li').removeClass('active2');
            $('#pagination-wrap ul li:eq(' + pos + ')').addClass('active2');
        }


        /*********** 2
         SLIDE LEFT
         ************/
        function lb_slideRight2() {
            pos2--;
            if (pos2 == -1) {
                pos2 = totalSlides2 - 1;
            }
            $('#slider-wrap2 ul#slider__2').css('left', -($('#slider-wrap2').width() * pos2));

            //*> optional
            lb_countSlides2();
            pagination2();
        }

        /************
         SLIDE RIGHT
         *************/
        function lb_slideRight2() {
            pos2++;
            if (pos2 == totalSlides2) {
                pos2 = 0;
            }
            $('#slider-wrap2 ul#slider__2').css('left', -($('#slider-wrap2').width() * pos2));

            //*> optional
            lb_countSlides2();
            pagination2();
        }

        /************************
         //*> OPTIONAL SETTINGS
         ************************/
        function lb_countSlides2() {
            //$('#counter').html(pos+1 + ' / ' + totalSlides);
        }

        function pagination2() {
            $('#pagination-wrap2 ul li').removeClass('active2');
            $('#pagination-wrap2 ul li:eq(' + pos2 + ')').addClass('active2');
        }


        //阻止冒泡事件的兼容性处理
        function stopBubble(e) {
            if (e && e.stopPropagation) { //非IE
                e.stopPropagation();
            } else { //IE
                window.event.cancelBubble = true;
            }
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

        // 设置slider中滑出窗内的 left2_table list高度
        function setSliderTableHeight() {
            var aa = $(".slider_").outerHeight(true) - $("#fxdxq_slider .slider_tit").outerHeight(true) - $("#fxdxq_slider .tab_ctt .head").outerHeight(true) - 15;
            $("#fxdxq_slider .left2_table").height(aa);
        }

        // 查看详情提示框
        function tipsInfo(idName) {
            layer.tips("<span style='color:#fff;'>点击查看详情</span>", '#' + idName, {
                tips: [3, '#f8ac59'],
                time: 1000,
                area: 'auto',
                maxWidth: 500,
                tipsMore: true
            });
        }

        $(function(){
            getJcdXq(null);


        });

        //获取监测点详情信息
        function getJcdXq(type) {
            $('#jcdxq').html("");
            $.ajax({
                type: 'POST',
                url: ctx + '/zxjkyj/zdwxycg/getjcdxq',
                data: {'type': type},
                success: function (data) {
                    var html = "";
                    var parseData = JSON.parse(data);
                    if (parseData != null && parseData != '' && parseData != undefined) {
                        $.each(parseData, function (index, el) {
                            html += '<li class="list_ f3">';
                            html += '   <div class="lihd">';
                            html += '       <img class="_ic" src="${ctx}/static/model/vd2/img/ic1_.png"/>';
                            html += '           <span class="rsn f2">储罐：'+el.cgname+'</span>';
                            html += '           <span class="lilab">已过期</span>';
                            html += '   </div>';
                            html += '   <div class="libd">';
                            html += '       <div class="info row__">';
                            html += '           <div>';
                            html += '               <div class="value_">'+el.wd+'</div>';
                            if (el.bjwd != 0) {
                                html += '           <div style="background-color: #b12525">温度(°C)</div>';
                            } else {
                                html += '           <div>温度(°C)</div>';
                            }
                            html += '           </div>';
                            html += '           <div>';
                            html += '               <div class="value_">'+el.yl+'</div>';
                            if (el.bjyl != 0) {
                                html += '           <div style="background-color: #b12525">压力(kPa)</div>';
                            } else {
                                html += '           <div>压力(kPa)</div>';
                            }
                            html += '           </div>';
                            html += '           <div>';
                            html += '               <div class="value_">'+el.yw+'</div>';
                            if (el.bjyw != 0) {
                                html += '           <div style="background-color: #b12525">液位(m)</div>';
                            } else {
                            html += '               <div>液位(m)</div>';
                            }
                            html += '           </div>';
                            html += '      </div>';
                            html += '   </div>';
                            html += '</li>';
                        });
                    }
                    $('#jcdxq').append(html.replace(/undefined/g, ''));
                }
            })
        }

        // 视频实时监测
        var spUrls = [];
        $(function () {
            $.ajax({
                type: 'POST',
                url: ctx + '/bis/spsbxx/zdwxySpUrls',
                data: {'qyid': qyid},
                dataType: 'json',
                success: function (data) {
                    var parseData = JSON.parse(data);
                    if (parseData != "" && parseData != null && parseData != undefined) {
                        if (parseData.length > 12) {// 如果超过十二个重大危险源视频，则显示全部按钮
                            $('#sp1').show();
                            $('#sp2').show();
                            $('#sp3').show();
                            $('#spmore').show();
                        } else if (parseData.length > 8) {// 如果有超过八个重大危险源视频，则显示'1,2,3'按钮
                            $('#sp1').show();
                            $('#sp2').show();
                            $('#sp3').show();
                        } else if (parseData.length > 4) {// 如果有超过四个重大危险源视频，则显示'1,2'按钮
                            $('#sp1').show();
                            $('#sp2').show();
                        } else if (parseData.length <= 4) {// 如果只有四个以内重大危险源视频，则只显示'1'按钮
                            $('#sp1').show();
                        }

                        // 循环将url放入spUrls中，方便按钮点击事件获取对应的url
                        $.each(parseData, function (index, el) {
                            spUrls.push(el.url);
                        })
                        // 显示视频图像
                        showVideo(spUrls, 0);
                    } else {
                        $('.spjk_btns').hide();
                        $('#playlive').append('<img src="${ctxStatic}/model/images/hgqy/nozdwxysp.png" style="height:300px;" />');
                    }
                }
            })
        });

        // 显示视频播放器
        function showVideo(urls, index) {
            var len = 0;
            if (index == 0) {
                if (urls.length <= 4) {
                    len = urls.length;
                } else {
                    len = 4;
                }
            } else if (index == 4) {
                if (urls.length <= 8) {
                    len = urls.length;
                } else {
                    len = 8;
                }
            } else if (index == 8) {
                if (urls.length <= 12) {
                    len = urls.length;
                } else {
                    len = 12;
                }
            }

            var html = '';
            for (var i = index; i < len; i++) {
                html = '<div class="qt_item v_btn_">\n' +
                    '        <div class="spjk_ playlive" style="width: 98%;height: 98%;margin: 1%" id="playlive'+i+'"></div>\n' +
                    '    </div>';
                $('#splb').append(html);

                var videoObject = {
                    container: '#playlive' + i,//“#”代表容器的ID，“.”或“”代表容器的class
                    variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
                    autoplay: false, //是否自动播放
                    video: urls[i] //视频地址
                };
                var player = new ckplayer(videoObject);
            }
        }

        // '1,2,3,更多'按钮点击触发事件
        $('#sp1').click(function () {
            $('#splb').html('')
            showVideo(spUrls, 0);
        });

        $('#sp2').click(function () {
            $('#splb').html('')
            showVideo(spUrls, 4);
        });

        $('#sp3').click(function () {
            $('#splb').html('')
            showVideo(spUrls, 8);
        });

        $('#spmore').click(function () {
            openDialogView("查看更多重大危险源视频", ctx + "/zdwxy/spjk/index?zdwxy=1", "90%", "90%", "");
        });

        /* 储罐实时监测 */
        //查看详情
        function viewxq(tankid) {
            openDialogView("查看储罐实时监测信息", ctx + "/zxjkyj/zdwxycg/viewxq/" + tankid, "1000px", "80%", "");
        }

        //查看
        function viewbjxx(tankid, jctype) {
            openDialogView("查看储罐", ctx + "/zxjkyj/zdwxycg/viewbjxx/" + tankid + "/" + jctype, "800px", "400px", "");
        }

        //查看波动图信息
        function viewbdt(tankid, jctype) {
            openDialogView("查看历史波动图", ctx + "/zxjkyj/zdwxycg/viewbdtindex/" + tankid + "/" + jctype, "90%", "80%", "");
        }

        /* 气体实时监测 */
        //查看详情
        function viewxq1(cgqbh) {
            openDialogView("查看气体实时监测信息", ctx + "/zxjkyj/zdwxyqt/viewxq/" + cgqbh, "900px", "450px", "");
        }

        //查看
        function viewbjxx1(cgqbh) {
            openDialogView("查看气体", ctx + "/zxjkyj/zdwxyqt/viewbjxx/" + cgqbh, "800px", "400px", "");
        }

        //查看波动图信息
        function viewbdt1(cgqbh, qttype) {
            openDialogView("查看历史波动图", ctx + "/zxjkyj/zdwxyqt/viewbdtindex/" + cgqbh + "/" + qttype, "90%", "80%", "");
        }

        // ajax获取最新的报警信息
        function getBjxx() {
            // 清空 消息列表
            $("#msg__s").html("");

            $.ajax({
                type: 'POST',
                url: ctx + '/bis/cgjcwhsj/getNewBjxx',
                success: function (data) {
                    var parseData = JSON.parse(data);
                    if (parseData != null && parseData != '' && parseData != undefined) {
                        var content  = '';
                        $.each(parseData, function (index, el) {
                            if (el.jctype == 1) {
                                content += "<span style='cursor: pointer;'>储罐 <span style='color: purple;'><strong>" + el.wh + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
                            } else if (el.jctype == 2) {
                                content += "<span style='cursor: pointer;'>储罐区 <span style='color: purple;'><strong>" + el.cgqname + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
                            } else if (el.jctype == 3) {
                                content += "<span style='cursor: pointer;'>高危工艺 <span style='color: purple;'><strong>" + el.name + "</strong></span> " + el.alarmtype + "，请及时处理！</span><br/>";
                            }
                        })
                        toastInfo(content);
                    }
                }
            })
        }

        // 每隔一分钟请求一次报警数据
        timerID = setInterval("getBjxx()", 60000);

        // toast提示框
        function toastInfo(msg) {
            $.Toast($("#msg__s"), "", msg, "error", {
                stack: true,
                has_icon: true,
                has_close_btn: true,
                fullscreen: false,
                timeout: 0,
                sticky: false,
                has_progress: true,
                rtl: false,
            });
        }

        // 点击toast弹出框查看对应的储罐
        function showBjxx(sigid, type) {
            openDialogView("查看储罐", ctx + "/zxjkyj/bjxx/cgbjindex?sigid=" + sigid + "&&type=" + type, "900px", "450px", "");
        }

        // 点击toast弹出框查看对应的气体
        function showBjxx1(sigid, type) {
            openDialogView("查看气体", ctx + "/zxjkyj/bjxx/ndbjindex?sigid=" + sigid + "&&type=" + type, "900px", "450px", "");
        }

        //获取二道门信息
        function getedminfo(){
            $.ajax({
                type: 'POST',
                url: ctx + '/zxjkyj/edm/getEdmInfo',
                success: function (data) {
                    $('#edminfo').html("");
                    var html="";
                    var parseData = JSON.parse(data);
                    if (parseData != null && parseData != '' && parseData != undefined) {
                        html = '<div class="ul__ dowebok" style="overflow:hidden">';
                        $.each(parseData, function (index, el) {
                            html += '<div class="li__" style="display: flex; justify-content: space-between;" title="'+el.ygname+' 工号：'+el.ygcode+' 在'+el.edmname+' '+el.type+'">';
                            html += '<div style="display: flex;align-item:center;">';
                            html += '<div class="tag__"><img src="${ctxStatic}/model/images/home/skin_/new.png" /></div>';
                            html += '<div class="line_ news__">';
                            html += el.ygname+' 工号：'+el.ygcode+' 在'+el.edmname+' '+el.type;
                            html += '</div>';
                            html += '</div>';
                            html += '<div class="date__">';
                            var gxtime = '';
                            if(el.updatetime != null){
                                var datetime = new Date(el.updatetime);
                                gxtime = datetime.format('yyyy-MM-dd hh:mm:ss');
                            }
                            html += gxtime;
                            html += '</div>';
                            html += '</div>';
                        });
                        html += '</div>';
                    }else{
                        html = '<div style="text-align: center;"><img src="${ctxStatic}/model/images/hgqy/noedm.png" style="height:200px;" /></div>';
                    }
                    $('#edminfo').append(html.replace(/undefined/g,''));
                }
            })
        }

        $(window).resize(function () {
            var ww = $(".item-ctt").innerWidth() + 20;
            $(".item_block").css("width", ww + "px");

            $(".list_ctt .list_b").height($(".list_ctt").height() - $(".list_ctt .list_h").height() - 20);

            //设置滑出窗内list高度
            setSliderTableHeight();
        })


        // 五分钟刷新下页面
        window.setInterval(reloadPage,300000);
        function reloadPage(){
            window.location.reload();
        }
    </script>
</body>
</html>
