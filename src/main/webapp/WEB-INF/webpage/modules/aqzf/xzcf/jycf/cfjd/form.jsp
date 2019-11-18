<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/jycf/cfjd/${action}"  method="post" class="form-horizontal" >
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		
				<tr>
				<td class="width-20 active"><label class="pull-right">案件名称：</label></td>
				<td class="width-30" colspan="3" >
				<input style="width:100%;height: 30px;" name="id1"  class="easyui-combobox" value="${jce.id1 }"
						data-options="panelHeight:'auto', required:'true',valueField: 'id1',textField: 'text',url:'${ctx}/xzcf/jycf/cfgz/casenamelist'" />
				</td>
				</tr>
				 <tr>
       				<td class="width-20 active"><label class="pull-right">受处罚类型：</label></td>
        			<td class="width-30" style="text-align:left">
           			 <span>
              		  <input type="radio" name="percomflag"style="width:15px;height:15px;margin-bottom:3px;" checked="true"  value="1">单位</input>
               		  <input type="radio" name="percomflag"style="width:15px;height:15px;margin-bottom:3px;margin-left:10px"  value="0">个人</input>
            		 </span>
        		</td>
        		<td class="width-20 active"><label class="pull-right">处罚时间：</label></td>
					<td class="width-30"><input name="punishdate" class="easyui-datebox" value="${jce.punishdate }" style="width: 100%;height: 30px;" /></td>	
   			   </tr>
				<tr name="company">
						<td class="width-20 active"><label class="pull-right">被处罚单位：</label></td>
						<td class="width-30" colspan="3">
							<input value="${jce.punishname }" id="qyname" name="punishname" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
				</tr>
				
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">法定代表人：</label></td>
					<td class="width-30"><input id="legal" name="legal" class="easyui-textbox"  value="${jce.legal }" style="width: 100%;height: 30px;"/></td>
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" ><input id="duty" name="duty"  class="easyui-textbox"  value="${jce.duty }" style="width: 100%;height: 30px;" /></td>
				
				</tr>
				
				  <tr name="company">
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="mobile" name="mobile" class="easyui-textbox"  value="${jce.mobile }" style="width: 100%;height: 30px;" validtype="mobile"/></td>
					<td class="width-20 active"><label class="pull-right">邮编：</label></td>
					<td class="width-30">
					<input name="ybcode" type="text" id="ybcode" value="${jce.ybcode }"  class="easyui-textbox" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr name="company">
					<td class="width-20 active"><label class="pull-right">地址：</label></td>
					<td class="width-30"  colspan="3"  ><input id="address"name="address" class="easyui-textbox" value="${jce.address }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				
				
				<tr name="person">
						<td class="width-20 active"><label class="pull-right">被处罚人姓名：</label></td>
						<td class="width-30" colspan="3">
							<input value="${jce.punishname }"  id="grname" name="punishname" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/ygxx/ygxxlist/3'" />
						</td>
				</tr>
				
				<tr name="person">
				<td class="width-20 active"><label class="pull-right">性别：</label></td>
					<td class="width-30"><input id="persex" name="sex" class="easyui-textbox" value="${jce.sex }" style="width:100%;height: 30px;" /></td>	
					<td class="width-20 active"><label class="pull-right">年龄：</label></td>
					<td class="width-30" ><input id="perage" name="age"  class="easyui-textbox"  value="${jce.age }" style="width: 100%;height: 30px;" /></td>
				</tr>
				
				  <tr name="person">
					<td class="width-20 active"><label class="pull-right">电话：</label></td>
					<td class="width-30" ><input id="pmobile" name="mobile" class="easyui-textbox"  value="${jce.mobile }" style="width: 100%;height: 30px;" validtype="mobile"/></td>
					<td class="width-20 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-30" ><input id="identity"name="identity1" validType="idCode" class="easyui-textbox"  value="${jce.identity1 }" style="width: 100%;height: 30px;" /></td>
				</tr>
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">家庭地址：</label></td>
					<td class="width-30"  colspan="3"  ><input id="paddress"name="address" class="easyui-textbox" value="${jce.address }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				
				<tr name="person">
				<td class="width-20 active"><label class="pull-right">所在单位：</label></td>
					<td class="width-30"><input id="dwname" name="dwname" class="easyui-textbox" value="${jce.dwname }" style="width: 100%;height: 30px;" /></td>	
					<td class="width-20 active"><label class="pull-right">职务：</label></td>
					<td class="width-30" ><input id="perduty" name="duty"  class="easyui-textbox"  value="${jce.duty }" style="width: 100%;height: 30px;" /></td>
				</tr>
				
				<tr name="person">
					<td class="width-20 active"><label class="pull-right">单位地址：</label></td>
					<td class="width-30"  colspan="3"  ><input id="dwaddress" name="dwaddress" class="easyui-textbox" value="${jce.dwaddress }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				
				<tr>
				<td class="width-20 active"><label class="pull-right">缴费类型：</label></td>
        			<td class="width-30" style="text-align:left">
           			 <span>
              		  <input type="radio" style="width:15px;height:15px;margin-bottom:3px;"  name="jftype"  value="1">当场缴费</input>
               		  <input type="radio"style="width:15px;height:15px;margin-bottom:3px;margin-left:10px" checked="true" name="jftype" value="0">银行缴费</input>
            		 </span>
        		</td>
				</tr>
				
				
				<tr>
					<td class="width-20 active"><label class="pull-right">违法事实和证据：</label></td>
					<td class="width-30" colspan="3" ><input name="illegalactandevidence" value="${jce.illegalactandevidence }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:['length[0,2500]']" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违反条款：</label></td>
					<td class="width-30"    colspan="3"><input name="unlaw" class="easyui-textbox" value="${jce.unlaw }" style="width: 100%;height: 80px;" data-options="multiline:true,validType:['length[0,500]']" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">惩罚依据：</label></td>
					<td class="width-30"  colspan="3" ><input name="enlaw" class="easyui-textbox" value="${jce.enlaw }" style="width: 100%;height: 80px;" data-options="multiline:true,validType:['length[0,500]']" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">处罚结果：</label></td>
					<td class="width-30"   colspan="3"><input name="punishresult" class="easyui-textbox" value="${jce.punishresult }" style="width: 100%;height: 80px;" data-options="multiline:true,validType:['length[0,500]']" /></td>
				</tr>
				
				<!-- 隐藏域 -->
				
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${jce.s1}"/>"/>
				 <input type="hidden" name="ID" value="${jce.ID}" />
				 <input type='hidden' name="id2" value="11"/>
				 <input type='hidden' name="number" value="${jce.number}"/>
		</table>
	</tbody>
