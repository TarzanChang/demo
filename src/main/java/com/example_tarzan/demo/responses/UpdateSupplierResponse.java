package com.example_tarzan.demo.responses;

public class UpdateSupplierResponse {
    private String suppliername;

    public UpdateSupplierResponse(){

    }

    public UpdateSupplierResponse(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
}
