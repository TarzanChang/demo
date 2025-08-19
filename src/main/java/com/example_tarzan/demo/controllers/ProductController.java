package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.models.Product;
import com.example_tarzan.demo.repositories.ProductRepository;
import com.example_tarzan.demo.requests.CreateProductRequest;
import com.example_tarzan.demo.requests.UpdateProductRequest;
import com.example_tarzan.demo.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    //Step 1. 建立 Product model
    //Step 2. 建立 Product Repository extends JpaRepository
    //Step 3. 建立 Product CRUD API
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public ResponseEntity<List<GetproductResponse>> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok(productList.stream().map(GetproductResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetproductResponse> getProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()){
            GetproductResponse response = new GetproductResponse(product.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProductById(@PathVariable int id, @RequestBody UpdateProductRequest request){
        //1. 找到 User
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            //2. 確定找到 user 之後，取得該 user資料
            Product updatedProduct = product.get();
            System.out.println("Before Update: "+ updatedProduct);
            //3. 將欲更新資料填充至對應的 user
            if (request.getProductname() != null && !request.getProductname().isEmpty()){
                updatedProduct.setProductname(request.getProductname());
            }
            if (request.getPrice() != null){
                updatedProduct.setPrice(request.getPrice());
            }
            if (request.getQuantity() != null){
                updatedProduct.setQuantity(request.getQuantity());
            }
            if (request.isStatus() != null){
                updatedProduct.setStatus(request.isStatus());
            }
            if (request.getSupplierId() != null){
                updatedProduct.setSupplierId(request.getSupplierId());
            }
            System.out.println("Before Save: "+ updatedProduct);
            updatedProduct = productRepository.save(updatedProduct);
            UpdateProductResponse response = new UpdateProductResponse(updatedProduct.getProductname());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createUser(@RequestBody CreateProductRequest request) {
        Product product = new Product();
        product.setProductname(request.getProductname());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setStatus(request.isStatus());
        product.setSupplierId(request.getSupplierId());
        System.out.println("Before Save: "+ product);
        Product savedProduct = productRepository.save(product);
        CreateProductResponse response =
                new CreateProductResponse(savedProduct.getProductname()
                        ,savedProduct.getPrice()
                        ,savedProduct.getQuantity()
                        ,savedProduct.isStatus()
                        ,savedProduct.getSupplierId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            //2. 確定找到 user 之後，取得該 user資料
            Product deleteProduct = product.get();
            System.out.println("Before Update: "+ deleteProduct);
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
