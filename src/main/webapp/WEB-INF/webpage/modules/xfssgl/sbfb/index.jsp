<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备分布</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div style="margin: 10px;text-align: center;">
		<input type="text" id="qy_name" name="qy_name" class="easyui-combobox" value="${qyname}"  style="height: 30px;width:300px;" data-options="panelHeight: 'auto', editable:true ,valueField: 'id',textField: 'm1',url:'${ctx}/xfssgl/sbfb/qylist',prompt: '企业名称' "/>
		<button class="btn btn-info btn-sm" onclick="search()"><i class="fa fa-search"></i> 查询</button>
		<button class="btn btn-info btn-sm" onclick="reset()"><i class="fa fa-refresh">全部数据</i></button>
	</div>
	<div id="qiyelist" style="width: 1300px;margin: 0 auto;" >
		<c:forEach items="${qylist}" var="st" varStatus="status">
		<div style="width:150px;height:200px;float: left;margin: 10px 15px;text-align: center;" >
		<a href="javascript:void(0)" onclick="showfb('${st.id}')" > 
		<img src="${ctxStatic}/model/images/xfssgl/2.png" style="width: 100px;"/> 
		<p style="color: #246793;padding: 5px 0;font-weight: bold;">${st.m1}</p>
		</a>
		</div>
		</c:forEach> 
	</div>

<script type="text/javascript">
//显示企业视频信息页面
function showfb(n){
	openDialogView("消防设施分布查看",ctx+'/xfssgl/sbfb/showfb/'+n,"100%", "100%","");
}

//查询
function search(){
	var qyname=$("#qy_name").combobox("getText");
	window.location.href='${ctx}/xfssgl/sbfb/index?qyname='+qyname;
}

//刷新
function reset() {
	$("#qy_name").combobox('setValue','');
	search("keyword");
}
</script>

</body>
</html>