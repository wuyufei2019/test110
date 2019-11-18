<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>储罐监测维护数据信息</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form id="inputForm" class="form-horizontal">
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-25 active"><label class="pull-right">设备编码：</label></td>
            <td class="width-25" colspan="3">${cglist.equip_code }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">传感器编号：</label></td>
            <td class="width-25" colspan="3">${cglist.cgqbh }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">指标名称：</label></td>
            <td class="width-25">${cglist.target_name }</td>

            <td class="width-25 active"><label class="pull-right">指标类型：</label></td>
            <td class="width-25">${cglist.label }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">计量单位：</label></td>
            <td class="width-25">${cglist.unit }</td>

            <td class="width-25 active"><label class="pull-right">位号：</label></td>
            <td class="width-25">${cglist.bit_no }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">阈值上限：</label></td>
            <td class="width-25">${cglist.threshold_up }</td>

            <td class="width-25 active"><label class="pull-right">阈值上上限：</label></td>
            <td class="width-25">${cglist.threshold_upplus }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">阈值下限：</label></td>
            <td class="width-25">${cglist.threshold_down }</td>

            <td class="width-25 active"><label class="pull-right">阈值下下限：</label></td>
            <td class="width-25">${cglist.threshold_downplus }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">量程上限：</label></td>
            <td class="width-25">${cglist.range_up }</td>

            <td class="width-15 active"><label class="pull-right">量程下限：</label></td>
            <td class="width-25" colspan="3">${cglist.range_down }</td>
        </tr>

        <tr>
            <td class="width-25 active"><label class="pull-right">描述：</label></td>
            <td class="width-25" colspan="3">${cglist.target_describe }</td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>