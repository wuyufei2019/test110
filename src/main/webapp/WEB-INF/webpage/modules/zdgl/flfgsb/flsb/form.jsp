<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>法律法规识别</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	
	function doSubmit(){
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
</head>
<body>
     <form id="inputForm" action="${ctx}/zdgl/flsb/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			    <c:if test="${action eq 'create'}">
				  	<tr>
				  		<td class="width-15 active"><label class="pull-right">大类别：</label></td>
						<td class="width-35"><input id="M1_1" name="M1_1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${flfg.m1_1 }" data-options="required:'true',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'8',text:'国际公约'},
								        {value:'7',text:'其他'}]" /></td>
						<td class="width-15 active"><label class="pull-right">小类别：</label></td>
						<td class="width-35"><input id="lb" name="lb" class="easyui-combotree" style="width: 100%;height: 30px;" value="${flfg.m1}" data-options="required:false,panelHeight:'150px',editable:false,url: '${ctx}/zdgl/flfg/treelist'" /></td>
					</tr>
					<tr >
						<td class="width-15 active"><label class="pull-right">法律法规名称：</label></td>
						<td class="width-35" colspan="3">
							<input id="flfgid" name="flfgid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${flfg.ID }" data-options="required:'true',
									panelHeight:'150px',valueField: 'id',textField: 'text'" />
						</td>
					</tr>
				</c:if>
				<c:if test="${action eq 'update'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">大类别：</label></td>
						<td class="width-35"><input id="M1_1" name="M1_1" class="easyui-combobox" style="width: 100%;height: 30px;" value="${flfg.m1_1 }" data-options="readonly:'true',required:'true',panelHeight:'150px',editable:false,data: [
										{value:'1',text:'法律'},
								        {value:'2',text:'法规'},
								        {value:'3',text:'规章'},
								        {value:'4',text:'地方性法规'},
								        {value:'5',text:'政府文件'},
								        {value:'6',text:'标准规范'},
								        {value:'8',text:'国际公约'},
								        {value:'7',text:'其他'}]" /></td>
						<td class="width-15 active"><label class="pull-right">小类别：</label></td>
						<td class="width-35"><input id="lb" name="lb" class="easyui-combotree" style="width: 100%;height: 30px;" value="${flfg.m1}" data-options="readonly:'true',panelHeight:'150px',editable:false,url: '${ctx}/zdgl/flfg/treelist'" /></td>
					</tr>
					<tr >
						<td class="width-15 active"><label class="pull-right">法律法规名称：</label></td>
						<td class="width-35" colspan="3">
							<input id="flfgid" name="flfgid" class="easyui-combobox" style="width: 100%;height: 30px;" value="${flfg.ID }" data-options="readonly:'true',
									panelHeight:'150px',valueField: 'id',textField: 'text'" />
						</td>
					</tr>
				</c:if>
				<tr >
					<td class="width-15 active"><label class="pull-right">颁发单位：</label></td>
					<td class="width-35">
						<input id="bfdw" name="bfdw" class="easyui-textbox" style="width: 100%;height: 30px;" value="${flfg.m3 }" data-options="validType:'length[0,100]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">文件编号：</label></td>
					<td class="width-35">
						<input id="wjbh" name="wjbh" style="width: 100%;height: 30px;" class="easyui-textbox" value="${flfg.m4 }" data-options="validType:'length[0,100]'"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发布日期：</label></td>
					<td class="width-35" ><input id="fbrq" name="fbrq" class="easyui-datebox" style="width: 100%;height: 30px;" value="${flfg.m5 }" data-options="editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">实施日期：</label></td>
					<td class="width-35" ><input id="ssrq" name="ssrq" class="easyui-datebox" style="width: 100%;height: 30px;" value="${flfg.m6 }" data-options="editable:false" /></td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">适用条款：</label></td>
					<td class="width-35">
						<input id="M1" name="M1" class="easyui-textbox" style="width: 100%;height: 30px;" value="${flsb.m1 }" data-options="validType:'length[0,100]'" />
					</td>
					<td class="width-15 active"><label class="pull-right">适用部门：</label></td>
					<td class="width-35">
						<input id="M2" name="M2" style="width: 100%;height: 30px;" class="easyui-combobox" value="${flsb.m2 }" 
						data-options="validType:'length[0,250]',panelHeight:'150px',editable:false,multiple:true,valueField:'id',textField:'text',url:'${ctx }/system/department/deptjson'"/>
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">Gap（差异）：</label></td>
					<td class="width-35" colspan="3">
						<input id="M13" name="M13" class="easyui-textbox" style="width: 100%;height: 50px;" value="${flsb.m13 }" data-options="validType:'length[0,500]',multiline:true" />
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3">
						<input id="M3" name="M3" class="easyui-textbox" style="width: 100%;height: 80px;" value="${flsb.m3 }" data-options="validType:'length[0,500]',multiline:true" />
					</td>
				</tr>
				
				<c:if test="${action != 'create'}">
					<c:if test="${type eq 'sh'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${flsb.m8=='0'}">
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
						<input type="hidden" name="role" value="2" />
					</c:if>
					
					<c:if test="${type eq 'pz'}">
						<tr>
							<td class="width-15 active"><label class="pull-right">部门审核意见：</label></td>
							<td class="width-35">
								<input class="easyui-textbox" style="width: 100%;height: 30px;" value="同意" data-options="readonly:'true'"/>
							</td>
							<td class="width-15 active"><label class="pull-right">领导批准意见：</label></td>
							<td class="width-35">
								<c:choose>
									<c:when test="${flsb.m11=='0'}">
										<input type="radio" value="1" class="i-checks" name="M11" />同意
										<input type="radio" value="0" class="i-checks"  name="M11" checked="checked" />不同意
									</c:when>
									<c:otherwise>
										<input type="radio" value="1" class="i-checks" name="M11" checked="checked" />同意
										<input type="radio" value="0" class="i-checks"  name="M11" />不同意
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<input type="hidden" name="role" value="3" />
						<input type="hidden" name="M7" value="${flsb.m7}" />
						<input type="hidden" name="M8" value="${flsb.m8}" />
						<input type="hidden" name="M9"
							value="<fmt:formatDate value="${flsb.m9}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</c:if>
				</c:if>
				
				<c:if test="${not empty flsb.ID}">
					<input type="hidden" name="ID" value="${flsb.ID}" />
					<input type="hidden" name="ID1" value="${flsb.ID1}" />
					<input type="hidden" name="M4" value="${flsb.m4}" />
					<input type="hidden" name="M5" value="${flsb.m5}" />
					<input type="hidden" name="M6"
						value="<fmt:formatDate value="${flsb.m6}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">

	function timeStamp2String (time){
	        var datetime = new Date();
	         datetime.setTime(time);
	         var year = datetime.getFullYear();
	         var month = datetime.getMonth() + 1;
	         var date = datetime.getDate();
	         var hour = datetime.getHours();
	         var minute = datetime.getMinutes();
	         var second = datetime.getSeconds();
	         var mseconds = datetime.getMilliseconds();
	         return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second+"."+mseconds;
	};
	
	$(function(){
	     //下拉关联信息
		$("#lb").combotree({
			onSelect: function(node){
			    $("#flfgid").combobox('setValue','');
			    $("#bfdw").textbox('setValue','');
				$("#wjbh").textbox('setValue','');
				$("#fbrq").datebox('setValue','');	
				$("#ssrq").datebox('setValue','');
				var lb=node.id;
				var m1_1 = $("#M1_1").combobox('getValue');
				if(lb!=''){
				    $.ajax({  
				       url:'${ctx}/zdgl/flfg/json1',
			           data:{'lb':lb,'m1_1':m1_1},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
							if(data.length==0){
								layer.msg("该类别暂无法律法规，请去标准库添加！");
							}
			           		$('#flfgid').combobox('loadData', data); 
			           }
				    });
			    }
			},
			loader:function (param, success, error) {
				var lb=$("#lb").combotree('getValue');
			    if(lb!=''){
				    $.ajax({  
				       url:'${ctx}/zdgl/flfg/json1',
			           data:{'lb':lb,'m1_1':m1_1},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			        	    if(data.length==0){
								layer.msg("该类别暂无法律法规，请去标准库添加！");
							}
			           		$('#flfgid').combobox('loadData', data); 
			           }
				    });
			    }
	 		}
		});
		
		$("#M1_1").combobox({
			onSelect: function(node){
			    $("#flfgid").combobox('setValue','');
			    $("#bfdw").textbox('setValue','');
				$("#wjbh").textbox('setValue','');
				$("#fbrq").datebox('setValue','');	
				$("#ssrq").datebox('setValue','');
				var m1_1=node.value;
				var lb = $("#lb").combotree('getValue');
				if(m1_1!=''){
				    $.ajax({  
				       url:'${ctx}/zdgl/flfg/json1',
			           data:{'lb':lb,'m1_1':m1_1},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			        	    if(data.length==0){
								layer.msg("该类别暂无法律法规，请去标准库添加！");
							}
			           		$('#flfgid').combobox('loadData', data); 
			           }
				    });
			    }
			},
			loader:function (param, success, error) {
				var m1_1=$("#M1_1").combobox('getValue');
			    if(m1_1!=''){
				    $.ajax({  
				       url:'${ctx}/zdgl/flfg/json1',
			           data:{'lb':lb,'m1_1':m1_1},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
			        	    if(data.length==0){
								layer.msg("该类别暂无法律法规，请去标准库添加！");
							}
			           		$('#flfgid').combobox('loadData', data); 
			           }
				    });
			    }
	 		}
		});
	     
		$("#flfgid").combobox({
			onSelect: function(){
				var flfgid = $('#flfgid').combobox('getValue');
				if(lb!=''){
				    $.ajax({  
				       url:'${ctx}/zdgl/flfg/json2',
			           data:{'id':flfgid},
			           dataType : 'json',
			           type : 'POST',
			           success: function (data){
							$("#bfdw").textbox('setValue',data.m3);
							$("#wjbh").textbox('setValue',data.m4);
							if(data.m5!=''&&data.m5!=null){
								var time1 = timeStamp2String(data.m5);
								$("#fbrq").datebox('setValue',time1);	
							}
							if(data.m6!=''&&data.m6!=null){
								var time2 = timeStamp2String(data.m6);
								$("#ssrq").datebox('setValue',time2);
							}
			           }
				    });
			    }
			}
		});
	});
</script>
</body>
</html>