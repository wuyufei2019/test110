﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!doctype html>
<meta charset="utf-8">
<title>警情警力分析</title>
<link href="css/style.css"  rel="stylesheet" type="text/css" media="all" />
<script src="js/echarts.min.js" charset="utf-8"></script>
<script src="js/jquery-3.2.0.min.js" charset="utf-8"></script>
<script src="beijing.js"></script>

<body>
<div class="wpbox">
<div class="bnt">
  <div class="topbnt_left fl">
   <ul><li ><a href="analysis.html">警情警力</a></li>
      <li><a href="people.html">实有人口</a></li>
      <li><a href="activity.html">活动情况</a></li>
 
   </ul>
  </div>
  <h1 class="tith1 fl">舆情分析</h1>
  <div class=" fr topbnt_right">
    <ul>
       <li><a href="#">返回</a></li>
       <li class="active"><a href="traffic.html">交通</a></li>
       <li><a href="index.html">舆情</a></li>
    </ul>
   
  </div>
</div>
<!-- bnt end -->
<div class="left1">
    <div class="aleftboxttop pt1"><h2 class="tith2">进州车辆情况</h2>
    <div class="lefttoday_tit" style=" height:8%"><p class="fl">地区：甘孜</p><p class="fm">周期：每日</p><p class="fr">2018-06-14</p></div>
      <div class="tlbox">
        <ul>
           <li>
             <p class="text"><span class="w12">本地车辆:</span><span><i class="ricon1"></i>昨日:6</span><span><i class="tricon1"></i>今日:5</span><span class="tr"><img src="img/icondown.png" height="16" /> 1</span></p>
             <p class="rwith"><span class="rwith_img" style="width:60%"></span></p>
           </li>
         <li>
           <p class="text"><span class="w12">外地车辆:</span><span><i class="ricon2"></i>昨日:600</span><span><i class="tricon2"></i>今日:500</span><span class="tr"><img src="img/iconup.png" height="16" /> 1</span></p>
           <p class="rwith bgc2"><span class="rwith_img qgc2" style="width:40%"></span></p>
         </li>
       <li>
         <p class="text"><span class="w12">乘客人员数量:</span><span><i class="ricon3"></i>昨日:6</span><span><i class="tricon3"></i>今日:5</span><span class="tr"><img src="img/iconup.png" height="16" /> 1</span></p>
         <p class="rwith bgc3"><span class="rwith_img qgc3" style="width:50%"></span></p>
       </li>
        </ul>
      </div>

    <!-- lefttoday_number end -->
    </div>
    <div class="aleftboxtmidd">
      <h2 class="tith2 pt2">出州车辆情况</h2>
      <div class="lefttoday_tit" style=" height:8%"><p class="fl">地区：甘孜</p><p class="fm">周期：每日</p><p class="fr">2018-06-14</p></div>
        <div class="tlbox">
          <ul>
             <li>
               <p class="text"><span class="w12">本地车辆:</span><span><i class="ricon1"></i>昨日:6</span><span><i class="tricon1"></i>今日:5</span><span class="tr"><img src="img/icondown.png" height="16" /> 1</span></p>
               <p class="rwith"><span class="rwith_img" style="width:60%"></span></p>
             </li>
           <li>
             <p class="text"><span class="w12">外地车辆:</span><span><i class="ricon2"></i>昨日:600</span><span><i class="tricon2"></i>今日:500</span><span class="tr"><img src="img/iconup.png" height="16" /> 1</span></p>
             <p class="rwith bgc2"><span class="rwith_img qgc2" style="width:40%"></span></p>
           </li>
         <li>
           <p class="text"><span class="w12">乘客人员数量:</span><span><i class="ricon3"></i>昨日:6</span><span><i class="tricon3"></i>今日:5</span><span class="tr"><img src="img/iconup.png" height="16" /> 1</span></p>
           <p class="rwith bgc3"><span class="rwith_img qgc3" style="width:50%"></span></p>
         </li>
          </ul>
        </div>
  </div>
  <div class="aleftboxtbott">
    <h2 class="tith2">总体驾驶人统计</h2>
        <!-- <div class="lefttoday_tit height"><p class="fl">状态：已调节.æl,kmo</p><p class="fr">时间段：2018-06-10 至 2018-06-14</p></div> -->
    <div id="aleftboxtmidd" class="aleftboxtbott_cont2" ></div>
