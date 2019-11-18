<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加整改信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/yhpc/rcjc/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
            <tr>
                <td class="width-15 active"><label class="pull-right">检查日期：</label></td>
                <td class="width-35" ><fmt:formatDate value="${entity.m1}" pattern="yyyy-MM-dd" /></td>
                <td class="width-15 active"><label class="pull-right">严重程度：</label></td>
                <td class="width-35" >${entity.m16 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">辖区部门：</label></td>
                <td class="width-35" >${entity.m3 }</td>
                <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
                <td class="width-35" > ${entity.m2 } </td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">检查类型：</label></td>
                <td class="width-35" >${entity.m6_1 }</td>
                <td class="width-15 active"><label class="pull-right">缺失类型：</label></td>
                <td class="width-35" >${entity.m6_2 }</td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">责任部门主管：</label></td>
                <td class="width-35" >${m8 }</td>
                <td class="width-15 active"><label class="pull-right">计划完成时间：</label></td>
                <td class="width-35" ><fmt:formatDate value="${entity.m10}" pattern="yyyy-MM-dd" /></td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
                <td class="width-85" colspan="3">${entity.m18 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right">现场照片：</label></td>
                <td class="width-85" colspan="3">
                    <c:if test="${not empty entity.m4}">
                        <c:forTokens items="${entity.m4}" delims="," var="url" varStatus="urls">
                            <c:set var="urlna" value="${fn:split(url, '||')}" />
                            <div style="float: left;text-align: center;margin: 0 10px 10px 0;">
                                <a target="_blank" href="${urlna[0]}"><img src="${urlna[0]}"  height="150px;"/><br/></a>
                            </div>
                        </c:forTokens>
                    </c:if>
                </td>
            </tr>

            <tr>
                <td class="width-15 active"><label class="pull-right">缺失情况：</label></td>
                <td class="width-35"  colspan="3" style=" height: 50px;">${entity.m5 }</td>
            </tr>
            <tr>
                <td class="width-15 active"><label class="pull-right" style="height: 25px">录入审核：</label></td>
                <td class="width-35" >
                    <c:if test="${entity.shstate=='1' }">通过</c:if>
                    <c:if test="${entity.shstate=='2' }">不通过</c:if>
                </td>
                <td class="width-15 active" ><label class="pull-right">指定整改人：</label></td>
                <td class="width-35" > ${m9 } </td>
            </tr>

            <tr ><td style="background-color: #50a8f1;" colspan="4"></td></tr>
        <c:if test="${entity.m13 =='3' }">
            <tr>
                <td class="width-15 active"><label class="pull-right">整改审核反馈：</label></td>
                <td class="width-35"  colspan="3" style="height: 50px;"> ${entity.m17 } </td>
            </tr>
        </c:if>
			  <tr>
				  <td class="width-15 active"><label class="pull-right">解决措施：</label></td>
				  <td class="width-35"  colspan="3"><input name="M7" style="width: 100%;height: 80px;" class="easyui-textbox"
														   value="${entity.m7 }" data-options="multiline: true, validType:['length[0,1000]']" /></td>
			  </tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">实际完成时间：</label></td>
					<td class="width-35" ><input name="M11" style="width: 100%;height: 30px;" class="easyui-datebox"
					 value="${entity.m11 }" data-options="required:true,editable:false" /></td>
					<td class="width-15 active"><label class="pull-right">整改费用：</label></td>
					<td class="width-35" ><input name="M15" style="width: 100%;height: 30px;" class="easyui-textbox"
					 value="${entity.m15 }" data-options=" validType:['length[0,25]']" /></td>
				</tr>
                 <tr>
                     <td class="width-15 active"><label class="pull-right">整改后照片：</label></td>
      				 <td class="width-35"  colspan="3">
      				 	<div id="uploader_dw_m12">
					    	<div id="m12fileList" class="uploader-list" ></div>
					    	<div id="imagePicker2">选择图片</div>
						</div>
					</td>
                 </tr>  
                 
            <input type="hidden" name="ID" value="${entity.ID}" />
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		$list = $('#m4fileList'); //现场照片
		$list2 = $('#m12fileList'); //整改照片
	
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			$("#inputForm").submit();
		}
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
						parent.layer.open({icon : 1,title : '提示',offset : 'rb',content : '操作成功！',shade : 0,time : 2000});
					else
						parent.layer.open({icon : 2,title : '提示',offset : 'rb',content : '操作失败！',shade : 0,time : 2000});
					parent.dg.datagrid('reload');
					parent.layer.close(index);//关闭对话框。
				}
			});

		});
		
	    // 负责预览图的销毁
	    function removeFile(fileid) {
	    	$("#"+fileid).remove();
	    	$("#input_"+fileid).remove();
	    };

		//现场照片
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
		
		//整改后照片
		var uploader2 = WebUploader.create({

		    auto: true,

		    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',

		    server: '${ctx}/kindeditor/upload?dir=image',

		    pick: {
		    	id:'#imagePicker2',
		    	multiple : false,
		    },
		    duplicate :true,	    
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/jpg,image/jpeg,image/png' 
		    }
		});	
		
		// 现场照片
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
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M4" value="'+newurl+'"/>');
				
				$('#uploader_dw_m4').append( $input );
			}else{
				layer.msg(res.message,{time: 3000});
			}
		});

		// 整改照片
		uploader2.on( 'uploadSuccess', function( file ,res) {
			if(res.error==0){
				var $li = $(
			            "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
			            	"<span class=\"cancel\" onClick=\"removeFile('"+file.id+"')\" style=\"cursor: pointer\">删除</span>"+
			                "<img>" +
			                "<div class=\"info\">" + file.name + "</div>" +
			            "</div>"
			            ),
		
			        $img = $li.find('img');

			    $list2.append( $li );
		
			    // 创建缩略图
			    uploader2.makeThumb( file, function( error, src ) {
			        if ( error ) {
			            $img.replaceWith('<span>不能预览</span>');
			            return;
			        }
		
			        $img.attr( 'src', src );
			    }, 100, 100 );
				
				
				var newurl=res.url+"||"+res.fileName;
				var $input = $('<input id="input_'+file.id+'" type="hidden" name="M12" value="'+newurl+'"/>');
				
				$('#uploader_dw_m12').append( $input );
			}else{
				layer.msg(res.message,{time: 3000});
			}
		});
		
		if('${update}' == 'update'){
			var fjUrl = '${entity.m12}';
			if(fjUrl!=null&&fjUrl!=''){
				arry =fjUrl.split(",");
				for(var i=0;i<arry.length;i++){
					var arry2 =arry[i].split("||");
					var id="ws_fj_"+i;
					var $li = $(
				            "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
				            	"<span class=\"cancel\" onClick=\"removeFile('"+id+"')\" style=\"cursor: pointer\">删除</span>"+
				                "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
				                "<div class=\"info\">" + arry2[1] + "</div>" +
				            "</div>"
				            );
				    $list2.append( $li );
				    
				    var $input = $('<input id="input_'+id+'" type="hidden" name="M12" value="'+arry[i]+'"/>');
					$('#uploader_dw_m12').append( $input );
				}
			}
			
		}
</script>

</body>
</html>