<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>网格月度绩效考核管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
<style type="text/css">
.myPanelHead .panel-title {
	font-size: 18px;
	height: 25px;
	line-height: 25px;
	color: red;
	text-align :center
}
</style>
</head>

<body  >
	<div class="easyui-panel" title="网格月度绩效考核" style="width:100%;height:100%;" data-options=" headerCls:'myPanelHead'">
		<div id="layout" class="easyui-layout" style="height:100%; " title="dsfasfdasfads">
			<div data-options="region:'west',split:true,border:true,title:'网格名称'" style="width: 200px">
				<table id="menuDg"></table>
			</div>
			<div data-options="region:'center',split:true,border:false,title:'考核情况'">
				<div id="tg_tb" style="padding:5px;height:auto">
					<form id="searchFrom" action="" style="margin-bottom: 8px;" class="form-inline">
						<div class="form-group">
							<input id="search_month" name="month" style="height: 30px;" class="easyui-datebox"
								data-options="editable :false,prompt: '日期'" /> <span class="btn btn-primary btn-rounded btn-outline btn-sm "
								onclick="search()"><i class="fa fa-search"></i> 查询</span> <span
								class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i>
								全部</span>
						</div>
					</form>
					<div>
						<shiro:hasPermission name="yhpc:wgkpimon:add">
							<button class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="left" onclick="init()" title="添加">
								<i class="fa fa-plus"></i> 考核评分
							</button>
						</shiro:hasPermission>
						<%--  <shiro:hasPermission name="yhpc:wgkpimon:update">
  				<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="upd()" title="修改"><i class="fa fa-file-text-o"></i> 评分</button>
		        </shiro:hasPermission> --%>
						<%-- 		        <shiro:hasPermission name="yhpc:wgkpimon:delete">
		        <button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="del()" title="删除"><i class="fa fa-trash-o"></i>删除</button> 
		        </shiro:hasPermission>
 --%>
					</div>
				</div>
				<table id="dg"></table>
			</div>
		</div>
	</div>


	<div id="dlg"></div> 
<div id="icon_dlg"></div>  
<div id="select_type" style="display:none;height:100%"><span id="datehide">
 <input id="add_month" style="height: 30px;" class="easyui-datebox" data-options="editable :false,prompt: '日期'" /></span>
   <table id="areadata"></table>
</div>
<div id="select_type1" style="display:none;height:100%">
   <table id="areadata1"></table>
