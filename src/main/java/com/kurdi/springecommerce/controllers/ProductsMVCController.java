package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Category;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.dto.product.AddCategoryToProductDTO;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import com.kurdi.springecommerce.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Product product = productsRepository.findById(id).get();
        List <Category> categories  = productsRepository.findById(id).get().getCategories();
        List <Category> productCategories = categoriesRepository.findAll();

        model.addAttribute("product", product);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("categories", categories);
        model.addAttribute("addCategoryToCartDTO", new AddCategoryToProductDTO());
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

    @PostMapping("addToCategory/")
    public String addToCategory(AddCategoryToProductDTO addCategoryToProductDTO, BindingResult result,Model model) {
        Product product = productsRepository.findById(addCategoryToProductDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + addCategoryToProductDTO.getProductId()));
        Category category = categoriesRepository.findById(addCategoryToProductDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + addCategoryToProductDTO.getCategoryId()));
        return "products/products";
    }



}
