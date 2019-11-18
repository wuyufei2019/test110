<%@ page
	contentType="text/html;charset=UTF-8"%>
<%@ include
	file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>统计分析</title>
<meta name="decorator"content="default" />
<script
	type="text/javascript"
	src="${ctxStatic}/echarts/echarts.js"></script>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/tjfx/view.js?v=1.2"></script>
</head>
<body>

<h2 style="text-align:center;padding:10px"> <div class="pull-left">
			<form id="searchFrom"  style="margin-bottom: 8px;" class="form-inline">
			<input type="text"  id="aqjg_jcjh_tjfx_year" class="easyui-combobox"  style="width:100px;height: 30px;"/>
			</form>
  </div>统计分析</h2>

	<div class="easyui-panel"  style="width:100%;height:100%;padding:10px;">
		<div class="easyui-layout" data-options="fit:true">
		
			<div data-options="region:'north'" style="width:65%;padding:10px;height:45%">
				<table id="aqjg_tjfx_dg"></table> 
			</div>
			<div data-options="region:'south',split:true" style="width:35%;padding:10px;height:55%">
				<div class="ibox-content">
						<div id="main2" style="height:350px;"></div>
					</div>
			</div>
			
		</div>
	</div>

	<script
		type="text/javascript">
		
		$("#aqjg_jcjh_tjfx_year").combobox({ 
			editable:'true',
			valueField:'year',    
			textField:'year',  
			panelHeight:'auto',
			prompt:'年份',
			onSelect:function(rec){
				loadEcharts2(rec.year);
				reload(rec.year);
			}
		});
		var data = [];
		var thisYear;
		var startYear=new Date().getUTCFullYear()+1;

		for(var i=0;i<5;i++){
			thisYear=startYear-i;
			data.push({"year":thisYear});
		}
		$("#aqjg_jcjh_tjfx_year").combobox("loadData", data);
		
		var datas;
		// 路径配置
		require.config({
			paths : {
				echarts : '${ctxStatic}/echarts'
			}
		});
		var year=$("#aqjg_jcjh_tjfx_year").val();
		loadEcharts2(year);

		function loadEcharts2(year) {
			require(
					[ 'echarts', 'echarts/chart/bar', 'echarts/chart/pie' ],
					function(ec) {
						var myChart2 = ec.init(document.getElementById('main2'));
						myChart2.showLoading();
						$.ajax({
							url : ctx + "/aqjd/tjfx/jsondate2",
							type : "POST",
							data:{"year":year},
							dataType : "json",
							success : function(data) {
								var datax = eval(data.xdata);
								var qycount = eval(data.qycount);
								var fincount = eval(data.fincount);
								var unfincount = eval(data.unfincount);
								myChart2.hideLoading();
								myChart2.setOption({
									title : {
										text : (year==""?new Date().getFullYear():year)+'年检查结果统计',
										
									},
									tooltip : {
										trigger : 'axis',
										axisPointer : {
											type : 'shadow'
										}
									},
									legend : {
										data : [ '计划检查次数', '企业完成检查次数', '企业只完成初查次数' ],
										x : 'center',
									},
									toolbox : {
										show : true,
										orient : 'vertical',
										y : 'center',
										feature : {
											magicType : {
												show : true,
												type : [ 'stack', 'tiled' ]
											},
											restore : {
												show : true
											},
											saveAsImage : {
												show : true
											}
										}
									},
									calculable : true,
									xAxis : [ {
										type : 'category',
										data : datax
									} ],
									yAxis : [ {
										type : 'value',
										splitArea : {
											show : true
										}
									} ],
									grid : {
										x:80,
										y:60,
										x2 :30,
										y2: '40'
									},
									series : [
											{
												name : '计划检查次数',
												type : 'bar',
												stack : '总量',
												data : qycount
											},
											{
												name : '企业完成检查次数',
												type : 'bar',
												stack : '总量',
												data :  fincount
											},
											{
												name : '企业只完成初查次数',
												type : 'bar',
												stack : '总量',
												data : unfincount
											}]
								});//隐藏加载动画
								myChart2.on('click', function(param) {
									var year1=(year=="")?new Date().getFullYear():year;
								})
							}
						});
					});
		}
	</script>
</body>
</html>