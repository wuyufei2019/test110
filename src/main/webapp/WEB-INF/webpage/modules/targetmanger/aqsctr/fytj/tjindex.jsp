<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计汇总</title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/model/css/aqsc/tjfxstyle.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body class="bggrey1">
	<div style="padding:5px;height:auto;width: 100%;">
		<button onclick="getCount('type');" class="easyui-linkbutton"
			style="width: 100px;">类别统计</button>
		<button onclick="getCount('depart');" class="easyui-linkbutton"
			style="width: 100px;">部门统计</button>
		<br />
	</div>
   <div class="box  padtop30"><!--  style="overflow: auto;" -->
      <div class="fytj">
         <div class="col-lg-6"><!--  style="min-width: 1000px;" -->
            <div class="box2 mrgl10">
               <div>
               </div>
               <div id="tipbox1">
                  <div class="ibox-content" id="ibox-content1" style="margin: 10px">
                  <div class="echart" id="main5" style="width:100%;height:442px"></div>
               </div>
               </div>
               <div id="tipbox2">
               <div class="ibox-content" id="ibox-content2" style="margin: 10px">
                  <div class="echart" id="main6" style="width:100%;height:442px"></div>
               </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <script>
   $(function(){
   		$("#tipbox2").hide();
		getCount('type');
   });
   
   function getCount(flag){
   		if(flag=='type'){
   			$("#tipbox2").hide();
   			$("#tipbox1").show();
   			$.ajax({
				type:'get',
				url: '${ctx}/aqsctr/fytj/lxcount',
				success : function(data) {
				    loadEcharts('main5',JSON.parse(data));
				}
			});
		}else{
		   $("#tipbox1").hide();
		   $("#tipbox2").show();
   			$.ajax({
				type:'get',
				url: '${ctx}/aqsctr/fytj/departcount',
				success : function(data) {
				    loadEcharts2('main6',JSON.parse(data));
				}
			});
		}
   }
   
require.config({
	paths : {
		echarts : '${ctxStatic}/echarts'
	}
});

function loadEcharts(div,lxdata) {
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
		var myChart = ec.init(document.getElementById(div));
		myChart.setOption({
		    title: {
                text: '支出项目类别统计图'
            },
			tooltip : {
				trigger : 'axis'
			},
			legend: {
            	data:['预算金额','支出金额'],
            	textStyle: {fontSize : 13,
            				fontFamily : 'Arial',
            				fontWeight : 100}
            },
			calculable : true,
			xAxis : [{
					name : '支出类别',
					type : 'category',
					data : lxdata.lx ,
	   				axisLabel:{  
	   					  textStyle : {
										fontSize : 11,
										fontFamily : 'Arial',
										fontWeight : 100,
						  },
	                      interval:0,
	                      rotate:-20
	         		}
				}],
			grid: {x2: 150,y2: 120 },
			yAxis : [ 
				{
					type : 'value',
					name :'金额(万元)',
				    axisLabel:{  
  					  textStyle : {
								fontSize : 13,
								fontFamily : 'Arial',
								fontWeight : 100,
				    },
				  }
				} 
			],
			series : [ {
				name : '预算金额',
				type : 'bar',
				barCategoryGap : '60%',
				data : lxdata.ys,
				itemStyle: {  
                    normal: {  
                        label: {  
                            show: true,//是否展示  
                            textStyle: {  
                                fontSize : '12',  
                                fontFamily : '微软雅黑', 
                                color:'black'
                            }  
                        }  
                    }  
                },
			}, {
				name : '支出金额',
				type : 'bar',
				barCategoryGap : '60%',
				data : lxdata.zc,
				itemStyle: {  
                    normal: {  
                        label: {  
                            show: true,//是否展示  
                            textStyle: {  
                                fontSize : '12',  
                                fontFamily : '微软雅黑', 
                                color:'black'
                            }  
                        }  
                    }  
                },
			}
			]
		});
	});
}

function loadEcharts2(div,bmdata) {
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
		var myChart = ec.init(document.getElementById(div));
		myChart.setOption({
			title: {
                text: '部门支出统计图'
            },
			tooltip : {
				trigger : 'axis'
			},
			calculable : true,
			xAxis : [

			{
				type : 'category',
				data : bmdata.depart,
				name:'部门',
				axisLabel:{  
                      interval:0,//横轴信息全部显示  
	                  formatter:function(value)  
	                  {  
	                      var ret = "";//拼接加\n返回的类目项  
	                      var maxLength = 5;//每项显示文字个数  
	                      var valLength = value.length;//X轴类目项的文字个数  
	                      var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数  
	                      if (rowN > 1)//如果类目项的文字大于3,  
	                      {  
	                          for (var i = 0; i < rowN; i++) {  
	                              var temp = "";//每次截取的字符串  
	                              var start = i * maxLength;//开始截取的位置  
	                              var end = start + maxLength;//结束截取的位置  
	                              //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧  
	                              temp = value.substring(start, end) + "\n";  
	                              ret += temp; //凭借最终的字符串  
	                          }  
	                          return ret;  
	                      }  
	                      else {  
	                          return value;  
	                      }  
	                  }   
                     		
                		} 
			} ],
			yAxis : [ {
				type : 'value',
				name:'支出金额(万元)' 
			} ],
			series : [ {
				name : '支出金额',
				type : 'bar',
				barCategoryGap : '60%',
				data : bmdata.zc,
				//顶部数字展示pzr  
                itemStyle: {  
                    normal: {  
                        label: {  
                            show: true,//是否展示  
                            textStyle: {  
                                fontSize : '12',  
                                fontFamily : '微软雅黑', 
                                color:'black'
                            }  
                        }  
                    }  
                }, 
				}
			]
		});
	});
}   
</script>
</body>
</html>