package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        resp.setContentType("text/html");
        if (session == null) {
            resp.getWriter().println("<h2>No active session. Expired or never created.</h2>" +
                    "<a href='login'>Login again</a>");
        } else {
            resp.getWriter().println(
                    "<h2>Session active</h2>" +
                            "<p>Username: " + session.getAttribute("username") + "</p>" +
                            "<p>Password: " + session.getAttribute("password") + "</p>" +
                            "<p>Session ID: " + session.getId() + "</p>"
            );
        }
    }
}