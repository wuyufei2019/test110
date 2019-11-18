<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>历史波动图</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>

<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">

    <div class="form-group" style="margin-left: 5px;">
        <input id="type" name="type" type="text" class="easyui-combobox" style="height: 30px;"
               data-options="editable:false ,panelHeight:'auto',valueField: 'value',textField: 'text', prompt: '时间段', data: [
                                            {value:'1',text:'近一周'},
                                            {value:'2',text:'近一个月'},
                                            {value:'3',text:'近三个月'} ],
                                            onSelect: function(row){
                                                getDataLoadChart();
                                            }
                                    "/>
        <input type="text" class="easyui-datebox" id="datestart" name="datestart" style="height: 30px;"
               data-options="prompt: '开始时间'">
        <input type="text" class="easyui-datebox" id="dateend" name="dateend" style="height: 30px;"
               data-options="prompt: '结束时间'">
        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</span>
        <span class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 全部</span>
    </div>
</form>

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
    var yz3 = '';// 阈值下限
    var yz4 = '';// 阈值下下限
    var maxval = '';
    var markLineData = [];// 水平线数据
    var dw = '${unit}';

    $(function () {
        getDataLoadChart();
    });

    function getDataLoadChart() {
        var type = $('#type').combobox('getValue');
        var datestart = $('#datestart').datebox('getValue');
        var dateend = $('#dateend').datebox('getValue');
        if (datestart != null && datestart != '' && dateend != null && dateend != '') {
            $('#type').combobox('setValue', '')
            type = null;
        }
        $.ajax({
            url: ctx + "/zxjkyj/lssj/getlinejson/" + pointid + "/" + jctype,
            type: "POST",
            data: {'type': type, 'datestart': datestart, 'dateend': dateend},
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

    // 创建查询对象并查询
    function search() {
        getDataLoadChart();
    }

    // 创建查询对象并查询
    function reset() {
        $("#searchFrom").form("clear");
        getDataLoadChart();
    }
</script>
</body>
</html>