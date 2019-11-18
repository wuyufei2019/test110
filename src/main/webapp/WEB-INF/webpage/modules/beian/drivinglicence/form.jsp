<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>驾驶证信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/beian/drivinglicence/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                 <tr>
                     <td class="width-15 active"><label class="pull-right">驾驶证号：</label></td>
      				 <td class="width-35"><input name="m1" id="m1" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m1 }" data-options="required: true,
      					 validType:['length[0,25]','FUN[ValidateName,\'该驾驶证已填写\']']" /></td>
   					 <td class="width-15 active"><label class="pull-right">姓名：</label></td>
      				 <td class="width-35" ><input name="m2" id="m2" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m2 }" data-options="validType:['length[0,25]']" /></td>
                 </tr> 
                 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">性别：</label></td>
      				 <td class="width-35" ><input name="m3" id="m3" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m3 }" editable="true" data-options="panelHeight: 'auto', data: [{'value': 1, 'text': '男'},{'value': 2, 'text': '女'}], editable: false" /></td>
					 <td class="width-15 active"><label class="pull-right">国籍：</label></td>
      				 <td class="width-35" ><input name="m4" id="m4" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m4 }" data-options="validType:['length[0,25]']" /></td>
                 </tr> 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">住址：</label></td>
      				 <td class="width-35" colspan="3"><input name="m5" id="m5" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m5 }" data-options="validType:['length[0,50]']" /></td>
                 </tr> 
                 
                 <tr>
					 <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
                     <td class="width-35" colspan="3"><input name="m12" id="m12" style="width: 100%;height: 30px;" class="easyui-textbox"
                          value="${entity.m12 }" data-options="validType:['length[0,100]']" /></td>
                 </tr> 
                 
                 
                 
                 
                 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">出生日期：</label></td>
      				 <td class="width-35"><input name="m6" id="m6" style="width: 100%;height: 30px;" class="easyui-datebox"
      					 value="${entity.m6 }" data-options="" /></td>
   					 <td class="width-15 active"><label class="pull-right">初次领证日期：</label></td>
      				 <td class="width-35" ><input name="m7" id="m7" style="width: 100%;height: 30px;" class="easyui-datebox"
      					 value="${entity.m7 }" data-options="" /></td>
                 </tr>  
                 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">准驾车型：</label></td>
      				 <td class="width-35" ><input name="m8" id="m8" style="width: 100%;height: 30px;" class="easyui-textbox"
      					 value="${entity.m8 }" data-options=" validType:['length[0,25]']" /></td>
                  
                     <td class="width-15 active"><label class="pull-right">有效期：</label></td>
      				 <td class="width-35" ><input name="m9" id="m9" style="width: 100%;height: 30px;" class="easyui-datebox"
      					 value="${entity.m9 }" data-options="editable: false" /></td>
                 </tr> 
                 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">上传扫描件：</label></td>
      				 <td class="width-35"  colspan="3">
      				 	<div id="uploader_dw_m11">
					    	<div id="m11fileList" class="uploader-list" ></div>
					    	<div id="imagePicker">选择图片</div>
						</div>
					</td>
                 </tr>
                 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">备注：</label></td>
      				 <td class="width-35"  colspan="3"><input name="m10" id="m10" style="width: 100%;height: 80px;" class="easyui-textbox"
      					 value="${entity.m10 }" data-options="multiline: true, validType:['length[0,100]']" /></td>
                 </tr> 
                 
				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="userid" value="${entity.userid}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss" />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
				</c:if>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		$list = $('#m11fileList'); //上传照片
        function ValidateName(val) {
            var result = true;
            if('${action}' =='create'){
                $.ajax({
                    method: 'POST',
                    url: "${ctx}/beian/drivinglicence/valid/unique/" + val,
                    async: false,
                    success: function (data) {
                        result = data;
                    }
                });
            }
            return result;
        }
		function doSubmit() {
			$("#inputForm").submit();
		}
		$(function() {
			var flag = true;
			$('#inputForm').form({
				onSubmit : function() {
                    if ($("input[name='m11']").length == 0) {
                        layer.msg("请上传扫描件！");
                        return false;
                    }
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
					if('${action}' == 'create'){
						$('#m11fileList').html('');
						$("#inputForm").form("clear");
					}else{
						parent.layer.close(index);//关闭对话框。
					}
				}
			});

		});
		
		//上传照片
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
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="m11" value="'+newurl+'"/>');
				
				$('#uploader_dw_m11').append( $input );
			}else{
				layer.msg(res.message,{time: 3000});
			}
		});
		
		if('${action}' == 'update' || '${action}' == 'update2'){
			var zpUrl = '${entity.m11}';
			if(zpUrl!=null&&zpUrl!=''&&zpUrl!=undefined){
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
				    
				    var $input = $('<input id="input_'+id+'" type="hidden" name="m11" value="'+arry[i]+'"/>');
					$('#uploader_dw_m11').append( $input );
				}
			}
		}
		
		 // 负责预览图的销毁
	    function removeFile(fileid) {
	    	$("#"+fileid).remove();
	    	$("#input_"+fileid).remove();
	    };
</script>

</body>
</html>