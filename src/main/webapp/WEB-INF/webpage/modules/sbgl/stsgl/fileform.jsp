<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>添加风险活动管理</title>
<meta name="decorator" content="default" />
</head>
<body>
   <form id="inputForm" action="${ctx }/sbgl/stsgl/${action}" method="post" class="form-horizontal">
      <table id="tablecont" class="table table-bordered">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">附件类别：</label></td>
               <td class="width-80" colspan="3"><div>
                     <input class="easyui-combobox" id="filetype" name="type" style="width: 100%;height: 30px;"
                        data-options="editable: false ,panelHeight:'auto' , data: [
							        {value:'1',text:'立项审批文件'},
							        {value:'2',text:'可行性研究报告'},
							        {value:'3',text:'预评价报告'},
							        {value:'4',text:'安全设施设计'},
							        {value:'5',text:'项目试生产方案'},
                                    {value:'6',text:'安全设施竣工验收'},
                                    {value:'7',text:'设计、施工、监理单位的相关资质'},
                                    {value:'8',text:'其他材料'} ]" />
                  </div></td>
            </tr>

               <input type="hidden" name="ID1" id="ID1"/>
               <input type="hidden" name="contjson" id="contjson"/>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
		var action = '${action}';
		if(parent.Global_Id)
			 $("#ID1").val(parent.Global_Id);
		$("#filetype").combobox({
			onSelect : function(rec){
				var html="";
				var uploadhtml="";
				$(".cont").remove();//重置内容
				if(rec.value=="1"){
					html='<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">立项日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td>'
					+'<td class="width-20 active"><label class="pull-right">批准部门：</label></td>'
					+'<td class="width-30"><input class="easyui-textbox"style="width: 100%;height: 30px;"/></td></tr>';
				}else if(rec.value=="2"){
					html='<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">编制日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td>'
					+'<td class="width-20 active"><label class="pull-right">编制单位：</label></td>'
					+'<td class="width-30"><input class="easyui-textbox"style="width: 100%;height: 30px;"/></td></tr>';
				}else if(rec.value=="3"){
					html='<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">编制日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td>'
					+'<td class="width-20 active"><label class="pull-right">备案日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td></tr>'
					+'<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">编制单位：</label></td>'
					+'<td class="width-30"><input class="easyui-textbox"style="width: 100%;height: 30px;"/></td></tr>';
				}else if(rec.value=="4"){
					html='<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">编制日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td>'
					+'<td class="width-20 active"><label class="pull-right">批复日期：</label></td>'
					+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td></tr>'
					+'<tr class="cont serial">'
					+'<td class="width-20 active"><label class="pull-right">编制单位：</label></td>'
					+'<td class="width-30"><input class="easyui-textbox"style="width: 100%;height: 30px;"/></td></tr>';
				}else if(rec.value=="5"){
					html='<tr class="cont serial">'
						+'<td class="width-20 active"><label class="pull-right">编制日期：</label></td>'
						+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td>'
						+'<td class="width-20 active"><label class="pull-right">备案日期：</label></td>'
						+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td></tr>';
				}else if(rec.value=="6"){
					html='<tr class="cont serial">'
						+'<td class="width-20 active"><label class="pull-right">验收日期：</label></td>'
						+'<td class="width-30"><input class="easyui-datebox"style="width: 100%;height: 30px;"/></td></tr>'
				}
				html+='<tr class="cont"><td class="width-20 active"><label class="pull-right">附件(单文件)：</label></td><td class="width-30"colspan="3"><div id="url"><div id="fileList"class="uploader-list"></div><div id="filePicker">选择文件</div></div></td></tr>';
				$("#tablecont").append(html);
				$.parser.parse('.cont'); //解析标签
				//设置上传文件属性
				fileupload=WebUploader.create({ 
						auto: true,
			    	    swf:  '${ctxStatic}/webuploader-0.1.5/Uploader.swf',
			    	    server: '${ctx}/kindeditor/upload?dir=file',
			    	    duplicate :true,
			    	    accept : {
			    	    	    title: 'files',
			    	    	    extensions: 'doc,docx,xls,xlsx,ppt,pptx,txt,zip,rar,pdf'
			    	    	   // mimeTypes: 'image/*'
			    	    }, 
			    	    pick: {
			    		id:'#filePicker',
			    		multiple : false,
			   			}
			   		});
				//上传时load动画
				fileupload.on( 'uploadProgress', function( file, percentage ) {
		      		$.jBox.tip("正在上传，请稍等...",'loading',{opacity:0});
		      	});
				//文件上传成功处理
				fileupload.on( 'uploadSuccess', function( file ,res) {
		          	$.jBox.closeTip();
		       		if(res.error==0){
		       			var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">" +
		       		            	"<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+res.url
		       		            	+"')\">"+res.fileName+"</a>"+
		       		            	"<span class=\"ss\" onClick=\"removeFile('"+file.id
		       		            	+"')\" style=\"cursor: pointer\">删除</span>"+
		       		                "</div>");
		       	
		       			$('#fileList').html( $li );
		       	 
		       			var newurl=res.url+"||"+res.fileName;
		       			
		       			var $input = $('<input id="input_'+file.id+'" type="hidden" name="url" value="'+newurl+'"/>');
		       			
		       			$('#url').append( $input );
		       		}else{
		       			layer.msg(res.message,{time: 3000});
		       		}
		       	});
				//上传文件错误处理
				fileupload.on('error', function(msg){
		       		if(msg=="Q_TYPE_DENIED"){
		       			layer.msg("文件格式错误，请上传doc,docx,xls,xlsx,ppt,pptx,txt,zip,rar,pdf文件");
		       		}
		       	});
			}
		});
	    // 负责预览图的销毁
	    function removeFile(fileid) {
	    	$("#"+fileid).remove();
	    	$("#input_"+fileid).remove();
	    };

		var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function doSubmit() {
			//序列化json
			var json={};
			$.each($(".serial"),function(index,item){
				var children =item.children;
				for(var i=0;i<children.length;i+=2){
					//获取easyui的值
					json[children[i].innerText.replace("：","")]=$(children[i+1]).find("input.textbox-value").val();
				}
			});
			$("#contjson").val(JSON.stringify(json));
			//判断是第一次添加还是更新操作
			if (parent.Global_Id) {
				//更新操作直接提交
				$("#inputForm").submit();
			} else {
				//添加操作处理
				//表单验证	
				var isValid = $("#inputForm").form('validate');
				if (!isValid)
					return false;
				var obj = $("#inputForm").serializeObject();

				/* if (action == 'update') {
					for ( var index in parent.dgdata) {
						if (parent.dgdata[index].time == parent.time) {
							parent.dgdata.splice(index, 1, Object.assign(obj, {
								"time" : getDate()
							}));
						}
					}
				} else  */
				if (action == 'addfile') {
					//将创建时间加上，方便父页面删除操作
					parent.Table_Data.push(Object.assign(obj,{time:new Date().getTime()}));
				}
				var accordion = parent.$('#accordion');
				accordion.accordion({
					height : 'auto'
				});//调整datagrid高度
				if (!accordion.accordion('getPanel', 0)) {
					//增加panel
					accordion.accordion('add', {
						title : '附件列表',
						content : '<table id="file_dg"></table>',
						selected : true,
					});
				}
				//父页面datagrid加载数据
				parent.Table_Dg = parent.$('#file_dg').datagrid(parent.Datagrid_Setting);
				parent.Table_Dg.datagrid("loadData", parent.Table_Data);
				parent.layer.close(layerindex);//关闭对话框。
			}
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
					parent.d.datagrid('reload');
					parent.layer.close(layerindex);//关闭对话框。
				}
			});

		});
			</script>
</body>
</html>