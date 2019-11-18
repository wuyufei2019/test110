<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>冶金信息管理</title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>

     <form id="inputForm" action="${ctx}/bis/yjxx/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${yjlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
						<input value="${yjlist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="width-15 active"><label class="pull-right">类别名称：</label></td>
					<td class="width-35"><input name="M1" class="easyui-combobox"
								value="${yjlist.m1 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'黑色金属铸造',text:'黑色金属铸造'},
								        {value:'有色金属铸造',text:'有色金属铸造'},
								        {value:'有色金属合金铸造',text:'有色金属合金铸造'},
								        {value:'铝合金压铸',text:'铝合金压铸'},
								        {value:'镁合金压铸',text:'镁合金压铸'},
													  ] " /></td>
					<td class="width-15 active"><label class="pull-right">熔炼场所建<br>筑物结构：</label></td>
					<td class="width-35"><input name="M7" class="easyui-combobox"
								value="${yjlist.m7 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'混合结构',text:'混合结构'},
								        {value:'框架结构',text:'框架结构'},
								        {value:'框架剪力墙结构',text:'框架剪力墙结构'},
								        {value:'剪力墙结构',text:'剪力墙结构'},
								        {value:'框筒结构',text:'框筒结构'},
								        {value:'筒中筒结构',text:'筒中筒结构'},
								        {value:'钢网架、悬索结构',text:'钢网架、悬索结构'} ] " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">金属液体<br>转运方式：</label></td>
					<td class="width-35"><input name="M8" class="easyui-combobox"
								value="${yjlist.m8 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'轨道',text:'轨道'},
								        {value:'行车',text:'行车'},
								        {value:'人工抬运',text:'人工抬运'},
								        {value:'其他',text:'其他'},
													 ] " /></td>
					<td class="width-15 active"><label class="pull-right">主要产品：</label></td>
					<td class="width-35"><input name="M2" type="text" class="easyui-textbox" value="${yjlist.m2 }" style="width: 100%;height: 30px;"  data-options="validType:'length[0,50]' " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">载体是否<br>为移动式：</label></td>
					<td class="width-35" ><input name="M3" class="easyui-combobox"
								value="${yjlist.m3 }" style="width: 100%;height: 30px;" data-options="required:'false', panelHeight:'auto',
								editable:false ,data: [
										{value:'0',text:'否'},
								        {value:'1',text:'是'},
													 ] " /></td>
					<td class="width-15 active"><label class="pull-right">加热方式：</label></td>
					<td class="width-35" >
							<input id="" name="M4" type="text" class="easyui-combobox"  style="width: 100%;height: 30px;" value="${yjlist.m4 }"
						data-options=" editable:false ,panelHeight:'auto', data: [
										{value:'电炉',text:'电炉'},
								        {value:'天然气',text:'天然气'},
								        {value:'柴油',text:'柴油'},
								        {value:'生物燃料',text:'生物燃料'},
								        {value:'重油',text:'重油'},
								        {value:'其他',text:'其他'}]"/>
					</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">年产量(t)：</label></td>
					<td class="width-35" ><input name="M5" type="text" class="easyui-textbox" value="${yjlist.m5 }" style="width: 100%;height: 30px;" data-options="validType:['number','length[0,10]'] " /></td>
					<td class="width-15 active"><label class="pull-right">现场人数：</label></td>
					<td class="width-35" >
							<input name="M6" type="text" class="easyui-numberbox" value="${yjlist.m6 }" style="width: 100%;height: 30px;"  data-options="min:0" />
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="M9" type="text" value="${yjlist.m9}"   class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' "></td>					
				</tr>
				
				<c:if test="${not empty yjlist.ID}">
					<input type="hidden" name="ID" value="${yjlist.ID}" />
					<input type="hidden" name="ID1" value="${yjlist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${yjlist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${yjlist.s3}" />
				</c:if>
				</tbody>
			</table>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
</script>
</body>
</html>