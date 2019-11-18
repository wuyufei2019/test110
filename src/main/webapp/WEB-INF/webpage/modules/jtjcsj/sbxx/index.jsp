<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/jtjcsj/sbxx/index.js?v=1.8"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="bis_scsb_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="bis_scsb_cx_m2" name="bis_scsb_cx_m2" class="easyui-combobox"  style="height: 30px;"
       	        data-options="width:198,valueField:'text',textField:'text',url:'${ctx }/bis/scsb/sblblist',prompt:'设备类别',
       	        						onSelect : function(rec){
											 	$.ajax({
											 	url:'${ctx}/bis/scsb/sbmclist',
					 							data:{'sblb':rec.text},
					 							dataType:'json',
					 							type : 'POST',
					 				 			success: function (data){
					 				 			
						           				}
										    });
										 }"/>
		<input type="text" id="bis_scsb_cx_m3" name="bis_scsb_cx_m3" style="height: 30px;" class="easyui-textbox" data-options="prompt: '设备名称' ,editable:true, width:200 "/>
       	<input type="text" id="bis_scsb_cx_m8" name="bis_scsb_cx_m8" style="height:30px" class="easyui-combobox" data-options="panelHeight:'auto', width:200,prompt: '投用日期',editable:false,data: [
								       {value:'1',text:'3年内'},
								       {value:'2',text:'3-5年内'},
								       {value:'3',text:'5-8年内'},
							           {value:'4',text:'8年以上'}] ,
										"/>
		<input type="text" id="bis_scsb_equipCode" name="bis_scsb_equipCode" class="easyui-combobox"  style="height: 30px;" data-options="panelHeight:'100px',width:200,valueField:'text',textField:'text',url:'${ctx }/jtjcsj/sbxx/equipCodeJson',prompt:'设备编码'">							
		
										    
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span> 
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="jtjcsj:sbxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:sbxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:sbxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="jtjcsj:sbxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jtjcsj:sbxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="jtjcsj:sbxx:exin">
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/jtjcsj/sbxx/exinjump','${ctx}/jtjcsj/sbxx/exin','${ctx}/static/templates/危险源设备信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
	</div>
	</div> 
	   
</div>


<table id="bis_scsb_dg"></table> 

</body>
</html>