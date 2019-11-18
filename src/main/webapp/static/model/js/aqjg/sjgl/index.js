var dg;
var d;
$(function(){
	dg=$('#aqjg_sjgl_dg').datagrid({    
	method: "post",
    url:ctx+'/aqjg/sjgl/list', 
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
        {field:'m2',title:'发生单位',sortable:false,width:100,align:'center'},
        {field:'m1',title:'发生时间',sortable:false,width : 50,
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
							+ '/' + (d < 10 ? ('0' + d) : d)
				} else {
					return '';
				}
			}},
       
       /* {field:'m3',title:'发生地点',sortable:false,width:50,align:'center'},*/
        {field:'m4',title:'事故类型',sortable:false,width:50,align:'center',
         	formatter : function(value) {
         		if(value!=null&&value!=""){
         		if(value=='wtdj')return '物体打击';
		        if(value=='zt')return '灼烫';
		        if(value=='wsbz')return '瓦斯爆炸';
		        if(value=='clsh')return '车辆伤害';
		        if(value=='hz')return '火灾';
		        if(value=='hybz')return '火药爆炸';
		        if(value=='jxsh')return '机械伤害';
		        if(value=='gczl')return '高处坠落';
		        if(value=='glbz')return '锅炉爆炸';
		        if(value=='qzsh')return '起重伤害';
		        if(value=='tt')return '坍塌';
		        if(value=='rqbz')return '容器爆炸';
		        if(value=='cd')return '触电';
		        if(value=='mdpb')return '冒顶片帮';
		        if(value=='qtbz')return '其他爆炸';
		        if(value=='yn')return '淹溺';
		        if(value=='ts')return '透水';
		        if(value=='zdhzx')return '中毒和窒息';
		        if(value=='fp')return '放炮';
		        if(value=='qtsh')return '其他伤害';
		        }else{
		        	return "";
		        }
        	} 
        },
        {field:'m5',title:'事故等级',sortable:false,width:50,align:'center',formatter:function(value){
        	if(value=='0')return '特别重大事故';
	        if(value=='1')return '重大事故';
	        if(value=='2')return '较大事故';
	        if(value=='3')return '一般事故';
        }},
        {field:'m6',title:'死亡人数',sortable:false,width:30,align:'center'},
        {field:'m7',title:'重伤人数',sortable:false,width:30,align:'center'},
        /*{field:'m8',title:'轻伤人数',sortable:false,width:30,align:'center'},*/
        {field:'m9',title:'直接经济损失(万元)',sortable:false,width:60,align:'center'},
        {field:'m10',title:'间接经济损失(万元)',sortable:false,width:60,align:'center'},
       /* {field:'m11',title:'事故描述',sortable:false,width:50,align:'center'},
        {field:'m12',title:'事故处理',sortable:false,width:50,align:'center'},
        {field:'m13',title:'事故预防对策',sortable:false,width:50,align:'center'},*/
        {field:'m14',title:'备注',sortable:false,width:100,align:'center'}
       
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
    	upd();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    onLoadSuccess:function(){
//    	  if(usertype=="1"){
//    		  $(this).datagrid("hideColumn",['qyname']);
//    	  }else{
//    		  $(this).datagrid("showColumn",['qyname']);
//    	  }
//    	  $(this).datagrid("autoMergeCells",['qyname']);
      },
    toolbar:'#aqjg_sjgl_tb'
	});
});

// 弹窗增加
function add(u) {
	openDialog("添加事件管理信息",ctx+"/aqjg/sjgl/create/","800px", "400px","");
}

// 删除
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
			url:ctx+"/aqjg/sjgl/delete/"+ids,
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


// 弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialog("修改事件管理信息",ctx+"/aqjg/sjgl/update/"+row.id,"800px", "400px","");
	
}

// 查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看事件管理信息",ctx+"/aqjg/sjgl/view/"+row.id,"800px", "400px","");
	
}

// 创建查询对象并查询
function search(){
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
}

function reset(){
	$("#searchFrom").form("clear");
	var obj=$("#searchFrom").serializeObject();
	dg.datagrid('load',obj); 
	// if(btflg=='2') $("#filter_EQS_departmen").hide();
}

