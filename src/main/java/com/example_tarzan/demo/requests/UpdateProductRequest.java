package com.example_tarzan.demo.requests;

import java.math.BigDecimal;

public class UpdateProductRequest {
    private String productname;
    private BigDecimal price;
    private Integer quantity;
    private Boolean status;
    private Integer supplierId;

    public UpdateProductRequest(){

    }

    public UpdateProductRequest(String productname, BigDecimal price, Integer quantity, Boolean status, Integer supplierId) {
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplierId = supplierId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
