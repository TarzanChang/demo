package com.example_tarzan.demo.requests;

public class CreateSupplierRequest {
    private String suppliername;
    private String address;
    private String phone;
    private String email;

    public CreateSupplierRequest(String suppliername, String address, String phone, String email) {
        this.suppliername = suppliername;
        this.address = address;
        this.phone = phone;
        this.email = email;
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
