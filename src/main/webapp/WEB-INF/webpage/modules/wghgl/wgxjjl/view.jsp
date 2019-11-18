<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">巡检点名称：</label></td>
					<td class="width-35" >${xjlist.wgdname }</td>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${xjlist.createtime }" /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">所属网格列：</label></td>
					<td class="width-35">${xjlist.wgname }</td>
					<td class="width-15 active"><label class="pull-right">所属企业：</label></td>
					<td class="width-35">${xjlist.qyname }</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">巡检网格员：</label></td>
					<td class="width-35">${xjlist.uname }</td>
					<td class="width-15 active"><label class="pull-right">所属班次：</label></td>
					<td class="width-35">${xjlist.bcname }</td>
				</tr>
				
				<tr>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查结果：</label></td>
					<td class="width-35"><c:if test="${xjlist.checkresult eq '1'}">有隐患</c:if><c:if test="${xjlist.checkresult eq '0'}">无隐患</c:if></td>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
						<td class="width-35" >
						 <c:if test="${not empty xjlist.checkphoto}">
						 <c:forTokens items="${xjlist.checkphoto}" delims="||" var="url" varStatus="urls">
						 	<c:set var="urlna" value="${fn:split(url, '||')}" />
						 	<div style="float: left;text-align: center;margin: 0 10px 0 0;">
						 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" style="max-width:100px;max-height:50px;"/><br/></a>
						 	</div>
						 </c:forTokens>
						 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">问题备注：</label></td>
					<td class="width-35" colspan="3">${xjlist.note }</td>
				</tr>
				</tbody>		
			</table>
			<center>检查内容</center>
			<table id="wghgl_xjnr_dg"></table>
	</form>
<script type="text/javascript">
var jcdid='${xjlist.checkpoint_id}';
var xjjlid='${xjlist.id}';
var dg;
$(function(){
	dg=$('#wghgl_xjnr_dg').datagrid({    
	method: "post",
    url:ctx+'/wghgl/wgd/xjnrlist?jcdid='+jcdid+'&xjjlid='+xjjlid,
    fit : false,
	fitColumns : true,
	selectOnCheck:false,
	idField : 'ID',
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
  	        {field:'content',title:'检查项目',width:250 },  
  	        {field:'checkresult',title:'检查结果',width:50,align:'center',
  	        	formatter:function(value, row, index){
  	        		if(row.error==1) {
  	        			return row.checkyes;  
	              	}else{
	              		return row.checkno;  
	              	}
  	        	}
  	        },
  	        {field:'yhzp',title:'隐患照片',width:50,align:'center',
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
    	view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:''
	});
	
});

//查看整改信息
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.ychid!=null&&row.ychid!=''){
		openDialogView("查看整改复查记录",ctx+"/wghgl/xjjlzg/view/"+row.ychid,"900px", "400px","");
	}
}
</script>
</body>
</html>