</div>
<script type="text/javascript">
var dg;
var d;
var menuDg;
var wgcode="";
var wgid="";
var dtime=new Date().format("yyyy-MM");
$(function(){   
	menuDg=$('#menuDg').treegrid({  
	method: "get",
	url:'${ctx}/system/admin/xzqy/xzqyjson', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'id',
	treeField: 'text',
	animate:true, 
	rownumbers:true,
	singleSelect:true,
	scrollbarSize:0,
	striped:true,
	loadFilter: lazyLoadFilter,
    columns:[[    
        {field:'id',title:'id',hidden:true},    
        {field:'text',title:'名称',width:100}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    dataPlain: true,
    onClickRow:function(rowData){
    	$('#layout').layout('panel', 'center').panel({
			title : rowData.text + '考核情况'
		});
     	wgcode = rowData.id;
     	wgid = rowData.s;
    	d.datagrid('reload',{wgcode: wgcode});
    }
	});
	
	d=$('#dg').datagrid({   
	method: "get",
    url:'${ctx}/yhpc/wgkpi/month/overviewlist',
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
	scrollbarSize:0,
	singleSelect:true,
	striped:true,
	toolbar:'#tg_tb',
      columns:[[
{
	field : 'id',
	title : 'id',
	hidden : true
	},{
		field : 'time',
		title : '年月',
		width : 50
	}, 
	{
		field : 'm1',
		title : '网格',
		width : 50
	}, {
		field : 'score',
		title : '得分',
		width : 40,
		sortable: true,
		styler: function(){
			return 'background-color:rgb(249, 156, 130)';
}
	}, {
		field : 'caozuo',
		title : '操作',
		width : 50,
		formatter : function(value, row) {
			return "<a class='btn btn-info btn-xs' onclick='view(" + row.id + ")'>查看信息</a>&nbsp;&nbsp;"
			       +"<a class='btn btn-danger btn-xs' onclick='upd(" + row.id + ")'>修改信息</a>";
		}
	}
      ]],
	});
	loadMonthContorl($('#search_month'));
});

//弹窗增加
function init() {
	var row = menuDg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请先选择网格！",{time: 1000});
		return;
	}
	loadMonthContorl($("#add_month"),true);
	$("#datehide").show();
	openDialog();
}
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if (dg.datagrid('validateRow', editIndex)){
		dg.datagrid('endEdit', editIndex);
		return true;
	} else {
		return false;
	}
}
function ValidateNumber(val) {
	var row = dg.datagrid('getData').rows[editIndex];
	if (row.score < val)
		return false;
	else
		return true;
}
function view(id) {
	layer.open({
		type: 1,  
		area: ['100%', '100%'],
		title:'查看评分信息',
		content: $("#select_type1"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	   var dg1 =$('#areadata1').datagrid({    
	    		nowrap:false,
	    		method: "post",
	    		url:'${ctx}/yhpc/wgkpi/month/viewdetail/'+id,
	    	    loadMsg :'正在加载',
	    		fitColumns : true,
				border : true,
				animate : true,
				rownumbers : true,
				striped : true,
				nowrap : false,
				scrollbarSize : 0,
	    	    columns:[[{field:'time',title:'年月',width:50}, 
	              {field:'wgname',title:'网格名称',width:100},    
	              {field:'name',title:'评分项目',width:100,formatter: function(value,row){
	            	  return row.name+"("+row.allscore+"分)";
	              }},
	              {field:'content',title:'考核内容',width:200},
	              {field:'standard',title:'考核标准',width:100},
	              {field:'descore',title:'扣分',width:40},
	              {field:'note',title:'备注',width:100},
	              {field:'total',title:'项目总得分',width:50,formatter : function(value ,row){
	            	  if(row.allscore<value){
	            		  return 0;
	            	  }else{
	            		  row.total=row.allscore-value;
	            		  return row.total;
	            	  }
	              },styler: function(){
	      			return 'background-color:rgb(249, 156, 130)';
	              }}
	    	]],
	    	onLoadSuccess : function(rowdata, rowindex, rowDomElement) {
	    		dg1.datagrid("autoMergeCells", [ 'time', 'wgname','name','content','total']);
			},
	    	});
	      },
		cancel: function(index){ 
		}
	}); 
}

