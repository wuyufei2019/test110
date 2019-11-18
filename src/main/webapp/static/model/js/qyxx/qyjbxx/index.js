var dg;
var d;
var jglx
var windowheight= $(window).height()-20;
if(windowheight>600) windowheight=600;
var tabwidth = window.parent.$("#mainTabs").width()
if(tabwidth>400) tabwidth=tabwidth-20;

$(function(){   
	dg=$('#bis_index_dg').datagrid({    
	nowrap:false,
	method: "post",
    url:ctx+'/bis/qyjbxx/list',
    queryParams: {state: state,jglx:jglx},
    loadMsg :'正在加载',
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
	scrollbarSize:0,
	selectOnCheck:false,
	border:false,
	singleSelect:true,
	checkOnSelect:false,
    columns:[[
        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},    
        {field:'m1',title:'企业名称',sortable:false,width:200},    
        {field:'m19',title:'主要负责人',sortable:false,width:100,align:'center'},
        {field:'m21',title:'主要负责人移动电话',sortable:false,width:100,align:'center'},
        {field:'m23',title:'安全负责人',sortable:false,width:100,align:'center'},
        {field:'m25',title:'安全负责人移动电话',sortable:false,width:100,align:'center'},
        {field:'m39',title:'重大危险源',sortable:true,width:100,align:'center',
        	formatter : function(value, row, index){
        		if(value=='1') return '是';
        		if(value=='0') return '否';
        		else return '/';
        	}
        },
        {field:'m47',title:'重点监管化学品',sortable:true,width:100,align:'center',
        	formatter : function(value, row, index){
        		if(value=='1') return '是';
        		if(value=='0') return '否';
        		else return '/';
        	}
        },
        {field:'m48',title:'高危工艺',sortable:true,width:60,align:'center',  
            formatter : function(value, row, index) {
            	if(value=='1') return '是';
            	if(value=='0') return '否';
            	else return '/';
        	} 
        },
        {field:'m40',title:'重大危险源等级',sortable:true,width:60,align:'center',  
            formatter : function(value, row, index) {
      		  if(row.m39=='1'){
            	if(value=='1') return '一级';
            	if(value=='2') return '二级';
            	if(value=='3') return '三级';
            	if(value=='4') return '四级';
      		  }else{
      			return '无';
      		  }
        	} 
         },
         {field:'m44',title:'企业风险等级',sortable:true,width:60,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return "<i class='fa fa-flag' style='font-size: 18px;color:red;'></i>";
            	if(value=='2') return "<i class='fa fa-flag' style='font-size: 18px;color:orange;'></i>";
            	if(value=='3') return "<i class='fa fa-flag' style='font-size: 18px;color:yellow;'></i>";
            	if(value=='4') return "<i class='fa fa-flag' style='font-size: 18px;color:blue;'></i>";
        	}
         },
         {field:'ktdate',title:'开通日期',sortable:true,width:100,align:'center',
         	formatter : function(value, row, index){
         		if (value) {
         			return new Date(value).format("yyyy-MM-dd");
         		}
         	}
         },
         {field:'dqdate',title:'到期日期',sortable:true,width:100,align:'center',
        	 formatter : function(value, row, index){
          		if (value) {
          			return new Date(value).format("yyyy-MM-dd");
          		}
          	}
         },
         {field:'caozuo',title:'操作',sortable:false,width:250,align:'center',  
        	 formatter : function(value, row, index) {
        		 var html= "<a class='btn btn-info btn-xs' onclick='exportWordAll("+row.id+")'>导出一企一档</a>" +
        		 		"<a class='btn btn-success btn-xs "+(row.hastv==null?"hidden":"")+"' onclick='viewsp("+row.id+")'><i class=\"fa fa-video-camera\">视频</i></a>" +
        		 		"<a class='btn btn-primary btn-xs "+(row.hasfa==null?"hidden":"")+"' onclick='viewfxd("+row.id+")'><i class=\"fa fa-asterisk\"></i>风险点</a>" +
        		 		"<a class='btn btn-warning btn-xs "+(row.hasbt==null?"hidden":"")+"' onclick='viewcg("+row.id+")'><i class=\"fa fa-archive\"></i>储罐</a>" ;
        		 if(row.m33_3!=null && row.m33_3!="")
        			 html=html+"<a class='btn btn-danger btn-xs' onclick='openImgView(\""+row.m33_3.split('||')[0]+"\")'><i class=\"fa fa-picture-o\"></i>平面图</a>"; 
        		 return html;
        	 }
         },
        {field:'aqqm',title:'企业安全全貌',sortable:false,width:100,align:'center',
            formatter : function(value, row, index) {
                var html= "<a class='btn btn-info btn-xs' onclick='viewaqqm("+row.id+")'>查看</a>" ;
                return html;
            }
        },
         {field:'state',title:'状态',sortable:true,width:100,align:'center',
          	formatter : function(value, row, index){
          		if (value == '0') {
          			return "<a class='btn btn-danger btn-xs'>已停用</a> ";
          		} else {
          			return "<a class='btn btn-success btn-xs'>已启用</a> ";
          		}
          	}
          }
    ]],
    onDblClickRow:function(index,row){
    	view();
    },
    onLoadSuccess: function(){
    	if (role == '0') {// 如果不是admin或者superadmin，隐藏开通日期、到期日期和状态栏
    		$(this).datagrid("hideColumn", [ 'ktdate']);
    		$(this).datagrid("hideColumn", [ 'dqdate']);
    		$(this).datagrid("hideColumn", [ 'state']);
    	}
    },
    enableHeaderClickMenu: true,
    enableRowContextMenu: false,
    toolbar:'#bis_index_tb'
	});
});

