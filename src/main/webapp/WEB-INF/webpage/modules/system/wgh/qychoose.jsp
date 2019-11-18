<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
     <title>绑定企业</title>
     <meta name="decorator" content="default"/>
     <script type="text/javascript" src="${ctxStatic}/common/initLocation.js"></script>
</head>
<body >

     <div class="easyui-panel" style="width:100%;height:100%;">
          <div class="easyui-layout" data-options="fit:true">
               <div collapsible="true"title="企业名称" data-options="region:'west'" style="width:35%;height:100%">
                    <div id="qylist">
                    </div></div>
               <div data-options="region:'center'" style="width:65%;height:100%">
                    <div id="qychoose_tb" style="padding:5px;height:auto">
                        <form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">                    
                           <div class="form-group" style="margin-top:10px">
                           <input type="text"name="qynm"id="qynm" class="easyui-textbox"style="width:140px;height: 30px" data-options="prompt:'企业名称'" />
                           <input id="zhen" name="zhen"class="easyui-combobox" style="width:140px;height:30px;"
                                        data-options="panelHeight:150,editable:false,prompt: '镇或街道',data: [
                                                  {value:'',text:'全部'},
                                                  {value:'东兴镇',text:'东兴镇'},
                                                  {value:'孤山镇',text:'孤山镇'},
                                                  {value:'季市镇',text:'季市镇'},
                                                  {value:'靖城街道',text:'靖城街道'},
                                                  {value:'马桥镇',text:'马桥镇'},
                                                  {value:'生祠镇',text:'生祠镇'},
                                                  {value:'西来镇',text:'西来镇'},
                                                  {value:'斜桥镇',text:'斜桥镇'},
                                                  {value:'新桥镇',text:'新桥镇'},
                                                  {value:'城南园区',text:'城南园区'},
                                                  {value:'城北园区',text:'城北园区'},
                                                  {value:'江阴靖江工业园区',text:'江阴靖江工业园区'},
                                                  {value:'开发区管委会',text:'开发区管委会'},
                                                  {value:'滨江新区',text:'滨江新区'},
                                                  {value:'江阴靖江工业园区办事处',text:'江阴靖江工业园区办事处'},
                                                ] " />
                           
                           </div>
                      <input type="hidden"name="code"id="code" />
                         </form>
                     <div class="pull-right" style="margin-top:-40px;margin-left:180px">
                    <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="searchqy()" ><i class="fa fa-search"></i> 查询</button>
                     <button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
                </div>
                </div>
                    <table id="qychoose_dg"></table> 
               </div>
          </div>
     </div>
<!-- 工具栏 -->

<script type="text/javascript">	
var dg;
var qylist=$('#qylist');
$("#code").val('${code}');

$(function(){
	dg=$('#qychoose_dg').datagrid({    
    url:"${ctx}"+"/system/admin/xzqy/qylist/",
    queryParams: {          
        "code":'${code}',
        "qynm":$("#qynm").textbox('getValue')
      },
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	pagination:false,
	rownumbers:true,
	scrollbarSize:0,
	singleSelect:false,
	striped:true,
    columns:[[    
{field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
{field:'m1',title:'企业名称',sortable:false,width:100,align:'center'},    
]],
checkOnSelect : true,
selectOnCheck : true,
onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
	
	$.each(rowdata.rows,function(i,row){
		if('1'==row.flag){
			$('#qychoose_dg').datagrid('checkRow',i);
		}else{
			return;
		}
	});
},
onCheck: function(rowIndex,rowData){
	var html = $("<div id='" + rowIndex + "' style='margin-left:5px;margin-bottom: 10px;'>"
			+ "<a style='color:#337ab7;text-decoration:none;cursor:pointer;'>" 
			+  rowData.m1+ "</a><span  onClick='removeQy("+ rowIndex
			+ ")' style='cursor: pointer;margin-left:5px;float:right'>删除</span></div>");
	qylist.append(html);
},
onUncheck: function(rowIndex,rowData){
	removeQy(rowIndex);
},
onUncheckAll: function(rowIndex,rowData){
	qylist.empty();
},
onCheckAll: function(rowIndex,rowData){
	 qylist.empty();
     var rows=dg.datagrid('getChecked');
     for(var i=0;i<rows.length;i++){
     	var html = $("<div id='" + rowIndex + "' style='margin-left:5px;margin-bottom: 10px;'>"
     			+ "<a style='color:#337ab7;text-decoration:none;cursor:pointer;'>" 
     			+  rows[i].m1+i+ "</a><span  onClick='removeQy("+ i
     			+ ")' style='cursor: pointer;margin-left:5px;float:right'>删除</span></div>");
     	qylist.append(html);
     }
},
    toolbar:'#qychoose_tb'
	});
});

function removeQy(id) {
	$("#" + id).remove();
	dg.datagrid('uncheckRow',id);
};

function searchqy() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

function reset(){
	$("#searchFrom").form("clear");
	$("#code").val('${code}');
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}
function getqyids(){
	var row=$('#qychoose_dg').datagrid('getChecked');
	var ids="";
	if (row != null) {
		for (var i = 0; i < row.length; i++) {
			ids = ids + row[i].id + ",";
		}
	}
	return ids.substring(0, ids.length-1);
}
</script>
</body>
</html>