function openDialog(id){
	var url='${ctx}/yhpc/wgkpi/month/examinelist?wgcode='+wgcode;
	if(id) url='${ctx}/yhpc/wgkpi/month/viewdetail/'+id;
	layer.open({
		type: 1,  
		area: ['100%', '100%'],
		title:'添加评分',
		zIndex : 1 ,
		maxmin: false, 
		content: $("#select_type"),
		btn: ['全部保存','关闭'],
	    success: function(layero, index){
	    	    dg =$('#areadata').datagrid({    
	    		method: "post",
	    		url: url,
	    	    loadMsg :'正在加载',
	    		fitColumns : true,
				border : true,
				rownumbers : true,
				singleSelect : true,
				striped : true,
				nowrap : false,
				scrollbarSize : 0,
	    	    columns:[[    
	    	              {field:'id',title:'id',checkbox:true,width:100},    
	    	              {field:'time',title:'年月',width:50,formatter: function(value,row){
	    	            	  if(value) return value;
								else{
									return dtime;
								}
	    	              }}, 
	    	              {field:'wgname',title:'网格名称',width:100},    
	    	              {field:'name',title:'评分项目',width:100,formatter: function(value,row){
	    	            	  return row.name+"("+row.allscore+"分)";
	    	              }},
	    	              {field:'content',title:'考核内容',width:200},
	    	              {field:'standard',title:'考核标准',width:100},
	    	              {field:'descore',title:'扣分',width:40,formatter : function(value,row){
	    	            	  if(value) return value;
	    	            	  else{
	    	            		  row.descore=0;
	    	            		  return 0;
	    	            	  }
	    	              },editor : {
	    	            	     type      : 'textbox',
	    	            	     options   :   {
								 validType : ['number','FUN[ValidateNumber,\'不能大于项目总分\']']
							}}},
	    	              {field:'note',title:'备注',width:100,editor:'text',formatter : function(value,row){
								if (!value) {
									row.note ="";
									return row.note;
								} else
									return value;
							}}
	    	]],
	    	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
	    		editIndex=0;
				dg.datagrid('beginEdit', 0);
	    		dg.datagrid("autoMergeCells", [ 'time','wgname','name','content']);
	    	},
	    	  onClickCell: function(index,field,value){  
    		  var row = dg.datagrid('getData').rows[index];
					if (endEditing()){
						editIndex=index;
						dg.datagrid('beginEdit', index);
						dg.datagrid("autoMergeCells", [ 'time','wgname','name','content']);
					}
	    	    } 
	    	});
	      },
	      yes :function(index){
	    	  if(dg.datagrid('validateRow')){
	    		  dg.datagrid('endEdit', editIndex);
	    	  }else return ;
	    	  var rows=dg.datagrid('getData').rows;
	    	  len=rows.length;
	    	  if(len>0){
	    	  var count= 0;//总分
	    	  var tmpnum=0;
	    		  var rs=[];
	    		  for(var i=0;i<len;i++){
	    			  var row=rows[i];
	    	 		  rs.push({id : row.id,id1: row.ovid,id2: row.bid, descore: row.descore,note: row.note});//对象数组
	    	 		  tmpnum+=parseFloat(row.descore);//总减分
	    	 		  if(i==len-1||row.name!=rows[i+1].name){//获取评分项目总分 ，扣分数超过总分，则为0；
	    	 			  if(row.allscore<tmpnum){
	    	 				  count+=0;
	    	 			  }else{
	    	 				  count+=(row.allscore-tmpnum);
	    	 			  }
	    	 			 tmpnum=0;
	    	 		  }
	    	 	 }
	    		  if(rs.length>0){
	    		     var overview={"time" : dtime, "score" : count ,"id1" : wgid , "id" : rows[0].ovid };
	    			  $.ajax({ 
	    		            type:"POST", 
	    		            url:"${ctx}/yhpc/wgkpi/month/createAllSub", 
	    		            data:{"list": JSON.stringify(rs),"entity" : JSON.stringify(overview)}, 
	    		            success:function(data){ 
	    		            	if (data == 'success')
									layer.msg("保存成功");
								else
									layer.msg("保存失败");
								d.datagrid('reload', {
									wgcode : wgcode
								});                       
	    		            } 
	    		         }); 
	    		  }
	    	  }
	    	layer.close(index);
	      },
		cancel: function(index){ 
		}
	}); 
}

//修改
function upd(id){
	$("#datehide").hide();
	openDialog(id);

}
function change(){
	dtime=$("#add_month").datebox("getValue");
	dg.datagrid('load',{'time' : dtime});
}
//创建查询对象并查询
function search() {
	var time = $("#search_month").datebox("getValue");
	d.datagrid('load',{'wgcode' : wgcode,'time' : time?time : ""});
}
function reset() {
	$("#searchFrom").form("clear");
	d.datagrid('load', {'wgcode' : wgcode});
}
function loadMonthContorl(M2,flg){
	//日期控件只显示年月
     M2.datebox({
         onShowPanel: function () {//显示日期选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
             span.trigger('click'); //触发click事件弹出月份层
             if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
             if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                 tds = p.find('div.calendar-menu-month-inner td');
                 tds.click(function (e) {
                     e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                     var year = /\d{4}/.exec(span.html())[0]//得到年份
                     , month = parseInt($(this).attr('abbr'), 10); //
                     M2.datebox('hidePanel')//隐藏日期对象
                     .datebox('setValue', year + '-' + (month<10?'0'+month : month)); //设置日期的值
                	 //绑定事件
                	 if(flg){
                		 change(); 
                	 }
                 });
             }, 0);
             yearIpt.unbind();//解绑年份输入框中任何事件
         },
         parser: function (s) {
             if (!s) return new Date();
             var arr = s.split('-');
             return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
         },
         formatter: function (d) { return d.format("yyyy-MM"); }
     });
     var p = M2.datebox('panel'), //日期选择对象
         tds = false, //日期选择对象中月份
         aToday = p.find('a.datebox-current'),
         yearIpt = p.find('input.calendar-menu-year'),//年份输入框
         //显示月份层的触发控件
         span = aToday.length ? p.find('div.calendar-title span') :p.find('span.calendar-text'); 
     if (aToday.length) {//重新绑定新事件设置日期框为今天，防止弹出日期选择面板
         aToday.unbind('click').click(function () {
             var now=new Date();
             M2.datebox('hidePanel').datebox('setValue', now.format("yyyy-MM"));
         });
     }
}
</script>
</body>
</html>