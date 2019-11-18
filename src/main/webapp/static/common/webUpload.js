(function ($, window) {
    var applicationPath = window.applicationPath === "" ? "" : window.applicationPath || "..";

    function SuiJiNum() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }
    //webupload支持性判断
    function supportUpload(){
        if (!WebUploader.Uploader.support()) {
            var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器!";
            layer.msg(error);
            return;
        }
    }
    //初始化webupload item 容器 options 自定义参数 defaults 默认参数
    function initUpload(item, options,defaults) {
        var opts = $.extend(defaults, options);
        var target = $(item);//容器
        var pickerid = "";
        if (typeof guidGenerator36 != 'undefined')//给一个唯一ID
            pickerid = guidGenerator36();
        else
            pickerid = (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        var uploaderStrdiv = '<div id="fileList"  class="uploader-list"></div>' +
            '<div id="hiddenfield"></div>'+
            '<div id="filePicker'+pickerid+'">选择文件</div>';
        target.append(uploaderStrdiv);
        var webuploaderoptions =$.extend({
            auto: true,
            // swf文件路径
            swf: applicationPath + '/webuploader-0.1.5/Uploader.swf',
            // 文件接收服务端。
            server:   ctx+'/kindeditor/upload?dir='+opts.filetype,
            fileSingleSizeLimit: undefined,//验证单个文件大小是否超出限制, 超出则不允许加入队列}
            PostbackHold: false,   //超出不允许加入队列
            duplicate: true,
            pick: {
                id: '#filePicker' + pickerid,
                multiple: false,
            }
        },opts.uploadopts);
        var  uploader = WebUploader.create(webuploaderoptions);
        return uploader;
    }
    //执行文件上传
    function initWebUpload(item, options) {
        supportUpload();
        //创建默认参数
        var defaults = {
            hiddenInputId: "uploadifyHiddenInputId", // input hidden id
            filetype: "file",//默认文件类型file
            existurl: undefined,//已存在的url（修改时用）
            fileNumLimit:undefined,//文件总数
            uploadopts:{//webupload上传控件预留参数
            }
        };
        var uploader=initUpload(item, options,defaults);
        var target = $(item);//容器
        var $list = target.find('.uploader-list'),
            $hidefield=target.find('#hiddenfield');
        //如果已存在url
        if(options.existurl){
            var fjUrl=options.existurl;
            if(fjUrl){
                var arry =fjUrl.split(",");
                for(var i=0;i<arry.length;i++){
                    var arry2 =arry[i].split("||");
                    var id="ws_fj_"+i;
                    var $li = $(
                        "<div id=\"" + id + "\" style=\"margin-bottom: 10px;\">" +
                        "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"+arry2[0]+"')\">"+arry2[1]+"</a>"+
                        "<span class=\"ss\" id="+id+" style=\"cursor: pointer\">删除</span>"+
                        "</div>"
                    );
                    $list.append($li);
                    var $input = $('<input id="input_' + id + '" type="hidden" name="'+options.hiddenInputId+'" value="' +arry[i]+ '"/>');
                    $hidefield.append($input);
                }
            }
        }

            uploader.on('uploadProgress', function () {
                $.jBox.tip("正在上传，请稍等...", 'loading', {opacity: 0});
            });
            //上传成功
            uploader.on('uploadSuccess', function (file, backinfo) {
                $.jBox.closeTip();
                if (backinfo.error == 0) {
                    var $li = $("<div id=\"" + file.id + "\" style=\"margin-bottom: 10px;\">"
                        + "<a style=\"color:#337ab7;text-decoration:none;cursor:pointer;margin-right:20px;\" onclick=\"window.open('"
                        + backinfo.url
                        + "')\">"
                        + backinfo.fileName
                        + "</a>"
                        + "<span class=\"ss\" id="
                        + file.id
                        + "style=\"cursor: pointer\">删除</span>"
                        + "</div>");
                    var newurl = backinfo.url + "||" + backinfo.fileName;
                    var $input = $('<input id="input_' + file.id + '" type="hidden" name="'+options.hiddenInputId+'" value="' + newurl + '"/>');
                    if(options.fileNumLimit && options.fileNumLimit==1){
                        $list.html($li);
                        $hidefield.html($input);
                    }else{
                        $list.append($li);
                        $hidefield.append($input);
                    }
                } else {
                    layer.msg(backinfo.message, {
                        time: 3000
                    });
                }
            });

            uploader.on('uploadError', function (file) {
                layer.msg('上传出错');
            });

            //删除
            $list.on("click", ".ss", function () {
                var $ele = $(this);
                var id = $ele.parent().attr("id");
                $("#" + id).remove();
                $("#input_" + id).remove();
            });
    }
    //执行图片上传
    function initWebUploadImage(item, options) {
        supportUpload();
        //创建默认参数
        var defaults = {
            hiddenInputId: "uploadifyHiddenInputId", // input hidden id
            filetype: "image",//默认文件类型image
            existurl: undefined,//已存在的url（修改时用）
            fileNumLimit:undefined,//文件总数
            uploadopts:{//webupload上传控件预留参数
            }
        };
        var uploader=initUpload(item, options,defaults);
        var target = $(item);//容器
        var $list = target.find('.uploader-list'),
            $hidefield=target.find('#hiddenfield');
        if(options.existurl){
            var fjUrl=options.existurl;
            if(fjUrl){
                var arry =fjUrl.split(",");
                for(var i=0;i<arry.length;i++){
                    var arry2 =arry[i].split("||");
                    var id="ws_fj_"+i;
                    var $li = $(
                        "<div id=\"" + id + "\" class=\"file-item thumbnail\">" +
                        "<span class=\"cancel\" id=\""+id+"\" style=\"cursor: pointer\">删除</span>"+
                        "<img src=\""+arry2[0]+"\" style=\"width:100px; height: 100px\">" +
                        "<div class=\"info\">" + arry2[1] + "</div>" +
                        "</div>"
                    );
                    $list.append($li);
                    var $input = $('<input id="input_' + id + '" type="hidden" name="'+options.hiddenInputId+'" value="' +arry[i]+ '"/>');
                    $hidefield.append($input);
                }
            }
        }

            uploader.on('uploadProgress', function () {
                $.jBox.tip("正在上传，请稍等...", 'loading', {opacity: 0});
            });
            uploader.on('uploadSuccess', function (file, backinfo) {
                $.jBox.closeTip();
                if(backinfo.error==0){
                    var $li = $(
                        "<div id=\"" + file.id + "\" class=\"file-item thumbnail\">" +
                        "<span class=\"cancel\" id="+file.id+" style=\"cursor: pointer\">删除</span>"+
                        "<img>" +
                        "<div class=\"info\">" + file.name + "</div>" +
                        "</div>"
                        ),
                    $img = $li.find('img');
                    var newurl = backinfo.url + "||" + backinfo.fileName;
                    var $input = $('<input id="input_' + file.id + '" type="hidden" name="'+options.hiddenInputId+'" value="' + newurl + '"/>');

                    if(options.fileNumLimit && options.fileNumLimit==1){
                        $list.html($li);
                        $hidefield.html($input);
                    }else{
                        $list.append($li);
                        $hidefield.append($input);
                    }
                    // 创建缩略图
                    uploader.makeThumb( file, function( error, src ) {
                        if ( error ) {
                            $img.replaceWith('<span>不能预览</span>');
                            return;
                        }

                        $img.attr( 'src', src );
                    }, 100, 100 );

                }else {
                    layer.msg(backinfo.message, {
                        time: 3000
                    });
                }
            });

            uploader.on('uploadError', function (file) {
                layer.msg('上传出错');
            });

            //删除
            $list.on("click", ".cancel", function () {
                var $ele = $(this);
                var id = $ele.parent().attr("id");
                $("#" + id).remove();
                $("#input_" + id).remove();
            });
    }

    $.fn.WebFileUpload = function (options) {
        var ele = this;
        if (typeof WebUploader == 'undefined') {
            layer.msg("请检查webuploader的路径是否正确!")
        }
        else {
            initWebUpload(ele, options);
        }
    }
    $.fn.WebImageUpload = function (options) {
        var ele = this;
        if (typeof WebUploader == 'undefined') {
            layer.msg("请检查webuploader的路径是否正确!")
        }
        else {
            initWebUploadImage(ele, options);
        }
    }
})(jQuery, window);