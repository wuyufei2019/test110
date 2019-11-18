<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患整改信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
	  <tbody>
	  	<tr>
			<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
			<td class="width-30" colspan="3">
				${jcyh.qyname}
			</td>
		</tr>

		<tr>
			<td class="width-20 active"><label class="pull-right">责任部门：</label></td>
			<td class="width-30">${jcyh.m13 }</td>
			<td class="width-20 active"><label class="pull-right">发生区域：</label></td>
			<td class="width-30">${jcyh.m14 }</td>
		</tr>
					  
		<tr>
			<td class="width-20 active"><label class="pull-right">发现时间：</label></td>
			<td class="width-30" ><fmt:formatDate type="date"  value="${jcyh.createtime }" /></td>
			<td class="width-20 active"><label class="pull-right">隐患类别：</label></td>
			<td class="width-30">${jcyh.m3 }</td>
		</tr>
        <tr >
            <td class="width-20 active"><label class="pull-right">检查人：</label></td>
            <td class="width-30" colspan='3' >${jcyh.jcr }</td>
        </tr>
		<tr >
			<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
			<td class="width-30" colspan='3' >${jcyh.jcnr }</td>
		</tr>

		<tr >
			<td class="width-20 active"><label class="pull-right">问题描述：</label></td>
			<td class="width-30" colspan='3' style="height:80px">${jcyh.m1 }</td>
		</tr>

		<tr >
			<td class="width-20 active"><label class="pull-right">解决措施：</label></td>
			<td class="width-30" colspan='3' style="height:80px">${jcyh.m15 }</td>
		</tr>
				
		<tr>
			<td class="width-20 active"><label class="pull-right">隐患照片：</label></td>
			<td class="width-80" colspan="3">
			 <c:if test="${not empty jcyh.m2}">
			 <c:forTokens items="${jcyh.m2}" delims="," var="url" varStatus="urls">
			 	<c:set var="urlna" value="${fn:split(url, '||')}" />
			 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
			 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="150px;"/><br/></a>
			 	</div>
			 </c:forTokens>
			 </c:if>
			</td>
		</tr>
		
		<tr >
			<td class="width-20 active"><label class="pull-right">隐患等级：</label></td>
			<td class="width-30" >${jcyh.m7 }</td>
			<td class="width-20 active"><label class="pull-right">计划完成时间：</label></td>
			<td class="width-30" ><fmt:formatDate type="date"  value="${jcyh.m4 }" /></td>
		</tr>
		</tbody>
	</table>
	
    <h3 style="text-align: center;color: #337ab7;">整改复查情况</h3>
    <div style="height:280px;">
   		<table id="yhpc_yhzg_dg" style="width: 100%;height: 100%"></table>
    </div>
	
	</tbody>
	</table>
	
<script type="text/javascript">
var yhid='${jcyh.id}';
console.log(yhid);
var dg;
$(function() {
	//历史整改复查信息
	dg=$('#yhpc_yhzg_dg').datagrid({    
		method: "post",
	    url:ctx+'/yhpc/jcyh/zglist?yhid='+yhid,
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
		              		return datetime.format('yyyy-MM-dd');
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
		              	}
		              	if(value=='0') {
		              		return '未整改';
		              	}else if(value=='1'){
		              		return '整改未完成';
		              	}else if(value=='2'){
		              		return '整改完成';
		              	}else if(value=='3'){
		              		return '复查不通过';
		              	}else if(value=='4'){
		              		return '复查通过';
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