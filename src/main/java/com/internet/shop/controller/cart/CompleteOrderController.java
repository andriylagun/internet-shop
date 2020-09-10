package com.internet.shop.controller.cart;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order/add")
public class CompleteOrderController extends HttpServlet {
    private static final String SHOPPING_CART_ID = "scId";
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long scId = Long.valueOf(req.getParameter(SHOPPING_CART_ID));
        ShoppingCart shoppingCart = shoppingCartService.get(scId);
        Order order = orderService.completeOrder(shoppingCart);
        resp.sendRedirect(req.getContextPath() + "/order/info?id=" + order.getId());
    }
}
