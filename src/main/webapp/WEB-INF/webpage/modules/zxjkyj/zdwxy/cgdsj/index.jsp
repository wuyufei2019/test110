<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<%-- <script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.1"></script> --%>
</head>
<body>
     <h2 style="text-align:center">储罐历史波动图</h2>
     <div style="margin:20px 0 10px 0;"></div>
     <div id="searchFrom" style="text-align: center">
         <c:choose>
             <c:when test="${empty usertype }">
                 <input type="text" id="check_company_name" name="check_company_name"
                        class="easyui-combobox" style="height: 30px;"
                        data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/zxjkyj/zdwxycg/qyjson',prompt: '企业名称',
                            onLoadSuccess: function(data) {
                                var data = $(this).combobox('getData');
                                if (data && data.length > 0) {
                                    $(this).combobox('select',data[0].text);
                                    $('#check_cg_name').combobox('reload', '${ctx}/bis/cgxx/getjson/'+data[0].id);
                                    var data1 = $('#check_cg_name').combobox('getData');
                                    if (data1 && data1.length > 0) {
                                        $('#check_cg_name').combobox('select',data1[0].id);
                                    }
                                }
                            }
                        " />
                 <input type="text" id="check_cg_name" name="check_cg_name"
                        class="easyui-combobox" style="height: 30px;"
                        data-options="panelHeight:'150px',editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/bis/cgxx/getjson',prompt: '储罐名称'" />
             </c:when>
             <c:otherwise>
                 <input type="text" id="check_cg_name" name="check_cg_name"
                        class="easyui-combobox" style="height: 30px;"
                        data-options="panelHeight:'150px',editable:false ,valueField: 'id',textField: 'text',url:'${ctx}/bis/cgxx/getjson/${qyid}',prompt: '储罐名称',
                            onLoadSuccess: function(data) {
                                var data = $(this).combobox('getData');
                                if (data && data.length > 0) {
                                    $(this).combobox('select',data[0].id);
                                }
                            }
                " />
             </c:otherwise>
         </c:choose>
         <input type="text" name="starttime" id="starttime" class="easyui-datebox"
               style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '开始日期' " />
         <input type="text" id="endtime" name="endtime" class="easyui-datebox" style="height: 30px;"
               data-options="panelHeight:'auto' ,editable:false , prompt: '结束日期' " />
          <button class="btn btn-info btn-sm" onclick="search()">
               <i class="fa fa-search"></i> 查询
          </button>
          <button class="btn btn-danger btn-sm" onclick="reset()">
               <i class="fa fa-refresh"></i> 全部
          </button></div>
     <div class="ibox-content" style="height:500px;width:100%">
          <div id="main" style="height:400px;width:100%"></div>
     </div>
     </div>
     </div>

     <script type="text/javascript">
     var param;
     var y = [];//y轴
	 var legend = [];//legend值
	 var x = [];//x轴
     var name = '';

  	 function getDataLoadChart(param){
        $.ajax({
            url : ctx + "/zxjkyj/zdwxydsj/getlinejson",
            type : "POST",
            data : param,
            dataType : "json",
            success : function(data) {
                y = [];//y轴
                legend = [];//legend值
                x = [];//x轴
                x = data.date;
                for (key in data) {
                    if (key == 'WD') {
                        name = '温度（℃）';
                        legend.push(name);
                        y.push({
                            "name" : name,
                            "type" : "line",
                            "data" : data[key]
                        });
                    } else if (key == 'YL') {
                        name = '压力（kPa）';
                        legend.push(name);
                        y.push({
                            "name" : name,
                            "type" : "line",
                            "data" : data[key]
                        });
                    } else if (key == 'YW') {
                        name = '液位（m）';
                        legend.push(name);
                        y.push({
                            "name" : name,
                            "type" : "line",
                            "data" : data[key]
                        });
                    }
                }
                loadEcharts();
            }
        });
	 }

	 function initParam(){
		 param={"starttime" : $("#starttime").datebox('getValue'),
                "endtime" : $("#endtime").datebox('getValue'),
                "tankid": $("#check_cg_name").combobox('getValue')
         };
 	 	 if('${usertype}'!='1') {
             param.qyid = $("#check_company_name").combobox('getText');
         }
	 }

     // 企业名称下拉框选择事件
     $('#check_company_name').combobox({
         onSelect: function(row) {
             var data = $(this).combobox('getData');
             if (data && data.length > 0) {
                 $(this).combobox('select',data[0].text);
                 $('#check_cg_name').combobox('reload', '${ctx}/bis/cgqxx/json/'+data[0].id);
                 var data1 = $('#check_cg_name').combobox('getData');
                 if (data1 && data1.length > 0) {
                     $('#check_cg_name').combobox('select',data1[0].id);
                 }
             }
             initParam();
             getDataLoadChart(param);
         }
     });


     // 储罐名称下拉框选择事件
	 $('#check_cg_name').combobox({
         onSelect: function(row) {
             initParam();
             getDataLoadChart(param);
         }
     });

		function search() {
			var d1 = $("#starttime").datebox('getValue');
			var d2 = $("#endtime").datebox('getValue');
			if ((d2 == "" && d1 == "") || (d1 != "" && d2 != "")) {
				 initParam();
		    	 getDataLoadChart(param);
			} else {
				layer.msg("请选择日期！", {
					time : 1000
				});
				return;
			}
		}

		function reset() {
            var tankid = $("#check_cg_name").combobox('getValue');
			$("#searchFrom").form("clear");
            $("#check_cg_name").combobox('setValue', tankid);
            initParam();
	    	 getDataLoadChart(param);
		}

		// 路径配置
 		require.config({
 			paths : {
 				echarts : '${ctxStatic}/echarts'
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
                    }],
                    series: y
                });

            });


        }
	</script>
</body>
</html>