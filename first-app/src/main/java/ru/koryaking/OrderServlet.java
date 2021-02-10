package ru.koryaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order-servlet")
public class OrderServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("OrderServlet is initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to OrderServlet");
        servletResponse.getWriter().println("<h1>This is order servlet</h1>");
        servletResponse.getWriter().println("<a href='./catalog-servlet'> Каталог");
        servletResponse.getWriter().println("<a href='./product-servlet'> Товар");
        servletResponse.getWriter().println("<a href='./cart-servlet'> Корзина");
        servletResponse.getWriter().println("<a href='./order-servlet'> Заказ");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
