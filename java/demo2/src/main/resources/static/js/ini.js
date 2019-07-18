$.ajaxSetup({
    async: false
});
var IsLogin=function () {
    var tag=false;
    $.post("/loginApi",
        {
        }, function(data){
            if(data.status=="200"){
                tag=true;
            }else {
                tag=false;
            }
        }
    );
    return tag;

}
var getdata=function(url){
    var data1;
    $.post(url,
        {
        }, function(data){
            if(data.status=="200"){
                data1=data;
            }else {
                data1=null;
            }
        }
    );
    return data1;
}
var geturlprame=function (name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r!=null) return unescape(r[2]); return null; //返回参数值
}

var postdata=function (Api,data) {
    var data1;
    $.post(Api,data, function(data){
            if(data.status=="200"){
                data1=data;
            }else {
                data1=null;
            }
        }
    );
    return data1;
}
function logout() {
    if(confirm("您确定要退出吗？"))
        LogOut();
    self.location = "index.html";
    return false;
}var LogOut=function() {
    var tag=false;
    $.post("/logoutApi"
        , function(data){
            if(data.status=="200"){
                tag = true;
            }else{
                tag = false;
            }
        }
    )
    return tag;
}
function logout() {
    if(confirm("您确定要退出吗？"))
        LogOut();
    self.location = "index.html";
    return false;
}
