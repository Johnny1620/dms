package com.companyname.dms.web.filter;

import com.companyname.dms.settings.domain.SystemAccount;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PowerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {


        System.out.println("过滤权限不足的过滤器");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        SystemAccount systemAccount = (SystemAccount) session.getAttribute("system");

        if (systemAccount!=null) {

            chain.doFilter(req, resp);

        } else {

            response.sendRedirect(request.getContextPath() + "/warn.html");
        }


    }
}
