package com.example.sessionmanagement;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessfulServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
/**
 * We are checking whether a cookie exists with the usernameKey key. If
 * such a cookie exists, then we know for sure that the user has logged
 * in, and thus we have authenticated that user.
 * If the usernameKey cookie does not exist, then we know for sure that
 * the user session has not been saved or verified, hence the session is
 * invalid. Thus, we redirect the user back to the login page again.
 * We give the user an option to logout, i.e. invalidate his session by
 * a logout button. The logout button will call the LogoutServlet, which
 * will process the user's logout request.
**/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookieArray = request.getCookies();
        PrintWriter out = response.getWriter();
        for (Cookie c:cookieArray)
        {
            if (c.getName().equals("usernameKey"))
            {
                out.println("<h1> Welcome! </h1>");
                out.println("<h2> To log out, please press the following button.</h2>");
                out.println("<br>\n" +
                        "<form method=\"post\" action=\"logout\"> <input" +
                        " type=\"submit\" value=\"Log out\"> ");
            }
            else
            {
                out.println("<h1> Invalid session. Please log in again. " +
                        "</h1>");
                RequestDispatcher rd = request.getRequestDispatcher(
                        "login.html");
                rd.include(request, response);
            }
        }
    }
}
