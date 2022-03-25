package web.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import service.ProductService;
import web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


public class AddProductServlet extends HttpServlet {

    private final ProductService productService;
    private final ObjectMapper mapper = new ObjectMapper();

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Cookie [] cookies = request.getCookies();
        boolean isValid = false;
        for (Cookie cookie:cookies) {
            if("user-token".equals(cookie.getName())){
                isValid = true;
                break;
            }
        }
        if(!isValid){
            response.sendRedirect("/login");
        }


        Map<String, Object> pageVariables = new HashMap<>();

        String page = PageGenerator.getPage("addProduct.html", pageVariables);

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(page);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        var jsonString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Product product = mapper.readValue(jsonString, Product.class);

        productService.add(product);

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/products");


    }

}
