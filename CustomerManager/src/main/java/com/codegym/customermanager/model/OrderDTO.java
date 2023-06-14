package com.codegym.customermanager.model;

import java.util.List;

public class OrderDTO {
    private Long id;
    private String name;
    private String phone;
    private String address;

    private double total;

    private List<OrderItemDTO> orderItemDTOs;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String name, String phone, String address, double total, List<OrderItemDTO> orderItemDTOs) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.orderItemDTOs = orderItemDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItemDTO> getOrderItemDTOs() {
        return orderItemDTOs;
    }

    public void setOrderItemDTOs(List<OrderItemDTO> orderItemDTOs) {
        this.orderItemDTOs = orderItemDTOs;
    }
}
