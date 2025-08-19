package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.Supplier;
import com.example_tarzan.demo.repositories.SupplierRepository;
import com.example_tarzan.demo.requests.CreateSupplierRequest;
import com.example_tarzan.demo.requests.UpdateSupplierRequest;
import com.example_tarzan.demo.responses.CreateSupplierResponse;
import com.example_tarzan.demo.responses.CreateUserResponse;
import com.example_tarzan.demo.responses.GetSupplierResponse;
import com.example_tarzan.demo.responses.UpdateSupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin("*")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public ResponseEntity<List<GetSupplierResponse>> getAllSuppliers(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return ResponseEntity.ok(supplierList.stream().map(GetSupplierResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSupplierResponse> getSupplierById(@PathVariable int id){
        Optional<Supplier> suppliers = supplierRepository.findById(id);

        if(suppliers.isPresent()){
            GetSupplierResponse response = new GetSupplierResponse(suppliers.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSupplierResponse> updateSupplierById(@PathVariable int id, @RequestBody UpdateSupplierRequest request){
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
            System.out.println("Before Save: "+ updateSupplier);
            updateSupplier = supplierRepository.save(updateSupplier);
            UpdateSupplierResponse response = new UpdateSupplierResponse(updateSupplier.getSuppliername());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateSupplierResponse> createSupplier(@RequestBody CreateSupplierRequest request){
        Supplier supplier = new Supplier();
        supplier.setSuppliername(request.getSuppliername());
        supplier.setAddress(request.getAddress());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        System.out.println("Before Save: "+ supplier);
        Supplier saveSupplier = supplierRepository.save(supplier);
        CreateSupplierResponse response = new CreateSupplierResponse(saveSupplier.getSuppliername()
                        ,saveSupplier.getAddress()
                        ,saveSupplier.getPhone()
                        ,saveSupplier.getEmail());
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

