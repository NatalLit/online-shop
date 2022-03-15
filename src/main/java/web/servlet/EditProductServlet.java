package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.jdbc.mapper.ProductRowMapper;
import entity.Product;
import service.ProductService;
import web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EditProductServlet extends HttpServlet {

    private final ProductService productService;

    private final ObjectMapper mapper = new ObjectMapper();

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product =   productService.getById(id);

        if (product == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/products");
            return;
        }
        Map<String, Object> pageVariables = new HashMap<>();

        pageVariables.put("product", product);

        String page = PageGenerator.getPage("editProduct.html", pageVariables);

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(page);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        var jsonString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Product product = mapper.readValue(jsonString, Product.class);

        productService.edit(product);

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/products");


    }

}

