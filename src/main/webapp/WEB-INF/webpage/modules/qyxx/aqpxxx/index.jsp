<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script  type="text/javascript" src="${ctx}/static/model/js/qyxx/aqpxxx/index.js?v=2.4"></script>
<title></title>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" class="form-inline" style="margin-bottom: 8px;" >
       	        <input type="text" id="bis_aqpxxx_cx_m1" name="bis_aqpxxx_cx_m1" style="height:30px" class="easyui-textbox easyui-validatebox" data-options="width:150,prompt: '安全培训人员姓名'"/>
		        <input type="text" id="bis_aqpx_cx_m2" name="M2" style="height:30px" class="easyui-textbox easyui-validatebox" data-options="width:150,prompt: '安全培训人员类别'"/>
		        <input type="text" class="easyui-combobox" id="m6" name="M6"  style="height:30px" 
		          data-options="editable:true ,width:150,prompt: '发证机关',valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/fzjg' " />
		        <input type="text" id="bis_aqpxxx_cx_m10" name="M10" style="height:30px" class="easyui-textbox easyui-validatebox" data-options="width:150,prompt: '证书名称'"/>
		        <input type="text" id="bis_aqpxxx_cx_m11" name="M11" style="height:30px" class="easyui-textbox easyui-validatebox" data-options="width:150,prompt: '证书编号'"/>
		         <input type="text" id="bis_aqpx_cx_m3" name="M3" style="height:30px" class="easyui-combobox" data-options="width:200,prompt: '下次培训到期查询',editable:false,data: [
								       {value:'7',text:'一个星期'},
								       {value:'14',text:'两个星期'},
								       {value:'21',text:'三个星期'},
							           {value:'30',text:'一个月'},
							           {value:'60',text:'两个月'},
							            {value:'0',text:'已过期'}] ,
							           onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#m4').combobox('clear');
								 		}
        							}"/>
		        <input type="text" class="easyui-datebox" id="m4" name="M4"  style="height:30px" 
		          data-options="width:200,prompt: '有效期到期查询' ,
		          onSelect:function(rec){
								 		if(rec!=null&&rec!=''){
								 			$('#bis_aqpx_cx_m3').combobox('clear');
								 		}
        							}" />
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
			<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</form>
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
			
				       	<shiro:hasPermission name="bis:aqpxxx:add">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
				       	</shiro:hasPermission>
				        <shiro:hasPermission name="bis:aqpxxx:update">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button>
				        </shiro:hasPermission>
				       	<shiro:hasPermission name="bis:aqpxxx:delete">
				       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button>
				        </shiro:hasPermission>
				        <shiro:hasPermission name="bis:aqpxxx:view">
				        	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button>
			        	</shiro:hasPermission>
			        	<shiro:hasPermission name="bis:aqpxxx:export">
						     <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left"  onclick="fileexport()" title="导出"><i class="fa fa-external-link"></i> 导出</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="bis:aqpxxx:exin">
							<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/bis/aqpxxx/exinjump','${ctx}/bis/aqpxxx/exin','${ctx}/static/templates/安全培训信息导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
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