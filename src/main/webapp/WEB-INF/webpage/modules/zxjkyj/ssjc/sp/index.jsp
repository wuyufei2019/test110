<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>视频监控</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div style="margin: 10px;text-align: center;">
		<input type="text" id="qy_name" name="qy_name" class="easyui-combobox" value="${qyname}"  style="height: 30px;width:300px;" data-options="editable:true ,valueField: 'id',textField: 'm1',url:'${ctx}/zxjkyj/spjk/qylist',prompt: '企业名称' "/>
		<button class="btn btn-info btn-sm" onclick="search()"><i class="fa fa-search"></i> 查询</button>
	</div>
	<div id="qiyelist" style="width: 1300px;margin: 0 auto;" >
		<c:forEach items="${qylist}" var="st" varStatus="status">
		<div style="width:150px;height:200px;float: left;margin: 10px 15px;text-align: center;" >
		<a href="javascript:void(0)" onclick="showsp('${st.id}')" > 
		<img src="${ctxStatic}/model/images/spjk.png" style="width: 150px;"/> 
		<p style="color: #246793;padding: 5px 0;font-weight: bold;">${st.m1}</p>
		</a>
		</div>
		</c:forEach> 
	</div>

<script type="text/javascript">
//显示企业视频信息页面
function showsp(n){
	openDialogView("视频监控查看",ctx+'/zxjkyj/spjk/showqysp/'+n,"100%", "100%","");
}


function search(){
	var qyname=$("#qy_name").combobox("getText");
	window.location.href='${ctx}/zxjkyj/spjk/index?qyname='+qyname;
}
</script>

</body>
</html>