package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.settings.service.Impl.DormAccountServiceImpl;
import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.vo.PaginationVO;
import com.companyname.dms.workbench.domain.Build;
import com.companyname.dms.workbench.service.BuildService;
import com.companyname.dms.workbench.service.Impl.BuildServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/build/save.do".equals(path)) {

            save(request,response);

        } else if ("/workbench/build/pageList.do".equals(path)) {

            pageList(request,response);

        } else if ("/workbench/build/delete.do".equals(path)) {

            deleteByIds(request,response);

        }else if ("/workbench/build/getDormNameId.do".equals(path)) {

            getDormNameId(request,response);

        }
        else if ("/workbench/build/getBuildById.do".equals(path)) {

            getBuildAndDormById(request,response);
        } else if ("/workbench/build/update.do".equals(path)) {

            update(request,response);

        }



    }

    private void getDormNameId(HttpServletRequest request, HttpServletResponse response) {

        DormAccountService das= (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        List<Map<String,Object>> aList = das.getDormNameIdALL();

        Map<String,Object> map = new HashMap<>();
        map.put("aList",aList);

        PrintJson.printJsonObj(response,map);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行更新操作");

        Build build = new Build();
        build.setId(Long.valueOf(request.getParameter("id")));
        build.setName(request.getParameter("name"));
        build.setInfo(request.getParameter("info"));
        build.setDate(request.getParameter("date"));

        String accountId = request.getParameter("accoutId");
        if(accountId!=null&&accountId!=""){
            build.setAccount_id(Long.valueOf(accountId));
        }

        BuildService bs = (BuildService) ServiceFactory.getService(new BuildServiceImpl());

        boolean success = false;
        success = bs.update(build);

        PrintJson.printJsonFlag(response,success);


    }

    private void getBuildAndDormById(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");

        BuildService bs = (BuildService) ServiceFactory.getService(new BuildServiceImpl());
        //需要修改的宿舍楼信息
        Map<String,Object> build = bs.getBuildAndDormNameById(id);

        DormAccountService das= (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());
        List<Map<String,Object>> aList = das.getDormNameIdALL();


        Map<String,Object> map = new HashMap<>();
        map.put("aList",aList);
        map.put("build",build);

        PrintJson.printJsonObj(response,map);

    }

    private void deleteByIds(HttpServletRequest request, HttpServletResponse response) {

        String[] ids = request.getParameterValues("id");

        BuildService bs = (BuildService) ServiceFactory.getService(new BuildServiceImpl());

        boolean success = false;
        success = bs.deleteByIds(ids);

        PrintJson.printJsonFlag(response,success);


    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String info = request.getParameter("info");
        String date = request.getParameter("date");

        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);

        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("info", info);
        map.put("date",date);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        BuildService bs = (BuildService) ServiceFactory.getService(new BuildServiceImpl());

        PaginationVO<Map<String,Object>> vo = bs.pageList(map);

        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {


        Build b = new Build();
        b.setName(request.getParameter("name"));
        b.setInfo(request.getParameter("info"));
        b.setAccount_id(Long.valueOf(request.getParameter("dormId")));
        b.setDate(request.getParameter("date"));

        BuildService bs= (BuildService) ServiceFactory.getService(new BuildServiceImpl());

        boolean success = false;
        success = bs.save(b);

        PrintJson.printJsonFlag(response,success);

    }
}
