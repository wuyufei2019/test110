<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/aqpxjl/index.js?v=1.7"></script>
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
       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课程名称'"/>
       	        <input type="text" name="aqpx_pxjl_cx_m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '员工名'"/>
				<input type="text" id="aqpx_pxjl_cx_m3" name="aqpx_pxjl_cx_m3" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' , editable:false,data: [
								        {value:'合格',text:'合格'},
								        {value:'不合格',text:'不合格'} ] ,prompt: '考试结果'"/>       	        
				<input type="hidden" id="kclx" name="kclx" value="1" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="viewsj()" ><i class="fa fa-search-plus"></i> 查看考卷</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="viewdz()" ><i class="fa fa-search-plus"></i> 错题订正</span>
                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="exportExcel2()" ><i class="fa fa-file-excel-o"></i> 导出</span>
                <shiro:hasPermission name="aqpx:pxjl:delete">
                	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="delKs()" ><i class="fa fa-trash-o"></i> 删除</span>
               	</shiro:hasPermission>
			</form>
		</div>
		<table id="aqpx_pxjl_dg"></table> 
	</div>

		
		<div title="学习记录" style="height:100%;">
			<div id="aqpx_pxjl_tb2" style="padding:5px;height:auto">
        	 
	        	<form id="aqpx_pxjl_searchFrom2" >
	       	        <input type="text" name="aqpx_pxjl_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '课程名称'"/>
	       	        <input type="text" name="aqpx_pxjl_cx_m2" class="easyui-textbox" style="height: 30px;" data-options="prompt: '员工名'"/>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA2()" ><i class="fa fa-refresh"></i> 全部</span>
	                <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="exportExcel()" ><i class="fa fa-file-excel-o"></i> 导出</span>
	                <shiro:hasPermission name="aqpx:pxjl:delete">
	                	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="delXx()" ><i class="fa fa-trash-o"></i> 删除</span>
	               	</shiro:hasPermission>
				</form>
        	</div>    
 		 
			<table id="aqpx_pxjl_dg2"></table> 
		</div>
</div>


<div id="aqpx_pxjl_dlg"></div>  

</body>
</html>