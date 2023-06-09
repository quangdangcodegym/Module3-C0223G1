package com.codegym.customermanager.model;

import java.time.LocalDate;

public class CustomerType {
    private int id;
    private String name;
    private LocalDate deleteAt;

    public CustomerType() {

    }

    public CustomerType(int id, String name, LocalDate deleteAt) {
        this.id = id;
        this.name = name;
        this.deleteAt = deleteAt;
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

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }
}
