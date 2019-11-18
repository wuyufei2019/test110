<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理台时确定</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/tsqd/index.js?v=1.1"></script>
</head>
<body >
<div id="sbssgl_tsqd_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<%-- <c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if> --%>
		<input type="text" name="deptname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/system/department/deptjson',prompt: '使用单位' "/>
		<input name="m3" id="m3" class="easyui-datebox" style="height: 30px;" data-options="editable:false,prompt: '日期'" />
		<input name="m1" class="easyui-numberbox" style="height: 30px;" data-options="prompt: '主要设备制度开动台时'" />
		<input name="m2" class="easyui-numberbox" style="height: 30px;" data-options="prompt: '主要设备实际开动台时'" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="pull-left">
				<shiro:hasPermission name="sbssgl:tsqd:add">
			       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:tsqd:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:tsqd:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:tsqd:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>
		        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			
				</div>
			<div class="pull-right">
			</div>
		</div>
	</div> 	   
</div>
<table id="sbssgl_tsqd_dg"></table> 

<script>
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
	$(function(){
		initDateShow("m3");
	})
	
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