<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>储罐报警信息</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/zxjkyj/bjyj/cgxx/index.js?v=1"></script>
<script type="text/javascript">
var ctx='${ctx}';
</script>
</head>
<body>
<div class="easyui-tabs" fit="true">
		<div title="未处理" style="height:100%;" data-options="">
			<div id="bjyj_cgxx_tb" style="padding:5px;height:auto">
			<div class="row">
					<div class="col-sm-12">	
        	<form id="bjyj_cgxx_searchFrom" style="margin-bottom: 8px;" action="" class="form-inline">
        	    <c:if test="${usertype != '1'}">
					<input type="text" id=fmew_bj_cx_m1 name="fmew_bj_cx_m1" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/cgssjc/qyjson',prompt: '企业名称' "/>
        		</c:if>
       	 		<input type="text" class="easyui-datetimebox" id="bjyj_cgxx_cx_time1" name="bjyj_cgxx_cx_time1"  style="height:30px" data-options="width:200,prompt: '报警时间起' " />       	        
        		<input type="text" class="easyui-datetimebox" id="bjyj_cgxx_cx_time2" name="bjyj_cgxx_cx_time2"  style="height:30px" data-options="width:200,prompt: '报警时间止' " />       	        
    			<input type="text" id="bjlx" name="bjlx" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '报警类型',data: [
								        {value:'液位报警',text:'液位报警'},
								        {value:'温度报警',text:'温度报警'},
								        {value:'压力报警',text:'压力报警'}
								        ] "/> 
				<input id="status" type="hidden" name="status" value="0" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="pull-left">
			<span id="divper">
				<shiro:hasPermission name="zxjkyj:cgbj:export">
           			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport1()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	    		</shiro:hasPermission>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i>查看</button> 
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
			</div>
			<div style = "text-align:right;">
        	</div>
        	</div>  
        	</div>
        	</div>      
			<table id="bjyj_cgxx_dg"></table> 
		</div>

		
		<div title="已处理" style="height:100%;" data-options="">
			<div id="bjyj_cgxx_tb2" style="padding:5px;height:auto">	
			<div class="row">
					<div class="col-sm-12">	
        	<form id="bjyj_cgxx_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline">
       	 		<input type="text" class="easyui-datetimebox" id="bjyj_cgxx_cx_time3" name="bjyj_cgxx_cx_time3"  style="height:30px" data-options="width:200,prompt: '报警时间起' " />       	        
        		<input type="text" class="easyui-datetimebox" id="bjyj_cgxx_cx_time4" name="bjyj_cgxx_cx_time4"  style="height:30px" data-options="width:200,prompt: '报警时间止' " />       	        
    			<input type="text" id="bjlx2" name="bjlx2" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false ,valueField: 'value',textField: 'text',prompt: '报警类型',data: [
								        {value:'液位报警',text:'液位报警'},
								        {value:'温度报警',text:'温度报警'},
								        {value:'压力报警',text:'压力报警'}
								        ] "/> 
				<input id="status2" type="hidden" name="status" value="1" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clearA2()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="pull-left">
			<span id="divper">
				<shiro:hasPermission name="zxjkyj:cgbj:export">
           			<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport2()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
	    		</shiro:hasPermission>
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view2()" title="查看"><i class="fa fa-search-plus"></i>查看</button> 
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</span>
			</div>
			<div style = "text-align:right;">
        	</div>
        	</div>  
        	  </div>
        	</div> 
			<table id="bjyj_cgxx_dg2"></table> 
		</div>
</div>


<div id="bjyj_cgxx_dlg"></div>  

</body>
</html>