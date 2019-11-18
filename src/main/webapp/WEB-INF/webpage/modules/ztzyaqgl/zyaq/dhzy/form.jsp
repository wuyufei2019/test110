<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>动火作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/dhzy/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${dhzy.bzr }" editable="false" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${dhzy.m1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">申请单位：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${dhzy.m2 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">申请人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${dhzy.m3 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">易燃易爆场所：</label></td>
					<td class="width-30" colspan="3">
						<input type="radio" value="0" class="i-checks" name="csflag" />涉及
						<input type="radio" value="1" class="i-checks"  name="csflag" />不涉及
					</td>
				</tr>
				
				<tr style="display:none" id="tsqk">
					<td class="width-20 active"><label class="pull-right">特殊情况：</label></td>
					<td class="width30" colspan="3"><input class="easyui-combobox" style="width: 100%;height: 30px;" id="M4_1" name="M4_1" value="${dhzy.m4_1 }" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火作业级别：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-combobox " value="${dhzy.m4 }" data-options="panelHeight:'auto', editable:false ,data: [{value:'二级动火',text:'二级动火'},
																																		        {value:'一级动火',text:'一级动火'},
																																		        {value:'特殊动火',text:'特殊动火'}] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火地点：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-textbox" value="${dhzy.m5 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">动火方式：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-textbox" value="${dhzy.m6 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><input id="M7" name="M7" class="easyui-datetimebox" value="${dhzy.m7 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><input id="M8" name="M8" class="easyui-datetimebox" value="${dhzy.m8 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">动火作业负责人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox" value="${dhzy.m9 }" data-options="validType:['length[0,25]'] " /></td>
					<td class="width-20 active"><label class="pull-right">动火人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M10" name="M10" class="easyui-textbox" value="${dhzy.m10 }" data-options="validType:['length[0,25]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">监火人：</label></td>
					<td class="width-30" ><input style="width: 100%;height: 30px;" id="M11" name="M11" class="easyui-textbox" value="${dhzy.m11 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
				
				<tr> 
					<td class="width-20 active"><label class="pull-right">涉及的其他特殊作业：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="进入受限空间" />进入受限空间</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="高处作业" />高处作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="吊装作业" />吊装作业</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="盲板抽堵" />盲板抽堵 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="动土作业" />动土作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="断路作业" />断路作业 </div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="临时用电" />临时用电 </div> 
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M12" value="其他" />其他</div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">其他特殊作业：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M12_1" name="M12_1" class="easyui-textbox" value="${dhzy.m12_1 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
								
				<tr> 
					<td class="width-20 active"><label class="pull-right">危害辨识：</label></td><td colspan="3">
																						<div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="物体打击" />物体打击</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="机械伤害" />机械伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="车辆伤害" />车辆伤害</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="起重伤害" />起重伤害</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="高处坠落" />高处坠落</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="中毒和窒息" />中毒和窒息</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="触电" />触电</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="淹溺" />淹溺</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="灼烫" />灼烫</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="火灾" />火灾</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="坍塌" />坍塌</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="透水" />透水</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="放炮" />放炮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="冒顶片帮" />冒顶片帮</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="火药爆炸" />火药爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="瓦斯爆炸" />瓦斯爆炸</div><br/>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="锅炉爆炸" />锅炉爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="容器爆炸" />容器爆炸</div>
																		                <div style="width: 25%;float: left;"><input type="checkbox" name="M13" value="其他" />其他</div>
																		                </td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">其他危害辨识：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M13_1" name="M13_1" class="easyui-textbox" value="${dhzy.m13_1 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
								
				<c:if test="${not empty dhzy.id}">
					<input type="hidden" name="ID" value="${dhzy.id}" />
					<input type="hidden" name="ID1" value="${dhzy.id1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dhzy.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${dhzy.s3}" />
					<input type="hidden" name="zt" value="${dhzy.zt}" />
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
	//特殊作业
	var tszy = "${dhzy.M12}";
	var tszyArr = tszy.split(",");
	for(var i=0;i<tszyArr.length;i++){
		$("input[name='M12']:checkbox[value='"+tszyArr[i]+"']").attr('checked','true');
	}
	
	//危害辨识
	var whbs = "${dhzy.M13}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M13']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='M12']:checkbox").css("width","18px");
	$("input[name='M12']:checkbox").css("height","18px");
	$("input[name='M13']:checkbox").css("width","18px");
	$("input[name='M13']:checkbox").css("height","18px");
});

$("#M7").datetimebox({  
    onChange:function(date){ 
   		var ksdate = $("#M7").datetimebox("getValue");   
        var jsdate = $("#M8").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M7").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M8").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M7").datetimebox("getValue");  
        var jsdate = $("#M8").datetimebox("getValue");  
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M8").datetimebox("setValue","");  
	            layer.msg("不能小于作业开始时间！", {
					time : 2000
				});
	        }
        }  
    }  
});

//判断动火等级
function fxdj(){
	var qk=$("#M4_1").combobox('getValue');
	if(qk=='无'){
		$("#M4").combobox('setValue','一级动火');
	}else{
		$("#M4").combobox('setValue','特殊动火');		
	}
}

$('.i-checks').on('ifChecked', function(event){    
    var selectedvalue = $(event.target).val();
	if (selectedvalue == 0) {
		$("#tsqk").show();
		$("#M4").combobox('setValue','');
		$("#M4_1").combobox({
		     editable:false,
			 valueField: 'text',
			 textField: 'text',
			 panelHeight:'auto',
			 url:'${ctx}/ztzyaqgl/dhzy/tsqklist',
			 onSelect: function(rec){
				 fxdj();
			 }			
		})
	} 
	if (selectedvalue == 1){
		$("#tsqk").hide();
		$("#M4").combobox('setValue','二级动火');
	}
      
}); 
</script>
</body>
</html>