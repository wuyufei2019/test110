var dg;
var d;
var url;
var jjdqCnt = 0;//即将到期个数
var gqCnt = 0;//过期个数
$(function(){
	url = ctx+'/sbssgl/qzjy/list';
	dg=$('#sbssgl_qzjy_dg').datagrid({    
	method: "post",
    url: url, 
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
        {field:'qyname',title:'所属企业',sortable:false,width:70,align : 'center'},
        {field:'deptname',title:'部门名称',sortable:false,width:50,align : 'center'},
        {field:'m2',title:'设备名称',sortable:false,width:50,align:'center'},
        {field:'m27',title:'型号',sortable:false,width:40,align:'center'},
        {field:'m8',title:'放置地点',sortable:false,width:60,align : 'center'},
        {field:'m28',title:'注册登记日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM');  
            	}
        	} 
        },
        {field:'m31',title:'本次检验日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	} 
        },
        {field:'m32',title:'下次检验日期',sortable:false,width:40,align:'center',
      	    formatter : function(value, row, index) {
            	if(value!==null&&value!='') {
            		var datetime=new Date(value);
            		 return datetime.format('yyyy-MM-dd');  
            	}
        	},
            styler: function(value, row, index){
        		var nowhm=(new Date()).getTime();
        		var time=value;
        		if(time){//在时间存在的情况下进行
        			if(nowhm<=time){//未过期（2个月以内）
                		var cha=(time-nowhm)/1000/60/60/24;
                		if(cha<=60) {
                			return 'background-color:rgb(247, 185, 175);color: white;';
                		} 
                		jjdqCnt = jjdqCnt + 1;
            		}else{//过期
            			gqCnt = gqCnt + 1;
            			return 'background-color:rgb(251, 45, 9);color: white;';
            		}
        		}
        	} 
        	
        },
        {field:'cz',title:'操作',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
        		var cz="&nbsp<a class='btn btn-success btn-xs' onclick='showhistory("+row.id+")'>查看历史</a>";
        		var nowhm=(new Date()).getTime();
        		var time=row.m32;
        		if(time){//在时间存在的情况下进行
            		var cha=(time-nowhm)/(1000 * 60 * 60 * 24);
            		if (cha<=60) {//未过期（2个月以内）
            			cz="<a class='btn btn-info btn-xs' onclick='upd2("+row.id+")'>更新日期</a>"+cz;
            		}
        		}
        		return cz;
        	} 
        }
    ]],
    onLoadSuccess: function(){
    	if(type == '1'){
			$(this).datagrid("hideColumn", [ 'qyname' ]);
		}else{
			$(this).datagrid("showColumn", [ 'qyname' ]);
			$(this).datagrid("autoMergeCells", [ 'qyname' ]);
		}
    	//即将到期和过期提醒
    	if((jjdqCnt > 0) || (gqCnt > 0)){
    		layer.open({icon:1,title: '提示',offset: 'rb',content:"有" + jjdqCnt + "个特种设备检验即将到期"+",有" + gqCnt + "个特种设备检验已过期" ,shade: 0 ,time: 5000 });
    	}
    	jjdqCnt = 0;
    	gqCnt = 0;
    },
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#sbssgl_qzjy_tb'
	});
});

//创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	search();
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	openDialogView("查看设备管理信息",ctx+"/sbssgl/sbgl/view/"+row.id,"800px", "400px","");
}	

//更新特种设备日期
function upd2(sbid){
	openDialog("更新检验日期",ctx+'/sbssgl/qzjy/update2/'+sbid,"800px", "400px","");
}

//查看更新历史
function showhistory(sbid) {
	openDialog("查看更新历史",ctx+'/sbssgl/qzjy/hisindex/'+sbid,"800px","400px","");
}

//导出
function fileexport(sbtype){
	var exportUrl;
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'RowNumber', coltext:'序号/No.'},
			   		{colval:'m1', coltext:'设备编号/Code'},
			   		{colval:'m2', coltext:'设备名称/Description'},
			   		{colval:'m5', coltext:'制造单位/Manufacturing company'},
			   		{colval:'m3', coltext:'设备型号 /Code'},
			   		{colval:'m9', coltext:'系列号/SN'},
			   		{colval:'m10', coltext:'电气功率/Power (30KVA)'},
			   		/*{colval:'m11', coltext:'用气量/Compressed Air(m3/min)'},
			   		{colval:'m12', coltext:'用水量/Water'},*/
			   		{colval:'m13', coltext:'外形尺寸/overall size'},
			   		{colval:'m14', coltext:'加工范围/Process field'},
			   		{colval:'m15', coltext:'重量/Weight'},
			   		{colval:'qysj', coltext:'启用时间/Opening time'},
			   		{colval:'m8', coltext:'安装地点/Installaton site'},
			   		{colval:'m17', coltext:'固定资产编号/CAPEX'},
			   		{colval:'m18', coltext:'备注/Remarks'}
		   	];
	
	if (sbtype == '1') {//特种设备
		exportUrl = ctx+'/sbssgl/sbgl/colindex2';
	} else if (sbtype == '0') {//普通设备
		exportUrl = ctx+'/sbssgl/sbgl/colindex';
	}
	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: exportUrl,
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}