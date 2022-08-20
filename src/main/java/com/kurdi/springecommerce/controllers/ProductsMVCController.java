package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Category;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.dto.product.AddCategoryToProductDTOForm;
import com.kurdi.springecommerce.dto.product.ProductCategorySellect;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import com.kurdi.springecommerce.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Product product = productsRepository.findById(id).get();
        Set<Category> productCategories  = productsRepository.findById(id).get().getCategories();
        List <Category> categories = categoriesRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("categories", categories);

        return "products/details";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id){
        if (!productsRepository.existsById(id))
        {
            return "redirect:/products/";
        }
        try{
            productsRepository.delete(productsRepository.findById(id).get());

        }catch (Exception e)
        {
        }

        return "redirect:/products/";
    }

    @PostMapping("/addToCategory")
    public String addToCategory(@RequestParam String productId,@RequestParam String categoryId) {
        Product product = productsRepository.findById(productId).get();
        Category category = categoriesRepository.findById(categoryId).get();
        try {
            product.addCategory(category);
            productsRepository.save(product);
        }catch (Exception e){}
        return "redirect:/products/details/" + productId;
    }

    @PostMapping("/removeCategory")
    public String removeCategory(@RequestParam String productId,@RequestParam String categoryId) {
        Product product = productsRepository.findById(productId).get();
        Category category = categoriesRepository.findById(categoryId).get();
        try {
            product.removeCategory(category);
            productsRepository.save(product);
        }catch (Exception e){}
        return "redirect:/products/details/" + productId;
    }
/*https://www.baeldung.com/thymeleaf-list*/


}
