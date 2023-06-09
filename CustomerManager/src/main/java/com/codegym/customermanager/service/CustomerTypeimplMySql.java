package com.codegym.customermanager.service;

import com.codegym.customermanager.model.CustomerType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeimplMySql extends DBContext implements ICustomerType {




    @Override
    public List<CustomerType> getAllCustomerTypes() {
        List<CustomerType> customerTypes = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer_type;");

            System.out.println("Function getAllCustomers " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CustomerType customerType = getCustomerFromRs(rs);
                customerTypes.add(customerType);
            }

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return customerTypes;
    }

    private CustomerType getCustomerFromRs(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String type = rs.getString("type");
        Date sqlDeleteAt = rs.getDate("delete_at");
        LocalDate lDeleteAt = null;
        if (sqlDeleteAt != null) {
            lDeleteAt = sqlDeleteAt.toLocalDate();
        }
        CustomerType customerType = new CustomerType(id, type, lDeleteAt);
        return customerType;
    }

    @Override
    public CustomerType findById(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer_type where (`id`=?);");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerType customerType = getCustomerFromRs(resultSet);
                return customerType;
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }
}
