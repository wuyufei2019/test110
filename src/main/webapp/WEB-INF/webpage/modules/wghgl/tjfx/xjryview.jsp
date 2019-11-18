<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检记录</title>
	<meta name="decorator" content="default"/>
</head>
<body >
<table id="wghgl_xjryview_dg"></table> 
<script type="text/javascript">
$(function(){
	var xjryid='${xjryid}';
	var yf='${yf}';
	var nf='${nf}';
	dg=$('#wghgl_xjryview_dg').datagrid({    
	method: "post",
    url:ctx+'/wghgl/tjfx/xjryviewlist', 
    queryParams:{'xjryid':xjryid,'yf':yf,'nf':nf},
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
        	//{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
        	{field : 'createtime',title : '检查时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }},
  			{field : 'wgdname',title : '巡检点名称',sortable : false,width : 90},
			{field : 'bcname',title : '所属班次',sortable : false,width : 50,align : 'center'},
			
			
			{field : 'checkresult',title : '巡检结果',sortable : false,width : 40,align : 'center',
				formatter : function(value, row, index) {
              		if(value=='0'){
              			return '无隐患';
              		}else if(value=='1'){
              			return '有隐患';
              		}
	            },styler: function(value,row,index){
					if (value == '1'){
						return 'color:red;';
					}
				}
	            
			},
			{field : 'checkphoto',title : '现场照片',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
    				var content="";
                  	if(value!=null&&value!='') {
                  		var arr1=value.split("||");
                    	for (var i = 0; i < arr1.length-1; i++) {
                    		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />'; 
                    	} 
                    	return content;
                  	}else{
                  		return '/';
                  	}
                }
			}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
	
});
</script>
</body>
</html>