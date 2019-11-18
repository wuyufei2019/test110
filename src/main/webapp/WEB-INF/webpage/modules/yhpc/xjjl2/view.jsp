<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>巡检记录</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<form id="inputForm" class="form-horizontal" >
			  <tbody>
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
				
<%--				<tr >
					<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-35" colspan="3">
					${xjlist.qyname }
					</td>
				</tr>--%>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查点：</label></td>
					<td class="width-35">${xjlist.checkpointname }</td>
					<td class="width-15 active"><label class="pull-right">所属班次：</label></td>
					<td class="width-35">
							${xjlist.checkorder }
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${xjlist.createtime }" /></td>
					<td class="width-15 active"><label class="pull-right">检查人：</label></td>
					<td class="width-35">${xjlist.checkperson }</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查结果：</label></td>
					<td class="width-35">
						${xjlist.checkresult}
					</td>
					<td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					<td class="width-35" >
					 <c:if test="${not empty xjlist.checkphoto}">
					 <c:forTokens items="${xjlist.checkphoto}" delims="||" var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 0 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" style="max-width:100px;max-height:50px;" /><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">问题备注：</label></td>
					<td class="width-35" colspan="3">${xjlist.note }</td>
				</tr>
				
				<tr>
					<td colspan="4" style="text-align: center"><label>检查内容</label></td> 
				</tr>	
			</table>
				</tbody>		
			<table id="fxgk_xjnr_dg"></table>
	</form>
<script type="text/javascript">
var jcdid='${xjlist.jcdid}';
var xjjlid='${xjlist.id}';
var type='${xjlist.type}';
var dg;
$(function(){
	/*dg=$('#fxgk_xjnr_dg').datagrid({
	method: "post",
    url:ctx+'/fxgk/fxxx/xjnrlist3?jcdid='+jcdid+'&xjjlid='+xjjlid+'&type='+type,
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
  	        {field:'yhzp',title:'隐患照片/巡检备注',width:50,align:'center',
  	        	formatter : function(value, row, index) {
    				var content="";
                  	if(value!=null&&value!='') {
                  		var arr1=value.split("||");
                    	for (var i = 0; i < arr1.length-1; i++) {
                    		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />'; 
                    	} 
                    	return content;
                  	}else if(row.zcqk!=null && row.zcqk!=''){
                  		return row.zcqk;
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
    toolbar:'#fxgk_xjnr_tb'
	});*/
	
});

//查看整改信息
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.ychid!=null&&row.ychid!=''&&row.error==1){
		openDialogView("查看整改复查记录",ctx+"/wghgl/xjjlzg/view/"+row.ychid,"900px", "400px","");
	}
}
</script>
</body>
</html>