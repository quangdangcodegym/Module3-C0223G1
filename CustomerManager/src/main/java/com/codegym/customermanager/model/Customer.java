package com.codegym.customermanager.model;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private Date createAt;

    private int typeId;
    private CustomerType customerType;


    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }



    public Customer() {
    }

    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer(int id, String name, String email, String address, Date createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.createAt = createAt;
    }

    public Customer(int id, String name, String email, String address, Date createAt, int typeId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.createAt = createAt;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
