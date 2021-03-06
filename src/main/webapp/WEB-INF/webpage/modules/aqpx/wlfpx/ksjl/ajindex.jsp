<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/wlfpx/ksjl/ajindex.js?v=1.3"></script>
<script type="text/javascript">
var ctx='${ctx}';
</script>
<style type="text/css">
.aqpx_pxjl_sj{
font-size: 12px;
margin: 8px 0px;
}
.aqpx_pxjl_sj span{
margin-right: 20px;
}
.aqpx_pxjl_sj input{
width: 13px;
margin: 0px;
padding: 0px;
}
.aqpx_pxjl_sjtk{
font-size: 12px;
margin: 5px 0px;
}
</style>
</head>
<body>
<div class="easyui-tabs" fit="true">
		<div title="考试记录" style="height:100%;" data-options="">
			<div id="aqpx_pxjl_tb" style="padding:5px;height:auto">
        	<form id="aqpx_pxjl_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
        		<input type="text" id="aqpx_pxjl_qyname" name="aqpx_pxjl_qyname" class="easyui-combobox"  style="height: 30px;" data-options="editable:true ,valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课程名称'"/>
       	        <input type="text" name="aqpx_pxjl_cx_m4" class="easyui-textbox" style="height: 30px;" data-options="prompt: '外来方单位名称'"/>
				<input type="text" id="aqpx_pxjl_cx_m3" name="aqpx_pxjl_cx_m3" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' , editable:false,data: [
								        {value:'合格',text:'合格'},
								        {value:'不合格',text:'不合格'} ] ,prompt: '考试结果'"/>       	        
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="viewsj()" ><i class="fa fa-search-plus"></i> 查看考卷</span>
			</form>
			</div>
			<table id="aqpx_pxjl_dg"></table> 
		</div>

</div>

<div id="aqpx_pxjl_dlg"></div>  

</body>
</html>