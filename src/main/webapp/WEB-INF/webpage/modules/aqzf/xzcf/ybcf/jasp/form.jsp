<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加结案审批记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/ybcf/jasp/${action}"  method="post" class="form-horizontal" >
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<c:if test="${action eq 'updateSub'}">
				<tr>
					<td class="width-20 active"><label class="pull-right">编号：</label></td>
					<td class="width-30"  ><input name="number" class="easyui-textbox" editable="false" value="${yje.number }" style="width: 100%;height: 30px;" /></td>
                </tr>
				</c:if>
				<tr>
						<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
						<td class="width-30"  colspan="3" >
							<input value="${yje.casename }" id="casename" name="casename" style="width: 100%;height: 30px;"
									class="easyui-textbox" />
						</td>
				</tr>
                    
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
					<td class="width-30"><input id="qypunishname" name="punishname" class="easyui-textbox"  value="${yje.punishname }" style="width: 100%;height: 30px;"/></td>
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-30" ><input id="qyaddress" name="qyaddress"  class="easyui-textbox"  value="${yje.qyaddress }" style="width: 100%;height: 30px;" /></td>
				
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-30"><input id="legal" name="legal" class="easyui-textbox"  value="${yje.legal }" style="width: 100%;height: 30px;"/></td>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" ><input id="duty" name="duty"  class="easyui-textbox"  value="${yje.duty }" style="width: 100%;height: 30px;" /></td>
				
				</tr>
				
				  <tr name="company">
					<td class="width-20 active"><label class="pull-right">单位邮编：</label></td>
					<td class="width-30">
					<input name="qyyb" type="text" id="qyyb" value="${yje.qyyb }"  class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'ZIP'"/></td>
				</tr>
				<tr name="person" >
					<td class="width-20 active"><label class="pull-right">被处罚人：</label></td>
					<td class="width-30 "><input id="grpunishname" name="punishname" class="easyui-textbox"  value="${yje.punishname }" style="width: 100%;height: 30px;"/></td>
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="pmobile" name="mobile" class="easyui-textbox"  value="${yje.mobile }" style="width: 100%;height: 30px;" validtype="mobile"/></td>
				
				</tr>
				
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">年龄：</label></td>
					<td class="width-30" ><input id="perage" name="age"  class="easyui-textbox"  value="${yje.age }" style="width: 100%;height: 30px;" data-options="validType:['length[0,2]','number']" /></td>
					<td class="width-20 active"><label class="pull-right">性别：</label></td>
					<td class="width-30"><input id="persex" name="sex" class="easyui-combobox" value="${yje.sex }" style="width:100%;height: 30px;"data-options="panelHeight:'auto',data: [{value:'男',text:'男'},
                                                {value:'女',text:'女'}]" /></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">所在单位：</label></td>
					<td class="width-30" colspan="3" ><input id="dwname" name="dwname"  class="easyui-textbox"  value="${yje.dwname }" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-30" colspan="3" ><input id="dwaddress" name="dwaddress" class="easyui-textbox" value="${yje.dwaddress }" style="width:100%;height: 30px;" /></td>	
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-30" ><input id="paddress"name="address" class="easyui-textbox" value="${yje.address }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
					<td class="width-20 active"><label class="pull-right">家庭邮编：</label></td>
					<td class="width-30"><input id="jtyb" name="jtyb" class="easyui-textbox" value="${yje.jtyb }" style="width:100%;height: 30px;"  data-options="validType:'ZIP'" /></td>	
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">处理结果：</label></td>
					<td class="width-30" colspan="3" ><input id="result"name="result" value="${yje.result }"   class="easyui-textbox" style="width: 100%;height: 80px;"data-options="multiline:true,multiple:true,validType:['length[0,500]']"/></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">执行情况：</label></td>
					<td class="width-30"colspan="3"><input id="exeucondition"name="exeucondition" class="easyui-textbox" value="${yje.exeucondition }" style="width: 100%;height: 80px;"   data-options="multiline:true,multiple:true,validType:['length[0,500]']"/></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="cbr1" name="cbr1" class="easyui-combobox" value="${yje.cbr1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">承办人：</label></td>
					<td class="width-30"><input type="text" id="cbr2" name="cbr2" class="easyui-combobox" value="${yje.cbr2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>    
                    
                <tr>
                	<td class="width-20 active"><label class="pull-right">承办日期：</label></td>
					<td class="width-30"><input id="cbsj" name="cbsj" class="easyui-datebox" value="${yje.cbsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
                </tr>
                    
                <tr>
					<td class="width-20 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yje.shyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="shyj" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="shyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="shyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审核人：</label></td>
					<td class="width-30"><input type="text" id="shr" name="shr" class="easyui-combobox" value="${yje.shr }" style="width: 100%;height: 30px;" data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-30"><input id="shsj" name="shsj" class="easyui-datebox" value="${yje.shsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-30" colspan="3">
						<c:choose>
						<c:when test="${yje.spyj=='0'}">
							<input type="radio" value="1" class="i-checks" name="spyj" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="spyj" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="spyj" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	
				<tr>
					<td class="width-20 active"><label class="pull-right">审批人：</label></td>
					<td class="width-30"><input type="text" name="spr" class="easyui-combobox" value="${yje.spr }"  data-options="panelHeight:'150px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-30"><input id="spsj" name="spsj" class="easyui-datebox" value="${yje.spsj }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
				</tr>
				
				
				
				
				<!-- 隐藏域 -->
				
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${yje.s1}"/>"/>
				 <input type="hidden" name="ID" value="${yje.ID}" />
				 <input type='hidden' name="id1" value="${yje.id1}"/>
                 <input type='hidden' name="percomflag" value="${yje.percomflag}"/>
		</table>
	</tbody>
</form><script type="text/javascript">

</script>
<script type="text/javascript">
$(function(){
	
	if('${action}'=='createSub'){
		if('${yje.percomflag}'==1){
		comResult();
		}else{
			personResult();
		}
	$("input[name='id1']").val('${id1}');
	}else{
		if('${yje.percomflag}'=='1'){
			comResult();
		}else{
			personResult();
		}
	}
});

function  personResult(){
	$("[name='person']").show();
	$("[name='company']").hide();
	$("#qypunishname").combobox("disable"); 
	$("#legal").textbox("disable"); 
	$("#qyyb").textbox("disable"); 
	$("#duty").textbox("disable"); 
	$("#qyaddress").textbox("disable"); 
	$('#grpunishname').combobox('enable');
	$('#pmobile').textbox('enable');
	$('#perage').textbox('enable');
	$('#persex').textbox('enable');
	$('#perduty').textbox('enable');
	$('#dwname').textbox('enable');
	$('#dwaddress').textbox('enable');
	$('#paddress').textbox('enable');
	$('#jtyb').textbox('enable');
}

function  comResult(){
	$("[name='person']").hide();
	$("[name='company']").show();
	$("#qypunishname").combobox("enable"); 
	$("#legal").textbox("enable"); 
	$("#qyyb").textbox("enable"); 
	$("#duty").textbox("enable"); 
	$("#qyaddress").textbox("enable"); 
	$('#grpunishname').combobox('disable');
	$('#pmobile').textbox('disable');
	$('#perage').textbox('disable');
	$('#persex').textbox('disable');
	$('#perduty').textbox('disable');
	$('#dwname').textbox('disable');
	$('#dwaddress').textbox('disable');
	$('#paddress').textbox('disable');
	$('#jtyb').textbox('disable'); 
}
function format(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
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
			return false; //返回false终止表单提交
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