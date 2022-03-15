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
        System.out.println(request.getPathInfo().replace("/", ""));

        try (InputStream inputStreamScript = StaticResourceServlet.class.getClassLoader().getResourceAsStream(request.getPathInfo())) {
            System.out.println(inputStreamScript);

            String script = InputStreamReader.getStringFromInputStream(inputStreamScript);

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().println(script);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
