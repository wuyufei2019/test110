<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>劳保用品仓库管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	
</script>
</head>
<body>
   <form id="inputForm" class="form-horizontal">
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-15 active"><label class="pull-right">用品名称：</label></td>
               <td class="width-35" colspan="3"><input name="ID1" id="ID1" style="width: 100%;" class="easyui-combobox" value="${entity.ID1 }"
                     data-options="readonly:true ,valueField: 'id',textField: 'text',url:'${ctx}/lbyp/wpxx/idjson'" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">采购时间：</label></td>
               <td class="width-35"><fmt:formatDate value="${entity.buytime}" pattern="yyyy-MM-dd" /></td>
            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">采购单价：</label></td>
               <td class="width-35">${entity.price }元</td>
               <td class="width-15 active"><label class="pull-right">采购数量：</label></td>
               <td class="width-35">${entity.amount }</td>
            </tr>

            </tr>
            <tr>
               <td class="width-15 active"><label class="pull-right">备注：</label></td>
               <td class="width-35" colspan="3">${entity.note }</td>
            </tr>
         </tbody>
      </table>
   </form>
</body>
</html>