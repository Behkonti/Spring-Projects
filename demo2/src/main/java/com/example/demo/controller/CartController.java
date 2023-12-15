package com.example.demo.controller;


import com.example.demo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrdersRepo ordersRepo;



    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/cartpage")
    public String viewCartClient(Model model, HttpSession session) {


        List<Item>items = (List<Item>) session.getAttribute("item");
        model.addAttribute("items",items);
        return "clientcart";
    }

    @PostMapping("/putorder")
    public String putOrder(Model model, HttpSession session) {


        List<Item>items = (List<Item>) session.getAttribute("item");
        Orders orders = new Orders();
        Client client = (Client) session.getAttribute("client");
        Client client1 = clientRepo.getById(client.getId());
      //  DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
     //   String date = dateFormat.format(new Date());
        LocalDateTime localDateTime = LocalDateTime.now();
       // orders.setDate(localDateTime);
        orders.setLocaldate(localDateTime);
        orders.setClient(client1);
        ordersRepo.save(orders);

        for(Item i:items){
            Item item = new Item();
            item.setProdimage(i.getProdimage());
            item.setCoffee(i.getCoffee());
            item.setOrders(orders);
            item.setQuantity(i.getQuantity());
            itemRepo.save(item);

        }
        items.clear();
        return "clientcart";
    }
}
