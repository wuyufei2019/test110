<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
</head>
<body>
	<div id="tt" class="easyui-tabs" data-options="fit:true" style="width:850px;height:auto;text-align: center;">
		<input type="hidden" id="qyid" value="${qyid }"/>
		<div title="基本信息" style="text-align: center;">
			<iframe frameborder="0" src="${ctx}/bis/qyjbxx/tabview/${qyid }" style="width: 100%;height:98%;"></iframe>
		</div>
		<div title="文件收发记录" style="padding-top: 5px;" >
			<table id="wjsfjl"></table>
		</div>
		<div title="自查自报" style="padding-top: 5px;" >
			<table id="zczb"></table>
		</div>
		<div title="第三方检查记录" style="padding-top: 5px;" >
			<table id="dsfjcjl"></table>
		</div>
		<div title="隐患排查记录" style="padding-top: 5px;" >
			<table id="yhpcjl"></table>
		</div>
		<div title="网格点巡检信息" style="padding-top: 5px;" >
			<table id="wgdxjxx"></table>
		</div>
	</div>
<script type="text/javascript">
var qyid;
$(function(){
	qyid= $("#qyid").val();
	console.log(qyid);
	$('#tt').tabs({
		onSelect: function(title,index){
			//$("#div01").html()="<td>test</td>";
			 if(title=="文件收发记录"){
				wjsflist();	
			 }else if(title=="自查自报"){
				 zczb();
			 }else if(title=="第三方检查记录"){
				 dsfjcjl();
			 }else if(title=="隐患排查记录"){
				 yhpcjl();
			 }else if(title=="网格点巡检信息"){
				 wgdxjxx();
			 }
		}
	});
});

