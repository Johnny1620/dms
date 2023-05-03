package com.companyname.dms.workbench.web.controller;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.settings.service.Impl.DormAccountServiceImpl;
import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;
import com.companyname.dms.vo.PaginationVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DormAccountController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if ("/workbench/dormaccount/pageList.do".equals(path)) {

            //展示宿管成员信息列表
            pageList(request,response);

        } else if ("/workbench/dormaccount/save.do".equals(path)) {

            //保存宿管成员信息
            save(request,response);

        } else if ("/workbench/dormaccount/delete.do".equals(path)) {

            //删除宿管成员信息
            delete(request,response);

        } else if ("/workbench/dormaccount/getDormInfoById.do".equals(path)) {
            getDormInfoById(request,response);
        } else if ("/workbench/dormaccount/update.do".equals(path)) {
            updateById(request,response);
        }

    }

    private void updateById(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行更新操作");

        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        DormAccount account = new DormAccount();

        account.setId(id);
        account.setName(name);
        account.setGender(gender);
        account.setPosition(position);
        account.setPhone(phone);
        account.setUsername(username);
        account.setPassword(password);

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        boolean success = false;
        success = das.updateById(account);

        PrintJson.printJsonFlag(response,success);

    }

    private void getDormInfoById(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行修改操作其一：查询");

        String id = request.getParameter("id");

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        DormAccount da = das.getDormInfoById(id);

        PrintJson.printJsonObj(response,da);


    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行删除操作");
        String[] ids = request.getParameterValues("id");

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        boolean success = false;

        success = das.delete(ids);

        PrintJson.printJsonFlag(response,success);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("dormaccount控制器：进去save方法");

        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DormAccount account = new DormAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setName(name);
        account.setGender(gender);
        account.setPhone(phone);
        account.setPosition(position);

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        boolean success = false;
        success = das.save(account);

        PrintJson.printJsonFlag(response,success);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {


        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String position = request.getParameter("position");

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
        map.put("position",position);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        PaginationVO<DormAccount> vo = das.pageList(map);

        //vo--> {"total":100,"dataList":[{成员信息1},{2},{3}]}
        PrintJson.printJsonObj(response, vo);

    }
}
