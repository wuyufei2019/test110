<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default" />
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts.js"></script>
	<%--  <script type="text/javascript" src="${ctxStatic}/model/js/qyxx/qyjbxx/qyfbmapindex.js"></script>  --%>
	<%-- <script type="text/javascript" src="${ctxStatic}/model/js/home/bootstrap.min.js"></script> --%>
	<script type="text/javascript" src="${ctxStatic}/model/js/home/jquery.liMarquee.js"></script>
	<%--  <script type="text/javascript" src="${ctxStatic}/model/js/zxjkyj/dtfxyt/mapindex.js?v=1.2"></script> --%>
	<link href="${ctxStatic}/model/css/home/styles.css" rel="stylesheet" />
	<link href="${ctxStatic}/model/css/home/liMarquee.css" rel="stylesheet" />
	<style type="text/css">
		.BMap_cpyCtrl {
			display: none;
		}

		.anchorBL {
			display: none;
		}

		.imgdiv {
			text-align: left;
			float: right;
			width: 50%;
			font-size: 12px;
		}

		.imgsty {
			width: 13px;
			height: 13px;
		}

		.mr {
			margin-left: 20px;
		}

		.titlediv {
			float: left;
			margin-left: -45px;
			margin-top: -25px;
			font-size: 15px
		}

		.afont {
			font-size: 15px;
			font-weight: 600;
		}


		.w15 {
			width: 15%;
		}

		.w20 {
			width: 20%;
		}

		.tds {
			font-size: 12px;
			letter-spacing: -0.1em;;
		}
		.col-lg-2-4 {
			width: 20%
		}
	</style>
</head>
<body id="win">
<div class="container-fluid">
	<div class="toptips mgt10">
		<div class="weather" style="height:30px;">
			<a id="weather" href="javascript:" style="display:block;margin-top:5px;font-size:15px;"> </a>
		</div>
	</div>
<<<<<<< .mine
	<div class="col-lg-12 col-md-12 col-xs-12  bs-example mgt10 mgl10">
		<div class="ajtitle">
			统计信息
			<a class="pull-right" href="#"></a>
		</div>
		<div class="col-lg-2-4  col-md-4 col-xs-6 ">
			<div class="tjbox1">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-35px">
					<div class="titlediv">企业概况</div>
					<br>
					<a href="#" style="color:white;" onclick="openPage('/bis/qyjbxx/index','一企一档')">企业总数：<span class="afont" id="qysum"></span></a><br>
					<a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=2','化工企业')">化工企业：<span class="afont" id="hgsum"></span></a><br>
					<a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=1','工贸企业')">工贸企业：<span class="afont" id="gmsum"></span></a><br>
					<a href="#" style="color:white;" onclick="openPage2('/bis/qyjbxx/index?jglx=9','其他企业')">其他企业：<span class="afont" id="qtsum"></span></a>
				</div>
||||||| .r1970
	<div id="chart_area" class="col-lg-12 col-md-12 col-xs-12  bs-example mgt10 mgl10">
		<div class="chart_card">
			<div class="tit tit_1">
				企业总数
=======
	<div class="col-lg-12 col-md-12 col-xs-12  bs-example mgt10 mgl10">
		<div class="ajtitle">
			统计信息
			<a class="pull-right" href="#"></a>
		</div>
		<div class="col-lg-2-4  col-md-4 col-xs-6 ">
			<div class="tjbox1">
				<div class="tjicon"></div>
				<div class="tjtext">
					<div class="titlediv">企业概况</div>
					<br>
					<a href="#" style="color:white;" onclick="openPage('/bis/qyjbxx/index','一企一档')">企业数：<span class="afont"></span></a><br>
					<a href="#" style="color:white;" onclick="openPage('/fxgk/fxxx/index','风险点信息')">风险点数：<span class="afont"></span></a>
				</div>
>>>>>>> .r1981
			</div>
<<<<<<< .mine
		</div>
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox2">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">两重点一重大</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px"> <br>
                  <a href="#" style="color:white" onclick="openPage('/bis/hazard/index','重大危险源')">重大危险源：<span class="afont" id="wxysum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/wlxx/index2','重点监管化学品')">重点监管化学品：<span class="afont" id="hxpsum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/gwgy/index','高危工艺')">高危工艺：<span class="afont" id="gwsum"></span></a></span>
||||||| .r1970
			<div class="ctt">
				<div id="chart1" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">
=======
		</div>
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox2">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">两重点一重大</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px"> <br>
                  <a href="#" style="color:white" onclick="openPage('/bis/wlxx/index2','重点监管化学品')">监管化学品：<span class="afont"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/gwgy/index','高危工艺')">高危工艺：<span class="afont"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/hazard/index','重大危险源')">重大危险源：<span class="afont"></span></a></span>
>>>>>>> .r1981

				</div>
			</div>
		</div>
<<<<<<< .mine
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox4">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">安全监督</div>
					<br>
					<a href="#" style="color:white"onclick="openDialogView('计划检查数','${ctx }/aqjd/jcjh/index','100%', '100%','');">计划检查数：<span class="afont" id="jcssum"></span></a><br>
					<a href="#" style="color:white"onclick="openDialogView('执行数','${ctx }/aqjd/jcjl/index','100%', '100%','');">执行数：<span class="afont" id="zxsum"></span></a><br>
					<a href="#" style="color:white"onclick="openDialogView('待复查数','${ctx }/aqjd/jcjl/index','100%', '100%','');">待复查数：<span class="afont" id="fcsum"></span></a>
				</div>
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				风险点数
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox4">
				<div class="tjicon"></div>
				<div class="tjtext">
					<div class="titlediv">风险点巡查</div>
					<br>
					<a href="#" style="color:white"onclick="openDialogView('已上线企业数','${ctx }/yhpc/tjfx/sjzl?type=2','100%', '100%','');">上线企业数：<span class="afont"></span></a><br>
					<a href="#" style="color:white"onclick="openDialogView('检查记录数','${ctx }/yhpc/xjjl/index','100%', '100%','');">巡查记录数：<span class="afont"></span></a>
				</div>
>>>>>>> .r1981
			</div>
		</div>

<<<<<<< .mine
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox5">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">安全执法</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/cfjd/index','处罚数')">处罚数：<span class="afont" id="cfsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/ajclcp/index','案件呈批数')">案件呈批数：<span class="afont" id="cpsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/xzcf/ybcf/jasp/index','结案数')">结案数：<span class="afont" id="jasum"></span></a>
                  </span>
				</div>
			</div>
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				重大危险源
			</div>
			<div class="ctt">
				<div id="chart3" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">

			</div>
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox5">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">隐患排查治理</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/yhpc/yhpcjl/index','隐患排查')">隐患总数：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/yhpc/yhpcjl/index','隐患排查',3)">已整改数：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/yhpc/yhpcjl/index','隐患排查','4')">未整改数：<span class="afont"></span></a>
                  </span>

				</div>
			</div>
