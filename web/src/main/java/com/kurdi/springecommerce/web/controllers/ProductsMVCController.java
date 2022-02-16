package com.kurdi.springecommerce.web.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.infrastructure.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("products")
public class ProductsMVCController {
    @Autowired
    ProductsRepository productsRepository;
    @GetMapping("/")
    public String get(Model model)
    {
        model.addAttribute("products", productsRepository.findAll());
        return "products/products";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("product", new Product());
        return "products/create";
    }
    @PostMapping("/save")
    public String save( Product product, BindingResult result, Model model)
    {
       // product.setId(null);
        productsRepository.save(product);
        return "redirect:/products/";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("product", product);
        return "products/edit";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return "redirect:/products/";
        }
        productsRepository.delete(productsRepository.findById(id).get());
        return "redirect:/products/";
    }

    /*

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id)
    {
        if (!productsRepository.existsById(id))
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(productsRepository.findById(id), HttpStatus.OK);
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
    }*/


}
