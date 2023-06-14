package com.codegym.customermanager.controller;

import com.codegym.customermanager.Config;
import com.codegym.customermanager.model.*;
import com.codegym.customermanager.service.IProductService;
import com.codegym.customermanager.service.MSProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private IProductService iProductService = new MSProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                actionInCart(req, resp, action);
                break;
            case "increase":
                increateProductInCart(req, resp, action);
                break;
            case "decrease":
                descreaseProductInCart(req, resp, action);
                break;
            default:
                showCarts(req, resp);
                break;
        }
    }

    private void increateProductInCart(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        actionInCart(req, resp, action);
    }

    private void descreaseProductInCart(HttpServletRequest req, HttpServletResponse resp , String action) throws ServletException, IOException {
        actionInCart(req, resp, action);
    }

    private void showCarts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(Config.URL_VIEW_FRONTEND + "cart.jsp").
                forward(req, resp);
    }

    private void actionInCart(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        int quantity = 1;
        if (action.equals("decrease")) {
            quantity = -1;
        }

        long idProduct = Long.parseLong(req.getParameter("id"));

        Order order = null;
        if (req.getSession().getAttribute("cart") != null) {
            order = (Order) req.getSession().getAttribute("cart");
        }else{
            order = new Order();
        }
        if (order.getOrderItems() == null) {
            List<OrderItem> orderItems = new ArrayList<>();
            OrderItem orderItem = new OrderItem(idProduct, 1);
            orderItems.add(orderItem);

            order.setOrderItems(orderItems);
        }else{
            boolean check = checkIdProductExistOrder(idProduct, order);
            if (check == true) {
                updateProductInOrder(idProduct, quantity, order);
            }else{
                addProductToOrder(idProduct, quantity, order);
            }
        }

        // Chuyển thông tin từ order (chưa có hình ảnh, tên sp) sang orderDTO (hình ảnh, tên sp)
        OrderDTO orderDTO = order.toOderDTO();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            OrderItem orderItem = order.getOrderItems().get(i);

            Product product = iProductService.findProductById(orderItem.getIdProduct());
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setId(orderItem.getId());
            orderItemDTO.setQuantiy(orderItem.getQuantiy());
            orderItemDTO.setIdProduct(orderItem.getIdProduct());
            orderItemDTO.setProduct(product);

            orderItemDTOS.add(orderItemDTO);
        }
        orderDTO.setOrderItemDTOs(orderItemDTOS);

        req.getSession().setAttribute("cart", order);
        req.setAttribute("orderDTO", orderDTO);
        req.getRequestDispatcher(Config.URL_VIEW_FRONTEND + "cart.jsp").
                forward(req, resp);

    }

    private void addProductToOrder(long idProduct, int quantity, Order order) {
        OrderItem orderItem = new OrderItem(idProduct, quantity);
        order.getOrderItems().add(orderItem);
    }

    private void updateProductInOrder(long idProduct, int quantity, Order order) {
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            if (order.getOrderItems().get(i).getIdProduct() == idProduct ) {
                OrderItem temp = order.getOrderItems().get(i);
                temp.setQuantiy(temp.getQuantiy() + quantity);
                break;
            }
        }
    }

    private boolean checkIdProductExistOrder(long idProduct, Order order) {
        if (order.getOrderItems() == null) {
            return false;
        }
        for (int i = 0; i < order.getOrderItems().size(); i++) {
            if (order.getOrderItems().get(i).getIdProduct() == idProduct) {
                return true;
            }
        }
        return false;
    }
}
