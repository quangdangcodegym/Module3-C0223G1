package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    List<Customer> findAll2();
    void save(Customer customer);
    Customer findById(int id);
    void update(int id, Customer customer);
    void remove(int id);


}
