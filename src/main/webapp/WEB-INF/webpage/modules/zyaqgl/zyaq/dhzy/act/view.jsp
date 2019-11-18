<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>动火作业管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>

<form id="inputForm" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">申请人：</label></td>
            <td class="width-35">${entity.applyer }</td>
            <td class="width-15 active"><label class="pull-right">作业证编号：</label></td>
            <td class="width-35">${entity.m1 }</td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">申请单位：</label></td>
            <td class="width-35" colspan="3">${entity.m2 }</td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">动火作业级别：</label></td>
            <td class="width-35">${entity.m3 }</td>
            <td class="width-15 active"><label class="pull-right">动火方式：</label></td>
            <td class="width-35">${entity.m4 }</td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">动火地点：</label></td>
            <td class="width-35" colspan="3">${entity.m5 }</td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">动火时间起：</label></td>
            <td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.m6 }"/></td>
            <td class="width-15 active"><label class="pull-right">动火时间止：</label></td>
            <td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd" value="${entity.m7 }"/></td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">动火作业负责人：</label></td>
            <td class="width-35">${entity.m8 }</td>
            <td class="width-15 active"><label class="pull-right">动火人：</label></td>
            <td class="width-35">${entity.m9 }</td>
        </tr>

        <tr>
            <td class="width-15 active"><label class="pull-right">承包商姓名：</label></td>
            <td class="width-35" colspan="3">${entity.m29 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="进入受限空间"/>进入受限空间</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="高处作业"/>高处作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="吊装作业"/>吊装作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="临时用电"/>临时用电</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="动土/断路作业"/>动土/断路作业</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="锁定"/>锁定</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M10" value="盲板抽堵"/>盲板抽堵</div>
            </td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
            <td colspan="3">
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="易燃易爆物质"/>易燃易爆物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="坠落"/>坠落</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="腐蚀性物质"/>腐蚀性物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="蒸汽"/>蒸汽</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高压气体/液体"/>高压气体/液体</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="有毒有害物质"/>有毒有害物质</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="高温/低温"/>高温/低温</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="触电"/>触电</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="窒息"/>窒息</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="噪音"/>噪音</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="产生火花/静电"/>产生火花/静电</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="旋转设备"/>旋转设备</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="机械伤害"/>机械伤害</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="粉尘"/>粉尘</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="不利天气"/>不利天气</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="淹没/埋没"/>淹没/埋没</div>
                <br/>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="辐射"/>辐射</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="交叉作业"/>交叉作业</div>
                <div style="width: 25%;float: left;"><input type="checkbox" name="M11" value="其他"/>其他</div>
            </td>
        </tr>
        </tbody>
    </table>
    <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>分析</strong></p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: -2px;"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">分析人：</label></td>
            <td class="width-30"></td>
        </tr>
        </tbody>
    </table>
    <table id="fxsj"></table>
    <!-- 安全措施 -->
    <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>安全措施</strong></p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" style="width: 50%;">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">编制人：</label></td>
            <td class="width-30">${entity.m15 }</td>
        </tr>
    </table>
    <table id="aqcs"></table>

    <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>审批确认</strong></p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-20 active"><label class="pull-right">安全措施编制人：</label></td>
            <td class="width-30">${entity.m15 }</td>
            <td class="width-20 active"><label class="pull-right">安全措施确认人：</label></td>
            <td class="width-30">${entity.m16 }</td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">生产部门负责人：</label></td>
            <td class="width-30">${entity.m17 }</td>
            <td class="width-20 active"><label class="pull-right">监护人：</label></td>
            <td class="width-30">${entity.m17 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">作业区域负责人：</label></td>
            <td class="width-30">${entity.m19 }</td>
            <td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
            <td class="width-30">${entity.m21 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位意见：</label></td>
            <td class="width-30" colspan="3">${entity.m22_1 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">申请单位负责人：</label></td>
            <td class="width-30">${entity.m22 }</td>
            <td class="width-20 active"><label class="pull-right">确认时间：</label></td>
            <td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.m22_2 }"/></td>

        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">安全管理部门意见：</label></td>
            <td class="width-30" colspan="3">${entity.m23_1 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">安全管理部门负责人：</label></td>
            <td class="width-30">${entity.m22 }</td>
            <td class="width-20 active"><label class="pull-right">确认时间：</label></td>
            <td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.m22_2 }"/></td>

        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火审批人意见：</label></td>
            <td class="width-30" colspan="3">${entity.m24_1 }</td>
        </tr>

        <tr>
            <td class="width-20 active"><label class="pull-right">动火审批人：</label></td>
            <td class="width-30">${entity.m24 }</td>
            <td class="width-20 active"><label class="pull-right">审批时间：</label></td>
            <td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.m24_2 }"/></td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">审批照片：</label></td>
            <td class="width-80" colspan="3">
                <c:if test="${not empty entity.m24_3}">
                    <c:forTokens items="${entity.m24_3}" delims="||" var="url" varStatus="urls">
                        <c:set var="urlna" value="${fn:split(url, '||')}"/>
                        <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                            <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"
                                                                       style="max-height: 100px;"/><br/></a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">验收人：</label></td>
            <td class="width-30">${entity.m25 }</td>
            <td class="width-20 active"><label class="pull-right">验收时间：</label></td>
            <td class="width-30"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.m25_1 }"/></td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">验收照片：</label></td>
            <td class="width-80" colspan="3">
                <c:if test="${not empty entity.m25_2}">
                    <c:forTokens items="${entity.m25_2}" delims="||" var="url" varStatus="urls">
                        <c:set var="urlna" value="${fn:split(url, '||')}"/>
                        <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                            <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"
                                                                       style="max-height: 100px;"/><br/></a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="width-20 active"><label class="pull-right">手写签名：</label></td>
            <td class="width-80" colspan="3">
                <c:if test="${not empty entity.m25_3}">
                    <c:forTokens items="${entity.m25_3}" delims="||" var="url" varStatus="urls">
                        <c:set var="urlna" value="${fn:split(url, '||')}"/>
                        <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                            <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"
                                                                       style="max-height: 100px;"/><br/></a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>

    <p style="margin-top: 10px;color: red;font-size: 15px;"><strong>附件</strong></p>
    <hr style="height:3px;border:none;border-top:3px double #6B8E23;margin-top: -5px;margin-bottom: 5px;"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">附件：</label></td>
            <td class="width-35" colspan="3">
                <c:if test="${not empty entity.m12}">
                    <c:forTokens items="${entity.m12}" delims="," var="url" varStatus="urls">
                        <c:set var="urlna" value="${fn:split(url, '||')}"/>
                        <div style="margin: 5px;">
                            <a style="color:#337ab7;text-decoration:none;" target="_blank"
                               href="${urlna[0]}">${urlna[1]}</a>
                        </div>
                    </c:forTokens>
                </c:if>
            </td>
        </tr>
        <tbody>
    </table>
</form>
<script type="text/javascript">
    //特殊作业
    var tszy = '${entity.m10}';
    var tszyArr = tszy.split(",");
    for (var i = 0; i < tszyArr.length; i++) {
        $("input[name='M10']:checkbox[value='" + tszyArr[i] + "']").attr('checked', 'true');
    }

    //危害辨识
    var whbs = '${entity.m11}';
    var whbsArr = whbs.split(",");
    for (var i = 0; i < whbsArr.length; i++) {
        $("input[name='M11']:checkbox[value='" + whbsArr[i] + "']").attr('checked', 'true');
    }


    $(function () {
        $("input[name='M10']:checkbox").css("width", "18px");
        $("input[name='M10']:checkbox").css("height", "18px");
        $("input[name='M11']:checkbox").css("width", "18px");
        $("input[name='M11']:checkbox").css("height", "18px");
    });

</script>
</body>
</html>