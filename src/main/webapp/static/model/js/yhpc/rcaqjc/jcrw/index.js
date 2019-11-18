var dg;
var d;
var content;//存在问题
var pic;//照片地址
var finishtime;//结束时间
var yhlb;//隐患类别
var zgr;//整改人
var yhdj;//隐患等级
var yhid="";//隐患的内容id
$(function() {	
	dg = $('#yhpc_jcrw_dg').datagrid(
			{
				method : "get",
				url : ctx + '/yhpc/jcrw/list',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 5,
				singleSelect : true,
				striped : true,
				columns : [ [
				             {field:'id',title:'id',checkbox:true,width:50,align:'center'},
				             {field:'m7',title:'任务名称',width:60,align:'center'},
				             {field:'depname',title:'部门',width:60,align:'center',
					             	formatter : function(value, row, index) {
					                  	if(value!=null&&value!='') {
					                  		 return value;  
					                  	}else{
					                  		return '全公司';
					                  	}
					              	}	
				             },
				             {field:'jcr',title:'检查人',width:60,align:'center'},
				             {field:'m5',title:'检查类别',width:60,align:'center'},
				             {field:'m3',title:'计划检查时间',width:60,align:'center',
				             	formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		 return datetime.format('yyyy-MM-dd');  
				                  	}
				              	}	 
				             },
				             {field:'m6',title:'状态',width:60,align:'center',
				             	formatter : function(value, row, index) {
				                  	if(value==2) {
				                  		return "检查未完成";
				                  	}
				                  	if(value==1) {
				                  		return "已检查";
				                  	}
				                  	if(value==0) {
				                  		return  "未检查";
				                  	}
				              	},styler: function(value,row,index){
									if (value == '0'){
										return 'color:red;';
									}
                                     if (value == '2'){
                                         return 'color:red;';
                                     }
								}					            	 
				             },
							 {field : 'jd',title : '进度',sortable : false,width : 50,align : 'center',
									formatter : function(value, row, index) {
										return " <a class='btn btn-info btn-xs' onclick='viewzt("+row.id+")'>查看进度</a> ";
									}
							 }
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				rowStyler:function(index,row){
			    	if (row.m6 == '0'){
						return 'background-color:#f9ebed;';
					}
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
					$(this).datagrid("autoMergeCells", [ 'plantime' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#yhpc_jcrw_tb'
			});
});

// 弹窗增加
function add(u) {
	openDialog("添加检查任务", ctx + "/yhpc/jcrw/create/", "90%", "90%", "");
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
			ids=row[i].id;
		}else{
			ids=ids+","+row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url : ctx + "/yhpc/jcrw/delete/" + ids,
			success: function(data){
				layer.alert(data, {offset: 'rb',shade: 0,time: 2000}); 
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
				dg2.datagrid('reload');
				dg2.datagrid('clearChecked');
				dg2.datagrid('clearSelections');
			}
		});
	});
 
}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	if(row.m6==1) {
		layer.msg("该任务已检查，无法修改！",{time: 2000});
		return;
	}
	openDialog("修改检查任务信息",ctx+"/yhpc/jcrw/update/"+row.id,"90%", "90%","");
}

// 查看
function view() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看检查任务",ctx + "/yhpc/jcrw/view/" + row.id,"800px", "400px","");

}

// 创建查询对象并查询
function search() {
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}
 

function reset() {
	$("#searchFrom").form("clear");
	var obj = $("#searchFrom").serializeObject();
	dg.datagrid('load', obj);
}

//我的任务list
$(function() {	
	dg2 = $('#yhpc_wdrw_dg').datagrid(
			{
				method : "get",
				url : ctx + '/yhpc/jcrw/mylist',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				nowrap : false,
				pageNumber : 1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				scrollbarSize : 5,
				singleSelect : true,
				striped : true,
				columns : [ [
				             {field:'id',title:'id',checkbox:true,width:50,align:'center'},
				             {field:'m7',title:'任务名称',width:60,align:'center'},
				             {field:'depname',title:'部门',width:60,align:'center',
				            	 formatter: function(value){
				            		 if (value == null) {
				            			 return "全部";
				            		 } else {
				            			 return value;
				            		 }
				            	 }
				             },
				             {field:'jcr',title:'检查人',width:60,align:'center'},
				             {field:'m5',title:'检查类别',width:60,align:'center'},
				             {field:'m3',title:'计划检查时间',width:60,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		 return datetime.format('yyyy-MM-dd');  
				                  	}
				              	}	 
				             },
				             {field : 'm6',title : '状态',sortable : false,width : 70,align : 'center',
									formatter : function(value, row, index) {
										if(value == '0'){
											return " <a class='btn btn-info btn-xs' onclick='addJl("+row.id+")'>开始检查</a> ";
										}else if(value == '1'){
											return "已检查";
										}else if(value == '2') {
										    return " <a class='btn btn-info btn-xs' onclick='bcJl("+row.id+")'>补充检查</a> ";
                                        }
									}
							 },
							 {field : 'jd',title : '进度',sortable : false,width : 50,align : 'center',
									formatter : function(value, row, index) {
										return " <a class='btn btn-info btn-xs' onclick='viewzt("+row.id+")'>查看进度</a> ";
									}
							 }
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view2();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
					$(this).datagrid("autoMergeCells", [ 'plantime' ]);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#yhpc_wdrw_tb'
			});
});

