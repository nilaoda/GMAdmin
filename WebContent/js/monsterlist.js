layui.use(['layer', 'form', 'element', 'table', 'slider'], function () {
    var layer = layui.layer
        , form = layui.form
        , element = layui.element
        , table = layui.table
        , slider = layui.slider
        , $ = layui.jquery; 


    //第一个实例
    table.render({
        elem: '#demo'
        , url: 'api/GetAllMonsters' + '?t=' + new Date().getTime() //数据接口
        , toolbar: '#barHead'
        , page: true //开启分页
        , cols: [[ //表头
            { field: 'id', title: '编号', sort: true }
            , { field: 'name', title: '怪物名称', sort: true }
            , { field: 'typename', title: '怪物种类', sort: true }
            , { field: 'attributename', title: '怪物属性' }
            , { field: 'power', title: '力' }
            , { field: 'speed', title: '速' }
            , { field: 'magic', title: '魔' }
            , { fixed: 'right', title: '操作', toolbar: '#barDemo', width: 120, align: 'center' }
        ]]
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
                            console.log(formJson);
                            //请求服务器
                            $.ajax({
                                url: 'api/AddMonster' + '?t=' + new Date().getTime(),
                                type: 'POST',
                                data: formJson,
                                async: false,
                                dataType: 'json',
                                success: function (data) {
                                    layer.msg("添加成功", { time: 3000 });
                                    table.reload('demo', {
                                    });
                                    layer.closeAll();
                                },
                                error: function () {
                                    layer.msg("添加失败", { time: 3000 });
                                }
                            });
                        });
                    }
                    , btn2: function (index, layero) {
                        //按钮【按钮二】的回调
                    }
                    , type: 1
                    , area: ['440px', '445px']
                    , content: $('#noDisplayFormAdd')
                    , end: function (index, layero) {
                        // 清空表单
                        form.render(null, 'addMonsterForm');
                        //表单初始赋值
                        form.val('addMonsterForm', {
                            "name": ''
                            , "power": ''
                            , "speed": ''
                            , "magic": ''
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
        if (layEvent == "del") {
            layer.confirm('确定删除么？ ', { icon: 3, title: '信息' }, function (index) {
                //请求服务器
                $.ajax({
                    url: 'api/DelMonster?id=' + data.id + '&t=' + new Date().getTime(),
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
            form.render(null, 'addMonsterForm');
            //表单初始赋值
            form.val('addMonsterForm', {
                "name": data.name
                , "speed": data.speed
                , "power": data.power
                , "magic": data.magic
                , "_type": data.type
                , "_attr": data.attribute
            });

            layer.open({
                title: '编辑 {' + data.id + ',' + data.name + '}'
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
                        formArray.push({ "name": "id", "value": data.id }); //把ID加进去
                        $.each(formArray, function (i, item) {
                            formObject[item.name] = item.value;
                        });
                        var formJson = JSON.stringify(formObject);
                        //请求服务器
                        $.ajax({
                            url: 'api/UpdateMonster' + '?t=' + new Date().getTime(),
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
                , btn2: function (index, layero) {
                    //按钮【按钮二】的回调
                }
                , type: 1
                , area: ['440px', '445px']
                , content: $('#noDisplayFormAdd')
                , end: function (index, layero) {
                    // 清空表单
                    //表单初始赋值
                    form.val('addMonsterForm', {
                        "name": ''
                        , "power": ''
                        , "speed": ''
                        , "magic": ''
                    });
                    form.render(null, 'addMonsterForm');
                }
            });
        }
    });
});