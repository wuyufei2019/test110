<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>模块数据完整性确认</title>
<meta name="decorator" content="default" />

<style type="text/css">
.thridmenu {
	text-align: center;
	float: left;
	display: none;
}

.fright {
	float: left;
	margin: 20px 20px;
}

.checksty {
	width: 15px;
	height: 15px;
}
.bg{
background-color: #dff3fe;
}
</style>

</head>
<body>
   <div class="easyui-panel" style="width:100%;height:100%;">
      <div class="easyui-layout" data-options="fit:true">
         <div collapsible="true" title="选择企业" data-options="region:'west'" style="width:300px;height:100%">
            <div class="panel">
               <ul class="list-group teammates" id="qylist">
               </ul>
            </div>
         </div>
         <div data-options="region:'center'" style="width:100%;height:100%" id="nodata" title="请确认无数据模块并勾选相应模块">
            <div class="thridmenu">
               <form id="inputForm">
               <div id="cj" class="fright">
                  <img alt="车间信息" title="车间信息" src="${ctx}/static/model/main/images/yqyd-cjxx.png">
                  <div>车间信息</div>
                  <input type="checkbox" name="bis_workshop" class="checksty"  >
               </div>
               <div id="wl" class="fright">
                  <img alt="物料信息" title="物料信息" src="${ctx}/static/model/main/images/yqyd-wlxx.png">
                  <div>物料信息</div>
                  <input name="bis_mat" type="checkbox"  class="checksty"  >
               </div>
               <div id="ck" class="fright">
                  <img alt="仓库信息" title="仓库信息" src="${ctx}/static/model/main/images/yqyd-ckxx.png">
                  <div>仓库信息</div>
                  <input name="bis_storage" type="checkbox" class="checksty"  >
               </div>
               <div id="cg" class="fright">
                  <img alt="储罐信息" title="储罐信息" src="${ctx}/static/model/main/images/yqyd-cgxx.png">
                  <div>储罐信息</div>
                  <input name="bis_tank" type="checkbox" class="checksty"  >
               </div>
               <div id="yj" class="fright">
                  <img alt="冶金信息" title="冶金信息" src="${ctx}/static/model/main/images/yqyd-yjxx.png">
                  <div>冶金信息</div>
                  <input name="bis_metallurgy" type="checkbox" class="checksty"  >
               </div>
               <div id="fc" class="fright">
                  <img alt="粉尘信息" title="粉尘信息" src="${ctx}/static/model/main/images/yqyd-fcxx.png">
                  <div>粉尘信息</div>
                  <input name="bis_dust" type="checkbox" class="checksty"  >
               </div>
               <div id="sxkj" class="fright">
                  <img alt="受限空间" title="受限空间" src="${ctx}/static/model/main/images/yqyd-sxkj.png">
                  <div>受限空间</div>
                  <input name="bis_confinedspace" type="checkbox" class="checksty"  >
               </div>
               <div id="zybc" class="fright">
                  <img alt="作业班次" title="作业班次" src="${ctx}/static/model/main/images/yqyd-zybc.png">
                  <div>作业班次</div>
                  <input name="bis_workorder" type="checkbox" class="checksty"  >
               </div>
               <div id="tzsb" class="fright">
                  <img alt="特种设备信息" title="特种设备信息" src="${ctx}/static/model/main/images/yqyd-tzsb.png">
                  <div>特种设备信息</div>
                  <input name="bis_specialequipment" type="checkbox" class="checksty"  >
               </div>
               <div id="yg" class="fright">
                  <img alt="员工信息" title="员工信息" src="${ctx}/static/model/main/images/yqyd-ygxx.png">
                  <div>员工信息</div>
                  <input name="bis_employee" type="checkbox" class="checksty"  >
               </div>
               <div id="aqpx" class="fright">
                  <img alt="安全培训信息" title="安全培训信息" src="${ctx}/static/model/main/images/yqyd-aqpx.png">
                  <div>安全培训信息</div>
                  <input name="bis_safetyeducation" type="checkbox" class="checksty"  >
               </div>
               <div id="scsb" class="fright">
                  <img alt="生产设备信息" title="生产设备信息" src="${ctx}/static/model/main/images/yqyd-scsb.png">
                  <div>生产设备信息</div>
                  <input name="bis_productionfacility" type="checkbox" class="checksty"  >
               </div>
               <div id="gygc" class="fright">
                  <img alt="公用工程信息" title="公用工程信息" src="${ctx}/static/model/main/images/aqwjfb-aqwjgl.png">
                  <div>公用工程信息</div>
                  <input name="bis_utilities" type="checkbox" class="checksty"  >
               </div>
               <div id="aqss" class="fright">
                  <img alt="安全设施" title="安全设施" src="${ctx}/static/model/main/images/yqyd-aqss.png">
                  <div>安全设施</div>
                  <input name="bis_safetyfacilities" type="checkbox" class="checksty"  >
               </div>
               <div id="cbs" class="fright">
                  <img alt="承包商信息" title="承包商信息" src="${ctx}/static/model/main/images/yqyd-cbsxx.png">
                  <div>承包商信息</div>
                  <input name="bis_contractor" type="checkbox" class="checksty"  >
               </div>
               <div id="ny" class="fright">
                  <img alt="化学能源信息" title="化学能源信息" src="${ctx}/static/model/main/images/yqyd-hxny.png">
                  <div>化学能源信息</div>
                  <input name="bis_chemical_Energy_Source" type="checkbox" class="checksty"  >
               </div>
               <div id="zyb" class="fright">
                  <img alt="职业病危害因素" title="职业病危害因素" src="${ctx}/static/model/main/images/yqyd-zyb.png">
                  <div>职业病危害因素</div>
                  <input name="bis_occupharmexam" type="checkbox" class="checksty"  >
               </div>
               <div id="jcbg" class="fright">
                  <img alt="检测报告管理" title="检测报告管理" src="${ctx}/static/model/main/images/yqyd-jcbggl.png">
                  <div>检测报告管理</div>
                  <input name="bis_occupharmexamreport" type="checkbox" class="checksty"  >
               </div>
               <div id="zybtj" class="fright">
                  <img alt="职业病体检" title="职业病体检" src="${ctx}/static/model/main/images/yqyd-zybtj.png">
                  <div>职业病体检</div>
                  <input name="bis_occupillexam" type="checkbox" class="checksty"  >
               </div>
               <div id="ygtj" class="fright">
                  <img alt="员工体检信息" title="员工体检信息" src="${ctx}/static/model/main/images/yqyd-ygtjxx.png">
                  <div>员工体检信息</div>
                  <input name="bis_employeetest" type="checkbox" class="checksty"  >
               </div>
               <div id="wxgy" class="fright" href="javascript:openwin('bis/gwgy/index')">
                  <img alt="危险工艺" title="危险工艺" src="${ctx}/static/model/main/images/yqyd-wxgy.png">
                  <div>危险工艺</div>
                  <input name="bis_dangerprocess" type="checkbox" class="checksty"  >
               </div>
               <div id="wxy" class="fright" href="javascript:openwin('bis/hazard/index')">
                  <img alt="重大危险源信息" title="重大危险源信息" src="${ctx}/static/model/main/images/yqyd-zdwxyxx.png">
                  <div>重大危险源信息</div>
                  <input name="bis_hazard" type="checkbox" class="checksty"  >
               </div>
               </form>
            </div>

            <div class="panel-footer bg-white" style="text-align: center;">
               <button class="btn btn-primary" onclick="submitdata()">确认</button>
            </div>
         </div>
      </div>
   </div>
   </div>

   <script type="text/javascript">
				var qylist;
				var qyid;
				$(function() {
					getQyListForNoDataModule();
				});
				function comfirmById(id) {
					$("#"+qyid).removeClass("bg");
					$("#"+id).addClass("bg");
					qyid=id;
					$(".checksty").removeAttr("checked");
					for (var i = 0; i < qylist.length; i++) {
					   if (qylist[i].id == id) {
							$(".thridmenu").show();
							var data = qylist[i];
						   $("#nodata").panel("setTitle",data.m1+": 请确认模块数据完整性并勾选相应模块");
							for ( var key in data) {
								if (data[key] != 0) {
									$("#" + key).hide();
								} else {
									$("#" + key).show();
								}
							}
							break;
						}else{
							$(".thridmenu").hide();
						}
					}
				}
				function getQyListForNoDataModule() {
					$.ajax({
						type : "POST",
						url : '${ctx}/bis/qyjbxx/nodatamoduleqys',
						dataType : 'json',
						async: false,
						success : function(data) {
							qylist = eval(data);
							var html = "";
							var d;
							for (var i = 0; i < qylist.length; i++) {
								d = qylist[i];
								html += "<li class=\"list-group-item\" id=\""+d.id+"\"><i class=\"fa fa-home\"></i><span class=\"pull-right label-danger\">&nbsp;无数据模块 &nbsp;</span>";
								html += "<a onclick=\"comfirmById(" + d.id + ")\"> " + d.m1 + "</a></li>";
							}
							$('#qylist').html(html);
						}
					});
				}
				function submitdata() {
					$.ajax({
						type : "POST",
						url : '${ctx}/bis/qyjbxx/addnodatamodule/'+qyid,
						dataType : 'text',
						data:$('#inputForm').serialize(),
						success : function(data) {
							if(data=='success'){
								layer.msg("保存成功");
								getQyListForNoDataModule();
								comfirmById(qyid);
								return ;
							}else{
								layer.msg("保存失败 ");
								return ;
							}
						}
					});
				}
			</script>
</body>
</html>
