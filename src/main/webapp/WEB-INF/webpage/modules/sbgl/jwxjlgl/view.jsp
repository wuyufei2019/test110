<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>检维修管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">设备名称：</label></td>
               <td class="width-35">
                  ${sbgl.sbname }
               </td>
               <td class="width-15 active"><label class="pull-right">使用部门：</label></td>
               <td class="width-35" ><input id="" name="M1" class="easyui-combobox" readonly="readonly" value="${sbgl.m1 }"
                   style="width:100%;height: 30px;" data-options="required:true, editable : false, panelHeight:100,valueField:'id', textField:'text',url: '${ctx}/system/department/deptjson'"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">规格型号：</label></td>
               <td class="width-35">${sbgl.sbxh }</td>
               <td class="width-15 active"><label class="pull-right">设备编号：</label></td>
               <td class="width-35">${sbgl.sbbh }</td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">故障现象：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m2 }</td>					
			</tr>
            <tr>
            	<td class="width-15 active"><label class="pull-right">分析人：</label></td>
                <td class="width-35">${sbgl.m3 }</td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">维修风险分析：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m4 }</td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">方案制定人：</label></td>
                <td class="width-35">${sbgl.m5 }</td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">检维修方案：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m6 }</td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">措施制定人：</label></td>
                <td class="width-35">${sbgl.m7 }</td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">采取和落实<br>预防和控制<br>措施：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m8 }</td>					
			</tr>
			<tr>
				<td class="width-15 active"><label class="pull-right">检维修安全<br>教育培训：</label></td>
				<td class="width-35" colspan="3">
					<table id="rwtable" style="width: 100%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
						<tr>
			            	<td style="width: 20%;height: 30px;background-color: #f5f5f5;"><label class="pull-right">培训人：</label></td>
			                <td style="width: 80%;" align="center">${sbgl.m9 }</td>
			            </tr>
			            <tr>
							<td style="width: 20%;height: 60px;background-color: #f5f5f5;"><label class="pull-right">培训内容：</label></td>
							<td style="width: 80%;" align="center" style="height: 50px">${sbgl.m10 }</td>					
						</tr>
						<tr>
			            	<td style="width: 20%; background-color: #f5f5f5;"><label class="pull-right">被培训人：</label></td>
			            	<td style="width: 80%; height: 70px;" align="center">
				                <table style="width: 95%;"  border="1" cellspacing="0" cellpadding="0" bordercolor="#e7e7e7" >
				                	<tr>
						            	<td style="width: 20%;height: 30px;background-color: #f5f5f5;"><label class="pull-right">检修人：</label></td>
						                <td style="width: 80%;" align="center">${sbgl.m11 }</td>
						            </tr>
						            <tr>
						            	<td style="width: 20%;height: 30px;background-color: #f5f5f5;"><label class="pull-right">监护人：</label></td>
						                <td style="width: 80%;" align="center">${sbgl.m12 }</td>
						            </tr>
				                </table>
			            	</td>
			            </tr>
					</table>	
				</td>
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">检维修人：</label></td>
                <td class="width-35">${sbgl.m13 }</td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">检维修记录：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m14 }</td>					
			</tr>
			<tr>
            	<td class="width-15 active"><label class="pull-right">验收人：</label></td>
                <td class="width-35">${sbgl.m15 }</td>
                <td class="width-15 active"><label class="pull-right">检维修时间：</label></td>
                <td class="width-35" ><fmt:formatDate value="${sbgl.m17}" /></td>
            </tr>
            <tr>
				<td class="width-15 active"><label class="pull-right">验收记录：</label></td>
				<td class="width-35" colspan="3" style="height: 50px">${sbgl.m16 }</td>					
			</tr>
         </tbody>
      </table>
	</form>
</body>
</html>