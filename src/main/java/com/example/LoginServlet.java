package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.SecureRandom;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
    private final SecureRandom random = new SecureRandom();

    private String generate(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession old = req.getSession(false);
        if (old != null) old.invalidate();

        String username = "user_" + generate(5);
        String password = generate(10);

        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setMaxInactiveInterval(120);

        resp.setContentType("text/html");
        resp.getWriter().println(
                "<h2>Session created</h2>" +
                        "<p>Username: " + username + "</p>" +
                        "<p>Password: " + password + "</p>" +
                        "<p>Session ID: " + session.getId() + "</p>" +
                        "<p>Expires in 2 minutes of inactivity.</p>" +
                        "<a href='check'>Check session</a>"
        );
    }
}