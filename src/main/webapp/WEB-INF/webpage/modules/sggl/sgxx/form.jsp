<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>事故信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/sggl/sgxx/${action}"  method="post" class="form-horizontal" >
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
				
				<c:if test="${action eq 'update'}">
				<tr >
					<td class="width-20 active"><label class="pull-right">事故编号：</label></td>
					<td class="width-30" colspan="3">
						<input style="width: 100%;height: 30px;" readonly="readonly" id="M1" name="M1" class="easyui-textbox" value="${res.m1 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				</c:if>

				<tr >
					<td class="width-20 active"><label class="pull-right">事故发生时间：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M5" name="M5" class="easyui-datetimebox" value="${res.m5 }" data-options="required:'true',editable:false" />
					</td>
					<td class="width-20 active"><label class="pull-right">事故名称：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M2" name="M2" class="easyui-textbox" value="${res.m2 }" data-options="required:'true',validType:'length[0,50]'"/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">所属部门：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" class="easyui-combobox" id="M23"  name="M23" value="${res.m23 }"  data-options="editable:false,required:'true',
								panelHeight:'120px',valueField: 'id',textField: 'text',url:'${ctx}/system/department/deptjson'"/>
					</td>
					<td class="width-20 active"><label class="pull-right">发生地点：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M6" name="M6" class="easyui-textbox" value="${res.m6 }" data-options="validType:'length[0,50]'"/>
					</td>
				</tr>
				
				<tr>
					<!-- multiple:'true', -->
					<td class="width-20 active"><label class="pull-right">事故类型：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;" class="easyui-combobox" id="sggl_sgxx_m3"  name="M3" value="${res.m3 }"  
							data-options="editable:false,required:'true',panelHeight:'auto',
									data: [ {value:'死亡Fatal',text:'死亡Fatal'},
									{value:'损工事故',text:'损工事故'},
									{value:'医疗处理事故',text:'医疗处理事故'},
									{value:'急救事故',text:'急救事故'},
									{value:'幸免事故',text:'幸免事故'}]"/></td>
					<td class="width-20 active"><label class="pull-right">事故等级：</label></td>
					<td class="width-30"><input style="width: 100%;height: 30px;"" name="M4" class="easyui-combobox" value="${res.m4 }" 
							data-options="required:'true',editable:false, panelHeight:'auto',
									data: [ {value:'一般',text:'一般'},
									{value:'较大',text:'较大'},
									{value:'重大',text:'重大'},
									{value:'特大',text:'特大'}]"/></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">经济损失（万元）：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" id="M11" name="M11" class="easyui-textbox" value="${res.m11 }" data-options="validType:['number','length[0,10]'] " />
					</td>
					<td class="width-20 active"><label class="pull-right">事故性质：</label></td>
					<td class="width-30">
						<input style="width: 100%;height: 30px;" name="M7" class="easyui-combobox" value="${res.m7 }" data-options="editable:true, panelHeight:'auto' ,required:'true', data: [ {value:'责任事故',text:'责任事故'},
																																												  {value:'意外事故',text:'意外事故'},
																																												  {value:'其他',text:'其他'}]"/>
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">事故经过：</label></td>
					<td class="width-30" colspan="3"><input name="M13"  style="width: 100%;height: 80px;" value="${res.m13 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">救援情况：</label></td>
					<td class="width-30" colspan="3"><input name="M14"  style="width: 100%;height: 80px;" value="${res.m14 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">事故教训：</label></td>
					<td class="width-30" colspan="3"><input name="M15"  style="width: 100%;height: 80px;" value="${res.m15 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">事故原因分析：</label></td>
					<td class="width-30" colspan="3"><input name="M16"  style="width: 100%;height: 80px;" value="${res.m16 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">事故预防措施：</label></td>
					<td class="width-30" colspan="3"><input name="M17"  style="width: 100%;height: 80px;" value="${res.m17 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">事故责任人处理：</label></td>
					<td class="width-30" colspan="3"><input name="M18"  style="width: 100%;height: 80px;" value="${res.m18 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">相关人员教育情况：</label></td>
					<td class="width-30" colspan="3"><input name="M19"  style="width: 100%;height: 80px;" value="${res.m19 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">备注：</label></td>
					<td class="width-30" colspan="3"><input name="M20"  style="width: 100%;height: 80px;" value="${res.m20 }" class="easyui-textbox" data-options="multiline:'true' " ></input></td>
				</tr>												

				<tr>
					<td class="width-20 active"><label class="pull-right">事故现场照片/视频：</label></td>
					<td class="width-85" colspan="3">
					<div id="uploader_ws_m21">
					    <div id="m21fileList" class="uploader-list" ></div>
					    <div id="imagePicker">选择图片</div>
					</div>
					</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">事故调查报告：</label></td>
					<td class="width-30" colspan="3">
						<div id="uploader_ws_m22">
					    <div id="m22fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				
				<tr>
               		<td class="width-20" colspan="4" style="text-align:center;"><label>
                     	<a class='btn btn-success btn-xs' onclick='addbdrn()'>添加伤亡人员</a>
                     	<a class='btn btn-success btn-xs' onclick='delbdrn()'>删除伤亡人员</a>
                  	</label>
                  	</td>
				</tr>
				
         		<div id="bdnr">
               		<table id="fxgk_bdnr_dg" ></table>
           		</div>
				
				<input type="hidden" id="swryids" name="swryids"/>
				<input type="hidden" id="shcdids" name="shcdids"/>
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

	var Address = [{ "value": "1", "text": "轻伤" }, { "value": "2", "text": "重伤" }, { "value": "3", "text": "死亡" }];
	function unitformatter(value, rowData, rowIndex) {
	    if (value == 0) {
	        return;
	    }
	 
	    for (var i = 0; i < Address.length; i++) {
	        if (Address[i].value == value) {
	            return Address[i].text;
	        }
	    }
	}

    $list = $('#m21fileList'); //图片上传
	$list2 = $('#m22fileList'); //文件上传
	
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
    	isuploadImg();
    };
	
	// 图片上传成功，显示预览图
	uploader.on( 'uploadSuccess', function( file ,res) {
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
		            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		                "<img>" +
		                "<div class=\"info\">" + file.name + "</div>" +
		            "</div>"
		            ),
	
		        $img = $li.find('img');

		    $list.append( $li );
	
		    // 创建缩略图
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
	
		        $img.attr( 'src', src );
		    }, 100, 100 );
			
			
			var newurl=res.url+"||"+res.fileName;
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M21" value="'+newurl+'"/>');
			
			$('#uploader_ws_m21').append( $input );
			uploadImgFlag=true;
			
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
 
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
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M22" value="'+newurl+'"/>');
			
			$('#uploader_ws_m22').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	if('${action}' == 'update'){
		var zpUrl = '${res.m21}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
			            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
			                "<div class=\"info\">" + arry2[1] + "</div>" +
			            "</div>"
			            );

			    $list.append( $li );
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M21" value="'+arry[i]+'"/>');
				$('#uploader_ws_m21').append( $input );
			}
		}
		
		var fjUrl = '${res.m22}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M22" value="'+arry[i]+'"/>');
				$('#uploader_ws_m22').append( $input );
			}
		}
		
		isuploadImg();
	}

	//添加
	function addbdrn() {
		if(usertype=='1')
			qyid='${qyid}';
		else
			 qyid=$("#ID1").combobox("getValue");
		if(qyid==null||qyid==""){
			layer.msg("请先选择企业！");
			return;
		}
		var id1='${id1}';
		if(id1==''){
			id1=0;
		}
		openDialogView("添加伤亡人员",ctx+"/sggl/sgxx/swrycreate/"+id1+","+qyid,"900px", "400px","");
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
		dg.datagrid('endEdit', editIndex);
		var rows = dg.datagrid('getData').rows;
		var ids="";
		var ids2="";
		for(var i=0;i<rows.length;i++){
			if(ids==""&&ids2==""){
				if(rows[i].id!=""&&(typeof(rows[i].shcd) == "undefined")||rows[i].shcd==""){
					layer.msg("请选择受伤程度",{time: 3000});
					return;
				}
				ids=rows[i].id;
				ids2=rows[i].shcd;
			}else{
				if(rows[i].id!=""&&(typeof(rows[i].shcd) == "undefined")||rows[i].shcd==""){
					layer.msg("请选择受伤程度",{time: 3000});
					return;
				}
				ids=ids+","+rows[i].id;
				ids2=ids2+","+rows[i].shcd;
			}
		}
		$("#swryids").val(ids);
		$("#shcdids").val(ids2);
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

 		if('${action}'=="create"){
			sgid="add";
		}
		else
			sgid='${res.ID}';
			dg=$('#fxgk_bdnr_dg').datagrid({    
				method: "post",
			    url:ctx+'/sggl/sgxx/swrylist?sgid='+sgid,
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
				onClickCell: onClickCell,
			    columns:[[    
			        {field:'ID',title:'id',checkbox:true,width:50,align:'center'},      
			        {field:'m1',title:'姓名',width:40},    
			        {field:'m3',title:'性别',width:40,align:'center'},
			        {field:'gw',title:'岗位',width:60,align:'center'},
			        {field:'m5',title:'学历',width:40,align:'center',editor:'text'},
			        {field:'m9',title:'联系方式',sortable:false,width:70,align:'center'},
					{field:'shcd',title:'伤害程度(点击选择)',width: 70, formatter: unitformatter, align: 'center', editor: { type: 'combobox',required:true, options: { editable:false, data: Address, valueField: "value", textField: "text", panelHeight:'auto' } } }
			    ]],
			    onLoadSuccess: function(){
			    },
			    onLoadError:function(){
			    	layer.open({title: '提示',offset: 'rb',content: '数据加载中，请耐心等待。',shade: 0 ,time: 2000 });
			    },
				checkOnSelect:false,
				selectOnCheck:false,
				});	 
	});

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = dg.datagrid('options');
			var fields = dg.datagrid('getColumnFields',true).concat(dg.datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = dg.datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			dg.datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = dg.datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if (dg.datagrid('validateRow', editIndex)){
		dg.datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickCell(index, field){
/* 	var row = dg.datagrid('getData').rows[index];
	alert(row.shcd); */
	if (endEditing()){
		dg.datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}
	
	//判断是否上传图片
	function isuploadImg(){
		var img=$("input[name='M21']").val();
		if(img==null||img==""){
			uploadImgFlag=false;
		}else{
			uploadImgFlag=true;
		}
	}
</script>
</body>
</html>