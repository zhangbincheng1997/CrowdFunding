function loading() {
    $("#loading").loading({
        loadingWidth: 240,
        title: '加载中...',
        name: 'loading',
        discription: '马上就好',
        direction: 'column',
        type: 'origin',
        originDivWidth: 40,
        originDivHeight: 40,
        originWidth: 6,
        originHeight: 6,
        smallLoading: false,
        loadingMaskBg: 'rgba(0,0,0,0.2)'
    });
}

// 发送金币 - 选中项目
function select_send(fundIndex) {
    $('#fundIndex2').val(fundIndex);
}

// 获取记录 - 选中项目
function select_record(fundIndex) {
    // 异步提交
    $.ajax({
        url: "getRecords",
        type: "POST",
        dataType: 'json',
        data: {
            "fundIndex": fundIndex
        },
        beforeSend: function () {
            $("#tip").html('<span style="color:blue">正在处理...</span>');
            return true;
        },
        success: function (data) {
            // 后台返回结果为空
            if ($.isEmptyObject(data)) {
                $("#records").html('<p class="text-danger">暂无捐赠</p>');
            }
            // 后台返回结果非空
            else {
                var tab = "";

                // thead
                tab += '<thead><tr>';
                tab += '<th>编号</th>';
                tab += '<th>捐赠成员</th>';
                tab += '<th>捐赠金币</th>';
                tab += '<th>捐赠时间</th>';
                tab += '<tr></thead>';
                // tbody
                tab += '<tbody>';
                $
                    .each(
                        data,
                        function (i, fund) {
                            tab += '<tr>';
                            tab += '<td>'
                                + i
                                + '</td>';
                            tab += '<td>'
                                + fund['member']
                                + '</td>';
                            tab += '<td>'
                                + fund['coin']
                                + '</td>';
                            // yyyy/MM/dd
                            var date = new Date();
                            date.setTime(fund['time'] * 1000);
                            tab += '<td>'
                                + date.toLocaleDateString()
                                + '</td>';
                            tab += '</tr>';
                        });
                tab += '</tbody>';

                $("#records").html(tab);
            }

        }
    });
}

$(function () {

    ////////// 发起众筹　//////////
    // 点击按钮
    $("#confirm").click(function () {
            if ($("#myForm").valid()) {
                // 读取文件
                var reader = new FileReader();
                reader.readAsText(document.getElementById("file").files[0], "UTF-8");
                reader.onload = function (e) {
                    var content = e.target.result;

                    // 异步提交
                    $.ajax({
                        url: "raiseFund",
                        type: "POST",
                        data: {
                            "desc": $("#desc").val(),
                            "goal": $("#goal").val(),
                            "password": $("#password").val(),
                            "content": content
                        },
                        beforeSend: function () {
                            $("#tip").html('<span style="color:blue">正在处理...</span>');
                            return true;
                        },
                        success: function (res) {
                            if (res) {
                                alert('操作成功');
                            } else {
                                alert('操作失败');
                            }
                            setTimeout(function () {
                                $("#myModal").modal('hide')
                            }, 1000);
                        }
                    });
                    return false;
                }
            }
        }
    );

    // 验证表单
    var validator = $("#myForm").validate(
        {
            rules: {
                desc: {
                    required: true,
                    rangelength: [1, 100]
                },
                goal: {
                    required: true,
                    range: [1, 1000]
                },
                password: {
                    required: true
                },
                file: {
                    required: true
                }
            },
            messages: {
                desc: {
                    required: "请输入描述",
                    rangelength: "描述不合法"
                },
                goal: {
                    required: "请输入目标",
                    range: "目标不合法"
                },
                password: {
                    required: '请输入密码',
                },
                file: {
                    required: '请放入钱包',
                }
            },
            success: function (label) {
                label.text("√"); // 正确的时候输出
            },
            errorPlacement: function (error, element) {
                // Append error within linked label
                $(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
            }
        })

    // 模态框
    $('#myModal').on(
        'hide.bs.modal',
        function () {
            // 清空输入
            $(':input', '#myForm')
                .not(':button, :submit, :reset, :hidden').val('')
                .removeAttr('checked').removeAttr('selected');
            $("#tip").html('<span id="tip"> </span>');
            // 清空提示
            validator.resetForm();
        });

    ////////// 发送金币　//////////
    // 点击按钮
    $("#confirm2")
        .click(
            function () {
                if ($("#myForm2").valid()) {
                    // 读取文件
                    var reader = new FileReader();
                    reader.readAsText(document.getElementById("file2").files[0], "UTF-8");
                    reader.onload = function (e) {
                        var content = e.target.result;

                        // 异步提交
                        $.ajax({
                            url: "sendCoin",
                            type: "POST",
                            data: {
                                "fundIndex": $("#fundIndex2").val(),
                                "coin": $("#coin2").val(),
                                "password": $("#password2").val(),
                                "content": content
                            },
                            beforeSend: function () {
                                $("#tip2").html('<span style="color:blue">正在处理...</span>');
                                return true;
                            },
                            success: function (res) {
                                if (res) {
                                    alert('操作成功');
                                } else {
                                    alert('操作失败');
                                }
                                setTimeout(function () {
                                    $("#myModal2").modal('hide')
                                }, 1000);
                            }
                        });
                    };
                    return false;
                }
            });

    // 验证表单
    var validator2 = $("#myForm2").validate(
        {
            rules: {
                coin2: {
                    required: true,
                    range: [1, 1000]
                },
                password2: {
                    required: true,
                },
                file2: {
                    required: true,
                }
            },
            messages: {
                coin2: {
                    required: "请输入金币",
                    range: "金币不合法"
                },
                password2: {
                    required: '请输入密码',
                },
                file2: {
                    required: '请放入钱包',
                }
            },
            success: function (label) {
                label.text("√"); // 正确的时候输出
            },
            errorPlacement: function (error, element) {
                // Append error within linked label
                $(element).closest("form").find("label[for='" + element.attr("id") + "']").append(error);
            }
        })

    // 模态框
    $('#myModal2').on(
        'hide.bs.modal',
        function () {
            // 清空输入
            $(':input', '#myForm2').not(':button, :submit, :reset, :hidden')
                .val('').removeAttr('checked').removeAttr('selected');
            $("#tip2").html('<span id="tip2"> </span>');
            // 清空提示
            validator2.resetForm();
        });
});