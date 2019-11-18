<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
	<style>
		.tabs-wrap {
			position: relative;
			left: 0;
			overflow-y: scroll;
			width: 100%;
			overflow-x: hidden;
			margin: 0;
			padding: 0;
		}
	</style>
</head>
<body>

	<div id="tt" class="easyui-tabs" data-options="tabPosition:'left'" style="width:100%;height:100%;text-align: center;">

		<input type="hidden" id="qyid" value="${qyid }"/>
		<div title="基本信息" style="text-align: center;">
			<iframe frameborder="0" src="${ctx}/bis/qyjbxx/tabview/${qyid }" style="width: 100%;height:98%;"></iframe>
		</div>
		<div title="车间信息（${tj.cj}）" style="padding-top: 5px;" >
			<table id="bis_cjxx_dg"></table>
		</div>
		<div title="物料信息（${tj.wl}）" style="padding-top: 5px;" >
			<table id="bis_wlxx_dg"></table>
		</div>
		<div title="冶金信息（${tj.yj}）" style="padding-top: 5px;">
			<table id="bis_yjxx_dg"></table>
		</div>
		<div title="储罐信息（${tj.cg}）" style="padding-top: 5px;" >
			<table id="bis_cgxx_dg"></table>
		</div>
		<div title="粉尘信息（${tj.fc}）" style="padding-top: 5px;">
			<table id="bis_fcxx_dg"></table>
		</div>
		<div title="燃气信息（${tj.rq}）" style="padding-top: 5px;">
			<table id="bis_rq_dg"></table>
		</div>

		<div title="受限空间（${tj.sxkj}）" style="padding-top: 5px;">
			<table id="bis_sxkj_dg"></table>
		</div>

		<div title="抛丸信息（${tj.pw}）" style="padding-top: 5px;">
			<table id="bis_pw_dg"></table>
		</div>

		<div title="现场供气（${tj.xcgq}）" style="padding-top: 5px;">
			<table id="bis_xcgq_dg"></table>
		</div>

		<div title="化学能源信息（${tj.hxny}）" style="padding-top: 5px;">
			<table id="bis_hxny_dg"></table>
		</div>

		<div title="其他信息（${tj.qtxx}）" style="padding-top: 5px;">
			<table id="bis_qtxx_dg"></table>
		</div>

		<div title="重大危险源信息" style="padding-top: 5px;" >
			<!-- <table id="bis_hazard_dg"></table> -->
			<div id="div14"></div>
		</div>

		<div title="承包商信息（${tj.cbs}）" style="padding-top: 5px;">
			<table id="bis_cbsxx_dg"></table>
		</div>

		<div title="员工信息（${tj.yg}）" style="padding-top: 5px;" >
			<table id="bis_ygxx_dg"></table>
		</div>

		<div title="评价报告（${tj.pjbg}）" style="padding-top: 5px;" >
			<table id="bis_pjbg_dg"></table>
		</div>
		<%--<div title="视频监控" style="padding-top: 5px;">--%>
			<%--<div id="div16"></div>--%>
		<%--</div>--%>
		<%--<div title="实时监测预警（${tj.ssjc}）" style="padding-top: 5px;">--%>
			<%--<table id="bis_ssjc_dg"></table>--%>
		<%--</div>--%>
	</div>
