//导入 用layui upload插件
layui.use([ "element", "laypage", "layer", "upload"], function() {
    var element = layui.element;
    var laypage = layui.laypage;
    var layer = layui.layer;
    var upload = layui.upload;//主要是这个
    layui.upload.render({
        elem: "#importData",//导入id
        url: "/user/importData",
        size: '3072',
        accept: "file",
        exts: 'xls|xlsx|xlsm|xlt|xltx|xltm',
        done: function (result) {
            if (result.status == 0) {
                refreshTable()
            }
            if (result.message != null) {
                refreshTable();
                layer.msg(result.message)
            }
        }
    });
    refreshTable()
});


//导出
function exportData() {
    $.ajax({
        type: "post", 
        url: "/user/exportData", 
        data: {}, 
        success: function (result) {
            if (result.status == 0) {
                window.open(result.data)
            }
            if (result.message != null) {
                layer.msg(result.message)
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('{"status":"' + XMLHttpRequest.status + '","readyState":"' + XMLHttpRequest.readyState + '","textStatus":"' + textStatus + '","errorThrown":"' + errorThrown + '"}')
        }
    })
};

//模板下载
function downloadTemplate() {
    $.ajax({
        type: "post", 
        url: "/user/downloadTemplate", 
        data: {},
        success: function (result) {
            if (result.status == 0) {
                window.open(result.data)
            }
            if (result.message != null) {
                layer.msg(result.message)
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('{"status":"' + XMLHttpRequest.status + '","readyState":"' + XMLHttpRequest.readyState + '","textStatus":"' + textStatus + '","errorThrown":"' + errorThrown + '"}')
        }
    })
}
//批量导入
/*
table.on('toolbar(search)', function (obj) {
    var object1 = []
    var arr;
    var checkStatus = table.checkStatus(obj.config.id);
    switch (obj.event) {//批量导出功能
        case 'outdata':
            debugger;
            var data = checkStatus.data;
            if (data != "" && data != null) {
                for (var i = 0; i < data.length; i++) { //循环拿到选择的数据放进数组
                    var id = data[i].ID;
                    object1.push(id);
                    arr = object1.join(','); //用逗号将数据隔开
                }
                $.ajax({
                    url: "/Left_D/OutData1",
                    type: "post", //提交方式
                    data: {
                        arr: arr
                    },
                    success: function (data) {
                        window.location.href = "/user/export_findUserByFuzzyUserName"; //转到导出方法
                    }
                });
            }
            else {
                layer.msg('请选择导出的数据');
                return;
            }
    };
})*/
