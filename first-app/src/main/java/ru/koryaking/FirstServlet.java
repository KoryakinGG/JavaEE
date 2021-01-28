package ru.koryaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("FirstServlet is initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to FirstServlet");
        servletResponse.getWriter().println("<h1>Cyberpunk 77</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