<script type="text/javascript">
var qyid = ${qyid};
$(function(){
	qyid= $("#qyid").val();
	$('#tt').tabs({
		onSelect: function(title,index){
			//$("#div01").html()="<td>test</td>";
			if(title=="车间信息（${tj.cj}）"){//车间信息
				cjlist();	
			 }else if(title=="物料信息（${tj.wl}）"){//物料
				 wllist();
			 }else if(title=="储罐信息（${tj.cg}）"){//储罐
				 cglist();
			 }else if(title=="安全设施（${tj.aqss}）"){
				 aqsslist();
			 }else if(title=="员工信息（${tj.yg}）"){//员工信息
				 ygxxlist();
			 }else if(title=="重大危险源信息"){//重大危险源
				$("#div14").html('<iframe frameborder="0" scrolling="no" src="${ctx}/bis/hazard/tabindex/${qyid}" style="width:100%;height:500px;"></iframe>');
			 }else if(title=="粉尘信息（${tj.fc}）"){
				 fcxxlist();
			 }else if(title=="受限空间（${tj.sxkj}）"){  
				 sxkjlist();
			 }else if(title=="承包商信息（${tj.cbs}）"){
				 cbsxxlist();
			 }else if(title=="冶金信息（${tj.yj}）"){  
				 yjxxlist();
			 }else if(title=="评价报告（${tj.pjbg}）"){
				 pjbglist();
			 }else if (title =="视频监控"){
				$("#div16").html('<iframe frameborder="0" scrolling="yes" src="${ctx}/zxjkyj/spjk/showqysp/${qyid}" style="width:100%;height:100%;align:center"></iframe>');
			}else if (title == "燃气信息（${tj.rq}）"){
				rqlist();
			}else if(title == "抛丸信息（${tj.pw}）"){
				pwlist();
			}else if(title =="现场供气（${tj.xcgq}）"){
				xcgqlist();
			}else if (title == "化学能源信息（${tj.hxny}）"){
				hxnylist();
			}else if(title == "其他信息（${tj.qtxx}）"){
				qtxxlist();
			}
			 
		}
	});
});

function cjlist(){
	$('#bis_cjxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cjxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'车间名称',sortable:false,width:100},    
	        {field:'m2',title:'车间编号',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'火灾危险等级',sortable:false,width:100,align:'center', 
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='甲类';
	        		if(value=='2') return value='乙类';
	        		if(value=='3') return value='丙类';
	        		if(value=='4') return value='丁类';
	        		if(value=='5') return value='戊类';
	        	}
	        
	        },
	        {field:'m5',title:'建筑结构',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='砖混';
	        		if(value=='2') return value='钢结构';
	        		if(value=='3') return value='框架';
	        		if(value=='4') return value='其他';
	        	}
	        },
	        {field:'m6',title:'层数',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	           	 return value+'层';
	       	} 
	        }
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}

