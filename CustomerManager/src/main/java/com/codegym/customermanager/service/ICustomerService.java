package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Customer;
import com.codegym.customermanager.model.Pageable;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    List<Customer> findAll2();

    //    List<Customer> findAdvanced(String kw, int page, int limit, String order, String sortField, int customerType);
    List<Customer> findAdvanced(Pageable pageable);
    void save(Customer customer);
    Customer findById(int id);
    void update(int id, Customer customer);
    void remove(int id);


}
