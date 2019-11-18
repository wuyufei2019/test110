<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>化学安全技术说明书</title>
<style type="text/css">
	#xbf {
		background-color: #E7E8C6
	}
	.text {
		background-color: #D9E2F3
	}
	
</style>
</head>
<body>
		<div style="width: 1100px;margin: 0 auto;">
  	<div style=" text-align:center;font-weight:bold">化学安全技术说明书</div>
  	<div>
  		<table border="1" width="100%" cellspacing="3" cellpadding="1" bordercolor="#E1ECF7" >
    		<tr>
    	<td id="xbf" colspan="2" align="center">第一部分：化学品名称</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">化学品中文名称：</td>
    	<td style=" width:77%;">${res.m1 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">化学品英文名称：</td>
    	<td style=" width:77%;">${res.m2 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">中文名称2：</td>
    	<td style=" width:77%;">${res.m3 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">英文名称2：</td>
    	<td style=" width:77%;">${res.m4 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">技术说明书编码：</td>
    	<td style=" width:77%;">${res.m5 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">CAS No：</td>
    	<td style=" width:77%;">${res.m6 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">分子式：</td>
    	<td style=" width:77%;">${res.m7 }</td>
    </tr>
    <tr>
    	<td class="text" style=" width:23%;text-align:right">分子量：</td>
    	<td style=" width:77%;">${res.m8 }</td>
    </tr>
    	</table>
  	</div>
  	<div style="height:10px"></div>
  	<div>
  		<div id="xbf" class="text" style=" text-align:center">第二部分：成分/组成信息</div>
  		<table bordercolor="#E1ECF7" cellspacing="3" cellpadding="1" border="1" align="center" style="width:90%;">
  			<tr>
  				<td class="text1" style=" width:23%;height:20px;text-align:right" rowspan=2>有害物质&nbsp;&nbsp;含量&nbsp;&nbsp;CAS&nbsp;No：</td>
  				<td align="center">有害成分</td>
  				<td align="center">含量</td>
  				<td align="center">CAS No</td>
  			</tr>
  			<tr>
  				<td height="40" colspan="3">${res.m9 }</td>
  			</tr>
  		</table>
  	</div>
  	<div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第三部分：危险性描述</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">健康危害：</td>
    			<td style=" width:77%;">${res.m10 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">环境危害：</td>
    			<td style=" width:77%;">${res.m11 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">燃爆危险：</td>
    			<td style=" width:77%;">${res.m12 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第四部分：急救措施</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">皮肤接触：</td>
    			<td style=" width:77%;">${res.m13 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">眼睛接触：</td>
    			<td style=" width:77%;">${res.m14 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">吸入：</td>
    			<td style=" width:77%;">${res.m15 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">食入：</td>
    			<td style=" width:77%;">${res.m16 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第五部分：消防措施</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">危险特性：</td>
    			<td style=" width:77%;">${res.m17 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">有害燃烧产物：</td>
    			<td style=" width:77%;">${res.m18 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">灭火方法：</td>
    			<td style=" width:77%;">${res.m19 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第六部分：泄漏应急处理</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">应急处理：</td>
    			<td style=" width:77%;">${res.m20 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第七部分：操作处理与储存</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">操作注意事项：</td>
    			<td style=" width:77%;">${res.m21 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">储存注意事项：</td>
    			<td style=" width:77%;">${res.m22 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第八部分：接触控制/个体防护</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">中国MAC（mg/m3）：</td>
    			<td style=" width:77%;">${res.m23 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">前苏联MAC（mg/m3）：</td>
    			<td style=" width:77%;">${res.m24 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">TLVTN：</td>
    			<td style=" width:77%;">${res.m25 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">TLVWN：</td>
    			<td style=" width:77%;">${res.m26 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">工程控制：</td>
    			<td style=" width:77%;">${res.m27 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">呼吸系统防护：</td>
    			<td style=" width:77%;">${res.m28 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">眼睛防护：</td>
    			<td style=" width:77%;">${res.m29 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">身体防护：</td>
    			<td style=" width:77%;">${res.m30 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">手防护：</td>
    			<td style=" width:77%;">${res.m31 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">其他防护：</td>
    			<td style=" width:77%;">${res.m32 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第九部分：理化特性</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">主要成分：</td>
    			<td style=" width:77%;">${res.m33 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">外观与形状：</td>
    			<td style=" width:77%;">${res.m34 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">熔点(℃)：</td>
    			<td style=" width:77%;">${res.m35 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">沸点(℃)：</td>
    			<td style=" width:77%;">${res.m36 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">相对密度(水=1)：</td>
    			<td style=" width:77%;">${res.m37 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">相对蒸气密度(空气=1)：</td>
    			<td style=" width:77%;">${res.m38 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">饱和蒸气压(kPa)：</td>
    			<td style=" width:77%;">${res.m39 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">燃烧热(kJ/mol)：</td>
    			<td style=" width:77%;">${res.m40 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">临界温度(℃)：</td>
    			<td style=" width:77%;">${res.m41 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">临界压力(MPa)：</td>
    			<td style=" width:77%;">${res.m42 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">辛醇/水分配系数的对数值：</td>
    			<td style=" width:77%;">${res.m43 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">闪点(℃)：</td>
    			<td style=" width:77%;">${res.m44 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">引燃温度(℃)：</td>
    			<td style=" width:77%;">${res.m45 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">爆炸上限%(V/V)：</td>
    			<td style=" width:77%;">${res.m46 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">爆炸下限%(V/V)：</td>
    			<td style=" width:67%;">${res.m47 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">主要用途：</td>
    			<td style=" width:77%;">${res.m48 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十部分：稳定性和反应活性</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">禁配物：</td>
    			<td style=" width:77%;">${res.m49 }</td>
    		</tr>
    		
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十一部分：毒理学资料</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">急性毒性LD50：</td>
    			<td style=" width:77%;">${res.m50 }</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">急性毒性LC50：</td>
    			<td style=" width:77%;">${res.m51 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十二部分：生态学资料</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">其他有害作用：</td>
    			<td style=" width:77%;">${res.m52 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十三部分：废弃处置</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">废弃物性质废弃处置方法：</td>
    			<td style=" width:77%;">${res.m53 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十四部分：运输信息</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">危险货物编号：</td>
    			<td style=" width:77%;">${res.m54 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">UN编号：</td>
    			<td style=" width:77%;">${res.m55 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">包装类别：</td>
    			<td style=" width:77%;">${res.m56 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">包装方法：</td>
    			<td style=" width:77%;">${res.m57 }</td>
    		</tr>
    		<tr>
    			<td class="text" style=" width:23%;text-align:right">运输注意事项：</td>
    			<td style=" width:77%;">${res.m58 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    <div>
    	<table bordercolor="#E1ECF7" border="1" width="100%" cellspacing="3" cellpadding="1">
  			<tr>
    			<td id="xbf" colspan="2" align="center">第十五部分：法规信息</td>
    		</tr>
   			<tr>
    			<td class="text" style=" width:23%;text-align:right">法规信息：</td>
    			<td style=" width:77%;">${res.m59 }</td>
    		</tr>
    		
  		</table>
    </div>
    <div style="height:10px"></div>
    
  </div>

</body>
</html>