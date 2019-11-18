<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>职业病危害因素管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/occupharm/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${whyslist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${whyslist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-20 active"><label class="pull-right">职业病危害因素类别：</label></td>
					<td class="width-30"><input id="bis_occupharm_m1" class="easyui-combobox"  style="width: 100%;height: 30px;" name="M1" value="${whyslist.m1 }" 
					data-options="panelHeight:'auto' ,required:'true', editable:true,data: [
								        {value:'粉尘',text:'粉尘'},
								        {value:'化学因素',text:'化学因素'},
								        {value:'物理因素',text:'物理因素'},
								        {value:'放射性因素',text:'放射性因素'},
								        {value:'生物因素',text:'生物因素'},
								        {value:'其他因素',text:'其他因素'} ],
					loader:function (param, success, error) {
									if('${action}'=='update'){ 
								   var t1=  $('#bis_occupharm_m1').combobox('getText');
								   var url = '${ctx}/bis/occupharm/zybzd2/'+t1;
								$.ajax({
							           url:'${ctx}/bis/occupharm/zybzd2/'+t1,
							           dataType : 'json',
							           type : 'POST',
							           contentType:'application/x-www-form-urlencoded; charset=UTF-8',
							           success: function (data){
							                $('#bis_occupharm_m2').combobox('loadData', data); 
							           }
							     });
							     }
								 } 
								,
					onSelect: function(rec){
						
	       	        	$('#bis_occupharm_m2').combobox('clear');
	       	        	$('#bis_occupharm_m4').val(' ');
	       	        	$('#bis_occupharm_m2').combobox('enable');
	       	        	
					    var url = '${ctx}/bis/occupharm/zybzd2/'+rec.text;
					    $('#bis_occupharm_m2').combobox('reload', url);
	       	        }
						    "/></td>
					<td class="width-20 active"><label class="pull-right">职业病危害因素名称：</label></td>
					<td class="width-30"><input id="bis_occupharm_m2" class="easyui-combobox" name="M2" value="${whyslist.m2 }" style="width: 100%;height: 30px;" data-options="
								required:'true', editable:true, valueField: 'id',textField: 'text' "
								/></td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">危害程度：</label></td>
					<td class="width-30" colspan="3">
						<input class="easyui-combobox" name="M3" value="${whyslist.m3 }" style="width: 100%;height: 30px;" data-options="panelHeight:'auto', required:'true',
								editable:false ,data: [
								        {value:'1',text:'轻度危险性'},
								        {value:'2',text:'一般危险性'},
								        {value:'3',text:'严重危险性'} ]
						    "/>
					</td>
				</tr>
				<tr >
					<td class="width-20 active"><label class="pull-right">可能导致的职业病：</label></td>
					<td class="width-30" colspan="3">
						<input id="M5" name="M5" class="easyui-combotree" value="${whyslist.m5 }" style="width: 100%;height: 30px;" data-options="method: 'get',required:'true',editable:'false',panelHeight:'150',url:'${ctx}/bis/occupharm/zybfl/json'"/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">存在部门：</label></td>
					<td class="width-30"><input id="M6" name="M6" type="text" class="easyui-combobox" value="${whyslist.m6 }"
						style="width: 100%;height: 30px;"
						data-options="editable:true ,valueField: 'text',textField: 'text', panelHeight:'140', url:'${ctx}/system/department/deptjson',
													onSelect : function(rec){	
														$('#bmid').val(rec.id);
														jcmd = '';
														$('#M9').val('');
														$('#M9_input').textbox('setValue', '');
													 }" /></td>
					<td class="width-20 active"><label class="pull-right">存在岗位：</label></td>
					<td class="width-30"><input id="M7" name="M7" type="text" class="easyui-combobox" style="width: 100%;height: 30px;"
						value="${whyslist.m7 }" data-options="editable:true ,validType:'length[0,25]',panelHeight: '100',valueField: 'text',textField: 'text',url:'${ctx}/bis/gzxx/textjson',
													onSelect : function(rec){	
														jcmd = '';
														$('#M9').val('');
														$('#M9_input').textbox('setValue', '');
													 }" /></td>
				<tr>
				
				<%-- <tr >
					<td class="width-20 active"><label class="pull-right">接触名单：</label></td>
					<td class="width-30" colspan="3">
						<input id="M9" name="M9" class="easyui-combobox" value="${whyslist.m9 }" style="width: 100%;height: 30px;" data-options="valueField: 'text',textField: 'text',editable:'false',panelHeight:'100',multiple:true"/>
					</td>
				</tr> --%>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">接触名单：</label></td>
					<td class="width-30" colspan="3">
						<input id="M9" name="M9" type="hidden" }">
						<input id="M9_input" class="easyui-textbox" value="" style="width: 84%;height: 30px;" data-options="editable: false"/>
						<span  class="btn btn-success btn-sm  "  style="width: 15%;height: 30px;" onclick="search()" ><i class="fa fa-search"></i> 查询</span>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-85" colspan="3">
					<input name="M4" type="text" value="${whyslist.m4 }"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,250]'">
					</td>
					
				</tr>
				
				<!-- 部门id -->
				<input type="hidden" id="bmid" value=""/>
				<c:if test="${not empty whyslist.ID}">
					<input type="hidden" name="ID" value="${whyslist.ID}" />
					<input type="hidden" name="ID1" value="${whyslist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${whyslist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${whyslist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var dg;
	var usertype=${usertype};
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
	var jcmd = '';//接触名单
	var ygids = '';//员工id