</div>
</div>
<!--  left1 end -->
<div class="mrbox">
      <div class="mrbox_topmidd" style="width: 69%;">
          <div class="amiddboxttop">
              <h2 class="tith2 pt1">实时地图</h2>
                <div class="hot_map" id="topmap"   >

                </div>
            </div>
            <!--  amiddboxttop end-->
                <div class="amidd_bott">
                  <div class="amiddboxtbott1 fl" >
                    <h2 class="tith2 pt1">每日上户车辆</h2>
                      <div class="lefttoday_tit"><p class="fr">2018-06-14</p></div>
                    <div id="amiddboxtbott1" class="amiddboxtbott1content2" ></div>
                  </div>

                <div class="amiddboxtbott2 fl"><h2 class="tith2 pt1">高危车辆统计</h2>
                    <div id="arightboxbott" class="amiddboxtbott2content"></div>
                </div>
            </div>
            <!-- amidd_bott end -->
          </div>
        <!-- mrbox_top end -->
        <div class="mrbox_top_right">
          <div class="arightboxtop"><h2 class="tith2">今日办驾照统计（雷达图）</h2>
            <div id="aleftboxtbott"  class="aleftboxtbott_contr"></div>
          </div>
            <div class="arightboxbott"><h2 class="tith2 ">今日新增驾驶人统计</h2>

              <div id="aleftboxtmiddr" class="arightboxbottcont2"></div>
            </div>
        </div>
        <!-- mrbox_top_right end -->
      </div>

    </div>
</div>

<script type="text/javascript">
var myChart = echarts.init(document.getElementById('aleftboxtmidd'));
option = {
color:['#4d72d9','#76c4bf','#e5ffc7'],
backgroundColor: 'rgba(1,202,217,.2)',
grid: {
				left:60,
				right:60,
				top:20,
				bottom:0,
        containLabel: true
			},
        legend: {
            left: 10,
            top: 5,
            textStyle:{
              fontSize:10,
              color:'rgba(255,255,255,.6)'
            },
            data: ['A照','B照','C照']
        },
        calculable : true,
        series : [

            {
                name:'面积模式',
                type:'pie',
                radius : [20, 70],
                center : ['50%', '55%'],
                roseType : 'area',
                data:[
                    {value:187650, name:'A照'},
                    {value:145896, name:'B照'},
                    {value:148920, name:'C照'},

                ]
            }
        ]
    };
myChart.setOption(option);
</script>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('aleftboxtmiddr'));
option = {
color:['#f1b1ff','#aae3fb','#e5ffc7'],
  backgroundColor: 'rgba(1,202,217,.2)',
  grid: {
						left:60,
						right:60,
						top:20,
						bottom:0,
            containLabel: true
					},
            legend: {
                left: 10,
                top: 5,
                textStyle:{
                  fontSize:10,
                  color:'rgba(255,255,255,.6)'
                },
                data: ['A照','B照','C照']
            },
            calculable : true,
            series : [

                {
                    name:'面积模式',
                    type:'pie',
                    radius : [20, 70],
                    center : ['50%', '55%'],
                    roseType : 'area',
                    data:[
                        {value:187650, name:'A照'},
                        {value:145896, name:'B照'},
                        {value:148920, name:'C照'},

                    ]
                }
            ]
        };
