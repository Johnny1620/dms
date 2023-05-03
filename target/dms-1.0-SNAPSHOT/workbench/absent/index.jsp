<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>


    <script type="text/javascript">

        $(function () {

            //页面加载完毕展示信息
            pageList(1,3);

            $(".time").datetimepicker({
                minView: "month",
                language:  'zh-CN',
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left"
            });


            //为查询按钮绑定事件，触发pageList方法
            $("#searchBtn").click(function () {


                //  点击查询按钮的时候，我们应该将搜索框中的信息保存起来,保存到隐藏域中

                $("#hidden-name").val($.trim($("#search-name").val()));
                $("#hidden-info").val($.trim($("#search-info").val()));
                $("#hidden-date").val($.trim($("#search-date").val()));

                pageList(1,3);

            })

            //为全选的复选框绑定事件，触发全选操作
            $("#qx").click(function () {

                $("input[name=xz]").prop("checked",this.checked);

            })

            /*
                因为动态生成的元素，是不能够以普通绑定事件的形式来进行操作的

                动态生成的元素，我们要以on方法的形式来触发事件

                语法：
                    $(需要绑定元素的有效的外层元素).on(绑定事件的方式,需要绑定的元素的jquery对象,回调函数)

             */
            $("#activityBody").on("click",$("input[name=xz]"),function () {

                $("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);

            })



            //为创建按钮绑定事件，执行添加操作
            $("#saveBtn").click(function () {


                $.ajax({

                    url : "workbench/absent/save.do",
                    data : {

                        "name" : $.trim($("#c-name").val()),
                        "grade" : $.trim($("#c-grade").val()),
                        "reason" : $.trim($("#c-reason").val()),
                        "date" : $.trim($("#c-date").val())

                    },
                    type : "post",
                    dataType : "json",
                    success : function (data) {

                        if(data.success){

                            alert("添加成功");

                            pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                            $("#activityAddForm")[0].reset();


                        }else{

                            alert("添加失败");
                        }




                    }

                })

            })







            //为删除按钮绑定事件，执行删除操作
            $("#deleteBtn").click(function () {


                //找到复选框中所有挑√的复选框的jquery对象
                var $xz = $("input[name=xz]:checked");

                if($xz.length==0){

                    alert("请选择需要删除的记录");

                    //肯定选了，而且有可能是1条，有可能是多条
                }else{


                    if(confirm("确定删除所选中的记录吗？")){

                        //url:../../delete.do?id=xxx&id=xxx&id=xxx

                        //拼接参数
                        var param = "";

                        //将$xz中的每一个dom对象遍历出来，取其value值，就相当于取得了需要删除的记录的id
                        for (var i=0;i<$xz.length;i++){

                            param += "id="+$($xz[i]).val();

                            //如果不是最后一个元素，需要在后面追加一个&符
                            if(i<$xz.length-1){

                                param += "&";

                            }
                        }

                        /*alert(param);*/

                        //发送AJAX请求，执行删除操作
                        $.ajax({

                            url : "workbench/absent/delete.do",
                            data : param,
                            type : "post",
                            dataType : "json",
                            success : function (data) {

                                /*
                                    data
                                        {"success":true/false}
                                 */
                                if(data.success){
                                    //删除成功后
                                    //回到第一页，维持每页展现的记录数
                                    pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                                }else{
                                    alert("删除失败");
                                }

                            }
                        })

                    }
                }


            })













            //页面加载后，执行的函数到此为止
        })








        function pageList(pageNo,pageSize) {


            $.ajax({

                url : "workbench/absent/pageList.do",
                data : {

                    "pageNo" : pageNo,
                    "pageSize" : pageSize,

                },
                type : "get",
                dataType : "json",
                success : function (data) {

                    /*  <td>学生</td>
                        <td>班级</td>
                        <td>缺勤原因</td>
                        <td>缺勤日期</td>
                        */

                    var html = "";

                    //每一个n就是每一个市场活动对象
                    $.each(data.dataList,function (i,n) {

                        html += '<tr class="active">';
                        html += '<td><input type="checkbox" name="xz" value="'+n.stuName+'"/></td>';

                        html += '<td>'+n.stuName+'</td>';
                        html += '<td>'+n.grade+'</td>';
                        html += '<td>'+n.reason+'</td>';
                        html += '<td>'+n.date+'</td>';
                        html += '</tr>';

                    })

                    $("#activityBody").html(html);

                    //计算总页数
                    var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;

                    //数据处理完毕后，结合分页查询，对前端展现分页信息
                    $("#activityPage").bs_pagination({
                        currentPage: pageNo, // 页码
                        rowsPerPage: pageSize, // 每页显示的记录条数
                        maxRowsPerPage: 20, // 每页最多显示的记录条数
                        totalPages: totalPages, // 总页数
                        totalRows: data.total, // 总记录条数

                        visiblePageLinks: 3, // 显示几个卡片

                        showGoToPage: true,
                        showRowsPerPage: true,
                        showRowsInfo: true,
                        showRowsDefaultInfo: true,

                        //该回调函数时在，点击分页组件的时候触发的
                        onChangePage : function(event, data){
                            pageList(data.currentPage , data.rowsPerPage);
                        }
                    });


                }

            })

        }

    </script>
</head>
<body>









<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>Absence Record</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form id="activityAddForm" class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">学生</div>
                        <input class="form-control" type="text" id="c-name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">班级</div>
                        <input class="form-control" type="text" id="c-grade">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">请假原因</div>
                        <input class="form-control" type="text" id="c-reason">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">缺勤日期</div>
                        <input class="form-control time" type="text" id="c-date">
                    </div>
                </div>


                <button type="button" class="btn btn-primary" id="saveBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
            </form>
        </div>


        <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 2px; position: relative;top: 5px;">

        </div>


        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">

                    <td><input type="checkbox" id="qx"/></td>
                    <td>学生</td>
                    <td>班级</td>
                    <td>缺勤原因</td>
                    <td>缺勤日期</td>

                </tr>
                </thead>


                <tbody id="activityBody">


                </tbody>
            </table>
        </div>

        <div style="height: 50px; position: relative;top: 30px;">

            <div id="activityPage"></div>

        </div>

    </div>

</div>
</body>
</html>