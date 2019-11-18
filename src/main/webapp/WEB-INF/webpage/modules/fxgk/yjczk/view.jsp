<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%>
<html>
<head>
<title>应急处置卡</title>
<meta name="decorator" content="default" />
</head>
<body>
      <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
         <tbody>
            <tr>
               <td class="width-20 active"><label class="pull-right">岗位名称：</label></td>
               <td class="width-30" colspan="3">${entity.m1 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">生产安全事故处置程序及职责：</label></td>
               <td class="width-30" colspan="3" style="height: 100px;">${entity.m2 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">注意事项：</label></td>
               <td class="width-30" colspan="3" style="height: 100px;">${entity.m3 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">本企业救援队联系方式：</label></td>
               <td class="width-30">${entity.m4 }</td>

               <td class="width-20 active"><label class="pull-right">应急负责人联系方式：</label></td>
               <td class="width-30">${entity.m5 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">控制室联系方式：</label></td>
               <td class="width-30">${entity.m6 }</td>

               <td class="width-20 active"><label class="pull-right">工厂总经理联系方式：</label></td>
               <td class="width-30">${entity.m7 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">班长联系方式：</label></td>
               <td class="width-30">${entity.m8 }</td>

               <td class="width-20 active"><label class="pull-right">当地应急响应中心联系方式：</label></td>
               <td class="width-30">${entity.m9 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">当地安监部门联系方式：</label></td>
               <td class="width-30">${entity.m10 }</td>

               <td class="width-20 active"><label class="pull-right">当地环保部门联系方式：</label></td>
               <td class="width-30">${entity.m11 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">社会救援队联系方式：</label></td>
               <td class="width-30">${entity.m12 }</td>

               <td class="width-20 active"><label class="pull-right">友邻单位名称：</label></td>
               <td class="width-30">${entity.m13 }</td>
            </tr>

            <tr>
               <td class="width-20 active"><label class="pull-right">友邻单位联系方式：</label></td>
               <td class="width-30">${entity.m14 }</td>
            </tr>
         </tbody>
      </table>

</body>
</html>