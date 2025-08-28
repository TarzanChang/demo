package com.example_tarzan.demo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String suppliername;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;

    // mappedBy = "objectName" 對應到 @ManyToOne 設定的建構子名稱
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Supplier(){

    }

    public Supplier(int id, String suppliername, String address, String phone, String email) {
        this.id = id;
        this.suppliername = suppliername;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Supplier{" +
                "id = " + id +
                ", suppliername = '" + suppliername + '\'' +
                ", address = '" + address + '\'' +
                ", phone = '" + phone + '\'' +
                ", email = '" + email + '\'' +
                '}';
    }
}
