<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;

	function doSubmit(){
		if('${usertype}' == '1'){
			var nodes = $('#tt').tree('getChecked');
			var bmids="";
			for(var i=0;i<nodes.length;i++){
				if(bmids==""){
					bmids=nodes[i].id;
				}else{
					bmids+=","+nodes[i].id;
				}
			}
			if(bmids==""){
				layer.msg("请勾选所在部门！",{time: 3000});
				return;
			}
			$("#bmids").val(bmids);
		}

		$("#inputForm").submit(); 
	}
	
	$(function(){
		$('#inputForm').form({    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){ 
		    	if(data=='success')
		    		parent.layer.open({icon:1,title: '提示',offset: 'rb',content: '操作成功！',shade: 0 ,time: 2000 });
		    	else
		    		parent.layer.open({icon:2,title: '提示',offset: 'rb',content: '操作失败！',shade: 0 ,time: 2000 });
		    	parent.dg.datagrid('reload');
		    	parent.layer.close(index);//关闭对话框。
		    }    
		});
		
		laydate({
            elem: '#birthday',  
            event: 'focus'  
        });
	});
	</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/system/user/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id }" />
		<input type="hidden" name="id2" value="<c:if test="${empty user.id2}">0</c:if><c:if test="${not empty user.id2}">${user.id2}</c:if>" />
		<input type="hidden" id="bmids" name="bmids" value="${bmids }" />
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
			  <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>账号：</label></td>
		         <td class="width-35"><input id="loginName" name="loginName" class="easyui-textbox easyui-validatebox" data-options=" " value="${user.loginName }" style="width: 100%;height: 30px;"> </td>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>姓名:</label></td>
		         <td class="width-35"><input name="name" type="text" value="${user.name }" class="easyui-textbox easyui-validatebox" data-options="required:'required',validType:'length[1,20]'" style="width: 100%;height: 30px;"/></td>
		      </tr>
		      <c:if test="${action != 'update'}">
		      <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>密码：</label></td>
		         <td class="width-35"><input id="plainPassword" name="plainPassword" type="password" class="easyui-textbox easyui-validatebox" value="000000" data-options="required:'required',validType:'length[6,20]'" style="width: 100%;height: 30px;"/>（默认000000）</td>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font>确认密码:</label></td>
		         <td class="width-35"><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-textbox easyui-validatebox" value="000000" data-options="required:'required',validType:'equalTo[$(\'#plainPassword\').val()]'" style="width: 100%;height: 30px;"/></td>
		      </tr>
		      </c:if>
		      
		      <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red"></font>出生日期：</label></td>
		         <td class="width-35"><input id="birthday" name="birthday" type="text" class="easyui-datebox"  value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>"  style="width: 100%;height: 30px;"/></td>
		         <td class="width-15 active"><label class="pull-right"><font color="red"></font>性别:</label></td>
		         <td class="width-35"><input type="radio" id="man" name="gender" value="1" class="i-checks"/><label for="man">男</label><input type="radio" id="woman" name="gender" value="0" class="i-checks"/><label for="woman">女</label></td>
		      </tr>
		      <tr>
		         <td class="width-15 active"><label class="pull-right">Email：</label></td>
		         <td class="width-35"><input type="text" name="email" value="${user.email }" class="easyui-textbox easyui-validatebox" data-options=" validType:'email'" style="width: 100%;height: 30px;"> </td>
		         <td class="width-15 active"><label class="pull-right">手机:</label></td>
		         <td class="width-35"><input type="text" name="phone" value="${user.phone }" class="easyui-numberbox easyui-validatebox"  data-options="validType:'mobile'" style="width: 100%;height: 30px;"/></td>
		      </tr>
		      <c:if test="${action != 'update' && usertype != '9' }">
				<input type="hidden" name="usertype" value="${usertype}" />
			  </c:if>
		      <c:if test="${usertype == '9' }">
				<tr>
					<td class="width-15 active"><label class="pull-right">隶属网格：</label></td>
					<td class="width-35"><input id="xzqy" name="xzqy" value="${user.xzqy}"  class="easyui-combotree" style="width: 100%;height: 30px;" data-options="url:'${ctx}/system/admin/xzqy/xzqyjson',method: 'get',panelHeight:180"/></td>
					<td class="width-15 active"><label class="pull-right">用户类型：</label></td>
					<td class="width-35">
						<input type="radio" name="usertype" value="1" class="i-checks usertype"/><label for="qy">企业</label>
						<input type="radio" name="usertype" value="0" class="i-checks usertype"/><label for="ajj">安监局</label>
						<input type="radio" name="usertype" value="5" class="i-checks usertype"/><label for="zj">中介</label>
					</td>
				</tr>
			   </c:if>

			  <tr>
				  <c:if test="${usertype == '0' || usertype == '1'  }">
					  <td class="width-15 active"><label class="pull-right">人员归档部门：</label></td>
					  <td class="width-35"><input id="departmen" name="departmen" value="${user.departmen}"  class="easyui-combotree" style="width: 100%;height: 30px;" data-options="require:true,editable:false,panelHeight:180, valueField: 'id',textField: 'text',url:'${ctx}/system/department/idjson' "/></td>
				  </c:if>
				  <td class="width-15 active"><label class="pull-right">分配角色：</label></td>
				  <c:choose>
					  <c:when test="${usertype == '1'}">
						  <td class="width-35"><input name="roleid" id="roleid" value="${roleid}"  class="easyui-combobox" style="width: 100%;height: 30px;" data-options="editable:false,panelHeight:180,multiple:true,required:true, valueField: 'id',textField: 'name',url:'${ctx}/system/compuser/comprole/json' "/></td>
					  </c:when>
					  <c:otherwise>
						  <td class="width-35"><input name="roleid" id="roleid" value="${roleid}"  class="easyui-combobox" style="width: 100%;height: 30px;" data-options="editable:false,panelHeight:180,required:true, valueField: 'id',textField: 'name',url:'${ctx}/system/role/json' "/></td>
					  </c:otherwise>
				  </c:choose>
			  </tr>

			  <c:if test="${usertype == '0' || usertype == '1'  }">
				  <tr>
					  <td class="width-15 active"><label class="pull-right">所在部门：</label></td>
					  <td class="width-85" colspan="3" >
						  <ul id="tt" style="width:100%" ></ul>
					  </td>
				  </tr>
			  </c:if>
		      			
				<c:if test="${action == 'create'&&usertype == '9'}">
			    <tr id="aj_jgfl" style="display: none;">
					<td class="width-15 active"><label class="pull-right">监管分类：</label></td>
					<td class="width-85" colspan="3"><input  class="easyui-combobox" style="width:200px;height:30px;" name="userroleflg" value="${user.userroleflg }" data-options=" editable:false,panelHeight:'auto' ,data:[{value:'0',text:'全部'},{value:'1',text:'工贸'},{value:'2',text:'化工'}]"/></td>
				</tr>
				</c:if>
				
				<c:if test="${action == 'create'&&usertype == '0'}">
			    <tr id="aj_jgfl">
					<td class="width-15 active"><label class="pull-right">监管分类：</label></td>
					<td class="width-85" colspan="3"><input  class="easyui-combobox" style="width:200px;height:30px;" name="userroleflg" value="${user.userroleflg }" data-options="required:'required',editable:false,panelHeight:'auto' ,data:[{value:'0',text:'全部'},{value:'1',text:'工贸'},{value:'2',text:'化工'}]"/></td>
				</tr>
				</c:if>
				
			    <c:if test="${action == 'update' && user.usertype == '0' }">
				<tr>
					<td class="width-15 active"><label class="pull-right">监管分类：</label></td>
					<td class="width-85" colspan="3"><input  class="easyui-combobox" style="width:200px;height:30px;" name="userroleflg" value="${user.userroleflg }" data-options="required:'required',editable:false,panelHeight:'auto' ,data:[{value:'0',text:'全部'},{value:'1',text:'工贸'},{value:'2',text:'化工'}]"/></td>
				</tr>
			   </c:if>
			   
		        
		      <tr>
		         <td class="width-15 active">	<label class="pull-right">描述:</label></td>
		         <td class="width-85" colspan="3" ><input type="text" name="description" value="${user.description}" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true"/></td>
		      </tr>

			  <c:if test="${usertype == '1'}">
				  <tr>
					  <td class="width-15 active"><label class="pull-right">电子印章：</label></td>
					  <td class="width-85" colspan="3">
						  <input type="hidden" id="seal" name="seal" value="${user.seal}" />
						  <div id="uploader_ws">
							  <div id="fileList" class="uploader-list" ></div>
							  <div id="imagePicker">选择电子印章</div>
						  </div>
					  </td>
				  </tr>
			  </c:if>
		      </tbody>
		     </table>
	</form>
