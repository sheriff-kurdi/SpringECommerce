package com.kurdi.springecommerce.web.controllers.api;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.infrastructure.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/")
public class ProductsController {
    @Autowired
    ProductsRepository productsRepository;
    @GetMapping("/")
    public List<Product> get()
    {
        return productsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productsRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity post(@RequestBody Product product)
    {
        product.setId(null);
        productsRepository.save(product);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody Product product, @PathVariable String id)
    {
        if (!productsRepository.existsById(id) || id != product.getId())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        productsRepository.save(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        productsRepository.delete(productsRepository.findById(id).get());
        return new ResponseEntity(productsRepository.findById(id), HttpStatus.OK);
    }


}