//导出安全档案
function exportWordAll(qyid) {
	$.ajax({
		url:ctx+"/bis/qyjbxx/exportWordAll/"+qyid,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});

}

//弹窗增加
function add() {
	openDialog("添加企业基本信息",ctx+"/bis/qyjbxx/create","900px", "480px","");
}

function viewsp(id) {
	openDialogView("视频信息",ctx+"/zxjkyj/spjk/showqysp/"+id,"1100px", "480px","");
}

function viewaqqm(id) {
    top.layer.open({
        type: 2,
        shift: 1,
        area: ["100%", "100%"],
        title: '安全运行全貌',
        maxmin: true,
        content: ctx+"/bis/qyjbxx/bzhyx?qyid="+id ,
        btn: ['关闭'],
        cancel: function(index){
        }
    });
    // openDialogView("安全运行全貌",,"95%", "95%","");
}

function viewfxd(id) {
	layer.open({
		type: 1,  
		area: ['800px', '450px'],
		title: '查看企业风险信息',
		maxmin: false, 
		content: $("#select_type"),
		btn: ['关闭'],
	    success: function(layero, index){
	    	var dg =$('#areadata').datagrid({    
	    		nowrap:false,
	    		method: "post",
	    		url:ctx+'/fxgk/fxxx/tablist/'+id,
	    	    loadMsg :'正在加载',
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
	    		scrollbarSize:0,
	    		selectOnCheck:false,
	    		border:false,
	    		singleSelect:true,
	    		checkOnSelect:false,
	    	    columns:[[    
			{field:'m1',title:'较大风险点名称',sortable:false,width:100,align:'center'},    
			{field:'m2',title:'风险类别',sortable:false,width:100,align:'center'},    
			{field:'m3',title:'行业',sortable:false,width:100,align:'center'},    
			{field:'m4',title:'行业类别',sortable:false,width:100,align:'center'},    
			{field:'m8',title:'事故类型',sortable:false,width:100,align:'center'},    
			{field:'m9',title:'风险分级',sortable:false,width:100,align:'center',sortable:true, 
				formatter : function(value, row, index){
					if(value=='1') return value='红';
					else if(value=='2') return value='橙';
					else if(value=='3') return value='黄';
					else if(value=='4') return value='蓝'; 
				},
				styler : function(value, row, index){
					if(value=='1')  return 'background-color:#FF0000;color:#1E1E1E';
					else if(value=='2')  return 'background-color:#FFC125;color:#1E1E1E';
					else if(value=='3')  return 'background-color:#FFFF00;color:#1E1E1E';
					else if(value=='4')  return 'background-color:#3A5FCD;color:#1E1E1E'; 
					}
			}
	    	]],
	    	/*onLoadSuccess:function(rowdata, rowindex, rowDomElement){
	    		dg.datagrid("autoMergeCells", [ 'qyname' ]);
	    	},*/
	    		checkOnSelect:true,
	    		selectOnCheck:false,
	    		});
	    	//dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
	      },
		cancel: function(index){ 
		}
	}); 
}
function viewcg(id) {
	layer.open({
	    type: 1,  
	    title: '储罐信息',
	    area:['800px','450px'],
	    content: $("#select_type"),
	    btn: ['关闭'],
	    success: function(layero, index){
	    	var dg =$('#areadata').datagrid({    
	    		nowrap:false,
	    		method: "post",
	    		url:ctx+'/bis/cgxx/tablist/'+id,
	    	    loadMsg :'正在加载',
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
	    		scrollbarSize:0,
	    		selectOnCheck:false,
	    		border:false,
	    		singleSelect:true,
	    		checkOnSelect:false,
	    	    columns:[[    
        {field:'m1',title:'储罐名称',sortable:false,width:100},    
        {field:'M9',title:'储罐位号',sortable:false,width:100,align:'center'},   
        {field:'m2',title:'储罐类型',sortable:false,width:100,align:'center',
         	formatter : function(value, row, index) {
            	if(value=='1') return value='立式圆筒形储罐';
            	if(value=='2') return value='卧式圆筒形储罐';
				if(value=='3') return value='球形储罐';
				if(value=='4') return value='其他储罐';
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
        {field:'m6',title:'火灾危险性等级',sortable:false,width:100,align:'center',
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
	    	onLoadSuccess:function(rowdata, rowindex, rowDomElement){
	    	},
	    		checkOnSelect:true,
	    		selectOnCheck:false,
	    		});
	    	//dg.datagrid('loadData',{"rows":rydata});//datagrid加载数据
	      },
	cancel: function(index){}
	}); 
}
function viewpmt(url) {
	window.open(url.split('||')[0]);
}

//删除
function del(){
	var row = dg.datagrid('getChecked');
	if(row==null||row=='') {
		layer.msg("请至少勾选一行记录！",{time: 1000});
		return;
	}

	var ids="";
	for(var i=0;i<row.length;i++){
		if(ids==""){
			ids=row[i].ID;
		}else{
			ids=ids+","+row[i].ID;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/bis/qyjbxx/delete/"+ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改企业基本信息",ctx+"/bis/qyjbxx/update/"+row.id,"900px", "480px","");
	 
}

//查看
function view(){
	
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '查看企业档案信息',
        maxmin: true, 
	    content: ctx+"/bis/qyjbxx/view/"+row.id ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	}); 	
}
//查看
function nodatamodule(){
	openDialogView("数据完整性确认",ctx+"/bis/qyjbxx/comfirmmudule","900px", "480px",""); 	
}
//统计
function statistics(){
	layer.open({
	    type: 2,  
	    area: ['100%', '100%'],
	    title: '统计分析',
        maxmin: true, 
	    content: ctx+"/bis/qyjbxx/statistics" ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       }
	});
}

//创建查询对象并查询
function search(){
	var obj=$("#bis_index_searchFrom").serializeObject();
	obj.check_company_name=$('#check_company_name').combobox('getText');
	dg.datagrid('load',obj); 
}
//清空
function reset(){
	$("#bis_index_searchFrom").form("clear");
	search();
}
//导出
function fileexport(){
	var obj=$("#bis_index_searchFrom").serializeObject();
	obj.check_company_name=$('#check_company_name').combobox('getText');
	window.expara=obj;
	window.excolumn=[[    
				        {field:'colval',title:'colval',checkbox:true,width:50,align:'center'},    
				        {field:'coltext',title:'列名',width:100,align:'center'}
				    ]];
	window.exdata=[
			   			{colval:'M1', coltext:'企业名称'},
						{colval:'M1_1', coltext:'母公司名称'},
						{colval:'M1_2', coltext:'集团公司名称'},
						{colval:'M3_1', coltext:'统一社会信用代码'},
						{colval:'M2_1', coltext:'营业执照类别'},
						{colval:'M5', coltext:'法定代表人'},
						{colval:'M6_1', coltext:'联系人'},
						{colval:'M6_2', coltext:'联系微信号'},
						{colval:'M6_3', coltext:'联系QQ号'},
						{colval:'M6', coltext:'联系电话'},
						{colval:'M7', coltext:'电子邮件'},
						{colval:'M8', coltext:'注册地址'},
						{colval:'M8_1', coltext:'注册资金(万元)'},
						{colval:'M8_2', coltext:'年销售收入(万元)'},
						{colval:'M8_3', coltext:'年利润(万元)'},
						{colval:'M8_4', coltext:'资产总额（万元）'},
						{colval:'M8_5', coltext:'占地面积'},
						{colval:'M8_6', coltext:'属地安监机构'},
						{colval:'M9', coltext:'邮政编码'},
						{colval:'M10', coltext:'经济类型代码'},
						{colval:'M11', coltext:'行政区域(省)'},
						{colval:'M11_1', coltext:'行政区域(市)'},
						{colval:'M11_2', coltext:'行政区域(区)'},
						{colval:'M11_3', coltext:'行政区域(镇或街道)'},
						{colval:'M12', coltext:'行业类别'},
						{colval:'M13', coltext:'企业行政隶属关系'},
						{colval:'M14', coltext:'经营范围'},
						{colval:'M15', coltext:'企业状态'},
						{colval:'M16', coltext:'企业位置经度'},
						{colval:'M17', coltext:'企业位置纬度'},
						{colval:'M18', coltext:'标准化达标等级'},
						{colval:'M19', coltext:'主要负责人'},
						{colval:'M20', coltext:'主要负责人固定电话'},
						{colval:'M21', coltext:'主要负责人移动电话'},
						{colval:'M22', coltext:'主要负责人电子邮箱'},
						{colval:'M23', coltext:'安全负责人'},
						{colval:'M24', coltext:'安全负责人固定电话'},
						{colval:'M25', coltext:'安全负责人移动电话'},
						{colval:'M26', coltext:'安全负责人电子邮箱'},
						{colval:'M27', coltext:'安全机构设置情况'},
						{colval:'M46', coltext:'是否位于化工集中区'},
						{colval:'M46_1', coltext:'化工集中区名称'},
						{colval:'M47', coltext:'是否涉及重点监管危化品'},
						{colval:'M48', coltext:'是否涉及高危工艺'},
						{colval:'M49', coltext:'是否涉及剧毒品生产或使用'},
						{colval:'M28', coltext:'从业人员数量'},
						{colval:'M29', coltext:'特种作业人员数量'},
						{colval:'M30', coltext:'专职安全生产管理人员数'},
						{colval:'M30_1', coltext:'兼职安全生产管理人员数量'},
						{colval:'M31', coltext:'专职应急管理人员数'},
						{colval:'M32', coltext:'注册安全工程师人员数'},
						{colval:'M33', coltext:'生产/经营地址'},
						{colval:'M33_1', coltext:'企业官方网站地址'},
						{colval:'M33_2', coltext:'单位传真'},
						{colval:'M33_3', coltext:'企业平面图'},
						{colval:'M34', coltext:'规模情况'},
						{colval:'M35', coltext:'企业规模'},
						{colval:'M36', coltext:'监管分类'},
						{colval:'M36_1', coltext:'安全监管等级'},
						{colval:'M37', coltext:'隐患排查治理制度'},
						{colval:'M38', coltext:'隐患排查治理计划'},
						{colval:'M39', coltext:'是否构成重大危险源'},
						{colval:'M40', coltext:'重大危险源等级'},
						{colval:'M41', coltext:'物料风险等级'},
						{colval:'M42', coltext:'工艺风险等级'},
						{colval:'M43', coltext:'物料储量风险等级'},
						{colval:'M44', coltext:'企业风险等级'},
						{colval:'M45', coltext:'备注'},
						{colval:'wgname', coltext:'所属网格'}
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/qyjbxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; 
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//停用企业
function disableBis(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	if (row.state != '0') {// 如果该企业的状态不是停用状态，则可以进行停用操作
		var id = row.id;
		console.log("id:"+id);
		top.layer.confirm('您确定要停用此企业？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/bis/qyjbxx/disablebis/"+id,
				success: function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}
			});
		});
	} else {
		layer.msg('该企业已经停用', {time: 3000});
	}
}

//启用企业
function enableBis(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	if (row.state == '0') {// 如果该企业的状态是停用状态，则可以进行启用操作
		var id = row.id;
		console.log("id:"+id);
		top.layer.confirm('您确定要启用此公司？', {icon: 3, title:'提示'}, function(index){
			$.ajax({
				type:'get',
				url:ctx+"/bis/qyjbxx/enablebis/"+id,
				success: function(data){
					layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
					top.layer.close(index);
					dg.datagrid('reload');
					dg.datagrid('clearChecked');
					dg.datagrid('clearSelections');
				}
			});
		});
	} else {
		layer.msg('该企业已经启用', {time: 3000});
	}
}

