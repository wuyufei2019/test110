<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全管理网络</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/echarts/echarts.js"></script>

</head>
<body>
<div id="echartsbox"style="width:100%;height:100%">
<div id="main_tree"></div>
</div>
<script type="text/javascript">
$(function(){ 
	initEchartsSize();
	loadEcharts();
	function initEchartsSize(){
		var height= $("#echartsbox").height();
		var width=$("#echartsbox").width();
		$("#main_tree").height(height*0.94);
		$("#main_tree").width(width*0.98);
	}
	$(window).resize(function(){
		initEchartsSize();
		loadEcharts();
	});
});
	
	
function loadEcharts(){
    // 路径配置
    require.config({
        paths: {
            echarts:'${ctx}/static/echarts/'
        }
    });
    // 使用
    require(
        [
        	'echarts',
            'echarts/chart/tree' // 使用树图 
        ],
        function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main_tree')); 
                
                // 添加点击事件  
		        var ecConfig = require('echarts/config');  
		        myChart.on(ecConfig.EVENT.CLICK, eConsole);   
                option = {
                 	   /*  toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    }, */
            	    series : [
            	        {
            	            name:'树图',
            	            type:'tree',
            	            orient: 'vertical',  // vertical horizontal
            	            rootLocation: {x: 'center',y: 100}, // 根节点位置  {x: 100, y: 'center'}
            	            nodePadding: 30,
            	            layerPadding: 100,
            	            hoverable: true,
            	            roam: true,
            	            symbolSize: 40,
            	            symbol: 'rectangle',
            	         	symbolSize: [80, 40],
            	            itemStyle: {
            	                normal: {
            	                    color: '#4883b4',
            	                    label: {
        	                       		show: true,
        	                      		position: 'inside',
        	                      		formatter : function(params){
        	                                console.log('fdgdfg')
        	                    			 return 'ssss<br/>ssf';
        	                			 },
        	                    },
        	                    lineStyle: {
        	                        color: '#48b',
        	                        type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
        	                    }
            	                },
            	                emphasis: {
            	                    color: '#4883b4',
            	                    label: {
            	                        show: true
            	                    },
            	                    borderWidth: 0
            	                }
            	            },
            	            data: ${datas}
            	        }
            	    ]
            	};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
                
            }
        );}
        

function eConsole(param) {    
    if (typeof param.seriesIndex == 'undefined') {    
        return;    
    }    
    if (param.type == 'click') {  
    	layer.msg(param.value,{time: 3000});
    	 
    }    
}  
</script>
</body>
</html>