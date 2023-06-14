package com.codegym.customermanager.service;

import com.codegym.customermanager.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();

    Product findProductById(Long id);
    void editProduct(Product Product);
    void deleteProductById(Long id);

    void createProduct(Product Product);


    List<Product> getAllProductByCategoryId(int idCategory);
}
