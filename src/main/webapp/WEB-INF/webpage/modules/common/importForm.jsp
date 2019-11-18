<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
<script>
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function doSubmit() {
		$("#importForm").submit();
	}
	$(function() {
		$('#importForm').form({
			success : function(data) {
				data = JSON.parse(data);
				var returncode=data.returncode;
				if(returncode=='success'){
					parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '一共'+data.total+'条数据，成功导入'+data.success+'条数据，'+data.error+'条数据导入失败！',shade: 0 ,time: 3000 });
				}else if(returncode=='warn'){
					parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '导入文件中没有数据或数据不匹配！',shade: 0 ,time: 3000 });
				}else if(returncode=='ext'){
					parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '文件格式错误！',shade: 0 ,time: 2000 });
				}else if(returncode==-2){
					parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '文件有误！',shade: 0 ,time: 2000 });
				}
				parent.dg.datagrid('reload');
				parent.layer.close(index); //关闭对话框。
			}
		});

	});
</script>
</head>

<body>
	<div style="padding:20px 0 0 15px;height:auto">
		<form id="importForm" style="height:auto" target="hidden_div"
			action="" method="post" enctype="multipart/form-data" >
			<input id="uploadFile" accept="application/vnd.ms-excel" name="file" type="file" style="width:330px" />
			<div id="alertLabel" style="width:100%;margin-top: 10px;">导入文件按照模板格式，仅允许导入“xls”格式文件！</div>
		</form>
	</div>
</body>
</html>
