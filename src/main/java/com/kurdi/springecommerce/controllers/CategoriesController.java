package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.entities.Category;
import com.kurdi.springecommerce.entities.Product;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Api/Categories/")
public class CategoriesController {
    @Autowired
    CategoriesRepository categoriesRepository;
    @GetMapping("/")
    public List<Category> get()
    {
        return categoriesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id)
    {
        if (!categoriesRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(categoriesRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity post(@RequestBody Category category)
    {
        category.setId(null);
        categoriesRepository.save(category);
        return new ResponseEntity(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody Category category, @PathVariable String id)
    {
        if (!categoriesRepository.existsById(id) || id != category.getId())
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoriesRepository.save(category);
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id)
    {
        if (!categoriesRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoriesRepository.delete(categoriesRepository.findById(id).get());
        return new ResponseEntity(categoriesRepository.findById(id), HttpStatus.OK);
    }


}
