<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" action="${ctx}/xzcf/jycf/cssb/${action}"  method="post" class="form-horizontal" >
		<tbody>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		
				<tr>
				<td class="width-15 active"><label class="pull-right">案件名称：</label></td>
				<td class="width-35" colspan="3"><input style="width:100%;height: 30px;"
						name="id1"  class="easyui-combobox" value="${jce.id1 }"
						data-options="panelHeight:'auto', required:'true',valueField: 'id1',textField: 'text',url:'${ctx}/xzcf/jycf/cfgz/casenamelist'" />
				</td>
				</tr>
		 		<tr>
		 			<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35"><input name="startdate" class="easyui-datetimebox" value="${jce.startdate }" style="width: 100%;height: 30px;" data-options="required:true,showSeconds:false" /></td>	
					<td class="width-15 active"><label class="pull-right">结束时间：</label></td>
					<td class="width-35"><input name="enddate" class="easyui-datetimebox" value="${jce.enddate }" style="width: 100%;height: 30px;"data-options="required:true,showSeconds:false" /></td>	
		 		</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35"  colspan="3"><input name="address" class="easyui-textbox" value="${jce.address }" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" /></td>
				</tr>
				<tr>
					<td class="width-15 active" ><label class="pull-right">陈述申辩人:</label></td>
					<td class="width-35"  >
					<input name="name"  class="easyui-textbox" value="${jce.name }" style="width: 100%;height: 30px;" data-options="validType:['length[0,50]'],required:'true'"/>
					</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35"><input name="sex" class="easyui-combobox" value="${jce.sex }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', data:[{value:'1',text:'男'},{value:'0',text:'女'}]"/></td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35" ><input name="duty" class="easyui-textbox"  value="${jce.duty }" style="width: 100%;height: 30px;" data-options="validType:['length[0,25]'] "/></td>
					<td class="width-15 active"><label class="pull-right">电话：</label></td>
					<td class="width-35" ><input name="phone" class="easyui-textbox"  value="${jce.phone }" style="width: 100%;height: 30px;" validtype="mobile"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-85" colspan="3">
					<input name="workunit" type="text" value="${jce.workunit }"   class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系地址：</label></td>
					<td class="width-85" colspan="3">
					<input name="contactaddress" type="text" value="${jce.contactaddress }"   class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:['length[0,100]']" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">邮编：</label></td>
					<td class="width-85">
					<input name="ybcode" type="text" value="${jce.ybcode }"   alidtype="zipcode" class="easyui-textbox" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				
				 <tr>
					<td class="width-15 active"><label class="pull-right">承办人：</label></td>
					<td class="width-35" ><input name="director" class="easyui-textbox"  value="${jce.director }" style="width: 100%;height: 30px;"/></td>
					<td class="width-15 active"><label class="pull-right">记录人：</label></td>
					<td class="width-35" ><input name="recorder" class="easyui-textbox"  value="${jce.recorder }" style="width: 100%;height: 30px;" /></td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-35" ><input name="enforcer1" class="easyui-textbox"  value="${jce.enforcer1 }" style="width: 100%;height: 30px;" data-options="
			        prompt: '右侧按钮添加执法人员',
			        iconWidth: 22,icons: [{
				    iconCls:'icon-add',handler: function(){
					$('#enforceradd').show();
				    }}] "/></td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35" ><input name="identity1" validType="idCode" class="easyui-textbox"  value="${jce.identity1 }" style="width: 100%;height: 30px;" /></td>
				</tr>
				
				<tr id ="enforceradd" style="display:none" >
					<td class="width-15 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-35" ><input name="enforcer2" class="easyui-textbox"  value="${jce.enforcer2 }" style="width:225px;height: 30px;"  data-options="
			        prompt: '移除执法人员',
			        iconWidth: 22,icons: [{
				    iconCls:'icon-remove',handler: function(){
					$('#enforceradd').hide();
				    }}] "/></td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35" ><input name="identity2" validType="idCode" class="easyui-textbox"  value="${jce.identity2 }" style="width: 225px;height: 30px;" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">申辩记录：</label></td>
					<td class="width-85" colspan="3">
					<input name="cssbrecord" type="text" value="${jce.cssbrecord }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="validType:['length[0,2500]']" />
					</td>
				</tr>
				<!-- 隐藏域 -->
				 <input type='hidden' name="S1" value="<fmt:formatDate value="${jce.s1}"/>"/>
				 <input type="hidden" name="ID" value="${jce.ID}" />
		</table>
	</div>
	</tbody>
</form>
<script type="text/javascript">
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