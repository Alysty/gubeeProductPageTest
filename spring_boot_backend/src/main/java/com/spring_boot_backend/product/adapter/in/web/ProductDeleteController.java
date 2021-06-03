package com.spring_boot_backend.product.adapter.in.web;

import com.spring_boot_backend.product.application.ports.out.DeleteProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/products")
public class ProductDeleteController {
    DeleteProductUseCase deleteProductUseCase;
    public ProductDeleteController(DeleteProductUseCase deleteProductUseCase){
        Objects.requireNonNull(deleteProductUseCase);
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam String id){
        deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
