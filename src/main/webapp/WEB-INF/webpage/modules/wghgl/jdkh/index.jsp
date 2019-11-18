<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检人员监督考核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/wghgl/jdkh/index.js?v=5"></script>
</head>
<body >
<div class="easyui-tabs" fit="true">   
		<div title="月检考核" style="height:100%;" data-options="">
			<div id="wghgl_xjry_tb1" style="padding:5px;height:auto">
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
				        	<form id="wghgl_xjry_searchFrom1" style="margin-bottom: 8px;" action="" class="form-inline">
				        	    <input type="text" id="wghgl_yjxjry_yf" name="wghgl_yjxjry_yf" class="easyui-datebox" style="height: 30px;"/>
				        	    <input type="text" id="wghgl_yjxjry_ryname" name="wghgl_yjxjry_ryname" class="easyui-textbox"  style="height: 30px;" data-options="editable:true ,prompt: '网格员' "/>
				       	        <input type="text"  name="wghgl_yjxjry_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
				       	        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search1()" ><i class="fa fa-search"></i> 查询</span>
								<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear1()" ><i class="fa fa-refresh"></i> 全部</span>
							</form>
						</div>
						<div class="pull-right">
						</div>
					</div>
				</div>
        	</div>   
			<table id="wghgl_xjry_dg1"></table> 
		</div>

		
		<div title="年检考核" style="height:100%;">
			<div id="wghgl_xjry_tb2" style="padding:5px;height:auto">
				<div class="row">
					<div class="col-sm-12">
						<div class="pull-left">
					       	<form id="wghgl_xjry_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline" >
					      	    <input type="text" id="wghgl_njxjry_nf" name="wghgl_njxjry_nf" class="easyui-textbox" style="height: 30px;" />
				        	    <input type="text" id="wghgl_njxjry_ryname" name="wghgl_njxjry_ryname" class="easyui-textbox"  style="height: 30px;" data-options="editable:true ,prompt: '网格员' "/>
				       	        <input type="text"  name="wghgl_njxjry_xzqy" class="easyui-combotree" style="height: 30px;" data-options="editable:false,valueField: 'id',textField: 'text',url:'${ctx}/system/admin/xzqy/xzqyjson',prompt: '网格' "/>
				       	        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
								<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear2()" ><i class="fa fa-refresh"></i> 全部</span>
							</form>
						</div>
						<div class="pull-right">
			        	</div> 
		        	</div>
	        	</div>  
        	</div> 
			<table id="wghgl_xjry_dg2"></table> 
		</div>
</div>
<script type="text/javascript">
$(function() {
	var yjyf = $('#wghgl_yjxjry_yf');
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
	    formatter: function (d) { return d.getFullYear() + '-' + (d.getMonth() + 1); }
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
	var now=new Date();
	yjyf.datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
});

$(function(){
	//年份下拉框初始化
	$("#wghgl_njxjry_nf").combobox({ 
		prompt: '年份',
		editable:'true',
		valueField:'year',    
		textField:'year',  
		panelHeight:'auto'
	});
	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear();
	
	for(var i=0;i<6;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
		
	$("#wghgl_njxjry_nf").combobox("loadData", data);//下拉框加载数据
	var now=new Date();
	$("#wghgl_njxjry_nf").combobox('setValue',now.getFullYear());
});
</script>
</body>
</html>