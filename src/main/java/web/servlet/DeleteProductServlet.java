package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class DeleteProductServlet extends HttpServlet {

    private final ProductService productService;

    private final ObjectMapper mapper = new ObjectMapper();

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Product product = productService.getById(id);

        if (product == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/products");
            return;
        }
        Map<String, Object> pageVariables = new HashMap<>();

        String page = PageGenerator.getPage("deleteProduct.html", pageVariables);

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println(page);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        productService.delete(id);

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/products");


    }

}
