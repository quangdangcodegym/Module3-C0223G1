package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImplMySql extends DBContext implements ICustomerService{
    // Chinh kết nối DB: c0223g1_customer: DB, username, password




    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            // lấy kết nối
            Connection connection = getConnection();
            // Dùng preparedStatement để boc cau lenh
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers");

            System.out.println("Function findAll " + preparedStatement);

            // Thuc thi cau lenh: executeQuery - select, executeUpdate - them/xoa/sua
            ResultSet rs = preparedStatement.executeQuery();
            // rs.next(): đọc qua từng dòng
            while (rs.next()) {
                // getInt, getString : lấy giá trị theo tên cột hoặc chỉ số cột (bat dau tu 1)
                Customer c = getCustomerFromRs(rs);
                customers.add(c);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    @Override
    public List<Customer> findAll2() {
        List<Customer> customers = new ArrayList<>();
        try {
            // lấy kết nối
            Connection connection = getConnection();
            // Dùng preparedStatement để boc cau lenh
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT c.*, ct.type, ct.delete_at FROM customers c join customer_type ct on c.type_id = ct.id;");

            System.out.println("Function findAll2 " + preparedStatement);

            // Thuc thi cau lenh: executeQuery - select, executeUpdate - them/xoa/sua
            ResultSet rs = preparedStatement.executeQuery();
            // rs.next(): đọc qua từng dòng
            while (rs.next()) {
                // getInt, getString : lấy giá trị theo tên cột hoặc chỉ số cột (bat dau tu 1)
                Customer c = getCustomerFromRs2(rs);
                customers.add(c);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    private Customer getCustomerFromRs2(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        Date createAt = rs.getDate("date");
        int idType = rs.getInt("type_id");

        String type = rs.getString("type");
        java.sql.Date sqlDeleteAt = rs.getDate("delete_at");

        LocalDate deleteAt = null;
        if (sqlDeleteAt != null) {
            deleteAt = sqlDeleteAt.toLocalDate();
        }

        CustomerType customerType = new CustomerType(idType, type, deleteAt);
        Customer c = new Customer(id, name, email, address, createAt, idType);
        c.setCustomerType(customerType);
        return  c;
    }

    private Customer getCustomerFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        Date createAt = rs.getDate("date");
        int idType = rs.getInt("type_id");
        Customer c = new Customer(id, name, email, address, createAt, idType);
        return  c;
    }


    @Override
    public void save(Customer customer) {
        try {
            Connection connection = getConnection();

            /**
             String queryFmt = "INSERT INTO customers` (`name`, `email`, `address`, `date`) VALUES ('%s', '%s', '%s', '%s')";
             String result = String.format(queryFmt, customer.getName(),customer.getEmail(),
             customer.getAddress(), new java.sql.Date(customer.getCreateAt().getTime()) );
             Statement statement = connection.createStatement();
             statement.executeQuery(result);
             */

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO `customers` (`name`, `email`, `address`, `date`, `type_id`) " +
                            "VALUES (?, ?, ?, ?,?);");
            // Đua tham số vào preparedStatement
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setDate(4, new java.sql.Date(customer.getCreateAt().getTime()));
            preparedStatement.setInt(5  , customer.getTypeId());


            System.out.println("Function save " + preparedStatement);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }

    @Override
    public Customer findById(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from customers where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer c = getCustomerFromRs(resultSet);
                return c;
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }


        return null;
    }

    @Override
    public void update(int id, Customer customer) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `c0223g1_customer`.`customers` " +
                            "SET `name` = ?, `email` = ?, `address` = ?, `date` = ?, `type_id` = ? " +
                            "WHERE (`id` = ?);");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer.getCreateAt().getTime()));
            preparedStatement.setInt(5, customer.getTypeId());
            preparedStatement.setInt(6,id);


            preparedStatement.executeUpdate();
            System.out.println("function update"+ preparedStatement);
            connection.close();

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }

    @Override
    public void remove(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM `customer_type` WHERE (`id` = ?);\n");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


            System.out.println("function remove"+ preparedStatement);
            connection.close();

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
}
