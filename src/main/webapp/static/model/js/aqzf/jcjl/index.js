var dg;
var d;
var wtid=1;
var wtnum=0;
var target=$('#tt').datagrid;
$(function() {	
	dg = $('#aqzf_jcjl_dg').datagrid(
			{
				method : "get",
				url : ctx + '/aqzf/jcjl/list',
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
				             {field:'ID',title:'id',checkbox:true,width:50,align:'center'},
				             {field:'m0',title:'编号',width:60,align:'center'},
				             {field:'qyname',title:'被检查单位',width:70,align:'center'},
				             {field:'m8',title:'检查人员',width:30,align:'center'},
				             {field:'m6',title:'检查时间起',width:50,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		return datetime.format('yyyy-MM-dd hh:mm:ss');  
				                  	}
				              	}	 
				             },				                 
				             {field:'m7',title:'检查时间止',width:50,align:'center',
				             	  formatter : function(value, row, index) {
				                  	if(value!=null&&value!='') {
				                  		var datetime=new Date(value);
				                  		return datetime.format('yyyy-MM-dd hh:mm:ss');
				                  	}
				              	}	 
				             },	
				             {field:'m10',title:'备注',width:70,align:'center'},
				             {field : 'tj',title : '添加操作',sortable : false,width : 70,align : 'center',
									formatter : function(value, row, index) {
										var a = '';
										//是否添加现场处理措施
										if(row.m12 == '0'){
											a += "<a  class='btn btn-danger btn-xs' onclick='addXccl("+row.id+")'>现场处理</a>";
										}else if(row.m12 == '1'){
											a += "已现处"
										}
										//是否添加责令整改
										if(row.m13 == '0'){
											a += "<a style='margin-left:5px' class='btn btn-success btn-xs' onclick='addZlzg("+row.id+")'>责令整改</a>";
										}else if(row.m13 == '1'){
											a += "<span style='margin-left:5px'>已责改</span>"
										}
										if(laspadd == '1'){
											//是否添加立案审批
											if(row.m14 == '0'){
												a += "<a style='margin-left:5px' class='btn btn-primary btn-xs' onclick='addLasp("+row.id+")'>立案审批</a>";
											}else if(row.m14 == '1'){
												a += "<span style='margin-left:5px'>已立案</span>"
											}
										}
										return a;
									}
				             }
				] ],
				onDblClickRow : function(rowdata, rowindex, rowDomElement) {
					view();
				},
				onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
			    	  $(this).datagrid("autoMergeCells",['qyname']);
				},
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#aqzf_jcjl_tb'
			});

});

//添加现场处理
function addXccl(id) {
	openDialog("添加现场处理措施信息", ctx + "/aqzf/clcs/create2/"+id, "900px","400px", "");
}

//添加责令整改
function addZlzg(id) {
	openDialog("添加责令整改信息", ctx + "/aqzf/zlzg/create/"+id, "900px","400px", "");
}

//添加立案审批
function addLasp(id) {
	openDialog("添加立案审批信息", ctx + "/xzcf/ybcf/lasp/create2/"+id, "900px","400px", "");
}

////添加复查意见
//function addFcyj(jczt,id) {
//	if (jczt==1){
//		layer.msg("请先添加责令限期整改指令书", {
//			time : 6000
//		});
//		return;
//	}else if (jczt==3){
//		layer.msg("该记录已添加整改复查信息，请去整改复查意见模块", {
//			time : 6000
//		});
//		return;
//	}
//	openDialog("添加复查意见信息", ctx + "/aqzf/fcyj/create/"+id, "900px","400px", "");
//}

//导出现场检查记录word
function fileexport(){
	var row = dg.datagrid('getSelected');
	if(row==null){
		layer.msg('请选择一行记录',{time: 1000});
		return;
	}
	
	$.ajax({
		url:ctx+"/aqzf/jcjl/export/"+row.id,
		type:"POST",
		success:function(data){
			window.open(ctx+data);
		}
	});
}

