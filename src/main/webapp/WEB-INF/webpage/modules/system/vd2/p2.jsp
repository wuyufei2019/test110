﻿<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>

<!doctype html>
<meta charset="utf-8">
<title>人口分析</title>
<link href="css/style.css"  rel="stylesheet" type="text/css" media="all" />
<script src="js/echarts.min.js" charset="utf-8"></script>


<body>
<div class="bnt">
  <div class="topbnt_left fl">
   <ul><li><a href="analysis.html">警情警力</a></li>
      <li><a href="people.html">实有人口</a></li>
      <li class="active"><a href="activity.html">活动情况</a></li>
 
   </ul>
  </div>
  <h1 class="tith1 fl">舆情分析</h1>
  <div class=" fr topbnt_right">
    <ul>
       <li><a href="#">返回</a></li>
       <li><a href="traffic.html">交通</a></li>
       <li><a href="index.html">舆情</a></li>
    </ul>
   
  </div>
</div>
<!-- bnt end -->
<div class="left1 pleft1">
    <div class="lefttime"><h2 class="tith2">统计时间</h2>
     <div class="lefttime_text">
        <ul>
          <li class="bg active">今日</li>
          <li></li>
          <li class="bg">本周</li>
          <li></li>
          <li class="bg">本月</li>
          <li></li>
          <li class="bg">本季</li>
          <li></li>
          <li class="bg">本年</li>
        </ul>
     </div>
    </div>
    <div class="plefttoday"><h2 class="tith2">今日活动统计</h2>
    <div class="lefttoday_tit" style=" height:8%"><p class="fl">地区：甘孜</p><p class="fr">2018-06-14</p></div>
    <div class="lefttoday_number">
      <div class="widget-inline-box text-center fl">
        <p>活动总数</p>
        <h3 class="ceeb1fd">54</h3>
        <h4 class="text-muted">环比<img src="img/iconup.png" height="16" />2%</h4>
      </div>
      <div class="widget-inline-box text-center fl">
         <p>佛事活动</p>
        <h3 class="c24c9ff">54</h3>
        <h4 class="text-muted">环比<img src="img/icondown.png" height="16" />3%</h4>
      </div>
      <div class="widget-inline-box text-center fl">
         <p>民俗活动</p>
        <h3 class="cffff00">4</h3>
        <h4 class="text-muted">环比<img src="img/icondown.png" height="16" />3%</h4>
      </div>
      <div class="widget-inline-box text-center fl">
         <p>其他活动</p>
        <h3 class="c11e2dd">4</h3>
        <h4 class="text-muted">环比<img src="img/icondown.png" height="16" />3%</h4>
      </div>
    </div>
    <!-- lefttoday_number end -->
    <!--  lefttoday_bar end-->
    </div>
    <div class="lpeftmidbot">
      <h2 class="tith2">活动占比</h2>
      <div id="lpeftbot" class="lpeftmidbotcont"></div>
  </div>
    <div class="lpeftbot">
      <h2 class="tith2">活动人员性别分析</h2>
      <div id="lpeftmidbot" class="lpeftbotcont" ></div>
  </div>
</div>
<!--  left1 end -->
<div class="left2">
  <div class="pleftbox2top">
    <h2 class="tith2">活动人口名族分析</h2>
    <div id="pleftbox2top" class="pleftbox2topcont"></div>
  </div>
  <div class="pleftbox2midd"><h2 class="tith2">实有人口年龄结构</h2>
  <!-- <div id="pleftbox2midd" class="pleftbox2middcont"></div> -->
  <div class="pvr fl  lpeftb2otcont1 hdtop" style=" height:82%;" >
    <ul>
      <li class="hot1">1</li>
      <li class="hot2">2</li>
      <li class="hot3">3</li>
      <li class="hot4">4</li>
      <li class="hot5">5</li>
    </ul>
    <div id="pleftbox2bott_cont"class="pleftbox2middcont" style=" height:100%;" ></div>
  </div>
  </div>
  <div class="lpeft2bot" >
    <h2 class="tith2 " >实有人口职业占比TOP5</h2>
<div id="prbottom_box1"class="lpeftb2otcont" ></div>

    </div>
