package com.example_tarzan.demo.responses;

import com.example_tarzan.demo.models.Supplier;

import java.util.List;

public class SupplierResponse {
    private int id;
    private String suppliername;
    private String address;
    private String phone;
    private String email;

    private List<ProductResponse> products;

    public SupplierResponse(){

    };

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public SupplierResponse(int id, String suppliername, String address, String phone, String email) {
        this.id = id;
        this.suppliername = suppliername;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public SupplierResponse(Supplier supplier) {
        this.id = supplier.getId();
        this.suppliername = supplier.getSuppliername();
        this.address = supplier.getAddress();
        this.phone = supplier.getPhone();
        this.email = supplier.getEmail();
        //方法一起頭:建立建構子，讓 Controller New 時包含在其中
//        this.products = supplier.getProducts()
//                .stream()
//                .map(ProductResponse::new).toList();
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
}
