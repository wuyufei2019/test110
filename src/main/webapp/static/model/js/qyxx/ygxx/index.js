var dg;
var d;
$(function(){
	dg=$('#bis_ygxx_dg').datagrid({    
	method: "post",
    url:ctx+'/bis/ygxx/list', 
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
        {field:'m2',title:'工号',sortable:false,width:60},    
        {field:'m1',title:'姓名',sortable:false,width:40,align:'center'},    
        {field:'m3',title:'性别',sortable:false,width:40,align:'center'},
        {field:'m8',title:'身份证号',sortable:false,width:100,align:'center'},
        {field:'bm',title:'部门',sortable:false,width:60,align:'center'},
        {field:'m4',title:'职位',sortable:false,width:60,align:'center'},
        {field:'m7',title:'职务',sortable:false,width:60,align:'center'},
        {field:'m9',title:'联系方式',sortable:false,width:70,align:'center'},
        /*{field:'m14',title:'到职日期',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
              	if(value!=null&&value!='') {
              		var datetime=new Date(value);
              		 return datetime.format('yyyy-MM-dd');  
              	}
          	} 	
        },*/
        {field:'m16',title:'人员类别',sortable:false,width:70,align:'center'},
        {field:'status',title:'人员状态',sortable:false,width:60,align:'center',
        	formatter : function(value, row, index) {
              	if(value == 2) {
              		return '离职';
              	}else {
              		return '在职';
              	} 
        	}
        },
        {field:'m17',title:'二维码',sortable:false,width:70,align:'center',
        	formatter : function(value, row, index) {
        		if(value!=''&&value!=null){
        			return "<a class='btn btn-success btn-xs' onclick='openerm(\""+value+"\")'>生成二维码</a>";
        		}
        	}
        },
        {field:'uid',title:'绑定账号',sortable:false,width:110,align:'center',formatter:function (value,row) {
				if(!value){
					return '未绑定账号';
				}else{
					return row.LOGIN_NAME+"--<a class='fa btn-danger btn-outline' onclick='removeBind("+row.id+")'>解除绑定</a>";
				}
            }
        }
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
                 view();
    },
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#bis_ygxx_tb'
	});
	
});

//弹窗增加
function add(u) {
	openDialog("添加员工信息",ctx+"/bis/ygxx/create/","900px", "450px","");
}

function removeBind(id) {
    confirmx("确认解除绑定吗？", function () {
        $.post(ctx + "/bis/ygxx/removebind/"+id, function (data) {
            layer.msg(data);
            dg.datagrid("reload");
        });
	});
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
			url:ctx+"/bis/ygxx/delete/"+ids,
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
    var row=validSelectRow(dg);
    if(row){
        openDialog("修改员工信息",ctx+"/bis/ygxx/update/"+row.id,"900px", "450px","");
    }
	
}

//查看
function view(){
	   var row=validSelectRow(dg);
	   if(row){
		   openDialogView("查看员工信息",ctx+"/bis/ygxx/view/"+row.id,"800px", "400px","");
	   }
}
//layer 弹出参数
var layerparams={
    type: 2,
    shift: 1,
    area: ['80%', '80%'],
    title: '绑定用户账户',
    maxmin: false,
    content: ctx + "/system/user/?ygym=1",
    btn: ['确定', '关闭'],
    cancel: function (index) {
    },
    success: function (layero, index) {
        var body = layer.getChildFrame('body', index);
        var operation = body.find("#operation");
        operation.remove();
    }
}

//验证选中行
function validSelectRow(dg) {
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return false;
    }else{
    	return row;
	}
}
//绑定用户
function connectUserAccount() {
    var row = validSelectRow(dg);
    if (row) {
        layer.open($.extend(layerparams,  {yes: function (index, layero) {
        	var childrow =validChildRow(index,layero);
        	console.log(childrow);
                if(childrow){
                    confirmx("确认关联吗？", function () {
                        $.post(ctx + "/bis/ygxx/connect", {id: row.id, userid: childrow.id}, function (data) {
                            layer.msg(data);
                            layer.close(index);
                            dg.datagrid("reload");
                        });
                    });
                }
            }}));
    }
}

function validChildRow(index,layero){
    var iframeWin = layero.find('iframe')[0];
    var childdg = iframeWin.contentWindow.dg;
    return validSelectRow(childdg);
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

//导出excel
function fileexport(){
	window.expara=$("#searchFrom").serializeObject();
	window.exdata=[
	               	{colval:'m2', coltext:'工号'},
			   		{colval:'m1', coltext:'姓名'},
			   		{colval:'m3', coltext:'性别'},
			   		{colval:'m8', coltext:'身份证号'},
			   		{colval:'m16', coltext:'人员类别'},
			   		{colval:'m10', coltext:'出生日期'},
			   		{colval:'m14', coltext:'到职日期'},
			   		{colval:'m11', coltext:'婚姻状况'},
			   		{colval:'bm', coltext:'部门'},
			   		{colval:'m4', coltext:'职位'},
			   		{colval:'m7', coltext:'职务'},
			   		{colval:'m5', coltext:'学历'},
			   		{colval:'m12', coltext:'贯籍'},
			   		{colval:'m13', coltext:'民族'},
			   		{colval:'m6', coltext:'专业'},
			   		{colval:'m9', coltext:'联系方式'},
		   	];

	layer.open({
	    type: 2,  
	    area: ['350px', '350px'],
	    title: '导出列选择',
        maxmin: false, 
        shift: 1,
	    content: ctx+'/bis/ygxx/colindex',
		btn: ['导出'],
	    yes: function(index, layero){
	    	 var body = layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0];  
	         var inputForm = body.find('#excel_mainform');
	         iframeWin.contentWindow.doExport();
		  },
	});
}

//生成二维码图片
function openerm(ewm){
	$.ajax({
		type : 'get',
		url : ctx + "/bis/ygxx/erm?ewm=" + ewm,
		success : function(data) {
			window.open(ctx+data);
		}
	});
}

//导出word
function fileexportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/bis/ygxx/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//导出职业病健康word
function fileexportword2(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/bis/ygxx/exportword2/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//离职
function disableYg(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.status==2){
        layer.msg("该员工已离职！");
        return;
    }
    openDialog("添加离职信息",ctx+"/bis/ygxx/addlzxx/"+row.id,"450px", "400px","");
}

//恢复
function enableYg(){
    var row = dg.datagrid('getSelected');
    if(row==null) {
        layer.msg("请选择一行记录！",{time: 1000});
        return;
    }
    if(row.status==1){
        layer.msg("该员工状态正常！");
        return;
    }
    top.layer.confirm('确定要恢复该员工吗？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            type:'get',
            url:ctx+"/bis/ygxx/enable/"+row.id,
            success: function(data){
                layer.alert(data, {offset: 'rb',shade: 0,time: 2000});
                top.layer.close(index);
                dg.datagrid('reload');
                dg.datagrid('clearChecked');
            }
        });
    });
}