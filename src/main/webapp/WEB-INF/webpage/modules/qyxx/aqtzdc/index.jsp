<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全台账导出</title>
	<meta name="decorator" content="default"/>
</head>
<body >
	<div>
		<form id="inputForm" class="form-horizontal">
			<div style="text-align:center;margin: 20px;">
				<h1 style="color: #1667c4;">安&nbsp;&nbsp;全&nbsp;&nbsp;台&nbsp;&nbsp;账&nbsp;&nbsp;导&nbsp;&nbsp;出</h1>				
			</div>
			<table class="table table-bordered  dataTables-example dataTable no-footer" style="margin:0 auto;min-width:900px;width:1000px;">
				<tbody>
					<tr>
						<td class="width-20 active"><label class="pull-right" style="font-size: 18px;">选择日期范围：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;padding-left: 15px;">
							<input id="starttime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '开始时间'" />&nbsp;&nbsp;&nbsp;～&nbsp;&nbsp;
	    					<input id="finishtime" class="easyui-datebox"  style="height: 30px;" data-options="editable:false,prompt: '结束时间'" />	
						</td>
					</tr>
					<tr>
						<td class="width-20 active"><label class="pull-right" style="font-size: 18px;">选择导出内容：</label></td>
						<td class="width-80" style="padding-top: 10px;padding-bottom: 10px;padding-left: 15px;">
							<input type="checkbox" id="checkall" name="checkall" style="margin-bottom: 7px;width: 18px;height: 18px;" value="0"/><span onclick="checkall(0)" style="font-size: 18px;margin-left: 5px;">全选</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="1"/><span onclick="checkon(0)" style="font-size: 18px;margin-left: 5px;">指标分配</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="2"/><span onclick="checkon(1)" style="font-size: 18px;margin-left: 5px;">目标自评</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="3"/><span onclick="checkon(2)" style="font-size: 18px;margin-left: 5px;">指标考核</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="4"/><span onclick="checkon(3)" style="font-size: 18px;margin-left: 5px;">安全费用使用</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="5"/><span onclick="checkon(4)" style="font-size: 18px;margin-left: 5px;">劳保用品发放</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="6"/><span onclick="checkon(5)" style="font-size: 18px;margin-left: 5px;">员工体检</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="7"/><span onclick="checkon(6)" style="font-size: 18px;margin-left: 5px;">安全会议</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="8"/><span onclick="checkon(7)" style="font-size: 18px;margin-left: 5px;">风险点辨识</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="9"/><span onclick="checkon(8)" style="font-size: 18px;margin-left: 5px;">重大风险清单</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="10"/><span onclick="checkon(9)" style="font-size: 18px;margin-left: 5px;">安全检查</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="11"/><span onclick="checkon(10)" style="font-size: 18px;margin-left: 5px;">安全巡查</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="12"/><span onclick="checkon(11)" style="font-size: 18px;margin-left: 5px;">隐患排查</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="13"/><span onclick="checkon(12)" style="font-size: 18px;margin-left: 5px;">应急演练</span><br/>
							<input type="checkbox" name="aqtz" style="margin-bottom: 7px;width: 18px;height: 18px;" value="14"/><span onclick="checkon(13)" style="font-size: 18px;margin-left: 5px;">安全培训</span><br/>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align:center;margin: 20px;">
				<a id="dc" class="btn btn-primary"  onclick="dc()" style="width:120px;height: 40px;font-size: 20px;">导&nbsp;&nbsp;&nbsp;出</a>				
			</div>
		</form>
	</div>
<script type="text/javascript">
var aqtz = document.getElementsByName("aqtz");

$("#checkall").change(function() { 
　　if($("#checkall").is(":checked")){   
		$("input[type=checkbox][name='aqtz']").attr("checked",true);
	}else{
		$("input[type=checkbox][name='aqtz']").attr("checked",false);
	}
});

function checkon(i){
	if(aqtz[i].checked){   
		$(":checkbox[value='"+(i+1)+"']").attr("checked",false);
	}else{
		$(":checkbox[value='"+(i+1)+"']").attr("checked",true);
	}
}

function checkall(i){
	if($("#checkall").is(":checked")){   
		$("#checkall").attr("checked",false);
		$("input[type=checkbox][name='aqtz']").attr("checked",false);
	}else{
		$("#checkall").attr("checked",true);
		$("input[type=checkbox][name='aqtz']").attr("checked",true);
	}
}

function dc(){
	var checkval = "";
	for(var i=0;i<aqtz.length;i++){
		if(aqtz[i].checked){   
			if(i==0){
				checkval += aqtz[i].value;
			}else{
				checkval += ","+aqtz[i].value;
			}
		}
	}
	var starttime = $("#starttime").datebox("getValue");
	var finishtime = $("#finishtime").datebox("getValue");
	
	if(checkval == ""){
		layer.msg("请至少勾选一项！",{time: 2000});
		return;
	}else{
		window.location.href='${ctx}/bis/aqtzdc/export?checkval='+checkval+'&starttime='+starttime+'&finishtime='+finishtime;
	}
}
</script>
</body>
</html>