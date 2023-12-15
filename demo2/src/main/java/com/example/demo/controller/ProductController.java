package com.example.demo.controller;


import com.example.demo.Coffee;
import com.example.demo.CoffeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    CoffeeRepo coffeeRepo;
    @GetMapping("/products")
    public String product(Model model,@RequestParam(value = "page",defaultValue = "0" ) int page,
                          RedirectAttributes redirectAttributes){

        Coffee coffee = new Coffee();
        List<Coffee>coffees = coffeeRepo.findall();


    //    System.out.println(coffees.get(0).getImage());
        Pageable pageable = PageRequest.of(page, 1);
        Page<Coffee> studentBatch = this.coffeeRepo.getAllCoffeesPage(pageable);
        coffees = studentBatch.getContent();
        model.addAttribute("batchRecord", studentBatch);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentBatch.getTotalPages());
        model.addAttribute("coffees",coffees);

        return "products";
    }

    @PostMapping("/filterproduct")
    public String filterproduct(@Param("price1")String price1,@Param("price2")String price2){
       double pric1 = Integer.parseInt(String.valueOf(price1));
        double pric2 = Integer.parseInt(String.valueOf(price2));
        System.out.println(pric1+"-"+pric2);
        List<Coffee>coffees=coffeeRepo.findByPrice(pric1,pric2);
        System.out.println(coffees.get(0).getCofname());
       return "index2";

    }

    @RequestMapping("/productpage")
    public String StudentBatchResult(Model model, @RequestParam(value = "page",defaultValue = "0" ) int page,
                                     RedirectAttributes redirectAttributes
                                     ) {

        Pageable pageable = PageRequest.of(page, 1);
        Page<Coffee> studentBatch = this.coffeeRepo.findAll(PageRequest.of(page,1,Sort.by("coffeeid").and(Sort.by("cofname"))));
        model.addAttribute("batchRecord", studentBatch);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentBatch.getTotalPages());

        return "Admission/all-batches";
    }
}
