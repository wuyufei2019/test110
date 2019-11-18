<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>询问笔录管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/webpage/include/kindeditor.jsp"%>
    <script type="text/javascript" src="${ctx}/static/model/js/aqzf/xzcf/ybcf/xwbl/index.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/xzcf/ybcf/xwbl/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <tr>
					<td class="width-15 active"><label class="pull-right">询问起始时间：</label></td>
					<td class="width-35"><input id="M1" name="M1" class="easyui-datetimebox" value="${xwbl.m1 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				    <td class="width-15 active"><label class="pull-right">询问结束时间：</label></td>
					<td class="width-35"><input id="M2" name="M2" class="easyui-datetimebox" value="${xwbl.m2 }" style="height: 30px;width: 100%;" data-options="editable:false" /></td>
				</tr>
			    <tr>
					<td class="width-15 active"><label class="pull-right">询问次号：</label></td>
					<td class="width-35" ><input  validType="number" type="text" id="M0" name="M0" class="easyui-textbox" value="${xwbl.m0 }"  style="height: 30px;width: 100%" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问地点：</label></td>
					<td class="width-35" colspan="3"><input data-options="validType:'length[0,100]'" type="text" id="M3" name="M3" class="easyui-textbox" value="${xwbl.m3 }"  style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">被询问人姓名：</label></td>
				    <td class="width-35"><input id="M4" name="M4" type="text"  class="easyui-textbox" value="${xwbl.m4}"  data-options="required:true, validType:'length[0,10]'" style="height: 30px;width: 100%" /></td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
				    <td class="width-35"><input id="M5" name="M5" type="text" value="${xwbl.m5}"  class="easyui-combobox"
								data-options="required:true, panelHeight:'auto',editable:false,data: [
						         {value:'男',text:'男'},
						         {value:'女',text:'女'}
						        	]  " style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">年龄：</label></td>
				    <td class="width-35"><input id="M6" name="M6" type="text"  class="easyui-textbox" value="${xwbl.m6 }"  data-options="required:true, validType:['length[0,2]','number']" style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">身份证号：</label></td>
				    <td class="width-35"><input id="M7" name="M7" type="text"  class="easyui-textbox" value="${xwbl.m7 }"  data-options="validType:'idCode'" style="height: 30px;width: 100%" /></td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
				    <td class="width-35" colspan="3"><input id="M8" name="M8" type="text"  class="easyui-textbox" value="${xwbl.m8 }"  data-options="validType:'length[0,100]'" style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
				<c:if test="${action eq 'create' or (zw eq '1')}">
					<td class="width-15 active" colspan="2" style="text-align: center;">
					<input type="radio" value="1" class="i-checks" name="zw" checked="checked"/>职务
					<span style="width: 50px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
					<input type="radio" value="2" class="i-checks"  name="zw"  />工种
					</td>
				    <td class="width-35" colspan="2"><input id="M9" name="M9" type="text"  class="easyui-combobox" value="${xwbl.m9 }"  data-options="validType:'length[0,50]',editable:true,valueField:'id',textField:'text',url:'${ctx }/aqzf/dict/json/zw'" style="height: 30px;width: 100%" /></td>
				</c:if> 
				<c:if test="${action eq 'update' and (zw eq '2')}">
					<td class="width-15 active" colspan="2" style="text-align: center;">
					<input type="radio" value="1" class="i-checks" name="zw" />职务
					<span style="width: 50px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
					<input type="radio" value="2" class="i-checks"  name="zw" checked="checked"/>工种
					</td>
				    <td class="width-35" colspan="2"><input id="M9" name="M9" type="text"  class="easyui-combobox" value="${xwbl.m9 }"  data-options="validType:'length[0,50]',editable:true,valueField:'id',textField:'text',url:'${ctx }/aqzf/dict/json/gz'" style="height: 30px;width: 100%" /></td>
				</c:if>     
				</tr>
				<tr>
				    <td class="width-15 active"><label class="pull-right">电话：</label></td>
				    <td class="width-35" ><input id="M11" name="M11" type="text"  class="easyui-textbox" value="${xwbl.m11 }"  data-options="validType:['length[0,20]','mobileAndTel'] " style="height: 30px;width: 100%" /></td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">住址：</label></td>
					<td class="width-35" colspan="3"><input data-options="validType:'length[0,100]'" type="text" id="M10" name="M10" class="easyui-textbox" value="${xwbl.m10 }"  style="height: 30px;width: 100%" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">询问人：</label></td>
				    <td class="width-35"><input id="M12" name="M12" type="text"  class="easyui-combobox" value="${xwbl.m12 }"  data-options="validType:'length[0,10]', editable:true , multiple:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' " style="height: 30px;width: 100%" /></td>
				    <td class="width-15 active"><label class="pull-right">记录人：</label></td>
				    <td class="width-35"><input id="M13" name="M13" type="text"  class="easyui-combobox" value="${xwbl.m13 }"  data-options="validType:'length[0,10]', editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' " style="height: 30px;width: 100%" /></td>	    
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">在场人员：</label></td>
				    <td class="width-35" colspan="3"><input id="M14" name="M14" type="text"  class="easyui-textbox" value="${xwbl.m14 }"  data-options="validType:'length[0,100]'" style="height: 30px;width: 100%" /></td>
				</tr>
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right">行政执法人员：</label></td>
				    <td class="width-35" colspan="3"><input id="M15" name="M15" type="text"  class="easyui-combobox" value="${xwbl.m15 }"  data-options="panelHeight:'auto', editable:true ,valueField: 'text', multiple:true, textField: 'text',url:'${ctx}/aqzf/zfry/idlist' " style="height: 30px;width: 100%" /></td>
				</tr> --%>
				<tr>
					<td class="width-15 active"><label class="pull-right">调查主题：</label></td>
				    <td class="width-35" colspan="3"><input id="M16" name="M16" type="text"  class="easyui-textbox" value="${xwbl.m16 }"  data-options="required:'true',validType:'length[0,100]'" style="height: 30px;width: 100%" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">询问记录：</label></td>
				    <td class="width-85" colspan="3"><textarea id="textarea_kindM17" name="M17" style="width:100%;height:300px;visibility:hidden;">${xwbl.m17 }</textarea></td>
				</tr>
				
				<input type="hidden" name="ID2" value="${xwbl.ID2}" />
				<c:if test="${not empty xwbl.ID}">
					<input type="hidden" name="ID" value="${xwbl.ID}" />
					<input type="hidden" name="M15" value="${xwbl.m15}" />
					<input type="hidden" name="S1"  value="<fmt:formatDate value="${xwbl.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$('.i-checks').on('ifChecked', function(event){    
	    var selectedvalue = $(event.target).val();
		if (selectedvalue == 1) {
			$("#M9").combobox({
			    validType:'length[0,50]',
			    editable:true,
			    valueField:'id',
			    textField:'text',url:'${ctx }/aqzf/dict/json/zw'
			});
		} 
		if (selectedvalue == 2){
			$("#M9").combobox({
			    validType:'length[0,50]',
			    editable:true,
			    valueField:'id',
			    textField:'text',url:'${ctx }/aqzf/dict/json/gz'
			});
		}
	});  
	
	$(function(){
		if('${action}'=='create'){
			var text="";//询问问题列表
			$.ajax({
  				type: 'post',
  			    url:'${ctx }/aqzf/dict/json/ggwt',
  			    dataType:'json',
  				success:function(data){
  					$.each(data, function(i, item) {
  						var count=i+1;
  						var question=count+"、"+item.text+"<br/>答：<br/>"
  						text+=question;
  					});
  					KindEditor.html("#textarea_kindM17", text);
  					//$("#textarea_kindM17").val(text);
  				}
  			});
		}
	    
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
		
		//富文本渲染
		onloadEditor();
	});
	
</script>
</body>
</html>