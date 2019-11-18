<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
		     WinMove();
		});	
	</script>
	 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script> 
	 <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/mapindex.js"></script>
	<style type="text/css">
	.BMap_cpyCtrl  
    {  
        display:none;   
    }  
    .anchorBL{  
        display:none;   
    }   
    </style>
</head>
<body class="gray-bg">
<script type="text/javascript">
var datas;

    $.ajax({
	   url:ctx+ '/msg/html',
	   type:"POST",
	    success:function(data){
		//$("#xdt").append(data);
		var html="<ul>";
		for(var i=0;i<data.length;i++){
	    html+="<li><span><a href=\"#\" >"+data[i].info+":"+data[i].content+"</a></span>";
		}
	    html+="</ul>";
	   	$("#xdt").append(html);
	       }
        });
    
    $.ajax({
 	   url:ctx+ '/aqjd/jcjh/html',
 	   type:"POST",
 	    success:function(data){
 	    	var html="<ul>";
 	    	var row;
 	    	for(var i=0;i<data.rows.length;i++){
 	    		row=data.rows[i];
 	    		if(data.usertype==1){
 	    		html+="<li style=\"padding:15px\"><span><a href=\"#\" >本月计划:"+row.m1+"</a></span>";
 	    		}else{
 	    		html+="<li style=\"padding:15px\"><span><a href=\"#\" onclick=\"upd("+row.ID+")\">本月计划:"+data.rows[i].m1+"</a></span>";
 	    		}
 	    	}
 	    	html+="</ul>";
 	   	$("#checkplan").append(html);
 	       }
         });
    
    function upd(id) {
    	openDialogView("修改文件信息", ctx + "/aqjd/jcjh/update/" + id, "900px",
    			"500px", "");
    }
        // 路径配置
        require.config({
            paths: {
                echarts:'${ctxStatic}/echarts'
            }
        });
        
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/bar'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['易爆炸物','易燃气体','易燃固体','氧化性液体','毒性物质']
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	        	magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    xAxis : [
                	        {
                	            type : 'category',
                	            boundaryGap : false,
                	            data : ['12:10','12:20','12:30','12:40','12:50','13:00','13:10']
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value'
                	        }
                	    ],
                	    series : [
                	        {
                	            name:'易爆炸物',
                	            type:'line',
                	           
                	            data:[120, 132, 101, 134, 90, 230, 210]
                	        },
                	        {
                	            name:'易燃气体',
                	            type:'line',
                	           
                	            data:[220, 182, 191, 234, 290, 330, 310]
                	        },
                	        {
                	            name:'易燃固体',
                	            type:'line',
                	           
                	            data:[150, 232, 201, 154, 190, 330, 410]
                	        },
                	        {
                	            name:'氧化性液体',
                	            type:'line',
                	            
                	            data:[320, 332, 301, 334, 390, 330, 320]
                	        },
                	        {
                	            name:'毒性物质',
                	            type:'line',
                	           
                	            data:[820, 932, 901, 934, 1290, 1330, 1320]
                	        }
                	    ]
                	};
                	                    
                 
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

</script>
   <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12" style="height: 300px;width: 100%">
        	<!-- 企业百度地图位置显示 -->
            <div id="dituContent" style="height: 100%;width: 100%"></div>
        </div>
    </div>
      
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-8">

                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>固有风险趋势图</h5> 
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div id="main" style="height:500px"></div>
                    </div>
                </div>
              
            </div>
            
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>天气信息 </h5>
                    </div>
                <div class="ibox-content">
						<iframe allowtransparency="true" frameborder="0" width="180" height="264" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=1&v=1&d=3&bd=0&k=&f=&q=1&e=0&a=1&c=70436&w=180&h=278&align=center"></iframe>                        
                        <hr>
                    </div> 
	              <!--  <div id="Msg" class="easyui-panel" title="消息提醒"  border="false" style="height:175px;width:190px">
	                </div> -->
	                <!-- <div id="Msg" class="easyui-panel" title="本月检查任务计划"  border="false" style="height:175px;width:190px">
	                </div> -->
	             <div  class="easyui-panel" title="消息提醒"  border="false" style="width:400px;height:190px">
	                
	                <div class="easyui-tabs" id="MsgTabs"  headerWidth="108px"  fit="true" tabPosition="left" >
		           		 <div title="本月检查计划" id="checkplan" style="border:none" >
			        		
		             	 </div>
		          		 <div  title="新动态" id="xdt"  border="false" style="padding-top:10px;border:none">
		 				</div>
	  				</div>
                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>