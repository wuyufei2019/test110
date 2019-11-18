var dg;
var d;
var num=1;
$(function(){
	dg=$('#aqpx_kcxx_dg').datagrid({    
	method: "post",
    url:ctx+'/aqpx/kcxx/list', 
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
              {field:'m5',title:'培训类别',sortable:true,width:100,
            	  formatter : function(value, view ,index){
                    	if(value=='1') 
                    		return '日常计划培训';  
                    	else if (value=='2')
                    		return '入职三级教育培训';
                    	else if (value=='3')
                    		return '外来方培训';
                    	else return value;
              	  }},    
              {field:'m1',title:'课程名称',sortable:true,width:100},    
              {field:'m2',title:'课程学时',sortable:true,width:50,align:'center'},
              {field:'m3',title:'课程学分',sortable:true,width:50,align:'center'}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#aqpx_kcxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加课程信息",ctx+"/aqpx/kcxx/create/","1000px", "600px","");
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
			url:ctx+"/aqpx/kcxx/delete/"+ids,
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
	
	openDialog("修改课程信息",ctx+"/aqpx/kcxx/update/"+row.id,"1000px", "600px","");
	
}

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看课程信息",ctx+"/aqpx/kcxx/view/"+row.ID,"800px", "400px","");
	
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
	url=ctx+"/aqpx/kcxx/export";
	window.location.href=url;
}

//弹出检查点选择框
function openkjlist() {
	layer.open({
	    type: 2,  
	    area: ['700px', '95%'],
	    title: '选择课件',
        maxmin: false, 
	    content: ctx + "/aqpx/kjkgl/kjchoose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	        debugger;
	    	var  $list= $("#jcdList");
	    	$list.html("");
	    	var iframeWin = layero.find('iframe')[0];
	    	var idname=iframeWin.contentWindow.getdata();
	        var arr1=idname.split(",");
	        var ids="";
			for (var i = 0; i < arr1.length-1; i++) {
				var arr2=arr1[i].split(";");
				var $li = $(
			            "<div id=\"kj_" + num + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arr2[2]+"')\">"+arr2[0]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('kj_"+num+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    
			    $list2.append( $li );
		 
				var $input = $('<input id="input_'+num+'" type="hidden" name="M4" value="'+arr2[1]+'"/>');
				var $input2 = $('<input id="lx_'+num+'" type="hidden" name="lx" value="'+arr2[3]+'"/>');
				$('#uploader_ws_m4').append( $input );
				$('#uploader_ws_m4').append( $input2 );			
				num++;
			}
			$("#jcdids").val(ids);
			layer.close(index);
			},
	cancel: function(index){}
	}); 	
}