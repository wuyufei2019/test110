<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备大修</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbdx/index.js?v=1.1"></script>
</head>
<body >
<div class="easyui-tabs" fit="true">
	<div title="设备大项修计划" style="height:100%;">
		<div id="sbssgl_sbdx_tb" style="padding:5px;height:auto">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
					<c:if test="${type eq '2'}">
						<input type="text" name="qyname" class="easyui-combobox" style="width: 150px;height: 30px;" 
							data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称',
								onSelect: function(row) {
									$('#m5').combobox('setValue', '');
									$('#m5').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							 "/>
					</c:if>
					<input name="m7" id="m7" class="easyui-combobox" style="width: 150px;height: 30px;" data-options="prompt: '年度', panelHeight: '150px'" />
					<input name="m5" id="m5" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
					<input name="m2" id="m2" class="easyui-textbox" style="width: 150px;height: 30px;" data-options="prompt: '设备编号'" />
					<input name="m18" id="m18" class="easyui-datebox" style="width: 150px;height: 30px;" data-options="editable:false,prompt: '计划修理时间'" />
					<input name="m8" id="m8" class="easyui-combobox" style="width: 120px;height: 30px;" 
						data-options="editable:false,prompt: '修理类别',panelHeight:'auto',data:[{value: '0', text: '大修'},{value: '1', text: '项修'}]" />
					<input name="m19" id="m19" class="easyui-combobox" style="width: 120px;height: 30px;" 
						data-options="editable:false,prompt: '是否完成',panelHeight:'auto',data:[{value: '0', text: '未完成'},{value: '1', text: '已完成'}]" />
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			    </div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="sbssgl:sbdx:add">
					       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:exin">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/sbssgl/sbdx/exinjump','${ctx}/sbssgl/sbdx/exin','${ctx}/static/templates/设备大项修信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport('${sbtype}')" title="导出设备大项修计划"><i class="fa fa-external-link"></i> 导出设备大项修计划</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="ndbb()" title="年度报表"><i class="fa fa-plus"></i> 年度报表</button> 
			        	</shiro:hasPermission>
			        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
					<div class="pull-right">
					</div>
				</div>
			</div> 	   
		</div>
		<table id="sbssgl_sbdx_dg"></table> 
	 </div>
    <div title="设备更新计划" style="height:100%;">
    	<div id="sbssgl_sbdx_gx_tb" style="padding:5px;height:auto">
			<form id="searchFrom2" action="" style="margin-bottom: 8px;" class="form-inline">				
				<div class="form-group">
					<c:if test="${type eq '2'}">
						<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" 
							data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称',
								onSelect: function(row) {
									$('#m4').combobox('setValue', '');
									$('#m4').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
								}
							 "/>
					</c:if>
					<input name="m14" id="m14" class="easyui-combobox" style="width: 120px;height: 30px;" data-options="prompt: '年度', panelHeight: '150px'" />
					<input name="m4" id="m4" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="panelHeight:'150px',valueField: 'id',textField: 'text',url: '${ctx}/system/department/deptjson',prompt: '使用单位' "/>
					<input name="m2" id="m2" class="easyui-textbox" style="width: 150px;height: 30px;" data-options="prompt: '设备名称'" />
					<input name="m5" id="m5" class="easyui-numberbox" style="width: 120px;height: 30px;" data-options="prompt: '单价（万元）'" />
					<input name="m6" id="m6" class="easyui-numberbox" style="width: 120px;height: 30px;" data-options="prompt: '数量（台）'" />
					<input name="m8" id="m8" class="easyui-combobox" style="width: 150px;height: 30px;" 
						data-options="editable:false,prompt: '设备更新类别',panelHeight:'auto',data:[{value: '0', text: '替换'},{value: '1', text: '新增'}]" />
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset2()" ><i class="fa fa-refresh"></i> 全部</span>
			    </div>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
						<shiro:hasPermission name="sbssgl:sbdx:add">
					       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add2()" title="添加"><i class="fa fa-plus"></i> 添加</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:update">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd2()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:delete">
							<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del2()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
						</shiro:hasPermission>
						<shiro:hasPermission name="sbssgl:sbdx:view">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:exin">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/sbssgl/sbgx/exinjump','${ctx}/sbssgl/sbgx/exin','${ctx}/static/templates/设备更新计划导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport2('${sbtype}')" title="导出设备更新计划"><i class="fa fa-external-link"></i> 导出设备更新计划</button> 
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="sbssgl:sbdx:export">
			        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="ndbb2()" title="年度报表"><i class="fa fa-plus"></i> 年度报表</button> 
			        	</shiro:hasPermission>
			        	<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
					<div class="pull-right">
					</div>
				</div>
			</div> 	   
		</div>
		<table id="sbssgl_sbdx_gx_dg"></table> 
	</div>
</div>
<script>
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
	
	$(function(){
		initDateShow("m18");
		
		var curYear = new Date().getFullYear();
		var data = [];
		for (var  i = (curYear - 3); i < (curYear + 3); i++) {
			data.push({value: i, text: i});
		}
		$("#m7").combobox('loadData', data);
		
		$("#m14").combobox('loadData', data);
	});
	
	//初始化年份-月份控件
	function initDateShow(idName){
		var yjyf = $("#"+idName);
		yjyf.datebox({
		    onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
		        span.trigger('click'); //触发click事件弹出月份层
		        if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
		        if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
		            tds = p.find('div.calendar-menu-month-inner td');
		            tds.click(function (e) {
		                e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
		                var year = /\d{4}/.exec(span.html())[0]//得到年份
		                , month = parseInt($(this).attr('abbr'), 10); //
		                yjyf.datebox('hidePanel')//隐藏日期对象
		                .datebox('setValue', year + '-' + month); //设置日期的值
		            });
		        }, 0);
		        yearIpt.unbind();//解绑年份输入框中任何事件
		    },
		    parser: function (s) {
		        if (!s) return new Date();
		        var arr = s.split('-');
		        return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		    },
		    formatter: function (d) { 
		    	var dMonth = (d.getMonth() + 1);
		    	if (dMonth < 10) {
		    		dMonth = ("0"+dMonth);//1月到9月自动补0
		    	}
		    	return d.getFullYear() + '-' + dMonth; 
	    	}
		});
		var p = yjyf.datebox('panel'), //日期选择对象
		    tds = false, //日期选择对象中月份
		    aToday = p.find('a.datebox-current'),
		    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
		    //显示月份层的触发控件
		    span = aToday.length ? p.find('div.calendar-title span') :
		    p.find('span.calendar-text'); 
		if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
		   
		    aToday.unbind('click').click(function () {
		        var now=new Date();
		        yjyf.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
		    });
		}
		/* var now=new Date();
		yjyf.datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1)); */
	}
	
	
</script>
</body>
</html>