</div>
</div>
<div class="mrbox prbox">
      <div class="hdmrbox_top">
          <div class="mrbox_top_midd">
                <div class="hdmrboxtm-mbox"><h2 class="tith2 pt1">地图分析</h2>
                  <div class="lefttoday_tit font14" style=" height:13%"><p class="fl"><i class="ricontop redr">12</i>民事活动</p><p class="fr" ><i class="ricontop bluer">12</i>佛事活动</p></div>
                <div class="mrboxtm-map hdmrboxtm-map">
                   <ul>
                     <li class="bluer" style="top:28%;left:12%">15</li>
                     <li class="redr" style="top:31%;left:18%">13</li>
                      <!--  石渠县-->
                     <li class="bluer" style="top:31%;left:33%">15</li>
                      <li class="redr" style="top:34%;left:36%">13</li>
                       <!--  -->
                      <li class="bluer" style="top:12%;left:43%">15</li>
                      <li class="redr" style="top:15%;left:47%">13</li>
                       <!--  -->
                      <li class="bluer" style="top:22%;left:36%">15</li>
                     <li class="redr" style="top:25%;left:41%">13</li>
                      <!--  -->
                     <li class="bluer" style="top:23%;left:57%">15</li>
                    <li class="redr" style="top:16%;left:53%">13</li>
                     <!--  -->
                    <li class="bluer" style="top:26%;left:64%">15</li>
                   <li class="redr" style="top:22%;left:67%">13</li>
                   <!--  -->
                   <li class="bluer" style="top:7%;left:68%">15</li>
                  <li class="redr" style="top:15%;left:69%">13</li>
                  <!--  丹巴-->
                  <li class="bluer" style="top:32%;left:77%">15</li>
                 <li class="redr" style="top:35%;left:80%">13</li>
                 <!--  康定-->
                 <li class="bluer" style="top:28%;left:81%">15</li>
                <li class="redr" style="top:27%;left:84%">13</li>
                <!--  泸定-->
                <li class="bluer" style="top:34%;left:69%">15</li>
               <li class="redr" style="top:37%;left:73%">13</li>
               <!--  雅江-->
               <li class="bluer" style="top:48%;left:87%">15</li>
              <li class="redr" style="top:47%;left:83%">13</li>
              <!--  九龙-->
              <li class="bluer" style="top:48%;left:60%">15</li>
             <li class="redr" style="top:56%;left:62%">13</li>
             <!--  理塘-->
             <li class="bluer" style="top:68%;left:60%">15</li>
            <li class="redr" style="top:70%;left:63%">13</li>
            <!--  巴塘-->
            <li class="bluer" style="top:67%;left:67%">15</li>
           <li class="redr" style="top:67%;left:71%">13</li>
           <!--  乡城-->
           <li class="bluer" style="top:82%;left:70%">15</li>
          <li class="redr" style="top:84%;left:73%">13</li>
          <!--  得荣-->
          <li class="bluer" style="top:68%;left:60%">15</li>
         <li class="redr" style="top:70%;left:63%">13</li>
         <!--  巴塘-->
         <li class="bluer" style="top:71%;left:78%">15</li>
        <li class="redr" style="top:74%;left:81%">13</li>
        <!--  稻城-->

                    <li class="bluer" style="top:31%;left:57%">15</li>
                    <li class="redr" style="top:33%;left:53%">13</li>
                    <li class="bluer" style="top:44%;left:47%">15</li>
                    <li class="redr" style="top:48%;left:52%">13</li>
                   </ul>
                 </div>
              </div>