>>>>>>> .r1981
		</div>
<<<<<<< .mine
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				特种设备类别
			</div>
			<div class="ctt">
				<div id="chart4" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox3">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">重点监管</div>
					<span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style="color:white" onclick="openDialogView('现场供气','${ctx }/bis/fieldsupply/index','100%', '100%','');">现场供气：<span class="afont"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/yjxx/index','冶金信息')">冶金信息：<span class="afont"></span></a><br>
                  <a href="#" style="color:white" onclick="openDialogView('粉尘信息','${ctx }/bis/fcxx/index','100%', '100%','')">粉尘信息：<span class="afont"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/bis/sxkj/index','受限空间')">受限空间：<span class="afont"></span></a></span>
					</span>
>>>>>>> .r1981

<<<<<<< .mine
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox6">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">安全许可</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/aqjg/aqba/index','已发证')">已发证：<span class="afont" id="yfsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/aqjg/aqba/index','已过期')">已过期：<span class="afont" id="gqsum"></span></a><br>
                  </span>
				</div>
||||||| .r1970
=======
				</div>
>>>>>>> .r1981
			</div>
		</div>
<<<<<<< .mine

		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox6">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-40px">
					<div class="titlediv">安全许可</div>
					<span style="font-size:12px;letter-spacing:0.1em;height:4px">
                  <br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjdw/index','应急队伍')">应急队伍：<span class="afont" id="dwsum"></span></a>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjwz/index','应急物资')">应急物资：<span class="afont" id="wzsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjzb/index','应急装备')">应急装备：<span class="afont" id="zbsum"></span></a>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjzj/index','应急专家')">应急专家：<span class="afont" id="zjsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/erm/yjyagl/index','应急预案')">应急预案：<span class="afont" id="yasum"></span></a>
                  </span>
				</div>
			</div>
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				隐患排查数据
			</div>
			<div class="ctt">
				<div id="chart5" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">

			</div>
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox6">
				<div class="tjicon"></div>
				<div class="tjtext">
					<div class="titlediv">危险作业报备（当日）</div>
					<br>
					<a href="#" style=" color:white" onclick="openPage('/dw/zysp/index','危险作业报备',2)">本厂：<span class="afont"></span></a><br>
					<a href="#" style=" color:white" onclick="openPage('/dw/zysp/index','危险作业报备',1)">外包：<span class="afont"></span></a>
				</div>
			</div>
>>>>>>> .r1981
		</div>
<<<<<<< .mine

		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox3">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-10px">
					<div class="titlediv">网格管理</div>
					<span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style="color:white" onclick="openDialogView('网格数','${ctx }/system/admin/xzqy','100%', '100%','');">网格数：<span class="afont" id="wgsum"></span></a><br>
                  <a href="#" style="color:white" onclick="openPage('/wghgl/xjjl/index','网格排查率')">网格排查率：<span class="afont" id="pcsum"></span></a><br>
                  </span>

||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				视频监控
			</div>
			<div class="ctt">
				<div id="chart6" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">

			</div>
		</div>
		<div class="chart_card">
			<div class="tit tit_1">
				受限空间
			</div>
			<div class="ctt pgrs" style="display:flex;justify-content: center;align-items: center;">
				<div style="">
					<div style="background: #03a9f4;">
						<span style="font-size:26px;font-weight:600">86</span>
					</div>
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox7">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">到期提醒</div>
					<span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style=" color:white" onclick="openPage('/bis/aqpxxx/index','安全培训')">安全培训：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/bis/tzsbxx/index','特种设备')">特种设备：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openPage('/aqjg/aqba/pjindex','安全评价报告')">安全评价报告：<span class="afont"></span></a></span><br>
					<a href="#" style=" color:white" onclick="openPage('/bis/occupharmreport/index','检测计划')">职业病危害检测：<span class="afont"></span></a></span>
>>>>>>> .r1981
				</div>
			</div>
		</div>
<<<<<<< .mine
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				监控化学品
			</div>
			<div class="ctt">
				<div id="chart8" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">
=======
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox8">
>>>>>>> .r1981

<<<<<<< .mine
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox3">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-15px">
					<div class="titlediv">文件发布</div>
					<span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style=" color:white" onclick="openDialogView('文件查看','${ctx }/issue/aqwjfb/index','100%', '100%','')">发文总数：<span class="afont" id="fwsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('1')">接收总数：<span class="afont" id="jssum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('2')">已读：<span class="afont" id="ydsum"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('3')">未读：<span class="afont" id="wdsum"></span></a><br>
                  </span>

||||||| .r1970
			</div>
		</div>
		<div class="chart_card">
			<div class="tit tit_1">
				涉爆粉尘
			</div>
			<div class="ctt pgrs" style="display:flex;justify-content: center;align-items: center;">
				<div style="">
					<div style="background: #ff7a50;width:91%;">
						<span style="font-size:26px;font-weight:600">125</span>
					</div>
=======
				<div class="titlediv" style="margin-left:-20px">风险管控</div>
				<div style="width :115%;">
					<table  style="width :100% ;margin-left: -20px;">
						<tr>
							<th></th>
							<th class="w15"><img class="imgsty" style="background-color: #FF5151;" /></th>
							<th class="w15"><img class="imgsty" style="background-color: #FFA042;" /></th>
							<th class="w20"><img class="imgsty" style="background-color: #F9F900;" /></th>
							<th class="w20"><img class="imgsty" style="background-color: #2894FF;" /></th>
						</tr>
						<tr>
							<td style="width:25%;font-size: 12px;text-align: center;">企业:</td>
							<th><a style="color:white" onclick="openPage('/fxgk/fxjg/index','企业风险等级',1)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxjg/index','企业风险等级',2)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxjg/index','企业风险等级',3)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxjg/index','企业风险等级',4)" class="afont tds">121</a></th>
						</tr>
						<tr>
							<td style="font-size: 12px;text-align: center;">风险点:</td>
							<th><a style="color:white" onclick="openPage('/fxgk/fxxx/index','风险点分级管控',1)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxxx/index','风险点分级管控',2)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxxx/index','风险点分级管控',3)" class="afont tds">121</a></th>
							<th><a style="color:white" onclick="openPage('/fxgk/fxxx/index','风险点分级管控',4)" class="afont tds">121</a></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-2-4  col-md-4 col-xs-6">
			<div class="tjbox9">
				<div class="tjicon"></div>
				<div class="tjtext" style="margin-top:-20px">
					<div class="titlediv">文件接收</div>
					<span style="font-size:12px;letter-spacing:-0.1em;height:4px">
                  <a href="#" style=" color:white" onclick="openDialogView('文件查看','${ctx }/issue/aqwjfb/index','100%', '100%','')">发文总数：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('1')">接收总数：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('2')">已读：<span class="afont"></span></a><br>
                  <a href="#" style=" color:white" onclick="openIssuePage('3')">未读：<span class="afont"></span></a><br>