myChart.setOption(option);
</script>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('aleftboxtbott'));
option = {
color:['#7ecef4'],
  backgroundColor: 'rgba(1,202,217,.2)',
  grid: {
						left:20,
						right:50,
						top:23,
						bottom:30,
            containLabel: true
					},

        xAxis: {
            type: 'value',
            axisLine:{
             lineStyle:{
               color:'rgba(255,255,255,.2)'
             }
           },
           splitLine:{
             lineStyle:{
               color:'rgba(255,255,255,0)'
             }
           },
           axisLabel:{
               color:"rgba(255,255,255,1)"
           },
           data: ['1000','5000','10000','15000','20000','25000'],
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            axisLine:{
             lineStyle:{
               color:'rgba(255,255,255,.5)'
             }
           },
           splitLine:{
             lineStyle:{
               color:'rgba(255,255,255,.1)'
             }
           },
           axisLabel:{
               color:"rgba(255,255,255,.5)"
           },
            data: ['C2','C1','B2','B1','A3','A2','A1']
        },
        series: [
            {
                name: '2011年',
                type: 'bar',
                barWidth :30,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(
                            1, 0, 0, 1,
                            [
                                {offset: 0, color: 'rgba(230,253,139,.7)'},
                                {offset: 1, color: 'rgba(41,220,205,.7)'}
                            ]
                        )
                    }
                },
                data: [18203, 23489, 29034,18203, 23489, 29034, 29034]
            }
        ]
    };
myChart.setOption(option);
</script>
<script type="text/javascript">
  var myChart = echarts.init(document.getElementById('amiddboxtbott1'));
  var data = [
      [[28604,77,17099,'Australia',1990],[31163,77.4,2440,'Canada',1990],[1516,68,1605773,'China',1990],[13670,74.7,10082,'Cuba',1990],[28599,75,49805,'Finland',1990],[29476,77.1,569499,'France',1990],[31476,75.4,789237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870776,'India',1990],[29550,79.1,129285,'Japan',1990],[2076,67.9,201954,'North Korea',1990],[12087,72,42954,'South Korea',1990],[24021,75.4,33934,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,381958,'Poland',1990],[19349,69.6,1475652,'Russia',1990],[10670,67.3,53905,'Turkey',1990],[26424,75.7,57117,'United Kingdom',1990],[37062,75.4,252810,'United States',1990]],
      [[44056,81.8,23973,'Australia',2015],[43294,81.7,35927,'Canada',2015],[13334,76.9,1376043,'China',2015],[21291,78.5,11562,'Cuba',2015],[38923,80.8,55057,'Finland',2015],[37599,81.9,64345,'France',2015],[44053,81.1,80545,'Germany',2015],[42182,82.8,329425,'Iceland',2015],[5903,66.8,1311027,'India',2015],[36162,83.5,126571,'Japan',2015],[1390,71.4,251317,'North Korea',2015],[34644,80.7,503439,'South Korea',2015],[34186,80.6,4528526,'New Zealand',2015],[64304,81.6,5210967,'Norway',2015],[24787,77.3,386194,'Poland',2015],[23038,73.13,143918,'Russia',2015],[19360,76.5,78630,'Turkey',2015],[38225,81.4,64715810,'United Kingdom',2015],[53354,79.1,321771,'United States',2015]]
  ];

  option = {
      backgroundColor: 'rgba(1,202,217,.2)',
      grid: {
        left:40,
        right:40,
        top:50,
        bottom:40
							},
      title: {
        top: 5,
        left:20,
          textStyle:{
            fontSize:10,
            color:'rgba(255,255,255,.6)'
          },
          text: '环比类型：日环比'
      },
      // legend: {
      //     right: 10,
      //     top: 5,
      //     textStyle:{
      //       fontSize:10,
      //       color:'rgba(255,255,255,.6)'
      //     },
      //     data: ['1990', '2015']
      // },
      xAxis: {
        axisLine:{
          lineStyle:{
            color:'rgba(255,255,255,.2)'
          }
        },
        splitLine:{
          lineStyle:{
            color:'rgba(255,255,255,.1)'
          }
        },
        axisLabel:{
            color:"rgba(255,255,255,.7)"
        }
      },
      yAxis: {
        axisLine:{
          lineStyle:{
            color:'rgba(255,255,255,.2)'
          }
        },
        splitLine:{
          lineStyle:{
            color:'rgba(255,255,255,.1)'
          }
        },
        axisLabel:{
            color:"rgba(255,255,255,.7)"
        },
          scale: true
      },
      series: [ {
          name: '2015',
          data: data[1],
          type: 'scatter',
          symbolSize: function (data) {
              return Math.sqrt(data[2]) / 5e2;
          },
          label: {
              emphasis: {
                  show: true,
                  formatter: function (param) {
                      return param.data[3];
                  },
                  position: 'top'
              }
          },
          itemStyle: {
              normal: {
                  shadowBlur: 10,
                  shadowColor: 'rgba(25, 100, 150, 0.5)',
                  shadowOffsetY: 5,
                  color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                      offset: 0,
                      color: 'rgb(129, 227, 238)'
                  }, {
                      offset: 1,
                      color: 'rgb(25, 183, 207)'
                  }])
              }
          }
      }]
  };
  myChart.setOption(option);
