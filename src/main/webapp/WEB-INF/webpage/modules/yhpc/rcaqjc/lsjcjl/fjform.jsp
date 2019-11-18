<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>临时复查管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.4.5/datagrid-detailview.js"></script>
	<script type="text/javascript" src="${ctx}/static/model/js/yhpc/rcaqjc/lsjcjl/index.js?v=1.3"></script>
	<script  type="text/javascript" src="${ctx}/static/model/js/form.js"></script>
</head>
<body>
     <form id="inputForm" action="${ctx}/yhpc/lsjcjl/${action}"  method="post" class="form-horizontal" >
     				
			<div title="检查信息" iconCls="icon-add" data-options="selected:false " style="padding:10px;">
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-15" colspan="4"><label class="pull-left">初查信息：</label></td>				
				</tr>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr >  
					<td class="width-15 active" ><label class="pull-right">检查企业：</label></td>
					<td class="width-35" colspan="3">
						<input value="${lsjcjl.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;" class="easyui-combobox"
									data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/qynmlist'" /></td>
					</tr>
				</c:if>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查名称：</label></td>
					<td class="width-35" colspan="3"><input name="M16" class="easyui-textbox" value="${lsjcjl.m16 }" style="width: 100%;height: 30px;" data-options="readonly:'true',validType:'length[0,50]' " /></td>
				</tr>				
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查时间：</label></td>
					<td class="width-35"><input id="SignedStartDate" name="M2" class="easyui-datebox" value="${lsjcjl.m2 }" style="width: 100%;height: 30px;" data-options="readonly:'true', editable:true " /></td>
										<td class="width-15 active"><label class="pull-right">整改期限：</label></td>
					<td class="width-35"><input id="SignedEndDate" name="M3" class="easyui-datebox" value="${lsjcjl.m3 }" style="width: 100%;height: 30px;" data-options="readonly:'true', editable:true, validType:'equaldDate[\'#SignedStartDate\']' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					<td class="width-35"><input name="M4" class="easyui-textbox" value="${lsjcjl.m4 }" style="width: 100%;height: 30px;" data-options="readonly:'true', required:'true', validType:'length[0,25]' " /></td>
					<td class="width-15 active"><label class="pull-right">整改负责人：</label></td>
					<td class="width-35"><input name="M5" class="easyui-textbox" value="${lsjcjl.m5 }" style="width: 100%;height: 30px;" data-options="readonly:'true', required:'true', validType:'length[0,25]' " /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传检查记录表：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m7">
					    <div id="m7fileList" class="uploader-list" ></div>
						</div> 
					</td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3" style="height:80px">
						${lsjcjl.m14 }
					</td>
				</tr>				
				</tbody>
			</table>
		</div>	

			
		<div title="复查信息" iconCls="icon-add" data-options="selected:true " style="padding:10px;">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			<tbody>
				<tr>
					<td class="width-15" colspan="4"><label class="pull-left">复查信息：</label></td>				
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">复查时间：</label></td>
					<td class="width-35"><input id="M8" name="M8" class="easyui-datebox" value="${lsjcjl.m8 }" style="width: 100%;height: 30px;" data-options="required:'true', editable:true " /></td>
					<td class="width-15 active"><label class="pull-right">复查人员：</label></td>
					<td class="width-35"><input id="M9" name="M9" class="easyui-textbox" value="${lsjcjl.m9 }" style="width: 100%;height: 30px;" data-options="required:'true', validType:'length[0,25]' " /></td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-85" colspan="3" >
						<table id="tt" style="width:605px"></table>
					</td>
					
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">上传检查记录表：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m12">
					    <div id="m12fileList" class="uploader-list" ></div>
					    <div id="filePicker2">选择文件</div>
					</div> 
					</td>
				</tr>
				</tbody>
			</table>
		</div>
				<c:if test="${not empty lsjcjl.ID}">
					<input type="hidden" name="ID" value="${lsjcjl.ID}" />
					<input type="hidden" name="ID1" value="${lsjcjl.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${lsjcjl.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${lsjcjl.s3}" />
				</c:if>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var checkflag=${checkflag};
	var jlid;
	jlid='${lsjcjl.ID}';
	
	var $ = jQuery,
	$list2 = $('#m7fileList'); //检查文件上传
	$list4 = $('#m12fileList'); //复查文件上传

	var fileuploader2 = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker2',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader2.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});

	// 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
    		
	// 文件上传成功 
	fileuploader2.on( 'uploadSuccess', function( file ,res) {
		$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list4.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
			
			$('#uploader_ws_m12').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});	
	
	if('${action}' == 'update'){		
		var fjUrl = '${lsjcjl.m7}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M7" value="'+arry[i]+'"/>');
				$('#uploader_ws_m7').append( $input );
			}
		}
		
		var fjUrl2 = '${lsjcjl.m12}';
		if(fjUrl2!=null&&fjUrl2!=''){
			arry =fjUrl2.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj2_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list4.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
				$('#uploader_ws_m12').append( $input );
			}
		}
	}
	