<!--  map end-->
                <!-- <div class="pmrboxbottom"><h2 class="tith2 pt1">涉稳警情</h2>
                    <div id="pmrboxbottom" class="pmrboxbottomcont" ></div>
                </div> -->
                <div class="mrbox_bottom_bott">
                    <div class="rbottom_box1 hdwid"><h2 class="tith2">高危人员分类分析</h2>
                    <div id="prbottom_box2" class="prbottom_box1cont"></div></div>
                    <div class="rbottom_box2 hdwid"><h2 class="tith2">活动人口统计</h2>
                    <div id="pleftbox2midd" class="prbottom_box1cont" ></div></div>
              </div>

          </div>
        <!-- mrbox_top_midd end -->
        <div class="mrbox_top_right">
          <div class="hdrightboxtop"><h2 class="tith2">今日活动提醒</h2>
            <div class="lefttoday_tit" style="height:4%"><p class="fl">状态：已调节</p><p class="fr">时间段：2018-06-10</p></div>
            <div class="left2_table hdleft2_table">
               <ul>
                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>

                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li>
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>
                    <li class="bg">
                    <p class="fl"><b>康定市公安局</b><br>
                      村名王某因为被隔壁邻居的狗咬了，产生了纠纷，村名报警。<br>
                    </p>
                    <p class="fr pt17">2018-06-22</p>
                    </li>

               </ul>
            </div>
        </div>


      <!-- <div class="mrbox_bottom">
          <div class="rbottom_box1"><h2 class="tith2">高危人员分类分析</h2>
          <div id="prbottom_box2" class="prbottom_box1cont"></div></div>
          <div class="rbottom_box2"><h2 class="tith2">活动人口统计</h2>
          <div id="pleftbox2midd" class="prbottom_box1cont" ></div></div>
          <div class="rbottom_box3"><h2 class="tith2 pt2">流动人口年龄分析</h2>
          <div id="prbottom_box3" class="prbottom_box1cont"></div></div>

      </div> -->
