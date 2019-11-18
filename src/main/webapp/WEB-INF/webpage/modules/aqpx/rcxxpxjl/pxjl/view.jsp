<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常线下培训记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">培训类别：</label></td>
				  <td class="width-85" colspan="3">
					  ${pxjl.m1_4 }
				  </td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">姓名：</label></td>
				  <td class="width-35">${pxjl.m1_1}</td>
				  <td class="width-15 active"><label class="pull-right">工作岗位：</label></td>
				  <td class="width-35">${pxjl.m1_3 }</td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">培训时间：</label></td>
				  <td class="width-35"><fmt:formatDate value="${pxjl.m2 }" pattern="yyyy-MM-dd"  /></td>
				  <td class="width-15 active"><label class="pull-right">培训人员：</label></td>
				  <td class="width-35">${pxjl.m4 }</td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">考试成绩：</label></td>
				  <td class="width-35">${pxjl.m7 }</td>
				  <td class="width-15 active"><label class="pull-right">培训结果：</label></td>
				  <td class="width-35">${pxjl.m8 }</td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">培训内容：</label></td>
				  <td class="width-35" colspan="3">
					  ${pxjl.m3 }
				  </td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">备注：</label></td>
				  <td class="width-35" colspan="3">
					  ${pxjl.m9 }
				  </td>
			  </tr>

			  <tr>
				  <td class="width-15 active"><label class="pull-right">附件：</label></td>
				  <td class="width-35" colspan="3">
					  <div id="uploader_ws_m10">
						  <div id="m10fileList" class="uploader-list" ></div>
						  <div id="filePicker">选择文件</div>
					  </div>
				  </td>
			  </tr>
				
			</table>

		  	</tbody>
	 </form>
</body>
</html>