package com.example_tarzan.demo.responses;

import com.example_tarzan.demo.models.Product;

import java.math.BigDecimal;

public class GetproductResponse {
    private int id;
    private String productname;
    private BigDecimal price;
    private int quantity;
    private boolean status;
    private Integer supplierId;

    public GetproductResponse(){

    }

    public GetproductResponse(int id, String productname, BigDecimal price, int quantity, boolean status,Integer supplierId) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplierId = supplierId;
    }

    public GetproductResponse(Product product){
        this.id = product.getId();
        this.productname = product.getProductname();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.status = product.isStatus();
        this.supplierId = product.getSupplierId();
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
