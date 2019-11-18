<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>气体实时监测详细信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
    <tbody>
    <tr >
        <td class="width-20 active"><label class="pull-right">储罐区编号：</label></td>
        <td class="width-30">${qt.m1 }</td>

        <td class="width-20 active"><label class="pull-right">设备编码：</label></td>
        <td class="width-30">${qt.equipcode }</td>
    </tr>
    <tr >
        <td class="width-20 active"><label class="pull-right">储罐区名称：</label></td>
        <td class="width-30" colspan="3">${qt.m2 }</td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">储罐区面积(㎡)：</label></td>
        <td class="width-30">${qt.m3 }</td>

        <td class="width-20 active"><label class="pull-right">储罐个数：</label></td>
        <td class="width-30">${qt.m4 }</td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">储罐区位置：</label></td>
        <td colspan="3" style="height:30px;line-height:30px;">
            <label>经度：</label>${qt.m6 }
            <label>纬度：</label>${qt.m7 }
        </td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">罐间最小距离(m)：</label></td>
        <td class="width-30" >${qt.m5 }</td>

        <td class="width-20 active"><label class="pull-right">防护堤长度(m)：</label></td>
        <td class="width-30" >${qt.m9 }</td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">防护堤宽度(m)：</label></td>
        <td class="width-30" >${qt.m10 }</td>

        <td class="width-20 active"><label class="pull-right">防护堤高度(m)：</label></td>
        <td class="width-30" >${qt.m11 }</td>
    </tr>
    <tr>
        <td colspan="4" style="font-size: 20px;text-align: center;color: red;"><strong>${qt.label}</strong></td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">气体浓度阈值上限：</label></td>
        <td class="width-30">
            <c:if test="${qt.threshold_up ne null and qt.threshold_up ne ''}">
                ${qt.threshold_up } ${qt.unit }
            </c:if>
        </td>

        <td class="width-20 active"><label class="pull-right">气体浓度阈值上上限：</label></td>
        <td class="width-30">
            <c:if test="${qt.threshold_upplus ne null and qt.threshold_upplus ne ''}">
                ${qt.threshold_upplus } ${qt.unit }
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">气体浓度阈值下限：</label></td>
        <td class="width-30">
            <c:if test="${qt.threshold_down ne null and qt.threshold_down ne ''}">
                ${qt.threshold_down } ${qt.unit }
            </c:if>
        </td>

        <td class="width-20 active"><label class="pull-right">气体浓度阈值下下限：</label></td>
        <td class="width-30">
            <c:if test="${qt.threshold_downplus ne null and qt.threshold_downplus ne ''}">
                ${qt.threshold_downplus } ${qt.unit }
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">报警浓度：</label></td>
        <td class="width-30">
            <c:if test="${qt.bjsj ne null and qt.bjsj ne '' }">
                <span style="color: red;"><strong>${qt.bjxx } ${qt.unit }</strong></span>
            </c:if>
        </td>

        <td class="width-20 active"><label class="pull-right">实时浓度：</label></td>
        <td class="width-30">
            <c:if test="${qt.value ne null and qt.value ne ''}">
                ${qt.value } ${qt.unit }
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="width-20 active"><label class="pull-right">采集时间：</label></td>
        <td class="width-80" colspan="3">
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${qt.cjsj }"/>
        </td>
    </tr>
    </tbody>
</table>

<h2 style="text-align:center">历史波动图</h2>
<div id="main" style="height:90%;width:100%"></div>

<script type="text/javascript">
    var y = [];//y轴
    var legend = [];//legend值
    var x = [];//x轴
    var pointid = '${pointid}';
    var jctype = '${jctype}';
    var name = '';
    var yz1 = '';// 阈值上限
    var yz2 = '';// 阈值上上限
    var yz3 = '';//
    var yz4 = '';// 阈值下下限
    var maxval = '';
    var markLineData = [];// 水平线数据
    var dw = '${qt.unit}';

    $(function () {
        getDataLoadChart();
    });

    function getDataLoadChart() {
        $.ajax({
            url: ctx + "/zxjkyj/lssj/getlinejson/" + pointid + "/" + jctype,
            type: "POST",
            dataType: "json",
            success: function (data) {
                y = [];//y轴
                legend = [];//legend值
                x = [];//x轴
                x = data.date;
                markLineData = [];
                name = (data.name + ' (' + dw + ')');
                y = data.label;
                yz1 = data.thresholdUp;
                yz2 = data.thresholdUpplus;
                yz3 = data.thresholdDown;
                yz4 = data.thresholdDownplus;

                if (y != undefined) {
                    if (yz1 != null && yz1 != '') {
                        markLineData.push([{
                            name: '阈值上限',
                            value: yz1,
                            xAxis: -1,
                            yAxis: yz1,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上限'}}}
                        }, {
                            xAxis: y.length,
                            yAxis: yz1,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上限'}}}
                        }]);
                    }
                    if (yz2 != null && yz2 != '') {
                        markLineData.push([{
                            name: '阈值上上限',
                            value: yz2,
                            xAxis: -1,
                            yAxis: yz2,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上上限'}}}
                        }, {
                            xAxis: y.length,
                            yAxis: yz2,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值上上限'}}}
                        }]);
                    }
                    if (yz3 != null && yz3 != '') {
                        markLineData.push([{
                            name: '阈值下限',
                            value: yz3,
                            xAxis: -1,
                            yAxis: yz3,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下限'}}}
                        }, {
                            xAxis: y.length,
                            yAxis: yz3,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下限'}}}
                        }]);
                    }
                    if (yz4 != null && yz4 != '') {
                        markLineData.push([{
                            name: '阈值下下限',
                            value: yz4,
                            xAxis: -1,
                            yAxis: yz4,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下下限'}}}
                        }, {
                            xAxis: y.length,
                            yAxis: yz4,
                            itemStyle: {normal: {lineStyle: {color: '#f95a5a'}, label: {formatter: '阈值下下限'}}}
                        }]);
                    }
                }
                legend.push(name);
                if (yz2 != null && yz2 != '') {
                    maxval = yz2 + 2;
                } else if (yz1 != null && yz1 != '') {
                    maxval = yz1 + 2;
                }
                loadEcharts();
            }
        });
    }

    // 路径配置
    require.config({
        paths: {
            echarts: '${ctxStatic}/echarts'
        }
    });

    function loadEcharts() {
        require(['echarts', 'echarts/chart/bar', 'echarts/chart/line'], function (ec) {
            var myChart = ec.init(document.getElementById('main'));
            myChart.showLoading();
            myChart.hideLoading();
            myChart.setOption({

                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true,
                    feature: {
                        magicType: {
                            show: true,
                            type: ['line', 'bar']
                        },
                        restore: {
                            show: true
                        },
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: x
                }],
                yAxis: [{
                    type: 'value',
                    name: dw,
                    max: maxval
                }],
                series: [{
                    name: name,
                    type: "line",
                    data: y,
                    markLine: {
                        data: markLineData,
                    },
                    itemStyle: {
                        normal: {lineStyle: {type: 'solid', color: '#2e8ded'}}
                    }
                }]
            });

        });
    }
</script>
</body>
</html>