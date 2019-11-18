<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>检查方案管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/jcfa/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<%-- <tr>
					<td class="width-15 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-35" colspan="3"><input readonly="true" type="text" id="M0" name="M0" class="easyui-textbox" value="${jcfa.m0 }"  style="height: 30px;width: 100%" /></td>
				</tr> --%>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">被检查单位：</label></td>
					<c:if test="${action eq 'create'}">
						<input type="hidden" name="ID1" value="${plan.ID1}" />
						<input type="hidden" name="ID2" value="${plan.ID2}" />	
						<input type="hidden" id="M6" name="M6" value="${jcfa.m6}" />	
						<td class="width-35" colspan="3"><input id="dw" type="text"  class="easyui-textbox" value="${qyname }"  data-options="readonly:'true' " style="height: 30px;width: 100%" /></td>
					</c:if>
					<c:if test="${action eq 'update'}">
						<td class="width-35" colspan="3"><input id="dw" type="text"  class="easyui-textbox" value="${jcfa.qyname }"  data-options="readonly:'true' " style="height: 30px;width: 100%" /></td>
					</c:if>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">地址：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" id="M1" name="M1" class="easyui-textbox" value="${jcfa.m1}"  style="height: 30px;width: 100%;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
					<td class="width-35"><input type="text" id="M2" name="M2" class="easyui-textbox" value="${jcfa.m2 }"  style="height: 30px;width: 100%" /></td>
					<td class="width-15 active"><label class="pull-right">所属行业：</label></td>
					<td class="width-35"><input type="text" id="M3" name="M3" class="easyui-textbox" style="height: 30px; width: 100%;" value="${jcfa.m3}" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35" ><input id="M4" name="M4" class="easyui-datebox" value="${jcfa.m4 }" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
					<td class="width-15 active"><label class="pull-right">行政执法人员：</label></td>
					<td class="width-35" ><input type="text" id="M5" name="M5" class="easyui-combobox" value="${jcfa.m5 }" style="width: 100%;height: 30px;" data-options="required:'true', panelHeight:'170px', editable:false ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>				
				</tr>
								
				<tr>
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<ul id="tt" style="width:400px" ></ul>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查方式：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M7" class="easyui-combobox" value="${jcfa.m7 }"  data-options="required:'true', editable:false ,panelHeight:'auto', 
																												data:[{value:'例行检查',text:'例行检查'},
																												{value:'专项检查',text:'专项检查'},
																												{value:'执法检查',text:'执法检查'},
																												{value:'突击检查',text:'突击检查'},
																												{value:'其他检查',text:'其他检查'}]" style="height: 30px;width: 100%" />
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审核意见：</label></td>
					<td class="width-35" colspan="3">
						<c:choose>
						<c:when test="${jcfa.m8=='0'}">
							<input type="radio" value="1" class="i-checks" name="M8" />同意
							<input type="radio" value="0" class="i-checks"  name="M8" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M8" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="M8" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审核日期：</label></td>
					<td class="width-35"><input id="M8_2" name="M8_2" class="easyui-datebox" value="${jcfa.m8_2 }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">审核人：</label></td>
					<td class="width-35"><input type="text" id="M8_1" name="M8_1" class="easyui-combobox" value="${jcfa.m8_1 }" style="width: 100%;height: 30px;" data-options="required:'true', panelHeight:'170px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">审批意见：</label></td>
					<td class="width-35" colspan="3">
						<c:choose>
						<c:when test="${jcfa.m9=='0'}">
							<input type="radio" value="1" class="i-checks" name="M9" />同意
							<input type="radio" value="0" class="i-checks"  name="M9" checked="checked" />不同意
						</c:when>
						<c:otherwise>
							<input type="radio" value="1" class="i-checks" name="M9" checked="checked" />同意
							<input type="radio" value="0" class="i-checks"  name="M9" />不同意
						</c:otherwise>
						</c:choose>
					</td>
				</tr>	

				<tr>
					<td class="width-15 active"><label class="pull-right">审批日期：</label></td>
					<td class="width-35"><input id="M9_2" id="M9_2" name="M9_2" class="easyui-datebox" value="${jcfa.m9_2 }" style="width: 100%;height: 30px;" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">审批人：</label></td>
					<td class="width-35"><input type="text" name="M9_1" class="easyui-combobox" value="${jcfa.m9_1 }"  data-options="required:'true', panelHeight:'170px', editable:true ,valueField: 'text', textField: 'text',url:'${ctx}/aqzf/zfry/idlist'" style="height: 30px;width: 100%" /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
						<input type="text" name="M10" class="easyui-textbox" value="${jcfa.m10 }"  data-options="multiline:true , validType:'length[0,250]'" style="width: 100%;height: 80px;" />
					</td>
				</tr>
												
				<c:if test="${not empty jcfa.id}">
					<input type="hidden" name="M0" value="${jcfa.m0}" />
					<input type="hidden" name="ID" value="${jcfa.id}" />
					<input type="hidden" name="ID1" value="${jcfa.id1}" />
					<input type="hidden" name="ID2" value="${jcfa.id2}" />
					<input type="hidden" id="M6" name="M6" value="${jcfa.m6}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${jcfa.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${jcfa.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;

//得到当前日期
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}  
 
function doSubmit(){
	var nodes = $('#tt').tree('getChecked');
		var ids="";
	for(var i=0;i<nodes.length;i++){
		if(ids==""){
			ids=nodes[i].id;
		}else{
			if(nodes[i].id!=0){
				ids=ids+","+nodes[i].id;
			}
		}
	}
	$("#M6").val(ids);
	$("#inputForm").submit(); 
}

$(function(){
    //第一次添加时,设置默认时间  ;
	var curr_time = new Date();
	if('${action}'=='create'){    
		$("#M4").datebox("setValue",myformatter(curr_time)); 	
		$("#M8_2").datebox("setValue",myformatter(curr_time)); 
		$("#M9_2").datebox("setValue",myformatter(curr_time)); 
	}
	$('#tt').tree({
    	url: ctx+'/aqzf/jcfa/jcnr',
    	checkbox: true,
    	loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
    	},
    	onLoadSuccess:function(){
    		if ('${action}' == 'update') {
			var m6=	$("#M6").val();
			if(m6!=null&&m6!=""){
			var array = m6.split(",");
    		for(var i=0;i<array.length;i++)  
       			{  
         			var node = $('#tt').tree('find',array[i]);
         			$('#tt').tree('check',node.target);  
       			} 
       		} 	
		  }
    	} 
	});
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
	
	if('${action}' == 'create'){
		$('#M1').textbox("setValue", '${address}');
		$('#M2').textbox("setValue", '${lxr}');
		var leibie = '${leibie}';
		if(leibie == '1'){
			$('#M3').textbox("setValue", "工贸");
		}else if(leibie == '2'){
			$('#M3').textbox("setValue", "化工");
		}
	}

});


</script>
</body>
</html>