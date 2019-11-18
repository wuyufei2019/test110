<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>事件信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctx}/static/model/js/aqjg/sjgl/tjfx/index.js?v=1.2"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="aqjg_tjfx_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;"
			class="form-inline">
			<div class="form-group">
				<input  name="aqjg_tjfx_year" style="height: 30px;"
					class="easyui-combobox"data-options="prompt:'年份',valueField: 'id',textField: 'text',url:'${ctx}/aqjg/tjfx/year'" /><input
					type="text" name="aqjg_tjfx_sgtype" class="easyui-combobox"
					style="height: 30px;"
					data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故类型',data: [
								        {value:'wtdj',text:'物体打击'},
								        {value:'zt',text:'灼烫'},
								        {value:'wsbz',text:'瓦斯爆炸'},
								        {value:'clsh',text:'车辆伤害'},
								        {value:'hz',text:'火灾'},
								        {value:'hybz',text:'火药爆炸'},
								        {value:'jxsh',text:'机械伤害'},
								        {value:'gczl',text:'高处坠落'},
								        {value:'glbz',text:'锅炉爆炸'},
								        {value:'qzsh',text:'起重伤害'},
								        {value:'tt',text:'坍塌'},
								        {value:'rqbz',text:'容器爆炸'},
								        {value:'cd',text:'触电'},
								        {value:'mdpb',text:'冒顶片帮'},
								        {value:'qtbz',text:'其他爆炸'},
								        {value:'yn',text:'淹溺'},
								        {value:'ts',text:'透水'},
								        {value:'zdhzx',text:'中毒和窒息'},
								        {value:'fp',text:'放炮'},
								        {value:'qtsh',text:'其他伤害'} ] " />
				<input type="text" id="aqjg_tjfx_sglevel" name="aqjg_tjfx_sglevel"
					class="easyui-combobox" style="height: 30px;"
					data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故等级',data: [
								        {value:'0',text:'特别重大事故'},
								        {value:'1',text:'重大事故'},
								        {value:'2',text:'较大事故'},
								        {value:'3',text:'一般事故'} ]" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</div>
		</form>
		<div class="row">
			<div class="col-sm-12">
				<div class="pull-left">

					<button class="btn btn-white btn-sm " data-toggle="tooltip"
						data-placement="left" onclick="refresh()" title="刷新">
						<i class="glyphicon glyphicon-repeat"></i> 刷新
					</button>

				</div>
			</div>
		</div>

	</div>

	<table style="height:50%;" id="aqjg_tjfx_dg"></table>


	<div class="wrapper wrapper-content">
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
		var year=$("[name=aqjg_tjfx_year]").val()+"年";
		if(year=='年')
		year="";
		loadEcharts(year);
		function loadEcharts(year) {
			// 路径配置
			require.config({
				paths : {
					echarts : '${ctxStatic}/echarts'
				}
			});

			// 使用
			require([ 'echarts', 'echarts/chart/line', // 使用柱状图就加载bar模块，按需加载
			'echarts/chart/bar' ], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));
				myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
				$.ajax({
					url : ctx + "/aqjg/tjfx/jsondate",
					type : "POST",
					data:{"sgtype":$("[name=aqjg_tjfx_sgtype]").val(),"sglevel":$("[name=aqjg_tjfx_sglevel]").val(),"year":$("[name=aqjg_tjfx_year]").val()},
					dataType : "json",
					success : function(data) {
						var jsondatex = eval(data.xdate);
						var jsondatey = eval(data.ydate);
						myChart.hideLoading(); //隐藏加载动画
						myChart.setOption(
						{
							title : {
								x : 'center',
								text :year+ '按月份事故统计图示'
							},
							tooltip : {
								trigger : 'item'
							},
							toolbox : {
								show : true,
								feature : {
									/* dataView: {show: true, readOnly: false}, */
									restore : {
										show : true
									},
									saveAsImage : {
										show : true
									}
								}
							},
							calculable : true,
							grid : {
								borderWidth : 0,
								y : 80,
								y2 : 60
							},
							xAxis : [ {
								type : 'category',
								show : true,
								data : jsondatex
							} ],
							yAxis : [ {
								type : 'value',
								show : true
							} ],
							series : [ {
								name : '事故统计',
								type : 'bar',
								itemStyle : {
									normal : {
										color : function(params) {
											// build a color map as your need.
											var colorList = [ '#C1232B',
													'#B5C334', '#FCCE10',
													'#E87C25', '#27727B',
													'#FE8463', '#9BCA63',
													'#FAD860', '#F3A43B',
													'#60C0DD', '#D7504B',
													'#C6E579', '#F4E001',
													'#F0805A', '#26C0C0' ];
											return colorList[params.dataIndex]
										},
										label : {
											show : true,
											position : 'top',
											formatter : '{b}\n{c}'
										}
									}
								},
								data : jsondatey
							} ]
						});
					}
				});
			});

		}
	</script>
</body>
</html>