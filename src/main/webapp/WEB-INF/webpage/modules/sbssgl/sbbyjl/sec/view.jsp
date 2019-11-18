<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>查看设备二级保养计划</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbbyjl/sec/view.js?v=1.0"></script>
</head>
<body>
	<div id="sbssgl_sbbystask_sec_tb" style="padding:5px;height:auto">
		<table id="rwtable" class="table table-bordered">
			<tbody class="w36">
				<tr class="w36">
					<td colspan="16" style="width: 100%;text-align: center"><span style="font-size: 18px;font-weight: bold;padding-top:10px">${entity.year } 年设备二级保养记录表</span></td>
   				</tr>
   				<tr>
					<td class="w7" style="width: 7%;text-align: center;"><label class="">所属企业</label></td>
					<td class="w17" style="width: 16%;text-align: center;">${entity.qyname }</td>
					<td class="w7" style="width: 7%;text-align: center;"><label class="">部门名称</label></td>
					<td class="w17" style="width: 16%;text-align: center;">${entity.deptname }</td>
					<td colspan="14" class="w74" style="width: 74%;text-align: right;"><label class="">编制日期</label></td>
					<td class="w17" style="width: 16%;text-align: center;"><fmt:formatDate value="${entity.bztime }" pattern="yyyy-MM-dd" /></td>
   				</tr>
  			</tbody>
		</table>
		<input type="hidden" name="sbtype" value="${sbtype}"><input type="hidden" name="bzrid" value="${entity.bzrid}"><input type="hidden" name="oldbzrq" value="<fmt:formatDate value="${entity.m14 }" pattern="yyyy-MM-dd" />">
	 	<input type="hidden" name="qyid" value="${entity.qyid}"><input type="hidden" name="deptid" value="${entity.deptid}"><input type="hidden" name="oldnd" value="${entity.m13}">
	 	<input type="hidden" name="jau" value=""><input type="hidden" name="feb" value=""><input type="hidden" name="mar" value=""><input type="hidden" name="apr" value="">
	 	<input type="hidden" name="may" value=""><input type="hidden" name="jun" value=""><input type="hidden" name="jul" value=""><input type="hidden" name="aug" value="">
	 	<input type="hidden" name="sep" value=""><input type="hidden" name="oct" value=""><input type="hidden" name="nov" value=""><input type="hidden" name="dec" value="">
	</div>
	<table id="sbssgl_sbbystask_sec_dg"></table> 
	 
<script>
	var role = '0';
	var uploadrole = '0';
	var taskid = ${entity.id};
</script>
<shiro:hasPermission name="sbssgl:sbbyjl:operation">
	<script>role = '1';</script>
</shiro:hasPermission>
<shiro:hasPermission name="sbssgl:sbbyjl:upload">
	<script>uploadrole = '1';</script>
</shiro:hasPermission>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var sbtype = '${sbtype}';//设备类型
var taskid = ${entity.id};
var flag = '${flag}'
var validateForm;

//设备二级保养计划，1-12月，各个数组中保存的是id
var jau = [];var feb = [];var mar = [];var apr = [];var may = [];var jun = [];
var jul = [];var aug = [];var sep = [];var oct = [];var nov = [];var dec = [];

</script>
</body>
</html>