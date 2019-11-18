<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业危险因素辨识管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/fxgk/fxdxx/index.js?v=1.4"></script>
	<script type="text/javascript">
	   var dg;
	   var d;
	   var zz = 1;
	   var f='${sys}';
	   var fxdj='${fxdj}';
	   $(function() {
	  		var url = ctx + '/fxgk/fxxx/ajlist';
			dg = $('#fxgk_fxxx_dg').datagrid(
					{
						method : "post",
						url : url,
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						pagination : true,
						rownumbers : true,
						nowrap : false,
						pageNumber : 1,
						pageSize : 50,
						pageList : [ 50, 100, 150, 200, 250 ],
						scrollbarSize : 5,
						singleSelect : true,
						striped : true,
						columns : [ [
				{field:'id',title:'id',checkbox:true,width:50,align:'center'},    
		        {field:'m1',title:'风险点名称',sortable:false,width:100,align:'center'},    
		        {field:'m2',title:'风险类别',sortable:false,width:100,align:'center'},    
		        {field:'m3',title:'行业',sortable:false,width:100,align:'center'},    
		        {field:'m4',title:'行业类别',sortable:false,width:100,align:'center'},    
		        {field:'m8',title:'事故类型',sortable:false,width:100,align:'center'},    
		        {field:'m9',title:'风险分级',sortable:false,width:100,align:'center', 
		        	formatter : function(value, row, index){
		        		if(value=='1') return value='红';
		        		else if(value=='2') return value='橙';
		        		else if(value=='3') return value='黄';
		        		else if(value=='4') return value='蓝'; 
		        	},
		        	styler : function(value, row, index){
		        		if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
		        		else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
		        		else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
		        		else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
		     		}
		        
		        },{field:'m16',title:'现场照片',sortable:false,width:50,align:'center',
		        	formatter : function(value, row, index){
		        		var content="";
	                  	if(value!=null&&value!='') {
	                  		var urls=value.split(",");
	                  		for(var i in urls){
	                    		content=content+ "<a class='fa fa-picture-o btn-danger btn-outline' onclick=openImgView('"+urls[i].split("||")[0]+"')>照片"+(parseInt(i)+1)+"</a>";
	                    	} 
	                    	return content;
	                  	}else
	                  		return '/';
		        	}
		        },
		        {field:'zt',title:'状态',sortable:false,width:50,align:'center',
		        	formatter : function(value, row, index) {
		        		if(row.stoppointid != null){
		        			return "<a class='btn btn-danger btn-xs'>停产</a>";
		        		}else{
		        			return "<a class='btn btn-info btn-xs'>正常</a>";
		        		}
		        	}
		        }	
						] ],
						onDblClickRow : function(rowdata, rowindex, rowDomElement) {
							view();
						},
						onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
						},
						onBeforeLoad:function(param){
					    	if(f!=''&&f=='sys'){
					    		$("#fxgk_fxxx_fxfj").combobox('setValue',fxdj);
					    		param.fxgk_fxxx_fxfj=fxdj;
					    	 }
						    },
						checkOnSelect : false,
						selectOnCheck : false,
						toolbar : '#fxgk_fxxx_tb'
					});
		});
	   
	 //停产
	   function disable(){
	       var row = dg.datagrid('getSelected');
	       if(row==null) {
	           layer.msg("请选择一行记录！",{time: 1000});
	           return;
	       }
	       if(row.stoppointid != null){
	           layer.msg("该风险点已停产！");
	           return;
	       }
	       openDialog("添加停产日期", ctx + "/yhpc/stoppoint/disableindex/"+row.id+"/1", "400px","350px", "");
	   }

	   //恢复
	   function enable(){
	       var row = dg.datagrid('getSelected');
	       if(row==null) {
	           layer.msg("请选择一行记录！",{time: 1000});
	           return;
	       }
	       if(row.stoppointid == null){
	           layer.msg("该风险点状态正常！");
	           return;
	       }
	       top.layer.confirm('确定要恢复该风险点？', {icon: 3, title:'提示'}, function(index){
	           $.ajax({
	               type:'get',
	               url: ctx + "/yhpc/stoppoint/enable/"+row.id+"/1",
	               success: function(data){
	                   layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
	                   top.layer.close(index);
	                   dg.datagrid('reload');
	                   dg.datagrid('clearChecked');
	                   dg.datagrid('clearSelections');
	               }
	           });
	       });
	   }
	</script>
</head>
<body >
<!-- 工具栏 -->
<div id="fxgk_fxxx_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<input type="text" id="fxgk_fxxx_name" name="fxgk_fxxx_name" class="easyui-textbox" style="height: 30px;" data-options="prompt: '风险点名称'" />
		<input type="text" name="fxgk_fxxx_fxlb" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险类别',
								editable:true ,panelHeight:'auto',valueField: 'text',textField: 'text',url:'${ctx}/tcode/dict/fxfl'" />
		<input type="text" id="fxgk_fxxx_fxfj"name="fxgk_fxxx_fxfj" class="easyui-combobox" style="height: 30px;" data-options="prompt: '风险分级',panelHeight:'auto',
								 data: [{value:'1',text:'红'},
								        {value:'2',text:'橙'},
								        {value:'3',text:'黄'},
								        {value:'4',text:'蓝'}]" />
		<input type="text" name="fxgk_fxxx_sglx" class="easyui-textbox" style="height: 30px;" data-options="prompt: '事故类型'" />
		<input  type="hidden" id="panduan" name="panduan" value="1"/>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>  
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="fxgk:fxxx:add">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加"><i class="fa fa-plus"></i> 添加</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:addfz">
		       		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="addfz()" title="复制"><i class="fa fa-copyright"></i> 复制</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="fxgk:fxxx:bdxjnr">
		       	<button class="btn btn-white btn-sm"  data-toggle="tooltip" data-placement="left" onclick="checkContentManage()" title="绑定巡检内容"><i class="fa fa-plus"></i> 绑定巡检内容</button>
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="fxgk:fxxx:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fxgk:fxxx:disable">
				<button class="btn btn-danger btn-sm" data-toggle="tooltip" data-placement="left" onclick="disable()" title="停产"> 停产</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="fxgk:fxxx:enable">
				<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="enable()" title="恢复"> 恢复</button>
			</shiro:hasPermission>
				<button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="openImportDialog('${ctx}/fxgk/fxxx/exinjump','${ctx}/fxgk/fxxx/exin','${ctx}/static/templates/风险辨识导入模板.xls')" title="导入"><i class="fa fa-folder-open-o"></i> 导入</button>
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportexc()" title="导出Excel"><i class="fa fa-file-excel-o"></i> 导出Excel</button> 
        	</shiro:hasPermission>        	
        	<shiro:hasPermission name="fxgk:fxxx:export1">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportexc()" title="导出Excel"><i class="fa fa-file-excel-o"></i> 导出Excel</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportbs()" title="危险有害因素告知卡"><i class="fa fa-file-word-o"></i> 危险有害因素告知卡</button> 
        	</shiro:hasPermission>
        	 <shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-info btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportfxgzA2()" title="导出彩色风险告知卡WORD(A2)"><i class="fa fa-file-word-o"></i> 导出彩色风险告知卡(A2)</button> 
        	</shiro:hasPermission>
        	<shiro:hasPermission name="fxgk:fxxx:export">
        		<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="exportfxgzA3()" title="导出彩色风险告知卡WORD(A3)"><i class="fa fa-file-word-o"></i> 导出彩色风险告知卡(A3)</button> 
        	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 
	   
</div>

<table id="fxgk_fxxx_dg"></table> 

</body>
</html>