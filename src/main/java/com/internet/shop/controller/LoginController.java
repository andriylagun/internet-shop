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
    public static final String USER_ID = "userId";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = authenticationService.login(login, password);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute(USER_ID, user.getId());
            resp.sendRedirect(req.getContextPath() + "/index");
        } catch (AuthException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }
}