>>>>>>> .r1981
				</div>
			</div>
		</div>
<<<<<<< .mine

		<div class="col-lg-2-4 col-md-4 col-xs-6">
			<div class="tjbox10">
				<div class="tjicon"></div>
				<div class="tjtext">
					<div class="titlediv">实时监控</div>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/cgindex','储罐报警')">储罐报警：<span class="afont" id="cgsum"></span></a><br>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/qtindex','浓度报警')">浓度报警：<span class="afont" id="ndsum"></span></a><br>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/gyindex','高危工艺报警')">高危工艺报警：<span class="afont" id="gwbjsum"></span></a></span>
				</div>
			</div>
||||||| .r1970
		<div class="chart_card">
			<div class="tit tit_1">
				涉氨涉氯
			</div>
			<div class="ctt">
				<div id="chart10" style="min-height: 150px;width:100%"></div>
			</div>
			<div class="tit tit_2">

			</div>
=======
		<div class="col-lg-2-4 col-md-4 col-xs-6">
			<div class="tjbox10">
				<div class="tjicon"></div>
				<div class="tjtext">
					<div class="titlediv">预警信息</div>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/cgindex','储罐报警')">储罐报警：<span class="afont"></span></a><br>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/qtindex','浓度报警')">浓度报警：<span class="afont"></span></a><br>
					<a href="#" style="color:white" onclick="openPage('/fmew/bj/gyindex','高危工艺报警')">高危工艺报警：<span class="afont"></span></a></span>
				</div>
			</div>
>>>>>>> .r1981
		</div>
	</div>

	<div class="col-lg-6 col-md-6 col-xs-12 bs-example mgt10">
		<div class="ajtitle">
			最新文件
			<a class="pull-right" onclick='openPage("/issue/aqwjfb/index/","安全文件管理")' href="#"> 更多+&nbsp;&nbsp;</a>
		</div>
		<div class="listnews">
			<ul class="clearpd" id="xdt" style="height:300px">
			</ul>
		</div>
	</div>
	<div class="col-lg-6 col-md-6 col-xs-12">
		<div class="bs-example mgt10">
			<div class="ajtitle">
				最新动态
				<a class="pull-right" onclick='openPage("/issue/aqscdt/index","安全生产动态")' href="#"> 更多+&nbsp;&nbsp;</a>
			</div>
			<div class="listnews">
				<ul class="clearpd" id="zxdt" style="height:300px">
				</ul>
			</div>
		</div>
	</div>

<<<<<<< .mine
	<div class="col-lg-12 col-md-12 col-xs-12 bs-example mgt10">
		<div class="bs-example mgt10">
			<div class="ajtitle">
				地图分布
				<a class="pull-right" href="#"></a>
			</div>
			<div class="mgt10" style="height:550px">
				<iframe frameborder=0 width=100% height=100% marginheight=0 marginwidth=0 scrolling=no src="${ctx}/bis/qyjbxx/onemap?type=1"></iframe>
			</div>
		</div>
	</div>
	<div class="col-lg-12 col-xs-12 bs-example mgt10">
		<div class="ajtitle">
			物料储量历史图
			<a class="pull-right" href="#"></a>
		</div>
		<div class="mgt10" style="height:400px">
