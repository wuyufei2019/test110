<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>指标信息管理</title>
<meta name="decorator" content="default" />
</head>
<body>

   <form id="inputForm" action="${ctx}/target/zbsz/${action}" method="post" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
              <shiro:hasAnyRoles name="admin,superadmin">
               <tr>
                  <td class="width-20 active"><label class="pull-right">企业名称：</label></td>
                  <td class="width-80" colspan="3">
                     <input value="${target.ID1 }" id="_qyid" name="ID1" style="width: 100%;height: 30px;"
                           class="easyui-combobox"  data-options="required:'true',valueField: 'id',
                            <c:if test="${action eq 'update' }">readonly:'true',</c:if>
                           textField: 'text',url:'${ctx}/bis/qyjbxx/idjson'" />
                  </td>
               </tr>
            </shiro:hasAnyRoles>
            <tr>
               <td class="width-20 active"><label class="pull-right">指标名称：</label></td>
               <td class="width-80"><input name="M1" type="text" value="${target.m1 }" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="required:true,validType:'length[0,100]' "></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">指标值：</label></td>
               <td class="width-80"><input name="M2" type="text" value="${target.m2 }" class="easyui-textbox" style="width: 100%;height: 30px;"
                     data-options="required:true,validType:'length[0,50]' "></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">备注：</label></td>
               <td class="width-80"><input name="M3" type="text" value="${target.m3 }" class="easyui-textbox" style="width: 100%;height: 80px;"
                     data-options="multiline:true,validType:'length[0,250]' "></td>
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
      var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
      var validateForm;
      function doSubmit() {
         $("#inputForm").serializeObject();
         $("#inputForm").submit();
      }     
      $(function() {
         var flag=true;
         $('#inputForm').form({
            onSubmit : function() {
               var isValid = $(this).form('validate');
               if(isValid&&flag){
                  flag=false;
                  $.jBox.tip("正在提交，请稍等...",'loading',{opacity:0});
                  return true;
               }
               return false;  // 返回false终止表单提交
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