<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>卡口作业运单查询</title>
    <meta name="decorator" content="default"/>
    <style>
        .lis {
            background: rgb(255, 255, 255);
            flex-shrink: 0;
            list-style-type: none;
            padding: 15px 17px;
            margin-bottom: 8px;
        }

        .divs {
            width: 100%;
            display: flex;
            justify-content: space-between
        }

        .spans {
            font-size: 17px;
            color: #333;
        }

        .is {
            width: 20px;
            height: 20px;
            margin-right: 5px;
            color: #333
        }

        .score {
            color: #38f;
            font-size: 17px;
        }

        .smalls {
            width: 100%;
            display: flex;
            justify-content: space-between;
            color: grey;
            font-size: 14px;
            margin-top: 3px

        }

    </style>
    <script src="${ctx}/static/common/jquery.barcode.js"></script>
</head>
<body style="    margin: 0;">
<!-- 搜索域-->
<div class="">
    <div class=""
         style="width:100%;height:89px;background: white;justify-content: center;display: flex;margin-top: 50px">
        <img style="width: 338px;height:89px" src="${ctx}/static/model/images/app/searchpic.png"/>
    </div>
    <div style="width: 100%;display: flex;justify-content: center">
        <div class=""
             style="width: 500px;height:40px;display: flex;align-items: center;border: 1px solid rgb(60, 60, 60);margin: 0 16px">
            <input id="plateNum" placeholder="请输入车牌号"
                   style="width: calc(100% - 50px);display: flex;flex-shrink:1;border:0;outline:none;margin-left:10px"/>
            <div class="" style="width:50px;display: flex;align-items: center;">
                <div class="search"
                     style="width:50px;height:40px;display: flex;flex-shrink: 0;justify-content: center;align-items: center;color:#38f;font-weight: bolder;border-left: 0.1px solid rgb(232, 232, 232)">
                    搜索
                </div>
            </div>
            <ul class="orderlist" style="position: absolute;background:rgba(237, 237, 237, 0.8);width:498px
            ;margin: 0px;margin-top:20px;display: flex;flex-direction:column;padding: 0;overflow-x: hidden;overflow-y: scroll;">
                <%-- <li class="lis">
                     <div class="divs">
                         <span class="spans"><i class="fa fa-list-alt is"></i>黑B888888</span>
                         <span class="score">积分：100</span>
                     </div>
                     <div class="smalls">
                         <span>单号：C_20190218150701012</span>
                         <span>2019/2/18</span>
                     </div>
                 </li>--%>
            </ul>
        </div>
    </div>

    <div class="noOrder"
         style="width:100%;flex-direction: column;align-items: center;justify-content:center;display:
         none">
        <img style="width: 300px;height: 222px" src="${ctx}/static/model/images/app/noresult.png">
        <span style="color: grey;">暂无匹配订单！</span>
    </div>

    <div id="qrcode">
        <div style="height:40%"></div>
        <!-- <p class="tip">...载入中...</p> -->
    </div>
</div>

<script>
    /*
    扫码绑定订单
     */
    function smbd(data){
        $.ajax({
            url:"${ctx}/yszy/kkzysb/smbd/",
            type:"post",
            data:{orderNum:data},
        })
    }


    $(function () {

        $("#plateNum").startListen({
            letter : true,
            number : true,
            check  : false,
            show : function(code){
                layer.closeAll();
                if(code.indexOf("out:")==0){
                    code = code.split(":")[1];
                    openDialogView("查看运单信息","${ctx}/yszy/kkzysb/view2/" + code.substring(code.length-13,code.length),"100%","100%");
                    smbd(code.substring(code.length-13,code.length));
                }else{
                    openDialogView("查看运单信息","${ctx}/yszy/kkzysb/view2/" + code.substring(code.length-13,code.length),"100%","100%");
                    smbd(code.substring(code.length-13,code.length));
                }
            }
        });
        var number = '${number}';
        var outnumber = '${number}';
        if (number) {
            if(number.indexOf("out:")==0){
                loadUnfinish("", number.split(":")[1]);
            }else
                loadUnfinish("", number);
        } else {
            $(".search").click();
        }
        $(".search").click(function () {
            var plateNum = $("#plateNum").val();
            if (plateNum) {
                loadUnfinish(plateNum, "");
            } else {
                layer.msg("请输入车牌号");
            }
        });

        $("#camera").click(function () {
            window.location.href = "${ctx}/QRcode/camera?url=yszy/kkzysb/order?number=";
        });


    });

    function loadUnfinish(plateNum, number) {
        $.post("${ctx}/yszy/kkzysb/unfinishlist", {plateNum: plateNum, number: number}, function (data) {
            if (data.length == 0) {
                $(".orderlist").html("");
                $(".noOrder").show();
                $(".noOrder").css('display', 'flex');
                if (validPlateNum(plateNum)) {
                    $.post("${ctx}/gps/behavior/valid/decScore", {plateNum: plateNum}, function (data) {
                        if (data) {
                            layer.confirm('暂未找到匹配订单，是否扣去积分？', function (index) {
                                layer.load();
                                setTimeout(function (args) {
                                    $.post("${ctx}/basic/car/decscore/" + plateNum, function (data) {
                                        layer.closeAll();
                                        if (data == "success") {
                                            layer.msg("扣除成功！");
                                        } else {
                                            layer.msg("扣除失败！");
                                        }
                                    });
                                }, 100);

                                layer.close(index);
                            });
                        } else {
                            layer.msg("该车辆本日已被扣除积分！");
                            return;
                        }
                    })


                } else {
                    layer.msg("未查询到此车牌号，请检查后重试！");
                }

            } else {
                $(".noOrder").hide();
                $(".noOrder").css('display', 'none');
                var html = "";
                $.each(data, function (index, value) {
                    html +=
                        ' <li class="lis" onclick="view(' + value.id + ')"><div class="divs"> <span class="spans"><i class="fa fa-list-alt is"></i>' + value.plateNum
                        + '</span><span class="score">积分：' + value.score
                        + '</span></div><div class="smalls"><span>单号：' + value.number
                        + '</span> <span>' + new Date(value.createtime).format("yyyy/MM/ss") + '</span> </div></li>';
                });
                $(".orderlist").html(html);

            }
        });
    }

    function view(id) {
        openDialogView("查看运单信息","${ctx}/yszy/kkzysb/view/" + id,"100%","100%");
    }

    function validPlateNum(value) {
        var xreg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF]$)|([DF][A-HJ-NP-Z0-9][0-9]{4}$))/;
        var creg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}$/;
        if (value.length == 7) {
            return creg.test(value);
        } else if (value.length == 8) {
            return xreg.test(value);
        } else {
            return false;
        }
    }


</script>
</body>
</html>