//查看状态
function viewzt(rwid){
	openDialogView("任务执行状态",ctx+"/yhpc/jcrw/viewzt/"+rwid,"800px", "400px","");
}

//创建查询对象并查询
function search2() {
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}
 
//重置
function reset2() {
	$("#searchFrom2").form("clear");
	var obj = $("#searchFrom2").serializeObject();
	dg2.datagrid('load', obj);
}

//查看
function view2() {
	var row = dg2.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	openDialogView("查看检查任务",ctx + "/yhpc/jcrw/view/" + row.id,"800px", "400px","");
}

//添加检查记录
function addJl(id){
    openDialog("添加检查记录", ctx + "/yhpc/jcrw/addJl/"+id, "95%","95%", "");
}

//补充检查记录
function bcJl(id){
    openDialog("补充检查记录", ctx + "/yhpc/rcjl/bcJl/"+id, "95%","95%", "");
}

//添加隐患
function addNr(nrid) {
	content=$("#czwt_"+nrid).val();
	pic=$("#imgurl_"+nrid).val();
	finishtime=$("#finishtime_"+nrid).val();
	yhlb=$("#yhlb_"+nrid).val();
	zgr=$("#zgr_"+nrid).val();
	yhdj=$("#yhdj_"+nrid).val();
	zrbm=$("#zrbm_"+nrid).val();
	fsqy=$("#fsqy_"+nrid).val();
	jjcs=$("#jjcs_"+nrid).val();
	layer.open({
	    type: 2,  
	    area: ['800px', '400px'],
	    title: '添加问题',
        maxmin: false, 
	    content: ctx + "/yhpc/jcrw/choosejcnr" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();//获取图片地址
	    	var czwt=iframeWin.contentWindow.getczwt();//获取问题内容
	    	var jssj=iframeWin.contentWindow.gettime();//获取结束时间
	    	var yhzgr=iframeWin.contentWindow.getzgr();//获取结束时间
	    	var lb=iframeWin.contentWindow.getlx();//获取隐患类别
	    	var yhdj=iframeWin.contentWindow.getyhdj();//获取隐患等级
	    	var zrbm=iframeWin.contentWindow.getzrbm();//获取责任部门
	    	var fsqy=iframeWin.contentWindow.getfsqy();//获取发生区域
	    	var jjcs=iframeWin.contentWindow.getjjcs();//获取解决措施
	    	if(czwt==""||czwt==null||czwt==undefined){
	    		layer.msg("请输入存在问题", {
	    			time : 2000
	    		});
	    		return;
	    	}
	    	if(url==""||url==null||url==undefined){
	    		layer.msg("请上传图片", {
	    			time : 2000
	    		});
	    		return;
	    	}
	    	//先删除div再添加
	    	$("#divwtfj_"+nrid).remove();
			var $li = $(
					'<div style="text-align:center;display: none;" id="divwtfj_'+ nrid + '" >' +
					'<input type="hidden" id="czwt_'+ nrid +'" name="czwt_'+ nrid +'" value="'+czwt+'" />'+
					'<input type="hidden" id="imgurl_'+ nrid +'" name="imgurl_'+ nrid +'" value="'+url+'" />'+
					'<input type="hidden" id="finishtime_'+ nrid +'" name="finishtime_'+ nrid +'" value="'+jssj+'" />'+
					'<input type="hidden" id="yhlb_'+ nrid +'" name="yhlb_'+ nrid +'" value="'+lb+'" />'+
					'<input type="hidden" id="zgr_'+ nrid +'" name="zgr_'+ nrid +'" value="'+yhzgr+'" />'+
					'<input type="hidden" id="yhdj_'+ nrid +'" name="yhdj_'+ nrid +'" value="'+yhdj+'" />'+
					'<input type="hidden" id="zrbm_'+ nrid +'" name="zrbm_'+ nrid +'" value="'+zrbm+'" />'+
					'<input type="hidden" id="fsqy_'+ nrid +'" name="fsqy_'+ nrid +'" value="'+fsqy+'" />'+
					'<input type="hidden" id="jjcs_'+ nrid +'" name="jjcs_'+ nrid +'" value="'+jjcs+'" />'+
					'<input type="hidden" id="yhid_'+ nrid +'" name="M4" value="'+nrid+'" />'+
					'</div>'
		            );
			$('#content').after( $li );
			layer.close(index);//关闭对话框。
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}
	
//删除隐患
function removeInfo(nrid){
	$("#divwtfj_"+nrid).remove();
}