package com.example_tarzan.demo.requests;

import java.math.BigDecimal;

public class CreateProductRequest {
    private String productname;
    private BigDecimal price;
    private int quantity;
    private boolean status;
    private int supplierId;

    public CreateProductRequest(String productname, BigDecimal price, int quantity, boolean status, int supplierId) {
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