<script type="text/javascript">
var action="${action}";

var $ = jQuery,
$list = $('#fileList'); //图片上传

var uploadImgFlag = false;

$(function(){
	//加载分管部门信息
	$('#tt').tree({
		url: ctx+'/system/department/idjson',
		lines: true,
		checkbox: true,
		cascadeCheck: false,//关闭级联
		loadFilter: function(data){
			if (data.d){
				return data.d;
			} else {
				return data;
			}
		},
		onLoadSuccess:function(){
			$('#tt').tree("collapseAll");
			if ('${action}' == 'update') {
				var bmids =	$("#bmids").val();
				if(bmids!=null&&bmids!=""){
					var array = bmids.split(",");
					for(var i=0;i<array.length;i++){
						var node = $('#tt').tree('find',array[i]);
						$('#tt').tree('check',node.target);
						var pNode = $("#tt").tree("getParent", node.target);

						for(var j=0;j<10;j++){
							if(pNode != null){
								$("#tt").tree("expand", pNode.target);
								pNode = $("#tt").tree("getParent", pNode.target);
							}else{
								break;
							}
						}
					}
				}
			}else{

			}
		}
	});

	/*if(action=='create')
		$("#departmen").combobox('setValue', '${depid}');*/
    $("#roleid").combobox({
        loadFilter:function (data) {
			return data.rows
        }
	});
	$.fn.validatebox.defaults.rules.remote.message = '用户名已存在请修正';
	
	$('.usertype').on('ifChecked', function(event){    
	    var selectedvalue = $(event.target).val();
		if (selectedvalue == 0){//安监
			$("#aj_jgfl").css("display","table-row");
			
		}else{
			$("#aj_jgfl").css("display","none");
		}
	      
	});
});

