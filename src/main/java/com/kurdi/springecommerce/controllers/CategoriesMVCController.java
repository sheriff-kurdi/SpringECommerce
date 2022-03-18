package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Category;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@ApiIgnore
@Controller
@RequestMapping("categories")
public class CategoriesMVCController {
    @Autowired
    CategoriesRepository categoriesRepository;


    @GetMapping("/")
    public String get(Model model)
    {
        model.addAttribute("categories", categoriesRepository.findAll());
        return "categories/index";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("category", new Category());
        return "categories/save";
    }
    @PostMapping("/save")
    public String save(Category category, BindingResult result, Model model)
    {
        // product.setId(null);
        categoriesRepository.save(category);
        return "redirect:/categories/";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("category", category);
        return "categories/save";
    }

    @GetMapping("delete/{id}")
    public String delete( Model model, RedirectAttributes redirectAttributes, @PathVariable String id)
    {
        if (!categoriesRepository.existsById(id))
        {
            return "redirect:/categories/";
        }
        try {
            categoriesRepository.delete(categoriesRepository.findById(id).get());
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("message",
                    "Deleted Successfully");

        }catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("failed", true);
            redirectAttributes.addFlashAttribute("message",
                    "Cannot Delete Categories That Have Registered Products");

        }
        return "redirect:/categories/";
    }



}
