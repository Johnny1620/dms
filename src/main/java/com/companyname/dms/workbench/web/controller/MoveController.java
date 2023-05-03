package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.utils.SqlSessionUtil;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Dorm;
import com.companyname.dms.workbench.domain.MoveInto;
import com.companyname.dms.workbench.domain.MoveOut;
import com.companyname.dms.workbench.domain.Student;
import com.companyname.dms.workbench.service.DormService;
import com.companyname.dms.workbench.service.Impl.DormServiceImpl;
import com.companyname.dms.workbench.service.Impl.MoveIntoServiceImpl;
import com.companyname.dms.workbench.service.Impl.MoveOutServiceImpl;
import com.companyname.dms.workbench.service.Impl.StudentServiceImpl;
import com.companyname.dms.workbench.service.MoveIntoService;
import com.companyname.dms.workbench.service.MoveOutService;
import com.companyname.dms.workbench.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/move/into/getStuNameId.do".equals(path)) {

            getStuNameId(request,response);

        } else if ("/workbench/move/into/save.do".equals(path)) {

            saveInto(request,response);

        } else if ("/workbench/move/into/pageList.do".equals(path)) {

            pageList(request,response);

        } else if ("/workbench/move/out/getStuNameId.do".equals(path)) {

            getStuNameIdByInto(request,response);

        } else if ("/workbench/move/out/save.do".equals(path)) {

            saveOut(request,response);

        } else if ("/workbench/move/out/pageList.do".equals(path)) {


            pageListToOut(request,response);

        }



    }

    private void pageListToOut(HttpServletRequest request, HttpServletResponse response) {

        String stuName = request.getParameter("stuName");

        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);


        //展现第几页
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //通过pageNo计算出，略过的记录数
        int skipCount = (pageNo-1)*pageSize;


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName", stuName);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());



        PaginationVO<Map<String,Object>> vo = ss.pageListByOut(map);


        System.out.println(vo);

        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);

    }

    private void saveOut(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("stuId");

        Student stu = new Student();
        stu.setId(Long.valueOf(id));
        stu.setState(request.getParameter("state"));
        stu.setDormId("无");

        MoveOut out = new MoveOut();
        out.setStuId(Long.valueOf(id));
        out.setMoveOutDate(request.getParameter("outDate"));

        boolean success = false;

        StudentService studentService = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        success = studentService.updateById(stu);

        MoveOutService moveOutService = (MoveOutService) ServiceFactory.getService(new MoveOutServiceImpl());

        success = moveOutService.saveStuAndDate(out);

        MoveIntoService moveIntoService = (MoveIntoService) ServiceFactory.getService(new MoveIntoServiceImpl());

        success = moveIntoService.deleteByStuId(id);

        PrintJson.printJsonFlag(response,success);



    }

    private void getStuNameIdByInto(HttpServletRequest request, HttpServletResponse response) {

        StudentService studentService = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        List<Map<String,Object>> list = studentService.getIntoStu();
        System.out.println(list);

  /*      Map<String,Object> map = new HashMap<>();
        map.put("sList",list);*/

        PrintJson.printJsonObj(response,list);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        String stuName = request.getParameter("stuName");

        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);

        //展现第几页
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("stuName", stuName);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());



        PaginationVO<Map<String,Object>> vo = ss.pageListByInto(map);
        System.out.println(vo);
        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);



    }

    private void saveInto(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("stuId");

        Student stu = new Student();
        stu.setId(Long.valueOf(id));
        stu.setState(request.getParameter("state"));
        stu.setDormId(request.getParameter("dormId"));

        MoveInto into = new MoveInto();
        into.setStuId(Long.valueOf(id));
        into.setMoveIntDate(request.getParameter("date"));

        boolean success = false;

        StudentService studentService = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        success = studentService.updateById(stu);

        MoveIntoService moveIntoService = (MoveIntoService) ServiceFactory.getService(new MoveIntoServiceImpl());

        success = moveIntoService.saveStuAndDate(into);

        MoveOutService moveOutService = (MoveOutService) ServiceFactory.getService(new MoveOutServiceImpl());

        success = moveOutService.deleteByStuId(id);



        PrintJson.printJsonFlag(response,success);


    }

    private void getStuNameId(HttpServletRequest request, HttpServletResponse response) {

        StudentService studentService = (StudentService) ServiceFactory.getService(new StudentServiceImpl());

        List<Map<String,Object>> list = studentService.getNotIntoStu();
        System.out.println(list);

  /*      Map<String,Object> map = new HashMap<>();
        map.put("sList",list);*/

        PrintJson.printJsonObj(response,list);

    }
}
