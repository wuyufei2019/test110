<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>指标考评信息</title>
	<meta name="decorator" content="default"/>
      <script type="text/javascript" src="${ctx}/static/model/js/targetmanger/mbzb/mbkp/qy_index.js?v=1.1"></script>
</head>
<body >
<!-- 工具栏 -->
<div id="target_mbkp_tb" style="padding:5px;height:auto">
	<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline pull-left">				
	<div class="form-group">	
   <c:if test="${ usertype eq 'isbloc' }">
        <input type="text"  name="target_mbkp_qyname" style="height:30px" class="easyui-textbox"
               data-options="width:200,prompt: '企业名称'" />
    </c:if>
	<input type="text"  name="target_mbkp_tname" style="height:30px" class="easyui-textbox" data-options="prompt: '指标名称'"/>																        										
	<input type="text" name="target_mbkp_year"id="target_mbkp_year" class="easyui-combobox"  style="width:100px;height: 30px;" 
   data-options="editable: false ,prompt:'年份',panelHeight:'120',valueField: 'year',textField: 'year' ">
    <input name="target_mbkp_quarter" type="text" class="easyui-combobox" style="height: 30px;width:100px;"
           data-options="prompt:'季度',panelHeight:'auto',data:[{value:'1',text:'第1季度'},{value:'2',text:'第2季度'},{value:'3',text:'第3季度'},{value:'4',text:'第4季度'}] ">
    <shiro:hasPermission name="target:mbkp:viewall">
        <input name="target_mbkp_deptname" type="text" class="easyui-combobox" style="height: 30px;width:150px;"
            data-options="prompt:'责任部门',panelHeight:'100',valueField:'text', textField:'text',url: '${ctx}/system/department/deptjson'"/>
    </shiro:hasPermission>
    <input name="target_mbkp_db" type="text" class="easyui-combobox" style="height: 30px;width:100px;"
           data-options="prompt:'达标情况',panelHeight:'auto',data:[{value:'1',text:'达标'},{value:'0',text:'未达标'}] ">
    </div>
	</form>
   <div class="pull-left">
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
         <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
      </div>
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
            <shiro:hasPermission name="target:mbkp:add">
                 <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()" title="添加"><i class="fa fa-plus"></i> 添加</button> 
            </shiro:hasPermission> 
            <shiro:hasPermission name="target:mbkp:update">
                <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 修改</button> 
            </shiro:hasPermission>
            <shiro:hasPermission name="target:mbkp:delete">
               <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i> 删除</button> 
            </shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="refresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			</div>
	</div>
	</div> 
</div>
<div id="yearcombo" style="width:100%;height: 100%;display: none;text-align: center;padding-top: 20px ;">
    <input type="text"id="year" class="easyui-combobox"  style="width:150px;height: 30px;" data-options="
   valueField:'year',textField:'year',prompt:'考核年份',panelHeight:'auto'"/> 
    <input type="text"id="quarter" class="easyui-combobox"  style="width:150px;height: 30px;" data-options="
   valueField:'value',textField:'text',prompt:'考核季度',panelHeight:'auto',data:[{value:'1',text:'第一季度'},{value:'2',text:'第二季度'},{value:'3',text:'第三季度'},{value:'4',text:'第四季度'}]"/> 
</div>
<table id="target_mbkp_dg"></table> 
<script type="text/javascript">
var qyid='${qyid}';
//年份下拉框加载数据
var usertype='${usertype}';
$(function(){
	var data = [];
	var thisYear;
	var startYear=new Date().getUTCFullYear();
	for(var i=0;i<2;i++){
		thisYear=startYear-i;
		data.push({"year":thisYear});
	}
	$("#target_mbkp_year").combobox("loadData", data);
});

//弹窗增加
function init(id) {
	openDialog("导入季度考评信息",ctx+"/target/mbkp/create","800px", "60%","");
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(row[i].id1!=qyid) {
			layer.msg("无法对下属企业数据进行操作！",{time: 3000});
			return;
		}	
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/target/mbkp/delete/"+ids,
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

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.id1!=qyid) {
		layer.msg("无法对下属企业数据进行操作！",{time: 3000});
		return;
	}
	openDialog("修改目标考评信息",ctx+"/target/mbkp/update/"+row.id,"800px", "350px","");
	
}
//统计页面弹窗
function statistics(){
	openDialogView("统计分析",ctx+"/target/mbkp/statistics","50%", "100%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看目标考评信息",ctx+"/target/mbkp/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	var Columns=dg.datagrid("options").columns[0];
	window.exdata=[];
	for(var i=0;i<Columns.length;i++){
		if(Columns[i].field.toUpperCase()!="ID")
		window.exdata.push({colval:Columns[i].field, coltext:Columns[i].title});
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/target/mbkp/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		},
	});
		
}</script>

</body>
</html>