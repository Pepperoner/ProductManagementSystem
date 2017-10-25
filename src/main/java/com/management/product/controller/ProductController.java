package com.management.product.controller;

import com.management.product.entity.Product;
import com.management.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.management.product.service")
@RequestMapping(value = "/admin/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("is_admin", true);
        modelAndView.setViewName("add_product");
        return modelAndView;
    }

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public String addNewProduct(
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "manufacturer", defaultValue = "") String manufacturer,
            @RequestParam(value = "description", defaultValue = "") String description,
            @RequestParam(value = "cost", defaultValue = "0") int cost
    ) {
        Product productToAdd = new Product(title, manufacturer, description, cost);
        Product productFromDb = productService.add(productToAdd);
        return "redirect:/product/" + productFromDb.getId();
    }

    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView getPageForUpdatingProduct(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.get(id));
        modelAndView.addObject("is_admin", true);
        modelAndView.setViewName("edit_product");
        return modelAndView;
    }

    @RequestMapping(
            value = "/update/{id}",
            method = RequestMethod.POST
    )
    public String update(
            @PathVariable(name = "id") long id,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "manufacturer", defaultValue = "") String manufacturer,
            @RequestParam(value = "description", defaultValue = "") String descriprion,
            @RequestParam(value = "cost", defaultValue = "0") int cost
    ) {
        Product productToUpdate = productService.get(id);
        productToUpdate.setTitle(title);
        productToUpdate.setManufacturer(manufacturer);
        productToUpdate.setDescription(descriprion);
        productToUpdate.setCost(cost);
        Product productFromDb = productService.update(productToUpdate);
        return ("redirect:/product/" + productFromDb.getId());
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.remove(id);
        return "redirect:/home";
    }

    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllProducts(){
        productService.removeAll();
        return "redirect:/home";
    }
}
