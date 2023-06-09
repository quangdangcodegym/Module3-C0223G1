package com.codegym.customermanager.service;

import com.codegym.customermanager.model.CustomerType;

import java.util.List;

public interface ICustomerType {
    List<CustomerType> getAllCustomerTypes();

    CustomerType findById(long id);
}
