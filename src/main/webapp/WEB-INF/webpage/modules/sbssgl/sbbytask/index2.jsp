<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>设备保养计划</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbytask/index.js?v=1.2"></script>
</head>
<body >
<div class="easyui-tabs" fit="true">
	<div title="设备一级保养计划" style="height:100%;">
		<div id="sbssgl_sbbystask_fir_tb" style="padding:5px;height:auto">
			<form id="inputForm" action="${ctx}/sbssgl/sbbytaskfir/${action}"  method="post" class="form-horizontal">
				<table id="rwtable" class="table table-bordered">
					<tbody class="w36">
						<tr class="w36">
							<td colspan="16" style="width: 100%;text-align: center">
								<input id="yearfir" name="yearfir" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.yearfir }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 年</span>
									<input id="month" name="month" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.month }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 月设备一级保养计划表</span>
		
								<a class="btn btn-info btn-gm" style="float:right" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i>保存</a>
							</td>
	      				</tr>
	      				<tr>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">所属企业</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input id="qyidfir" name="qyidfir" type="text" value="${entity.qyid }" class="easyui-combobox" style="width: 100%;height: 30px;" 
									data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
										onSelect: function(row){
											$('#deptidfir').combobox('setValue', '');
											$('#deptidfir').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
										}
								">
							</td>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">部门名称</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input id="deptidfir" name="deptidfir" type="text" value="${entity.deptid }" class="easyui-combobox" style="width: 100%;height: 30px;" 
									data-options="required:true,editable : false, panelHeight:100,valueField:'id', textField:'text',url:'${ctx}/system/department/deptjson',
										onSelect: function(row){
											dg.datagrid('load', {m23: row.id, sbtype: sbtype});
										}
								">
							</td>
							<td colspan="7" class="w74" style="width: 74%;text-align: right;"><label class="">编制日期</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input name="bztimefir" id="bztimefir" style="width: 100%;height: 30px;margin-left:10px" class="easyui-datebox" value="${entity.bztimefir }" data-options="required:true" />
							</td>
	    				</tr>
		  			</tbody>
				</table>
				<input type="hidden" name="sbtypefir" value="${sbtype}">
			 	<input type="hidden" name="one" value=""><input type="hidden" name="tow" value=""><input type="hidden" name="thr" value=""><input type="hidden" name="fur" value="">
			 	<input type="hidden" name="fiv" value=""><input type="hidden" name="six" value=""><input type="hidden" name="sen" value=""><input type="hidden" name="eig" value="">
			 	<input type="hidden" name="nin" value=""><input type="hidden" name="ten" value=""><input type="hidden" name="ele" value=""><input type="hidden" name="twl" value="">
			 	<input type="hidden" name="tht" value=""><input type="hidden" name="fut" value=""><input type="hidden" name="fft" value=""><input type="hidden" name="sxt" value="">
			 	<input type="hidden" name="svt" value=""><input type="hidden" name="egt" value=""><input type="hidden" name="nnt" value=""><input type="hidden" name="tty" value="">
			 	<input type="hidden" name="tty_one" value=""><input type="hidden" name="tty_two" value=""><input type="hidden" name="tty_thr" value=""><input type="hidden" name="tty_fur" value="">
			 	<input type="hidden" name="tty_fiv" value=""><input type="hidden" name="tty_six" value=""><input type="hidden" name="tty_sen" value=""><input type="hidden" name="tty_eig" value="">
			 	<input type="hidden" name="tty_nin" value=""><input type="hidden" name="tiy" value=""><input type="hidden" name="tiy_one" value="">
		  	</form>
	  	</div>
		<table id="sbssgl_sbbystask_fir_dg"></table>
	</div>
	<div title="设备二级保养计划" style="height:100%;">
		<div id="sbssgl_sbbystask_sec_tb" style="padding:5px;height:auto">
			<form id="inputForm2" action="${ctx}/sbssgl/sbbytasksec/${action}"  method="post" class="form-horizontal">
				<table id="rwtable" class="table table-bordered">
					<tbody class="w36">
						<tr class="w36">
							<td colspan="16" style="width: 100%;text-align: center">
								<input id="yearsec" name="yearsec" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.yearsec }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 年设备二级保养计划表</span>
		
								<a class="btn btn-info btn-gm" style="float:right" data-toggle="tooltip" data-placement="left" onclick="add2()" title="添加"><i class="fa fa-plus"></i>保存</a>
							</td>
	      				</tr>
	      				<tr>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">所属企业</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input id="qyidsec" name="qyidsec" type="text" value="${entity.qyid }" class="easyui-combobox" style="width: 100%;height: 30px;" 
									data-options="required:'true',editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'${ctx}/bis/qyjbxx/idjson',
										onSelect: function(row){
											$('#deptidsec').combobox('setValue', '');
											$('#deptidsec').combobox('reload', '${ctx}/system/department/deptjson3/'+row.id);
										}
								">
							</td>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">部门名称</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input id="deptidsec" name="deptidsec" type="text" value="${entity.deptid }" class="easyui-combobox" style="width: 100%;height: 30px;" 
									data-options="required:true,editable : false, panelHeight:100,valueField:'id', textField:'text',url:'${ctx}/system/department/deptjson',
										onSelect: function(row){
											dg2.datagrid('load', {m23: row.id, sbtype: sbtype});
										}
								">
							</td>
							<td colspan="7" class="w74" style="width: 74%;text-align: right;"><label class="">编制日期</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input name="bztimesec" id="bztimesec" style="width: 100%;height: 30px;margin-left:10px" class="easyui-datebox" value="${entity.bztimesec }" data-options="required:true" />
							</td>
	    			</tr>
		  			</tbody>
				</table>
				<input type="hidden" name="sbtypesec" value="${sbtype}">
			 	<input type="hidden" name="jau" value=""><input type="hidden" name="feb" value=""><input type="hidden" name="mar" value=""><input type="hidden" name="apr" value="">
			 	<input type="hidden" name="may" value=""><input type="hidden" name="jun" value=""><input type="hidden" name="jul" value=""><input type="hidden" name="aug" value="">
			 	<input type="hidden" name="sep" value=""><input type="hidden" name="oct" value=""><input type="hidden" name="nov" value=""><input type="hidden" name="dec" value="">
		  	</form>
		 </div>
	  	<table id="sbssgl_sbbystask_sec_dg"></table>   
	</div>
