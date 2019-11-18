<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>

</head>
<body>
            <form action="${ctx}/bis/aqpxxx/${action}" method="post" id="inputForm" novalidate class="form-horizontal">
			<table id="table_" class="table table-bordered dataTable"  style="margin:auto;">
			<tbody>
			<c:if test="${usertype != '1' and action eq 'create'}">
				<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${qylist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height:30px;"
									class="easyui-combobox"
									data-options="required:'true',valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
			    <c:if test="${usertype != '1' and action eq 'update'}">
				<tr>
						<td class="width-15 active"><label class="pull-right">企业名称：</label></td>
						<td class="width-35" colspan="3">
							<input value="${qylist.ID1 }" id="_qyid" name="ID1" style="width: 100%;height:30px;"
									class="easyui-combobox"
									data-options="editable:false, disabled:true,valueField: 'id',textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
						</td>
					</tr>
			    </c:if>
				<tr>
               <td class="width-15 active"><label class="pull-right">培训人员姓名：</label></td>
               <td class="width-35">
               		<input id="M1" name="M1" style="width: 100%;height: 30px;" class="easyui-combogrid" value="${qylist.m1 }"
                     data-options="required:true , validType:['cczu_stringCheckSub','length[0,10]'],panelWidth: '33%',fitColumns : true,editable:true ,idField: 'text',textField: 'text',url:'${ctx}/bis/ygxx/ygxxlist/${qyid}',
                        columns:[[
                                 {field:'text',title:'姓名',width:100},
                                 {field:'idcard',title:'身份证号',width:200}
                                 ]]" />
               </td>
               <td class="width-15 active"><label class="pull-right">培训人员类别：</label></td>
					<td class="width-35"><input id="M2" name="M2" class="easyui-combobox" value="${qylist.m2 }" style="width:100%;height:30px;" 
								data-options="validType:'length[0,100]',editable:true,data: [
							        {value:'主要负责人',text:'主要负责人'},
							        {value:'安全管理人员',text:'安全管理人员'},
							        {value:'操作人员',text:'操作人员'},
							        {value:'特种设备作业人员',text:'特种设备作业人员'},
							        {value:'特种作业人员',text:'特种作业人员'},
							        {value:'安全生产负责人',text:'安全生产负责人'},
							        {value:'注册安全工程师',text:'注册安全工程师'},
							        {value:'注册消防工程师',text:'注册消防工程师'},
							        {value:'焊接切割',text:'焊接切割'},
							        {value:'电工',text:'电工'},
							        {value:'起重机械',text:'起重机械'},
							        {value:'叉车',text:'叉车'},
							        {value:'其他人员',text:'其他人员'} ]" /></td>
				</tr>
				
				<tr>
					<td class="width-15 active"><label class="pull-right">培训人员职务：</label></td>
					<td class="width-35"><input id="M3" name="M3" class="easyui-combobox" value="${qylist.m3 }" style="width:100%;height:30px;"  
								data-options="validType:'length[0,100]',editable:true,data: [
							        {value:'董事长',text:'董事长'},
							        {value:'总经理',text:'总经理'},
							        {value:'副总经理',text:'副总经理'},
							        {value:'安全总监',text:'安全总监'},
							        {value:'安全经理',text:'安全经理'},
							        {value:'部门经理',text:'部门经理'},
							        {value:'车间主任',text:'车间主任'},
							        {value:'班组长',text:'班组长'},
							        {value:'其他',text:'其他'} ]" /></td>
					<td class="width-15 active"><label class="pull-right">证书名称：</label></td>
					<td class="width-35">
						<input type="text" name="M10" class="easyui-textbox" value="${qylist.m10 }"  data-options="validType:'length[0,100]'" style="width: 100%;height: 30px;" />
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">证书编号：</label></td>
					<td class="width-35">
						<input type="text" name="M11" class="easyui-textbox" value="${qylist.m11 }"  data-options="validType:'length[0,50]'" style="width: 100%;height: 30px;" />
					</td>
					<td class="width-15 active"><label class="pull-right">有效期：</label></td>
					<td class="width-35"><input id="bis_aqpxxx_form_M9" name="M9" type="text" class="easyui-datebox" value="<fmt:formatDate value="${qylist.m9 }"/>" style="width:100%;height:30px;" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最近培训时间：</label></td>
					<td class="width-35"><input id="bis_aqpxxx_form_M4" name="M4" type="text" class="easyui-datebox" value="<fmt:formatDate value="${qylist.m4 }"/>" style="width:100%;height:30px;" data-options="required:'true',editable:false"/></td>
					<td class="width-15 active"><label class="pull-right">下次培训时间：</label></td>
					<td class="width-35"><input id="bis_aqpxxx_form_M5" name="M5" type="text" class="easyui-datebox" value="<fmt:formatDate value="${qylist.m5 }"/>" style="width:100%;height:30px;" data-options="editable:false,validType:'compareDate[\'#bis_aqpxxx_form_M4\',\'下次培训时间不能早于最近培训时间!\' ]' "/></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">发证机关：</label></td>
					<td class="width-35" colspan="3"><input id="M6" class="easyui-combobox" name="M6" value="${qylist.m6 }" style="width:100%;height:30px;"  data-options="required:true ,panelHeight:'auto' ,editable:true ,valueField: 'text',textField: 'text',url: '${ctx}/tcode/dict/fzjg' " /></td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">上传证书文件：</label></td>
					<td class="width-35" colspan="3">
						<div id="uploader_ws_m8">
					    <div id="m8fileList" class="uploader-list" ></div>
					    <div id="filePicker">选择文件</div>
					</div> 
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注：</label></td>
					<td class="width-35" colspan="3"><input name="M7" type="text" value="${qylist.m7 }" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true,validType:'length[0,500]'"/></td>
				</tr>
				
				<input type="hidden" id="eid" name="eid" value="${qylist.eid }"/>
				<c:if test="${not empty qylist.ID}">
					<input type="hidden" name="ID" value="${qylist.ID}" />
					<input type="hidden" name="ID1" value="${qylist.ID1}" />
					<input type="hidden" name="S1"
						value="<fmt:formatDate value="${qylist.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${qylist.s3}" />
				</c:if>
				</tbody>
			</table>
            </form>
            <script type="text/javascript">
	var usertype=${usertype};
	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
		$("#M2").combobox('setValue',$("#M3").combobox('getText'));
		$("#M3").combobox('setValue',$("#M3").combobox('getText'));
		$("#M6").combobox('setValue',$("#M6").combobox('getText'));
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
	});
	
	
	var $ = jQuery,
	$list2 = $('#m8fileList'); //文件上传
	$("#M1").combogrid({
		onSelect:function (index,row){
			$("#eid").val(row.id);
		}
	});
	
	var fileuploader = WebUploader.create({

	    auto: true,

	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

	    server: '${ctx}/kindeditor/upload?dir=draw',
	    		
	    pick: {
	    	id:'#filePicker',
	    	multiple : false,
	    },
	    duplicate :true  
	});
	
	fileuploader.on( 'uploadProgress', function( file, percentage ) {
		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
	});
	
	    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#input_"+fileid).remove();
    };
	
	// 文件上传成功 
	fileuploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
		if(res.error==0){
			var $li = $(
		            "<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url+"')\">"+res.fileName+"</a>"+
		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
		            "</div>"
		            );
	
		    
		    $list2.append( $li );
	 
			var newurl=res.url+"||"+res.fileName;
			
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M8" value="'+newurl+'"/>');
			
			$('#uploader_ws_m8').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	
	
	if('${action}' == 'update'){
		
		var fjUrl = '${qylist.m8}';
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
			    
			    var $input = $('<input id="input_'+id+'" type="hidden" name="M8" value="'+arry[i]+'"/>');
				$('#uploader_ws_m8').append( $input );
			}
		}
	}
	
	</script>
</body>

</html>