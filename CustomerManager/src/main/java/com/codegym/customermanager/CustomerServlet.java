package com.codegym.customermanager;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.service.CustomerServiceImpl;
import com.codegym.customermanager.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService iCustomerService;

    @Override
    public void init() throws ServletException {
        iCustomerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(req, resp);
                break;
            case "edit":
                showFromEdit(req, resp);
                break;
            default:
                showCustomers(req, resp);
        }

    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.findAll();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "list.jsp").forward(req, resp);

    }

    private void showFromEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer c = iCustomerService.findById(id);

        req.setAttribute("customer", c);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN +  "edit.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // action để làm gì: lấy action là gì
        // getParameter("action"): lấy param trong url, param trong form
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                editCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:

        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCustomer = Integer.parseInt(req.getParameter("idEdit"));
        iCustomerService.remove(idCustomer);

        List<Customer> customers = iCustomerService.findAll();

//        req.setAttribute("customers", customers);
//        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "list.jsp").forward(req, resp);

        resp.sendRedirect("/customers");
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int idCustomer = Integer.parseInt(req.getParameter("id"));

        Customer c = iCustomerService.findById(idCustomer);
        String newName = req.getParameter("name");
        String newEmail = req.getParameter("email");
        String newAddress = req.getParameter("address");
        c.setAddress(newAddress);
        c.setEmail(newEmail);
        c.setName(newName);

        iCustomerService.update(c.getId(), c);


    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        //id, String name, String email, String address
        long id = System.currentTimeMillis() %100000;
        Customer customer = new Customer( (int) id, name, email, address);
        iCustomerService.save(customer);


        // req.setAttribute :: nhét thuộc tính với key và value vào request
        req.setAttribute("message", "Thêm thành công");
        // RequestDispatcher :: điều phối qua qua trang jsp
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Config.URL_VIEW_ADMIN +  "create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
