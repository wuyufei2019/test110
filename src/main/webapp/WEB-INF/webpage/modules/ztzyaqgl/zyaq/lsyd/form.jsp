<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时用电作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/lsyd/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${lsyd.bzr }" editable="false" /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${lsyd.m1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${lsyd.m2 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${lsyd.m3 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><input id="M4" name="M4" class="easyui-datetimebox" value="${lsyd.m4 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><input id="M5" name="M5" class="easyui-datetimebox" value="${lsyd.m5 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right">作业地点：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-textbox" value="${lsyd.m6 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业规模：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M10" name="M10" class="easyui-combobox " value="${lsyd.m10 }" data-options="panelHeight:'auto', editable:false ,data: [{value:'内部临时用电',text:'内部临时用电'},
																																		        {value:'涉及变配电所',text:'涉及变配电所'}] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">电源接入点：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-textbox" value="${lsyd.m7 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">工作电压：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${lsyd.m8 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">用电设备及功率：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox" value="${lsyd.m9 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">作业人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M11" name="M11" class="easyui-textbox" value="${lsyd.m11 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">电工证号：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M12" name="M12" class="easyui-textbox" value="${lsyd.m12 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M13" name="M13" class="easyui-textbox" value="${lsyd.m13 }" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M14" name="M14" class="easyui-textbox" value="${lsyd.m14 }" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr> 
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="物体打击" />物体打击</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="机械伤害" />机械伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="车辆伤害" />车辆伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="起重伤害" />起重伤害</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="高处坠落" />高处坠落</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="中毒和窒息" />中毒和窒息</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="触电" />触电</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="淹溺" />淹溺</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="灼烫" />灼烫</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="火灾" />火灾</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="坍塌" />坍塌</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="透水" />透水</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="放炮" />放炮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="冒顶片帮" />冒顶片帮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="火药爆炸" />火药爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="瓦斯爆炸" />瓦斯爆炸</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="锅炉爆炸" />锅炉爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="容器爆炸" />容器爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M15" value="其他" />其他</div>
																		                </td>
				</tr>
	
				<tr>
					<td class="width-20 active"><label class="pull-right">其他危害辨识：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M15_1" name="M15_1" class="easyui-textbox" value="${ldyd.m15_1 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
							
				<c:if test="${not empty lsyd.id}">
					<input type="hidden" name="ID" value="${lsyd.id}" />
					<input type="hidden" name="ID1" value="${lsyd.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${lsyd.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${lsyd.s3}" />
					<input type="hidden" name="zt" value="${lsyd.zt}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
<script type="text/javascript">
var usertype=${usertype};
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

var $ = jQuery;

function doSubmit(){
	$("#inputForm").serializeObject();
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

var action = "${action}";
if(action=="update"){
	//危害辨识
	var whbs = "${lsyd.m15}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M15']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='M15']:checkbox").css("width","18px");
	$("input[name='M15']:checkbox").css("height","18px");
});

$("#M4").datetimebox({  
    onChange:function(date){ 
   		var ksdate = $("#M4").datetimebox("getValue");   
        var jsdate = $("#M5").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M4").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M5").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M4").datetimebox("getValue");  
        var jsdate = $("#M5").datetimebox("getValue");  
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M5").datetimebox("setValue","");  
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