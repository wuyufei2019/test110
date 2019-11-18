<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>应急处置方案信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
    <tbody>
    <tr>
        <td class="width-15 active"><label class="pull-right">等级：</label></td>
        <td class="width-35">${res.m1 }</td>
        <td class="width-15 active"><label class="pull-right">岗位名称：</label></td>
        <td class="width-35"><input name="M2" readonly class="easyui-combobox" value="${res.m2 }" style="width: 100%;height: 30px;"
                                    data-options="editable:false,panelHeight:'auto',data:[{value:'总指挥(主要负责人)_1',text:'总指挥(主要负责人)'},
                       {value:'副总指挥(应急负责人)_1',text:'副总指挥(应急负责人)'},
                       {value:'应急抢险组_2',text:'应急抢险组'},
                       {value:'污染防控组_2',text:'污染防控组'},
                       {value:'后勤保障组_2',text:'后勤保障组'},
                       {value:'警戒保卫组_2',text:'警戒保卫组'},
                       {value:'医疗救护组_2',text:'医疗救护组'},
                       {value:'甲醛生产线_3',text:'甲醛生产线'},
                       {value:'原料成品罐区(火灾)_3',text:'原料成品罐区(火灾)'},
                       {value:'原料成品罐区(泄漏)_3',text:'原料成品罐区(泄漏)'},
                       {value:'装车台(火灾)_3',text:'装车台(火灾)'},
                       {value:'装车台(泄漏)_3',text:'装车台(泄漏)'}]"/></td>
    </tr>
    <c:if test="${not empty res.m3 }">
        <tr>
            <td class="width-15 active"><label class="pull-right">生产安全事故</br>处置程序及职责：</label></td>
            <td class="width-85" colspan="3" style="width: 100%;height: 80px;">${res.m3 }</td>
        </tr>
    </c:if>
    <c:if test="${not empty res.m19 }">
        <tr>
            <td class="width-15 active"><label class="pull-right">风险提示：</label></td>
            <td class="width-85" colspan="3" style="width: 100%;height: 80px;">${res.m19 }</td>
        </tr>
    </c:if>
    <c:if test="${not empty res.m20 }">
        <tr>
            <td class="width-15 active"><label class="pull-right">应急处置方法：</label></td>
            <td class="width-85" colspan="3" style="width: 100%;height: 80px;">${res.m20 }</td>
        </tr>
    </c:if>


    <tr>
        <td class="width-15 active"><label class="pull-right">注意事项：</label></td>
        <td class="width-85" colspan="3" style="width: 100%;height: 80px;">${res.m4 }</td>
    </tr>
    <tr>
        <td class="width-100 active " colspan="4" style="text-align:center"><label class="center">内部应急联系方式</label> </td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m5 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m5_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m6 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m6_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m7 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m7_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m8 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m8_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m9 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m9_num }</td>
    </tr>
        <td class="width-100 active " colspan="4" style="text-align:center"><label class="center">外部应急联系方式</label> </td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m11 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m11_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m12 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m12_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m13 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m13_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m14 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m14_num }</td>
    </tr>
    <tr>
        <td class="width-15 active"><label class="pull-right">联系人岗位：</label></td>
        <td class="width-35">${res.m15 }</td>
        <td class="width-15 active"><label class="pull-right">联系方式：</label></td>
        <td class="width-35">${res.m15_num }</td>
    </tr>


    </tbody>
</table>
</body>
</html>