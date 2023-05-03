package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Absent;
import com.companyname.dms.workbench.service.AbsentService;
import com.companyname.dms.workbench.service.BuildService;
import com.companyname.dms.workbench.service.Impl.AbsentServiceImpl;
import com.companyname.dms.workbench.service.Impl.BuildServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbsentController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/absent/pageList.do".equals(path)) {

            pageList(request,response);

        } else if ("/workbench/absent/save.do".equals(path)) {

            save(request,response);

        } else if ("/workbench/absent/delete.do".equals(path)) {

            delete(request,response);

        }



    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        String[] names = request.getParameterValues("id");

        AbsentService as = (AbsentService) ServiceFactory.getService(new AbsentServiceImpl());

        boolean success = as.delete(names);

        PrintJson.printJsonFlag(response,success);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        Absent a = new Absent();
        a.setStuName(request.getParameter("name"));
        a.setGrade(request.getParameter("grade"));
        a.setReason(request.getParameter("reason"));
        a.setDate(request.getParameter("date"));

        AbsentService as = (AbsentService) ServiceFactory.getService(new AbsentServiceImpl());

        boolean success = as.save(a);

        PrintJson.printJsonFlag(response,success);


    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);


        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //通过pageNo计算出，略过的记录数
        int skipCount = (pageNo-1)*pageSize;


        AbsentService as = (AbsentService) ServiceFactory.getService(new AbsentServiceImpl());

        PaginationVO<Absent> vo = as.pageList(skipCount,pageSize);


        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);

    }
}
