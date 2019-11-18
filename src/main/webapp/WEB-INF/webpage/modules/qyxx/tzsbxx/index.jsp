<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title></title>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/qyxx/tzsbxx/index.js?v=2.1"></script>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;" >
       	        <input type="text" id="bis_tzsbxx_cx_m1" name="bis_tzsbxx_cx_m1" style="height: 30px;" class="easyui-textbox" data-options="prompt: '特种设备名称'"/>
		        <input id="m3" name="M3" class="easyui-combobox"  style="width:178px;height: 30px;"
				data-options="editable:false ,prompt: '设备类型',valueField: 'id',textField: 'text',url:'${ctx}/tcode/dict/tzsblx' " />
				<input type="text" id="bis_tzsb_cx_time1" name="time1" style="height:30px" class="easyui-combobox" data-options="width:200,prompt: '到期时间查询',editable:false,data: [
								       {value:'7',text:'一个星期'},
								       {value:'14',text:'两个星期'},
								       {value:'21',text:'三个星期'},
							           {value:'30',text:'一个月'},
							           {value:'60',text:'两个月'} ,
							           {value:'0',text:'已过期'}] ,
							           onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#bis_tzsb_cx_time2').combobox('clear');
								 		}
        							}"/>
        							  <input type="text" class="easyui-datebox" id="bis_tzsb_cx_time2" name="time2"  style="height:30px" 
		          data-options="width:200,prompt: '具体到期时间查询' ,
		          onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#bis_tzsb_cx_time1').combobox('clear');
								 		}
        							}" />
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
	<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
			
				       	<shiro:hasPermission name="bis:tzsbxx:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				       	</shiro:hasPermission>
				        <shiro:hasPermission name="bis:tzsbxx:update">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				        </shiro:hasPermission>
				       	<shiro:hasPermission name="bis:tzsbxx:delete">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
				        </shiro:hasPermission>
				        <shiro:hasPermission name="bis:tzsbxx:view">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="bis:tzsbxx:export">
						    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="bis:tzsbxx:exin">
							<button class="btn btn-white btn-sm "  data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/tzsbxx/exinjump','${ctx}/bis/tzsbxx/exin','${ctx}/static/templates/特种设备信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
						</shiro:hasPermission>
						<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
					</div>
				</div>
			</div>
        </div>
  </div>
<table id="dg"></table> 
<script type="text/javascript">
var f='${sys}';
</script>
</body>
</html>