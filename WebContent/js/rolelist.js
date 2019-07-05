
layui.use(['layer', 'form', 'element', 'table'], function () {
    var layer = layui.layer
        , form = layui.form
        , element = layui.element
        , table = layui.table
        , $ = layui.jquery;


    //监听搜索栏提交事件
    form.on('submit(searchBtn)', function (data) {
        var rolename = document.getElementById("rolename").value;
        var career = document.getElementById("career").value;
        var min_money = document.getElementById("min_money").value;
        var max_money = document.getElementById("max_money").value;
        var min_gamecoin = document.getElementById("min_gamecoin").value;
        var max_gamecoin = document.getElementById("max_gamecoin").value;
        var min_daycontinuous = document.getElementById("min_daycontinuous").value;
        var max_daycontinuous = document.getElementById("max_daycontinuous").value;
        var min_daytotal = document.getElementById("min_daytotal").value;
        var max_daytotal = document.getElementById("max_daytotal").value;
        //重新渲染表单
        table.render({
            elem: '#demo'
            , url: 'api/GetAllRoles?'
                + 'rolename=' + rolename + '&career=' + career + '&min_money=' + min_money
                + '&max_money=' + max_money + '&min_gamecoin=' + min_gamecoin + '&max_gamecoin=' + max_gamecoin
                + '&min_daycontinuous=' + min_daycontinuous + '&max_daycontinuous=' + max_daycontinuous
                + '&min_daytotal=' + min_daytotal + '&max_daytotal=' + max_daytotal + '&t=' + new Date().getTime() //数据接口
            , toolbar: '#barHead'
            , page: true //开启分页
            , cols: [[ //表头
                { field: 'id', title: '编号', sort: true, fixed: 'left' }
                , { field: 'rolename', title: '角色名' }
                , { field: 'careername', title: '职业' }
                , { field: 'money', title: '充值金额', sort: true }
                , { field: 'gamecoin', title: '游戏币数额', sort: true }
                , { field: 'daycontinuous', title: '连续签到天数', sort: true }
                , { field: 'daytotal', title: '累计签到天数', sort: true }
                , { field: 'userid', title: '所属账号', sort: true }
                , { fixed: 'right', title: '操作', toolbar: '#barRole', width: 150, align: 'center'  }
            ]]
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //第一个实例
    table.render({
        elem: '#demo'
        , url: 'api/GetAllRoles' + '?t=' + new Date().getTime() //数据接口
        , toolbar: '#barHead'
        , page: true //开启分页
        , cols: [[ //表头
            { field: 'id', title: '编号', sort: true, fixed: 'left' }
            , { field: 'rolename', title: '角色名' }
            , { field: 'careername', title: '职业' }
            , { field: 'money', title: '充值金额', sort: true }
            , { field: 'gamecoin', title: '游戏币数额', sort: true }
            , { field: 'daycontinuous', title: '连续签到天数', sort: true }
            , { field: 'daytotal', title: '累计签到天数', sort: true }
            , { field: 'userid', title: '所属账号', sort: true }
            , { fixed: 'right', title: '操作', toolbar: '#barRole', width: 150, align: 'center'  }
        ]]
    });


    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent == "show") {
            //显示层
            layer.open({
                title: '查看 ' + data.rolename + ' 所拥有的物品'
                , type: 1
                , area: ['400px', '340px']
                , content: $('#noDisplayTable2')
                , maxmin: true
                , end: function (index, layero) {
                }
                , full: function (index, layero) {
                    table.resize('goodsTable');
                }
                , restore: function (index, layero) {
                    table.resize('goodsTable');
                }
                , resizing: function (layero) {
                    table.resize('goodsTable');
                }
            });

            //绘制表格
            table.render({
                elem: '#goodsTable'
                , cellMinWidth: 120
                , url: 'api/GetRoleGoods?roleid=' + data.id + '&t=' + new Date().getTime()
                , title: '物品表'
                , cols: [[ //表头
                    { field: 'goodsname', title: '物品名' }
                    , { field: 'count', title: '数量', sort: true }
                ]]
            });
        }
        else if (layEvent == "del") {
            layer.confirm('确认删除该角色么', { icon: 3, title: '信息' }, function (index) {
                //请求服务器
                $.ajax({
                    url: 'api/DelRole?roleid=' + data.id + '&t=' + new Date().getTime(),
                    type: 'GET',
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        obj.del(); //删除对应行（tr）的DOM结构
                        layer.msg("删除成功", { time: 3000 });
                        layer.close(index);
                    },
                    error: function () {
                        layer.msg("删除失败", { time: 3000 });
                    }
                });
            });
        }
    });
});