function wjsflist(){
	$('#wjsfjl').datagrid({    
		method: "post",
	    url:ctx+'/issue/aqwjfb/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'M1',title:'文件名称',sortable:false,width:100 },    
        {field:'M5',title:'文件类型',sortable:false,width:50,align:'center'},  
        
        {field:'look',title:'查阅情况',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        	 if(value==1){
        		 return '已查看';
        	 }
           	 return '未查看';
        	}
        },
        {field:'M2',title:'下载情况',sortable:false,width:50,align:'center',
        	formatter : function(value, row, index) {
        	 if(value==1){
        		 return '已下载';
        	 }
           	 return '未下载';
        	}
        },
        {field:'S2',title:'更新时间',sortable:false,width:60,align:'center',formatter : function(value){
        	if(value!=null&&value!=''){
        		var date = new Date(value);
	        	return date.format("yyyy-MM-dd hh:mm:ss"); 
        	}else{
        		return '';
        	}
        }},
        {field:'hz',title:'回执情况',align:'center',sortable:false,width:50,formatter : function(rowdata, rowindex, rowDomElement){
        	var html;
        	if(rowdata!=null&&rowdata=='1')
        		html="<a  class='btn btn-success btn-xs' onclick='updateHz("+ rowindex.ID + ")'>查看回执</a>";
        	else
        		html="无回执";
        	return html;
        }}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}

//弹窗增加
function updateHz(id) {
	openDialogView("查看回执信息", ctx + "/issue/wjcdjs/update/"+id, "600px", "300px", "");
}

function zczb(){
	$('#zczb').datagrid({    
		method: "post",
	    url:ctx+'/aqjd/jcjl/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        
										{
											field : 'ID',
											title : 'ID',
											checkbox : true,
											width : 50,
											align : 'center'
										},
										{
											field : 'ym',
											title : '检查时间',
											sortable : false,
											width : 50,
											align : 'center',
											formatter : function(value) {
												var arry=value.split("-");
												return arry[0]+"年"+arry[1]+"月"
											}
										},
										{
											field : 'm1',
											title : '专项检查名称',
											sortable : false,
											width : 125,
											align : 'center'
										},
										{
											field : 'm2',
											title : '初查时间',
											sortable : false,
											width : 80,
											align : 'center',
											formatter : function(value) {
												if (value != null && value != '') {
													var date = new Date(value);
													var y = date.getFullYear();
													var m = date.getMonth() + 1;
													var d = date.getDate();
													var hh = date.getHours();
													var mm = date.getMinutes();
													var ss = date.getSeconds();
													return y + '/' + (m < 10 ? ('0' + m) : m)
															+ '/' + (d < 10 ? ('0' + d) : d);
												} else {
													return '';
												}
											}
										},
										{
											field : 'm3',
											title : '初查负责人',
											sortable : false,
											width : 80,
											align : 'center'
										},
										
										{
											field : 'm10',
											title : '复查时间',
											sortable : false,
											width : 50,
											align : 'center',
											formatter : function(value) {
												if (value != null && value != '') {
													var date = new Date(value);
													var y = date.getFullYear();
													var m = date.getMonth() + 1;
													var d = date.getDate();
													var hh = date.getHours();
													var mm = date.getMinutes();
													var ss = date.getSeconds();
													return y + '/' + (m < 10 ? ('0' + m) : m)
															+ '/' + (d < 10 ? ('0' + d) : d);
												} else {
													return '';
												}
											}
										},
										{
											field : 'm9',
											title : '复查负责人',
											sortable : false,
											width : 80,
											align : 'center'
										},
										{
											field : 'checkflag',
											title : '检查进度',
											sortable : false,
											width : 80,
											align : 'center',
											formatter : function(value, row, index) {
									            	if(value==null||value==''||value=='0') return "<a  class='btn btn-info btn-xs' onclick='addReCheck("+row.id+")'>添加复查</a>";
									            	else return "检查已完成";
										},
								    	styler: function(value, row, index){
								    		if(value!=null&&value!=''&&value!='0')
								    			return 'background-color:rgb(216,155,176);';
								    	}
										}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function dsfjcjl(){
	$('#dsfjcjl').datagrid({    
		method: "post",
	    url:ctx+'/dsffw/jcjl/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'ID',title:'id',checkbox:true,width:50,align:'center'}, 
        {field:'dwname',title:'第三方单位名称',sortable:false,width:80,align:'center'},
        {field:'m2',title:'检查时间',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');  
              	}
          	} 
          },
        {field:'m4',title:'检查人员',sortable:false,width:80,align:'center'},
        {field:'m3',title:'整改期限',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');  
              	}
          	} 
          },
        {field:'m5',title:'整改负责人',sortable:false,width:80,align:'center'},
		{field :'m13',title :'操作',sortable : false,width : 50,align : 'center',
			formatter : function(value, row, index) {
	            	if(value==null||value==''||value=='0') return "已完成初查";
	            	if(value=='1') return "已完成复查";
		}
		}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function yhpcjl(){
	$('#yhpcjl').datagrid({    
		method: "post",
	    url:ctx+'/yhpc/yhpcjl/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
	{field : 'dq',title : '地区',sortable : false,width : 50,align : 'center'},
	{field : 'jcd',title : '检查点',sortable : false,width : 60,align : 'center'},
{field : 'createtime',title : '检查时间',sortable : false,width : 70,align : 'center',
	formatter : function(value, row, index) {
      	if(value!=null&&value!='') {
      		var datetime=new Date(value);
      		return datetime.format('yyyy-MM-dd hh:mm:ss');
      	}
    }},
{field : 'checktitle',title : '检查内容',sortable : false,width : 130,align : 'center'},
	{field : 'yh',title : '隐患',sortable : false,width : 60,align : 'center'},
  {field:'yhjb',title:'隐患级别',width:100,align : 'center',
  	formatter : function(value, row, index){
  		if(value=='0') return value='一般隐患';
  		if(value=='1') return value='二级隐患';
  		if(value=='2') return value='三级隐患';
  		if(value=='3') return value='四级隐患';
  	}
  },
{field : 'zgsj',title : '整改时间',sortable : false,width : 70,align : 'center',
	formatter : function(value, row, index) {
      	if(value!=null&&value!='') {
      		var datetime=new Date(value);
      		return datetime.format('yyyy-MM-dd hh:mm:ss');
      	}
    }},
{field : 'zgr',title : '整改人',sortable : false,width : 50,align : 'center'},
{field : 'rechecktime',title : '复查时间',sortable : false,width : 70,align : 'center',
	formatter : function(value, row, index) {
      	if(value!=null&&value!='') {
      		var datetime=new Date(value);
      		return datetime.format('yyyy-MM-dd hh:mm:ss');
      	}
    }},
{field : 'recheckuser',title : '复查人',sortable : false,width : 50,align : 'center'},   
{field : 'dangerstatus',title : '整改状态',sortable : false,width : 60,align : 'center',
	formatter : function(value, row, index) {
		if(value=='0'){
			return "<a style='margin:2px' class='btn btn-danger btn-xs' onclick='view("
			+row.id + ")'>未整改</a>"
		}else if(value=='1'){
			return "<a  class='btn btn-success btn-xs' onclick='view("
			+row.id + ")'>已整改</a>"
		}else if(value=='2'){
			return "<a  class='btn btn-danger btn-xs' onclick='view("
			+row.id + ")'>整改未完成</a>"
		}else if(value=='3'){
			return "<a  class='btn btn-success btn-xs' onclick='view("
			+row.id + ")'>已完成</a>"
		}

    }
}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}

function wgdxjxx(){
	dg=$('#wgdxjxx').datagrid({    
	method: "post",
    url:ctx+'/wghgl/xjjl/tablist/'+qyid, 
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
  			{field : 'id',title : 'id',checkbox : true,width : 50,align : 'center'},
  			{field : 'wgdname',title : '巡检点名称',sortable : false,width : 90},
  			{field : 'wgname',title : '所属网格列',sortable : false,width :50,align : 'center'},
  			{field : 'qyname',title : '所属企业',sortable : false,width : 90,align : 'center'},
  			{field : 'uname',title : '巡检网格员',sortable : false,width : 50,align : 'center'},
			{field : 'bcname',title : '所属班次',sortable : false,width : 50,align : 'center'},
			{field : 'createtime',title : '检查时间',sortable : false,width : 60,align : 'center',
				formatter : function(value, row, index) {
	              	if(value!=null&&value!='') {
	              		var datetime=new Date(value);
	              		return datetime.format('yyyy-MM-dd hh:mm:ss');
	              	}
	            }},
			
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
					if(value != null && value != ''){
						return '<a href="../xjjl/img?imgurl='+value+'" target="_blank">'+'查看照片'+'</a>';
					}
	            }
			}
    ]],
     onLoadError:function(){
    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
    },
	checkOnSelect:false,
	selectOnCheck:false,
	});
	
};
</script>
</body>
</html>