</script>

<script type="text/javascript">
  var myChart = echarts.init(document.getElementById('amiddboxtbott2'));
  option = {
      backgroundColor: 'rgba(1,202,217,.2)',
      grid: {
								left:60,
								right:60,
								top:50,
								bottom:40
							},

toolbox: {
    feature: {
        dataView: {show: true, readOnly: false},
        magicType: {show: true, type: ['line', 'bar']},
        restore: {show: true},
        saveAsImage: {show: true}
    }
},
legend: {
  top:10,
  textStyle:{
    fontSize: 10,
    color:'rgba(255,255,255,.7)'
  },
    data:['2017年','2018年','同比增速']
},
xAxis: [
    {
        type: 'category',
        axisLine:{
					lineStyle:{
						color:'rgba(255,255,255,.2)'
					}
				},
				splitLine:{
					lineStyle:{
						color:'rgba(255,255,255,.1)'
					}
				},
				axisLabel:{
						color:"rgba(255,255,255,.7)"
				},

        data: ['1','2','3','4','5','6','7','8','9','10','11','12'],
        axisPointer: {
            type: 'shadow'
        }
    }
],
yAxis: [
    {
        type: 'value',
        name: '',
        min: 0,
        max: 250,
        interval: 50,
        axisLine:{
					lineStyle:{
						color:'rgba(255,255,255,.3)'
					}
				},
				splitLine:{
					lineStyle:{
						color:'rgba(255,255,255,.01)'
					}
				},

        axisLabel: {
            formatter: '{value} ml'
        }
    },
    {
        type: 'value',
        name: '',
        max: 25,
        interval: 5,
        axisLine:{
					lineStyle:{
						color:'rgba(255,255,255,.3)'
					}
				},
				splitLine:{
					lineStyle:{
						color:'rgba(255,255,255,.1)'
					}
				},
        axisLabel: {
            formatter: '{value} °C'
        }
    }
],
series: [

    {
        name:'2017年',
        type:'bar',
        itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#b266ff'},
                                    {offset: 1, color: '#121b87'}
                                ]
                            )
                        }
                    },
        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
    },
    {
        name:'2018年',
        type:'bar',
        itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#00f0ff'},
                                    {offset: 1, color: '#032a72'}
                                ]
                            )
                        }
                    },
        data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
    },
    {
        name:'同比增速',
        type:'line',
        itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#fffb34'},
                                    {offset: 1, color: '#fffb34'}
                                ]
                            )
                        }
                    },
        yAxisIndex: 1,
        data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
    }
]
};
  myChart.setOption(option);
