<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>请购资产</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<div id="sbssgl_qgsb_tb" style="padding:5px;height:auto">
	</div>
	<table id="sbssgl_qgsb_dg"></table> 	
		
<script type="text/javascript">
var datagridindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
var sbsqid = '${sbsqid}';//设备id
var dg;
$(function(){
	dg=$('#sbssgl_qgsb_dg').datagrid({    
	method: "post",
    url:ctx+'/sbssgl/sbsq/qgsblist/'+sbsqid, 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	striped:true,
	pagination:true,
	rownumbers:true,
	nowrap:false,
	pageNumber:1,
	pageSize : 50,
	pageList : [ 50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'id',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'资产名称',sortable:false,width:70,align : 'center'},
        {field:'m2',title:'规格型号',sortable:false,width:50,align:'center'},
        {field:'m3',title:'制造商/品牌',sortable:false,width:70,align:'center'},
        {field:'m4',title:'数量',sortable:false,width:50,align:'center'},
        {field:'m5',title:'单价（RMB）',sortable:false,width:50,align:'center'},
        {field:'m6',title:'总价（RMB）',sortable:false,width:50,align:'center'},
        {field:'ysstatus',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
        		if(value == '0'){
        			return "待上传附件";
        		}else if(value == '1'){
        			return "总部确认中";
        		}else if(value == '2'){
        			return "通过";
        		}else if(value == '3'){
        			return "驳回";
        		}else{
        			return '<button class="btn btn-info btn-sm" style="width:84px" data-toggle="tooltip" data-placement="left" onclick="addYs('+row.id+');" title="添加验收"><i class="fa fa-plus"></i> 添加验收</button>';
        		}
        	} 
        }
    ]],
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_qgsb_tb'
	});
});

function addYs(qgsbid){
	layer.open({
	    type: 2,  
	    shift: 1,
	    area: ["900px", "90%"],
	    title: "添加设备验收信息",
        maxmin: false, 
	    content: ctx+"/sbssgl/sbys/yscreate?qgsbid="+qgsbid ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];
	         var inputForm = body.find('#inputForm');
	         iframeWin.contentWindow.doSubmit();
		  },
		  cancel: function(index){ 
	     }
	}); 
	
}

function refreah(){
	console.log("refreah");
	dg.datagrid('reload'); 
}

</script>
</body>
</html>