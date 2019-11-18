var dg;
var d;
var wtid=1;
$(function(){
	dg=$('#yhpc_lsjcjl_dg').datagrid({    
	method: "post",
    url:ctx+'/yhpc/lsjcjl/list', 
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
        {field:'qyname',title:'企业名称',sortable:false,width:80,align:'center'},
        {field:'m16',title:'检查名称',sortable:false,width:80,align:'center'},
        {field:'m2',title:'检查时间',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		return datetime.format("yyyy-MM-dd");
              	}
          	} 
        },
        {field:'m4',title:'检查人员',sortable:false,width:80,align:'center'},
        {field:'m3',title:'整改期限',sortable:false,width:80,align:'center',
        	  formatter : function(value, row, index) {
              	if(value!='') {
              		var datetime=new Date(value);
              		return datetime.format("yyyy-MM-dd");
              	}
          	} 
        },
        {field:'m5',title:'整改负责人',sortable:false,width:80,align:'center'},
		{field :'m15',title :'操作',sortable : false,width : 50,align : 'center',
			formatter : function(value, row, index) {
				if(value=='1') 
					return "<a class='btn btn-warning btn-xs' onclick='upd("+row.id+")'>修改初查</a>" +
					"<a class='btn btn-info btn-xs' onclick='addReCheck("+row.id+")'>添加复查</a>";
				if(value=='2') 
					return "<a class='btn btn-danger btn-xs' onclick='updateReCheck("+row.id+")'>再次复查</a>";
				if(value=='3') 
					return "已完成整改";

			}
		}
    ]],
    onDblClickRow: function (rowdata, rowindex, rowDomElement){
        view();
    },
    onLoadSuccess:function(data){
		if(usertype=="1"){
			$(this).datagrid("hideColumn", "qyname");
		}
		$(this).datagrid("autoMergeCells", [ 'dwname' ]);
	},
	checkOnSelect:false,
	selectOnCheck:false,
    toolbar:'#yhpc_lsjcjl_tb'
	});
	
});

//添加初查信息
function add(u) {
	openDialog("添加临时检查信息",ctx+"/yhpc/lsjcjl/create/","800px", "465px","");
}

//修改初查信息
function upd(id){
	openDialog("修改临时检查信息",ctx+"/yhpc/lsjcjl/updatecc/"+id,"800px", "400px","");
	
}

//添加复查信息
function addReCheck(id){
	top.layer.confirm('添加复查记录后初查记录将不能修改！', {
		icon : 4,
		title : '提示'
	}, function(index) {
		top.layer.close(index);
		openDialog("添加复查记录", ctx + "/yhpc/lsjcjl/addReCheck/"+id, "800px", "600px", "");
	});
}

