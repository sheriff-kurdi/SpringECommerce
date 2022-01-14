package com.kurdi.springecommerce.controllers.api;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Category;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import com.kurdi.springecommerce.repositories.ProductsRepository;
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
    @Autowired
    CategoriesRepository categoriesRepository;
    @GetMapping("/")
    public List<Product> get()
    {
        List<Product> products = productsRepository.findAll();


        return products;
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
    @PostMapping("/addCategory")
    public ResponseEntity AddCategory(@RequestParam String productId, @RequestParam String categoryId)
    {
        Category category = categoriesRepository.findById(categoryId).get();
        Product product = productsRepository.findById(productId).get();

        try {
            product.addCategory(category);
            categoriesRepository.save(category);
            productsRepository.save(product);
        }catch (Exception e){
            return new ResponseEntity(e, HttpStatus.OK);

        }

        return new ResponseEntity(product, HttpStatus.OK);

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
