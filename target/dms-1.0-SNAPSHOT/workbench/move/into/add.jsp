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





        function displayTime(){
            var time = new Date();
            var strTime = time.toLocaleString();
            document.getElementById("currentDate").innerHTML = strTime;
        }
        // 每隔1秒调用displayTime()函数
        function start(){
            // 从这行代码执行结束开始,则会不间断的,每隔1000毫秒调用一次displayTime()函数.
            v = window.setInterval("displayTime()", 1000);
        }
        start();




        $(function () {


            $.ajax({

                url : "workbench/move/into/getStuNameId.do",
                data : { },
                type : "get",
                dataType : "json",
                success : function (data) {

                    //处理下拉框
                    var html = "<option></option>";

                    $.each(data,function (i,n) {

                        html += "<option value='"+n.id+"'>"+n.name+"</option>";

                    })

                    $("#create-stu").html(html);

                }

            })




            $(".time").datetimepicker({
                minView: "month",
                language:  'zh-CN',
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left"
            });




            //保存并输出日志
            var html = "";

            //为保存按钮绑定事件，执行添加操作
            $("#saveBtn").click(function () {


                $.ajax({

                    url : "workbench/move/into/save.do",
                    data : {

                        "stuId" : $.trim($("#create-stu").val()),
                        "state" : $.trim($("#create-state").val()),
                        "dormId" : $.trim($("#create-dorm").val()),
                        "date" : $.trim($("#create-date").val())

                    },
                    type : "post",
                    dataType : "json",
                    success : function (data) {

                        if(data.success){



                            //添加成功后

                            alert("添加成功");

                            var time = new Date();
                            var strTime = time.toLocaleString();

              /*            //获取下拉框节点对象
                            var select = document.getElementById("create-stu");
                            //获取下拉框选中的索引
                            var index = select.selectedIndex;*/


                            html += '<tr class="active" >';
                            html += '[Current Date = '+strTime+']-';
                            html += '  ADMINISTRATORS[ ROOT ]  ';
                            html +='  Check-in  '+$("#create-stu option:selected").text()+'    '+$("#create-state").val();
                            html +='  :  '+$("#create-dorm").val();
                            html += '</tr>';

                            document.getElementById("mark").innerHTML = html;
                            /*$("#mark").html(html);*/

                            //清空添加操作模态窗口中的数据

                            $("#activityAddForm")[0].reset();


                        }else{

                            alert("添加失败");

                        }




                    }

                })

            })

            $("#clean").click(function () {
                $("#activityAddForm")[0].reset();
            })

            //页面加载后，加载的函数到此为止
        })




    </script>
</head>
<body>


<div class="modal-body">

    <div>
        <div style="position: relative; left: 10px; top: -10px;">
            <div class="page-header">
                <h3>Move into (a dorm)</h3>
            </div>
        </div>
    </div>

    <div>&nbsp;</div>

    <div class="btn-toolbar" role="toolbar" style="height: 80px;">
        <form id="activityAddForm" class="form-horizontal" role="form">


            <div class="form-group">
                <label for="create-marketActivityOwner" class="col-sm-2 control-label">学 生</label>
                <div class="col-sm-10" style="width: 300px;">
                    <select class="form-control" id="create-stu">

                        <%--
                        <option value='"+n.id+"'>"+n.Name+"</option>
                        --%>

                    </select>

                </div>
                <label for="create-marketActivityName" class="col-sm-2 control-label">状 态</label>
                <div class="col-sm-10" style="width: 300px;">
                    <select class="form-control" id="create-state">
                        <option>请选择</option>
                        <option value="待入住">待入住</option>
                        <option value="已入住">已入住</option>
                    </select>

                </div>
            </div>

            <div class="form-group">
                <label for="create-startTime" class="col-sm-2 control-label">入住宿舍</label>
                <div class="col-sm-10" style="width: 300px;">
                    <input type="text" class="form-control" id="create-dorm">



                </div>
                <label for="create-endTime" class="col-sm-2 control-label">入住日期</label>
                <div class="col-sm-10" style="width: 300px;">
                    <input type="text" class="form-control time" id="create-date">
                </div>
            </div>

            <div class="form-group">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <button type="button" class="btn btn-primary" id="saveBtn">保 存</button>
                <button type="button" class="btn btn-primary" id="clean">重 置</button>

            </div>



        </form>
    </div>



</div>

<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>
<div>&nbsp;</div>

<span class="carousel-control"  style="position: relative;top: 30px; left: 10px;">
                            <span style="color: green;font-size:smaller " > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志信息&nbsp;&nbsp; </span>
                </span>

<span class="active"  style="position: relative;top: 30px; left: 10px;">
                           <%-- <span style="color: green;font-size:smaller " > 日志信息 </span>--%>
                <span style="color: green">TIME:</span>
                <span id="currentDate" style="color: green"></span>

                <div style="position:absolute;left:-1px;top:24px;width:841px; height:300px" > <%--border:1px solid #000;--%>

                    <table class="table table-hover">
                        <thead>
                        <tr></tr>
                        </thead>
                        <tbody id="mark" style="color: #00B83F">

                        </tbody>

                    </table>

                </div>


            </span>







</body>
</html>
