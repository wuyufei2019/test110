<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常檢查表庫</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/rcjcbk/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-20 active"><label class="pull-right">类别：</label></td>
					<td class="width-80" ><input name="M1" id="M1" class="easyui-combobox" value="${rcjcbk.m1 }" style="width: 100%;height: 30px;" data-options="editable:false, validType:'length[0,100]', data: [
								        {value:'公司级',text:'公司级'},
								        {value:'车间级',text:'车间级'},
								        {value:'班组级',text:'班组级'},
								        {value:'季节性',text:'季节性'},
								        {value:'节假日',text:'节假日'},
								        {value:'专项检查',text:'专项检查'},
								        {value:'其他',text:'其他'}] " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">单元：</label></td>
					<td class="width-80" ><input name="M2" id="M2" class="easyui-combobox" value="${rcjcbk.m2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'120px', editable:true ,valueField: 'text',textField: 'text', validType:'length[0,250]' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-80" ><input name="M3" id="M3" type="text" class="easyui-textbox" value="${rcjcbk.m3 }" style="width: 100%;height: 80px;"  data-options="multiline:'true', validType:'length[0,500]' " /></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">依据：</label></td>
					<td class="width-80" ><input name="M4" type="text" value="${rcjcbk.m4}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,250]' "></td>					
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-80" ><input name="M5" type="text" value="${rcjcbk.m5}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,250]' "></td>					
				</tr>
				
				<c:if test="${not empty rcjcbk.ID}">
					<input type="hidden" name="ID" value="${rcjcbk.ID}" />
					<input type="hidden" name="ID1" value="${rcjcbk.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${rcjcbk.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${rcjcbk.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	
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
		    	parent.$('#M1').combobox('reload');
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
	});
	
	$("#M1").combobox({
		panelHeight:'120px',
		onLoadSuccess:function (param, success, error) {
		   var lx=$('#M1').combobox('getValue');
		    if(lx!=''){
			    $.ajax({  
			       url:'${ctx}/yhpc/rcjcbk/unitlist',
		           data:{'lx':lx},
		           dataType : 'json',
		           type : 'POST',
		           success: function (data){
		           	$('#M2').combobox('loadData', data); 
		           	}
			    });
		    }
		 },
		 onSelect: function(rec){
		  	$.ajax({
		           url:'${ctx}/yhpc/rcjcbk/unitlist',
		           data:{'lx':rec.text},
		           dataType : 'json',
		           type : 'POST',
		           success: function (data){
		           		$('#M2').combobox('setValue', '');
		           		$('#M2').combobox('loadData', data);
						$('#M3').val('');
		           }
		     });
		 }
	});

</script>
</body>
</html>