</form><script type="text/javascript">

</script>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var a='${action}';

$(function(){
	if(a=="createSub"){
		comResult();
	}else{
		if('${flag}'=='1'){
			$("input[name='percomflag']").get(0).checked=true; 
			comResult();
			}else{
			$("input[name='percomflag']").get(1).checked=true; 
			personResult();
		}
	}
});
function  personResult(){
	$("[name='person']").show();
	$("[name='company']").hide();
	$("#qyname").combobox("disable"); 
	$("#mobile").textbox("disable"); 
	$("#duty").textbox("disable"); 
	$("#address").textbox("disable"); 
	$('#grname').combobox('enable');
	$('#pmobile').textbox('enable');
	$('#perduty').textbox('enable');
	$('#paddress').textbox('enable');
}

function  comResult(){
	$("[name='person']").hide();
	$("[name='company']").show();
	$("#qyname").combobox("enable" ); 
	$("#mobile").textbox("enable" ); 
	$("#duty").textbox("enable" ); 
	$("#address").textbox("enable" ); 
	$("#grname").combobox("disable"); 
	$("#pmobile").textbox("disable"); 
	$("#perduty").textbox("disable"); 
	$("#paddress").textbox("disable"); 
}

$("#qyname").combobox({
	onSelect: function(){
		var id=$('#qyname').combobox('getValue');
		$.ajax({
			type:'get',
			url: '${ctx}/bis/qyjbxx/qydetail/'+id,
			success : function(data) {
				var d=JSON.parse(data);
				$("#legal").textbox('setValue',d.m5);	
				$("#mobile").textbox('setValue',d.m6);	
				$("#ybcode").textbox('setValue',d.m9);	
				$("#address").textbox('setValue',d.m8);	
				
			}
		});
	}
});
$("#grname").combobox({
	onSelect: function(){
		var id=$('#grname').combobox('getValue');
		$.ajax({
			type:'get',
			url: '${ctx}/bis/ygxx/ygdetail/'+id,
			success : function(data) {
				var d=JSON.parse(data);
				
				$("#persex").textbox('setValue',d.m3);	
				//$("#perage").textbox('setValue',d.m6);	
				$("#pmobile").textbox('setValue',d.m9);	
				$("#identity").textbox('setValue',d.m8);
				$("#perage").textbox('setValue',new Date().getFullYear()-new Date(d.m10).getFullYear());	
				$("#dwname").textbox('setValue',d.m1);	
				//$("#perduty").textbox('setValue',d.m7);	
				$("#dwaddress").textbox('setValue',d.m33);	
			}
		});
	}
});


$(":radio").change(function() {
	var flag=$('input:radio:checked').val();
    if(flag=="1"){
   		//$("#grname").attr("disabled",true); 
   		//$("#qyname").attr("disabled",false); 
   		comResult();
    }
    if(flag=="0"){
    	personResult();
		//$("#grname").attr("disabled",false); 
		//'$("#pmobile").attr("disabled",false); 
		//$("#perduty").attr("disabled",false); 
		//$("#paddress").attr("disabled",false); 
    }
});



function doSubmit(){
	 /* var flag=$('input:radio:checked').val();
	alert(flag);
	if(flag=="0"){
		$("#qyname").attr("disabled",true); 
		$("#mobile").attr("disabled",true); 
		$("#duty").attr("disabled",true); 
		$("#address").attr("disabled",true); 
	}else{
		$("#grname").attr("disabled",true); 
		$("#pmobile").attr("disabled",true); 
		$("#perduty").attr("disabled",true); 
		$("#paddress").attr("disabled",true); 
	}  */
	if($('input:radio:checked').val()==1)
	$("#qyname").combobox("setValue",$("#qyname").combobox("getText"));
	else
	$("#grname").combobox("setValue",$("#grname").combobox("getText"));	
	$("#inputForm").serializeObject();
	d=$("#inputForm").serializeObject();
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