</script>
<script type="text/javascript">
      var myChart = echarts.init(document.getElementById('arightboxbott'));
      option = {
        color:['#00ffff','#7fd7b1', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
          backgroundColor: 'rgba(1,202,217,.2)',

          grid: {
              left: '5%',
              right: '8%',
              bottom: '7%',
              top:'8%',
              containLabel: true
          },
          toolbox: {
              feature: {
                  saveAsImage: {}
              }
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              axisLine:{
                lineStyle:{
                  color:'rgba(255,255,255,.2)'
                }
              },
              splitLine:{
                lineStyle:{
                  color:'rgba(255,255,255,.1)'
                }
              },
              axisLabel:{
                  color:"rgba(255,255,255,.7)"
              },
              data: ['6-08','6-09','6-10','6-11','6-12','6-13','6-14']
          },
          yAxis: {
              type: 'value',
              axisLine:{
                lineStyle:{
                  color:'rgba(255,255,255,.2)'
                }
              },
              splitLine:{
                lineStyle:{
                  color:'rgba(255,255,255,.1)'
                }
              },
              axisLabel:{
                  color:"rgba(255,255,255,.7)"
              },
          },
          series: [
              {
                  name:'2014年',
                  type:'line',
                  stack: '总量',
                    areaStyle: {normal: {}},
                  data:[120, 132, 101, 134, 90, 230, 210]
              }

          ]
          };
      myChart.setOption(option);
  </script>
  <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('arightboxbott'));
        option = {
          color:['#00ffff','#7fd7b1', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
            backgroundColor: 'rgba(1,202,217,.2)',

            grid: {
                left: '5%',
                right: '8%',
                bottom: '7%',
                top:'8%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLine:{
                  lineStyle:{
                    color:'rgba(255,255,255,.2)'
                  }
                },
                splitLine:{
                  lineStyle:{
                    color:'rgba(255,255,255,.1)'
                  }
                },
                axisLabel:{
                    color:"rgba(255,255,255,.7)"
                },
                data: ['6-08','6-09','6-10','6-11','6-12','6-13','6-14']
            },
            yAxis: {
                type: 'value',
                axisLine:{
                  lineStyle:{
                    color:'rgba(255,255,255,.2)'
                  }
                },
                splitLine:{
                  lineStyle:{
                    color:'rgba(255,255,255,.1)'
                  }
                },
                axisLabel:{
                    color:"rgba(255,255,255,.7)"
                },
            },
            series: [
                {
                    name:'2014年',
                    type:'line',
                    stack: '总量',
                      areaStyle: {normal: {}},
                    data:[120, 132, 101, 134, 90, 230, 210]
                }

            ]
            };
        myChart.setOption(option);
    </script>

    <script type="text/javascript">
    $(function(){
        //使用echarts.init()方法初始化一个Echarts实例，在init方法中传入echarts map的容器 dom对象
	var mapChart = echarts.init(document.getElementById('topmap'));
	// mapChart的配置
	var option = {
		tooltip: {
	            trigger: 'item',
	            formatter: '{b}<br/>{c} (个)'
    		},
    		toolbox: {
	           show: true,
	           orient: 'vertical',
	           left: 'right',
	           top: 'center',
	           feature: {
	               dataView: {readOnly: false},
	               restore: {},
	               saveAsImage: {}
	           }
    		},
    		visualMap: {
		            min: 0,
		            max: 2000,
		            text:['高','低'],
		            realtime: false,
		            calculable: true,
		            inRange: {
		                color: ['lightskyblue','yellow', 'orangered']
		            }
   			},
    		series:[
    			{
    				name: '北京各区',
           	 		type: 'map',//type必须声明为 map 说明该图标为echarts 中map类型
           	 		map: '北京', //这里需要特别注意。如果是中国地图，map值为china，如果为各省市则为中文。这里用北京
           	 		aspectScale: 0.75, //长宽比. default: 0.75
           	 		zoom: 1.2,
           	 		//roam: true,
	                itemStyle:{
	                    normal:{label:{show:true}},
	                    emphasis:{label:{show:true}}
	                },
        			data: [
        				{name:'东城区', value: 1800},
        				{name:'西城区', value: 1700},
        				{name:'朝阳区', value: 1600},
        				{name:'丰台区', value: 1400},
        				{name:'石景山区', value: 1200},
        				{name:'海淀区', value: 1000},
        				{name:'门头沟区', value: 800},
        				{name:'房山区', value: 600},
        				{name:'通州区', value: 400},
        				{name:'顺义区', value: 200},
        				{name:'昌平区', value: 100},
        				{name:'大兴区', value: 300},
        				{name:'怀柔区', value: 500},
        				{name:'平谷区', value: 700},
        				{name:'密云县', value: 900},
        				{name:'延庆县', value: 1100}

        			]
    			}
    		]
	};
        //设置图表的配置项
        mapChart.setOption(option);

});




    </script>
</body>
</html>