//用户 添加修改区分
if(action=='create'){
	$("input[name='gender'][value=1]").attr("checked",true); 
	
	
	//用户名存在验证
	$('#loginName').textbox({    
	    required: true,    
	    validType:{
	    	length:[2,20],
	    	remote:["${ctx}/system/user/checkLoginName","loginName"]
	    }
	});  
	
}else if(action=='update'){
	//修改界面  用户类型不可编辑
	$("input[name='usertype']").attr("disabled","disabled"); 
	$('#loginName').textbox({    
	    required: true,    
	    validType:{
	    	length:[2,20],
	    	remote:["${ctx}/system/user/checkLoginName2/${user.id}","loginName"]
	    }
	});  
	
	//性别选择
	var gender='${user.gender}';
	if(gender==null||gender=='')
		gender=1;
	$("input[name='gender'][value="+gender+"]").attr("checked",true);
	//用户类别
	var usertype="${user.usertype}";
	if(usertype!=""){
		$("input[name='usertype'][value=${user.usertype}]").attr("checked",true);
	}

	if(usertype == '1'){
		var zpUrl = '${user.seal}';
		if(zpUrl!=null&&zpUrl!=''){
			var arry =zpUrl.split(",");
			for(var i=0;i<arry.length;i++){
				var arry2 =arry[i].split("||");
				var id="ws_zp_"+i;
				var $li = $(
						"<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
						"<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
						"<div class=\"info\">" + arry2[1] + "</div>" +
						"</div>"
				);

				$list.html( $li );
			}
			uploadImgFlag=true;
		}
	}
}

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
	},
	compress :{
		width: 1200,
		height: 1200,
		quality: 90,
		allowMagnify: false,
		crop: false,
		preserveHeaders: false,
		noCompressIfLarger: false,
		compressSize: 1024*50
	}
});

uploader.on( 'uploadProgress', function( file, percentage ) {
	$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
});

// 图片上传成功，显示预览图
uploader.on( 'uploadSuccess', function( file ,res) {
	$.jBox.closeTip();
	if(res.error==0){
		var $li = $(
				"<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
				"<img>" +
				"<div class=\"info\">" + file.name + "</div>" +
				"</div>"
				),
				$img = $li.find('img');

		$list.html( $li );

		// 创建缩略图
		uploader.makeThumb( file, function( error, src ) {
			if ( error ) {
				$img.replaceWith('<span>不能预览</span>');
				return;
			}

			$img.attr( 'src', src );
		}, 100, 100 );

		var newurl=res.url+"||"+res.fileName;
		$('#seal').val( newurl );
		uploadImgFlag=true;

	}else{
		layer.msg(res.message,{time: 3000});
	}
});
</script>
</body>
</html>