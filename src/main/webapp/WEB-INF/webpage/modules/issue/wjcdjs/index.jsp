<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>文件接收与传递</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/aqwj/wjcdjs/index.js?v=1.3"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="issue_cdjs_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" name="issue_wjcdjs_cx_m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '文件名称'" />
		<input type="text" name="check_company_fbstarttime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布开始日期' "/>
		<input type="text" name="check_company_fbendtime" class="easyui-datebox" style="height: 30px;" data-options="panelHeight:'auto' ,editable:false , prompt: '发布结束日期' "/>
		<input type="text" name="issue_wjcdjs_m1" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '查阅情况', 
																												data:[{value:'0',text:'未查看'},{value:'1',text:'已查看'}]"/>
		<input type="text" name="issue_wjcdjs_m2" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '下载情况', 
																												data:[{value:'0',text:'未下载'},{value:'1',text:'已下载'}]"/>
		<input type="text" name="issue_wjcdjs_m5" class="easyui-combobox" style="height: 30px;" data-options="panelHeight:'auto', prompt: '回执情况', 
																												data:[{value:'0',text:'未回执'},{value:'1',text:'已回执'}]"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
          <shiro:hasPermission name="issue:wjcdjs:view">
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看回执内容"><i class="fa fa-search-plus"></i> 查看回执内容</button> 
          </shiro:hasPermission>
			<shiro:hasPermission name="issue:wjcdjs:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
               
        	<shiro:hasPermission name="issue:wjcdjs:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button> 
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>
<table id="issue_cdjs_dg"></table> 
</body>
</html>