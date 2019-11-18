<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训需求调查统计</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript">
var dg;
var ctx = '${ctx}';

//路径配置
require.config({
	paths : {
		echarts : '${ctxStatic}/echarts'
	}
});

$(function() {//初始化
	require([ 'echarts', 'echarts/chart/bar'], function(ec) {
		var myChart = ec.init(document.getElementById('main'));
		myChart.setOption(option);
	});
});

var option = {
	    title: {
	        x: 'center',
	        text: '培训课程投票调查统计'
	    },
	    tooltip: {
	        trigger: 'item'
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataView: {show: true, readOnly: false},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    calculable: true,
	    grid: {
	        borderWidth: 0,
	        y: 80,
	        y2: 60
	    },
	    xAxis: [
	        {
	            type: 'category',
	            //show: false,
	            data: ${x}
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            //show: false
	        }
	    ],
	    series: [
	        {
	            name: '培训课程投票调查统计',
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: function(params) {
	                        // build a color map as your need.
	                        var colorList = [
	                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
	                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
	                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
	                        ];
	                        return colorList[params.dataIndex]
	                    },
	                    label: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{b}\n{c}'
	                    }
	                }
	            },
	            data: ${y}
	        }
	    ]
	};
	
	var ztid = '${ztid}';
	function tpxx() {
		//openDialogView("培训需求调查统计",ctx+"/aqpx/xqdc/dctjindex","100%", "100%","");
		
		window.location.href = ctx+"/aqpx/xqdc/tpindex/"+ztid;
	}
</script>
</head>
<body>
	<div id="main" style="width: 100%;height:600px;"></div>
	<a href="javascript:void(0);" class="btn btn-info" onclick="tpxx()" style="width:80px;float: right;margin-right: 100px;" >投票信息</a>
</body>
</html>
