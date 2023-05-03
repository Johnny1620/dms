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
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        //页面加载完毕
        $(function(){

            //导航中所有文本颜色为黑色
            $(".liClass > a").css("color" , "black");

            //默认选中导航菜单中的第一个菜单项
            $(".liClass:first").addClass("active");

            //第一个菜单项的文字变成白色
            $(".liClass:first > a").css("color" , "white");

            //给所有的菜单项注册鼠标单击事件
            $(".liClass").click(function(){
                //移除所有菜单项的激活状态
                $(".liClass").removeClass("active");
                //导航中所有文本颜色为黑色
                $(".liClass > a").css("color" , "black");
                //当前项目被选中
                $(this).addClass("active");
                //当前项目颜色变成白色
                $(this).children("a").css("color","white");
            });

            //在页面加载完毕后，在工作区打开相应的页面
            window.open("workbench/main/index.html","workareaFrame");

        });

    </script>

</head>
<body>


、


<!-- 退出系统的模态窗口 -->
<div class="modal fade" id="exitModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">账号注销</h4>
            </div>
            <div class="modal-body">
                <p>您确定要退出系统吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.href='login.jsp';">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 顶部 -->
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">DMS &nbsp;<span style="font-size: 12px;">&copy;2023&nbsp;</span></div>
    <div style="position: absolute; top: 15px; right: 20px;">
        <ul>
            <li class="dropdown user-dropdown">
                <a href="javascript:void(0)" style="text-decoration: none; color: white;" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span><span>&nbsp;</span> ${system.name} ${dorm.name} <span>&nbsp;</span>
                    <a href="javascript:void(0);" data-toggle="modal" data-target="#exitModal"><span class="glyphicon glyphicon-off"></span> 安全退出</a>

                <%--    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                        <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>--%>
                </a>

            </li>
        </ul>

    </div>
</div>

<!-- 中间 -->
<div id="center" style="position: absolute;top: 50px; bottom: 30px; left: 0px; right: 0px;">

    <!-- 导航 -->
    <div id="navigation" style="left: 0px; width: 18%; position: relative; height: 100%; overflow:auto;">

        <ul id="no1" class="nav nav-pills nav-stacked">
            <li class="liClass"><a href="workbench/main/index.html" target="workareaFrame"><span class="glyphicon glyphicon-home"></span> 主界面 </a></li>
            <li class="liClass"><a href="workbench/accountmanager/index.jsp" target="workareaFrame"><span class="glyphicon glyphicon-tag"></span> 宿管管理 </a></li>
            <li class="liClass"><a href="workbench/student/index.jsp" target="workareaFrame"><span class="glyphicon glyphicon-user"></span> 学生管理 </a></li>
            <li class="liClass">
                <a href="#no22" class="collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-stats"></span> 楼宇管理 </a>
                <ul id="no22" class="nav nav-pills nav-stacked collapse">
                    <li class="liClass"><a href="workbench/build/add.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 添加楼宇 </a></li>
                    <li class="liClass"><a href="workbench/build/manager.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 管理楼宇 </a></li>
                </ul>
            </li>
            <li class="liClass"><a href="workbench/dorm/index.jsp" target="workareaFrame"><span class="glyphicon glyphicon-file"></span> 宿舍管理 </a></li>

            <li class="liClass">
                <a href="#no222" class="collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-user"></span> 学生入住管理 </a>
                <ul id="no222" class="nav nav-pills nav-stacked collapse">
                    <li class="liClass"><a href="workbench/move/into/add.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 学生入住登记 </a></li>
                    <li class="liClass"><a href="workbench/move/into/list.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 学生入住记录 </a></li>

                </ul>
            </li>

            <li class="liClass">
                <a href="#no2222" class="collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-user"></span> 学生迁出管理 </a>
                <ul id="no2222" class="nav nav-pills nav-stacked collapse">
                    <li class="liClass"><a href="workbench/move/out/add.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 学生迁出登记 </a></li>
                    <li class="liClass"><a href="workbench/move/out/list.jsp" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 学生迁出记录 </a></li>

                </ul>
            </li>

            <li class="liClass"><a href="workbench/absent/index.jsp" target="workareaFrame"><span class="glyphicon glyphicon-send"></span> 学生缺勤记录 </a></li>



            <li class="liClass"><a href="javascript:void(0)" target="workareaFrame"><span class="glyphicon glyphicon-phone-alt"></span> 联系电话 </a></li>
            <li class="liClass">
                <a href="#no2" class="collapsed" data-toggle="collapse"><span class="glyphicon glyphicon-stats"></span> 扩展模块</a>
                <ul id="no2" class="nav nav-pills nav-stacked collapse">
                    <li class="liClass"><a href="javascript:void(0)" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 扩展模块A</a></li>
                    <li class="liClass"><a href="javascript:void(0)" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 扩展模块B</a></li>
                    <li class="liClass"><a href="javascript:void(0)" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 扩展模块C</a></li>
                    <li class="liClass"><a href="javascript:void(0)" target="workareaFrame">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-chevron-right"></span> 扩展模块D</a></li>
                </ul>
            </li>


        </ul>

        <!-- 分割线 -->
        <div id="divider1" style="position: absolute; top : 0px; right: 0px; width: 1px; height: 100% ; background-color: #B3B3B3;"></div>
    </div>

    <!-- 工作区 -->
    <div id="workarea" style="position: absolute; top : 0px; left: 18%; width: 82%; height: 100%;">
        <iframe style="border-width: 0px; width: 100%; height: 100%;" name="workareaFrame"></iframe>
    </div>

</div>

<div id="divider2" style="height: 1px; width: 100%; position: absolute;bottom: 30px; background-color: #B3B3B3;"></div>

<!-- 底部 -->
<div id="down" style="height: 30px; width: 100%; position: absolute;bottom: 0px;"></div>

</body>
</html>