package com.example_tarzan.demo.responses;

import com.example_tarzan.demo.models.Product;

import java.math.BigDecimal;

public class ProductResponse {
    private int id;
    private String productname;
    private BigDecimal price;
    private int quantity;
    private SupplierResponse supplier;

    public SupplierResponse getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponse supplier) {
        this.supplier = supplier;
    }

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productname = product.getProductname();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public ProductResponse(int id, String productname, BigDecimal price, int quantity) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductResponse(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
