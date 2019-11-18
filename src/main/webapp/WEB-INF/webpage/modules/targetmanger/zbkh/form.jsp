<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标考核管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/zbkh/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered no-footer" style="margin-bottom: 5px" >
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35"><input id="year" name="year" value="${target.year }" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="readonly:true"></td>
               <td class="width-15 active"><label class="pull-right">考核部门：</label></td>
               <td class="width-35"><input id="ID2" name="ID2" type="text" value="${target.ID2 }" class="easyui-combobox" style="width: 100%;height: 30px;"
                     data-options="readonly:true,panelheight:200,valueField:'id',textField:'text',url: '${ctx}/system/department/deptjson'"></td>
            </tr>

            <tr>
              <td class="width-15 active"><label class="pull-right">目标指标数：</label></td>
              <td class="width-35"><input id="tnum"name="tnum" type="text" value="${target.tnum }" class="easyui-textbox"
                     style="width: 75%;height: 30px;" data-options="readonly:true">
                      <a class='btn btn-info btn-xs' onclick="viewfp()">查看详情</a></td>
              <td class="width-15 active"><label class="pull-right">自评达标数：</label></td>
              <td class="width-35"><input name="zpnum"  type="text" value="${target.zpnum }" class="easyui-textbox"
                     style="width: 75%;height: 30px;" data-options="readonly:true">
                     <a class='btn btn-info btn-xs' onclick="viewzp()">查看详情</a></td>
            </tr>
               </tbody>
      </table>
      <div class="easyui-accordion" id="accordion" border="true" style="border-width:  1px 1px 0 1px">
       		<div title="指标考核" border="false"><table id="target_dg" ></table></div>
	  </div>
      <table class="table table-bordered" style="margin-top: 5px">
            <tr>
              <td class="width-15 active"><label class="pull-right">考核达标数：</label></td>
              <td class="width-35"><input id="khnum" name="khnum" type="text" value="${target.khnum }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="required:true,readonly :true,validType:['number','FUN[ValidateNumber,\'不能大于目标指标数\']']"><a id="dd" href="javascript:void(0)"></a></td>
              <td class="width-15 active"><label class="pull-right">考核结论：</label></td>
              <td class="width-35"><input id="m4" name="m4" type="text" value="${target.m4 }" class="easyui-combobox"
                     style="width: 100%;height: 30px;" data-options="readonly : true ,required:true,panelHeight:'auto',
                     data:[{value:'1',text:'达标'},{value:'0',text:'未达标'}]"></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">考核人：</label></td>
               <td class="width-35"><input id="M2" name="M2" value="${target.m2 }" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,25]'"></td>
               <td class="width-15 active"><label class="pull-right">考核日期：</label></td>
               <td class="width-35" ><input id="M3"name="M3" value="${target.m3 }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">考核情况说明：</label></td>
               <td class="width-35" colspan="3"><input name="M5" type="text" value="${target.m5 }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,250]' "></td>
            </tr>
         </table> 
			 <input type="hidden" name="dbids" value="${target.dbids}" />
			 <input type="hidden" name="bdbids" value="${target.bdbids}" />
            <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="ID1" value="${target.ID1}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
      
   </form>
   <div id="select_fp" style="display:none;height:100%"  >
   <table id="target_fp"></table> 
   </div>
   <div id="select_zp" style="display:none;height:100%"  >
   <table id="target_zp"></table> 
   </div>
   <script type="text/javascript">
   		  var tnum;
   		  var dbids="${target.dbids}";
   		  var bdbids="${target.bdbids}";
   		  var flg1='${target.khnum }';
   		  var flg2='${target.m4 }';
   		  var khdg = $('#target_dg').datagrid({
				method : "post",
				queryParams :{"deptid":'${target.ID2 }',"m1":'${target.year }'},
				url : '${ctx}/target/zbfp/idjson',
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				rownumbers : true,
				nowrap : false,
				scrollbarSize : 0,
				singleSelect : true,
				striped : true,
				columns : [ [  {
					field : 'id',
					title : 'id',
					hidden : true
				},
				{
					field : 'targetname',
					title : '指标名称',
					width : 150,
					align : 'center'
				},{
					field : 'targetval',
					title : '指标值',
					width : 50,
					align : 'center'
				},{
					field : 'result',
					title : '自评结果',
					width : 100,
					align : 'center',formatter : function (value, row){
					 	var f=true;
						if(dbids&&flg2){
							f=new RegExp( "\\b"+row.id+ "\\b").test(dbids);
						} 
						return "<input type='radio' name='db"+row.id+"'  value='1' class='i-checks' "+(f?"checked='checked'":"")+" />达标"+
						       "<input type='radio' name='db"+row.id+"' value='0' class='i-checks' "+(!f?"checked='checked'":"")+"/>不达标";
					}
				}

				] ],
				 onLoadSuccess: function(){
					 //更改考核达标数和考核达标结果
					 var total;
					 //是否第一次添加
					 if(!flg1&&!flg2){
						 total=khdg.datagrid("getData").total
						 $("#khnum").textbox("setValue",total);
						 $("#m4").combobox("setValue",1);
					 }else{
						 total= $("#khnum").textbox("getValue");
					 }
					  $('input').iCheck({
						    radioClass: 'iradio_square-green'
						  }); 
					  $('input').on('ifChecked', function(event){
						  if(event.target.value==1){
							  total++;
						  }else{
							  total--;
						  }
						  $("#khnum").textbox("setValue",total);
						  if(total==khdg.datagrid("getData").total){
							  $("#m4").combobox("setValue",1);
						  }else{
							  $("#m4").combobox("setValue",0);
						  }
						});
				    },
				checkOnSelect : false,
				selectOnCheck : false
			});
   			var  dg=$('#target_zp').datagrid({    
 		    		fitColumns : true,
 		    		striped:true,
 		    		rownumbers:true,
 		    		nowrap:false,
 		    		scrollbarSize:0,
 		    		striped:true,
 		    		});
   			var  d=$('#target_fp').datagrid({    
 		    		fitColumns : true,
 		    		striped:true,
 		    		rownumbers:true,
 		    		nowrap:false,
 		    		scrollbarSize:0,
 		    		striped:true,
 		    		});
            $(function(){
            	if(!'${target.m2 }'){
            		$("#M2").textbox("setValue",'${username}');
            	}
           	    $("#M3").datebox("setValue", new Date().format("yyyy-MM-dd"));
            	tnum=$("#tnum").textbox("getValue");
              });
            function ValidateNumber(val){
            	var tnum=$("#tnum").textbox("getValue");
            	return parseInt(tnum)>=parseInt(val);
            }
            function viewzp(){
            	layer.open({
            	    type: 1,  
            	    title: '信息详情',
            	    area:['700px','400px'],
            	    content: $("#select_zp"),
            	    btn: ['关闭'],
            	    success: function(index,layero){
            	      	dg.datagrid({    
            	    		method: "post",
            	    		url  : ctx+'/target/mbzp/qylist',
            	    		queryParams :{'target_mbzp_year':$("#year").textbox("getValue"),'target_mbzp_bm':$("#ID2").combobox("getValue"),'target_mbzp_db': '1'},
            	    		columns:[[    
    			    	             {field:'year',title:'年度',sortable: false,width:50,align:'center'},    
    			    	              {field:'department',title:'责任部门',sortable: false,width:80,align:'center'},
    			    	              {field:'quarter',title:'季度',sortable: false,width:50,align:'center',
    			    	            	  formatter:function(value){
    			    	        		  if(value) {
    			    	   					  return "第"+value+"季度";
    			    	        		  }
    			    	        		  else return ;
    			    	        	  }},
    			    	              {field:'targetname',title:'指标名称',sortable: false,width:100,align:'center'},    
    			    	              {field:'targetval',title:'指标值',sortable: false,width:80,align:'center'},    
    			    	              {field:'m4',title:'达标情况',sortable: false,width:50,align:'center',
    			    	            	  formatter:function(value,row){
    			    	            		  if(value) {
    			    	       					  return value==1?"达标":"未达标";
    			    	            		  }
    			    	            		  else 
    			    	            			  return "";
    			    	            	  }},    
    			    	              {field:'m1',title:'评定人',sortable: false,width:80,align:'center'},    
    			    	              {field:'m2',title:'评定日期',sortable: false,width:80,align:'center',
    			    	            	  formatter:function(value){
    			    	            		  if(value) return new Date(value).format('yyyy-MM-dd');
    			    	            		  else return ;
    			    	            	  }},      
	    		 		    		]],
	    		 		    onLoadSuccess: function(){dg.datagrid("autoMergeCells",['year','quarter','department']);}
            	    		});
            	    },
            	    cancel: function(index){}
            	}); 
            }
            function viewfp(){
            	layer.open({
            	    type: 1,  
            	    title: '信息详情',
            	    area:['700px','400px'],
            	    content: $("#select_fp"),
            	    btn: ['关闭'],
            	    success: function(index,layero){
            	    	d.datagrid({    
            	    		method: "post",
            	    		url  : ctx+'/target/zbfp/qylist',
            	    		queryParams :{'target_zbfp_m1':$("#year").textbox("getValue"),'target_zbfp_deptid':$("#ID2").combobox("getValue")},
            	   		    columns:[[    
	 					              {field:'m1',title:'年份',sortable: false,width:50,align:'center'},    
	 					              {field:'m3',title:'级别',sortable: false,width:50,align:'center',
	 					            	  formatter:function(value){
	 					        		  if(value==1) return '公司';
	 					        		  if(value==2) return '部门';
	 					        		  if(value==3) return '班组';
	 					        		  else return ;
	 					        	  }},
	 					              {field:'deptname',title:'责任部门',sortable: false,width:50,align:'center'},
	 					              {field:'targetname',title:'指标名称',sortable: false,width:150,align:'center'},    
	 					              {field:'targetval',title:'指标值',sortable: false,width:50,align:'center'},    
	 					              /* {field:'m7',title:'制定人',sortable: false,width:100,align:'center'},    
	 					              {field:'m8',title:'审核人',sortable: false,width:100,align:'center'},    
	 					              {field:'m9',title:'批准人',sortable: false,width:100,align:'center'},   */  
	 					              {field:'m5',title:'批准日期',sortable: false,width:80,align:'center',
	 					            	  formatter:function(value){
	 					            		  if(value) return new Date(value).format('yyyy-MM-dd');
	 					            		  else return ;
	 					            	  }} 
 					   				 ]],
		 		    		  onLoadSuccess: function(){d.datagrid("autoMergeCells",['m1','m3','deptname']);}
            	    		});
            	    },
            	    cancel: function(index){}
            	}); 
            
            }
				//提交处理
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				var validateForm;
				function doSubmit() {
				   var rows=khdg.datagrid("getData").rows;
				   var db=[];
				   var bdb=[];
				   for (var index in rows){
					   if($("input[name='db"+rows[index].id+"']:checked").val()==1){
						   db.push(rows[index].id);
					   }else{
						   bdb.push(rows[index].id);
					   }
				    }
				   $("input[name='dbids']").val(db.join());
				   $("input[name='bdbids']").val(bdb.join());
				   $("#inputForm").serializeObject();
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
			</script>
</body>

</html>