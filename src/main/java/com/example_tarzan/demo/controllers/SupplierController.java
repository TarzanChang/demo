package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.Product;
import com.example_tarzan.demo.models.Supplier;
import com.example_tarzan.demo.repositories.SupplierRepository;
import com.example_tarzan.demo.requests.CreateSupplierRequest;
import com.example_tarzan.demo.requests.UpdateSupplierRequest;
import com.example_tarzan.demo.responses.ProductResponse;
import com.example_tarzan.demo.responses.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers(){
        List<Supplier> supplierList = supplierRepository.findAll();
        //方法一：在 SupplierResponse 加入建構子 this.product = supplier.getProducts().stream().map(ProductResponse::new).toList();
        //return ResponseEntity.ok(supplierList.stream().map(SupplierResponse::new).toList());
        //方法二：在 SupplierController 將 Product 賦值 Setter進去
//        return ResponseEntity.ok(supplierList.stream().map(supplier -> {
//            SupplierResponse response = new SupplierResponse(supplier);
//            response.setProducts(supplier.getProducts().stream().map(ProductResponse::new).toList());
//            return response;
//        }).toList());
        //方法三：非 "->" lamda 方式
        List<SupplierResponse> responses = supplierList
                .stream()
                .map(supplier -> {
                    SupplierResponse response = new SupplierResponse(supplier);
                    response.setProducts(supplier.getProducts().stream().map(ProductResponse::new).toList());
                    return response;
                }).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable int id){
        Optional<Supplier> suppliers = supplierRepository.findById(id);

        if(suppliers.isPresent()){
            SupplierResponse response = new SupplierResponse(suppliers.get());
            List<Product> productList = suppliers.get().getProducts();
            List<ProductResponse> productResponseList = productList.stream()
                    .map(product -> new ProductResponse(product))
                    .toList();
            response.setProducts(productResponseList);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplierById(@PathVariable int id, @RequestBody UpdateSupplierRequest request){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isPresent()){
            Supplier updateSupplier = supplier.get();
            System.out.println("Before Update: "+ updateSupplier);

            if(request.getSuppliername()!= null && !request.getSuppliername().isEmpty()){
                updateSupplier.setSuppliername(request.getSuppliername());
            }
            if(request.getAddress()!= null && !request.getAddress().isEmpty()){
                updateSupplier.setAddress(request.getAddress());
            }
            if(request.getPhone()!= null && !request.getPhone().isEmpty()){
                updateSupplier.setPhone(request.getPhone());
            }
            if(request.getEmail()!= null && !request.getEmail().isEmpty()){
                updateSupplier.setEmail(request.getEmail());
            }
            updateSupplier = supplierRepository.save(updateSupplier);
            SupplierResponse response = new SupplierResponse(updateSupplier);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody CreateSupplierRequest request){
        Supplier supplier = new Supplier();
        supplier.setSuppliername(request.getSuppliername());
        supplier.setAddress(request.getAddress());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        System.out.println("Before Save: "+ supplier);
        Supplier saveSupplier = supplierRepository.save(supplier);
        SupplierResponse response = new SupplierResponse(saveSupplier);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> daleteSupplierById (@PathVariable int id){
        Optional<Supplier> suppliers = supplierRepository.findById(id);
        if (suppliers.isPresent()){
            Supplier deleteSupplier = suppliers.get();
            System.out.println("Before Delete: "+ deleteSupplier);
            supplierRepository.delete(suppliers.get());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

