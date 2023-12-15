package com.example.demo.controller;


import com.example.demo.*;
import com.example.demo.model.OrderClient;
import com.example.demo.service.Orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderContoller {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderClientRepo clientRepo;

    @Autowired
    Orderservice orderservice;
    @GetMapping("/getorders")
    public String getorders(Model model, @RequestParam(value = "page",defaultValue = "0" ) int page, HttpSession session){
       // List<Orders> ordersList = ordersRepo.findall();

        orderservice.orderbyyears((Client) session.getAttribute("client"));
        Page<OrderClient> studentBatch = this.clientRepo.findAll(PageRequest.of(page,3, Sort.by("orderyear").descending()
                .and(Sort.by("ordermonth")).descending().and(Sort.by("orderday")).descending()));
        List<OrderClient>orderClients=studentBatch.getContent();
        //    System.out.println(coffees.get(0).getImage());
        Pageable pageable = PageRequest.of(page, 3);
        //Page<Orders> studentBatch = this.ordersRepo.getAllOrdersPage(pageable);
       // ordersList = studentBatch.getContent();
        model.addAttribute("ordersRepo",ordersRepo);
        model.addAttribute("orderClients",orderClients);
        return "orderclient";
    }
}
