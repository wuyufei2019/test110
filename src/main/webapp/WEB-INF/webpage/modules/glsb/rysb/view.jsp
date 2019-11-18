<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>双向人脸识别</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
                  <td class="width-15 active"><label class="pull-right">进出编号：</label></td>
                  <td class="width-35" >${entity.code}</td>
                  <td class="width-15 active"><label class="pull-right">人员名称：</label></td>
   				  <td class="width-35" >${entity.ryname}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">进出时间：</label></td>
   				  <td class="width-35" ><fmt:formatDate value="${entity.in_out_time}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
              </tr>
		</tbody>
	</table>

</body>
</html>