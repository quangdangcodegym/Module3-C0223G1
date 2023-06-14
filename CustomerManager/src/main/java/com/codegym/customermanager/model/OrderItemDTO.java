package com.codegym.customermanager.model;

public class OrderItemDTO {
    private Long id;
    private Long idProduct;
    private long idOrder;
    private int quantiy;
    private Product product;

    public OrderItemDTO(Long id, Long idProduct, long idOrder, int quantiy, Product product) {
        this.id = id;
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.quantiy = quantiy;
        this.product = product;
    }
    public OrderItemDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(int quantiy) {
        this.quantiy = quantiy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