function wllist(){
	$('#bis_wlxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/wlxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'原料名称',sortable:false,width:100},    
	        {field:'m2',title:'年用量（t）',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'最大储量（t）',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'CAS号',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'储存方式',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index){
	        		if(value=='1') return value='储罐';
	        		if(value=='2') return value='桶装';
	        		if(value=='3') return value='袋装';
	        		if(value=='4') return value='其他';
	        	}
	        },
	        {field:'m8',title:'主要危险性',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function rqlist(){
	$('#bis_rq_dg').datagrid({
		method: "post",
	    url:ctx+'/bis/gas/tablist/'+qyid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'燃气类别',sortable:false,width:100},
	        {field:'m2',title:'储存类型',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'储罐数量',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'容积（m³）',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'瓶组数量',sortable:false,width:100,align:'center'},
	        {field:'m6',title:'瓶组体积(KG)',sortable:false,width:100,align:'center'},
			{field:'m7',title:'管道月用量(m³/月)',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function cglist(){
	$('#bis_cgxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cgxx/tablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'储罐名称',sortable:false,width:100},    
	        {field:'m2',title:'储罐类型',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='立式圆筒形储罐';
	            	if(value=='2') return value='卧式圆筒形储罐';
					if(value=='3') return value='球形储罐';
					if(value=='4') return value='双曲线储罐';
					if(value=='5') return value='悬链式储罐';
					if(value=='6') return value='固定顶储罐';
					if(value=='7') return value='外浮顶罐';
					if(value=='8') return value='内浮顶罐';
					if(value=='9') return value='其他储罐';
	        	}      
	        },
	        {field:'m4',title:'材质',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='滚塑';
	            	if(value=='2') return value='玻璃钢';
					if(value=='3') return value='碳钢';
					if(value=='4') return value='陶瓷';
					if(value=='5') return value='橡胶';
					if(value=='6') return value='其他';
	        	}      
	       	},
	        {field:'m5',title:'数量',sortable:false,width:100,align:'center'},
	        {field:'m6',title:'储存物料类别',sortable:false,width:100,align:'center',
	         	formatter : function(value, row, index) {
	            	if(value=='1') return value='甲类';
	            	if(value=='2') return value='乙类';
					if(value=='3') return value='丙类';
					if(value=='4') return value='丁类';
					if(value=='5') return value='戊类';
	        	}      
	        },
	        {field:'m7',title:'存储物料名称',sortable:false,width:100,align:'center'},
	        {field:'m8',title:'CAS号',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function pwlist(){
	$('#bis_pw_dg').datagrid({
		method: "post",
	    url:ctx+'/bis/ballblast/tablist/'+qyid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'设备型号',sortable:false,width:100},
	        {field:'m2',title:'台数',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'作业区域人数',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'产品材质',sortable:false,width:100,align:'center'},
			{field:'m6',title:'沙丸材质',sortable:false,width:100,align:'center'},
	        {field:'m7',title:'清理制度',sortable:false,width:100,align:'center',
				formatter : function(value, row, index){
					if(value=='') return value='';
					if(value=='1') return value='无';
					if(value=='0') return value='有';
				}
			},
			{field:'m8',title:'清理记录',sortable:false,width:100,align:'center',
				formatter : function(value, row, index){
					if(value=='') return value='';
					if(value=='1') return value='无';
					if(value=='0') return value='有';
				}
			}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function xcgqlist(){
	$('#bis_xcgq_dg').datagrid({
		method: "post",
	    url:ctx+'/bis/fieldsupply/tablist/'+qyid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'ID',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[    
	        {field:'m1',title:'介质',sortable:false,width:100},
	        {field:'M2',title:'容积（m³）',sortable:false,width:100,align:'center'},
	        {field:'M4',title:'气站性质',sortable:false,width:100,align:'center',
				formatter : function(value, row, index){
					if(value=='') return value='';
					if(value=='1') return value='租用';
					if(value=='0') return value='自建';
				}
			},
	        {field:'M5',title:'供气单位',sortable:false,width:100,align:'center'},
	        {field:'m6_1',title:'安评单位',sortable:false,width:100,align:'center'},
	        {field:'m7_1',title:'备案编号',sortable:false,width:100,align:'center'},
			{field:'m9',title:'备注',sortable:false,width:100,align:'center'}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function hxnylist(){
	$('#bis_hxny_dg').datagrid({
		method: "post",
	    url:ctx+'/bis/hxnyxx/tablist/'+qyid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[
			{field:'ID',title:'id',checkbox:true,width:50,align:'center'},
			{field:'qyname',title:'企业名称',sortable:false,width:100,align:'left'},
			{field:'m1',title:'能源名称',sortable:false,width:100,align:'center'},
			{field:'m2',title:'年用量（t/m3）',sortable:false,width:100,align:'center'},
			{field:'m3',title:'最大储存量（t/m3）',sortable:false,width:100,align:'center'},
			{field:'m4',title:'涉及工艺',sortable:false,width:100,align:'center'},
			{field:'m5',title:'是否进行安全评价',sortable:true,width:100,align:'center',
				formatter : function(value,row,index){
					if(value=='0') return value='否';
					if(value=='1') return value='是';

				}},
			{field:'m6',title:'是否设置监控设施',sortable:true,width:100,align:'center',
				formatter : function(value,row,index){
					if(value=='0') return value='否';
					if(value=='1') return value='是';

				}}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}
function qtxxlist(){
	$('#bis_qtxx_dg').datagrid({
		method: "post",
	    url:ctx+'/bis/otherinformation/tablist/'+qyid,
	    fit : true,
		fitColumns : true,
		selectOnCheck:false,
		border : false,
		idField : 'id',
		striped:true,
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize : 50,
		pageList : [ 50, 100, 150, 200, 250 ],
		singleSelect:true,
		scrollbarSize:0,
	    columns:[[
			{field:'ID',title:'id',checkbox:true,width:50,align:'center'},
			{field:'m1',title:'现场供气',sortable:false,width:100,
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m3',title:'污水处理',sortable:false,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m5',title:'涂装',sortable:true,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m7',title:'电镀',sortable:true,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m9',title:'阴极氧化',sortable:true,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m11',title:'厂房权属',sortable:true,width:100,align:'center'},
			{field:'m12',title:'有协议',sortable:false,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}},
			{field:'m13',title:'锅炉',sortable:false,width:100,align:'center',
				formatter : function (value,row,index) {
					if(value == '0') return value = '是';
					if(value == '1') return value = '否';
				}}
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
		});
}

function ygxxlist(){
	dg=$('#bis_ygxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/ygxx/tablist/'+qyid, 
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
	        {field:'m1',title:'姓名',sortable:false,width:100},    
	        {field:'m8',title:'身份证号',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'性别',sortable:false,width:100,align:'center'},
	        {field:'m4',title:'岗位',sortable:false,width:100,align:'center'},
	        {field:'m5',title:'学历',sortable:false,width:100,align:'center'},
	        {field:'m7',title:'职称',sortable:false,width:100,align:'center'},
	    ]],

		checkOnSelect:false,
		selectOnCheck:false 
		});
}

function aqsslist(){
	dg=$('#bis_aqss_dg').datagrid({    
		nowrap:false,
		method: "post",
	    url:ctx+'/bis/aqss/tablist/'+qyid, 
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
		checkOnSelect:false,
		selectOnCheck:false,
	    columns:[[    
	        {field:'m1',title:'类别',sortable:false,width:100,align:'center'},    
	        {field:'m2',title:'安全设施名称',sortable:false,width:100,align:'center'},
	        {field:'m3',title:'涉及工艺设施',sortable:false,width:100,align:'center' },
	        {field:'m4',title:'数量',sortable:false,width:100,align:'center' },
	        {field:'m5',title:'检测时间',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	           	 if(value!=null){
	           		var datetime = new Date(value);  
					 return datetime.format('yyyy-MM-dd');    
	        	 }
	       	}},
	       	{field:'m6',title:'到期时间',sortable:false,width:100,align:'center',
	        	formatter : function(value, row, index) {
	        		if(value!=null){
	               		var datetime = new Date(value);  
	    				 return datetime.format('yyyy-MM-dd');    
	            	 }
	       	}},
	       	{field:'m7',title:'状态',sortable:false,width:100,align:'center',
	       		formatter : function(value, row, index){
	       			if(value==null) return value='';
	       			else if(value=='1') return value='失效';
	        		else if(value=='2') return value='停用';
	        		else if(value=='0') return value='正常'; 
	        		else return value='';
	        	}} 
	    ]],
	    enableHeaderClickMenu: true,
	    enableRowContextMenu: false
	});
}

function fcxxlist(){
	dg=$('#bis_fcxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/fcxx/tablist/'+qyid, 
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
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	              {field:'m1',title:'粉尘种类',sortable:false,width:100,align:'center'},    
	              {field:'m2',title:'涉粉作业人数',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'涉粉单班作业人数',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'取缔',sortable:false,width:100,align:'center', 
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m5',title:'关闭',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m6',title:'有除尘器',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m7',title:'除尘器种类',sortable:false,width:100,align:'center'},    
	              {field:'m8',title:'建立粉尘清扫制度',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m9',title:'安装监控',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m11',title:'通过验收',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m12',title:'除尘器数量',sortable:false,width:100,align:'center'  },
	              {field:'m13',title:'是否涉爆',sortable:false,width:100,align:'center', 
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              },
	              {field:'m14',title:'是否职业病危害',sortable:false,width:100,align:'center',
	              	formatter : function(value, row, index){
	              		if(value=='1') return value='是';
	              		if(value=='0') return value='否';
	              	}
	              }
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function sxkjlist(){
	dg=$('#bis_sxkj_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/sxkj/tablist/'+qyid, 
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
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	              {field:'m5',title:'名称',sortable:false,width:100,align:'center'},    
	              {field:'m6',title:'主要危险有害物质',sortable:false,width:100,align:'center'},
	              {field:'m2',title:'数量',sortable:false,width:50,align:'center'},
	              {field:'m3',title:'位置',sortable:false,width:100,align:'center'},
	              {field:'m7',title:'易导致事故类型',sortable:false,width:100,align:'center'},
	              {field:'m8',title:'安全设施（预防/应急）',sortable:false,width:100,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}

function cbsxxlist(){
	dg=$('#bis_cbsxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/cbsxx/tablist/'+qyid, 
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
	              {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	              {field:'m1',title:'承包商名称',sortable:false,width:100,align:'center'},    
	              {field:'m2',title:'承包项目',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'作业内容',sortable:false,width:100,align:'center'},
	              {field:'m4',title:'作业人数',sortable:false,width:100,align:'center'},            
	              {field:'m8',title:'承包商负责人',sortable:false,width:100,align:'center'},
	              {field:'m9',title:'联系方式',sortable:false,width:100,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}
function yjxxlist(){
	dg=$('#bis_yjxx_dg').datagrid({    
		method: "post",
	    url:ctx+'/bis/yjxx/tablist/'+qyid, 
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
		          {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
		          {field:'m1',title:'类别名称',sortable:false,width:100},
	              {field:'m2',title:'主要产品',sortable:false,width:100,align:'center'},
	              {field:'m3',title:'载体是否为移动式',sortable:false,width:60,align:'center',
	            	  formatter : function(value,row,index){
	              		if(value=='0') return value='否';
	              		if(value=='1') return value='是';
	              		
	              	}},
	              {field:'m4',title:'加热方式',sortable:false,width:60,align:'center'},
	              {field:'m5',title:'年产量',sortable:false,width:60,align:'center'},
	              {field:'m6',title:'现场人数',sortable:false,width:60,align:'center'},
	              {field:'m7',title:'熔炼场所建筑物结构',sortable:false,width:60,align:'center'},
	              {field:'m8',title:'金属液体转运方式',sortable:false,width:60,align:'center'}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}


function pjbglist(){
	dg=$('#bis_pjbg_dg').datagrid({    
		method: "post",
	    url:ctx+'/aqjg/aqba/pjtablist/'+qyid, 
	    fit : true,
		fitColumns : true,
		border : false,
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
	            {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
	            {field:'m3',title:'备案类别',sortable:false,width:50,align:'center'},
		        {field:'m1',title:'备案编号',sortable:false,width:80,align:'center'},
		        {field:'m2',title:'备案日期',sortable:false,width:50,align:'center',
		        	formatter:function(value,row,index){
		     		if(value!=null){
		                var datetime = new Date(value);  
		                return datetime.format('yyyy-MM-dd');  }  
		            } },
		        {field:'m4',title:'是否审核',sortable:false,width:50,align:'center',
		         	formatter : function(value, row, index) {
		            	if(value=='0') return value='否';
				        if(value=='1') return value='是';
				        else return value='否';
		        	} 
		        },
		        {field:'m7',title:'备案经手人',sortable:false,width:50,align:'center'},
		        {field:'m6',title:'附件下载',sortable:false,width:50,align:'center',
		        	formatter : function(value) {
	        		if(value){
	        			var urls=value.split(",");
	        			var html="";
	        			for(var index in urls){
	        				html+="<a class='fa fa-download btn-danger btn-outline' target='_blank' style='margin:1px auto' href='"+urls[index].split("||")[0]+"'> 下载报告</a><br>"; 
	        			}
	        			return html;
	        		}else return ; 
	       	 }}
	    ]],
		checkOnSelect:false,
		selectOnCheck:false,
		});
}

</script>
</body>
</html>