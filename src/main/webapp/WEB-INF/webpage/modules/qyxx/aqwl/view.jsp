<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>安全管理网络</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<div>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>
			<tr>
		         <td class="width-15 active"><label class="pull-right">名称:</label></td>
		         <td class="width-35">${bis.m1}</td>
		         <td class="width-15 active"><label class="pull-right">所属上级:</label></td>
		         <td class="width-35">${pname}</td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">负责人:</label></td>
		         <td class="width-35" >${bis.m2}</td>
		         <td class="width-15 active"><label class="pull-right">负责人联系方式:</label></td>
		         <td class="width-35">${bis.m3}</td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">安全职责:</label></td>
		         <td class="width-35" colspan="3">${bis.m4}</td> 
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">备注:</label></td>
		         <td class="width-35" colspan="3">${bis.m5}</td> 
		      </tr>
		      
		</tbody>
		
	</table>
	</form>
</div> 
<script type="text/javascript">
 
</script>
</body>
</html>