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
            pageList(1,2);


            //为创建按钮绑定事件，打开添加操作的模态窗口
            $("#addBtn").click(function () {

                $("#createActivityModal").modal("show");

            })
            //为保存按钮绑定事件，执行添加操作
            $("#saveBtn").click(function () {

                $.ajax({

                    url : "workbench/dorm/save.do",
                    data : {

                        "id" : $.trim($("#create-id").val()),
                        "type" : $.trim($("#create-type").val()),
                        "residue" : $.trim($("#create-residue").val()),
                        "phone" : $.trim($("#create-phone").val()),
                        "buildName" : $.trim($("#create-buildName").val())

                    },
                    type : "post",
                    dataType : "json",
                    success : function (data) {

                        if(data.success){


                            pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

                            $("#activityAddForm")[0].reset();

                            //关闭添加操作的模态窗口
                            $("#createActivityModal").modal("hide");

                        }else{
                            alert("添加失败");
                        }
                    }

                })

            })




            //为查询按钮绑定事件，触发pageList方法
            $("#searchBtn").click(function () {


                //  点击查询按钮的时候，将搜索框中的信息保存起来,保存到隐藏域中

                $("#hidden-dormId").val($.trim($("#search-dormId").val()));
                $("#hidden-type").val($.trim($("#search-type").val()));
                $("#hidden-residue").val($.trim($("#search-residue").val()));
                $("#hidden-build").val($.trim($("#search-build").val()));


                pageList(1,2);

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


                        //发送AJAX请求，执行删除操作
                        $.ajax({

                            url : "workbench/dorm/delete.do",
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

                    /*alert(id);*/

                    $.ajax({

                        url : "workbench/dorm/getDormInfoById.do",
                        data : {

                            "id" : id

                        },
                        type : "get",
                        dataType : "json",
                        success : function (data) {

                            /*
                                   data ：{成员信息}
                             */

                            //处理响应回来的数据
                            $("#edit-dormId").val(data.dormId);
                            $("#edit-type").val(data.type);
                            $("#edit-residue").val(data.residue);
                            $("#edit-phone").val(data.phone);
                            $("#edit-buildName").val(data.buildName);


                            //所有的值都填写好之后，打开修改操作的模态窗口
                            $("#editActivityModal").modal("show");

                        }

                    })

                }

            })


            //为更新按钮绑定事件，执行更新操作
            $("#updateBtn").click(function () {

                $.ajax({

                    url : "workbench/dorm/update.do",
                    data : {

                        "dormId" : $.trim($("#edit-dormId").val()),
                        "type" : $.trim($("#edit-type").val()),
                        "residue" : $.trim($("#edit-residue").val()),
                        "phone" : $.trim($("#edit-phone").val()),
                        "buildName" : $.trim($("#edit-buildName").val())

                    },
                    type : "post",
                    dataType : "json",
                    success : function (data) {

                        if(data.success){

                            //修改操作后，维持在当前页，维持每页展现的记录数
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
            $("#search-dormId").val($.trim($("#hidden-dormId").val()));
            $("#search-type").val($.trim($("#hidden-type").val()));
            $("#search-residue").val($.trim($("#hidden-residue").val()));
            $("#search-build").val($.trim($("#hidden-build").val()));

            $.ajax({

                url : "workbench/dorm/pageList.do",
                data : {
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "dormId" : $.trim($("#search-dormId").val()),
                    "type" : $.trim($("#search-type").val()),
                    "residue" : $.trim(($("#search-residue").val())),
                    "buildName" : $.trim($("#search-build").val()),

                },
                type : "get",
                dataType : "json",
                success : function (data) {

                    var html = "";

                    //每一个n就是每一个市场活动对象
                    $.each(data.dataList,function (i,n) {

                        html += '<tr class="active">';
                        html += '<td><input type="checkbox" name="xz" value="'+n.dormId+'"/></td>';

                        html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/dormaccount/detail.do?id='+n.dormId+'\';">'+n.dormId+'</a></td>';
                        html += '<td>'+n.type+'</td>';
                        html += '<td>'+n.residue+'</td>';
                        html += '<td>'+n.phone+'</td>';
                        html += '<td>'+n.buildName+'</td>';
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

<input type="hidden" id="hidden-dormId"/>
<input type="hidden" id="hidden-type"/>
<input type="hidden" id="hidden-residue"/>
<input type="hidden" id="hidden-build"/>




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
                    <%--<input type="hidden" id="edit-id"/>--%>

                    <div class="form-group">
                        <label for="create-marketActivityOwner" class="col-sm-2 control-label">宿 舍<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" readonly="true" class="form-control" id="edit-dormId">  <%--业务主键--%>

                        </div>
                        <label for="create-marketActivityName" class="col-sm-2 control-label">类 型</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-type">
                        </div>


                    </div>

                    <div class="form-group">

                        <label for="create-marketActivityName" class="col-sm-2 control-label">空余床位</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-residue">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-startTime" class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-phone">
                        </div>
                        <label for="create-endTime" class="col-sm-2 control-label">楼 宇</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-buildName">
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
<%--<input type="hidden" id="hidden-name"/>
    <input type="hidden" id="hidden-gender"/>

    <input type="hidden" id="hidden-grade"/>
    <input type="hidden" id="hidden-major"/>

    <input type="hidden" id="hidden-state"/>
    <input type="hidden" id="hidden-createDate"/>
    <input type="hidden" id="hidden-dormId"/>
--%>
<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建</h4>
            </div>
            <div class="modal-body">

                <form id="activityAddForm" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-marketActivityOwner" class="col-sm-2 control-label">宿 舍</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-id">

                        </div>
                        <label for="create-marketActivityName" class="col-sm-2 control-label">类 型</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-type">
                        </div>


                    </div>

                    <div class="form-group">

                        <label for="create-marketActivityName" class="col-sm-2 control-label">空余床位</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-residue">
                        </div>

                        <label for="create-startTime" class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-phone">
                        </div>
                    </div>

                    <div class="form-group">

                        <label for="create-endTime" class="col-sm-2 control-label">楼 宇</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-buildName">
                        </div>
                    </div>



                </form>

            </div>
            <div class="modal-footer">
                <!--

                    data-dismiss="modal"
                        表示关闭模态窗口

                -->
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>






<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>Student Manage</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">宿 舍</div>
                        <input class="form-control" type="text" id="search-dormId">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">类型</div>
                        <input class="form-control" type="text" id="search-type">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">空余床位</div>
                        <input class="form-control" type="text" id="search-residue">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">楼宇</div>
                        <input class="form-control" type="text" id="search-build">
                    </div>
                </div>


                <button type="button" id="searchBtn" class="btn btn-default">查询</button>




            </form>
        </div>
        <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <!--

                -->
                <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
                <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>

                <%--<button type="button" class="btn btn-primary" id="renewBtn"><span class="glyphicon glyphicon-pencil"></span> 更新</button>--%>

            </div>

        </div>
        <div style="position: relative;top: 10px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">

                    <td><input type="checkbox" id="qx"/></td>
                    <td>宿舍</td>
                    <td>类型</td>
                    <td>空余床位</td>
                    <td>宿舍电话</td>
                    <td>楼宇</td>


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