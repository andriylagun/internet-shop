package com.internet.shop.filter;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final String USER_ID = "userId";
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) INJECTOR.getInstance(UserService.class);
    private final Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/products", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart/products/remove", Set.of(Role.RoleName.USER));
        protectedUrls.put("/shopping-cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/add", Set.of(Role.RoleName.USER));
        protectedUrls.put("/user/orders", Set.of(Role.RoleName.USER));
        protectedUrls.put("/users", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/delete", Set.of(Role.RoleName.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String servletPath = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        if (!protectedUrls.containsKey(servletPath)
                || isUserAuthorized(userService.get(userId), protectedUrls.get(servletPath))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isUserAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role userRole : user.getRoles()) {
            for (Role.RoleName authorizedRole : authorizedRoles) {
                if (userRole.getRoleName().equals(authorizedRole)) {
                    return true;
                }
            }
        }
        return false;
    }
}
