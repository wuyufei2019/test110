<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>事故统计</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctx}/static/model/js/sggl/sgtj/index.js?v=1.6"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
<script src="${ctxStatic}/echarts/macarons.js"></script>
</head>
<body>
	<!-- 工具栏 -->
	<div id="sggl_sgtj_tb" style="padding:5px;height:auto">
		<form id="searchFrom" action="" style="margin-bottom: 8px;"
			class="form-inline">
			<div class="form-group">
				<input style="height: 30px;" id="type" name="type" class="easyui-combobox" 
						data-options="editable:false,panelHeight:'auto',data: [{value:'0',text:'按月份'},{value:'1',text:'按类型'}],
							   onSelect: function(rec){
						        	if(rec.value=='0'){
						        		loadEcharts(year);
						        	}
						        	if(rec.value=='1'){
						        		loadEcharts(year);
						        	}
						        }
						 " /> 
				<input  id="sggl_sgtj_year" name="sggl_sgtj_year" style="height: 30px;" class="easyui-combobox" 
						data-options="editable:false,panelHeight:'120px',prompt:'年份' ,valueField: 'id' ,textField:'text' ,url:'${ctx}/sggl/sgxx/year'" />
				<input type="text" id="sggl_sgtj_sglx" name="sggl_sgtj_sglx" class="easyui-combobox" style="height: 30px;" 
						data-options="prompt: '事故类型',panelHeight:'auto',editable:false,data: [
									{value:'死亡Fatal',text:'死亡Fatal'},
									{value:'损工事故',text:'损工事故'},
									{value:'医疗处理事故',text:'医疗处理事故'},
									{value:'急救事故',text:'急救事故'},
									{value:'幸免事故',text:'幸免事故'}]" />
				<input type="text" id="sggl_sgtj_sglevel" name="sggl_sgtj_sglevel" class="easyui-combobox" style="height: 30px;"
					data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '事故等级',data: [
								        {value:'一般',text:'一般'},
								        {value:'较大',text:'较大'},
								        {value:'重大',text:'重大'},
								        {value:'特大',text:'特大'} ]" />
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

	<table style="height:40%;" id="sggl_sgtj_dg"></table>


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
		//设置下拉框默认时间	
		var nowyear=new Date().getUTCFullYear();
		
		var year=$("[name=sggl_sgtj_year]").val()+"年";
		if(year=='年'){
			year=nowyear+"年";
		}
		
		$(function(){
			//默认搜索类型
			$('#type').combobox('setValue', '按月份');
			$('#sggl_sgtj_year').combobox('setValue', nowyear);
			loadEcharts(year);
		});
		
		function loadEcharts(year) {
			var type=$('#type').combobox('getValue');
			var url=ctx + "/sggl/sgxx/jsondate";
			var title="按事故月份统计图示";
			if(type=="1"){
				url=ctx + "/sggl/sgxx/jsontype";
				title="按事故类型统计图示";
			}else if(type=="2"){
				url=ctx + "/sggl/sgxx/jsonbm";
				title="按部门统计图示";
			}
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
				var myChart = ec.init(document.getElementById('main'),'macarons');
				myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
				$.ajax({
					url : url,
					type : "POST",
					data:{"sgtype":$("[name=sggl_sgtj_sglx]").val(),"sglevel":$("[name=sggl_sgtj_sglevel]").val(),"year":$("[name=sggl_sgtj_year]").val()},
					dataType : "json",
					success : function(data) {
						var jsondatex = eval(data.xdate);
						var jsondatey = eval(data.ydate);
						myChart.hideLoading(); //隐藏加载动画
						myChart.setOption(
						{
							title : {
								x : 'center',
								text :year+ title
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
								show : true,
							} ],
							series : [ {
								name : '事故统计',
								type : 'bar',
								itemStyle : {
									normal : {
/*  										color : function(params) {
											// build a color map as your need.
											var colorList = [ '#C1232B',
													'#B5C334', '#FCCE10',
													'#E87C25', '#27727B',
													'#FE8463', '#9BCA63',
													'#FAD860', '#F3A43B',
													'#60C0DD', '#D7504B',
													'#C6E579', '#F4E001',
													'#F0805A', '#26C0C0',
													'#E87C25', '#27727B',
													'#FE8463', '#9BCA63',
													'#FAD860', '#F3A43B',
													'#60C0DD', '#D7504B', ];
											return colorList[params.dataIndex]
										},  */
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