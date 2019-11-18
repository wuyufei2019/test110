<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>信息报送查看</title>
<meta name="decorator" content="default"/>
<style type="text/css">
	 
	.fltitle{
		text-align:center;
		font-weight:bold;
		color: #BA0101;
		font-size: 24px;
		margin-bottom: 8px;
		padding-top: 30px;
		text-shadow: 1px 2px 2px #D2D7DA;
	}
	.intro{
    	font-size: 12px;
    	font-weight: normal;
    	text-align: center;
    	color: #999;
	}
	.shadow{
		width:1000px;
		box-shadow: 5px 5px 10px 5px #999;
		margin: 10px auto;
	}
	
</style>
</head>
<body>
<div class="shadow">
	<div style="padding: 30px 35px;">
	  	<div  class="fltitle" >${tfsj.m1 }</div>
	  	<div  class="intro" >发生地点：${tfsj.m3 } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 发生时间: <fmt:formatDate type="date"  value="${tfsj.m4 }" /></div>
	  	<hr />
		<br />
	  	<div >
	  	<p style="line-height:20px;">${tfsj.m2 }</p>
		<br />
	    <br /> 
	  	</div>
  	</div>
  	<div id="issue_sjpj_tb" style="padding:5px;height:auto">
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
				       	<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()" title="添加意见"><i class="fa fa-plus"></i> 添加意见</button>
					</div>
				</div>
			</div> 
		</div>

		<table id="issue_sjpj_dg"></table>
 </div>
 
 <script type="text/javascript">
var dg;
var userid = '${userid}';
$(function(){
	dg=$('#issue_sjpj_dg').datagrid({    
	method: "post",
    url:ctx+'/issue/tfsj/pllist/'+${tfsj.ID}, 
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
        {field:'m1',title:'意见内容',sortable:false,width:100},  
        {field:'plr',title:'评论人',sortable:false,width:30,align:'center'},
        {field:'m2',title:'添加时间',sortable:false,width:40,align:'center',
        	formatter : function(value){
        		if(value!=null&&value!=''){
            		var date = new Date(value);
    	        	return date.format("yyyy-MM-dd hh:mm:ss"); 
            	}else{
            		return '';
            	}
        	}
        },
        {field:'cz',title:'操作',sortable:false,width:40,align:'center',
        	formatter : function(value,row){
        		if(row.id2 == userid){
        		    return "<a class='btn btn-info btn-xs' onclick='upd("+row.id+")'>修改</a>  <a style='margin-left:2px' class='btn btn-warning btn-xs' onclick='del("+row.id+")'>删除</a>";
        		}else{
        			return '';
        		}
        	}
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#issue_sjpj_tb'
	});
});
 
//弹窗增加
function add() {
	if('${tfsj.m5 }' == '3'){
		layer.msg("该信息已处理完成，禁止评论意见！",{time: 1000});
		return;
	}else{
		openDialog("添加处理意见",ctx+"/issue/tfsj/zcpl/"+${tfsj.ID},"800px", "400px","");
	}
} 

//弹窗修改
function upd(id){
	openDialog("修改意见",ctx+"/issue/tfsj/updatepl/"+id,"800px", "400px","");
	
}

//删除
function del(id){
	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/issue/tfsj/deletepl/"+id,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
			}
		});
	});
 
}
 </script>
</body>
</html>