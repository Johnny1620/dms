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
            pageList(1,5);

            //为查询按钮绑定事件，触发pageList方法
            $("#searchBtn").click(function () {

                //  点击查询按钮的时候，我们应该将搜索框中的信息保存起来,保存到隐藏域中

                $("#hidden-stuId").val($.trim($("#search-stuId").val()));

                pageList(1,5);

            })

            $("#fBtn").click(function () {

                //  刷新

                $("#hidden-stuId").val(null);

                pageList(1,5);

            })




        //页面加载后，加载的函数到此为止
        })




        function pageList(pageNo,pageSize) {

            //将全选的复选框的√干掉
            $("#qx").prop("checked",false);

            //查询前，将隐藏域中保存的信息取出来，重新赋予到搜索框中
            $("#search-stuId").val($.trim($("#hidden-stuId").val()));

            $.ajax({

                url : "workbench/move/into/pageList.do",
                data : {

                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "stuName" : $.trim($("#search-stuId").val())

                },
                type : "get",
                dataType : "json",
                success : function (data) {

                    /*

                    <td>学生</td>
                    <td>班级</td>
                    <td>入学日期</td>
                    <td>状态</td>
                    <td>宿舍</td>
                    <td>入住日期</td>

                    */
                    var html = "";

                    //每一个n就是每一个市场活动对象
                    $.each(data.dataList,function (i,n) {

                        html += '<tr class="active">';
                        html += '<td>'+n.name+'</td>';
                        html += '<td>'+n.grade+'</td>';
                        html += '<td>'+n.create_date+'</td>';
                        html += '<td>'+n.state+'</td>';
                        html += '<td>'+n.dorm_id+'</td>';
                        html += '<td>'+n.move_into_date+'</td>';
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

<input type="hidden" id="hidden-stuId"/>



<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>Check-in Record</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">学 生</div>
                        <input class="form-control" type="text" id="search-stuId">
                    </div>
                </div>


                <button type="button" id="searchBtn" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <!--

                -->
                <button type="button" class="btn btn-default" id="fBtn"><span class="glyphicon"></span>刷新</button>
            </div>

        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">

                    <td>学生</td>
                    <td>班级</td>
                    <td>入学日期</td>
                    <td>状态</td>
                    <td>宿舍</td>
                    <td>入住日期</td>

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