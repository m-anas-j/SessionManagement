package com.example.sessionmanagement;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookieArray = request.getCookies();
        Cookie tempCookie = null;
        for (Cookie c:cookieArray)
        {
            if (c.getName().equals("usernameKey"))
                tempCookie = c;
        }

        if (tempCookie!=null)
        {
            tempCookie.setMaxAge(0); //since cookie does not have any
            // invalidate method like HttpSession object, we need to
            // invalidate it by setting its lifetime to 0.
        }
        PrintWriter out = response.getWriter();
        out.println("<h1> Successfully logged out </h1>");

        RequestDispatcher rd = request.getRequestDispatcher("login.html");
        rd.include(request, response);
    }
}