</div>
<script type="text/javascript">
var type = '${type}';
var sbtype = '${sbtype}';//设备类型

//设备一级保养计划，1-31号，各个数组中保存的是sbid（设备id）
var one = [];var tow = [];var thr = [];var fur = [];var fiv = [];var six = [];var sen = [];var eig = [];var nin = [];var ten = [];
var ele = [];var twl = [];var tht = [];var fut = [];var fft = [];var sxt = [];var svt = [];var egt = [];var nnt = [];var tty = [];
var tty_one = [];var tty_two = [];var tty_thr = [];var tty_fur = []; var tty_fiv = [];
var tty_six = [];var tty_sen = [];var tty_eig = [];var tty_nin = [];var tiy = []; var tiy_one = [];

//设备二级保养计划，1-12月，各个数组中保存的是sbid（设备id）
var jau = [];var feb = [];var mar = [];var apr = [];var may = [];var jun = [];
var jul = [];var aug = [];var sep = [];var oct = [];var nov = [];var dec = [];


function add(){
	if ($("#yearfir").combobox('getValue') == '' || $("#yearfir").combobox('getValue') == null) {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	if ($("#month").combobox('getValue') == '' || $("#month").combobox('getValue') == null) {
		layer.msg('请选择月度', {time: 3000});
		return;
	}
	if ($("#deptidfir").combobox('getValue') == '' || $("#deptidfir").combobox('getValue') == null) {
		layer.msg('请选择使用部门', {time: 3000});
		return;
	}
	if ($("#bztimefir").combobox('getValue') == '' || $("#bztimefir").combobox('getValue') == null) {
		layer.msg('请选择编制日期', {time: 3000});
		return;
	}
	layer.confirm('确定保存信息？', {icon: 3, title:'提示'}, function(index){
		layer.close(index);
		$("[name='one']").val(one);$("[name='tow']").val(tow);$("[name='thr']").val(thr);$("[name='fur']").val(fur);
		$("[name='fiv']").val(fiv);$("[name='six']").val(six);$("[name='sen']").val(sen);$("[name='eig']").val(eig);
		$("[name='nin']").val(nin);$("[name='ten']").val(ten);$("[name='ele']").val(ele);$("[name='twl']").val(twl);
		$("[name='tht']").val(tht);$("[name='fut']").val(fut);$("[name='fft']").val(fft);$("[name='sxt']").val(sxt);
		$("[name='svt']").val(svt);$("[name='egt']").val(egt);$("[name='nnt']").val(nnt);$("[name='tty']").val(tty);
		$("[name='tty_one']").val(tty_one);$("[name='tty_two']").val(tty_two);$("[name='tty_thr']").val(tty_thr);$("[name='tty_fur']").val(tty_fur);
		$("[name='tty_fiv']").val(tty_fiv);$("[name='tty_six']").val(tty_six);$("[name='tty_sen']").val(tty_sen);$("[name='tty_eig']").val(tty_eig);
		$("[name='tty_nin']").val(tty_nin);$("[name='tiy']").val(tiy);$("[name='tiy_one']").val(tiy_one);
		
		
		$("#inputForm").submit(); 
	});
}


function add2(){
	if ($("#yearsec").combobox('getValue') == '' || $("#yearsec").combobox('getValue') == null) {
		layer.msg('请选择年度', {time: 3000});
		return;
	}
	if ($("#deptidsec").combobox('getValue') == '' || $("#deptidsec").combobox('getValue') == null) {
		layer.msg('请选择使用部门', {time: 3000});
		return;
	}
	if ($("#bztimesec").combobox('getValue') == '' || $("#bztimesec").combobox('getValue') == null) {
		layer.msg('请选择编制日期', {time: 3000});
		return;
	}
	layer.confirm('确定保存信息？', {icon: 3, title:'提示'}, function(index){
		layer.close(index);
		$("[name='jau']").val(jau);$("[name='feb']").val(feb);$("[name='mar']").val(mar);$("[name='apr']").val(apr);
		$("[name='may']").val(may);$("[name='jun']").val(jun);$("[name='jul']").val(jul);$("[name='aug']").val(aug);
		$("[name='sep']").val(sep);$("[name='oct']").val(oct);$("[name='nov']").val(nov);$("[name='dec']").val(dec);
		console.log("jau:"+$("[name='jau']").val()+";")
		console.log("feb:"+$("[name='feb']").val()+";")
		console.log("mar:"+$("[name='mar']").val()+";")
		console.log("apr:"+$("[name='apr']").val()+";")
		console.log("may:"+$("[name='may']").val()+";")
		console.log("jun:"+$("[name='jun']").val()+";")
		console.log("jul:"+$("[name='jul']").val()+";")
		console.log("aug:"+$("[name='aug']").val()+";")
		console.log("sep:"+$("[name='sep']").val()+";")
		console.log("oct:"+$("[name='oct']").val()+";")
		console.log("nov:"+$("[name='nov']").val()+";")
		console.log("dec:"+$("[name='dec']").val()+";")
		
		$("#inputForm2").submit(); 
	});
}

$(function(){
	var curYear = new Date().getFullYear();
	var years = [];
	for (var  i = (curYear - 3); i < (curYear + 3); i++) {
		years.push({value: i, text: i});
	}
	$("#yearfir").combobox('loadData', years);
	$("#yearsec").combobox('loadData', years);
	
	var mons = [];
	for (var j = 1; j < 13; j++) {
		mons.push({value: j, text: j});
	}
	$("#month").combobox('loadData', mons);
	
	var flag=true;
	var flag2=true;
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
	    		layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    }    
	});
	$('#inputForm2').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag2){
	    		flag2=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    }    
	});
})

</script>


</body>
</html>