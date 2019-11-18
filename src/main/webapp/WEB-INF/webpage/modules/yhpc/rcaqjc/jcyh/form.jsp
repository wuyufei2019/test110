<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>隐患整改页面</title>
	<meta name="decorator" content="default"/>
</head>
<body>

     <form id="inputForm" action="${ctx}/yhpc/jcyh/${action}"  method="post" class="form-horizontal" >
			<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer" >
			  <tbody>
			  	<tr>
					<td class="width-20 active"><label class="pull-right">企业名称：</label></td>
					<td class="width-30" colspan="3">
						${jcyh.qyname}
					</td>
				</tr>

				<tr>
					<td class="width-20 active"><label class="pull-right">责任部门：</label></td>
					<td class="width-30">${jcyh.m13 }</td>
					<td class="width-20 active"><label class="pull-right">发生区域：</label></td>
					<td class="width-30">${jcyh.m14 }</td>
				</tr>
							  
				<tr>
					<td class="width-20 active"><label class="pull-right">发现时间：</label></td>
					<td class="width-30" ><fmt:formatDate type="date"  value="${jcyh.jcsj }" /></td>
					<td class="width-20 active"><label class="pull-right">隐患类别：</label></td>
					<td class="width-30">${jcyh.m3 }</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">检查内容：</label></td>
					<td class="width-30" colspan='3' >${jcyh.jcnr }</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">问题描述：</label></td>
					<td class="width-30" colspan='3' style="height:80px">${jcyh.m1 }</td>
				</tr>

				<tr >
					<td class="width-20 active"><label class="pull-right">解决措施：</label></td>
					<td class="width-30" colspan='3' style="height:80px">${jcyh.m15 }</td>
				</tr>
				
				<tr>
					<td class="width-20 active"><label class="pull-right">隐患照片：</label></td>
					<td class="width-80" colspan="3">
					 <c:if test="${not empty jcyh.m2}">
					 <c:forTokens items="${jcyh.m2}" delims="," var="url" varStatus="urls">
					 	<c:set var="urlna" value="${fn:split(url, '||')}" />
					 	<div style="float: left;text-align: center;margin: 0 10px 10px 0;">
					 		<a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}" height="150px;"/><br/></a>
					 	</div>
					 </c:forTokens>
					 </c:if>
					</td>
				</tr>
				
				<tr >
					<td class="width-20 active"><label class="pull-right">隐患等级：</label></td>
					<td class="width-30" >${jcyh.m7 }</td>
					<td class="width-20 active"><label class="pull-right">计划完成时间：</label></td>
					<td class="width-30" ><fmt:formatDate type="date"  value="${jcyh.m4 }" /></td>
				</tr>
				</tbody>
			</table>
			
			<c:if test="${type eq 'update' }">
				<center>整改情况</center>
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
					<tr >
						<td class="width-20 active"><label class="pull-right">整改人：</label></td>
						<td class="width-30" >${jcyh.zgr }</td>
						<td class="width-20 active"><label class="pull-right">整改情况：</label></td>
						<td class="width-30" >
						<input style="width: 100%;height: 30px;"
	                                        id="M11" name="M11" class="easyui-combobox "
	                                        data-options="required:'true', editable:false, panelHeight:'auto', data: [
									        {value:'2',text:'整改完成'},
									        {value:'1',text:'整改未完成'},
									        {value:'0',text:'未整改'} ]" />
					     </td>
					</tr>
	
					<tr>
						<td class="width-20 active"><label class="pull-right">整改完成时间：</label></td>
						<td class="width-30" ><input id="M5" name="M5" class="easyui-datebox" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
						<td class="width-20 active"><label class="pull-right">整改费用：</label></td>
						<td class="width-30"><input name="M10" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]' " /></td>
					</tr>
	
					<tr>
						<td class="width-20 active"><label class="pull-right">整改后照片：</label></td>
						<td class="width-80" colspan="3">
						<div id="uploader_ws_m6">
						    <div id="m6fileList" class="uploader-list" ></div>
						    <div id="imagePicker">选择图片</div>
						</div>
						</td>
					</tr>
	
					<tr>
						<td class="width-20 active"><label class="pull-right">备注：</label></td>
						<td class="width-30" colspan="3"><input name="M8" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' " /></td>
					</tr>
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${type eq 'review' }">
				<center>复查情况</center>
				<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
					<tr >
						<td class="width-20 active"><label class="pull-right">复查人：</label></td>
						<td class="width-30" >${jcyh.fxr }</td>
						<td class="width-20 active"><label class="pull-right">复查结果：</label></td>
						<td class="width-30" >
						<input style="width: 100%;height: 30px;"
	                                        id="M11" name="M11" class="easyui-combobox "
	                                        data-options="required:'true', editable:false, panelHeight:'auto', data: [
									        {value:'3',text:'复查不通过'},
									        {value:'4',text:'复查通过'} ]" />
					     </td>
					</tr>
					
					<tr>
						<td class="width-20 active"><label class="pull-right">复查时间：</label></td>
						<td class="width-30" ><input id="M5" name="M5" class="easyui-datebox" style="width: 100%;height: 30px;" data-options="editable:false, required:'true' " /></td>
						<td class="width-20 active"><label class="pull-right">复查费用：</label></td>
						<td class="width-30"><input name="M10" class="easyui-textbox" style="width: 100%;height: 30px;" data-options="validType:'length[0,50]' " /></td>
					</tr>
						
					<tr>
						<td class="width-20 active"><label class="pull-right">现场照片：</label></td>
						<td class="width-80" colspan="3">
						<div id="uploader_ws_m6">
						    <div id="m6fileList" class="uploader-list" ></div>
						    <div id="imagePicker">选择图片</div>
						</div>
						</td>
					</tr>
					
					<tr>
						<td class="width-20 active"><label class="pull-right">备注：</label></td>
						<td class="width-30" colspan="3"><input name="M8" class="easyui-textbox" style="width: 100%;height: 80px;" data-options="multiline:true, validType:'length[0,500]' " /></td>
					</tr>
					</tbody>
				</table>	
			</c:if>
			
				<input type="hidden" name="ID" value="${jcyh.id}" />
				<input type="hidden" name="ID1" value="${jcyh.id1}" />
				<input type="hidden" name="qyid" value="${jcyh.qyid}" />
				<input type="hidden" name="S1" value="<fmt:formatDate value="${jcyh.s1}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="M1" value="${jcyh.m1}" />
				<input type="hidden" name="M1_1" value="${jcyh.m1_1}" />
				<input type="hidden" name="M2" value="${jcyh.m2}" />
				<input type="hidden" name="M3" value="${jcyh.m3}" />
				<input type="hidden" name="M4" value="<fmt:formatDate value="${jcyh.m4}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="createtime" value="<fmt:formatDate value="${jcyh.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<input type="hidden" name="M7" value="${jcyh.m7}" />
				<input type="hidden" name="M9" value="${jcyh.m9}" />
				<input type="hidden" name="M12" value="${jcyh.m12}" />
				<input type="hidden" name="M13" value="${jcyh.m13}" />
				<input type="hidden" name="M14" value="${jcyh.m14}" />
				<input type="hidden" name="M15" value="${jcyh.m15}" />
				<input type="hidden" name="M16" value="${jcyh.m16}" />
				<input type="hidden" name="M17" value="${jcyh.m17}" />					
       </form>
	   <h3 style="text-align: center;color: #337ab7;">整改复查情况</h3>
	   <div style="height:280px;">
	   		<table id="yhpc_yhzg_dg" style="width: 100%;height: 100%"></table>
	   </div> 
<script type="text/javascript">
	var yhid='${jcyh.id}';
	var dg;
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	var validateForm;
	function doSubmit(){
		$("#inputForm").serializeObject();
		$("#inputForm").submit(); 
	}

    // 负责预览图的销毁
    function removeFile(fileid) {
    	$("#"+fileid).remove();
    	$("#pic_"+fileid).remove();
    	$("#url_"+fileid).remove();
    };
 
    
	var $ = jQuery,
    $list = $('#m6fileList'); //图片上传
	
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
			var $input = $('<input id="input_'+file.id+'" type="hidden" name="M6" value="'+newurl+'"/>');
			
			$('#uploader_ws_m6').append( $input );
		}else{
			layer.msg(res.message,{time: 3000});
		}
	});
	
	$(function() {
		var flag = true;
		$('#inputForm').form({
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (isValid && flag) {
					flag = false;
					$.jBox.tip("正在提交，请稍等...", 'loading', {
						opacity : 0
					});
					return true;
				}
				return false; // 返回false终止表单提交
			},
			success : function(data) {
				$.jBox.closeTip();
				if (data == 'success')
					parent.layer.open({
						icon : 1,
						title : '提示',
						offset : 'rb',
						content : '操作成功！',
						shade : 0,
						time : 2000
					});
				else
					parent.layer.open({
						icon : 2,
						title : '提示',
						offset : 'rb',
						content : '操作失败！',
						shade : 0,
						time : 2000
					});
				parent.dg.datagrid('reload');
				parent.layer.close(index);//关闭对话框。
			}
		});

		//历史整改复查信息
		dg=$('#yhpc_yhzg_dg').datagrid({    
			method: "post",
		    url:ctx+'/yhpc/jcyh/zglist?yhid='+yhid,
		    fit : true,
			fitColumns : true,
			selectOnCheck:false,
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
		  	        {field:'zgr',title:'整改复查人员',width:50 },  
		  	        {field:'handletime',title:'整改复查时间',width:50,align:'center',
		  	        	formatter : function(value, row, index) {
			              	if(value!=null&&value!='') {
			              		var datetime=new Date(value);
			              		return datetime.format('yyyy-MM-dd');
			              	}
			            }
		  	        },
		  	        {field:'handlemoney',title:'整改费用',width:30,align:'center',
		  	         	formatter : function(value, row, index) {
			              	if(row.handletype=='2') {
			              		return '/';
			              	}else{
			              		return value;
			              	}
			            }
		  	        },
		  	        {field:'handleuploadphoto',title:'整改复查照片',width:50,align:'center',
			  	        formatter : function(value, row, index) {
		    				var content="";
		                  	if(value!=null&&value!='') {
		                  		var arr1=value.split("||");
		                    	for (var i = 0; i < arr1.length-1; i++) {
		                    		content=content+ '<img onclick=openImgView("'+arr1[i]+'") src='+arr1[i]+' style=margin-right:5px;max-width:100px;max-height:50px; />'; 
		                    	} 
		                    	return content;
		                  	}else{
		                  		return '/';
		                  	}
		                }
		             },
		  	        {field:'handletype',title:'类型',width:30,align:'center',
			  	        formatter : function(value, row, index) {
			              	if(value=='1') {
			              		return '整改';
			              	}else if(value=='2'){
			              		return '复查';
			              	}
			            }
		  	        },
		  	        {field:'handledesc',title:'整改复查备注',width:80,align:'center'},
		  	        {field:'handlestatus',title:'审核结果',width:40,align:'center',
			  	        formatter : function(value, row, index) {
			  	        	if(row.handletype=='1') {
			              		return '/';
			              	}
			              	if(value=='0') {
			              		return '未整改';
			              	}else if(value=='1'){
			              		return '整改未完成';
			              	}else if(value=='2'){
			              		return '整改完成';
			              	}else if(value=='3'){
			              		return '复查不通过';
			              	}else if(value=='4'){
			              		return '复查通过';
			              	}
			            }
		  	        }
		    ]],
		    onLoadSuccess: function(){
		    },
		    onLoadError:function(){
		    	layer.open({title: '提示',offset: 'rb',content: '数据加载失败！',shade: 0 ,time: 2000 });
		    },
			checkOnSelect:false,
			selectOnCheck:false,
		    toolbar:''
			});
	});	
</script>
</body>
</html>