package com.azka_cake_project.service;


import com.azka_cake_project.entity.Category;
import com.azka_cake_project.entity.Payment;
import com.azka_cake_project.entity.Product;
import com.azka_cake_project.repository.ProductRepository;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    //GET LIST PRODUCT
    public List<Product> products(){
        return productRepository.findAll();
    }


    //CREATE PRODUCT
    public ResponseEntity<Product> createProduct(Product product){
        return ResponseEntity.ok(productRepository.save(product));
    }


    //UPDATE PRODUCT
    public ResponseEntity<Product> updateProduct(String id, Product updateProduct){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()){
            Product existingProduct = optional.get();//mengambil product yang sudah ada, lalu diset ulang
            String existingID = existingProduct.getId();

            existingProduct.setNamaProduct(updateProduct.getNamaProduct());
            existingProduct.setHargaProduct(updateProduct.getHargaProduct());
            existingProduct.setQuantity(updateProduct.getQuantity());
            existingProduct.setCreateAT(updateProduct.getCreateAT());
            if (existingProduct.getCategory() != null){//jika categorynya tetap tidak ada perubahan
                existingProduct.setCategory(existingProduct.getCategory());
            }

            existingProduct.setId(existingID);
            Product saveProduct = productRepository.save(existingProduct);

            return ResponseEntity.ok(saveProduct);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



    //DELETE PRODUCT
    public ResponseEntity<String> deleteProduct(String name){
        Optional<Product> optional = productRepository.findByNamaProduct(name);
        if (optional.isPresent()){
            productRepository.delete(optional.get());
            return ResponseEntity.ok("berhasil menghapus product dengan id: " +name);
        }else {
            return ResponseEntity.ok("product dengan id: "+name+" tidak ditemukan");
        }
    }


}
