<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>点巡检记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
		<tbody>	
				<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-85" colspan="3">
						${dxjjl.qyname }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">设备名称：</label></td>
					<td class="width-35">${dxjjl.sbname }</td>
					<td class="width-15 active"><label class="pull-right">所属班次：</label></td>
					<td class="width-35">${dxjjl.bcname }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dxjjl.createtime }" /></td>
					<td class="width-15 active"><label class="pull-right">检查人：</label></td>
					<td class="width-35">${dxjjl.jcr }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查结果：</label></td>
					<td class="width-35">${dxjjl.cljg}</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-85" colspan="3">
					 <c:if test="${not empty dxjjl.checkphoto}">
					 <c:forTokens items="${dxjjl.checkphoto}" delims="||" var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 0 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" style="max-height:100px;" /><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查备注：</label></td>
					<td class="width-85" colspan="3">${dxjjl.note }</td>
				</tr>
				
				<tr>
					<td colspan="4" style="text-align: center"><label>设备巡检项目</label></td> 
				</tr>
		</tbody>	
	</table>
	<table id="dxj_dxjjl_dg" style="height: 280px;"></table>
<script type="text/javascript">
var xjjlid='${dxjjl.id}';
var dg;
$(function(){
	dg=$('#dxj_dxjjl_dg').datagrid({    
	method: "post",
    url:ctx+'/dxj/dxjjl/yhlist?xjjlid='+xjjlid,
    fit : false,
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
  	        {field:'sbxmname',title:'设备项目名称',width:80 },  
  	        {field:'jcjg',title:'检查结果',width:50,align:'center',
  	        	formatter:function(value, row, index){
  	        		if(row.id!=null) {
  	        			return '异常';  
	              	}else{
	              		return '正常';  
	              	}
  	        	}
  	        },
  	        {field:'dangerdesc',title:'问题描述',width:100,align:'center' },  
  	        {field:'dangerphoto',title:'隐患照片',width:100,align:'center',
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
    onLoadSuccess: function(){
    },
    onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
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