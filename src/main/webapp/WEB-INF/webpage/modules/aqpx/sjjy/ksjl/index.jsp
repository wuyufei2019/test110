<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>培训记录信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/sjjy/ksjl/index.js?v=1.1"></script>
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
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="viewsj()" ><i class="fa fa-search-plus"></i> 查看考卷</span>
				<shiro:hasPermission name="aqpx:pxjl:delete">
					<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="delKs()" ><i class="fa fa-trash-o"></i> 删除</span>
				</shiro:hasPermission>
			</form>
			</div>
			<table id="aqpx_pxjl_dg"></table> 
		</div>
      <div title="学习时长统计" style="height:100%;" data-options="">
         <div id="aqpx_kssc_tb" style="padding:5px;height:auto">
         <form id="aqpx_kssc_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
                 <input type="text" name="view_aqpx_kssc_m1" class="easyui-datebox" style="height: 30px;" data-options="prompt: '开始时间'"/>
                 <input type="text" name="view_aqpx_kssc_m2" class="easyui-datebox" style="height: 30px;" data-options="prompt: '结束时间'"/>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchkssc()" ><i class="fa fa-search"></i> 查询</span>
            <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
         </form>
         </div>
         <table id="aqpx_kssc_dg"></table> 
      </div>
   </div>

</body>
</html>