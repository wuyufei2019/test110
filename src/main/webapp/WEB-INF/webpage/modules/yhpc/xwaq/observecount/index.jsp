<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>观察统计</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/yhpc/xwaq/observecount/index.js?v=1.0"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="yhpc_observecount_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;"
			class="form-inline">
			<div class="form-group">
				<c:if test="${usertype ne '1' }">
					<input type="text" id="yhpc_observe_qyname" name="yhpc_observe_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
				</c:if>
				<input style="height: 30px;" id="depart" name="depart" class="easyui-combobox" 
						data-options="prompt:'部门', validType:'length[0,250]',panelHeight:'120px',editable:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'" />
				<input style="height: 30px;" name="starttime" editable="false" class="easyui-datebox" data-options="prompt:'观察时间起'" />
				<input style="height: 30px;" name="endtime" editable="false"  class="easyui-datebox" data-options="prompt:'观察时间止'" />
				<span class="btn btn-primary btn-rounded btn-outline btn-sm "
					onclick="search()">
					<i class="fa fa-search"></i> 查询
				</span>
				<span class="btn btn-primary btn-rounded btn-outline btn-sm "
					onclick="reset()">
					<i class="fa fa-refresh"></i> 重置
				</span>
			</div>
		</form>
	</div>

	<table style="height:40%;" id="yhpc_observecount_dg"></table>

	<div style="height:40%;" class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-15">

				<div class="ibox float-e-margins">
					<div class="ibox-title" style="width:100%">
						<h5>统计分析图</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div id="main" style="height:400px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
		var datas;
		loadEcharts();

function loadEcharts() {
	require.config({
		paths : {
			echarts : '${ctxStatic}/echarts'
		}
	});
	require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ], function(ec) {
		var myChart = ec.init(document.getElementById('main'));
		myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		$.ajax({
			url : ctx + "/yhpc/gctj/lxcount",
			type : "POST",
			data:{"depart":$("[name=depart]").val(),"starttime":$("[name=starttime]").val(),"endtime":$("[name=endtime]").val(),"qyname":$("[name=yhpc_observe_qyname]").val()},
			dataType : "json",
			success : function(data) {
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({
				    title: {
		                text: '观察统计分析图'
		            },
					tooltip : {
						trigger : 'axis'
					},
					legend: {
		            	data:['不安全行为数量','不安全状态数量'],
		            	textStyle: {fontSize : 13,
		            				fontFamily : 'Arial',
		            				fontWeight : 100}
		            },
					calculable : true,
					xAxis : [
					{
						name : '观察类别',
						type : 'category',
						data : data.lx ,
		   				axisLabel:{  
		   					  textStyle : {
											fontSize : 13,
											fontFamily : 'Arial',
											fontWeight : 100,
							  },
			                  interval: 0,  
			                  formatter:function(value)  
			                  {  
			                      var ret = "";//拼接加\n返回的类目项  
			                      var maxLength = 10;//每项显示文字个数  
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
					yAxis : [ 
						{
							type : 'value',
							name :'不安全数量',
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
						name : '不安全行为数量',
						type : 'bar',
						barCategoryGap : '60%',
						data : data.xw,
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
						name : '不安全状态数量',
						type : 'bar',
						barCategoryGap : '60%',
						data : data.zt,
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
			}
		 });
	});
}
	</script>
</body>
</html>