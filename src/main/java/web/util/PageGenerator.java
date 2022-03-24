package web.util;

import config.PropertiesReader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

public class PageGenerator {

    private static final String HTML_DIR = PropertiesReader.get("html.directory");

    private static final Configuration cfg = new Configuration();


    public static String getPage(String filename, Map<String, ?> data) {
        Writer writer = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + "/" + filename);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }


}
