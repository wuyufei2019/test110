<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>强制检验</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/qzjy/index.js?v=1.0"></script>
</head>
<body >
<div id="sbssgl_qzjy_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="m2" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备名称'" />
		<input id="m28" name="m28" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '注册登记日期'" />
		<input type="text" id="time1" name="time1" style="height:30px" class="easyui-combobox" 
			data-options="width:150,prompt: '到期时间查询',editable:false,panelHeight: 'auto',
				data: [
			        {value:'7',text:'一个星期'},
			        {value:'14',text:'两个星期'},
			        {value:'21',text:'三个星期'},
		            {value:'30',text:'一个月'},
		            {value:'60',text:'两个月'} ] ,
	           onSelect:function(rec){
		 		   if(rec!=null&&rec!=''){
		 			  $('#m32').combobox('clear');
		 		   }
  			   }"/>
        <input type="text" class="easyui-datebox" id="m32" name="m32"  style="height:30px" 
           data-options="width:150,prompt: '具体到期时间查询' ,
          	   onSelect:function(rec){
		 		   if(rec!=null&&rec!=''){
		 			   $('#time1').combobox('clear');
		 		   }
			  }" />
		<input id="status" name="status" class="easyui-combobox" style="width: 120px;height: 30px;" 
			data-options="editable:false,prompt: '到期状态',panelHeight:'auto',data:[{value: '0', text: '未到期'},{value: '1', text: '即将到期'},{value: '2', text: '已过期'}]" />
			
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
		<%-- <div class="col-sm-12">
			<div class="pull-left">
				<shiro:hasPermission name="sbssgl:sbgl:add">
			       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbgl:update">
				    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbgl:delete">
					<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
				</shiro:hasPermission>
				<shiro:hasPermission name="sbssgl:sbgl:view">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="sbssgl:sbgl:export">
	        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport('${sbtype}')" title="导出设备清单"><i class="fa fa-external-link"></i> 导出设备清单</button> 
	        	</shiro:hasPermission>
		        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			
				</div>
			<div class="pull-right">
			</div>
		</div> --%>
	</div> 	   
</div>
<table id="sbssgl_qzjy_dg"></table> 
<script>
var role = '0';
var type = '${type}';
$(function(){
	initDateShow("m28");
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
<shiro:hasPermission name="sbssgl:sbgl:operation">
	<script>role = '1';</script>
</shiro:hasPermission>
</body>
</html>