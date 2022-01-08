package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import com.kurdi.springecommerce.repositories.ProductsRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@Controller
@RequestMapping("products")
public class ProductsMVCController {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CategoriesRepository categoriesRepository;


    @GetMapping("/")
    public String get(Model model)
    {
        model.addAttribute("products", productsRepository.findAll());
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("product", new Product());
        return "products/save";
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
        return "products/save";
    }

    @GetMapping("details/{id}")
    public String details(@PathVariable("id") String id, Model model) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("product", product);
        return "products/details";
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



}
