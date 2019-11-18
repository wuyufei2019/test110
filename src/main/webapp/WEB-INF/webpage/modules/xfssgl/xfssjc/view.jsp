<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>查看消防设施检查信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=OTVG2uzqudiovwoHlFIs0P8Z3T7ID4K2"></script>
    <script type="text/javascript" src="${ctx}/static/common/initmap.js?v=1.1"></script> 
</head>
<body>

     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
				    <td class="width-15 active"><label class="pull-right">设施名称：</label></td>
					<td class="width-35">
						<input name="name"  class="easyui-textbox" value="${entity.xfssname }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,20]']" readonly="readonly"/>
					</td>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><input id="checktime" name="checktime" class="easyui-datebox" value="${entity.checktime }" style="width: 100%;height: 30px;" 
						data-options="required:'true', editable:true " readonly="readonly"/></td>
				</tr>
   				<tr >
				    <td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-35" colspan="3"><input name="checkcontent"  class="easyui-textbox" value="${entity.checkcontent }" style="width: 100%;height: 30px;"
								data-options="required:'true',validType:['length[0,100]']" readonly="readonly"/>
					</td>
				</tr>
				<tr >

					<td class="width-15 active"><label class="pull-right">检查结论：</label></td>
					<td class="width-35">
						<input name="checkresult" class="easyui-textbox" value="${entity.checkresult }" style="width: 100%;height: 30px;" data-options="validType:'length[0,100]'" readonly="readonly"/>
					</td>
					<td class="width-15 active" ><label class="pull-right">检查人员：</label></td>
					<td class="width-35" colspan="3">
						<input name="employeename" class="easyui-textbox" value="${entity.employeename }" style="width: 100%;height: 30px;"
							 data-options=" editable:false ,panelHeight: 'auto',valueField: 'value',textField: 'text'" readonly="readonly"/></td>				
				</tr>
				
				<tr>
              		    <td class="width-15 active"><label class="pull-right">备注：</label></td>
               			<td class="width-35" colspan="3"><input name="M1" type="text" value="${entity.m1 }" class="easyui-textbox"
                   			style="width: 100%;height: 60px;" data-options="multiline:true,validType:'length[0,250]' " readonly="readonly"></td>
           	   </tr>

			   <tr>
              		    <td class="width-15 active"><label class="pull-right">图片/视频：</label></td>
               			<td class="width-35" colspan="3">
               				<c:if test="${not empty entity.m2}">
								<c:forTokens items="${entity.m2}" delims="," var="url" varStatus="urls">
							 		<c:set var="urlna" value="${fn:split(url, '||')}" />
							 			<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
							 				<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" alt="${urlna[1]}" width="300px;"/><br/>${urlna[1]}</a>
							 			</div>
								 </c:forTokens>
							 </c:if>
                 		</td>
           	   </tr>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">

</script>
</body>
</html>