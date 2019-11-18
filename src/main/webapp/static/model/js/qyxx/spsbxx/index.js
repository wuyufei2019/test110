var dg;
var d;
$(function(){
	dg=$('#bis_spsbxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/spsbxx/list?zdwxy='+zdwxy,
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
			  {field:'m1',title:'摄像头编码',sortable:false,width:100,align:'center'},
			  {field:'m3',title:'设备名称',sortable:false,width:80,align:'center'},
			  {field:'m21',title:'是否为重大危险源区域',sortable:false,width:80,align:'center',
			  	  formatter: function (value) {
					  if (value == '0') {
						return '否';
					  } else if (value == '1') {
					  	return '是';
					  }
			  	  }
			  },
			  {field:'rtsp',title:'RTSP视频流',sortable:false,width:150,align:'center'},
			  {field:'apiaddress',title:'接口请求地址',sortable:true,width:80,align:'center'},
			  {field:'playaddress',title:'播放地址',sortable:false,width:80,align:'center'},
		      {field:'url',title:'视频流url',sortable:false,width:120,align:'center'},
			  {field:'caozuo',title:'操作',sortable:false,width:60,align:'center',
				  formatter : function(value, row, index){
					  return "<a  class='btn btn-info btn-xs' onclick='showlive("+ row.id + ")'>播放</a>";
			  	  }
			  }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_spsbxx_tb'
	});
	
});

//播放视频
function showlive(id){
	openDialogView("查看直播",ctx+"/bis/spsbxx/showsp/"+id,"600px", "400px","");
}

//弹窗增加
function add(u) {
	openDialog("添加视频设备信息",ctx+"/bis/spsbxx/create/","1000px", "800px","");
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
			url:ctx+"/bis/spsbxx/delete/"+ids,
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
	
	openDialog("修改视频设备信息",ctx+"/bis/spsbxx/update/"+row.id,"1000px", "800px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看视频设备信息",ctx+"/bis/spsbxx/view/"+row.id,"800px", "400px","");
	
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
			   		{colval:'m1', coltext:'车牌号码'},
			   		{colval:'m2', coltext:'车型'},
			   		{colval:'m3', coltext:'驾驶员姓名'},
					{colval:'m4', coltext:'驾驶员手机号码'},
					{colval:'m5', coltext:'押送员姓名'},
					{colval:'m6', coltext:'押送员手机号码'},
					{colval:'m7', coltext:'允许行驶的线路'},
					{colval:'m8', coltext:'允许停泊的位置'},
					{colval:'m9', coltext:'允许停泊的时长'},
					{colval:'m10', coltext:'备注'},
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/spsbxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//重新生成视频流
function resetVideo(){
	top.layer.confirm('确定要执行吗？', {icon: 3, title:'提示'}, function(index){
		top.layer.close(index);
		var loding = layer.load(1, { shade: [0.3,'#fff']  });//弹出加载层
		$.ajax({
			type:'get',
			url:ctx+'/bis/spsbxx/reset',
			success:function(data){
				layer.close(loding);//关闭加载层
				layer.msg(data,{time: 1000});
			},
			error:function(){
				layer.close(loding);//关闭加载层
				layer.msg("操作失败！",{time: 1000});
			}
		});
	});
}