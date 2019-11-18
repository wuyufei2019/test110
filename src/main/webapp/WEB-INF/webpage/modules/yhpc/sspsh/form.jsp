<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>随手拍审核</title>
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
     <form id="inputForm" action="${ctx}/yhpc/sspsh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">计划整改人：</label></td>
					<td class="width-30" ><input id="handlepersons" name="handlepersons" class="easyui-combobox" style="width: 100%;height: 30px;" value="${sspsh.handlepersons }" data-options="panelHeight:'170px',editable:true,validType:'length[0,100]',valueField: 'id', multiple:true, textField: 'text',url:'${ctx}/system/compuser/userjson'" /></td>
					<td class="width-20 active"><label class="pull-right">计划整改时间：</label></td>
					<td class="width-30" ><input id="sechandletime" name="sechandletime" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sspsh.sechandletime }" data-options="editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患等级：</label></td>
					<td class="width-30" >
						<input type="text" name="dangerlevel" class="easyui-combobox" value="${sspsh.dangerlevel }"  data-options="required:'true',panelHeight:'auto',editable:false,data: [
									         {value:'0',text:'无隐患'},
									         {value:'1',text:'一级'},
									         {value:'2',text:'二级'},
									         {value:'3',text:'三级'},
									         {value:'4',text:'四级'}]" style="width: 100%;height: 30px;" />
					</td>
					<td class="width-20 active"><label class="pull-right">隐患发现时间：</label></td>
					<td class="width-30" ><input id="createtime" name="createtime" class="easyui-datetimebox" style="width: 100%;height: 30px;" value="${sspsh.createtime }" data-options="readonly:'true',editable:false" /></td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患备注：</label></td>
					<td class="width-80" colspan="3">
						<input type="text" name="dangerdesc" class="easyui-textbox" value="${sspsh.dangerdesc }"  data-options="readonly:'true',multiline:true" style="width: 100%;height: 80px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty sspsh.dangerphoto}">
					 <c:forTokens items="${sspsh.dangerphoto}" delims="||" var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  style="max-height: 150px;"/><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
					<%-- <td class="width-20 active"><label class="pull-right">隐患类型：</label></td>
					<td class="width-30" >
						<input type="text" name="hiddentype" class="easyui-combobox" value="${sspsh.hiddentype }"  data-options="required:'true',panelHeight:'150px',editable:false,data: [
									         {value:'高处作业',text:'高处作业'},
									         {value:'现场5S',text:'现场5S'},
									         {value:'设备安全',text:'设备安全'},
									         {value:'起重吊装',text:'起重吊装'},
									         {value:'电气安全',text:'电气安全'},
									         {value:'设备工具安全',text:'设备工具安全'},
									         {value:'断能上锁',text:'断能上锁'},
									         {value:'密闭空间',text:'密闭空间'},
									         {value:'无资质/未授权作业',text:'无资质/未授权作业'},
									         {value:'车辆移动',text:'车辆移动'},
									         {value:'焊接切割',text:'焊接切割'},
									         {value:'PPE',text:'PPE'},
									         {value:'5S类',text:'5S类'},
									         {value:'消防类',text:'消防类'},
									         {value:'环境类',text:'环境类'},
									         {value:'其它',text:'其它'}]" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-20 active"><label class="pull-right">违规级别：</label></td>
					<td class="width-30" >
						<input type="text" name="violationlevel" class="easyui-combobox" value="${sspsh.violationlevel }"  data-options="required:'true',panelHeight:'auto',editable:false,data: [
									         {value:'KPI考核',text:'KPI考核'},
									         {value:'整改项',text:'整改项'}]" style="width: 100%;height: 30px;" />
					</td>
				</tr> --%>
				<input type="hidden" name="hiddentype" value="" />
				<input type="hidden" name="violationlevel" value="" />
				
				<c:if test="${not empty sspsh.id}">
					<input type="hidden" name="ID" value="${sspsh.id}" />
					<input type="hidden" name="qyid" value="${sspsh.qyid}" />
					<input type="hidden" name="dangerstatus" value="${sspsh.dangerstatus}" />
					<input type="hidden" name="dangerorigin" value="${sspsh.dangerorigin}" />
					<input type="hidden" name="userid" value="${sspsh.userid}" />
					<input type="hidden" name="dangerphoto" value="${sspsh.dangerphoto}" />
				</c:if>
				</tbody>
			</table>
       </form>
<script type="text/javascript">

</script>
</body>
</html>