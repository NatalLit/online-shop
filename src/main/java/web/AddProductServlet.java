package web;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Product;
import service.ProductService;
import web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


public class AddProductServlet extends HttpServlet {

    private final ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = new ArrayList<>();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", products);
        String page = PageGenerator.getPage("addProduct.html", pageVariables);

        response.getWriter().println(page);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        var jsonString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonString, Product.class);

//        System.out.println("Name: " + product.getName() + " " + "price: " + product.getPrice());

        productService.add(product);

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/products");


    }

}
