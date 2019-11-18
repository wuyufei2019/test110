<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>受限空间作业</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
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
</head>
<body>
     <form id="inputForm" action="${ctx}/zyaqgl/sxzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${sxzy.sqr }" editable="false" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" >
						<input id="M0" name="M0" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sxzy.m0 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>  
				<tr >
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30" colspan="3">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${sxzy.m1 }" data-options="required:'true',validType:'length[0,50]'" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30" ><input id="M6" name="M6" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sxzy.m6 }" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30" ><input id="M7" name="M7" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sxzy.m7 }" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">受限空间所属单位：</label></td>
					<td class="width-30"><input id="M2" name="M2" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m2 }" data-options="validType:'length[0,50]'"/></td>
					<td class="width-20 active"><label class="pull-right">受限空间名称：</label></td>
					<td class="width-30"><input id="M3" name="M3" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m3 }" data-options="validType:'length[0,100]'"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">作业内容：</label></td>
					<td class="width-30"><input id="M4" name="M4" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m4 }" data-options="validType:'length[0,100]'"/></td>
					<td class="width-20 active"><label class="pull-right">空间内介质名称：</label></td>
					<td class="width-30"><input id="M5" name="M5" style="width: 100%;height: 30px;" class="easyui-textbox" value="${sxzy.m5 }" data-options="validType:'length[0,50]'"/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="动火作业"/>动火作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="高处作业"/>高处作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="吊装作业"/>吊装作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="临时用电"/>临时用电</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="动土/断路作业"/>动土/断路作业</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="锁定"/>锁定</div>
						<div style="width: 25%;float: left;"><input type="checkbox" name="M8" style="margin-bottom: 6px;" value="盲板抽堵"/>盲板抽堵</div>
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td>
					<td class="width-30" colspan="3">
						<div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="易燃易爆物质" />易燃易爆物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="坠落" />坠落</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="腐蚀性物质" />腐蚀性物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="蒸汽" />蒸汽</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="高压气体/液体" />高压气体/液体</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="有毒有害物质" />有毒有害物质</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="高温/低温" />高温/低温</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="触电" />触电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="窒息" />窒息</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="噪音" />噪音</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="产生火花/静电" />产生火花/静电</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="旋转设备" />旋转设备</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="机械伤害" />机械伤害</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="粉尘" />粉尘</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="不利天气" />不利天气</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="淹没/埋没" />淹没/埋没</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="辐射" />辐射</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="交叉作业" />交叉作业</div>
		                <div style="width: 25%;float: left;"><input type="checkbox" name="M9" style="margin-bottom: 6px;" value="其他" />其他</div>
					</td>
				</tr>
				
				<c:if test="${not empty sxzy.ID}">
					<input type="hidden" name="ID" value="${sxzy.ID}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">
if('${action}' == 'update'){
	//涉及的其他特殊作业
	var tszy = "${sxzy.m8}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M8']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
	//危害辨识
	var whbs = "${sxzy.m9}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M9']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$("#M6").datetimebox({  
    onChange:function(date){ 
   		var ksdate = $("#M6").datetimebox("getValue");   
        var jsdate = $("#M7").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M6").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M7").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M6").datetimebox("getValue");  
        var jsdate = $("#M7").datetimebox("getValue");  
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M7").datetimebox("setValue","");  
	            layer.msg("不能小于作业开始时间！", {
					time : 2000
				});
	        }
        }  
    }  
});
</script>
</body>
</html>