//删除
function del() {
	var row = dg.datagrid('getChecked');
	if (row == null || row == '') {
		layer.msg("请至少勾选一行记录！", {
			time : 1000
		});
		return;
	}

	var ids = "";
	for (var i = 0; i < row.length; i++) {
		if(row[i].m12=='1'){
			layer.msg("存在已生成现场处理措施的记录，不得删除!", {
				time : 3000
			});
			return;
		}else if(row[i].m13=='1'){
			layer.msg("存在已生成责令整改的记录，不得删除!", {
				time : 3000
			});
			return;
		}else if(row[i].m14=='1'){
			layer.msg("存在已生成立案审批的记录，不得删除!", {
				time : 3000
			});
			return;
		}
		if (ids == "") {
			ids = row[i].id;
		} else {
			ids = ids + "," + row[i].id;
		}
	}

	top.layer.confirm('删除后无法恢复您确定要删除？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.ajax({
			type : 'get',
			url : ctx + "/aqzf/jcjl/delete/" + ids,
			success : function(data) {
				layer.alert(data, {
					offset : 'rb',
					shade : 0,
					time : 2000
				});
				top.layer.close(index);
				dg.datagrid('reload');
				dg.datagrid('clearChecked');
				dg.datagrid('clearSelections');
			}
		});
	});

}

// 弹窗修改
function upd() {
	var row = dg.datagrid('getSelected');
	if (row == null) {
		layer.msg("请选择一行记录！", {
			time : 1000
		});
		return;
	}
	if(row.m12=='0' && row.m13=='0' && row.m14=='0'){
		openDialog("修改检查记录", ctx + "/aqzf/jcjl/update/" + row.id, "900px","400px", "");
	}else if(row.m12=='1'){
		layer.msg("该记录已生成现场处理措施，不得修改!", {
			time : 3000
		});
		return;
	}else if(row.m13=='1'){
		layer.msg("该记录已生成责令整改，不得修改!", {
			time : 3000
		});
		return;
	}else if(row.m14=='1'){
		layer.msg("该方案已生成立案审批，不得修改!", {
			time : 3000
		});
		return;
	}
	
}

////导出责令整改
//function exportZlzg() {
//	var row = dg.datagrid('getSelected');
//	if(row==null){
//		layer.msg('请选择一行记录',{time: 1000});
//		return;
//	}
//	if (row.jczt==1){
//		layer.msg("请先添加责令整改信息", {
//			time : 3000
//		});
//		return;
//	}
//	$.ajax({
//		url:ctx+"/aqzf/zlzg/exportword/"+row.zlid,
//		type:"POST",
//		success:function(data){
//			window.open(ctx+data);
//		}
//	});
//
//}

////导出复查意见
//function exportFcyj() {
//	var row = dg.datagrid('getSelected');
//	if(row==null){
//		layer.msg('请选择一行记录',{time: 1000});
//		return;
//	}
//	if (row.jczt!=3){
//		layer.msg("请先添加复查意见信息", {
//			time : 3000
//		});
//		return;
//	}
//	
//	$.ajax({
//		url:ctx+"/aqzf/fcyj/exportword/"+row.fcid,
//		type:"POST",
//		success:function(data){
//			window.open(ctx+data);
//		}
//	});
//}

//直接添加检查记录
function addJcjl() {
	openDialog("添加检查记录信息", ctx + "/aqzf/jcjl/addJl2/", "900px","400px", "");
}

