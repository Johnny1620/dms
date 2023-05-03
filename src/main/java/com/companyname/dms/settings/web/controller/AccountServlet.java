package com.companyname.dms.settings.web.controller;

import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.domain.SystemAccount;
import com.companyname.dms.settings.service.DormAccountService;
import com.companyname.dms.settings.service.Impl.DormAccountServiceImpl;
import com.companyname.dms.settings.service.Impl.SystemAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.companyname.dms.settings.service.SystemAccountService;
import com.companyname.dms.utils.PrintJson;
import com.companyname.dms.utils.ServiceFactory;

public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");


        if ("system".equals(type)) {

            loginBySystem(request,response);

        } else if ("dorm".equals(type)) {

            loginByDormAccount(request,response);


        }


    }

    private void loginByDormAccount(HttpServletRequest request, HttpServletResponse response) {

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        DormAccountService das = (DormAccountService) ServiceFactory.getService(new DormAccountServiceImpl());

        try {

            DormAccount da = das.login(loginAct, loginPwd);

            request.getSession().setAttribute("dorm",da);

            PrintJson.printJsonFlag(response, true);

        } catch (Exception e) {
            e.printStackTrace();

            String msg = e.getMessage();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success", false);
            map.put("msg", msg);

            PrintJson.printJsonObj(response, map);

        }



    }

    public void loginBySystem(HttpServletRequest request, HttpServletResponse response) {

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        SystemAccountService service = (SystemAccountService) ServiceFactory.getService(new SystemAccountServiceImpl());

        try {

            SystemAccount account = service.login(loginAct, loginPwd);

            request.getSession().setAttribute("system",account);

            PrintJson.printJsonFlag(response, true);

        } catch (Exception e) {
            e.printStackTrace();

            String msg = e.getMessage();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success", false);
            map.put("msg", msg);

            PrintJson.printJsonObj(response, map);

        }




    }
}
