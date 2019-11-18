<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标考评管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/mbkp/${action}" met hod="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">安全指标：</label></td>
               <td class="width-35" colspan="3" ><input id="id2" name="ID2" value="${target.ID2 }" class="easyui-combobox"style="width: 100%;height: 30px;"
                     data-options="readonly:true,valueField: 'id',textField: 'text',url:'${ctx}/target/zbfp/idjson'"></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">指标值：</label></td>
               <td class="width-35"><input id="targetval" type="text" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="readonly:true"></td>
               <td class="width-15 active"><label class="pull-right">完成值：</label></td>
               <td class="width-35"><input id="completion" name="completion" type="text" value="${target.completion }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" ></td>
            </tr>
                     
            <tr>
               <td class="width-15 active"><label class="pull-right">年度：</label></td>
               <td class="width-35"><input id="year" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="readonly:true"></td>
               <td class="width-15 active"><label class="pull-right">责任部门：</label></td>
               <td class="width-35"><input id="department" style="width: 100%;height: 30px;"class="easyui-textbox" data-options="readonly:true"></td>
            </tr>

            <tr>
               <td class="width-15 active"><label class="pull-right">达标情况：</label></td>
               <td class="width-35" ><input name="M4" type="text" value="${target.m4 }" class="easyui-combobox"
                     style="width: 100%;height: 30px;" data-options="editable: false,required:true,panelHeight:'auto',data:[{value:'1',text:'达标'},{value:'0',text:'未达标'}]"></td>
               <td class="width-15 active"><label class="pull-right">季度：</label></td>
               <td class="width-35" ><input id="quarter" name="quarter" type="text" class="easyui-combobox" value="${target.quarter }" style="height: 30px;width:100%;"
                      data-options="readonly:true,panelHeight:'auto',data:[{value:'1',text:'第一季度'},{value:'2',text:'第二季度'},{value:'3',text:'第三季度'},{value:'4',text:'第四季度'}] "></td>
           
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">评定人：</label></td>
               <td class="width-35" ><input id="M1" name="M1" value="${target.m1 }" class="easyui-textbox"style="width: 100%;height: 30px;"
                     data-options="validType:'length[0,10]'"></td>
               <td class="width-15 active"><label class="pull-right">评定日期：</label></td>
               <td class="width-35" ><input id="pddate"name="M2" value="${target.m2 }" class="easyui-datebox" style="width: 100%;height: 30px;"/></td>
            </tr>
            

            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3"><input name="M3" type="text" value="${target.m3 }" class="easyui-textbox"
                     style="width: 100%;height: 30px;" data-options="multiline:true,validType:'length[0,250]' "></td>
            </tr>
            <c:if test="${not empty target.ID}">
               <input type="hidden" name="ID" value="${target.ID}" />
               <input type="hidden" name="S1" value="<fmt:formatDate value="${target.s1}" pattern="yyyy-MM-dd HH:mm:ss"  />" />
               <input type="hidden" name="S3" value="${target.s3}" />
            </c:if>
         </tbody>
      </table>
   </form>
   <script type="text/javascript">
         $(function(){
        	 if('${action}'=='create'){
        		 $("#M1").textbox("setValue",'${username}');
        	 }
      	  $("#pddate").datebox("setValue", new Date().format("yyyy-MM-dd"));
      	  $("#id2").combobox({
				loader:function (param, success, error) {
				    var id2=$('#id2').combobox('getValue');
				    if(id2){
						 $.post('${ctx}/target/zbfp/idjson',{"id":id2},function(rec){
							var data=eval(rec)[0];
							$("#year").textbox("setValue",data.year);
							$("#department").textbox("setValue",data.department);
							$("#targetval").textbox("setValue",data.targetval);
						 });
				    }
				 }
			});
         });
		
		//提交处理
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		var validateForm;
		function doSubmit() {
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