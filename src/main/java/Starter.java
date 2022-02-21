import config.PropertiesReader;
import dao.ConnectionManager;
import dao.jdbc.JdbcProductDao;
import dao.ProductDao;
import org.flywaydb.core.Flyway;
import service.ProductService;
import web.AddProductServlet;
import web.AllProductsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {

        Flyway flyway = Flyway.configure().dataSource(PropertiesReader.get(ConnectionManager.URL_KEY)
                ,PropertiesReader.get(ConnectionManager.USERNAME_KEY)
                , PropertiesReader.get(ConnectionManager.PASSWORD_KEY)).load();
        flyway.migrate();


        ProductDao productDao = new JdbcProductDao();

        ProductService productService = new ProductService(productDao);

        AllProductsServlet productsServlet = new AllProductsServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);

        ServletContextHandler servletContextHandler =  new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        servletContextHandler.addServlet(new ServletHolder(addProductServlet), "/products/add");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);

        server.start();
    }

}
