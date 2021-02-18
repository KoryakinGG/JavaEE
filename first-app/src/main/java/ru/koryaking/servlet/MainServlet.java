package ru.koryaking.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main-servlet")
public class MainServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(MainServlet.class);

    private transient ServletConfig servletConfig;

    /**
     * Метод вызывается контейнером после того, как был создан класс сервлета
     *
     * @param servletConfig
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("MainServlet is initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    /**
     * Метод вызывается для каждого нового HTTP запроса к данному сервлету
     *
     * @param servletRequest
     * @param servletResponse
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/header").include(servletRequest, servletResponse);
        logger.info("New request to MainServlet");
        servletResponse.getWriter().println("<h1>This is main servlet</h1>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * При завершении работы веб-приложения, контейнер вызывает этот метод для всех сервлетов из этого приложения
     */
    @Override
    public void destroy() {

    }
}
