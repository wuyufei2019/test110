<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业风险点辨识管理</title>
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
	   
	</script>
</head>
<body >

	<div class="easyui-layout" style="height:100%; ">
		<div id="fxgk_fxxx_tb" style="padding:5px;height:auto">


		</div>

		<table id="fxgk_fxxx_dg"></table>
	</div>

</body>
</html>