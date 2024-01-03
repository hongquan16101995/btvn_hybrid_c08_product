package com.example.product.controller;

import com.example.product.model.Category;
import com.example.product.model.Product;
import com.example.product.service.ICategoryService;
import com.example.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String upload;

    @ModelAttribute("categories")
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView findAll(@PageableDefault Pageable pageable) {
        return new ModelAndView("/product/list", "products",productService.findAll(pageable));
    }

    @PostMapping
    public String save(@ModelAttribute Product product) {
        MultipartFile file = product.getFile();
        if (file.getSize() != 0) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
                product.setImage(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (product.getId() == null){
            product.setImage("banh-gao.jpg");
        } else {
            product.setImage(productService.findOne(product.getId()).getImage());
        }
        productService.save(product);
        return "redirect:/api/products";
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("/product/form", "product",new Product());
    }

    @GetMapping("/{id}")
    public ModelAndView findOne(@PathVariable Long id) {
        return new ModelAndView("/product/form", "product",productService.findOne(id));
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/api/products";
    }
}
