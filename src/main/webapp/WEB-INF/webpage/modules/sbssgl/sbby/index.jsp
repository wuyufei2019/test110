<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>设备设施管理设备保养管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/sbby/index.js?v=1.1"></script>
</head>
<body >
<div id="sbssgl_sbby_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">				
	<div class="form-group">
		<c:if test="${type eq '2'}">
			<input type="text" name="qyname" class="easyui-combobox" style="height: 30px;" data-options="editable:true,panelHeight:'150px',valueField: 'text',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson',prompt: '企业名称' "/>
		</c:if>
		<input name="jhnf" class="easyui-numberbox"  style="height: 30px;" data-options="min:0,editable:true,prompt: '年份'" />
		<input id="jhlx" name="jhlx" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '保养计划类型',panelHeight:'auto',data: [
										{value:'1',text:'月度'},
										{value:'2',text:'季度'},
										{value:'3',text:'半年度'},
										{value:'4',text:'年度'}]" />
		<input id="byqx" name="byqx" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '执行保养期限',panelHeight:'auto'" />
		<input name="sbbh" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备编号'" />
		<input name="sbname" class="easyui-textbox"  style="height: 30px;" data-options="editable:true,prompt: '设备名称'" />
		<input name="zt" class="easyui-combobox"  style="height: 30px;" data-options="editable:false,prompt: '是否上传附件',panelHeight:'auto',data: [
										{value:'0',text:'未上传附件'},
										{value:'1',text:'已上传附件'}]" />
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
		<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
    </div>
	</form>

	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sbssgl:sbby:update">
			    <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbssgl:sbby:delete">
				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
			</shiro:hasPermission>
			<shiro:hasPermission name="sbssgl:sbby:view">
        		<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="view()" title="查看"><i class="fa fa-search-plus"></i> 查看</button> 
        	</shiro:hasPermission>
	        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
		</div>
	</div>
	</div> 	   
</div>
<table id="sbssgl_sbby_dg"></table> 
<script>
	var uploadrole = '0';//上传附件 
	var type = '${type}';
	var sbtype = '${sbtype}';//设备类型
	$('#jhlx').combobox({
		onSelect: function(record){
			var jhlx = record.value;
			if(jhlx == '1'){
				$('#byqx').combobox({  
					editable:false,
					prompt: '执行保养期限',
					panelHeight:'150px',
					data: [ {value:'1',text:'1月'},
							{value:'2',text:'2月'},
							{value:'3',text:'3月'},
							{value:'4',text:'4月'},
							{value:'5',text:'5月'},
							{value:'6',text:'6月'},
							{value:'7',text:'7月'},
							{value:'8',text:'8月'},
							{value:'9',text:'9月'},
							{value:'10',text:'10月'},
							{value:'11',text:'11月'},
							{value:'12',text:'12月'}]
				}); 
			}else if(jhlx == '2'){
				$('#byqx').combobox({  
					editable:false,
					prompt: '执行保养期限',
					panelHeight:'auto',
					data: [ {value:'1',text:'第1季度'},
							{value:'2',text:'第2季度'},
							{value:'3',text:'第3季度'},
							{value:'4',text:'第4季度'}]
				}); 
			}else if(jhlx == '3'){
				$('#byqx').combobox({  
					editable:false,
					prompt: '执行保养期限',
					panelHeight:'auto',
					data: [ {value:'1',text:'上半年度'},
							{value:'2',text:'下半年度'}]
				}); 
			}else if(jhlx == '4'){
				$('#byqx').combobox({  
					editable:false,
					prompt: '执行保养期限',
					panelHeight:'auto',
					data: [ {value:'1',text:'全年'}]
				}); 
			}
		}
	});
</script>
<shiro:hasPermission name="sbssgl:sbby:upload">
	<script>uploadrole = '1';</script>
</shiro:hasPermission>
</body>
</html>