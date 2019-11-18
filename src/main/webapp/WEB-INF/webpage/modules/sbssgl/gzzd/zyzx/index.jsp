<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/webpage/include/taglib.jsp"%> 
<html>
<head>
<title>资源中心</title>
<meta name="decorator" content="default"/>
<script type="text/javascript" src="${ctx}/static/model/js/sbssgl/gzzd/zyzx/index.js?v=1.0"></script>
<script type="text/javascript" src="${ctx}/static/model/js/aqpx/zxxx/jquery.media.js"></script>
<script type="text/javascript" src="${ctxStatic}/flexpaper/flexpaper.js"></script>
<style type="text/css">
	.up{border-radius: 5px;background: rgb(72, 195, 203);padding: 6.5px 15px;color: white; font-weight: bolder;font-size: 12px;margin: 0px 6px 0 30px;}
	.up:hover{cursor: pointer;border-radius: 5px;border:1px solid rgb(72, 195, 203) ;background: rgb(24, 166, 137);padding: 5.5px 14px;color: white; font-weight: bolder;font-size: 12px;margin: 0px 6px 0 30px; transition: all .5s;}
	.uploadArea{width: 100%;display: flex;align-items: center;margin:20px 0 0 0 }
	.upload{width:32px;height: 32px;margin: 0px 6px 0 30px; background: url('${ctx}/static/model/images/aqpx/zxxx/upload.png') no-repeat center;background-size: 100% 100%;}
	.upload:hover{cursor: pointer;width:32px;height: 32px;margin: 0px 6px 0 30px; background: url('${ctx}/static/model/images/aqpx/zxxx/upload-fill.png') no-repeat center;background-size: 100% 100%;}
	.uname{padding:3px 5px 9px 0;font-size: 13px;font-weight: 450;line-height: 24px}
	.line{overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:1;-webkit-box-orient:vertical;}
	.line2{overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;}
	.fs{flex-shrink:0}
	.ic{width:18px;height: 18px;margin: 0px 0 0 6px;margin-top: -2px}
    .file:hover .ic{width:18px;height: 18px;margin: 0px 0 0 6px; background: url('${ctx}/static/model/images/sbssgl/zyzx/lod.png') no-repeat center;background-size: 100% 100%;}
	.ic2{width:18px;height: 18px;margin: 0px 0 0 6px;margin-top: -2px}
	.file:hover .ic2{width:18px;height: 18px;margin: 0px 0 0 6px; background: url('${ctx}/static/model/images/sbssgl/zyzx/del(1).png') no-repeat center;background-size: 100% 100%;}
	.lab{font-size: 12px; color: grey;}
	.fileBg{width:100%; height: 110px;display: flex;align-items: center;justify-content: center;}
	.file {
		width:160px;display:flex;flex-direction:column;margin: 13px 13px;
		border: none;
		box-shadow: 0px 0px 8.82px 0.18px #ccc;
	}
	.file:hover {
		margin-top:5px;
		width: 164px;
		cursor: pointer;
		/*border: 2px solid #30a6f5;
		-moz-box-shadow: 0 0 10px rgba(48, 166, 245, 1);
		-webkit-box-shadow: 0 0 10px rgba(48, 166, 245, 1);*/
		box-shadow: 0px 5px 12px 0 #B8B8B8;
	}
	.file:hover .fileBg {width:100%; height: 118px;}
	.fileName{padding:3px 15px;font-size: 14px;font-weight: bold;color: white;}
	.fileInfo{display: flex;flex-direction:column;padding:5px 8px 7px;}

	.files {
		width: 100%;
        display: flex;
        flex-wrap: wrap;
		/*height: 750px;*/
		margin: 15px 0 0 15px;
	}

.syiconbox h5 {
	font-size: 16px;
	margin: 16px 10px;
	padding: 15px;
	border-left: #1e87f2 5px solid;
}
</style>
</head>

<script>
    var winHeight = $(window).height();
    $(".files").css("height",winHeight);
    $(window).resize(function() {
        alert('aaaa');
        winHeight = $(window).height();
        $("#files").css("height",winHeight);
    });
</script>
<body>
	<div class="uploadArea">
		<!--<div class="upload"></div><span>上传文件</span>-->
		<shiro:hasPermission name="sbssgl:zyzx:upload">
			<div class="up" onclick="add()"><i class="fa fa-plus"></i> 上传文件</div>
		</shiro:hasPermission>
		<form id="searchFrom" action="${ctx}/sbssgl/zyzx/index" style="height: 30px;margin-left: 5px" class="form-inline">
			<div class="form-group">
				<input type="text" id="m1" name="m1" class="easyui-textbox" style="height: 30px;" data-options="prompt: '标题'" />
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</span>
				<span  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 全部</span>
			</div>
		</form>
	</div>

<div class="files">
    <c:forEach items="${list}" var="fj" varStatus="status">
		<div class="file">
			<div class='fileBg' style=" background: url('${ctx}/static/model/images/sbssgl/zyzx/${status.index%6+1}.png') no-repeat center;background-size: 100% 100%;">
				<span class="fileName line2" >${fj.m1}</span>
			</div>
	
			<div class="fileInfo" >
				<div style="display: flex;justify-content: space-between;">
					<div class="line uname" style="line-height: 24px">上传人：${fj.scrname}</div>
				</div>
				<div style="display: flex;justify-content: space-between;">
					<div class="lab"><fmt:formatDate value="${fj.m2}" pattern="yyyy / MM / dd"/></div>
					<div style="display: flex">
                        <div class="ic fs" onclick="downloadfj('${fj.m3}');"></div>
                        <div class="ic2 fs" style="margin-left: 10px" onclick="del(${fj.id});"></div>
                    </div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

</body>


</html>