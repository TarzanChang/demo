package com.example_tarzan.demo.responses;

public class UpdateProductResponse {
    private String productname;

    public UpdateProductResponse(){

    }

    public UpdateProductResponse(String productname) {
        this.productname = productname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
