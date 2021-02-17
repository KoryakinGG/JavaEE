package ru.koryaking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.koryaking.persist.Product;
import ru.koryaking.persist.ProductRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ProductServlet", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if ( productRepository == null) {
            throw new ServletException("ProductRepository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Product product = productRepository.findById(id);
            if (product == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            // TODO delete product
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            productRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath()+ "/product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            BigDecimal price;
            try {
                price = new BigDecimal(req.getParameter("price"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Product product = new Product(id, req.getParameter("name"), req.getParameter("description"), price);
            productRepository.saveOrUpdate(product);
            resp.sendRedirect(getServletContext().getContextPath() + "/product");
        }
        else if (req.getPathInfo().equals("/product_add_form")) {

            getServletContext().getRequestDispatcher("/WEB-INF/product_add_form.jsp").forward(req, resp);
        }
    }
}
