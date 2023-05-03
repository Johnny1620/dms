package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Build;
import com.companyname.dms.workbench.domain.Dorm;
import com.companyname.dms.workbench.domain.Student;
import com.companyname.dms.workbench.service.BuildService;
import com.companyname.dms.workbench.service.DormService;
import com.companyname.dms.workbench.service.Impl.BuildServiceImpl;
import com.companyname.dms.workbench.service.Impl.DormServiceImpl;
import com.companyname.dms.workbench.service.Impl.StudentServiceImpl;
import com.companyname.dms.workbench.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DormController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/dorm/pageList.do".equals(path)) {

            pageList(request,response);

        } else if ("/workbench/dorm/save.do".equals(path)) {

            save(request,response);

        } else if ("/workbench/dorm/delete.do".equals(path)) {

            deleteByIds(request,response);

        } else if ("/workbench/dorm/getDormInfoById.do".equals(path)) {

            getDormInfoById(request,response);
        } else if ("/workbench/dorm/update.do".equals(path)) {

            update(request,response);

        }


    }

    private void update(HttpServletRequest request, HttpServletResponse response) {


        Dorm dorm = new Dorm();
        dorm.setDormId(request.getParameter("dormId"));
        dorm.setType(request.getParameter("type"));
        dorm.setResidue(Integer.valueOf(request.getParameter("residue")));
        dorm.setPhone(request.getParameter("phone"));
        dorm.setBuildName(request.getParameter("buildName"));


        DormService ds = (DormService) ServiceFactory.getService(new DormServiceImpl());

        boolean success = false;
        success = ds.updateById(dorm);

        PrintJson.printJsonFlag(response,success);



    }

    private void getDormInfoById(HttpServletRequest request, HttpServletResponse response) {

        String dormId = request.getParameter("id");


        DormService ds = (DormService) ServiceFactory.getService(new DormServiceImpl());

        Dorm d = ds.getDormInfoById(dormId);

        

        PrintJson.printJsonObj(response,d);

    }

    private void deleteByIds(HttpServletRequest request, HttpServletResponse response) {

        String[] ids = request.getParameterValues("id");

        DormService ds = (DormService) ServiceFactory.getService(new DormServiceImpl());

        boolean success = false;

        success = ds.deleteByIds(ids);

        PrintJson.printJsonFlag(response,success);


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        Dorm d = new Dorm();
        d.setDormId(request.getParameter("id"));
        d.setType(request.getParameter("type"));
        d.setResidue(Integer.valueOf(request.getParameter("residue")));
        d.setPhone(request.getParameter("phone"));
        d.setBuildName(request.getParameter("buildName"));

        DormService ds = (DormService) ServiceFactory.getService(new DormServiceImpl());

        boolean success = false;
        success = ds.save(d);

        PrintJson.printJsonFlag(response,success);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        String dormId = request.getParameter("dormId");
        String type = request.getParameter("type");
        String residue = request.getParameter("residue");
        /*Integer integer = Integer.valueOf(residue);*/
        String buildName = request.getParameter("buildName");

        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dormId", dormId);
        map.put("type", type);
        map.put("residue",residue);
        map.put("buildName",buildName);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        DormService ds = (DormService) ServiceFactory.getService(new DormServiceImpl());

        PaginationVO<Dorm> vo = ds.pageList(map);
        System.out.println(vo);
        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);
    }



}
