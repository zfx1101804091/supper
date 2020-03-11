function checkPermission() {
    var pers = [];
    //localStorage.permission = ["sys:role:del","sys:menu:del","sys:user:query","sys:user:password","sys:menu:add","sys:user:add","sys:menu:edit","sys:role:query","sys:role:edit","sys:menu:query","sys:role:add"]
    var permissions = jQuery.parseJSON(localStorage.permission);
    $("[permission]").each(function() {
        var per = $(this).attr("permission");
        //alert(per);
        //jQuery.inArray()方法 : 返回数组中指定元素的索引值
        if ($.inArray(per, permissions) < 0) {
            $(this).hide();
        }
    });

    return pers;
}

function checkPermissionForTable(){
    var permissions = jQuery.parseJSON(localStorage.permission);
    $("[permission]").each(function() {
        var per = $(this).attr("permission");
        if ($.inArray(per, permissions) >= 0) {
            return true;
        }
    });
    return false;
}