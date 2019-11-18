<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>入库管理查看</title>
<meta name="decorator" content="default" />
</head>
<body>
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物名称：</label></td>
               <td class="width-30"><input style="width: 100%;height: 30px;" class="easyui-combobox" value="${entity.id1 }"
                     data-options="readonly: true,valueField: 'id',textField: 'name',url:'${ctx}/hjbh/wxfwgl/wxfwjson'" /></td>
               <td class="width-20 active"><label class="pull-right">入库时间：</label></td>
               <td class="width-30"><fmt:formatDate value="${entity.intime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物来源：</label></td>
               <td class="width-30" colspan="3">${entity.resource }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">废物数量(公斤/立方米)：</label></td>
               <td class="width-30">${entity.amount }</td>
               <td class="width-20 active"><label class="pull-right">容器数量：</label></td>
               <td class="width-30">${entity.containeramount }</td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">容器材质：</label></td>
               <td class="width-30">${entity.containermaterial }</td>
               <td class="width-20 active"><label class="pull-right">容器体积：</label></td>
               <td class="width-30">${entity.containervolume }</td>
            </tr>
            <tr>
               <td class="width-20 active"><label class="pull-right">废物存放位置：</label></td>
               <td class="width-30" colspan="3">${entity.location }</td>
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