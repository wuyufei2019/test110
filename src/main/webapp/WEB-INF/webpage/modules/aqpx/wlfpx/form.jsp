<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>外来方培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqpx/wlfpx/regist"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15 active"><label class="pull-right">培训类别：</label></td>
					<td class="width-85" colspan="3">
					<input id="M1_4" name="M1_4" type="text" class="easyui-combobox" style="width: 100%;height: 30px;" data-options="required:'true',editable: false,panelHeight:'150',data:[
										{value: '访客', text: '访客'},
										{value: '建筑施工承包商', text: '建筑施工承包商'},
										{value: '行政服务外包商', text: '行政服务外包商'},
										{value: '设备维修维保服务商', text: '设备维修维保服务商'},
										{value: '物流服务商', text: '物流服务商'}
										]">
					</td>					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">姓名：</label></td>
					<td class="width-35"><input id="M1_1" name="M1_1" style="width: 100%;height: 30px;" class="easyui-textbox"
								 data-options="required:'true'"  /></td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35"><input id="M1_2" name="M1_2"  class="easyui-textbox"
								 style="width: 100%;height: 30px;" data-options="required:'true',validType:'idCode'" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">所属单位：</label></td>
					<td class="width-35"><input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;"
								data-options="required:'true'"/></td>
					<td class="width-15 active"><label class="pull-right">岗位：</label></td>
					<td class="width-35"><input id="M1_3" name="M1_3" class="easyui-textbox"
								style="width: 100%;height: 30px;" data-options="required:'true'" /></td>
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var validateForm;	

function doSubmit(){
	$("#inputForm").submit(); 
}

$(function(){
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },
	    success:function(data){
	    	parent.location.reload() 
	    	parent.layer.close(index);//关闭对话框。
	    }     
	});

});	
</script>
</body>
</html>