</div>
<script type="text/javascript">
        var myChart = echarts.init(document.getElementById('lpeftmidbot'));
        option = {
            backgroundColor: 'rgba(1,202,217,.2)',
            grid: {
                      left:50,
                      right:20,
                      top:45,
                      bottom:30
                    },
                    legend: {
                      top:5,
                      textStyle:{
                        fontSize: 10,
                        color:'rgba(255,255,255,.7)'
                      },
                      data:['男','女','总数']
                    },
      // toolbox: {
      //     feature: {
      //         dataView: {show: true, readOnly: false},
      //         magicType: {show: true, type: ['line', 'bar']},
      //         restore: {show: true},
      //         saveAsImage: {show: true}
      //     }
      // },
      // legend: {
      //   top:10,
      //   left:10,
      //   textStyle:{
      //     fontSize: 10,
      //     color:'rgba(255,255,255,.7)'
      //   },
      //     data:['男','女','总数']
      // },
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
                  formatter: '{value}'
              }
          }

      ],
      series: [
          {
              name:'男',
              type:'bar',
              itemStyle: {
                              normal: {
                                  color:'#00a0e9'
                                  // color: new echarts.graphic.LinearGradient(
                                  //     0, 0, 0, 1,
                                  //     [
                                  //         {offset: 0, color: '#b266ff'},
                                  //         {offset: 1, color: '#121b87'}
                                  //     ]
                                  // )
                              }
                          },
              data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
          },
          {
              name:'女',
              type:'bar',
              itemStyle: {
                              normal: {
                                  color:'#fff799'
                                  // color: new echarts.graphic.LinearGradient(
                                  //     0, 0, 0, 1,
                                  //     [
                                  //         {offset: 0, color: '#00f0ff'},
                                  //         {offset: 1, color: '#032a72'}
                                  //     ]
                                  // )
                              }
                          },
              data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
          },
          {
              name:'总数',
              type:'bar',
              itemStyle: {
                              normal: {
                                color:'#6fcc8c'
                                  // color: new echarts.graphic.LinearGradient(
                                  //     0, 0, 0, 1,
                                  //     [
                                  //         {offset: 0, color: '#fffb34'},
                                  //         {offset: 1, color: '#032a72'}
                                  //     ]
                                  // )
                              }
                          },
              data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
      ]
  };
        myChart.setOption(option);
    </script>

    <script type="text/javascript">
            var myChart = echarts.init(document.getElementById('lpeftbot'));
            option = {
              color:['#00b7ee','#f8e19a', '#f19ec2', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
                backgroundColor: 'rgba(1,202,217,.2)',
                grid: {
    											left:20,
    											right:20,
    											top:0,
    											bottom:20
    										},
                        legend: {
                          top:10,
                          textStyle:{
                            fontSize: 10,
                            color:'rgba(255,255,255,.7)'
                          },
                            data:['佛事活动','民俗活动','其他活动']
                        },
                        series : [
                 {
                     name: '访问来源',
                     type: 'pie',
                     radius : '55%',
                     center: ['50%', '55%'],
                     data:[
                         {value:335, name:'佛事活动'},
                         {value:310, name:'民俗活动'},
                         {value:370, name:'其他活动'}

                     ],
                     itemStyle: {
                         emphasis: {
                             shadowBlur: 10,
                             shadowOffsetX: 0,
                             shadowColor: 'rgba(0, 0, 0, 0.5)'
                         }
                     }
                 }
             ]
            };
            myChart.setOption(option);
        </script>
        <script type="text/javascript">
                var myChart = echarts.init(document.getElementById('pleftbox2top'));
                option = {
                  color:['#d2d17c','#00b7ee', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
                    backgroundColor: 'rgba(1,202,217,.2)',
                    grid: {
                              left:20,
                              right:20,
                              top:0,
                              bottom:20
                            },
                            legend: {
                              top:10,
                              textStyle:{
                                fontSize: 10,
                                color:'rgba(255,255,255,.7)'
                              },
                                data:['藏族','回族','彝族','汉族','土家族']
                            },
                            series : [
                     {
                         name: '访问来源',
                         type: 'pie',
                         radius : '55%',
                         center: ['50%', '55%'],
                         data:[
                           {value:335, name:'藏族'},
                           {value:310, name:'回族'},
                           {value:234, name:'彝族'},
                           {value:135, name:'汉族'},
                           {value:158, name:'土家族'}

                         ],
                         itemStyle: {
                             emphasis: {
                                 shadowBlur: 10,
                                 shadowOffsetX: 0,
                                 shadowColor: 'rgba(0, 0, 0, 0.5)'
                             }
                         }
                     }
                 ]
                };
                myChart.setOption(option);
            </script>
            <script type="text/javascript">
                    var myChart = echarts.init(document.getElementById('pleftbox2midd'));
                    option = {
                      color:['#f8e19a','#00b7ee', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
                        backgroundColor: 'rgba(1,202,217,.2)',
                        grid: {
                                  left:20,
                                  right:20,
                                  top:0,
                                  bottom:20
                                },
                                legend: {
                                  top:10,
                                  textStyle:{
                                    fontSize: 10,
                                    color:'rgba(255,255,255,.7)'
                                  },
                                    data:['常住人口','活动人口']
                                },
                                series : [
                         {
                             name: '访问来源',
                             type: 'pie',
                             radius : '55%',
                             center: ['50%', '65%'],
                             data:[
                               {value:335, name:'常住人口'},
                               {value:310, name:'活动人口'}
                             ],
                             itemStyle: {
                                 emphasis: {
                                     shadowBlur: 10,
                                     shadowOffsetX: 0,
                                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                                 }
                             }
                         }
                     ]
                    };
                    myChart.setOption(option);
                </script>
                <script type="text/javascript">
                        var myChart = echarts.init(document.getElementById('pmrboxbottom'));
                        option = {
                          color:['#d2d17c','#00b7ee', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
                            backgroundColor: 'rgba(1,202,217,.2)',
                            legend: {
                              top:10,
                              textStyle:{
                                fontSize: 10,
                                color:'rgba(255,255,255,.7)'
                              },
                                  data:['涉恐人员','涉稳人员','涉毒人员','在逃人员','重点上访人员','肇事肇祸精神病人','刑事犯罪前科人员']
                              },
                              grid: {
                                  left: 10,
                                  right: 10,
                                  top: 40,
                                  bottom: 10,
                                  containLabel: true
                              },

                              xAxis : [
                                     {
                                         type : 'category',
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
                                         data : ['康定市','泸定县','丹巴县','九龙县','新龙县','巴塘县','得荣县','理塘县','甘孜县','道孚县','德格县','色达县']
                                     }
                                 ],
                              yAxis : [
                                  {
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
                                    },
                                      type : 'value'
                                  }

                              ],
                              series : [

                                  {
                                      name:'涉恐人员',
                                      type:'bar',
                                      barWidth :30,
                                      stack: '搜索引擎',
                                      data:[620, 732, 701, 734, 1090, 1130, 1120,620, 732, 701, 734, 1090,]
                                  },
                                  {
                                      name:'涉稳人员',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[120, 132, 101, 134, 290, 230, 220, 101, 134, 290, 230, 220]
                                  },
                                  {
                                      name:'涉毒人员',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[60, 72, 71, 74, 190, 130, 110, 71, 74, 190, 130, 110]
                                  },
                                  ,
                                  {
                                      name:'在逃人员',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[120, 132, 101, 134, 290, 230, 220, 101, 134, 290, 230, 220]
                                  },
                                  {
                                      name:'重点上访人员',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[60, 72, 71, 74, 190, 130, 110, 71, 74, 190, 130, 110]
                                  },
                                  {
                                      name:'肇事肇祸精神病人',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[60, 72, 71, 74, 190, 130, 110, 71, 74, 190, 130, 110]
                                  },
                                  {
                                      name:'重大刑事犯罪前科人员',
                                      type:'bar',
                                      stack: '搜索引擎',
                                      data:[62, 82, 91, 84, 109, 110, 120, 91, 84, 109, 110, 120]
                                  }
                              ]
                              };
                        myChart.setOption(option);
                    </script>
                    <script type="text/javascript">
                            var myChart = echarts.init(document.getElementById('pleftbox2bott_cont'));
                            option = {
                              color:['#7ecef4'],
                                backgroundColor: 'rgba(1,202,217,.2)',
                                grid: {
                    											left:40,
                    											right:20,
                    											top:30,
                    											bottom:0,
                                          containLabel: true
                    										},

                                      xAxis: {
                                          type: 'value',
                                          axisLine:{
                                           lineStyle:{
                                             color:'rgba(255,255,255,0)'
                                           }
                                         },
                                         splitLine:{
                                           lineStyle:{
                                             color:'rgba(255,255,255,0)'
                                           }
                                         },
                                         axisLabel:{
                                             color:"rgba(255,255,255,0)"
                                         },
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
                                          data: ['务农','建筑工','技工','销售','职工']
                                      },
                                      series: [
                                          {
                                              name: '2011年',
                                              type: 'bar',
                                              barWidth :20,
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
                                              data: [18203, 23489, 29034, 104970, 131744]
                                          }
                                      ]
                                  };
                            myChart.setOption(option);
                        </script>
                        <script type="text/javascript">
                                var myChart = echarts.init(document.getElementById('prbottom_box1'));
                                option = {
                                  color:['#d2d17c','#7fd7b1', '#5578cf', '#5ebbeb', '#d16ad8','#f8e19a',  '#00b7ee', '#81dabe','#5fc5ce'],
                                    backgroundColor: 'rgba(1,202,217,.2)',
                                    grid: {
                                              left:20,
                                              right:30,
                                              top:0,
                                              bottom:20
                                            },
                                            legend: {
                                              top: 5,

                                                textStyle:{
                                                  fontSize:10,
                                                  color:'rgba(255,255,255,.6)'
                                                },
                                        data:['男','女','总数']
                                    },
                                    grid: {
                                      left:20,
                                      right:30,
                                      top:40,
                                      bottom:10,
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
                                        data: ['0-6','6-18','18-28','28-55','55以上']
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
                                            name:'男',
                                            type:'line',
                                            stack: '总量',
                                              areaStyle: {normal: {}},
                                            data:[120, 132, 101, 134, 90]
                                        },
                                        {
                                            name:'女',
                                            type:'line',
                                            stack: '总量',
                                            data:[220, 182, 191, 234, 290]
                                        },
                                        {
                                            name:'总数',
                                            type:'line',
                                            stack: '总量',
                                            data:[150, 232, 201, 154, 190]
                                        }
                                    ]
                                    };
                                myChart.setOption(option);
                            </script>
                    <script type="text/javascript">
                            var myChart = echarts.init(document.getElementById('prbottom_box2'));
                            option = {
                                backgroundColor: 'rgba(1,202,217,.2)',
                                  color:['#7ecef4','#7fd7b1'],
                                grid: {
                                          left:30,
                                          right:20,
                                          top:20,
                                          bottom:40
                                        },
                                        xAxis: {
                                          axisLine:{
                    												lineStyle:{
                    													color:'rgba(255,255,255,.7)'
                    												}
                    											},
                    											splitLine:{
                    												lineStyle:{
                    													color:'rgba(255,255,255,.1)'
                    												}
                    											},
                                          data: ['涉恐','涉稳','涉毒','重点','肇事','重大']
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
                    											}
                                        },
                                        series: [{
                                            symbolSize: 20,
                                            data: [
                                              [0.0, 8.04],
                                               [2.0, 6.95],
                                               [3.0, 7.58],
                                               [5.0, 8.81],
                                               [0.4, 8.33],
                                               [4.0, 1.96],
                                               [0.3, 7.24],
                                               [4.6, 4.26],
                                               [2.0, 4.84],
                                               [2.0, 4.82],
                                               [5.0, 5.68]
                                            ],
                                            type: 'scatter'
                                        }]
                                    };
                    myChart.setOption(option);
                  </script>

                        <script type="text/javascript">
                                var myChart = echarts.init(document.getElementById('prbottom_box3'));
                                option = {
                                    backgroundColor: 'rgba(1,202,217,.2)',
                                    grid: {
                        											left:60,
                        											right:20,
                        											top:40,
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
                                  data:['男','女','总数']
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

                                      data: ['0-6','6-18','18-28','28-55','55以上'],
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
                													color:'rgba(255,255,255,0)'
                												}
                											},
                											splitLine:{
                												lineStyle:{
                													color:'rgba(255,255,255,0)'
                												}
                											}
                                  }
                              ],
                              series: [

                                  {
                                      name:'男',
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
                                      data:[2.0, 4.9, 7.0, 23.2, 25.6]
                                  },
                                  {
                                      name:'女',
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
                                      data:[8.6, 5.9, 9.0, 26.4, 28.7]
                                  },
                                  {
                                      name:'总数',
                                      type:'bar',
                                      itemStyle: {
                                                      normal: {
                                                          color: new echarts.graphic.LinearGradient(
                                                              0, 0, 0, 1,
                                                              [
                                                                  {offset: 0, color: '#fffb34'},
                                                                  {offset: 1, color: '#032a72'}
                                                              ]
                                                          )
                                                      }
                                                  },
                                      yAxisIndex: 1,
                                      data:[6.0, 5.2, 3.3, 4.5, 6.3]
                                  }
                              ]
                          };
                                myChart.setOption(option);
                            </script>
                            <script type="text/javascript">
                                    var myChart = echarts.init(document.getElementById('pmrmidd'));
                                    option = {
                                      color:['#f1ff00','#00c1ff', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
                                        // backgroundColor: 'rgba(1,202,217,.2)',
                                        grid: {
                            											left:20,
                            											right:20,
                            											top:30,
                            											bottom:30
                            										},
                                       radar: {
                                           name: {
                                                  textStyle: {
                                                      fontSize: 10,
                                                      color: 'rgba(255,255,255,.8)'
                                                  }
                                              },
                                              splitLine: {
                                                  lineStyle: {
                                                      color: [
                                                          'rgba(1,202,217,.01)', 'rgba(1,202,217,.01)'
                                                      ].reverse()
                                                  }
                                              },
                                              splitArea: {
                                                  show: false
                                              },
                                              axisLine: {
                                                  lineStyle: {
                                                      color: 'rgba(1,202,217,.7)'
                                                  }
                                              },
                                           indicator: [
                                              { name: '红色警情', max: 6500},
                                              { name: '橙色警情', max: 16000},
                                              { name: '黄色警情', max: 30000},
                                              { name: '绿色警情', max: 44000}
                                           ]
                                       },
                                       series: [{
                                           name: '',
                                           type: 'radar',
                                           radius : [5, 100],
                                           center: ['50%', '50%'],
                                           data : [
                                               {
                                                   value : [4300, 10000, 28000, 19000],
                                                   name : ''
                                               },
                                                {
                                                   value : [5000, 14000,  22000, 41000],
                                                   name : ''
                                               }
                                           ]
                                       }]
                                    };
                                    myChart.setOption(option);
                                </script>
</body>
</html>
