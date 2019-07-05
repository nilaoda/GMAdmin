layui.use(['layer', 'form', 'element', 'table'], function () {
    var layer = layui.layer
        , form = layui.form
        , element = layui.element
        , table = layui.table
        , $ = layui.jquery;

    //第一个实例
    table.render({
        elem: '#demo'
        , url: 'api/GetAllUsers' + '?t=' + new Date().getTime() //数据接口
        , toolbar: '#barHeadDemo'
        , page: true //开启分页
        , cols: [[ //表头
            { field: 'uid', title: 'UID', sort: true, fixed: 'left', width: 120 }
            , { field: 'username', title: '用户名' }
            , { field: 'password', title: '密码' }
            , { field: 'email', title: '邮箱' }
            , { field: 'money', title: '账户充值金额', width: 130, sort: true }
            , { fixed: 'right', title: '操作', toolbar: '#barDemo', width: 220, align: 'center' }
        ]]
    });

    //监听搜索栏提交事件
    form.on('submit(searchBtn)', function (data) {
        var uid = document.getElementById("uid").value;
        var username = document.getElementById("username").value;
        var email = document.getElementById("email").value;
        //重新渲染表单
        table.render({
            elem: '#demo'
            , url: 'api/GetAllUsers?'
                + 'uid=' + uid + '&username=' + username + '&email=' + email + '&t=' + new Date().getTime() //数据接口
            , toolbar: '#barHeadDemo'
            , page: true //开启分页
            , cols: [[ //表头
                { field: 'uid', title: 'UID', sort: true, fixed: 'left', width: 120 }
                , { field: 'username', title: '用户名' }
                , { field: 'password', title: '密码' }
                , { field: 'email', title: '邮箱' }
                , { field: 'money', title: '账户充值金额', width: 130, sort: true }
                , { fixed: 'right', title: '操作', toolbar: '#barDemo', width: 220, align: 'center' }
            ]]
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'add':
                layer.open({
                    title: '添加：'
                    , btn: ['添加', '取消']
                    , success: function (layero, index) {
                        //添加form标识
                        layero.addClass('layui-form');
                        //将按钮重置为可提交按钮以触发表单验证
                        layero.find('.layui-layer-btn0').attr('lay-filter', 'formContent').attr('lay-submit', '');
                        form.render();
                    }
                    , yes: function (index, layero) {
                        //监听提交按钮
                        form.on('submit(formContent)', function (data) {
                            //转为json字符串
                            var formObject = {};
                            var formArray = $('#layui-layer' + index).find('form').serializeArray();
                            $.each(formArray, function (i, item) {
                                formObject[item.name] = item.value;
                            });
                            var formJson = JSON.stringify(formObject);
                            //请求服务器
                            $.ajax({
                                url: 'api/AddUser' + '?t=' + new Date().getTime(),
                                type: 'POST',
                                data: formJson,
                                async: false,
                                dataType: 'json',
                                success: function (data) {
                                    layer.msg("添加用户成功", { time: 3000 });
                                    table.reload('demo', {
                                    });
                                    layer.closeAll();
                                },
                                error: function () {
                                    layer.msg("添加失败，该邮箱已存在", { time: 3000 });
                                }
                            });
                        });
                    }
                    , btn2: function (index, layero) {
                        //按钮【按钮二】的回调
                    }
                    , type: 1
                    , area: ['400px', '285px']
                    , content: $('#noDisplayFormAdd')
                    , end: function (index, layero) {
                        // 清空表单
                        $("#addUserForm")[0].reset();
                        form.render(null, 'addUserForm');
                        //表单初始赋值
                        form.val('addUserForm', {

                        });
                    }
                });
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) {
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        //删除操作
        if (layEvent === 'del') {
            layer.confirm('该用户所拥有的所有角色也将被删除! ', { icon: 3, title: '真的删除 {' + data.uid + ',' + data.username + '} 么?' }, function (index) {
                //请求服务器
                $.ajax({
                    url: 'api/DelUser?uid=' + data.uid + '&t=' + new Date().getTime(),
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
        //编辑操作
        else if (layEvent === 'edit') {

            //赋值表单操作
            $("#addUserForm")[0].reset();
            form.render(null, 'addUserForm');
            //表单初始赋值
            form.val('addUserForm', {
                "username": data.username
                , "email": data.email
                , "password": data.password
            });

            layer.open({
                title: '编辑 {' + data.uid + ',' + data.username + '}'
                , btn: ['更改', '取消']
                , success: function (layero, index) {
                    //添加form标识
                    layero.addClass('layui-form');
                    //将按钮重置为可提交按钮以触发表单验证
                    layero.find('.layui-layer-btn0').attr('lay-filter', 'formContent').attr('lay-submit', '');
                    form.render();
                }
                , yes: function (index, layero) {
                    //监听提交按钮
                    form.on('submit(formContent)', function () {
                        //转为json字符串
                        var formObject = {};
                        var formArray = $('#layui-layer' + index).find('form').serializeArray();
                        formArray.push({ "name": "uid", "value": data.uid }); //把UID加进去
                        $.each(formArray, function (i, item) {
                            formObject[item.name] = item.value;
                        });
                        var formJson = JSON.stringify(formObject);
                        //请求服务器
                        $.ajax({
                            url: 'api/UpdateUser' + '?t=' + new Date().getTime(),
                            type: 'POST',
                            data: formJson,
                            async: false,
                            dataType: 'json',
                            success: function (data) {
                                layer.msg("修改成功", { time: 3000 });
                                table.reload('demo', {
                                });
                                layer.closeAll();
                            },
                            error: function () {
                                layer.msg("修改失败", { time: 3000 });
                            }
                        });
                    });
                }
                , type: 1
                , area: ['400px', '285px']
                , content: $('#noDisplayFormAdd')
                , end: function (index, layero) {
                    // 清空表单
                    $("#addUserForm")[0].reset();
                    form.render(null, 'addUserForm');
                }
            });
        }
        else if (layEvent == "addRole") {
            layer.open({
                title: '为用户 ' + data.username + ' 添加角色：'
                , btn: ['添加', '取消']
                , success: function (layero, index) {
                    //添加form标识
                    layero.addClass('layui-form');
                    //将按钮重置为可提交按钮以触发表单验证
                    layero.find('.layui-layer-btn0').attr('lay-filter', 'formContent').attr('lay-submit', '');
                    form.render();
                }
                , yes: function (index, layero) {
                    //监听提交按钮
                    form.on('submit(formContent)', function () {
                        //转为json字符串
                        var formObject = {};
                        var formArray = $('#layui-layer' + index).find('form').serializeArray();
                        formArray.push({ "name": "uid", "value": data.uid }); //把UID加进去
                        $.each(formArray, function (i, item) {
                            formObject[item.name] = item.value;
                        });
                        var formJson = JSON.stringify(formObject);
                        //请求服务器
                        $.ajax({
                            url: 'api/AddRole' + '?t=' + new Date().getTime(),
                            type: 'POST',
                            data: formJson,
                            async: false,
                            dataType: 'json',
                            success: function (data) {
                                layer.msg("添加角色成功", { time: 3000 });
                                table.reload('roleTable', {
                                });
                                layer.closeAll();
                            },
                            error: function (data) {
                                layer.msg("添加失败", { time: 3000 });
                            }
                        });
                    });
                }
                , btn2: function (index, layero) {
                    //按钮【按钮二】的回调
                }
                , type: 1
                , area: ['430px', '235px']
                , content: $('#noDisplayFormAddRole')
                , end: function (index, layero) {
                    // 清空表单
                    $("#addUserForm")[0].reset();
                    form.render(null, 'addUserForm');
                    //表单初始赋值
                    form.val('addUserForm', {

                    });
                }
            });
        }
        else if (layEvent == "detail") {
            //显示层
            layer.open({
                title: '查看 ' + data.username + ' 所拥有的角色'
                , type: 1
                , area: ['1000px', '460px']
                , content: $('#noDisplayTable')
                , maxmin: true
                , end: function (index, layero) {
                }
                , full: function (index, layero) {
                    table.resize('roleTable');
                }
                , restore: function (index, layero) {
                    table.resize('roleTable');
                }
                , resizing: function (layero) {
                    table.resize('roleTable');
                }
            });

            //绘制表格
            table.render({
                elem: '#roleTable'
                , cellMinWidth: 120
                , url: 'api/GetAllRoles?uid=' + data.uid + '&t=' + new Date().getTime()
                , title: '角色表'
                , cols: [[ //表头
                    { field: 'id', title: '编号', sort: true, fixed: 'left' }
                    , { field: 'rolename', title: '角色名' }
                    , { field: 'careername', title: '职业' }
                    , { field: 'money', title: '充值金额', sort: true }
                    , { field: 'gamecoin', title: '游戏币数额', sort: true }
                    , { field: 'daycontinuous', title: '连续签到天数', sort: true }
                    , { field: 'daytotal', title: '累计签到天数', sort: true }
                    , { fixed: 'right', title: '背包物品', toolbar: '#barRole', align: 'center' }
                ]]
            });


            //监听行工具事件
            table.on('tool(roleTable)', function (obj) {
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
            });
        }
    });


});