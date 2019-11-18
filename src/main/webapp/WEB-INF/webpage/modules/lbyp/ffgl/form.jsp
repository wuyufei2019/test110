<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标自评管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
         
            <tr>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35" colspan="3">
               <input id="department" style="width: 100%;height: 30px;" class="easyui-combobox" 
                data-options="required:true,editable : false ,panelHeight:100"></td>
            </tr>
            
               <tr>
               <td class="width-15 active"><label class="pull-right">领取日期：</label></td>
               <td class="width-35"><input name="time" id="time" class="easyui-datetimebox" "
                  style="width: 100%;height: 30px;" data-options="editable:false,required:'true'" /></td>
               <td class="width-15 active"><label class="pull-right">领取人姓名：</label></td>
               <td class="width-35"><input id="receiveperson" class="easyui-textbox""
                  style="width: 100%;height: 30px;"
                  data-options="prompt: '填写将覆盖未修改的默认领取人'" /></td>
            </tr>

         </tbody>
      </table>
   </form>
         <div class="easyui-accordion" id="accordion" border="false">
         </div>
    <script type="text/javascript">
   var dg;
   var receiveperson;
   var dgdata;//记录datagrid的的加载数据
   $(function(){
	   
	   
		$("#time").datetimebox("setValue",new Date().format("yyyy-MM-dd hh:mm:ss"));
		$("#department").combobox({
			valueField:'id',
			textField:'text',
			url: '${ctx}/system/department/deptjson',
			onSelect : function(rec) {
				deal(rec.id);
			},
			onLoadSuccess : function(){
				deal();
			},
			 loadFilter : function(data){
				 return [{id:'',text:"全部"}].concat(data);
			}
		});
		
		
		$("#receiveperson").textbox({
			onChange : function(newValue,oldValue) {
				// receiveperson=newValue;
				 dg.datagrid('endEdit', editIndex);
				 var rows=dg.datagrid('getData').rows;
				 $.each(rows, function(i,row){ 
					 if(!row.flg)
						row.receiveperson=newValue;
				 });
				 dg.datagrid('loadData',rows);
			},
		});
	});
   
	 function deal(id){
			$('#accordion').accordion({height : 'auto'});//调整datagrid高度
			if($('#accordion').accordion('getPanel',0))
				$('#accordion').accordion('remove',0);
			$('#accordion').accordion('add', {
				title: '待领取用品',
				content: '<table id="lbyp_dg"></table>',
				selected: true,
			});
			   dg = $('#lbyp_dg').datagrid({
				method : "post",
				queryParams : {"expiration":1,"deptid":id},
				url : '${ctx}/lbyp/ffgl/list',
				fitColumns : true,
				border : false,
				idField : 'eid',
				striped : true,
				rownumbers : true,
				nowrap : false,
				scrollbarSize : 0,
				singleSelect : true,
				striped : true,
				columns : [ [   
		                {field:'eid',title:'eid',checkbox :true,sortable:false,width:50},    
		                {field:'jobtype',title:'岗位/工种名称',sortable:false,width:50},
		                {field:'ename',title:'员工姓名',sortable:false,width:50},
		                {field:'goodsname',title:'用品名称',sortable:false,width:40},
		                {field:'lasttime',title:'最近领取日期',sortable:false,width:40, formatter : function (value, row){
		                	if(value) return new Date(value).format("yyyy-MM-dd");
		                	else return '';
		                }},    
		                {field:'nexttime',title:'下次领取日期',sortable:false,width:40, formatter : function (value, row){
		                	var d = getNextDate(row);
		                	if(d) return d;
		                }, styler: function(value, row, index){
		                	var d = getNextDate(row);
		            		var now=new Date().format("yyyy-MM-dd");
		        			if(now>=d){
		            			return 'background-color:rgb(249, 156, 140);';
		            		}
		            	}},
		            	 {field:'caozuo',title:'操作', sortable:false, width:30,align :'center',
		            		formatter : function (value, row, index){
		         			return "<a class=' fa fa-times-circle btn btn-danger btn-xs' onclick='remove("+index+")'>移除</a>";
		                 }},
		                 {field:'receiveperson',title:'领取人', sortable:false, width:30,editor:'text',
		                	 formatter : function (value, row){
		                		 if(!value){
		                			 row.receiveperson=row.ename;
		                		 }
		                		 return row.receiveperson;
		                 }
		                 }
				] ],
				 onClickCell: function(index){  
					 clickfun(index);
		    	    } ,
			     onDblClickRow: function (index){
			    	 clickfun(index);
			    },
				 onLoadSuccess: function(){
					    dgdata = dg.datagrid('getData').rows;
					    //editIndex=0;
						//dg.datagrid('beginEdit', 0);
						//$("#datagrid-row-r1-2-0").find("td[field='receiveperson']").find("input[type='text']").focus();
						 $('#receiveperson').next('span').find('input').focus(function(){
							 endEditing();
							});
				    },
				checkOnSelect : false,
				selectOnCheck : false
			});
	 }
	 
	 function clickfun(index){
		  var row = dg.datagrid('getData').rows[index];
			if (endEditing()){
				editIndex=index;
				dg.datagrid('beginEdit', index);
			}
	 }
	 function remove(index){
		 dgdata.splice(index,1);
		 dg.datagrid('loadData',dgdata);
	 }
	 var editIndex = undefined;
	 function endEditing(){
	 	if (editIndex == undefined){return true}
	 	if (dg.datagrid('validateRow', editIndex)){
	 		dg.datagrid('endEdit', editIndex);
	 		//编辑过的行为1
	 		dg.datagrid("getData").rows[editIndex].flg=true;
	 		return true;
	 	} else {
	 		return false;
	 	}
	 }
	 function getNextDate(row){
			if(row.lasttime) {
				var d= new Date(row.lasttime);
				if(row.cycleyear)
					d.setFullYear(d.getFullYear()+parseInt(row.cycleyear));
				if(row.cyclemonth)
					d.setMonth(d.getMonth()+parseInt(row.cyclemonth));
				return d.format("yyyy-MM-dd");
			}
			else return null;
		}
   
		//提交处理
		var layerindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function exportbd() {
			var time=$("#time").datetimebox("getValue");
			var rows=dg.datagrid("getData").rows;
			var list=[];
		    for (var index in rows){
		    	var row=rows[index];
		    	var entity={"jobtype": row.jobtype ,"ename" : row.ename ,"goodsname" : row.goodsname ,"amount" : row.amount,"specifications":row.specifications};
		    	list.push(entity);
		    }
		    $.ajax({  
                type : 'POST',  
                url : "${ctx}/lbyp/ffgl/exportbd",  
                dataType:"text", 
                contentType: "application/json",  
                data: JSON.stringify(list),  
                success : function(data) {
                	window.open('${ctx}/download/' + data);
                }
            }); 
		}
		function doSubmit() {
			var time=$("#time").datetimebox("getValue");
			var rows=dg.datagrid("getData").rows;
			var list=[];
		    for (var index in rows){
		    	var row=rows[index];
		    	var entity={"id1": row.eid ,"goodsname" : row.goodsname ,"amount" : row.amount,"receiveperson":row.receiveperson,"time":new Date(time.replace(/-/,"/"))};
		    	list.push(entity);
		    }
		    $.ajax({  
                type : 'POST',  
                url : "${ctx}/lbyp/ffgl/createall",  
                dataType:"text", 
                contentType: "application/json",  
                data: JSON.stringify(list),  
                success : function(data) {
                	$.jBox.closeTip();
					if (data == 'success'){
						parent.layer.open({
							icon : 1,
							title : '提示',
							offset : 'rb',
							content : '操作成功！',
							shade : 0,
							time : 2000
						});
					}
					else
						layer.open({
							icon : 2,
							title : '提示',
							offset : 'rb',
							content : '操作失败！',
							shade : 0,
							time : 2000
						});
						parent.dg.datagrid('reload');
						parent.layer.close(layerindex);//关闭对话框。
					
                },
                beforeSend : function(){
                	var isValid = $("#inputForm").form('validate');
					if (isValid) {
						$.jBox.tip("正在提交，请稍等...", 'loading', {
							opacity : 0
						});
						return true;
					}
					return false; // 返回false终止表单提交
                }
            });  
		}
	
			</script>


</body>
</html>