$(function(){
	//默认时间
	var now=new Date();
	$('#M8').datebox('setValue', now.format('yyyy-mm-dd'));	
	
	var flag=true;
	$('#inputForm').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
	    	if(isValid&&flag){
	    		flag=false;
	    		$.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
	    		return true;
	    	}
			return false;	// 返回false终止表单提交
	    },    
	    success:function(data){ 
	    	$.jBox.closeTip();
	    	if(data=='success')
	    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
	    	else
	    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
	    	parent.dg.datagrid('reload');
	    	parent.layer.close(index);//关闭对话框。
	    }    
	});

	if ('${action}' == 'create') {
		    $("#M0").textbox("setValue", "（    ）安监检记〔    〕  号");
	}

	target=$('#tt').datagrid({
		method : "get",
		view: detailview,
		width:638,
		height: 'auto',
		nowrap:false,
		singleSelect:true,
		url:ctx+'/yhpc/lsjcjl/nrlist/'+jlid,
		columns:[[
			{field:'m1',title:'隐患名称(展开查看复查意见)',width:300},
			{field:'m2',title:'初检图片',width:160,
				formatter: function(value, rowData, rowIndex){
				    	if(value==null||value==""){
    						return '无';
    					}else{
    						var img=value.split("||");
    						return '<a target="_blank" href="'+img[0]+'">'+
								   '<div class=\'info\'>' + img[1] + '</div>' +
								   '</a>'
    					}
				}
			},
			{field:'m5',title:'状态',width:150,
				formatter: function(value, rowData, rowIndex){
					if(value=='1'){
                		return '已整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" />' +
                		'未整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" />';
            		}else if(value=='0'){
            		    return '已整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" />' +
                		'未整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" />';
            		}else{
            			return '已整改<input checked="checked" type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_1_' + rowData.id + ' value=1 ' + ' onclick="collapseView(' + 
						rowIndex+' )" />' +
                		'未整改<input type=radio style=width:20px;height:20px name=ra_' + rowData.id + ' id=ra_2_' + rowData.id + ' value=0 ' + ' onclick="openView(' + 
						rowIndex+' )" />';
            		}
            	}
			},
			{field:'ID',title:'id',width:50,hidden:'true'}
		]], 
        detailFormatter:function(rowIndex, rowData){
            var fcwt=rowData.m3;
        	if((fcwt==null)||(fcwt=="")){
        		fcwt="";
        	};
        	var picurl = '';
        	var url=rowData.m4;
        	if((url!=null)&&(url!="")&&(url!="undefined")){
        		var img=url.split("||");
        		picurl = '<a target="_blank" href="'+img[0]+'" id="picurl_'+rowData.id+'">'+img[1]+'</a>';
        	}
			return '<div id="ddv-' + rowIndex + '"><table><tr>' +
					'<td style="border:1px;width:700px " colspan="3" >' +
					'<input type="hidden" name="ccwt_' + rowData.id + '" value="' + rowData.m1 + '" />' +
					'<input type="hidden" name="ccpic_' + rowData.id + '" value="' + rowData.m2 + '" />' +
					'<input type="hidden" name="nrid" value="' + rowData.id + '" />' +
					'</td>' +
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'复查意见：' +	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'<textarea name="fcwt_' + rowData.id + '" class="textarea" style="width: 100%;height: 80px;" >' +fcwt+'</textarea>'+
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'复查照片：' +
					'<span id="pict_'+rowData.id+'"></span>'+	
					'</td>'+									
					'</tr>'+
					'<tr>'+
					'<td class="width-30" colspan="3">' +
					'<div>'+
					'<input type="hidden" name="fcpic_'+rowData.id+'" id="fcpic_'+rowData.id+'" value="'+rowData.m4+'" />'+
						picurl+
					'<div id="fileList_'+rowData.id+'" class="uploader-list" ></div>'+
					'<a style="margin:2px" class="btn btn-success btn-xs" onclick="openPicForm(' + 
						rowData.id+','+rowIndex+' )">上传图片</a>'+
					'</div>'+
					'</td>'+									
					'</tr>'+
					'</table></div>';    
		},
		onLoadSuccess : function() {
			 var row = $("#tt").datagrid("getRows");
            for (var r = 0; r < row.length; r++)
            {
                $("#tt").datagrid("expandRow",r);
            }
		},
 		onExpandRow: function(index,row){  
            $('#tt').datagrid('fixRowHeight',index);//防止出现滑动条  
 		},
 		onCollapseRow: function(index,row){  
            $('#tt').datagrid('fixRowHeight',index);//防止出现滑动条  
 		}
	});
});	
	</script>
</body>
</html>