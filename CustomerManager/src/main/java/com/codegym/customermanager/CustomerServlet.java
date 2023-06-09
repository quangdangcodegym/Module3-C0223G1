package com.codegym.customermanager;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.model.CustomerType;
import com.codegym.customermanager.service.*;
import com.codegym.customermanager.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private ICustomerService iCustomerService;
    private ICustomerType iCustomerType;

    @Override
    public void init() throws ServletException {
        iCustomerService = new CustomerServiceImplMySql();
        iCustomerType = new CustomerTypeimplMySql();
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
                System.out.println("Default............");
                showCustomers(req, resp);
        }

    }

    private void showCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = iCustomerService.findAll2();
//        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();


        req.setAttribute("customers", customers);
//        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "list.jsp").forward(req, resp);

    }

    private void showFromEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer c = iCustomerService.findById(id);
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();

        req.setAttribute("customer", c);
        req.setAttribute("customerTypes", customerTypes);
        req.getRequestDispatcher(Config.URL_VIEW_ADMIN +  "edit.jsp").forward(req, resp);
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();

        req.setAttribute("customerTypes", customerTypes);
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

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<String> errors = new ArrayList<>();
        int idCustomer = Integer.parseInt(req.getParameter("id"));

        Customer c = iCustomerService.findById(idCustomer);


        validateInputName(req, errors, c);
        validateInputEmail(req, errors, c);
        validateInputCustomerType(req, errors, c);

        String newAddress = req.getParameter("address");
        c.setAddress(newAddress);

        if (errors.isEmpty()) {
            iCustomerService.update(c.getId(), c);
            resp.sendRedirect("/customers");
        }else{
            req.setAttribute("customer", c);
            req.setAttribute("errors", errors);

            List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();
            req.setAttribute("customerTypes", customerTypes);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(Config.URL_VIEW_ADMIN + "edit.jsp");
            requestDispatcher.forward(req, resp);

        }


    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        Customer customer = new Customer();
        validateInputName(req, errors, customer);
        validateInputEmail(req, errors, customer);
        validateInputCustomerType(req, errors, customer);

        String address = req.getParameter("address");
        //id, String name, String email, String address
        long id = System.currentTimeMillis() %100000;
        customer.setCreateAt(new Date());

        // Nếu rỗng thì cho lưu
        if (errors.isEmpty()) {
            // cho luu
            iCustomerService.save(customer);
            req.setAttribute("message", "Thêm thành công");
        }else{
            // báo lỗi
            req.setAttribute("errors", errors);
            req.setAttribute("customer", customer);
        }
        List<CustomerType> customerTypes = iCustomerType.getAllCustomerTypes();
        req.setAttribute("customerTypes", customerTypes);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Config.URL_VIEW_ADMIN +  "create.jsp");
        requestDispatcher.forward(req, resp);




    }

    private void validateInputCustomerType(HttpServletRequest req, List<String> errors, Customer customer) {
        try {
            int customerType = Integer.parseInt(req.getParameter("customertype"));
            CustomerType ct = iCustomerType.findById(customerType);

            if (ct == null) {
                errors.add("Không tìm thấy loại khách hàng");
                customer.setTypeId(1);
            }else {
                customer.setTypeId(customerType);
            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Loại khách hàng không hợp lệ");
        }

    }

    private void validateInputEmail(HttpServletRequest req, List<String> errors, Customer customer) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ");
        }
        customer.setEmail(email);
    }

    private void validateInputName(HttpServletRequest req, List<String> errors, Customer customer) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-20 kí tự và bắt đầu là chữ cái");
        }
        customer.setName(name);
    }
}
