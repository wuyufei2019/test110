<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>日常检查信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form id="inputForm" action="${ctx}/yhpc/rcjc/${action}" method="post" class="form-horizontal">
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
                  
                 <tr>
                     <td class="width-15 active"><label class="pull-right">检查日期：</label></td>
      				 <td class="width-35" ><input name="M1" id="M1" style="width: 100%;height: 30px;" class="easyui-datebox"
      					 value="${entity.m1 }" data-options="required:true,editable:false" /></td>
					 <td class="width-15 active"><label class="pull-right">严重程度：</label></td>
					 <td class="width-35" ><input style="width:100%;height: 30px;" id="M16" name="M16" value="${entity.m16 }" class="easyui-combobox" data-options="panelHeight:'auto', editable:false ,
									valueField: 'text',textField: 'text',required:true,
									data: [
								        {value:'A',text:'A'},
								        {value:'B',text:'B'},
								        {value:'C',text:'C'}]"/></td>
                 </tr> 
                 <tr>
                     <td class="width-15 active"><label class="pull-right">辖区部门：</label></td>
      				 <td class="width-35" ><input name="M3" id="M3" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m3 }" editable="true" data-options="required:true,valueField:'text',textField:'text',url:'${ctx }/system/department/deptjson', panelHeight:'120px'" /></td>
                 	 <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
      				 <td class="width-35" ><input name="M2" id="rcjc_zrbm" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m2 }" editable="true" data-options="required:true " /></td>
                 </tr> 
                 <tr>
                 	<td class="width-15 active"><label class="pull-right">检查类型：</label></td>
      				<td class="width-35" ><input name="M6_1" id="M6_1" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m6_1 }" data-options="valueField: 'value',textField: 'text',panelHeight:'120', editable:true ,
									valueField: 'text',textField: 'text',url:'${ctx }/yhpc/rcjc/lxjson/1', validType:['length[0,25]']" /></td>
					<td class="width-15 active"><label class="pull-right">缺失类型：</label></td>
      				<td class="width-35" ><input name="M6_2" id="M6_2" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m6_2 }" data-options="valueField: 'value',textField: 'text',panelHeight:'120', editable:true ,
									valueField: 'text',textField: 'text',url:'${ctx }/yhpc/rcjc/lxjson/2', validType:['length[0,25]']" /></td>
                 </tr> 

                 <tr>
                     <td class="width-15 active"><label class="pull-right">责任部门主管：</label></td>
      				 <td class="width-35" ><input name="M8" id="rcjc_zrbmzg" style="width: 100%;height: 30px;" class="easyui-combobox"
      					 value="${entity.m8 }" data-options="valueField: 'id',textField: 'text' " /></td>
					 <td class="width-15 active"><label class="pull-right">计划完成时间：</label></td>
					 <td class="width-35" ><input name="M10" id="M10" style="width: 100%;height: 30px;" class="easyui-datebox"
												  value="${entity.m10 }" data-options="editable:false" /></td>
                 </tr>
                 
                 <tr>
					 <td class="width-15 active"><label class="pull-right">检查人员：</label></td>
					 <td class="width-85" colspan="3"><input name="M18" id="M18" style="width: 100%;height: 30px;" class="easyui-textbox"
												  value="${entity.m18 }" data-options=" validType:['length[0,25]']" /></td>
                 </tr>
				 <tr>
					 <td class="width-15 active"><label class="pull-right">现场照片：</label></td>
					 <td class="width-35"  colspan="3">
						 <div id="uploader_dw_m4">
							 <div id="m4fileList" class="uploader-list" ></div>
							 <div id="imagePicker">选择图片</div>
						 </div>
					 </td>
				 </tr>

				 <tr>
					 <td class="width-15 active"><label class="pull-right">缺失情况：</label></td>
					 <td class="width-35"  colspan="3"><input name="M5" id="M5" style="width: 100%;height: 80px;" class="easyui-textbox"
															  value="${entity.m5 }" data-options="multiline: true, validType:['length[0,1000]']" /></td>
				 </tr>

				<c:if test="${not empty entity.ID}">
					<input type="hidden" name="ID" value="${entity.ID}" />
					<input type="hidden" name="S1" value="<fmt:formatDate value="${entity.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
					<input type="hidden" name="S3" value="${entity.s3}" />
					<input type="hidden" name="qyid" value="${entity.qyid}" />
					<input type="hidden" name="M13" value="${entity.m13}" />
					<input type="hidden" name="M19" value="${entity.m19}" />
					<input type="hidden" name="M17" value="${entity.m17}" />
				</c:if>
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
			//严重程度
			$('#M16').combobox({
			    onSelect: function (row) {
			    	var v = $('#M1').datebox('getValue');
			    	if(v == '' || v == null) {
			    		return;
			    	}
			    	if(row.value == 'A') {
				    	v = addMoth(v,1);
			    	}else {
				    	v = addMoth(v,3);
			    	}
			    	$('#M10').datebox('setValue', v);	// set datebox value
			    }
			});
			
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

		});

        //责任部门下拉框，管理责任部门主管
        $("#rcjc_zrbm").combobox({
            panelHeight:'120px',
            editable:true ,
            valueField: 'text',
            textField: 'text',
            url:'${ctx}/system/department/deptjson',
            onLoadSuccess:function (param, success, error) {
                var depid;
                $.each(param, function(i, item){
                    if(item.text=='${entity.m2 }')
                        depid=item.id;return false;
                });

                $.ajax({
                    url:'${ctx}/system/user/ryjson',
                    data:{'depid':depid},
                    dataType : 'json',
                    type : 'POST',
                    success: function (data){
                        $('#rcjc_zrbmzg').combobox('loadData', data);
                    }
                });
            },
            onSelect: function(rec){
                $.ajax({
                    url:'${ctx}/system/user/ryjson',
                    data:{'depid':rec.id},
                    dataType : 'json',
                    type : 'POST',
                    success: function (data){
                        $('#rcjc_zrbmzg').combobox('setValue', '');
                        $('#rcjc_zrbmzg').combobox('loadData', data);
                    }
                });
            }
        });





		//时间处理
		function addMoth(d,m){
			var ds=d.split('-');
			d=new Date( ds[0],ds[1]-1+m,ds[2])
			return d.toLocaleDateString().match(/\d+/g).join('-')  
		}
		
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
		
		if('${action}' == 'update'){
			var zpUrl = '${entity.m4}';
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
				    
				    var $input = $('<input id="input_'+id+'" type="hidden" name="M4" value="'+arry[i]+'"/>');
					$('#uploader_dw_m4').append( $input );
				}
			}
			
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