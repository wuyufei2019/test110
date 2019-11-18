<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>卡口作业申报信息</title>
    <meta name="decorator" content="default"/>
    <%--<script src="${ctxStatic}/common/webUpload.js"></script>--%>
    <style>

        .active label {
            text-align: center;
        }

        .hbj {
            display: none;
        }

        .width-14 label {
            color: #d0ebff;
        }

        .fxdxxtip {
            width: 400px;
            color: #ffffff;
            background: rgba(103, 103, 103, 0.9);
            position: absolute;
            top: 10px;
            border-radius: 20px;
            padding: 10px 10px 5px 0px;
            z-index: 1200000;
            display: none;
            text-align: left;
            font-size: 5px;
        }

    </style>

    <script src="${ctxStatic}/common/webUpload.js"></script>
</head>
<body>
<form id="inputForm" action="${ctx}/yszy/kkzysb/${action}" method="post" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tr>
            <td class="width-15 active"><label class="pull-right">订单类型：</label></td>
            <td class="width-85"><input name="type" id="ordertype" style="width: 100%;height: 30px;"
                                        value="${entity.type }"
                                        class="easyui-combobox" data-options="required:true,editable:false,panelHeight:'auto',data: [
								        {value:'0',text:'安监局审核普通危化品'},
								        {value:'3',text:'环保审核危废'},
								        {value:'4',text:'公安审核易爆易制毒'}]"/></td>
        </tr>
    </table>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>

        <tr>
            <td class="width-10 active" rowspan="2"><label class="pull-right">托运方：</label></td>
            <td class="width-10 active"><label class="pull-right">单位名称：</label></td>
            <td class="width-30">
                <input id="entrustCompany" name="entrustCompany" style="width: 84%;height: 30px;"
                       class="easyui-combogrid" data-options="panelHeight:'308px'" value="${entity.entrustCompany }"/>
                <span class="btn btn-success btn-sm btn-outline" style="width: 15%;height: 30px;"
                      onclick="search()">查询</span>
            </td>
            <td class="width-10 active" rowspan="3"><label class="pull-right">收货方：</label></td>
            <td class="width-10 active"><label class="pull-right">单位名称：</label></td>
            <td class="width-30"><input name="consigneeCompany" id="consigneeCompany" style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.consigneeCompany }"
                                        data-options="required:true"/>
            </td>
        </tr>
        <tr>
            <td class="width-10 active"><label class="pull-right">联系电话：</label></td>
            <td class="width-30"><input name="entrustContact" id="entrustContact"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.entrustContact }"
                                        data-options="required:true,validType:'mobileAndTel'"/></td>

            <td class="width-10 active"><label class="pull-right">联系电话：</label></td>
            <td class="width-30"><input name="consigneeContact" id="consigneeContact" style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.consigneeContact }"
                                        data-options="required:true,validType:'mobileAndTel'"/>
            </td>
        </tr>


        <tr <c:if test="${entity.type ne 3}">class="hbj"  </c:if>>
            <td class="width-10" colspan="4">
            <td class="width-10 active"><label class="pull-right">危险废物经<br>营许可证：</label></td>
            <td class="width-30">
                <div id="attachment_uploader" style="margin-left:10px"></div>
            </td>
            </td>
        </tr>
    </table>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tr>
            <td class="width-15 active"><label class="pull-right">装货地点：</label></td>
            <td class="width-35"><input name="loadingAddress" id="loadingAddress"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.loadingAddress }"
                                        data-options="required:true"/>
            </td>
            <td class="width-15 active"><label class="pull-right">运输目的地：</label></td>
            <td class="width-35"><input name="transportDestination" id="transportDestination"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.transportDestination }"
                                        data-options="required:true"/>
            </td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">装货日期：</label></td>
            <td class="width-35"><input name="loadingTime" id="loadingTime"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-datebox"
                                        value="${entity.loadingTime }" data-options="required:true"/>
            </td>
            <td class="width-15 active"><label class="pull-right">预计到达日期：</label></td>
            <td class="width-35"><input name="predictArriveTime" id="predictArriveTime"
                                        style="width:  100%;height:  30px;"
                                        class="easyui-datebox"
                                        value="${entity.predictArriveTime }" data-options="required:true"/>
            </td>
        </tr>

    </table>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">

        <tr>
            <td class="width-10 active" rowspan="7"><label class="pull-right">承运方：</label></td>
            <td class="width-15 active"><label class="pull-right">单位名称：</label></td>
            <td class="width-15" colspan="5"><input name="acceptCompany" id="acceptCompany"
                                                    style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"
                                                    value="${entity.acceptCompany }"
                                                    data-options="required:true"/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">经营许可证：</label></td>
            <td class="width-15" colspan="2"><input name="businessPermitNum" id="businessPermitNum"
                                                    style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"
                                                    value="${entity.businessPermitNum }"
                                                    data-options="required:true"/></td>
            <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
            <td class="width-15" colspan="2"><input name="acceptContact" id="acceptContact"
                                                    style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"
                                                    value="${entity.acceptContact }"
                                                    data-options="validType:'mobileAndTel'"/></td>
        </tr>
        <tr>
            <td class="width-15 active" rowspan="2"><label class="pull-right">车辆信息：</label></td>
            <td class="width-15 active"><label class="pull-right">车牌号码：</label></td>
            <td class="width-15"><input name="plateNum" id="plateNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-combobox"
                                        value="${entity.plateNum }"
                                        data-options="textField:'m3',valueField:'m3',url:'${ctx}/beian/roadtransport/listjson',required:true,
                                        validType:['plateCode','FUN[ValidateName,\'请选择车牌\']']"/>
            </td>
            <td class="width-15 active" rowspan="2"><label class="pull-right">挂车信息：</label></td>
            <td class="width-15 active"><label class="pull-right">车牌号码：</label></td>
            <td class="width-15"><input name="plateNumG" id="plateNumG"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-combobox"
                                        value="${entity.plateNumG }"
                                        data-options="textField:'m3',valueField:'m3',url:'${ctx}/beian/roadtransport/listjson',
                                        validType:['plateCode','FUN[ValidateName,\'请选择车牌\']']"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">道路运输证号：</label></td>
            <td class="width-15"><input name="wayTransportNum" id="wayTransportNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.wayTransportNum }"
                                        data-options="required:true"/></td>
            <td class="width-15 active"><label class="pull-right">道路运输证号：</label></td>
            <td class="width-15"><input name="wayTransportNumG" id="wayTransportNumG"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.wayTransportNumG }"
                                        data-options=""/></td>
        </tr>

        <tr>
            <td class="width-15 active" rowspan="3"><label class="pull-right">驾驶员：</label></td>
            <td class="width-15 active"><label class="pull-right">姓名：</label></td>
            <td class="width-15"><input name="driverName" id="driverName"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverName }"
                                        data-options="required:true"/></td>
            <td class="width-15 active" rowspan="3"><label class="pull-right">押运员：</label></td>
            <td class="width-15 active"><label class="pull-right">姓名：</label></td>
            <td class="width-15"><input name="supercargoName" id="supercargoName"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoName }"
                                        data-options="required:true"/></td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">从业资格证号：</label></td>
            <td class="width-15"><input name="driverDutyRequireNum" id="driverDutyRequireNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverDutyRequireNum }"
                                        data-options="required:true"/></td>
            <td class="width-15 active"><label class="pull-right">从业资格证号：</label></td>
            <td class="width-15"><input name="supercargoDutyRequireNum" id="supercargoDutyRequireNum"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoDutyRequireNum }"
                                        data-options="required:true"/></td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-15"><input name="driverContact" id="driverContact"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.driverContact }"
                                        data-options="required:true,validType:'mobile'"/></td>
            <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
            <td class="width-15"><input name="supercargoContact" id="supercargoContact"
                                        style="width: 100%;height: 30px;"
                                        class="easyui-textbox"
                                        value="${entity.supercargoContact }"
                                        data-options="required:true,validType:'mobile'"/></td>
        </tr>
    </table>
    <table id="rwtable" class="table table-bordered  table-condensed dataTables-example dataTable no-footer">


        <tfoot id="tfooter">
        <tr>
            <td align="center" colspan="7">
                <a class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left"
                   onclick="addGoods()" title="确定">添加危化品</a>
        </tr>
        </tfoot>
        <c:if test="${action eq 'create'}">
            <tr>
                <td class="width-15 active hwmc"><label class="pull-right">货物名称：</label></td>
                <td class="width-35 hwmc"><input name="whpName" id="whpName"
                                            style="width: 100%;height: 30px;"
                                            class="easyui-combobox"
                                            data-options="required:true, valueField: 'whpName', textField: 'whpName',
                                                        url: '${ctx}/sekb/msds/listjson',required:true"/></td>
                <td class="width-15 active unit"><label class="pull-right">联合国编码：</label></td>
                <td class="width-35 unit"><input name="uniteNationNum" id="uniteNationNum"
                                                 style="width: 100%;height: 30px;"
                                                 class="easyui-textbox"/></td>

            </tr>
            <tr class="hbj">
                <td class="width-15 active hbj"><label class="pull-right">转移联单编号：</label></td>
                <td class="width-35 hbj"><input name="transferNumber" id="transferNumber"
                                                style="width: 100%;height: 30px;"
                                                class="easyui-textbox"/></td>
                <td class="width-15 active"><label class="pull-right">危废名称：</label></td>
                <td class="width-35"><input name="hazardouswastename" id="hazardouswastename"
                                            style="width: 100%;height: 30px;" 
                                            class="easyui-textbox"/></td>
            </tr>
            <tr class="hbj">
                <td class="width-15 active"><label class="pull-right">危废类别：</label></td>
                <td class="width-35"><input name="hazardouswastetype" id="hazardouswastetype"
                                            style="width: 100%;height: 30px;" 
                                            class="easyui-textbox"/></td>
                <td class="width-15 active"><label class="pull-right">危废代码：</label></td>
                <td class="width-35"><input name="hazardouswastenum" id="hazardouswastenum"
                                            style="width: 100%;height: 30px;" 
                                            class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">类/项别：</label></td>
                <td class="width-35"><input name="type_0" id="type"
                                            style="width: 100%;height: 30px;"
                                            class="easyui-combotree"
                                            data-options=""/></td>
                <td class="width-15 active"><label class="pull-right">包装类别：</label></td>
                <td class="width-35"><input name="packageType" id="packageType"
                                            style="width: 100%;height: 30px;"
                                            class="easyui-combobox"
                                            data-options=" editable: true,  valueField: 'value',textField: 'value',
                                         data: [{value: 'I类包装'}, {value: 'Ⅱ类包装'}, {value: 'Ⅲ类包装'}, {value: '其他'}],
                                        panelHeight: 'auto'"/>
                </td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">规格：</label></td>
                <td class="width-35"><input name="specification" id="specification"
                                            style="width: 100%;height: 30px;"
                                            class="easyui-textbox"/></td>
                <td class="width-15 active"><label class="pull-right">质量(kg)：</label></td>
                <td class="width-35"><input name="tonnage" id="tonnage"
                                            style="width: 100%;height: 30px;"
                                            class="easyui-textbox" required="true"
                                            data-options="validType:'number'"/></td>
                <input type="hidden" name="count" value="0"/>
            </tr>



            <input type="hidden" name="count" value="0"/>

        </c:if>


        <c:if test="${action eq 'update'}">
            <c:forEach items="${goodsList}" var="goods" varStatus="status">
                <tr>
                    <td class="width-15 active hwmc"><label class="pull-right">货物名称：</label></td>
                    <td class="width-35 hwmc"><input name="whpName" id="whpName${status.index}"
                                                style="width: 100%;height: 30px;"
                                                class="easyui-combobox" value="${goods.whpName }"
                                                data-options="required:true, valueField: 'whpName', textField: 'whpName',
                                                        url: '${ctx}/sekb/msds/listjson',required:true"/></td>

                    <c:if test="${entity.type eq 3}">
                        <td class="width-15 active"><label class="pull-right">转移联单编号：</label></td>
                        <td class="width-35"><input name="transferNumber" value="${goods.transferNumber }"
                                                    style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"/></td>
                    </c:if>
                    <c:if test="${entity.type ne 3}">
                        <td class="width-15 active"><label class="pull-right">联合国编码：</label></td>
                        <td class="width-35"><input name="uniteNationNum" value="${goods.uniteNationNum }"
                                                    style="width: 100%;height: 30px;"
                                                    class="easyui-textbox"/></td>
                    </c:if>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">类/项别：</label></td>
                    <td class="width-35"><input name="type_${status.index }" id="type"
                                                style="width: 100%;height: 30px;" value="${goods.type }"
                                                class="easyui-combotree"
                                                data-options="valueField:'text',textField:'text', editable: true,multiple: true,onlyLeafCheck: true,
                                           url: '${ctx}/static/model/js/yszy/kkzysb/type.json',
                                           formatter: function (node) {
                                           		var nodes = node.text.split('/');
								                if (nodes.length > 1) {
								                    return nodes[nodes.length - 1];
								                } else {
								                    return nodes;
								                }
								            }"/></td>
                    <td class="width-15 active"><label class="pull-right">包装类别：</label></td>
                    <td class="width-35"><input name="packageType"
                                                style="width: 100%;height: 30px;" value="${goods.packageType }"
                                                class="easyui-combobox"
                                                data-options=" editable: true,  valueField: 'value',textField: 'value',
                                         data: [{value: 'I类包装'}, {value: 'Ⅱ类包装'}, {value: 'Ⅲ类包装'}, {value: '其他'}],
                                        panelHeight: 'auto'"/>
                    </td>
                </tr>
                <tr>
                    <td class="width-15 active"><label class="pull-right">规格：</label></td>
                    <td class="width-35"><input name="specification"
                                                style="width: 100%;height: 30px;" value="${goods.specification }"
                                                class="easyui-textbox"/></td>
                    <td class="width-15 active"><label class="pull-right">质量(kg)：</label></td>
                    <td class="width-35"><input name="tonnage" value="${goods.tonnage }"
                                                style="width: 100%;height: 30px;"
                                                class="easyui-textbox" readonly="true"
                                                        data-options="valid:'number',required:true"/>
                    </td>
                    <input type="hidden" name="count" value="${status.index }"/>
                </tr>
                <c:if test="${entity.type eq 3}">
                    <tr>
                        <td class="width-15 active"><label class="pull-right">危废名称：</label></td>
                        <td class="width-35"><input name="hazardouswastename" value="${goods.hazardouswastename }"
                                                    style="width: 100%;height: 30px;" 
                                                    class="easyui-textbox"/></td>
                        <td class="width-15 active"><label class="pull-right">危废类型：</label></td>
                        <td class="width-35"><input name="hazardouswastetype" value="${goods.hazardouswastetype }"
                                                    style="width: 100%;height: 30px;" 
                                                    class="easyui-textbox"/></td>
                    </tr>
                    <tr>
                        <td class="width-15 active"><label class="pull-right">危废代码：</label></td>
                        <td class="width-35"><input name="hazardouswastenum" value="${goods.hazardouswastenum }"
                                                    style="width: 100%;height: 30px;" 
                                                    class="easyui-textbox"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>


        <c:if test="${not empty entity.ID}">
            <input type="hidden" name="ID" value="${entity.ID}"/>
            <input type="hidden" name="number" value="${entity.number}"/>
            <input type="hidden" name="userid" value="${entity.userid}"/>
            <input type="hidden" name="state" value="${entity.state}"/>
            <input type="hidden" name="S1"
                   value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss" />"/>
        </c:if>
        </tbody>
    </table>
    <div class="fxdxxtip"></div>
