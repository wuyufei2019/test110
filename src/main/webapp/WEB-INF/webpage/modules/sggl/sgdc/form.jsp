<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故调查管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/sggl/sgdc/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<c:if test="${usertype != '1' and action eq 'create'}">
					<tr>
						<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${res.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
					<tr>  
						<td class="width-20 active" ><label class="pull-right">企业名称：</label></td>
						<td class="width-30" colspan="3">
							<input value="${res.qyid }" id="_qyid" name="qyid" style="width: 100%;height: 30px;"
										class="easyui-combobox"
										data-options="editable:false, disabled:true, valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" /></td>
					</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">事故名称：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 30px;" id="M1" name="M1" class="easyui-textbox" value="${res.m1 }" data-options="required:'true',validType:'length[0,50]'"/>
					</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">发生时间：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-datetimebox" value="${res.m2 }"  data-options="required:'true',editable:false" />
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经济损失（万元）：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M3" name="M3" class="easyui-textbox" value="${res.m3 }" data-options="validType:['number','length[0,10]'] " />
					</td>
					<td class="width-20 active"><label class="pull-right">伤亡人数：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M4" name="M4" class="easyui-textbox" value="${res.m4 }" data-options="validType:['number','length[0,10]'] " />
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">调查报告：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m5">
					    <div id="m5fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<c:if test="${not empty res.ID}">
					<input type="hidden" name="ID" value="${res.ID}" />
					<input type="hidden" name="qyid" value="${res.qyid}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${res.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${res.s3}" />
				</c:if>
			</table>

		  	<tbody>
       </form>
 
<script type="text/javascript">
	var usertype=${usertype};
	var action='${action}';
	var sgid;//事故id
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;	

	$list2 = $('#m5fileList'); //文件上传
	
	var uploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=image',

	    pick: {
	    	id:'#imagePicker',
	    	multiple : false,
	    },
	    duplicate :true,	    
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/jpg,image/jpeg,image/png' 
	    }
	});
	
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=file',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M5" value="'+newurl+'"/>');
			
			$('#uploader_ws_m5').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var fjUrl = '${res.m5}';
		if(fjUrl!=null&&fjUrl!=''){
			arry =fjUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_fj_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
			            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
			            	"<span class=\"ss\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			            "</div>"
			            );
			    $list2.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M5" value="'+arry[i]+'"/>');
				$('#uploader_ws_m5').append( $input );
			}
		}
	}

	//添加
	function addbdrn() {
		var id1='${id1}';
		openDialogView("添加伤亡人员",ctx+"/fxgk/fxxx/swrycreate/"+id1,"900px", "400px","");
	}
	
	//删除
	function delbdrn() {
		var rows = dg.datagrid('getChecked');
		if(rows==null||rows=='') {
			layer.msg("请至少勾选一行记录！",{time: 1000});
			return;
		}
		while(rows.length!=0){
			var row=dg.datagrid("getChecked")[0];
			var rowIndex = dg.datagrid('getRowIndex', row);
			dg.datagrid('deleteRow',rowIndex);  
		}
	}

	function doSubmit(){
		$("#inputForm").submit(); 
	}
	
	$(function(){
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

/* 		if('${action}'=="create"){
			sgid="add";
		}
		else
			sgid='${id1}';
			dg=$('#fxgk_bdnr_dg').datagrid({    
				method: "post",
			    url:ctx+'/sggl/sgdc/swrylist/'+sgid,
				fitColumns : true,
				selectOnCheck:false,
				idField : 'ID',
				striped:true,
				pagination:true,
				rownumbers:true,
				nowrap:false,
				pageNumber:1,
				pageSize : 50,
				pageList : [ 50, 100, 150, 200, 250 ],
				striped:true,
			    columns:[[    
			  			{field : 'ID',title : 'id',checkbox : true,width : 50,align : 'center'},  
			  	        {field:'dangerlevel',title:'姓名',width:150},
			  	        {field:'checktitle',title:'性别',width:150 },  
			  	        {field:'content',title:'年龄',width:150 },
			  	        {field:'content',title:'薪资',width:150 },
			  	        {field:'content',title:'岗位',width:150 },
			  	        {field:'content',title:'学历',width:150 },  
			  	        {field:'content',title:'职务',width:150 },  
			  	        {field:'content',title:'伤害程度',width:150 }  
			    ]],
			    onLoadSuccess: function(){
			    },
			    onLoadError:function(){
			    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			    },
				checkOnSelect:false,
				selectOnCheck:false,
				});	 */
	});
	
</script>
</body>
</html>