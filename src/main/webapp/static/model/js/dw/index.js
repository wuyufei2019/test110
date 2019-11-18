var dg;
var d;
$(function(){
	dg=$('#dw_zysp_dg').datagrid({    
	method: "post",
    url:ctx+'/dw/zysp/list', 
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
		        {field:'id',title:'id',checkbox:true,width:50,align:'center'},
		        {field:'qyname',title:'企业名称',width:100},  
		        {field:'m20',title:'作业类型',width:60,align:'center',
					formatter: function(value,row,index){
						if (value == "1"){
							return "特种作业";
						} else if (value == "2"){
							return "一般作业";
						} 
					}},		        
		        {field:'m1',title:'作业方式',width:60,align:'center',
					formatter: function(value,row,index){
						if(value!=null&&value!=""){
							var zyfs = "";
							var arr = value.split(",");
							arr.forEach(function(e) {
								var fs;
								if (e == "1"){
									fs = "动火作业";
								} else if (e == "2"){
									fs = "受限空间作业";
								} else if (e == "3"){
									fs = "管道拆卸作业";
								} else if (e == "4"){
									fs = "盲板抽堵作业";
								} else if (e == "5"){
									fs = "高处安全作业";
								} else if (e == "6"){
									fs = "吊装安全作业";
								} else if (e == "7"){
									fs = "临时用电安全作业";
								} else if (e == "8"){
									fs = "动土安全作业";
								} else if (e == "9"){
									fs = "断路安全作业";
								} else if (e == "10"){
									fs = "其他安全作业";
								}
								zyfs = "," + fs + zyfs;
						    })
						    return zyfs.replace(",","");
						}else{
							return "";
						}
					}},		        
		        {field:'m2',title:'作业级别',width:50,align:'center',
						formatter: function(value,row,index){
							if (value == "1"){
								return "特级";
							} else if (value == "2"){
								return "一级";
							} else if (value == "3"){
								return "二级";
							} else if (value == "4"){
								return "其他";
							}
						}},
		        {field:'m3',title:'作业地点',width:100,align:'center'},
		        {field:'m17',title:'作业队伍',width:50,align:'center',
		          	styler: function(value, row, index){
		          		if (value == "1"){
							return 'background-color:rgb(255, 228, 141);';
						} else if (value == "2"){
							return 'background-color:rgb(249, 156, 140);';
						} 
		          	},
					formatter: function(value,row,index){
						if (value == "1"){
							return "外包施工队";
						} else if (value == "2"){
							return "本厂人员";
						} 
					}
		        },
		        {field:'m4',title:'作业负责人',width:50,align:'center'},
				{field:'m5',title:'作业人员',width:50,align:'center'},
				{field:'m7',title:'时间起',width:80,align:'center',
			      	  formatter : function(value, row, index) {
			            	if(value!='') {
			            		var datetime=new Date(value);
			            		return datetime.format('yyyy-MM-dd hh:mm:ss');
			            	}
			        	} 	
				},
				{field:'m8',title:'时间止',width:80,align:'center',
			      	  formatter : function(value, row, index) {
			            	if(value!='') {
			            		var datetime=new Date(value);
			            		return datetime.format('yyyy-MM-dd hh:mm:ss');
			            	}
			        	} 	
				},	
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
    onBeforeLoad:function(param){
    	if(f!=''&&f=='sys'){
    		$("#dw_zysp_cx_m17").combobox('setValue',zydw);
    		param.dw_zysp_cx_m17=zydw;
    	 }
	},
    onLoadSuccess:function(){
  	  if(usertype=="1"){
  		  $(this).datagrid("hideColumn",['qyname']);
  	  }else{
  		  $(this).datagrid("showColumn",['qyname']);
  	  }
  	  $(this).datagrid("autoMergeCells",['qyname']);
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#dw_zysp_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("危险作业报备申请",ctx+"/dw/zysp/create/","90%", "85%","");
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
		if(row[i].id1!=qyid) {
			layer.msg("无法对下属企业数据进行操作！",{time: 3000});
			return;
		}		
		if(ids==""){
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/dw/zysp/delete/"+ids,
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
	if(row.id1!=qyid) {
		layer.msg("无法对下属企业数据进行操作！",{time: 3000});
		return;
	}
	openDialog("作业报备详细页面",ctx+"/dw/zysp/update/"+row.id,"90%", "85%","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("作业报备详细页面",ctx+"/dw/zysp/view/"+row.id,"800px", "400px","");
	
}

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	//if(btflg=='2') $("#filter_EQS_departmen").hide();
}

//导出
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'m20', coltext:'作业类型'},
			   		{colval:'m1', coltext:'作业方式'},
			   		{colval:'m2', coltext:'作业级别'},
			   		{colval:'m17', coltext:'作业队伍'},
			   		{colval:'m3', coltext:'作业地点'},
			   		{colval:'m4', coltext:'作业负责人'},
			   		{colval:'m5', coltext:'作业人员'},
			   		{colval:'m6', coltext:'作业内容'},
			   		{colval:'m7', coltext:'动火时间起'},
			   		{colval:'m8', coltext:'动火时间止'},
			   		{colval:'m9', coltext:'危险因素'},
			   		{colval:'m10', coltext:'其他危险因素'},
			   		{colval:'m11', coltext:'可能导致的事故类型'},
			   		{colval:'m18', coltext:'第三方服务公司'},
			   		{colval:'m19', coltext:'第三方公司作业负责人'},
		   	];
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/dw/zysp/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
		
}