$(function(){
	//修改
	if ('${action}' == 'update') {
		$("#bmid").val(${deptid});
	 	var m9 = '${whyslist.m9 }';
	 	if(m9==null||m9==""){
			$("#M9").val("");
		}else {
			var arr_m9 = m9.split("||");
			$("#M9").val(arr_m9[0]);
			$("#M9_input").textbox('setValue', arr_m9[1]);
		}
	 }
	
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

function doSubmit(){
	if(usertype!= '1'){
	 	var options = $("#_qyid").combobox('options');  
     	var data = $("#_qyid").combobox('getData');/* 下拉框所有选项 */  
     	var value = $("#_qyid").combobox('getValue');/* 用户输入的值 */  
     	var b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */  
     	for (var i = 0; i < data.length; i++) {  
        	if (data[i][options.valueField] == value) {  
            	b=true;  
           	 	break;  
        	}  
    	}  
		if(b==false){
				layer.open({icon:1,title: '提示',offset: 'auto',content: '所选企业不存在！',shade: 0 ,time: 2000 });
				return;
			}
		}
	 $("#M5").combotree('setValue',$("#M5").combotree('getText'));
	 $("#inputForm").submit(); 
}

//查询
function search(){
	var bmid = $('#bmid').val();
	var gwname = $('#M7').combobox('getValue');
	var ygid = $("#M9").val();
	$.ajax({
		type: 'post',
		url: ctx+'/bis/occupharm/jcmd/json?bmid='+bmid+'&gwname='+gwname,
		success: function(data){
			var total = data.total;
			if (total == 0) {
				layer.msg('该岗位暂无员工', {time: 2000});
			} else {
				layer.open({
				    type: 2,  
				    shift: 1,
				    area: ['500px', '400px'],
				    title: '选择接触名单',
			        maxmin: false, 
				    content: ctx+"/bis/occupharm/jcmd?bmid="+bmid+"&gwname="+gwname+"&ygid="+ygid,
				    btn: ['确定', '关闭'],
				    yes: function(index, layero){
				    	jcmd = '';
				    	ygids = '';
				    	var selectRow = dg.datagrid('getSelections');
				    	for (var i = 0; i < selectRow.length; i++) {
				    		jcmd += (","+selectRow[i].name);//接触名单
				    		ygids += (","+selectRow[i].id);//员工id
				    		$('#M9').val(ygids.substring(1));
				    		$("#M9_input").textbox('setValue', jcmd.substring(1));
				    	}
				    	layer.close(index);
				    },
				    cancel: function(index){ 
			        }
				}); 	
			}
		}
	});
}
</script>
</body>
</html>