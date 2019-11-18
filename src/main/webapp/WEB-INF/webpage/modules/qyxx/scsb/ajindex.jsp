<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>生产设备信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/qyxx/scsb/ajindex.js?v=2.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_scsb_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input id="bis_scsb_cx_id1" name="bis_scsb_cx_id1" class="easyui-combobox" style="height: 30px;"  data-options="valueField: 'text' , textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt:'企业名称' " />
		<input type="text" id="bis_scsb_cx_m2" name="bis_scsb_cx_m2" class="easyui-combobox"  style="height: 30px;"
       	        data-options="width:198,valueField:'text',textField:'text',url:'${ctx }/bis/scsb/sblblist',prompt:'设备类别',
       	        						onSelect : function(rec){
											 	$.ajax({
											 	url:'${ctx}/bis/scsb/sbmclist',
					 							data:{'sblb':rec.text},
					 							dataType:'json',
					 							type : 'POST',
					 				 			success: function (data){
					 				 			$('#bis_scsb_cx_m3').combobox('setValue', '');
						           				$('#bis_scsb_cx_m3').combobox('loadData', data); 
						           				}
										    });
										 }"/>
		<input type="text" id="bis_scsb_cx_m3" name="bis_scsb_cx_m3" style="height: 30px;" class="easyui-combobox" data-options="prompt: '设备名称' ,editable:true, width:200 ,valueField:'text' ,textField:'text' "/>
       	<input type="text" id="bis_scsb_cx_m8" name="bis_scsb_cx_m8" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '投用日期',editable:false,data: [
								       {value:'1',text:'3年内'},
								       {value:'2',text:'3-5年内'},
								       {value:'3',text:'5-8年内'},
							           {value:'4',text:'8年以上'}] ,
										"/>       
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="bis:scsb:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:scsb:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:scsb:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="bis:scsb:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:scsb:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="bis:scsb:statistics">
	           <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="statistics()" title="统计"><i class="fa fa-bar-chart"></i> 统计</button>
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_scsb_dg"></table> 

</body>
</html>