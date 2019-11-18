<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>修改设备一级保养计划</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbyjl/fir/form.js?v=1.0"></script>
</head>
<body>
	<div id="sbssgl_sbbystask_fir_tb" style="padding:5px;height:auto;width: 100%">
		<form id="inputForm" action="${ctx}/sbssgl/sbbytaskfir/${action}"  method="post" class="form-horizontal">
				<table id="rwtable" class="table table-bordered">
					<tbody class="w36">
						<tr class="w36">
							<td colspan="6" style="width: 100%;text-align: center">
								<input id="year" name="year" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.year }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 年</span>
									<input id="month" name="month" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.month }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 月设备一级保养计划表</span>
							</td>
	      				</tr>
	      				<tr>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">所属企业</label></td>
							<td class="w17" style="width: 16%;text-align: center;">${entity.qyname }
							</td>
							<td class="w7" style="width: 7%;text-align: center;"><label class="">部门名称</label></td>
							<td class="w17" style="width: 16%;text-align: center;">${entity.deptname }
							</td>
							<td colspan="7" class="w74" style="width: 74%;text-align: right;"><label class="">编制日期</label></td>
							<td class="w17" style="width: 16%;text-align: center;">
								<input name="bztime" id="bztime" style="width: 100%;height: 30px;margin-left:10px" class="easyui-datebox" value="<fmt:formatDate value="${entity.bztime }" pattern="yyyy-MM-dd" />" />
	    			</tr>
		  			</tbody>
				</table>
				<input type="hidden" name="ID" value="${entity.id}">
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
	<table id="sbssgl_sbbystask_fir_dg" ></table>  

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var sbtype = '${sbtype}';//设备类型
var taskid = ${entity.id};
var validateForm;

//设备一级保养计划，1-31号，各个数组中保存的是id
var one = [];var tow = [];var thr = [];var fur = [];var fiv = [];var six = [];var sen = [];var eig = [];var nin = [];var ten = [];
var ele = [];var twl = [];var tht = [];var fut = [];var fft = [];var sxt = [];var svt = [];var egt = [];var nnt = [];var tty = [];
var tty_one = [];var tty_two = [];var tty_thr = [];var tty_fur = []; var tty_fiv = [];
var tty_six = [];var tty_sen = [];var tty_eig = [];var tty_nin = [];var tiy = []; var tiy_one = [];

function doSubmit(){
	layer.confirm('确定保存信息？', {icon: 3, title:'提示'}, function(index){
		top.layer.close(index);
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

$(function(){
	var curYear = new Date().getFullYear();
	var data = [];
	for (var  i = (curYear - 3); i < (curYear + 3); i++) {
		data.push({value: i, text: i});
	}
	$("#year").combobox('loadData', data);
	
	var mons = [];
	for (var j = 1; j < 13; j++) {
		mons.push({value: j, text: j});
	}
	$("#month").combobox('loadData', mons);
	
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
	    	if(data=='success'){
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	}else{
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
			}
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>