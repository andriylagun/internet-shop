package com.internet.shop.controller.product;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products/add")
public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", productService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        if (price.matches("^\\d+[.]*\\d{0,2}")) {
            productService.create(new Product(name, Double.parseDouble(price)));
            req.setAttribute("products", productService.getAll());
            req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "You entered invalid price format");
            req.getRequestDispatcher("/WEB-INF/views/addProduct.jsp").forward(req, resp);
        }
    }
}
