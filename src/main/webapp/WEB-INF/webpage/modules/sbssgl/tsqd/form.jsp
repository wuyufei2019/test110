<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>台时确定</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/sbssgl/tsqd/${action}"  method="post" class="form-horizontal" >
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<%-- <tr> 暂时不需要此字段
					<td class="width-20 active"><label class="pull-right">选择企业：</label></td>
					<td class="width-80" colspan="3">
						<input id="qyid" name="qyid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${tsqd.qyid }" 
							data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
										  onSelect: function(row){
										  	if ('${sbtype }' == '0') {
										  		$('#m5').combobox('setValue', '');
											    $('#m5').combobox('reload', '${ctx}/sbssgl/sbgl/sbjson/'+row.id);
										  	} else if ('${sbtype }' == '1')  {
										  		$('#m5').combobox('setValue', '');
											    $('#m5').combobox('reload', '${ctx}/sbssgl/sbgl/tzsbjson/'+row.id);	
										  	}
										  	  
										  }" />
					</td>
				</tr> --%> 
				<tr>
				   	<td class="width-25 active"><label class="pull-right">使用单位：</label></td>
					<td class="width-25">
						<input id="deptid" name="deptid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${tsqd.deptid }" 
							data-options="panelHeight:'100px',valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'" />
				   	</td>
				   	<td class="width-25 active"><label class="pull-right">日期：</label></td>
					<td class="width-25">
						<input id="m3" name="m3" class="easyui-datebox" style="width: 100%;height: 30px;" value="${tsqd.m3 }" data-options="editable:false" />
				   	</td>
				</tr>
				<tr>
					<td class="width-25 active"><label class="pull-right">主要设备制度开动台时（H）：</label></td>
					<td class="width-25">
						<input id="m1" name="m1" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${tsqd.m1 }" data-options="min:0" />
					</td>
					<td class="width-25 active"><label class="pull-right">主要设备实际开动台时（H）：</label></td>
					<td class="width-25">
						<input id="m2" name="m2" class="easyui-numberbox" style="width: 100%;height: 30px;" value="${tsqd.m2 }"  data-options="min:0" />
					</td>
				</tr>
			</tbody>
		</table>	
		
		<c:if test="${not empty tsqd.ID}">
			<input type="hidden" name="ID" value="${tsqd.ID}" />
			<input type="hidden" name="S1" value="<fmt:formatDate value="${tsqd.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			<input type="hidden" name="S3" value="${tsqd.s3}" />
		</c:if>			
	</form>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

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


function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>