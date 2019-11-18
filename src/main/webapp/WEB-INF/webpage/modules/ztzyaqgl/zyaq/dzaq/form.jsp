<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>吊装安全作业管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/ztzyaqgl/dzaq/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">编制人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="sqr" class="easyui-textbox" value="${dzaq.bzr }" editable="false" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">作业证编号：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${dzaq.m1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">吊装地点：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${dzaq.m2 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">吊装工具名称：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${dzaq.m3 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">吊装人员：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${dzaq.m4 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">特种作业证号：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M4_1" name="M4_1" class="easyui-textbox" value="${dzaq.m4_1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">吊装指挥：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-textbox" value="${dzaq.m5 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">特种作业证号：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M5_1" name="M5_1" class="easyui-textbox" value="${dzaq.m5_1 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">起吊重物质量（t）：</label></td>
					<td class="width-30" ><input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-numberbox" value="${dzaq.m6 }" data-options="min:0,max:200,
						onChange:function(){
							fxdj();
						}
					  " /></td>
					<td class="width-20 active"><label class="pull-right">作业等级：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M7" name="M7" class="easyui-combobox " value="${dzaq.m7 }" data-options="panelHeight:'auto', editable:false ,data: [{value:'三级吊装',text:'三级吊装'},
																																		        {value:'二级吊装',text:'二级吊装'},
																																		        {value:'一级吊装',text:'一级吊装'}] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">安全监护人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M8" name="M8" class="easyui-textbox" value="${dzaq.m8 }" required="true" data-options="validType:['length[0,50]'] " /></td>
					<td class="width-20 active"><label class="pull-right">实施安全教育人：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" id="M9" name="M9" class="easyui-textbox" value="${dzaq.m9 }" required="true" data-options="validType:['length[0,50]'] " /></td>
				</tr>
																
				<tr>
					<td class="width-20 active"><label class="pull-right">作业时间起：</label></td>
					<td class="width-30"><input id="M10" name="M10" class="easyui-datetimebox" value="${dzaq.m10 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
					<td class="width-20 active"><label class="pull-right">作业时间止：</label></td>
					<td class="width-30"><input id="M11" name="M11" class="easyui-datetimebox" value="${dzaq.m11 }" style="width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">吊装内容：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M12" name="M12" class="easyui-textbox" value="${dzaq.m12 }" data-options="validType:['length[0,100]'] " /></td>
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
					<td class="width-30" colspan="3"><input style="width: 100%;height: 30px;" id="M13_1" name="M13_1" class="easyui-textbox" value="${ldyd.M13_1 }" data-options="validType:['length[0,100]'] " /></td>
				</tr>
								
				<c:if test="${not empty dzaq.id}">
					<input type="hidden" name="ID" value="${dzaq.id}" />
					<input type="hidden" name="ID1" value="${dzaq.id1}" />
					<input type="hidden" name="ID2" value="${dzaq.id2}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${dzaq.s1}" pattern="yyyy-MM-dd HH:mm"  />" />
					<input type="hidden" name="S3" value="${dzaq.s3}" />
					<input type="hidden" name="zt" value="${dzaq.zt}" />
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
	var whbs = "${dzaq.M13}";
	var whbsArr = whbs.split(",");
	for(var i=0;i<whbsArr.length;i++){
		$("input[name='M13']:checkbox[value='"+whbsArr[i]+"']").attr('checked','true');
	}
}

$(function(){ 
	$("input[name='M13']:checkbox").css("width","18px");
	$("input[name='M13']:checkbox").css("height","18px");
});

function fxdj(){
	var weight=$("#M6").textbox('getValue');
	if(weight>100){
		$("#M7").combobox('setValue','一级吊装');
	}else if(weight>=40&&weight<=100){
		$("#M7").combobox('setValue','二级吊装');
	}else if(weight<40){
		$("#M7").combobox('setValue','三级吊装');
	}
}

$("#M10").datetimebox({  
    onChange:function(date){ 
   		var ksdate = $("#M10").datetimebox("getValue");   
        var jsdate = $("#M11").datetimebox("getValue");  
        if(jsdate != ''){
        	if(ksdate>jsdate){  
	            $("#M10").datetimebox("setValue","");  
	            layer.msg("不能大于作业截止时间！", {
					time : 2000
				});
	        }  
        }
    }  
});

$("#M11").datetimebox({  
    onChange:function(date){  
        var ksdate = $("#M10").datetimebox("getValue");  
        var jsdate = $("#M11").datetimebox("getValue");  
        if(ksdate != ''){
	        if(jsdate<ksdate){  
	            $("#M11").datetimebox("setValue","");  
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