package web.servlet;

import entity.Product;
import service.ProductService;
import web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllProductsServlet extends HttpServlet {

    private final ProductService productService;

    public AllProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", products);
        String page = PageGenerator.getPage("products.html", pageVariables);

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(page);



    }


}
