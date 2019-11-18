<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>修改设备二级保养计划</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbyjl/sec/form.js?v=1.0"></script>
</head>
<body>
	<div id="sbssgl_sbbystask_sec_tb" style="padding:5px;height:auto;width: 100%">
		<form id="inputForm" action="${ctx}/sbssgl/sbbytasksec/${action}"  method="post" class="form-horizontal">
				<table id="rwtable" class="table table-bordered">
					<tbody class="w36">
						<tr class="w36">
							<td colspan="6" style="width: 100%;text-align: center">
								<input id="year" name="year" class="easyui-combobox" style="width: 100px;height: 30px;" value="${entity.year }" 
									data-options="required:'true', editable:false, panelHeight: '150px'" /><span style="font-size: 18px;font-weight: bold;padding-top:10px"> 年设备二级保养计划表</span>
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
			 	<input type="hidden" name="jau" value=""><input type="hidden" name="feb" value=""><input type="hidden" name="mar" value=""><input type="hidden" name="apr" value="">
			 	<input type="hidden" name="may" value=""><input type="hidden" name="jun" value=""><input type="hidden" name="jul" value=""><input type="hidden" name="aug" value="">
			 	<input type="hidden" name="sep" value=""><input type="hidden" name="oct" value=""><input type="hidden" name="nov" value=""><input type="hidden" name="dec" value="">
		  	</form>
	</div>
	<table id="sbssgl_sbbystask_sec_dg" ></table>  
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var sbtype = '${sbtype}';//设备类型
var taskid = ${entity.id};
var validateForm;

//设备二级保养计划，1-12月，各个数组中保存的是id
var jau = [];var feb = [];var mar = [];var apr = [];var may = [];var jun = [];
var jul = [];var aug = [];var sep = [];var oct = [];var nov = [];var dec = [];

var removeJauIds = [];var removeFebIds = [];var removeMarIds = [];var removeAprIds = [];var removeMayIds = [];var removeJunIds = [];
var removeJulIds = [];var removeAugIds = [];var removeSepIds = [];var removeOctIds = [];var removeNovIds = [];var removeDecIds = [];

function doSubmit(){
	layer.confirm('确定保存信息？', {icon: 3, title:'提示'}, function(index){
		top.layer.close(index);
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
	    	parent.dg2.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});
});
</script>
</body>
</html>