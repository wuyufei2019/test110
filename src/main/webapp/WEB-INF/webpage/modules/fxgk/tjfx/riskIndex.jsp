<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>风险管控统计分析</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script type="text/javascript"
	src="${ctx}/static/model/js/fxgk/tjfx/mydatagrid.js?v=1.1"></script>
<script type="text/javascript"
	src="${ctx}/static/model/js/fxgk/tjfx/myechart.js?v=1.2"></script>
<script>
var xdata = [];
	var dg;
	var type = '${type}';
	var ctx = '${ctx}';
	$(function() {//初始化
		dianji('fl');
	});
	// 路径配置
		require.config({
			paths : {
				echarts : '${ctxStatic}/echarts'
			}
		});
	//点击选择风险等级或者乡镇
	function dianji(flag) {
		require([ 'echarts', 'echarts/chart/bar'], function(ec) {
		var sss = 15;
		var myChart = ec.init(document.getElementById('main'));
		if (flag == 'fx') {
			if (type != 1) {
				fengxianGrid();
			}
			$.post(ctx + '/fxgk/tjfx/FXDjFXD', function(result) {
				if(result){
					option.series[0].data=[ {
						value : result.lan,
						itemStyle : {
							normal : {
								color : '#5690D2'
							}
						}
					}, {
						value : result.huang,
						itemStyle : {
							normal : {
								color : '#FFF147'
							}
						}
					}, {
						value : result.cheng,
						itemStyle : {
							normal : {
								color : '#E87C25'
							}
						}
					}, {
						value : result.hong,
						itemStyle : {
							normal : {
								color : '#FF0000'
							}
						}
					} ];
				}
				myChart.setOption(option);
				myChart.on('click',function(param){
					fengxianGrid(param.name);
		     	}); 
			});

		} else if (flag == 'xz') {
			if (type != 1) {
				xiangzhenGrid();
			}
			$.ajax({ 
				url:ctx + '/fxgk/tjfx/FXDjXZ',
				type:'post',
				async : false,
				success:function(result) { 
				xdata = result.xzlist;
				var listsize=result.listsize;
				var x1=[];
				var x2=[];
				var x3=[];
				var x4=[];
				for(var i=1;i<=listsize;i++){
					x1.push(result['xz'+i+'_4']);
					x2.push(result['xz'+i+'_3']);
					x3.push(result['xz'+i+'_2']);
					x4.push(result['xz'+i+'_1']);
				}
				option2.series[0].data=x1;
				option2.series[1].data=x2;
				option2.series[2].data=x3;
				option2.series[3].data=x4;
				option2.xAxis.data=xdata;
				myChart.setOption(option2);
				myChart.on('click',function(param){
					xiangzhenGrid(param.name);
		     	}); 
			}
		})
	}
		 else if (flag == 'fl') {
			if (type != 1) {
				fenleiGrid();
			}
			$.post(ctx + '/fxgk/tjfx/FXDjFL', function(result) {
				if(result){
				option3.series[0].data=[ result.xz1_4, result.xz2_4, result.xz3_4, result.xz4_4, result.xz5_4, result.xz6_4, result.xz7_4, result.xz8_4, result.xz9_4, result.xz10_4, result.xz11_4,
											result.xz12_4, result.xz13_4, result.xz14_4 ];
				option3.series[1].data=[ result.xz1_3, result.xz2_3, result.xz3_3, result.xz4_3, result.xz5_3, result.xz6_3, result.xz7_3, result.xz8_3, result.xz9_3, result.xz10_3, result.xz11_3,
											result.xz12_3, result.xz13_3, result.xz14_3 ];
				option3.series[2].data=[ result.xz1_2, result.xz2_2, result.xz3_2, result.xz4_2, result.xz5_2, result.xz6_2, result.xz7_2, result.xz8_2, result.xz9_2, result.xz10_2, result.xz11_2,
											result.xz12_2, result.xz13_2, result.xz14_2 ];
				option3.series[3].data=[ result.xz1_1, result.xz2_1, result.xz3_1, result.xz4_1, result.xz5_1, result.xz6_1, result.xz7_1, result.xz8_1, result.xz9_1, result.xz10_1, result.xz11_1,
											result.xz12_1, result.xz13_1, result.xz14_1 ];
				myChart.setOption(option3);
				myChart.on('click',function(param){
					fenleiGrid(param.name);
		     	}); 
				}

			});
		} else if (flag == 'sg') {
			if (type != 1) {
				shiguGrid();
			}
			$.post(ctx + '/fxgk/tjfx/FXDjSg', function(result) {
				if(result){
					option4.series[0].data=[ result.xz1_4, result.xz2_4, result.xz3_4, result.xz4_4, result.xz5_4, result.xz6_4, result.xz7_4, result.xz8_4, result.xz9_4, result.xz10_4, result.xz11_4,
												result.xz12_4, result.xz13_4, result.xz14_4, result.xz15_4, result.xz16_4, result.xz17_4, result.xz18_4, result.xz19_4, result.xz20_4 ];
					option4.series[1].data=[ result.xz1_3, result.xz2_3, result.xz3_3, result.xz4_3, result.xz5_3, result.xz6_3, result.xz7_3, result.xz8_3, result.xz9_3, result.xz10_3, result.xz11_3,
												result.xz12_3, result.xz13_3, result.xz14_3, result.xz15_3, result.xz16_3, result.xz17_3, result.xz18_3, result.xz19_3, result.xz20_3 ];
					option4.series[2].data=[ result.xz1_2, result.xz2_2, result.xz3_2, result.xz4_2, result.xz5_2, result.xz6_2, result.xz7_2, result.xz8_2, result.xz9_2, result.xz10_2, result.xz11_2,
												result.xz12_2, result.xz13_2, result.xz14_2, result.xz15_2, result.xz16_2, result.xz17_2, result.xz18_2, result.xz19_2, result.xz20_1 ];
					option4.series[3].data=[ result.xz1_1, result.xz2_1, result.xz3_1, result.xz4_1, result.xz5_1, result.xz6_1, result.xz7_1, result.xz8_1, result.xz9_1, result.xz10_1, result.xz11_1,
												result.xz12_1, result.xz13_1, result.xz14_1, result.xz15_1, result.xz16_1, result.xz17_1, result.xz18_1, result.xz19_1, result.xz20_1 ];
			     	myChart.setOption(option4); 
			     	myChart.on('click',function(param){
			     		shiguGrid(param.name);
			     	});  
				}

			});
		}
	});
	}
</script>
</head>
<body>
	<div style="padding:5px;height:auto;width: 100%;">
		<button onclick="dianji('fl');" class="easyui-linkbutton"
			style="width: 100px;">风险分类</button>
		<button onclick="dianji('fx');" class="easyui-linkbutton"
			style="width: 100px;">风险等级</button>
		<button onclick="dianji('sg');" class="easyui-linkbutton"
			style="width: 100px;">易发事故</button>
		<shiro:hasAnyRoles name="ajcountyadmin,ajcounty,admin,superadmin,ajtownadmin,ajtown">
		<button onclick="dianji('xz');" class="easyui-linkbutton"
			style="width: 100px;">网格</button>
		</shiro:hasAnyRoles>
		<br />
		<br />
		<br />
		<div id="main" style="width: 100%;height:450px;">option2</div>
	</div>
	<table id="fxgk_tjfx_dg" style="width: 100%;"></table>
</body>
</html>