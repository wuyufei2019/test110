<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqjg/aqjdjc/jcjl/index.js?v=1.6"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="aqjg_jcjl_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="aqjg_jcjh_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '检查计划名称'" />
		<%-- <input  name="aqjg_jcjl_year" style="height: 30px;"class="easyui-combobox"data-options="prompt:'年份',valueField: 'id',textField: 'text',url:'${ctx}/aqjd/jcjh/year'" /> --%>
		<input type="text" class="easyui-datebox" name="aqjg_jcjj_date"  style="height: 30px;" data-options="prompt: '检查计划时间'"  >
		<input type="text" class="easyui-textbox" name="aqjg_jcjj_qyname"  style="height: 30px;" data-options="prompt: '企业名称'"  >
        <input type="text" class="easyui-combobox" id="aqjg_jcjj_checkflag"name="aqjg_jcjj_checkflag"  style="height: 30px;" data-options="panelHeight:'auto', prompt: '检查进度',editable:false,data: [
                                {value:'2',text:'初查'},
                                {value:'1',text:'复查'} ]"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="aqjd:jcjl:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出检查记录</button> 
        	</shiro:hasPermission>
			<shiro:hasPermission name="aqjd:jcjl:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
</div>
<table id="aqjg_jcjl_dg"></table> 
<script>
	var f='${sys}';
	var jcjd='${jcjd}';
			//日期控件只显示年月
           var M2 = $('[name=aqjg_jcjj_date]');
            M2.datebox({
                onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                    span.trigger('click'); //触发click事件弹出月份层
                    if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
                    if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                        tds = p.find('div.calendar-menu-month-inner td');
                        tds.click(function (e) {
                            e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                            var year = /\d{4}/.exec(span.html())[0]//得到年份
                            , month = parseInt($(this).attr('abbr'), 10); //
                            M2.datebox('hidePanel')//隐藏日期对象
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
            var p = M2.datebox('panel'), //日期选择对象
                tds = false, //日期选择对象中月份
                aToday = p.find('a.datebox-current'),
                yearIpt = p.find('input.calendar-menu-year'),//年份输入框
                //显示月份层的触发控件
                span = aToday.length ? p.find('div.calendar-title span') :
                p.find('span.calendar-text'); 
            if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
               
                aToday.unbind('click').click(function () {
                    var now=new Date();
                    M2.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
                });
            }

</script>
</body>
</html>