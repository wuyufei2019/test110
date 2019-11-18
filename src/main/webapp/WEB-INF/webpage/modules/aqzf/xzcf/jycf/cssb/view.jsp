<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>添加惩罚告知记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		
				<tr>
				<td class="width-15 active"><label class="pull-right">案件名称：</label></td>
				<td class="width-35" colspan="3">${jce.id1 }</td>
				</tr>
		 		<tr>
		 			<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35">${jce.startdate }</td>	
					<td class="width-15 active"><label class="pull-right">开始时间：</label></td>
					<td class="width-35">${jce.enddate }</td>	
		 		</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">地点：</label></td>
					<td class="width-35"  colspan="3">${jce.address }</td>
				</tr>
				<tr>
					<td class="width-15 active" ><label class="pull-right">陈述申辩人:</label></td>
					<td class="width-35"  >${jce.name }</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35">${jce.sex }</td>	
				</tr>
		        <tr>
					<td class="width-15 active"><label class="pull-right">职务：</label></td>
					<td class="width-35" >${jce.duty }</td>
					<td class="width-15 active"><label class="pull-right">电话：</label></td>
					<td class="width-35" >${jce.phone }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">工作单位：</label></td>
					<td class="width-85" colspan="3">${jce.workunit }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系地址：</label></td>
					<td class="width-85" colspan="3">${jce.contactaddress }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">邮编：</label></td>
					<td class="width-85">${jce.ybcode }</td>
				</tr>
				
				 <tr>
					<td class="width-15 active"><label class="pull-right">承办人：</label></td>
					<td class="width-35" >${jce.director }</td>
					<td class="width-15 active"><label class="pull-right">记录人：</label></td>
					<td class="width-35" >${jce.recorder }</td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-35" >${jce.enforcer1 }</td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35" >${jce.identity1 }</td>
				</tr>
				 <tr>
					<td class="width-15 active"><label class="pull-right">执法人员：</label></td>
					<td class="width-35" >${jce.enforcer2 }</td>
					<td class="width-15 active"><label class="pull-right">身份证号：</label></td>
					<td class="width-35" >${jce.identity2 }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">申辩记录：</label></td>
					<td class="width-85" colspan="3">${jce.cssbrecord }</td>
				</tr>
		</table>
	</div>
</body>
</html>