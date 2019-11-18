<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问通知书管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/xwtz/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${action eq 'update'}">
				<tr>
					<td class="width-15 active"><label class="pull-right">检查编号：</label></td>
					<td class="width-35" colspan="3"><input data-options="readonly:'true' " type="text" id="M0" name="M0" class="easyui-textbox" value="${xwtz.m0 }"  style="height: 30px;width: 100%" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">被询问单位：</label></td>
				    <td class="width-35"><input id="qyname" name="ID2" type="text"  class="easyui-combobox" value="${xwtz.ID2}"  style="height: 30px;width: 100%" data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				    <td class="width-15 active"><label class="pull-right">询问时间：</label></td>
				<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${xwtz.m2 }" style="height: 30px;width: 100%;height: 30px;" data-options="required:'true',editable:false,showSeconds:false" /></td>
				<tr>
					<td class="width-15 active"><label class="pull-right">调查主题：</label></td>
					<td class="width-35" colspan="3" >
						<input style="width:100%;height: 30px;"  id="M1"name="M1"  class="easyui-textbox" value="${xwtz.m1 }" 
						data-options="required:'true',validType:'length[0,100]'" />
				   </td>	
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问地点：</label></td>
				    <td class="width-35" colspan="3"><input id="M3" name="M3" type="text"  class="easyui-textbox" value="${xwtz.m3 }"  data-options="required:'true',validType:'length[0,100]'" style="height: 30px;width: 100%" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">证件材料：</label></td>
				    <td class="width-35" colspan="3">
				    <input type="text" id="M8" name="M8" class="easyui-combobox" value="${xwtz.m8 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', editable:false , multiple:true,data: [
							        {value:'身份证',text:'身份证'},
							        {value:'营业执照',text:'营业执照'},
							         {value:'法定代表人身份证明或者委托书',text:'法定代表人身份证明或者委托书'}
							        	]  "/>
				    </td>
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">其他材料：</label></td>
					<td class="width-35" colspan="3">
			        <input value="${xwtz.m4}" type="text" id="M4" name="M4" class="easyui-textbox" style="height: 80px;width: 100%"
								data-options="multiline:true,validType:'length[0,500]'" />
					</td>
				</tr>
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">联系人：</label></td>
				    <td class="width-35"><input id="M6" name="M6" type="text"  class="easyui-textbox" value="${xwtz.m6 }"  data-options="validType:'length[0,25]'" style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
				    <td class="width-35"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${xwtz.m7 }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr> --%>
				<c:if test="${action eq 'update' or action eq 'create' }">
				<input type="hidden" name="ID3" value="${xwtz.ID3}" />
                    </c:if>
				<c:if test="${not empty xwtz.ID}">
					<input type="hidden" name="ID" value="${xwtz.ID}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${xwtz.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					<input type="hidden" name="M6" value="${xwtz.m6}" />
					<input type="hidden" name="M7" value="${xwtz.m7}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	$(function(){
		var a = '${action}';
		if(a == 'createtemp'){
		$("#qyname").combobox('readonly',false); 
		$("#M1").combobox('readonly',false); 
	    $("#M2").datetimebox("setValue",formate(new Date()));  
		}
		if(a == 'create'){
	    $("#M2").datetimebox("setValue",formate(new Date()));  
	    }
	});
	
	function formate(curr_time){
		 var strDate=curr_time.getFullYear()+"-";     
		    strDate +=curr_time.getMonth()+1+"-";     
		    strDate +=curr_time.getDate()+"-";     
		    strDate +=" "+curr_time.getHours()+":";     
		    strDate +=curr_time.getMinutes()+":";     
		    strDate +=curr_time.getSeconds();  
		    return strDate;
	}
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
	    var flag=true;
			$('#inputForm').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');
			    	if(isValid&&flag){
			    		flag=false;
			    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
			    		return true;
			    	}
					return false; // 返回false终止表单提交
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