//修改复查信息
function updateReCheck(id){
	openDialog("修改复查记录", ctx + "/yhpc/lsjcjl/updateCheck/"+id, "800px", "600px", "");
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
		if(usertype!="1"&&row[i].type=="2"){
		}else{
			if(ids==""){
				ids=row[i].id;
			}else{
				ids=ids+","+row[i].id;
			}
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {icon: 3, title:'提示'}, function(index){
		$.ajax({
			type:'get',
			url:ctx+"/yhpc/lsjcjl/delete/"+ids,
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

//查看
function view(){
	var row = dg.datagrid('getSelected');
	if(row==null) {
		layer.msg("请选择一行记录！",{time: 1000});
		return;
	}
	
	openDialogView("查看临时检查信息",ctx+"/yhpc/lsjcjl/view/"+row.id,"800px", "400px","");
	
}

//初始化
function initData(){
	$.each(wtListInit, function(idx, obj) {
    	var url=obj.m2;
    	var czwt=obj.m1;
    	var img=url.split("||");
    	var pic="";
    	if(url==null||url==""){
    		pic='<input type="hidden" name="czwturl" value="' + url + '" />';
    	}else{
    		pic='<a target="_blank" href="'+img[0]+'">'+
			'<img src=\''+img[0]+'\' style=\'width:100px; height: 100px\'>' +
			'<div class=\'info\'>' + img[1] + '</div>' +
			'</a>'+
			'<input type="hidden" name="czwturl" value="' + url + '" />';
    	}
		var $trHtml = $(
			'<tr style="height:80px" >'+
			'<td style="width:40%" >'+
			'<textarea name="czwt" class="textarea" style="border:0px;width: 100%;height: 80px;" >' +czwt+'</textarea>'+
			'</td>'+
			'<td>'+
			'<div id="divwtfj_'+ wtid + '" >' +
			pic+
			'</div>'+
			'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
			'</td>'+
			'<td>'+
			'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:80px;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
			'&nbsp;&nbsp;&nbsp;<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-plus"></i> 删除</button>'+
			'</td>'+
			'</tr>'
		 );	
		//id和数量加1
		wtid=wtid+1;
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml );
	});
	

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
}

//删除指定行的初查问题
function removeTr(obj) {
	obj.remove();
}

//添加初查问题和图片
function addNr() {
	layer.open({
	    type: 2,  
	    area: ['400px', '250px'],
	    title: '添加问题',
        maxmin: false, 
	    content: ctx + "/yhpc/lsjcjl/choosenr" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	var czwt=iframeWin.contentWindow.getczwt();
	    	var img="";
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<div id="divwtfj_'+ wtid + '" >' +
				'<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:100px; height: 100px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'<input type="hidden" name="czwturl" value="' + url + '" />' +
				'</a>';
	    	}
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(
					'<tr style="height:80px" >'+
					'<td style="width:40%" >'+
					'<textarea name="czwt" class="textarea" style="border:0px;width: 100%;height: 80px;" >' +czwt+'</textarea>'+
					'</td>'+
					'<td>'+
					'<div id="divwtfj_'+ wtid + '" >' +
					url2+
					'</div>'+
					'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
					'</td>'+
					'<td>'+
					'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:80px;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
					'&nbsp;&nbsp;&nbsp;<button class="btn btn-info btn-sm" style="width:80px" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
					'</td>'+
					'</tr>'
		            );
	    	var  $list= $("#czwttable tr").eq(-1);
			$list.after( $li );
			layer.close(index);//关闭对话框。
			//id和数量加1
			wtid=wtid+1;
			},
	cancel: function(index){}
	}); 	
}

//修改初查图片
function updatepic(wtid) {
	layer.open({
	    type: 2,  
	    area: ['400px', '250px'],
	    title: '修改图片',
      maxmin: false, 
	    content: ctx + "/yhpc/lsjcjl/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	var img="";
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:100px; height: 100px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'</a>'+
				'<input type="hidden" name="czwturl" value="' + url + '" />' ;
	    	}
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(''+
					url2+
		            '');
			$('#divwtfj_'+wtid).html($li);
			layer.close(index);//关闭对话框。
			//id和数量加1
			wtid=wtid+1;			
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

//展开detailview
function openView(rowIndex){
	$('#tt').datagrid("expandRow",rowIndex);
}

//折叠detailview
function collapseView(rowIndex){
	$('#tt').datagrid("collapseRow",rowIndex);
}

//修改复查照片
function openPicForm(id1,index1) {
	layer.open({
	    type: 2,  
	    area: ['400px', '350px'],
	    title: '上传图片',
        maxmin: false, 
	    content: ctx + "/yhpc/lsjcjl/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	    	
	    	var  $list= $("#fileList_"+id1);
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	if(url==null||url==""){
	    		layer.msg("请上传图片", {
	    			time : 2000
	    		});
	    		return;
	    	}
	    	var img=url.split("||");
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(
					'<a target="_blank" href="'+img[0]+'">'+img[1]+'</a>'
		            );
			$list.html( $li );
			var  $list2= $("#picurl_"+id1);
			$list2.remove();
			layer.close(index);//关闭对话框。
			$('#fcpic_'+id1).val(url);
			//防止出现内部滚动条导致高度错位的bug
			$('#tt').datagrid("collapseRow",index1);
			$('#tt').datagrid("expandRow",index1);
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '上传成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

//导出现场检查记录word
function exportword(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	$.ajax({
		url:ctx+"/yhpc/lsjcjl/exportword/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}
