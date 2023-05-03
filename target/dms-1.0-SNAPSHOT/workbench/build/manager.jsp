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

                            url : "workbench/build/delete.do",
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





            //为修改按钮绑定事件，打开修改操作的模态窗口
            $("#editBtn").click(function () {

                var $xz = $("input[name=xz]:checked");

                if($xz.length==0){

                    alert("请选择需要修改的记录");

                }else if($xz.length>1){

                    alert("只能选择其中一条记录进行修改");

                //只选一条才能修改
                }else{

                    var id = $xz.val();

                    $.ajax({

                        url : "workbench/build/getBuildById.do",
                        data : {

                            "id" : id

                        },
                        type : "get",
                        dataType : "json",
                        success : function (data) {



                            //处理宿管下拉框
                            var html = "<option>请选择</option>";

                            $.each(data.aList,function (i,n) {

                                html += "<option value='"+n.id+"'>"+n.name+"</option>";

                            })

                            /*alert(html);*/

                            $("#edit-dormAccount").html(html);

                            //处理响应回来的数据
                            $("#edit-id").val(data.build.id);
                            $("#edit-name").val(data.build.name);
                            $("#edit-info").val(data.build.info);
                            $("#edit-date").val(data.build.date);
                            $("#edit-dormAccount").val(data.build.name);


                            //所有的值都填写好之后，打开修改操作的模态窗口
                            $("#editActivityModal").modal("show");


                        }

                    })

                }

            })


            //为更新按钮绑定事件，执行更新操作
            $("#updateBtn").click(function () {

                $.ajax({

                    url : "workbench/build/update.do",
                    data : {

                        "id" : $.trim($("#edit-id").val()),
                        "name" : $.trim($("#edit-name").val()),
                        "info" : $.trim($("#edit-info").val()),
                        "date" : $.trim($("#edit-date").val()),
                        "accoutId" : $.trim($("#edit-dormAccount").val())

                    },
                    type : "post",
                    dataType : "json",
                    success : function (data) {

                        if(data.success){
                            /*
                                修改操作后，应该维持在当前页，维持每页展现的记录数
                             */
                            pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
                                ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                            //关闭修改操作的模态窗口
                            $("#editActivityModal").modal("hide");

                        }else{
                            alert("修改失败");
                        }

                    }

                })

            })










            //页面加载后，执行的函数到此为止
        })








        function pageList(pageNo,pageSize) {

            //将全选的复选框的√干掉
            $("#qx").prop("checked",false);

            //查询前，将隐藏域中保存的信息取出来，重新赋予到搜索框中
            $("#search-name").val($.trim($("#hidden-name").val()));
            $("#search-info").val($.trim($("#hidden-info").val()));
            $("#search-date").val($.trim($("#hidden-date").val()));

            $.ajax({

                url : "workbench/build/pageList.do",
                data : {

                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "name" : $.trim($("#search-name").val()),
                    "info" : $.trim($("#search-info").val()),
                    "date" : $.trim(($("#search-date").val()))

                },
                type : "get",
                dataType : "json",
                success : function (data) {

                    var html = "";

                    //每一个n就是每一个市场活动对象
                    $.each(data.dataList,function (i,n) {

                        html += '<tr class="active">';
                        html += '<td><input type="checkbox" name="xz" value="'+n.id+'"/></td>';
                        html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/dormaccount/detail.do?id='+n.id+'\';">'+n.name+'</a></td>';
                        html += '<td>'+n.info+'</td>';
                        html += '<td>'+n.date+'</td>';

                        html += '<td>'+n.dormName+'</td>';
                        html += '<td>'+n.position+'</td>';
                        html += '<td>'+n.phone+'</td>';
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

<input type="hidden" id="hidden-name"/>
<input type="hidden" id="hidden-info"/>
<input type="hidden" id="hidden-date"/>



<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">

                    <%--隐藏域id--%>
                    <input type="hidden" id="edit-id"/>

                    <div class="form-group">
                        <label for="edit-marketActivityName" class="col-sm-2 control-label">楼宇<span style="font-size: 15px; color: red;">*</span></label>

                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name">
                        </div>
                        <label for="edit-marketActivityName" class="col-sm-2 control-label">所属学院<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-info">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-marketActivityOwner" class="col-sm-2 control-label">宿管</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-dormAccount">

                            <%--
                            <option value='"+n.dormid+"'>"+n.dornName+"</option>
                            --%>

                            </select>



                        </div>
                        <label for="edit-endTime" class="col-sm-2 control-label">修改日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="time form-control" id="edit-date">
                        </div>
                    </div>


                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="updateBtn">更新</button>
            </div>
        </div>
    </div>
</div>








<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>Build Manage</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">楼宇</div>
                        <input class="form-control" type="text" id="search-name">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所属学院</div>
                        <input class="form-control" type="text" id="search-info">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">创建日期</div>
                        <input class="form-control" type="text" id="search-date">
                    </div>
                </div>

                <button type="button" id="searchBtn" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <!--

                    点击创建按钮，观察两个属性和属性值

                    data-toggle="modal"：
                        表示触发该按钮，将要打开一个模态窗口

                    data-target="#createActivityModal"：
                        表示要打开哪个模态窗口，通过#id的形式找到该窗口


                    现在我们是以属性和属性值的方式写在了button元素中，用来打开模态窗口
                    但是这样做是有问题的：
                        问题在于没有办法对按钮的功能进行扩充

                    所以未来的实际项目开发，对于触发模态窗口的操作，一定不要写死在元素当中，
                    应该由我们自己写js代码来操作



                -->
                <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
            </div>

        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">

                    <td><input type="checkbox" id="qx"/></td>
                    <td>楼宇</td>
                    <td>所属学院</td>
                    <td>修改日期</td>
                    <td>宿管</td>
                    <td>宿管宿舍</td>
                    <td>联系电话</td>

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