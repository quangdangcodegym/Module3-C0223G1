package com.codegym.customermanager.model;

import java.time.LocalDate;

public class Product {
    private long id;
    private String name;
    private String description;
    private LocalDate createAt;
    private LocalDate deleteAt;

    private float price;

    private long idCategory;
    private Category category;
}
