package com.example_tarzan.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String productname;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="quantity")
    private int quantity;
    @Column(name="status")
    private boolean status;
    @Column(name="supplierId")
    private Integer supplierId;

    public Product(){

    }

    public Product(int id, String productname, BigDecimal price, int quantity, boolean status, Integer supplierId) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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

    @Override
    public String toString(){
        return "Product{" +
                "id = " + id +
                ", productname = '" + productname + '\'' +
                ", price = '" + price + '\'' +
                ", quantity = '" + quantity + '\'' +
                ", status = '" + status + '\'' +
                ", supplierId = '" + supplierId + '\'' +
                '}';
    }
}

