<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <!--
        Author : cap
        Date : 19/3/1
     -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>人员定位</title>
    <link rel="stylesheet" href="${ctxStatic}/fengmap/lib/bootstrap.min.css">
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/style.css">
    <link rel="stylesheet" href="${ctxStatic}/model/css/rydw/style.css">
    <!-- 引入自定义 css js -->
    <link rel="stylesheet" href="${ctxStatic}/fengmap/css/team36.css">
    <script type="text/javascript"
            src="${ctxStatic}/fengmap/js/jquery-1.12.0.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/fengmap/js/team36.js"></script>

    <script type="text/javascript" src="${ctxStatic}/model/js/lydw/rydw/index.js"></script>
</head>

<style>
    * {
        -webkit-box-sizing: unset;
        -moz-box-sizing: unset;
        box-sizing: unset;
    }

    .table>tbody>tr>td,.table>tbody>tr>th,.table>tfoot>tr>td,.table>tfoot>tr>th,.table>thead>tr>td,.table>thead>tr>th
    {
        padding: 4px;
        text-align: center;
        font-size: 14px;
    }

    .typeBtn {
        display: inline-block;
        margin-bottom: 0px;
        font-size: 14px;
        font-weight: 400;
        line-height: 1.42857;
        text-align: center;
        white-space: nowrap;
        vertical-align: middle;
        touch-action: manipulation;
        cursor: pointer;
        user-select: none;
        background-image: none;
        padding: 6px 12px;
        border-width: 1px;
        border-style: solid;
        border-color: transparent;
        border-image: initial;
        border-radius: 4px;
    }

    .funcCtrlStyle {
        position: absolute;
        top: 15px;
        padding: 0 10px;
        display: flex;
        background: white;
        align-items: center;
        height: 35px;
        border-radius: 3px;
        border: 1px solid #ccc;
        box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.075);
    }

    .search_chosen {
        background: rgb(102, 175, 233);
        color: white
    }

    .search1:hover {
        cursor: pointer;
    }

    .search2:hover {
        cursor: pointer;
    }

    #searchSpan:hover {
        cursor: pointer;
        background: whitesmoke
    }

    input:focus {
        outline: none;
        border-color: 0px;
        box-shadow: 0 0 0px rgba(0, 0, 0, 0);
    }


    .bgr36{
        background:rgba(220, 14, 14, 0.92);
    }
    .bgo36{
        background:rgba(255, 188, 0, 0.92);
    }
    .bggr36{
        background:rgba(22, 165, 28, 0.94);
    }
    .bgb36{
        background: rgba(14, 129, 220, 0.94);
    }

    .msgCard{
        width:0px;
        height:44px;
        overflow:hidden;
        opacity:0;
        padding: 5px 7px;
        display: flex;
        flex-shrink: 0;
        align-items: center;
        box-shadow:0 0 8px 0 rgba(0, 0, 0, 0.3) inset;
        border-radius: 5px;
        margin-right: 8px;
    }
    .msgCard:hover{
        cursor: pointer;
        box-shadow: 0 0 8px 0 rgba(0,0,0,0.5);
        transition: all .3s;
        -webkit-transition: all .3s;
    }
    .msgCardIc{
        width: 28px;
        height: 28px;
        margin-right: 7px;
        flex-shrink: 0;
        background: url('${ctx}/static/fengmap/img/warning1.png');
        background-size:cover;
    }
    .cardDiv{
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
        color:white
    }
    .cardInfo{
        width: 170px;
        padding: 3px 0;
        display: flex;
        flex-direction: column;
    }
    .cardType{
        font-size: 14px;
        padding:0px;
        font-weight: bolder;
        margin-bottom: 1px;
    }
    .cardContent{
        font-size: 12px;
    }
    .cardClose{
        width: 24px;
        height: 24px;
        flex-shrink: 0;
        display: flex;
        background: url('${ctx}/static/fengmap/img/close.png') no-repeat;
        background-position: center;
        background-size: 60%;
        margin-left: 7px;
    }

    #msgbar:hover{
        cursor: pointer;
    }

    .hid36{display:none}


    #vd_header_{
        background:rgb(1, 15, 43) url(${ctx}/static/model/vd2/img/rydw/title.png) no-repeat;
        background-size: 100%;
        height: 70px;
        text-align: center;
        font-size: 30px;
        font-weight: 600;
        color: #a9feff;
        line-height: 64px;
        background-position-y: center;
    }

     .vd-header{
         position: absolute;
         top: 0;
         margin: 0 auto;
     }
    .vd-header.img{
    }

    .vd-lab-cards{
        position: absolute;
        top: 100px;
        left: 0%;
        display: flex;
    }
    .vd-lab-card{
        margin-left: 60px;
        width: 220px;
        height: 70px;
        background: url(${ctx}/static/model/vd2/img/rydw/rydw-lab1.png) no-repeat;
        background-size: 100% 100%;
    }
    .vd-lab-card2{
        background: url(${ctx}/static/model/vd2/img/rydw/rydw-lab2.png) no-repeat;
        background-size: 100% 100%;
    }
    .vd-lab-card3{
        background: url(${ctx}/static/model/vd2/img/rydw/rydw-lab3.png) no-repeat;
        background-size: 100% 100%;
    }

    .vd-lab-card>span{
        font-size: 30px;
        font-weight: 600;
        margin-left: 22px;
        line-height: 60px;
    }
    .vd-lab-card>span>span{
        font-size: 16px;
        font-weight: 600;
        line-height: 70px;
    }

    .vd-func-btns{
        position: absolute;
        top: 240px;
        left: 3%;
        display: flex;
    }

    .vd-func-btn{
        width: 70px;
        height: 70px;
        margin-bottom: 15px;
        cursor: pointer;
        background-size: 100% 100%;
    }
    .vd-btn-1{
        background: url(${ctx}/static/model/vd2/img/rydw/btn1.png);
        background-size: 100% 100%;
    }
    .vd-btn-2{
        background: url(${ctx}/static/model/vd2/img/rydw/btn2.png) no-repeat;
        background-size: 100% 100%;
    }
    .vd-btn-3{
        background: url(${ctx}/static/model/vd2/img/rydw/btn3.png) no-repeat;
        background-size: 100% 100%;
    }
    .vd-btn-4{
        background: url(${ctx}/static/model/vd2/img/rydw/btn4.png) no-repeat;
        background-size: 100% 100%;
    }

    .search_box{
        width: 200px;
        position: absolute;
        right: 20px;
        top: 100px;
        display: flex;
        flex-direction: column;
    }

    .vd-func-views{
        width: 360px;
        height: 300px;
        /* background: rgba(0, 14, 40, 0.6); */
        margin-left: 30px;
        padding: 5px;
        border: 2px solid rgba(1, 15, 43, 0.5);
        display: none;
    }

    .vd-func-view{
        /*width: calc(100% - 20px);*/
        background: rgba(0, 14, 40, 0.6);
        height: 100%;
        border: 1px solid #1aadff;
    }
    .vw_vdo_sit{
        background: url(${ctx}/static/model/vd2/img/noinfo.png) no-repeat;
        background-size: 100% 100%;
    }
</style>
<body class="bd">
<!-- fun view -->
<div class="searchView" style="display:none">
    <div class="d1">
        <input id="inp" class="inp" placeholder="搜搜~" onclick="inp_click()"
               onblur="inp_blur(this)" oninput="input()" />
        <div class="close36 icd">
            <img class="closeic" style="display: none;"
                 src="${ctxStatic}/fengmap/img/delete.png"
                 onmousedown="view_close()" />
        </div>
        </input>

        <div class="road36 icd"></div>
    </div>

    <!--其他弹出内容外层div-->
    <div class="d2">
        <div class="row36 row1"></div>
        <div class="row36 row2"></div>
        <!-- 当input 输入字符时 隐藏前两个row 同时在该row中的ul里添加li，遍历ajax 检索项  -->
        <div class="row36 row3"></div>
        <!-- 小小操控栏 -->
        <div class="row36 row4"></div>
    </div>

</div>

<div class="search36 icd" style="display:none" onmousedown="search36()"></div>
<!--<div class="hidSearch"></div>-->


<!-- 右侧function bar
 给div 添加焦点事件时 需要添加tabindex 属性 去除焦点虚边线 添加hidefocus 属性
