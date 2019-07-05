layui.use(['layer', 'form', 'element'], function () {
    var layer = layui.layer
        , form = layui.form
        , element = layui.element

}); 

function setPage(src) {
    //直接删除元素重建，否则会产生历史记录导致后退出问题
    document.getElementById("LAY_IFRAME").innerHTML = '';
    var iframe = '<iframe name="myiframe" id="main_iframe" src="' + src + '" frameborder="0" class="layadmin-iframe"></iframe>';
    document.getElementById("LAY_IFRAME").innerHTML = iframe;
}
function setForcus(id) {
    var x = document.getElementsByClassName("layui-this");
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].className = "";
    }
    document.getElementById(id).className = "layui-this";
}

function load() {
    var p = location.hash;
    if (p != null && p.toString().length > 1) {
        if (p == "#userlist") {
            setForcus("child_userlist");
            setPage("User_UserList.html");
        }
        else if (p == "#useradd") {
            setForcus("child_useradd");
            setPage("User_UserAdd.html");
        }
        else if (p == "#rolelist") {
            setForcus("child_rolelist");
            setPage("Role_RoleList.html");
        }
        else if (p == "#rolerestore") {
            setForcus("child_rolerestore");
            setPage("Role_RoleRestore.html");
        }
        else if (p == "#monsterlist") {
            setForcus("child_monsterlist");
            setPage("Monster_MonsterList.html");
        }
        else if (p == "#goodslist") {
            setForcus("child_goodslist");
            setPage("Goods_GoodsList.html");
        }
    }
}

function goto(page) {
    location.hash = "#" + page;
    load();
}

// onhashchange可以监控hash变化
window.onhashchange = function () {
    load();
};