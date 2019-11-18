<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>相关方违规管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-20 active"><label class="pull-right">相关方单位名称：</label></td>
					<td class="width-30" colspan="3">
						${pddw}
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">违规日期：</label></td>
					<td class="width-30"><fmt:formatDate value="${xgfwg.m1 }" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
					<td class="width-20 active"><label class="pull-right">相关责任人：</label></td>
					<td class="width-30" colspan="3">
						${xgfwg.m3 }
					</td>
				</tr>

		         <tr>
		            <td class="width-20 active"><label class="pull-right">分类：</label></td>
		            <td class="width-30" >${xgfwg.type }</td>
		         </tr>

		         <tr>
		            <td class="width-20 active"><label class="pull-right">考评内容：</label></td>
		            <td class="width-30" colspan="3">${xgfwg.m2 }</td>
		         </tr>
		         		         
				<tr>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>扣分分值：</label></td>
					<td class="width-30">${xgfwg.m4 }</td>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>考核金额：</label></td>
					<td class="width-30">${xgfwg.m5 }</td>						
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>评定人：</label></td>
					<td class="width-30">${xgfwg.pdry }</td>						
				</tr>
								
				<tr>
					<td class="width-20 active"><label class="pull-right"><font color="red"></font>备注：</label></td>
					<td class="width-30" colspan="3">${xgfwg.m6 }</td>
				</tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	$("#type1").combobox({
		 valueField: 'text',
		 textField: 'text',
         url:ctx+'/zyaqgl/xgfwg/findType',
         dataType : 'json',
         type : 'POST',
		 onSelect: function(rec){
		  	$.ajax({
		  		   url:ctx+'/zyaqgl/xgfwg/findContent',
		           data:{'type':rec.text},
		           dataType : 'json',
		           type : 'POST',
		           success: function (data){
		        	   $('#M2').combobox('setValue', ''); 
		        	   $('#M2').combobox('loadData', data); 
		           	   $('#M4').textbox('setValue','');
		           	   $('#M5').textbox('setValue','');
		           }
		     });
	    }
	});
	
	$("#M2").combobox({
		 valueField: 'text',
		 textField: 'text',
		 onSelect: function(rec){
			 	var type=$('#type1').combobox('getValue');
			  	$.ajax({
			  		   url:ctx+'/zyaqgl/xgfwg/findBzInfo',
			           data:{'type':type, 'content':rec.text},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			           		$('#M4').textbox('setValue',data.m3);
			           		$('#M5').textbox('setValue',data.m4);
			           }
			     });
		 }
	});
	
	if('${action}' == 'create'){
		$("#ID2").val(${dwid});
		$("#pdry").val('${pdry}');
	}
	

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
</script>
</body>
</html>