-->
<div class="funcBar overhid" style="outline:0" tabindex="0"
     hidefocus="true" onblur='funcDivBlur(this)'>
    <div class="funcDiv0">
        <div class="funcDiv"
             style="border-left: 0px solid gainsboro;overflow: unset;">
            <div class="funcName" style="margin:0 6px 0 -2px;overflow: hidden;">
                围栏</div>
            <div class="">
                <select class="fenceSelect1" name="">
                    <option value="0">关闭</option>
                    <option value="1">第一个</option>
                    <option value="2">第二个</option>
                    <option value="3">第三个</option>
                </select>
            </div>
        </div>
    </div>

    <div class="funcDiv0">
        <div class="funcDiv">
            <img class="funcIc" src="${ctxStatic}/fengmap/img/Siren.png" /> <img
                class="funcIc2 hid" src="${ctxStatic}/fengmap/img/Siren2.png" />
            <div class="funcName">拉升报警器</div>
        </div>
    </div>

    <div class="funcDiv0">
        <div class="funcDiv">
            <img class="funcIc" src="${ctxStatic}/fengmap/img/alarmbell.png" />
            <img class="funcIc2 hid"
                 src="${ctxStatic}/fengmap/img/alarmbell2.png" />
            <div class="funcName">警铃</div>
        </div>
    </div>

    <div class="funcDiv0">
        <div class="funcDiv">
            <img class="funcIc" src="${ctxStatic}/fengmap/img/camera.png" /> <img
                class="funcIc2 hid" src="${ctxStatic}/fengmap/img/camera2.png" />
            <div class="funcName">摄像头</div>
        </div>
    </div>

    <div class="funcDiv0 divBlur" onclick="view_warning()" tabindex="0"
         hidefocus="true" onblur='divBlur(this)'>
        <div class="funcDiv warning">
            <img class="funcIc" src="${ctxStatic}/fengmap/img/msg.png" /> <img
                class="funcIc2 hid" src="${ctxStatic}/fengmap/img/camera2.png" />
            <div class="msgCount">3</div>
            <div class="funcName" style="margin-left: 10px;">警报</div>
            <div class="upIc"></div>
        </div>

        <!-- 警报消息列表 -->
        <div class="warning_list"></div>
    </div>
</div>


<!--
右侧 plural function bar
-->
<div class="plurBtn" style="display:none">
    <div class="plurIc"></div>
</div>

<div class="plurBar overhid" style="display:none">
    <div class="plurItem plurItem1">
        <!--hover 滑出二级菜单-->

        <img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
    </div>

    <div class="plurItem plurItem2">
        <!--hover 滑出二级菜单-->

        <img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
    </div>

    <div class="plurItem plurItem3">
        <!--hover 滑出二级菜单-->

        <img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
    </div>

    <div class="plurItem plurItem4">
        <!--hover 滑出二级菜单-->

        <img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
    </div>

    <div class="plurItem plurItem5">
        <!--hover 滑出二级菜单-->

        <img class="plurItemIc" src="${ctxStatic}/fengmap/img/area.png">
    </div>
</div>

<style>
    .frk{
        position: absolute;
        right: 20px;
        color: white;
        top: 44px;
        z-index: 999;
        font-size: 13px;
        line-height: 20px;
        background: url(${ctx}/static/model/vd2/img/bnt.png);
        background-size: 100% 100%;
        background-repeat: no-repeat;
        font-weight: 500;
        padding: 5px 12px;
        color: #c6e2df;
        cursor: pointer;
    }
</style>
<!-- 蜂鸟 map-->
<div id="vd_header_" style="height: 70px;">人员在岗在位管理系统 <div class="frk" onclick="req()">导航菜单</div></div>
<div id="fengMap" style="height: calc(100% - 70px);background-repeat: no-repeat;background-size: 100% 100%"></div>

<!-- 人员警报提示框-->
<div class="promt">
    <div class="alert alert-info alert-dismissable hidden" id="polygon-info">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <strong>提示：目标点已进入电子围栏范围</strong>
    </div>
</div>

<!--  -->
<div class="search_box">
    <div class=""
         style="border-radius:4px; border: 1px solid #ccc;width: 198px;display:flex;background:white;overflow: hidden;">
        <div class="search1 search_chosen"
             style="padding:6px 0;margin: 0px;width: 100px;text-align:center;">
            搜建筑</div>
        <div class="search2"
             style="padding:6px 0;margin: 0px;width: 100px;text-align:center;">
            搜人员</div>
    </div>

    <!-- 建筑物搜索框 -->
    <div class="search_1" style="width: 200px;">
        <input type="text" class="form-control"
               style="box-shadow: 0 0 0px rgba(0, 0, 0, 0);outline:none;border-color: #ccc;width: calc(100% - 26px); height: 23px;MARGIN-TOP: -1px;"
               placeholder="建筑物名称.." id="seachText">
        <ul id="hotwords" class="hotwords" style="display:none;width: 198px;">
        </ul>
    </div>

    <!-- 人员搜索 -->
    <div class="search_2"
         style="overflow: hidden;display:none;position:absolute;top:33px;padding:0 10px;border: 1px solid #ccc; box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);background:white;align-items:center;height:35px;border-radius:3px;">
        <input id="input_2"
               style="width:130px; height: 36px;border:0px;outline: none;"
               placeholder="人员名称.." /> <span id="searchSpan"
                                             style="padding:7px 13px 7px 15px;color:grey;font-size:15px; flex-shrink: 0;"
                                             onclick="checkValue()">搜索</span>
    </div>
</div>

<!-- 电子围栏selector
<div class="funcCtrlStyle" style="left:228px;/*left:361px;*/">
    <span id="testaa" style="margin-right:5px">显示围栏 </span>
    <select class="fenceSelect" name="">

    </select>
</div> -->

<!--搜索出来的数据显示在table-->
<div id="table-container" class="scrollbar"
     style="width:calc(100% - 89px);padding: 5px;position: absolute;bottom:0px;background:rgb(255,255,255,1)">
    <div class="title">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
    </div>
    <table class="table table-bordered table-hover" id="table"
           style="margin-bottom: 0px;">
        <thead>
        <tr>
            <th>行号</th>
            <th>建筑Id</th>
            <th>建筑名称</th>
            <th>楼层</th>
            <th>x</th>
            <th>y</th>
            <th>z</th>
        </tr>
        </thead>
        <tbody id="table-body">
        </tbody>
    </table>
</div>

<!-- 全新警报消息卡 -->
<div id="msgbar" style="display:none;position:absolute;z-index:9999;top:15px;right:250px;border-radius:5px;box-shadow:0 0 8px 0 rgb(0,0,0,.3);background:rgba(255,255,255,0.9);padding:8px 10px;">
    <div id="msgCardBox" style="">

    </div>
    <!-- 警报消息btn -->
    <div onselectstart="return false" id="msgbtn" style="background:white;width:54px;height:54px;border-radius:5px;box-shadow: 0 0 8px 0 rgba(0,0,0,0.3);" onclick="showMsgList()">
        <div style="height: 22px; width: 22px; margin: 8px 16px 1px; background: url(${ctx}/static/fengmap/image/bell.png);"></div>
        <div id="msgCount" style="width:54px;text-align:center;font-size: 10px;">0</div>
    </div>

    <!-- 消息列表 -->
    <div>
        <div style="display:none;position: absolute;margin: 68px 0 0 -291px;padding-left:0px;font-size:13px;padding:10px 0 10px;background: rgba(255,255,255,0.9); border-radius: 5px;" >
            <div id="msgCardUl" style="max-height:300px;overflow:scroll;flex-direction: column;padding-left:0px;font-size:13px;padding:0px;" class="hid36">
            </div>
        </div>
    </div>
</div>

<!-- 数据浮窗
<div class=""
     style="display:flex;flex-direction: column;width:220px;background:rgba(255,255,255,0.9);position:absolute;top:15px;right:15px;border-radius:5px;box-shadow:0 0 8px 0 rgb(0,0,0,.3)">
    <div class=""
         style="width:100%;display:flex;align-items:center;border-bottom:0.1px solid #c1c1c1;">
        <div class="" style="width:50%;padding:8px 0;text-align:center;">
            部门</div>
        <div class="" style="width:50%;padding:5px 0;text-align:center;">
            人员</div>
    </div>
    <div class="" style="width:calc(100% - 10px);padding:3px 5px; max-height: 94px;overflow-y: scroll;">
        <ul id="deptPersonInfo" style="padding-left:0px;font-size:13px;margin-bottom:0px;">

        </ul>
    </div>
</div> -->


<!-- 分类查询按钮-->
<div class="typeBtns" style="left: 600px;display:none">
    <button class="typeBtn btn-default" data-id="">员工</button>
    <button class="typeBtn btn-default" data-id="160000">访客</button>
    <button class="typeBtn btn-default" data-id="160000">其他</button>
</div>

<!-- 部门selector
<div class="funcCtrlStyle" style="left:228px;display:none">
    <span style="margin-right:5px">部门 </span> <select class="deptSelect"
                                                      name="">
    <option value="0">关闭</option>
    <option value="1">第一个</option>
    <option value="2">第二个</option>
    <option value="3">第三个</option>
</select>
</div> -->

<!--模型拾取操作按钮-->
<div class="operating" style="display:none">
    <button class="btn btn-default">开启模型拾取</button>
    <button class="btn btn-default">关闭模型拾取</button>
