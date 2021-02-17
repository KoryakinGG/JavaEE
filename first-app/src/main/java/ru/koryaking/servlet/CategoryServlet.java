package ru.koryaking.servlet;

import ru.koryaking.category.Category;
import ru.koryaking.persist.Product;
import ru.koryaking.repository.CategoryRepository;
import ru.koryaking.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category/*")
public class CategoryServlet extends HttpServlet {

    CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (categoryRepository == null) {
            throw new ServletException("СategoryRepository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categorys", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit_category")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Category category = categoryRepository.findById(id);
            if (category == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete_category")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            categoryRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
        } else if (req.getPathInfo().equals("/add_category")) {
            getServletContext().getRequestDispatcher("/WEB-INF/category_add_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals("/edit_category")){
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Category category = new Category(id, req.getParameter("name"), req.getParameter("description"));
            categoryRepository.saveOrUpdate(category);
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
        } else if(req.getPathInfo().equals("/add_category")){
            Category category = new Category(null, req.getParameter("name"), req.getParameter("description"));
            categoryRepository.saveOrUpdate(category);
            resp.sendRedirect(getServletContext().getContextPath() + "/category");
        }
    }

}
