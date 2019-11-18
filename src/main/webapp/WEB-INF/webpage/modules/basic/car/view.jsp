<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>车辆信息</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
               
              <tr>
              	  <td class="width-15 active"><label class="pull-right">牵引车车牌号码：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m3}</td>
   				  <td class="width-15 active"><label class="pull-right">挂车车牌号码：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m13}</td>
   
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">车型：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m1}</td>
                  <td class="width-15 active"><label class="pull-right">车辆吨位：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m4}</td>
              </tr> 
              <tr>
              	  <td class="width-15 active"><label class="pull-right">车辆品牌：</label></td>
   				  <td class="width-35" >${entity.m2}</td>
                  <td class="width-15 active"><label class="pull-right">保险公司：</label></td>
   				  <td class="width-35"  colspan="3" style="height: 30px;">${entity.m12}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">最大核载人数：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m5}</td>
              	  <td class="width-15 active"><label class="pull-right">车高(米)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m6}</td>
              </tr>
              <tr>
                  <td class="width-15 active"><label class="pull-right">车长(米)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m7}</td>
                  <td class="width-15 active"><label class="pull-right">车宽(米)：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m8}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">车辆负责人：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m9}</td>
               
                  <td class="width-15 active"><label class="pull-right">联系电话：</label></td>
   				  <td class="width-35" style="height: 30px;">${entity.m10}</td>
              </tr> 
              <tr>
                  <td class="width-15 active"><label class="pull-right">备注：</label></td>
   				  <td class="width-35"  colspan="3" style="height: 80px;">${entity.m11}</td>
              </tr> 
		</tbody>
	</table>

</body>
</html>