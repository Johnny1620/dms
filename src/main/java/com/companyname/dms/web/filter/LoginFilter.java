package com.companyname.dms.web.filter;


import com.companyname.dms.settings.domain.DormAccount;
import com.companyname.dms.settings.domain.SystemAccount;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到验证有没有登录过的过滤器");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();

        //不应该被拦截的资源，自动放行请求
        if ("/login.jsp".equals(path) || "/settings/account/login.do".equals(path)){

            chain.doFilter(req, resp);

        //其他资源必须验证有没有登录过
        } else {

            HttpSession session = request.getSession();

            SystemAccount systemAccount = (SystemAccount) session.getAttribute("system");

            DormAccount dormAccount = (DormAccount) session.getAttribute("dorm");


            //如果systemAccount不为null，说明登录过
            if(systemAccount!=null || dormAccount!=null){

                chain.doFilter(req, resp);


            //没有登录过
            }else{

                //重定向到登录页
                response.sendRedirect(request.getContextPath() + "/login.jsp");

            }


        }


    }
}
