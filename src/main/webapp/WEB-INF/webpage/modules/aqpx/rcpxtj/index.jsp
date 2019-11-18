<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>日常培训统计</title>
	<meta name="decorator" content="default"/>
</head>
<body >
	<div title="员工学习统计" style="height:100%;">
		<div id="rcpxtj_tb2" style="padding:5px;height:auto">
			<div class="row">
				<div class="col-sm-12">
					<div class="pull-left">
				       	<form id="rcpxtj_searchFrom2" style="margin-bottom: 8px;" action="" class="form-inline" >
				       		<input type="text" id="ygname" name="ygname" class="easyui-textbox" style="height: 30px;" data-options="prompt: '员工姓名'" />
			       	        <span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search2()" ><i class="fa fa-search"></i> 查询</span>
							<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="clear2()" ><i class="fa fa-refresh"></i> 全部</span>
						</form>
					</div>
					<div class="pull-right">
		        	</div> 
	        	</div>
        	</div>  
       	</div> 
		<table id="rcpxtj_dg2"></table> 
	</div>
<script type="text/javascript">
var dg2;
var d;

//员工培训统计datagrid
$(function(){   
	dg2=$('#rcpxtj_dg2').datagrid({    
	method: "post",
    url:ctx+'/aqpx/rcpxtj/ygxxlist', 
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
	pageList : [50, 100, 150, 200, 250 ],
	scrollbarSize:5,
	singleSelect:true,
	striped:true,
    columns:[[    
        {field:'name',title:'员工姓名',width:70},  
        {field:'xxcount',title:'累计学习时间(分钟)',width:100,align:'center'},  
        {field:'kscount',title:'考试次数',width:50,align:'center'},
        {field:'xxsj',title:'最近学习时间',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if(value!=null&&value!=""){
         			var datetime=new Date(value);
         			return datetime.format('yyyy-MM-dd');
         		}else{
         			return '暂无学习记录';
         		}
            }
        },
        {field:'kssj',title:'最近考试时间',width:50,align:'center',
        	formatter : function(value, row, index) {
         		if(value!=null&&value!=""){
         			var datetime=new Date(value);
         			return datetime.format('yyyy-MM-dd');
         		}
            }
        },
        {field:'jgl',title:'考试合格率',width:50,align:'center',
         	formatter : function(value, row, index) {
         			return value+'%';
            }       	
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	view2();
    },
    onLoadSuccess: function(){
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#rcpxtj_tb2'
	});
});


//巡检点年检统计查询
function search2(){
	var obj=$("#rcpxtj_searchFrom2").serializeObject();
	$('#rcpxtj_dg2').datagrid('load',obj); 
}

//清空
function clear2(){
	$("#rcpxtj_searchFrom2").form("clear");
	search2();
}

// 查看
function view2() {
	var row = dg2.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	window.ygid=row.userid;
	openDialogView("安全培训记录",ctx + "/aqpx/rcpxtj/ygindex" ,"100%", "100%","");
}
</script>
</body>
</html>