package com.codegym.customermanager.controller;

import com.codegym.customermanager.Config;
import com.codegym.customermanager.model.Product;
import com.codegym.customermanager.service.IProductService;
import com.codegym.customermanager.service.MSProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = "/home")
public class HomeController extends HttpServlet {
    private IProductService iProductService;

    @Override
    public void init() throws ServletException {
        iProductService = new MSProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getSession().getId());

        List<Product> products = iProductService.getAllProduct();
        req.setAttribute("products", products);
        req.getRequestDispatcher(Config.URL_VIEW_FRONTEND + "home.jsp").forward(req,resp);
    }
}
