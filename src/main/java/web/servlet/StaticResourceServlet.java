package web.servlet;

import web.util.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class StaticResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println(request.getPathInfo().substring(1));

        try (InputStream inputStream= StaticResourceServlet.class.getClassLoader().getResourceAsStream(request.getPathInfo().substring(1))) {
            System.out.println(inputStream);

            String script = InputStreamReader.getStringFromInputStream(inputStream);

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().println(script);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
