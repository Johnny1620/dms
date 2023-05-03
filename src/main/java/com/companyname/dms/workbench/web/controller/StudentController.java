package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.settings.service.Impl.DormAccountServiceImpl;
import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Student;
import com.companyname.dms.workbench.service.Impl.StudentServiceImpl;
import com.companyname.dms.workbench.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/student/pageList.do".equals(path)) {
            //展示学生信息
            pageList(request,response);

        } else if ("/workbench/student/save.do".equals(path)) {

            //保存学生信息
            save(request,response);
        } else if ("/workbench/student/delete.do".equals(path)) {

            deleteByIds(request,response);

        } else if ("/workbench/student/getStuInfoById.do".equals(path)) {

            getStuInfoById(request,response);
        } else if ("/workbench/student/update.do".equals(path)) {
            update(request,response);
        }



    }

    private void update(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("student控制器：执行更新操作");

        Student stu = new Student();
        stu.setId(Long.valueOf(request.getParameter("id")));
        stu.setName(request.getParameter("name"));
        stu.setGender(request.getParameter("gender"));
        stu.setAge(Integer.valueOf(request.getParameter("age")));
        stu.setGrade(request.getParameter("grade"));
        stu.setMajor(request.getParameter("major"));
        stu.setCreateDate(request.getParameter("createDate"));
        stu.setState(request.getParameter("state"));
        stu.setDormId(request.getParameter("dormId"));

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        boolean success = false;
        success = ss.updateById(stu);

        PrintJson.printJsonFlag(response,success);


    }

    private void getStuInfoById(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行修改操作其一：查询要修改的学生信息");

        String id = request.getParameter("id");

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        Student stu = ss.getStuInfoById(id);

        PrintJson.printJsonObj(response,stu);
    }

    private void deleteByIds(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行删除操作");
        String[] ids = request.getParameterValues("id");

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        boolean success = false;

        success = ss.delete(ids);

        PrintJson.printJsonFlag(response,success);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("student控制器：进去save方法");

        Student stu = new Student();
        stu.setName(request.getParameter("name"));
        stu.setGender(request.getParameter("gender"));
        stu.setAge(Integer.valueOf(request.getParameter("age")));
        stu.setGrade(request.getParameter("grade"));
        stu.setMajor(request.getParameter("major"));
        stu.setCreateDate(request.getParameter("createDate"));
        stu.setState(request.getParameter("state"));
        stu.setDormId(request.getParameter("dormId"));

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        boolean success = false;
        success = ss.save(stu);

        PrintJson.printJsonFlag(response,success);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String grade = request.getParameter("grade");
        String major = request.getParameter("major");
        String state = request.getParameter("state");
        String createDate = request.getParameter("createDate");
        String dormId = request.getParameter("dormId");

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);

        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("gender", gender);
        map.put("grade",grade);
        map.put("major",major);
        map.put("state",state);
        map.put("createDate",createDate);
        map.put("dormId",dormId);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        PaginationVO<Student> vo = ss.pageList(map);

        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);
    }
}