</div>
<!-- data-backdrop="false" -->
<div id="dlgModelInfo" class="modal fade">
    <div class="modal-dialog bottom">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">拾取对象类型：</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">鼠标点击位置坐标：</div>
                        <div class="col-md-8" id="m-info"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- cap visual data view -->
<div class="vd-header">

</div>

<div class="vd-lab-cards">
    <div class="vd-lab-card">
        <span style="color: #FFEB3B;">356<span>人</span></span>
    </div>

    <div class="vd-lab-card vd-lab-card2">
        <span style="color: #FF5722;">39<span>条</span></span>
    </div>

    <div class="vd-lab-card vd-lab-card3">
        <span style="color: #8BC34A;">10090<span>m²</span></span>
    </div>
</div>


<div class="vd-func-btns">
    <div>
        <div class="vd-func-btn vd-btn-1"></div>
        <div class="vd-func-btn vd-btn-2"></div>
        <div class="vd-func-btn vd-btn-3"></div>
        <div class="vd-func-btn vd-btn-4"></div>
    </div>

    <div class="vd-func-views vd-func-views-1">
        <div class="vd-func-view">
            <div class="vd-func-view-tit" style="padding: 10px 0;">
                <div><span>警告数据</span><span class="vw_cls" style="padding: 7px 12px; color: white;cursor: pointer">X</span></div>
            </div>
            <div>
                <div class="vd-func-view-table-tit">
                    <div>警告类型</div>
                    <div>告警人员</div>
                    <div>时间</div>
                </div>
                <div class="vd-func-view-table-body" style="overflow-y: scroll;height: 230px;">
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                    <div style="width: 100%;display: flex">
                        <div>警告类型值</div>
                        <div>告警人员值</div>
                        <div>时间值</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="vd-func-views vd-func-views-2">
        <div class="vd-func-view" style="background: rgba(0, 14, 40, 0.83);">
            <div class="vd-func-view-tit" style="padding: 10px 0;">
                <div><span>警告分析</span><span class="vw_cls" style="padding: 7px 12px; color: white;cursor: pointer">X</span></div>
            </div>
            <div class="vd-func-view-table-body2" style="overflow-y: scroll;height: 230px;">
                <div id="aleftboxtmidd" style="width: 358px;height: 230px"></div>
            </div>
        </div>
    </div>

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
                <img src="${ctxStatic}/model/vd2/img/noinfo.png" style="height:300px;margin-top: 40px" />
            </div>
        </div>
        <div class="vw_b_r">
            <div class="vw_vdo2 f3">
                <div class="">设备列表</div>
                <div class="sblist">
                    <ul class="menuul">
                        <li class="menu1">设备1</li>
                        <ul class="menu1ul">
                            <li class="menu1"><div></div>设备2</li>
                            <ul class="menu2ul">
                                <li class="menu2">设备2-1</li>
                                <li class="menu2">设备2-2</li>
                            </ul>
                        </ul>
                        <li class="menu1">设备3</li>
                        <ul class="menu1ul">
                            <li class="menu1"><div></div>设备4</li>
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

