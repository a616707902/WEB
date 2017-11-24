/**
 *标题：ajax封装
 *说明:
 * GET/POST/PUT/DELETE
 * GET：查询资源
 * POST:创建资源
 * PUT：更新资源
 * DELETE：删除资源
 *作者：武伟
 *日期：2017/9/6
 */

// var urlPrefix = 'http://www.databeacon.cn/VehicleManager/';
var urlPrefix = 'http://localhost:8088/VehicleManager/';


/**
 * GET：查询资源
 * @param url
 * @param data
 * @param callback
 */
function doGet(url, data, callback) {
    doRequest('GET', url, data, callback);
}

/**
 * POST:创建资源
 * @param url
 * @param data
 * @param callback
 */
function doPost(url, data, callback) {
    doRequest('POST', url, data, callback);
}

/**
 * PUT：更新资源
 * @param url
 * @param data
 * @param callback
 */
function doPut(url, data, callback) {
    doRequest('PUT', url, data, callback);
}

/**
 * DELETE：删除资源
 * @param url
 * @param data
 * @param callback
 */
function doDelete(url, data, callback) {
    doRequest('DELETE', url, data, callback);
}

/**
 * GET：查询资源 不返回弹窗提示
 * @param url
 * @param data
 * @param callback
 */
function doGet2(url, data, callback) {
    doRequest('GET', url, data, callback);
}



/**
 * GET/POST/PUT/DELETE
 * @param type
 * @param url
 * @param data
 * @param callback
 */
function doRequest(type, url, data, callback) {
    $('head').append('<link rel="stylesheet" href="/WebFront/plugin/layui-v2.1.5/layui/css/modules/layer/default/layer.css">');
    $('head').append('<script type="text/javascript" src="/WebFront/plugin/layui-v2.1.2/layui/lay/modules/layer.js"></script>');
    $('head').append('<script type="text/javascript" src="/WebFront/plugin/jquery.cookie.js"></script>');

    // 解决IE8/IE9跨域请求无响应问题
    $('head').append("<!--[if lte IE 9]> <script>jQuery.support.cors = true;</script><![endif]-->");

    // 从cookie中获取token
    var token = $.cookie('X-Token');

    if (undefined == token) {
        token = "";
    }

    // var index;

    $.ajax({
        type: type,
        async : true,
        url: urlPrefix + url,
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "X-Token": token
        },
        data: data,
        dataType: 'json',
        success: function (response) {
            callback(response);
        },
        beforeSend: function (xhr) {
            // index = layer.load(0, {shade: false});
        },
        complete: function () {
            // layer.close(index);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

            var url = window.location.href;
            if(url.indexOf('lcd.html')>0){
                return;
            }

            if("Token is invaild" == XMLHttpRequest.responseJSON.meta.message) {

                window.location.href = "/VehicleManagerWeb/html/login.html";

            } else {
                layer.msg('#ERROR', {icon: 5});
            }

        }
    });
}

/**
 * 功能描述：弹出框提示
 * 作者：齐遥遥
 * 时间：2017-10-24
 * @param msg  消息
 * @param icon  图标NUMB
 */
function mymsg(msg,iconNum) {
    top.layer.msg(msg, {icon: iconNum});
}
