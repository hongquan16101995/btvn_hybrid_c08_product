package com.example.product.controller;

import com.example.product.model.Category;
import com.example.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView findAll(@PageableDefault Pageable pageable) {
        return new ModelAndView("/category/list", "categories",categoryService.findAll(pageable));
    }

    @PostMapping
    public String save(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/api/categories";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("/category/form", "category",new Category());
    }

    @GetMapping("/{id}")
    public ModelAndView findOne(@PathVariable Long id) {
        return new ModelAndView("/category/form", "category",categoryService.findOne(id));
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/api/categories";
    }
}
