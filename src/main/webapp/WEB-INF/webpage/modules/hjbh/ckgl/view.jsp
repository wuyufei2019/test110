<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>出库管理查看</title>
<meta name="decorator" content="default" />
</head>
<body>
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物名称：</label></td>
               <td class="width-30"><input style="width: 100%;height: 30px;" class="easyui-combobox" value="${entity.id1 }"
                     data-options="readonly: true,valueField: 'id',textField: 'name',url:'${ctx}/hjbh/wxfwgl/wxfwjson'" /></td>
               <td class="width-20 active"><label class="pull-right">出库时间：</label></td>
               <td class="width-30"><fmt:formatDate value="${entity.outtime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">废物数量(公斤/立方米)：</label></td>
               <td class="width-30">${entity.amount }</td>
               <td class="width-20 active"><label class="pull-right">废物流向：</label></td>
               <td class="width-30">${entity.direction }</td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物运送部门经办人：</label></td>
               <td class="width-30">${entity.ysoperator }</td>
               <td class="width-20 active"><label class="pull-right">废物贮存部门经办人：</label></td>
               <td class="width-30">${entity.ccoperator }</td>
            </tr>
         </tbody>
      </table>

</body>
</html>