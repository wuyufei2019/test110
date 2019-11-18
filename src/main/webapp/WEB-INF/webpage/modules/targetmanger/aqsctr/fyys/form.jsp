<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用预算管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqsctr/fyys/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-80">
							<input value="${fyys.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-80">
							<input value="${fyys.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">年度：</label></td>
					<td class="width-80">
						<input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${fyys.m1 }" data-options="editable:false ,validType:'length[0,50]'"/>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">支出项目类别：</label></td>
					<td class="width-80">
						<input class="easyui-combotree" id="M2" name="M2" value="${fyys.m2 }" style="width: 100%;height: 30px;"
								data-options="method: 'get', panelHeight:'120px',
								editable:false ,url:'${ctx}/aqsctr/fyys/zclxjson' " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">项目说明：</label></td>
					<td class="width-30" colspan="3"><input style="width: 100%;height: 80px;" id="M5" name="M5" class="easyui-textbox" value="${fyys.m5 }" data-options="multiline:true, validType:['length[0,1000]'] " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">预算费用（万元）：</label></td>
					<td class="width-80">
						<input required="true" style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${fyys.m3 }" data-options="validType:['number','length[0,10]'] "/>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">预算识别人：</label></td>
					<td class="width-80"><input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${fyys.m4 }" data-options="validType:['length[0,100]'] "/></td>
				</tr>
				
				<c:if test="${not empty fyys.id}">
					<input type="hidden" name="ID" value="${fyys.id}" />
					<input type="hidden" name="qyid" value="${fyys.qyid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${fyys.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${fyys.s3}" />
				</c:if>
				
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	//年份下拉框初始化
	$("#M1").combobox({ 
		valueField:'year',    
		textField:'year',  
		panelHeight:'auto'
	});
	
	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear()+2;
	
	for(var i=0;i<6;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
		
	$("#M1").combobox("loadData", data);//下拉框加载数据
	if('${action}' == 'create') {
		//获取今年年份,设置年度下拉框默认值
		var nowyear=new Date().getUTCFullYear();
		$("#M1").combobox("setValue", nowyear);//下拉框加载数据
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