||||||| .r1970
<script type="text/javascript">
    // echarts
    var chart1 = echarts.init(document.getElementById("chart1"),"vintage");
    var value = 354;
    chart1.setOption({
        tooltip: {
            formatter: "{a} <br/>{b} : {c}个"
        },
        title: {
            text: value+"",
            //text: `车辆总数`,
            subtext: '',
            left: 'center',
            top: 'center',//top待调整
            textStyle: {
                color: '#df6606',
                fontSize: 20,
                fontFamily: 'PingFangSC-Regular'
            },
            subtextStyle: {
                color: '#ff',
                fontSize: 14,
                fontFamily: 'PingFangSC-Regular',
                top: 'center'
            },
            itemGap: -1//主副标题间距
        },
=======
<script type="text/javascript">
    // echarts
    // var chart1 = echarts.init(document.getElementById("chart1"),"vintage");
    // var value = 354;
    // chart1.setOption({
    //     tooltip: {
    //         formatter: "{a} <br/>{b} : {c}个"
    //     },
    //     title: {
    //         text: value+"",
    //         //text: `车辆总数`,
    //         subtext: '',
    //         left: 'center',
    //         top: 'center',//top待调整
    //         textStyle: {
    //             color: '#df6606',
    //             fontSize: 20,
    //             fontFamily: 'PingFangSC-Regular'
    //         },
    //         subtextStyle: {
    //             color: '#ff',
    //             fontSize: 14,
    //             fontFamily: 'PingFangSC-Regular',
    //             top: 'center'
    //         },
    //         itemGap: -1//主副标题间距
    //     },
	//
    //     series: [{
    //         name: '企业接入数',
    //         type: 'pie',
    //         clockWise: true,
    //         radius: ['65%', '70%'],
    //         itemStyle: {
    //             normal: {
    //                 label: {
    //                     show: false
    //                 },
    //                 labelLine: {
    //                     show: false
    //                 }
    //             }
    //         },
    //         hoverAnimation: false,
    //         data: [{
    //             value: value,
    //             name: '已接入',
    //             itemStyle: {
    //                 normal: {
    //                     borderWidth: 8,
    //                     borderColor: {
    //                         colorStops: [{
    //                             offset: 0,
    //                             color: '#1d54f7' || '#00cefc' // 0% 处的颜色
    //                         }, {
    //                             offset: 1,
    //                             color: '#68eaf9' || '#367bec' // 100% 处的颜色
    //                         }]
    //                     },
    //                     color: { // 完成的圆环的颜色
    //                         colorStops: [{
    //                             offset: 0,
    //                             color: '#1d54f7' || '#00cefc' // 0% 处的颜色
    //                         }, {
    //                             offset: 1,
    //                             color: '#68eaf9' || '#367bec' // 100% 处的颜色
    //                         }]
    //                     },
    //                     label: {
    //                         show: false
    //                     },
    //                     labelLine: {
    //                         show: false
    //                     }
    //                 }
    //             }
    //         }, {
    //             name: 'gap',
    //             value: 500 - value,
    //             itemStyle: {
    //                 normal: {
    //                     label: {
    //                         show: false
    //                     },
    //                     labelLine: {
    //                         show: false
    //                     },
    //                     color: 'rgba(0, 0, 0, 0)',
    //                     borderColor: 'rgba(0, 0, 0, 0)',
    //                     borderWidth: 0
    //                 }
    //             }
    //         }]
    //     }]
    // })
	//
    // var chart2 = echarts.init(document.getElementById("chart2"), "vintage");
    // chart2.setOption({
    //     legend: {},
    //     tooltip: {},
    //     dataset: {
    //         source: [
    //             ['红', 31],
    //             ['橙', 90],
    //             ['黄', 144],
    //             ['蓝', 301]
    //         ]
    //     },
    //     grid: {
    //         left: '3%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     xAxis: {type: 'category'},
    //     yAxis: {},
    //     series: [{
    //         type: 'bar',
    //         itemStyle: {
    //             normal:{
    //                 color: function (params){
    //                     var colorList = ['#ff4844','#ffa042','#f9f900','#2894ff'];
    //                     return colorList[params.dataIndex];
    //                 }
    //             },
    //         }
    //     }]
    // })
	//
    // var chart3 = echarts.init(document.getElementById("chart3"), "vintage");
    // chart3.setOption({
    //     tooltip : {
    //         trigger: 'axis',
    //         axisPointer: {
    //             type: 'cross',
    //             label: {
    //                 backgroundColor: '#6a7985'
    //             }
    //         }
    //     },
    //     grid: {
    //         left: '0%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     xAxis : [
    //         {
    //             type : 'category',
    //             boundaryGap : false,
    //             data : ['周一','二','三','四']
    //         }
    //     ],
    //     yAxis : [
    //         {
    //             type : 'value',
    //             axisLine: {
    //                 lineStyle: {
    //                     color: '#c1c1c1'
    //                 }
    //             },
    //             axisLabel: {
    //                 color: '#333',
    //                 fontSize: 10,
    //                 formatter: function(value,index){
    //                     var value;
    //                     if (value >=1000) {
    //                         value = value/1000+'k';
    //                     }else if(value <1000){
    //                         value = value;
    //                     }
    //                     return value
    //                 }
    //             },
    //             splitLine: {
    //                 show:false,
    //                 lineStyle: {
    //                     color: '#fff'
    //                 }
    //             },
    //             interval:1000
    //         }
    //     ],
    //     series : [
    //         {
    //             name:'一级危险源',
    //             type:'line',
    //             stack: '总量',
    //             areaStyle: {},
    //             data:[30, 92, 61, 35]
    //         },
    //         {
    //             name:'二级危险源',
    //             type:'line',
    //             stack: '总量',
    //             areaStyle: {},
    //             data:[84, 152, 201, 101]
    //         },
    //         {
    //             name:'三级危险源',
    //             type:'line',
    //             stack: '总量',
    //             areaStyle: {normal: {}},
    //             data:[198, 281, 321, 201]
    //         },
    //         {
    //             name:'四级危险源',
    //             type:'line',
    //             stack: '总量',
    //             label: {
    //                 normal: {
    //                     show: true,
    //                     position: 'top'
    //                 }
    //             },
    //             areaStyle: {normal: {}},
    //             data:[457, 386, 472, 387]
    //         }
    //     ]
    // })
	//
    // var chart4 = echarts.init(document.getElementById("chart4"), "vintage");
    // chart4.setOption({
    //     tooltip: {
    //         trigger: 'item',
    //         formatter: "{a} <br/>{b}: {c} ({d}%)"
    //     },
    //     grid: {
    //         left: '0%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     series: [
    //         {
    //             name:'访问来源',
    //             type:'pie',
    //             radius: ['60%', '80%'],
    //             avoidLabelOverlap: false,
    //             label: {
    //                 normal: {
    //                     show: false,
    //                     position: 'center'
    //                 },
    //                 emphasis: {
    //                     show: true,
    //                     textStyle: {
    //                         fontSize: '16',
    //                         fontWeight: 'bold'
    //                     }
    //                 }
    //             },
    //             labelLine: {
    //                 normal: {
    //                     show: false
    //                 }
    //             },
    //             data:[
    //                 {value:335, name:'锅炉'},
    //                 {value:310, name:'压力容器'},
    //                 {value:234, name:'压力管道'},
    //                 {value:135, name:'电梯'},
    //                 {value:335, name:'起重机械'},
    //                 {value:310, name:'客运索道'},
    //                 {value:234, name:'大型游乐设施'},
    //                 {value:135, name:'场（厂）内专用机动车辆'},
    //                 {value:1548, name:'其他设备'}
    //             ],
    //             itemStyle: {
    //                 emphasis: {
    //                     shadowBlur: 10,
    //                     shadowOffsetX: 0,
    //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
    //                 },
    //                 normal:{
    //                     color:function(params) {
    //                         //自定义颜色
    //                         var colorList = [
    //                             '#2299eb', '#00FF00', '#e4d33f', '#FF8C00', '#FF0000', '#9C27B0','#90bcc2', '#6d7c87', '#f56395'
    //                         ];
    //                         return colorList[params.dataIndex]
    //                     }
    //                 }
    //             }
    //         }
    //     ]
    // })
	//
    // // 默认高亮
    // let index = 0; // 高亮索引
    // chart4.dispatchAction({
    //     type: "highlight",
    //     seriesIndex: index,
    //     dataIndex: index
    // });
    // chart4.on("mouseover", function(e) {
    //     if (e.dataIndex != index) {
    //         chart4.dispatchAction({
    //             type: "downplay",
    //             seriesIndex: 0,
    //             dataIndex: index
    //         });
    //     }
    // });
    // chart4.on("mouseout", function(e) {
    //     index = e.dataIndex;
    //     chart4.dispatchAction({
    //         type: "highlight",
    //         seriesIndex: 0,
    //         dataIndex: e.dataIndex
    //     });
    // });
	//
	//
    // var chart5 = echarts.init(document.getElementById("chart5"), "vintage");
    // chart5.setOption({
    //     tooltip : {
    //         trigger: 'axis',
    //         axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    //             type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    //         }
    //     },
    //     grid: {
    //         left: '0%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     xAxis:  {
    //         type: 'value',
    //         axisLabel: {
    //             color: '#333',
    //             fontSize: 10,
    //             formatter: function(value,index){
    //                 var value;
    //                 if (value >=1000) {
    //                     value = value/1000+'k';
    //                 }else if(value <1000){
    //                     value = value;
    //                 }
    //                 return value
    //             }
    //         }
    //     },
    //     yAxis: {
    //         type: 'category',
    //         data: ['一','二','三','周四']
    //     },
    //     series: [
    //         {
    //             name: '未整改',
    //             type: 'bar',
    //             stack: '总量',
    //             label: {
    //                 normal: {
    //                     show: false,
    //                     position: 'insideRight'
    //                 }
    //             },
    //             data: [19, 21, 0, 35],
    //             itemStyle: {normal:{color:'#ff0000'}}
    //         },
    //         {
    //             name: '已整改',
    //             type: 'bar',
    //             stack: '总量',
    //             label: {
    //                 normal: {
    //                     show: false,
    //                     position: 'insideRight'
    //                 }
    //             },
    //             data: [60, 81, 49, 74],
    //             itemStyle: {normal:{color:'#4CAF50'}}
    //         },
    //         {
    //             name: '隐患总数',
    //             type: 'bar',
    //             stack: '总量',
    //             label: {
    //                 normal: {
    //                     show: true,
    //                     position: 'insideRight'
    //                 }
    //             },
    //             data: [79, 102, 49, 109],
    //             itemStyle: {normal:{color:'#d2d2d2'}}
    //         }
    //     ]
    // })
	//
    // var chart6 = echarts.init(document.getElementById("chart6"),"vintage");
    // var value2 = 124;
    // chart6.setOption({
    //     tooltip: {
    //         formatter: "{a} <br/>{b} : {c}个"
    //     },
    //     title: {
    //         text: value2+"",
    //         subtext: '',
    //         left: 'center',
    //         top: 'center',//top待调整
    //         textStyle: {
    //             color: '#2894ff',
    //             fontSize: 20,
    //             fontFamily: 'PingFangSC-Regular'
    //         },
    //         subtextStyle: {
    //             color: '#ff',
    //             fontSize: 14,
    //             fontFamily: 'PingFangSC-Regular',
    //             top: 'center'
    //         },
    //         itemGap: -1//主副标题间距
    //     },
	//
    //     series: [{
    //         name: '监控接入数',
    //         type: 'pie',
    //         clockWise: true,
    //         radius: ['65%', '70%'],
    //         itemStyle: {
    //             normal: {
    //                 label: {
    //                     show: false
    //                 },
    //                 labelLine: {
    //                     show: false
    //                 }
    //             }
    //         },
    //         hoverAnimation: false,
    //         data: [{
    //             value: value2,
    //             name: '已接入',
    //             itemStyle: {
    //                 normal: {
    //                     borderWidth: 8,
    //                     borderColor: {
    //                         colorStops: [{
    //                             offset: 0,
    //                             color: '#eb3600' || '#cc9a00' // 0% 处的颜色
    //                         }, {
    //                             offset: 1,
    //                             color: '#d0a00e' || '#d0570e' // 100% 处的颜色
    //                         }]
    //                     },
    //                     color: { // 完成的圆环的颜色
    //                         colorStops: [{
    //                             offset: 0,
    //                             color: '#eb3600' || '#cc9a00' // 0% 处的颜色
    //                         }, {
    //                             offset: 1,
    //                             color: '#d0a00e' || '#d0570e' // 100% 处的颜色
    //                         }]
    //                     },
    //                     label: {
    //                         show: false
    //                     },
    //                     labelLine: {
    //                         show: false
    //                     }
    //                 }
    //             }
    //         }, {
    //             name: 'gap',
    //             value: 150 - value2,
    //             itemStyle: {
    //                 normal: {
    //                     label: {
    //                         show: false
    //                     },
    //                     labelLine: {
    //                         show: false
    //                     },
    //                     color: 'rgba(0, 0, 0, 0)',
    //                     borderColor: 'rgba(0, 0, 0, 0)',
    //                     borderWidth: 0
    //                 }
    //             }
    //         }]
    //     }]
    // })
	//
    // var chart8 = echarts.init(document.getElementById("chart8"), "vintage");
    // chart8.setOption({
    //     tooltip: {
    //         trigger: 'item',
    //         formatter: "{a} <br/>{b}: {c} ({d}%)"
    //     },
    //     grid: {
    //         left: '0%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     series: [
    //         {
    //             name:'访问来源',
    //             type:'pie',
    //             radius: ['60%', '80%'],
    //             avoidLabelOverlap: false,
    //             label: {
    //                 normal: {
    //                     show: false,
    //                     position: 'center'
    //                 },
    //                 emphasis: {
    //                     show: true,
    //                     textStyle: {
    //                         fontSize: '16',
    //                         fontWeight: 'bold'
    //                     }
    //                 }
    //             },
    //             labelLine: {
    //                 normal: {
    //                     show: false
    //                 }
    //             },
    //             data:[
    //                 {value:235, name:'产品'},
    //                 {value:610, name:'原料'}
    //             ],
    //             itemStyle: {
    //                 emphasis: {
    //                     shadowBlur: 10,
    //                     shadowOffsetX: 0,
    //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
    //                 },
    //                 normal:{
    //                     color:function(params) {
    //                         //自定义颜色
    //                         var colorList = [
    //                             '#b1d7c6', '#6d7c87', '#FF0000', 'orange', '#2894ff', '#FE8463',
    //                         ];
    //                         return colorList[params.dataIndex]
    //                     }
    //                 }
    //             }
    //         }
    //     ]
    // })
	//
    // // 默认高亮
    // let index2 = 0; // 高亮索引
    // chart8.dispatchAction({
    //     type: "highlight",
    //     seriesIndex: index2,
    //     dataIndex: index2
    // });
    // chart8.on("mouseover", function(e) {
    //     if (e.dataIndex != index2) {
    //         chart8.dispatchAction({
    //             type: "downplay",
    //             seriesIndex: 0,
    //             dataIndex: index2
    //         });
    //     }
    // });
    // chart8.on("mouseout", function(e) {
    //     index2 = e.dataIndex;
    //     chart8.dispatchAction({
    //         type: "highlight",
    //         seriesIndex: 0,
    //         dataIndex: e.dataIndex
    //     });
    // });
	//
	//
    // var chart10 = echarts.init(document.getElementById("chart10"), "vintage");
    // chart10.setOption({
    //     legend: {},
    //     tooltip: {},
    //     dataset: {
    //         source: [
    //             ['氨', 43.3],
    //             ['氯', 83.1]
    //         ]
    //     },
    //     grid: {
    //         left: '3%',
    //         right: '5%',
    //         top: '3%',
    //         bottom: '3%',
    //         containLabel: true
    //     },
    //     xAxis: {type: 'category'},
    //     yAxis: {},
    //     series: [{
    //         type: 'bar',
    //         itemStyle: {
    //             normal:{
    //                 color: function (params){
    //                     var colorList = ['#ff4844','#2894ff'];
    //                     return colorList[params.dataIndex];
    //                 }
    //             },
    //         }
    //     }]
    // })
>>>>>>> .r1981

<<<<<<< .mine
			<div class="ibox-content" style="height:400px">
				<div id="main" style="height:400px"></div>
			</div>
		</div>
	</div>
</div>
<script>
	var mappoint='${mappoint}';
	var forbidscrollflg='${forbidScroll}';
	var Gsys = true ;
	var IssueType;
	$(function(){
		//调用天气插件
		$.getScript('http://ip.ws.126.net/ipquery',function(){
			var citytq=localAddress.city;
			$.ajax({
				type:'get',
				url:url = "http://wthrcdn.etouch.cn/weather_mini?city=" + citytq,
				scriptCharset: "gbk",
				dataType:'json',
				success: function(data){
					var _w0 = data.data.forecast;
					var date= new Date();
					var html="";
					var r = /\[.+\[(.+?)\]/;
					for(var i=0;i<_w0.length;i++){
						html+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+"星期"+"日一二三四五六".charAt(date.getDay());
						if(i==0){
							html+=" 今日天气：";
						}
						else if (i==1)
							html+= " 明天天气: ";
						else
							html+=" "+"天气: ";
						html+=_w0[i].type+"; ";
						html+=_w0[i].high+"~"+_w0[i].low+";&nbsp"+_w0[i].fengxiang+_w0[i].fengli.match(r)[1]+";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						date.setDate(date.getDate()+1);
					}
					$("#weather").html(html);
					$('.weather').liMarquee({});
				}
			});
		});
	});
	$.ajax({
		url : ctx + '/syshome/zxdt',
		type : "POST",
		success : function(data) {
			//$("#xdt").append(data);
			var html="";
			for (var i = 0; i < data.length; i++) {
				var date=new Date(data[i].s1);
				html+= "<li><a onclick=\"opendt("+data[i].id+")\" href=\"#\" >"+(data[i].m1.length>25?data[i].m1.substring(0,30)+"...":data[i].m1)
						+ "</a><span class=\"pull-right\">"+(date.getFullYear()+'-'
								+((date.getMonth() + 1) < 10 ? ('0' + (date.getMonth()+ 1)) :(date.getMonth()+ 1))
								+'-' + (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()))+"</span></li>";
			}
			$("#zxdt").append(html);
		}
	});
	$.ajax({
		url : ctx + '/syshome/issue',
		type : "POST",
		success : function(data) {
			//$("#xdt").append(data);
			var html="";
			for (var i = 0; i < data.length; i++) {
				var date=new Date(data[i].S1);
				html+= "<li><a onclick=\"openwj("+data[i].ID+")\" href=\"#\" >"+ (data[i].M1.length>25?data[i].M1.substring(0,30)+"...":data[i].M1)
						+ "</a><span  class=\"pull-right\">"+(date.getFullYear()+'-'
								+((date.getMonth() + 1) < 10 ? ('0' + (date.getMonth()+ 1)) :(date.getMonth()+ 1))
								+'-' + (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate()))+"</span></li>";
			}
			$("#xdt").append(html);
		}
	});
	//报警信息
	/* $.ajax({
        url : ctx + '/syshome/bjxx',
||||||| .r1970
        series: [{
            name: '企业接入数',
            type: 'pie',
            clockWise: true,
            radius: ['65%', '70%'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            hoverAnimation: false,
            data: [{
                value: value,
                name: '已接入',
                itemStyle: {
                    normal: {
                        borderWidth: 8,
                        borderColor: {
                            colorStops: [{
                                offset: 0,
                                color: '#1d54f7' || '#00cefc' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#68eaf9' || '#367bec' // 100% 处的颜色
                            }]
                        },
                        color: { // 完成的圆环的颜色
                            colorStops: [{
                                offset: 0,
                                color: '#1d54f7' || '#00cefc' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#68eaf9' || '#367bec' // 100% 处的颜色
                            }]
                        },
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                }
            }, {
                name: 'gap',
                value: 500 - value,
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                        color: 'rgba(0, 0, 0, 0)',
                        borderColor: 'rgba(0, 0, 0, 0)',
                        borderWidth: 0
                    }
                }
            }]
        }]
    })

    var chart2 = echarts.init(document.getElementById("chart2"), "vintage");
    chart2.setOption({
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['红', 31],
                ['橙', 90],
                ['黄', 144],
                ['蓝', 301]
            ]
        },
        grid: {
            left: '3%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{
            type: 'bar',
            itemStyle: {
                normal:{
                    color: function (params){
                        var colorList = ['#ff4844','#ffa042','#f9f900','#2894ff'];
                        return colorList[params.dataIndex];
                    }
                },
            }
        }]
    })

    var chart3 = echarts.init(document.getElementById("chart3"), "vintage");
    chart3.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        grid: {
            left: '0%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','二','三','四']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLine: {
                    lineStyle: {
                        color: '#c1c1c1'
                    }
                },
                axisLabel: {
                    color: '#333',
                    fontSize: 10,
                    formatter: function(value,index){
                        var value;
                        if (value >=1000) {
                            value = value/1000+'k';
                        }else if(value <1000){
                            value = value;
                        }
                        return value
                    }
                },
                splitLine: {
                    show:false,
                    lineStyle: {
                        color: '#fff'
                    }
                },
                interval:1000
            }
        ],
        series : [
            {
                name:'一级危险源',
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:[30, 92, 61, 35]
            },
            {
                name:'二级危险源',
                type:'line',
                stack: '总量',
                areaStyle: {},
                data:[84, 152, 201, 101]
            },
            {
                name:'三级危险源',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[198, 281, 321, 201]
            },
            {
                name:'四级危险源',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[457, 386, 472, 387]
            }
        ]
    })

    var chart4 = echarts.init(document.getElementById("chart4"), "vintage");
    chart4.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        grid: {
            left: '0%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['60%', '80%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:335, name:'锅炉'},
                    {value:310, name:'压力容器'},
                    {value:234, name:'压力管道'},
                    {value:135, name:'电梯'},
                    {value:335, name:'起重机械'},
                    {value:310, name:'客运索道'},
                    {value:234, name:'大型游乐设施'},
                    {value:135, name:'场（厂）内专用机动车辆'},
                    {value:1548, name:'其他设备'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    normal:{
                        color:function(params) {
                            //自定义颜色
                            var colorList = [
                                '#2299eb', '#00FF00', '#e4d33f', '#FF8C00', '#FF0000', '#9C27B0','#90bcc2', '#6d7c87', '#f56395'
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                }
            }
        ]
    })

    // 默认高亮
    let index = 0; // 高亮索引
    chart4.dispatchAction({
        type: "highlight",
        seriesIndex: index,
        dataIndex: index
    });
    chart4.on("mouseover", function(e) {
        if (e.dataIndex != index) {
            chart4.dispatchAction({
                type: "downplay",
                seriesIndex: 0,
                dataIndex: index
            });
        }
    });
    chart4.on("mouseout", function(e) {
        index = e.dataIndex;
        chart4.dispatchAction({
            type: "highlight",
            seriesIndex: 0,
            dataIndex: e.dataIndex
        });
    });


    var chart5 = echarts.init(document.getElementById("chart5"), "vintage");
    chart5.setOption({
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '0%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value',
            axisLabel: {
                color: '#333',
                fontSize: 10,
                formatter: function(value,index){
                    var value;
                    if (value >=1000) {
                        value = value/1000+'k';
                    }else if(value <1000){
                        value = value;
                    }
                    return value
                }
            }
        },
        yAxis: {
            type: 'category',
            data: ['一','二','三','周四']
        },
        series: [
            {
                name: '未整改',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: [19, 21, 0, 35],
                itemStyle: {normal:{color:'#ff0000'}}
            },
            {
                name: '已整改',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: false,
                        position: 'insideRight'
                    }
                },
                data: [60, 81, 49, 74],
                itemStyle: {normal:{color:'#4CAF50'}}
            },
            {
                name: '隐患总数',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                data: [79, 102, 49, 109],
                itemStyle: {normal:{color:'#d2d2d2'}}
            }
        ]
    })

    var chart6 = echarts.init(document.getElementById("chart6"),"vintage");
    var value2 = 124;
    chart6.setOption({
        tooltip: {
            formatter: "{a} <br/>{b} : {c}个"
        },
        title: {
            text: value2+"",
            subtext: '',
            left: 'center',
            top: 'center',//top待调整
            textStyle: {
                color: '#2894ff',
                fontSize: 20,
                fontFamily: 'PingFangSC-Regular'
            },
            subtextStyle: {
                color: '#ff',
                fontSize: 14,
                fontFamily: 'PingFangSC-Regular',
                top: 'center'
            },
            itemGap: -1//主副标题间距
        },

        series: [{
            name: '监控接入数',
            type: 'pie',
            clockWise: true,
            radius: ['65%', '70%'],
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            hoverAnimation: false,
            data: [{
                value: value2,
                name: '已接入',
                itemStyle: {
                    normal: {
                        borderWidth: 8,
                        borderColor: {
                            colorStops: [{
                                offset: 0,
                                color: '#eb3600' || '#cc9a00' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#d0a00e' || '#d0570e' // 100% 处的颜色
                            }]
                        },
                        color: { // 完成的圆环的颜色
                            colorStops: [{
                                offset: 0,
                                color: '#eb3600' || '#cc9a00' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#d0a00e' || '#d0570e' // 100% 处的颜色
                            }]
                        },
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    }
                }
            }, {
                name: 'gap',
                value: 150 - value2,
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                        color: 'rgba(0, 0, 0, 0)',
                        borderColor: 'rgba(0, 0, 0, 0)',
                        borderWidth: 0
                    }
                }
            }]
        }]
    })

    var chart8 = echarts.init(document.getElementById("chart8"), "vintage");
    chart8.setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        grid: {
            left: '0%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        series: [
            {
                name:'访问来源',
                type:'pie',
                radius: ['60%', '80%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:235, name:'产品'},
                    {value:610, name:'原料'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    normal:{
                        color:function(params) {
                            //自定义颜色
                            var colorList = [
                                '#b1d7c6', '#6d7c87', '#FF0000', 'orange', '#2894ff', '#FE8463',
                            ];
                            return colorList[params.dataIndex]
                        }
                    }
                }
            }
        ]
    })

    // 默认高亮
    let index2 = 0; // 高亮索引
    chart8.dispatchAction({
        type: "highlight",
        seriesIndex: index2,
        dataIndex: index2
    });
    chart8.on("mouseover", function(e) {
        if (e.dataIndex != index2) {
            chart8.dispatchAction({
                type: "downplay",
                seriesIndex: 0,
                dataIndex: index2
            });
        }
    });
    chart8.on("mouseout", function(e) {
        index2 = e.dataIndex;
        chart8.dispatchAction({
            type: "highlight",
            seriesIndex: 0,
            dataIndex: e.dataIndex
        });
    });


    var chart10 = echarts.init(document.getElementById("chart10"), "vintage");
    chart10.setOption({
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['氨', 43.3],
                ['氯', 83.1]
            ]
        },
        grid: {
            left: '3%',
            right: '5%',
            top: '3%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {type: 'category'},
        yAxis: {},
        series: [{
            type: 'bar',
            itemStyle: {
                normal:{
                    color: function (params){
                        var colorList = ['#ff4844','#2894ff'];
                        return colorList[params.dataIndex];
                    }
                },
            }
        }]
    })

    var Gsys = true ;
    var IssueType;
    $(function(){
        //调用天气插件
        $.getScript('http://ip.ws.126.net/ipquery',function(){
            var citytq=localAddress.city;
            $.ajax({
                type:'get',
                url:url = "http://wthrcdn.etouch.cn/weather_mini?city=" + citytq,
                scriptCharset: "gbk",
                dataType:'json',
                success: function(data){
                    var _w0 = data.data.forecast;
                    var date= new Date();
                    var html="";
                    var r = /\[.+\[(.+?)\]/;
                    for(var i=0;i<_w0.length;i++){
                        html+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+"星期"+"日一二三四五六".charAt(date.getDay());
                        if(i==0){
                            html+=" 今日天气：";
                        }
                        else if (i==1)
                            html+= " 明天天气: ";
                        else
                            html+=" "+"天气: ";
                        html+=_w0[i].type+"; ";
                        html+=_w0[i].high+"~"+_w0[i].low+";&nbsp"+_w0[i].fengxiang+_w0[i].fengli.match(r)[1]+";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        date.setDate(date.getDate()+1);
                    }
                    $("#weather").html(html);
                    $('.weather').liMarquee({});
                }
            });
        });
    });
    $.ajax({
        url : ctx + '/syshome/zxdt',
=======
    var Gsys = true ;
    var IssueType;
    $(function(){
        //调用天气插件
        $.getScript('http://ip.ws.126.net/ipquery',function(){
            var citytq=localAddress.city;
            $.ajax({
                type:'get',
                url:url = "http://wthrcdn.etouch.cn/weather_mini?city=" + citytq,
                scriptCharset: "gbk",
                dataType:'json',
                success: function(data){
                    var _w0 = data.data.forecast;
                    var date= new Date();
                    var html="";
                    var r = /\[.+\[(.+?)\]/;
                    for(var i=0;i<_w0.length;i++){
                        html+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+"星期"+"日一二三四五六".charAt(date.getDay());
                        if(i==0){
                            html+=" 今日天气：";
                        }
                        else if (i==1)
                            html+= " 明天天气: ";
                        else
                            html+=" "+"天气: ";
                        html+=_w0[i].type+"; ";
                        html+=_w0[i].high+"~"+_w0[i].low+";&nbsp"+_w0[i].fengxiang+_w0[i].fengli.match(r)[1]+";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        date.setDate(date.getDate()+1);
                    }
                    $("#weather").html(html);
                    $('.weather').liMarquee({});
                }
            });
        });
    });
    $.ajax({
        url : ctx + '/syshome/zxdt',
>>>>>>> .r1981
        type : "POST",
        success : function(data) {
            //$("#xdt").append(data);
            var html="";
            for(var i=0;i<data.length;i++){
                var arry=data[i].situation.split(";");
                var bjname=arry[arry.length-1];
               if(data[i].type=='1')
                 html+="<li><span class=\"label label-info\">储罐</span><a onclick='openPage(\"\/fmew\/bj\/cgindex\",\"储罐报警数据\")\'>";
                else if(data[i].type=='2')
                 html+="<li><span class=\"label label-warning\">浓度</span><a onclick='openPage(\"\/fmew\/bj\/qtindex\",\"浓度报警数据\")\'>";
                 else
                 html+="<li><span class=\"label label-danger\">高危工艺</span><a onclick='openPage(\"\/fmew\/bj\/gyindex\",\"高危工艺报警数据\")\'>";
                 html+=(new Date(data[i].colltime)).format("yyyy-MM-dd hh:mm")+"  "+data[i].qyname+":"+bjname+"</a></li>";
            }
               $("#bjxx").append(html);
               $('.dowebok').liMarquee({direction: 'up',scrollamount: 40});
        }
    }); */
	$.ajax({
		url : ctx + '/syshome/sjzs',
		type : "POST",
		success : function(data) {
			var tjdata=eval(data);
			console.log(tjdata);
			for (var key in tjdata) {
				for ( var i in tjdata[key] ){
					$("#"+i).text(tjdata[key][i]);
				}
			}
			/*		var sj=eval(data);
                    var afont= $(".afont");
                    for(var i=0;i<afont.length;i++){
                        afont[i].innerText=sj[i];
                    }*/
		}
	});

	function openwj(id){
		window.open(ctx + "/issue/aqwjfb/view/" +id, "信息查看");
	}
	function opendt(id){
		window.open(ctx+"/issue/aqscdt/view/"+id,"生产动态信息查看");
	}
	function openPage(url,title,mflag){
		url='${ctx}'+url+"?sys=1";
		if(mflag!=null&&mflag!='')
			url+="&mflag="+mflag;
		layer.open({
			type: 2,
			area: ['100%', '100%'],
			title: title,
			maxmin: true,
			content: url,
			btn: ['关闭'],
			yes: function(index, layero){
				layer.close(index);
			},
			cancel: function(index){}
		});
	}

	function openPage2(url,title,mflag){
		url='${ctx}'+url+"&sys=1";
		if(mflag!=null&&mflag!='')
			url+="&mflag="+mflag;
		layer.open({
			type: 2,
			area: ['100%', '100%'],
			title: title,
			maxmin: true,
			content: url,
			btn: ['关闭'],
			yes: function(index, layero){
				layer.close(index);
			},
			cancel: function(index){}
		});
	}
	function openIssuePage(type){
		IssueType = type;
		layer.open({
			type: 2,
			area: ['100%', '100%'],
			title: '文件查看',
			maxmin: true,
			content: '${ctx}/issue/zfwjfb/index',
			btn: ['关闭'],
			yes: function(index, layero){
				layer.close(index);
			},
			cancel: function(index){}
		});
	}

	// 路径配置
	require.config({
		paths : {
			echarts : '${ctxStatic}/echarts'
		}
	});


<<<<<<< .mine
	var y = [];//y轴
	var legend = [];//legend值
	var x = [];//x轴
	$(function(){
		$.ajax({
			url : ctx + "/zxjkyj/dsjfx/getlinejson",
			type : "POST",
			dataType : "json",
			success : function(data) {
				y = [];//y轴
				legend = [];//legend值
				x = [];//x轴
				x = data.date;
				for (key in data) {
					if (key != "date") {
						y.push({
							"name" : key,
							"type" : "line",
							"data" : data[key]
						});
						legend.push(key);
					}
				}
				loadEcharts();
			}
		});
	});
||||||| .r1970
    var y = [];//y轴
    var legend = [];//legend值
    var x = [];//x轴
    $(function(){
        chart1.resize();
=======
    var y = [];//y轴
    var legend = [];//legend值
    var x = [];//x轴
    $(function(){
        // chart1.resize();
>>>>>>> .r1981

	function loadEcharts() {
		require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], function(ec) {
			var myChart = ec.init(document.getElementById('main'));
			myChart.showLoading();
			myChart.hideLoading();
			myChart.setOption({

<<<<<<< .mine
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : legend
				},
				toolbox : {
					show : true,
					feature : {
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : x
				} ],
				yAxis : [ {
					type : 'value',
					'name':'m³',
				} ],
				series : y
			});

		});
	}
||||||| .r1970
    window.onresize = function () {
        chart1.resize();
        chart2.resize();
        chart3.resize();
        chart4.resize();
        chart5.resize();
        chart6.resize();
        chart8.resize();
        chart10.resize();
    }
=======
    // window.onresize = function () {
    //     chart1.resize();
    //     chart2.resize();
    //     chart3.resize();
    //     chart4.resize();
    //     chart5.resize();
    //     chart6.resize();
    //     chart8.resize();
    //     chart10.resize();
    // }
>>>>>>> .r1981
</script>
</body>
</html>