<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>处理措施管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/aqzf/clcs/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位：</label></td>
					<c:if test="${action eq 'create'}">
						<input type="hidden" name="ID1" value="${plan.ID1}" />
						<td class="width-30" colspan="3"><input type="text" id="ID2" name="ID2" class="easyui-combobox"  style="height: 30px;width: 100%" data-options="required:true, editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' "/></td>
					</c:if>
					<c:if test="${action eq 'update'}">
						<td class="width-30" colspan="3"><input type="text" id="ID2" name="ID2" class="easyui-combobox" value="${clcs.ID2 }"  style="height: 30px;width: 100%" data-options="required:true, panelHeight:'auto', editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson' "/></td>
					</c:if>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">检查日期：</label></td>
					<td class="width-30" colspan="3">
						<input id="M1" name="M1" class="easyui-datebox" value="${clcs.m1 }" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">事故隐患：</label></td>
					<td class="width-85" colspan="3">
					<input name="M2" type="text" value="${clcs.m2 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>
								
				<tr >
					<td class="width-20 active"><label class="pull-right">依据规定：</label></td>
					<td class="width-30" colspan="3">
						<input type="text" id="M3" name="M3" class="easyui-textbox" value="${clcs.m3}"  data-options=" " style="height: 30px;width: 100%;" />
					</td>
				</tr>
									
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">处理决定：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${clcs.m4 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true , validType:'length[0,250]'">
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">申诉政府：</label></td>
					<td class="width-30"><input type="text" id="M5" name="M5" class="easyui-textbox" value="${clcs.m5 }"  data-options=" " style="height: 30px;width: 100%" /></td>
					<td class="width-20 active"><label class="pull-right">诉讼法院：</label></td>
					<td class="width-30"><input type="text" id="M6" name="M6" class="easyui-textbox" style="height: 30px; width: 100%;" value="${clcs.m6}" data-options=" " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M7_1" name="M7_1" class="easyui-combobox" value="${clcs.m7_1 }" style="width: 100%;height: 30px;" data-options="panelHeight:'190px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M8_1" name="M8_1" class="easyui-textbox" style="height: 30px; width: 100%;" value="${clcs.m8_1 }" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-30"><input type="text" id="M7_2" name="M7_2" class="easyui-combobox" value="${clcs.m7_2 }" style="width: 100%;height: 30px;" data-options="panelHeight:'190px',editable:true ,valueField: 'id',textField: 'text',url:'${ctx}/aqzf/zfry/idlist' "/></td>
					<td class="width-20 active"><label class="pull-right">证号：</label></td>
					<td class="width-30"><input type="text" id="M8_2" name="M8_2" class="easyui-textbox" style="height: 30px; width: 100%;" value="${clcs.m8_2}" data-options="readonly:true " /></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">被检查单位负责人：</label></td>
					<td class="width-30" colspan="3" ><input id="M9"type="text" name="M9" class="easyui-textbox" value="${clcs.m9 }"  data-options=" " style="height: 30px;width: 100%" /></td>
				</tr>
								
				<c:if test="${not empty clcs.ID}">
					<input type="hidden" name="ID" value="${clcs.ID}" />
					<input type="hidden" name="ID1" value="${clcs.ID1}" />
					<input type="hidden" name="M0" value="${clcs.m0}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${clcs.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${clcs.s3}" />
				</c:if>
			</table>

		  	<tbody>
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
	$('#M7_1').combobox('setValue',$('#M7_1').combobox('getText'));
	$('#M7_2').combobox('setValue',$('#M7_2').combobox('getText'));
	$("#inputForm").submit(); 
}

$(function(){
    //第一次添加时,设置默认时间  ;
	var curr_time = new Date();
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
		   if ('${action}' == 'create') {
		    $("#M3").textbox("setValue", "安全生产法第62条");
			$("#M5").textbox('setValue','${gov}');
			$("#M6").textbox('setValue','${court}');
			$("#M9").textbox('setValue','${duty}');
		  }
		  
	//下拉关联信息
	$("#M7_1").combobox({
		onSelect: function(){
			var id=$("#M7_1").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M8_1").textbox('setValue',data);
			}
		});
		}
	});

	$("#M7_2").combobox({
		onSelect: function(){
			var id=$("#M7_2").combobox('getValue');
			$.ajax({
			type:'get',
			url:ctx+"/aqzf/zfry/findidcard/"+id,
			success: function(data){
				$("#M8_2").textbox('setValue',data);
			}
		});
		}
	});
	
	//下拉关联信息
	$("#ID2").combobox({
		onSelect: function(){
			var id=$('#ID2').combobox('getValue');
			$.ajax({
				type:'get',
				url: '${ctx}/bis/qyjbxx/qydetail/'+id,
				success : function(data) {
					var d=JSON.parse(data);
					$("#M9").textbox('setValue',d.m5);	
				}
			});
		}
	});

});
</script>
</body>
</html>