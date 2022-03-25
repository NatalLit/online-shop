package web.servlet;

import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {

    private final ProductService productService;

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        productService.delete(id);

        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("/products");

    }

}
