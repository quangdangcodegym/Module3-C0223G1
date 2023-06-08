package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImplMySql implements ICustomerService{
    // Chinh kết nối DB: c0223g1_customer: DB, username, password
    private String jdbcURL = "jdbc:mysql://localhost:3306/c0223g1_customer?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Raisingthebar123!!/";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                Date createAt = rs.getDate("date");
                Customer c = new Customer(id, name, email, address, createAt);
                customers.add(c);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customers;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
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

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers` (`name`, `email`, `address`, `date`) VALUES (?, ?, ?, ?);");
            // Đua tham số vào preparedStatement
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setDate(4, new java.sql.Date(customer.getCreateAt().getTime()));



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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customers where id = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCustomer = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                Date createAt = resultSet.getDate("date");
                Customer c = new Customer(idCustomer, name, email, address, createAt);
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `c0223g1_customer`.`customers` SET `name` = ?, `email` = ?, `address` = ?, `date` = ? WHERE (`id` = ?);");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getEmail());
            preparedStatement.setString(3,customer.getAddress());
            preparedStatement.setDate(4,new java.sql.Date(customer.getCreateAt().getTime()));
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
            System.out.println("function update"+ preparedStatement);
            connection.close();

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

    }

    @Override
    public void remove(int id) {

    }
}