//弹出图片选择框
function openPicForm(id1,index) {
	layer.open({
	    type: 2,  
	    area: ['400px', '250px'],
	    title: '上传图片',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjl/choose" ,
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
			$('#url_'+id1).val(url);
			var ii=index-1;
			
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '上传成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

//展开view
function openView(rowIndex){
	$('#tt').datagrid("expandRow",rowIndex);
	$('#tt').datagrid('fixRowHeight',rowIndex);
}

//折叠view
function collapseView(rowIndex){
	$('#tt').datagrid("collapseRow",rowIndex);
	$('#tt').datagrid('fixRowHeight',rowIndex);
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
	
	openDialogView("查看检查记录",ctx + "/aqzf/jcjl/view/" + row.id,"900px","400px","");

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

// 负责预览图的销毁
function removeFile(fileid) {
	$("#"+fileid).remove();
	$("#input_"+fileid).remove();
};

function addNr() {
	layer.open({
	    type: 2,  
	    area: ['600px', '250px'],
	    title: '添加问题',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjl/choosenr" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	var czwt=iframeWin.contentWindow.getczwt();
	    	if(czwt==""||czwt==null||czwt==undefined){
	    		layer.msg("请选择存在问题", {
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
	    	var img="";
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<div id="divwtfj_'+ wtid + '" >' +
				'<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
				'<div class=\'info\'>' + img[1] + '</div>' +
				'<input type="hidden" name="czwturl" value="' + url + '" />' +
				'</a>';
	    	}
	    	var filename=iframeWin.contentWindow.getfilename();
			var $li = $(
					'<tr style="height:80px" >'+
					'<td style="width:40%" >'+
					'<input type="text" id="czwtidwd_'+ wtid +'" name="czwt" style="width: 100%;height: 100%;" value="'+czwt+'" />'+
					'</td>'+
					'<td>'+
					'<div style="text-align:center;" id="divwtfj_'+ wtid + '" >' +
					url2+
					'</div>'+
					'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
					'</td>'+
					'<td>'+
					'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:100%;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
					'<button class="btn btn-info btn-sm" style="width:100%" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
					'</td>'+
					'</tr>'
		            );
	    	var  $list= $("#czwttable tr").eq(-1);
			$list.after( $li );
			$("#czwtidwd_"+wtid).combobox({
				editable:true ,
    			valueField:'id',
    			textField:'text',
    			panelHeight:'100px',
    			multiline:true ,
    			url:ctx+'/aqzf/wfxw/idlist'
			});
			layer.close(index);//关闭对话框。
			//id和数量加1
			wtid=wtid+1;
			wtnum = wtnum + 1;
			
			$("#wtnum").val(wtnum);			
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

var jcwtallids2 = "";
function addJcnr() {
	layer.open({
	    type: 2,  
	    area: ['850px', '300px'],
	    title: '添加问题',
        maxmin: false, 
	    content: ctx + "/aqzf/jcjl/choosejcnr" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	faid=iframeWin.contentWindow.getnrid();
	    	$('#nrid').val(faid);
	    	var target=$('#tt').datagrid({
	    		method : "get",
	    		view: detailview,
	    		width:705,
	    		nowrap:false,
	    		singleSelect:true,
	    		url:ctx+'/aqzf/jcjl/nrlist/'+faid,
	    		columns:[[
	    			{field:'jcdy',title:'检查单元',width:192},
	    			{field:'m2',title:'检查内容',width:343},
	    			{field:'m3',title:'操作',width:143,
	    				formatter: function(value, rowData, rowIndex){
	                    	return '符合<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + 'onclick="collapseView(' + 
	    						rowIndex+' )" />' +
	                    	'不符合<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + rowIndex + ')" />';
	                	}
	    			},
	    			{field:'ID',title:'id',width:50,hidden:'true'}
	    		]], 
	            detailFormatter:function(rowIndex, rowData){
	            	jcwtallids2 +="jcwt_" + rowData.id+",";
	    			return '<div id="ddv-' + rowIndex + '"><table style="width: 98%;">' +
	    					'<input type="hidden" name="jcnrid_' + rowData.id + '" value="' + rowData.id + '" />' +
	    					'<input type="hidden" id="jcnr_' + rowData.id + '" value="' + rowData.jcnr + '" />' +
	    					'<tr>'+
	    					'<td>' +
	    					'检查问题：' +	
	    					'</td>'+									
	    					'</tr>'+
	    					'<tr>'+
	    					'<td>' +
	    					'<input type="text" id="jcwt_' + rowData.id + '" name="jcwt_' + rowData.id + '" style="width: 667px;height: 30px;" />' +	
	    					'</td>'+									
	    					'</tr>'+
	    					'<tr>'+
	    					'<td>' +
	    					'图片：' +	
	    					'</td>'+									
	    					'</tr>'+
	    					'<tr>'+
	    					'<td>' +
	    					'<div id="uploader_ws_'+rowData.id+'" style="height: 42px;">'+
	    					'<input type="hidden" name="url_'+rowData.id+'" id="url_'+rowData.id+'" value="'+rowData.pic+'" />'+
	    					'<div id="fileList_'+rowData.id+'" class="uploader-list" ></div>'+
	    					'<a style="margin:2px" class="btn btn-success btn-xs" onclick="openPicForm(' + 
	    						rowData.id+','+rowIndex+' )">上传图片</a>'+
	    					'</div>'+
	    					'</td>'+									
	    					'</tr>'+
	    					'</table></div>';    
	    		},
	    		onLoadSuccess : function(rowdata, rowindex, rowDomElement) {	
	    		   	if(jcwtallids2.length>0){
	    				jcwtallids2 = jcwtallids2.substr(0,jcwtallids2.length - 1);
	    			}
	    			var jcwtid = jcwtallids2.split(",");
	    			for (x in jcwtid){
	    				$("#"+jcwtid[x]).combobox({
	    					editable:true ,
	    	    			valueField:'id',
	    	    			textField:'text',
	    	    			//multiple:true ,  
	            			//multiline:true ,
	            			//separator:'||',
	    	    			panelHeight:'135px',
	    	    			url:ctx+'/aqzf/wfxw/idlist'
	    				});
	    			}
	    		}
	    	});
	    	layer.close(index);//关闭对话框。
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	

}

//上传图片
function updatepic(wtid) {
	layer.open({
	    type: 2,  
	    area: ['400px', '250px'],
	    title: '修改图片',
      maxmin: false, 
	    content: ctx + "/aqzf/jcjl/choose" ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){	
	    	//jQuery 1.4 新增支持：参数index可以为负数。如果index为负数，则将其视作length + index
	    	var iframeWin = layero.find('iframe')[0];
	    	var url=iframeWin.contentWindow.geturl();
	    	if(url==""||url==null||url==undefined){
	    		layer.msg("请上传图片", {
	    			time : 2000
	    		});
	    		return;
	    	}
	    	var url2='<input type="hidden" name="czwturl" value="" />';
	    	var img="";
	    	if(url!=null&&url!=""){
	    		img=url.split("||");
	    		url2='<a target="_blank" href="'+img[0]+'">'+
				'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
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
			wtnum = wtnum + 1;
			
			$("#wtnum").val(wtnum);			
			parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '添加成功！',shade: 0 ,time: 2000 });
			},
	cancel: function(index){}
	}); 	
}

//初始化
function initData(){
	var wtallids = "";
	nrList=JSON.parse(wtListInit);  
	$.each(nrList, function(idx, obj) {
    	var url=obj.m4;
    	var czwt=obj.m2;
    	var img=url.split("||");
    	var pic="";
    	if(url==null||url==""){
    		pic='<input type="hidden" name="czwturl" value="' + url + '" />';
    	}else{
    		pic='<a target="_blank" href="'+img[0]+'">'+
			'<img src=\''+img[0]+'\' style=\'width:50px; height: 50px\'>' +
			'<div class=\'info\'>' + img[1] + '</div>' +
			'</a>'+
			'<input type="hidden" name="czwturl" value="' + url + '" />';
    	}
    	wtallids +="czwtidwd_" + wtid+",";
		var $trHtml = $(
			'<tr style="height:80px" >'+
			'<td style="width:40%" >'+
			'<input type="text" id="czwtidwd_'+ wtid +'" name="czwt" style="width: 100%;height: 100%;" value="'+czwt+'" />'+
			'</td>'+
			'<td>'+
			'<div style="text-align:center;" id="divwtfj_'+ wtid + '" >' +
			pic+
			'</div>'+
			'<input type="hidden" name="wtfjnum_' + wtid + '" value="0" />' +
			'</td>'+
			'<td>'+
			'<a class="btn btn-info btn-sm" style="margin-bottom:5px; width:100%;" data-toggle="tooltip" data-placement="left" onclick="updatepic('+wtid+')" title="修改图片"><i class="fa fa-file-text-o"></i> 修改图片</a>'+
			'<button class="btn btn-info btn-sm" style="width:100%" data-toggle="tooltip" data-placement="left" onclick="removeTr($(this).parent().parent())" title="删除"><i class="fa fa-trash-o"></i> 删除</button>'+
			'</td>'+
			'</tr>'
		 );	
		//id和数量加1
		wtid=wtid+1;
    	var  $list= $("#czwttable tr").eq(-1);
		$list.after( $trHtml );
	});
	if(wtallids.length>0){
		wtallids = wtallids.substr(0,wtallids.length - 1);
	}
	var wtallid = wtallids.split(",");
	for (x in wtallid){
		$("#"+wtallid[x]).combobox({
			editable:true ,
			valueField:'id',
			textField:'text',
			panelHeight:'100px',
			multiline:true ,
			url:ctx+'/aqzf/wfxw/idlist'
		});
	}

}

function removeTr(obj) {
//	roid=roid-1;
//	$("#jcyh").val(roid);
//	$("#jcyh2").val(roid);
	obj.remove();
}
