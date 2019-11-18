<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>卡口作业申报信息</title>
    <meta name="decorator" content="default"/>
    <style>

        .width-4 {
            width: 4%;
        }

        .width-12 {
            width: 12%;
        }

        .width-24 {
            width: 24%;
        }

        .width-5 {
            width: 5%;
        }

        .width-10 {
            width: 10%;
        }

        .width-20 {
            width: 20%;
        }

        .active label {
            text-align: center;
        }

        input {
            background-color: unset
        }

        .textbox {
            background-color: unset;
            border-width: 0px;
            text-align: right
        }

        .textbox .textbox-text {
            text-align: right;
            padding-right: 30px;
            color: black;
            font-size: 14px
        }

        .right36 .textbox .textbox-text {
            text-align: left;
            padding-left: 200px;
            color: black
        }

        .textbox-addon.textbox-addon-right {
            display: none
        }

        .left36 .textbox .textbox-text {
            text-align: left;
            padding-left: 190px;
            color: black
        }

        textarea {
            color: black
        }

        .chow2 .textbox {
            text-align: left
        }

        .asda::-webkit-scrollbar {
            background-color: #F5F5F500;
        }

        .asda::-webkit-scrollbar-track {
            background-color: #F5F5F500;
        }
    </style>
</head>
<body>
<div style="width: 100%;height: 100%;display: flex;align-items: center;justify-content: center;background: rgb(227, 227, 227)">
    <!-- 运单类型 -->
    <c:if test="${entity.type == '0'}">
    <div style="width: 1000px;height: 628px;background-image: url('${ctx}/static/model/images/yszy/sheet.png')">
        </c:if>
        <c:if test="${entity.type == '4'}">
        <div style="width: 1000px;height: 628px;background-image: url('${ctx}/static/model/images/yszy/sheet2.png')">
            </c:if>
            <!-- 运单类型 为 环保局 -->
            <c:if test="${entity.type == '3'}">
            <div style="width: 1000px;height: 628px;background-image: url('${ctx}/static/model/images/yszy/sheet3.png')">
                </c:if>
                <div style="display: flex;width: 100%;flex-direction: column;">
                    <div style="display: flex;width: 100%;margin-top: 130px;">
                        <div class="left36"
                             style="display: flex;width: 50%;flex-direction: column;align-items: flex-start">
                            <div style="display: flex;width: 100%;flex-direction: column">
                                <!--托运方 单位名称-->
                                <input name="entrustCompany" id="entrustCompany"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.entrustCompany }"
                                       data-options="required:true"/>
                                <!--托运方 单位名称-->
                                <input name="entrustContact" id="entrustContact"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.entrustContact }"
                                       data-options="required:true,validType:'mobileAndTel'"/>
                            </div>
                            <div style="display: flex;width: 100%;flex-direction: column;    margin-top: 48px;">
                                <!--收货方 电话-->
                                <input name="consigneeCompany" id="consigneeCompany" style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.consigneeCompany }"
                                       data-options="required:true"/>
                                <!--收货方 电话-->
                                <input name="consigneeContact" id="consigneeContact" style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.consigneeContact }"
                                       data-options="required:true,validType:'mobileAndTel'"/>

                                <c:if test="${entity.type eq 3}">
                                    <!--收货方 目的地
                                    <input name="transportDestination" id="transportDestination1"
                                    style="width: 100%;height: 30px;margin-top: 5px;display: none"
                                    class="easyui-textbox"
                                    value="${entity.transportDestination }"
                                    data-options="required:true"/>-->
                                    <button style="width: 70px; margin: 8px 0 0 190px;" onclick="showattachment()">查看
                                     </button>
                                </c:if>
                                <c:if test="${entity.type ne 3}">
                                    <button style="width: 70px; margin: 8px 0 0 190px;opacity: 0">查看</button>
                                </c:if>
                            </div>
                            <div class="zhdd"
                                 style="display: flex;width: 100%;flex-direction: column;    margin-top: 48px;">
                                <!--装货地点-->
                                <textarea name="loadingAddress" id="loadingAddress"
                                          style="width:100px;flex-shrink: 0;border-width: 0px; background: rgb(233, 247, 237); margin-top:0px;margin-left: 150px;height: 25px;"
                                          readonly>${entity.loadingAddress}</textarea>
                                <!--<textarea name="loadingAddress" id="loadingAddress"
                                   style="width: 100%;height: 30px;"
                                   class="easyui-textbox"
                                   value="${entity.loadingAddress }"
                                   data-options="required:true"/>-->
                                <!--收货方 目的地-->
                                <textarea name="transportDestination" id="transportDestination"
                                          style="width:100px;flex-shrink: 0;border-width: 0px; background: rgb(233, 247, 237);margin: -26px 0 12px 380px;height: 25px;"
                                          readonly>${entity.transportDestination}</textarea>
                                <!-- <input name="transportDestination" id="transportDestination"
                                   style="width: 100%;height: 30px;"
                                   class="easyui-textbox"
                                   value="${entity.transportDestination }"
                                   data-options="required:true"/>-->
                                <!--装货日期-->
                                <input name="loadingTime" id="loadingTime"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-datebox"
                                       value="${entity.loadingTime }" data-options="required:true"/>
                                <!--预计送达日期-->
                                <input name="predictArriveTime" id="predictArriveTime"
                                       style="width:  100%;height:  30px;"
                                       class="easyui-datebox"
                                       value="${entity.predictArriveTime }" data-options="required:true"/>
                            </div>
                        </div>
                        <div class="right36" style="display: flex;width: 50%;flex-direction: column">
                            <input style="color: white;font-weight: 600;margin-left: 290px;margin-top: -95px;position: absolute; border-width: 0;"
                                   value="${entity.number }"/>
                            <!--承运方信息-->
                            <div class="cyf36" style="display: flex;width: 100%;flex-direction: column">
                                <input name="acceptCompany" id="acceptCompany"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.acceptCompany }"
                                       data-options="required:true"/>
                                <input name="businessPermitNum" id="businessPermitNum"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.businessPermitNum }"
                                       data-options="required:true"/>
                                <input name="acceptContact" id="acceptContact"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.acceptContact }"
                                       data-options="validType:'mobileAndTel'"/>
                            </div>
                            <!-- 车辆信息-->
                            <div style="display: flex;width: 100%;flex-direction: column">
                                <input name="plateNum" id="plateNum"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.plateNum }"
                                       data-options="required:true"/>
                                <input name="wayTransportNum" id="wayTransportNum"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.wayTransportNum }"
                                       data-options="required:true"/>
                            </div>
                            <!-- 挂车信息-->
                            <div style="display: flex;width: 100%;flex-direction: column; margin-top: 4px;">
                                <input name="plateNumG" id="plateNumG"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.plateNumG }"
                                       data-options=""/>
                                <input name="wayTransportNumG" id="wayTransportNumG"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.wayTransportNumG }"
                                       data-options=""/>
                            </div>
                            <!-- 驾驶员信息-->
                            <div style="display: flex;width: 100%;flex-direction: column">
                                <input name="driverName" id="driverName"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.driverName }"
                                       data-options="required:true"/>
                                <input name="driverDutyRequireNum" id="driverDutyRequireNum"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.driverDutyRequireNum }"
                                       data-options="required:true"/>
                                <input name="driverContact" id="driverContact"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.driverContact }"
                                       data-options="required:true,validType:'mobile'"/>
                            </div>
                            <!-- 押运员信息-->
                            <div style="display: flex;width: 100%;flex-direction: column; margin-top: 4px;">
                                <input name="supercargoName" id="supercargoName"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.supercargoName }"
                                       data-options="required:true"/>
                                <input name="supercargoDutyRequireNum" id="supercargoDutyRequireNum"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.supercargoDutyRequireNum }"
                                       data-options="required:true"/>
                                <input name="supercargoContact" id="supercargoContact"
                                       style="width: 100%;height: 30px;"
                                       class="easyui-textbox"
                                       value="${entity.supercargoContact }"
                                       data-options="required:true,validType:'mobile'"/>
                            </div>
                        </div>
                    </div>
                    <div class="asda"
                         style="display: flex;width: 86%;margin-top: 60px;margin-left: 7%;flex-direction: column;overflow: scroll;height: 100px">
                        <c:forEach items="${goodsList}" var="goods" varStatus="status">
                            <!-- 安监 、公安-->
                            <c:if test="${entity.type ne 3}">
                                <div class="chow"
                                     style="width: 100%;display: flex;border-top: 0.5px solid rgb(130, 185, 146);flex-shrink: 0;">
                                    <textarea
                                            style="width:120px;margin-top: 5px;mix-height:40px;flex-shrink: 0;margin-left: 0px;border-width: 0px;background: rgb(233, 247, 237);"
                                            readonly>${goods.whpName}</textarea>
                                    <!--联合国码-->
                                    <input name="uniteNationNum" value="${goods.uniteNationNum}"
                                           style="width: 90%;height: 30px;"
                                           class="easyui-textbox" readonly/>
                                    <!--类 项-->
                                    <textarea
                                            style="width: 260px;flex-shrink: 0;margin-left: 30px;border-width: 0px; background: rgb(233, 247, 237); margin-top: 5px;"
                                            readonly>${goods.type}</textarea>
                                    <!--包装类别-->
                                    <input name="packageType" value="${goods.packageType}"
                                           style="width: 100%;height: 30px;"
                                           class="easyui-textbox" readonly/>
                                    <!--规格-->
                                    <input name="specification" value="${goods.specification}"
                                           style="width: 100%;height: 30px;padding-left:30px"
                                           class="easyui-textbox" readonly/>
                                    <!--质量-->
                                    <input name="tonnage" value="${goods.tonnage}"
                                           style="width: 100%;height: 30px;padding-left:20px"
                                           class="easyui-numberbox" readonly/>
                                </div>
                            </c:if>

                            <!--环保局-->
                            <c:if test="${entity.type eq 3}">
                                <div class="chow2"
                                     style="width: 100%;display: flex;border-top: 0.5px solid rgb(130, 185, 146);flex-shrink: 0;align-items: center;color: black">
                                    <span style="margin-top: -10px;width: 160px">${goods.transferNumber}</span>

                                    <textarea
                                            style="width:135px;flex-shrink: 0;margin-left: 5px;border-width: 0px; background: rgb(233, 247, 237); margin-top: 5px;"
                                            readonly>${goods.hazardouswastename}</textarea>
                                    <span style="margin-top: -10px;width: 130px">${goods.hazardouswastetype}</span>
                                    <span style="margin-top: -10px;width: 130px">${goods.hazardouswastenum}</span>
                                    <!--类 项-->
                                    <textarea
                                            style="width: 200px;flex-shrink: 0;margin-left: 10px;border-width: 0px; background: rgb(233, 247, 237); margin-top: 5px;"
                                            readonly>${goods.type}</textarea>
                                    <span style="margin-top: -10px;width: 120px;padding-left: 10px;">${goods.packageType}</span>
                                    <span style="margin-top: -10px;width: 120px">${goods.specification}</span>
                                    <span style="margin-top: -10px;width: 120px;    padding-left: 10px;">${goods.tonnage}</span>


                                        <%--
                                        <span name="transferNumber" value="${goods.transferNumber}"
                                              style="width:  1500px;height: 30px;"
                                              class="easyui-textbox" readonly/>
                                        <input name="hazardouswastetype" value=""
                                               style="width: 900px;height: 30px;"
                                               class="easyui-textbox" readonly/>
                                        <input name="hazardouswastenum" value="${goods.hazardouswastenum}"
                                               style="width: 90%;height: 30px;"
                                               class="easyui-textbox" readonly/>
                                        <!--包装类别-->
                                        <input name="packageType" value="${goods.packageType}"
                                               style="width: 100%;height: 30px;"
                                               class="easyui-textbox" readonly/>
                                        <!--规格-->
                                        <input name="specification" value="${goods.specification}"
                                               style="width: 100%;height: 30px;padding-left:30px"
                                               class="easyui-textbox" readonly/>
                                        <!--质量-->
                                        <input name="tonnage" value="${goods.tonnage}"
                                               style="width: 100%;height: 30px;padding-left:20px"
                                               class="easyui-numberbox" readonly/>--%>
                                </div>
                            </c:if>

                        </c:forEach>
                    </div>

                </div>
            </div>
            <c:if test="${entity.state == 2}">
                <div style="position:absolute;width: 498px;height: 367px;background-image: url('${ctx}/static/model/images/yszy/sheetdone.png');"></div>
            </c:if>
        </div>

        <script>
            if ("${error}" && "${error}" == "error") {
                layer.msg("二维码有误，请检查！ ");
            }
            window.onload = function () {
                $(".asda").children("div").children("span").children("input").css('padding-right', '0px');
                $(".asda").children("div").children("span").children("input").css('text-align', 'left');
                $(".asda").children("div").children("span").css('margin-left', '10px');

                $(".right36").children("div").children("span").css('height', '24px');
                $(".right36").children("div").children("span").children("input").css('height', '24px');
                $(".zhdd").children("span").eq(1).children("input").eq(0).css('padding-left', '340px');
                $(".zhdd").children("span").eq(1).children("input").eq(0).css('padding-left', '400px');
                $(".zhdd").children("span").eq(1).children("input").eq(0).css('padding-right', '0px');
                $(".zhdd").children("textarea").eq(1).css('margin-top', '-30px');
                $(".zhdd").children("span").eq(1).css('margin-top', '-30px');
                $(".zhdd").children("span").eq(1).children("input").eq(0).css('text-align', 'right');

                $(".chow2").children("input").css('flex-shrink', '0');
                $("input").attr('readonly', true);
            }

            function showattachment() {

                var  showattachmentname = encodeURI('${entity.attachment}');
                window.open('${ctx}/yszy/kkzysb/showattachment?showattachment='+showattachmentname);
            }

        </script>
</body>
</html>