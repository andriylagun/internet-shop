package com.internet.shop.controller;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fill")
public class FillDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        userService.create(new User("Bob", "bob", "123"));
        shoppingCartService.create(new ShoppingCart(1L));
        productService.create(new Product("Nokia 1100", 100.0));
        productService.create(new Product("Iphone 4s", 300.0));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