</form>
<script type="text/javascript">
    var action = '${action}';
    if ('${entity.type}' == '3') {
        $("#attachment_uploader").WebImageUpload({
            hiddenInputId: 'attachment',
            existurl: '${entity.attachment}'
        });
    }
    var count = 0;
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    function ValidateName(val) {
        var result = true;
        $.ajax({
            method: 'POST',
            url: "${ctx}/beian/roadtransport/valid/unique/review/" + val,
            async: false,
            success: function (data) {
                result = !data;
            }
        });
        return result;
    }

    var hdzWeight;//核定载质量
    $(function () {
        $("#ordertype").combobox({
            onSelect: function (res) {
                if (res.value == '3' && action == 'create') {
                    $("#attachment_uploader").WebImageUpload({
                        hiddenInputId: 'attachment',
                        existurl: '${entity.attachment}'
                    });
                    $(".hbj").show();
                    $(".unit").hide();
                    $(".hwmc").hide();
                    $.parser.parse('.hbj');
                    // $(".type").attr("data-options","required:true");
                    // $(".packageType").attr("data-options","required:true");
                    // $(".specification").attr("data-options","required:true");
                    /* $('#type').combotree({required:true});
                    $('#packageType').combobox({required:true});
                    $('#specification').textbox({required:true}); */
                } else {
                    $(".hbj").hide();
                    $(".unit").show();
                }
            }
        });
        //根据输入的风险名称查找相应的数据并自动填充
        $('#entrustCompany').combogrid({
            panelWidth: 900,
            idField: 'entrust_company', //ID字段
            textField: 'entrust_company', //显示的字段
            fitColumns: true,
            method: "post",
            url: '${ctx}/yszy/kkzysb/list',
            collapsible: false,//是否可折叠的
            nowrap: false,
            pageSize: 10,//每页显示的记录条数，默认为10
            pageList: [10],//可以设置每页记录条数的列表
            method: 'post',
            columns: [[
                {field: 'id', title: 'id', checkbox: true, width: 50, align: 'center'},
                {
                    field: 'entrust_company',
                    title: '托运方单位名称',
                    sortable: false,
                    align: 'center',
                    align: 'center',
                    width:
                        100
                },
                {
                    field: 'consignee_company', title: '收货方单位名称', sortable: false, align: 'center', align: 'center',
                    width:
                        100
                },
                {field: 'plate_num', title: '车牌号码', sortable: false, align: 'center', align: 'center', width: 100},
                {field: 'driver_name', title: '驾驶员姓名', sortable: false, width: 100, align: 'center'},
                {field: 'driver_contact', title: '联系方式', sortable: false, align: 'center', width: 100}
            ]],
            onSelect: function (rowIndex, rowData) {
                $('#consigneeCompany').textbox("setValue", rowData.consignee_company);
                $('#entrustContact').textbox("setValue", rowData.entrust_contact);
                $('#consigneeContact').textbox("setValue", rowData.consignee_contact);
                $('#loadingAddress').textbox("setValue", rowData.loading_address);
                $('#transportDestination').textbox("setValue", rowData.transport_destination);
                $('#acceptCompany').textbox("setValue", rowData.accept_company);
                $('#businessPermitNum').textbox("setValue", rowData.business_permit_num);
                $('#acceptContact').textbox("setValue", rowData.accept_contact);
                /*    $('#plateNum').combobox("setValue", rowData.plate_num);
                    $('#plateNumG').combobox("setValue", rowData.plate_numg);
                    $('#wayTransportNum').textbox("setValue", rowData.way_transport_num);
                    $('#wayTransportNumG').textbox("setValue", rowData.way_transport_numg);*/
                $('#driverName').combobox("setValue", rowData.driver_name);
                $('#supercargoName').combobox("setValue", rowData.supercargo_name);
                $('#driverDutyRequireNum').textbox("setValue", rowData.driver_duty_require_num);
                $('#supercargoDutyRequireNum').textbox("setValue", rowData.supercargo_duty_require_num);
                $('#driverContact').textbox("setValue", rowData.driver_contact);
                $('#supercargoContact').textbox("setValue", rowData.supercargo_contact);
            },
            onLoadSuccess: function (rows) {
                $(".datagrid-row").find("td[field='entrust_company']").mouseover(function () {
                    var index = $(".datagrid-row").attr("datagrid-row-index");
                    mouseovertip(event, index);
                });
                $(".datagrid-row").mouseout(function () {
                    $(".fxdxxtip").hide();
                });
            }
        });

        $("#plateNum").combobox({
            onSelect: function (res) {
                $("#wayTransportNum").textbox("setValue", res.m4);
                if (res.m18)
                    hdzWeight = res.m18;
            }
        });
        $("#plateNumG").combobox({
            onSelect: function (res) {
                $("#wayTransportNumG").textbox("setValue", res.m4);
                if (res.m18)
                    hdzWeight = res.m18;
            }
        });
        /* $("#driverName").combobox({
            valueField: 'driverName',
            textField: 'driverName',
            url: '${ctx}/beian/drivingcertificate/listjson',
            onSelect: function (res) {
                $("#driverDutyRequireNum").textbox("setValue", res.driverDutyRequireNum);
                $("#driverContact").textbox("setValue", res.phone);
            }
        });
        $("#supercargoName").combobox({
            valueField: 'driverName',
            textField: 'driverName',
            url: '${ctx}/beian/drivingcertificate/listjson',
            onSelect: function (res) {
                $("#supercargoDutyRequireNum").textbox("setValue", res.driverDutyRequireNum);
                $("#supercargoContact").textbox("setValue", res.phone);
            }
        }); */
        $("#whpName").combobox({
            valueField: 'whpName',
            textField: 'whpName',
            url: '${ctx}/sekb/msds/listjson',
            onSelect: function (rec) {
                $("#uniteNationNum").textbox("setValue", rec.unNum);
                $("#packageType").textbox("setValue", rec.pt);
                $("#type").combotree("setValue", rec.type);
            }
        });

        $('#type').combotree({
            valueField: 'text',
            textField: 'text',
            cascadeCheck: false,
            editable: true,
            multiple: true,
            onlyLeafCheck: true,
            validType: ['length[0,250]'],
            url: '${ctx}/static/model/js/yszy/kkzysb/type.json',
            formatter: function (node) {
                var nodes = node.text.split("/");
                if (nodes.length > 1) {
                    return nodes[nodes.length - 1];
                } else {
                    return nodes;
                }
            }
        });

        $(".btn-danger").click(function () {
            $(this).parent().parent().remove();
        });

        if ('${action}' == 'create') {
            $("#consigneeCompany").textbox("setValue", '${qyname}');
        }
    });

    function mouseovertip(event, index) {
        var data = $('#entrustCompany').combogrid("grid").datagrid("getData").rows[index];
        var tiphtml = "<table style='width: 100%;'><tbody>";
        tiphtml += "<tr><td rowspan='4' class='width-8' style='width:20px;word-wrap: break-word;padding:0 5px 0 20px;'>基础信息</td><td class='width-20 active'><label class='pull-right' valign='top'>托运方单位名称：</label></td>";
        tiphtml += "<td class='width-75'>" + data.entrust_company + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active'><label class='pull-right' valign='top'>收货方单位名称：</label></td>";
        tiphtml += "<td class='width-75'>" + data.consignee_company + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active'><label class='pull-right' valign='top'>装货地点：</label></td>";
        tiphtml += "<td class='width-75'>" + data.loading_address + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active'><label class='pull-right' valign='top'>运输目的地：</label></td>";
        tiphtml += "<td class='width-75'>" + data.transport_destination + "</td></tr>";
        tiphtml += "<tr style='border-top: dashed 2px rgb(200,200,200);margin-top: 5px'><td rowspan='5' class='width-8' style='width:20px;word-wrap: break-word;padding:0 5px 0 20px;'>承运方</td><td class='width-30 active' valign='top'><label class='pull-right'>承运方单位名称：</label></td>";
        tiphtml += "<td class='width-75'>" + data.accept_company + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active' valign='top'><label class='pull-right'>车牌号码：</label></td>";
        tiphtml += "<td class='width-75'>" + data.plate_num + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active' valign='top'><label class='pull-right'>挂车车牌：</label></td>";
        tiphtml += "<td class='width-75'>" + data.plate_numg + "</td></tr>";
        tiphtml += "<tr><td class='width-30 active' valign='top'><label class='pull-right'>驾驶员：</label></td>";
        tiphtml += "<td class='width-75'>" + data.driver_name + "</td></tr>";
        tiphtml += "<tr style='border-bottom: dashed 2px rgb(200,200,200);margin-top: 5px'><td class='width-30 active' valign='top'><label class='pull-right'>押运员：</label></td>";
        tiphtml += "<td class='width-75'>" + data.supercargo_name + "</td></tr>";
        /* tiphtml +=
             "<tr><td rowspan='2' class='width-8' style='width:20px;word-wrap: break-word;padding:0 5px 0 20px;'>货物信息</td><td class='width-20 active' valign='top'><label class='pull-right'>联合国编号：</label></td>";
         tiphtml += "<td class='width-75'>" + 8 + "</td></tr>";
         tiphtml += "<tr><td class='width-20 active' valign='top'><label class='pull-right'>货物名称：</label></td>";
         tiphtml += "<td class='width-75'>" + 7 + "</td></tr>";*/

        tiphtml += "</tbody></table>";
        $(".fxdxxtip").html(tiphtml);

        x = event.clientX;
        var left = 0;
        if (x > 620) {
            left = x - 620;
        } else {
            left = x + 20;
        }

        $(".fxdxxtip").show();
        $(".fxdxxtip").css("left", left);
    }

    function addGoods() {
        count++;
        var $list = $("#rwtable tr").eq(-1);
        var html = '<tr>';
        if ($("#ordertype").combobox("getValue") != '3') {
           html += '<td class="width-15 active"><i class="fa fa-minus-square btn-danger btn-outline"></i><label class="pull-right">货物名称：</label></td>' +
            '<td class="width-35"><input name="whpName"id="whpName' + count +
            '"style="width: 100%;height: 30px;"class="easyui-combobox"data-options="required:true, valueField: \'whpName\', textField: \'whpName\',url: \'${ctx}/sekb/msds/listjson\',required:true"/></td>';

        }
        if ($("#ordertype").combobox("getValue") == '3') {
            html += '<td class="width-15 active"><i class="fa fa-minus-square btn-danger btn-outline"></i><label class="pull-right">转移联单编号：</label></td>' +
                '<td class="width-35"><input name="transferNumber"id="transferNumber' + count +
                '"style="width: 100%;height: 30px;"class="easyui-textbox"/></td>';
        } else {
            html += '<td class="width-15 active"><label class="pull-right">联合国编码：</label></td>' +
                '<td class="width-35"><input name="uniteNationNum"id="uniteNationNum' + count +
                '"style="width: 100%;height: 30px;"class="easyui-textbox"/></td>';
        }
        html += '</tr><tr>' +
            '<td class="width-15 active"><label class="pull-right">类/项别：</label></td>' +
            '<td class="width-35"><input name="type_' + count + '" id="type' + count + '"style="width: 100%;height: 30px;"class="easyui-combotree"data-options=" editable: true,multiple: true,onlyLeafCheck: true,url: \'${ctx}/static/model/js/yszy/kkzysb/type.json\',formatter: function (node) {var nodes = node.text.split(\'/\');if (nodes.length > 1) {return nodes[nodes.length - 1];}else{return nodes;}}"/></td>' +
            '<td class="width-15 active"><label class="pull-right">包装类别：</label></td>' +
            '<td class="width-35"><input name="packageType"id="packageType' + count + '"style="width: 100%;height: 30px;"class="easyui-combobox"data-options=" editable: true,  valueField: \'value\',textField: \'value\',data: [{value: \'I类包装\'}, {value: \'Ⅱ类包装\'}, {value: \'Ⅲ类包装\'}, {value: \'其他\'}],panelHeight: \'auto\'"/></td>' +
            '</tr><tr>' +
            '<td class="width-15 active"><label class="pull-right">规格：</label></td>' +
            '<td class="width-35"><input name="specification"id="specification"style="width: 100%;height: 30px;"class="easyui-textbox"/></td>' +
            '<td class="width-15 active"><label class="pull-right">质量(kg)：</label></td>' +
            '<td class="width-35"><input name="tonnage"id="tonnage"style="width: 100%;height: 30px;"class="easyui-textbox"data-options="required:true,valid:\'number\'"/></td>' +
            '<input type="hidden" name="count" value="' + count + '"/>' +
            '</tr>';
        if ($("#ordertype").combobox("getValue") == '3') {

            html += '<tr>' +
                '<td class="width-15 active"><label class="pull-right">危废名称：</label></td>' +
                '<td class="width-35"><input name="hazardouswastename" data-options="required:true" id="hazardouswastename' + count +
                '"style="width: 100%;height: 30px;"class="easyui-textbox"/></td>' +
                '<td class="width-15 active"><label class="pull-right">危废类别：</label></td>' +
                '<td class="width-35"><input name="hazardouswastetype" data-options="required:true" id="hazardouswastetype' + count +
                '" style="width: 100%;height: 30px;"class="easyui-textbox"/></td>' +
                '</tr>' +
                '<tr>' +
                '<td class="width-15 active"><label class="pull-right">危废代码：</label></td>' +
                '<td class="width-35"><input name="hazardouswastenum" data-options="required:true" id="hazardouswastenum' + count +
                '" style="width: 100%;height: 30px;"class="easyui-textbox"/></td>' +
                '</tr>';
        }

        $list.after(html);
        $.parser.parse($("#rwtable tr:last"));
        $.parser.parse($("#rwtable tr:last").prev());
        $.parser.parse($("#rwtable tr:last").prev().prev());
        $.parser.parse($("#rwtable tr:last").prev().prev().prev());
        $.parser.parse($("#rwtable tr:last").prev().prev().prev().prev());
        $("#whpName" + count).combobox({
            onSelect: function (rec) {
                $("#uniteNationNum" + count).textbox("setValue", rec.unNum);
                $("#packageType" + count).textbox("setValue", rec.pt);
                $("#type" + count).combotree("setValue", rec.type);
            }
        });

        $(".btn-danger").click(function () {
            $(this).parent().parent().nextAll().remove();
            $(this).parent().parent().remove();
        });

    }

    function search() {
        var name = $('#entrustCompany').combogrid("getText");
        $('#entrustCompany').combogrid("showPanel");
        $('#entrustCompany').combogrid("grid").datagrid("reload", {'entrustCompany': name});
    }


    function openCar() {
        openDialog("添加车辆信息", ctx + "/basic/car/create/", "800px", "400px", {
            yes: function (index, layero) {

            }
        });
    }

    function doSubmit() {
        $("#inputForm").submit();
    }

    $(function () {
        var flag = true;
        $('#inputForm').form({
            onSubmit: function () {
                if(action =='create'){
                    if (!hdzWeight) {
                        layer.msg("牵引车或挂车的核定载质量未填，请完善相关行驶证信息再来填报！");
                        return false;
                    } else {
                        var tmpWeight = 0;
                        $.each($("input[name='tonnage']"), function (index, item) {
                            tmpWeight += parseFloat($(item).val());
                        });
                        if (tmpWeight >= hdzWeight) {
                            layer.msg("您所输入的危化品质量超过牵引车或挂车的核定载质量,请检查货物数据！");
                            return false;
                        }
                    }
                }

                if ($("#ordertype").combobox("getValue") == '3') {
                    if ($("input[name='attachment']").length == 0) {
                        layer.msg("请上传危险废物经营许可证扫描件！");
                        return false;
                    }
                }

                var isValid = $(this).form('validate');
                if (isValid && flag) {
                    flag = false;
                    $.jBox.tip("正在提交，请稍等...", 'loading', {
                        opacity: 0
                    });
                    return true;
                }
                return false; // 返回false终止表单提交
            },
            success: function (data) {
                $.jBox.closeTip();
                if (data == 'success')
                    parent.layer.open({
                        icon: 1,
                        title: '提示',
                        offset: 'rb',
                        content: '操作成功！',
                        shade: 0,
                        time: 2000
                    });
                else
                    parent.layer.open({
                        icon: 2,
                        title: '提示',
                        offset: 'rb',
                        content: '操作失败！',
                        shade: 0,
                        time: 2000
                    });
                parent.dg.datagrid('reload');
                parent.layer.close(index);//关闭对话框。
                if ('${action}' == 'create') {
                    $("#inputForm").form("clear");
                } else {
                    parent.layer.close(index);//关闭对话框。
                }
            }
        });

    });

</script>

</body>
</html>