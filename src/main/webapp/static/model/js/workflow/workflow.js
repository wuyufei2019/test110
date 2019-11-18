function graphTrace(options) {

    var _defaults = {
        srcEle: this,
        pid: $(this).attr('pid'),
        pdid: $(this).attr('pdid'),
        status: $(this).attr('status')
    };
    var opts = $.extend(true, _defaults, options);


    $('#diagram-viewer').live('click', function () {
        $('#workflowTraceDialog').dialog('close');

        if ($('#imgDialog').length > 0) {
            $('#imgDialog').remove();
        }

        var url = ctx + '/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid;

        $('<div/>', {
            'id': 'imgDialog',
            title: '此对话框显示的图片是由引擎自动生成的，并用红色标记当前的节点',
            html: '<iframe src="' + url + '" width="100%" height="' + document.documentElement.clientHeight * 0.90 + '" />'
        }).appendTo('body').dialog({
            modal: true,
            resizable: false,
            dragable: false,
            width: document.documentElement.clientWidth * 0.95,
            height: document.documentElement.clientHeight * 0.95
        });
    });

    layer.open({
        type: 2,
        shift: 1,
        area: ['100%', '100%'],
        title: '流程查看',
        maxmin: true,
        content: ctx + '/diagram-viewer/index.html?processDefinitionId=' + opts.pdid + '&processInstanceId=' + opts.pid,
        yes: function () {
        },
        cancel: function (index) {
        }
    });

    // 获取图片资源
    //var imageUrl = ctx + "/workflow/resource/process-instance?pid=" + opts.pid + "&type=image";
    /*    layer.open({
            type: 2,
            shift: 1,
            area: ['100%', '100%'],
            title: '流程查看',
            maxmin: true,
            content: ctx+"/workflow/process/trace?pid="+opts.pid ,
            yes:function(){
            },
            cancel: function(index){
            }
        });*/

    /*    $.getJSON(ctx + '/workflow/process/trace?pid=' + opts.pid, function(infos) {
            console.log(infos)

            var positionHtml = "";

            // 生成图片
            var varsArray = new Array();
            $.each(infos, function(i, v) {
                var $positionDiv = $('<div/>', {
                    'class': 'activity-attr'
                }).css({
                    position: 'absolute',
                    left: (v.x -6),
                    top: (v.y -6),
                    width: (v.width +4),
                    height: (v.height +4),
                    backgroundColor: 'black',
                    opacity: 0
                });

                // 节点边框
                var $border = $('<div/>', {
                    'class': 'activity-attr-border'
                }).css({
                    position: 'absolute',
                    left: (v.x -6),
                    top: (v.y -6),
                    width: (v.width +4),
                    height: (v.height +4)/!*,
                    zIndex: $.fn.qtip.zindex - 2*!/
                });

                if (v.activityType=='A') {
                    $border.addClass('ui-corner-all-12').css({
                        border: '3px solid red'
                    });
                }
                if (v.activityType=='B') {
                    $border.addClass('ui-corner-all-12').css({
                        border: '3px solid blue'
                    });
                }
                positionHtml += $positionDiv[0].outerHTML + $border[0].outerHTML
                varsArray[varsArray.length] = v.vars;
            });

            if ($('#workflowTraceDialog').length == 0) {
                $('<div/>', {
                    id: 'workflowTraceDialog',
                    title: '查看流程（按ESC键可以关闭）',
                    html: "<div><img src='" + imageUrl + "' style='position:absolute; left:0px; top:0px;' />" +
                    "<div id='processImageBorder'>" +
                    positionHtml +
                    "</div>" +
                    "</div>"
                }).appendTo('body');
            } else {
                $('#workflowTraceDialog img').attr('src', imageUrl);
                $('#workflowTraceDialog #processImageBorder').html(positionHtml);
            }

            // 设置每个节点的data
            $('#workflowTraceDialog .activity-attr').each(function(i, v) {
                $(this).data('vars', varsArray[i]);
            });


                layer.open({
                    type: 1,
                    shift: 1,
                    area: ['900px', '400px'],
                    title: '流程查看',
                    maxmin: true,
                    content: $('#workflowTraceDialog') ,
                    yes:function(){
                        $('#workflowTraceDialog').remove();
                    },
                    cancel: function(index){
                        $('#workflowTraceDialog').remove();
                    }
                });


        });*/

}
