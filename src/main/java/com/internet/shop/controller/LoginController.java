package com.internet.shop.controller;

import com.internet.shop.exception.AuthException;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.User;
import com.internet.shop.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("username");
        try {
            String password = req.getParameter("password");
            User user = authenticationService.login(login, password);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("userId", user.getId());
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (AuthException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/homepage.jsp").forward(req, resp);
        }
    }
}
