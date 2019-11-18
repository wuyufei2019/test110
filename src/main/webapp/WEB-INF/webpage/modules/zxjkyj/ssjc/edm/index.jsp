<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>二道门人员数据监测</title>
	<meta name="decorator" content="default"/>
</head>
<body class="gray-bg">
<!-- 工具栏 -->
<div id="ssjc_edm_tb" style="padding:5px;height:auto">
	<div class="row">
	<div class="col-sm-12">
	<!-- 安监和集团公司界面 -->
	<c:if test="${usertype != '1'}">
		<div class="pull-left">
			<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
			<input type="text" id="ssjc_edm_qy_name" name="ssjc_edm_qy_name" class="easyui-combobox"  style="height: 30px;width:250px;" data-options="editable:true ,valueField: 'm1',textField: 'm1',url:'${ctx}/zxjkyj/edmjc/qyjson',prompt: '企业名称',
					onLoadSuccess:function(data){
						var text='接入实时数据企业数：'+data.length;
						$('#shuzi').append(text);
					}			
			 "/>
			</form>
        </div>
    </c:if>
        
		<div class="pull-left">
		<c:if test="${usertype != '1'}">
			<button  class="btn btn-info btn-sm" onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-danger btn-sm" onclick="reset()" ><i class="fa fa-refresh"></i> 全部</button>
		</c:if>
            <button class="btn btn-warning btn-sm" onclick="fileexport()"><i class="fa fa-external-link"></i> 导出</button>
            <button class="btn btn-primary btn-sm" onclick="refresh()"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
			<label id="shuzi" style="font-size: 15px;color:#FF00FF"></label>
		</div>
	</div>
	</div> 
</div>

<table id="ssjc_edm_dg"></table> 

<div id="selectQyPanel" style="display: none;width: 100%;height: 100%;">
	<table id="qyDatagrid">	</table>
</div>

<script type="text/javascript">
var usertype=${usertype};
var dg;
var d;

$(function(){ 
	dg=$('#ssjc_edm_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/zxjkyj/edmjc/list',
    fit : true,
	fitColumns : true,
	selectOnCheck:false,
	nowrap: false,
	idField : 'id',
	striped:true,
	scrollbarSize:0,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 50,
	pageList : [20, 50, 100, 150, 200, 250 ],
	singleSelect:true,
    columns:[[    
        {field:'qyname',title:'企业名称',sortable:false,width:100,align:'center'},    
        {field:'m1',title:'部门',sortable:false,width:100,align:'center'},    
     	{field:'m6',title:'实时人数',sortable:false,width:100,align:'center'},
    	{field:'m5',title:'更新时间',sortable:false,width:100,align:'center',
    		formatter: function(value,row,index){
			if(value!=null){
				var datetime = new Date();  
				return datetime.format('yyyy-MM-dd hh:mm:ss');
			}  
		}},
     	{field:'total',title:'合计人数',sortable:false,width:100,align:'center'}    
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	
    },
    onLoadSuccess: function(){
        $(this).datagrid("autoMergeCells",['qyname','total']);
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
    rowStyler:function(index,row){
		if (row.m10!=null&&row.m10>0&&row.m8>row.m10){
			return 'background-color:rgb(232, 190, 101);';
		}
	},
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#ssjc_edm_tb'
	});
  
});

timerID = setInterval("refresh()",60000);

//清空
function reset(){
	$("#searchFrom").form("clear");
	search();
}
//查询
function search(){
	$('#ssjc_edm_qy_name').combobox('setValue',$('#ssjc_edm_qy_name').combobox('getText') ) ;
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

//datagrid刷新
function refresh(){
dg.datagrid('reload'); 
}

//导出企业选项
function fileexport(){
	if(usertype=='1'){
		window.open('${ctx}/zxjkyj/edmjc/export?ids=');
	}else{
		layer.open({
		    type: 1,  
		    area: ['400px', '400px'],
		    title: '选择企业',
	        shift: 5,
	        shade :0,
		    content: $('#selectQyPanel'),
		    success: function(layero, index){
		    	loadSelectDatagrid();
		    },
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	var row = $('#qyDatagrid').datagrid('getChecked');
	
		    	var ids="";
		    	for(var i=0;i<row.length;i++){
		    		if(ids==""){
		    			ids=row[i].id;
		    		}else{
		    			ids=ids+","+row[i].id;
		    		}
		    	}
		    	window.open('${ctx}/zxjkyj/edmjc/export?ids='+ids);
			  },
			cancel: function(index){ 
		     }
		    
		});
	} 
}

//加载企业名称
function loadSelectDatagrid(){
	$('#qyDatagrid').datagrid({
	    url:'${ctx}/zxjkyj/edmjc/qyjson',
	    fit : true,
	    scrollbarSize:0,
		fitColumns : true,
		rownumbers:true,
	    columns:[[
			{field:'id',title:'id',checkbox:true},
			{field:'m1',title:'企业名称',width:100}
	    ]]
	});
}
</script>
</body>
</html>