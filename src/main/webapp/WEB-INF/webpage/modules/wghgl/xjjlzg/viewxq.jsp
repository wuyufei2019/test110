<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
     <form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr >
					<td class="width-15 active"><label class="pull-right">巡检点名称：</label></td>
					<td class="width-35" colspan="3">
					${yhpcjl.wgdname}
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-35" colspan="3">
					${yhpcjl.jcnr }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患处理状态：</label></td>
					<td class="width-35">
					${yhpcjl.yhzt }
					</td>
					<td class="width-15 active"><label class="pull-right">隐患发现人：</label></td>
					<td class="width-35">
					${yhpcjl.yhfxr }
					</td>
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患发现时间：</label></td>
					<td class="width-35" >
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${yhpcjl.createtime }" />
					</td>
					<td class="width-15 active"><label class="pull-right">计划整改时间：</label></td>
					<td class="width-35">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${yhpcjl.sechandletime }" />
					</td>	
				</tr>
				<tr >
					<td class="width-15 active"><label class="pull-right">隐患备注：</label></td>
					<td class="width-35" colspan="3">
					${yhpcjl.dangerdesc }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty yhpcjl.dangerphoto}">
					 <c:forTokens items="${yhpcjl.dangerphoto}" delims="||" var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;""/><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
		  	</tbody>
			</table>
       </form>
    
    <h3 style="text-align: center;color: #337ab7;">整改复查情况</h3>
	<div style="height:200px;">
		<table id="wghgl_yhzg_dg" style="width: 100%;height: 100%"></table>
	</div>
	<script type="text/javascript">
	var yhjlid='${yhpcjl.id}';
	var dg;
	$(function(){
		dg=$('#wghgl_yhzg_dg').datagrid({    
		method: "post",
	    url:ctx+'/wghgl/xjjlzg/zglist?yhjlid='+yhjlid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
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
	  	        {field:'zgr',title:'整改复查人员',width:50 },  
	  	        {field:'handletime',title:'整改复查时间',width:50,align:'center',
	  	        	formatter : function(value, row, index) {
		              	if(value!=null&&value!='') {
		              		var datetime=new Date(value);
		              		return datetime.format('yyyy-MM-dd hh:mm:ss');
		              	}
		            }
	  	        },
	  	        {field:'handlemoney',title:'整改费用',width:30,align:'center',
	  	         	formatter : function(value, row, index) {
		              	if(row.handletype=='2') {
		              		return '/';
		              	}else{
		              		return value;
		              	}
		            }
	  	        },
	  	        {field:'handleuploadphoto',title:'整改复查照片',width:50,align:'center',
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
	             },
	  	        {field:'handletype',title:'类型',width:30,align:'center',
		  	        formatter : function(value, row, index) {
		              	if(value=='1') {
		              		return '整改';
		              	}else if(value=='2'){
		              		return '复查';
		              	}
		            }
	  	        },
	  	        {field:'handledesc',title:'整改复查备注',width:80,align:'center'},
	  	        {field:'handlestatus',title:'审核结果',width:40,align:'center',
		  	        formatter : function(value, row, index) {
		              	if(row.handletype=='1') {
		              		return '/';
		              	}else if(value=='2'){
		              		return '审核通过';
		              	}else if(value=='1'){
		              		return '审核未通过';
		              	}
		            }
	  	        }
	    ]],
	    onLoadSuccess: function(){
	    },
	    onLoadError:function(){
	    	layer.open({title: '提示',offset: 'rb',content: '数据加载失败！',shade: 0 ,time: 2000 });
	    },
		checkOnSelect:false,
		selectOnCheck:false,
	    toolbar:''
		});
		
	});
</script>
</body>
</html>