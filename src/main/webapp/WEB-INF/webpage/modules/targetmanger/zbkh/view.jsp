<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标考核管理</title>
<meta name="decorator" content="default" />
</head>
<body>

      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
           <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35">${target.year }</td>
               <td class="width-15 active"><label class="pull-right">考核部门：</label></td>
               <td class="width-35"><input id="ID2" name="ID2" type="text" value="${target.ID2 }" class="easyui-combobox" 
                     data-options="readonly:true,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'"></td>
            </tr>

            <tr>
              <td class="width-15 active"><label class="pull-right">目标指标数：</label></td>
              <td class="width-35">${target.tnum }
                      <a class='btn btn-info btn-xs' onclick="viewfp()">查看详情</a></td>
              <td class="width-15 active"><label class="pull-right">自评达标数：</label></td>
              <td class="width-35">${target.zpnum }
                     <a class='btn btn-info btn-xs' onclick="viewzp()">查看详情</a></td>
            </tr>
            <tr>
              <td class="width-15 active"><label class="pull-right">考核达标数：</label></td>
              <td class="width-35">${target.khnum }</td>
              <td class="width-15 active"><label class="pull-right">考核结论：</label></td>
              <td class="width-35"> <c:if test="${target.m4 eq 1}">达标</c:if><c:if test="${target.m4 eq 0}">不达标</c:if></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">考核人：</label></td>
               <td class="width-35">${target.m2 }</td>
               <td class="width-15 active"><label class="pull-right">考核日期：</label></td>
               <td class="width-35" ><fmt:formatDate value="${target.m3 }" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">考核情况说明：</label></td>
               <td class="width-35" colspan="3" >${target.m5 }</td>
            </tr>
         </tbody>
         </table>
	<div id="select_dlg" style="display:none;height:100%">
		<table id="target_mbzp_dg"></table>
	</div>
	<script type="text/javascript">
	var  dg=$('#target_mbzp_dg').datagrid({    
	    fit : true,
 		fitColumns : true,
 		border : false,
 		idField : 'id',
 		striped:true,
 		pagination:false,
 		rownumbers:true,
 		nowrap:false,
 		pageNumber:1,
 		scrollbarSize:0,
 		singleSelect:true,
 		striped:true,
 		checkOnSelect:true,
 		selectOnCheck:false,
 		});
      function viewzp(){
      	layer.open({
      	    type: 1,  
      	    title: '信息详情',
      	    area:['700px','300px'],
      	    content: $("#select_dlg"),
      	    btn: ['关闭'],
      	    success: function(index,layero){
      	    	$.ajax({
      	    		url  : ctx+'/target/mbzp/qylist',
      	    		data : {'target_mbzp_year':'${target.year }','target_mbzp_bm':"${target.ID2 }",'target_mbzp_db': '1'},
      	    		success : function(data){
      	    				dg.datagrid({
      	    				    columns:[[    
									{field:'year',title:'年度',sortable: false,width:50,align:'center'},    
									{field:'department',title:'责任部门',sortable: false,width:80,align:'center'},
									{field:'quarter',title:'季度',sortable: false,width:50,align:'center',
										  formatter:function(value){
										  if(value) {
												  return "第"+value+"季度";
										  }
										  else return ;
									}},
									{field:'targetname',title:'指标名称',sortable: false,width:100,align:'center'},    
									{field:'targetval',title:'指标值',sortable: false,width:80,align:'center'},    
									{field:'m4',title:'达标情况',sortable: false,width:50,align:'center',
										  formatter:function(value,row){
											  if(value) {
													  return value==1?"达标":"未达标";
											  }
											  else 
												  return "";
										  }},    
									{field:'m1',title:'评定人',sortable: false,width:80,align:'center'},    
									{field:'m2',title:'评定日期',sortable: false,width:80,align:'center',
										  formatter:function(value){
											  if(value) return new Date(value).format('yyyy-MM-dd');
											  else return ;
										  }},   
      	    		 		    	]]
      	    				});
      	 		    	
      	 		    	dg.datagrid('loadData',{"rows":data.rows});//datagrid加载数据
      	 		      },
      	    	});
      	    },
      	    cancel: function(index){}
      	}); 
      
      }
      function viewfp(){
      	layer.open({
      	    type: 1,  
      	    title: '信息详情',
      	    area:['700px','300px'],
      	    content: $("#select_dlg"),
      	    btn: ['关闭'],
      	    success: function(index,layero){
      	    	$.ajax({
      	    		url  : ctx+'/target/zbfp/qylist',
      	    		data : {'target_zbfp_m1':'${target.year }','target_zbfp_deptid':"${target.ID2 }"},
      	    		success : function(data){
      	    				dg.datagrid({
      	    					 columns:[[    
										{field:'m1',title:'年份',sortable: false,width:50,align:'center'},    
										 {field:'m3',title:'级别',sortable: false,width:50,align:'center',
											  formatter:function(value){
											  if(value==1) return '公司';
											  if(value==2) return '部门';
											  if(value==3) return '班组';
											  else return ;
										 }},
										 {field:'deptname',title:'责任部门',sortable: false,width:50,align:'center'},
										 {field:'targetname',title:'指标名称',sortable: false,width:150,align:'center'},    
										 {field:'targetval',title:'指标值',sortable: false,width:50,align:'center'},    
										 /* {field:'m7',title:'制定人',sortable: false,width:100,align:'center'},    
										 {field:'m8',title:'审核人',sortable: false,width:100,align:'center'},    
										 {field:'m9',title:'批准人',sortable: false,width:100,align:'center'},   */  
										 {field:'m5',title:'批准日期',sortable: false,width:80,align:'center',
											  formatter:function(value){
												  if(value) return new Date(value).format('yyyy-MM-dd');
												  else return ;
											  }}    
      	    					    ]],
      	    				});
      	 		    	
      	 		    	dg.datagrid('loadData',{"rows":data.rows});//datagrid加载数据
      	 		      },
      	    	});
      	    },
      	    cancel: function(index){}
      	}); 
      
      }
      
      </script>
</body>

</html>