<style>
    .vd-func-view-tit div{
        font-size: 18px;
        color: #02b8f1;
        font-weight: 600;
        line-height: 18px;
        height: 18px;
        border-left: 6px solid #01a8e0;
        padding-left: 10px;
        margin-left: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .vd-func-view-table-tit{
        display: flex;
        background: #448ac1;
        color: #f9f9f9;
        font-size: 15px;
        padding: 5px 0;
    }
    .vd-func-view-table-tit div{
        width: 33.3%;
        text-align: center;
    }

    .vd-func-view-table-body>div{
        display: flex;
        background: rgba(1, 15, 43, 0.50);
        color: #f9f9f9;
        font-size: 14px;
        padding: 5px 0;
        cursor: pointer;
    }
    .vd-func-view-table-body>div:hover{
        background:#426784;
    }
    .vd-func-view-table-body>div>div{
        width: 33.3%;
        text-align: center;
        color: #FF5722;
        font-size: 13px;
    }


    .show_ {
        display: unset;
    }

    .videoWindow {
        z-index: 999;
        position: fixed;
        background: rgba(13, 34, 84, 0.8705882352941177);
        box-shadow: 0 0 16px 0 #0009318f;
        border-radius: 10px;
        display: none;
    }
    @media screen and (max-width: 10000px){
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
    }

    @media screen and (max-width: 1366px){
        .videoWindow {
            width: 640px;
            height: 460px;
            margin-left: calc(50% - 320px);
            margin-top: 7%;
            color: #e6e6e6;
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
        width: 75%;
    }

    .videoWindow .vw_b_r {
        height: 100%;
        width: 25%;
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
    .menu2ul{
        display: none;
        padding: 5px 0 5px 0px;
    }
    .menuul .menu2{
        padding-left:30px;
    }
    .menuul li{
        padding-top:6px;
        padding-bottom:6px;
        padding-left: 16px;
    }
    .menuul li{
        cursor: pointer;
    }
    .menuul li.chos_{
        background: #3190c2;
    }
    .menuul .menu1ul .menu1{
        display:flex;
        align-items:center;
    }
    .menuul .menu1ul .menu1>div{
        width:12px;
        height:12px;
        margin-left: -18px;
        margin-right: 6px;
        background: url(${ctx}/static/model/vd2/img/hideson.png) no-repeat;
    }
    .menuul .menu1ul .menu1.show_>div{
        width:12px;
        height:12px;
        margin-top: -3px;
        background: url(${ctx}/static/model/vd2/img/showson.png) no-repeat;
    }
</style>


<script src="${ctx}/static/model/vd2/js/echarts.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/echarts/theme/vintage.js"></script>
<script src="${ctxStatic}/fengmap/lib/jquery-2.1.4.min.js"></script>
<script src="${ctxStatic}/fengmap/lib/fengmap.min.js"></script>
<script src="${ctxStatic}/fengmap/js/createBubble.js"></script>
<script src="${ctxStatic}/fengmap/lib/bootstrap.min.js"></script>
<script src="${ctxStatic}/fengmap/js/layerGroup.js"></script>
<script type="text/javascript">
    var fxdchart = echarts.init(document.getElementById('aleftboxtmidd'));
    fxdoption = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}个"
        },
        color:['red','orange', 'yellow', '#5578cf', 'green'],
        backgroundColor: 'rgba(1,202,217,.0)',
        grid: {
            left:20,
            right:20,
            top:0,
            bottom:20
        },
        series : [
            {
                name: '告警类型分析',
                type: 'pie',
                radius : '60%',
                center: ['50%', '50%'],
                data:[
                    {value:15, name:'电子围栏告警'},
                    {value:3, name:'SOS告警'},
                    {value:7, name:'滞留告警'},
                    {value:4, name:'标签电压高警'},
                    {value:10, name:'其他类型告警'}
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
    /*fxdchart.on("click",function(param) {
        if(param.dataIndex == 0)
            showOrhide_slider(null,"echart_fxd_r");
        if(param.dataIndex == 1)
            showOrhide_slider(null,"echart_fxd_o");
        if(param.dataIndex == 2)
            showOrhide_slider(null,"echart_fxd_y");
        if(param.dataIndex == 3)
            showOrhide_slider(null,"echart_fxd_b");
        var oEvent = param.event || event;
        stopBubble(oEvent);
    });*/

    window.onresize = function () {
        fxdchart.resize();
    }


    //阻止冒泡事件的兼容性处理
    function stopBubble(e) {
        if(e && e.stopPropagation) { //非IE
            e.stopPropagation();
        } else { //IE
            window.event.cancelBubble = true;
        }
    }

    function closeFuncViews(e) {
        $(".vd-func-views").hide();
        $(".vd-func-views").removeClass("show_");
    }
    function closeVideoWindow(e) {
        $(".videoWindow").hide();
        $(".videoWindow").removeClass("show_");
    }

    $(function () {
        $("#fengMap").css("background-image","url(${ctx}/static/model/vd2/img/rydw/bg__.jpg)");

        // func view show/ hide
        $(".vd-func-views .vw_cls,.videoWindow .vw_cls").click(function(ev){
            closeFuncViews();
            closeVideoWindow();
        })
        $(".vd-btn-1").click(function(ev){
            closeVideoWindow();
            $(".vd-func-views-2").hide();
            $(".vd-func-views-2").removeClass("show_");

            // 根据点击按钮， 获取相应内容
            if($(".vd-func-views-1").hasClass("show_")){
                $(".vd-func-views-1").hide();
                $(".vd-func-views-1").removeClass("show_");
            }else{
                $(".vd-func-views-1").show();
                $(".vd-func-views-1").addClass("show_");
            }
        })
        $(".vd-btn-3").click(function(ev){
            closeFuncViews();

            // 根据点击按钮， 获取相应内容
            if($(".videoWindow").hasClass("show_")){
                $(".videoWindow").hide();
                $(".videoWindow").removeClass("show_");
            }else{
                $(".videoWindow").show();
                $(".videoWindow").addClass("show_");
            }
        })

        $(".vd-btn-4").click(function(ev){
            closeVideoWindow();
            $(".vd-func-views-1").hide();
            $(".vd-func-views-1").removeClass("show_");

            // 根据点击按钮， 获取相应内容
            if($(".vd-func-views-2").hasClass("show_")){
                $(".vd-func-views-2").hide();
                $(".vd-func-views-2").removeClass("show_");
            }else{
                $(".vd-func-views-2").show();
                $(".vd-func-views-2").addClass("show_");
            }
        })

        $(".menuul .menu1").click(function(){
            $(".menu1ul .menu2ul").hide();
            if($(this).parent().hasClass("menu1ul")){
                if($(this).hasClass("show_")){
                    $(this).removeClass("show_");
                }else{
                    $(".menu1ul .menu1.show_").removeClass("show_");
                    $(this).siblings("ul").toggle();
                    $(this).addClass("show_");
                }
            }else{
                $(".menu1ul .menu1.show_").removeClass("show_");
                $(".menuul li.chos_").removeClass("chos_");
                $(this).addClass("chos_");
            }
        })
        $(".menuul .menu2").click(function(){
            $(".menuul li.chos_").removeClass("chos_");
            $(this).addClass("chos_");
        })

    })


    //获取版本号,设置title
    //document.title = '地图单击事件V' + fengmap.VERSION;

    //定义全局map变量
    var map;
    var fmapID = 'zalk-dlfactory';

    //控制文本标注层显示/隐藏
    var labelVisible = true;
    //控制POI标注层显示/隐藏
    var poiVisible = true;
    // 楼层id
    var groupId = 1;

    // 楼层总数
    var glength;

    // 标注
    var layer0 = null;
    var personLayer = null;

    var im = null;
    var im2 = null;
    var jump;
    var markerImg = "";
    var personMarkerImg;
    var markerHeight = 2;
    var markerAnimation = true;
    var personMarkerAnimation = true;
    //	标注添加的类型：0：初始化生成 ,1：查询生成（需清除之前的marker）,2：查询聚焦（换marker颜色，并跳动）
    var addType = 0;

    // 人员实时追踪
    var count = 0;
    var locationMarker = null;

    // 	分类查询
    var groupLayer;
    var searchAnalyser;
    var defaultGroupID = 1; //默认显示楼层
    var res = [];

    //	电子围栏显示
    var polygonMarker;
    var addPolygon = true;
    var addFenceMarker = true;
    var moveMarker = true;
    var imageFenceMarker = null;
    var fenceLayer = null;

    // 控制是否弹出信息框
    var startPick = false;
    //判断当前是否点击的是poi,控制点击公共设施的时候只弹出公共设施的信息框
    var clickedPOI = false;
    // 点击事件ID
    var eventID = null;

    var eventID2 = null;
    var bubbleObj = null;

    // 人员点位数组
    var personCoords = null;

    // popmarker 对象
    var popMarker = null;

    // 人员点位数组
    var personMarkerInfo = [];

    // 人员imagemarker
    var markerMap = {};

    // 电子围栏数据
    var fenceDataList = [];

    // 围栏pmmarker对象map
    var fenceMap = {};

    // 请求点位实时数据的定时器
    var updateTimer;

    // 是否聚焦
    var focus = 1;

    // 生成警报信息定时器
    var msgCardTimer;

    // 警报消息id
    var msgId = 0;

    // 消息、警报等条数
    var msgCount = 0;

    /**
     * 地图坐标转换
     * */


//创建转换器
    var trasformer = new CoordTransformer();


    //转换器初始化
    trasformer.init(locOrigion,locRange,mapParas);



    /**
     * WebSocket
     * */
    var websocket = null;


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        $(window).unload( function() {
        closeWebSocket();
    });
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        if (innerHTML == 'WebSocket连接成功' || innerHTML == 'WebSocket连接发生错误' || innerHTML == 'WebSocket连接关闭'  ) {
            //alert(innerHTML);
        }
        else
            dealData(innerHTML);
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }

    /**
     * websocket 请求数据 人员ImageMarker 请求数据 实时定位更新
     */
    function dealData(data){
        console.info(22);
        if (data.indexOf("DW:") == 0) {//定位信息
            data = data.substring(3, data.length);
            handlePersonMarkerInfo(data);
        } else if (data.indexOf("TJ:") == 0) {//统计人数
            data = data.substring(3, data.length);
            wsGetDeptPersonCount(data);
        }else if (data.indexOf("UD") == 0) {
            data = data.substring(3, data.length);//更新位置信息
            updatePersonMarkerInfo(data);
        }
    }

    window.onload = function() {
        $("#fengMap").css("background-image","url(${ctx}/static/model/vd2/img/rydw/bg__.jpg)");

        //楼层控制控件配置参数
        var ctlOpt = new fengmap.controlOptions({
            //默认在右上角
            position : fengmap.controlPositon.RIGHT_BOTTOM,
            //默认显示楼层的个数
            showBtnCount : 7,
            //初始是否是多层显示，默认单层显示
            allLayer : true,
            //位置x,y的偏移量
            offset : {
                x : 5,
                y : 55
            },
            imgURL : '${ctxStatic}/fengmap/image/',
        });
        map = new fengmap.FMMap({
            //渲染dom
            container : document.getElementById('fengMap'),
            //地图数据位置
            mapServerURL : '${ctxStatic}/fengmap/data/' + fmapID,

            //主题数据位置
            mapThemeURL : '${ctxStatic}/fengmap/data/theme',
            //设置主题
            defaultThemeName : 'zalk-dlfactory',
            // 默认比例尺级别设置为20级
            defaultMapScaleLevel : 20,
            //两楼层间的高度
            defaultGroupSpace: 20,
            //不支持单击模型高亮，true为单击时模型高亮
            modelSelectedEffect : false,

            //是否对不可见图层启用透明设置 默认为true
            focusAlphaMode : false,
            //对不聚焦图层启用透明设置，当focusAlphaMode = true时有效
            focusAlpha : 0.1,

            //开发者申请应用下web服务的key
            key : '0c5907c2c43a800d7b007941d72126b5',
            //开发者申请应用名称
            appName : 'bh_dlkj',
        });


        //打开Fengmap服务器的地图数据和主题
        map.openMapById(fmapID);

        //禁用浏览器右键事件
        $('body').on("contextmenu", function(event) {
            return false;
        })

        /*
         *	地图加载完成回调	===	提示搜索方法区
         */
        map.on('loadComplete', function() {
            map.setBackgroundColor("#ffffff",0);

            //地图加载完后再连接 websocket

            //判断当前浏览器是否支持WebSocket，并建立连接
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://"+document.location.host+"${ctx}/lydwWebsocket");
            }
            else {
                alert('当前浏览器 Not support websocket')
            }
            websocket.onerror = function () {
                setMessageInnerHTML("WebSocket连接发生错误");
            };

            //连接成功建立的回调方法
            websocket.onopen = function () {
                setMessageInnerHTML("WebSocket连接成功");
            }
            //接收到消息的回调方法
            websocket.onmessage = function (event) {
                setMessageInnerHTML(event.data);
            }


            //连接关闭的回调方法
            websocket.onclose = function () {
                setMessageInnerHTML("WebSocket连接关闭");
            }
            glength = map.groupIDs.length;
            if(map.mapScaleLevel>18){
                for(var i = 0;i < glength;i++){
                    var group3 = map.getFMGroup(map.groupIDs[i]);
                    //遍历图层
                    group3.traverse(function(fm, deep) {
                        if (fm instanceof fengmap.FMFacilityLayer) {
                            fm.visible = true;
                        }
                        if (fm instanceof fengmap.FMLabelLayer) {
                            fm.visible = true;
                        }
                    });
                }
            }else{
                for(var i = 0;i < glength;i++){
                    var group3 = map.getFMGroup(map.groupIDs[i]);
                    //遍历图层
                    group3.traverse(function(fm, deep) {
                        if (fm instanceof fengmap.FMFacilityLayer) {
                            fm.visible = false;
                        }
                        if (fm instanceof fengmap.FMLabelLayer) {
                            fm.visible = false;
                        }
                    });
                }
            }

            //创建楼层(按钮型)，创建时请在地图加载后(loadComplete回调)创建。
            groupControl = new fengmap.scrollGroupsControl(map, ctlOpt);
            //通过楼层切换控件切换聚焦楼层时的回调函数
            //groupContro 即为楼层控件对象
            groupControl.onChange(function(groups, allLayer) {
                //groups 表示当前要切换的楼层ID数组,
                //allLayer表示当前楼层是单层状态还是多层状态。
                groupId = map.focusGroupID;

                //	自动跳转到地图中心点
                map.moveTo({
                    //改变中心点x的位置
                    x : map.center.x,
                    //改变中心点y的位置
                    y : map.center.y,
                    //默认时间是0.3秒
                    time : 1,
                    callback : function() {
                    }
                });
            });

            // 默认楼层加载完后不显示，需自定义设置要显示的楼层和聚焦层
            map.visibleGroupIDs = map.groupIDs;
            map.focusGroupID = map.groupIDs[0];

            /*// 获取人员点标注  coords
            getPersonCoords();

            // 获取部门、人数
            getDeptPersonCount();

            // 设置定时器  每隔10秒钟请求一次点位的实时数据
            updateTimer = window.setInterval(getDeptPersonCount, 10000);*/

            // 获取电子围栏信息
            getPolygonMarker();

            // 生成信息警报卡

            // 五秒后间断请求信息、警报数据
            msgCardTimer = window.setInterval(getMsgData, 10000);

            //获取搜索类
            searchAnalyser = map.searchAnalyser;

            // 选择围栏显示
            $('.fenceSelect').change(function() {
                var polygonMarkerId = $(".fenceSelect").val();
                focus = 1;
                // 清空围栏
                removeFences();
                //	显示围栏
                if (polygonMarkerId != -1) {
                    if(polygonMarkerId == -2) {
                        focus = 0;
                        for(var i=0;i<fenceDataList.length;i++){
                            addPmMarker(fenceDataList[i].fence,focus);
                        }
                    }else{
                        addPmMarker(fenceDataList[polygonMarkerId].fence,focus);
                    }
                }
            });
        });

        /**
         *	地图级别变化事件.比如pc端鼠标滚动放大\缩小事件等.
         */
        map.on('scaleLevelChanged', function(event) {
            if(map.mapScaleLevel>18){
                for(var i = 0;i < glength;i++){
                    var group3 = map.getFMGroup(map.groupIDs[i]);

                    //遍历图层
                    group3.traverse(function(fm, deep) {
                        if (fm instanceof fengmap.FMFacilityLayer) {
                            fm.visible = true;
                        }
                        if (fm instanceof fengmap.FMLabelLayer) {
                            fm.visible = true;
                        }
                    });
                }
            }else{
                for(var i = 0;i < glength;i++){
                    var group3 = map.getFMGroup(map.groupIDs[i]);

                    //遍历图层
                    group3.traverse(function(fm, deep) {
                        if (fm instanceof fengmap.FMFacilityLayer) {
                            fm.visible = false;
                        }
                        if (fm instanceof fengmap.FMLabelLayer) {
                            fm.visible = false;
                        }
                    });
                }
            }
        })


        /**
         *	地图楼层变化事件
         */
        map.on('focusGroupIDChanged', function(event) {
            if(popMarker != null){
                popMarker.close();
            }
        })


        /**
         *	基础点击弹出组件信息tip 方法区
         */
        map.on('mapClickNode', function(event) {
            if (event.nodeType == fengmap.FMNodeType.IMAGE_MARKER) {
                if(event.target.myiframe != null && event.target.myiframe != undefined){
                    var popmarkerInfo = event.target.myiframe;
                }else{
                    var popmarkerInfo = event.target.name;
                }
                var popmarkerInfoArr = popmarkerInfo.split("_");

                if(popMarker != null){
                    popMarker.close();
                }
                //信息框控件大小配置
                var ctlOpt1 = new fengmap.controlOptions({
                    mapCoord: {
                        //设置弹框的x轴
                        x: event.target.x,
                        //设置弹框的y轴
                        y: event.target.y,
                        height: 0, //控制信息窗的高度
                        //设置弹框位于的楼层
                        groupID: event.target.groupID
                    },
                    //设置弹框的宽度
                    width: 170,
                    //设置弹框的高度
                    height: 80,
                    marginTop: 0,
                    //设置弹框的内容
                    content: '<div class="popMarkerDiv"><div style="height:30px;">'+popmarkerInfoArr[0]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[1]+' - '+popmarkerInfoArr[2]+'</div><div style="height:20px;font-size:12px">'+popmarkerInfoArr[3]+'</div></div>',
                    closeCallBack: function() {
                        //信息窗点击关闭操作
                        // alert('信息窗关闭了！');
                    }
                });

                //添加弹框到地图上
                popMarker = new fengmap.FMPopInfoWindow(map, ctlOpt1);
                //popMarker.close();
                $(".fm-control-popmarker .popMarkerDiv").parent("div").css('height','70px');

                // 标注视野聚焦
                map.moveTo({
                    //改变中心点x的位置
                    x : event.target.x,
                    //改变中心点y的位置
                    y : event.target.y,
                    groupID: event.target.groupID,
                    //默认时间是0.3秒
                    time : 1,
                    callback : function() {
                    }
                });
            }
        });


        $(".search_2").hide();
        $(".search1").click(function() {
            $(this).addClass("search_chosen").siblings()
                .removeClass("search_chosen");
            $(".search_1").show();
            $(".search_2").css('display', 'none');
        })
        $(".search2").click(function() {
            $(this).addClass("search_chosen").siblings()
                .removeClass("search_chosen");
            $(".search_1").hide();
            $(".search_2").css('display', 'flex');
        });

    };


    /**
     *	获取部门、人数
     */
    var deptPersonCount;
    function wsGetDeptPersonCount(data){
        deptPersonCount = jQuery.parseJSON(data);
        var deptPersonCountLength = deptPersonCount.length;
        $("#deptPersonInfo li").remove();
        if (deptPersonCountLength > 0) {
            var html = '';
            for (var i = 0; i < deptPersonCountLength; i++) {
                html += '<li style="display:flex;align-items:center;">';
                html += '<div class="" style="width:50%;padding:5px 0;text-align:center;">'+deptPersonCount[i].bm+'</div>';
                html += '<div class="" style="width:50%;padding:5px 0;text-align:center;">'+deptPersonCount[i].count+'</div>';
                html += '</li>';
            }
            $("#deptPersonInfo").append(html);
        }
    }


    /**
     * 	全新人员批处理生成  imageMarker lastIndex为 personMarkerInfo中的最有一位
     */
    function addMarkersBatch(index,batchCount,maxCount,newPersonMarkerInfo) {
        if (index >= maxCount) {
            return;
        }
        for (var i = index; i < (batchCount + index); i++) {
            var newPersonCoord = newPersonMarkerInfo[i];
            var newPid = "_"+newPersonCoord.id;
            if(markerMap.newPid==undefined || markerMap.newPid==null){
                var newGroup = map.getFMGroup(map.groupIDs[newPersonCoord.z]);

                //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
                var newLayer = newGroup.getOrCreateLayer('imageMarker');

                //图标标注对象，默认位置为该楼层中心点
                var newIm = new fengmap.FMImageMarker({
                    x: newPersonCoord.x,
                    y:newPersonCoord.y,
                    // 设置信息窗内容
                    name: newPersonCoord.popmarkerInfo,
                    groupID: newPersonCoord.z + 1,
                    height:2,
                    ID:newPersonCoord.id,
                    //设置图片路径
                    url: '${ctxStatic}/fengmap/image/bluePersonMarker.png',
                    //设置图片显示尺寸
                    size: 24,
                    callback: function() {
                        index++;
                        if (index % batchCount == 0) {
                            addMarkersBatch(index,batchCount,maxCount,newPersonMarkerInfo);
                        }
                    }
                });
                // 注：markerMap[key] 中key应为字符串 不然后续delete markerMap.key无法执行
                markerMap["_"+newPersonCoord.id] = newIm;
                newLayer.addMarker(newIm);
            }
        }
    };


    /**
     * 人员ImageMarker 请求数据 实时定位更新
     */
    function updateMarkers() {
        $.ajax({
            type : 'post',
            url : "${ctx}/lydw/rydw/listjson",
            success : function(data) {
                if (data != null) {
                    handlePersonMarkerInfo(data);
                }
            }
        });
    }

    /**
     * 将新收到的蓝牙信息或是未绑定人员的信息放到新的集合中
     */
    function addLyInfo(newPersonMarkerInfo,personCoord){

        // 此时得到的点位为全新点位，故再次addImageMarker 并加入到personMarkerInfo数组中
        var popmarkerInfo = "设备id:"+personCoord.file+"_"+"未绑定人员"+"_"+"请绑定人员并刷新"+"_"+ dateFtt("yyyy-MM-dd hh:mm:ss",new Date(personCoord.uptime));
        var personMarker = {
            x : personCoord.x,
            y : personCoord.y,
            z : personCoord.z,
            name: personCoord.name,
            id : personCoord.file,
            file : personCoord.file,
            popmarkerInfo : popmarkerInfo
        }
        personMarkerInfo.push(personMarker);
        newPersonMarkerInfo.push(personMarker);

    }

    /**
     * 根据设备id和人员id匹配，进行移动，如果有新的蓝牙设备信息，无法与人员相匹配，给出提示
     */

     function updatePersonMarkerInfo(data) {
        if (data != null) {
            // 定时请求得到的新人员点位数组
            var newPersonMarkerInfo = [];
            var personCoord = jQuery.parseJSON(data);
            var file = personCoord.file;//设备id
            var flag = false;//该设备是否已存在
            var loc = {'x':personCoord.x,'y':personCoord.y};
            var mapCoord = trasformer.transform(loc);
            personCoord.x = mapCoord.x;
            personCoord.y = mapCoord.y;
            for (var i = 0; i < personMarkerInfo.length ; i++) {

                if (personMarkerInfo[i].file == file) {
                    flag = true;
                    personMarkerInfo[i].x = personCoord.x;
                    personMarkerInfo[i].y = personCoord.y;
                    personMarkerInfo[i].z = personCoord.z;

                    var popmarkerInfo = personMarkerInfo[i].popmarkerInfo.split("_");//信息
                    //更新蓝牙定位日期信息
                    var newInfo = popmarkerInfo[0]+"_"+popmarkerInfo[1]+"_"+popmarkerInfo[2]+"_"+ dateFtt("yyyy-MM-dd hh:mm:ss",new Date(personCoord.uptime));
                    personMarkerInfo[i].popmarkerInfo = newInfo;


                    markerMap["_"+personMarkerInfo[i].id]["x_"] = personCoord.x;
                    markerMap["_"+personMarkerInfo[i].id]["y_"] = personCoord.y;
                    markerMap["_"+personMarkerInfo[i].id]["z_"] = personCoord.z;
                    markerMap["_"+personMarkerInfo[i].id]["name_"] = newInfo;
                    markerMap["_"+personMarkerInfo[i].id].moveTo({
                        //设置imageMarker的x坐标
                        x:personMarkerInfo[i].x,
                        //设置imageMarker的y坐标
                        y: personMarkerInfo[i].y,
                        // 楼层
                        z: personCoord.z,
                        // moveTo时间设置为6秒,默认为1秒。
                        time: 3,//判断目标点是否进入围栏区域   personMarker: 人员当前位置
                        update: function(personMarker) {
                            // 判断PolygonMarker是否包含Marker当前的位置
                            var isContained = polygonMarker.contain(personMarker);

                            //未进入围栏
                            if (!isContained) {
                                personMarker.url = '${ctxStatic}/fengmap/image/redPeopleImage.png';
                            } else {
                                personMarker.url = '${ctxStatic}/fengmap/image/bluePeopleImage.png';
                            }
                        },
                        callback: function() {
                        },
                    });


                }
                
            }
            if (!flag) {
                addLyInfo(newPersonMarkerInfo,personCoord)
                var length = newPersonMarkerInfo.length;
                if(length>0 && length>50){
                    addMarkersBatch(0,50,length,newPersonMarkerInfo);
                }
                else if(length>0 && length<=50){
                    addMarkersBatch(0,length,length,newPersonMarkerInfo);
                }
            }
        }

    }


    /**
     * 处理人员位置的数据,如果有新的蓝牙设备信息，无法与人员相匹配，给出提示
     * data
     */
    function handlePersonMarkerInfo(data) {
        if (data != null) {
            personCoords = jQuery.parseJSON(data);
            var personCoordsLength = personCoords.length;
            personMarkerImg = 0;
            if (personCoordsLength > 0) {
                // 将老im对象集合提出来进行removePersonMarkers 以提高执行效率
                var markerMap_old = markerMap;
                // 每次定时更新点位时，清空新增人员点位信息数组及markerMap_cz_id对象的id数组
                var markerMap_cz_id = [];
                // 定时请求得到的新人员点位数组
                var newPersonMarkerInfo = [];
                // 重置personMarkerInfo
                personMarkerInfo = [];

                for (var i = 0; i < personCoordsLength; i++) {
                    var popmarkerInfo = personCoords[i].name+"_"+personCoords[i].bm+"_"+personCoords[i].gw+"_"+personCoords[i].uptime;
                    var loc = {'x':personCoords[i].x,'y':personCoords[i].y};
                    var mapCoord = trasformer.transform(loc);
                    personCoords[i].x = mapCoord.x;
                    personCoords[i].y = mapCoord.y;
                    var personCoord = {
                        x : personCoords[i].x,
                        y : personCoords[i].y,
                        z : personCoords[i].z ,
                        name: personCoords[i].name,
                        id : personCoords[i].ygid,
                        file : personCoords[i].file,
                        popmarkerInfo : popmarkerInfo
                    }
                    personMarkerInfo.push(personCoord);

                    // markerMap存在im对象时
                    if(Object.getOwnPropertyNames(markerMap).length>0){
                        // 在markerMap 中找到相应id 的imagemarker对象，调用moveTo方法，实现实时定位更新
                        if(markerMap["_"+personCoords[i].ygid]){
                            markerMap["_"+personCoords[i].ygid].moveTo({
                                //设置imageMarker的x坐标
                                x: personCoords[i].x,
                                //设置imageMarker的y坐标
                                y: personCoords[i].y,
                                // 楼层
                                z: personCoords[i].z ,
                                // moveTo时间设置为6秒,默认为1秒。
                                time: 3,
                                //判断目标点是否进入围栏区域   personMarker: 人员当前位置
                                update: function(personMarker) {
                                    // 判断PolygonMarker是否包含Marker当前的位置
                                    var isContained = polygonMarker.contain(personMarker);

                                    //未进入围栏
                                    if (!isContained) {
                                        personMarker.url = '${ctxStatic}/fengmap/image/redPeopleImage.png';
                                    } else {
                                        personMarker.url = '${ctxStatic}/fengmap/image/bluePeopleImage.png';
                                    }
                                },
                                callback: function() {
                                },
                            });
                            markerMap["_"+personCoords[i].ygid][name] = popmarkerInfo;
                            // 将已存在的im对象放入新的集合中
                            markerMap_cz_id.push("_"+personCoords[i].ygid);
                        }else{
                            // 此时得到的点位为全新点位，故再次addImageMarker 并加入到personMarkerInfo数组中
                            var popmarkerInfo = personCoords[i].name+"_"+personCoords[i].bm+"_"+personCoords[i].gw+"_"+personCoords[i].uptime;
                            var personCoord = {
                                x : personCoords[i].x,
                                y : personCoords[i].y,
                                z : personCoords[i].z,
                                name: personCoords[i].name,
                                id : personCoords[i].ygid,
                                file : personCoords[i].file,
                                popmarkerInfo : popmarkerInfo
                            }
                            newPersonMarkerInfo.push(personCoord);
                        }
                    }else{// markerMap为空
                        // 此时得到的点位为全新点位，故再次addImageMarker 并加入到personMarkerInfo数组中
                        var popmarkerInfo = personCoords[i].name+"_"+personCoords[i].bm+"_"+personCoords[i].gw+"_"+personCoords[i].uptime;
                        var personCoord = {
                            x : personCoords[i].x,
                            y : personCoords[i].y,
                            z : personCoords[i].z,
                            name: personCoords[i].name,
                            id : personCoords[i].ygid,
                            file : personCoords[i].file,
                            popmarkerInfo : popmarkerInfo
                        }
                        newPersonMarkerInfo.push(personCoord);
                    }
                }
                // 将为存在于personMarkerInfo数组中，本次未获取到的点位标注去除
                removePersonMarkers(markerMap_old,markerMap_cz_id);

                // 若有全新点位 ，调用 imagemarker批处理生成方法
                if(newPersonMarkerInfo!=null && newPersonMarkerInfo.length>0){
                    var length = newPersonMarkerInfo.length;
                    if(length>0 && length>50){
                        addMarkersBatch(0,50,length,newPersonMarkerInfo);
                    }
                    else if(length>0 && length<=50){
                        addMarkersBatch(0,length,length,newPersonMarkerInfo);
                    }
                }
            }else{
                if(Object.getOwnPropertyNames(markerMap).length>0){
                    Object.keys(markerMap).forEach(function(key){
                        // 当在定时更新的im对象集合里找不到旧im对象集合中的 im对象时，删除该对象对应的标注点
                        for(var i = 0;i < glength;i++){
                            var newGroup = map.getFMGroup(i+1);
                            //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
                            var newLayer = newGroup.getOrCreateLayer('imageMarker');
                            newLayer.removeMarker(markerMap[key]);
                            delete(markerMap[key]);
                        }
                    });
                }
            }
        }
    }




    /**
     * 将为存在于personMarkerInfo数组中，本次未获取到的点位标注去除
     */
    function removePersonMarkers(markerMap_old, markerMap_cz_id) {
        Object.keys(markerMap_old).forEach(function(key){
            //markerMap_old.forEach(function (value, key, map) {
            var count = 0;
            for(var i = 0;i < markerMap_cz_id.length;i++){
                if(key != markerMap_cz_id[i])
                {
                    count++;
                }
            }
            // 当在定时更新的im对象集合里找不到旧im对象集合中的 im对象时，删除该对象对应的标注点
            if(count == markerMap_cz_id.length)
            {
                for(var i = 0;i < glength;i++){
                    var newGroup = map.getFMGroup(i+1);
                    //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
                    var newLayer = newGroup.getOrCreateLayer('imageMarker');
                    newLayer.removeMarker(markerMap[key]);
                }
                delete(markerMap[key]);
            }
        });
    }

    /**
     * 创建定位点标注		暂未调用
     */
    function addLocationMarkers(personCoord, personMarkerImg) {
        if (personMarkerImg == 0) {
            personMarkerImg = "bluePersonMarker";
        } else if (personMarkerImg == 1) {
            personMarkerImg = "redPersonMarker";
        } else {
            personMarkerImg = "bluePersonMarker";
        }

        locationMarker = new fengmap.FMLocationMarker({
            //设置图片的路径
            url : '${ctxStatic}/fengmap/image/' + personMarkerImg + '.png',
            //设置图片显示尺寸
            size : 36,
            // 设置信息窗内容
            name: personCoord.popmarkerInfo,
            //show: false,
            //设置图片高度
            height : 5,
            callback : function() {
                // 设置LocationMarker的，初始方向
                //locationMarker.direction = -90;
            }
        });

        map.addLocationMarker(locationMarker);

        locationMarker.setPosition({
            //设置定位点的x坐标
            x : personCoord.x,
            //设置定位点的y坐标
            y : personCoord.y,
            //设置定位点所在楼层
            groupID : map.groupIDs[personCoord.z],
            //设置定位点的高于楼层多少
            zOffset : 1,
        });
    };


    /**
     *	优化搜索 留出公共的find功能		3/22	cap
     */
    function checkValue() {
        //	禁止瞬时连点
        $("#searchSpan").css('pointer-events','none');
        // 解除禁止连点
        var noclickingsTimer = window.setTimeout(noclickings, 500);
        function noclickings() {
            $("#searchSpan").css('pointer-events','unset');
            //去掉定时器的方法
            window.clearTimeout(noclickingsTimer);
        }

        var personInpVal = $("#input_2").val();
        if (personInpVal != "" && personInpVal != "人员姓名..") {
            findPerson(personInpVal,0);
        }else{
            alert("请输入正确的人员名称！");
        }
    }


    /**
     *	查找人员 跳动显示 点标注 markers
     */
    function findPerson(personInpVal,state) {
        if(state == 9){
            if(!$("#msgCardUl").hasClass("hid36")){
                $("#msgCardUl").addClass("hid36");
                $("#msgCardUl").parent().css('display','none');
            }
        }

        // 清空之前的点标注
        removeImageMarkers();

        // 除去地图元素高亮
        map.selectNull();

        // 关闭之前打开的pop marker
        if(popMarker != null){
            popMarker.close();
        }
        var isfind = false;
        for(var i = 0; i < personMarkerInfo.length; i++){
            if(personMarkerInfo[i].name == personInpVal){
                isfind = true;
                var queryMarker = personMarkerInfo[i];

                var group2 = map.getFMGroup(map.groupIDs[queryMarker.z]);

                //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
                personLayer = group2.getOrCreateLayer('imageMarker');

                //添加图片标注层到模型层。否则地图上不会显示(实际上 加不加都行- -)
                //group2.addLayer(personLayer);

                im2 = new fengmap.FMImageMarker({
                    x : queryMarker.x,
                    y : queryMarker.y,
                    height : 4,
                    url : '${ctxStatic}/fengmap/image/redImageMarker.png',
                    size : 36,
                    callback : function() {
                        if (markerAnimation) {
                            jump = im2.jump({
                                times : 0,
                                duration : 1,
                                delay : 0.5,
                                height : 6
                            });
                        }
                    }
                });
                personLayer.addMarker(im2);


                // 标注视野聚焦
                map.moveTo({
                    //改变中心点x的位置
                    x : queryMarker.x,
                    //改变中心点y的位置
                    y : queryMarker.y,
                    groupID: queryMarker.z + 1,
                    //默认时间是0.3秒
                    time : 1,
                    callback : function() {
                    }
                });

                break;
            }
        }
        if (!isfind) {
            alert("5尚未找到该人员相应信息！");
        }
    }


    // 清除imageMarker 对象
    function removeImageMarkers() {
        if (personLayer != null && im2 != null) {
            personLayer.removeMarker(im2);
        }
        if (layer0 != null && im!=null) {
            layer0.removeMarker(im);
        }

        // 搜索table隐藏
        oTable_container.style.display = 'none';
    }


    /**
     *	创建电子围栏
     */
    function getPolygonMarker(polygonMarkerId) {
        //polygonMarkerId ?
        $.ajax({
            type:'post',
            url:"${ctx}/lydw/dzwl/dzwllist",
            success: function(data){
                $(".fenceSelect option").remove();
                // 生成电子围栏option
                var html = '<option value="-2">显示全部</option><option value="-1">不显示</option>';

                var data = $.parseJSON(data);
                if(data.length > 0 && data != null){
                    for (var i = 0; i < data.length; i++) {
                        var index = i;
                        var fenceData = [];
                        var ponits = data[i].mappoint.split('||');
                        for (var j = 0; j < ponits.length; j++) {
                            var p = ponits[j].split(',');

                            var coord = {
                                x: p[0],
                                y: p[1],
                                z: p[2]
                            }
                            fenceData.push(coord);
                        }
                        var fencesData = {
                            fence: fenceData,
                            fenceId: data[i].id
                        }
                        fenceDataList.push(fencesData);
                        html += '<option value="'+index+'">'+data[i].name+'</option>';
                    }

                    focus = 0;
                    for(var i=0;i<fenceDataList.length;i++){
                        addPmMarker(fenceDataList[i].fence,fenceDataList[i].fenceId,focus);
                    }
                }
                $(".fenceSelect").append(html);
            }
        });
    }


    // 真正想地图中添加电子围栏的方法
    function addPmMarker(fenceData,fenceId,focus) {
        var gid = fenceData[0].z - 1;
        var group = map.getFMGroup(map.groupIDs[gid]);

        //返回当前层中第一个polygonMarker,如果没有，则自动创建
        fenceLayer = group.getOrCreateLayer('polygonMarker');

        polygonMarker = new fengmap.FMPolygonMarker({
            //设置透明度
            alpha: .5,
            //设置边框线的宽度
            lineWidth: 1,
            //设置高度
            height: 2,
            //设置多边形坐标点
            points: fenceData
        });

        fenceLayer.addMarker(polygonMarker);

        // 将该电子围栏对象polygonMarker 存入fenceMap 集合中备用
        fenceMap[fenceId] = polygonMarker;


        if(focus!=0){
            // 标注视野聚焦
            map.moveTo({
                //改变中心点x的位置
                x : fenceData[0].x,
                //改变中心点y的位置
                y : fenceData[0].y,
                groupID: fenceData[0].z,
                //默认时间是0.3秒
                time : 0.3,
                callback : function() {
                }
            });
        }
    }


    /**
     *  清空所有楼层的围栏
     */
    function removeFences(){
        // 真正想地图中添加电子围栏的方法
        for(var i = 0; i<5;i++){
            var group = map.getFMGroup(map.groupIDs[i]);

            //返回当前层中第一个polygonMarker,如果没有，则自动创建
            var fenceLayer_ = group.getOrCreateLayer('polygonMarker');
            fenceLayer_.removeAll();
        }
    }


    /**
     *	原生搜索框  + table
     */
    var oSeachText = document.querySelector('#seachText');
    var oTable_container = document.querySelector('#table-container');
    var oHotwords = document.querySelector('#hotwords');

    $("body").click(function(e) {
        if (e.target.tagName == "CANVAS") {
            $(oHotwords).hide();
        }
    });

    //根据FID查询
    function findCoordinate(fid) {
        oHotwords.style.display = 'none';
        var searchReq = new fengmap.FMSearchRequest(
            fengmap.FMNodeType.MODEL); //设置查询地图元素类型
        searchReq.FID(fid);
        searchAnalyser.query(searchReq, function(request, result) {
            //result 为查询到的结果集。
            var models = result[0];
            if (models != null) {
                filldata(models);
                oTable_container.style.display = 'block';
            } else {
                oTable_container.style.display = 'none';
            }
        });
    };

    // 原生搜索 table添加Markers
    function addMarkers(gid, X, Y, markerImg) {
        // removeMarkers();
        removeImageMarkers();

        var group = map.getFMGroup(map.groupIDs[gid - 1]);

        //返回当前层中第一个imageMarkerLayer,如果没有，则自动创建
        layer0 = group.getOrCreateLayer('imageMarker');

        if (markerImg == 0) {
            markerImg = "bluePersonMarker";
            markerHeight = 2;
            markerAnimation = false;
        } else if (markerImg == 1) {
            markerImg = "redPersonMarker";
            markerHeight = 2;
            markerAnimation = false;
        } else {
            markerImg = "blueImageMarker";
            markerHeight = 8;
        }

        im = new fengmap.FMImageMarker({
            x : X,
            y : Y,
            height : markerHeight,
            url : '${ctxStatic}/fengmap/image/' + markerImg + '.png',
            size : 36,
            callback : function() {
                im.alwaysShow();
                if (markerAnimation) {
                    jump = im.jump({
                        times : 0,
                        duration : 1,
                        delay : 0.5,
                        height : 10
                    });
                }
            }
        });
        layer0.addMarker(im);
    };


    //定位跳转
    function moveTo(coord) {
        map.focusGroupID = coord.groupID;
        map.moveTo(coord);
    };

    //删除Marker
    function removeMarkers() {
        //获取多楼层Marker
        map.callAllLayersByAlias('imageMarker', function(layer0) {
            layer0.removeAll();
        });
    };

    //根据关键字查询店铺
    oSeachText.addEventListener('keyup', function(e) {
        var keyword = this.value.trim();
        if (keyword !== '搜索' && keyword !== '') {
            var searchReq = new fengmap.FMSearchRequest(
                fengmap.FMNodeType.MODEL);
            searchReq.keyword(keyword);
            searchAnalyser.query(searchReq, function(request, result) {
                var models = result;
                if (models != null && models.length > 0) {
                    oHotwords.style.display = 'block';
                    listDate(models);
                } else {
                    oTable_container.style.display = 'none';
                    oHotwords.style.display = 'none';
                }
            });
        } else {
            oHotwords.style.display = 'none';
            $(oTable_container).hide();
        }
    });

    //创建提示下拉框
    var listDate = function(data) {
        oHotwords.innerHTML = '';
        var li = '';
        for ( var i in data) {
            var model = data[i];
            li += '<li data-id="' + model.FID + '">'
                + (!model.name ? "空" : model.name) + '</li>';
        }

        oHotwords.innerHTML = li;
        var aLi = oHotwords.getElementsByTagName('li');
        for (var i = 0; i < aLi.length; i++) {
            (function(i) {
                aLi[i].onclick = function(e) {
                    e.stopPropagation(); //阻止冒泡到body
                    var index = i;
                    oSeachText.value = this.innerHTML;
                    var fid = this.attributes["data-id"].nodeValue;
                    findCoordinate(fid);
                    oHotwords.style.display = 'none';
                }
            })(i);
        }
    }


    //	填充表格数据
    function filldata(model) {
        var oTable = document.querySelector('#table');
        var oTableBody = document.querySelector('#table-body');
        var aTr = oTable.getElementsByTagName('tr');
        oTableBody.innerHTML = '';

        markerImg = 2;
        addMarkers(model.groupID, model.mapCoord.x, model.mapCoord.y,markerImg);

        var coord = {
            x : model.mapCoord.x,
            y : model.mapCoord.y,
            groupID : model.groupID
        };
        moveTo(coord); //定位跳转

        //地图上当前设置的模型元素处于高亮状态。
        //如果最后一个参数设置为true,storeSelect(model,true,true)，表示之前的和当前的模型都处于高亮转态。可使用map.selectNull()方法清除。
        map.storeSelect(model, true, false);
        oTableBody.innerHTML = '<tr class="active"><td style="padding: 4px;text-align:center">1</td><td>'
            + (model.ID == undefined ? "空" : model.ID)
            + '</td><td>'
            + (!model.name ? "空" : model.name)
            + '</td><td>'
            + model.groupID
            + '</td><td>'
            + model.mapCoord.x
            + '</td><td>'
            + model.mapCoord.y
            + '</td><td>' + model.mapCoord.z + '</td></tr>';

        for (var i = 0; i < aTr.length; i++) {
            (function(i) {
                aTr[i].onclick = function() {
                    var index = i;
                    if (index <= 0)
                        return;
                    var x = parseFloat(this.cells[4].innerText);
                    var y = parseFloat(this.cells[5].innerText);
                    var z = parseFloat(this.cells[6].innerText);
                    var gid = parseInt(this.cells[3].innerText);
                    var id = parseInt(this.cells[1].innerText);
                    var index = parseInt(this.cells[0].innerText);

                    var coord = {
                        x : x,
                        y : y,
                        z : z,
                        groupID : gid
                    };
                    moveTo(coord); //定位跳转

                    map.storeSelect(model, true, false);
                }
            })(i);
        }
    }

    // table 关闭按钮
    var oTitle = document.querySelectorAll('.title')[0];
    oTitle.onclick = function() {
        oTable_container.style.display = 'none';
    };


    /**
     *  获取警报等信息
     */
    function getMsgData(){
        $("#msgCount").siblings("div").css('background','url(${ctx}/static/fengmap/image/bell2.png)');
        // data ?
        //	msgCount = ?
        msgCount++;

        if(msgCount>2){
            $("#msgCount").html("999+");
        }else{
            $("#msgCount").html(msgCount);
        }

        addMsgCard(1,1,"夏永强",1);
    }

    /**
     * 	加载完成 显示警报通知卡
     *	captain		19/1/2
     */
    function addMsgCard(coord,info,uname,type){
        msgId++;

        // 警报卡颜色设置
        var cardBg = '';
        if(type==0){
            cardBg = "bgr36";
        }else if(type==1){
            cardBg = "bgo36";
        }else if(type==2){
            cardBg = "bggr36";
        }else{
            cardBg = "bgb36";
        }

        $("#msgCardBox").html("");

        // 生成警报卡
        var msgCard = '<div class="msgCard '+cardBg+' msgId'+msgId+'" onclick="findPerson(\''+uname+'\',9)">';
        msgCard += '<div class="msgCardIc"></div>';
        msgCard += '<div class="cardDiv">';
        msgCard += '<div class="cardInfo">';
        msgCard += '<div class="cardType cardDiv">';
        msgCard += '<div class="cardType line" style="font-size:15px;font-weight: bolder;">围栏警报</div><div style="margin-top: -2px;font-size:12px;font-weight: normal;margin-left:10px;flex-shrink:0;">03/26 10:21</div>';
        msgCard += '</div>';
        msgCard += '<div class="cardType cardDiv">';
        msgCard += '<div class="cardType line" style="font-size:12px">您有2条警报信息未处理</div><div style="margin-top: -2px;margin-left:10px;flex-shrink:0;">'+uname+'</div>';
        msgCard += '</div>';

        //msgCard += '<div class="cardContent line"></div></div>';
        //msgCard += '<div class="cardClose" onclick="hideCard(this)"></div>';
        msgCard += '</div></div>';

        // 向msgCardBox 中滑入msgCard
        $("#msgCardBox").html(msgCard);

        $(".msgId"+msgId).animate({
            width: '205px',
            opacity: '1'
        }, "1000");

        // 将消息卡插入到 消息卡列表中
        // 生成消息卡列表中的消息卡
        var msgCard2 = '<div class="msgCard '+cardBg+' msg2Id'+msgId+'" style="margin-left:10px;margin-bottom:5px" onclick="findPerson(\''+uname+'\',9)">';
        msgCard2 += '<div class="msgCardIc"></div>';
        msgCard2 += '<div class="cardDiv">';
        msgCard2 += '<div class="cardInfo">';
        msgCard2 += '<div class="cardType cardDiv">';
        msgCard2 += '<div class="cardType">围栏警报</div><div style="margin-top: -2px;">'+uname+'</div>';
        msgCard2 += '</div>';
        msgCard2 += '<div class="cardContent line">您有2条警报信息未处理</div></div>';
        //msgCard2 += '<div class="cardClose" onclick="hideCard(this)" style="display:none"></div>';
        msgCard2 += '</div></div>';

        $("#msgCardUl").prepend(msgCard2);

        $(".msg2Id"+msgId).animate({
            width: '205px',
            opacity: '1'
        }, "1000");
    }


    /**
     *  关闭当前消息卡
     */
    function hideCard(msgCard){
        event.stopPropagation();
        $(msgCard).parent().parent().animate({
            width: '0px',
            opacity: '0'
        }, "1000",function(){
            $(msgCard).parent().parent().remove();
        });
    }

    /**
     *  显隐消息列表
     */
    function showMsgList(){
        $("#msgCardUl").toggleClass("hid36");
        if($("#msgCardUl").hasClass("hid36"))
            $("#msgCardUl").parent().css('display','none')
        else
            $("#msgCardUl").parent().css('display','flex')
    }

    /**
     * 将时间戳 date 转为指定日期格式
     * @param fmt
     * @param date
     * @returns {*}
     */
    function dateFtt(fmt,date){
        var o = {
            "M+" : date.getMonth()+1,                 //月份
            "d+" : date.getDate(),                    //日
            "h+" : date.getHours(),                   //小时
            "m+" : date.getMinutes(),                 //分
            "s+" : date.getSeconds(),                 //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S"  : date.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

    function req() {
        window.location.href="${ctx}/a";
    }
</script>

</body>
</html>
